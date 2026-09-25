# Turn structure and AI war: implementation-exact spec (Z==2)

Source: `decompiled/b.java` and `c.java`. Line numbers match the decoded copies in scratchpad/ and the
originals, which have the same line count. All arithmetic is Java `int` with truncating division.
`s()` = `Math.abs(ap.nextInt())` (b:1699). Where a method dispatches on `Z`, only the `Z==2` target is given.
Anything marked **[bytecode]** was checked against `b.class` with scratchpad/jar/dis.py because CFR output was
unreliable there.

Field glossary (in addition to GAME_LOGIC §0):

| field | meaning |
|---|---|
| `a[f]` | `f` if faction f is alive, `0` if dead or unused (bi() b:8167; `a[8]=8` only for a custom faction, b:10807) |
| `aB[0..7]` | per-month "slot not yet played" flags; slot k is faction k+1 |
| `aC` / `aD` | month (1..12) / year (starts at 189) |
| `aE` | loop index to resume `au()` from (0, or set by load) |
| `as` / `cP` | acting faction / last real acting faction (restored when a dead slot is drawn) |
| `ar` | player faction |
| `aG` | player's command tokens left this turn |
| `aF` | AI action counter inside `bw()` |
| `aq` | global control/return code (see each use) |
| `aO` | event id for screen 19 (text built in `c.e()`, c:3242) |
| `au` / `av` | source city / target city |
| `az[]` / `aA` | AI attacking officers / their count |
| `at` / `ay` | faction / officer argument for event texts and `at()` |
| `bG` | post-battle follow-up code consumed by `at()` |
| battle: `bg[]`,`bi`,`bu[]` | **player side** officers, count, and status |
| battle: `bh[]`,`bj`,`bv[]` | **enemy side** officers, count, and status |
| battle: `bc`,`bd` | player side gold and food |
| battle: `be`,`bf` | enemy side gold and food |
| battle: `bb` | battle city; `aZ` = player ruler; `ba` = enemy ruler; `bp` = true means the player is **defending** |
| unit status `bu`/`bv` | 0 active, 1 left the field (retreated), 2 captured by the other side, 3 killed, >=4 active with a status effect (countdown) |

Faction to ruler at start (`lib114` = extract/data/rulers.csv, `b[f]`; city counts match cities.csv col1):

| f | ruler id | name | start cities |
|---|---|---|---|
| 1 | 2 | 献帝 | 2 |
| 2 | 35 | 曹操 | 6 |
| 3 | 93 | 袁绍 | 6 |
| 4 | 122 | 孙坚 | 6 |
| 5 | 196 | 吕布 | 4 |
| 6 | 150 | 刘焉 | 5 |
| 7 | 173 | 刘表 | 5 |

Cities 16 and 36 start empty (`i==0`, `u==0`).

---

## 1. Turn structure

### 1.1 Top level (b:6942–6976)

```java
run():                       // b:6956
  for k in 0..7: aB[k] = 1
  q(); p();                  // load data + title screen loop; p() returns when aq>0
  if (aq == 1) aq = 0;       // new game. Load-from-title: d(byte) b:1686 -> bi(); aq=2
  aG();                      // -> aF()
  (exceptions are caught here; the game thread just ends)

aF():  while (true) { aD(); if (aq != 0) return; aE(); }        // aE() = new year

aD():                        // b:6860, the month loop
  while (aC <= 12) {
    au();                    // one "month" = 8 faction slots
    if (aq != 0) return;
    aE = 0;                  // resume index only matters for the first month after a load
    aC();                    // month end; increments aC
    if (aq != 0) return;     // game over (aq=1)
  }
```

`aE()` (b:6894) is the new year. It sets `aC = 1` and `aD++`, then `l(aD)`: every officer with `T[s]==aD` gets
`J[s]=0` and is appended to `B[U[s]]` (the 在野 list of city `U[s]`, only if `U[s]>0`). Then it runs the 26
scripted joins in `aa[]/ab[]/ac[]`. When one fires in a player city, it shows event 12 and waits for a key.

### 1.2 Faction draw `t()` (b:1714)

```java
void t() {
  int n = s() % 8 + 1;          // 1..8, ONE RNG call
  int k = 0;
  while (n > 0) {
    n -= aB[k];                 // only slots still available (aB==1) count
    if (n == 0) break;
    if (++k == 8) k = 0;        // wraps around cyclically
  }
  aB[k] = 0;
  as = a[k + 1];                // 0 if that faction is dead or unused (slot 8)
}
```

This picks the `((n-1) mod m)`-th remaining slot in index order, where m is the number of remaining slots. It
always scans from k=0. Dead factions and the unused slot 8 are still drawn and consume a slot. They just
produce `as==0`.

### 1.3 The slot loop `au()` (b:6344)

```java
void au() {
  byte by = aE;
  while (by < 8) {
    if (aq == 0) t();           // skipped when aq==4 (continue after a battle) or aq==2 (just loaded)
    if (as <= 0) {
      as = cP;                  // dead or unused slot: nothing happens, no delay, UI keeps the last name
    } else {
      cP = as;
      if (as == ar) {                         // ---- PLAYER ----
        ao();                                 // returns when aq>0
        if (cO) { by = aE; cO = false; }      // a save was loaded during the turn (bj(), b:8180)
        if (aq == 3) aq = 0;                  // normal end (tokens exhausted)
        if (aq == 4) {                        // player launched an attack
          al(); by--; at();                   // battle; by-- replays this slot
          if (aq == 99) { aq = 0; return; }   // player eliminated
        }
      } else {                                // ---- AI ----
        ao.repaint(); ao.serviceRepaints();   // map now shows "<ruler> 战略中....."
        long t0 = now(); aq = 0;
        bw();                                 // AI turn
        if (aq == 3) {                        // AI attacked a player city
          an(); by--; at();                   // defense battle; by-- replays this slot
          if (aq == 99) { aq = 0; return; }
        }
        long dt = now() - t0;
        if (dt < 2000) sleep(2000 - dt);      // each AI slot lasts at least 2 s (key waits count)
      }
    }
    by++;
  }
}
```

How the replay after a battle works: `at()` always leaves `aq = 4` unless the player is eliminated (aq=99).
On the replayed iteration `t()` is skipped, so `as` is unchanged.

* **Player:** `ao()` is entered with aq==4. It sets aq=0 and calls `aL()`, which uses up the token for the war
  command. The player's turn then continues with the tokens left. Tokens are **not** reset.
* **AI:** `aq=0; bw()` runs again. `bw()` reset `aF=0` when it broke out for the attack (b:9794), so the AI
  faction **starts a full new turn** with a fresh budget of `e(as)` actions. See bug B2.

`aq==99` returns out of `au()` with aq=0. `aD()` then calls `aC()`, where `aA()` (c[ar]==0) ends the game.
Factions still waiting that month do not act.

Load bookkeeping (`bi()`, b:8094–8110): `aE = (number of aB==0) - 1`, `as = ar = saved`, `cP = ar`. The save is
taken during the player's turn, when the player's slot is already 0. So the resumed loop has exactly
`8 - zeros` iterations left.

### 1.4 Player turn `ao()` (b:6154)

```java
if (aq == 0) {                 // fresh turn
  aG = e(ar);                  // tokens = clamp(c[ar], 3, 8)   (e(byte) b:1726)
  bP = 0; bQ = 0;
  u();                         // au = aw = first player city with i[c]==j[c] (ruler is governor); unchanged if none
  ao.m(); ao.repaint(); ao.f();
} else if (aq == 2) { aK(); aq = 0; }   // just loaded: open the city menu (N(6))
else if (aq == 4) { aq = 0; aL(); }     // back from own battle: charge the war token
loop every 100 ms: route keys to ao.f(); return when aq > 0
```

`aL()` (b:7415) runs after every finished command. It calls `aJ()` (resets bP, bQ, aH=aI=true, aw=au), then
`aG--`. If `aG <= 0`, it sets `bP=0; aq=3; N(4)`: the turn ends and the map is shown. Otherwise, if the current
city was lost (`i[au]!=b[ar]`), it calls `u()`, then `N(6)`. There is no separate end-turn command. The turn
ends only when the tokens run out.

### 1.5 AI turn `bw()` (b:9747). This part is only what matters for war

```java
byte budget = e(as);                            // clamp(c[as], 3, 8)
while (aF < budget) {
  aF++; bP = -1; au = 0; av = 0;
  ag(as);                                       // as(as) b:11699: aV[] = frontline cities (ai(c): a neighbour has another owner), aW[] = the rest
  int n = cb();                                 // b:11723 attack evaluation
  if (bP == 3) {
    n = C(n);                                   // b:11593 attack-probability roll; may set bP=-1
    if (bP == 3) { t(n); if (aq != 3) continue; break; }
  }
  bB(); if (bP == 2) { bD(); continue; }        // other AI actions (out of scope)
  n = bF(); if (bP == 1) { v(n); continue; }
  bH(); if (bP == 19) { bJ(); continue; }
  bL(); if (bP == 8) { bM(); continue; }
  n = bO(); if (bP == 12) { x(n); continue; }
  bQ();
}
aF = 0;                                         // also runs after the break for an attack on the player
```

If the attack roll fails, the same iteration falls through to the other action checks. Only `t(int)` ever sets
`aq=3` in AI code (b:9795–13741 contain no other `aq=` writes).

`cb()` target choice (Z==2):

```java
int n = 999; boolean allied = bx();          // bx(): as is in the player's alliance list X[0..W-1]
for c in aV[0..aX-1] with r[c] > 2:
  for t in D[c][0..E[c]-1]:
    if (i[c]==i[t] || (allied && i[t]==b[ar])) continue;
    int v = z(c, t);                          // b:11717
    if (v > n) { n = v; au = c; av = t; }     // strict >; first maximum wins
if (n >= 1000) bP = 3;
return n;

int z(src, dst) {
  int food = m[src] / 4 * 1000 / 45;          // troops the food can support
  int v = (food >= u[src] ? u[src] : food) - u[dst];
  return v + (r[src] - r[dst]) * 200;          // no penalty for empty targets in Z==2
}

int C(int n) {                                // n = margin
  n /= 1000;                                  // n >= 1
  int p = (n==1) ? 20 : (n==2) ? 50 : (n==3) ? 70 : 80;
  if (s() % 100 > p) bP = -1;                 // attack chance 21% / 51% / 71% / 81%
  return n;                                   // t() receives margin/1000
}
```

### 1.6 What the screen shows during AI turns

* **Map screen.** It is screen 4, painted by `c.V()` (c:6570). The title is `"{aD}年{aC%13}月"` (c.W, c:6624). While
  `as != ar`, the status line at bottom centre (c.d, c:6744) is `G[b[as]] + " 战略中....."`. The player's
  cursor is drawn only when `as==ar` (or bP==112).
* **Events.** An AI event calls `N(19)`, which opens screen 19 (`c.a(19)` builds the text with `c.e()` and resets
  the key state). It then calls `ax()` (b:6440), which is `bv()` plus `N(4)`. `bv()` (b:9666) blocks until a key
  is pressed; nothing advances automatically. Events with `aO<14` are full-screen, with a picture `k(120..136)`
  and frame `k(11)`. Events with `aO>=14` are a text box, `k(20)`.
* **Delays.** Every `t(int)` in branches (b) and (c) ends with `Thread.sleep(500)` (b:9735). Every AI slot lasts at
  least 2000 ms. Dead slots have no delay.
* **Popups from `at()`.** `a(byte,short,short)` → `c.a(byte,short,short)` (c:3360) draws a popup on the map. Any
  key closes it: `c.k()` c:6051 sets `bp=false; aq=1`, `ap()` returns, then `aq=0`.

### 1.7 Month end `aC()` (b:6719)

Runs after all 8 slots:

```
aS = 0
if az() (all 36 cities owned by b[ar])  -> aO=0; N(23); ap(); aq=1; return      // win
if aA() (c[ar]==0)                      -> aO=1; N(23); ap(); aq=1; return      // loss
aC++                                                   // the checks below use the NEW month
disasters: for city 1..36: if av() (aC in 3..10 and s()%500<4) add to aR[]
  if none: for city 1..36: if aw() (s()%1000<=1) add          // plague candidates
  else: D(city) for each; aO = s()%3+6 (6/7/8); N(19); ax(); aS=0
  if aS>0 (plague): E(city) for each; aO=9; N(19); ax(); aS=0
riots: for city 1..36 with i>0: if F(city) add; if any: H(city) each; aO=13; N(19); ax()
if Z>=2: aB(): every alive non-player faction: K(city) growth for each city
aC==4 -> I(city) gold income for all 36; aO=10 event.   aC==10 -> J(city) food; aO=11 event
HP: every officer 1..231: if L<K then L += K/10, capped at K
alliances: for s = W-1 downto 0: Y[s]--; if Y[s]<=0: (if X[s] in 1..7: aO=14, at=X[s], event)
           X[s]=X[W]; Y[s]=Y[W]; W--          // see bug B12
ay()   // year>=210 special buffs to officers 55/193 in cities 14/2 when the player owns them
aB[0..7] = 1                                  // per-month slot reset
```

The end screen 23 text is `G[b[ar]] + d.j[aO]`:
* aO=0: 「 经过多年征战,终于实现了宇内混一!」
* aO=1: 「 势力被消灭了,全国局势仍处于混乱之中...」

### 1.8 Summary of per-turn and per-month resets

| when | reset |
|---|---|
| each draw | `aB[slot]=0` |
| month end | `aB[*]=1`, `aE=0` |
| player fresh turn | `aG=e(ar)`, `bP=bQ=0`, `au` = capital |
| each player command | `aJ()` |
| AI turn start | budget `e(as)` |
| AI turn end | `aF=0` |
| each AI action | `bP=-1`, `au=av=0`, frontline lists recomputed |
| new year | `aC=1` |

---

## 2. AI war execution `t(int n)` (b:9685)

`n` is margin/1000 (>=1). `au` is the source city and `av` the target city, both set by `cb()`.

```java
void t(int n) {
  aA = e(az, n);                          // pick attacking officers (b:11670), removes them from au
  int food = c(az, aA);                   // b:9225
  int gold = (l[au] < 2) ? 0 : s() % (l[au] / 2);
  l[au] -= gold;
  m[au] -= food;
  if (i[av] == b[ar])  { ...branch (a)... return; }
  if (i[av] == 0)      { ...branch (b)... }
  else                 { ...branch (c)... }
  u[av] = sumJ(A[av], r[av]);
  sleep(500);
}
```

### 2.1 Officer selection `e(short[] az, int n)` (b:11670)

```java
byte[] avail = {1,1,1,1,1,1,1,1,1,1};
byte left = r[au];
int acc = -(u[av] + r[av] * 200);          // defender strength (0 for an empty city)
byte cnt = 0, k = 0;                       // k is NOT reset between picks
int thr = (s() % n) * 1000;                // RNG #1
while (left > 1) {                          // at least one officer always stays
  int c = 0, want = s() % left + 1;         // RNG per pick
  while ((c += avail[k]) != want) { if (++k == r[au]) k = 0; }  // the want-th available slot, counting on from the last pick
  az[cnt] = A[au][k]; A[au][k] = 0;
  acc += J[az[cnt]];
  cnt++; avail[k] = 0; left--;
  if ((acc += 200) >= thr) break;
}
r[au] = left;
ar(au);     // b:11638: if A[au][0]==0 (governor left): compact, then new governor = highest P (loyalty), moved
            // to the front (c(byte,short[],byte) b:11618); otherwise only compact. Both set u[au].
e(az, cnt); // b:11647: if the ruler i[au] is among them, swap him to index 0; otherwise the first officer with
            // the highest N (智) goes to 0 (swap with az[0])
return cnt;
```

So the AI keeps adding officers until (sum of J) + 200 × count ≥ u[av] + 200 × r[av] + a random surplus of
0..(n-1)×1000.

### 2.2 Supplies

```java
int c(short[] az, int cnt) {                 // food carried
  int t = sumJ(az, cnt) * 4 / 1000 + 1;
  int f = s() % (t * 30) + t * 30;
  if (f < m[au] / 2) f = m[au] / 2;          // always takes at least half the city's food
  else if (f >= m[au]) f = m[au] * 2 / 3;
  return f;
}
```

RNG order for all of t(): threshold, then one roll per pick, then the food roll, then the gold roll (only if
`l[au] >= 2`).

### 2.3 Branch (a): target is a player city (b:9694–9702)

```java
aO = 1; N(19); bv();            // "{G[i[au]]} 军攻打 {h[av]}..." + wait for key (no N(4))
be = gold; bf = food;           // enemy-side supplies for the battle
aq = 3; return;                 // no u[av] update, no sleep
```

Then `bw()` breaks and sets `aF=0`. `au()` sees `aq==3` and calls `an()`, then `by--`, then `at()`.

#### `am()` defense setup (b:6045)

| field | value when the player defends | value when the player attacks (`z()`, b:1950) |
|---|---|---|
| `aZ` | `i[bb]` (the player ruler) | `b[ar]` |
| `ba` | `b[as]` (AI ruler) | `i[av]` |
| `bb` | `av` | `av` |
| `bg[0..bi-1]` (player side) | `A[bb][0..r[bb]-1]`: **every** officer in the city, governor first | the chosen `az` |
| `bh[0..bj-1]` (enemy side) | the AI's `az[0..aA-1]` (leader = ruler or highest 智) | `A[bb][]` |
| `bc`, `bd` | `l[bb]`, `m[bb]`: the city's whole treasury (not deducted from the city during battle) | carried, set earlier |
| `be`, `bf` | AI gold/food carried (set in t()) | `l[bb]`, `m[bb]` |
| `bp` | **true** | false |

Common initialisation: `bu[]=bv[]=0`, `bw=bx=by=bz=1`, `bA=0` (day), `bk=0`, `bl=0`, `bB=true`, `bC=-1`,
`bk = a(bg,bi,bu,bk)` (player mobility pool), then `y()`.

`y()` (b:1931): `v()` (build the battlefield `bm` for `bb`), then `bp ? x() : w()`, then `ao.a(20)` (battle
screen), then `cM = new byte[bj]` (zeroed) and `cN = 0` (Z>1). Last come `ao.b(); ao.a(); ao.j()`.

#### Defensive deployment `x()` (b:1872)

```java
// find the city tile (terrain == 2)
for (x = 0; x < 31; x++)                      // note: 31, w() uses 32
  for (y = 0; y < 19; y++)
    if (bm[y][x] == 2) {
      cx = x; cy = y;
      if (x > 15) { bw = -1; by = 0; }
      if (y > 10) { bx = -1; bz = 0; }
      break;                                   // inner loop only; keeps scanning columns
    }
// => the top tile of the RIGHT-MOST column that contains a city tile
bq[0] = cx; br[0] = cy; bu[0] = 0; bm[cy][cx] |= 0x40;           // player leader ON the city tile
for k in 1..bi-1:
  do { X = (s()%2)*bw + cx;  Y = (s()%5)*bx + cy; } while (f(bm[Y][X]) > 0);  // RNG: X then Y per try
  bq[k]=X; br[k]=Y; bm[Y][X] |= 0x40; bu[k]=0;
for k in 0..bj-1:                                                   // attackers 8–9 columns away
  do { X = (s()%2 + 8)*bw + cx; Y = (s()%5)*bx + cy; } while (f(bm[Y][X]) > 0);
  bs[k]=X; bt[k]=Y; bm[Y][X] |= 0x80; bv[k]=0;
```

`f()` (b:1799) tests only occupancy (0x40 = friendly, 0x80 = enemy), not terrain. Units can therefore be placed on
any terrain. The 2×5 blocks always have room: 9 free tiles for the player's 9 others, and 10 for up to 9 attackers.

#### Battle loop `an()` (b:6091)

`an()` is the same as `al()` (b:5985), except that it calls `am()` instead of `z()`. The `bp` flag changes only
these places:

1. **Deployment:** `x()` vs `w()` (b:1933).
2. **Enemy phase each day:** `R()` (b:3911) calls `bp ? Q() : O()` (b:3942). With Z==2, `Q()` is `bY()` (b:11293),
   the attacking AI. `cN` counts its phases: for the first 3 it uses `a(...)` to target units within 5 tiles,
   after that `c(...)`. After each enemy unit moves, `k(unit)` (b:3833) checks
   `(bm[bt][bs] & 0xF)==2`. If an enemy stands on the city tile: W=21 「敌军占领我方城池!」, `E()`. That
   leads to the retreat flow below, so **the player loses the city**.
3. **Loss handling:** `C()` runs before `D()` when the player loses (b:2993, 5814, 5896).

Daily upkeep in `R()`, the same in both directions:
`bd -= (A(bg)-1)/250+1`. If `bd<=0`: W=7 「我军粮草耗竭,全军撤退!」 and the player loses.
`bf -= (A(bh)-1)/250+1`. If `bf<=0`: W=6 「敌方粮草耗竭,已全军逃窜!」 and the player wins.
After `bA` reaches 30: W=20 「战争回合结束！」. `c.i()` (c:4474) sends W 7/9/20/21 to `b.A()`, the **player
retreats**, and W 5/6/22 to `b.L()`, the **player wins** (c:4466). The enemy flees
(W=5 「敌方全军逃窜!」) when `bW()` (b:10913) is true:
`a(bb,ba)` (the enemy has a retreat city) `&& (H() < G()/4 || (J[bh[0]]<100 && L[bh[0]]<40))`, where
`G/H = Σ(500+J)` over each side's active units.

#### After the battle: the player LOST the city (defense)

This is reached from W=7/20/21, or when our leader `bg[0]` is killed or captured (`a(short,true)` b:5779 /
`b(short,true)` b:5864).

* When `bg[0]` is killed or captured, these functions set `bE=true` (never reset, bug B7). They set `bu[0]=3`
  (killed) or `2` (captured). If `bg[0]==aZ` and he was killed, they set `bG=2`. Enemy officers we had
  captured (`bv==2`) are released (`→0`). The retreat then starts at the first remaining active `bg[k]`.
* `A()` (b:2766): `ax=bg[0]; j(bb)`. The retreat list `aR[0..aS-1]` holds each neighbour `D[bb][*]` that is
  either (owned by `aZ` and `r<10`) or empty (`i==0`) (b:3005).
  * If `aS==0 && ax==aZ`, the ruler is killed via `a(ax,true)` (`bG=2`).
  * Otherwise `bC=0` and the retreat-city picker opens.
* **Player's choice (c:3106 draw, c:5083 cursor, c:4880 confirm).** Officers are asked one at a time in `bg` order
  (skipping killed, captured, and already-retreated ones). For each, the player picks a city from `aR` or
  「下野」. 「下野」 is hidden when `P[ax]==100`.
  `i(byte by)` (b:2942):
  * `by>0` → `a(ax, by, aZ)` (b:2827): append to the city, `u += J`. An empty city is claimed for the player
    (`d[h(aZ)]` append, `i=aZ`, `j=ax`). If `ax==aZ`, he becomes governor (`h(by)` moves him to the front).
  * `by==0` (下野) → appended to `B[bb]` (在野 list of the lost city).
  * The first mover (`bC==0`) carries `bc/bd` (the city's treasury) into his city, capped at 9999.
* After our officers, each enemy officer we captured (`bv==2`) also goes through the picker (`m()` loyalty
  re-roll if `aS>0`). This is how the player takes captives home.
* When nobody is left: `if (bp) C(); D(); aq=4`.
* `C()` (b:2865): `c[ar]--`; remove `bb` from `d[ar]` (buggy, see B6); `d[as][c[as]++]=bb`;
  `i[bb]=b[as]`; `j[bb]=bh[0]`.
* `D()` (b:2889):
  * Compact `bh` to units with `bv==0 || bv>3`.
  * Our captured officers (`bu==2`) join the AI (`m()` re-roll, `bh[bj++]`), or go to `B[bb]` if `bj==10`.
  * `l[bb]=be; m[bb]=bf` (the AI's leftover carried supplies become the city's stock). If `bE`, add `bc/bd`
    as well (cap 9999).
  * `A[bb]/r[bb]/u[bb] = bh` (`a(short[],byte)` b:3241).
  * If `bG>1` (our ruler died): W=8 「君主战亡!」 (c.f sets aq=1), `E()`, `aq=0`; if `c[ar]<=0` then `bG=3`.

#### After the battle: the player WON (defense)

`c.i()` → `L()` (b:3385) → `K()` (b:3251).

* **`K()`:**
  1. Keep active `bg`. Collect our captured officers (`bu==2`) into `S`.
  2. `bl = (bv[0]==2 || bv[0]==3 || J()<0)`. `J()` (b:3198) picks a retreat city at random for the enemy:
     a neighbour of `bb` that is (owned by `ba` and `r<10`) or empty. Z==2: prefer the enemy's own cities
     (`c(short,byte[],byte,byte)` b:10943, one RNG call). Return -1 if there is none.
     If `bl` and `P[bh[0]]==100` (treated as the ruler): `at=h(ba); bG=1; bv[0]=3` (the enemy ruler dies).
  3. Surviving enemies (`bv==0||>3`) each call `J()` in turn and retreat there (`a(short,byte)` b:3230 →
     `a(s,city,ba)`). The first one carries `be/bf` if `!bl`. If `J()<0` part-way, all remaining enemies
     become our captives.
  4. Our captured officers are taken by the retreating enemy (`m()` re-roll, `a(s, J())`). If `J()<0` here the
     loop never ends (bug B8).
  5. Enemy captives (`bv==2`) join `bg` (`m()` re-roll), or go to `B[bb]` if `bi>=10`.
  6. `A[bb] = bg`; `m[bb]=bd; l[bb]=bc`; if `bl`, add `be/bf` (cap 9999). `aq=4`.
* **`L()`, rest:** moves `bb` from faction `h(i[bb])` to `h(aZ)`. When defending these are the same faction, so
  `bb` just moves to the end of `d[ar]`. It sets `i[bb]=b[h(aZ)]` and `j[bb]=bg[0]`.
  `if (c[by]==0) { bG=4; a[at]=0; }` is never true when defending.

#### `at()` after the battle (b:6311)

`ao.a(4)` (map), `aq=0`, then:

| `bG` | what happens |
|---|---|
| 1 (enemy ruler died) | `s=b[at]`; `C(at)` → `aq(at)` succession (§2.5, "Succession"); popup 6 「{G[s]}死亡,新君主 {G[b[at]]} 继位!」 |
| 2 (our ruler died, cities left) | popup 2 「{G[s]}　君主死亡!」; `as()` (b:6289): the player picks the heir in a UI, `a(ar, au, ax)`; popup 6 |
| 3 (player eliminated) | `aG=1`; popup 5 「{G[b[ar]]} 势力灭亡了!」; `aq=99`; return |
| 4 (faction eliminated, player-attack path only) | popup 5 「{G[b[at]]} 势力灭亡了!」 |

Afterwards `bG=0` and `aq=4`.

**Does the AI get another round? Yes.** `by--` replays the slot, and `aq==4` skips `t()`, so `as` is still the AI.
`bw()` runs again from `aF=0` with a fresh `e(as)` budget. The AI can attack the player again in the same month,
with no limit (bug B2).

### 2.4 Branch (b): target is an empty city (b:9704–9708)

```java
aO = 2; N(19); ax();               // "{G[i[au]]} 占领空城 {h[av]}!" + key
a(az, aA, food, gold);             // b:9238
  // d[as][c[as]++] = av;
  // A[av][0..aA-1] = az; r[av] = aA; i[av] = i[au]; j[av] = az[0];
  // food/gold parameters are NOT used (bug B4); the city keeps its old l/m
u[av] = sumJ(...); sleep(500);
```

### 2.5 Branch (c): AI vs AI auto-resolve (b:9709–9731)

```java
short s = i[av]; byte df = h(s);                    // defender ruler / faction
aO = 1; N(19); ax();                                // "{G[i[au]]} 军攻打 {h[av]}..."
aO = d(az, aA, food, gold) ? 4 : 5; N(19); ax();    // 4 "{G[i[au]]} 军攻占了 {h[av]}!"  5 "...{h[av]}失败了!"
if (a[df] <= 0) { ay = s; at = df; aO = 18; N(19); ax(); }  // "{G[s]} 势力灭亡了!"
else if (b[df] != s) { ay = s; at = df; N(19); ax(); }      // meant to be aO=19 (bug B3): shows event 4 again
u[av] = sumJ(A[av], r[av]); sleep(500);
```

#### Strength and outcome `d(short[] az, byte cnt, int food, int gold)` (b:9636)

There is **no day loop**. The battle length is a single "days" roll.

```java
int pw(short o) {                                   // s(short) b:9617
  int v = (N[o] + M[o]) * (J[o] + 150);
  v = tier(N[o], v); v = tier(M[o], v); return v;
}
int tier(byte x, int v) {                           // c(byte,int) b:9252
  return x > 95 ? v*2 : x > 90 ? v*18/10 : x > 85 ? v*15/10 : x > 50 ? v*12/10 : v;
}
int atk = Σ pw(az[k]);
int def = Σ pw(A[av][k]);
short g = A[av][0];                                 // governor
int gb = tier(M[g], M[g] * J[g] * 3 / 2);           // governor bonus
def += gb;
int diff = atk - def;
int ratio = diff > 0 ? 300*atk/def : 300*def/atk;   // 300 = parity
if (ratio > 1000) ratio = 1000;
food -= consume(az, cnt, av, ratio); if (food < 0) food = 0;    // attacker running out has no effect
if (m[av] < 0)       { m[av] = 0; starved(az, cnt, food, gold); }        // true
else if (diff > 0)   { win(az, cnt, food, gold, ratio); }                 // true
else                 { lose(az, cnt, food, gold, ratio); return false; }  // a tie is a loss
return true;

int consume(az, cnt, av, ratio) {                   // a(short[],byte,byte,int) b:9268; one RNG call
  int days = ratio > 600 ? s()%5+6 : ratio > 450 ? s()%10+11 : s()%10+21;   // 6–10 / 11–20 / 21–30
  m[av] -= u[av] * 4 * days / 1000;                 // defender eats (cached u[av])
  return sumJ(az, cnt) * 4 * days / 1000;           // attacker's consumption
}
```

#### Casualties `b(short o, int cap)` (b:9277). `cap` limits the troops **remaining**

```java
if (J[o] > 0) {                          // RNG only if J>0
  if (J[o] < cap) cap = J[o];
  int lo = cap - 300; if (lo < 0) lo = 0;
  int v = s() % 301 + lo;                // [cap-300, cap]
  if (v < J[o]) J[o] = (short) v;
}
if (L[o] > 0) L[o] = (byte)(s() % L[o] + 1);     // HP -> 1..L
```

| outcome | defenders (`A[av]` in order) | attackers (`az` in order) | then |
|---|---|---|---|
| `win` (b:9553) | `b(o, 500)` | `b(o, ratio<700 ? 700 : ratio)` | `capture` |
| `lose` (b:9585) | `b(o, ratio<700 ? 700 : ratio)` | `b(o, 500)` | attackers retreat: `retreat(az, cnt, i[au], food, gold)` (**return value ignored**, b:9614) |
| `starved` (b:9533) | `b(o, 1000)` | `b(o, 1000)` | `capture` |

The winner's cap is `max(700, ratio)`, which is at least 700. The loser's cap is 500. Casualties are always
rolled defenders first, then attackers. Experience comes next, in this order:

```java
expAtk = defTroopsBefore - defTroopsAfter;  share(az, cnt, expAtk);     // c(short[],byte,short) b:9307
expDef = atkTroopsBefore - atkTroopsAfter;  share(A[av], r[av], expDef);
share(arr, n, pts): for k in 0..n-2: { if (pts<=0) {pts=0; continue-without-RNG}
                                       x = s()%pts; pts -= x; Q[arr[k]] += x; f(arr[k]); }
                    Q[arr[n-1]] += pts; f(arr[n-1]);       // f(short) b:2721: level-up from Q thresholds
```

#### Capture `capture(az, cnt, food, gold)`: `b(short[],byte,int,int)` (b:9495)

```java
int n3 = r[av]; short s = i[av]; byte df = h(s);
short[] old = copy(A[av][0..n3-1]);
bu();                                       // b:9318: d[h(i[au])][c++] = av; c[df]--; swap-remove av from d[df]
A[av][0..cnt-1] = az; r[av] = cnt; i[av] = i[au]; j[av] = az[0];
int oldGold = l[av]; l[av] = gold;
int oldFood = m[av]; m[av] = food;
boolean rulerLost = retreat(old, n3, s, oldFood, oldGold);
if (rulerLost) { if (c[df] > 0) C(df) /* → aq(df) */; else a[df] = 0; }   // succession or elimination
if (a[df] > 0) ag(a[df]);                   // recompute frontline lists (for df, but they are global)
```

#### Retreat/capture routine `retreat(off[], cnt, ruler, food, gold)` (b:9420)

This is **[bytecode]**-verified. CFR output was unstructured. The "winner city" is always `av`.

```java
byte nc = 0; byte[] cand = new byte[E[av]];
int captured = 0; byte firstDest = 0; boolean rulerLost = false;
for k in 0..E[av]-1: { c = D[av][k]; if (i[c]==0 || (i[c]==ruler && r[c]<10)) cand[nc++] = c; }
for idx in 0..cnt-1:
  short o = off[idx];
  if (nc == 0) {                                   // nowhere to go
    if (idx == 0) { captured = 1; if (o == ruler) { rulerLost = true; continue; } }  // ruler just vanishes (dies)
    if (r[av] == 10) p(o);  else r(o);             // p: append to B[av] (在野); r: join the winner (q(): m() re-roll...)
    continue;
  }
  if (r[av] == 10) {                               // winner's city full: forced retreat
    dest = pick(ruler, cand, nc); if (idx==0) firstDest = dest;
    nc = moveTo(o, cand, nc, dest, ruler); continue;
  }
  if (L[o] <= 35 && J[o] <= 150) {                 // weak: capture roll
    int th = N[o]>=95 ? 10 : N[o]>=90 ? 20 : N[o]>=80 ? 40 : 80;
    if (s() % 100 <= th) {                         // captured (a HIGHER 智 means LESS chance)
      if (idx == 0) {
        if (o == ruler) {                          // a ruler is never captured, he retreats
          dest = pick(ruler, cand, nc); firstDest = dest;
          nc = moveTo(o, cand, nc, dest, ruler); continue;
        }
        captured = 1;
      }
      r(o); continue;                              // joins the winner in av
    }
  }
  dest = pick(ruler, cand, nc); if (idx==0) firstDest = dest;
  nc = moveTo(o, cand, nc, dest, ruler);
if (captured > 0) addSupplies(av, food, gold);     // a(byte,int,int) b:9404: l+=gold, m+=food, cap 9999
else              addSupplies(firstDest, food, gold);
return rulerLost;
```

Helpers used by the routine:

* **`pick`:** `c(short,byte[],byte,byte)` (b:10943). It prefers the retreating side's own cities, chosen at
  random (`s()%count`). If there are none, it picks a random candidate (an empty city). It makes one RNG call.
* **`moveTo`:** `a(short,byte[],byte,byte,short)` (b:9361).
  * If the city is empty, the faction claims it: `d[h(ruler)]` append, `i=b[h(ruler)]`, `j=o`, `r=1`,
    `A[0]=o`, `u=J[o]`, and `nc` is returned unchanged.
  * Otherwise, if `o==ruler`, A is shifted right, `A[0]=o`, and `j=o`. If not, `o` is appended.
  * `r++`, `u+=J[o]`. If `r==10 && --nc != 0`, the city is swap-removed from `cand`.
* **`r(o)`:** (b:9354) `q(o)` then `A[av][r[av]++]=o`, with no `u` update.
  * `q(o)` = `m(o)`; if `P<70`, `P+=30`; then if `P<40`, `P+=15`.
  * `m(o)` (b:7665) is a captive's loyalty re-roll and uses exactly one RNG call:
    `n=100-P; h=(n+50)/2;`
    * `n==h` → `P = n + (s()%3-1)`
    * `P>50` → `P = n + s()%(h-n)`
    * otherwise → `P = h + s()%(n-h)`

    Loyalty is roughly inverted: P=90 becomes 10..29, and P=20 becomes 65..79.

RNG per retreating officer: one capture roll if weak; one `pick` roll if he retreats; one `m()` roll if he
joins the winner.

#### Succession `aq(byte f)` (b:11563)

This applies to the Z==2 dispatch `C(byte)` (b:6237).

```java
int best = -1; short bs = 0; byte bc = 0;
for (p = 0; p < e[f]; p++)                       // priority list f[f][0..e[f]-1]
  for each city c in d[f][0..c[f]-1]
    for each officer o in A[c][0..r[c]-1] {
      if (N[o]+M[o]+O[o] > best) { best = N+M+O; bs = o; bc = c; }    // fallback: highest 智+武+德
      if (o == f[f][p]) { a(f, c, o); return; }
    }
a(f, bc, bs);
// a(byte,byte,short) b:6196: i[every city of f] = o; c(o, city) (move to front, j=o); P[o]=100; b[f]=o
```

The lists are hard-coded in `b()` (b:1289–1305). Names come from officers.csv, where id = row index; `F[s]=s`
at b:7168.

| f (ruler) | e[f] | priority order |
|---|---|---|
| 1 献帝 | 5 | 161 诸葛亮, 5 关羽, 6 于吉, 181 魏延, 200 马腾 |
| 2 曹操 | 11 | 211 曹丕, 225 刘氏, 215 越吉, 213 庞德, 217 蔡瑁, 56 曹仁, 58 曹洪, 204 程银, 210 韩遂, 63 夏侯惇, 57 夏侯渊 |
| 3 袁绍 | 3 | 37 荀彧, 8 张飞, 34 刘晔 |
| 4 孙坚 | 5 | 191 高定, 138 程普, 201 华雄, 155 法正, 208 马岱 |
| 5 吕布 | 6 | 26 关统, 25 邓芝, 18 文聘, 19 刘封, 28 赵统, 21 蒯越 |
| 6 刘焉 | 1 | 40 陈泰 |
| 7 刘表 | 5 | 140 朱桓, 2 献帝, 172 张鲁, 190 董茶奴, 160 张任 |

Where these officers start (col14 = city, col13 = debut year):

* Faction 2's list is mostly correct (曹仁 and 曹洪 are in 曹操 cities; 夏侯惇 and 夏侯渊 debut in 289, so never).
* The other lists point at other factions' officers. Faction 3 (袁绍) has 荀彧 (a 曹操 city) and 张飞/刘晔
  (献帝 cities). Faction 5 (吕布) has 献帝's officers. Faction 7 (刘表) lists 献帝 himself.
* The lists only match if those officers have been recruited by then. In practice the fallback, highest 智+武+德
  within the faction, usually decides. It looks like the lists were written for a different roster or ID
  numbering (bug B9).

The player's own succession never uses `aq()`. It goes through `bG=2` → `as()` and the player chooses.

#### Elimination

* **AI vs AI:** a faction is marked dead (`a[df]=0`) only inside `capture`, when its ruler was lost **and** it
  has no cities left (b:9525). Then event 18 is shown.
* **Turn loop:** a dead faction's slot is still drawn by `t()`, yields `as=0`, and is skipped (§1.3).
* **Player:** via `bG=3` (ruler died and `c[ar]<=0`) → `aq=99` → month end `aA()` → end screen, aO=1.
  If the player loses the last city without the ruler dying (only possible through bug B11), the loss is only
  noticed at the next `aC()`.

---

## 3. Event and message texts on these paths

### Screen 19: `c.e()` (c:3242)

The text is built when `N(19)` is called. Picture = `k(id)`.

| aO | pic | text |
|---|---|---|
| 1 | 120 | `G[i[au]]` + 「 军攻打 」 + `h[av]` + 「...」 |
| 2 | 121 | `G[i[au]]` + 「 占领空城 」 + `h[av]` + 「!」 |
| 3 | 120 | `G[0]` + 「 军攻打 」 + `h[0]` + 「...」 (unused) |
| 4 | 122 | `G[i[au]]` + 「 军攻占了 」 + `h[av]` + 「!」 |
| 5 | 123 | `G[i[au]]` + 「 军攻打 」 + `h[av]` + 「失败了!」 |
| 6/7/8 | 124/125/126 | 「 发生旱灾!波及城市:」 / 「发生水灾!波及城市:」 / 「发生蝗灾!波及城市:」 + 「 城名」 × aS |
| 9 | 127 | 「发生瘟疫!波及城市:」 + list |
| 10 / 11 | 128 / 129 | 「收金的季节到了!」 / 「收粮的季节到了!」 |
| 12 | 130 | `G[ax]` + 「跟随 」 + `G[ay]` + 「 加入我军为君主效力!」 |
| 13 | 136 | 「百姓发生骚乱,波及城市:」 + list |
| 14 | box | `G[b[at]]` + 「　同盟解除!」 |
| 16 | box | `G[ay]` + 「正与敌方会谈中...」 |
| 17 | box | 「糟了,」 + `G[ay]` + 「投奔了」 + `G[i[au]]` |
| 18 | box | `G[ay]` + 「 势力灭亡了!」 |
| 19 | box | `G[ay]` + 「死亡,新君主 」 + `G[b[at]]` + 「 继位!」 (never reached from t(int), see B3) |

### Popups from `at()`: `c.a(byte,short,short)` (c:3360)

| code | text |
|---|---|
| 2 | `G[s]` + 「　君主死亡!」 |
| 5 | `G[s]` + 「 势力灭亡了!」 |
| 6 | `G[s]` + 「死亡,新君主 」 + `G[s2]` + 「 继位!」 |

### Battle messages `W`: `c.f(int)` (c:4344)

These can appear in the defense battle.

| W | text |
|---|---|
| 2 | 「敌军攻打我军!」 |
| 3 | 「敌将遁逃!」 |
| 5 | 「敌方全军逃窜!」 |
| 6 | 「敌方粮草耗竭,已全军逃窜!」 |
| 7 | 「我军粮草耗竭,全军撤退!」 |
| 8 | 「君主战亡!」 |
| 9 | 「君主所在城市被攻占,无可退城市!」 (never set) |
| 18 | 「无城可退，背水一战吧」 (mid-battle retreat of the ruler with no city, b:2787) |
| 19 | 「武将中计,无法动弹!」 |
| 20 | 「战争回合结束！」 |
| 21 | 「敌军占领我方城池!」 |

The retreat picker title is `"撤退武将 " + G[ax]`, and the extra option is 「下野」.

### Status line and end screen

* Status line: `G[b[as]] + " 战略中....."`.
* End screen 23: `G[b[ar]]` + 「 经过多年征战,终于实现了宇内混一!」 / 「 势力被消灭了,全国局势仍处于混乱之中...」.

---

## 4. Bugs and quirks

| # | where | issue |
|---|---|---|
| B1 | `t()` b:1714 | The faction order is biased. The draw is `(s()%8) mod m` over the remaining slots, always scanned from index 0. With m=3 the odds are 3/8, 3/8, 2/8. Dead slots and unused slot 8 are drawn too and waste nothing but a slot. |
| B2 | `bw()` b:9763/9794 + `au()` b:6380 | After an AI attack on the player, `aF` is reset to 0 and the slot is replayed. The AI gets a **full new action budget** after every defense battle, so it can attack the player any number of times in one month. |
| B3 | `t(int)` b:9726 | Succession after AI-vs-AI capture calls `N(19)` without `aO=19`. The player sees event 4 「…军攻占了…!」 a second time instead of 「…死亡,新君主…继位!」. |
| B4 | `a(short[],byte,int,int)` b:9238 | When the AI takes an empty city, the food and gold it carried are ignored. They were already taken from `au`, so they vanish. |
| B5 | `b(short[],byte,int,int,int)` b:9614 | The `rulerLost` result of the attackers' retreat is ignored. If the attacking ruler led the attack, lost, and had no retreat city, he silently vanishes. `b[f]` and `i[]` keep his id, there is no succession, and later `h(i[...])` returns 0, which corrupts `c[0]/d[0]` and gives a false event 18. |
| B6 | `C()` b:2879 **[bytecode]** | The removal loop is `d[ar][k] = d[ar][k]`, a self-assignment. After losing a defense, the lost city stays in `d[ar]` and the **last** city in the list is dropped instead (it is still owned by the player). The list stays wrong until a save/load rebuilds it (`bi()`). |
| B7 | `bE` b:5784/5869/2919 | `bE` is set but never reset. After the first battle in which our lead unit was killed or captured, every later `D()` also adds `bc/bd` to the lost city. When defending, the retreating leader has already carried `bc/bd` away (`i()` b:2950), so the player's treasury is **duplicated** into the conqueror's city. |
| B8 | `K()` b:3340–3347 **[bytecode]** | In `while (n<n3) bg[bi++]=S[n];` the counter `n` never increments. If the enemy has no retreat city and at least one of our officers is in captured state (`bu==2`) when we win, `bi` overflows `bg[10]`. The resulting AIOOBE ends the game thread (the game freezes). |
| B9 | `f[][]` b:1296–1303 | The succession priority lists mostly name officers from other factions (for example, 刘表's list has 献帝, and 吕布's has 献帝's officers). They were probably written for a different roster. The fallback (max 智+武+德) is what normally applies. |
| B10 | `R()` b:3955–3964 | The 30-day timeout W=20 always makes the **player's** side retreat, including when the player is defending. Holding out for 30 days therefore loses the city. If the enemy runs out of food on day 30, W=6 is overwritten by W=20. |
| B11 | c:4880 / c:5083 | When there is no retreat city (`aS==0`), the picker's only index (0 == aS) means 「下野」 even when it is hidden for P==100. If the player ruler is not `bg[0]` (for example, another officer was made governor), he is put into `B[bb]` as 在野 instead of dying. The faction then has a ruler who is in no city, and `D()` checks `c[ar]` only when `bG>1`. |
| B12 | `aC()` b:6846 | Removing an expired alliance copies `X[W]/Y[W]`, one past the last entry, instead of `X[W-1]`. Removing a non-last alliance loses the last alliance and can bring back a stale one. |
| B13 | `K()` b:3284 | "Is ruler" is tested with `P==100`. Any other officer with P==100 would trigger a false death and succession. The data has one: id 61, a second 「曹操」 with P=100 in city 25. The same test hides 「下野」. |
| B14 | `L()` b:3411 | `a[at]=0` uses whatever `at` currently holds (set only by K() when the enemy ruler dies) instead of `by`. This only matters on the player-attack path. |
| B15 | `d()` b:9645 | Possible division by zero. `def` is 0 when the target city has `r==0`; `atk` is 0 only if every attacker has N+M==0. |
| B16 | `x()` b:1878 | The city scan goes over columns 0..30 and keeps scanning after a hit, so it uses the right-most city column. `w()` scans 0..31. Deployment ignores terrain (`f()` only checks occupancy). |
| B17 | `aq(byte)` b:11590 | If the faction has no officers left, `a(f,0,0)` sets `i[]` of all its cities to 0 (they become ownerless), while `a[f]` stays alive. |
| Q1 | `z()` b:11717 | With Z==2 there is no penalty for empty targets. With Z==1, `x()` subtracts 2500. |
| Q2 | `c(short[],int)` b:9225 | The AI always carries at least half of the source city's food. If the attacker runs out of food in `d()` it is simply clamped to 0, with no penalty. |
| Q3 | `aE()` b:6894 | Scripted joins append to `A[]` without updating `u[]`. |
