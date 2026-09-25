/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.rms.RecordStore
 *  javax.microedition.rms.RecordStoreNotOpenException
 */
import java.io.InputStream;
import java.util.Random;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreNotOpenException;

public final class a
extends Thread {
    public byte[] a = new byte[9];
    public short[] b = new short[9];
    public String[] c = new String[9];
    public byte[] d = new byte[9];
    public byte[][] e = new byte[9][];
    public byte[] f;
    public short[][] g;
    public byte[] h;
    public String[] i;
    public short[] j;
    public short[] k;
    public byte[] l;
    public short[] m;
    public short[] n;
    public short[] o;
    public short[] p;
    public int[] q;
    public byte[] r;
    public byte[] s;
    public byte[] t;
    public byte[] u;
    public short[] v;
    public short[] w;
    public byte[] x;
    public byte[] y;
    public byte[] z;
    public byte[] A;
    public short[][] B;
    public short[][] C;
    public short[][] D;
    public byte[][] E;
    public byte[] F;
    public short[] G;
    public String[] H;
    public byte[] I;
    public byte[] J;
    public short[] K;
    public byte[] L;
    public byte[] M;
    public byte[] N;
    public byte[] O;
    public byte[] P;
    public byte[] Q;
    public short[] R;
    public byte[] S;
    public byte[] T;
    public short[] U;
    public byte[] V;
    public boolean[] W;
    public byte X;
    public byte[] Y;
    public byte[] Z;
    public byte aa;
    public short[] ab;
    public short[] ac;
    public short[] ad;
    public String[] ae;
    public short[] af;
    public byte[] ag;
    public byte[][] ah;
    public byte[][] ai;
    public byte[][] aj;
    public byte[][] ak;
    public short[] al;
    public byte[] am;
    public short[] an;
    public String[] ao;
    public byte[] ap;
    public b aq;
    public Random ar;
    public byte as;
    public byte at;
    public byte au;
    public byte av;
    public byte aw;
    public byte ax;
    public byte ay;
    public short az;
    public short aA;
    public short[] aB;
    public byte aC;
    public byte[] aD;
    public byte aE;
    public short aF;
    public byte aG;
    public byte aH;
    public byte aI;
    public boolean aJ;
    public boolean aK;
    public short aL;
    public String aM;
    public boolean aN;
    public short[] aO;
    public byte aP;
    public int aQ;
    public int aR;
    public byte[] aS;
    public byte[] aT;
    public byte aU;
    public byte aV;
    public boolean aW;
    public short aX;
    public short aY;
    public byte aZ;
    public short ba;
    public short bb;
    public short bc;
    public short bd;
    public short[] be;
    public short[] bf;
    public byte bg;
    public byte bh;
    public byte bi;
    public byte bj;
    public byte[][] bk;
    public byte bl;
    public byte bm;
    public boolean bn;
    public byte[] bo;
    public byte[] bp;
    public byte[] bq;
    public byte[] br;
    public byte[] bs;
    public byte[] bt;
    public byte bu;
    public byte bv;
    public byte bw;
    public byte bx;
    public byte by;
    public boolean bz;
    public byte bA;
    public String bB;
    public boolean bC;
    public byte bD;
    public byte bE;
    public byte bF;
    public byte bG;
    public byte bH;
    public byte[][] bI;
    public byte bJ;
    public byte bK;
    public byte bL;
    public byte bM;
    public byte bN;
    public byte[][] bO;
    public byte[][] bP;
    public byte[][] bQ;
    public byte[][] bR;
    public short[][] bS;
    public short[][] bT;
    public byte[][] bU;
    public byte[][] bV;
    public byte[][] bW;
    public boolean[][] bX;
    public boolean[][] bY;
    public byte bZ;
    public byte ca;
    public byte cb;
    public byte cc;
    public short[] cd;
    public short[] ce;
    public byte cf;
    public byte[] cg;
    public byte ch;
    public boolean ci;
    public boolean cj;
    public byte ck;
    public byte cl;
    public byte cm;
    public byte cn;
    public byte co;
    public byte cp;
    public byte cq;
    public byte cr;
    public boolean cs;
    public short ct;
    public short cu;
    public short cv;
    public short cw;
    public boolean cx;
    public byte cy;
    public byte cz;
    public byte cA;
    public byte cB;
    public int cC;
    public boolean cD;
    public byte cE;
    public int cF;
    public byte cG;
    public byte cH;
    public byte cI;
    public int cJ;
    public int cK;
    public byte cL;
    public int cM;
    public int cN;
    public byte[] cO;
    public String[][] cP;

    public final int a(int n, int n2, int n3, int n4, int n5) {
        return n * (n5 - n4 + 1) / (n3 - n2 + 1) + n4;
    }

    public final int a(int n, int n2, int n3) {
        return n * 1000 / (n3 - n2 + 1);
    }

    public final void a(int n, int n2, byte by) {
        if (n < this.cM) {
            this.cM = n;
            if (n == 1000) {
                this.cL = (byte)8;
                return;
            }
            this.cL = by;
            this.cN = n2;
            return;
        }
        if (n < 1000 && n == this.cM && n2 < this.cN) {
            this.cL = by;
            this.cN = n2;
        }
    }

    public final void a(int n, int n2) {
        int n3;
        int n4 = 0;
        if (n >= 999) {
            n3 = 2000;
            this.cO[n2] = 6;
            this.cJ += 100;
        } else if (n >= 900) {
            n3 = 2000;
            this.cO[n2] = 5;
            this.cJ += this.a(n, 900, 998, 85, 90);
        } else if (n >= 700) {
            n3 = 1000;
            this.cO[n2] = 4;
            this.cJ += this.a(n, 700, 899, 80, 84);
        } else if (n >= 500) {
            n3 = 1000;
            this.cO[n2] = 3;
            this.cJ += this.a(n, 500, 699, 60, 79);
        } else if (n >= 200) {
            n3 = 750;
            this.cO[n2] = 2;
            n4 = this.a(n, 200, 499);
            this.cJ += this.a(n, 200, 499, 30, 59);
        } else if (n >= 100) {
            n3 = 500;
            this.cO[n2] = 1;
            n4 = this.a(n, 100, 199);
            this.cJ += this.a(n, 100, 199, 1, 29);
        } else {
            n3 = 250;
            this.cO[n2] = 0;
            n4 = this.a(n, 0, 99);
        }
        this.a(n3, n4, (byte)0);
    }

    public final void a() {
        int n = 0;
        int n2 = 1;
        while (n2 < 37) {
            n += this.o[n2];
            n2 = (byte)(n2 + 1);
        }
        this.a(n /= 36, 0);
    }

    public final void b() {
        int n = 0;
        int n2 = 1;
        while (n2 < 37) {
            n += this.p[n2];
            n2 = (byte)(n2 + 1);
        }
        this.a(n /= 36, 1);
    }

    public final void c() {
        int n;
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        while (n4 < 37) {
            n2 += this.r[n4];
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 36) >= 99) {
            n = 2000;
            this.cO[2] = 4;
            this.cJ += 30;
        } else if (n2 >= 90) {
            n = 1000;
            this.cO[2] = 3;
            this.cJ += this.a(n2, 90, 98, 10, 20);
        } else if (n2 >= 70) {
            n = 1000;
            this.cO[2] = 2;
            this.cJ += this.a(n2, 70, 89, 5, 9);
        } else if (n2 >= 50) {
            n = 667;
            this.cO[2] = 1;
            n3 = this.a(n2, 50, 69);
            this.cJ += this.a(n2, 50, 69, 1, 4);
        } else {
            n = 333;
            this.cO[2] = 0;
            n3 = this.a(n2, 0, 49);
        }
        this.a(n, n3, (byte)2);
    }

    public final void d() {
        int n;
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        while (n4 < 37) {
            n2 += this.q[n4];
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 36) >= 990000) {
            n = 2000;
            this.cO[3] = 5;
            this.cJ += 100;
        } else if (n2 >= 500000) {
            n = 2000;
            this.cO[3] = 4;
            this.cJ += this.a(n2, 500000, 989999, 80, 90);
        } else if (n2 >= 200000) {
            n = 1000;
            this.cO[3] = 3;
            this.cJ += this.a(n2, 200000, 499999, 70, 79);
        } else if (n2 >= 100000) {
            n = 1000;
            this.cO[3] = 2;
            this.cJ += this.a(n2, 100000, 199999, 50, 69);
        } else if (n2 >= 50000) {
            n = 667;
            this.cO[3] = 1;
            n3 = this.a(n2, 50000, 99999);
            this.cJ += this.a(n2, 50000, 99999, 1, 49);
        } else {
            n = 333;
            this.cO[3] = 0;
            n3 = this.a(n2, 0, 49999);
        }
        this.a(n, n3, (byte)3);
    }

    public final void e() {
        int n;
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        while (n4 < 37) {
            n2 += this.l[n4];
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 36) >= 99) {
            n = 2000;
            this.cO[5] = 5;
            this.cJ += 70;
        } else if (n2 >= 90) {
            n = 2000;
            this.cO[5] = 4;
            this.cJ += this.a(n2, 90, 98, 40, 60);
        } else if (n2 >= 80) {
            n = 1000;
            this.cO[5] = 3;
            this.cJ += this.a(n2, 80, 89, 30, 39);
        } else if (n2 >= 60) {
            n = 1000;
            this.cO[5] = 2;
            this.cJ += this.a(n2, 60, 79, 10, 29);
        } else if (n2 >= 30) {
            n = 667;
            this.cO[5] = 1;
            n3 = this.a(n2, 30, 59);
            this.cJ += this.a(n2, 30, 59, 1, 9);
        } else {
            n = 333;
            this.cO[5] = 0;
            n3 = this.a(n2, 0, 29);
        }
        this.a(n, n3, (byte)5);
    }

    public final void f() {
        int n;
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        while (n4 < 37) {
            byte by = 1;
            while (by < this.s[n4]) {
                n2 += this.K[this.B[n4][by]];
                by = (byte)(by + 1);
            }
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 222) >= 1000) {
            n = 2000;
            this.cO[6] = 5;
            this.cJ += 100;
        } else if (n2 >= 900) {
            n = 2000;
            this.cO[6] = 4;
            this.cJ += this.a(n2, 900, 999, 60, 80);
        } else if (n2 >= 800) {
            n = 1000;
            this.cO[6] = 3;
            this.cJ += this.a(n2, 800, 899, 50, 59);
        } else if (n2 >= 700) {
            n = 1000;
            this.cO[6] = 2;
            this.cJ += this.a(n2, 700, 799, 20, 49);
        } else if (n2 >= 500) {
            n = 667;
            this.cO[6] = 1;
            n3 = this.a(n2, 500, 699);
            this.cJ += this.a(n2, 500, 699, 1, 19);
        } else {
            n = 333;
            this.cO[6] = 0;
            n3 = this.a(n2, 0, 499);
        }
        this.a(n, n3, (byte)6);
    }

    public final void g() {
        int n;
        int n2 = -100;
        int n3 = 0;
        int n4 = 1;
        while (n4 < 36) {
            byte by = 1;
            while (by < this.s[n4]) {
                n2 += this.Q[this.B[n4][by]];
                by = (byte)(by + 1);
            }
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 221) >= 99) {
            n = 2000;
            this.cO[4] = 6;
            this.cJ += 100;
        } else if (n2 >= 95) {
            n = 2000;
            this.cO[4] = 5;
            this.cJ += this.a(n2, 95, 98, 50, 70);
        } else if (n2 >= 90) {
            n = 1000;
            this.cO[4] = 4;
            this.cJ += this.a(n2, 90, 94, 30, 49);
        } else if (n2 >= 80) {
            n = 1000;
            this.cO[4] = 3;
            this.cJ += this.a(n2, 80, 899, 29, 29);
        } else if (n2 >= 60) {
            n = 750;
            this.cO[4] = 2;
            n3 = this.a(n2, 60, 79);
            this.cJ += this.a(n2, 60, 79, 10, 19);
        } else if (n2 >= 36) {
            n = 500;
            this.cO[4] = 1;
            n3 = this.a(n2, 36, 59);
            this.cJ += this.a(n2, 36, 59, 1, 9);
        } else {
            n = 250;
            this.cO[4] = 0;
            n3 = this.a(n2, 0, 35);
        }
        this.a(n, n3, (byte)4);
    }

    public final void h() {
        int n = 0;
        int n2 = 1;
        while (n2 < 36) {
            byte by = 1;
            while (by < this.s[n2]) {
                n += this.R[this.B[n2][by]];
                by = (byte)(by + 1);
            }
            n2 = (byte)(n2 + 1);
        }
        if ((n /= 222) >= 30000) {
            this.cJ += 100;
        } else if (n >= 20000) {
            this.cJ += this.a(n, 20000, 29999, 50, 70);
        } else if (n >= 15000) {
            this.cJ += this.a(n, 15000, 19999, 30, 49);
        } else if (n >= 10000) {
            this.cJ += this.a(n, 10000, 14999, 29, 29);
        } else if (n >= 7000) {
            this.cJ += this.a(n, 7000, 9999, 1, 19);
        }
        if (this.cL == 9 && n < 20000) {
            this.cL = (byte)8;
            this.cM = 1000;
        }
    }

    public final void i() {
        int n = 0;
        int n2 = 222;
        int n3 = 1;
        while (n3 < 36) {
            byte by = 1;
            while (by < this.s[n3]) {
                byte by2 = this.O[this.B[n3][by]];
                if (by2 < 90) {
                    n += by2;
                } else {
                    --n2;
                }
                by = (byte)(by + 1);
            }
            n3 = (byte)(n3 + 1);
        }
        if ((n /= n2) >= 89) {
            this.cJ += 100;
        } else if (n == 88) {
            this.cJ += 80;
        } else if (n == 87) {
            this.cJ += 60;
        } else if (n >= 86) {
            this.cJ += 50;
        } else if (n >= 80) {
            this.cJ += this.a(n, 80, 84, 20, 40);
        } else if (n >= 70) {
            this.cJ += this.a(n, 70, 79, 1, 19);
        }
        if (this.cL == 9 && n < 80) {
            this.cL = (byte)8;
            this.cM = 1000;
        }
    }

    public final void j() {
        int n = 0;
        int n2 = 0;
        if (this.cI == 4 || this.cI == 6 || this.cI == 7) {
            n2 = 1;
        }
        int n3 = 1;
        while (n3 < 36) {
            byte by = 1;
            while (by < this.s[n3]) {
                switch (this.S[this.B[n3][by]]) {
                    case 15: 
                    case 23: {
                        n += 45;
                        break;
                    }
                    case 22: {
                        n += 55;
                        break;
                    }
                    case 5: 
                    case 6: 
                    case 7: {
                        n += 100;
                        break;
                    }
                    case 14: {
                        if (++n2 > 3) break;
                        n += 100;
                    }
                }
                switch (this.T[this.B[n3][by]]) {
                    case 30: {
                        n += 55;
                        break;
                    }
                    case 31: {
                        n += 100;
                    }
                }
                by = (byte)(by + 1);
            }
            n3 = (byte)(n3 + 1);
        }
        this.cJ += n * 100 / 800;
    }

    public final void k() {
        this.a();
        this.b();
        this.c();
        this.d();
        this.e();
        this.f();
        this.g();
        this.h();
        this.i();
        this.j();
        this.cK = this.cJ / 10;
    }

    public final String a(int n) {
        return this.cP[n][this.cO[n]];
    }

    public final byte a(short s) {
        byte by = 15;
        while (by > 0) {
            if (by * by <= s) {
                return by;
            }
            by = (byte)(by - 1);
        }
        return 0;
    }

    public final boolean a(short s, short s2) {
        int n = this.O[s] + 20 - this.O[s2];
        if (n < 0) {
            return false;
        }
        n = this.a((short)((byte)n)) * 10;
        return this.s() % 100 <= n;
    }

    public final boolean l() {
        if (this.a(this.az, this.aA)) {
            this.ca = (byte)20;
            return true;
        }
        return false;
    }

    public final boolean m() {
        int n = this.N[this.aA] + this.M[this.aA] - this.N[this.az] - this.M[this.az] + 30;
        n = this.a((short)n) * 10;
        return this.s() % 51 <= n;
    }

    public final boolean a(byte by) {
        this.bL = (byte)(this.bL - this.b(by));
        switch (by) {
            case 0: {
                return this.l();
            }
            case 1: {
                return this.m();
            }
            case 2: {
                this.ca = (byte)51;
                return true;
            }
            case 3: {
                this.ca = (byte)68;
                return true;
            }
            case 4: {
                this.ca = (byte)83;
                return true;
            }
            case 5: {
                return true;
            }
        }
        return false;
    }

    public final byte b(byte by) {
        switch (by) {
            case 0: {
                return 3;
            }
            case 1: {
                return 5;
            }
            case 2: {
                return 7;
            }
            case 3: {
                return 8;
            }
            case 4: {
                return 10;
            }
            case 5: {
                return 12;
            }
        }
        return 100;
    }

    public final void a(byte by, byte by2, boolean bl) {
        if (bl) {
            byte[] byArray = this.bI[by2];
            byte by3 = by;
            byArray[by3] = (byte)(byArray[by3] | 0x80);
            return;
        }
        byte[] byArray = this.bI[by2];
        byte by4 = by;
        byArray[by4] = (byte)(byArray[by4] | 0x40);
    }

    public final int a(boolean bl, byte by, byte by2, int n, byte by3) {
        if (bl) {
            byte by4 = by;
            while (by4 < by2) {
                this.bU[0][by4] = 3;
                this.bR[0][by4] = by3;
                this.bW[0][by4] = (byte)(n / (this.bG - by4));
                n -= this.bW[0][by4];
                this.d(true, by4);
                this.bY[0][by4] = true;
                by4 = (byte)(by4 + 1);
            }
        } else {
            byte by5 = by;
            while (by5 < by2) {
                this.bU[1][by5] = 2;
                this.bR[1][by5] = by3;
                this.bW[1][by5] = (byte)(n / (this.bH - by5));
                n -= this.bW[1][by5];
                this.d(false, by5);
                this.bY[1][by5] = true;
                by5 = (byte)(by5 + 1);
            }
        }
        return n;
    }

    public final void a(byte by, int n, boolean bl, byte by2, byte by3) {
        if (by < by2) {
            this.a(bl, (byte)1, by, n, (byte)1);
            return;
        }
        if (by < by3) {
            n = this.a(bl, (byte)1, by2, n, (byte)1);
            this.a(bl, by2, by, n, (byte)2);
            return;
        }
        if (by <= 11) {
            n = this.a(bl, (byte)1, by2, n, (byte)1);
            n = this.a(bl, by2, by3, n, (byte)2);
            this.a(bl, by3, by, n, (byte)3);
        }
    }

    public final void a(boolean bl) {
        short s;
        short s2 = 0;
        byte by = 0;
        if (bl) {
            s2 = this.K[this.az];
            by = this.bG;
            s = this.az;
        } else {
            s2 = this.K[this.aA];
            by = this.bH;
            s = this.aA;
        }
        switch (this.I[s]) {
            case 1: {
                this.a(by, (int)s2, bl, (byte)3, (byte)6);
                return;
            }
            case 2: {
                this.a(by, (int)s2, bl, (byte)3, (byte)7);
                return;
            }
            case 3: {
                this.a(by, (int)s2, bl, (byte)4, (byte)7);
                return;
            }
            case 4: {
                this.a(by, (int)s2, bl, (byte)5, (byte)8);
                return;
            }
            case 5: {
                this.a(by, (int)s2, bl, (byte)6, (byte)9);
                return;
            }
            case 6: {
                this.a(by, (int)s2, bl, (byte)6, (byte)10);
                return;
            }
            case 7: {
                this.a(by, (int)s2, bl, (byte)7, (byte)10);
                return;
            }
            case 8: {
                this.a(by, (int)s2, bl, (byte)7, (byte)12);
            }
        }
    }

    public final byte b(boolean bl) {
        byte by = 0;
        if (bl) {
            byte by2 = 1;
            while (by2 < this.bG) {
                if (this.bR[0][by2] == 1) {
                    this.bR[0][by2] = 2;
                }
                if (this.bR[0][by2] == 1 || this.bR[0][by2] == 2) {
                    by = (byte)(by + 1);
                }
                by2 = (byte)(by2 + 1);
            }
        } else {
            byte by3 = 1;
            while (by3 < this.bH) {
                if (this.bR[1][by3] == 1) {
                    this.bR[1][by3] = 2;
                }
                if (this.bR[1][by3] == 1 || this.bR[1][by3] == 2) {
                    by = (byte)(by + 1);
                }
                by3 = (byte)(by3 + 1);
            }
        }
        return by;
    }

    public final void a(boolean bl, byte by) {
        if (bl) {
            byte by2 = 1;
            while (by2 <= by) {
                this.bP[0][by2] = (byte)(8 - (by2 - 1) / 2);
                this.bQ[0][by2] = (byte)((by2 - 1) % 2 * 6);
                this.a(this.bP[0][by2], this.bQ[0][by2], false);
                by2 = (byte)(by2 + 1);
            }
            return;
        }
        byte by3 = 1;
        while (by3 <= by) {
            this.bP[1][by3] = (byte)(7 + (by3 - 1) / 2);
            this.bQ[1][by3] = (byte)((by3 - 1) % 2 * 6);
            this.a(this.bP[1][by3], this.bQ[1][by3], true);
            by3 = (byte)(by3 + 1);
        }
    }

    public final void b(boolean bl, byte by) {
        if (bl) {
            byte by2 = 1;
            while (by2 <= by) {
                this.bP[0][this.bG - by2] = 2;
                this.bQ[0][this.bG - by2] = by2;
                this.a(this.bP[0][this.bG - by2], this.bQ[0][this.bG - by2], false);
                by2 = (byte)(by2 + 1);
            }
            return;
        }
        byte by3 = 1;
        while (by3 <= by) {
            this.bP[1][this.bH - by3] = 13;
            this.bQ[1][this.bH - by3] = by3;
            this.a(this.bP[1][this.bH - by3], this.bQ[1][this.bH - by3], true);
            by3 = (byte)(by3 + 1);
        }
    }

    public final void c(boolean bl, byte by) {
        byte by2 = bl ? this.bG : this.bH;
        if (by > 6) {
            this.a(bl, (byte)6);
            this.b(bl, (byte)(by2 - 7));
            return;
        }
        this.a(bl, by);
        this.b(bl, (byte)(by2 - by - 1));
    }

    public final void a(byte by, byte by2) {
        switch (by2) {
            case 0: {
                boolean bl = false;
                boolean bl2 = false;
                byte by3 = 1;
                while (by3 < by) {
                    int n;
                    int n2;
                    byte by4 = (byte)((by3 - 1) / 3);
                    byte by5 = (byte)((by3 - 1) % 3);
                    if (by4 == 0) {
                        n2 = 3;
                        n = 6 - by5;
                    } else if (by4 == 1) {
                        n2 = 2;
                        n = (byte)(4 - by5);
                    } else if (by4 == 2) {
                        n2 = 1;
                        n = (byte)(2 - by5);
                    } else {
                        n2 = 1;
                        n = 6;
                    }
                    this.bP[0][by3] = (byte)(n2 + 1);
                    this.bQ[0][by3] = n;
                    this.a(this.bP[0][by3], this.bQ[0][by3], false);
                    by3 = (byte)(by3 + 1);
                }
                return;
            }
            case 1: {
                boolean bl = false;
                boolean bl3 = false;
                byte by6 = 0;
                byte by7 = 0;
                byte by8 = 1;
                while (by8 < by) {
                    by6 = (byte)(3 - (by8 - 1) / 4);
                    by7 = by8 % 2 == 1 ? (byte)(6 - (by8 + 1) / 4) : (byte)(by8 / 4);
                    this.bP[0][by8] = (byte)(by6 + 1);
                    this.bQ[0][by8] = by7;
                    this.a(this.bP[0][by8], this.bQ[0][by8], false);
                    by8 = (byte)(by8 + 1);
                }
                return;
            }
            case 2: {
                boolean bl = false;
                boolean bl4 = false;
                byte by9 = 0;
                byte by10 = 1;
                while (by10 < by) {
                    int n;
                    if (by10 > 7) {
                        by9 = (byte)(by10 - 7);
                        n = -2;
                    } else {
                        by9 = by10;
                        n = 0;
                    }
                    n = (byte)(n + (byte)(4 - by9 / 2));
                    byte by11 = by9 % 2 == 1 ? (byte)(3 - by9 / 2) : (byte)(3 + by9 / 2);
                    this.bP[0][by10] = (byte)(n + 1);
                    this.bQ[0][by10] = by11;
                    this.a(this.bP[0][by10], this.bQ[0][by10], false);
                    by10 = (byte)(by10 + 1);
                }
                return;
            }
            case 3: {
                boolean bl = false;
                boolean bl5 = false;
                int n = 0;
                int n3 = 0;
                byte by12 = 0;
                byte by13 = 1;
                while (by13 < by) {
                    if (by13 > 5) {
                        by12 = (byte)(by13 - 5);
                        n = -2;
                    } else {
                        by12 = by13;
                        n = 0;
                    }
                    if (by12 > 3) {
                        by12 = (byte)(by12 - 3);
                        n = (byte)(n - 1);
                        n3 = -1;
                    } else {
                        n3 = 0;
                    }
                    n = (byte)(n + 4);
                    n3 = by12 == 1 ? (int)((byte)(n3 + 3)) : (by12 == 2 ? (int)((byte)(n3 + 5)) : (int)((byte)(n3 + 1)));
                    this.bP[0][by13] = (byte)(n + 1);
                    this.bQ[0][by13] = n3;
                    this.a(this.bP[0][by13], this.bQ[0][by13], false);
                    by13 = (byte)(by13 + 1);
                }
                break;
            }
        }
    }

    public final void b(byte by, byte by2) {
        switch (by2) {
            case 0: {
                boolean bl = false;
                boolean bl2 = false;
                byte by3 = 1;
                while (by3 < by) {
                    int n;
                    int n2;
                    byte by4 = (byte)((by3 - 1) / 3);
                    byte by5 = (byte)((by3 - 1) % 3);
                    if (by4 == 0) {
                        n2 = 12;
                        n = 6 - by5;
                    } else if (by4 == 1) {
                        n2 = 13;
                        n = (byte)(4 - by5);
                    } else if (by4 == 2) {
                        n2 = 14;
                        n = (byte)(2 - by5);
                    } else {
                        n2 = 14;
                        n = 6;
                    }
                    this.bP[1][by3] = (byte)(n2 - 1);
                    this.bQ[1][by3] = n;
                    this.a(this.bP[1][by3], this.bQ[1][by3], true);
                    by3 = (byte)(by3 + 1);
                }
                return;
            }
            case 1: {
                boolean bl = false;
                boolean bl3 = false;
                byte by6 = 0;
                byte by7 = 0;
                byte by8 = 1;
                while (by8 < by) {
                    by6 = (byte)(12 + (by8 - 1) / 4);
                    by7 = by8 % 2 == 1 ? (byte)(6 - (by8 + 1) / 4) : (byte)(by8 / 4);
                    this.bP[1][by8] = (byte)(by6 - 1);
                    this.bQ[1][by8] = by7;
                    this.a(this.bP[1][by8], this.bQ[1][by8], true);
                    by8 = (byte)(by8 + 1);
                }
                return;
            }
            case 2: {
                boolean bl = false;
                boolean bl4 = false;
                byte by9 = 0;
                byte by10 = 1;
                while (by10 < by) {
                    int n;
                    if (by10 > 7) {
                        by9 = (byte)(by10 - 7);
                        n = 2;
                    } else {
                        by9 = by10;
                        n = 0;
                    }
                    n = (byte)(n + (byte)(11 + by9 / 2));
                    byte by11 = by9 % 2 == 1 ? (byte)(3 - by9 / 2) : (byte)(3 + by9 / 2);
                    this.bP[1][by10] = (byte)(n - 1);
                    this.bQ[1][by10] = by11;
                    this.a(this.bP[1][by10], this.bQ[1][by10], true);
                    by10 = (byte)(by10 + 1);
                }
                return;
            }
            case 3: {
                boolean bl = false;
                boolean bl5 = false;
                int n = 0;
                int n3 = 0;
                byte by12 = 0;
                byte by13 = 1;
                while (by13 < by) {
                    if (by13 > 5) {
                        by12 = (byte)(by13 - 5);
                        n = 2;
                    } else {
                        by12 = by13;
                        n = 0;
                    }
                    if (by12 > 3) {
                        by12 = (byte)(by12 - 3);
                        n = (byte)(n + 1);
                        n3 = -1;
                    } else {
                        n3 = 0;
                    }
                    n = (byte)(n + 11);
                    n3 = by12 == 1 ? (int)((byte)(n3 + 3)) : (by12 == 2 ? (int)((byte)(n3 + 5)) : (int)((byte)(n3 + 1)));
                    this.bP[1][by13] = (byte)(n - 1);
                    this.bQ[1][by13] = n3;
                    this.a(this.bP[1][by13], this.bQ[1][by13], true);
                    by13 = (byte)(by13 + 1);
                }
                break;
            }
        }
    }

    public final void c(byte by) {
        this.bR = new byte[2][];
        this.bP = new byte[2][];
        this.bQ = new byte[2][];
        this.bU = new byte[2][];
        this.bW = new byte[2][];
        this.bX = new boolean[2][];
        this.bY = new boolean[2][];
        this.bS = new short[2][];
        this.bT = new short[2][];
        this.bV = new byte[2][];
        this.bR[0] = new byte[this.bG];
        this.bP[0] = new byte[this.bG];
        this.bQ[0] = new byte[this.bG];
        this.bU[0] = new byte[this.bG];
        this.bW[0] = new byte[this.bG];
        this.bX[0] = new boolean[this.bG];
        this.bY[0] = new boolean[this.bG];
        this.bS[0] = new short[this.bG];
        this.bT[0] = new short[this.bG];
        this.bV[0] = new byte[this.bG];
        this.bR[1] = new byte[this.bH];
        this.bP[1] = new byte[this.bH];
        this.bQ[1] = new byte[this.bH];
        this.bU[1] = new byte[this.bH];
        this.bW[1] = new byte[this.bH];
        this.bX[1] = new boolean[this.bH];
        this.bY[1] = new boolean[this.bH];
        this.bS[1] = new short[this.bH];
        this.bT[1] = new short[this.bH];
        this.bV[1] = new byte[this.bH];
        this.bR[0][0] = 0;
        this.bP[0][0] = 1;
        this.bQ[0][0] = 3;
        this.bU[0][0] = 3;
        this.bW[0][0] = 100;
        this.d(true, (byte)0);
        this.a(this.bP[0][0], this.bQ[0][0], false);
        this.a(true);
        if (!this.ci && this.cj) {
            this.ck = this.b(true);
            this.c(true, this.ck);
        } else if (this.ci || !this.cj) {
            this.a(this.bG, by);
        }
        this.bR[1][0] = 0;
        this.bP[1][0] = (byte)(this.bJ - 2);
        this.bQ[1][0] = 3;
        this.bU[1][0] = 2;
        this.bW[1][0] = 100;
        this.d(false, (byte)0);
        this.a(this.bP[1][0], this.bQ[1][0], true);
        this.a(false);
        if (this.ci && this.cj) {
            this.ck = this.b(false);
            this.c(false, this.ck);
            return;
        }
        this.b(this.bH, (byte)(this.s() % 4));
    }

    public final void n() {
        byte by = 1;
        while (by < 4) {
            this.cd[by] = 0;
            this.ce[by] = 0;
            by = (byte)(by + 1);
        }
        this.cd[0] = this.M[this.az];
        by = 1;
        while (by < this.bG) {
            byte by2 = this.bR[0][by];
            this.cd[by2] = (short)(this.cd[by2] + this.bW[0][by]);
            by = (byte)(by + 1);
        }
        this.ce[0] = this.M[this.aA];
        by = 1;
        while (by < this.bH) {
            byte by3 = this.bR[1][by];
            this.ce[by3] = (short)(this.ce[by3] + this.bW[1][by]);
            by = (byte)(by + 1);
        }
    }

    public final void d(boolean bl, byte by) {
        short s;
        int n;
        if (bl) {
            n = 0;
            s = this.az;
        } else {
            n = 1;
            s = this.aA;
        }
        this.bX[n][by] = false;
        this.bY[n][by] = true;
        if (this.bR[n][by] == 0) {
            this.bS[n][by] = (short)(this.N[s] + this.N[s] * this.ag[this.S[s]] / 100);
        } else if (this.bR[n][by] == 1) {
            this.bS[n][by] = this.N[s];
        } else if (this.bR[n][by] == 2 || this.bR[n][by] == 3) {
            this.bS[n][by] = (short)(this.N[s] * 3 / 5);
        }
        if (this.bS[n][by] == 0) {
            this.bS[n][by] = 1;
        }
        if (this.aR == 0) {
            this.bT[n][by] = (short)(this.N[s] + this.N[s] * this.ag[this.T[s]] / 100);
            return;
        }
        this.bT[n][by] = this.bS[n][by];
    }

    public final void o() {
        this.bL = (byte)(this.O[this.az] * 13 / 100);
    }

    public final void c(byte by, byte by2) {
        byte by3 = (byte)(this.bI[this.bQ[by][by2]][this.bP[by][by2]] & 0xC0);
        byte[] byArray = this.bI[this.bQ[by][by2]];
        byte by4 = this.bP[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bQ[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] - 1);
        this.bU[by][by2] = 0;
        byte[] byArray3 = this.bI[this.bQ[by][by2]];
        byte by6 = this.bP[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void d(byte by, byte by2) {
        byte by3 = (byte)(this.bI[this.bQ[by][by2]][this.bP[by][by2]] & 0xC0);
        byte[] byArray = this.bI[this.bQ[by][by2]];
        byte by4 = this.bP[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bQ[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] + 1);
        this.bU[by][by2] = 1;
        byte[] byArray3 = this.bI[this.bQ[by][by2]];
        byte by6 = this.bP[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void e(byte by, byte by2) {
        byte by3 = (byte)(this.bI[this.bQ[by][by2]][this.bP[by][by2]] & 0xC0);
        byte[] byArray = this.bI[this.bQ[by][by2]];
        byte by4 = this.bP[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bP[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] - 1);
        this.bU[by][by2] = 2;
        byte[] byArray3 = this.bI[this.bQ[by][by2]];
        byte by6 = this.bP[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void f(byte by, byte by2) {
        byte by3 = (byte)(this.bI[this.bQ[by][by2]][this.bP[by][by2]] & 0xC0);
        byte[] byArray = this.bI[this.bQ[by][by2]];
        byte by4 = this.bP[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bP[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] + 1);
        this.bU[by][by2] = 3;
        byte[] byArray3 = this.bI[this.bQ[by][by2]];
        byte by6 = this.bP[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public a() {
        byte[] byArray = new byte[8];
        byArray[1] = 5;
        byArray[2] = 11;
        byArray[3] = 3;
        byArray[4] = 5;
        byArray[5] = 6;
        byArray[6] = 1;
        byArray[7] = 5;
        this.f = byArray;
        short[][] sArrayArray = new short[8][];
        sArrayArray[1] = new short[]{161, 5, 6, 181, 200};
        sArrayArray[2] = new short[]{211, 225, 215, 213, 217, 56, 58, 204, 210, 63, 57};
        sArrayArray[3] = new short[]{37, 8, 34};
        sArrayArray[4] = new short[]{191, 138, 201, 155, 208};
        sArrayArray[5] = new short[]{26, 25, 18, 19, 28, 21};
        sArrayArray[6] = new short[]{40};
        sArrayArray[7] = new short[]{140, 2, 172, 190, 160};
        this.g = sArrayArray;
        this.h = new byte[37];
        this.i = new String[37];
        this.j = new short[37];
        this.k = new short[37];
        this.l = new byte[37];
        this.m = new short[37];
        this.n = new short[37];
        this.o = new short[37];
        this.p = new short[37];
        this.q = new int[37];
        this.r = new byte[37];
        this.s = new byte[37];
        this.t = new byte[37];
        this.u = new byte[37];
        this.v = new short[37];
        this.w = new short[37];
        this.x = new byte[37];
        this.y = new byte[37];
        this.z = new byte[37];
        this.A = new byte[37];
        this.B = new short[37][10];
        this.C = new short[37][];
        this.D = new short[37][];
        byte[][] byArrayArray = new byte[37][];
        byArrayArray[1] = new byte[]{3, 5};
        byArrayArray[2] = new byte[]{4};
        byArrayArray[3] = new byte[]{1};
        byArrayArray[4] = new byte[]{2, 8, 15};
        byArrayArray[5] = new byte[]{1, 9};
        byArrayArray[6] = new byte[]{8, 9, 12};
        byArrayArray[7] = new byte[]{11};
        byArrayArray[8] = new byte[]{4, 6, 12};
        byArrayArray[9] = new byte[]{5, 6, 10, 14};
        byArrayArray[10] = new byte[]{9, 13};
        byArrayArray[11] = new byte[]{7, 15, 16, 18};
        byArrayArray[12] = new byte[]{6, 8, 14, 15};
        byArrayArray[13] = new byte[]{10, 17, 19, 20};
        byArrayArray[14] = new byte[]{9, 12, 15, 19, 23};
        byArrayArray[15] = new byte[]{4, 11, 12, 14, 21, 18};
        byArrayArray[16] = new byte[]{11};
        byArrayArray[17] = new byte[]{13, 22};
        byArrayArray[18] = new byte[]{11, 15, 21, 24};
        byArrayArray[19] = new byte[]{13, 14, 20, 23, 25};
        byArrayArray[20] = new byte[]{13, 19, 22};
        byArrayArray[21] = new byte[]{15, 18, 23};
        byArrayArray[22] = new byte[]{17, 20, 32, 28};
        byArrayArray[23] = new byte[]{14, 19, 21, 31, 34};
        byArrayArray[24] = new byte[]{18, 27};
        byArrayArray[25] = new byte[]{19, 30, 32, 34};
        byArrayArray[26] = new byte[]{27, 31};
        byArrayArray[27] = new byte[]{24, 26};
        byArrayArray[28] = new byte[]{22, 29};
        byArrayArray[29] = new byte[]{28};
        byArrayArray[30] = new byte[]{25, 32, 34};
        byArrayArray[31] = new byte[]{23, 26, 34, 35};
        byArrayArray[32] = new byte[]{22, 25, 30};
        byArrayArray[33] = new byte[]{35, 36};
        byArrayArray[34] = new byte[]{23, 25, 30, 31};
        byArrayArray[35] = new byte[]{31, 33};
        byArrayArray[36] = new byte[]{33};
        this.E = byArrayArray;
        byte[] byArray2 = new byte[37];
        byArray2[1] = 2;
        byArray2[2] = 1;
        byArray2[3] = 1;
        byArray2[4] = 3;
        byArray2[5] = 2;
        byArray2[6] = 3;
        byArray2[7] = 1;
        byArray2[8] = 3;
        byArray2[9] = 4;
        byArray2[10] = 2;
        byArray2[11] = 4;
        byArray2[12] = 4;
        byArray2[13] = 4;
        byArray2[14] = 5;
        byArray2[15] = 6;
        byArray2[16] = 1;
        byArray2[17] = 2;
        byArray2[18] = 4;
        byArray2[19] = 5;
        byArray2[20] = 3;
        byArray2[21] = 3;
        byArray2[22] = 4;
        byArray2[23] = 5;
        byArray2[24] = 2;
        byArray2[25] = 4;
        byArray2[26] = 2;
        byArray2[27] = 2;
        byArray2[28] = 2;
        byArray2[29] = 1;
        byArray2[30] = 3;
        byArray2[31] = 4;
        byArray2[32] = 3;
        byArray2[33] = 2;
        byArray2[34] = 4;
        byArray2[35] = 2;
        byArray2[36] = 1;
        this.F = byArray2;
        this.G = new short[232];
        this.H = new String[232];
        this.I = new byte[232];
        this.J = new byte[232];
        this.K = new short[232];
        this.L = new byte[232];
        this.M = new byte[232];
        this.N = new byte[232];
        this.O = new byte[232];
        this.P = new byte[232];
        this.Q = new byte[232];
        this.R = new short[232];
        this.S = new byte[232];
        this.T = new byte[232];
        this.U = new short[232];
        this.V = new byte[232];
        this.W = new boolean[232];
        this.X = 0;
        this.Y = new byte[7];
        this.Z = new byte[7];
        this.aa = 1;
        this.ab = new short[]{192, 192, 194, 195, 196, 197, 197, 198, 199, 199, 200, 201, 202, 203, 203, 203, 204, 204, 205, 205, 208, 208, 209, 210, 210, 210};
        this.ac = new short[]{138, 140, 160, 172, 179, 190, 191, 194, 200, 201, 204, 210, 211, 213, 213, 214, 215, 216, 217, 218, 219, 220, 222, 225, 226, 227};
        this.ad = new short[]{69, 1, 1, 1, 57, 1, 69, 5, 4, 69, 61, 61, 61, 6, 61, 63, 69, 5, 61, 5, 112, 63, 112, 211, 161, 164};
        this.ae = new String[]{"\u77ed\u5251", "\u98de\u5251", "\u957f\u5251", "\u7834\u65a9\u5251", "\u5927\u5251", "\u4e03\u661f\u5251", "\u9752\u91ed\u5251", "\u501a\u5929\u5251", "\u96cd\u5200", "\u7709\u5c16\u5200", "\u4e09\u5c16\u5200", "\u94a9\u9570\u5200", "\u5043\u6708\u5200", "\u51e4\u5634\u5200", "\u9f99\u7259\u5200", "\u5173\u738b\u5200", "\u77db", "\u53cc\u621f", "\u5355\u94a9\u67aa", "\u6218\u65a7", "\u53cc\u94a9\u67aa", "\u65b9\u5929\u5316\u621f", "\u72fc\u7259\u67aa", "\u86c7\u77db", "\u7eb6\u5dfe", "\u7b3c\u624b", "\u515c", "\u786c\u76ae", "\u9e64\u6c05", "\u7532\u80c4", "\u5723\u8005\u4e4b\u8863", "\u9f99\u9cde\u5b9d\u94e0"};
        short[] sArray = new short[32];
        sArray[0] = 50;
        sArray[1] = 70;
        sArray[2] = 120;
        sArray[3] = 180;
        sArray[4] = 250;
        sArray[8] = 60;
        sArray[9] = 100;
        sArray[10] = 150;
        sArray[11] = 200;
        sArray[12] = 240;
        sArray[13] = 260;
        sArray[15] = 500;
        sArray[16] = 80;
        sArray[17] = 100;
        sArray[18] = 120;
        sArray[19] = 200;
        sArray[20] = 250;
        sArray[21] = 300;
        sArray[22] = 500;
        sArray[23] = 500;
        sArray[24] = 50;
        sArray[25] = 80;
        sArray[26] = 100;
        sArray[27] = 150;
        sArray[28] = 200;
        sArray[29] = 250;
        sArray[30] = 500;
        this.af = sArray;
        this.ag = new byte[]{5, 7, 12, 18, 25, 50, 50, 50, 6, 10, 15, 20, 24, 26, 45, 50, 8, 10, 12, 20, 25, 30, 50, 50, 5, 8, 10, 15, 20, 25, 50, 60};
        byte[][] byArrayArray2 = new byte[6][];
        byte[] byArray3 = new byte[4];
        byArray3[1] = 1;
        byArray3[2] = 2;
        byArray3[3] = 3;
        byArrayArray2[0] = byArray3;
        byArrayArray2[1] = new byte[]{1, 2, 3, 4};
        byArrayArray2[2] = new byte[]{1, 2, 3, 4};
        byte[] byArray4 = new byte[4];
        byArray4[1] = 1;
        byArray4[2] = 2;
        byArray4[3] = 3;
        byArrayArray2[3] = byArray4;
        byte[] byArray5 = new byte[4];
        byArray5[1] = 1;
        byArray5[2] = 2;
        byArray5[3] = 3;
        byArrayArray2[4] = byArray5;
        byte[] byArray6 = new byte[4];
        byArray6[1] = 1;
        byArray6[2] = 2;
        byArray6[3] = 3;
        byArrayArray2[5] = byArray6;
        this.ah = byArrayArray2;
        this.ai = new byte[][]{{8, 9, 10, 11}, {10, 11, 12, 13}, {10, 11, 12, 13}, {8, 9, 10, 11}, {8, 9, 10, 15}, {8, 9, 10, 11}};
        this.aj = new byte[][]{{16, 17, 18, 19}, {18, 19, 20, 21}, {18, 19, 20, 21}, {16, 17, 18, 23}, {17, 18, 19, 20}, {16, 17, 18, 22}};
        this.ak = new byte[][]{{24, 25, 26, 27}, {26, 27, 28, 29}, {26, 27, 28, 30}, {24, 25, 26, 27}, {24, 25, 26, 27}, {24, 25, 26, 27}};
        this.al = new short[3];
        this.am = new byte[3];
        this.an = new short[3];
        this.ao = new String[3];
        this.ap = new byte[3];
        this.ar = new Random();
        this.as = 0;
        this.aB = new short[10];
        this.aD = new byte[8];
        this.aE = 1;
        this.aF = (short)189;
        this.aG = 0;
        this.aH = 0;
        this.aJ = true;
        this.aK = true;
        this.aN = true;
        this.aO = new short[10];
        this.aP = 0;
        this.aS = new byte[8];
        this.aT = new byte[37];
        this.aW = true;
        this.bk = new byte[19][32];
        this.bl = (byte)32;
        this.bm = (byte)19;
        this.bo = new byte[10];
        this.bp = new byte[10];
        this.bq = new byte[10];
        this.br = new byte[10];
        this.bs = new byte[10];
        this.bt = new byte[10];
        this.bE = 0;
        this.bI = new byte[][]{new byte[16], new byte[16], new byte[16], new byte[16], new byte[16], new byte[16], new byte[16]};
        this.bJ = (byte)16;
        this.bK = (byte)7;
        byte[][] byArrayArray3 = new byte[24][];
        byte[] byArray7 = new byte[3];
        byArray7[0] = 2;
        byArray7[1] = 100;
        byArrayArray3[0] = byArray7;
        byte[] byArray8 = new byte[7];
        byArray8[0] = 2;
        byArray8[1] = 3;
        byArray8[2] = 3;
        byArray8[3] = 99;
        byArray8[4] = 4;
        byArray8[5] = 1;
        byArrayArray3[1] = byArray8;
        byArrayArray3[2] = new byte[]{5, 1};
        byte[] byArray9 = new byte[10];
        byArray9[0] = 2;
        byArray9[1] = 3;
        byArray9[2] = 6;
        byArray9[3] = 3;
        byArray9[4] = 83;
        byArray9[5] = 3;
        byArray9[6] = 84;
        byArray9[7] = 85;
        byArray9[9] = 1;
        byArrayArray3[3] = byArray9;
        byArrayArray3[4] = new byte[]{2, 8};
        byte[] byArray10 = new byte[5];
        byArray10[0] = 9;
        byArray10[1] = 3;
        byArray10[2] = 91;
        byArray10[3] = 1;
        byArrayArray3[5] = byArray10;
        byArrayArray3[6] = new byte[]{3, 10, 1};
        byte[] byArray11 = new byte[5];
        byArray11[0] = 3;
        byArray11[1] = 3;
        byArray11[2] = 98;
        byArray11[3] = 1;
        byArrayArray3[7] = byArray11;
        byte[] byArray12 = new byte[4];
        byArray12[0] = 3;
        byArray12[1] = 11;
        byArray12[2] = 1;
        byArrayArray3[8] = byArray12;
        byte[] byArray13 = new byte[3];
        byArray13[0] = 3;
        byArray13[1] = 97;
        byArrayArray3[9] = byArray13;
        byte[] byArray14 = new byte[5];
        byArray14[0] = 3;
        byArray14[1] = 12;
        byArray14[2] = 96;
        byArray14[3] = 1;
        byArrayArray3[10] = byArray14;
        byte[] byArray15 = new byte[5];
        byArray15[0] = 3;
        byArray15[1] = 12;
        byArray15[2] = 95;
        byArray15[3] = 1;
        byArrayArray3[11] = byArray15;
        byte[] byArray16 = new byte[4];
        byArray16[0] = 2;
        byArray16[1] = 16;
        byArray16[2] = 1;
        byArrayArray3[12] = byArray16;
        byte[] byArray17 = new byte[5];
        byArray17[0] = 3;
        byArray17[1] = 13;
        byArray17[2] = 94;
        byArray17[3] = 1;
        byArrayArray3[13] = byArray17;
        byte[] byArray18 = new byte[5];
        byArray18[0] = 3;
        byArray18[1] = 12;
        byArray18[2] = 93;
        byArray18[3] = 1;
        byArrayArray3[14] = byArray18;
        byte[] byArray19 = new byte[3];
        byArray19[0] = 14;
        byArray19[1] = 1;
        byArrayArray3[15] = byArray19;
        byte[] byArray20 = new byte[4];
        byArray20[0] = 3;
        byArray20[1] = 15;
        byArray20[2] = 1;
        byArrayArray3[16] = byArray20;
        byte[] byArray21 = new byte[4];
        byArray21[0] = 3;
        byArray21[1] = 89;
        byArray21[2] = 1;
        byArrayArray3[18] = byArray21;
        byte[] byArray22 = new byte[4];
        byArray22[0] = 3;
        byArray22[1] = 88;
        byArray22[2] = 1;
        byArrayArray3[19] = byArray22;
        byArrayArray3[20] = new byte[]{2, 3, 3, 92, 1};
        byte[] byArray23 = new byte[6];
        byArray23[0] = 2;
        byArray23[1] = 3;
        byArray23[2] = 3;
        byArray23[3] = 90;
        byArray23[4] = 1;
        byArrayArray3[21] = byArray23;
        byte[] byArray24 = new byte[3];
        byArray24[0] = 17;
        byArray24[1] = 87;
        byArrayArray3[22] = byArray24;
        byArrayArray3[23] = new byte[]{17, 86};
        this.bO = byArrayArray3;
        this.ca = 0;
        this.cb = 0;
        this.cd = new short[4];
        this.ce = new short[4];
        this.cg = new byte[4];
        this.ch = 0;
        this.cO = new byte[7];
        this.cP = new String[][]{{"\u6781\u5ea6\u8d2b\u7620\u7684\u571f\u5730, \u6839\u672c\u65e0\u6cd5\u751f\u4ea7\u51fa\u7cae\u98df,\u997f\u6b8d\u904d\u91ce,\u767e\u59d3\u82e6\u4e0d\u582a\u8a00", "\u571f\u5730\u8d44\u6e90\u975e\u5e38\u532e\u4e4f,\u7cae\u98df\u6536\u6210\u975e\u5e38\u4f4e\u4e0b,\u5404\u5730\u767e\u59d3\u997f\u6b7b\u73b0\u8c61\u65f6\u6709\u53d1\u751f,\u65e0\u6cd5\u7ee7\u7eed\u751f\u6d3b\u4e0b\u53bb", "\u519c\u4e1a\u53d1\u5c55\u8f83\u4e3a\u843d\u540e,\u7cae\u98df\u4ea7\u91cf\u4ecd\u65e0\u6cd5\u6ee1\u8db3\u767e\u59d3\u7684\u751f\u6d3b,\u5404\u5730\u7530\u91cc,\u5730\u91cc\u4e00\u7247\u83dc\u8272", "\u968f\u7740\u519c\u4e1a\u5730\u9010\u6b65\u53d1\u5c55,\u7cae\u98df\u4ea7\u91cf\u57fa\u672c\u8fbe\u5230\u4e86\u767e\u59d3\u751f\u6d3b\u5730\u9700\u6c42", "\u968f\u7740\u571f\u5730\u5730\u4e0d\u65ad\u5f00\u53d1,\u7cae\u98df\u4ea7\u91cf\u5df2\u8f83\u4e3a\u5bcc\u88d5,\u767e\u59d3\u5403\u996d\u95ee\u9898\u5df2\u5b8c\u5168\u89e3\u51b3\u4e86", "\u65b0\u7684\u519c\u4e1a\u5de5\u5177\u5730\u4e0d\u65ad\u51fa\u73b0,\u5728\u571f\u5730\u4f5c\u4e1a\u4e2d\u7684\u9010\u6e10\u666e\u53ca,\u5404\u5730\u7cae\u98df\u5df2\u6781\u4e3a\u5145\u88d5", "\u519c\u4e1a\u53d1\u5c55\u7a7a\u524d,\u7cae\u98df\u5e74\u5e74\u4e30\u4ea7,\u4eba\u6c11\u8863\u98df\u65e0\u5fe7"}, {"\u6781\u5ea6\u843d\u540e\u7684\u5546\u4e1a,\u4eba\u4eec\u6700\u57fa\u672c\u7684\u7269\u54c1\u4ea4\u6362\u90fd\u65e0\u6cd5\u8fdb\u884c.\u56fd\u5bb6\u8d2b\u82e6\u4e0d\u582a", "\u5546\u4e1a\u8d44\u6e90\u975e\u5e38\u8d2b\u4e4f,\u4eba\u4eec\u65e0\u6cd5\u5f97\u5230\u6700\u57fa\u672c\u7684\u751f\u6d3b\u7528\u54c1,\u751f\u6d3b\u975e\u5e38\u8270\u8f9b", "\u5546\u4e1a\u53d1\u5c55\u4ecd\u8f83\u4e3a\u843d\u540e,\u5546\u52a1\u6d3b\u52a8\u8f83\u5c11, \u767e\u59d3\u751f\u6d3b\u6c34\u5e73\u975e\u5e38\u4f4e\u4e0b", "\u968f\u7740\u5546\u4e1a\u5730\u9010\u6b65\u53d1\u5c55,\u5546\u52a1\u6d3b\u52a8\u57fa\u672c\u8fbe\u5230\u4e86\u767e\u59d3\u751f\u6d3b\u5730\u9700\u6c42", "\u968f\u7740\u5b98\u5e9c\u7684\u4e0d\u65ad\u91cd\u89c6,\u5546\u4e1a\u53d1\u5c55\u5df2\u8f83\u4e3a\u53d1\u8fbe,\u56fd\u5bb6\u7ecf\u6d4e\u72b6\u51b5\u6709\u4e86\u5f88\u5927\u6539\u5584", "\u4e0e\u5916\u56fd\u7684\u7ecf\u6d4e\u5f80\u6765,\u4f7f\u5f97\u56fd\u5bb6\u5546\u52a1\u53d1\u5c55\u6781\u5ea6\u53d1\u8fbe,\u767e\u59d3\u751f\u6d3b\u5728\u4e86\u4e00\u4e2a\u5bcc\u88d5\u7684\u56fd\u5bb6\u4e2d", "\u56fd\u5bb6\u7684\u7ecf\u6d4e\u53d1\u5c55\u4e3e\u4e16\u7f55\u89c1, \u65b0\u7684\u9886\u57df\u7684\u7ecf\u6d4e\u5f80\u6765\u4e0d\u65ad\u589e\u5f3a\u672c\u56fd\u7684\u7ecf\u6d4e\u5b9e\u529b"}, {"\u761f\u75ab,\u8757\u866b,\u65f1\u6d9d,\u5404\u79cd\u707e\u5bb3\u904d\u5730,\u6c11\u4e0d\u804a\u751f", "\u761f\u75ab\u7b49\u8fd9\u79cd\u707e\u5bb3\u4ecd\u65f6\u6709\u53d1\u751f,\u767e\u59d3\u707e\u96be\u6df1\u91cd", "\u7531\u4e8e\u65b0\u56fd\u5bb6\u5b98\u5e9c\u7684\u4e00\u5b9a\u91cd\u89c6,\u707e\u5bb3\u5728\u4e00\u5b9a\u7a0b\u5ea6\u4e0a\u5f97\u5230\u4e86\u904f\u5236", "\u7ecf\u8fc7\u5b98\u5e9c\u7684\u4e0d\u65ad\u52aa\u529b,\u707e\u5bb3\u9632\u5fa1\u63aa\u65bd\u9010\u6e10\u5b9e\u65bd,\u767e\u59d3\u57fa\u672c\u8fdc\u79bb\u4e86\u707e\u5bb3\u75db\u82e6", "\u5927\u575d\u5efa\u6210,\u5404\u79cd\u6cbb\u7406\u707e\u5bb3\u63aa\u65bd\u4e95\u7136\u6709\u5e8f,\u767e\u59d3\u518d\u65e0\u707e\u5bb3\u4e4b\u5fe7"}, {"\u7ecf\u8fc7\u591a\u5e74\u7684\u6218\u4e89,\u5404\u5730\u4e00\u7247\u8352\u829c,\u5404\u57ce\u5e02\u4eba\u4e01\u7a00\u5c11,\u767e\u91cc\u96be\u89c1\u4e00\u4eba", "\u6218\u540e\u6062\u590d\u4ecd\u6ca1\u6709\u8db3\u591f\u7684\u8fdb\u884c,\u5404\u57ce\u5e02\u52b3\u52a8\u529b\u8d44\u6e90\u4ecd\u7a0d\u663e\u4e0d\u8db3", "\u6218\u4e89\u7684\u785d\u70df\u9010\u6e10\u8fdc\u53bb,\u5404\u57ce\u5e02\u4eba\u53e3\u8d44\u6e90\u5df2\u5b8c\u5168\u6ee1\u8db3\u4e86\u52b3\u52a8\u7684\u9700\u6c42", "\u56fd\u5bb6\u5f00\u59cb\u91cd\u89c6\u4e86\u57ce\u5e02\u7684\u5efa\u8bbe,\u57ce\u5e02\u89c4\u6a21\u53d1\u5c55\u7a33\u5b9a,\u5927\u578b\u57ce\u5e02\u9010\u6b65\u5174\u8d77", "\u7ecf\u8fc7\u56fd\u5bb6\u591a\u65b9\u9762\u52aa\u529b,\u57ce\u5e02\u5411\u90fd\u5e02\u5316\u53d1\u5c55\u5f00\u59cb,\u5404\u57ce\u5e02\u6b23\u6b23\u5411\u8363", "\u5404\u5730\u53d1\u5c55\u5e73\u8861,\u90fd\u5e02\u5316\u53d1\u5c55\u5b8c\u6210,\u56fd\u5bb6\u53d1\u5c55\u7a7a\u524d,\u767e\u59d3\u5b89\u5c45\u4e50\u4e1a"}, {"\u90e8\u4e0b\u91ce\u5fc3\u52c3\u52c3,\u53db\u53d8,\u53cd\u4e71\u4e8b\u4ef6\u5c42\u51fa\u4e0d\u7a77,\u56fd\u5bb6\u9a6c\u4e0a\u5c06\u88ab\u98a0\u8986", "\u5c5e\u4e0b\u4eba\u5fc3\u6d6e\u52a8,\u6d41\u8a00\u56db\u8d77,\u67d0\u4e9b\u6b66\u5c06\u7684\u53db\u4e71\u4f3c\u4e4e\u5df2\u5728\u773c\u524d", "\u5927\u591a\u6b66\u5c06\u5fe0\u4e8e\u541b\u4e3b,\u4f46\u5978\u81e3\u5f53\u9053,\u53db\u53d8\u7684\u79cd\u5b50\u4ecd\u8fdc\u672a\u6d88\u5931", "\u541b\u4e3b\u8d4f\u7f5a\u5206\u660e,\u6069\u5a01\u5e76\u65bd,\u4e0b\u5c5e\u6b66\u5c06\u5bf9\u56fd\u5bb6\u8f83\u4e3a\u5fe0\u8bda", "\u541b\u4e3b\u5584\u4e8e\u5fa1\u4eba\u4e4b\u9053, \u6b66\u5c06\u4eec\u5bf9\u541b\u4e3b, \u56fd\u5bb6\u5fe0\u5fc3\u803f\u803f", "\u4ee5\u6069\u4e49\u8457\u79f0\u7684\u65b0\u541b\u4e3b,\u5f97\u5230\u4e86\u5c5e\u4e0b\u8a93\u6b7b\u6548\u5fe0,\u5404\u6b66\u5c06\u7edd\u65e0\u4e8c\u5fc3", "\u7ecf\u8fc7\u591a\u5e74\u7684\u540c\u751f\u5171\u6b7b,\u81e3\u50da\u4eec\u7528\u9c9c\u8840\u6765\u53d1\u8a93,\u5b50\u5b50\u5b59\u5b59\u8a93\u6b7b\u6548\u5fe0\u541b\u4e3b"}, {"\u65b0\u56fd\u5bb6\u7684\u6240\u4f5c\u6240\u4e3a,\u767e\u59d3\u6df1\u6076\u75db\u75be,\u5404\u5730\u70fd\u70df\u56db\u8d77,\u8d77\u4e49\u904d\u5730,\u65e0\u6cd5\u63a7\u5236", "\u65b0\u56fd\u5bb6\u5e76\u6ca1\u6709\u5b9e\u65bd\u4ec1\u653f,\u767e\u59d3\u5bf9\u65b0\u56fd\u5bb6\u975e\u5e38\u4e0d\u6ee1, \u8d77\u4e49\u7684\u79cd\u5b50\u56db\u5904\u840c\u53d1", "\u65b0\u56fd\u5bb6\u7684\u4ec1\u653f\u601d\u60f3\u9010\u6e10\u4f20\u64ad,\u9010\u6e10\u4f7f\u5f97\u767e\u59d3\u5bf9\u65b0\u7684\u56fd\u5bb6\u57fa\u672c\u4e0a\u8ba4\u53ef\u4e86", "\u541b\u4e3b\u4e00\u5411\u4ee5\u4ec1\u653f\u800c\u95fb\u540d,\u56fd\u5bb6\u5efa\u7acb\u4ee5\u6765,\u5f97\u5230\u4e86\u5404\u5730\u767e\u59d3\u7684\u652f\u6301\u548c\u6b22\u8fce", "\u541b\u4e3b\u8d24\u540d\u8fdc\u64ad,\u7ba1\u7406\u56fd\u5bb6\u653f\u7b56\u5f97\u5f53,\u65bd\u4ee5\u767e\u59d3\u7684\u4ec1\u653f\u6df1\u5165\u4eba\u5fc3", "\u5168\u56fd\u767e\u59d3\u575a\u51b3\u62e5\u62a4\u65b0\u7684\u56fd\u5bb6,\u541b\u4e3b\u5df2\u6210\u4e3a\u5927\u4f17\u7684\u5076\u50cf,\u548c\u7cbe\u795e\u7825\u67f1"}, {"\u591a\u5e74\u6218\u4e89\u9020\u6210\u56fd\u5bb6\u5175\u7532\u6781\u5ea6\u4e0d\u8db3,\u8fdc\u8fdc\u65e0\u6cd5\u62b5\u5fa1\u5916\u654c\u4fb5\u7565,\u968f\u65f6\u6709\u706d\u56fd\u5371\u9669", "\u6218\u540e\u5175\u7532\u4ecd\u7136\u4e0d\u8db3,\u65e0\u6cd5\u62b5\u5fa1\u5308\u5974\u7b49\u5916\u654c\u7684\u5927\u89c4\u6a21\u8fdb\u653b,\u56fd\u5bb6\u5c8c\u5c8c\u53ef\u5371", "\u5308\u5974,\u5916\u65cf\u864e\u89c6\u803d\u803d,\u5168\u90e8\u5175\u4e01\u5df2\u6d3e\u5f80\u524d\u7ebf,\u4e0e\u5f3a\u5927\u7684\u654c\u4eba\u8fbe\u6210\u5fae\u5f31\u7684\u519b\u4e8b\u5e73\u8861", "\u5728\u6218\u4e89\u79ef\u7d2f\u4e86\u8db3\u591f\u519b\u4e8b\u7ecf\u9a8c,\u56fd\u5bb6\u519b\u4e8b\u5b9e\u529b\u7a33\u5b9a,\u5728\u968f\u540e\u7684\u6218\u4e89\u4e2d\u6570\u6b21\u6218\u8d25\u5916\u4fb5\u7565\u8005", "\u6b66\u5668,\u9635\u6cd5\u7684\u66f4\u65b0,\u5c06\u9886\u7684\u57f9\u517b,\u4f7f\u5f97\u519b\u4e8b\u5b9e\u529b\u5f3a\u5927,\u5f81\u670d\u4e86\u8bb8\u591a\u5468\u56f4\u7684\u5c0f\u56fd\u5bb6", "\u519b\u4e8b\u5b9e\u529b\u7a7a\u524d,\u5bf9\u5468\u8fb9\u56fd\u5bb6\u5f62\u6210\u4e86\u5f3a\u5927\u7684\u9707\u6151\u4f5c\u7528.\u5404\u56fd\u7eb7\u7eb7\u9063\u4f7f\u8fdb\u8d21,\u6216\u4ece\u5c5e.\u5f3a\u5927\u7684\u5e1d\u56fd\u5df2\u7ecf\u4ea7\u751f"}};
    }

    public final synchronized void a(b b2) {
        this.aq = b2;
        this.aW = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void p() {
        long l = System.currentTimeMillis();
        while (true) {
            this.aq.l();
            if (this.as > 0) {
                return;
            }
            this.aq.a = 0;
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void q() {
        this.aC();
        this.aq.a((byte)1);
    }

    public final void r() {
        this.aq.a((byte)18);
    }

    public final void d(byte by) {
        if (by == 5) {
            this.aq.a((byte)1);
            return;
        }
        this.aQ = by;
        this.bd();
        this.as = (byte)2;
    }

    public final int s() {
        return Math.abs(this.ar.nextInt());
    }

    public final boolean g(byte by, byte by2) {
        byte by3 = 0;
        while (by3 < this.F[by]) {
            if (this.E[by][by3] == by2) {
                return true;
            }
            by3 = (byte)(by3 + 1);
        }
        return false;
    }

    public final void t() {
        int n = this.s() % 8 + 1;
        int n2 = 0;
        while (n > 0) {
            if ((n -= this.aD[n2]) == 0) break;
            if (++n2 != 8) continue;
            n2 = 0;
        }
        this.aD[n2] = 0;
        this.au = this.a[n2 + 1];
    }

    public final byte e(byte by) {
        int n = this.d[by];
        if (n < 3) {
            n = 3;
        } else if (n > 8) {
            n = 8;
        }
        return (byte)n;
    }

    public final void u() {
        byte by = 0;
        while (by < this.d[this.at]) {
            if (this.j[this.e[this.at][by]] == this.k[this.e[this.at][by]]) {
                this.aw = this.e[this.at][by];
                return;
            }
            by = (byte)(by + 1);
        }
    }

    public final String a(String string) {
        String string2 = null;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(string);
            byte[] byArray = new byte[inputStream.available()];
            inputStream.read(byArray);
            inputStream.close();
            string2 = new String(byArray);
        }
        catch (Exception exception) {}
        return string2;
    }

    public final void a(short s, byte by, String string) {
        this.bk[s][by] = Byte.parseByte(string);
    }

    public final void b(String string) {
        int n = 0;
        int n2 = 0;
        byte by = 0;
        short s = 0;
        String string2 = null;
        try {
            while (n2 < string.length()) {
                if (string.charAt(n2) == ',' || string.charAt(n2) == '\n' || string.charAt(n2) == '\r') {
                    if (n2 != n) {
                        string2 = string.substring(n, n2);
                        this.a(s, by, string2);
                    }
                    n = n2 + 1;
                    if (string.charAt(n2) == '\n') {
                        s = (byte)(s + 1);
                        by = 0;
                    } else {
                        by = (byte)(by + 1);
                    }
                }
                ++n2;
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void v() {
        String string = this.a("/res/map/" + this.aZ + ".map");
        this.b(string);
    }

    public final int f(byte by) {
        int n = by & 0xC0;
        if (n == 64) {
            return 1;
        }
        if (n == 128) {
            return 2;
        }
        return 0;
    }

    public final void w() {
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        int n = 0;
        byte by4 = 0;
        while (by4 < 32) {
            int n2 = 0;
            while (n2 < 19) {
                if (this.bk[n2][by4] == 2) {
                    by3 = by4;
                    n = n2;
                    if (by4 > 15) {
                        this.bu = (byte)-1;
                    } else {
                        this.bw = 0;
                    }
                    if (n2 > 10) {
                        this.bv = (byte)-1;
                        break;
                    }
                    this.bx = 0;
                    break;
                }
                n2 = (byte)(n2 + 1);
            }
            by4 = (byte)(by4 + 1);
        }
        this.bq[0] = by3;
        this.br[0] = n;
        this.bt[0] = 0;
        byte[] byArray = this.bk[n];
        byte by5 = by3;
        byArray[by5] = (byte)(byArray[by5] | 0x80);
        by4 = 1;
        while (by4 < this.bh) {
            do {
                by = (byte)(this.s() % 2 * this.bu + by3);
            } while (this.f(this.bk[by2 = (byte)(this.s() % 5 * this.bv + n)][by]) > 0);
            this.bq[by4] = by;
            this.br[by4] = by2;
            byte[] byArray2 = this.bk[by2];
            byte by6 = by;
            byArray2[by6] = (byte)(byArray2[by6] | 0x80);
            this.bt[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
        by4 = 0;
        while (by4 < this.bg) {
            do {
                by = (byte)((this.s() % 2 + 8) * this.bu + by3);
            } while (this.f(this.bk[by2 = (byte)(this.s() % 5 * this.bv + n)][by]) > 0);
            this.bo[by4] = by;
            this.bp[by4] = by2;
            byte[] byArray3 = this.bk[by2];
            byte by7 = by;
            byArray3[by7] = (byte)(byArray3[by7] | 0x40);
            this.bs[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
    }

    public final void x() {
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        int n = 0;
        byte by4 = 0;
        while (by4 < 31) {
            int n2 = 0;
            while (n2 < 19) {
                if (this.bk[n2][by4] == 2) {
                    by3 = by4;
                    n = n2;
                    if (by4 > 15) {
                        this.bu = (byte)-1;
                        this.bw = 0;
                    }
                    if (n2 <= 10) break;
                    this.bv = (byte)-1;
                    this.bx = 0;
                    break;
                }
                n2 = (byte)(n2 + 1);
            }
            by4 = (byte)(by4 + 1);
        }
        this.bo[0] = by3;
        this.bp[0] = n;
        this.bs[0] = 0;
        byte[] byArray = this.bk[n];
        byte by5 = by3;
        byArray[by5] = (byte)(byArray[by5] | 0x40);
        by4 = 1;
        while (by4 < this.bg) {
            do {
                by = (byte)(this.s() % 2 * this.bu + by3);
            } while (this.f(this.bk[by2 = (byte)(this.s() % 5 * this.bv + n)][by]) > 0);
            this.bo[by4] = by;
            this.bp[by4] = by2;
            byte[] byArray2 = this.bk[by2];
            byte by6 = by;
            byArray2[by6] = (byte)(byArray2[by6] | 0x40);
            this.bs[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
        by4 = 0;
        while (by4 < this.bh) {
            do {
                by = (byte)((this.s() % 2 + 8) * this.bu + by3);
            } while (this.f(this.bk[by2 = (byte)(this.s() % 5 * this.bv + n)][by]) > 0);
            this.bq[by4] = by;
            this.br[by4] = by2;
            byte[] byArray3 = this.bk[by2];
            byte by7 = by;
            byArray3[by7] = (byte)(byArray3[by7] | 0x80);
            this.bt[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
    }

    public final void y() {
        this.v();
        if (this.bn) {
            this.x();
        } else {
            this.w();
        }
        this.aq.a((byte)20);
    }

    public final void z() {
        this.as = 0;
        this.aX = this.b[this.at];
        this.aY = this.j[this.ax];
        this.aZ = this.ax;
        this.be = this.aB;
        this.bg = this.aC;
        byte by = 0;
        while (by < this.bg) {
            this.bs[by] = 0;
            by = (byte)(by + 1);
        }
        this.bf = this.B[this.aZ];
        this.bh = this.s[this.aZ];
        by = 0;
        while (by < this.bh) {
            this.bt[by] = 0;
            by = (byte)(by + 1);
        }
        this.bn = false;
        this.bu = 1;
        this.bv = 1;
        this.bw = 1;
        this.bx = 1;
        this.by = 0;
        this.bj = 0;
        this.bc = this.m[this.aZ];
        this.bd = this.n[this.aZ];
        this.bz = true;
        this.bA = (byte)-1;
        this.bi = this.a(this.be, this.bg, this.bs, this.bi);
        this.y();
        this.aq.b();
        this.aq.a();
        this.aq.j();
    }

    public final int h(byte by, byte by2) {
        if ((by & 0xC0) != 0) {
            return 100;
        }
        by = (byte)(by & 0x1F);
        if (by2 == 1) {
            switch (by) {
                case 0: 
                case 1: 
                case 3: 
                case 4: {
                    return 2;
                }
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: {
                    return 5;
                }
                case 5: {
                    return 5;
                }
                case 6: {
                    return 5;
                }
                case 2: {
                    return 6;
                }
            }
        } else if (by2 == 3) {
            switch (by) {
                case 0: 
                case 1: 
                case 3: 
                case 4: {
                    return 3;
                }
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: {
                    return 3;
                }
                case 5: {
                    return 5;
                }
                case 6: {
                    return 6;
                }
                case 2: {
                    return 6;
                }
            }
        } else if (by2 == 2) {
            switch (by) {
                case 0: 
                case 1: 
                case 3: 
                case 4: {
                    return 3;
                }
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: {
                    return 6;
                }
                case 5: {
                    return 5;
                }
                case 6: {
                    return 3;
                }
                case 2: {
                    return 6;
                }
            }
        }
        return 100;
    }

    public final short a(short[] sArray, byte by, byte[] byArray) {
        short s = 0;
        byte by2 = 0;
        while (by2 < by) {
            if (byArray[by2] == 0 || byArray[by2] > 3) {
                s = (short)(s + this.K[sArray[by2]]);
            }
            by2 = (byte)(by2 + 1);
        }
        return s;
    }

    public final int b(short s) {
        int n = s / 1000;
        switch (n) {
            case 0: {
                return 10;
            }
            case 1: {
                return 16;
            }
            case 2: {
                return 20;
            }
            case 3: {
                return 24;
            }
            case 4: {
                return 28;
            }
            case 5: {
                return 32;
            }
            case 6: {
                return 35;
            }
            case 7: {
                return 37;
            }
            case 8: {
                return 40;
            }
            case 9: 
            case 10: {
                return 42;
            }
        }
        return 0;
    }

    public final byte c(short s) {
        int n = s / 1000;
        switch (n) {
            case 0: {
                return 20;
            }
            case 1: {
                return 26;
            }
            case 2: {
                return 32;
            }
            case 3: {
                return 36;
            }
            case 4: {
                return 42;
            }
            case 5: {
                return 46;
            }
            case 6: {
                return 51;
            }
            case 7: {
                return 53;
            }
            case 8: {
                return 58;
            }
            case 9: 
            case 10: {
                return 60;
            }
        }
        return 0;
    }

    public final byte a(short[] sArray, byte by, byte[] byArray, byte by2) {
        byte by3;
        short s = this.a(sArray, by, byArray);
        byte by4 = (byte)(this.b(s) + by2);
        if (by4 > (by3 = this.c(s))) {
            by4 = by3;
        }
        return by4;
    }

    public final byte d(short s) {
        byte by = this.O[s];
        byte by2 = this.I[s];
        if (by >= 99 && by2 >= 8) {
            return 16;
        }
        if (by >= 98 && by2 >= 7) {
            return 15;
        }
        if (by >= 97 && by2 >= 5) {
            return 14;
        }
        if (by >= 93 && by2 >= 4) {
            return 12;
        }
        if (by >= 85) {
            return 10;
        }
        if (by >= 80) {
            return 8;
        }
        if (by >= 60) {
            return 6;
        }
        if (by >= 40) {
            return 4;
        }
        return 2;
    }

    public final byte g(byte by) {
        switch (by) {
            case 0: 
            case 3: {
                return 6;
            }
            case 1: {
                return 5;
            }
            case 2: {
                return 4;
            }
            case 4: {
                return 7;
            }
            case 5: 
            case 6: 
            case 7: 
            case 13: {
                return 8;
            }
            case 9: {
                return 9;
            }
            case 8: 
            case 10: 
            case 11: 
            case 12: 
            case 15: {
                return 10;
            }
            case 14: {
                return 12;
            }
        }
        return 100;
    }

    public final boolean a(byte by, byte by2, byte by3, byte by4, int n) {
        return Math.abs(by - by3) <= n && Math.abs(by2 - by4) <= n;
    }

    public final byte i(byte by, byte by2) {
        int n = 0;
        if (by > 0) {
            if (((this.bk[by2][by] | this.bk[by2][by - 1]) & 0xC0) == 192) {
                n = 16;
            } else if ((this.bk[by2][by] & this.bk[by2][by - 1] & 0xC0) != 0) {
                n = 1;
            }
        }
        if (by <= this.bl) {
            if (((this.bk[by2][by] | this.bk[by2][by + 1]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.bk[by2][by] & this.bk[by2][by + 1] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        if (by2 > 0) {
            if (((this.bk[by2][by] | this.bk[by2 - 1][by]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.bk[by2][by] & this.bk[by2 - 1][by] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        if (by2 <= this.bm) {
            if (((this.bk[by2][by] | this.bk[by2 + 1][by]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.bk[by2][by] & this.bk[by2 + 1][by] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        return (byte)n;
    }

    public final boolean a(int n, int n2, byte by, boolean bl) {
        byte[] byArray;
        byte[] byArray2;
        byte[] byArray3;
        byte[] byArray4;
        if (bl) {
            byArray4 = this.bo;
            byArray3 = this.bp;
            byArray2 = this.bq;
            byArray = this.br;
        } else {
            byArray4 = this.bq;
            byArray3 = this.br;
            byArray2 = this.bo;
            byArray = this.br;
        }
        byte by2 = byArray4[n];
        byte by3 = byArray3[n];
        byte by4 = byArray2[n2];
        byte by5 = byArray[n2];
        byte by6 = (byte)(this.bk[by3][by2] & 0x1F);
        byte by7 = (byte)(this.bk[by5][by4] & 0x1F);
        if (!this.a(by2, by3, by4, by5, 5)) {
            return false;
        }
        switch (by7) {
            case 0: 
            case 1: 
            case 3: 
            case 4: {
                if (by != 0 && by != 8 && by != 15) break;
                return true;
            }
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                if (by >= 1 && by <= 3 || by == 8 || by == 15) {
                    return true;
                }
                if (by != 6 || (this.i(by4, by5) & 1) == 0 || !this.a(by2, by3, by4, by5, 1)) break;
                return true;
            }
            case 6: {
                if (by == 4 || by == 8) {
                    return true;
                }
                if (by != 10 && by != 12 || (this.i(by4, by5) & 1) == 0) break;
                return true;
            }
            case 5: {
                if ((by < 0 || by > 3) && by != 5 && by != 8 && by != 15) break;
                return true;
            }
            case 2: {
                if (by != 7 || !this.a(by2, by3, by4, by5, 1)) break;
                return true;
            }
        }
        if ((by == 13 && by6 == 2 || by == 9 && by6 >= 7 && by6 <= 12 && by7 != 2) && this.a(by2, by3, by4, by5, 1)) {
            return true;
        }
        if (by == 14 && by7 >= 5 && by7 <= 12 && (this.i(by4, by5) & 1) != 0) {
            return false;
        }
        return false;
    }

    public final int a(short s, short s2, byte by, short s3) {
        int n = 0;
        int n2 = 0;
        if (by == 8) {
            if (s2 != this.be[0] && s2 != this.bf[0] && this.h(s, s2)) {
                return 0x5000000;
            }
            return -1;
        }
        if (by == 11) {
            return 0xA000000;
        }
        switch (by) {
            case 0: 
            case 1: {
                n = 40;
                break;
            }
            case 2: 
            case 7: 
            case 9: {
                n = 56;
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                n = 48;
                break;
            }
            case 6: 
            case 10: 
            case 12: 
            case 13: {
                n = 64;
                break;
            }
            case 14: 
            case 15: {
                n = 72;
            }
        }
        int n3 = this.I[s] >= this.I[s2] ? this.O[s] + this.O[s] * (this.I[s] - this.I[s2]) / 10 - this.O[s2] : this.O[s] - this.O[s2] - this.O[s2] * (this.I[s2] - this.I[s]) / 10;
        if (n3 < n && this.s() % n >= n3) {
            return -1;
        }
        int n4 = 20 + this.ar.nextInt() % 4;
        if (n4 >= this.M[s2]) {
            n4 = this.M[s2] - 1;
        }
        n4 <<= 16;
        if (by == 1) {
            return n4 |= 0x2000000;
        }
        switch (by) {
            case 2: {
                return 0x7000000;
            }
            case 10: {
                return 0x8000000;
            }
            case 15: {
                return 0x9000000;
            }
            case 0: 
            case 3: 
            case 4: 
            case 7: {
                n3 = 100;
                n2 = 0x1000000;
                break;
            }
            case 5: {
                n3 = 160;
                n2 = 0x3000000;
                break;
            }
            case 6: {
                n3 = 150;
                n2 = 0x6000000;
                break;
            }
            case 9: {
                n3 = 150;
                n2 = 0x4000000;
                break;
            }
            case 12: {
                n3 = 180;
                n2 = 0x6000000;
                break;
            }
            case 13: 
            case 14: {
                n3 = 200;
                n2 = 0x6000000;
            }
        }
        if (this.I[s] > this.I[s2]) {
            n3 += n3 * (1 + (this.I[s] - this.I[s2]) / 10);
        }
        n3 += this.ar.nextInt() % 30;
        if (by == 5) {
            if (n3 > s3) {
                n3 = s3;
            }
        } else if (n3 > this.K[s2]) {
            n3 = this.K[s2];
        }
        if (by == 9) {
            n3 |= n4;
        }
        return n3 | n2;
    }

    public final void a(int n, byte by) {
        short s = (short)(n & 0xFFFF);
        byte by2 = (byte)((n >>= 16) & 0xFF);
        switch (by) {
            case 0: 
            case 3: 
            case 4: 
            case 6: 
            case 7: 
            case 12: 
            case 13: 
            case 14: {
                this.bB = "\u58eb\u5175\u6570\u51cf\u5c11 " + s;
                return;
            }
            case 1: {
                this.bB = "\u6b66\u5c06\u4f53\u529b\u51cf\u5c11 " + by2;
                return;
            }
            case 2: {
                this.bB = "\u519b\u961f\u4e2d\u8ba1\u88ab\u56f0";
                return;
            }
            case 5: {
                this.bB = "\u7cae\u98df\u51cf\u5c11 " + s;
                return;
            }
            case 8: {
                this.bB = "\u6210\u529f\u7b3c\u7edc\u6b66\u5c06!";
                return;
            }
            case 9: {
                this.bB = "\u5c06\u4f53\u529b\u51cf\u5c11 " + by2 + ",\u5175\u6570\u51cf\u5c11 " + s;
                return;
            }
            case 10: {
                this.bB = "\u519b\u961f\u4e2d\u8ba1, \u88ab\u56f0\u4e09\u5929";
                return;
            }
            case 11: {
                this.bB = "\u519b\u961f\u57cb\u4f0f\u5b8c\u6bd5!";
                return;
            }
            case 15: {
                this.bB = "\u5947\u95e8\u9041\u7532\u65bd\u5c55\u6210\u529f!";
            }
        }
    }

    public final void a(short s, byte[] byArray, byte by, byte by2, int n, boolean bl) {
        short s2 = (short)(n & 0xFFFF);
        byte by3 = (byte)((n >>= 16) & 0xFF);
        byte by4 = (byte)(n >>= 8);
        switch (by4) {
            case 1: {
                short s3 = s;
                this.K[s3] = (short)(this.K[s3] - s2);
                return;
            }
            case 2: {
                short s4 = s;
                this.M[s4] = (byte)(this.M[s4] - by3);
                return;
            }
            case 3: {
                if (bl) {
                    this.bd = (short)(this.bd - s2);
                    return;
                }
                this.bb = (short)(this.bb - s2);
                return;
            }
            case 4: {
                short s5 = s;
                this.K[s5] = (short)(this.K[s5] - s2);
                short s6 = s;
                this.M[s6] = (byte)(this.M[s6] - by3);
                return;
            }
            case 5: {
                byArray[by] = 3;
                if (bl) {
                    if (this.bg == 10) {
                        this.C[this.aZ] = this.b(this.C[this.aZ], this.t[this.aZ], s);
                        byte by5 = this.aZ;
                        this.t[by5] = (byte)(this.t[by5] + 1);
                        byte[] byArray2 = this.bk[this.br[by]];
                        byte by6 = this.bq[by];
                        byArray2[by6] = (byte)(byArray2[by6] & 0x3F);
                        return;
                    }
                    this.be[this.bg] = this.bf[by];
                    this.bo[this.bg] = this.bq[by];
                    this.bp[this.bg] = this.br[by];
                    this.bs[this.bg] = 0;
                    byte[] byArray3 = this.bk[this.br[by]];
                    byte by7 = this.bq[by];
                    byArray3[by7] = (byte)(byArray3[by7] & 0x3F);
                    byte[] byArray4 = this.bk[this.br[by]];
                    byte by8 = this.bq[by];
                    byArray4[by8] = (byte)(byArray4[by8] | 0x40);
                    this.bg = (byte)(this.bg + 1);
                    return;
                }
                if (this.bh == 10) {
                    this.C[this.aZ] = this.b(this.C[this.aZ], this.t[this.aZ], s);
                    byte by9 = this.aZ;
                    this.t[by9] = (byte)(this.t[by9] + 1);
                    byte[] byArray5 = this.bk[this.bp[by]];
                    byte by10 = this.bo[by];
                    byArray5[by10] = (byte)(byArray5[by10] & 0x3F);
                    return;
                }
                this.bf[this.bh] = this.be[by];
                this.bq[this.bh] = this.bo[by];
                this.br[this.bh] = this.bp[by];
                byte[] byArray6 = this.bk[this.bp[by]];
                byte by11 = this.bo[by];
                byArray6[by11] = (byte)(byArray6[by11] & 0x3F);
                byte[] byArray7 = this.bk[this.bp[by]];
                byte by12 = this.bo[by];
                byArray7[by12] = (byte)(byArray7[by12] | 0x80);
                this.bt[this.bh] = 0;
                this.bh = (byte)(this.bh + 1);
                return;
            }
            case 7: {
                byArray[by] = 4;
                return;
            }
            case 6: {
                short s7 = s;
                this.K[s7] = (short)(this.K[s7] - s2);
                if (bl) {
                    byte by13 = 0;
                    while (by13 < this.bh) {
                        if ((this.bt[by13] <= 0 || this.bt[by13] >= 4) && Math.abs(this.bq[by13] - this.bq[by]) + Math.abs(this.br[by13] - this.br[by]) == 1) {
                            if (this.K[this.bf[by13]] >= s2) {
                                short s8 = this.bf[by13];
                                this.K[s8] = (short)(this.K[s8] - s2);
                            } else {
                                this.K[this.bf[by13]] = 0;
                            }
                        }
                        by13 = (byte)(by13 + 1);
                    }
                    return;
                }
                byte by14 = 0;
                while (by14 < this.bg) {
                    if ((this.bs[by14] <= 0 || this.bs[by14] >= 4) && Math.abs(this.bo[by14] - this.bo[by]) + Math.abs(this.bp[by14] - this.bp[by]) == 1) {
                        if (this.K[this.be[by14]] >= s2) {
                            short s9 = this.be[by14];
                            this.K[s9] = (short)(this.K[s9] - s2);
                        } else {
                            this.K[this.be[by14]] = 0;
                        }
                    }
                    by14 = (byte)(by14 + 1);
                }
                return;
            }
            case 8: {
                byArray[by] = 7;
                if (bl) {
                    byte by15 = 0;
                    while (by15 < this.bh) {
                        if ((this.bt[by15] <= 0 || this.bt[by15] >= 4) && Math.abs(this.bq[by15] - this.bq[by]) + Math.abs(this.br[by15] - this.br[by]) == 1) {
                            this.bt[by15] = 7;
                        }
                        by15 = (byte)(by15 + 1);
                    }
                    return;
                }
                byte by16 = 0;
                while (by16 < this.bg) {
                    if ((this.bs[by16] <= 0 || this.bs[by16] >= 4) && Math.abs(this.bo[by16] - this.bo[by]) + Math.abs(this.bp[by16] - this.bp[by]) == 1) {
                        this.bs[by16] = 7;
                    }
                    by16 = (byte)(by16 + 1);
                }
                return;
            }
            case 9: {
                byArray[by] = 17;
                return;
            }
            case 11: {
                if (bl) {
                    byte by17 = 0;
                    while (by17 < this.bh) {
                        if ((this.bt[by17] <= 0 || this.bt[by17] >= 4) && Math.abs(this.bo[by2] - this.bq[by17]) + Math.abs(this.bp[by2] - this.br[by17]) == 1) {
                            if (this.K[this.bf[by17]] >= s2) {
                                short s10 = this.bf[by17];
                                this.K[s10] = (short)(this.K[s10] - s2);
                            } else {
                                this.K[this.bf[by17]] = 0;
                            }
                        }
                        by17 = (byte)(by17 + 1);
                    }
                    return;
                }
                byte by18 = 0;
                while (by18 < this.bg) {
                    if ((this.bs[by18] <= 0 || this.bs[by18] >= 4) && Math.abs(this.bq[by2] - this.bo[by18]) + Math.abs(this.br[by2] - this.bp[by18]) == 1) {
                        if (this.K[this.be[by18]] >= s2) {
                            short s11 = this.be[by18];
                            this.K[s11] = (short)(this.K[s11] - s2);
                        } else {
                            this.K[this.be[by18]] = 0;
                        }
                    }
                    by18 = (byte)(by18 + 1);
                }
                break;
            }
        }
    }

    public final void e(short s) {
        int n = this.N[s] / 10;
        switch (n) {
            case 9: {
                return;
            }
            case 8: {
                short s2 = s;
                this.N[s2] = (byte)(this.N[s2] + 2);
                return;
            }
            case 7: {
                short s3 = s;
                this.N[s3] = (byte)(this.N[s3] + 3);
                return;
            }
            case 6: {
                short s4 = s;
                this.N[s4] = (byte)(this.N[s4] + 4);
                return;
            }
            case 5: {
                short s5 = s;
                this.N[s5] = (byte)(this.N[s5] + 5);
                return;
            }
        }
        short s6 = s;
        this.N[s6] = (byte)(this.N[s6] + 6);
    }

    public final void f(short s) {
        int n;
        if (this.R[s] < 0) {
            this.R[s] = 30000;
            n = 0;
        }
        if (this.R[s] < 2000) {
            n = 1;
        } else if (this.R[s] < 4000) {
            n = 2;
        } else if (this.R[s] < 7000) {
            n = 3;
        } else if (this.R[s] < 10000) {
            n = 4;
        } else if (this.R[s] < 15000) {
            n = 5;
        } else if (this.R[s] < 20000) {
            n = 6;
        } else if (this.R[s] < 30000) {
            n = 7;
        } else {
            this.R[s] = 30000;
            n = 8;
        }
        if (this.I[s] < n) {
            this.I[s] = n;
            this.e(s);
        }
    }

    public final byte a(short[] sArray, byte by, short s) {
        byte by2 = 0;
        while (by2 < by) {
            if (sArray[by2] == s) {
                return by2;
            }
            by2 = (byte)(by2 + 1);
        }
        return 0;
    }

    public final void g(short s) {
        this.az = s;
        byte by = this.a(this.be, this.bg, s);
        this.j(this.aZ);
        if (by == 0) {
            if (this.aU == 0 && this.az == this.aX) {
                this.aq.W = (byte)18;
            } else {
                this.bA = 0;
            }
        } else {
            this.bA = -by;
        }
        this.aq.Q = true;
    }

    public final byte h(short s) {
        byte by = 1;
        while (by < 8) {
            if (this.b[by] == s) {
                return by;
            }
            by = (byte)(by + 1);
        }
        return 0;
    }

    public final void h(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        while (by3 < this.s[by]) {
            if (this.B[by][by3] == this.k[by]) {
                by2 = by3;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        short s = this.k[by];
        by3 = by2;
        while (by3 > 0) {
            this.B[by][by3] = this.B[by][by3 - 1];
            by3 = (byte)(by3 - 1);
        }
        this.B[by][0] = s;
    }

    public final void a(short s, byte by, short s2) {
        byte by2 = by;
        byte by3 = this.s[by2];
        this.s[by2] = (byte)(by3 + 1);
        this.B[by][by3] = s;
        this.v[by] = this.K[s];
        if (this.j[by] == 0) {
            byte by4 = this.h(s2);
            this.e[by4][this.d[by4]] = by;
            byte by5 = by4;
            this.d[by5] = (byte)(this.d[by5] + 1);
            this.j[by] = s2;
            this.k[by] = s;
            return;
        }
        if (s == s2) {
            this.k[by] = s;
            this.h(by);
        }
    }

    public final void A() {
        byte by = (byte)Math.abs(this.bA);
        if (by < this.bg) {
            this.bs[by] = 1;
            byte[] byArray = this.bk[this.bp[by]];
            byte by2 = this.bo[by];
            byArray[by2] = (byte)(byArray[by2] & 0x3F);
            return;
        }
        by = (byte)(by - this.bh);
        this.bt[by] = 1;
        byte[] byArray = this.bk[this.br[by]];
        byte by3 = this.bq[by];
        byArray[by3] = (byte)(byArray[by3] & 0x3F);
    }

    public final void B() {
        byte by = this.at;
        this.d[by] = (byte)(this.d[by] - 1);
        byte by2 = this.d[this.at];
        byte by3 = 0;
        while (by3 < this.d[this.at]) {
            if (this.e[this.at][by3] == this.aZ) {
                by2 = by3;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = by2;
        while (by3 < this.d[this.at]) {
            this.e[this.at][by3] = this.e[this.at][by3];
            by3 = (byte)(by3 + 1);
        }
        this.e[this.au][this.d[this.au]] = this.aZ;
        byte by4 = this.au;
        this.d[by4] = (byte)(this.d[by4] + 1);
        this.j[this.aZ] = this.b[this.au];
        this.k[this.aZ] = this.bf[0];
    }

    public final void C() {
        byte by = 0;
        byte by2 = 0;
        while (by2 < this.bh) {
            if (this.bt[by2] == 0 || this.bt[by2] > 3) {
                byte by3 = by;
                by = (byte)(by3 + 1);
                this.bf[by3] = this.bf[by2];
            }
            by2 = (byte)(by2 + 1);
        }
        this.bh = by;
        by2 = 0;
        while (by2 < this.bg) {
            if (this.bs[by2] == 2) {
                if (this.bh < 10) {
                    this.m(this.be[by2]);
                    byte by4 = this.bh;
                    this.bh = (byte)(by4 + 1);
                    this.bf[by4] = this.be[by2];
                } else {
                    this.C[this.aZ] = this.b(this.C[this.aZ], this.t[this.aZ], this.be[by2]);
                    byte by5 = this.aZ;
                    this.t[by5] = (byte)(this.t[by5] + 1);
                }
            }
            by2 = (byte)(by2 + 1);
        }
        this.m[this.aZ] = this.bc;
        this.n[this.aZ] = this.bd;
        if (this.bC) {
            byte by6 = this.aZ;
            this.m[by6] = (short)(this.m[by6] + this.ba);
            byte by7 = this.aZ;
            this.n[by7] = (short)(this.n[by7] + this.bb);
            if (this.m[this.aZ] > 9999) {
                this.m[this.aZ] = 9999;
            }
            if (this.n[this.aZ] > 9999) {
                this.n[this.aZ] = 9999;
            }
        }
        this.a(this.bf, this.bh);
        if (this.bE > 1) {
            this.aq.W = (byte)8;
            this.D();
            this.as = 0;
            if (this.d[this.at] <= 0) {
                this.bE = (byte)3;
            }
        }
    }

    public final void i(byte by) {
        if (by > 0) {
            this.a(this.az, by, this.aX);
        } else {
            this.C[this.aZ] = this.b(this.C[this.aZ], this.t[this.aZ], this.az);
            byte by2 = this.aZ;
            this.t[by2] = (byte)(this.t[by2] + 1);
        }
        if (this.bA == 0 && by > 0) {
            byte by3 = by;
            this.m[by3] = (short)(this.m[by3] + this.ba);
            byte by4 = by;
            this.n[by4] = (short)(this.n[by4] + this.bb);
            if (this.m[by] > 9999) {
                this.m[by] = 9999;
            }
            if (this.n[by] > 9999) {
                this.n[by] = 9999;
            }
        }
        if (this.bA < 0) {
            this.aq.Q = false;
        } else {
            this.aq.Z = true;
            byte by5 = this.bA = (byte)(this.bA + 1);
            while (by5 < this.bg) {
                if (this.bs[by5] == 0 || this.bs[by5] > 3) break;
                by5 = (byte)(by5 + 1);
            }
            if (by5 < this.bg) {
                this.j(this.aZ);
                this.bA = by5;
                this.az = this.be[this.bA];
            } else {
                if (this.bA < this.bg) {
                    this.bA = this.bg;
                }
                by5 = (byte)(this.bA - this.bg);
                while (by5 < this.bh) {
                    if (this.bt[by5] == 2) break;
                    by5 = (byte)(by5 + 1);
                }
                if (by5 < this.bh) {
                    this.j(this.aZ);
                    this.bA = (byte)(this.bg + by5);
                    this.az = this.bf[by5];
                } else {
                    this.aq.Q = false;
                    if (this.bn) {
                        this.B();
                    }
                    this.C();
                    this.as = (byte)4;
                    return;
                }
            }
        }
        this.A();
    }

    public final void j(byte by) {
        int n = 0;
        byte by2 = 0;
        while (by2 < this.F[by]) {
            byte by3 = this.E[by][by2];
            if (this.aX == this.j[by3] && this.s[by3] < 10 || this.j[by3] == 0) {
                int n2 = n;
                n = (byte)(n2 + 1);
                this.aT[n2] = by3;
            }
            by2 = (byte)(by2 + 1);
        }
        this.aT[n] = -1;
        this.aU = (byte)n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void D() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.aq.a != 0) {
                this.aq.i();
                this.aq.a = 0;
            }
            if (this.as > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void b(short[] sArray, byte by, byte[] byArray) {
        byte by2 = 0;
        while (by2 < by) {
            if (byArray[by2] >= 4) {
                if (byArray[by2] > 7) {
                    short s = sArray[by2];
                    this.K[s] = (short)(this.K[s] - 100);
                    if (this.K[sArray[by2]] < 0) {
                        this.K[sArray[by2]] = 0;
                    }
                }
                if (byArray[by2] == 4 || byArray[by2] == 5 || byArray[by2] == 8) {
                    byArray[by2] = 0;
                } else {
                    byte by3 = by2;
                    byArray[by3] = (byte)(byArray[by3] - 1);
                }
            }
            by2 = (byte)(by2 + 1);
        }
    }

    public final boolean a(byte by, short s) {
        int n = 0;
        while (n < this.F[by]) {
            byte by2 = this.E[by][n];
            if (this.j[by2] == s && this.s[by2] < 10 || this.j[by2] == 0) {
                return true;
            }
            ++n;
        }
        return false;
    }

    public final boolean E() {
        if (!this.a(this.aZ, this.aY)) {
            return false;
        }
        return this.K[this.bf[0]] < 100 && this.M[this.bf[0]] < 40;
    }

    public final boolean F() {
        return this.E();
    }

    public final int j(byte by, byte by2) {
        byte by3 = this.d(this.bf[by]);
        byte[] byArray = new byte[16];
        int n = 0;
        byte by4 = 0;
        while (by4 < by3) {
            if (by4 != 8 && by4 != 11 && (this.K[this.be[by2]] != 0 || by4 != 0 && by4 != 3 && by4 != 4) && this.a((int)by, (int)by2, by4, false) && this.g(by4) <= this.bj) {
                byArray[n] = by4;
                n = (byte)(n + 1);
            }
            by4 = (byte)(by4 + 1);
        }
        if (n == 0) {
            return -1;
        }
        return byArray[this.s() % n];
    }

    public final int k(byte by, byte by2) {
        return this.j(by, by2);
    }

    public final void a(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        byArray2[by] = new byte[this.bg];
        byte by2 = 0;
        while (by2 < this.bg) {
            if (this.bs[by2] <= 0 || this.bs[by2] >= 4) {
                int n = Math.abs(this.bq[by] - this.bo[by2]);
                int n2 = Math.abs(this.br[by] - this.bp[by2]);
                if (n <= 5 && n2 <= 5 && this.k(by, by2) >= 0) {
                    byArray2[by][byArray3[by]] = by2;
                    byte by3 = by;
                    byArray3[by3] = (byte)(byArray3[by3] + 1);
                }
            }
            by2 = (byte)(by2 + 1);
        }
    }

    public final void a(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.F()) {
            byArray[0] = 3;
            return;
        }
        this.a((byte)0, byArray, byArray2, byArray3);
        if (byArray3[0] > 0) {
            byArray[0] = 2;
        }
    }

    public final byte a(short s, byte[] byArray, byte by, byte by2) {
        return byArray[this.s() % by];
    }

    public final byte b(short s, byte[] byArray, byte by, byte by2) {
        return this.a(s, byArray, by, by2);
    }

    public final byte G() {
        byte[] byArray = new byte[this.F[this.aZ]];
        byte by = 0;
        int n = 0;
        while (n < this.F[this.aZ]) {
            byte by2 = this.E[this.aZ][n];
            if (this.j[by2] == this.aY && this.s[by2] < 10 || this.j[by2] == 0) {
                byArray[by] = by2;
                by = (byte)(by + 1);
            }
            ++n;
        }
        if (by > 0) {
            return this.b(this.aY, byArray, by, this.aZ);
        }
        return -1;
    }

    public final void i(short s) {
        byte by = 0;
        while (by < this.bh) {
            if (this.bf[by] == s) {
                this.bt[by] = 1;
                byte[] byArray = this.bk[this.br[by]];
                byte by2 = this.bq[by];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                return;
            }
            by = (byte)(by + 1);
        }
    }

    public final void a(short s, byte by) {
        if (by > 0) {
            this.a(s, by, this.aY);
        } else {
            this.C[this.aZ] = this.b(this.C[this.aZ], this.t[this.aZ], s);
            byte by2 = this.aZ;
            this.t[by2] = (byte)(this.t[by2] + 1);
        }
        this.i(s);
    }

    public final void a(short[] sArray, byte by) {
        this.v[this.aZ] = this.b(sArray, by);
        this.s[this.aZ] = by;
        this.B[this.aZ] = sArray;
    }

    public final void H() {
        byte by;
        byte by2;
        short[] sArray = new short[10];
        short[] sArray2 = new short[10];
        short[] sArray3 = new short[10];
        byte by3 = 0;
        byte by4 = 0;
        byte by5 = 0;
        boolean bl = false;
        byte by6 = 0;
        while (by6 < this.bg) {
            if (this.bs[by6] == 0 || this.bs[by6] > 3) {
                byte by7 = by3;
                by3 = (byte)(by7 + 1);
                sArray3[by7] = this.be[by6];
            } else if (this.bs[by6] == 2) {
                byte by8 = by4;
                by4 = (byte)(by8 + 1);
                sArray[by8] = this.be[by6];
            }
            by6 = (byte)(by6 + 1);
        }
        this.be = sArray3;
        this.bg = by3;
        sArray3 = new short[10];
        by3 = 0;
        if (this.bt[0] == 2 || this.bt[0] == 3 || this.G() < 0) {
            bl = true;
            if (this.Q[this.bf[0]] == 100) {
                this.av = this.h(this.aY);
                this.bE = 1;
                this.bt[0] = 3;
            }
        }
        by6 = 0;
        while (by6 < this.bh) {
            if (this.bt[by6] == 0 || this.bt[by6] > 3) {
                byte by9 = by3;
                by3 = (byte)(by9 + 1);
                sArray3[by9] = this.bf[by6];
            } else if (this.bt[by6] == 2) {
                byte by10 = by5;
                by5 = (byte)(by10 + 1);
                sArray2[by10] = this.bf[by6];
            }
            by6 = (byte)(by6 + 1);
        }
        this.bf = sArray3;
        this.bh = by3;
        by6 = 0;
        while (by6 < this.bh) {
            by2 = this.G();
            if (by2 < 0) {
                by = by6;
                while (by < this.bh) {
                    byte by11 = by5;
                    by5 = (byte)(by11 + 1);
                    sArray2[by11] = this.bf[by];
                    by = (byte)(by + 1);
                }
                break;
            }
            if (!bl && by6 == 0) {
                byte by12 = by2;
                this.m[by12] = (short)(this.m[by12] + this.bc);
                byte by13 = by2;
                this.n[by13] = (short)(this.n[by13] + this.bd);
                if (this.m[by2] > 9999) {
                    this.m[by2] = 9999;
                }
                if (this.n[by2] > 9999) {
                    this.n[by2] = 9999;
                }
            }
            this.a(this.bf[by6], by2);
            by6 = (byte)(by6 + 1);
        }
        by6 = 0;
        while (by6 < by4) {
            by2 = this.G();
            if (by2 < 0) {
                by = by6;
                while (by < by4) {
                    byte by14 = this.bg;
                    this.bg = (byte)(by14 + 1);
                    this.be[by14] = sArray[by];
                }
                break;
            }
            this.m(sArray[by6]);
            this.a(sArray[by6], by2);
            by6 = (byte)(by6 + 1);
        }
        by6 = 0;
        while (by6 < by5) {
            if (this.bg >= 10) {
                this.C[this.aZ] = this.b(this.C[this.aZ], this.t[this.aZ], sArray2[by6]);
                byte by15 = this.aZ;
                this.t[by15] = (byte)(this.t[by15] + 1);
            } else {
                this.m(sArray2[by6]);
                byte by16 = this.bg;
                this.bg = (byte)(by16 + 1);
                this.be[by16] = sArray2[by6];
            }
            by6 = (byte)(by6 + 1);
        }
        this.a(this.be, this.bg);
        this.n[this.aZ] = this.bb;
        this.m[this.aZ] = this.ba;
        if (bl) {
            byte by17 = this.aZ;
            this.m[by17] = (short)(this.m[by17] + this.bc);
            byte by18 = this.aZ;
            this.n[by18] = (short)(this.n[by18] + this.bd);
            if (this.m[this.aZ] > 9999) {
                this.m[this.aZ] = 9999;
            }
            if (this.n[this.aZ] > 9999) {
                this.n[this.aZ] = 9999;
            }
        }
        this.as = (byte)4;
    }

    public final void I() {
        byte by;
        this.H();
        byte by2 = this.h(this.aX);
        byte by3 = by = this.h(this.j[this.aZ]);
        this.d[by3] = (byte)(this.d[by3] - 1);
        byte by4 = this.d[by];
        byte by5 = 0;
        while (by5 < this.d[by]) {
            if (this.e[by][by5] == this.aZ) {
                by4 = by5;
                break;
            }
            by5 = (byte)(by5 + 1);
        }
        by5 = by4;
        while (by5 < this.d[by]) {
            this.e[by][by5] = this.e[by][by5 + 1];
            by5 = (byte)(by5 + 1);
        }
        this.e[by2][this.d[by2]] = this.aZ;
        byte by6 = by2;
        this.d[by6] = (byte)(this.d[by6] + 1);
        this.j[this.aZ] = this.b[by2];
        this.k[this.aZ] = this.be[0];
        if (this.d[by] == 0) {
            this.bE = (byte)4;
            this.a[this.av] = 0;
        }
    }

    public final boolean b(int n) {
        if (!this.a(this.aZ, this.aY)) {
            return false;
        }
        return this.K[this.bf[n]] < 100 && this.M[this.bf[n]] < 50;
    }

    public final void a(byte by, byte by2, byte by3, byte by4, byte[] byArray, byte[][] byArray2, byte[] byArray3, byte by5) {
        int n = by3 - by;
        int n2 = by4 - by2;
        byArray2[by5] = new byte[16];
        byArray3[by5] = 0;
        if (n >= 0 && n2 >= 0) {
            if (n - n2 >= 0) {
                if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by4 < this.bm - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                }
            } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            } else if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by4 < this.bm - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            }
        } else if (n < 0 && n2 >= 0) {
            if (n + n2 < 0) {
                if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by4 < this.bl - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                }
            } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            } else if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by4 < this.bl - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            }
        } else if (n >= 0 && n2 < 0) {
            if (n + n2 >= 0) {
                if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                } else if (by4 < this.bm - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                }
            } else if (by4 < this.bm - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            } else if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            }
        } else if (n < 0 && n2 < 0) {
            if (n - n2 < 0) {
                if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                } else if (by4 < this.bm - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                }
            } else if (by4 < this.bm - 1 && this.f(this.bk[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            } else if (by3 < this.bl - 1 && this.f(this.bk[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by3 > 0 && this.f(this.bk[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by4 > 0 && this.f(this.bk[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            }
        }
        while (by != by3 || by2 != by4) {
            n = by3 - by;
            n2 = by4 - by2;
            if (Math.abs(n) - Math.abs(n2) >= 0) {
                if (n >= 0 && (this.bk[by2][by + 1] & 0xC0) == 0) {
                    byArray2[by5][byArray3[by5]] = 4;
                    by = (byte)(by + 1);
                } else if (n < 0 && (this.bk[by2][by - 1] & 0xC0) == 0) {
                    byArray2[by5][byArray3[by5]] = 3;
                    by = (byte)(by - 1);
                } else if (n2 >= 0) {
                    byArray2[by5][byArray3[by5]] = 2;
                    by2 = (byte)(by2 + 1);
                } else {
                    byArray2[by5][byArray3[by5]] = 1;
                    by2 = (byte)(by2 - 1);
                }
            } else if (n2 >= 0 && (this.bk[by2 + 1][by] & 0xC0) == 0) {
                byArray2[by5][byArray3[by5]] = 2;
                by2 = (byte)(by2 + 1);
            } else if (n2 < 0 && (this.bk[by2 - 1][by] & 0xC0) == 0) {
                byArray2[by5][byArray3[by5]] = 1;
                by2 = (byte)(by2 - 1);
            } else if (n >= 0) {
                byArray2[by5][byArray3[by5]] = 4;
                by = (byte)(by + 1);
            } else {
                byArray2[by5][byArray3[by5]] = 3;
                by = (byte)(by - 1);
            }
            byte by6 = by5;
            byArray3[by6] = (byte)(byArray3[by6] + 1);
            if (byArray3[by5] == 16) break;
        }
        int n3 = 0;
        while (n3 < byArray3[by5] / 2) {
            byte by7 = byArray2[by5][n3];
            byArray2[by5][n3] = byArray2[by5][byArray3[by5] - n3 - 1];
            byArray2[by5][byArray3[by5] - n3 - 1] = by7;
            n3 = (byte)(n3 + 1);
        }
    }

    public final void a(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        this.a(this.bq[by], this.br[by], this.bo[by2], this.bp[by2], byArray, byArray2, byArray3, by);
    }

    public final void b(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        this.a(by, by2, byArray, byArray2, byArray3);
    }

    public final void b(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if ((this.bt[by] & 0x7F) != 0) {
            byArray[by] = 0;
            return;
        }
        byArray[by] = 1;
        this.b(by, (byte)0, byArray, byArray2, byArray3);
    }

    public final void j(short s) {
        int n = this.K[s] / 2;
        if ((n += this.s() % 100) < 200) {
            n = 200 + this.s() % 100;
        }
        if (n > this.K[s]) {
            n = this.K[s];
        }
        short s2 = s;
        this.K[s2] = (short)(this.K[s2] - n);
    }

    public final void b(int n, int n2) {
        try {
            this.az = this.be[n];
            this.aA = this.bf[n2];
            this.aq.X = this.az;
            this.aq.Y = this.aA;
            this.bF = (byte)(this.bk[this.bp[n]][this.bo[n]] & 0xF);
            this.aq.W = (byte)2;
            this.D();
            this.as = (byte)3;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void a(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        byte by3 = 0;
        int n = 0;
        if (this.bj < 2) {
            return;
        }
        byte by4 = 0;
        while (by4 < this.bg) {
            byte by5;
            byte by6;
            if ((this.bs[by4] <= 0 || this.bs[by4] >= 4) && (by6 = (byte)Math.abs(this.bo[by4] - this.bq[by])) + (by5 = (byte)Math.abs(this.bp[by4] - this.br[by])) == 1) {
                this.bj = (byte)(this.bj - 2);
                this.b((int)by4, (int)by);
                return;
            }
            by4 = (byte)(by4 + 1);
        }
        switch (byArray[byArray2[by] - 1]) {
            case 1: {
                by3 = this.bq[by];
                n = (byte)(this.br[by] - 1);
                break;
            }
            case 2: {
                by3 = this.bq[by];
                n = (byte)(this.br[by] + 1);
                break;
            }
            case 3: {
                by3 = (byte)(this.bq[by] - 1);
                n = this.br[by];
                break;
            }
            case 4: {
                by3 = (byte)(this.bq[by] + 1);
                n = this.br[by];
            }
        }
        byte by7 = (byte)this.h(this.bk[n][by3], this.J[this.bf[by]]);
        if (this.bj >= by7) {
            this.bj = (byte)(this.bj - by7);
            byte[] byArray3 = this.bk[this.br[by]];
            byte by8 = this.bq[by];
            byArray3[by8] = (byte)(byArray3[by8] & 0x3F);
            byte[] byArray4 = this.bk[n];
            byte by9 = by3;
            byArray4[by9] = (byte)(byArray4[by9] | 0x80);
            this.bq[by] = by3;
            this.br[by] = n;
            byte by10 = by;
            byArray2[by10] = (byte)(byArray2[by10] - 1);
            if ((this.bk[n][by3] & 0x20) != 0) {
                this.bB = "\u4e2d\u8ba1\u8c0b\u5341\u9762\u57cb\u4f0f";
                this.aq.W = (byte)100;
                this.D();
                this.as = 0;
                this.j(this.bf[by]);
            }
        }
    }

    public final void b(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        this.a(by, by2, byArray, byArray2);
    }

    public final void c(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        byte by3 = byArray[this.s() % byArray2[by]];
        byte by4 = (byte)this.k(by, by3);
        if (by4 < 0) {
            return;
        }
        this.bj = (byte)(this.bj - this.g(by4));
        int n = this.a(this.bf[by], this.be[by3], by4, this.bb);
        if (n > 0) {
            this.a(n, by4);
            short s = this.K[this.be[by3]];
            this.a(this.be[by3], this.bs, by3, by, n, false);
            short s2 = this.bf[by];
            this.R[s2] = (short)(this.R[s2] + (s - this.K[this.be[by3]]));
            this.f(this.bf[by]);
        } else {
            this.bB = "\u8ba1\u7b56\u5931\u8d25\uff01";
        }
        this.aq.W = (byte)100;
        this.D();
        this.as = 0;
    }

    public final void d(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        switch (by2) {
            case 1: {
                this.b(by, by2, byArray, byArray2);
                break;
            }
            case 2: {
                this.c(by, by2, byArray, byArray2);
                break;
            }
            case 3: {
                this.aq.W = (byte)3;
                this.D();
                this.as = 0;
                this.a(this.bf[by], this.G());
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (Exception exception) {}
        this.aq.repaint();
    }

    public final void J() {
        byte by = -1;
        byte[] byArray = new byte[this.bh];
        byte[][] byArray2 = new byte[this.bh][];
        byte[] byArray3 = new byte[this.bh];
        while (by != this.bj && this.bj >= 2) {
            System.gc();
            by = this.bj;
            byte by2 = 0;
            while (by2 < this.bh) {
                byArray[by2] = 0;
                byArray3[by2] = 0;
                by2 = (byte)(by2 + 1);
            }
            this.a(byArray, byArray2, byArray3);
            if (byArray[0] == 3) {
                this.aq.W = (byte)5;
                this.D();
                return;
            }
            by2 = 1;
            while (by2 < this.bh) {
                if ((this.bt[by2] & 0x1F) == 0) {
                    if (this.b((int)by2)) {
                        byArray[by2] = 3;
                    } else {
                        this.b(by2, byArray, byArray2, byArray3);
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            by2 = this.bD;
            while (by2 < this.bh) {
                System.gc();
                if ((this.bt[by2] & 0x1F) == 0) {
                    this.d(by2, byArray[by2], byArray2[by2], byArray3);
                    if (this.as > 0) {
                        this.bD = (byte)(by2 + 1);
                        return;
                    }
                }
                by2 = (byte)(by2 + 1);
            }
        }
        System.gc();
    }

    public final void K() {
        this.J();
    }

    public final boolean k(byte by) {
        if ((this.bk[this.br[by]][this.bq[by]] & 0xF) == 2) {
            this.aq.W = (byte)21;
            this.D();
            return true;
        }
        return false;
    }

    public final void b(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.F()) {
            byArray[0] = 3;
            return;
        }
        this.b((byte)0, byArray, byArray2, byArray3);
    }

    public final void L() {
        byte by = -1;
        byte[] byArray = new byte[this.bh];
        byte[][] byArray2 = new byte[this.bh][];
        byte[] byArray3 = new byte[this.bh];
        while (by != this.bj && this.bj >= 2) {
            System.gc();
            by = this.bj;
            byte by2 = 0;
            while (by2 < this.bh) {
                byArray[by2] = 0;
                byArray3[by2] = 0;
                by2 = (byte)(by2 + 1);
            }
            this.b(byArray, byArray2, byArray3);
            if (byArray[0] == 3) {
                this.aq.W = (byte)5;
                this.D();
                return;
            }
            by2 = 1;
            while (by2 < this.bh) {
                if ((this.bt[by2] & 0x1F) == 0) {
                    if (this.b((int)by2)) {
                        byArray[by2] = 3;
                    } else {
                        this.b(by2, byArray, byArray2, byArray3);
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            by2 = 0;
            while (by2 < this.bh) {
                System.gc();
                if ((this.bt[by2] & 0x1F) == 0) {
                    this.d(by2, byArray[by2], byArray2[by2], byArray3);
                    if (this.k(by2)) {
                        this.as = (byte)4;
                        return;
                    }
                    if (this.as > 0) {
                        this.bD = (byte)(by2 + 1);
                        return;
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            System.gc();
        }
    }

    public final void M() {
        this.L();
    }

    public final void N() {
        block13: {
            do {
                if (this.as == 0) {
                    this.ci = true;
                    this.by = (byte)(this.by + 1);
                    this.bz = true;
                    if (this.by > 1) {
                        this.bi = this.a(this.be, this.bg, this.bs, this.bi);
                    }
                    this.D();
                } else if (this.as == 2 || this.as == 5 || this.as == 6) {
                    this.as = 0;
                    this.D();
                }
                if (this.as == 1) {
                    this.as = 0;
                    this.b(this.be, this.bg, this.bs);
                    this.bj = this.a(this.bf, this.bh, this.bt, this.bj);
                    this.bz = false;
                    this.ci = false;
                    this.bD = 0;
                } else {
                    if (this.as == 2 || this.as == 4 || this.as == 5) break block13;
                    if (this.as == 6) {
                        return;
                    }
                    if (this.as == 3) {
                        this.as = 0;
                    }
                }
                if (this.bn) {
                    this.M();
                } else {
                    this.K();
                }
                if (this.as == 3) {
                    return;
                }
                this.b(this.bf, this.bh, this.bt);
                this.bb = (short)(this.bb - ((this.a(this.be, this.bg, this.bs) - 1) / 250 + 1));
                if (this.bb <= 0) {
                    this.bb = 0;
                    this.aq.W = (byte)7;
                    this.D();
                    return;
                }
                this.bd = (short)(this.bd - ((this.a(this.bf, this.bh, this.bt) - 1) / 250 + 1));
                if (this.bd > 0) continue;
                this.bd = 0;
                this.aq.W = (byte)6;
            } while (this.by != 30);
            this.aq.W = (byte)20;
            this.D();
            return;
        }
    }

    public final void O() {
        this.cj = false;
        this.bG = this.K[this.az] == 0 ? (byte)1 : (byte)((this.K[this.az] - 1) / 100 + 2);
        this.bH = this.K[this.aA] == 0 ? (byte)1 : (byte)((this.K[this.aA] - 1) / 100 + 2);
        int n = 0;
        while (n < this.bJ) {
            byte by = 0;
            while (by < this.bK) {
                this.bI[by][n] = 0;
                by = (byte)(by + 1);
            }
            n = (byte)(n + 1);
        }
        n = 0;
        while (n < 4) {
            this.cg[n] = 0;
            n = (byte)(n + 1);
        }
        if (this.bF == 2) {
            if (this.ci) {
                this.bI[0][11] = 32;
                this.bI[6][11] = 32;
                this.bI[1][11] = 32;
                this.bI[5][11] = 32;
                this.bI[3][9] = 32;
                this.bI[0][6] = 2;
                this.bI[1][10] = 16;
                this.bI[6][6] = 2;
                this.bI[5][10] = 16;
                n = 6;
                while (n < 10) {
                    this.bI[1][n] = 32;
                    this.bI[5][n] = 32;
                    n = (byte)(n + 1);
                }
            } else {
                this.bI[0][4] = 32;
                this.bI[6][4] = 32;
                this.bI[1][4] = 32;
                this.bI[5][4] = 32;
                this.bI[6][4] = 32;
                this.bI[3][6] = 32;
                this.bI[0][9] = 2;
                this.bI[1][5] = 16;
                this.bI[6][9] = 2;
                this.bI[5][5] = 16;
                n = 6;
                while (n < 10) {
                    this.bI[1][n] = 32;
                    this.bI[5][n] = 32;
                    n = (byte)(n + 1);
                }
                n = 0;
                while (n < 4) {
                    this.cg[n] = 1;
                    n = (byte)(n + 1);
                }
            }
            this.cj = true;
        }
        this.o();
        this.bZ = 0;
        this.ca = 0;
        this.cb = 0;
        this.cc = 0;
        this.cf = 0;
        this.ch = 0;
        this.cl = 0;
        this.cm = 0;
        this.cs = false;
        n = 0;
        while (n < 4) {
            this.cd[n] = 0;
            this.ce[n] = 0;
            n = (byte)(n + 1);
        }
        this.aq.a((byte)21);
        this.aq.af = 1;
        this.aq.ag = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void P() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.aq.a != 0) {
                this.aq.h();
                this.aq.a = 0;
            }
            if (this.as > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final boolean Q() {
        int n = 0;
        int n2 = 0;
        if (this.bV[1][0] == 2) {
            return true;
        }
        int n3 = 1;
        while (n3 < this.bG) {
            if (this.bY[0][n3]) {
                n = (short)(n + this.bW[0][n3]);
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < this.bH) {
            if (this.bY[1][n3]) {
                n2 = (short)(n2 + this.bW[1][n3]);
            }
            ++n3;
        }
        return n > 300 && n2 < 100;
    }

    public final void c(int n) {
        if (this.Q() && n >= 4) {
            int n2 = 0;
            while (n2 < this.bH) {
                this.bV[1][n2] = 2;
                ++n2;
            }
            return;
        }
        int n3 = 0;
        while (n3 < this.bH) {
            this.bV[1][n3] = 1;
            ++n3;
        }
    }

    public final void d(int n) {
        this.c(n);
    }

    public final void e(int n) {
        if (this.Q() && n >= 4) {
            int n2 = 0;
            while (n2 < this.bH) {
                this.bV[1][n2] = 2;
                ++n2;
            }
            return;
        }
        this.bV[1][0] = n >= 7 ? (byte)0 : 1;
        int n3 = 1;
        while (n3 < this.bH) {
            this.bV[1][n3] = 0;
            ++n3;
        }
    }

    public final void f(int n) {
        this.e(n);
    }

    public final void g(int n) {
        this.e(n);
    }

    public final void h(int n) {
        if (this.cj) {
            if (this.ci) {
                this.d(n);
                return;
            }
            this.f(n);
            return;
        }
        this.g(n);
    }

    public final byte l(byte by) {
        byte by2 = this.bP[1][0];
        byte by3 = this.bQ[1][0];
        byte by4 = this.bP[0][by];
        byte by5 = this.bQ[0][by];
        int n = -1;
        if (by2 < 15 && (this.bI[by3][by2 + 1] & 0x60) == 0 && by3 - by5 != 0) {
            by2 = (byte)(by2 + 1);
        } else if (by3 - by5 <= 0) {
            if (by3 > 0 && (this.bI[by3 - 1][by2] & 0x60) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < 6 && (this.bI[by3 + 1][by2] & 0x60) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by2 > 0 && (this.bI[by3][by2 - 1] & 0x60) == 0) {
                by2 = (byte)(by2 - 1);
            }
        } else if (by3 < 6 && (this.bI[by3 + 1][by2] & 0x60) == 0) {
            by3 = (byte)(by3 + 1);
        } else if (by3 > 0 && (this.bI[by3 - 1][by2] & 0x60) == 0) {
            by3 = (byte)(by3 - 1);
        } else if (by2 > 0 && (this.bI[by3][by2 - 1] & 0x60) == 0) {
            by2 = (byte)(by2 - 1);
        }
        if (by4 < 15 && by2 - by4 > 0 && (this.bI[by5][by4 + 1] & 0x60) == 0) {
            if (by2 == by4 + 1 && by3 == by5) {
                n = 3;
            } else {
                return 3;
            }
        }
        if (by4 > 0 && by2 - by4 < 0 && (this.bI[by5][by4 - 1] & 0x60) == 0) {
            if (by2 == by4 - 1 && by3 == by5) {
                n = 2;
            } else {
                return 2;
            }
        }
        if (by5 < 6 && by3 - by5 > 0 && (this.bI[by5 + 1][by4] & 0x60) == 0) {
            return 1;
        }
        if (by5 > 0 && by3 - by5 < 0 && (this.bI[by5 - 1][by4] & 0x60) == 0) {
            return 0;
        }
        if (by2 - by4 == 0) {
            if (by4 < 15 && by4 > 0 && (this.bI[by5][by4 + 1] & 0x60) == 0 && this.bI[by5][by4 - 1] == 0) {
                return (byte)(this.s() % 2 + 2);
            }
            if (by4 < 15 && (this.bI[by5][by4 + 1] & 0x60) == 0) {
                return 3;
            }
            if (by4 > 0 && (this.bI[by5][by4 - 1] & 0x60) == 0) {
                return 2;
            }
        }
        if (by3 - by5 == 0) {
            if (by5 < 6 && by5 > 0 && (this.bI[by5 + 1][by4] & 0x60) == 0 && (this.bI[by5 - 1][by4] & 0x60) == 0) {
                return (byte)(this.s() % 2);
            }
            if (by5 < 6 && (this.bI[by5 + 1][by4] & 0x60) == 0) {
                return 1;
            }
            if (by5 > 0 && (this.bI[by5 - 1][by4] & 0x60) == 0) {
                return 0;
            }
        }
        return (byte)n;
    }

    public final void a(byte by, byte by2, byte by3) {
        switch (by3) {
            case 0: {
                this.c(by, by2);
                return;
            }
            case 1: {
                this.d(by, by2);
                return;
            }
            case 2: {
                this.e(by, by2);
                return;
            }
            case 3: {
                this.f(by, by2);
            }
        }
    }

    public final byte l(byte by, byte by2) {
        byte[] byArray = new byte[5];
        int n = 0;
        if (this.bQ[0][by] > 0 && (this.bI[this.bQ[0][by] - 1][this.bP[0][by]] & 0x80) != 0) {
            if (by2 == 0 || this.bR[0][by] == 0 && this.bP[1][0] == this.bP[0][by] && this.bQ[1][0] == this.bQ[0][by] - 1) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (this.bQ[0][by] < 6 && (this.bI[this.bQ[0][by] + 1][this.bP[0][by]] & 0x80) != 0) {
            if (by2 == 1 || this.bR[0][by] == 0 && this.bP[1][0] == this.bP[0][by] && this.bQ[1][0] == this.bQ[0][by] + 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (this.bP[0][by] > 0 && (this.bI[this.bQ[0][by]][this.bP[0][by] - 1] & 0x80) != 0) {
            if (by2 == 2 || this.bR[0][by] == 0 && this.bP[1][0] == this.bP[0][by] - 1 && this.bQ[1][0] == this.bQ[0][by]) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (this.bP[0][by] < 15 && (this.bI[this.bQ[0][by]][this.bP[0][by] + 1] & 0x80) != 0) {
            if (by2 == 3 || this.bR[0][by] == 0 && this.bP[1][0] == this.bP[0][by] + 1 && this.bQ[1][0] == this.bQ[0][by]) {
                return 3;
            }
            byArray[n++] = 3;
        }
        if (n > 0 && (by2 == -1 || this.s() % 10 >= 3)) {
            byte by3 = (byte)(this.s() % n);
            return byArray[by3];
        }
        return -1;
    }

    public final void a(byte by, byte by2, byte by3, byte by4, boolean bl) {
        this.co = by;
        this.cp = by2;
        this.cq = by3;
        this.cr = by4;
        this.cs = bl;
        this.cn = (byte)6;
    }

    public final synchronized void b(byte by, byte by2, boolean bl) {
        byte by3;
        byte by4;
        if (bl) {
            by4 = this.bP[1][by];
            by3 = this.bQ[1][by];
        } else {
            by4 = this.bP[0][by];
            by3 = this.bQ[0][by];
        }
        switch (by2) {
            case 0: {
                by3 = (byte)(by3 - 1);
                break;
            }
            case 1: {
                by3 = (byte)(by3 + 1);
                break;
            }
            case 2: {
                by4 = (byte)(by4 - 1);
                break;
            }
            case 3: {
                by4 = (byte)(by4 + 1);
            }
        }
        if (bl) {
            byte by5 = 0;
            while (by5 < this.bG) {
                if (this.bP[0][by5] == by4 && this.bQ[0][by5] == by3) {
                    this.a((byte)1, by, (byte)0, by5, bl);
                }
                by5 = (byte)(by5 + 1);
            }
            return;
        }
        byte by6 = 0;
        while (by6 < this.bH) {
            if (this.bP[1][by6] == by4 && this.bQ[1][by6] == by3) {
                this.a((byte)0, by, (byte)1, by6, bl);
            }
            by6 = (byte)(by6 + 1);
        }
    }

    public final boolean m(byte by, byte by2) {
        byte by3 = this.l(by, by2);
        if (by3 >= 0) {
            this.b(by, by3, false);
        } else if (by2 >= 0) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final byte n(byte by, byte by2) {
        byte[] byArray = new byte[4];
        int n = 0;
        int n2 = 5;
        if (this.bQ[0][by] > 0 && (this.bI[this.bQ[0][by] - 1][this.bP[0][by]] & 0x80) != 0 && by2 == 0) {
            return 0;
        }
        if (this.bQ[0][by] < 6 && (this.bI[this.bQ[0][by] + 1][this.bP[0][by]] & 0x80) != 0 && by2 == 1) {
            return 1;
        }
        if (this.bP[0][by] > 0 && (this.bI[this.bQ[0][by]][this.bP[0][by] - 1] & 0x80) != 0 && by2 == 2) {
            return 2;
        }
        if (this.bP[0][by] < 15 && (this.bI[this.bQ[0][by]][this.bP[0][by] + 1] & 0x80) != 0 && by2 == 3) {
            return 3;
        }
        if ((this.ca & 0xF0) == 48) {
            n2 = 7;
        }
        int n3 = 1;
        while (n3 < n2) {
            if (this.bQ[0][by] + n3 > 6) break;
            if ((this.bI[this.bQ[0][by] + n3][this.bP[0][by]] & 0x80) != 0) {
                ++n;
                byArray[0] = 1;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bQ[0][by] < n3) break;
            if ((this.bI[this.bQ[0][by] - n3][this.bP[0][by]] & 0x80) != 0) {
                byArray[n++] = 0;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bP[0][by] < n3) break;
            if ((this.bI[this.bQ[0][by]][this.bP[0][by] - n3] & 0x80) != 0) {
                byArray[n++] = 2;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bP[0][by] + n3 > 15) break;
            if ((this.bI[this.bQ[0][by]][this.bP[0][by] + n3] & 0x80) != 0) {
                byArray[n++] = 3;
                break;
            }
            ++n3;
        }
        if (n > 0 && (by2 == -1 || this.s() % 10 >= 2)) {
            return byArray[this.s() % n];
        }
        return -1;
    }

    public final synchronized void c(byte by, byte by2, boolean bl) {
        byte by3;
        int n;
        byte by4;
        byte by5;
        byte by6 = 5;
        if (bl) {
            by5 = this.bP[1][by];
            by4 = this.bQ[1][by];
            n = 64;
            if ((this.cb & 0xF0) == 48) {
                by6 = 7;
            }
        } else {
            by5 = this.bP[0][by];
            by4 = this.bQ[0][by];
            n = -128;
            if ((this.ca & 0xF0) == 48) {
                by6 = 7;
            }
        }
        block0 : switch (by2) {
            case 0: {
                by3 = 1;
                while (by3 < by6) {
                    if (by4 - by3 >= 0 && (this.bI[by4 - by3][by5] & n) != 0) {
                        by4 = (byte)(by4 - by3);
                        break block0;
                    }
                    by3 = (byte)(by3 + 1);
                }
                break;
            }
            case 1: {
                by3 = 1;
                while (by3 < by6) {
                    if (by4 + by3 <= 6 && (this.bI[by4 + by3][by5] & n) != 0) {
                        by4 = (byte)(by4 + by3);
                        break block0;
                    }
                    by3 = (byte)(by3 + 1);
                }
                break;
            }
            case 2: {
                by3 = 1;
                while (by3 < by6) {
                    if (by5 - by3 >= 0 && (this.bI[by4][by5 - by3] & n) != 0) {
                        by5 = (byte)(by5 - by3);
                        break block0;
                    }
                    by3 = (byte)(by3 + 1);
                }
                break;
            }
            case 3: {
                by3 = 1;
                while (by3 < by6) {
                    if (by5 + by3 <= 15 && (this.bI[by4][by5 + by3] & n) != 0) {
                        by5 = (byte)(by5 + by3);
                        break block0;
                    }
                    by3 = (byte)(by3 + 1);
                }
                break;
            }
        }
        if (bl) {
            this.aq.a(this.bP[1][by], this.bQ[1][by], by5, by4, !bl);
            by3 = 0;
            while (by3 < this.bG) {
                if (this.bP[0][by3] == by5 && this.bQ[0][by3] == by4) {
                    this.a((byte)1, by, (byte)0, by3, bl);
                }
                by3 = (byte)(by3 + 1);
            }
            return;
        }
        this.aq.a(this.bP[0][by], this.bQ[0][by], by5, by4, !bl);
        by3 = 0;
        while (by3 < this.bH) {
            if (this.bP[1][by3] == by5 && this.bQ[1][by3] == by4) {
                this.a((byte)0, by, (byte)1, by3, bl);
            }
            by3 = (byte)(by3 + 1);
        }
    }

    public final boolean o(byte by, byte by2) {
        byte by3 = this.n(by, by2);
        if (by3 >= 0) {
            this.c(by, by3, false);
        } else if (by2 >= 0) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final boolean m(byte by) {
        byte by2 = this.l(by);
        if (by2 >= 0) {
            this.bU[0][by] = by2;
        }
        switch (this.bR[0][by]) {
            case 0: 
            case 1: 
            case 3: {
                return this.m(by, by2);
            }
            case 2: {
                return this.o(by, by2);
            }
        }
        return false;
    }

    public final boolean n(byte by) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.bQ[0][by] > 0 && (this.bI[this.bQ[0][by] - 1][this.bP[0][by]] & 0x80) != 0) {
            ++n;
            byArray[0] = 0;
        }
        if (this.bQ[0][by] < 6 && (this.bI[this.bQ[0][by] + 1][this.bP[0][by]] & 0x80) != 0) {
            byArray[n++] = 1;
        }
        if (this.bP[0][by] > 0 && (this.bI[this.bQ[0][by]][this.bP[0][by] - 1] & 0x80) != 0) {
            byArray[n++] = 2;
        }
        if (this.bP[0][by] < 15 && (this.bI[this.bQ[0][by]][this.bP[0][by] + 1] & 0x80) != 0) {
            byArray[n++] = 3;
        }
        if (n == 0) {
            return false;
        }
        this.b(by, byArray[this.s() % n], false);
        return true;
    }

    public final boolean o(byte by) {
        byte[] byArray = new byte[4];
        int n = 0;
        byte by2 = 5;
        if ((this.ca & 0xF0) == 48) {
            by2 = 7;
        }
        byte by3 = 1;
        while (by3 < by2) {
            if (this.bQ[0][by] < by3) break;
            if ((this.bI[this.bQ[0][by] - by3][this.bP[0][by]] & 0x80) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bQ[0][by] + by3 > 6) break;
            if ((this.bI[this.bQ[0][by] + by3][this.bP[0][by]] & 0x80) != 0) {
                byArray[n++] = 1;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bP[0][by] < by3) break;
            if ((this.bI[this.bQ[0][by]][this.bP[0][by] - by3] & 0x80) != 0) {
                byArray[n++] = 2;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bP[0][by] + by3 > 15) break;
            if ((this.bI[this.bQ[0][by]][this.bP[0][by] + by3] & 0x80) != 0) {
                byArray[n++] = 3;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        if (n == 0) {
            return false;
        }
        this.c(by, byArray[this.s() % n], false);
        return true;
    }

    public final boolean p(byte by) {
        switch (this.bR[0][by]) {
            case 0: 
            case 1: 
            case 3: {
                return this.n(by);
            }
            case 2: {
                return this.o(by);
            }
        }
        return false;
    }

    public final boolean q(byte by) {
        if (this.bP[0][by] > 0 && (this.bI[this.bQ[0][by]][this.bP[0][by] - 1] & 0xE0) != 0) {
            int n = 0;
            if (this.bQ[0][by] > 0 && (this.bI[this.bQ[0][by] - 1][this.bP[0][by]] & 0xE0) == 0) {
                ++n;
            }
            if (this.bQ[0][by] < 6 && (this.bI[this.bQ[0][by] + 1][this.bP[0][by]] & 0xE0) == 0) {
                n += 2;
            }
            boolean bl = false;
            if (n == 1) {
                this.c((byte)0, by);
            } else if (n == 2) {
                this.d((byte)0, by);
            } else if (n == 3) {
                if (this.s() % 2 == 0) {
                    this.c((byte)0, by);
                } else {
                    this.d((byte)0, by);
                }
            } else {
                return false;
            }
            return true;
        }
        if (this.bP[0][by] == 0) {
            byte[] byArray = this.bI[this.bQ[0][by]];
            byte by2 = this.bP[0][by];
            byArray[by2] = (byte)(byArray[by2] & 0x32);
            this.bP[0][by] = -1;
            this.bQ[0][by] = -1;
            this.bY[0][by] = false;
            return true;
        }
        this.e((byte)0, by);
        return true;
    }

    public final byte p(byte by, byte by2) {
        byte[] byArray = new byte[5];
        int n = 0;
        byte by3 = this.bP[0][by];
        byte by4 = this.bQ[0][by];
        byte by5 = this.bP[0][0];
        byte by6 = this.bQ[0][0];
        if (by2 == 0 && by6 == by4 - 1 && by5 == by3) {
            by2 = (byte)-1;
        } else if (by4 > 0 && (this.bI[by4 - 1][by3] & 0x80) != 0 && (by6 != by4 - 1 || by5 != by3)) {
            if (by2 == 0) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (by2 == 1 && by6 == by4 + 1 && by5 == by3) {
            by2 = (byte)-1;
        } else if (by4 < 6 && (this.bI[by4 + 1][by3] & 0x80) != 0 && (by6 != by4 + 1 || by5 != by3)) {
            if (by2 == 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (by2 == 2 && by6 == by4 && by5 == by3 - 1) {
            by2 = (byte)-1;
        } else if (by3 > 0 && (this.bI[by4][by3 - 1] & 0x80) != 0 && (by6 != by4 || by5 != by3 - 1)) {
            if (by2 == 2) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (by2 == 3 && by6 == by4 && by5 == by3 + 1) {
            by2 = (byte)-1;
        } else if (by3 < 15 && (this.bI[by4][by3 + 1] & 0x80) != 0 && (by5 != by3 + 1 || by6 != by4)) {
            if (by2 == 3) {
                return 3;
            }
            byArray[n++] = 3;
        }
        if (n > 0 && (by2 < 0 || this.s() % 10 >= 3)) {
            byte by7 = (byte)(this.s() % n);
            return byArray[by7];
        }
        if (by2 < 0) {
            return -2;
        }
        return -1;
    }

    public final boolean q(byte by, byte by2) {
        byte by3 = this.p(by, by2);
        if (by3 >= 0) {
            this.b(by, by3, false);
        } else if (by2 >= 0 && by3 != -2) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final byte r(byte by, byte by2) {
        byte[] byArray = new byte[4];
        int n = 0;
        byte by3 = this.bP[0][by];
        int n2 = this.bQ[0][by];
        byte by4 = this.bP[0][0];
        byte by5 = this.bQ[0][0];
        int n3 = 5;
        if (n2 > 0 && (this.bI[n2 - 1][by3] & 0x80) != 0 && by2 == 0) {
            if (by5 == n2 - 1 && by4 == by3) {
                by2 = (byte)-1;
            } else {
                return 0;
            }
        }
        if (n2 < 6 && (this.bI[n2 + 1][by3] & 0x80) != 0 && by2 == 1) {
            if (by5 == n2 + 1 && by4 == by3) {
                by2 = (byte)-1;
            } else {
                return 1;
            }
        }
        if (by3 > 0 && (this.bI[n2][by3 - 1] & 0x80) != 0 && by2 == 2) {
            if (by5 == n2 && by4 == by3 - 1) {
                by2 = (byte)-1;
            } else {
                return 2;
            }
        }
        if (by3 < 15 && (this.bI[n2][by3 + 1] & 0x80) != 0 && by2 == 3) {
            if (by5 == n2 && by4 == by3 + 1) {
                by2 = (byte)-1;
            } else {
                return 3;
            }
        }
        if ((this.ca & 0xF0) == 48) {
            n3 = 7;
        }
        int n4 = 1;
        while (n4 < n3) {
            if (n2 < n4 || by5 == n2 - n4 && by4 == by3) break;
            if ((this.bI[n2 - n4][by3] & 0x80) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            ++n4;
        }
        n4 = 1;
        while (n4 < n3) {
            if (n2 + n4 > 6 || by5 == n2 + n4 && by4 == by3) break;
            if ((this.bI[n2 + n4][by3] & 0x80) != 0) {
                byArray[n++] = 1;
                break;
            }
            ++n4;
        }
        n4 = 1;
        while (n4 < n3) {
            if (by3 < n4 || by5 == n2 && by4 == by3 - n4) break;
            if ((this.bI[n2][by3 - n4] & 0x80) != 0) {
                byArray[n++] = 2;
                break;
            }
            ++n4;
        }
        n4 = 1;
        while (n4 < n3) {
            if (by3 + n4 > 15 || by5 == n2 && by4 == by3 + n4) break;
            if ((this.bI[by5][by4 + n4] & 0x80) != 0) {
                byArray[n++] = 3;
                break;
            }
            ++n4;
        }
        if (n > 0 && (by2 == -1 || this.s() % 10 >= 2)) {
            return byArray[this.s() % n];
        }
        if (by2 == -1) {
            return -2;
        }
        return -1;
    }

    public final boolean s(byte by, byte by2) {
        by2 = this.l(by);
        byte by3 = this.r(by, by2);
        if (by3 >= 0) {
            this.c(by, by3, false);
        } else if (by2 >= 0 && by3 != -2) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final boolean r(byte by) {
        byte by2 = this.l(by);
        if (by2 >= 0) {
            this.bU[0][by] = by2;
        }
        switch (this.bR[0][by]) {
            case 0: 
            case 1: 
            case 3: {
                return this.q(by, by2);
            }
            case 2: {
                return this.s(by, by2);
            }
        }
        return false;
    }

    public final boolean s(byte by) {
        boolean bl = false;
        if (!this.bY[0][by]) {
            return false;
        }
        this.bV[0][by] = this.cg[this.bR[0][by]];
        switch (this.bV[0][by]) {
            case 0: {
                bl = this.m(by);
                break;
            }
            case 1: {
                bl = this.p(by);
                break;
            }
            case 2: {
                bl = this.q(by);
                break;
            }
            case 3: {
                bl = this.r(by);
            }
        }
        return bl;
    }

    public final byte t(byte by) {
        byte by2 = this.bP[0][0];
        byte by3 = this.bQ[0][0];
        byte by4 = this.bP[1][by];
        byte by5 = this.bQ[1][by];
        int n = -1;
        if (by2 > 0 && (this.bI[by3][by2 + 1] & 0xA0) == 0 && by3 - by5 != 0) {
            by2 = (byte)(by2 - 1);
        } else if (by3 - by5 <= 0) {
            if (by3 > 0 && (this.bI[by3 - 1][by2] & 0xA0) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < 6 && (this.bI[by3 + 1][by2] & 0xA0) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by2 < 15 && (this.bI[by3][by2 - 1] & 0xA0) == 0) {
                by2 = (byte)(by2 + 1);
            }
        } else if (by3 < 6 && (this.bI[by3 + 1][by2] & 0xA0) == 0) {
            by3 = (byte)(by3 + 1);
        } else if (by3 > 0 && (this.bI[by3 - 1][by2] & 0xA0) == 0) {
            by3 = (byte)(by3 - 1);
        } else if (by2 < 15 && (this.bI[by3][by2 - 1] & 0xA0) == 0) {
            by2 = (byte)(by2 + 1);
        }
        if (by4 < 15 && by2 - by4 > 0 && (this.bI[by5][by4 + 1] & 0xA0) == 0) {
            if (by2 == by4 + 1 && by3 == by5) {
                n = 3;
            } else {
                return 3;
            }
        }
        if (by4 > 0 && by2 - by4 < 0 && (this.bI[by5][by4 - 1] & 0xA0) == 0) {
            if (by2 == by4 - 1 && by3 == by5) {
                n = 2;
            } else {
                return 2;
            }
        }
        if (by5 < 6 && by3 - by5 > 0 && (this.bI[by5 + 1][by4] & 0xA0) == 0) {
            return 1;
        }
        if (by5 > 0 && by3 - by5 < 0 && (this.bI[by5 - 1][by4] & 0xA0) == 0) {
            return 0;
        }
        if (by2 - by4 == 0) {
            if (by4 < 15 && by4 > 0 && (this.bI[by5][by4 + 1] & 0xA0) == 0 && this.bI[by5][by4 - 1] == 0) {
                return (byte)(this.s() % 2 + 2);
            }
            if (by4 < 15 && (this.bI[by5][by4 + 1] & 0xA0) == 0) {
                return 3;
            }
            if (by4 > 0 && (this.bI[by5][by4 - 1] & 0xA0) == 0) {
                return 2;
            }
        }
        if (by3 - by5 == 0) {
            if (by5 > 0 && by5 < 6 && (this.bI[by5 + 1][by4] & 0xA0) == 0 && (this.bI[by5 - 1][by4] & 0xA0) == 0) {
                return (byte)(this.s() % 2);
            }
            if (by5 < 6 && (this.bI[by5 + 1][by4] & 0xA0) == 0) {
                return 1;
            }
            if (by5 > 0 && (this.bI[by5 - 1][by4] & 0xA0) == 0) {
                return 0;
            }
        }
        return (byte)n;
    }

    public final byte t(byte by, byte by2) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.bQ[1][by] > 0 && (this.bI[this.bQ[1][by] - 1][this.bP[1][by]] & 0x40) != 0) {
            if (by2 == 0 || this.bR[1][by] == 0 && this.bP[0][0] == this.bP[1][by] && this.bQ[0][0] == this.bQ[1][by] - 1) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (this.bQ[1][by] < 6 && (this.bI[this.bQ[1][by] + 1][this.bP[1][by]] & 0x40) != 0) {
            if (by2 == 1 || this.bR[1][by] == 0 && this.bP[0][0] == this.bP[1][by] && this.bQ[0][0] == this.bQ[1][by] + 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (this.bP[1][by] > 0 && (this.bI[this.bQ[1][by]][this.bP[1][by] - 1] & 0x40) != 0) {
            if (by2 == 2 || this.bR[1][by] == 0 && this.bP[0][0] == this.bP[1][by] - 1 && this.bQ[0][0] == this.bQ[1][by]) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (this.bP[1][by] < 15 && (this.bI[this.bQ[1][by]][this.bP[1][by] + 1] & 0x40) != 0) {
            if (by2 == 3 || this.bR[1][by] == 0 && this.bP[0][0] == this.bP[1][by] + 1 && this.bQ[0][0] == this.bQ[1][by]) {
                return 3;
            }
            byArray[n++] = 3;
        }
        if (n > 0 && (by2 == -1 || this.s() % 10 >= 3)) {
            return byArray[this.s() % n];
        }
        return -1;
    }

    public final boolean u(byte by, byte by2) {
        byte by3 = this.t(by, by2);
        if (by3 >= 0) {
            this.b(by, by3, true);
        } else if (by2 >= 0) {
            this.a((byte)1, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final byte v(byte by, byte by2) {
        byte[] byArray = new byte[4];
        int n = 0;
        int n2 = 5;
        if (this.bQ[1][by] > 0 && (this.bI[this.bQ[1][by] - 1][this.bP[1][by]] & 0x40) != 0 && by2 == 0) {
            return 0;
        }
        if (this.bQ[1][by] < 6 && (this.bI[this.bQ[1][by] + 1][this.bP[1][by]] & 0x40) != 0 && by2 == 1) {
            return 1;
        }
        if (this.bP[1][by] > 0 && (this.bI[this.bQ[1][by]][this.bP[1][by] - 1] & 0x40) != 0 && by2 == 2) {
            return 2;
        }
        if (this.bP[1][by] < 15 && (this.bI[this.bQ[1][by]][this.bP[1][by] + 1] & 0x40) != 0 && by2 == 3) {
            return 3;
        }
        if ((this.cb & 0xF0) == 48) {
            n2 = 7;
        }
        int n3 = 1;
        while (n3 < n2) {
            if (this.bQ[1][by] < n3) break;
            if ((this.bI[this.bQ[1][by] - n3][this.bP[1][by]] & 0x40) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bQ[1][by] + n3 > 6) break;
            if ((this.bI[this.bQ[1][by] + n3][this.bP[1][by]] & 0x40) != 0) {
                byArray[n++] = 1;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bP[1][by] < n3) break;
            if ((this.bI[this.bQ[1][by]][this.bP[1][by] - n3] & 0x40) != 0) {
                byArray[n++] = 2;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bP[1][by] + n3 > 15) break;
            if ((this.bI[this.bQ[1][by]][this.bP[1][by] + n3] & 0x40) != 0) {
                byArray[n++] = 3;
                break;
            }
            ++n3;
        }
        if (n > 0 && (by2 == -1 || this.s() % 10 >= 2)) {
            return byArray[this.s() % n];
        }
        return -1;
    }

    public final boolean w(byte by, byte by2) {
        byte by3 = this.v(by, by2);
        if (by3 >= 0) {
            this.c(by, by3, true);
        } else if (by2 >= 0) {
            this.a((byte)1, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final boolean u(byte by) {
        if (this.bY[1][by]) {
            byte by2 = this.t(by);
            if (by2 >= 0) {
                this.bU[1][by] = by2;
            }
            switch (this.bR[1][by]) {
                case 0: 
                case 1: 
                case 3: {
                    return this.u(by, by2);
                }
                case 2: {
                    return this.w(by, by2);
                }
            }
        }
        return false;
    }

    public final boolean v(byte by) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.bQ[1][by] > 0 && (this.bI[this.bQ[1][by] - 1][this.bP[1][by]] & 0x40) != 0) {
            ++n;
            byArray[0] = 0;
        }
        if (this.bQ[1][by] < 6 && (this.bI[this.bQ[1][by] + 1][this.bP[1][by]] & 0x40) != 0) {
            byArray[n++] = 1;
        }
        if (this.bP[1][by] > 0 && (this.bI[this.bQ[1][by]][this.bP[1][by] - 1] & 0x40) != 0) {
            byArray[n++] = 2;
        }
        if (this.bP[1][by] < 15 && (this.bI[this.bQ[1][by]][this.bP[1][by] + 1] & 0x40) != 0) {
            byArray[n++] = 3;
        }
        if (n == 0) {
            return false;
        }
        this.b(by, byArray[this.s() % n], true);
        return true;
    }

    public final boolean w(byte by) {
        byte[] byArray = new byte[4];
        int n = 0;
        byte by2 = 5;
        if ((this.cb & 0xF0) == 48) {
            by2 = 7;
        }
        byte by3 = 1;
        while (by3 < by2) {
            if (this.bQ[1][by] < by3) break;
            if ((this.bI[this.bQ[1][by] - by3][this.bP[1][by]] & 0x40) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bQ[1][by] + by3 > 6) break;
            if ((this.bI[this.bQ[1][by] + by3][this.bP[1][by]] & 0x40) != 0) {
                byArray[n++] = 1;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bP[1][by] < by3) break;
            if ((this.bI[this.bQ[1][by]][this.bP[1][by] - by3] & 0x40) != 0) {
                byArray[n++] = 2;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bP[1][by] + by3 > 15) break;
            if ((this.bI[this.bQ[1][by]][this.bP[1][by] + by3] & 0x40) != 0) {
                byArray[n++] = 3;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        if (n == 0) {
            return false;
        }
        this.c(by, byArray[this.s() % n], true);
        return true;
    }

    public final boolean x(byte by) {
        if (this.bY[1][by]) {
            switch (this.bR[1][by]) {
                case 0: 
                case 1: 
                case 3: {
                    return this.v(by);
                }
                case 2: {
                    return this.w(by);
                }
            }
        }
        return false;
    }

    public final boolean y(byte by) {
        if (this.bP[1][by] < 15 && (this.bI[this.bQ[1][by]][this.bP[1][by] + 1] & 0xE0) != 0) {
            return false;
        }
        if (this.bP[1][by] == 15) {
            byte[] byArray = this.bI[this.bQ[1][by]];
            byte by2 = this.bP[1][by];
            byArray[by2] = (byte)(byArray[by2] & 0x32);
            this.bP[1][by] = -1;
            this.bQ[1][by] = -1;
            this.bY[1][by] = false;
            return true;
        }
        this.f((byte)1, by);
        return true;
    }

    public final boolean z(byte by) {
        boolean bl = false;
        if (!this.bY[1][by]) {
            return false;
        }
        switch (this.bV[1][by]) {
            case 0: {
                bl = this.u(by);
                break;
            }
            case 1: {
                bl = this.x(by);
                break;
            }
            case 2: {
                bl = this.y(by);
            }
        }
        return bl;
    }

    public final byte R() {
        int n;
        int n2;
        int n3 = 0;
        int n4 = this.bS[this.co][this.cp] * this.bS[this.co][this.cp] / this.bT[this.cq][this.cr];
        n3 = this.bS[this.co][this.cp] >= this.bT[this.cq][this.cr] ? (n4 < (n2 = this.bS[this.co][this.cp] * 3 / 2) ? n4 : n2) : (n4 > (n = this.bS[this.co][this.cp] * 2 / 3) ? n4 : n);
        n3 = this.a((short)n3) * 5;
        if ((this.ca & 0xF0) == 64) {
            n3 *= 1;
        } else if ((this.ca & 0xF0) == 80 && this.bR[this.co][this.cp] == 2) {
            n3 *= 1;
        }
        n3 = n3 * this.bW[this.co][this.cp] / 100;
        n3 += this.ar.nextInt() % (n3 / 10 + 1);
        if (n3 > 110) {
            n3 = 110;
        }
        return (byte)n3;
    }

    public final void c(boolean bl) {
        byte by = this.R();
        if (this.bR[this.cq][this.cr] == 0) {
            if (this.bR[this.co][this.cp] == 0) {
                this.as = 1;
                return;
            }
            if (bl) {
                short s = this.az;
                this.M[s] = (byte)(this.M[s] - (by / 4 + 1));
                if (this.M[this.az] <= 0) {
                    this.bY[this.cq][this.cr] = false;
                    this.M[this.az] = 0;
                    return;
                }
            } else {
                short s = this.aA;
                this.M[s] = (byte)(this.M[s] - (by / 4 + 1));
                if (this.M[this.aA] <= 0) {
                    this.bY[this.cq][this.cr] = false;
                    this.M[this.aA] = 0;
                    return;
                }
            }
        } else {
            byte[] byArray = this.bW[this.cq];
            byte by2 = this.cr;
            byArray[by2] = (byte)(byArray[by2] - by);
            if (this.bW[this.cq][this.cr] <= 0) {
                this.bY[this.cq][this.cr] = false;
                this.bW[this.cq][this.cr] = 0;
                byte[] byArray2 = this.bI[this.bQ[this.cq][this.cr]];
                byte by3 = this.bP[this.cq][this.cr];
                byArray2[by3] = (byte)(byArray2[by3] & 0x32);
                this.bQ[this.cq][this.cr] = -1;
                this.bP[this.cq][this.cr] = -1;
            }
        }
    }

    public final void S() {
        byte by = this.bP[0][0];
        byte by2 = this.bQ[0][0];
        int n = by + 1;
        while (n < by + 4) {
            if (n > 15) {
                return;
            }
            int n2 = by2 - 1;
            while (n2 < by2 + 2) {
                if (n2 >= 0) {
                    if (n2 > 6) break;
                    int n3 = 0;
                    while (n3 < this.bH) {
                        if (this.bP[1][n3] == n && this.bQ[1][n3] == n2) {
                            if (n3 == 0) {
                                short s = this.aA;
                                this.M[s] = (byte)(this.M[s] - (this.s() % 21 + 30));
                                if (this.M[this.aA] <= 0) {
                                    this.M[this.aA] = 0;
                                    this.bY[1][0] = false;
                                }
                            } else if (this.bY[1][n3]) {
                                byte[] byArray = this.bW[1];
                                int n4 = n3;
                                byArray[n4] = (byte)(byArray[n4] - (this.s() % 21 + 60));
                                if (this.bW[1][n3] <= 0) {
                                    byte[] byArray2 = this.bI[this.bQ[1][n3]];
                                    byte by3 = this.bP[1][n3];
                                    byArray2[by3] = (byte)(byArray2[by3] & 0x3F);
                                    this.bW[1][n3] = 0;
                                    this.bY[1][n3] = false;
                                    this.bP[1][n3] = -1;
                                    this.bQ[1][n3] = -1;
                                }
                            }
                        }
                        ++n3;
                    }
                }
                ++n2;
            }
            ++n;
        }
    }

    public final void T() {
        byte by = this.bP[1][0];
        byte by2 = this.bQ[1][0];
        int n = by - 1;
        while (n > by - 4) {
            if (n < 0) {
                return;
            }
            int n2 = by2 - 1;
            while (n2 < by2 + 2) {
                if (n2 >= 0) {
                    if (n2 > 6) break;
                    int n3 = 0;
                    while (n3 < this.bG) {
                        if (this.bP[0][n3] == n && this.bQ[0][n3] == n2) {
                            if (n3 == 0) {
                                short s = this.az;
                                this.M[s] = (byte)(this.M[s] - (this.s() % 21 + 30));
                                if (this.M[this.az] <= 0) {
                                    this.M[this.az] = 0;
                                    this.bY[0][0] = false;
                                }
                            } else if (this.bY[0][n3]) {
                                byte[] byArray = this.bW[0];
                                int n4 = n3;
                                byArray[n4] = (byte)(byArray[n4] - (this.s() % 21 + 60));
                                if (this.bW[0][n3] <= 0) {
                                    byte[] byArray2 = this.bI[this.bQ[0][n3]];
                                    byte by3 = this.bP[0][n3];
                                    byArray2[by3] = (byte)(byArray2[by3] & 0x3F);
                                    this.bW[0][n3] = 0;
                                    this.bY[0][n3] = false;
                                    this.bP[0][n3] = -1;
                                    this.bQ[0][n3] = -1;
                                }
                            }
                        }
                        ++n3;
                    }
                }
                ++n2;
            }
            --n;
        }
    }

    public final boolean U() {
        int n = 0;
        if (this.M[this.aA] > 40 || this.Q[this.aA] > 99) {
            return false;
        }
        if (this.bQ[1][0] > 0 && (this.bk[this.bQ[1][0] - 1][this.bQ[1][0]] & 0x40) != 0) {
            n = 1;
        }
        if (this.bQ[1][0] < 6 && (this.bk[this.bQ[1][0] + 1][this.bQ[1][0]] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (this.bQ[1][0] > 0 && (this.bk[this.bQ[1][0]][this.bQ[1][0] - 1] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (this.bQ[1][0] < 15 && (this.bk[this.bQ[1][0]][this.bQ[1][0] + 1] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (n == 4) {
            n = 95;
        } else if (n == 3) {
            n = 80;
        } else if (n == 2) {
            n = 50;
        } else {
            return false;
        }
        if (this.N[this.aA] > 95 || this.Q[this.aA] > 95) {
            n = (byte)(n - 30);
        } else if (this.N[this.aA] >= 90 || this.Q[this.aA] >= 90) {
            n = (byte)(n - 20);
        } else if (this.N[this.aA] >= 80 || this.Q[this.aA] >= 80) {
            n = (byte)(n - 10);
        }
        return this.s() % 100 < n;
    }

    public final void V() {
        if (!this.bY[0][0]) {
            if (this.M[this.az] <= 0) {
                this.bZ = (byte)2;
                return;
            }
            this.bZ = (byte)6;
            if (this.s() % 10 > 1) {
                this.cc = (byte)(this.s() % 12 + 2);
                if (this.cc >= this.M[this.az]) {
                    this.cc = this.M[this.az];
                    this.bZ = (byte)2;
                }
                short s = this.az;
                this.M[s] = (byte)(this.M[s] - this.cc);
            }
            return;
        }
        if (!this.bY[1][0]) {
            if (this.M[this.aA] <= 0) {
                this.bZ = 1;
                return;
            }
            this.bZ = (byte)5;
            if (this.s() % 10 > 1) {
                this.cc = (byte)(this.s() % 12 + 2);
                if (this.cc >= this.M[this.aA]) {
                    this.cc = this.M[this.aA];
                    this.bZ = 1;
                }
                short s = this.aA;
                this.M[s] = (byte)(this.M[s] - this.cc);
            }
        }
    }

    public final void W() {
        short s = this.K[this.az];
        short s2 = this.K[this.aA];
        this.K[this.az] = 0;
        byte by = 1;
        while (by < this.bG) {
            short s3 = this.az;
            this.K[s3] = (short)(this.K[s3] + this.bW[0][by]);
            by = (byte)(by + 1);
        }
        this.K[this.aA] = 0;
        by = 1;
        while (by < this.bH) {
            short s4 = this.aA;
            this.K[s4] = (short)(this.K[s4] + this.bW[1][by]);
            by = (byte)(by + 1);
        }
        short s5 = this.az;
        this.R[s5] = (short)(this.R[s5] + (s2 - this.K[this.aA]));
        short s6 = this.aA;
        this.R[s6] = (short)(this.R[s6] + (s - this.K[this.az]));
        this.f(this.az);
        this.f(this.aA);
    }

    public final void X() {
        this.W();
        if (this.ci) {
            this.as = (byte)2;
            return;
        }
        this.as = (byte)3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void Y() {
        if (this.as == 0) {
            this.aq.aa = 0;
            this.cB = 0;
            this.cC = 0;
            this.cE = 0;
            this.cF = 0;
            this.cG = 0;
            this.cz = this.bG > 1 ? (byte)1 : 0;
            this.cA = this.bH > 1 ? (byte)1 : 0;
            this.cH = 0;
            this.h(this.cC);
        } else {
            this.as = 0;
        }
        long l = System.currentTimeMillis();
        block5: while (this.as <= 0) {
            long l2;
            if (this.aq.a != 0) {
                this.aq.h();
                this.aq.a = 0;
            }
            if ((l2 = System.currentTimeMillis() - l) < 50L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(50L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
            if (this.cf == 2 || this.bZ > 0) continue;
            if (this.ca != 0) {
                if (this.cF == 0) {
                    this.cF = this.cC;
                    this.cG = this.bG;
                } else if (this.cC > this.cF && this.bG >= this.cG) {
                    this.cF = this.cC;
                    this.ca = (byte)(this.ca - 1);
                    if ((this.ca & 0xF) == 0) {
                        this.ca = 0;
                        this.cF = 0;
                    }
                }
            }
            this.cH = (byte)(this.cH + 1);
            if (this.aq.aj) continue;
            if (this.cn > 0) {
                this.bX[this.cq][this.cr] = true;
                this.cn = (byte)(this.cn - 1);
                if (this.cn != 0) continue;
                this.c(this.cs);
                this.cH = 0;
                this.V();
                continue;
            }
            if (this.aq.ak || this.aq.al) {
                if (this.cl > 0 && (this.cl = (byte)(this.cl - 1)) == 0) {
                    this.S();
                    this.aq.ak = false;
                    this.cH = 0;
                }
                if (this.cm <= 0 || (this.cm = (byte)(this.cm - 1)) != 0) continue;
                this.T();
                this.aq.al = false;
                continue;
            }
            if (this.cH < 10) continue;
            this.V();
            if (this.bZ > 0) continue;
            this.cH = 0;
            if (this.cE > 1) {
                ++this.cC;
                this.cz = this.bG > 1 ? (byte)1 : 0;
                this.cA = this.bH > 1 ? (byte)1 : 0;
                this.cE = 0;
            }
            if (this.cE == 0) {
                this.cD = false;
                do {
                    this.cD = this.s(this.cz);
                    if (this.cz == 0 && this.cB >= 1) {
                        this.cB = 0;
                        this.cE = 1;
                        if (this.U()) {
                            this.bZ = (byte)3;
                            continue;
                        }
                        this.h(this.cC);
                        if (this.cb == 0) continue block5;
                        if ((this.cb & 0xF) == 0) {
                            this.cb = 0;
                            continue block5;
                        }
                        this.cb = (byte)(this.cb - 1);
                        continue block5;
                    }
                    if (!this.bY[0][this.cz] || this.bR[0][this.cz] >= 2 || this.cB >= 1) {
                        this.cz = (byte)(this.cz + 1);
                        this.cB = 0;
                    } else {
                        this.cB = (byte)(this.cB + 1);
                    }
                    if (this.cz != this.bG) continue;
                    this.cz = 0;
                } while (!this.cD);
                continue;
            }
            if (this.cE != 1) continue;
            this.cD = false;
            do {
                this.cD = this.cA == 0 && (this.ca & 0xF0) == 16 ? this.x((byte)0) : this.z(this.cA);
                if (this.cA == 0 && this.cB >= 1) {
                    this.cB = 0;
                    this.cE = (byte)2;
                    continue block5;
                }
                if (!this.bY[1][this.cA] || this.bR[1][this.cA] >= 2 || this.cB >= 1) {
                    this.cA = (byte)(this.cA + 1);
                    this.cB = 0;
                } else {
                    this.cB = (byte)(this.cB + 1);
                }
                if (this.cA != this.bH) continue;
                this.cA = 0;
            } while (!this.cD);
        }
        return;
    }

    public final boolean b(short s, short s2) {
        int n = (this.N[s] - this.N[s2]) / 20;
        return this.ar.nextInt() % 10 < 5 + n;
    }

    public final byte c(short s, short s2) {
        if (this.M[s] < 20 && this.s() % 2 == 0) {
            return 4;
        }
        if (this.M[s] < this.L[s] / 2 && this.M[s2] - this.M[s] > 10 && this.s() % 10 < 3) {
            return 2;
        }
        if (this.M[s] < this.M[s2]) {
            if (this.s() % 10 < 6) {
                return 1;
            }
            return 0;
        }
        if (this.s() % 10 < 6) {
            return 0;
        }
        return 1;
    }

    public final byte d(short s, short s2) {
        return this.c(s, s2);
    }

    public final void Z() {
        int n = this.S[this.az] / 8;
        int n2 = this.S[this.aA] / 8;
        if ((n -= n2) == 0) {
            this.cy = 0;
            return;
        }
        if (n == -1 || n == 1) {
            this.cy = (byte)-1;
            return;
        }
        this.cy = 1;
    }

    public final void aa() {
        this.ct = (short)(this.N[this.az] + this.N[this.az] * this.ag[this.S[this.az]] / 100);
        this.cu = (short)(this.N[this.az] + this.N[this.az] * this.ag[this.T[this.az]] / 100);
        this.cv = (short)(this.N[this.aA] + this.N[this.aA] * this.ag[this.S[this.aA]] / 100);
        this.cw = (short)(this.N[this.aA] + this.N[this.aA] * this.ag[this.T[this.aA]] / 100);
        this.Z();
        this.cx = false;
        if (this.aA == 61) {
            if (this.I[this.az] <= 3 && this.M[this.aA] == this.L[this.aA]) {
                this.cx = true;
            }
            return;
        }
        if (this.aA == 4 || this.aA == 24 || this.aA == 30 || this.aA == 40 || this.aA == 69) {
            if (this.M[this.aA] == this.L[this.aA]) {
                this.cx = true;
            }
            return;
        }
        if (this.aA == 1) {
            this.cx = true;
        }
    }

    public final void d(boolean bl) {
        if (bl) {
            byte by = 0;
            while (by < this.bH) {
                this.bV[1][by] = 2;
                by = (byte)(by + 1);
            }
            return;
        }
        this.bV[0][0] = 2;
        int n = 0;
        while (n < 4) {
            this.cg[n] = 2;
            n = (byte)(n + 1);
        }
    }

    public final int ab() {
        if (this.cx) {
            if (this.aA == 61) {
                this.S[this.az] = 6;
                return 0;
            }
            if (this.aA == 4) {
                this.T[this.az] = 31;
                return 1;
            }
            if (this.aA == 1) {
                if (this.M[this.az] == this.L[this.az]) {
                    this.S[this.az] = 7;
                    return 2;
                }
            } else if (this.aA == 24) {
                if (this.M[this.az] * 5 <= this.L[this.az]) {
                    this.S[this.az] = 5;
                    return 3;
                }
            } else if (this.bM == 1) {
                this.S[this.az] = 14;
                return 4;
            }
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void ac() {
        this.aq.a((byte)22);
        long l = System.currentTimeMillis();
        while (this.as <= 0) {
            long l2;
            if (this.aq.a != 0) {
                this.aq.g();
                this.aq.a = 0;
            }
            if ((l2 = System.currentTimeMillis() - l) < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
        return;
    }

    public final void ad() {
        this.as = 0;
        this.O();
        this.P();
        this.as = 0;
        while (true) {
            this.Y();
            if (this.as == 1) {
                this.as = 0;
                this.aa();
                this.ac();
                this.aq.a((byte)21);
                this.aq.af = (byte)2;
                continue;
            }
            if (this.as == 2) break;
        }
        this.X();
    }

    public final boolean a(short s, boolean bl) {
        if (bl) {
            byte by;
            if (s == this.be[0]) {
                this.as = (byte)2;
                this.bC = true;
                this.bs[0] = 3;
                byte[] byArray = this.bk[this.bp[0]];
                byte by2 = this.bo[0];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                if (this.be[0] == this.aX) {
                    this.bE = (byte)2;
                }
                by = 0;
                while (by < this.bh) {
                    if (this.bt[by] == 2) {
                        this.bt[by] = 0;
                    }
                    by = (byte)(by + 1);
                }
                this.bA = 1;
                by = 1;
                while (by < this.bg) {
                    if (this.bs[by] == 0 || this.bs[by] > 3) break;
                    by = (byte)(by + 1);
                }
                if (by < this.bg) {
                    this.j(this.aZ);
                    this.bA = by;
                    this.az = this.be[this.bA];
                    this.aq.c();
                    this.aq.Q = true;
                    this.aq.J = false;
                    this.aq.K = true;
                } else {
                    if (this.bn) {
                        this.B();
                    }
                    this.C();
                    return false;
                }
            }
            by = 1;
            while (by < this.bg) {
                if (this.be[by] == s) {
                    this.bs[by] = 3;
                    byte[] byArray = this.bk[this.bp[by]];
                    byte by3 = this.bo[by];
                    byArray[by3] = (byte)(byArray[by3] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        } else {
            if (s == this.bf[0]) {
                this.as = (byte)2;
                this.bt[0] = 3;
                byte[] byArray = this.bk[this.br[0]];
                byte by = this.bq[0];
                byArray[by] = (byte)(byArray[by] & 0x3F);
                byte by4 = 1;
                while (by4 < this.bg) {
                    if (this.bs[by4] == 2) {
                        this.bs[by4] = 0;
                    }
                    by4 = (byte)(by4 + 1);
                }
                this.aq.W = (byte)5;
                return true;
            }
            byte by = 1;
            while (by < this.bh) {
                if (this.bf[by] == s) {
                    this.bt[by] = 3;
                    byte[] byArray = this.bk[this.br[by]];
                    byte by5 = this.bq[by];
                    byArray[by5] = (byte)(byArray[by5] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        }
        return true;
    }

    public final boolean b(short s, boolean bl) {
        if (bl) {
            byte by;
            if (s == this.be[0]) {
                this.as = (byte)2;
                this.bC = true;
                this.bs[0] = 2;
                byte[] byArray = this.bk[this.bp[0]];
                byte by2 = this.bo[0];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                by = 0;
                while (by < this.bh) {
                    if (this.bt[by] == 2) {
                        this.bt[by] = 0;
                    }
                    by = (byte)(by + 1);
                }
                this.bA = 1;
                by = 1;
                while (by < this.bg) {
                    if (this.bs[by] == 0 || this.bs[by] > 3) break;
                    by = (byte)(by + 1);
                }
                if (by < this.bg) {
                    this.j(this.aZ);
                    this.bA = by;
                    this.az = this.be[this.bA];
                    this.aq.c();
                    this.aq.Q = true;
                    this.aq.J = false;
                    this.aq.K = true;
                } else {
                    if (this.bn) {
                        this.B();
                    }
                    this.C();
                    return false;
                }
            }
            by = 1;
            while (by < this.bg) {
                if (this.be[by] == s) {
                    this.bs[by] = 2;
                    byte[] byArray = this.bk[this.bp[by]];
                    byte by3 = this.bo[by];
                    byArray[by3] = (byte)(byArray[by3] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        } else {
            if (s == this.bf[0]) {
                this.as = (byte)2;
                this.bt[0] = 2;
                byte[] byArray = this.bk[this.br[0]];
                byte by = this.bq[0];
                byArray[by] = (byte)(byArray[by] & 0x3F);
                byte by4 = 1;
                while (by4 < this.bg) {
                    if (this.bs[by4] == 2) {
                        this.bs[by4] = 0;
                    }
                    by4 = (byte)(by4 + 1);
                }
                this.aq.W = (byte)5;
                return true;
            }
            byte by = 1;
            while (by < this.bh) {
                if (this.bf[by] == s) {
                    this.bt[by] = 2;
                    byte[] byArray = this.bk[this.br[by]];
                    byte by5 = this.bq[by];
                    byArray[by5] = (byte)(byArray[by5] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        }
        return true;
    }

    public final boolean ae() {
        switch (this.bZ) {
            case 1: {
                return this.a(this.aA, false);
            }
            case 2: {
                return this.a(this.az, true);
            }
            case 3: {
                return this.b(this.aA, false);
            }
            case 4: {
                return this.b(this.az, true);
            }
        }
        return true;
    }

    public final void af() {
        this.aq.aA = true;
        this.aq.a((byte)17);
        this.as = 0;
        this.al();
    }

    public final void ag() {
        this.aq.aA = true;
        this.aq.a((byte)16);
        this.as = 0;
        this.al();
    }

    public final void A(byte by) {
        this.aq.a((byte)20);
        this.aq.W = by;
        this.as = 0;
        this.D();
    }

    public final void ah() {
        this.az = 0;
        this.z();
        this.D();
        this.as = 0;
        while (true) {
            this.N();
            if (this.as == 2 || this.as == 3) {
                byte by = this.as;
                this.ad();
                this.aq.a((byte)20);
                this.aq.c();
                this.as = by;
                if (this.ae()) continue;
                this.as = (byte)4;
                continue;
            }
            if (this.as == 5) {
                this.as = 0;
                this.af();
                switch (this.as) {
                    case 1: {
                        this.A((byte)11);
                        break;
                    }
                    case 2: {
                        this.A((byte)10);
                        break;
                    }
                    case 3: {
                        this.A((byte)12);
                        break;
                    }
                    case 4: {
                        this.A((byte)13);
                    }
                }
                this.as = (byte)5;
                this.aq.c();
                continue;
            }
            if (this.as == 6) {
                this.ag();
                switch (this.as) {
                    case 1: {
                        this.A((byte)14);
                        break;
                    }
                    case 2: {
                        this.A((byte)10);
                    }
                }
                this.as = (byte)6;
                this.aq.c();
                continue;
            }
            if (this.as > 0) break;
        }
    }

    public final void ai() {
        this.as = 0;
        this.aY = this.b[this.au];
        this.aZ = this.ax;
        this.aX = this.j[this.aZ];
        this.bf = this.aB;
        this.be = this.B[this.aZ];
        this.bh = this.aC;
        this.bg = this.s[this.aZ];
        this.ba = this.m[this.aZ];
        this.bb = this.n[this.aZ];
        this.bn = true;
        byte by = 0;
        while (by < this.bg) {
            this.bs[by] = 0;
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < this.bh) {
            this.bt[by] = 0;
            by = (byte)(by + 1);
        }
        this.bu = 1;
        this.bv = 1;
        this.bw = 1;
        this.bx = 1;
        this.by = 0;
        this.bi = 0;
        this.bj = 0;
        this.bz = true;
        this.bA = (byte)-1;
        this.bi = this.a(this.be, this.bg, this.bs, this.bi);
        this.y();
        this.aq.b();
        this.aq.a();
        this.aq.j();
    }

    public final void aj() {
        this.ai();
        this.az = 0;
        this.D();
        this.as = 0;
        while (true) {
            this.N();
            if (this.as == 2 || this.as == 3) {
                byte by = this.as;
                this.ad();
                this.aq.a((byte)20);
                this.aq.c();
                this.as = by;
                if (this.ae()) continue;
                this.as = (byte)4;
                continue;
            }
            if (this.as == 5) {
                this.as = 0;
                this.af();
                switch (this.as) {
                    case 1: {
                        this.A((byte)11);
                        break;
                    }
                    case 2: {
                        this.A((byte)10);
                        break;
                    }
                    case 3: {
                        this.A((byte)12);
                        break;
                    }
                    case 4: {
                        this.A((byte)13);
                    }
                }
                this.as = (byte)5;
                this.aq.c();
                continue;
            }
            if (this.as == 6) {
                this.ag();
                switch (this.as) {
                    case 1: {
                        this.A((byte)14);
                        break;
                    }
                    case 2: {
                        this.A((byte)10);
                    }
                }
                this.as = (byte)6;
                this.aq.c();
                continue;
            }
            if (this.as > 0) break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void ak() {
        if (this.as == 0) {
            this.aI = this.e(this.at);
            this.bM = 0;
            this.bN = 0;
            this.u();
            this.aq.m();
            this.aq.repaint();
            this.aq.f();
        } else if (this.as == 2) {
            this.aF();
            this.as = 0;
        } else if (this.as == 4) {
            this.as = 0;
            this.aG();
        }
        long l = System.currentTimeMillis();
        while (true) {
            if (this.aq.a != 0) {
                this.aq.f();
                this.aq.a = 0;
            }
            if (this.as > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void a(byte by, byte by2, short s) {
        int n = 0;
        while (n < this.d[by]) {
            this.j[this.e[by][n]] = s;
            ++n;
        }
        this.c(s, by2);
        this.Q[s] = 100;
        this.b[by] = s;
        this.c[by] = this.H[s];
    }

    public final void B(byte by) {
        byte by2 = -1;
        short s = 0;
        byte by3 = 0;
        int n = 0;
        while (n < this.f[by]) {
            int n2 = 0;
            while (n2 < this.d[by]) {
                byte by4 = this.e[by][n2];
                int n3 = 0;
                while (n3 < this.s[by4]) {
                    short s2 = this.B[by4][n3];
                    if (this.Q[s2] > by2) {
                        by2 = this.Q[s2];
                        s = s2;
                        by3 = by4;
                    }
                    if (s2 == this.g[by][n]) {
                        this.a(by, by4, s2);
                        return;
                    }
                    ++n3;
                }
                ++n2;
            }
            ++n;
        }
        this.a(by, by3, s);
    }

    public final void C(byte by) {
        this.B(by);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void al() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.aq.a != 0) {
                this.aq.f();
                this.aq.a = 0;
            }
            if (this.as > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void am() {
        this.aq.a((byte)4);
        this.aq.m();
        this.al();
    }

    public final void an() {
        this.aq.a((byte)7);
        this.al();
    }

    public final void ao() {
        this.bM = (byte)22;
        this.aw = this.e[this.at][0];
        while (true) {
            this.am();
            this.as = 0;
            this.an();
            if (this.as > 1) break;
            this.as = 0;
        }
        this.a(this.at, this.aw, this.az);
        this.as = 0;
        this.aq.a((byte)4);
    }

    public final void a(byte by, short s, short s2) {
        this.aq.a(by, s, s2);
        this.al();
        this.as = 0;
    }

    public final void ap() {
        this.aq.a((byte)4);
        this.as = 0;
        if (this.bE == 1) {
            short s = this.b[this.av];
            this.C(this.av);
            this.a((byte)6, s, this.b[this.av]);
            this.bE = 0;
        } else if (this.bE == 2) {
            short s = this.b[this.at];
            this.a((byte)2, s, (short)0);
            this.ao();
            this.a((byte)6, s, this.b[this.at]);
            this.bE = 0;
        } else if (this.bE == 3) {
            this.aI = 1;
            this.bE = 0;
            this.a((byte)5, this.b[this.at], (short)0);
            this.as = (byte)99;
        } else if (this.bE == 4) {
            this.a((byte)5, this.b[this.av], (short)0);
        }
        this.as = (byte)4;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void aq() {
        byte by = this.aG;
        while (by < 7) {
            if (this.as == 0) {
                this.t();
            }
            if (this.au > 0) {
                if (this.au == this.at) {
                    this.ak();
                    if (this.as == 3) {
                        this.as = 0;
                    }
                    if (this.as == 4) {
                        this.ah();
                        by = (byte)(by - 1);
                        this.ap();
                    }
                } else {
                    long l;
                    this.aq.repaint();
                    this.aq.serviceRepaints();
                    long l2 = System.currentTimeMillis();
                    this.as = 0;
                    this.br();
                    if (this.as == 3) {
                        this.aj();
                        by = (byte)(by - 1);
                        this.ap();
                    }
                    if ((l = System.currentTimeMillis() - l2) < 2000L) {
                        try {
                            a a2 = this;
                            synchronized (a2) {
                                Thread.sleep(2000L - l);
                            }
                        }
                        catch (Exception exception) {}
                    }
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final boolean ar() {
        if (this.aE < 11 && this.aE > 2) {
            int n = this.s() % 500;
            return n < 4;
        }
        return false;
    }

    public final boolean as() {
        return this.s() % 1000 <= 1;
    }

    public final int a(int n, byte by, int n2) {
        return by * (n /= 2) / n2;
    }

    public final void D(byte by) {
        if (this.r[by] < 90) {
            byte by2 = by;
            this.m[by2] = (short)(this.m[by2] - this.a((int)this.m[by], this.r[by], 90));
            byte by3 = by;
            this.n[by3] = (short)(this.n[by3] - this.a((int)this.n[by], this.r[by], 90));
            byte by4 = by;
            this.l[by4] = (byte)(this.l[by4] - this.a((int)this.l[by], this.r[by], 90));
        }
        if (this.r[by] < 99) {
            byte by5 = by;
            this.p[by5] = (short)(this.p[by5] - this.a((int)this.p[by], this.r[by], 99));
            byte by6 = by;
            this.o[by6] = (short)(this.o[by6] - this.a((int)this.o[by], this.r[by], 99));
        }
        if (this.r[by] > 0) {
            byte by7 = by;
            this.r[by7] = (byte)(this.r[by7] - (this.r[by] / 10 + 1));
        }
    }

    public final void at() {
        this.bq();
        this.M((byte)4);
    }

    public final void E(byte by) {
        if (this.r[by] < 90) {
            this.v[by] = 0;
            byte by2 = 0;
            while (by2 < this.s[by]) {
                short s = this.B[by][by2];
                this.K[s] = (short)(this.K[s] - this.a((int)this.K[this.B[by][by2]], this.r[by], 90));
                byte by3 = by;
                this.v[by3] = (short)(this.v[by3] + this.K[this.B[by][by2]]);
                by2 = (byte)(by2 + 1);
            }
            byte by4 = by;
            this.w[by4] = (short)(this.w[by4] - this.a((int)this.w[by], this.r[by], 90));
            byte by5 = by;
            this.l[by5] = (byte)(this.l[by5] - this.a((int)this.l[by], this.r[by], 90));
        }
        if (this.r[by] < 99) {
            byte by6 = by;
            this.q[by6] = this.q[by6] - this.a(this.q[by], this.r[by], 99);
        }
        if (this.r[by] > 0) {
            byte by7 = by;
            this.r[by7] = (byte)(this.r[by7] - (this.r[by] / 10 + 1));
        }
    }

    public final boolean F(byte by) {
        if (this.l[by] < 15) {
            int n = this.l[by] * 10 / 15 + 80;
            if (this.s() % 100 >= n) {
                return true;
            }
        } else if (this.l[by] < 30) {
            int n = (this.l[by] - 15) / 3 + 90;
            if (this.s() % 100 >= n) {
                return true;
            }
        } else if (this.l[by] < 40) {
            int n = (this.l[by] - 30) / 5 + 98;
            if (this.s() % 100 >= n) {
                return true;
            }
        } else if (this.l[by] < 60) {
            int n = (this.l[by] - 40) / 5 + 998;
            if (this.s() % 1000 >= n) {
                return true;
            }
        }
        return false;
    }

    public final void G(byte by) {
        byte by2 = by;
        this.l[by2] = (byte)(this.l[by2] - this.s() % 15);
        if (this.l[by] < 0) {
            this.l[by] = 0;
        }
    }

    public final short a(short s, int n, int n2) {
        int n3 = s * n / n2;
        if (n3 > 0) {
            return (short)(s - this.s() % n3);
        }
        return s;
    }

    public final void H(byte by) {
        int n = 0;
        if (this.l[by] < 15) {
            byte by2 = by;
            this.q[by2] = this.q[by2] / 3;
            byte by3 = by;
            this.o[by3] = (short)(this.o[by3] / 3);
            byte by4 = by;
            this.p[by4] = (short)(this.p[by4] / 3);
            byte by5 = by;
            this.m[by5] = (short)(this.m[by5] / 2);
            byte by6 = by;
            this.n[by6] = (short)(this.n[by6] / 2);
            this.G(by);
            this.w[by] = this.a(this.w[by], 2, 3);
            byte by7 = 0;
            while (by7 < this.s[by]) {
                this.K[this.B[by][by7]] = this.a(this.K[this.B[by][by7]], 2, 3);
                n += this.K[this.B[by][by7]];
                by7 = (byte)(by7 + 1);
            }
            this.v[by] = (short)n;
            return;
        }
        if (this.l[by] < 30) {
            byte by8 = by;
            this.q[by8] = this.q[by8] / 2;
            byte by9 = by;
            this.o[by9] = (short)(this.o[by9] / 2);
            byte by10 = by;
            this.p[by10] = (short)(this.p[by10] / 2);
            this.m[by] = (short)(this.m[by] * 3 / 7);
            this.n[by] = (short)(this.n[by] * 3 / 7);
            this.G(by);
            this.w[by] = this.a(this.w[by], 1, 2);
            byte by11 = 0;
            while (by11 < this.s[by]) {
                this.K[this.B[by][by11]] = this.a(this.K[this.B[by][by11]], 1, 2);
                n += this.K[this.B[by][by11]];
                by11 = (byte)(by11 + 1);
            }
            this.v[by] = (short)n;
            return;
        }
        this.q[by] = this.q[by] * 2 / 3;
        this.o[by] = (short)(this.o[by] * 2 / 3);
        this.p[by] = (short)(this.p[by] * 2 / 3);
        this.m[by] = (short)(this.m[by] * 1 / 7);
        this.n[by] = (short)(this.n[by] * 1 / 7);
        this.G(by);
        this.w[by] = this.a(this.w[by], 1, 5);
        byte by12 = 0;
        while (by12 < this.s[by]) {
            this.K[this.B[by][by12]] = this.a(this.K[this.B[by][by12]], 1, 5);
            n += this.K[this.B[by][by12]];
            by12 = (byte)(by12 + 1);
        }
        this.v[by] = (short)n;
    }

    public final int b(int n, int n2, int n3) {
        int n4 = 125 * n3 / 20000;
        int n5 = n4 * n * 2 / 5 / 125;
        n4 += n5;
        n5 = 0;
        if (n2 > 90) {
            n5 = 0 + n4 * 4 / 5;
        } else if (n2 > 80) {
            n5 = 0 + n4 * 3 / 5;
        } else if (n2 > 70) {
            n5 = 0 + n4 * 2 / 5;
        } else if (n2 > 50) {
            n5 = 0 + n4 / 5;
        }
        return n4 += n5;
    }

    public final void I(byte by) {
        int n = this.m[by] + this.b((int)this.p[by], (int)this.l[by], this.q[by]);
        if (n > 9999) {
            this.m[by] = 9999;
            return;
        }
        this.m[by] = (short)n;
    }

    public final void J(byte by) {
        int n = this.n[by] + this.b((int)this.o[by], (int)this.l[by], this.q[by]);
        if (n > 9999) {
            this.n[by] = 9999;
            return;
        }
        this.n[by] = (short)n;
    }

    public final void au() {
        short s;
        int n;
        if (this.aF < 210) {
            return;
        }
        if (this.s() % 100 < 2 && this.b[this.at] == this.j[14]) {
            n = 0;
            while (n < this.s[14]) {
                s = this.B[14][n];
                if (s == 55) {
                    this.O[s] = 99;
                    this.P[s] = 99;
                    this.N[s] = 99;
                    this.L[s] = 99;
                }
                ++n;
            }
        }
        if (this.s() % 100 < 3 && this.b[this.at] == this.j[2]) {
            n = 0;
            while (n < this.s[2]) {
                s = this.B[2][n];
                if (s == 193) {
                    this.O[s] = 99;
                    this.P[s] = 99;
                    this.N[s] = 99;
                    this.L[s] = 99;
                }
                ++n;
            }
        }
    }

    public final boolean av() {
        int n = 1;
        while (n < 37) {
            if (this.j[n] != this.b[this.at]) {
                return false;
            }
            n = (byte)(n + 1);
        }
        return true;
    }

    public final boolean aw() {
        return this.d[this.at] == 0;
    }

    public final void ax() {
        this.aU = 0;
        if (this.av()) {
            this.aQ = 0;
            this.M((byte)23);
            this.al();
            this.as = 1;
            return;
        }
        if (this.aw()) {
            this.aQ = 1;
            this.M((byte)23);
            this.al();
            this.as = 1;
            return;
        }
        this.aE = (byte)(this.aE + 1);
        short s = 1;
        while (s < 37) {
            if (this.ar()) {
                byte by = this.aU;
                this.aU = (byte)(by + 1);
                this.aT[by] = (byte)s;
            }
            s = (short)(s + 1);
        }
        if (this.aU < 1) {
            s = 1;
            while (s < 37) {
                if (this.as()) {
                    byte by = this.aU;
                    this.aU = (byte)(by + 1);
                    this.aT[by] = (byte)s;
                }
                s = (short)(s + 1);
            }
        } else {
            s = 0;
            while (s < this.aU) {
                this.D(this.aT[s]);
                s = (short)(s + 1);
            }
            this.aQ = this.s() % 3 + 6;
            this.M((byte)19);
            this.at();
            this.aU = 0;
        }
        if (this.aU > 0) {
            s = 0;
            while (s < this.aU) {
                this.E(this.aT[s]);
                s = (short)(s + 1);
            }
            this.aQ = 9;
            this.M((byte)19);
            this.at();
            this.aU = 0;
        }
        s = 1;
        while (s < 37) {
            if (this.j[s] > 0 && this.F((byte)s)) {
                byte by = this.aU;
                this.aU = (byte)(by + 1);
                this.aT[by] = (byte)s;
            }
            s = (short)(s + 1);
        }
        if (this.aU > 0) {
            s = 0;
            while (s < this.aU) {
                this.H(this.aT[s]);
                s = (short)(s + 1);
            }
            this.aQ = 13;
            this.M((byte)19);
            this.at();
            this.aU = 0;
        }
        if (this.aE == 4) {
            s = 1;
            while (s < 37) {
                this.I((byte)s);
                s = (short)(s + 1);
            }
            this.aQ = 10;
            this.M((byte)19);
            this.at();
        } else if (this.aE == 10) {
            s = 1;
            while (s < 37) {
                this.J((byte)s);
                s = (short)(s + 1);
            }
            this.aQ = 11;
            this.M((byte)19);
            this.at();
        }
        s = 1;
        while (s < 232) {
            if (this.M[s] < this.L[s]) {
                short s2 = s;
                this.M[s2] = (byte)(this.M[s2] + this.L[s] / 10);
                if (this.M[s] > this.L[s]) {
                    this.M[s] = this.L[s];
                }
            }
            s = (short)(s + 1);
        }
        s = (short)(this.X - 1);
        while (s >= 0) {
            short s3 = s;
            this.Z[s3] = (byte)(this.Z[s3] - 1);
            if (this.Z[s] <= 0) {
                byte by = 1;
                while (by <= 7) {
                    if (by == this.Y[s]) {
                        this.aQ = 14;
                        this.av = by;
                        this.M((byte)19);
                        this.at();
                        break;
                    }
                    by = (byte)(by + 1);
                }
                this.Y[s] = this.Y[this.X];
                this.Z[s] = this.Z[this.X];
                this.X = (byte)(this.X - 1);
            }
            s = (short)(s - 1);
        }
        this.au();
        s = 1;
        while (s < 8) {
            this.aD[s - 1] = 1;
            s = (short)(s + 1);
        }
    }

    public final void ay() {
        while (this.aE <= 12) {
            this.aq();
            if (this.as != 0) {
                return;
            }
            this.aG = 0;
            this.ax();
            if (this.as == 0) continue;
            return;
        }
    }

    public final void k(short s) {
        this.K[s] = 0;
        if (this.V[s] <= 0) {
            return;
        }
        byte by = this.V[s];
        this.C[by] = this.b(this.C[by], this.t[by], s);
        byte by2 = by;
        this.t[by2] = (byte)(this.t[by2] + 1);
    }

    public final void l(short s) {
        short s2 = 1;
        while (s2 < 232) {
            if (this.U[s2] == s) {
                this.k(s2);
            }
            s2 = (short)(s2 + 1);
        }
    }

    public final void az() {
        short s = 0;
        this.aE = 1;
        this.aF = (short)(this.aF + 1);
        this.l(this.aF);
        int n = 0;
        while (n < 26) {
            int n2 = 0;
            if (this.ab[n] == this.aF) {
                short s2 = this.ac[n];
                int n3 = 1;
                while (n3 < 37) {
                    byte by = 0;
                    while (by < this.s[n3]) {
                        if (this.B[n3][by] == this.ad[n]) {
                            s = this.ad[n];
                            n2 = n3;
                            break;
                        }
                        by = (byte)(by + 1);
                    }
                    if (n2 > 0) break;
                    n3 = (byte)(n3 + 1);
                }
                if (n2 != 0) {
                    if (this.s[n2] < 10) {
                        int n4 = n2;
                        byte by = this.s[n4];
                        this.s[n4] = (byte)(by + 1);
                        this.B[n2][by] = s2;
                        if (this.j[n2] == this.b[this.at]) {
                            this.aQ = 12;
                            this.az = s2;
                            this.aA = s;
                            this.M((byte)19);
                            this.at();
                        }
                    } else {
                        this.C[n2] = this.b(this.C[n2], this.t[n2], s2);
                        int n5 = n2;
                        this.t[n5] = (byte)(this.t[n5] + 1);
                    }
                }
            }
            n = (byte)(n + 1);
        }
    }

    public final void aA() {
        while (true) {
            this.ay();
            if (this.as != 0) {
                return;
            }
            this.az();
        }
    }

    public final void aB() {
        this.aA();
    }

    public final void run() {
        int n = 0;
        while (n < 8) {
            this.aD[n] = 1;
            n = (byte)(n + 1);
        }
        this.q();
        this.p();
        if (this.as == 1) {
            this.as = 0;
        }
        this.aB();
    }

    public final void aC() {
        this.a[8] = 0;
        this.b[8] = 0;
        this.d[8] = 0;
        this.c[8] = "\u81ea\u5efa";
        this.e[8] = new byte[37];
        String string = this.c("/lib114.jg");
        this.a(string, 3);
        string = this.c("/lib115.jg");
        this.a(string, 1);
        string = this.c("/lib116.jg");
        System.gc();
        this.a(string, 2);
        int n = 229;
        while (n < 232) {
            this.G[n] = n;
            this.U[n] = 189;
            this.V[n] = 1;
            n = (short)(n + 1);
        }
        this.aD();
    }

    public final String c(String string) {
        String string2 = null;
        byte[] byArray = new byte[12000];
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/res" + string);
            int n = inputStream.read(byArray, 0, 12000);
            inputStream.close();
            string2 = new String(byArray, 0, n, "UTF-8");
        }
        catch (Exception exception) {}
        return string2;
    }

    public final void a(String string, int n) {
        int n2 = 0;
        int n3 = 0;
        byte by = 0;
        short s = 1;
        String string2 = null;
        try {
            while (n3 < string.length()) {
                if (string.charAt(n3) == ',' || string.charAt(n3) == '\n' || string.charAt(n3) == '\r') {
                    string2 = n3 == n2 ? "0" : string.substring(n2, n3);
                    n2 = n3 + 1;
                    if (n == 1) {
                        this.b(s, by, string2);
                    } else if (n == 2) {
                        this.c(s, by, string2);
                    } else if (n == 3) {
                        this.d(s, by, string2);
                    }
                    if (string.charAt(n3) == '\n') {
                        s = (short)(s + 1);
                        by = 0;
                    } else {
                        by = (byte)(by + 1);
                    }
                }
                ++n3;
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void b(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.i[s] = c.a[s];
                this.h[s] = (byte)s;
                this.t[s] = 0;
                this.u[s] = 0;
                this.s[s] = 0;
                this.B[s] = new short[10];
                this.y[s] = 0;
                return;
            }
            case 1: {
                this.j[s] = Short.parseShort(string);
                if (this.j[s] <= 0) break;
                int n = 1;
                while (n < 8) {
                    if (this.j[s] == this.b[n]) {
                        int n2 = n;
                        byte by2 = this.d[n2];
                        this.d[n2] = (byte)(by2 + 1);
                        this.e[n][by2] = this.h[s];
                    }
                    n = (byte)(n + 1);
                }
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                this.k[s] = Short.parseShort(string);
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                this.l[s] = Byte.parseByte(string);
                return;
            }
            case 6: {
                this.m[s] = Short.parseShort(string);
                return;
            }
            case 7: {
                this.n[s] = Short.parseShort(string);
                return;
            }
            case 8: {
                this.o[s] = Short.parseShort(string);
                return;
            }
            case 9: {
                this.p[s] = Short.parseShort(string);
                return;
            }
            case 10: {
                this.q[s] = Integer.parseInt(string);
                return;
            }
            case 11: {
                this.r[s] = Byte.parseByte(string);
                return;
            }
            case 12: {
                this.s[s] = 0;
                return;
            }
            case 13: {
                this.v[s] = Short.parseShort(string);
                return;
            }
            case 14: {
                this.w[s] = Short.parseShort(string);
                return;
            }
            case 15: {
                this.x[s] = Byte.parseByte(string);
                return;
            }
            case 16: {
                if (Byte.parseByte(string) != 1) break;
                short s2 = s;
                this.y[s2] = (byte)(this.y[s2] | 1);
                return;
            }
            case 17: {
                if (Byte.parseByte(string) != 1) break;
                short s3 = s;
                this.y[s3] = (byte)(this.y[s3] | 2);
                return;
            }
            case 18: {
                if (Byte.parseByte(string) != 1) break;
                short s4 = s;
                this.y[s4] = (byte)(this.y[s4] | 4);
                return;
            }
            case 19: {
                if (Byte.parseByte(string) != 1) break;
                short s5 = s;
                this.y[s5] = (byte)(this.y[s5] | 8);
                return;
            }
            case 20: {
                this.z[s] = Byte.parseByte(string);
                return;
            }
            case 21: {
                this.A[s] = Byte.parseByte(string);
            }
        }
    }

    public final void c(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.G[s] = s;
                return;
            }
            case 1: {
                this.H[s] = string;
                return;
            }
            case 2: {
                this.I[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 3: {
                this.J[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 4: {
                this.K[s] = (short)Integer.parseInt(string);
                return;
            }
            case 5: {
                this.M[s] = (byte)Integer.parseInt(string);
                this.L[s] = this.M[s];
                return;
            }
            case 6: {
                this.O[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 7: {
                this.N[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 8: {
                this.P[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 9: {
                this.Q[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 10: {
                this.R[s] = (short)(Integer.parseInt(string) * 100);
                return;
            }
            case 11: {
                this.S[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 12: {
                this.T[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 13: {
                this.U[s] = (short)Integer.parseInt(string);
                return;
            }
            case 14: {
                this.V[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 15: {
                if (this.U[s] != 189) break;
                byte by2 = this.V[s];
                if (string.trim().compareTo("0") == 0) {
                    this.C[by2] = this.b(this.C[by2], this.t[by2], this.G[s]);
                    byte by3 = by2;
                    this.t[by3] = (byte)(this.t[by3] + 1);
                    return;
                }
                if (string.trim().compareTo("1") != 0) break;
                byte by4 = by2;
                byte by5 = this.s[by4];
                this.s[by4] = (byte)(by5 + 1);
                this.B[by2][by5] = this.G[s];
            }
        }
    }

    public final void d(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.a[s] = Byte.parseByte(string);
                this.e[s] = new byte[37];
                return;
            }
            case 1: {
                this.b[s] = Short.parseShort(string);
                return;
            }
            case 2: {
                this.c[s] = string;
                return;
            }
            case 3: {
                this.d[s] = 0;
            }
        }
    }

    public final void aD() {
        block24: {
            RecordStore recordStore = null;
            try {
                String string = null;
                int n = 0;
                while (n < 3) {
                    int n2;
                    this.al[n] = 0;
                    this.ap[n] = 1;
                    string = "id" + n;
                    recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)false);
                    if (recordStore.getNumRecords() <= 0) {
                        try {
                            n2 = 0;
                            while (n2 < 100) {
                                recordStore.closeRecordStore();
                                ++n2;
                            }
                        }
                        catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                    } else {
                        byte[] byArray = recordStore.getRecord(1);
                        this.ap[n] = byArray[0];
                        try {
                            n2 = 0;
                            while (n2 < 100) {
                                recordStore.closeRecordStore();
                                ++n2;
                            }
                        }
                        catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                        string = "record" + (n + byArray[0] * 3);
                        recordStore = null;
                        recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)false);
                        if (recordStore.getNumRecords() <= 0) {
                            try {
                                n2 = 0;
                                while (n2 < 100) {
                                    recordStore.closeRecordStore();
                                    ++n2;
                                }
                            }
                            catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                        } else {
                            byArray = recordStore.getRecord(1);
                            this.al[n] = (short)(byArray[1] << 8 | byArray[0] & 0xFF);
                            this.am[n] = byArray[2];
                            this.an[n] = (short)(byArray[4] << 8 | byArray[3] & 0xFF);
                            this.ao[n] = this.H[this.al[n]];
                            try {
                                n2 = 0;
                                while (n2 < 100) {
                                    recordStore.closeRecordStore();
                                    ++n2;
                                }
                            }
                            catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                            recordStore = null;
                        }
                    }
                    n = (byte)(n + 1);
                }
                return;
            }
            catch (Exception exception) {
                if (recordStore == null) break block24;
                try {
                    int n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        ++n;
                    }
                    return;
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    return;
                }
                catch (Exception exception2) {}
            }
        }
    }

    public final short[] b(short[] sArray, byte by, short s) {
        short[] sArray2 = new short[by + 1];
        byte by2 = 0;
        while (by2 < by) {
            sArray2[by2] = sArray[by2];
            by2 = (byte)(by2 + 1);
        }
        sArray2[by2] = s;
        return sArray2;
    }

    public final void K(byte by) {
        this.at = by;
        this.as = 1;
        this.aq.a((byte)4);
    }

    public final boolean L(byte by) {
        byte by2 = 0;
        while (by2 < this.X) {
            if (this.j[by] == this.b[this.Y[by2]]) {
                return true;
            }
            by2 = (byte)(by2 + 1);
        }
        return false;
    }

    public final short b(short[] sArray, byte by) {
        short s = 0;
        byte by2 = 0;
        while (by2 < by) {
            s = (short)(s + this.K[sArray[by2]]);
            by2 = (byte)(by2 + 1);
        }
        return s;
    }

    public final void b(short s, byte by) {
        byte by2 = by;
        this.w[by2] = (short)(this.w[by2] + s);
        byte by3 = by;
        this.m[by3] = (short)(this.m[by3] - (s + 4) / 5);
        byte by4 = by;
        this.q[by4] = this.q[by4] - s;
    }

    public final void M(byte by) {
        this.aq.a(by);
        this.aq.repaint();
        this.aW = true;
    }

    public final void aE() {
        this.bN = 0;
        this.bM = 0;
        this.aJ = true;
        this.aK = true;
        this.ay = this.aw;
        System.gc();
    }

    public final void aF() {
        this.aE();
        this.M((byte)6);
    }

    public final void aG() {
        this.aE();
        this.aI = (byte)(this.aI - 1);
        if (this.aI <= 0) {
            this.bM = 0;
            this.as = (byte)3;
            this.M((byte)4);
            return;
        }
        if (this.j[this.aw] != this.b[this.at]) {
            this.u();
        }
        this.M((byte)6);
    }

    public final void aH() {
        this.aq.m();
        this.M((byte)4);
    }

    public final void aI() {
        this.aw = this.ax;
        this.bN = (byte)(this.bN + 1);
        this.bg();
    }

    public final void aJ() {
        this.aA = this.az;
        this.M((byte)7);
    }

    public final void aK() {
        this.c(this.az, this.aw);
        this.bN = (byte)(this.bN + 1);
        this.bg();
    }

    public final void aL() {
        this.c(this.az, this.aw);
        this.a(this.az, c.d[2][3]);
        this.az = this.aB[0];
        this.bN = (byte)6;
    }

    public final void aM() {
        this.c(this.aw, this.az);
        this.M((byte)11);
        this.bN = (byte)(this.bN + 1);
    }

    public final void a(short s, String string) {
        this.aL = s;
        this.aM = string;
        this.M((byte)10);
    }

    public final void aN() {
        if (this.c(this.aw, this.az, this.aA)) {
            this.a(this.az, c.d[2][0]);
        } else {
            this.a(this.az, c.d[2][1]);
        }
        this.bN = (byte)(this.bN + 1);
    }

    public final void aO() {
        this.d(this.az, this.aw);
        this.bN = (byte)(this.bN + 1);
        this.a(this.az, c.d[2][3]);
    }

    public final void aP() {
        int n = 0;
        while (n < this.aC) {
            if (this.aB[n] == this.az) {
                boolean bl = false;
                this.aB[n] = this.aB[0];
                this.aB[0] = this.az;
            }
            ++n;
        }
        this.bN = (byte)(this.bN + 1);
        this.a(this.az, c.d[0][2]);
    }

    public final void aQ() {
        if (this.aQ > this.m[this.aw]) {
            this.aQ = this.m[this.aw];
        }
        this.M((byte)13);
    }

    public final void aR() {
        this.aQ = this.s(this.az);
        this.aQ();
    }

    public final void aS() {
        this.aQ = this.t(this.az);
        this.aQ();
    }

    public final void a(byte by, short s, int n) {
        int n2 = this.c(s, n);
        byte by2 = by;
        this.m[by2] = (short)(this.m[by2] - n);
        byte by3 = by;
        this.o[by3] = (short)(this.o[by3] + n2);
        if (this.o[by] > 999) {
            this.o[by] = 999;
        }
        if (this.l[by] < 99) {
            byte by4 = by;
            this.l[by4] = (byte)(this.l[by4] + 1);
        }
    }

    public final void aT() {
        this.a(this.aw, this.az, this.aQ);
        this.a(this.az, c.d[3][0]);
        this.bN = (byte)(this.bN + 1);
    }

    public final void b(byte by, short s, int n) {
        int n2 = this.c(s, n);
        byte by2 = by;
        this.m[by2] = (short)(this.m[by2] - n);
        byte by3 = by;
        this.p[by3] = (short)(this.p[by3] + n2);
        if (this.p[by] > 999) {
            this.p[by] = 999;
        }
        if (this.l[by] < 99) {
            byte by4 = by;
            this.l[by4] = (byte)(this.l[by4] + 1);
        }
    }

    public final void aU() {
        this.b(this.aw, this.az, this.aQ);
        this.a(this.az, c.d[3][1]);
        this.bN = (byte)(this.bN + 1);
    }

    public final void c(byte by, short s, int n) {
        int n2 = this.d(s, n);
        byte by2 = by;
        this.m[by2] = (short)(this.m[by2] - n);
        byte by3 = by;
        this.r[by3] = (byte)(this.r[by3] + n2);
        if (this.r[by] > 99) {
            this.r[by] = 99;
        }
    }

    public final void aV() {
        this.c(this.aw, this.az, this.aQ);
        this.a(this.az, c.d[3][3]);
        this.bN = (byte)(this.bN + 1);
    }

    public final void d(byte by, short s, int n) {
        int n2 = this.e(s, n);
        byte by2 = by;
        this.m[by2] = (short)(this.m[by2] - n);
        byte by3 = by;
        this.q[by3] = this.q[by3] + n2;
        if (this.q[by] > 990000) {
            this.q[by] = 990000;
        }
        if (this.l[by] < 99) {
            byte by4 = by;
            this.l[by4] = (byte)(this.l[by4] + 1);
        }
    }

    public final void aW() {
        this.d(this.aw, this.az, this.aQ);
        this.a(this.az, c.d[3][4]);
        this.bN = (byte)(this.bN + 1);
    }

    public final int e(short s, short s2) {
        byte by = this.O[s];
        int n = this.O[s2];
        byte by2 = this.Q[s2];
        if (by2 > 99) {
            n = 10000;
        } else if (by2 > 96) {
            n += 50;
        } else if (by2 > 89) {
            n += 20;
        }
        if (by - n <= this.s() % 30) {
            return 0;
        }
        int n2 = this.s() % (by - n);
        n2 /= 10;
        n2 *= 9;
        n2 /= 5;
        return ++n2;
    }

    public final boolean f(short s, short s2) {
        int n = this.e(s, s2);
        if (n > 0) {
            short s3 = s2;
            this.Q[s3] = (byte)(this.Q[s3] - (byte)n);
            if (this.Q[s2] < 0) {
                this.Q[s2] = 0;
            }
            return true;
        }
        return false;
    }

    public final void aX() {
        if (this.f(this.az, this.aA)) {
            this.a(this.az, c.d[5][1]);
        } else {
            this.a(this.az, c.d[5][0]);
        }
        this.bN = (byte)(this.bN + 1);
    }

    public final int g(short s, short s2) {
        byte by = this.P[s];
        byte by2 = this.O[s2];
        int n = by * by;
        n /= 100;
        if ((n -= by2) <= this.s() % 100) {
            return -1;
        }
        return 0;
    }

    public final void aY() {
        short s = this.b[this.av];
        int n = this.g(this.az, s);
        if (n == 0) {
            this.Y[this.X] = this.av;
            this.Z[this.X] = 12;
            this.X = (byte)(this.X + 1);
            this.a(this.az, c.d[1][0]);
        } else {
            this.a(this.az, c.d[1][1]);
        }
        this.bN = (byte)(this.bN + 1);
    }

    public final void m(short s) {
        int n = 100 - this.Q[s];
        int n2 = (n + 50) / 2;
        if (n == n2) {
            n2 = this.s() % 3 - 1;
        } else if (this.Q[s] > 50) {
            n2 = this.s() % (n2 - n);
        } else {
            n = this.s() % (n - n2);
        }
        this.Q[s] = (byte)(n + n2);
    }

    public final boolean h(short s, short s2) {
        byte by = this.O[s];
        byte by2 = this.O[s2];
        byte by3 = this.Q[s2];
        if (by3 > 35) {
            return false;
        }
        int n = by - by2 + (35 - by3);
        if (n >= this.s() % 20) {
            this.m(s2);
            return true;
        }
        return false;
    }

    public final boolean a(byte by, byte by2, short s, short s2) {
        if (this.h(s, s2)) {
            int n = 0;
            while (n < this.s[by2]) {
                if (this.B[by2][n] == s2) break;
                ++n;
            }
            byte by3 = by2;
            this.s[by3] = (byte)(this.s[by3] - 1);
            int n2 = n;
            while (n2 < this.s[by2]) {
                this.B[by2][n2] = this.B[by2][n2 + 1];
                ++n2;
            }
            this.B[by][this.s[by]] = s2;
            byte by4 = by;
            this.s[by4] = (byte)(this.s[by4] + 1);
            byte by5 = by2;
            this.v[by5] = (short)(this.v[by5] - this.K[s2]);
            byte by6 = by;
            this.v[by6] = (short)(this.v[by6] + this.K[s2]);
            if (this.s[by2] == 0) {
                byte by7 = this.h(this.j[by2]);
                n = 0;
                while (n < this.d[by7]) {
                    if (this.e[by7][n] == by2) break;
                    ++n;
                }
                byte by8 = by7;
                this.d[by8] = (byte)(this.d[by8] - 1);
                n2 = n;
                while (n2 < this.d[by7]) {
                    this.e[by7][n2] = this.e[by7][n2 + 1];
                    ++n2;
                }
                this.j[by2] = 0;
                this.k[by2] = 0;
            } else {
                this.k[by2] = this.B[by2][0];
            }
            return true;
        }
        return false;
    }

    public final void aZ() {
        if (this.a(this.aw, this.ax, this.az, this.aA)) {
            this.a(this.az, c.d[5][3]);
        } else {
            this.a(this.az, c.d[5][2]);
        }
        this.bN = (byte)(this.bN + 1);
    }

    public final int n(short s) {
        if (this.O[s] < 30 || this.O[s] > 70) {
            return this.s() % 5 + 6;
        }
        return this.s() % 7 + 6;
    }

    public final void ba() {
        short s = 10;
        if (this.O[this.az] < 30) {
            s = 30;
        } else if (this.O[this.az] < 60) {
            s = 20;
        }
        if (this.m[this.aw] >= s) {
            short s2 = this.az;
            this.O[s2] = (byte)(this.O[s2] + this.n(this.az));
            byte by = this.aw;
            this.m[by] = (short)(this.m[by] - s);
            this.a(this.az, c.d[4][3]);
            this.bN = (byte)(this.bN + 1);
            return;
        }
        this.a(this.az, "\u8d44\u8d22\u4e0d\u8db3");
        this.bN = (byte)3;
    }

    public final void bb() {
        this.bE();
        this.a(this.az, c.d[4][4]);
        this.bN = (byte)(this.bN + 1);
    }

    public final short a(byte by, byte[] byArray, short s) {
        short s2 = s;
        s = (short)(s2 + 1);
        byArray[s2] = (byte)(this.j[by] & 0xFF);
        short s3 = s;
        s = (short)(s3 + 1);
        byArray[s3] = (byte)(this.j[by] >> 8);
        short s4 = s;
        s = (short)(s4 + 1);
        byArray[s4] = (byte)(this.k[by] & 0xFF);
        short s5 = s;
        s = (short)(s5 + 1);
        byArray[s5] = (byte)(this.k[by] >> 8);
        short s6 = s;
        s = (short)(s6 + 1);
        byArray[s6] = this.l[by];
        short s7 = s;
        s = (short)(s7 + 1);
        byArray[s7] = (byte)(this.m[by] & 0xFF);
        short s8 = s;
        s = (short)(s8 + 1);
        byArray[s8] = (byte)(this.m[by] >> 8);
        short s9 = s;
        s = (short)(s9 + 1);
        byArray[s9] = (byte)(this.n[by] & 0xFF);
        short s10 = s;
        s = (short)(s10 + 1);
        byArray[s10] = (byte)(this.n[by] >> 8);
        short s11 = s;
        s = (short)(s11 + 1);
        byArray[s11] = (byte)(this.o[by] & 0xFF);
        short s12 = s;
        s = (short)(s12 + 1);
        byArray[s12] = (byte)(this.o[by] >> 8);
        short s13 = s;
        s = (short)(s13 + 1);
        byArray[s13] = (byte)(this.p[by] & 0xFF);
        short s14 = s;
        s = (short)(s14 + 1);
        byArray[s14] = (byte)(this.p[by] >> 8);
        short s15 = s;
        s = (short)(s15 + 1);
        byArray[s15] = (byte)(this.q[by] & 0xFF);
        short s16 = s;
        s = (short)(s16 + 1);
        byArray[s16] = (byte)(this.q[by] >> 8 & 0xFF);
        short s17 = s;
        s = (short)(s17 + 1);
        byArray[s17] = (byte)(this.q[by] >> 16 & 0xFF);
        short s18 = s;
        s = (short)(s18 + 1);
        byArray[s18] = (byte)(this.q[by] >> 24);
        short s19 = s;
        s = (short)(s19 + 1);
        byArray[s19] = this.r[by];
        short s20 = s;
        s = (short)(s20 + 1);
        byArray[s20] = this.s[by];
        short s21 = s;
        s = (short)(s21 + 1);
        byArray[s21] = this.t[by];
        short s22 = s;
        s = (short)(s22 + 1);
        byArray[s22] = this.u[by];
        short s23 = s;
        s = (short)(s23 + 1);
        byArray[s23] = (byte)(this.v[by] & 0xFF);
        short s24 = s;
        s = (short)(s24 + 1);
        byArray[s24] = (byte)(this.v[by] >> 8);
        short s25 = s;
        s = (short)(s25 + 1);
        byArray[s25] = (byte)(this.w[by] & 0xFF);
        short s26 = s;
        s = (short)(s26 + 1);
        byArray[s26] = (byte)(this.w[by] >> 8);
        short s27 = s;
        s = (short)(s27 + 1);
        byArray[s27] = this.x[by];
        short s28 = s;
        s = (short)(s28 + 1);
        byArray[s28] = this.y[by];
        short s29 = s;
        s = (short)(s29 + 1);
        byArray[s29] = this.z[by];
        return s;
    }

    public final short a(short s, byte[] byArray, short s2) {
        short s3 = s2;
        s2 = (short)(s3 + 1);
        byArray[s3] = (byte)(s & 0xFF);
        short s4 = s2;
        s2 = (short)(s4 + 1);
        byArray[s4] = (byte)(s >> 8);
        return s2;
    }

    public final short b(short s, byte[] byArray, short s2) {
        short s3 = s2;
        s2 = (short)(s3 + 1);
        byArray[s3] = this.I[s];
        short s4 = s2;
        s2 = (short)(s4 + 1);
        byArray[s4] = this.N[s];
        short s5 = s2;
        s2 = (short)(s5 + 1);
        byArray[s5] = (byte)(this.K[s] & 0xFF);
        short s6 = s2;
        s2 = (short)(s6 + 1);
        byArray[s6] = (byte)(this.K[s] >> 8);
        short s7 = s2;
        s2 = (short)(s7 + 1);
        byArray[s7] = this.M[s];
        short s8 = s2;
        s2 = (short)(s8 + 1);
        byArray[s8] = this.O[s];
        short s9 = s2;
        s2 = (short)(s9 + 1);
        byArray[s9] = this.Q[s];
        short s10 = s2;
        s2 = (short)(s10 + 1);
        byArray[s10] = (byte)(this.R[s] & 0xFF);
        short s11 = s2;
        s2 = (short)(s11 + 1);
        byArray[s11] = (byte)(this.R[s] >> 8);
        short s12 = s2;
        s2 = (short)(s12 + 1);
        byArray[s12] = this.S[s];
        short s13 = s2;
        s2 = (short)(s13 + 1);
        byArray[s13] = this.T[s];
        return s2;
    }

    public final short b(byte by, byte[] byArray, short s) {
        short s2 = s;
        s = (short)(s2 + 1);
        byArray[s2] = (byte)(this.b[by] & 0xFF);
        short s3 = s;
        s = (short)(s3 + 1);
        byArray[s3] = (byte)(this.b[by] >> 8);
        return s;
    }

    public final void bc() {
        int n;
        block34: {
            int n2;
            RecordStore recordStore;
            block35: {
                String string = null;
                n = 0;
                RecordStore recordStore2 = null;
                recordStore = null;
                int n3 = this.ap[this.aQ] == 0 ? 1 : 0;
                try {
                    string = "record" + (this.aQ + n3 * 3);
                    try {
                        RecordStore.deleteRecordStore((String)string);
                    }
                    catch (Exception exception) {}
                    System.gc();
                    byte[] byArray = new byte[1024];
                    recordStore2 = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)true);
                    byArray[0] = (byte)(this.b[this.at] & 0xFF);
                    byArray[1] = (byte)(this.b[this.at] >> 8);
                    byArray[2] = this.aE;
                    byArray[3] = (byte)(this.aF & 0xFF);
                    byArray[4] = (byte)(this.aF >> 8);
                    short s = 0;
                    recordStore2.addRecord(byArray, 0, 6);
                    recordStore2 = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)true);
                    s = 0;
                    while (s < 8) {
                        byArray[s] = this.aD[s];
                        s = (short)(s + 1);
                    }
                    short s2 = s;
                    s = (short)(s2 + 1);
                    byArray[s2] = this.at;
                    short s3 = s;
                    s = (short)(s3 + 1);
                    byArray[s3] = this.aw;
                    short s4 = s;
                    s = (short)(s4 + 1);
                    byArray[s4] = this.aI;
                    short s5 = s;
                    s = (short)(s5 + 1);
                    byArray[s5] = this.aE;
                    short s6 = s;
                    s = (short)(s6 + 1);
                    byArray[s6] = (byte)(this.aF & 0xFF);
                    short s7 = s;
                    s = (short)(s7 + 1);
                    byArray[s7] = (byte)(this.aF >> 8);
                    short s8 = s;
                    s = (short)(s8 + 1);
                    byArray[s8] = this.aa;
                    short s9 = 1;
                    while (s9 < 37) {
                        s = this.a((byte)s9, byArray, s);
                        short s10 = 0;
                        while (s10 < this.s[s9]) {
                            s = this.a(this.B[s9][s10], byArray, s);
                            s10 = (short)(s10 + 1);
                        }
                        s10 = 0;
                        while (s10 < this.u[s9]) {
                            s = this.a(this.D[s9][s10], byArray, s);
                            s10 = (short)(s10 + 1);
                        }
                        s10 = 0;
                        while (s10 < this.t[s9]) {
                            s = this.a(this.C[s9][s10], byArray, s);
                            s10 = (short)(s10 + 1);
                        }
                        if (s9 == 18) {
                            recordStore2.addRecord(byArray, 0, (int)s);
                            s = 0;
                        }
                        s9 = (short)(s9 + 1);
                    }
                    recordStore2.addRecord(byArray, 0, (int)s);
                    s = 0;
                    s9 = 1;
                    while (s9 < 81) {
                        s = this.b(s9, byArray, s);
                        s9 = (short)(s9 + 1);
                    }
                    recordStore2.addRecord(byArray, 0, (int)s);
                    s = 0;
                    s9 = 81;
                    while (s9 < 161) {
                        s = this.b(s9, byArray, s);
                        s9 = (short)(s9 + 1);
                    }
                    recordStore2.addRecord(byArray, 0, (int)s);
                    s = 0;
                    s9 = 161;
                    while (s9 < 229) {
                        s = this.b(s9, byArray, s);
                        s9 = (short)(s9 + 1);
                    }
                    s9 = 1;
                    while (s9 <= 8) {
                        s = this.b((byte)s9, byArray, s);
                        s9 = (short)(s9 + 1);
                    }
                    short s11 = s;
                    s = (short)(s11 + 1);
                    byArray[s11] = this.X;
                    s9 = 0;
                    while (s9 < this.X) {
                        short s12 = s;
                        s = (short)(s12 + 1);
                        byArray[s12] = this.Y[s9];
                        short s13 = s;
                        s = (short)(s13 + 1);
                        byArray[s13] = this.Z[s9];
                        s9 = (short)(s9 + 1);
                    }
                    recordStore2.addRecord(byArray, 0, (int)s);
                    try {
                        s9 = 0;
                        while (s9 < 100) {
                            recordStore2.closeRecordStore();
                            s9 = (short)(s9 + 1);
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                    recordStore2 = null;
                    string = "id" + this.aQ;
                    try {
                        RecordStore.deleteRecordStore((String)string);
                    }
                    catch (Exception exception) {}
                    recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)true);
                    byArray[0] = n3;
                    recordStore.addRecord(byArray, 0, 1);
                    try {
                        s9 = 0;
                        while (s9 < 100) {
                            recordStore.closeRecordStore();
                            s9 = (short)(s9 + 1);
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                    this.ap[this.aQ] = n3;
                    this.al[this.aQ] = this.b[this.at];
                    this.am[this.aQ] = this.aE;
                    this.an[this.aQ] = this.aF;
                    this.ao[this.aQ] = this.c[this.at];
                    n = 1;
                    recordStore = null;
                    System.gc();
                    break block34;
                }
                catch (Exception exception) {
                    if (recordStore2 == null) break block35;
                    try {
                        n2 = 0;
                        while (n2 < 100) {
                            recordStore2.closeRecordStore();
                            n2 = (short)(n2 + 1);
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    }
                    catch (Exception exception2) {}
                }
            }
            if (recordStore != null) {
                try {
                    n2 = 0;
                    while (n2 < 100) {
                        recordStore.closeRecordStore();
                        n2 = (short)(n2 + 1);
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                }
                catch (Exception exception) {}
            }
        }
        if (n > 0) {
            this.a(this.k[this.aw], "\u5b58\u50a8\u6210\u529f");
        } else {
            this.a(this.k[this.aw], "\u5b58\u50a8\u5931\u8d25, \u8bf7\u7a0d\u540e\u518d\u8bd5");
        }
        this.bN = (byte)(this.bN + 1);
    }

    public final short a(byte by, byte[] byArray, int n) {
        this.j[by] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
        this.k[by] = (short)(byArray[n + 3] << 8 | byArray[n + 2] & 0xFF);
        this.l[by] = byArray[n + 4];
        this.m[by] = (short)(byArray[n + 6] << 8 | byArray[n + 5] & 0xFF);
        this.n[by] = (short)(byArray[n + 8] << 8 | byArray[n + 7] & 0xFF);
        this.o[by] = (short)(byArray[n + 10] << 8 | byArray[n + 9] & 0xFF);
        this.p[by] = (short)(byArray[n + 12] << 8 | byArray[n + 11] & 0xFF);
        this.q[by] = byArray[n + 16] << 24 | byArray[n + 15] << 16 & 0xFF0000 | byArray[n + 14] << 8 & 0xFF00 | byArray[n + 13] & 0xFF;
        this.r[by] = byArray[n + 17];
        this.s[by] = byArray[n + 18];
        this.t[by] = byArray[n + 19];
        this.u[by] = byArray[n + 20];
        this.v[by] = (short)(byArray[n + 22] << 8 | byArray[n + 21] & 0xFF);
        this.w[by] = (short)(byArray[n + 24] << 8 | byArray[n + 23] & 0xFF);
        this.x[by] = byArray[n + 25];
        this.y[by] = byArray[n + 26];
        this.z[by] = byArray[n + 27];
        return 28;
    }

    public final int a(byte[] byArray, int n, short s) {
        this.I[s] = byArray[n];
        this.N[s] = byArray[n + 1];
        this.K[s] = (short)(byArray[n + 3] << 8 | byArray[n + 2] & 0xFF);
        this.M[s] = byArray[n + 4];
        this.O[s] = byArray[n + 5];
        this.Q[s] = byArray[n + 6];
        this.R[s] = (short)(byArray[n + 8] << 8 | byArray[n + 7] & 0xFF);
        this.S[s] = byArray[n + 9];
        this.T[s] = byArray[n + 10];
        return n + 11;
    }

    public final void bd() {
        block26: {
            RecordStore recordStore = null;
            try {
                short s;
                String string = "record" + (this.aQ + this.ap[this.aQ] * 3);
                System.gc();
                recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)false);
                byte[] byArray = recordStore.getRecord(2);
                this.aG = (byte)-1;
                int n = 0;
                while (n < 8) {
                    this.aD[n] = byArray[n];
                    if (byArray[n] == 0) {
                        this.aG = (byte)(this.aG + 1);
                    }
                    ++n;
                }
                this.au = this.at = byArray[n++];
                this.aw = byArray[n++];
                this.aI = byArray[n++];
                this.aE = byArray[n++];
                this.aF = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
                n += 2;
                this.aa = byArray[n++];
                byte by = 1;
                while (by < 37) {
                    n += this.a(by, byArray, n);
                    s = 0;
                    while (s < this.s[by]) {
                        this.B[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                        n += 2;
                        s = (short)(s + 1);
                    }
                    if (this.u[by] > 0) {
                        this.D[by] = new short[this.u[by]];
                    }
                    s = 0;
                    while (s < this.u[by]) {
                        this.D[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                        n += 2;
                        s = (short)(s + 1);
                    }
                    if (this.t[by] > 0) {
                        this.C[by] = new short[this.t[by]];
                    }
                    s = 0;
                    while (s < this.t[by]) {
                        this.C[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                        n += 2;
                        s = (short)(s + 1);
                    }
                    if (by == 18) {
                        byArray = recordStore.getRecord(3);
                        n = 0;
                    }
                    by = (byte)(by + 1);
                }
                byArray = recordStore.getRecord(4);
                n = 0;
                s = 1;
                while (s < 81) {
                    n = this.a(byArray, n, s);
                    s = (short)(s + 1);
                }
                byArray = recordStore.getRecord(5);
                n = 0;
                s = 81;
                while (s < 161) {
                    n = this.a(byArray, n, s);
                    s = (short)(s + 1);
                }
                byArray = recordStore.getRecord(6);
                n = 0;
                s = 161;
                while (s < 229) {
                    n = this.a(byArray, n, s);
                    s = (short)(s + 1);
                }
                by = 1;
                while (by <= 8) {
                    this.b[by] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
                    this.d[by] = 0;
                    n += 2;
                    if (this.b[by] > 0) {
                        s = 1;
                        while (s < 37) {
                            if (this.j[s] == this.b[by]) {
                                byte by2 = by;
                                byte by3 = this.d[by2];
                                this.d[by2] = (byte)(by3 + 1);
                                this.e[by][by3] = (byte)s;
                            }
                            s = (short)(s + 1);
                        }
                        this.a[by] = this.d[by] == 0 || this.b[by] <= 0 ? (byte)0 : by;
                    }
                    by = (byte)(by + 1);
                }
                this.X = byArray[n++];
                by = 0;
                while (by < this.X) {
                    this.Y[by] = byArray[n++];
                    this.Z[by] = byArray[n++];
                    by = (byte)(by + 1);
                }
                try {
                    by = 0;
                    while (by < 100) {
                        recordStore.closeRecordStore();
                        by = (byte)(by + 1);
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
                return;
            }
            catch (Exception exception) {
                if (recordStore == null) break block26;
                try {
                    int n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        n = (byte)(n + 1);
                    }
                    return;
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    return;
                }
                catch (Exception exception2) {}
            }
        }
    }

    public final void be() {
        this.bd();
        this.aF();
    }

    public final void bf() {
        byte by = this.aw;
        this.m[by] = (short)(this.m[by] - this.ba);
        byte by2 = this.aw;
        this.n[by2] = (short)(this.n[by2] - this.bb);
        if (this.j[this.ax] == 0) {
            byte by3 = this.at;
            byte by4 = this.d[by3];
            this.d[by3] = (byte)(by4 + 1);
            this.e[this.at][by4] = this.ax;
            this.j[this.ax] = this.b[this.at];
            this.k[this.ax] = this.aB[0];
            this.s[this.ax] = this.aC;
            byte by5 = 0;
            while (by5 < this.aC) {
                this.B[this.ax][by5] = this.aB[by5];
                by5 = (byte)(by5 + 1);
            }
            this.v[this.ax] = this.b(this.aB, this.aC);
            byte by6 = this.ax;
            this.m[by6] = (short)(this.m[by6] + this.ba);
            byte by7 = this.ax;
            this.n[by7] = (short)(this.n[by7] + this.bb);
            this.aw = this.ax;
            this.bN = (byte)(this.bN + 2);
            this.bg();
            return;
        }
        this.as = (byte)4;
    }

    public final void bg() {
        byte by = this.bO[this.bM][this.bN];
        System.gc();
        switch (by) {
            case 0: {
                this.aF();
                return;
            }
            case 1: {
                this.aG();
                return;
            }
            case 2: {
                this.aH();
                return;
            }
            case 3: {
                this.aJ();
                return;
            }
            case 4: {
                this.bN = (byte)(this.bN + 1);
                this.M((byte)10);
                return;
            }
            case 5: {
                this.M((byte)8);
                return;
            }
            case 6: {
                this.M((byte)14);
                return;
            }
            case 8: {
                this.M((byte)5);
                return;
            }
            case 9: {
                this.M((byte)15);
                return;
            }
            case 10: {
                this.aM();
                return;
            }
            case 11: {
                this.M((byte)12);
                return;
            }
            case 12: {
                this.aR();
                return;
            }
            case 13: {
                this.aS();
                return;
            }
            case 14: {
                this.M((byte)16);
                return;
            }
            case 15: {
                this.M((byte)17);
                return;
            }
            case 16: {
                this.M((byte)9);
                return;
            }
            case 17: {
                this.M((byte)18);
                return;
            }
            case 83: {
                this.aL();
                return;
            }
            case 84: {
                this.aP();
                return;
            }
            case 85: {
                this.bf();
                return;
            }
            case 86: {
                this.be();
                return;
            }
            case 87: {
                this.bc();
                return;
            }
            case 88: {
                this.bb();
                return;
            }
            case 89: {
                this.ba();
                return;
            }
            case 90: {
                this.aZ();
                return;
            }
            case 91: {
                this.aY();
                return;
            }
            case 92: {
                this.aX();
                return;
            }
            case 93: {
                this.aW();
                return;
            }
            case 94: {
                this.aV();
                return;
            }
            case 95: {
                this.aU();
                return;
            }
            case 96: {
                this.aT();
                return;
            }
            case 97: {
                this.aO();
                return;
            }
            case 98: {
                this.aN();
                return;
            }
            case 99: {
                this.aK();
                return;
            }
            case 100: {
                this.aI();
            }
        }
    }

    public final boolean bh() {
        if (this.aW) {
            this.aW = false;
            return true;
        }
        return false;
    }

    public final void a(byte by, int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.ay = this.bM == 1 || this.bM == 3 ? this.aw : by;
        this.ax = by;
        this.bN = (byte)(this.bN + 1);
        this.bg();
    }

    public final void i(int n) {
        byte by;
        short[] sArray;
        if (this.bM != 22 && !this.bh()) {
            return;
        }
        if (this.aN) {
            sArray = this.B[this.ay];
            by = this.s[this.ay];
        } else {
            sArray = this.aO;
            by = this.aP;
            this.aN = true;
        }
        if (n == 1) {
            this.c(sArray, by);
            if (this.bM == 22) {
                this.as = 1;
                return;
            }
            this.aF();
            return;
        }
        byte by2 = 0;
        while (by2 < by) {
            if (this.W[sArray[by2]]) {
                this.az = sArray[by2];
                this.W[this.az] = false;
                break;
            }
            by2 = (byte)(by2 + 1);
        }
        if (this.bM == 22) {
            this.as = (byte)2;
            return;
        }
        if (this.bM == 9) {
            this.aW = true;
        }
        this.bN = (byte)(this.bN + 1);
        this.ay = this.aw;
        this.bg();
    }

    public final void c(short s, byte by) {
        byte by2 = 0;
        while (by2 < this.s[by]) {
            if (this.B[by][by2] == s) break;
            by2 = (byte)(by2 + 1);
        }
        byte by3 = by2;
        while (by3 > 0) {
            this.B[by][by3] = this.B[by][by3 - 1];
            by3 = (byte)(by3 - 1);
        }
        this.B[by][0] = s;
        this.k[by] = s;
    }

    public final void d(short s, byte by) {
        if (!this.bh()) {
            return;
        }
        this.c(s, by);
    }

    public final void N(byte by) {
        this.v[by] = 0;
        byte by2 = 0;
        while (by2 < this.s[by]) {
            byte by3 = by;
            this.v[by3] = (short)(this.v[by3] + this.K[this.B[by][by2]]);
            by2 = (byte)(by2 + 1);
        }
    }

    public final void bi() {
        byte by = 0;
        while (by < this.aC) {
            byte by2 = this.ax;
            byte by3 = this.s[by2];
            this.s[by2] = (byte)(by3 + 1);
            this.B[this.ax][by3] = this.aB[by];
            by = (byte)(by + 1);
        }
        if (this.j[this.ax] == this.aB[0]) {
            this.aW = true;
            this.d(this.aB[0], this.ax);
        }
        this.N(this.ax);
    }

    public final void bj() {
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        while (by3 < this.s[this.aw]) {
            if (by < this.aC && this.B[this.aw][by3] == this.aB[by]) {
                by = (byte)(by + 1);
            } else {
                this.B[this.aw][by2] = this.B[this.aw][by3];
                by2 = (byte)(by2 + 1);
            }
            by3 = (byte)(by3 + 1);
        }
        this.s[this.aw] = by2;
        this.N(this.aw);
    }

    public final void bk() {
        this.bi();
        this.bj();
    }

    public final void e(short s, byte by) {
        short s2 = s;
        this.d[s2] = (byte)(this.d[s2] - 1);
        byte by2 = 0;
        while (by2 < this.d[s]) {
            if (this.e[s][by2] == by) break;
            by2 = (byte)(by2 + 1);
        }
        byte by3 = by2;
        while (by3 < this.d[s]) {
            this.e[s][by3] = this.e[s][by3 + 1];
            by3 = (byte)(by3 + 1);
        }
    }

    public final void bl() {
        this.s[this.aw] = 0;
        this.k[this.aw] = 0;
        this.j[this.aw] = 0;
        this.v[this.aw] = 0;
        this.e((short)this.at, this.aw);
    }

    public final void j(int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        if (this.bM == 1) {
            this.bi();
            this.bl();
            this.aw = this.ax;
            this.bN = (byte)4;
        } else {
            this.az = this.aB[0];
            this.bN = (byte)2;
        }
        this.bg();
    }

    public final void c(short[] sArray, byte by) {
        byte by2 = 0;
        while (by2 < by) {
            this.W[sArray[by2]] = false;
            by2 = (byte)(by2 + 1);
        }
    }

    public final void k(int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.c(this.B[this.aw], this.s[this.aw]);
            this.aF();
            return;
        }
        boolean bl = false;
        this.aJ = true;
        this.aC = 0;
        byte by = 0;
        while (by < this.s[this.aw]) {
            if (this.W[this.B[this.aw][by]]) {
                if (by == 0) {
                    bl = true;
                }
                byte by2 = this.aC;
                this.aC = (byte)(by2 + 1);
                this.aB[by2] = this.B[this.aw][by];
                this.W[this.B[this.aw][by]] = false;
            }
            by = (byte)(by + 1);
        }
        this.aL = this.aB[0];
        if (this.bM == 1) {
            if (this.aC + this.s[this.ax] > 10) {
                this.a(this.aB[0], "\u79fb\u52a8\u6b66\u5c06\u6570\u8fc7\u591a!");
                this.bN = (byte)6;
                return;
            }
            this.aM = c.d[0][0];
        }
        if (this.aC == this.s[this.aw]) {
            this.aq.n();
            this.aW = true;
            this.aq.repaint();
            return;
        }
        if (this.bM == 1) {
            if (bl) {
                this.ay = this.aw;
                this.aK = false;
                this.bN = (byte)2;
            } else {
                this.bN = (byte)4;
            }
            this.bk();
        } else {
            this.bN = (byte)(this.bN + 1);
        }
        this.bg();
    }

    public final void l(int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.bN = (byte)(this.bN + 1);
        this.bg();
    }

    public final void m(int n) {
        this.l(n);
    }

    public final void bm() {
        if (!this.bh()) {
            return;
        }
        this.bg();
    }

    public final void a(short[] sArray) {
        byte by = this.aw;
        this.m[by] = (short)(this.m[by] - sArray[0]);
        byte by2 = this.aw;
        this.n[by2] = (short)(this.n[by2] - sArray[1]);
        byte by3 = this.aw;
        this.x[by3] = (byte)(this.x[by3] - sArray[2]);
        byte by4 = this.ax;
        this.m[by4] = (short)(this.m[by4] + sArray[0]);
        byte by5 = this.ax;
        this.n[by5] = (short)(this.n[by5] + sArray[1]);
        byte by6 = this.ax;
        this.x[by6] = (byte)(this.x[by6] + sArray[2]);
    }

    public final void a(int n, short[] sArray) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.a(sArray);
        this.bN = (byte)(this.bN + 1);
        this.a(this.k[this.aw], c.d[0][0]);
    }

    public final void a(short s, short s2, int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.ba = s;
        this.bb = s2;
        this.bN = (byte)6;
        this.aM = c.d[0][2];
        if (this.aC == this.s[this.aw]) {
            this.bl();
        } else {
            if (this.aB[0] == this.k[this.aw]) {
                this.aK = false;
                this.ay = this.aw;
                this.bN = (byte)3;
            } else {
                this.aK = false;
                this.aN = false;
                int n2 = 0;
                while (n2 < this.aC) {
                    this.aO[n2] = this.aB[n2];
                    ++n2;
                }
                this.aP = this.aC;
                this.bN = (byte)5;
            }
            this.bj();
        }
        this.bg();
    }

    public final void o(short s) {
        short s2 = this.az;
        this.Q[s2] = (byte)(this.Q[s2] + this.w(s));
        if (this.Q[this.az] > 99) {
            this.Q[this.az] = 99;
        }
        if (s == 200) {
            byte by = this.aw;
            this.x[by] = (byte)(this.x[by] - 1);
            return;
        }
        byte by = this.aw;
        this.m[by] = (short)(this.m[by] - s);
    }

    public final void a(short s, int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.o(s);
        this.bN = (byte)(this.bN + 1);
        this.a(this.az, c.d[2][2]);
    }

    public final void n(int n) {
        this.l(n);
    }

    public final void b(byte by, int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.av = by;
        this.bN = (byte)(this.bN + 1);
        this.bg();
    }

    public final void bn() {
        if (!this.bh()) {
            return;
        }
        this.bN = 0;
        this.bg();
    }

    public final void o(int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 1) {
            this.aF();
            return;
        }
        this.a(this.k[this.aw], c.d[4][0]);
        this.bN = (byte)(this.bN + 1);
    }

    public final void p(int n) {
        if (!this.bh()) {
            return;
        }
        switch (n) {
            case 0: {
                this.a(this.az, c.d[4][1]);
                this.bN = (byte)(this.bN + 1);
                return;
            }
            case 1: {
                this.aF();
                return;
            }
            case 2: {
                this.a(this.k[this.az], "\u8d44\u8d22\u4e0d\u8db3");
                this.bN = (byte)3;
                return;
            }
            case 3: {
                this.a(this.az, "\u542f\u7980\u4e3b\u516c,\u5c5e\u4e0b\u65e0\u6cd5\u4f7f\u7528\u6b64\u6b66\u5668");
                this.bN = (byte)3;
            }
        }
    }

    public final void q(int n) {
        if (!this.bh()) {
            return;
        }
        if (n == 5) {
            this.aF();
            return;
        }
        this.aQ = n;
        this.bN = (byte)(this.bN + 1);
        this.bg();
    }

    public final void O(byte by) {
        this.bM = by;
        this.bg();
    }

    public final void P(byte by) {
        this.bM = by;
        byte by2 = 0;
        while (by2 < this.F[this.aw]) {
            if (this.j[this.E[this.aw][by2]] == this.j[this.aw]) {
                this.aJ = false;
                this.bg();
                return;
            }
            by2 = (byte)(by2 + 1);
        }
        this.bN = (byte)6;
        this.a(this.k[this.aw], "\u5f53\u524d\u65e0\u53ef\u79fb\u52a8\u7684\u57ce!");
    }

    public final void Q(byte by) {
        this.bM = by;
        byte by2 = 0;
        while (by2 < this.F[this.aw]) {
            if (this.j[this.E[this.aw][by2]] == this.j[this.aw]) {
                this.bg();
                return;
            }
            by2 = (byte)(by2 + 1);
        }
        this.bN = (byte)3;
        this.a(this.k[this.aw], "\u5f53\u524d\u65e0\u53ef\u8f93\u9001\u7684\u57ce!");
    }

    public final void R(byte by) {
        this.bM = by;
        this.aP = 0;
        byte by2 = 0;
        while (by2 < this.s[this.aw]) {
            if (this.Q[this.B[this.aw][by2]] < 90) {
                byte by3 = this.aP;
                this.aP = (byte)(by3 + 1);
                this.aO[by3] = this.B[this.aw][by2];
            }
            by2 = (byte)(by2 + 1);
        }
        if (this.aP > 0) {
            this.aN = false;
            this.bg();
            return;
        }
        this.bN = (byte)3;
        this.a(this.k[this.aw], "\u540f\u58eb\u7686\u5fe0\u5fc3\u4e0d\u8d30!");
    }

    public final void S(byte by) {
        this.bM = by;
        if (this.s[this.aw] == 10) {
            this.a(this.k[this.aw], "\u672c\u57ce\u7f6e\u540f\u5df2\u8db3!");
            this.bN = (byte)4;
            return;
        }
        if (this.u[this.aw] == 0) {
            this.a(this.k[this.aw], "\u91ce\u65e0\u9057\u8d24!");
            this.bN = (byte)4;
            return;
        }
        this.aN = false;
        this.aP = this.u[this.aw];
        byte by2 = 0;
        while (by2 < this.aP) {
            this.aO[by2] = this.D[this.aw][by2];
            by2 = (byte)(by2 + 1);
        }
        this.bg();
    }

    public final void T(byte by) {
        this.bM = by;
        if (this.j[this.aw] == this.k[this.aw]) {
            this.a(this.j[this.aw], c.d[2][4]);
            this.bN = (byte)2;
            return;
        }
        this.bg();
    }

    public final boolean bo() {
        if (this.m[this.aw] == 0) {
            this.a(this.k[this.aw], "\u8d44\u8d22\u4e0d\u8db3");
            this.bN = (byte)4;
            return true;
        }
        return false;
    }

    public final void U(byte by) {
        this.bM = by;
        if (this.bo()) {
            return;
        }
        if (this.o[this.aw] == 999) {
            this.a(this.k[this.aw], c.d[3][2]);
            this.bN = (byte)4;
            return;
        }
        this.aR = 0;
        this.bN = 0;
        this.bg();
    }

    public final void V(byte by) {
        this.bM = by;
        if (this.bo()) {
            return;
        }
        if (this.p[this.aw] == 999) {
            this.a(this.k[this.aw], c.d[3][5]);
            this.bN = (byte)4;
            return;
        }
        this.aR = 1;
        this.bN = 0;
        this.bg();
    }

    public final void W(byte by) {
        this.bM = by;
        if (this.bo()) {
            return;
        }
        if (this.r[this.aw] == 99) {
            this.a(this.k[this.aw], c.d[3][6]);
            this.bN = (byte)4;
            return;
        }
        this.aR = 2;
        this.bN = 0;
        this.bg();
    }

    public final void X(byte by) {
        this.bM = by;
        if (this.bo()) {
            return;
        }
        if (this.q[this.aw] == 990000) {
            this.a(this.k[this.aw], c.d[3][7]);
            this.bN = (byte)4;
            return;
        }
        this.aR = 3;
        this.bN = 0;
        this.bg();
    }

    public final void Y(byte by) {
        this.bM = by;
        boolean bl = false;
        this.aV = 0;
        int n = 1;
        while (n <= 7) {
            if (this.a[n] > 0 && this.a[n] != this.at) {
                byte by2 = 0;
                while (by2 < this.X) {
                    if (this.Y[by2] == this.a[n]) break;
                    by2 = (byte)(by2 + 1);
                }
                if (by2 >= this.X) {
                    byte by3 = this.aV;
                    this.aV = (byte)(by3 + 1);
                    this.aS[by3] = this.a[n];
                }
            }
            n = (byte)(n + 1);
        }
        if (this.aV == 0) {
            this.a(this.k[this.aw], "\u5df2\u4e0e\u6240\u6709\u52bf\u529b\u540c\u76df");
            this.bN = (byte)4;
        }
        this.bg();
    }

    public final void Z(byte by) {
        this.bM = by;
        if (this.s[this.aw] == 10) {
            this.a(this.k[this.aw], "\u672c\u57ce\u7f6e\u540f\u5df2\u8db3!");
            this.bN = (byte)5;
            return;
        }
        this.bg();
    }

    public final void aa(byte by) {
        this.bM = by;
        if ((this.y[this.aw] & 8) != 8) {
            this.bN = (byte)2;
            this.a(this.k[this.aw], "\u672c\u57ce\u65e0\u7cae\u5e97,\u65e0\u6cd5\u4e70\u5356\u7cae\u8349");
            return;
        }
        this.bg();
    }

    public final void ab(byte by) {
        this.bM = by;
        if ((this.y[this.aw] & 1) != 1) {
            this.bN = (byte)3;
            this.a(this.k[this.aw], "\u672c\u57ce\u65e0\u6b66\u5668\u5e97,\u65e0\u6cd5\u8d2d\u5165\u6b66\u5668");
            return;
        }
        this.bg();
    }

    public final void ac(byte by) {
        this.bM = by;
        if ((this.y[this.aw] & 2) == 2) {
            this.aP = 0;
            byte by2 = 0;
            while (by2 < this.s[this.aw]) {
                if (this.O[this.B[this.aw][by2]] < 80) {
                    byte by3 = this.aP;
                    this.aP = (byte)(by3 + 1);
                    this.aO[by3] = this.B[this.aw][by2];
                }
                by2 = (byte)(by2 + 1);
            }
            if (this.aP > 0) {
                this.aN = false;
                this.bg();
                return;
            }
            this.a(this.k[this.aw], "\u672c\u57ce\u6b66\u5c06\u7686\u624d\u534e\u6a2a\u6ea2, \u65e0\u9700\u5b66\u4e60");
            this.bN = (byte)3;
            return;
        }
        this.a(this.k[this.aw], "\u672c\u57ce\u65e0\u5b66\u5802,\u65e0\u6cd5\u4fee\u4e60\u667a\u529b");
        this.bN = (byte)3;
    }

    public final void ad(byte by) {
        boolean bl = false;
        this.bM = by;
        if ((this.y[this.aw] & 4) != 4) {
            this.a(this.k[this.aw], "\u672c\u57ce\u65e0\u533b\u9662,\u65e0\u6cd5\u6cbb\u7597\u6b66\u5c06");
            this.bN = (byte)3;
            return;
        }
        this.aP = 0;
        byte by2 = 0;
        while (by2 < this.s[this.aw]) {
            if (this.M[this.B[this.aw][by2]] < this.L[this.B[this.aw][by2]]) {
                byte by3 = this.aP;
                this.aP = (byte)(by3 + 1);
                this.aO[by3] = this.B[this.aw][by2];
            }
            by2 = (byte)(by2 + 1);
        }
        if (this.aP > 0) {
            if (this.m[this.aw] >= 50) {
                this.aN = false;
                this.bg();
                return;
            }
            this.a(this.k[this.aw], "\u8d44\u8d22\u4e0d\u8db3");
            this.bN = (byte)3;
            return;
        }
        this.a(this.k[this.aw], "\u5c06\u58eb\u5747\u9f99\u7cbe\u864e\u731b!");
        this.bN = (byte)3;
    }

    public final void ae(byte by) {
        this.bM = by;
        byte by2 = 0;
        while (by2 < this.F[this.aw]) {
            byte by3 = this.E[this.aw][by2];
            if (this.j[by3] != this.b[this.at]) {
                byte by4 = 0;
                while (by4 < this.X) {
                    if (this.b[this.Y[by4]] == this.j[by3]) break;
                    by4 = (byte)(by4 + 1);
                }
                if (by4 >= this.X) break;
            }
            by2 = (byte)(by2 + 1);
        }
        if (by2 < this.F[this.aw]) {
            this.aJ = false;
            this.bg();
        } else {
            this.a(this.k[this.aw], "\u5f53\u524d\u65e0\u53ef\u6218\u4e89\u57ce\u5e02!");
            this.bN = (byte)7;
        }
        this.bg();
    }

    public final int r(int n) {
        int n2;
        n = n < 0 ? -1 : (n /= 1000);
        switch (n) {
            case -1: {
                n2 = 5;
                break;
            }
            case 0: {
                n2 = 10;
                break;
            }
            case 1: {
                n2 = 20;
                break;
            }
            case 2: {
                n2 = 50;
                break;
            }
            case 3: {
                n2 = 70;
                break;
            }
            default: {
                n2 = 80;
            }
        }
        if (this.s() % 250 > n2) {
            this.bM = (byte)-1;
        }
        return n;
    }

    public final int s(int n) {
        return this.r(n);
    }

    public final void a(byte by, short[] sArray, int n) {
        int n2 = 0;
        while (n2 < 10) {
            if (sArray[n2] == 0) break;
            ++n2;
        }
        int n3 = n2;
        n2 = n3 + 1;
        while (n2 < 10) {
            if (n3 >= n) break;
            if (sArray[n2] != 0) {
                sArray[n3] = sArray[n2];
                sArray[n2] = 0;
                ++n3;
            }
            ++n2;
        }
        this.v[by] = this.b(sArray, (byte)n);
    }

    public final void a(byte by, short[] sArray, byte by2) {
        this.k[by] = sArray[0];
    }

    public final void b(byte by, short[] sArray, byte by2) {
        this.a(by, sArray, by2);
    }

    public final void d(short[] sArray, byte n) {
        int n2 = 0;
        while (n2 < n) {
            if (sArray[n2] == this.j[this.aw]) {
                short s = sArray[0];
                sArray[0] = sArray[n2];
                sArray[n2] = s;
                return;
            }
            ++n2;
        }
    }

    public final byte a(short[] sArray, int n) {
        byte[] byArray = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        byte by = this.s[this.aw];
        int n2 = -1 * this.v[this.ax];
        byte by2 = 0;
        byte by3 = 0;
        n += 3;
        n = this.s() % n;
        n -= 2;
        n *= 1000;
        while (by > 1) {
            int n3 = 0;
            int n4 = this.s() % by + 1;
            while ((n3 += byArray[by3]) != n4) {
                if (++by3 != this.s[this.aw]) continue;
                by3 = 0;
            }
            sArray[by2] = this.B[this.aw][by3];
            this.B[this.aw][by3] = 0;
            by2 = (byte)(by2 + 1);
            byArray[by3] = 0;
            by = (byte)(by - 1);
            if ((n2 += this.K[sArray[by2]]) >= n) break;
        }
        this.s[this.aw] = by;
        this.a(this.aw, this.B[this.aw], (int)by);
        this.b(this.aw, this.B[this.aw], by);
        this.d(sArray, by2);
        return by2;
    }

    public final byte b(short[] sArray, int n) {
        return this.a(sArray, n);
    }

    public final int c(short[] sArray, int n) {
        int n2 = 0;
        n2 = this.b(sArray, (byte)n);
        n2 = n2 * 4 / 1000 + 1;
        int n3 = this.s() % (n2 * 30) + n2 * 30;
        if (n3 < this.n[this.aw] / 2) {
            n3 = this.n[this.aw] / 2;
        } else if (n3 >= this.n[this.aw]) {
            n3 = this.n[this.aw] * 2 / 3;
        }
        return n3;
    }

    public final void a(short[] sArray, byte n, int n2, int n3) {
        byte by = this.h(this.j[this.aw]);
        this.e[by][this.d[by]] = this.ax;
        int n4 = 0;
        while (n4 < n) {
            this.B[this.ax][n4] = sArray[n4];
            ++n4;
        }
        this.s[this.ax] = n;
        this.j[this.ax] = this.j[this.aw];
        this.k[this.ax] = sArray[0];
    }

    public final int c(byte by, int n) {
        if (by > 95) {
            return n * 2;
        }
        if (by > 90) {
            return n * 18 / 10;
        }
        if (by > 85) {
            return n * 15 / 10;
        }
        if (by > 50) {
            return n * 12 / 10;
        }
        return n;
    }

    public final int a(short[] sArray, byte by, byte by2, int n) {
        short s = 0;
        int n2 = n > 600 ? this.s() % 5 + 6 : (n > 450 ? this.s() % 10 + 11 : this.s() % 10 + 21);
        byte by3 = by2;
        this.n[by3] = (short)(this.n[by3] - this.v[by2] * 4 * n2 / 1000);
        s = this.b(sArray, by);
        return s * 4 * n2 / 1000;
    }

    public final void b(short s, int n) {
        if (this.K[s] > 0) {
            if (this.K[s] < n) {
                n = this.K[s];
            }
            this.K[s] = (short)(this.s() % (n + 1));
        }
        if (this.M[s] > 0) {
            this.M[s] = (byte)(this.s() % this.M[s] + 1);
        }
    }

    public final short i(short s, short s2) {
        if (s2 <= 0) {
            return 0;
        }
        short s3 = (short)(this.s() % s2);
        s2 = (short)(s2 - s3);
        short s4 = s;
        this.R[s4] = (short)(this.R[s4] + s3);
        this.f(s);
        return s2;
    }

    public final void c(short[] sArray, byte by, short s) {
        int n = 0;
        while (n < by - 1) {
            s = this.i(sArray[n], s);
            n = (byte)(n + 1);
        }
        short s2 = sArray[n];
        this.R[s2] = (short)(this.R[s2] + s);
        this.f(sArray[n]);
    }

    public final void bp() {
        byte by = this.h(this.j[this.aw]);
        byte by2 = this.h(this.j[this.ax]);
        this.e[by][this.d[by]] = this.ax;
        byte by3 = by;
        this.d[by3] = (byte)(this.d[by3] + 1);
        byte by4 = by2;
        this.d[by4] = (byte)(this.d[by4] - 1);
        if (this.d[by2] == 0) {
            this.a[by2] = -1;
            return;
        }
        int n = 0;
        while (n <= this.d[by2]) {
            if (this.e[by2][n] == this.ax) break;
            ++n;
        }
        this.e[by2][n] = this.e[by2][this.d[by2]];
    }

    public final void p(short s) {
        this.C[this.ax] = this.b(this.C[this.ax], this.t[this.ax], s);
        byte by = this.ax;
        this.t[by] = (byte)(this.t[by] + 1);
    }

    public final void q(short s) {
        this.m(s);
        this.B[this.ax][this.s[this.ax]] = s;
        byte by = this.ax;
        this.s[by] = (byte)(this.s[by] + 1);
    }

    public final byte a(short s, byte[] byArray, byte by, byte by2, short s2) {
        int n;
        byte by3 = by2;
        if (this.j[by3] == 0) {
            byte by4 = this.h(s2);
            this.e[by4][this.d[by4]] = by3;
            byte by5 = by4;
            this.d[by5] = (byte)(this.d[by5] + 1);
            this.j[by3] = this.b[by4];
            this.k[by3] = s;
            this.s[by3] = 1;
            this.B[by3][0] = s;
            this.v[by3] = this.K[s];
            return by;
        }
        if (s == s2) {
            n = this.s[by3];
            while (n > 0) {
                this.B[by3][n] = this.B[by3][n - 1];
                --n;
            }
            this.B[by3][0] = s;
            this.k[by3] = s;
        } else {
            this.B[by3][this.s[by3]] = s;
        }
        byte by6 = by3;
        this.s[by6] = (byte)(this.s[by6] + 1);
        byte by7 = by3;
        this.v[by7] = (short)(this.v[by7] + this.K[s]);
        if (this.s[by3] == 10 && (by = (byte)(by - 1)) != 0) {
            n = 0;
            while (n < by) {
                if (byArray[n] == by2) {
                    byArray[n] = byArray[by];
                    break;
                }
                n = (byte)(n + 1);
            }
        }
        return by;
    }

    public final void a(byte by, int n, int n2) {
        byte by2 = by;
        this.m[by2] = (short)(this.m[by2] + n2);
        byte by3 = by;
        this.n[by3] = (short)(this.n[by3] + n);
        if (this.m[by] > 9999) {
            this.m[by] = 9999;
        }
        if (this.n[by] > 9999) {
            this.n[by] = 9999;
        }
    }

    /*
     * Unable to fully structure code
     */
    public final boolean a(short[] var1_1, byte var2_2, short var3_3, int var4_4, int var5_5) {
        var6_6 = 0;
        var8_7 = new byte[this.F[this.ax]];
        var10_8 = 0;
        var12_9 = 0;
        var13_10 = false;
        var7_11 = 0;
        while (var7_11 < this.F[this.ax]) {
            var9_12 = this.E[this.ax][var7_11];
            if (this.j[var9_12] == 0 || this.j[var9_12] == var3_3 && this.s[var9_12] < 10) {
                var8_7[var6_6] = var9_12;
                var6_6 = (byte)(var6_6 + 1);
            }
            ++var7_11;
        }
        var7_11 = 0;
        while (var7_11 < var2_2) {
            block14: {
                block16: {
                    block15: {
                        block13: {
                            if (var6_6 != 0) break block13;
                            if (var7_11 != 0) ** GOTO lbl-1000
                            var10_8 = 1;
                            if (var1_1[var7_11] == var3_3) {
                                var13_10 = true;
                            } else if (this.s[this.ax] == 10) {
                                this.p(var1_1[var7_11]);
                            } else {
                                this.q(var1_1[var7_11]);
                            }
                            break block14;
                        }
                        if (this.s[this.ax] != 10) break block15;
                        var11_13 = this.b(var3_3, var8_7, var6_6, this.ax);
                        if (var7_11 == 0) {
                            var12_9 = var11_13;
                        }
                        var6_6 = this.a(var1_1[var7_11], var8_7, var6_6, (byte)var11_13, var3_3);
                        break block14;
                    }
                    if (this.M[var1_1[var7_11]] > 35 || this.K[var1_1[var7_11]] > 150) break block16;
                    var11_13 = this.O[var1_1[var7_11]] >= 95 || this.O[var1_1[var7_11]] >= 95 ? 10 : (this.O[var1_1[var7_11]] >= 90 || this.O[var1_1[var7_11]] >= 90 ? 20 : (this.O[var1_1[var7_11]] >= 80 || this.O[var1_1[var7_11]] >= 80 ? 40 : 80));
                    if (this.s() % 100 > var11_13) break block16;
                    if (var7_11 != 0) ** GOTO lbl46
                    if (var1_1[var7_11] == var3_3) {
                        v0 = this.b(var3_3, var8_7, var6_6, this.ax);
                        var11_13 = v0;
                        var12_9 = v0;
                        var6_6 = this.a(var1_1[var7_11], var8_7, var6_6, (byte)var11_13, var3_3);
                    } else {
                        var10_8 = 1;
lbl46:
                        // 2 sources

                        this.q(var1_1[var7_11]);
                    }
                    break block14;
                }
                var11_13 = this.b(var3_3, var8_7, var6_6, this.ax);
                if (var7_11 == 0) {
                    var12_9 = var11_13;
                }
                var6_6 = this.a(var1_1[var7_11], var8_7, var6_6, (byte)var11_13, var3_3);
            }
            ++var7_11;
        }
        if (var10_8 > 0) {
            this.a(this.ax, var4_4, var5_5);
        } else {
            this.a((byte)var12_9, var4_4, var5_5);
        }
        return var13_10;
    }

    public final void b(short[] sArray, byte n, int n2, int n3) {
        short[] sArray2 = this.B[this.ax];
        byte by = this.s[this.ax];
        short s = this.j[this.ax];
        byte by2 = this.h(s);
        this.bp();
        int n4 = 0;
        while (n4 < n) {
            this.B[this.ax][n4] = sArray[n4];
            ++n4;
        }
        this.s[this.ax] = n;
        this.j[this.ax] = this.j[this.aw];
        this.k[this.ax] = sArray[0];
        n4 = this.m[this.ax];
        this.m[this.ax] = (short)n3;
        n3 = n4;
        n4 = this.n[this.ax];
        this.n[this.ax] = (short)n2;
        n2 = n4;
        boolean bl = this.a(sArray2, by, s, n2, n3);
        if (bl && by2 > 0) {
            this.C(by2);
        }
    }

    public final void c(short[] sArray, byte by, int n, int n2) {
        short s = this.b(sArray, by);
        short s2 = this.b(this.B[this.ax], this.s[this.ax]);
        int n3 = 0;
        while (n3 < this.s[this.ax]) {
            this.b(this.B[this.ax][n3], 1000);
            ++n3;
        }
        n3 = 0;
        while (n3 < by) {
            this.b(sArray[n3], 1000);
            ++n3;
        }
        short s3 = this.b(this.B[this.ax], this.s[this.ax]);
        this.c(sArray, by, (short)(s2 - s3));
        s3 = this.b(sArray, by);
        this.c(this.B[this.ax], this.s[this.ax], (short)(s - s3));
        this.b(sArray, by, n, n2);
    }

    public final void a(short[] sArray, byte by, int n, int n2, int n3) {
        short s = this.b(sArray, by);
        short s2 = this.b(this.B[this.ax], this.s[this.ax]);
        int n4 = 0;
        while (n4 < this.s[this.ax]) {
            this.b(this.B[this.ax][n4], 300);
            ++n4;
        }
        n4 = 0;
        while (n4 < by) {
            this.b(sArray[n4], n3);
            ++n4;
        }
        short s3 = this.b(this.B[this.ax], this.s[this.ax]);
        this.c(sArray, by, (short)(s2 - s3));
        s3 = this.b(sArray, by);
        this.c(this.B[this.ax], this.s[this.ax], (short)(s - s3));
        this.b(sArray, by, n, n2);
    }

    public final void b(short[] sArray, byte by, int n, int n2, int n3) {
        short s = this.b(sArray, by);
        short s2 = this.b(this.B[this.ax], this.s[this.ax]);
        int n4 = 0;
        while (n4 < this.s[this.ax]) {
            this.b(this.B[this.ax][n4], n3);
            ++n4;
        }
        n4 = 0;
        while (n4 < by) {
            this.b(sArray[n4], 300);
            ++n4;
        }
        short s3 = this.b(this.B[this.ax], this.s[this.ax]);
        this.c(sArray, by, (short)(s2 - s3));
        s3 = this.b(sArray, by);
        this.c(this.B[this.ax], this.s[this.ax], (short)(s - s3));
        this.a(sArray, by, this.j[this.aw], n, n2);
    }

    public final int r(short s) {
        int n = (this.O[s] + this.N[s]) * (this.K[s] + 150);
        n = this.c(this.O[s], n);
        n = this.c(this.N[s], n);
        return n;
    }

    public final int d(short[] sArray, int n) {
        int n2 = 0;
        int n3 = 0;
        n3 = 0;
        while (n3 < n) {
            int n4 = this.r(sArray[n3]);
            n2 += n4;
            ++n3;
        }
        return n2;
    }

    public final boolean d(short[] sArray, byte by, int n, int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        n3 = this.d(sArray, (int)by);
        n4 = this.d(this.B[this.ax], (int)this.s[this.ax]);
        n5 = this.N[this.B[this.ax][0]] * this.K[this.B[this.ax][0]] * 3 / 2;
        n5 = this.c(this.N[this.B[this.ax][0]], n5);
        if ((n6 = (n5 = n3 - (n4 += n5)) > 0 ? 300 * n3 / n4 : 300 * n4 / n3) > 1000) {
            n6 = 1000;
        }
        if ((n -= this.a(sArray, by, this.ax, n6)) < 0) {
            n = 0;
        }
        if (this.n[this.ax] < 0) {
            this.n[this.ax] = 0;
            this.c(sArray, by, n, n2);
        } else if (n5 > 0) {
            this.a(sArray, by, n, n2, n6);
        } else {
            this.b(sArray, by, n, n2, n6);
            return false;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void bq() {
        long l = System.currentTimeMillis();
        while (this.aq.a == 0) {
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    a a2 = this;
                    synchronized (a2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.aq.repaint();
            l = System.currentTimeMillis();
        }
        return;
    }

    public final void t(int n) {
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        this.aC = this.b(this.aB, n);
        n2 = this.c(this.aB, (int)this.aC);
        n3 = this.m[this.aw] < 2 ? 0 : this.s() % (this.m[this.aw] / 2);
        byte by = this.aw;
        this.m[by] = (short)(this.m[by] - n3);
        byte by2 = this.aw;
        this.n[by2] = (short)(this.n[by2] - n2);
        System.gc();
        if (this.j[this.ax] == this.b[this.at]) {
            this.aQ = 1;
            this.M((byte)19);
            this.bq();
            this.bc = (short)n3;
            this.bd = (short)n2;
            this.as = (byte)3;
            return;
        }
        if (this.j[this.ax] == 0) {
            this.aQ = 2;
            this.M((byte)19);
            this.at();
            this.a(this.aB, this.aC, n2, n3);
        } else {
            short s = this.j[this.ax];
            byte by3 = this.h(s);
            this.aQ = 1;
            this.M((byte)19);
            this.at();
            this.aQ = this.d(this.aB, this.aC, n2, n3) ? 4 : 5;
            this.M((byte)19);
            this.at();
            if (this.a[by3] <= 0) {
                this.av = by3;
                this.aQ = 18;
                this.M((byte)19);
                this.at();
            } else if (this.b[by3] != s) {
                this.aA = s;
                this.av = by3;
                this.M((byte)19);
                this.at();
            }
        }
        this.v[this.ax] = this.b(this.B[this.ax], this.s[this.ax]);
        try {
            Thread.sleep(500L);
        }
        catch (Exception exception) {}
        System.gc();
    }

    public final void br() {
        int n = 0;
        boolean bl = false;
        byte by = this.e(this.au);
        while (this.aH < by) {
            this.aH = (byte)(this.aH + 1);
            this.bM = (byte)-1;
            this.aw = 0;
            this.ax = 0;
            System.gc();
            n = this.bu();
            if (this.bM == 3) {
                n = this.s(n);
                if (this.bM == 3) {
                    this.t(n);
                    if (this.as != 3) continue;
                    break;
                }
            }
            this.bw();
            if (this.bM == 2) {
                this.by();
                continue;
            }
            n = this.bA();
            if (this.bM == 1) {
                this.v(n);
                continue;
            }
            this.bC();
            if (this.bM == 19) {
                this.bE();
                continue;
            }
            this.bG();
            if (this.bM == 8) {
                this.bH();
                continue;
            }
            n = this.bJ();
            if (this.bM == 12) {
                this.x(n);
                continue;
            }
            this.bL();
        }
        this.aH = 0;
    }

    public final boolean af(byte by) {
        int n = 0;
        while (n < this.s[by]) {
            if (this.M[this.B[by][n]] < this.L[this.B[by][n]] && this.M[this.B[by][n]] <= 40) {
                return false;
            }
            ++n;
        }
        return true;
    }

    public final int x(byte by, byte by2) {
        int n = this.n[by] / 4 * 1000 / 45;
        if (!this.af(by)) {
            return -2000;
        }
        int n2 = n >= this.v[by] ? this.v[by] - this.v[by2] : n - this.v[by2];
        if (this.j[by2] == 0) {
            n2 -= 2500;
        }
        return n2;
    }

    public final int y(byte by, byte by2) {
        return this.x(by, by2);
    }

    public final boolean bs() {
        byte by = 0;
        while (by < this.X) {
            if (this.Y[by] == this.au) {
                return true;
            }
            by = (byte)(by + 1);
        }
        return false;
    }

    public final int bt() {
        int n = -1001;
        boolean bl = this.bs();
        byte by = 0;
        while (by < this.d[this.au]) {
            byte by2 = this.e[this.au][by];
            if (this.s[by2] > 2) {
                byte by3 = 0;
                while (by3 < this.F[by2]) {
                    int n2;
                    byte by4 = this.E[by2][by3];
                    if (!(this.j[by2] == this.j[by4] || bl && this.j[by4] == this.j[this.at] || (n2 = this.y(by2, by4)) <= n)) {
                        n = n2;
                        this.aw = by2;
                        this.ax = by4;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
        if (n >= -1000) {
            this.bM = (byte)3;
        }
        return n;
    }

    public final int bu() {
        return this.bt();
    }

    public final void bv() {
        if (this.s() % 3 > 0) {
            return;
        }
        byte by = 0;
        while (by < this.d[this.au]) {
            byte by2 = this.e[this.au][by];
            if (this.v[by2] < this.s[by2] * 1000 && (this.m[by2] >= 50 || this.w[by2] > 0)) {
                byte by3 = 0;
                while (by3 < this.F[by2]) {
                    if (this.j[this.E[by2][by3]] != this.j[by2]) {
                        this.bM = (byte)2;
                        this.aw = by2;
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void bw() {
        this.bv();
    }

    public final void bx() {
        int n = 0;
        while (n < this.v[this.aw] / 1000) {
            this.K[this.B[this.aw][n]] = 1000;
            n = (byte)(n + 1);
        }
        if (n < this.s[this.aw]) {
            this.K[this.B[this.aw][n]] = (short)(this.v[this.aw] % 1000);
        }
    }

    public final void by() {
        int n = this.s[this.aw] * 1000;
        int n2 = n - this.v[this.aw];
        int n3 = n2 - this.w[this.aw];
        if (n3 <= 0) {
            this.v[this.aw] = (short)n;
            byte by = this.aw;
            this.w[by] = (short)(this.w[by] - n2);
        } else {
            byte by = this.aw;
            this.v[by] = (short)(this.v[by] + this.w[this.aw]);
            int n4 = ((n3 - 1) / 100 + 1) * 20;
            if (this.m[this.aw] >= n4) {
                byte by2 = this.aw;
                this.m[by2] = (short)(this.m[by2] - n4);
                this.v[this.aw] = (short)n;
                this.w[this.aw] = (short)(n4 * 5 - n3);
            } else {
                this.w[this.aw] = 0;
                n4 = this.m[this.aw] / 20 * 20;
                byte by3 = this.aw;
                this.v[by3] = (short)(this.v[by3] + n4 * 5);
                byte by4 = this.aw;
                this.m[by4] = (short)(this.m[by4] - n4);
            }
        }
        this.bx();
    }

    public final boolean ag(byte by) {
        byte by2 = 0;
        while (by2 < this.F[by]) {
            if (this.j[this.E[by][by2]] != this.j[by]) {
                return true;
            }
            by2 = (byte)(by2 + 1);
        }
        return false;
    }

    public final int bz() {
        int n = 0;
        if (this.s() % 3 > 0) {
            return 0;
        }
        byte by = 0;
        while (by < this.d[this.au]) {
            block11: {
                byte by2;
                byte by3;
                byte by4;
                block7: {
                    by4 = this.e[this.au][by];
                    int n2 = 0;
                    by3 = 0;
                    while (by3 < this.F[by4]) {
                        n2 = this.j[this.E[by4][by3]] == this.j[by4] ? (int)((byte)(n2 | 1)) : (int)((byte)(n2 | 2));
                        if (n2 == 3) break;
                        by3 = (byte)(by3 + 1);
                    }
                    if (by3 >= this.F[by4]) break;
                    if (this.s[by4] >= 3) break block7;
                    by3 = 0;
                    while (by3 < this.F[by4]) {
                        block8: {
                            block10: {
                                block9: {
                                    by2 = this.E[by4][by3];
                                    if (this.j[by2] != this.j[by4] || this.s[by2] <= 1) break block8;
                                    if (!this.ag(by2)) break block9;
                                    if (this.s[by2] < 4) break block8;
                                    n = (this.s[by2] - this.s[by4]) / 2;
                                    break block10;
                                }
                                n = this.s[by2] - 1;
                            }
                            this.bM = 1;
                            this.aw = by4;
                            this.ax = by2;
                            return n;
                        }
                        by3 = (byte)(by3 + 1);
                    }
                    break block11;
                }
                if (this.s[by4] < 6) {
                    by3 = 0;
                    while (by3 < this.F[by4]) {
                        by2 = this.E[by4][by3];
                        if (this.j[by2] == this.j[by4] && this.s[by2] > 2 && !this.ag(by2)) {
                            this.bM = 1;
                            this.aw = by4;
                            this.ax = by2;
                            n = this.s[by2] - 2;
                            return n;
                        }
                        by3 = (byte)(by3 + 1);
                    }
                }
            }
            by = (byte)(by + 1);
        }
        return 0;
    }

    public final int bA() {
        return this.bz();
    }

    public final void u(int n) {
        int n2 = 0;
        while (n2 < n) {
            byte by = this.ax;
            this.s[by] = (byte)(this.s[by] - 1);
            this.B[this.aw][this.s[this.aw]] = this.B[this.ax][this.s[this.ax]];
            short s = this.K[this.B[this.aw][this.s[this.aw]]];
            byte by2 = this.aw;
            this.v[by2] = (short)(this.v[by2] + s);
            byte by3 = this.ax;
            this.v[by3] = (short)(this.v[by3] - s);
            this.B[this.ax][this.s[this.ax]] = 0;
            byte by4 = this.aw;
            this.s[by4] = (byte)(this.s[by4] + 1);
            if (this.s[this.aw] >= 10) {
                return;
            }
            ++n2;
        }
    }

    public final void v(int n) {
        this.u(n);
    }

    public final void bB() {
        if (this.s() % 3 > 0) {
            return;
        }
        byte by = 0;
        while (by < this.d[this.au]) {
            byte by2 = this.e[this.au][by];
            if ((this.y[by2] & 4) == 4 && this.m[by2] >= 100 && this.ag(by2)) {
                byte by3 = 0;
                while (by3 < this.s[by2]) {
                    if (this.L[this.B[by2][by3]] - 40 > this.M[this.B[by2][by3]]) {
                        this.bM = (byte)19;
                        this.aw = by2;
                        this.az = this.B[by2][by3];
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void bC() {
        this.bB();
    }

    public final int bD() {
        return 35 + this.s() % 17;
    }

    public final void bE() {
        short s = this.az;
        this.M[s] = (byte)(this.M[s] + this.bD());
        if (this.M[this.az] > this.L[this.az]) {
            this.M[this.az] = this.L[this.az];
        }
        byte by = this.aw;
        this.m[by] = (short)(this.m[by] - 50);
    }

    public final void bF() {
        if (this.s() % 5 > 0) {
            return;
        }
        byte by = 0;
        while (by < this.d[this.au]) {
            byte by2 = this.e[this.au][by];
            if (this.m[by2] >= 200 || this.x[by2] != 0) {
                byte by3 = 0;
                while (by3 < this.s[by2]) {
                    if (this.Q[this.B[by2][by3]] < 50) {
                        this.bM = (byte)8;
                        this.aw = by2;
                        this.az = this.B[by2][by3];
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void bG() {
        this.bF();
    }

    public final int w(int n) {
        if (n == 200) {
            return 11 + this.s() % 8;
        }
        int n2 = n / 8;
        int n3 = this.s() % 8;
        if (n3 < n % 8) {
            ++n2;
        }
        if (n2 > 7) {
            n2 += this.s() % 2 - 1;
        }
        return n2;
    }

    public final void bH() {
        if (this.x[this.aw] > 0) {
            short s = this.az;
            this.Q[s] = (byte)(this.Q[s] + this.w(200));
            byte by = this.aw;
            this.x[by] = (byte)(this.x[by] - 1);
        } else {
            short s = this.az;
            this.Q[s] = (byte)(this.Q[s] + this.w(100));
            byte by = this.aw;
            this.m[by] = (short)(this.m[by] - 100);
        }
        if (this.Q[this.az] > 99) {
            this.Q[this.az] = 99;
        }
    }

    public final int bI() {
        int n = 0;
        if (this.s() % 3 > 0) {
            return 0;
        }
        byte by = 0;
        while (by < this.d[this.au]) {
            block6: {
                byte by2 = this.e[this.au][by];
                if (!this.ag(by2) || this.m[by2] + this.n[by2] >= 400) break block6;
                byte by3 = 0;
                while (by3 < this.F[by2]) {
                    block7: {
                        byte by4;
                        block9: {
                            block8: {
                                by4 = this.E[by2][by3];
                                if (this.b[this.au] != this.j[by4]) break block7;
                                if (!this.ag(by4) || this.m[by4] + this.n[by4] < 1500) break block8;
                                n = 1;
                                break block9;
                            }
                            if (this.ag(by4) || this.m[by4] + this.n[by4] < 800) break block7;
                            n = 0;
                        }
                        this.bM = (byte)12;
                        this.aw = by2;
                        this.ax = by4;
                        return n;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
        return 0;
    }

    public final int bJ() {
        return this.bI();
    }

    public final void x(int n) {
        int n2;
        int n3;
        if (n == 0) {
            n3 = this.m[this.ax] - 100;
            n2 = this.n[this.ax] - 100;
        } else {
            n3 = this.m[this.ax] - (this.m[this.aw] + this.m[this.ax]) / 2;
            n2 = this.n[this.ax] - (this.n[this.aw] + this.n[this.ax]) / 2;
        }
        if (n3 > 0) {
            byte by = this.aw;
            this.m[by] = (short)(this.m[by] + n3);
            byte by2 = this.ax;
            this.m[by2] = (short)(this.m[by2] - n3);
        }
        if (n2 >= 0) {
            byte by = this.aw;
            this.n[by] = (short)(this.n[by] + n2);
            byte by3 = this.ax;
            this.n[by3] = (short)(this.n[by3] - n2);
        }
    }

    public final void b(byte by, short s) {
        if (this.O[by] < 30) {
            byte by2 = by;
            this.m[by2] = (short)(this.m[by2] - 30);
        } else if (this.O[by] < 60) {
            byte by3 = by;
            this.m[by3] = (short)(this.m[by3] - 20);
        } else {
            byte by4 = by;
            this.m[by4] = (short)(this.m[by4] - 10);
        }
        short s2 = s;
        this.O[s2] = (byte)(this.O[s2] + this.n(s));
    }

    public final boolean ah(byte by) {
        byte by2 = 0;
        short s = 0;
        if ((this.y[by] & 8) == 8) {
            if (this.m[by] < 100 && this.n[by] > 500) {
                byte by3 = by;
                this.n[by3] = (short)(this.n[by3] - 200);
                byte by4 = by;
                this.m[by4] = (short)(this.m[by4] + 150);
                return true;
            }
            if (this.n[by] < 200 && this.m[by] > 500) {
                byte by5 = by;
                this.m[by5] = (short)(this.m[by5] - 200);
                byte by6 = by;
                this.n[by6] = (short)(this.n[by6] + 266);
                return true;
            }
        }
        if (this.m[by] < 50 || (this.y[by] & 2) != 2) {
            return false;
        }
        byte by7 = 0;
        while (by7 < this.s[by]) {
            if (by2 < this.N[this.B[by][by7]] && this.O[this.B[by][by7]] < 80) {
                s = this.B[by][by7];
                by2 = this.N[s];
            }
            by7 = (byte)(by7 + 1);
        }
        if (by2 > 0) {
            this.b(by, s);
            return true;
        }
        return false;
    }

    public final void b(byte by, short s, short s2) {
        if (this.j[by] == this.b[this.at]) {
            this.aQ = 16;
            this.aA = s2;
            this.M((byte)19);
            this.at();
        }
        this.f(s, s2);
    }

    public final void b(byte by, byte by2, short s, short s2) {
        if (this.a(by, by2, s, s2) && this.j[by2] == this.b[this.at]) {
            this.aQ = 17;
            this.aw = by;
            this.aA = s2;
            this.M((byte)19);
            this.at();
        }
    }

    public final boolean ai(byte by) {
        byte by2 = 0;
        byte by3 = 100;
        byte by4 = 1;
        while (by4 < 37) {
            by2 = by4;
            if (this.j[by2] != this.j[by]) {
                byte by5 = 0;
                while (by5 < this.s[by2]) {
                    if (by3 > this.Q[this.B[by2][by5]]) {
                        this.ax = by2;
                        this.aA = this.B[by2][by5];
                        by3 = this.Q[this.aA];
                    }
                    by5 = (byte)(by5 + 1);
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (by3 == 100) {
            return false;
        }
        this.az = this.B[by][0];
        byte by6 = this.O[this.az];
        by4 = 1;
        while (by4 < this.s[by]) {
            if (by6 < this.O[this.B[by][by4]]) {
                this.az = this.B[by][by4];
                by6 = this.O[this.az];
            }
            by4 = (byte)(by4 + 1);
        }
        if (this.s[by] < 10 && (by3 < 15 || by3 < 35 && this.s() % 2 > 0)) {
            this.b(by, this.ax, this.az, this.aA);
        } else {
            this.b(this.ax, this.az, this.aA);
        }
        return true;
    }

    public final boolean j(short s, short s2) {
        byte by = this.P[s];
        byte by2 = this.O[s2] > this.N[s2] ? this.O[s2] : this.N[s2];
        by2 = by2 > this.P[s2] ? by2 : this.P[s2];
        Math.abs(this.ar.nextInt());
        boolean bl = false;
        return by2 - by < this.s() % 40;
    }

    public final short[] d(short[] sArray, byte by, short s) {
        if (by <= 1) {
            return null;
        }
        short[] sArray2 = new short[by - 1];
        int n = 0;
        byte by2 = 0;
        while (by2 < by) {
            if (sArray[by2] != s) {
                int n2 = n;
                n = (byte)(n2 + 1);
                sArray2[n2] = sArray[by2];
            }
            by2 = (byte)(by2 + 1);
        }
        return sArray2;
    }

    public final boolean c(byte by, short s, short s2) {
        if (this.j(s, s2)) {
            this.B[by][this.s[by]] = s2;
            byte by2 = by;
            this.v[by2] = (short)(this.v[by2] + this.K[s2]);
            byte by3 = by;
            this.s[by3] = (byte)(this.s[by3] + 1);
            this.D[by] = this.d(this.D[by], this.u[by], s2);
            byte by4 = by;
            this.u[by4] = (byte)(this.u[by4] - 1);
            return true;
        }
        return false;
    }

    public final int f(short s, byte by) {
        int n;
        byte by2 = this.O[s];
        byte by3 = this.P[s];
        int n2 = this.s() % 120;
        int n3 = 0;
        if (n2 > by2) {
            return 0;
        }
        if (n2 > 79) {
            int n4 = this.s() % 100;
            n3 = n4 < 5 ? 4 : (n4 < 15 ? 3 : (n4 < 40 ? 2 : 1));
            n = 4;
        } else if (n2 > 59 && by != 0) {
            n3 = this.s() % by;
            n = 3;
        } else {
            int n5 = by3 * 12 / 10;
            int n6 = by3 * 17 / 10;
            n3 = (n5 + this.s() % (n6 - n5)) * 6 / 10;
            n = n2 % 2 == 1 ? 2 : 1;
        }
        return n += (n3 <<= 8);
    }

    public final void c(byte by, short s) {
        this.aQ = this.f(s, this.t[by]);
        this.aR = this.aQ & 0xFF;
        this.aQ >>= 8;
        if (this.aR == 1) {
            byte by2 = by;
            this.n[by2] = (short)(this.n[by2] + this.aQ);
            if (this.n[by] > 9999) {
                this.n[by] = 9999;
                return;
            }
        } else if (this.aR == 2) {
            byte by3 = by;
            this.m[by3] = (short)(this.m[by3] + this.aQ);
            if (this.m[by] > 9999) {
                this.m[by] = 9999;
                return;
            }
        } else {
            if (this.aR == 3) {
                this.D[by] = this.b(this.D[by], this.u[by], this.C[by][this.aQ]);
                this.C[by] = this.d(this.C[by], this.t[by], this.C[by][this.aQ]);
                byte by4 = by;
                this.u[by4] = (byte)(this.u[by4] + 1);
                byte by5 = by;
                this.t[by5] = (byte)(this.t[by5] - 1);
                return;
            }
            if (this.aR == 4) {
                byte by6 = by;
                this.x[by6] = (byte)(this.x[by6] + this.aQ);
                if (this.x[by] > 99) {
                    this.x[by] = 99;
                }
            }
        }
    }

    public final short aj(byte by) {
        short[] sArray = new short[this.s[by]];
        int n = 0;
        byte by2 = 0;
        byte by3 = 0;
        while (by3 < this.s[by]) {
            if (by2 < this.O[this.B[by][by3]]) {
                this.az = this.B[by][by3];
                by2 = this.O[this.az];
            }
            if (this.O[this.B[by][by3]] >= 70) {
                int n2 = n;
                n = (byte)(n2 + 1);
                sArray[n2] = this.B[by][by3];
            }
            by3 = (byte)(by3 + 1);
        }
        if (n == 0) {
            return this.az;
        }
        return sArray[this.s() % n];
    }

    public final void ak(byte by) {
        this.az = this.aj(by);
        if (this.s[by] < 10 && this.u[by] > 0) {
            this.aA = this.D[by][this.s() % this.u[by]];
            this.c(by, this.az, this.aA);
            return;
        }
        this.c(by, this.az);
    }

    public final int s(short s) {
        byte by = this.O[s];
        int n = 10;
        if (by > 70) {
            n += 8;
        } else if (by > 50) {
            n += 4;
        }
        return n += 2 * (this.s() % 5);
    }

    public final int c(short s, int n) {
        byte by = this.O[s];
        int n2 = 0;
        if (by < 12) {
            return 1;
        }
        int n3 = by * n / 100;
        int n4 = n3 * 7 / 10;
        n2 = n4 + this.s() % (n3 - n4 + 1);
        return n2;
    }

    public final int t(short s) {
        byte by = this.O[s];
        int n = 10;
        if (by > 70) {
            n += 6;
        } else if (by > 50) {
            n += 3;
        }
        return n += this.s() % 4;
    }

    public final int d(short s, int n) {
        byte by = this.O[s];
        int n2 = 0;
        if (by < 12) {
            return 1;
        }
        int n3 = by * n / 100;
        int n4 = n3 / 2;
        n2 = n4 + this.s() % (n3 - n4 + 1);
        return n2;
    }

    public final int e(short s, int n) {
        byte by = this.O[s];
        int n2 = 0;
        if (by < 17) {
            return 1000;
        }
        n2 = by * n / 100;
        int n3 = n2 / 2;
        int n4 = n2 - 1;
        n2 = n3 + this.s() % (n4 - n3 + 1);
        return n2 *= 100;
    }

    public final void d(byte by, short s) {
        int n = this.s(s);
        this.a(by, s, n);
    }

    public final void e(byte by, short s) {
        int n = this.s(s);
        this.b(by, s, n);
    }

    public final void f(byte by, short s) {
        int n = this.s(s);
        this.d(by, s, n);
    }

    public final void g(byte by, short s) {
        int n = this.t(s);
        this.c(by, s, n);
    }

    public final void al(byte by) {
        if (this.r[by] < 60) {
            this.g(by, this.az);
            return;
        }
        if (this.s() % 2 > 0) {
            if (this.o[by] < 999 && this.p[by] < 999) {
                if (this.aE < 4 || this.aE >= 10) {
                    this.d(by, this.az);
                    return;
                }
                this.e(by, this.az);
                return;
            }
            if (this.o[by] < 999) {
                this.d(by, this.az);
                return;
            }
            if (this.p[by] < 999) {
                this.e(by, this.az);
                return;
            }
        }
        if (this.q[by] < 990000) {
            this.f(by, this.az);
            return;
        }
        this.g(by, this.az);
    }

    public final void am(byte by) {
        this.az = this.aj(by);
        this.al(by);
    }

    public final void bK() {
        int n = this.s() % this.d[this.au];
        byte by = this.e[this.au][n];
        int n2 = this.m[by] >= 50 && (this.o[by] < 999 || this.p[by] < 999 || this.q[by] < 990000 || this.r[by] < 99) ? 350 : 150;
        int n3 = 0;
        while (n3 < 10) {
            n = this.s() % n2;
            if (n < 10) {
                if (this.ah(by)) {
                    break;
                }
            } else if (n < 50) {
                if (this.ai(by)) {
                    break;
                }
            } else {
                if (n < 150) {
                    this.ak(by);
                    break;
                }
                if (n < 350) {
                    this.am(by);
                    break;
                }
            }
            n3 = (byte)(n3 + 1);
        }
        if (n3 == 10) {
            this.ak(by);
        }
    }

    public final void bL() {
        this.bK();
    }

    public final boolean k(short s, short s2) {
        if (this.Q[s2] > 99) {
            return false;
        }
        int n = (this.P[s] * 7 + this.O[s] * 3) * 10 / 99;
        if ((n = (n - 70) / 5) == 4) {
            n = 6;
        } else if (n == 5) {
            n = 8;
        }
        n = this.M[s2] + this.Q[s2] - n + this.ar.nextInt() % 4;
        return n < 100;
    }

    public final int a(short s, short s2, short s3) {
        int n = this.N[s];
        n *= s2;
        if ((n /= s3) > 2 * this.N[s]) {
            n = 2 * this.N[s];
        }
        if (n < this.N[s] / 3) {
            n = this.N[s] / 3;
        }
        n += n / 10;
        return n;
    }

    public final byte b(short s, short s2, short s3) {
        byte by = (byte)(this.a(s, s2, s3) / 20);
        if (by > 0) {
            return by;
        }
        return 1;
    }

    public final byte c(short s, short s2, short s3) {
        byte by = 0;
        if (this.cx && this.aA == 4 && s != 4) {
            this.cx = false;
        }
        by = s == this.az ? (byte)(7 + this.cy) : (byte)(7 - this.cy);
        if (this.s() % 10 < by) {
            return (byte)(this.a(s, s2, s3) / 5);
        }
        return 0;
    }

    public final byte d(short s, short s2, short s3) {
        byte by = 0;
        this.bM = (byte)3;
        if (this.cx && this.aA == 4 && s != 4) {
            this.cx = false;
        }
        by = s == this.az ? (byte)(2 + this.cy) : (byte)(2 - this.cy);
        if (this.s() % 10 < by) {
            return (byte)(this.a(s, s2, s3) / 2);
        }
        return (byte)(this.ar.nextInt() % 5 - 18);
    }

    public final short e(boolean bl) {
        short s = 0;
        if (bl) {
            int n = 1;
            while (n < 4) {
                s = (short)(s + this.cd[n]);
                n = (byte)(n + 1);
            }
        } else {
            int n = 1;
            while (n < 4) {
                s = (short)(s + this.ce[n]);
                n = (byte)(n + 1);
            }
        }
        return s;
    }
}

