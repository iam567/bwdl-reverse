# Strategic AI: per-turn decision procedure (Z == 2), implementation-exact

Source: `decompiled/b.java` and `c.java`. Line numbers match the decoded copies in the scratchpad, which have the same line numbers as the originals.
Conventions: all arithmetic is Java `int` with truncating division. `(byte)` and `(short)` casts are kept wherever the original has them.
`rnd()` means `b.s()` = `Math.abs(ap.nextInt())` (b.java:1699). A raw `ap.nextInt()` is written out explicitly.
City indices are **1..36** (arrays are sized 37, and `ak()` loops `1..36`). `i[c]` = ruler officer id of city c (0 = empty/neutral), `b[f]` = ruler of faction f,
`c[f]`/`d[f][]` = the faction's city count and city list, `as` = acting faction, `ar` = player faction. `bP` = "chosen action" code.
Scratch globals used by the AI: `au` (source/own city), `av` (other city), `ax`/`ay` (officers), `aO` (event code, also reused as a scratch value).

---

## 0. Where the AI turn is driven from

### 0.1 Month loop / faction order (b.java:6344 `au()`, 1714 `t()`)
```
au():                                         // one month
  for (by = aE; by < 8; by++) {
    if (aq == 0) t();                         // pick next faction
    if (as <= 0) { as = cP; }                 // empty slot -> nothing happens
    else {
      cP = as;
      if (as == ar) { ...player turn... }
      else {
        repaint; aq = 0;
        bw();                                 // AI turn
        if (aq == 3) {                        // bw attacked the PLAYER
          an();                               // interactive battle (b.java:6091)
          by--;                               // does not consume the slot
          at();                               // sets aq = 4 (or 99 = player dead -> return)
        }
        sleep so that the faction takes >= 2000 ms
      }
    }
  }

t():   // b.java:1714 - random unused faction slot
  n = rnd() % 8 + 1; n2 = 0;
  while (n > 0) { n -= aB[n2]; if (n == 0) break; if (++n2 == 8) n2 = 0; }
  aB[n2] = 0; as = a[n2 + 1];
```
`aB[0..7]` is reset to 1 at the end of every month (b.java:6853-6856). Every slot 1..8 therefore acts once per month in random order.

**Consequence (bug B1):** after the AI attacks the player, `aq == 4` at the next iteration, so `t()` is **not** called and `as` stays the same.
`bw()` runs again with `aF` already reset to 0 (b.java:9794), so that faction gets a **complete fresh set of tokens**.
A faction that keeps attacking the player can chain full turns without limit in one month.
Only the tokens it spent before the attack are lost. The `break` at 9773 drops the rest of the current set.

### 0.2 Token count (b.java:1726)
```
byte e(byte f) { n = c[f]; if (n < 3) n = 3; else if (n > 8) n = 8; return (byte)n; }
```
It is the same formula as the player's `aG`. It is computed **once** at the start of `bw()` from the city count at that moment, so conquests during the turn do not add tokens.

---

## 1. `bw()`: the token loop (b.java:9747-9795)

```
void bw() {
  int n = 0;
  byte tokens = e(as);
  while (aF < tokens) {
    aF++;                                 // every iteration consumes exactly 1 token
    bP = -1; au = 0; av = 0;
    ag(as);                               // -> as(as): rebuild frontier/interior lists (every token)
    n = cb();                             // WAR candidate (bz(), Z==2)
    if (bP == 3) {
      n = C(n);                           // go/no-go roll (s(int), Z==2)
      if (bP == 3) {
        t(n);                             // war: army, supplies, execute
        if (aq != 3) continue;            // AI-vs-AI / empty city: next token
        break;                            // attacked the player: leave (see B1)
      }
    }                                     // vetoed war falls through (token not wasted)
    cc();          if (bP == 2)  { bD();  continue; }      // 征兵
    n = cd();      if (bP == 1)  { D(n);  continue; }      // 调将
    ch();          if (bP == 19) { bJ();  continue; }      // 医馆
    ce();          if (bP == 8)  { bM();  continue; }      // 奖赏
    n = cf();      if (bP == 12) { x(n);  continue; }      // 输送
    cg();                                                  // catch-all
  }
  aF = 0;
}
```
- The cascade has a strict priority: war > 征兵 > 调将 > 医馆 > 奖赏 > 输送 > catch-all. It stops at the first stage that sets `bP`.
- Each stage has its own RNG gate, listed per stage below. A stage that passes its gate but finds no candidate leaves `bP == -1` and the cascade continues.
- `cg()` always does something, even if only a search, so **no token is ever idle**. There are no token refunds.

### 1.1 Frontier/interior partition: `as(f)` (b.java:11699)
```
aV = new byte[36]; aW = new byte[36]; aX = 0; aY = 0;
for (k = 0; k < c[f]; k++) { city = d[f][k];
  if (ai(city)) aV[aX++] = city;   // frontier
  else          aW[aY++] = city;   // interior
}
ai(c) (b.java:9952): true iff some neighbour D[c][k] (k < E[c]) has i[nb] != i[c]
```
Neutral cities (i = 0) count as "different", so a city next to an empty city is frontier.

### 1.2 RNG consumption per token (Z == 2), in order
| step | draws |
|---|---|
| `as()`, `cb()` | 0 |
| `C()` (only if `cb` found a target) | 1 (`%100`) |
| `t(n)` prelude (only if go) | `e()`: 1 + 1 per officer drafted; `c()`: 1; gold: 1 if `l[au] >= 2`; then the execution draws (out of scope) |
| `cc` | 1 (`%3`) |
| `cd` | 1 (`%3`) |
| `ch` | 1 (`%3`); `bJ`: 1 |
| `ce` | 1 (`%2`); +1 if 35 < minP < 70; `bM`: 1 (treasure) or 2 (gold) |
| `cf` | 1 (`%2`, has no effect); +1 if the result is n == 2 or n == 1 |
| `cg` | 2 (city pick), then per attempt 1 + the action's draws |

Once a stage fires, none of the later stages consume draws.

---

## 2. War decision (decision part only)

### 2.1 Target scoring: `cb()` (b.java:11723) with `z()` (11717)
```
int cb() {
  int best = 999;
  boolean allied = bx();                     // b.java:9830: as ∈ X[0..W-1] (alliance list with the player)
  bP = -1;
  for (k = 0; k < aX; k++) { src = aV[k];    // frontier cities only
    if (r[src] > 2) {                        // needs >= 3 officers
      for (j = 0; j < E[src]; j++) { dst = D[src][j];
        if (i[src] == i[dst]) continue;                  // own city
        if (allied && i[dst] == b[ar]) continue;         // allied with player: spare player cities
        int sc = z(src, dst);
        if (sc > best) { best = sc; au = src; av = dst; } // strict >: first max wins
      }
    }
  }
  if (best >= 1000) bP = 3;
  return best;
}
int z(src, dst) {
  int cap = m[src] / 4 * 1000 / 45;                      // troops the grain can feed
  int s = (cap >= u[src] ? u[src] - u[dst] : cap - u[dst]);
  return s + (r[src] - r[dst]) * 200;
}
```
- Score = min(own troops, grain cap) − enemy troops + 200 × officer difference. It must be **≥ 1000** because `best` starts at 999.
- Neutral cities (`i = 0`, `u = r = 0`) are valid targets. Unlike the Z==1 `x()`, there is no −2500 penalty for them.
- No distinction between factions other than the player alliance. AI factions never ally with each other.

### 2.2 Go/no-go: `C(n)` (b.java:11593)
```
int C(int n) {
  n /= 1000;
  int p = (n == 1) ? 20 : (n == 2) ? 50 : (n == 3) ? 70 : 80;   // switch; default 80
  if (rnd() % 100 > p) bP = -1;
  return n;                       // score in thousands, passed on to t()
}
```
P(go) = 21 % (score 1000-1999), 51 % (2000-2999), 71 % (3000-3999), 81 % (≥ 4000).
A veto only cancels the war. The same token continues down the cascade.

### 2.3 `t(n)` prelude up to the attack (b.java:9685-9697)
```
aA = e(az, n);                   // b(short[],int) -> Z==2 e(): army selection, az = new short[10]
n2 = c(az, aA);                  // grain carried
n3 = (l[au] < 2) ? 0 : rnd() % (l[au] / 2);   // gold carried: 0 .. l/2-1
l[au] -= n3;  m[au] -= n2;
// branch: i[av]==b[ar] -> player defends (aO=1, aq=3, return); i[av]==0 -> empty-city capture (aO=2);
// else AI-vs-AI (aO=1 then 4/5, maybe 18 / new-ruler event). -> executed by another spec.
```

### 2.4 Army selection: `e(short[] army, int n)` (b.java:11670)
```
byte[] avail = {1,1,1,1,1,1,1,1,1,1};
byte left = r[au];
int acc = -1 * (u[av] + r[av] * 200);         // -(defender strength)
byte cnt = 0, pos = 0;
n = rnd() % n;  n *= 1000;                     // threshold k*1000, k in [0, score/1000 - 1]
while (left > 1) {                             // always leaves >= 1 officer at home
  int s = 0, want = rnd() % left + 1;
  while ((s += avail[pos]) != want) { if (++pos == r[au]) pos = 0; }  // want-th free slot, cyclic from pos
  army[cnt] = A[au][pos];  A[au][pos] = 0;
  acc += J[army[cnt]];  cnt++;
  avail[pos] = 0;  left--;
  if ((acc += 200) >= n) break;
}
r[au] = left;
ar(au);                        // b.java:11638 (see below)
e(army, cnt);                  // b.java:11647: choose the leader
return cnt;
```
- `pos` is **not** reset between picks. The scan restarts at the previous pick, whose slot is now 0.
- The draft stops when Σ(J + 200) of the army − (enemy u + 200·r) ≥ k·1000. Officers are drafted at random, not by strength. Officers with J = 0 can be drafted.
- `ar(au)`: if `A[au][0] == 0` (slot 0 was drafted), then `a(au, A[au], r)` compacts the list and recomputes `u[au] = ΣJ`. After that, `c(au, A[au], r)` (11618) makes the **highest-loyalty** officer (first max, strict `<`) the 太守 `j[au]` and rotates him to slot 0. Otherwise only the compaction and recompute run (b.java:9135).
- Leader `e(army, cnt)`: if the faction ruler `i[au]` is in the army, he is swapped to slot 0. Otherwise the highest-N officer (first max) is swapped to slot 0.

### 2.5 Supplies: `c(short[] army, int cnt)` (b.java:9225)
```
int t = b(army, (byte)cnt);          // b.java:7376: Σ J over the army (short)
t = t * 4 / 1000 + 1;
int g = rnd() % (t * 30) + t * 30;   // 30t .. 60t-1
if (g < m[au] / 2) g = m[au] / 2;    // floor: at least HALF the city's grain
else if (g >= m[au]) g = m[au] * 2 / 3;
return g;
```
In practice the AI almost always carries ≥ half the source city's grain.

---

## 3. 征兵 (bP = 2)

### 3.1 Choice: `cc()` (b.java:11751)
```
int best = 0;
if (rnd() % 3 > 1) return;                            // runs with p = 2/3
for (k = 0; k < aX; k++) { c = aV[k];                 // frontier only
  int need = r[c] * 1000 - u[c];
  if (need > 0 && (l[c] >= 50 || v[c] > 0)) {
    int afford = (l[c] - 50) * 5;
    if (need > afford) need = afford;
    if (need >= best) { au = c; best = need; }        // >=: LAST max wins
  }
}
if (best > 0) bP = 2;
```

### 3.2 Execution: `bD()` (b.java:9923) and `bC()` (9912)
```
int cap = r[au] * 1000;
int def = cap - u[au];
int rest = def - v[au];                    // v = 预备兵 reserve
if (rest <= 0) { u[au] = (short)cap; v[au] -= def; }        // reserve covers it for free
else {
  u[au] += v[au];
  int cost = ((rest - 1) / 100 + 1) * 20;  // 20 gold per started 100 troops
  if (l[au] >= cost) { l[au] -= cost; u[au] = (short)cap; v[au] = (short)(cost * 5 - rest); } // overshoot -> reserve
  else { v[au] = 0; cost = l[au] / 20 * 20; u[au] += cost * 5; l[au] -= cost; }            // spend all gold
}
bC():
  n = 0;
  while (n < u[au] / 1000) { J[A[au][n]] = 1000; n = (byte)(n + 1); }
  if (n < r[au]) J[A[au][n]] = (short)(u[au] % 1000);
```
- The rate is 1 gold per 5 troops, the same as the player's `b(short,byte)` at b.java:7386. However, the AI path does **not** reduce population `p[]`, while the player path does `p -= s`.
- `bD` may spend gold below the 50 that `cc` reserved in its estimate.
- No RNG. No event.

---

## 4. 调将 (bP = 1)

### 4.1 Threat: `at(c)` (b.java:11777)
```
int n = -20000;
for each neighbour nb of c: n = max(n, u[nb] + r[nb]*200 - u[c] - r[c]*200);
return n;
```
**Every** neighbour is counted, including the faction's own cities.

### 4.2 Choice: `cd()` (b.java:11824) with `d(c, threat)` (11791)
```
int cd() {
  int best = -20000, amt = 0, tmp = 0;
  au = 0; av = 0;                                   // before the gate
  if (rnd() % 3 > 1) return 0;                      // p = 2/3
  for (k = 0; k < aX; k++) { c = aV[k];              // frontier recipients
    int th;
    if (r[c] != 10 && (th = at(c)) > best && (tmp = d(c, th)) != 0) {
      au = c; bP = 1; best = th; amt = tmp;          // highest-threat recipient that has a donor
    }
  }
  return amt;
}
int d(byte c, int th) {                              // picks donor av
  byte most = 1; int lim = th - 1999; int t = 0;
  for each neighbour nb of c:
    if (i[nb] == i[c] && r[nb] > 1) {
      if (!ai(nb)) {                                   // interior donor
        if (r[nb] > most) { av = nb; most = r[nb]; }
      } else if (most == 1 && th > 0 && (t = at(nb)) < lim) {   // frontier donor, much safer
        av = nb; lim = t;
      }
    }
  if (most > 1) return most - 1;                       // OFFICER COUNT (all but one)  -> always < 50
  if (lim < th - 1999) { t = (th - lim) / 2; return (t > th) ? th : t; }   // TROOP AMOUNT
  return 0;
}
```
`av` is written only on the paths that return non-zero, so a failed `d()` leaves the earlier candidate intact.

### 4.3 Execution: `D(int n)` (b.java:11853)
```
if (n < 50) {                                 // move n officers (from the tail of av's list)
  for (k = 0; k < n; k++) {
    r[av]--; A[au][r[au]] = A[av][r[av]];
    s = J[moved]; u[au] += s; u[av] -= s;
    A[av][r[av]] = 0; r[au]++;
    if (r[au] >= 10) return;
  }                                           // exceptions caught, print "move error"
} else {                                      // move officers until (200+J) sum >= n
  int acc = 0;
  while (acc < n) {
    if (r[av] <= 1) return;
    if (r[au] >= 10) return;
    (same move as above); acc += 200 + s;
  }                                           // catch prints "move error2" and calls wait()
}
```
- Interior donors give all but one officer.
- Frontier donors, used only when `th > 0` and the donor's own threat is < `th − 1999`, give officers worth ≈ (th − donorThreat)/2 in (200 + J) units, capped at `th`.
- Neither `j[au]` nor `j[av]` (太守) is updated, even if the moved officer was the 太守 or the ruler.
- No RNG inside `D`. No event.

---

## 5. 医馆 (bP = 19): `ch()` (b.java:12175), `bJ()` (10110), `bI()` (10106)
```
if (rnd() % 3 > 1) return;                             // p = 2/3
for (k = 0; k < aX; k++) { c = aV[k];                  // frontier only
  if ((x[c] & 4) == 4 && l[c] >= 100)                  // has 医馆, gold >= 100
    for (j = 0; j < r[c]; j++)
      if (K[A[c][j]] - 40 > L[A[c][j]]) { bP = 19; au = c; ax = A[c][j]; return; }  // FIRST match
}
bJ():
  L[ax] = (byte)(L[ax] + (35 + rnd() % 17));           // +35..51
  if (L[ax] > K[ax] || L[ax] <= 0) L[ax] = K[ax];      // <=0 catches byte overflow
  l[au] -= 50;
```
Only one officer is healed per token. No event.

---

## 6. 奖赏 (bP = 8): `ce()` (b.java:11915), `bM()` (10170), `w()` (10155)
```
byte minP = 100;
if (rnd() % 2 > 0) return;                             // p = 1/2
for (k = 0; k < c[as]; k++) { c = d[as][k];            // ALL own cities
  if (l[c] >= 200 || w[c] != 0)
    for (j = 0; j < r[c]; j++)
      if (P[A[c][j]] < minP) { au = c; ax = A[c][j]; minP = P[ax]; }   // global first-min
}
if (minP >= 70 || minP > 35 && rnd() % 2 > 0) return;  // P<=35 always; 36..69 with 1/2
bP = 8;

bM():
  if (w[au] > 0) { P[ax] = (byte)(P[ax] + w(200)); w[au]--; }       // treasure: +11..18
  else           { P[ax] = (byte)(P[ax] + w(100)); l[au] -= 100; }  // gold: +11..13
  if (P[ax] > 99 || P[ax] <= 0) P[ax] = 99;
w(200) = 11 + rnd() % 8
w(100): v = 12; if (rnd() % 8 < 4) v = 13; v += rnd() % 2 - 1;   // v>7 always -> {11,12,12,13}, i.e. 11 (25%), 12 (50%), 13 (25%)
```
Rulers have P = 100 and never qualify. No event.

---

## 7. 输送 (bP = 12): `cf()` (b.java:12086), donor finders `au/av/aw/ax(byte)` (11942/11978/12014/12050), `x(int)` (10235)
```
int cf() {
  short mode = 0, key = 10000; int n = 0;
  au = 0; av = 0;
  if (rnd() % 2 > 1) return 0;                 // NEVER true: gate is a no-op, but 1 draw is consumed
  for (k = 0; k < aX; k++) { c = aV[k];        // frontier recipients
    short t;
    if (m[c] < 200 && m[c] < key && (t = au(c)) >= 0)            { au=c; key=m[c]; n=4; mode=t; }  // grain-poor
    else if (n < 4 && l[c] < 200 && l[c] < key && (t = av(c)) >= 0) { au=c; key=l[c]; n=3; mode=t; }  // gold-poor
    else {
      t = l[c] + m[c];
      if (t < key) {
        if (n < 3 && t < 1000 && (t = aw(c)) >= 0)               { au=c; key=(short)(l[c]+m[c]); n=2; mode=t; }
        else { t = l[c] + m[c];
          if (n < 2 && t < 4000 && (t = ax(c)) >= 0)             { au=c; key=(short)(l[c]+m[c]); n=1; mode=t; } }
      }
    }
  }
  if (n > 2 || n == 2 && rnd() % 2 > 0 || n == 1 && rnd() % 5 > 3) bP = 12;   // n>=3 always; 2: 1/2; 1: 1/5
  return mode;
}
```
Donor finder, shown for grain `au(c)`. Only same-ruler neighbours are considered.
```
bestI=-1 (interior value), bestF=-1 (frontier value)
for nb in neighbours(c) with i[nb]==i[c]:
  if (!ai(nb)) { if (m[nb] > bestI) { bestI = m[nb]; di = nb; } }
  else if (bestI < 1000 && m[nb] > bestF) { bestF = m[nb]; df = nb; }
if (bestI >= 1000)                    { av = di; return 0; }
if (bestF - bestI >= 500 && bestF >= 600) { av = df; return 1; }
if (bestI >= 400)                     { av = di; return 0; }
return -1;
```
| finder | resource | interior "rich" | frontier rule | interior fallback |
|---|---|---|---|---|
| `au` | m | ≥ 1000 | F − I ≥ 500 && F ≥ 600 | I ≥ 400 |
| `av` | l | ≥ 1000 | F − I ≥ 500 && F ≥ 600 | I ≥ 400 |
| `aw` | l+m | ≥ 1500 | F − I ≥ 800 && F ≥ 1500 | I ≥ 800 |
| `ax` | l+m | ≥ 2000 | **F − (l[c]+m[c]) ≥ 1000** && F ≥ 3000 | I ≥ 1000 |

(The frontier test `bestI < X` depends on whether an interior neighbour was seen earlier in the neighbour order.)

Execution `x(mode)`:
```
if (mode == 0) { g = l[av] - 100; f = m[av] - 100; }                        // interior donor keeps 100/100
else { g = l[av] - (l[au] + l[av]) / 2; f = m[av] - (m[au] + m[av]) / 2; } // frontier donor: equalise
if (g > 0)  { l[au] += g; l[av] -= g; }
if (f >= 0) { m[au] += f; m[av] -= f; }
```
Gold **and** grain always move together, whichever shortage triggered the transfer. There is no 9999 clamp. No event.

---

## 8. Catch-all `cg()` (b.java:12136)
```
int n = 0, r2 = 0; byte c = 0;
try {
  c = (rnd() % 10 > 6 && aY > 0 || aX <= 0) ? aW[rnd() % aY] : aV[rnd() % aX];  // 30% interior (if any)
  n = (l[c] >= 50 && (this.n[c] < 999 || o[c] < 999 || p[c] < 990000 || q[c] < 99)) ? 460 : 220;
} catch (Exception e) { print "other first"; }
for (k = 0; k < 10; k = (byte)(k+1)) {
  r2 = rnd() % n;
  if      (r2 < 40)  { if (aj(c)) break; }   // 集市 (grain trade / 学院)    -- may fail -> retry
  else if (r2 < 120) { if (ak(c)) break; }   // 离间 / 招揽               -- may fail -> retry
  else if (r2 < 220) { am(c); break; }       // 搜索 / 登用
  else if (r2 < 460) { ao(c); break; }       // 内政 (only reachable when n == 460)
}
if (k == 10) am(c);
```
Probabilities per attempt:
- n = 460: 集市 40/460, 离/招 80/460, 搜 100/460, 内政 240/460.
- n = 220 (gold < 50 or city fully developed): 集市 40/220, 离/招 80/220, 搜 100/220, and **no 内政**.

### 8.1 集市: `aj(c)` (b.java:10274), `b(byte,short)` (10259), `n(short)` (7747)
```
if ((x[c] & 8) == 8) {                                      // 粮店
  if (l[c] < 100 && m[c] > 500) { m[c] -= 200; l[c] += 150; return true; }   // sell 200 grain -> 150 gold
  if (m[c] < 200 && l[c] > 500) { l[c] -= 200; m[c] += 266; return true; }   // buy 266 grain for 200 gold
}
if (l[c] < 50 || (x[c] & 2) != 2) return false;              // 学院 needed
byte bestM = 0; short who = 0;
for (j = 0; j < r[c]; j++)
  if (bestM < M[A[c][j]] && N[A[c][j]] < 80) { who = A[c][j]; bestM = M[who]; }   // strongest warrior with N<80
if (bestM > 0) { b(c, who); return true; }
return false;

b(byte c, short s):                              // AI 学院
  if      (N[c] < 30) l[c] -= 30;                // BUG: N[c] indexes the officer array with the CITY id
  else if (N[c] < 60) l[c] -= 20;
  else                l[c] -= 10;
  N[s] = (byte)(N[s] + n(s));
n(s) = (N[s] < 30 || N[s] > 70) ? rnd() % 5 + 6 : rnd() % 7 + 6;   // +6..10 / +6..12
```
No event.

### 8.2 离间 / 招揽: `ak(c)` (b.java:10331) and helpers
```
boolean ak(byte c) {
  byte minP = 100;
  for (cc = 1; cc < 37; cc++)                             // every city on the map
    if (i[cc] != i[c])                                    // any other ruler (incl. player, allies)
      for (j = 0; j < r[cc]; j++)
        if (minP > P[A[cc][j]]) { av = cc; ay = A[cc][j]; minP = P[ay]; }  // global first-min loyalty
  if (minP == 100) return false;
  ax = A[c][0]; byte bn = N[ax];
  for (j = 1; j < r[c]; j++) if (bn < N[A[c][j]]) { ax = A[c][j]; bn = N[ax]; }   // own best-N officer
  if (r[c] < 10 && (minP < 15 || minP < 35 && rnd() % 2 > 0))
       b(c, av, ax, ay);          // 招揽 (poach) attempt
  else b(av, ax, ay);             // 离间 (erode loyalty)
  return true;                    // counts as success whatever the outcome
}
```
There is no adjacency requirement and no alliance check. Rulers (P = 100, set again by `a(byte,byte,short)` at b.java:6203 on succession) are never targets.
At the start of the scenario the minimum loyalty is 40 (the four 方士), so poaching only becomes possible after 离间 has worn loyalty down.

**离间**, `b(byte tc, short me, short tgt)` (b.java:10311):
```
if (i[tc] == b[ar]) { aO = 16; ay = tgt; N(19); ax(); }  // event is shown BEFORE and REGARDLESS of the roll
f(me, tgt);
f(me,tgt) (7618): d = e(me,tgt); if (d > 0) { P[tgt] = (byte)(P[tgt] - (byte)d); if (P[tgt] < 0) P[tgt] = 0; }
e(me,tgt) (7597):
  int a = N[me], dN = N[tgt];
  if (P[tgt] > 99) dN = 10000; else if (P[tgt] > 96) dN += 50; else if (P[tgt] > 89) dN += 20;
  if (a - dN <= rnd() % 30) return 0;
  int x = rnd() % (a - dN); x /= 10; x *= 9; x /= 5; return x + 1;
```
Loyalty drop = ((rnd % (N_me − N_tgt')) / 10 · 9 / 5) + 1, for example 1..8 at an intelligence gap of 50.

**招揽**, `b(byte c, byte tc, short me, short tgt)` (b.java:10321), then `a(c,tc,me,tgt)` (7693), `h()` (7678), `m()` (7665):
```
if (a(c, tc, me, tgt) && i[tc] == b[ar]) { aO = 17; au = c; ay = tgt; N(19); ax(); }
h(me,tgt): if (P[tgt] > 35) return false;
           if (N[me] - N[tgt] + (35 - P[tgt]) >= rnd() % 20) { m(tgt); return true; } return false;
m(tgt):    n = 100 - P; n2 = (n + 50) / 2;
           if (n == n2) n2 = rnd()%3 - 1; else if (P > 50) n2 = rnd() % (n2 - n); else n = rnd() % (n - n2);
           P = (byte)(n + n2);                   // for P<=35: P' in [(150-P)/2, 99-P], e.g. P=0 -> 75..99
a(...) on success: remove tgt from A[tc] (shift down), r[tc]--; append to A[c], r[c]++;
           u[tc] -= J[tgt]; u[c] += J[tgt];
           if (r[tc] == 0) { f = h(i[tc]); remove tc from d[f], c[f]--; i[tc] = 0; j[tc] = 0; }  // city goes neutral
           else j[tc] = A[tc][0];                  // 太守 reset to slot 0
```

**Player-visible events.** Both are drawn by `c.e()` (c.java:3240). Codes ≥ 14 are shown as a text-only popup (c.java:3226).
| aO | when | text (c.java) |
|---|---|---|
| 16 | AI runs 离间 against an officer in a **player** city, shown before the roll | `G[ay] + "正与敌方会谈中..."` (3343) |
| 17 | AI **successfully** poaches from a **player** city that still has officers | `"糟了," + G[ay] + "投奔了" + G[i[au]]` (3347), where `i[au]` is the poaching AI ruler |

`ax()` (b.java:6440) = `bv()` (waits for the key press) followed by `N(4)`.

### 8.3 搜索 / 登用: `am(c)` (b.java:10499), `al(c)` (10476)
```
short al(byte c) {                         // pick the acting officer
  short[] pool = new short[r[c]]; int k = 0; byte bn = 0;
  for (j = 0; j < r[c]; j++) {
    if (bn < N[A[c][j]]) { ax = A[c][j]; bn = N[ax]; }        // side effect: ax = best-N (first max)
    if (N[A[c][j]] >= 70) pool[k++] = A[c][j];
  }
  if (k == 0) return ax;
  return pool[rnd() % k];                   // random officer with N >= 70
}
void am(byte c) {
  ax = al(c);
  if (r[c] < 10 && t[c] > 0) {              // someone already discovered -> 登用
    ay = C[c][rnd() % t[c]];
    c(c, ax, ay);                           // b.java:10398
    return;
  }
  c(c, ax);                                 // search, b.java:10438
}
```
**登用**, `c(byte c, short me, short tgt)`: calls `j(me,tgt)` (10371):
```
Math.abs(ap.nextInt());                     // discarded draw (dead code, consumes RNG)
return max(N[tgt], M[tgt], O[tgt]) - O[me] < rnd() % 40;
```
On success: `A[c][r[c]] = tgt; u[c] += J[tgt]; r[c]++; C[c] = remove(C[c], t[c], tgt); t[c]--`. Newly appeared officers have J = 0 (b.java:6873).

**搜索**, `c(byte c, short me)`: calls `f(me, s[c])` (10413):
```
roll = rnd() % 120;
if (roll > N[me]) -> nothing
elif (roll > 79): q = rnd()%100; amt = q<5?4 : q<15?3 : q<40?2 : 1; type 4 (宝物 w[c] += amt, cap 99)
elif (roll > 59 && s[c] != 0): idx = rnd() % s[c]; type 3 -> C[c] += B[c][idx]; B[c] -= it; t[c]++; s[c]--
else: lo = O*12/10; hi = O*17/10; amt = (lo + rnd() % (hi - lo)) * 6 / 10;
      type = (roll % 2 == 1) ? 2 (gold l[c] += amt, cap 9999) : 1 (grain m[c] += amt, cap 9999)
```
There is no gold cost and no event. `aO`/`aP` are clobbered as scratch values.

### 8.4 内政: `ao(c)` (b.java:10618), `an(c)` (10588)
```
ao(c): ax = al(c); an(c);
an(c):
  if (q[c] < 60) { g(c, ax); return; }                       // 治水 first if water control < 60
  if (rnd() % 2 > 0) {
    if (n[c] < 999 && o[c] < 999) {
      if (aC < 4 || aC >= 10) { d(c, ax); return; }         // months 1-3, 10-12: 开垦
      e(c, ax); return;                                    // months 4-9: 劝商
    }
    if (n[c] < 999) { d(c, ax); return; }
    if (o[c] < 999) { e(c, ax); return; }
  }
  if (p[c] < 990000) { f(c, ax); return; }                   // 巡查 (population)
  g(c, ax);                                                  // 治水
```
The amount functions are shared with the player's commands:
| action | cost `n` (gold) | gain | extra |
|---|---|---|---|
| 开垦 `d`→`a(c,s,n)` (7517) | `t(s)` = 10 + (N>70?8 : N>50?4 : 0) + 2·(rnd%5) | `c(s,n)`: N<12 → 1; else h = N·n/100, lo = h·7/10, gain = lo + rnd%(h−lo+1). n[c] += gain (cap 999) | k[c]++ if < 99 |
| 劝商 `e`→`b(c,s,n)` (7538) | `t(s)` | same `c(s,n)`, o[c] (cap 999) | k[c]++ if < 99 |
| 巡查 `f`→`d(c,s,n)` (7576) | `t(s)` | `e(s,n)`: N<17 → 1000; else h = N·n/100, gain = (h/2 + rnd%(h−1−h/2+1))·100. p[c] += gain (cap 990000) | k[c]++ if < 99 |
| 治水 `g`→`c(c,s,n)` (7559) | `u(s)` = 10 + (N>70?6 : N>50?3 : 0) + rnd%4 | `d(s,n)`: N<12 → 1; else h = N·n/100, gain = h/2 + rnd%(h−h/2+1). q[c] += gain (cap 99) | — |

There is no affordability check. It is only safe because `ao` is reachable solely when `l[c] ≥ 50` and the cost is at most 28. No event.

---

## 9. Bugs and quirks found

1. **B1: extra full turn after attacking the player** (b.java:6376-6385 with 9773/9794). After the defence battle, `at()` sets `aq = 4`, so `t()` is skipped and the same faction runs `bw()` again with a full `e(as)` tokens. This can repeat without limit.
2. **B2: `cf()` gate is dead** (12092): `rnd() % 2 > 1` is never true, so the 50 % gate never applies. The draw is still consumed.
3. **B3: AI 学院 cost indexes the wrong array** (10260-10268): `N[by]` uses the city id as an officer id. The price is decided by officer #city's intelligence.
4. **B4: `D()` count mode can drain and underflow a donor** (11824/11791/11853):
   - `d()` returns `min((th − donorThreat)/2, th)`. When `1 ≤ th ≤ 49` and a frontier donor qualifies, the return value is `th` (< 50). `D()` then treats this troop amount as an **officer count**.
   - Count mode has no `r[av] > 1` guard. If `r[au] + r[av] ≤ 10`, the donor drops to 0 officers.
   - If the sum is < 10 and n > r[av], the next move sets `r[av] = -1`, and `A[av][-1]` throws. The exception is caught ("move error") but `r[av]` stays −1.
   - The owned city keeps `i[]` with no officers.
5. **B5: `bC()` leaves stale troops** (9912): after a partial (gold-limited) 征兵, officers after index `u/1000` keep their old `J`. As a result ΣJ ≠ `u`, and later recomputations such as `u = b(A,r)` at 9733 inflate the troop count. If `u % 1000 == 0`, the officer at index `u/1000` is set to 0 troops.
6. **B6: reserve-only 征兵 never selected** (11761-11766): for `l < 50`, `afford = (l−50)·5 < 0`, so the `|| v > 0` clause can never lead to a pick.
7. **B7: silent city loss to 招揽** (10322): the event-17 check reads `i[tc]` **after** `a()` has zeroed it for an emptied city. A player city that loses its last officer turns neutral with no message.
8. **B8: `at()` counts friendly neighbours as threats** (11777). A frontier city next to a big friendly city looks endangered, which skews 调将.
9. **B9: mixed-unit key in `cf()`**: one `key` variable is compared against m, l, or l+m depending on the branch. Priority n = 4 can also overwrite an earlier pick without an `n <` guard.
10. **B10: `D()` never updates 太守 `j[]`** of either city. The ruler or 太守 can be moved away while `j[]` still points at him.
11. **Dead draw in `j()`** (10375): `Math.abs(ap.nextInt())` is discarded but consumes RNG (already documented in GAME_LOGIC §3.1). This spec keeps it for exactness.
12. **Latent crashes**:
    - `cg()` with `aX == aY == 0` leaves `n = 0`, so `rnd() % 0` throws outside the try.
    - The resource branch of `f()` throws on `hi == lo`, i.e. O ≤ 1. No CSV officer has that.
    - `al()` with `r == 0` returns a stale `ax`.
13. **Asymmetries vs the player**: AI 征兵 costs no population. AI market trades are fixed at 200→150 and 200→266 with no 9999 clamp. `x()` transfers ignore the 9999 cap.
14. **Not executed (Z ≠ 2), for reference**: `by()` (9841) and `ci()` (12217) test `i[this.ar]`, a faction index used as a city index. The correct test is `b[ar]`, which `cb()` uses.
