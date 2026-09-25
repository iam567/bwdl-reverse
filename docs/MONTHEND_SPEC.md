# Month-end / Year-end / Event popup / Ending spec (implementation-exact)

Source: `decompiled/b.java`, `decompiled/c.java`, `decompiled/d.java` (line numbers refer to these files; the
un-escaped copies in scratchpad have the same line numbers). Critical points were checked against the
`b.class` bytecode (`scratchpad/jar/dis.py`).

Conventions used below
- `s()` = `Math.abs(ap.nextInt())` (b.java:1699). Every `s()%N` below is one RNG draw, non-negative
  (except when `nextInt()==Integer.MIN_VALUE`: `abs` stays negative. That happens about 1 time in 2^32 and is ignored here).
  No function in this spec calls `ap.nextInt()` directly. They all go through `s()`.
- `(byte)`, `(short)` = Java narrowing casts (wrap-around). `/` = Java int division (truncates toward 0).
- Cities are indexed **1..36** (`d.a[0] == ""`). Every "all cities" loop is `for s=1; s<37`.
- Factions are indexed 1..8. `a[f]` = f when alive and 0 when dead. `b[f]` = ruler officer id, `c[f]` = city count,
  `d[f][0..c[f]-1]` = list of city indices. `ar` = player faction. A city is owned by faction f iff `i[city] == b[f]`.
  `i[city]==0` means an empty city.
- `aC` = month (byte), `aD` = year (short), `aO` = event code (int), `aS`/`aR[37]` = count and list of affected cities.
- `N(x)` (b:7395) = `ao.a(x)` (switch the screen, and `c.a(byte)` ends with `this.a = 0`, which clears the key latch; c:1007) + repaint.
- `ax()` (b:6440) = `bv()` (busy-wait until any key sets `ao.a != 0`, b:9666) then `N(4)` (back to the map).
  **Every popup blocks until a key is pressed.**
- `Z` (difficulty/mode byte) is written only by the constructor (`Z = 2`, b:1426) and by save loading (b:8115).
  No UI changes it, so **Z == 2 in practice**.

## 0. Where month-end is called

```java
// b:6860
void aD() {                        // one year
    while (aC <= 12) {
        au();                      // all factions take their turns for month aC
        if (aq != 0) return;
        aE = 0;                    // turn-order start index for the next au()
        aC();                      // MONTH-END (below)
        if (aq != 0) return;       // ending screen was shown -> quit
    }
}
// b:6942
void aF() { while (true) { aD(); if (aq != 0) return; aE(); } }   // aE = year-end
```
`aC()` runs **after** month `aC` is played. It increments `aC` itself, so every "month" condition inside
it (`aC==4`, `2<aC<11`) tests the **new** month. After December, `aC` becomes 13, the while-loop exits, and `aE()` sets
`aC = 1`. The map header shows `aD + "年" + (aC % 13) + "月"` (c:6627).

---

## 1. `b.aC()` month-end (b.java:6719–6858), step by step

```java
void aC() {
    aS = 0;
    // ---- STEP 1: victory / defeat (checked on the OLD month, before the increment)
    if (az()) { aO = 0; N(23); ap(); aq = 1; return; }      // 6721
    if (aA()) { aO = 1; N(23); ap(); aq = 1; return; }      // 6728
    // ---- STEP 2: advance the month
    aC = (byte)(aC + 1);                                      // 6735
    // ---- STEP 3: drought/flood/locust roll, ALL 36 cities (owned, AI, empty alike)
    for (short s = 1; s < 37; s++)
        if (av()) aR[aS++] = (byte)s;                         // 6737
    if (aS < 1) {
        // ---- STEP 4a: no drought/flood/locust -> plague roll, ALL 36 cities
        for (short s = 1; s < 37; s++)
            if (aw()) aR[aS++] = (byte)s;                     // 6746
    } else {
        // ---- STEP 4b: apply the disaster, then show the message
        for (short s = 0; s < aS; s++) D(aR[s]);              // 6757
        aO = s() % 3 + 6;          // 6=drought 7=flood 8=locust. This only picks the TEXT and picture.
        N(19); ax();
        aS = 0;                    // so no plague this month
    }
    // ---- STEP 5: plague (reached only if 4a found some)
    if (aS > 0) {
        for (short s = 0; s < aS; s++) E(aR[s]);              // 6768
        aO = 9; N(19); ax();
        aS = 0;
    }
    // ---- STEP 6: riots. Only cities with an owner (i[s] > 0). Player and AI both.
    for (short s = 1; s < 37; s++)
        if (i[s] > 0 && F((byte)s)) aR[aS++] = (byte)s;       // 6778  (F not called for empty cities -> no RNG)
    if (aS > 0) {
        for (short s = 0; s < aS; s++) H(aR[s]);              // 6788
        aO = 13; N(19); ax();
        aS = 0;
    }
    // ---- STEP 7: AI free growth
    if (Z >= 2) aB();                                         // 6797 (always runs, Z==2)
    // ---- STEP 8: harvest, ALL 36 cities (including empty cities)
    if (aC == 4) {                  // after month 3 has been played
        for (short s = 1; s < 37; s++) I((byte)s);            // gold
        aO = 10; N(19); ax();
    } else if (aC == 10) {          // after month 9 has been played
        for (short s = 1; s < 37; s++) J((byte)s);            // grain
        aO = 11; N(19); ax();
    }
    // ---- STEP 9: HP recovery, officers 1..231 (every slot, dead or not yet appeared too)
    for (short s = 1; s < 232; s++)
        if (L[s] < K[s]) {
            L[s] = (byte)(L[s] + K[s] / 10);                  // byte wrap: only 69 北斗 (K=120) can hit it. L>=116 -> L+12>127 wraps negative
            if (L[s] > K[s]) L[s] = K[s];                     //   and the clamp does not fire (L stays negative). Keep the cast or fix it knowingly.
        }
    // ---- STEP 10: alliance countdown (W = count, X[7] = faction, Y[7] = months left)
    for (short s = (short)(W - 1); s >= 0; s--) {             // 6831
        Y[s] = (byte)(Y[s] - 1);
        if (Y[s] <= 0) {
            for (byte f = 1; f <= 7; f++)
                if (f == X[s]) { aO = 14; at = f; N(19); ax(); break; }   // popup only when 1<=X[s]<=7. Overwrites global `at`
            X[s] = X[W];  Y[s] = Y[W];                        // BUG: copies slot W (one past the end), not W-1
            W = (byte)(W - 1);
        }
    }
    // ---- STEP 11: easter egg
    ay();                                                     // 6852
    // ---- STEP 12: reset turn-order flags
    for (short s = 0; s < 8; s++) aB[s] = 1;                  // 6854 (used by t() b:1714 to draw a random faction order)
}
```

Alliance creation for reference: `bd()` b:7650 does `X[W]=at; Y[W]=12; W++` (no bound check, and X/Y have length 7).
Effect of the removal bug: when the entry that expires is not the last one, slot s receives the stale
`X[W],Y[W]` and the real last entry (index W-1) drops out of range. **One live alliance is silently lost.**
The stale data is 0/0 from init or a previously removed entry. With Y ≤ 0 it expires on the next month-end, with
no popup if X is 0. If `W` reaches 7, `X[W]` throws AIOOBE. Replicate as-is or document the fix.

### 1.1 `az()` victory / `aA()` defeat (b:6641, 6652)
```java
boolean az() { for (int n=1; n<37; n=(byte)(n+1)) if (i[n] != b[ar]) return false; return true; }
boolean aA() { return c[ar] == 0; }
```
Victory requires **all 36 cities, empty ones included**, to have `i == player ruler`. It is only checked at month-end.
Trigger: `aO = 0|1`, `N(23)` (ending screen, §4), `ap()` pumps the key loop until `aq > 0`. That only happens
when the ending screen's final key calls `Sango.notifyDestroyed()`. Then `aq = 1` and return, so `aD`, `aF` and `run()` return.

### 1.2 `av()` / `D(city)` drought/flood/locust (b:6403, 6419)
```java
boolean av() {
    if (aC < 11 && aC > 2) { int n = s() % 500; return n < 4; }   // new month 3..10: p = 4/500 per city
    return false;                                                   // other months: NO RNG draw
}
int a(int n, byte q, int d) { return q * (n /= 2) / d; }            // b:6415  == q*(n/2)/d
void D(byte c) {
    if (q[c] < 90) {
        l[c] = (short)(l[c] - a(l[c], q[c], 90));   // gold
        m[c] = (short)(m[c] - a(m[c], q[c], 90));   // grain
        k[c] = (byte) (k[c] - a(k[c], q[c], 90));   // 统治
    }
    if (q[c] < 99) {
        o[c] = (short)(o[c] - a(o[c], q[c], 99));   // commerce
        n[c] = (short)(n[c] - a(n[c], q[c], 99));   // farmland
    }
    if (q[c] > 0) q[c] = (byte)(q[c] - (q[c] / 10 + 1));
}
```
- Loss is **proportional to q (治水)**: higher water control means more loss, and q==0 means no loss. q ≥ 90 spares gold, grain and 统治.
  q == 99 spares everything except the q decay. This is how the code works. Do not "fix" it silently.
- There is no RNG inside D. The message type is one extra `s()%3` drawn **after** all D calls. Effects are identical
  for 6/7/8.

### 1.3 `aw()` / `E(city)` plague (b:6411, 6445)
```java
boolean aw() { return s() % 1000 <= 1; }            // p = 2/1000, 36 draws, only if no D-disaster this month
void E(byte c) {
    if (q[c] < 90) {
        u[c] = 0;
        for (byte j = 0; j < r[c]; j++) {
            short id = A[c][j];
            J[id] = (short)(J[id] - a(J[id], q[c], 90));    // each officer's troops
            u[c]  = (short)(u[c] + J[id]);                   // total troops recomputed (reserve v is NOT included)
        }
        v[c] = (short)(v[c] - a(v[c], q[c], 90));            // reserve troops
        k[c] = (byte) (k[c] - a(k[c], q[c], 90));
    }
    if (q[c] < 99) p[c] = p[c] - a(p[c], q[c], 99);          // population (int)
    if (q[c] > 0)  q[c] = (byte)(q[c] - (q[c] / 10 + 1));
}
```

### 1.4 `F(city)` riot test / `H(city)` riot damage (b:6471, 6512) + `G`, `a(short,int,int)`
```java
boolean F(byte c) {                         // k = 统治
    if      (k[c] < 15) { int t = k[c]*10/15 + 80;      if (s()%100  >= t) return true; }  // t 80..89
    else if (k[c] < 30) { int t = (k[c]-15)/3 + 90;     if (s()%100  >= t) return true; }  // t 90..94
    else if (k[c] < 40) { int t = (k[c]-30)/5 + 98;     if (s()%100  >= t) return true; }  // t 98..99
    else if (k[c] < 60) { int t = (k[c]-40)/5 + 998;    if (s()%1000 >= t) return true; }  // t 998,999,1000,1001
    return false;                           // k >= 60: no RNG draw, never riots
}
```
Riot probability: k<15 is (20-k*10/15)%, k 15–29 is (10-(k-15)/3)%, k 30–34 is 2%, k 35–39 is 1%, k 40–44 is 0.2%,
k 45–49 is 0.1%, k 50–59 is 0 (but the RNG is still drawn).

```java
void G(byte c) { k[c] = (byte)(k[c] - s() % 15); if (k[c] < 0) k[c] = 0; }                   // b:6497
short a(short v, int num, int den) { int m = v*num/den; return m > 0 ? (short)(v - s()%m) : v; } // b:6504

void H(byte c) {
    int sum = 0;
    if (k[c] < 15) {                                   // tier 1
        p[c] = p[c] / 3;  n[c] = (short)(n[c] / 3);  o[c] = (short)(o[c] / 3);
        l[c] = (short)(l[c] / 2);  m[c] = (short)(m[c] / 2);
        G(c);
        v[c] = a(v[c], 2, 3);
        for (byte j = 0; j < r[c]; j++) { J[A[c][j]] = a(J[A[c][j]], 2, 3); sum += J[A[c][j]]; }
        u[c] = (short)sum; return;
    }
    if (k[c] < 30) {                                   // tier 2
        p[c] = p[c] / 2;  n[c] = (short)(n[c] / 2);  o[c] = (short)(o[c] / 2);
        l[c] = (short)(l[c] * 3 / 7);  m[c] = (short)(m[c] * 3 / 7);
        G(c);
        v[c] = a(v[c], 1, 2);
        for (...) { J[id] = a(J[id], 1, 2); sum += J[id]; }
        u[c] = (short)sum; return;
    }
    // tier 3 (k 30..59)
    p[c] = p[c] * 2 / 3;  n[c] = (short)(n[c] * 2 / 3);  o[c] = (short)(o[c] * 2 / 3);
    l[c] = (short)(l[c] * 1 / 7);  m[c] = (short)(m[c] * 1 / 7);     // NOTE: tier 3 keeps only 1/7 of gold and grain (harsher than tiers 1 and 2)
    G(c);
    v[c] = a(v[c], 1, 5);
    for (...) { J[id] = a(J[id], 1, 5); sum += J[id]; }
    u[c] = (short)sum;
}
```
The tier is chosen from `k` as it is when H starts. G lowers k afterwards. RNG order inside H: G, then v (only if
v*num/den > 0), then each officer in A order (only if its m > 0). All F tests for all cities run **before**
any H.

### 1.5 `aB()` / `K(city)` AI free growth (b:6704, 6656)
```java
void aB() {
    for (int f = 1; f < 8; f++)                       // faction 8 (custom faction) is never grown
        if (a[f] > 0 && a[f] != ar)                   // alive AI factions only. Player excluded.
            for (int j = 0; j < c[f]; j++) K(d[f][j]);  // each city in list order
}
void K(byte c) {
    int n  = s() % 4;
    int n2 = s() % 5 + 1;                             // 1..5
    switch (n) {
        case 0: n[c] = (short)(n[c] + n2);        if (n[c] > 999)    n[c] = 999;    break;  // farmland
        case 1: o[c] = (short)(o[c] + n2);        if (o[c] > 999)    o[c] = 999;    break;  // commerce
        case 2: p[c] = p[c] + n2 * 500;           if (p[c] > 990000) p[c] = 990000; break;  // population
        case 3: q[c] = (byte)(q[c] + (n2/2 + 1)); if (q[c] > 99)     q[c] = 99;             // water control
    }
    n2 = s();                                          // ALWAYS drawn (3 draws per city)
    if      (k[c] < 30) k[c] = (byte)(k[c] + (n2 % 4 + 2));   // +2..5
    else if (k[c] < 40) k[c] = (byte)(k[c] + (n2 % 2 + 1));   // +1..2
    else if (k[c] < 60) k[c] = (byte)(k[c] + n2 % 2);         // +0..1
}
```

### 1.6 Harvest `I` / `J` / `b(dev,k,p)` (b:6589, 6598, 6572)
```java
int b(int dev, int k, int p) {
    int base = 125 * p / 20000;                 // p up to 990000 -> 6187
    int t    = base * dev * 2 / 5 / 125;        // evaluated left to right: ((base*dev*2)/5)/125
    base += t;
    int bonus = 0;
    if      (k > 90) bonus = base * 4 / 5;
    else if (k > 80) bonus = base * 3 / 5;
    else if (k > 70) bonus = base * 2 / 5;
    else if (k > 50) bonus = base / 5;
    return base + bonus;
}
void I(byte c) { int v = l[c] + b(o[c], k[c], p[c]); l[c] = v > 9999 ? 9999 : (short)v; }  // gold from commerce
void J(byte c) { int v = m[c] + b(n[c], k[c], p[c]); m[c] = v > 9999 ? 9999 : (short)v; }  // grain from farmland
```
Applied to **all 36 cities**, empty ones included. The popup (10/11) is shown every year and does not depend on ownership.

### 1.7 `ay()` easter egg (b:6607)
```java
void ay() {
    if (aD < 210) return;                                     // no RNG before year 210
    if (s() % 100 < 2 && b[ar] == i[14])                      // 襄阳 owned by player. The s() draw ALWAYS happens.
        for (n = 0; n < r[14]; n++) if (A[14][n] == 55)  { N[55]=O[55]=M[55]=K[55]=99; }   // 张郃 (CSV id 55)
    if (s() % 100 < 3 && b[ar] == i[2])                       // 云南 owned by player
        for (n = 0; n < r[2];  n++) if (A[2][n]  == 193) { N[193]=O[193]=M[193]=K[193]=99; } // 金环三结 (id 193)
}
```
(Order of the assignments: N, O, M, K. L is not touched.)

### 1.8 RNG call order per month-end (summary)
1. The victory and defeat checks draw nothing.
2. `av`: 36 draws if the new month is 3..10, otherwise 0.
3. If any city was hit: 1 draw (`%3`) for the message. Otherwise `aw`: 36 draws.
4. `F`: 1 draw per owned city (i>0) with k<60, cities in 1..36 order.
5. `H`: for each rioting city in list order: G(1), v(0/1), each officer (0/1).
6. `aB`: 3 draws per AI city (faction 1→7, then d[] order).
7. Harvest, HP, alliances: 0 draws.
8. `ay`: 2 draws if aD ≥ 210.

---

## 2. Year-end `aE()` (b:6894)

```java
void aE() {
    short s = 0;
    aC = 1;
    aD = (short)(aD + 1);
    l(aD);                                    // officers with T==aD appear as 在野 in B[U]
    for (int n = 0; n < 26; n = (byte)(n + 1)) {
        int city = 0;
        if (aa[n] == aD) {
            short follower = ab[n];
            for (int c = 1; c < 37; c = (byte)(c + 1)) {           // find the leader SERVING (A list) in any city
                for (byte j = 0; j < r[c]; j++)
                    if (A[c][j] == ac[n]) { s = ac[n]; city = c; break; }
                if (city > 0) break;
            }
            if (city != 0) {
                if (r[city] < 10) {                                 // A is short[37][10]
                    A[city][r[city]++] = follower;                  // joins as serving officer. Nothing else changes:
                                                                    //   no u/J update, not removed from any other list.
                    if (i[city] == b[ar]) { aO = 12; ax = follower; ay = s; N(19); ax(); }  // popup only in player cities
                } else {
                    B[city] = b(B[city], this.s[city], follower);  // city full -> append to 在野 list
                    this.s[city]++;                                 // (no popup)
                }
            }
            // leader not serving anywhere (not appeared, 在野, dead, captured): nothing happens, and it is never retried
        }
    }
}
```
Tables (b:1427–1429). Names come from `officers.csv` (identical to the jar's `/res/lib116.jg`, 228 rows, row n = id n,
col13 = T appearance year, col14 = U city).

| n | year aa | follower ab | leader ac |
|---|---|---|---|
| 0 | 192 | 138 程普 (T189,U27) | 69 北斗 (T208,U29) |
| 1 | 192 | 140 朱桓 (T189,U33) | 1 管辂 (T202,U3) |
| 2 | 194 | 160 张任 (T189,U6) | 1 管辂 |
| 3 | 195 | 172 张鲁 (T189,U6) | 1 管辂 |
| 4 | 196 | 179 黄月英 (T189,U15) | 57 夏侯渊 (T289,U23) |
| 5 | 197 | 190 董茶奴 (T189,U7) | 1 管辂 |
| 6 | 197 | 191 高定 (T189,U7) | 69 北斗 |
| 7 | 198 | 194 朵思大王 (T190,U16) | 5 关羽 (T289,U17) |
| 8 | 199 | 200 马腾 (T189,U9) | 4 南斗 (T206,U36) |
| 9 | 199 | 201 华雄 (T189,U9) | 69 北斗 |
| 10 | 200 | 204 程银 (T189,U5) | 61 曹操 (T289,U25) |
| 11 | 201 | 210 韩遂 (T189,U3) | 61 曹操 |
| 12 | 202 | 211 曹丕 (T202,U0) | 61 曹操 |
| 13 | 203 | 212 庞柔 (T189,U3) | 6 于吉 (T204,U2) |
| 14 | 203 | 213 庞德 (T189,U6) | 61 曹操 |
| 15 | 203 | 214 马云禄 (T189,U3) | 63 夏侯惇 (T289,U25) |
| 16 | 204 | 215 越吉 (T190,U3) | 61 曹操 |
| 17 | 204 | 216 蔡氏 (T189,U21) | 5 关羽 |
| 18 | 205 | 217 蔡瑁 (T189,U21) | 61 曹操 |
| 19 | 205 | 218 韩德 (T191,U5) | 5 关羽 |
| 20 | 208 | 219 马铁 (T192,U3) | 162 司马懿 (T294,U23) |
| 21 | 208 | 220 马休 (T193,U3) | 63 夏侯惇 |
| 22 | 209 | 222 文鸯 (T189,U4) | 162 司马懿 |
| 23 | 210 | 225 刘氏 (T189,U20) | 211 曹丕 (T202,U0) |
| 24 | 210 | 226 蔡氏 (T289,U15) | 161 诸葛亮 (T294,U15) |
| 25 | 210 | 227 诸葛瞻 (T193,U15) | 164 诸葛瑾 (T294,U31) |

**Do the ids plausibly match? Mostly no.**
- Every leader id is either a hidden "special" officer (1 管辂, 4 南斗, 6 于吉, 69 北斗, LV 8, T 202–208) or the
  **never-appearing duplicate** of a famous officer. The real 曹操 is 35, 关羽 is 7, 夏侯惇 is 52, 司马懿 is 81,
  诸葛亮 is 178 and 曹丕 is 42. The ac ids 61/5/63/57/162/161/164 are copies with T=289/294, and 211 曹丕 has U=0.
- A few pairs make historical sense by name (庞德, 韩遂, 蔡瑁, 程银 and 曹丕 following 曹操), but most do not (程普 following 北斗,
  朵思大王 following 关羽, 马铁 following 司马懿, and so on). The table was probably written against a different roster numbering.
- **With the shipped data, not one of the 26 entries can fire.** In every entry the leader's T is greater than aa, or the leader
  has U=0 (entry 23 曹丕 211: `k()` returns early when `U<=0`, so the leader never reaches any list). Officers only enter
  `A[][]` through CSV col15=1 at T==189 or by being recruited after appearing, so the leader is never serving
  in year aa.
- Even if an entry fired, most followers (T189) are already placed at game start, so they would be duplicated.
- Port recommendation: implement the logic faithfully (it is effectively a no-op), or remap ac to the real ids as a
  documented house rule.

---

## 3. Event popup, screen 19 (`c.a(19)` c:889, `c.e()` c:3241, `c.R()` c:3225)

Screen init (c:890): `if (aO < 14) bb[1] = lib11 (frame) else if (bd==null) bd = lib20 (dialog box)`, then `e()`.
Images are `/res/lib<N>.jg` (PNG with a patched header, c:6407).

Draw `R()`:
- `aO < 14`: fill black, draw `bb[0]` (event picture) at (W/2, 24) with anchor HCENTER|TOP, draw a 0x996600 rect at
  (12, 24, picW, picH), draw the frame `bb[1]` at (W/2, 16), and draw white text `bx` wrapped in box (10, 150, 150×100).
- `aO >= 14`: no picture. Draw the dialog `a(g, bx)`: lib20 at (88, 50) anchor 17, white text in (20, 65, 130×95), over
  the current map.

Dismiss: any key (`bv()` waits for `ao.a != 0`), then `N(4)`. Exception: code 1 when the player is the target, see below.

| aO | picture | text template (exact) | variables | set by |
|---|---|---|---|---|
| 1 | lib120 | `G[i[au]] + " 军攻打 " + h[av] + "..."` | au = attacker city, av = target city | AI attack `t(int)` b:9698 (player target: uses `bv()` without N(4), then `aq=3` → defense battle), b:9714 (AI vs AI, before resolution) |
| 2 | lib121 | `G[i[au]] + " 占领空城 " + h[av] + "!"` | as above | b:9707, target empty |
| 3 | lib120 | `G[0] + " 军攻打 " + h[0] + "..."` | (junk indices) | **never set** |
| 4 | lib122 | `G[i[au]] + " 军攻占了 " + h[av] + "!"` | | b:9717, AI attack won |
| 5 | lib123 | `G[i[au]] + " 军攻打 " + h[av] + "失败了!"` | | b:9717, AI attack lost |
| 6 | lib124 | `" 发生旱灾!波及城市:"` + for each aR[0..aS-1]: `" " + h[city]` | aS/aR | month-end D |
| 7 | lib125 | `"发生水灾!波及城市:"` + same list | | month-end D |
| 8 | lib126 | `"发生蝗灾!波及城市:"` + same list | | month-end D |
| 9 | lib127 | `"发生瘟疫!波及城市:"` + same list | | month-end plague |
| 10 | lib128 | `"收金的季节到了!"` | | month-end aC==4 |
| 11 | lib129 | `"收粮的季节到了!"` | | month-end aC==10 |
| 12 | lib130 | `G[ax] + "跟随 " + G[ay] + " 加入我军为君主效力!"` | ax = follower, ay = leader | aE (dead with shipped data) |
| 13 | lib136 | `"百姓发生骚乱,波及城市:"` + same list | | month-end riots |
| 14 | none (dialog) | `G[b[at]] + "　同盟解除!"` (U+3000 full-width space) | at = ex-ally faction | month-end alliance expiry |
| 15 | none | *(no assignment: shows the previous `bx`)* | | never set |
| 16 | none | `G[ay] + "正与敌方会谈中..."` | ay = player officer being courted | b:10313 (`i[city]==player`) |
| 17 | none | `"糟了," + G[ay] + "投奔了" + G[i[au]]` | ay = defector, au = enemy city | b:10323 (defection succeeded from a player city) |
| 18 | none | `G[ay] + " 势力灭亡了!"` | ay = old ruler | b:9723 (AI attack destroyed faction `at`) |
| 19 | none | `G[ay] + "死亡,新君主 " + G[b[at]] + " 继位!"` | | **never set**. The bytecode at b:9728 (ruler changed after an AI attack) calls `N(19)` without assigning aO, so the popup **re-shows the previous code (4 or 5)**. This is a bug. |

Notes
- For 6/7/8/9/13 the list is **all** affected cities (including enemy and empty ones), each prefixed by one ASCII space.
  Only code 6 starts with a leading space.
- `c.e()` first logs `"AI事件类型:"+aO` (debug only).
- A separate overlay path, `c.a(byte,short,short)` c:3360 (not screen 19), is used for the player's own events: 2 → `G[s]+"　君主死亡!"`,
  5 → `G[s]+" 势力灭亡了!"`, 6 → `G[s]+"死亡,新君主 "+G[s2]+" 继位!"`.

---

## 4. Ending screen 23 and scoring

### 4.1 Screen init `c.a(23)` (c:939)
```java
b.k();                      // compute score (always, victory or defeat)
bj = lib11;                 // frame
if (b.aO == 1) {            // DEFEAT
    bb[0] = lib132;
    g = G[b[ar]] + d.j[1];  // "<ruler> 势力被消灭了,全国局势仍处于混乱之中..."
    cm = true;
} else {                    // VICTORY (aO == 0)
    bb[0] = lib131;
    g = G[b[ar]] + d.j[0];  // "<ruler> 经过多年征战,终于实现了宇内混一!"
    co = b.cT;              // == 0 in practice (see 4.3)
}
```
`d.j = {" 经过多年征战,终于实现了宇内混一!", " 势力被消灭了,全国局势仍处于混乱之中..."}` (each starts with a space).

### 4.2 Draw `c.y()` (c:1985) and keys `c.t()` (c:3561)
Draw: fill black. Draw the picture at (W/2, 24), the 0x996600 rect at (12, 24, picW, picH), and the frame lib11 at (W/2, 16).
Draw yellow 0xFFFF00 text `g` wrapped in (8, 135, 150×70). If `cn` is set, `g` is first replaced by
`"统一度:" + b.cS + " 分数:" + b.cR`.

Key handler (called from `b.ap()` on each key, b:6250):
```java
void t() {
    if (cn || cm) { cn = false; b.aq = 1; o.a(); }   // Sango.a() == notifyDestroyed(): the MIDlet EXITS
    if (co < 7) { g = by = b.a(co); co++; return; }  // b.a(n) = cX[n][cW[n]]
    cn = true;
}
```
- **Victory sequence:** the title text, then keys 1–7 show category verdicts 0..6 (`cX[k][cW[k]]`), then key 8 shows the
  "统一度:X 分数:Y" line, then key 9 exits the application.
- **Defeat:** the defeat text. Key 1 exits the application (the verdict assignment after `o.a()` is irrelevant).
- After exit, `ap()` returns (aq=1), `aC()` sets aq=1 and returns, and `aD`, `aF`, `run()` unwind. There is no return to the title.

### 4.3 Scoring `b.k()` (b:616) and helpers
```java
int interp(int x, int lo, int hi, int outLo, int outHi) { return (x-lo)*(outHi-outLo+1)/(hi-lo+1) + outLo; }  // b:224  a(5 ints)
int prog(int x, int lo, int hi) { return x * 1000 / (hi - lo + 1); }                                        // b:228  a(3 ints)
void worst(int w, int pr, byte cat) {                                                                       // b:232  a(int,int,byte)
    if (w < cU) { cU = w; if (w >= 1000) { cT = 8; return; } cT = cat; cV = pr; return; }
    if (w < 1000 && w == cU && pr < cV) { cT = cat; cV = pr; }
}
void k() {
    a(); b(); c(); d(); e(); f(); g(); h(); i(); j();   // each adds to cR (int, never reset. Default 0)
    cS = cR / 10;                                       // "统一度"
    if (Z == 2) cR = aO * 3 / 2;                        // verified in bytecode: getfield aO. Victory aO=0 -> cR=0
}
```
- `cU`, `cV`, `cT`, `cQ` are never assigned anywhere else (default 0). So `w < cU` (w ≥ 250) is never true and **cT stays 0**.
  The "worst category" logic and the `cT==9 → cT=8` lines in h()/i() are dead. The verdicts always start at category 0.
- Because Z == 2, the **displayed 分数 is always 0 on victory** (1 on defeat, but it is never displayed there).
  "统一度" = raw total/10 is the only meaningful number. Faithful port: show `分数:0`, and note it as an original bug.

Category functions (cW index = verdict table row). "avg36" means the sum over cities 1..36 divided by 36 (int). "officers*" means
`for city in [1, lastCity): for slot j = 1 .. r[city]-1` (**slot 0 skipped**).

| fn | cW idx | metric | tiers → (cW value, cR add) | weight / progress (dead) |
|---|---|---|---|---|
| a() b:286 | 0 | avg36 of n (farmland) → `a(avg,0)` b:249 | ≥999:(6,+100) · ≥900:(5,interp 900..998→85..90) · ≥700:(4,700..899→80..84) · ≥500:(3,500..699→60..79) · ≥200:(2,200..499→30..59) · ≥100:(1,100..199→1..29) · else (0,+0) | w=2000/2000/1000/1000/750/500/250 |
| b() b:296 | 1 | avg36 of o (commerce) → same `a(avg,1)` | same as a() | same |
| c() b:306 | 2 | avg36 of q (water control) | ≥99:(4,+30) · ≥90:(3,90..98→10..20) · ≥70:(2,70..89→5..9) · ≥50:(1,50..69→1..4) · else (0,0) | |
| d() b:338 | 3 | avg36 of p (population, int sum) | ≥990000:(5,+100) · ≥500000:(4,500000..989999→80..90) · ≥200000:(3,→70..79) · ≥100000:(2,→50..69) · ≥50000:(1,→1..49) · else (0,0) | |
| e() b:378 | 5 | avg36 of k (统治) | ≥99:(5,+70) · ≥90:(4,90..98→40..60) · ≥80:(3,80..89→30..39) · ≥60:(2,60..79→10..29) · ≥30:(1,30..59→1..9) · else (0,0) | |
| f() b:414 | 6 | Σ J (troops) over officers*, cities 1..**36**, **/222** | ≥1000:(5,+100) · ≥900:(4,900..999→60..80) · ≥800:(3,→50..59) · ≥700:(2,→20..49) · ≥500:(1,→1..19) · else (0,0) | |
| g() b:452 | 4 | start **-100**, + Σ P (loyalty) over officers*, cities 1..**35**, **/221** | ≥99:(6,+100) · ≥95:(5,95..98→50..70) · ≥90:(4,90..94→30..49) · ≥80:(3, `interp(n,80,899,29,29)` = +29) · ≥60:(2,60..79→10..19) · ≥36:(1,36..59→1..9) · else (0,0) | |
| h() b:500 | – | Σ Q (strategy points ×100) over officers*, cities 1..35, /222 | ≥30000:+100 · ≥20000:interp→50..70 · ≥15000:→30..49 · ≥10000:+29 · ≥7000:→1..19 · else 0 | |
| i() b:533 | – | Σ N (int) of officers* with N<90, cities 1..35. Divisor starts at 222 and is decremented once for each officer with N≥90 | ≥89:+100 · ==88:+80 · ==87:+60 · ==86:+50 · 80..85: interp(80,84→20,40) (85 gives 41) · 70..79: →1..19 · else 0 | |
| j() b:569 | – | per officer* (cities 1..35): R∈{15,23}:+45 · R==22:+55 · R∈{5,6,7}:+100 · R==14: `if(++cnt>3) no add; else +100` (cnt starts at `cQ∈{4,6,7}?1:0` = 0) · then S==30:+55 · S==31:+100 | `cR += n*100/800` | |

Verdict tables `cX[row][cW]` (b:1642), in display order (row = `co` 0..6):

- **Row 0, farmland (a):** 0 "极度贫瘠的土地, 根本无法生产出粮食,饿殍遍野,百姓苦不堪言" · 1 "土地资源非常匮乏,粮食收成非常低下,各地百姓饿死现象时有发生,无法继续生活下去" · 2 "农业发展较为落后,粮食产量仍无法满足百姓的生活,各地田里,地里一片菜色" · 3 "随着农业地逐步发展,粮食产量基本达到了百姓生活地需求" · 4 "随着土地地不断开发,粮食产量已较为富裕,百姓吃饭问题已完全解决了" · 5 "新的农业工具地不断出现,在土地作业中的逐渐普及,各地粮食已极为充裕" · 6 "农业发展空前,粮食年年丰产,人民衣食无忧"
- **Row 1, commerce (b):** 0 "极度落后的商业,人们最基本的物品交换都无法进行.国家贫苦不堪" · 1 "商业资源非常贫乏,人们无法得到最基本的生活用品,生活非常艰辛" · 2 "商业发展仍较为落后,商务活动较少, 百姓生活水平非常低下" · 3 "随着商业地逐步发展,商务活动基本达到了百姓生活地需求" · 4 "随着官府的不断重视,商业发展已较为发达,国家经济状况有了很大改善" · 5 "与外国的经济往来,使得国家商务发展极度发达,百姓生活在了一个富裕的国家中" · 6 "国家的经济发展举世罕见, 新的领域的经济往来不断增强本国的经济实力"
- **Row 2, water control/disasters (c):** 0 "瘟疫,蝗虫,旱涝,各种灾害遍地,民不聊生" · 1 "瘟疫等这种灾害仍时有发生,百姓灾难深重" · 2 "由于新国家官府的一定重视,灾害在一定程度上得到了遏制" · 3 "经过官府的不断努力,灾害防御措施逐渐实施,百姓基本远离了灾害痛苦" · 4 "大坝建成,各种治理灾害措施井然有序,百姓再无灾害之忧"
- **Row 3, population (d):** 0 "经过多年的战争,各地一片荒芜,各城市人丁稀少,百里难见一人" · 1 "战后恢复仍没有足够的进行,各城市劳动力资源仍稍显不足" · 2 "战争的硝烟逐渐远去,各城市人口资源已完全满足了劳动的需求" · 3 "国家开始重视了城市的建设,城市规模发展稳定,大型城市逐步兴起" · 4 "经过国家多方面努力,城市向都市化发展开始,各城市欣欣向荣" · 5 "各地发展平衡,都市化发展完成,国家发展空前,百姓安居乐业"
- **Row 4, loyalty (g):** 0 "部下野心勃勃,叛变,反乱事件层出不穷,国家马上将被颠覆" · 1 "属下人心浮动,流言四起,某些武将的叛乱似乎已在眼前" · 2 "大多武将忠于君主,但奸臣当道,叛变的种子仍远未消失" · 3 "君主赏罚分明,恩威并施,下属武将对国家较为忠诚" · 4 "君主善于御人之道, 武将们对君主, 国家忠心耿耿" · 5 "以恩义著称的新君主,得到了属下誓死效忠,各武将绝无二心" · 6 "经过多年的同生共死,臣僚们用鲜血来发誓,子子孙孙誓死效忠君主"
- **Row 5, 统治/public support (e):** 0 "新国家的所作所为,百姓深恶痛疾,各地烽烟四起,起义遍地,无法控制" · 1 "新国家并没有实施仁政,百姓对新国家非常不满, 起义的种子四处萌发" · 2 "新国家的仁政思想逐渐传播,逐渐使得百姓对新的国家基本上认可了" · 3 "君主一向以仁政而闻名,国家建立以来,得到了各地百姓的支持和欢迎" · 4 "君主贤名远播,管理国家政策得当,施以百姓的仁政深入人心" · 5 "全国百姓坚决拥护新的国家,君主已成为大众的偶像,和精神砥柱"
- **Row 6, military (f):** 0 "多年战争造成国家兵甲极度不足,远远无法抵御外敌侵略,随时有灭国危险" · 1 "战后兵甲仍然不足,无法抵御匈奴等外敌的大规模进攻,国家岌岌可危" · 2 "匈奴,外族虎视耽耽,全部兵丁已派往前线,与强大的敌人达成微弱的军事平衡" · 3 "在战争积累了足够军事经验,国家军事实力稳定,在随后的战争中数次战败外侵略者" · 4 "武器,阵法的更新,将领的培养,使得军事实力强大,征服了许多周围的小国家" · 5 "军事实力空前,对周边国家形成了强大的震慑作用.各国纷纷遣使进贡,或从属.强大的帝国已经产生"

Final line: `"统一度:" + (rawTotal/10) + " 分数:" + cR` (cR = 0 on victory because Z==2). Then the next key calls `notifyDestroyed()`.

### 4.4 Anomalies to keep or document
1. `cR = aO*3/2` destroys the score (b:629, confirmed in bytecode). The intent was probably `cR*3/2`.
2. The worst-category (cT/cU/cV) machinery is dead. Verdicts always run 0→6.
3. g() starts at -100 and divides by 221. f() divides by 222. g/h/i/j skip city 36. f..j skip A-slot 0. g's 80–89 tier uses hi=899.
4. Alliance removal copies slot W instead of W-1 (§1 step 10).
5. Riot tier 3 (k 30–59) cuts gold and grain to 1/7, harsher than tiers 1 and 2.
6. Disaster/plague loss scales with q (治水) upward.
7. aO=19 is never assigned, so a successor-ruler popup re-shows the previous code (4/5).
8. All 26 follower-table entries are unreachable with the shipped officer data.
