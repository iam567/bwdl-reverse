/*
 * Decompiled with CFR 0.152.
 */
import java.io.InputStream;
import java.util.Random;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreNotOpenException;

public final class b
extends Thread {
    public byte[] a = new byte[9];
    public short[] b = new short[9];
    public byte[] c = new byte[9];
    public byte[][] d = new byte[9][];
    public byte[] e;
    public short[][] f;
    public byte[] g;
    public String[] h;
    public short[] i;
    public short[] j;
    public byte[] k;
    public short[] l;
    public short[] m;
    public short[] n;
    public short[] o;
    public int[] p;
    public byte[] q;
    public byte[] r;
    public byte[] s;
    public byte[] t;
    public short[] u;
    public short[] v;
    public byte[] w;
    public byte[] x;
    public byte[] y;
    public byte[] z;
    public short[][] A;
    public short[][] B;
    public short[][] C;
    public byte[][] D;
    public byte[] E;
    public short[] F;
    public String[] G;
    public byte[] H;
    public byte[] I;
    public short[] J;
    public byte[] K;
    public byte[] L;
    public byte[] M;
    public byte[] N;
    public byte[] O;
    public byte[] P;
    public short[] Q;
    public byte[] R;
    public byte[] S;
    public short[] T;
    public byte[] U;
    public boolean[] V;
    public byte W;
    public byte[] X;
    public byte[] Y;
    public byte Z;
    public short[] aa;
    public short[] ab;
    public short[] ac;
    public String[] ad;
    public short[] ae;
    public byte[] af;
    public byte[][] ag;
    public byte[][] ah;
    public byte[][] ai;
    public byte[][] aj;
    public short[] ak;
    public byte[] al;
    public short[] am;
    public String[] an;
    public c ao;
    public Random ap;
    public byte aq;
    public byte ar;
    public byte as;
    public byte at;
    public byte au;
    public byte av;
    public byte aw;
    public short ax;
    public short ay;
    public short[] az;
    public byte aA;
    public byte[] aB;
    public byte aC;
    public short aD;
    public byte aE;
    public byte aF;
    public byte aG;
    public boolean aH;
    public boolean aI;
    public short aJ;
    public String aK;
    public boolean aL;
    public short[] aM;
    public byte aN;
    public int aO;
    public int aP;
    public byte[] aQ;
    public byte[] aR;
    public byte aS;
    public byte aT;
    public boolean aU;
    public byte[] aV;
    public byte[] aW;
    public byte aX;
    public byte aY;
    public short aZ;
    public short ba;
    public byte bb;
    public short bc;
    public short bd;
    public short be;
    public short bf;
    public short[] bg;
    public short[] bh;
    public byte bi;
    public byte bj;
    public byte bk;
    public byte bl;
    public byte[][] bm;
    public byte bn;
    public byte bo;
    public boolean bp;
    public byte[] bq;
    public byte[] br;
    public byte[] bs;
    public byte[] bt;
    public byte[] bu;
    public byte[] bv;
    public byte bw;
    public byte bx;
    public byte by;
    public byte bz;
    public byte bA;
    public boolean bB;
    public byte bC;
    public String bD;
    public boolean bE;
    public byte bF;
    public byte bG;
    public byte bH;
    public byte bI;
    public byte bJ;
    public byte[][] bK;
    public byte bL;
    public byte bM;
    public byte bN;
    public byte bO;
    public byte bP;
    public byte bQ;
    public byte[][] bR;
    public byte[][] bS;
    public byte[][] bT;
    public byte[][] bU;
    public short[][] bV;
    public short[][] bW;
    public byte[][] bX;
    public byte[][] bY;
    public byte[][] bZ;
    public boolean[][] ca;
    public boolean[][] cb;
    public byte cc;
    public byte cd;
    public byte ce;
    public byte cf;
    public short[] cg;
    public short[] ch;
    public byte ci;
    public byte[] cj;
    public byte ck;
    public boolean cl;
    public boolean cm;
    public byte cn;
    public byte co;
    public byte cp;
    public byte cq;
    public byte cr;
    public byte cs;
    public byte ct;
    public byte cu;
    public boolean cv;
    public boolean cw;
    public short cx;
    public short cy;
    public short cz;
    public short cA;
    public boolean cB;
    public byte cC;
    public byte cD;
    public byte cE;
    public byte cF;
    public int cG;
    public boolean cH;
    public byte cI;
    public int cJ;
    public byte cK;
    public byte cL;
    public byte[] cM;
    public int cN;
    public boolean cO;
    public byte cP;
    public byte cQ;
    public int cR;
    public int cS;
    public byte cT;
    public int cU;
    public int cV;
    public byte[] cW;
    public String[][] cX;
    public short[] cY;
    public byte[] cZ;
    public byte[] da;
    public String db;
    public String dc;
    public String dd;

    public final int a(int n, int n2, int n3, int n4, int n5) {
        return (n - n2) * (n5 - n4 + 1) / (n3 - n2 + 1) + n4;
    }

    public final int a(int n, int n2, int n3) {
        return n * 1000 / (n3 - n2 + 1);
    }

    public final void a(int n, int n2, byte by) {
        if (n < this.cU) {
            this.cU = n;
            if (n >= 1000) {
                this.cT = (byte)8;
                return;
            }
            this.cT = by;
            this.cV = n2;
            return;
        }
        if (n < 1000 && n == this.cU && n2 < this.cV) {
            this.cT = by;
            this.cV = n2;
        }
    }

    public final void a(int n, int n2) {
        int n3;
        int n4 = 0;
        if (n >= 999) {
            n3 = 2000;
            this.cW[n2] = 6;
            this.cR += 100;
        } else if (n >= 900) {
            n3 = 2000;
            this.cW[n2] = 5;
            this.cR += this.a(n, 900, 998, 85, 90);
        } else if (n >= 700) {
            n3 = 1000;
            this.cW[n2] = 4;
            this.cR += this.a(n, 700, 899, 80, 84);
        } else if (n >= 500) {
            n3 = 1000;
            this.cW[n2] = 3;
            this.cR += this.a(n, 500, 699, 60, 79);
        } else if (n >= 200) {
            n3 = 750;
            this.cW[n2] = 2;
            n4 = this.a(n, 200, 499);
            this.cR += this.a(n, 200, 499, 30, 59);
        } else if (n >= 100) {
            n3 = 500;
            this.cW[n2] = 1;
            n4 = this.a(n, 100, 199);
            this.cR += this.a(n, 100, 199, 1, 29);
        } else {
            n3 = 250;
            this.cW[n2] = 0;
            n4 = this.a(n, 0, 99);
        }
        this.a(n3, n4, (byte)0);
    }

    public final void a() {
        int n = 0;
        int n2 = 1;
        while (n2 < 37) {
            n += this.n[n2];
            n2 = (byte)(n2 + 1);
        }
        this.a(n /= 36, 0);
    }

    public final void b() {
        int n = 0;
        int n2 = 1;
        while (n2 < 37) {
            n += this.o[n2];
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
            n2 += this.q[n4];
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 36) >= 99) {
            n = 2000;
            this.cW[2] = 4;
            this.cR += 30;
        } else if (n2 >= 90) {
            n = 1000;
            this.cW[2] = 3;
            this.cR += this.a(n2, 90, 98, 10, 20);
        } else if (n2 >= 70) {
            n = 1000;
            this.cW[2] = 2;
            this.cR += this.a(n2, 70, 89, 5, 9);
        } else if (n2 >= 50) {
            n = 667;
            this.cW[2] = 1;
            n3 = this.a(n2, 50, 69);
            this.cR += this.a(n2, 50, 69, 1, 4);
        } else {
            n = 333;
            this.cW[2] = 0;
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
            n2 += this.p[n4];
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 36) >= 990000) {
            n = 2000;
            this.cW[3] = 5;
            this.cR += 100;
        } else if (n2 >= 500000) {
            n = 2000;
            this.cW[3] = 4;
            this.cR += this.a(n2, 500000, 989999, 80, 90);
        } else if (n2 >= 200000) {
            n = 1000;
            this.cW[3] = 3;
            this.cR += this.a(n2, 200000, 499999, 70, 79);
        } else if (n2 >= 100000) {
            n = 1000;
            this.cW[3] = 2;
            this.cR += this.a(n2, 100000, 199999, 50, 69);
        } else if (n2 >= 50000) {
            n = 667;
            this.cW[3] = 1;
            n3 = this.a(n2, 50000, 99999);
            this.cR += this.a(n2, 50000, 99999, 1, 49);
        } else {
            n = 333;
            this.cW[3] = 0;
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
            n2 += this.k[n4];
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 36) >= 99) {
            n = 2000;
            this.cW[5] = 5;
            this.cR += 70;
        } else if (n2 >= 90) {
            n = 2000;
            this.cW[5] = 4;
            this.cR += this.a(n2, 90, 98, 40, 60);
        } else if (n2 >= 80) {
            n = 1000;
            this.cW[5] = 3;
            this.cR += this.a(n2, 80, 89, 30, 39);
        } else if (n2 >= 60) {
            n = 1000;
            this.cW[5] = 2;
            this.cR += this.a(n2, 60, 79, 10, 29);
        } else if (n2 >= 30) {
            n = 667;
            this.cW[5] = 1;
            n3 = this.a(n2, 30, 59);
            this.cR += this.a(n2, 30, 59, 1, 9);
        } else {
            n = 333;
            this.cW[5] = 0;
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
            while (by < this.r[n4]) {
                n2 += this.J[this.A[n4][by]];
                by = (byte)(by + 1);
            }
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 222) >= 1000) {
            n = 2000;
            this.cW[6] = 5;
            this.cR += 100;
        } else if (n2 >= 900) {
            n = 2000;
            this.cW[6] = 4;
            this.cR += this.a(n2, 900, 999, 60, 80);
        } else if (n2 >= 800) {
            n = 1000;
            this.cW[6] = 3;
            this.cR += this.a(n2, 800, 899, 50, 59);
        } else if (n2 >= 700) {
            n = 1000;
            this.cW[6] = 2;
            this.cR += this.a(n2, 700, 799, 20, 49);
        } else if (n2 >= 500) {
            n = 667;
            this.cW[6] = 1;
            n3 = this.a(n2, 500, 699);
            this.cR += this.a(n2, 500, 699, 1, 19);
        } else {
            n = 333;
            this.cW[6] = 0;
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
            while (by < this.r[n4]) {
                n2 += this.P[this.A[n4][by]];
                by = (byte)(by + 1);
            }
            n4 = (byte)(n4 + 1);
        }
        if ((n2 /= 221) >= 99) {
            n = 2000;
            this.cW[4] = 6;
            this.cR += 100;
        } else if (n2 >= 95) {
            n = 2000;
            this.cW[4] = 5;
            this.cR += this.a(n2, 95, 98, 50, 70);
        } else if (n2 >= 90) {
            n = 1000;
            this.cW[4] = 4;
            this.cR += this.a(n2, 90, 94, 30, 49);
        } else if (n2 >= 80) {
            n = 1000;
            this.cW[4] = 3;
            this.cR += this.a(n2, 80, 899, 29, 29);
        } else if (n2 >= 60) {
            n = 750;
            this.cW[4] = 2;
            n3 = this.a(n2, 60, 79);
            this.cR += this.a(n2, 60, 79, 10, 19);
        } else if (n2 >= 36) {
            n = 500;
            this.cW[4] = 1;
            n3 = this.a(n2, 36, 59);
            this.cR += this.a(n2, 36, 59, 1, 9);
        } else {
            n = 250;
            this.cW[4] = 0;
            n3 = this.a(n2, 0, 35);
        }
        this.a(n, n3, (byte)4);
    }

    public final void h() {
        int n = 0;
        int n2 = 1;
        while (n2 < 36) {
            byte by = 1;
            while (by < this.r[n2]) {
                n += this.Q[this.A[n2][by]];
                by = (byte)(by + 1);
            }
            n2 = (byte)(n2 + 1);
        }
        if ((n /= 222) >= 30000) {
            this.cR += 100;
        } else if (n >= 20000) {
            this.cR += this.a(n, 20000, 29999, 50, 70);
        } else if (n >= 15000) {
            this.cR += this.a(n, 15000, 19999, 30, 49);
        } else if (n >= 10000) {
            this.cR += this.a(n, 10000, 14999, 29, 29);
        } else if (n >= 7000) {
            this.cR += this.a(n, 7000, 9999, 1, 19);
        }
        if (this.cT == 9 && n < 20000) {
            this.cT = (byte)8;
            this.cU = 1000;
        }
    }

    public final void i() {
        int n = 0;
        int n2 = 222;
        int n3 = 1;
        while (n3 < 36) {
            byte by = 1;
            while (by < this.r[n3]) {
                byte by2 = this.N[this.A[n3][by]];
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
            this.cR += 100;
        } else if (n == 88) {
            this.cR += 80;
        } else if (n == 87) {
            this.cR += 60;
        } else if (n >= 86) {
            this.cR += 50;
        } else if (n >= 80) {
            this.cR += this.a(n, 80, 84, 20, 40);
        } else if (n >= 70) {
            this.cR += this.a(n, 70, 79, 1, 19);
        }
        if (this.cT == 9 && n < 80) {
            this.cT = (byte)8;
            this.cU = 1000;
        }
    }

    public final void j() {
        int n = 0;
        int n2 = 0;
        if (this.cQ == 4 || this.cQ == 6 || this.cQ == 7) {
            n2 = 1;
        }
        int n3 = 1;
        while (n3 < 36) {
            byte by = 1;
            while (by < this.r[n3]) {
                switch (this.R[this.A[n3][by]]) {
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
                switch (this.S[this.A[n3][by]]) {
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
        this.cR += n * 100 / 800;
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
        this.cS = this.cR / 10;
        if (this.Z == 2) {
            this.cR = this.aO * 3 / 2;
        }
    }

    public final String a(int n) {
        return this.cX[n][this.cW[n]];
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
        int n = this.N[s] + 20 - this.N[s2];
        if (n < 0) {
            return false;
        }
        n = this.a((short)((byte)n)) * 10;
        return this.s() % 100 <= n;
    }

    public final boolean l() {
        if (this.a(this.ax, this.ay)) {
            this.cd = (byte)20;
            return true;
        }
        return false;
    }

    public final boolean m() {
        int n = this.M[this.ay] + this.L[this.ay] - this.M[this.ax] - this.L[this.ax] + 30;
        n = this.a((short)n) * 10;
        return this.s() % 51 <= n;
    }

    public final boolean a(byte by) {
        this.bN = (byte)(this.bN - this.b(by));
        switch (by) {
            case 0: {
                return this.l();
            }
            case 1: {
                return this.m();
            }
            case 2: {
                this.cd = (byte)51;
                return true;
            }
            case 3: {
                this.cd = (byte)68;
                return true;
            }
            case 4: {
                this.cd = (byte)83;
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
            byte[] byArray = this.bK[by2];
            byte by3 = by;
            byArray[by3] = (byte)(byArray[by3] | 0x80);
            return;
        }
        byte[] byArray = this.bK[by2];
        byte by4 = by;
        byArray[by4] = (byte)(byArray[by4] | 0x40);
    }

    public final int a(boolean bl, byte by, byte by2, int n, byte by3) {
        if (bl) {
            byte by4 = by;
            while (by4 < by2) {
                this.bX[0][by4] = 3;
                this.bU[0][by4] = by3;
                this.bZ[0][by4] = (byte)(n / (this.bI - by4));
                n -= this.bZ[0][by4];
                this.d(true, by4);
                this.cb[0][by4] = true;
                by4 = (byte)(by4 + 1);
            }
        } else {
            byte by5 = by;
            while (by5 < by2) {
                this.bX[1][by5] = 2;
                this.bU[1][by5] = by3;
                this.bZ[1][by5] = (byte)(n / (this.bJ - by5));
                n -= this.bZ[1][by5];
                this.d(false, by5);
                this.cb[1][by5] = true;
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
            s2 = this.J[this.ax];
            by = this.bI;
            s = this.ax;
        } else {
            s2 = this.J[this.ay];
            by = this.bJ;
            s = this.ay;
        }
        switch (this.H[s]) {
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
            while (by2 < this.bI) {
                if (this.bU[0][by2] == 1) {
                    this.bU[0][by2] = 2;
                }
                if (this.bU[0][by2] == 1 || this.bU[0][by2] == 2) {
                    by = (byte)(by + 1);
                }
                by2 = (byte)(by2 + 1);
            }
        } else {
            byte by3 = 1;
            while (by3 < this.bJ) {
                if (this.bU[1][by3] == 1) {
                    this.bU[1][by3] = 2;
                }
                if (this.bU[1][by3] == 1 || this.bU[1][by3] == 2) {
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
                this.bS[0][by2] = (byte)(8 - (by2 - 1) / 2);
                this.bT[0][by2] = (byte)((by2 - 1) % 2 * 6);
                this.a(this.bS[0][by2], this.bT[0][by2], false);
                by2 = (byte)(by2 + 1);
            }
            return;
        }
        byte by3 = 1;
        while (by3 <= by) {
            this.bS[1][by3] = (byte)(7 + (by3 - 1) / 2);
            this.bT[1][by3] = (byte)((by3 - 1) % 2 * 6);
            this.a(this.bS[1][by3], this.bT[1][by3], true);
            by3 = (byte)(by3 + 1);
        }
    }

    public final void b(boolean bl, byte by) {
        if (bl) {
            byte by2 = 1;
            while (by2 <= by) {
                this.bS[0][this.bI - by2] = 2;
                this.bT[0][this.bI - by2] = by2;
                this.a(this.bS[0][this.bI - by2], this.bT[0][this.bI - by2], false);
                by2 = (byte)(by2 + 1);
            }
            return;
        }
        byte by3 = 1;
        while (by3 <= by) {
            this.bS[1][this.bJ - by3] = 13;
            this.bT[1][this.bJ - by3] = by3;
            this.a(this.bS[1][this.bJ - by3], this.bT[1][this.bJ - by3], true);
            by3 = (byte)(by3 + 1);
        }
    }

    public final void c(boolean bl, byte by) {
        byte by2 = bl ? this.bI : this.bJ;
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
                    this.bS[0][by3] = (byte)(n2 + 1);
                    this.bT[0][by3] = n;
                    this.a(this.bS[0][by3], this.bT[0][by3], false);
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
                    this.bS[0][by8] = (byte)(by6 + 1);
                    this.bT[0][by8] = by7;
                    this.a(this.bS[0][by8], this.bT[0][by8], false);
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
                    this.bS[0][by10] = (byte)(n + 1);
                    this.bT[0][by10] = by11;
                    this.a(this.bS[0][by10], this.bT[0][by10], false);
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
                    this.bS[0][by13] = (byte)(n + 1);
                    this.bT[0][by13] = n3;
                    this.a(this.bS[0][by13], this.bT[0][by13], false);
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
                    this.bS[1][by3] = (byte)(n2 - 1);
                    this.bT[1][by3] = n;
                    this.a(this.bS[1][by3], this.bT[1][by3], true);
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
                    this.bS[1][by8] = (byte)(by6 - 1);
                    this.bT[1][by8] = by7;
                    this.a(this.bS[1][by8], this.bT[1][by8], true);
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
                    this.bS[1][by10] = (byte)(n - 1);
                    this.bT[1][by10] = by11;
                    this.a(this.bS[1][by10], this.bT[1][by10], true);
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
                    this.bS[1][by13] = (byte)(n - 1);
                    this.bT[1][by13] = n3;
                    this.a(this.bS[1][by13], this.bT[1][by13], true);
                    by13 = (byte)(by13 + 1);
                }
                break;
            }
        }
    }

    public final void c(byte by) {
        this.bU = new byte[2][];
        this.bS = new byte[2][];
        this.bT = new byte[2][];
        this.bX = new byte[2][];
        this.bZ = new byte[2][];
        this.ca = new boolean[2][];
        this.cb = new boolean[2][];
        this.bV = new short[2][];
        this.bW = new short[2][];
        this.bY = new byte[2][];
        this.bU[0] = new byte[this.bI];
        this.bS[0] = new byte[this.bI];
        this.bT[0] = new byte[this.bI];
        this.bX[0] = new byte[this.bI];
        this.bZ[0] = new byte[this.bI];
        this.ca[0] = new boolean[this.bI];
        this.cb[0] = new boolean[this.bI];
        this.bV[0] = new short[this.bI];
        this.bW[0] = new short[this.bI];
        this.bY[0] = new byte[this.bI];
        this.bU[1] = new byte[this.bJ];
        this.bS[1] = new byte[this.bJ];
        this.bT[1] = new byte[this.bJ];
        this.bX[1] = new byte[this.bJ];
        this.bZ[1] = new byte[this.bJ];
        this.ca[1] = new boolean[this.bJ];
        this.cb[1] = new boolean[this.bJ];
        this.bV[1] = new short[this.bJ];
        this.bW[1] = new short[this.bJ];
        this.bY[1] = new byte[this.bJ];
        this.bU[0][0] = 0;
        this.bS[0][0] = 1;
        this.bT[0][0] = 3;
        this.bX[0][0] = 3;
        this.bZ[0][0] = 100;
        this.d(true, (byte)0);
        this.a(this.bS[0][0], this.bT[0][0], false);
        this.a(true);
        if (!this.cl && this.cm) {
            this.cn = this.b(true);
            this.c(true, this.cn);
        } else if (this.cl || !this.cm) {
            this.a(this.bI, by);
        }
        this.bU[1][0] = 0;
        this.bS[1][0] = (byte)(this.bL - 2);
        this.bT[1][0] = 3;
        this.bX[1][0] = 2;
        this.bZ[1][0] = 100;
        this.d(false, (byte)0);
        this.a(this.bS[1][0], this.bT[1][0], true);
        this.a(false);
        if (this.cl && this.cm) {
            this.cn = this.b(false);
            this.c(false, this.cn);
            return;
        }
        this.b(this.bJ, (byte)(this.s() % 4));
    }

    public final void n() {
        byte by = 1;
        while (by < 4) {
            this.cg[by] = 0;
            this.ch[by] = 0;
            by = (byte)(by + 1);
        }
        this.cg[0] = this.L[this.ax];
        by = 1;
        while (by < this.bI) {
            byte by2 = this.bU[0][by];
            this.cg[by2] = (short)(this.cg[by2] + this.bZ[0][by]);
            by = (byte)(by + 1);
        }
        this.ch[0] = this.L[this.ay];
        by = 1;
        while (by < this.bJ) {
            byte by3 = this.bU[1][by];
            this.ch[by3] = (short)(this.ch[by3] + this.bZ[1][by]);
            by = (byte)(by + 1);
        }
    }

    public final void d(boolean bl, byte by) {
        short s;
        int n;
        if (bl) {
            n = 0;
            s = this.ax;
        } else {
            n = 1;
            s = this.ay;
        }
        this.ca[n][by] = false;
        this.cb[n][by] = true;
        if (this.bU[n][by] == 0) {
            this.bV[n][by] = (short)(this.M[s] + this.M[s] * this.af[this.R[s]] / 100);
        } else if (this.bU[n][by] == 1) {
            this.bV[n][by] = this.M[s];
        } else if (this.bU[n][by] == 2 || this.bU[n][by] == 3) {
            this.bV[n][by] = (short)(this.M[s] * 3 / 5);
        }
        if (this.bV[n][by] == 0) {
            this.bV[n][by] = 1;
        }
        if (this.aP == 0) {
            this.bW[n][by] = (short)(this.M[s] + this.M[s] * this.af[this.S[s]] / 100);
            return;
        }
        this.bW[n][by] = this.bV[n][by];
    }

    public final void o() {
        this.bN = (byte)(this.N[this.ax] * 13 / 100);
        this.bO = (byte)(this.N[this.ay] * 13 / 100);
    }

    public final void c(byte by, byte by2) {
        byte by3 = (byte)(this.bK[this.bT[by][by2]][this.bS[by][by2]] & 0xC0);
        byte[] byArray = this.bK[this.bT[by][by2]];
        byte by4 = this.bS[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bT[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] - 1);
        this.bX[by][by2] = 0;
        byte[] byArray3 = this.bK[this.bT[by][by2]];
        byte by6 = this.bS[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void d(byte by, byte by2) {
        byte by3 = (byte)(this.bK[this.bT[by][by2]][this.bS[by][by2]] & 0xC0);
        byte[] byArray = this.bK[this.bT[by][by2]];
        byte by4 = this.bS[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bT[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] + 1);
        this.bX[by][by2] = 1;
        byte[] byArray3 = this.bK[this.bT[by][by2]];
        byte by6 = this.bS[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void e(byte by, byte by2) {
        byte by3 = (byte)(this.bK[this.bT[by][by2]][this.bS[by][by2]] & 0xC0);
        byte[] byArray = this.bK[this.bT[by][by2]];
        byte by4 = this.bS[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bS[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] - 1);
        this.bX[by][by2] = 2;
        byte[] byArray3 = this.bK[this.bT[by][by2]];
        byte by6 = this.bS[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void f(byte by, byte by2) {
        byte by3 = (byte)(this.bK[this.bT[by][by2]][this.bS[by][by2]] & 0xC0);
        byte[] byArray = this.bK[this.bT[by][by2]];
        byte by4 = this.bS[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.bS[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] + 1);
        this.bX[by][by2] = 3;
        byte[] byArray3 = this.bK[this.bT[by][by2]];
        byte by6 = this.bS[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public b() {
        byte[] byArray = new byte[8];
        byArray[1] = 5;
        byArray[2] = 11;
        byArray[3] = 3;
        byArray[4] = 5;
        byArray[5] = 6;
        byArray[6] = 1;
        byArray[7] = 5;
        this.e = byArray;
        short[][] sArrayArray = new short[8][];
        sArrayArray[1] = new short[]{161, 5, 6, 181, 200};
        sArrayArray[2] = new short[]{211, 225, 215, 213, 217, 56, 58, 204, 210, 63, 57};
        sArrayArray[3] = new short[]{37, 8, 34};
        sArrayArray[4] = new short[]{191, 138, 201, 155, 208};
        sArrayArray[5] = new short[]{26, 25, 18, 19, 28, 21};
        sArrayArray[6] = new short[]{40};
        sArrayArray[7] = new short[]{140, 2, 172, 190, 160};
        this.f = sArrayArray;
        this.g = new byte[37];
        this.h = new String[37];
        this.i = new short[37];
        this.j = new short[37];
        this.k = new byte[37];
        this.l = new short[37];
        this.m = new short[37];
        this.n = new short[37];
        this.o = new short[37];
        this.p = new int[37];
        this.q = new byte[37];
        this.r = new byte[37];
        this.s = new byte[37];
        this.t = new byte[37];
        this.u = new short[37];
        this.v = new short[37];
        this.w = new byte[37];
        this.x = new byte[37];
        this.y = new byte[37];
        this.z = new byte[37];
        this.A = new short[37][10];
        this.B = new short[37][];
        this.C = new short[37][];
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
        this.D = byArrayArray;
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
        this.E = byArray2;
        this.F = new short[232];
        this.G = new String[232];
        this.H = new byte[232];
        this.I = new byte[232];
        this.J = new short[232];
        this.K = new byte[232];
        this.L = new byte[232];
        this.M = new byte[232];
        this.N = new byte[232];
        this.O = new byte[232];
        this.P = new byte[232];
        this.Q = new short[232];
        this.R = new byte[232];
        this.S = new byte[232];
        this.T = new short[232];
        this.U = new byte[232];
        this.V = new boolean[232];
        this.W = 0;
        this.X = new byte[7];
        this.Y = new byte[7];
        this.Z = (byte)2;
        this.aa = new short[]{192, 192, 194, 195, 196, 197, 197, 198, 199, 199, 200, 201, 202, 203, 203, 203, 204, 204, 205, 205, 208, 208, 209, 210, 210, 210};
        this.ab = new short[]{138, 140, 160, 172, 179, 190, 191, 194, 200, 201, 204, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 222, 225, 226, 227};
        this.ac = new short[]{69, 1, 1, 1, 57, 1, 69, 5, 4, 69, 61, 61, 61, 6, 61, 63, 61, 5, 61, 5, 162, 63, 162, 211, 161, 164};
        this.ad = new String[]{"\u77ed\u5251", "\u98de\u5251", "\u957f\u5251", "\u7834\u65a9\u5251", "\u5927\u5251", "\u4e03\u661f\u5251", "\u9752\u91ed\u5251", "\u501a\u5929\u5251", "\u96cd\u5200", "\u7709\u5c16\u5200", "\u4e09\u5c16\u5200", "\u94a9\u9570\u5200", "\u5043\u6708\u5200", "\u51e4\u5634\u5200", "\u9f99\u7259\u5200", "\u5173\u738b\u5200", "\u77db", "\u53cc\u621f", "\u5355\u94a9\u67aa", "\u6218\u65a7", "\u53cc\u94a9\u67aa", "\u65b9\u5929\u5316\u621f", "\u72fc\u7259\u67aa", "\u86c7\u77db", "\u7eb6\u5dfe", "\u7b3c\u624b", "\u515c", "\u786c\u76ae", "\u9e64\u6c05", "\u7532\u80c4", "\u5723\u8005\u4e4b\u8863", "\u9f99\u9cde\u5b9d\u94e0"};
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
        this.ae = sArray;
        this.af = new byte[]{5, 7, 12, 18, 25, 50, 50, 50, 6, 10, 15, 20, 24, 26, 45, 50, 8, 10, 12, 20, 25, 30, 50, 50, 5, 8, 10, 15, 20, 25, 50, 60};
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
        this.ag = byArrayArray2;
        this.ah = new byte[][]{{8, 9, 10, 11}, {10, 11, 12, 13}, {10, 11, 12, 13}, {8, 9, 10, 11}, {8, 9, 10, 15}, {8, 9, 10, 11}};
        this.ai = new byte[][]{{16, 17, 18, 19}, {18, 19, 20, 21}, {18, 19, 20, 21}, {16, 17, 18, 23}, {17, 18, 19, 20}, {16, 17, 18, 22}};
        this.aj = new byte[][]{{24, 25, 26, 27}, {26, 27, 28, 29}, {26, 27, 28, 30}, {24, 25, 26, 27}, {24, 25, 26, 27}, {24, 25, 26, 27}};
        this.ak = new short[2];
        this.al = new byte[2];
        this.am = new short[2];
        this.an = new String[2];
        this.ap = new Random();
        this.aq = 0;
        this.az = new short[10];
        this.aB = new byte[8];
        this.aC = 1;
        this.aD = (short)189;
        this.aE = 0;
        this.aF = 0;
        this.aH = true;
        this.aI = true;
        this.aL = true;
        this.aM = new short[10];
        this.aN = 0;
        this.aQ = new byte[8];
        this.aR = new byte[37];
        this.aU = true;
        this.bg = new short[10];
        this.bh = new short[10];
        this.bm = new byte[19][32];
        this.bn = (byte)32;
        this.bo = (byte)19;
        this.bq = new byte[10];
        this.br = new byte[10];
        this.bs = new byte[10];
        this.bt = new byte[10];
        this.bu = new byte[10];
        this.bv = new byte[10];
        this.bG = 0;
        this.bK = new byte[][]{new byte[16], new byte[16], new byte[16], new byte[16], new byte[16], new byte[16], new byte[16]};
        this.bL = (byte)16;
        this.bM = (byte)7;
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
        this.bR = byArrayArray3;
        this.cd = 0;
        this.ce = 0;
        this.cg = new short[4];
        this.ch = new short[4];
        this.cj = new byte[4];
        this.ck = 0;
        this.cO = false;
        this.cP = 0;
        this.cW = new byte[7];
        this.cX = new String[][]{{"\u6781\u5ea6\u8d2b\u7620\u7684\u571f\u5730, \u6839\u672c\u65e0\u6cd5\u751f\u4ea7\u51fa\u7cae\u98df,\u997f\u6b8d\u904d\u91ce,\u767e\u59d3\u82e6\u4e0d\u582a\u8a00", "\u571f\u5730\u8d44\u6e90\u975e\u5e38\u532e\u4e4f,\u7cae\u98df\u6536\u6210\u975e\u5e38\u4f4e\u4e0b,\u5404\u5730\u767e\u59d3\u997f\u6b7b\u73b0\u8c61\u65f6\u6709\u53d1\u751f,\u65e0\u6cd5\u7ee7\u7eed\u751f\u6d3b\u4e0b\u53bb", "\u519c\u4e1a\u53d1\u5c55\u8f83\u4e3a\u843d\u540e,\u7cae\u98df\u4ea7\u91cf\u4ecd\u65e0\u6cd5\u6ee1\u8db3\u767e\u59d3\u7684\u751f\u6d3b,\u5404\u5730\u7530\u91cc,\u5730\u91cc\u4e00\u7247\u83dc\u8272", "\u968f\u7740\u519c\u4e1a\u5730\u9010\u6b65\u53d1\u5c55,\u7cae\u98df\u4ea7\u91cf\u57fa\u672c\u8fbe\u5230\u4e86\u767e\u59d3\u751f\u6d3b\u5730\u9700\u6c42", "\u968f\u7740\u571f\u5730\u5730\u4e0d\u65ad\u5f00\u53d1,\u7cae\u98df\u4ea7\u91cf\u5df2\u8f83\u4e3a\u5bcc\u88d5,\u767e\u59d3\u5403\u996d\u95ee\u9898\u5df2\u5b8c\u5168\u89e3\u51b3\u4e86", "\u65b0\u7684\u519c\u4e1a\u5de5\u5177\u5730\u4e0d\u65ad\u51fa\u73b0,\u5728\u571f\u5730\u4f5c\u4e1a\u4e2d\u7684\u9010\u6e10\u666e\u53ca,\u5404\u5730\u7cae\u98df\u5df2\u6781\u4e3a\u5145\u88d5", "\u519c\u4e1a\u53d1\u5c55\u7a7a\u524d,\u7cae\u98df\u5e74\u5e74\u4e30\u4ea7,\u4eba\u6c11\u8863\u98df\u65e0\u5fe7"}, {"\u6781\u5ea6\u843d\u540e\u7684\u5546\u4e1a,\u4eba\u4eec\u6700\u57fa\u672c\u7684\u7269\u54c1\u4ea4\u6362\u90fd\u65e0\u6cd5\u8fdb\u884c.\u56fd\u5bb6\u8d2b\u82e6\u4e0d\u582a", "\u5546\u4e1a\u8d44\u6e90\u975e\u5e38\u8d2b\u4e4f,\u4eba\u4eec\u65e0\u6cd5\u5f97\u5230\u6700\u57fa\u672c\u7684\u751f\u6d3b\u7528\u54c1,\u751f\u6d3b\u975e\u5e38\u8270\u8f9b", "\u5546\u4e1a\u53d1\u5c55\u4ecd\u8f83\u4e3a\u843d\u540e,\u5546\u52a1\u6d3b\u52a8\u8f83\u5c11, \u767e\u59d3\u751f\u6d3b\u6c34\u5e73\u975e\u5e38\u4f4e\u4e0b", "\u968f\u7740\u5546\u4e1a\u5730\u9010\u6b65\u53d1\u5c55,\u5546\u52a1\u6d3b\u52a8\u57fa\u672c\u8fbe\u5230\u4e86\u767e\u59d3\u751f\u6d3b\u5730\u9700\u6c42", "\u968f\u7740\u5b98\u5e9c\u7684\u4e0d\u65ad\u91cd\u89c6,\u5546\u4e1a\u53d1\u5c55\u5df2\u8f83\u4e3a\u53d1\u8fbe,\u56fd\u5bb6\u7ecf\u6d4e\u72b6\u51b5\u6709\u4e86\u5f88\u5927\u6539\u5584", "\u4e0e\u5916\u56fd\u7684\u7ecf\u6d4e\u5f80\u6765,\u4f7f\u5f97\u56fd\u5bb6\u5546\u52a1\u53d1\u5c55\u6781\u5ea6\u53d1\u8fbe,\u767e\u59d3\u751f\u6d3b\u5728\u4e86\u4e00\u4e2a\u5bcc\u88d5\u7684\u56fd\u5bb6\u4e2d", "\u56fd\u5bb6\u7684\u7ecf\u6d4e\u53d1\u5c55\u4e3e\u4e16\u7f55\u89c1, \u65b0\u7684\u9886\u57df\u7684\u7ecf\u6d4e\u5f80\u6765\u4e0d\u65ad\u589e\u5f3a\u672c\u56fd\u7684\u7ecf\u6d4e\u5b9e\u529b"}, {"\u761f\u75ab,\u8757\u866b,\u65f1\u6d9d,\u5404\u79cd\u707e\u5bb3\u904d\u5730,\u6c11\u4e0d\u804a\u751f", "\u761f\u75ab\u7b49\u8fd9\u79cd\u707e\u5bb3\u4ecd\u65f6\u6709\u53d1\u751f,\u767e\u59d3\u707e\u96be\u6df1\u91cd", "\u7531\u4e8e\u65b0\u56fd\u5bb6\u5b98\u5e9c\u7684\u4e00\u5b9a\u91cd\u89c6,\u707e\u5bb3\u5728\u4e00\u5b9a\u7a0b\u5ea6\u4e0a\u5f97\u5230\u4e86\u904f\u5236", "\u7ecf\u8fc7\u5b98\u5e9c\u7684\u4e0d\u65ad\u52aa\u529b,\u707e\u5bb3\u9632\u5fa1\u63aa\u65bd\u9010\u6e10\u5b9e\u65bd,\u767e\u59d3\u57fa\u672c\u8fdc\u79bb\u4e86\u707e\u5bb3\u75db\u82e6", "\u5927\u575d\u5efa\u6210,\u5404\u79cd\u6cbb\u7406\u707e\u5bb3\u63aa\u65bd\u4e95\u7136\u6709\u5e8f,\u767e\u59d3\u518d\u65e0\u707e\u5bb3\u4e4b\u5fe7"}, {"\u7ecf\u8fc7\u591a\u5e74\u7684\u6218\u4e89,\u5404\u5730\u4e00\u7247\u8352\u829c,\u5404\u57ce\u5e02\u4eba\u4e01\u7a00\u5c11,\u767e\u91cc\u96be\u89c1\u4e00\u4eba", "\u6218\u540e\u6062\u590d\u4ecd\u6ca1\u6709\u8db3\u591f\u7684\u8fdb\u884c,\u5404\u57ce\u5e02\u52b3\u52a8\u529b\u8d44\u6e90\u4ecd\u7a0d\u663e\u4e0d\u8db3", "\u6218\u4e89\u7684\u785d\u70df\u9010\u6e10\u8fdc\u53bb,\u5404\u57ce\u5e02\u4eba\u53e3\u8d44\u6e90\u5df2\u5b8c\u5168\u6ee1\u8db3\u4e86\u52b3\u52a8\u7684\u9700\u6c42", "\u56fd\u5bb6\u5f00\u59cb\u91cd\u89c6\u4e86\u57ce\u5e02\u7684\u5efa\u8bbe,\u57ce\u5e02\u89c4\u6a21\u53d1\u5c55\u7a33\u5b9a,\u5927\u578b\u57ce\u5e02\u9010\u6b65\u5174\u8d77", "\u7ecf\u8fc7\u56fd\u5bb6\u591a\u65b9\u9762\u52aa\u529b,\u57ce\u5e02\u5411\u90fd\u5e02\u5316\u53d1\u5c55\u5f00\u59cb,\u5404\u57ce\u5e02\u6b23\u6b23\u5411\u8363", "\u5404\u5730\u53d1\u5c55\u5e73\u8861,\u90fd\u5e02\u5316\u53d1\u5c55\u5b8c\u6210,\u56fd\u5bb6\u53d1\u5c55\u7a7a\u524d,\u767e\u59d3\u5b89\u5c45\u4e50\u4e1a"}, {"\u90e8\u4e0b\u91ce\u5fc3\u52c3\u52c3,\u53db\u53d8,\u53cd\u4e71\u4e8b\u4ef6\u5c42\u51fa\u4e0d\u7a77,\u56fd\u5bb6\u9a6c\u4e0a\u5c06\u88ab\u98a0\u8986", "\u5c5e\u4e0b\u4eba\u5fc3\u6d6e\u52a8,\u6d41\u8a00\u56db\u8d77,\u67d0\u4e9b\u6b66\u5c06\u7684\u53db\u4e71\u4f3c\u4e4e\u5df2\u5728\u773c\u524d", "\u5927\u591a\u6b66\u5c06\u5fe0\u4e8e\u541b\u4e3b,\u4f46\u5978\u81e3\u5f53\u9053,\u53db\u53d8\u7684\u79cd\u5b50\u4ecd\u8fdc\u672a\u6d88\u5931", "\u541b\u4e3b\u8d4f\u7f5a\u5206\u660e,\u6069\u5a01\u5e76\u65bd,\u4e0b\u5c5e\u6b66\u5c06\u5bf9\u56fd\u5bb6\u8f83\u4e3a\u5fe0\u8bda", "\u541b\u4e3b\u5584\u4e8e\u5fa1\u4eba\u4e4b\u9053, \u6b66\u5c06\u4eec\u5bf9\u541b\u4e3b, \u56fd\u5bb6\u5fe0\u5fc3\u803f\u803f", "\u4ee5\u6069\u4e49\u8457\u79f0\u7684\u65b0\u541b\u4e3b,\u5f97\u5230\u4e86\u5c5e\u4e0b\u8a93\u6b7b\u6548\u5fe0,\u5404\u6b66\u5c06\u7edd\u65e0\u4e8c\u5fc3", "\u7ecf\u8fc7\u591a\u5e74\u7684\u540c\u751f\u5171\u6b7b,\u81e3\u50da\u4eec\u7528\u9c9c\u8840\u6765\u53d1\u8a93,\u5b50\u5b50\u5b59\u5b59\u8a93\u6b7b\u6548\u5fe0\u541b\u4e3b"}, {"\u65b0\u56fd\u5bb6\u7684\u6240\u4f5c\u6240\u4e3a,\u767e\u59d3\u6df1\u6076\u75db\u75be,\u5404\u5730\u70fd\u70df\u56db\u8d77,\u8d77\u4e49\u904d\u5730,\u65e0\u6cd5\u63a7\u5236", "\u65b0\u56fd\u5bb6\u5e76\u6ca1\u6709\u5b9e\u65bd\u4ec1\u653f,\u767e\u59d3\u5bf9\u65b0\u56fd\u5bb6\u975e\u5e38\u4e0d\u6ee1, \u8d77\u4e49\u7684\u79cd\u5b50\u56db\u5904\u840c\u53d1", "\u65b0\u56fd\u5bb6\u7684\u4ec1\u653f\u601d\u60f3\u9010\u6e10\u4f20\u64ad,\u9010\u6e10\u4f7f\u5f97\u767e\u59d3\u5bf9\u65b0\u7684\u56fd\u5bb6\u57fa\u672c\u4e0a\u8ba4\u53ef\u4e86", "\u541b\u4e3b\u4e00\u5411\u4ee5\u4ec1\u653f\u800c\u95fb\u540d,\u56fd\u5bb6\u5efa\u7acb\u4ee5\u6765,\u5f97\u5230\u4e86\u5404\u5730\u767e\u59d3\u7684\u652f\u6301\u548c\u6b22\u8fce", "\u541b\u4e3b\u8d24\u540d\u8fdc\u64ad,\u7ba1\u7406\u56fd\u5bb6\u653f\u7b56\u5f97\u5f53,\u65bd\u4ee5\u767e\u59d3\u7684\u4ec1\u653f\u6df1\u5165\u4eba\u5fc3", "\u5168\u56fd\u767e\u59d3\u575a\u51b3\u62e5\u62a4\u65b0\u7684\u56fd\u5bb6,\u541b\u4e3b\u5df2\u6210\u4e3a\u5927\u4f17\u7684\u5076\u50cf,\u548c\u7cbe\u795e\u7825\u67f1"}, {"\u591a\u5e74\u6218\u4e89\u9020\u6210\u56fd\u5bb6\u5175\u7532\u6781\u5ea6\u4e0d\u8db3,\u8fdc\u8fdc\u65e0\u6cd5\u62b5\u5fa1\u5916\u654c\u4fb5\u7565,\u968f\u65f6\u6709\u706d\u56fd\u5371\u9669", "\u6218\u540e\u5175\u7532\u4ecd\u7136\u4e0d\u8db3,\u65e0\u6cd5\u62b5\u5fa1\u5308\u5974\u7b49\u5916\u654c\u7684\u5927\u89c4\u6a21\u8fdb\u653b,\u56fd\u5bb6\u5c8c\u5c8c\u53ef\u5371", "\u5308\u5974,\u5916\u65cf\u864e\u89c6\u803d\u803d,\u5168\u90e8\u5175\u4e01\u5df2\u6d3e\u5f80\u524d\u7ebf,\u4e0e\u5f3a\u5927\u7684\u654c\u4eba\u8fbe\u6210\u5fae\u5f31\u7684\u519b\u4e8b\u5e73\u8861", "\u5728\u6218\u4e89\u79ef\u7d2f\u4e86\u8db3\u591f\u519b\u4e8b\u7ecf\u9a8c,\u56fd\u5bb6\u519b\u4e8b\u5b9e\u529b\u7a33\u5b9a,\u5728\u968f\u540e\u7684\u6218\u4e89\u4e2d\u6570\u6b21\u6218\u8d25\u5916\u4fb5\u7565\u8005", "\u6b66\u5668,\u9635\u6cd5\u7684\u66f4\u65b0,\u5c06\u9886\u7684\u57f9\u517b,\u4f7f\u5f97\u519b\u4e8b\u5b9e\u529b\u5f3a\u5927,\u5f81\u670d\u4e86\u8bb8\u591a\u5468\u56f4\u7684\u5c0f\u56fd\u5bb6", "\u519b\u4e8b\u5b9e\u529b\u7a7a\u524d,\u5bf9\u5468\u8fb9\u56fd\u5bb6\u5f62\u6210\u4e86\u5f3a\u5927\u7684\u9707\u6151\u4f5c\u7528.\u5404\u56fd\u7eb7\u7eb7\u9063\u4f7f\u8fdb\u8d21,\u6216\u4ece\u5c5e.\u5f3a\u5927\u7684\u5e1d\u56fd\u5df2\u7ecf\u4ea7\u751f"}};
        this.cY = new short[3];
        this.cZ = new byte[10240];
    }

    public final synchronized void a(c c2) {
        this.ao = c2;
        this.aU = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void p() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.ao.a > 0) {
                this.ao.l();
            }
            if (this.aq > 0) {
                return;
            }
            this.ao.a = 0;
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void q() {
        this.aH();
        this.ao.a((byte)1);
    }

    public final void r() {
        this.ao.a((byte)18);
    }

    public final void d(byte by) {
        if (by == 5) {
            this.ao.a((byte)1);
            return;
        }
        this.aO = by;
        this.bi();
        this.aq = (byte)2;
    }

    public final int s() {
        return Math.abs(this.ap.nextInt());
    }

    public final boolean g(byte by, byte by2) {
        byte by3 = 0;
        while (by3 < this.E[by]) {
            if (this.D[by][by3] == by2) {
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
            if ((n -= this.aB[n2]) == 0) break;
            if (++n2 != 8) continue;
            n2 = 0;
        }
        this.aB[n2] = 0;
        this.as = this.a[n2 + 1];
    }

    public final byte e(byte by) {
        int n = this.c[by];
        if (n < 3) {
            n = 3;
        } else if (n > 8) {
            n = 8;
        }
        return (byte)n;
    }

    public final void u() {
        byte by = 0;
        while (by < this.c[this.ar]) {
            if (this.i[this.d[this.ar][by]] == this.j[this.d[this.ar][by]]) {
                this.aw = this.au = this.d[this.ar][by];
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
        this.bm[s][by] = Byte.parseByte(string);
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
        String string = this.a("/res/map/" + this.bb + ".map");
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
                if (this.bm[n2][by4] == 2) {
                    by3 = by4;
                    n = n2;
                    if (by4 > 15) {
                        this.bw = (byte)-1;
                    } else {
                        this.by = 0;
                    }
                    if (n2 > 10) {
                        this.bx = (byte)-1;
                        break;
                    }
                    this.bz = 0;
                    break;
                }
                n2 = (byte)(n2 + 1);
            }
            by4 = (byte)(by4 + 1);
        }
        this.bs[0] = by3;
        this.bt[0] = n;
        this.bv[0] = 0;
        byte[] byArray = this.bm[n];
        byte by5 = by3;
        byArray[by5] = (byte)(byArray[by5] | 0x80);
        by4 = 1;
        while (by4 < this.bj) {
            do {
                by = (byte)(this.s() % 2 * this.bw + by3);
            } while (this.f(this.bm[by2 = (byte)(this.s() % 5 * this.bx + n)][by]) > 0);
            this.bs[by4] = by;
            this.bt[by4] = by2;
            byte[] byArray2 = this.bm[by2];
            byte by6 = by;
            byArray2[by6] = (byte)(byArray2[by6] | 0x80);
            this.bv[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
        by4 = 0;
        while (by4 < this.bi) {
            do {
                by = (byte)((this.s() % 2 + 8) * this.bw + by3);
            } while (this.f(this.bm[by2 = (byte)(this.s() % 5 * this.bx + n)][by]) > 0);
            this.bq[by4] = by;
            this.br[by4] = by2;
            byte[] byArray3 = this.bm[by2];
            byte by7 = by;
            byArray3[by7] = (byte)(byArray3[by7] | 0x40);
            this.bu[by4] = 0;
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
                if (this.bm[n2][by4] == 2) {
                    by3 = by4;
                    n = n2;
                    if (by4 > 15) {
                        this.bw = (byte)-1;
                        this.by = 0;
                    }
                    if (n2 <= 10) break;
                    this.bx = (byte)-1;
                    this.bz = 0;
                    break;
                }
                n2 = (byte)(n2 + 1);
            }
            by4 = (byte)(by4 + 1);
        }
        this.bq[0] = by3;
        this.br[0] = n;
        this.bu[0] = 0;
        byte[] byArray = this.bm[n];
        byte by5 = by3;
        byArray[by5] = (byte)(byArray[by5] | 0x40);
        by4 = 1;
        while (by4 < this.bi) {
            do {
                by = (byte)(this.s() % 2 * this.bw + by3);
            } while (this.f(this.bm[by2 = (byte)(this.s() % 5 * this.bx + n)][by]) > 0);
            this.bq[by4] = by;
            this.br[by4] = by2;
            byte[] byArray2 = this.bm[by2];
            byte by6 = by;
            byArray2[by6] = (byte)(byArray2[by6] | 0x40);
            this.bu[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
        by4 = 0;
        while (by4 < this.bj) {
            do {
                by = (byte)((this.s() % 2 + 8) * this.bw + by3);
            } while (this.f(this.bm[by2 = (byte)(this.s() % 5 * this.bx + n)][by]) > 0);
            this.bs[by4] = by;
            this.bt[by4] = by2;
            byte[] byArray3 = this.bm[by2];
            byte by7 = by;
            byArray3[by7] = (byte)(byArray3[by7] | 0x80);
            this.bv[by4] = 0;
            by4 = (byte)(by4 + 1);
        }
    }

    public final void y() {
        this.v();
        if (this.bp) {
            this.x();
        } else {
            this.w();
        }
        this.ao.a((byte)20);
        if (this.Z > 1) {
            this.cM = new byte[this.bj];
            byte by = 0;
            while (by < this.bj) {
                this.cM[by] = 0;
                by = (byte)(by + 1);
            }
            this.cN = 0;
        }
    }

    public final void z() {
        this.aq = 0;
        this.aZ = this.b[this.ar];
        this.ba = this.i[this.av];
        this.bb = this.av;
        this.bi = this.aA;
        int n = 0;
        while (n < this.bi) {
            this.bg[n] = this.az[n];
            ++n;
        }
        n = 0;
        while (n < this.bi) {
            this.bu[n] = 0;
            n = (byte)(n + 1);
        }
        this.bj = this.r[this.bb];
        n = 0;
        while (n < this.bj) {
            this.bh[n] = this.A[this.bb][n];
            ++n;
        }
        n = 0;
        while (n < this.bj) {
            this.bv[n] = 0;
            n = (byte)(n + 1);
        }
        this.bp = false;
        this.bw = 1;
        this.bx = 1;
        this.by = 1;
        this.bz = 1;
        this.bA = 0;
        this.bl = 0;
        this.be = this.l[this.bb];
        this.bf = this.m[this.bb];
        this.bB = true;
        this.bC = (byte)-1;
        this.bk = this.a(this.bg, this.bi, this.bu, this.bk);
        this.y();
        this.ao.b();
        this.ao.a();
        this.ao.j();
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
                s = (short)(s + this.J[sArray[by2]]);
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
        byte by = this.N[s];
        byte by2 = this.H[s];
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
            if (((this.bm[by2][by] | this.bm[by2][by - 1]) & 0xC0) == 192) {
                n = 16;
            } else if ((this.bm[by2][by] & this.bm[by2][by - 1] & 0xC0) != 0) {
                n = 1;
            }
        }
        if (by <= this.bn) {
            if (((this.bm[by2][by] | this.bm[by2][by + 1]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.bm[by2][by] & this.bm[by2][by + 1] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        if (by2 > 0) {
            if (((this.bm[by2][by] | this.bm[by2 - 1][by]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.bm[by2][by] & this.bm[by2 - 1][by] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        if (by2 <= this.bo) {
            if (((this.bm[by2][by] | this.bm[by2 + 1][by]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.bm[by2][by] & this.bm[by2 + 1][by] & 0xC0) != 0) {
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
            byArray4 = this.bq;
            byArray3 = this.br;
            byArray2 = this.bs;
            byArray = this.bt;
        } else {
            byArray4 = this.bs;
            byArray3 = this.bt;
            byArray2 = this.bq;
            byArray = this.bt;
        }
        byte by2 = byArray4[n];
        byte by3 = byArray3[n];
        byte by4 = byArray2[n2];
        byte by5 = byArray[n2];
        byte by6 = (byte)(this.bm[by3][by2] & 0x1F);
        byte by7 = (byte)(this.bm[by5][by4] & 0x1F);
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
            if (s2 != this.bg[0] && s2 != this.bh[0] && this.h(s, s2)) {
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
        int n3 = this.H[s] >= this.H[s2] ? this.N[s] + this.N[s] * (this.H[s] - this.H[s2]) / 10 - this.N[s2] : this.N[s] - this.N[s2] - this.N[s2] * (this.H[s2] - this.H[s]) / 10;
        if (n3 < n && this.s() % n >= n3) {
            return -1;
        }
        int n4 = 20 + this.ap.nextInt() % 4;
        if (n4 >= this.L[s2]) {
            n4 = this.L[s2] - 1;
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
        if (this.H[s] > this.H[s2]) {
            n3 += n3 * (1 + (this.H[s] - this.H[s2]) / 10);
        }
        n3 += this.ap.nextInt() % 30;
        if (by == 5) {
            if (n3 > s3) {
                n3 = s3;
            }
        } else if (n3 > this.J[s2]) {
            n3 = this.J[s2];
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
                this.bD = "\u58eb\u5175\u6570\u51cf\u5c11 " + s;
                return;
            }
            case 1: {
                this.bD = "\u6b66\u5c06\u4f53\u529b\u51cf\u5c11 " + by2;
                return;
            }
            case 2: {
                this.bD = "\u519b\u961f\u4e2d\u8ba1\u88ab\u56f0";
                return;
            }
            case 5: {
                this.bD = "\u7cae\u98df\u51cf\u5c11 " + s;
                return;
            }
            case 8: {
                this.bD = "\u6210\u529f\u7b3c\u7edc\u6b66\u5c06!";
                return;
            }
            case 9: {
                this.bD = "\u5c06\u4f53\u529b\u51cf\u5c11 " + by2 + ",\u5175\u6570\u51cf\u5c11 " + s;
                return;
            }
            case 10: {
                this.bD = "\u519b\u961f\u4e2d\u8ba1, \u88ab\u56f0\u4e09\u5929";
                return;
            }
            case 11: {
                this.bD = "\u519b\u961f\u57cb\u4f0f\u5b8c\u6bd5!";
                return;
            }
            case 15: {
                this.bD = "\u5947\u95e8\u9041\u7532\u65bd\u5c55\u6210\u529f!";
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
                this.J[s3] = (short)(this.J[s3] - s2);
                return;
            }
            case 2: {
                short s4 = s;
                this.L[s4] = (byte)(this.L[s4] - by3);
                return;
            }
            case 3: {
                if (bl) {
                    this.bf = (short)(this.bf - s2);
                    return;
                }
                this.bd = (short)(this.bd - s2);
                return;
            }
            case 4: {
                short s5 = s;
                this.J[s5] = (short)(this.J[s5] - s2);
                short s6 = s;
                this.L[s6] = (byte)(this.L[s6] - by3);
                return;
            }
            case 5: {
                byArray[by] = 3;
                if (bl) {
                    if (this.bi == 10) {
                        this.B[this.bb] = this.b(this.B[this.bb], this.s[this.bb], s);
                        byte by5 = this.bb;
                        this.s[by5] = (byte)(this.s[by5] + 1);
                        byte[] byArray2 = this.bm[this.bt[by]];
                        byte by6 = this.bs[by];
                        byArray2[by6] = (byte)(byArray2[by6] & 0x3F);
                        return;
                    }
                    this.bg[this.bi] = this.bh[by];
                    this.bq[this.bi] = this.bs[by];
                    this.br[this.bi] = this.bt[by];
                    this.bu[this.bi] = 0;
                    byte[] byArray3 = this.bm[this.bt[by]];
                    byte by7 = this.bs[by];
                    byArray3[by7] = (byte)(byArray3[by7] & 0x3F);
                    byte[] byArray4 = this.bm[this.bt[by]];
                    byte by8 = this.bs[by];
                    byArray4[by8] = (byte)(byArray4[by8] | 0x40);
                    this.bi = (byte)(this.bi + 1);
                    return;
                }
                if (this.bj == 10) {
                    this.B[this.bb] = this.b(this.B[this.bb], this.s[this.bb], s);
                    byte by9 = this.bb;
                    this.s[by9] = (byte)(this.s[by9] + 1);
                    byte[] byArray5 = this.bm[this.br[by]];
                    byte by10 = this.bq[by];
                    byArray5[by10] = (byte)(byArray5[by10] & 0x3F);
                    return;
                }
                this.bh[this.bj] = this.bg[by];
                this.bs[this.bj] = this.bq[by];
                this.bt[this.bj] = this.br[by];
                byte[] byArray6 = this.bm[this.br[by]];
                byte by11 = this.bq[by];
                byArray6[by11] = (byte)(byArray6[by11] & 0x3F);
                byte[] byArray7 = this.bm[this.br[by]];
                byte by12 = this.bq[by];
                byArray7[by12] = (byte)(byArray7[by12] | 0x80);
                this.bv[this.bj] = 0;
                this.bj = (byte)(this.bj + 1);
                return;
            }
            case 7: {
                byArray[by] = 4;
                return;
            }
            case 6: {
                short s7 = s;
                this.J[s7] = (short)(this.J[s7] - s2);
                if (bl) {
                    byte by13 = 0;
                    while (by13 < this.bj) {
                        if ((this.bv[by13] <= 0 || this.bv[by13] >= 4) && Math.abs(this.bs[by13] - this.bs[by]) + Math.abs(this.bt[by13] - this.bt[by]) == 1) {
                            if (this.J[this.bh[by13]] >= s2) {
                                short s8 = this.bh[by13];
                                this.J[s8] = (short)(this.J[s8] - s2);
                            } else {
                                this.J[this.bh[by13]] = 0;
                            }
                        }
                        by13 = (byte)(by13 + 1);
                    }
                    return;
                }
                byte by14 = 0;
                while (by14 < this.bi) {
                    if ((this.bu[by14] <= 0 || this.bu[by14] >= 4) && Math.abs(this.bq[by14] - this.bq[by]) + Math.abs(this.br[by14] - this.br[by]) == 1) {
                        if (this.J[this.bg[by14]] >= s2) {
                            short s9 = this.bg[by14];
                            this.J[s9] = (short)(this.J[s9] - s2);
                        } else {
                            this.J[this.bg[by14]] = 0;
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
                    while (by15 < this.bj) {
                        if ((this.bv[by15] <= 0 || this.bv[by15] >= 4) && Math.abs(this.bs[by15] - this.bs[by]) + Math.abs(this.bt[by15] - this.bt[by]) == 1) {
                            this.bv[by15] = 7;
                        }
                        by15 = (byte)(by15 + 1);
                    }
                    return;
                }
                byte by16 = 0;
                while (by16 < this.bi) {
                    if ((this.bu[by16] <= 0 || this.bu[by16] >= 4) && Math.abs(this.bq[by16] - this.bq[by]) + Math.abs(this.br[by16] - this.br[by]) == 1) {
                        this.bu[by16] = 7;
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
                    while (by17 < this.bj) {
                        if ((this.bv[by17] <= 0 || this.bv[by17] >= 4) && Math.abs(this.bq[by2] - this.bs[by17]) + Math.abs(this.br[by2] - this.bt[by17]) == 1) {
                            if (this.J[this.bh[by17]] >= s2) {
                                short s10 = this.bh[by17];
                                this.J[s10] = (short)(this.J[s10] - s2);
                            } else {
                                this.J[this.bh[by17]] = 0;
                            }
                        }
                        by17 = (byte)(by17 + 1);
                    }
                    return;
                }
                byte by18 = 0;
                while (by18 < this.bi) {
                    if ((this.bu[by18] <= 0 || this.bu[by18] >= 4) && Math.abs(this.bs[by2] - this.bq[by18]) + Math.abs(this.bt[by2] - this.br[by18]) == 1) {
                        if (this.J[this.bg[by18]] >= s2) {
                            short s11 = this.bg[by18];
                            this.J[s11] = (short)(this.J[s11] - s2);
                        } else {
                            this.J[this.bg[by18]] = 0;
                        }
                    }
                    by18 = (byte)(by18 + 1);
                }
                break;
            }
        }
    }

    public final void e(short s) {
        int n = this.M[s] / 10;
        switch (n) {
            case 9: {
                return;
            }
            case 8: {
                short s2 = s;
                this.M[s2] = (byte)(this.M[s2] + 2);
                return;
            }
            case 7: {
                short s3 = s;
                this.M[s3] = (byte)(this.M[s3] + 3);
                return;
            }
            case 6: {
                short s4 = s;
                this.M[s4] = (byte)(this.M[s4] + 4);
                return;
            }
            case 5: {
                short s5 = s;
                this.M[s5] = (byte)(this.M[s5] + 5);
                return;
            }
        }
        short s6 = s;
        this.M[s6] = (byte)(this.M[s6] + 6);
    }

    public final void f(short s) {
        int n;
        if (this.Q[s] < 0) {
            this.Q[s] = 30000;
            n = 0;
        }
        if (this.Q[s] < 2000) {
            n = 1;
        } else if (this.Q[s] < 4000) {
            n = 2;
        } else if (this.Q[s] < 7000) {
            n = 3;
        } else if (this.Q[s] < 10000) {
            n = 4;
        } else if (this.Q[s] < 15000) {
            n = 5;
        } else if (this.Q[s] < 20000) {
            n = 6;
        } else if (this.Q[s] < 30000) {
            n = 7;
        } else {
            this.Q[s] = 30000;
            n = 8;
        }
        if (this.H[s] < n) {
            this.H[s] = n;
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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean A() {
        this.ax = this.bg[0];
        this.j(this.bb);
        if (this.aS == 0 && this.ax == this.aZ) {
            if (!this.a(this.ax, true)) return false;
            this.ax = this.bg[this.bC];
            this.aq = 0;
            return true;
        } else {
            this.bC = 0;
            this.ao.Q = true;
        }
        return true;
    }

    public final void g(short s) {
        this.ax = s;
        byte by = this.a(this.bg, this.bi, s);
        this.j(this.bb);
        if (by == 0) {
            if (this.aS == 0 && this.ax == this.aZ) {
                this.ao.W = (byte)18;
            } else {
                this.bC = 0;
            }
        } else {
            this.bC = -by;
        }
        this.ao.Q = true;
    }

    public final byte h(short s) {
        byte by = 1;
        while (by <= 8) {
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
        while (by3 < this.r[by]) {
            if (this.A[by][by3] == this.j[by]) {
                by2 = by3;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        short s = this.j[by];
        by3 = by2;
        while (by3 > 0) {
            this.A[by][by3] = this.A[by][by3 - 1];
            by3 = (byte)(by3 - 1);
        }
        this.A[by][0] = s;
    }

    public final void a(short s, byte by, short s2) {
        byte by2 = by;
        byte by3 = this.r[by2];
        this.r[by2] = (byte)(by3 + 1);
        this.A[by][by3] = s;
        byte by4 = by;
        this.u[by4] = (short)(this.u[by4] + this.J[s]);
        if (this.i[by] == 0) {
            byte by5 = this.h(s2);
            this.d[by5][this.c[by5]] = by;
            byte by6 = by5;
            this.c[by6] = (byte)(this.c[by6] + 1);
            this.i[by] = s2;
            this.j[by] = s;
            return;
        }
        if (s == s2) {
            this.j[by] = s;
            this.h(by);
        }
    }

    public final void B() {
        byte by = (byte)Math.abs(this.bC);
        if (by < this.bi) {
            this.bu[by] = 1;
            byte[] byArray = this.bm[this.br[by]];
            byte by2 = this.bq[by];
            byArray[by2] = (byte)(byArray[by2] & 0x3F);
            return;
        }
        by = (byte)(by - this.bi);
        this.bv[by] = 1;
        byte[] byArray = this.bm[this.bt[by]];
        byte by3 = this.bs[by];
        byArray[by3] = (byte)(byArray[by3] & 0x3F);
    }

    public final void C() {
        byte by = this.ar;
        this.c[by] = (byte)(this.c[by] - 1);
        byte by2 = this.c[this.ar];
        byte by3 = 0;
        while (by3 < this.c[this.ar]) {
            if (this.d[this.ar][by3] == this.bb) {
                by2 = by3;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = by2;
        while (by3 < this.c[this.ar]) {
            this.d[this.ar][by3] = this.d[this.ar][by3];
            by3 = (byte)(by3 + 1);
        }
        this.d[this.as][this.c[this.as]] = this.bb;
        byte by4 = this.as;
        this.c[by4] = (byte)(this.c[by4] + 1);
        this.i[this.bb] = this.b[this.as];
        this.j[this.bb] = this.bh[0];
    }

    public final void D() {
        byte by = 0;
        byte by2 = 0;
        while (by2 < this.bj) {
            if (this.bv[by2] == 0 || this.bv[by2] > 3) {
                byte by3 = by;
                by = (byte)(by3 + 1);
                this.bh[by3] = this.bh[by2];
            }
            by2 = (byte)(by2 + 1);
        }
        this.bj = by;
        by2 = 0;
        while (by2 < this.bi) {
            if (this.bu[by2] == 2) {
                if (this.bj < 10) {
                    this.m(this.bg[by2]);
                    byte by4 = this.bj;
                    this.bj = (byte)(by4 + 1);
                    this.bh[by4] = this.bg[by2];
                } else {
                    this.B[this.bb] = this.b(this.B[this.bb], this.s[this.bb], this.bg[by2]);
                    byte by5 = this.bb;
                    this.s[by5] = (byte)(this.s[by5] + 1);
                }
            }
            by2 = (byte)(by2 + 1);
        }
        this.l[this.bb] = this.be;
        this.m[this.bb] = this.bf;
        if (this.bE) {
            byte by6 = this.bb;
            this.l[by6] = (short)(this.l[by6] + this.bc);
            byte by7 = this.bb;
            this.m[by7] = (short)(this.m[by7] + this.bd);
            if (this.l[this.bb] > 9999) {
                this.l[this.bb] = 9999;
            }
            if (this.m[this.bb] > 9999) {
                this.m[this.bb] = 9999;
            }
        }
        this.a(this.bh, this.bj);
        if (this.bG > 1) {
            this.ao.W = (byte)8;
            this.E();
            this.aq = 0;
            if (this.c[this.ar] <= 0) {
                this.bG = (byte)3;
            }
        }
    }

    public final void i(byte by) {
        if (by > 0) {
            this.a(this.ax, by, this.aZ);
        } else {
            this.B[this.bb] = this.b(this.B[this.bb], this.s[this.bb], this.ax);
            byte by2 = this.bb;
            this.s[by2] = (byte)(this.s[by2] + 1);
        }
        if (this.bC == 0 && by > 0) {
            byte by3 = by;
            this.l[by3] = (short)(this.l[by3] + this.bc);
            byte by4 = by;
            this.m[by4] = (short)(this.m[by4] + this.bd);
            if (this.l[by] > 9999) {
                this.l[by] = 9999;
            }
            if (this.m[by] > 9999) {
                this.m[by] = 9999;
            }
        }
        if (this.bC < 0) {
            this.ao.Q = false;
        } else {
            this.ao.Z = true;
            byte by5 = this.bC = (byte)(this.bC + 1);
            while (by5 < this.bi) {
                if (this.bu[by5] == 0 || this.bu[by5] > 3) break;
                by5 = (byte)(by5 + 1);
            }
            if (by5 < this.bi) {
                this.j(this.bb);
                this.bC = by5;
                this.ax = this.bg[this.bC];
            } else {
                if (this.bC < this.bi) {
                    this.bC = this.bi;
                }
                by5 = (byte)(this.bC - this.bi);
                while (by5 < this.bj) {
                    if (this.bv[by5] == 2) break;
                    by5 = (byte)(by5 + 1);
                }
                if (by5 < this.bj) {
                    this.j(this.bb);
                    if (this.aS > 0) {
                        this.m(this.bh[by5]);
                    }
                    this.bC = (byte)(this.bi + by5);
                    this.ax = this.bh[by5];
                } else {
                    this.ao.Q = false;
                    if (this.bp) {
                        this.C();
                    }
                    this.D();
                    this.aq = (byte)4;
                    return;
                }
            }
        }
        this.B();
    }

    public final void j(byte by) {
        int n = 0;
        byte by2 = 0;
        while (by2 < this.E[by]) {
            byte by3 = this.D[by][by2];
            if (this.aZ == this.i[by3] && this.r[by3] < 10 || this.i[by3] == 0) {
                int n2 = n;
                n = (byte)(n2 + 1);
                this.aR[n2] = by3;
            }
            by2 = (byte)(by2 + 1);
        }
        this.aR[n] = -1;
        this.aS = (byte)n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void E() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.ao.a != 0) {
                this.ao.i();
                if (!this.ao.aC) {
                    this.ao.a = 0;
                }
            }
            if (this.aq > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void b(short[] sArray, byte by, byte[] byArray) {
        byte by2 = 0;
        while (by2 < by) {
            if (byArray[by2] >= 4) {
                if (byArray[by2] > 7) {
                    short s = sArray[by2];
                    this.J[s] = (short)(this.J[s] - 100);
                    if (this.J[sArray[by2]] < 0) {
                        this.J[sArray[by2]] = 0;
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
        while (n < this.E[by]) {
            byte by2 = this.D[by][n];
            if (this.i[by2] == s && this.r[by2] < 10 || this.i[by2] == 0) {
                return true;
            }
            ++n;
        }
        return false;
    }

    public final boolean F() {
        if (!this.a(this.bb, this.ba)) {
            return false;
        }
        return this.J[this.bh[0]] < 100 && this.L[this.bh[0]] < 40;
    }

    public final short G() {
        short s = 0;
        byte by = 0;
        while (by < this.bi) {
            if (this.bu[by] <= 0 || this.bu[by] >= 4) {
                s = (short)(s + 500);
                s = (short)(s + this.J[this.bg[by]]);
            }
            by = (byte)(by + 1);
        }
        return s;
    }

    public final short H() {
        short s = 0;
        byte by = 0;
        while (by < this.bj) {
            if (this.bv[by] <= 0 || this.bv[by] >= 4) {
                s = (short)(s + 500);
                s = (short)(s + this.J[this.bh[by]]);
            }
            by = (byte)(by + 1);
        }
        return s;
    }

    public final boolean I() {
        if (this.Z == 1) {
            return this.F();
        }
        if (this.Z == 2) {
            return this.bW();
        }
        return true;
    }

    public final int j(byte by, byte by2) {
        byte by3 = this.d(this.bh[by]);
        byte[] byArray = new byte[16];
        int n = 0;
        byte by4 = 0;
        while (by4 < by3) {
            if (by4 != 8 && by4 != 11 && (this.J[this.bg[by2]] != 0 || by4 != 0 && by4 != 3 && by4 != 4) && this.a((int)by, (int)by2, by4, false) && this.g(by4) <= this.bl) {
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
        if (this.Z == 1) {
            return this.j(by, by2);
        }
        if (this.Z == 2) {
            return this.c((int)by, (int)by2);
        }
        return 0;
    }

    public final void a(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        byArray2[by] = new byte[this.bi];
        byte by2 = 0;
        while (by2 < this.bi) {
            if (this.bu[by2] <= 0 || this.bu[by2] >= 4) {
                int n = Math.abs(this.bs[by] - this.bq[by2]);
                int n2 = Math.abs(this.bt[by] - this.br[by2]);
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
        if (this.I()) {
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
        if (this.Z == 1) {
            return this.a(s, byArray, by, by2);
        }
        if (this.Z == 2) {
            return this.c(s, byArray, by, by2);
        }
        return 0;
    }

    public final byte J() {
        byte[] byArray = new byte[this.E[this.bb]];
        byte by = 0;
        int n = 0;
        while (n < this.E[this.bb]) {
            byte by2 = this.D[this.bb][n];
            if (this.i[by2] == this.ba && this.r[by2] < 10 || this.i[by2] == 0) {
                byArray[by] = by2;
                by = (byte)(by + 1);
            }
            ++n;
        }
        if (by > 0) {
            return this.b(this.ba, byArray, by, this.bb);
        }
        return -1;
    }

    public final void i(short s) {
        byte by = 0;
        while (by < this.bj) {
            if (this.bh[by] == s) {
                this.bv[by] = 1;
                byte[] byArray = this.bm[this.bt[by]];
                byte by2 = this.bs[by];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                return;
            }
            by = (byte)(by + 1);
        }
    }

    public final void a(short s, byte by) {
        if (by > 0) {
            this.a(s, by, this.ba);
        } else {
            this.B[this.bb] = this.b(this.B[this.bb], this.s[this.bb], s);
            byte by2 = this.bb;
            this.s[by2] = (byte)(this.s[by2] + 1);
        }
        this.i(s);
    }

    public final void a(short[] sArray, byte n) {
        this.u[this.bb] = this.b(sArray, (byte)n);
        this.r[this.bb] = n;
        int n2 = 0;
        while (n2 < n) {
            this.A[this.bb][n2] = sArray[n2];
            ++n2;
        }
    }

    public final void K() {
        int n;
        byte by;
        short[] sArray = new short[10];
        short[] sArray2 = new short[10];
        short[] sArray3 = new short[10];
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        boolean bl = false;
        int n5 = 0;
        while (n5 < this.bi) {
            if (this.bu[n5] == 0 || this.bu[n5] > 3) {
                int n6 = n2;
                n2 = (byte)(n6 + 1);
                sArray3[n6] = this.bg[n5];
            } else if (this.bu[n5] == 2) {
                int n7 = n3;
                n3 = (byte)(n7 + 1);
                sArray[n7] = this.bg[n5];
            }
            n5 = (byte)(n5 + 1);
        }
        n5 = 0;
        while (n5 < n2) {
            this.bg[n5] = sArray3[n5];
            ++n5;
        }
        this.bi = n2;
        sArray3 = new short[10];
        n2 = 0;
        if (this.bv[0] == 2 || this.bv[0] == 3 || this.J() < 0) {
            bl = true;
            if (this.P[this.bh[0]] == 100) {
                this.at = this.h(this.ba);
                this.bG = 1;
                this.bv[0] = 3;
            }
        }
        n5 = 0;
        while (n5 < this.bj) {
            if (this.bv[n5] == 0 || this.bv[n5] > 3) {
                int n8 = n2;
                n2 = (byte)(n8 + 1);
                sArray3[n8] = this.bh[n5];
            } else if (this.bv[n5] == 2) {
                int n9 = n4;
                n4 = (byte)(n9 + 1);
                sArray2[n9] = this.bh[n5];
            }
            n5 = (byte)(n5 + 1);
        }
        this.bj = (byte)n2;
        n5 = 0;
        while (n5 < this.bj) {
            this.bh[n5] = sArray3[n5];
            n5 = (byte)(n5 + 1);
        }
        n5 = 0;
        while (n5 < this.bj) {
            by = this.J();
            if (by < 0) {
                n = n5;
                while (n < this.bj) {
                    int n10 = n4;
                    n4 = (byte)(n10 + 1);
                    sArray2[n10] = this.bh[n];
                    n = (byte)(n + 1);
                }
                break;
            }
            if (!bl && n5 == 0) {
                byte by2 = by;
                this.l[by2] = (short)(this.l[by2] + this.be);
                byte by3 = by;
                this.m[by3] = (short)(this.m[by3] + this.bf);
                if (this.l[by] > 9999) {
                    this.l[by] = 9999;
                }
                if (this.m[by] > 9999) {
                    this.m[by] = 9999;
                }
            }
            this.a(this.bh[n5], by);
            n5 = (byte)(n5 + 1);
        }
        n5 = 0;
        while (n5 < n3) {
            by = this.J();
            if (by < 0) {
                n = n5;
                while (n < n3) {
                    byte by4 = this.bi;
                    this.bi = (byte)(by4 + 1);
                    this.bg[by4] = sArray[n];
                }
                break;
            }
            this.m(sArray[n5]);
            this.a(sArray[n5], by);
            n5 = (byte)(n5 + 1);
        }
        n5 = 0;
        while (n5 < n4) {
            if (this.bi >= 10) {
                this.B[this.bb] = this.b(this.B[this.bb], this.s[this.bb], sArray2[n5]);
                byte by5 = this.bb;
                this.s[by5] = (byte)(this.s[by5] + 1);
            } else {
                this.m(sArray2[n5]);
                byte by6 = this.bi;
                this.bi = (byte)(by6 + 1);
                this.bg[by6] = sArray2[n5];
            }
            n5 = (byte)(n5 + 1);
        }
        this.a(this.bg, this.bi);
        this.m[this.bb] = this.bd;
        this.l[this.bb] = this.bc;
        if (bl) {
            byte by7 = this.bb;
            this.l[by7] = (short)(this.l[by7] + this.be);
            byte by8 = this.bb;
            this.m[by8] = (short)(this.m[by8] + this.bf);
            if (this.l[this.bb] > 9999) {
                this.l[this.bb] = 9999;
            }
            if (this.m[this.bb] > 9999) {
                this.m[this.bb] = 9999;
            }
        }
        this.aq = (byte)4;
    }

    public final void L() {
        byte by;
        this.K();
        byte by2 = this.h(this.aZ);
        byte by3 = by = this.h(this.i[this.bb]);
        this.c[by3] = (byte)(this.c[by3] - 1);
        byte by4 = this.c[by];
        byte by5 = 0;
        while (by5 < this.c[by]) {
            if (this.d[by][by5] == this.bb) {
                by4 = by5;
                break;
            }
            by5 = (byte)(by5 + 1);
        }
        by5 = by4;
        while (by5 < this.c[by]) {
            this.d[by][by5] = this.d[by][by5 + 1];
            by5 = (byte)(by5 + 1);
        }
        this.d[by2][this.c[by2]] = this.bb;
        byte by6 = by2;
        this.c[by6] = (byte)(this.c[by6] + 1);
        this.i[this.bb] = this.b[by2];
        this.j[this.bb] = this.bg[0];
        if (this.c[by] == 0) {
            this.bG = (byte)4;
            this.a[this.at] = 0;
        }
    }

    public final boolean b(int n) {
        if (!this.a(this.bb, this.ba)) {
            return false;
        }
        return this.J[this.bh[n]] < 100 && this.L[this.bh[n]] < 50;
    }

    public final short M() {
        short s = 0;
        byte by = 0;
        while (by < this.bi) {
            if (this.bu[by] <= 0 || this.bu[by] >= 4) {
                s = (short)(s + 500);
                s = (short)(s + this.J[this.bg[by]]);
            }
            by = (byte)(by + 1);
        }
        return s;
    }

    public final void a(byte by, byte by2, byte by3, byte by4, byte[] byArray, byte[][] byArray2, byte[] byArray3, byte by5) {
        int n = by3 - by;
        int n2 = by4 - by2;
        byArray2[by5] = new byte[16];
        byArray3[by5] = 0;
        if (n >= 0 && n2 >= 0) {
            if (n - n2 >= 0) {
                if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by4 < this.bo - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                }
            } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            } else if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by4 < this.bo - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            }
        } else if (n < 0 && n2 >= 0) {
            if (n + n2 < 0) {
                if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by4 < this.bn - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                }
            } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            } else if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by4 < this.bn - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            }
        } else if (n >= 0 && n2 < 0) {
            if (n + n2 >= 0) {
                if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                } else if (by4 < this.bo - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                }
            } else if (by4 < this.bo - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            } else if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            }
        } else if (n < 0 && n2 < 0) {
            if (n - n2 < 0) {
                if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                } else if (by4 < this.bo - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                }
            } else if (by4 < this.bo - 1 && this.f(this.bm[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            } else if (by3 < this.bn - 1 && this.f(this.bm[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by3 > 0 && this.f(this.bm[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by4 > 0 && this.f(this.bm[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            }
        }
        while (by != by3 || by2 != by4) {
            n = by3 - by;
            n2 = by4 - by2;
            if (Math.abs(n) - Math.abs(n2) >= 0) {
                if (n >= 0 && (this.bm[by2][by + 1] & 0xC0) == 0) {
                    byArray2[by5][byArray3[by5]] = 4;
                    by = (byte)(by + 1);
                } else if (n < 0 && (this.bm[by2][by - 1] & 0xC0) == 0) {
                    byArray2[by5][byArray3[by5]] = 3;
                    by = (byte)(by - 1);
                } else if (n2 >= 0) {
                    byArray2[by5][byArray3[by5]] = 2;
                    by2 = (byte)(by2 + 1);
                } else {
                    byArray2[by5][byArray3[by5]] = 1;
                    by2 = (byte)(by2 - 1);
                }
            } else if (n2 >= 0 && (this.bm[by2 + 1][by] & 0xC0) == 0) {
                byArray2[by5][byArray3[by5]] = 2;
                by2 = (byte)(by2 + 1);
            } else if (n2 < 0 && (this.bm[by2 - 1][by] & 0xC0) == 0) {
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
        this.a(this.bs[by], this.bt[by], this.bq[by2], this.br[by2], byArray, byArray2, byArray3, by);
    }

    public final void b(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.Z == 1) {
            this.a(by, by2, byArray, byArray2, byArray3);
            return;
        }
        this.c(by, by2, byArray, byArray2, byArray3);
    }

    public final void b(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if ((this.bv[by] & 0x7F) != 0) {
            byArray[by] = 0;
            return;
        }
        byArray[by] = 1;
        this.b(by, (byte)0, byArray, byArray2, byArray3);
    }

    public final void a(int n, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        int n2 = 100;
        byArray2[n] = new byte[1];
        byte by = 0;
        while (by < this.bi) {
            if (this.bu[by] <= 0 || this.bu[by] >= 4) {
                int n3 = Math.abs(this.bs[0] - this.bq[by]);
                int n4 = Math.abs(this.bt[0] - this.br[by]);
                if (n3 <= 3 && n4 <= 3) {
                    byArray3[n] = 1;
                    n3 = Math.abs(this.bs[n] - this.bq[by]);
                    if (n2 > n3 + (n4 = Math.abs(this.bt[n] - this.br[by]))) {
                        n2 = n3 + n4;
                        byArray2[n][0] = by;
                        byArray[n] = 1;
                    }
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void c(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.bv[by] == 0) {
            this.a((int)by, byArray, byArray2, byArray3);
            if (byArray[by] == 1) {
                byte by2 = byArray2[by][0];
                this.b(by, by2, byArray, byArray2, byArray3);
                return;
            }
        }
        this.a(by, byArray, byArray2, byArray3);
        if (byArray3[by] > 0) {
            byArray[by] = 2;
        }
    }

    public final void j(short s) {
        int n = this.J[s] / 2;
        if ((n += this.s() % 100) < 200) {
            n = 200 + this.s() % 100;
        }
        if (n > this.J[s]) {
            n = this.J[s];
        }
        short s2 = s;
        this.J[s2] = (short)(this.J[s2] - n);
    }

    public final void b(int n, int n2) {
        try {
            this.ax = this.bg[n];
            this.ay = this.bh[n2];
            this.ao.X = this.ax;
            this.ao.Y = this.ay;
            this.bH = (byte)(this.bm[this.br[n]][this.bq[n]] & 0xF);
            this.ao.W = (byte)2;
            this.E();
            this.aq = (byte)3;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void a(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        byte by3 = 0;
        int n = 0;
        if (this.bl < 2) {
            return;
        }
        byte by4 = 0;
        while (by4 < this.bi) {
            byte by5;
            byte by6;
            if ((this.bu[by4] <= 0 || this.bu[by4] >= 4) && (by6 = (byte)Math.abs(this.bq[by4] - this.bs[by])) + (by5 = (byte)Math.abs(this.br[by4] - this.bt[by])) == 1) {
                this.bl = (byte)(this.bl - 2);
                this.b((int)by4, (int)by);
                return;
            }
            by4 = (byte)(by4 + 1);
        }
        switch (byArray[byArray2[by] - 1]) {
            case 1: {
                by3 = this.bs[by];
                n = (byte)(this.bt[by] - 1);
                break;
            }
            case 2: {
                by3 = this.bs[by];
                n = (byte)(this.bt[by] + 1);
                break;
            }
            case 3: {
                by3 = (byte)(this.bs[by] - 1);
                n = this.bt[by];
                break;
            }
            case 4: {
                by3 = (byte)(this.bs[by] + 1);
                n = this.bt[by];
            }
        }
        byte by7 = (byte)this.h(this.bm[n][by3], this.I[this.bh[by]]);
        if (this.bl >= by7) {
            this.bl = (byte)(this.bl - by7);
            byte[] byArray3 = this.bm[this.bt[by]];
            byte by8 = this.bs[by];
            byArray3[by8] = (byte)(byArray3[by8] & 0x3F);
            byte[] byArray4 = this.bm[n];
            byte by9 = by3;
            byArray4[by9] = (byte)(byArray4[by9] | 0x80);
            this.bs[by] = by3;
            this.bt[by] = n;
            byte by10 = by;
            byArray2[by10] = (byte)(byArray2[by10] - 1);
            if ((this.bm[n][by3] & 0x20) != 0) {
                this.bD = "\u4e2d\u8ba1\u8c0b\u5341\u9762\u57cb\u4f0f";
                this.ao.W = (byte)100;
                this.E();
                this.aq = 0;
                this.j(this.bh[by]);
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
        this.bl = (byte)(this.bl - this.g(by4));
        int n = this.a(this.bh[by], this.bg[by3], by4, this.bd);
        if (n > 0) {
            this.a(n, by4);
            short s = this.J[this.bg[by3]];
            this.a(this.bg[by3], this.bu, by3, by, n, false);
            short s2 = this.bh[by];
            this.Q[s2] = (short)(this.Q[s2] + (s - this.J[this.bg[by3]]));
            this.f(this.bh[by]);
        } else {
            this.bD = "\u8ba1\u7b56\u5931\u8d25\uff01";
        }
        this.ao.W = (byte)100;
        this.E();
        this.aq = 0;
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
                this.ao.W = (byte)3;
                this.E();
                this.aq = 0;
                this.a(this.bh[by], this.J());
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (Exception exception) {}
        this.ao.repaint();
    }

    public final void N() {
        byte by = -1;
        byte[] byArray = new byte[this.bj];
        byte[][] byArray2 = new byte[this.bj][];
        byte[] byArray3 = new byte[this.bj];
        while (by != this.bl && this.bl >= 2) {
            System.gc();
            by = this.bl;
            byte by2 = 0;
            while (by2 < this.bj) {
                byArray[by2] = 0;
                byArray3[by2] = 0;
                by2 = (byte)(by2 + 1);
            }
            this.a(byArray, byArray2, byArray3);
            if (byArray[0] == 3) {
                this.ao.W = (byte)5;
                this.E();
                return;
            }
            by2 = 1;
            while (by2 < this.bj) {
                if ((this.bv[by2] & 0x1F) == 0) {
                    if (this.b((int)by2)) {
                        byArray[by2] = 3;
                    } else {
                        this.b(by2, byArray, byArray2, byArray3);
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            by2 = this.bF;
            while (by2 < this.bj) {
                System.gc();
                if ((this.bv[by2] & 0x1F) == 0) {
                    this.d(by2, byArray[by2], byArray2[by2], byArray3);
                    if (this.aq > 0) {
                        this.bF = (byte)(by2 + 1);
                        return;
                    }
                }
                by2 = (byte)(by2 + 1);
            }
        }
        System.gc();
    }

    public final byte a(short[] sArray, byte[] byArray, byte by) {
        int n = 0;
        int n2 = 1;
        while (n2 < 64) {
            if ((sArray[n2] & 0x100) == 0 && sArray[n2] < sArray[n]) {
                n = n2;
            }
            ++n2;
        }
        return (byte)n;
    }

    public final void O() {
        if (this.Z == 1) {
            this.N();
            return;
        }
        if (this.Z == 2) {
            this.bX();
        }
    }

    public final boolean k(byte by) {
        if ((this.bm[this.bt[by]][this.bs[by]] & 0xF) == 2) {
            this.ao.W = (byte)21;
            this.E();
            return true;
        }
        return false;
    }

    public final void b(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.I()) {
            byArray[0] = 3;
            return;
        }
        this.b((byte)0, byArray, byArray2, byArray3);
    }

    public final void P() {
        byte by = -1;
        byte[] byArray = new byte[this.bj];
        byte[][] byArray2 = new byte[this.bj][];
        byte[] byArray3 = new byte[this.bj];
        while (by != this.bl && this.bl >= 2) {
            System.gc();
            by = this.bl;
            byte by2 = 0;
            while (by2 < this.bj) {
                byArray[by2] = 0;
                byArray3[by2] = 0;
                by2 = (byte)(by2 + 1);
            }
            this.b(byArray, byArray2, byArray3);
            if (byArray[0] == 3) {
                this.ao.W = (byte)5;
                this.E();
                return;
            }
            by2 = 1;
            while (by2 < this.bj) {
                if ((this.bv[by2] & 0x1F) == 0) {
                    if (this.b((int)by2)) {
                        byArray[by2] = 3;
                    } else {
                        this.b(by2, byArray, byArray2, byArray3);
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            by2 = 0;
            while (by2 < this.bj) {
                System.gc();
                if ((this.bv[by2] & 0x1F) == 0) {
                    this.d(by2, byArray[by2], byArray2[by2], byArray3);
                    if (this.k(by2)) {
                        this.aq = (byte)4;
                        return;
                    }
                    if (this.aq > 0) {
                        this.bF = (byte)(by2 + 1);
                        return;
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            System.gc();
        }
    }

    public final void Q() {
        if (this.Z == 1) {
            this.P();
            return;
        }
        if (this.Z == 2) {
            this.bY();
        }
    }

    public final void R() {
        block13: {
            do {
                if (this.aq == 0) {
                    this.cl = true;
                    this.bA = (byte)(this.bA + 1);
                    this.bB = true;
                    if (this.bA > 1) {
                        this.bk = this.a(this.bg, this.bi, this.bu, this.bk);
                    }
                    this.E();
                } else if (this.aq == 2 || this.aq == 5 || this.aq == 6) {
                    this.aq = 0;
                    this.E();
                }
                if (this.aq == 1) {
                    this.aq = 0;
                    this.b(this.bg, this.bi, this.bu);
                    this.bl = this.a(this.bh, this.bj, this.bv, this.bl);
                    this.bB = false;
                    this.cl = false;
                    this.bF = 0;
                } else {
                    if (this.aq == 2 || this.aq == 4 || this.aq == 5) break block13;
                    if (this.aq == 6) {
                        return;
                    }
                    if (this.aq == 3) {
                        this.aq = 0;
                    }
                }
                if (this.bp) {
                    this.Q();
                } else {
                    this.O();
                }
                if (this.aq == 3) {
                    return;
                }
                this.b(this.bh, this.bj, this.bv);
                this.bd = (short)(this.bd - ((this.a(this.bg, this.bi, this.bu) - 1) / 250 + 1));
                if (this.bd <= 0) {
                    this.bd = 0;
                    this.ao.W = (byte)7;
                    this.E();
                    return;
                }
                this.bf = (short)(this.bf - ((this.a(this.bh, this.bj, this.bv) - 1) / 250 + 1));
                if (this.bf > 0) continue;
                this.bf = 0;
                this.ao.W = (byte)6;
            } while (this.bA != 30);
            this.ao.W = (byte)20;
            this.E();
        }
    }

    public final void S() {
        this.cm = false;
        this.bI = this.J[this.ax] == 0 ? (byte)1 : (byte)((this.J[this.ax] - 1) / 100 + 2);
        this.bJ = this.J[this.ay] == 0 ? (byte)1 : (byte)((this.J[this.ay] - 1) / 100 + 2);
        int n = 0;
        while (n < this.bL) {
            byte by = 0;
            while (by < this.bM) {
                this.bK[by][n] = 0;
                by = (byte)(by + 1);
            }
            n = (byte)(n + 1);
        }
        n = 0;
        while (n < 4) {
            this.cj[n] = 0;
            n = (byte)(n + 1);
        }
        if (this.bH == 2) {
            if (this.cl) {
                this.bK[0][11] = 32;
                this.bK[6][11] = 32;
                this.bK[1][11] = 32;
                this.bK[5][11] = 32;
                this.bK[3][9] = 32;
                this.bK[0][6] = 2;
                this.bK[1][10] = 16;
                this.bK[6][6] = 2;
                this.bK[5][10] = 16;
                n = 6;
                while (n < 10) {
                    this.bK[1][n] = 32;
                    this.bK[5][n] = 32;
                    n = (byte)(n + 1);
                }
            } else {
                this.bK[0][4] = 32;
                this.bK[6][4] = 32;
                this.bK[1][4] = 32;
                this.bK[5][4] = 32;
                this.bK[6][4] = 32;
                this.bK[3][6] = 32;
                this.bK[0][9] = 2;
                this.bK[1][5] = 16;
                this.bK[6][9] = 2;
                this.bK[5][5] = 16;
                n = 6;
                while (n < 10) {
                    this.bK[1][n] = 32;
                    this.bK[5][n] = 32;
                    n = (byte)(n + 1);
                }
                n = 0;
                while (n < 4) {
                    this.cj[n] = 1;
                    n = (byte)(n + 1);
                }
            }
            this.cm = true;
        }
        this.o();
        this.cc = 0;
        this.cd = 0;
        this.ce = 0;
        this.cf = 0;
        this.ci = 0;
        this.ck = 0;
        this.co = 0;
        this.cp = 0;
        this.cv = false;
        n = 0;
        while (n < 4) {
            this.cg[n] = 0;
            this.ch[n] = 0;
            n = (byte)(n + 1);
        }
        this.ao.a((byte)21);
        this.ao.af = 1;
        this.ao.ag = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void T() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.ao.a != 0) {
                this.ao.h();
                this.ao.a = 0;
            }
            if (this.aq > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final boolean U() {
        int n = 0;
        int n2 = 0;
        if (this.bY[1][0] == 2) {
            return true;
        }
        int n3 = 1;
        while (n3 < this.bI) {
            if (this.cb[0][n3]) {
                n = (short)(n + this.bZ[0][n3]);
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < this.bJ) {
            if (this.cb[1][n3]) {
                n2 = (short)(n2 + this.bZ[1][n3]);
            }
            ++n3;
        }
        return n > 300 && n2 < 100;
    }

    public final void c(int n) {
        if (this.U() && n >= 4) {
            int n2 = 0;
            while (n2 < this.bJ) {
                this.bY[1][n2] = 2;
                ++n2;
            }
            return;
        }
        int n3 = 0;
        while (n3 < this.bJ) {
            this.bY[1][n3] = 1;
            ++n3;
        }
    }

    public final void d(int n) {
        if (this.Z == 1) {
            this.c(n);
            return;
        }
        if (this.Z == 2) {
            this.z(n);
        }
    }

    public final void e(int n) {
        if (this.U() && n >= 4) {
            int n2 = 0;
            while (n2 < this.bJ) {
                this.bY[1][n2] = 2;
                ++n2;
            }
            return;
        }
        this.bY[1][0] = n >= 7 ? (byte)0 : 1;
        int n3 = 1;
        while (n3 < this.bJ) {
            this.bY[1][n3] = 0;
            ++n3;
        }
    }

    public final void f(int n) {
        if (this.Z == 1) {
            this.e(n);
            return;
        }
        if (this.Z == 2) {
            this.A(n);
        }
    }

    public final void g(int n) {
        if (this.Z == 1) {
            this.e(n);
            return;
        }
        if (this.Z == 2) {
            this.B(n);
        }
    }

    public final void h(int n) {
        if (this.cm) {
            if (this.cl) {
                this.d(n);
                return;
            }
            this.f(n);
            return;
        }
        this.g(n);
    }

    public final byte l(byte by) {
        byte by2 = this.bS[1][0];
        byte by3 = this.bT[1][0];
        byte by4 = this.bS[0][by];
        byte by5 = this.bT[0][by];
        int n = -1;
        if (by2 < 15 && (this.bK[by3][by2 + 1] & 0x60) == 0 && by3 - by5 != 0) {
            by2 = (byte)(by2 + 1);
        } else if (by3 - by5 <= 0) {
            if (by3 > 0 && (this.bK[by3 - 1][by2] & 0x60) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < 6 && (this.bK[by3 + 1][by2] & 0x60) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by2 > 0 && (this.bK[by3][by2 - 1] & 0x60) == 0) {
                by2 = (byte)(by2 - 1);
            }
        } else if (by3 < 6 && (this.bK[by3 + 1][by2] & 0x60) == 0) {
            by3 = (byte)(by3 + 1);
        } else if (by3 > 0 && (this.bK[by3 - 1][by2] & 0x60) == 0) {
            by3 = (byte)(by3 - 1);
        } else if (by2 > 0 && (this.bK[by3][by2 - 1] & 0x60) == 0) {
            by2 = (byte)(by2 - 1);
        }
        if (by4 < 15 && by2 - by4 > 0 && (this.bK[by5][by4 + 1] & 0x60) == 0) {
            if (by2 == by4 + 1 && by3 == by5) {
                n = 3;
            } else {
                return 3;
            }
        }
        if (by4 > 0 && by2 - by4 < 0 && (this.bK[by5][by4 - 1] & 0x60) == 0) {
            if (by2 == by4 - 1 && by3 == by5) {
                n = 2;
            } else {
                return 2;
            }
        }
        if (by5 < 6 && by3 - by5 > 0 && (this.bK[by5 + 1][by4] & 0x60) == 0) {
            return 1;
        }
        if (by5 > 0 && by3 - by5 < 0 && (this.bK[by5 - 1][by4] & 0x60) == 0) {
            return 0;
        }
        if (by2 - by4 == 0) {
            if (by4 < 15 && by4 > 0 && (this.bK[by5][by4 + 1] & 0x60) == 0 && this.bK[by5][by4 - 1] == 0) {
                return (byte)(this.s() % 2 + 2);
            }
            if (by4 < 15 && (this.bK[by5][by4 + 1] & 0x60) == 0) {
                return 3;
            }
            if (by4 > 0 && (this.bK[by5][by4 - 1] & 0x60) == 0) {
                return 2;
            }
        }
        if (by3 - by5 == 0) {
            if (by5 < 6 && by5 > 0 && (this.bK[by5 + 1][by4] & 0x60) == 0 && (this.bK[by5 - 1][by4] & 0x60) == 0) {
                return (byte)(this.s() % 2);
            }
            if (by5 < 6 && (this.bK[by5 + 1][by4] & 0x60) == 0) {
                return 1;
            }
            if (by5 > 0 && (this.bK[by5 - 1][by4] & 0x60) == 0) {
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
        if (this.bT[0][by] > 0 && (this.bK[this.bT[0][by] - 1][this.bS[0][by]] & 0x80) != 0) {
            if (by2 == 0 || this.bU[0][by] == 0 && this.bS[1][0] == this.bS[0][by] && this.bT[1][0] == this.bT[0][by] - 1) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (this.bT[0][by] < 6 && (this.bK[this.bT[0][by] + 1][this.bS[0][by]] & 0x80) != 0) {
            if (by2 == 1 || this.bU[0][by] == 0 && this.bS[1][0] == this.bS[0][by] && this.bT[1][0] == this.bT[0][by] + 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (this.bS[0][by] > 0 && (this.bK[this.bT[0][by]][this.bS[0][by] - 1] & 0x80) != 0) {
            if (by2 == 2 || this.bU[0][by] == 0 && this.bS[1][0] == this.bS[0][by] - 1 && this.bT[1][0] == this.bT[0][by]) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (this.bS[0][by] < 15 && (this.bK[this.bT[0][by]][this.bS[0][by] + 1] & 0x80) != 0) {
            if (by2 == 3 || this.bU[0][by] == 0 && this.bS[1][0] == this.bS[0][by] + 1 && this.bT[1][0] == this.bT[0][by]) {
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
        this.cr = by;
        this.cs = by2;
        this.ct = by3;
        this.cu = by4;
        this.cv = bl;
        this.cq = (byte)6;
    }

    public final synchronized void b(byte by, byte by2, boolean bl) {
        byte by3;
        byte by4;
        if (bl) {
            by4 = this.bS[1][by];
            by3 = this.bT[1][by];
        } else {
            by4 = this.bS[0][by];
            by3 = this.bT[0][by];
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
            while (by5 < this.bI) {
                if (this.bS[0][by5] == by4 && this.bT[0][by5] == by3) {
                    this.a((byte)1, by, (byte)0, by5, bl);
                }
                by5 = (byte)(by5 + 1);
            }
            return;
        }
        byte by6 = 0;
        while (by6 < this.bJ) {
            if (this.bS[1][by6] == by4 && this.bT[1][by6] == by3) {
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
        if (this.bT[0][by] > 0 && (this.bK[this.bT[0][by] - 1][this.bS[0][by]] & 0x80) != 0 && by2 == 0) {
            return 0;
        }
        if (this.bT[0][by] < 6 && (this.bK[this.bT[0][by] + 1][this.bS[0][by]] & 0x80) != 0 && by2 == 1) {
            return 1;
        }
        if (this.bS[0][by] > 0 && (this.bK[this.bT[0][by]][this.bS[0][by] - 1] & 0x80) != 0 && by2 == 2) {
            return 2;
        }
        if (this.bS[0][by] < 15 && (this.bK[this.bT[0][by]][this.bS[0][by] + 1] & 0x80) != 0 && by2 == 3) {
            return 3;
        }
        if ((this.cd & 0xF0) == 48) {
            n2 = 7;
        }
        int n3 = 1;
        while (n3 < n2) {
            if (this.bT[0][by] + n3 > 6) break;
            if ((this.bK[this.bT[0][by] + n3][this.bS[0][by]] & 0x80) != 0) {
                ++n;
                byArray[0] = 1;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bT[0][by] < n3) break;
            if ((this.bK[this.bT[0][by] - n3][this.bS[0][by]] & 0x80) != 0) {
                byArray[n++] = 0;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bS[0][by] < n3) break;
            if ((this.bK[this.bT[0][by]][this.bS[0][by] - n3] & 0x80) != 0) {
                byArray[n++] = 2;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bS[0][by] + n3 > 15) break;
            if ((this.bK[this.bT[0][by]][this.bS[0][by] + n3] & 0x80) != 0) {
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
            by5 = this.bS[1][by];
            by4 = this.bT[1][by];
            n = 64;
            if ((this.ce & 0xF0) == 48) {
                by6 = 7;
            }
        } else {
            by5 = this.bS[0][by];
            by4 = this.bT[0][by];
            n = -128;
            if ((this.cd & 0xF0) == 48) {
                by6 = 7;
            }
        }
        block0 : switch (by2) {
            case 0: {
                by3 = 1;
                while (by3 < by6) {
                    if (by4 - by3 >= 0 && (this.bK[by4 - by3][by5] & n) != 0) {
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
                    if (by4 + by3 <= 6 && (this.bK[by4 + by3][by5] & n) != 0) {
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
                    if (by5 - by3 >= 0 && (this.bK[by4][by5 - by3] & n) != 0) {
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
                    if (by5 + by3 <= 15 && (this.bK[by4][by5 + by3] & n) != 0) {
                        by5 = (byte)(by5 + by3);
                        break block0;
                    }
                    by3 = (byte)(by3 + 1);
                }
                break;
            }
        }
        if (bl) {
            this.ao.a(this.bS[1][by], this.bT[1][by], by5, by4, !bl);
            by3 = 0;
            while (by3 < this.bI) {
                if (this.bS[0][by3] == by5 && this.bT[0][by3] == by4) {
                    this.a((byte)1, by, (byte)0, by3, bl);
                }
                by3 = (byte)(by3 + 1);
            }
            return;
        }
        this.ao.a(this.bS[0][by], this.bT[0][by], by5, by4, !bl);
        by3 = 0;
        while (by3 < this.bJ) {
            if (this.bS[1][by3] == by5 && this.bT[1][by3] == by4) {
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
            this.bX[0][by] = by2;
        }
        switch (this.bU[0][by]) {
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
        if (this.bT[0][by] > 0 && (this.bK[this.bT[0][by] - 1][this.bS[0][by]] & 0x80) != 0) {
            ++n;
            byArray[0] = 0;
        }
        if (this.bT[0][by] < 6 && (this.bK[this.bT[0][by] + 1][this.bS[0][by]] & 0x80) != 0) {
            byArray[n++] = 1;
        }
        if (this.bS[0][by] > 0 && (this.bK[this.bT[0][by]][this.bS[0][by] - 1] & 0x80) != 0) {
            byArray[n++] = 2;
        }
        if (this.bS[0][by] < 15 && (this.bK[this.bT[0][by]][this.bS[0][by] + 1] & 0x80) != 0) {
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
        if ((this.cd & 0xF0) == 48) {
            by2 = 7;
        }
        byte by3 = 1;
        while (by3 < by2) {
            if (this.bT[0][by] < by3) break;
            if ((this.bK[this.bT[0][by] - by3][this.bS[0][by]] & 0x80) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bT[0][by] + by3 > 6) break;
            if ((this.bK[this.bT[0][by] + by3][this.bS[0][by]] & 0x80) != 0) {
                byArray[n++] = 1;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bS[0][by] < by3) break;
            if ((this.bK[this.bT[0][by]][this.bS[0][by] - by3] & 0x80) != 0) {
                byArray[n++] = 2;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bS[0][by] + by3 > 15) break;
            if ((this.bK[this.bT[0][by]][this.bS[0][by] + by3] & 0x80) != 0) {
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
        switch (this.bU[0][by]) {
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
        if (this.bS[0][by] > 0 && (this.bK[this.bT[0][by]][this.bS[0][by] - 1] & 0xE0) != 0) {
            int n = 0;
            if (this.bT[0][by] > 0 && (this.bK[this.bT[0][by] - 1][this.bS[0][by]] & 0xE0) == 0) {
                ++n;
            }
            if (this.bT[0][by] < 6 && (this.bK[this.bT[0][by] + 1][this.bS[0][by]] & 0xE0) == 0) {
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
        if (this.bS[0][by] == 0) {
            byte[] byArray = this.bK[this.bT[0][by]];
            byte by2 = this.bS[0][by];
            byArray[by2] = (byte)(byArray[by2] & 0x32);
            this.bS[0][by] = -1;
            this.bT[0][by] = -1;
            this.cb[0][by] = false;
            return true;
        }
        this.e((byte)0, by);
        return true;
    }

    public final byte p(byte by, byte by2) {
        byte[] byArray = new byte[5];
        int n = 0;
        byte by3 = this.bS[0][by];
        byte by4 = this.bT[0][by];
        byte by5 = this.bS[1][0];
        byte by6 = this.bT[1][0];
        if (by2 == 0 && by6 == by4 - 1 && by5 == by3) {
            by2 = (byte)-1;
        } else if (by4 > 0 && (this.bK[by4 - 1][by3] & 0x80) != 0 && (by6 != by4 - 1 || by5 != by3)) {
            if (by2 == 0) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (by2 == 1 && by6 == by4 + 1 && by5 == by3) {
            by2 = (byte)-1;
        } else if (by4 < 6 && (this.bK[by4 + 1][by3] & 0x80) != 0 && (by6 != by4 + 1 || by5 != by3)) {
            if (by2 == 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (by2 == 2 && by6 == by4 && by5 == by3 - 1) {
            by2 = (byte)-1;
        } else if (by3 > 0 && (this.bK[by4][by3 - 1] & 0x80) != 0 && (by6 != by4 || by5 != by3 - 1)) {
            if (by2 == 2) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (by2 == 3 && by6 == by4 && by5 == by3 + 1) {
            by2 = (byte)-1;
        } else if (by3 < 15 && (this.bK[by4][by3 + 1] & 0x80) != 0 && (by5 != by3 + 1 || by6 != by4)) {
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
        byte by3 = this.bS[0][by];
        int n2 = this.bT[0][by];
        byte by4 = this.bS[1][0];
        byte by5 = this.bT[1][0];
        int n3 = 5;
        if (n2 > 0 && (this.bK[n2 - 1][by3] & 0x80) != 0 && by2 == 0) {
            if (by5 == n2 - 1 && by4 == by3) {
                by2 = (byte)-1;
            } else {
                return 0;
            }
        }
        if (n2 < 6 && (this.bK[n2 + 1][by3] & 0x80) != 0 && by2 == 1) {
            if (by5 == n2 + 1 && by4 == by3) {
                by2 = (byte)-1;
            } else {
                return 1;
            }
        }
        if (by3 > 0 && (this.bK[n2][by3 - 1] & 0x80) != 0 && by2 == 2) {
            if (by5 == n2 && by4 == by3 - 1) {
                by2 = (byte)-1;
            } else {
                return 2;
            }
        }
        if (by3 < 15 && (this.bK[n2][by3 + 1] & 0x80) != 0 && by2 == 3) {
            if (by5 == n2 && by4 == by3 + 1) {
                by2 = (byte)-1;
            } else {
                return 3;
            }
        }
        if ((this.cd & 0xF0) == 48) {
            n3 = 7;
        }
        int n4 = 1;
        while (n4 < n3) {
            if (n2 < n4 || by5 == n2 - n4 && by4 == by3) break;
            if ((this.bK[n2 - n4][by3] & 0x80) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            ++n4;
        }
        n4 = 1;
        while (n4 < n3) {
            if (n2 + n4 > 6 || by5 == n2 + n4 && by4 == by3) break;
            if ((this.bK[n2 + n4][by3] & 0x80) != 0) {
                byArray[n++] = 1;
                break;
            }
            ++n4;
        }
        n4 = 1;
        while (n4 < n3) {
            if (by3 < n4 || by5 == n2 && by4 == by3 - n4) break;
            if ((this.bK[n2][by3 - n4] & 0x80) != 0) {
                byArray[n++] = 2;
                break;
            }
            ++n4;
        }
        n4 = 1;
        while (n4 < n3) {
            if (by3 + n4 > 15 || by5 == n2 && by4 == by3 + n4) break;
            if ((this.bK[n2][by3 + n4] & 0x80) != 0) {
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
            this.bX[0][by] = by2;
        }
        switch (this.bU[0][by]) {
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
        if (!this.cb[0][by]) {
            return false;
        }
        this.bY[0][by] = this.cj[this.bU[0][by]];
        switch (this.bY[0][by]) {
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
        byte by2 = this.bS[0][0];
        byte by3 = this.bT[0][0];
        byte by4 = this.bS[1][by];
        byte by5 = this.bT[1][by];
        int n = -1;
        if (by2 > 0 && (this.bK[by3][by2 + 1] & 0xA0) == 0 && by3 - by5 != 0) {
            by2 = (byte)(by2 - 1);
        } else if (by3 - by5 <= 0) {
            if (by3 > 0 && (this.bK[by3 - 1][by2] & 0xA0) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < 6 && (this.bK[by3 + 1][by2] & 0xA0) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by2 < 15 && (this.bK[by3][by2 - 1] & 0xA0) == 0) {
                by2 = (byte)(by2 + 1);
            }
        } else if (by3 < 6 && (this.bK[by3 + 1][by2] & 0xA0) == 0) {
            by3 = (byte)(by3 + 1);
        } else if (by3 > 0 && (this.bK[by3 - 1][by2] & 0xA0) == 0) {
            by3 = (byte)(by3 - 1);
        } else if (by2 < 15 && (this.bK[by3][by2 - 1] & 0xA0) == 0) {
            by2 = (byte)(by2 + 1);
        }
        if (by4 < 15 && by2 - by4 > 0 && (this.bK[by5][by4 + 1] & 0xA0) == 0) {
            if (by2 == by4 + 1 && by3 == by5) {
                n = 3;
            } else {
                return 3;
            }
        }
        if (by4 > 0 && by2 - by4 < 0 && (this.bK[by5][by4 - 1] & 0xA0) == 0) {
            if (by2 == by4 - 1 && by3 == by5) {
                n = 2;
            } else {
                return 2;
            }
        }
        if (by5 < 6 && by3 - by5 > 0 && (this.bK[by5 + 1][by4] & 0xA0) == 0) {
            return 1;
        }
        if (by5 > 0 && by3 - by5 < 0 && (this.bK[by5 - 1][by4] & 0xA0) == 0) {
            return 0;
        }
        if (by2 - by4 == 0) {
            if (by4 < 15 && by4 > 0 && (this.bK[by5][by4 + 1] & 0xA0) == 0 && this.bK[by5][by4 - 1] == 0) {
                return (byte)(this.s() % 2 + 2);
            }
            if (by4 < 15 && (this.bK[by5][by4 + 1] & 0xA0) == 0) {
                return 3;
            }
            if (by4 > 0 && (this.bK[by5][by4 - 1] & 0xA0) == 0) {
                return 2;
            }
        }
        if (by3 - by5 == 0) {
            if (by5 > 0 && by5 < 6 && (this.bK[by5 + 1][by4] & 0xA0) == 0 && (this.bK[by5 - 1][by4] & 0xA0) == 0) {
                return (byte)(this.s() % 2);
            }
            if (by5 < 6 && (this.bK[by5 + 1][by4] & 0xA0) == 0) {
                return 1;
            }
            if (by5 > 0 && (this.bK[by5 - 1][by4] & 0xA0) == 0) {
                return 0;
            }
        }
        return (byte)n;
    }

    public final byte t(byte by, byte by2) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.bT[1][by] > 0 && (this.bK[this.bT[1][by] - 1][this.bS[1][by]] & 0x40) != 0) {
            if (by2 == 0 || this.bU[1][by] == 0 && this.bS[0][0] == this.bS[1][by] && this.bT[0][0] == this.bT[1][by] - 1) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (this.bT[1][by] < 6 && (this.bK[this.bT[1][by] + 1][this.bS[1][by]] & 0x40) != 0) {
            if (by2 == 1 || this.bU[1][by] == 0 && this.bS[0][0] == this.bS[1][by] && this.bT[0][0] == this.bT[1][by] + 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (this.bS[1][by] > 0 && (this.bK[this.bT[1][by]][this.bS[1][by] - 1] & 0x40) != 0) {
            if (by2 == 2 || this.bU[1][by] == 0 && this.bS[0][0] == this.bS[1][by] - 1 && this.bT[0][0] == this.bT[1][by]) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (this.bS[1][by] < 15 && (this.bK[this.bT[1][by]][this.bS[1][by] + 1] & 0x40) != 0) {
            if (by2 == 3 || this.bU[1][by] == 0 && this.bS[0][0] == this.bS[1][by] + 1 && this.bT[0][0] == this.bT[1][by]) {
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
        if (this.bT[1][by] > 0 && (this.bK[this.bT[1][by] - 1][this.bS[1][by]] & 0x40) != 0 && by2 == 0) {
            return 0;
        }
        if (this.bT[1][by] < 6 && (this.bK[this.bT[1][by] + 1][this.bS[1][by]] & 0x40) != 0 && by2 == 1) {
            return 1;
        }
        if (this.bS[1][by] > 0 && (this.bK[this.bT[1][by]][this.bS[1][by] - 1] & 0x40) != 0 && by2 == 2) {
            return 2;
        }
        if (this.bS[1][by] < 15 && (this.bK[this.bT[1][by]][this.bS[1][by] + 1] & 0x40) != 0 && by2 == 3) {
            return 3;
        }
        if ((this.ce & 0xF0) == 48) {
            n2 = 7;
        }
        int n3 = 1;
        while (n3 < n2) {
            if (this.bT[1][by] < n3) break;
            if ((this.bK[this.bT[1][by] - n3][this.bS[1][by]] & 0x40) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bT[1][by] + n3 > 6) break;
            if ((this.bK[this.bT[1][by] + n3][this.bS[1][by]] & 0x40) != 0) {
                byArray[n++] = 1;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bS[1][by] < n3) break;
            if ((this.bK[this.bT[1][by]][this.bS[1][by] - n3] & 0x40) != 0) {
                byArray[n++] = 2;
                break;
            }
            ++n3;
        }
        n3 = 1;
        while (n3 < n2) {
            if (this.bS[1][by] + n3 > 15) break;
            if ((this.bK[this.bT[1][by]][this.bS[1][by] + n3] & 0x40) != 0) {
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
        if (this.cb[1][by]) {
            byte by2 = this.t(by);
            if (by2 >= 0) {
                this.bX[1][by] = by2;
            }
            switch (this.bU[1][by]) {
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
        if (this.bT[1][by] > 0 && (this.bK[this.bT[1][by] - 1][this.bS[1][by]] & 0x40) != 0) {
            ++n;
            byArray[0] = 0;
        }
        if (this.bT[1][by] < 6 && (this.bK[this.bT[1][by] + 1][this.bS[1][by]] & 0x40) != 0) {
            byArray[n++] = 1;
        }
        if (this.bS[1][by] > 0 && (this.bK[this.bT[1][by]][this.bS[1][by] - 1] & 0x40) != 0) {
            byArray[n++] = 2;
        }
        if (this.bS[1][by] < 15 && (this.bK[this.bT[1][by]][this.bS[1][by] + 1] & 0x40) != 0) {
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
        if ((this.ce & 0xF0) == 48) {
            by2 = 7;
        }
        byte by3 = 1;
        while (by3 < by2) {
            if (this.bT[1][by] < by3) break;
            if ((this.bK[this.bT[1][by] - by3][this.bS[1][by]] & 0x40) != 0) {
                ++n;
                byArray[0] = 0;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bT[1][by] + by3 > 6) break;
            if ((this.bK[this.bT[1][by] + by3][this.bS[1][by]] & 0x40) != 0) {
                byArray[n++] = 1;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bS[1][by] < by3) break;
            if ((this.bK[this.bT[1][by]][this.bS[1][by] - by3] & 0x40) != 0) {
                byArray[n++] = 2;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 1;
        while (by3 < by2) {
            if (this.bS[1][by] + by3 > 15) break;
            if ((this.bK[this.bT[1][by]][this.bS[1][by] + by3] & 0x40) != 0) {
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
        if (this.cb[1][by]) {
            switch (this.bU[1][by]) {
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
        if (this.bS[1][by] < 15 && (this.bK[this.bT[1][by]][this.bS[1][by] + 1] & 0xE0) != 0) {
            return false;
        }
        if (this.bS[1][by] == 15) {
            byte[] byArray = this.bK[this.bT[1][by]];
            byte by2 = this.bS[1][by];
            byArray[by2] = (byte)(byArray[by2] & 0x32);
            this.bS[1][by] = -1;
            this.bT[1][by] = -1;
            this.cb[1][by] = false;
            return true;
        }
        this.f((byte)1, by);
        return true;
    }

    public final boolean z(byte by) {
        boolean bl = false;
        if (!this.cb[1][by]) {
            return false;
        }
        switch (this.bY[1][by]) {
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

    public final byte V() {
        int n;
        int n2;
        int n3 = 0;
        int n4 = this.bV[this.cr][this.cs] * this.bV[this.cr][this.cs] / this.bW[this.ct][this.cu];
        n3 = this.bV[this.cr][this.cs] >= this.bW[this.ct][this.cu] ? (n4 < (n2 = this.bV[this.cr][this.cs] * 3 / 2) ? n4 : n2) : (n4 > (n = this.bV[this.cr][this.cs] * 2 / 3) ? n4 : n);
        n3 = this.a((short)n3) * 5;
        if ((this.cd & 0xF0) == 64) {
            n3 *= 1;
        } else if ((this.cd & 0xF0) == 80 && this.bU[this.cr][this.cs] == 2) {
            n3 *= 1;
        }
        n3 = n3 * this.bZ[this.cr][this.cs] / 100;
        n3 += this.ap.nextInt() % (n3 / 10 + 1);
        if (n3 > 110) {
            n3 = 110;
        }
        return (byte)n3;
    }

    public final void c(boolean bl) {
        byte by = this.V();
        if (this.bU[this.ct][this.cu] == 0) {
            if (this.bU[this.cr][this.cs] == 0) {
                this.aq = 1;
                return;
            }
            if (bl) {
                short s = this.ax;
                this.L[s] = (byte)(this.L[s] - (by / 4 + 1));
                if (this.L[this.ax] <= 0) {
                    this.cb[this.ct][this.cu] = false;
                    this.L[this.ax] = 0;
                    return;
                }
            } else {
                short s = this.ay;
                this.L[s] = (byte)(this.L[s] - (by / 4 + 1));
                if (this.L[this.ay] <= 0) {
                    this.cb[this.ct][this.cu] = false;
                    this.L[this.ay] = 0;
                    return;
                }
            }
        } else {
            byte[] byArray = this.bZ[this.ct];
            byte by2 = this.cu;
            byArray[by2] = (byte)(byArray[by2] - by);
            if (this.bZ[this.ct][this.cu] <= 0) {
                this.cb[this.ct][this.cu] = false;
                this.bZ[this.ct][this.cu] = 0;
                byte[] byArray2 = this.bK[this.bT[this.ct][this.cu]];
                byte by3 = this.bS[this.ct][this.cu];
                byArray2[by3] = (byte)(byArray2[by3] & 0x32);
                this.bT[this.ct][this.cu] = -1;
                this.bS[this.ct][this.cu] = -1;
            }
        }
    }

    public final void W() {
        byte by = this.bS[0][0];
        byte by2 = this.bT[0][0];
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
                    while (n3 < this.bJ) {
                        if (this.bS[1][n3] == n && this.bT[1][n3] == n2) {
                            if (n3 == 0) {
                                short s = this.ay;
                                this.L[s] = (byte)(this.L[s] - (this.s() % 21 + 30));
                                if (this.L[this.ay] <= 0) {
                                    this.L[this.ay] = 0;
                                    this.cb[1][0] = false;
                                }
                            } else if (this.cb[1][n3]) {
                                byte[] byArray = this.bZ[1];
                                int n4 = n3;
                                byArray[n4] = (byte)(byArray[n4] - (this.s() % 21 + 60));
                                if (this.bZ[1][n3] <= 0) {
                                    byte[] byArray2 = this.bK[this.bT[1][n3]];
                                    byte by3 = this.bS[1][n3];
                                    byArray2[by3] = (byte)(byArray2[by3] & 0x3F);
                                    this.bZ[1][n3] = 0;
                                    this.cb[1][n3] = false;
                                    this.bS[1][n3] = -1;
                                    this.bT[1][n3] = -1;
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

    public final void X() {
        byte by = this.bS[1][0];
        byte by2 = this.bT[1][0];
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
                    while (n3 < this.bI) {
                        if (this.bS[0][n3] == n && this.bT[0][n3] == n2) {
                            if (n3 == 0) {
                                short s = this.ax;
                                this.L[s] = (byte)(this.L[s] - (this.s() % 21 + 30));
                                if (this.L[this.ax] <= 0) {
                                    this.L[this.ax] = 0;
                                    this.cb[0][0] = false;
                                }
                            } else if (this.cb[0][n3]) {
                                byte[] byArray = this.bZ[0];
                                int n4 = n3;
                                byArray[n4] = (byte)(byArray[n4] - (this.s() % 21 + 60));
                                if (this.bZ[0][n3] <= 0) {
                                    byte[] byArray2 = this.bK[this.bT[0][n3]];
                                    byte by3 = this.bS[0][n3];
                                    byArray2[by3] = (byte)(byArray2[by3] & 0x3F);
                                    this.bZ[0][n3] = 0;
                                    this.cb[0][n3] = false;
                                    this.bS[0][n3] = -1;
                                    this.bT[0][n3] = -1;
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

    public final boolean Y() {
        int n = 0;
        if (this.L[this.ay] > 40 || this.P[this.ay] > 99) {
            return false;
        }
        if (this.bT[1][0] > 0 && (this.bK[this.bT[1][0] - 1][this.bS[1][0]] & 0x40) != 0) {
            n = 1;
        }
        if (this.bT[1][0] < 6 && (this.bK[this.bT[1][0] + 1][this.bS[1][0]] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (this.bS[1][0] > 0 && (this.bK[this.bT[1][0]][this.bS[1][0] - 1] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (this.bS[1][0] < 15 && (this.bK[this.bT[1][0]][this.bS[1][0] + 1] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (n == 4) {
            n = 100;
        } else if (n == 3) {
            n = 90;
        } else if (n == 2) {
            n = 70;
        } else if (n == 1) {
            n = 50;
        } else {
            return false;
        }
        if (this.M[this.ay] > 95 || this.P[this.ay] > 95) {
            n = (byte)(n - 30);
        } else if (this.M[this.ay] >= 90 || this.P[this.ay] >= 90) {
            n = (byte)(n - 20);
        } else if (this.M[this.ay] >= 80 || this.P[this.ay] >= 80) {
            n = (byte)(n - 10);
        } else if (n > 0) {
            if (this.P[this.ay] < 15) {
                n = (byte)(n + 40);
            } else if (this.P[this.ay] < 35) {
                n = (byte)(n + 20);
            } else if (this.P[this.ay] < 50) {
                n = (byte)(n + 10);
            }
        }
        return this.s() % 100 < n;
    }

    public final void Z() {
        if (!this.cb[0][0]) {
            if (this.L[this.ax] <= 0) {
                this.cc = (byte)2;
                return;
            }
            this.cc = (byte)6;
            if (this.s() % 10 > 1) {
                this.cf = (byte)(this.s() % 12 + 2);
                if (this.cf >= this.L[this.ax]) {
                    this.cf = this.L[this.ax];
                    this.cc = (byte)2;
                }
                short s = this.ax;
                this.L[s] = (byte)(this.L[s] - this.cf);
            }
            return;
        }
        if (!this.cb[1][0]) {
            if (this.L[this.ay] <= 0) {
                this.cc = 1;
                return;
            }
            this.cc = (byte)5;
            if (this.s() % 10 > 1) {
                this.cf = (byte)(this.s() % 12 + 2);
                if (this.cf >= this.L[this.ay]) {
                    this.cf = this.L[this.ay];
                    this.cc = 1;
                }
                short s = this.ay;
                this.L[s] = (byte)(this.L[s] - this.cf);
            }
        }
    }

    public final void aa() {
        short s = this.J[this.ax];
        short s2 = this.J[this.ay];
        this.J[this.ax] = 0;
        byte by = 1;
        while (by < this.bI) {
            short s3 = this.ax;
            this.J[s3] = (short)(this.J[s3] + this.bZ[0][by]);
            by = (byte)(by + 1);
        }
        this.J[this.ay] = 0;
        by = 1;
        while (by < this.bJ) {
            short s4 = this.ay;
            this.J[s4] = (short)(this.J[s4] + this.bZ[1][by]);
            by = (byte)(by + 1);
        }
        short s5 = this.ax;
        this.Q[s5] = (short)(this.Q[s5] + (s2 - this.J[this.ay]));
        short s6 = this.ay;
        this.Q[s6] = (short)(this.Q[s6] + (s - this.J[this.ax]));
        this.f(this.ax);
        this.f(this.ay);
    }

    public final void ab() {
        this.aa();
        if (this.cl) {
            this.aq = (byte)2;
            return;
        }
        this.aq = (byte)3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void ac() {
        if (this.aq == 0) {
            this.ao.aa = 0;
            this.cF = 0;
            this.cG = 0;
            this.cI = 0;
            this.cJ = 0;
            this.cK = 0;
            this.cD = this.bI > 1 ? (byte)1 : 0;
            this.cE = this.bJ > 1 ? (byte)1 : 0;
            this.cL = 0;
            this.h(this.cG);
        } else {
            this.aq = 0;
        }
        long l = System.currentTimeMillis();
        block5: while (this.aq <= 0) {
            long l2;
            if (this.ao.a != 0) {
                this.ao.h();
                this.ao.a = 0;
            }
            if ((l2 = System.currentTimeMillis() - l) < 50L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(50L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
            if (this.ci == 2 || this.cc > 0) continue;
            if (this.cd != 0) {
                if (this.cJ == 0) {
                    this.cJ = this.cG;
                    this.cK = this.bI;
                } else if (this.cG > this.cJ && this.bI >= this.cK) {
                    this.cJ = this.cG;
                    this.cd = (byte)(this.cd - 1);
                    if ((this.cd & 0xF) == 0) {
                        this.cd = 0;
                        this.cJ = 0;
                    }
                }
            }
            this.cL = (byte)(this.cL + 1);
            if (this.ao.aj) continue;
            if (this.cq > 0) {
                this.ca[this.ct][this.cu] = true;
                this.cq = (byte)(this.cq - 1);
                if (this.cq != 0) continue;
                this.c(this.cv);
                this.cL = 0;
                this.Z();
                continue;
            }
            if (this.ao.ak || this.ao.al) {
                if (this.co > 0 && (this.co = (byte)(this.co - 1)) == 0) {
                    this.W();
                    this.ao.ak = false;
                    this.cL = 0;
                }
                if (this.cp <= 0 || (this.cp = (byte)(this.cp - 1)) != 0) continue;
                this.X();
                this.ao.al = false;
                continue;
            }
            if (this.cL < 10) continue;
            this.Z();
            if (this.cc > 0) continue;
            this.cL = 0;
            if (this.cI > 1) {
                ++this.cG;
                this.cD = this.bI > 1 ? (byte)1 : 0;
                this.cE = this.bJ > 1 ? (byte)1 : 0;
                this.cI = 0;
            }
            if (this.cI == 0) {
                this.cH = false;
                do {
                    this.cH = this.s(this.cD);
                    if (this.cD == 0 && this.cF >= 1) {
                        this.cF = 0;
                        this.cI = 1;
                        if (this.Y()) {
                            this.cc = (byte)3;
                            continue;
                        }
                        this.h(this.cG);
                        if (this.ce == 0) continue block5;
                        if ((this.ce & 0xF) == 0) {
                            this.ce = 0;
                            continue block5;
                        }
                        this.ce = (byte)(this.ce - 1);
                        continue block5;
                    }
                    if (!this.cb[0][this.cD] || this.bU[0][this.cD] >= 2 || this.cF >= 1) {
                        this.cD = (byte)(this.cD + 1);
                        this.cF = 0;
                    } else {
                        this.cF = (byte)(this.cF + 1);
                    }
                    if (this.cD != this.bI) continue;
                    this.cD = 0;
                } while (!this.cH);
                continue;
            }
            if (this.cI != 1) continue;
            this.cH = false;
            do {
                this.cH = this.cE == 0 && (this.cd & 0xF0) == 16 ? this.x((byte)0) : this.z(this.cE);
                if (this.cE == 0 && this.cF >= 1) {
                    this.cF = 0;
                    this.cI = (byte)2;
                    continue block5;
                }
                if (!this.cb[1][this.cE] || this.bU[1][this.cE] >= 2 || this.cF >= 1) {
                    this.cE = (byte)(this.cE + 1);
                    this.cF = 0;
                } else {
                    this.cF = (byte)(this.cF + 1);
                }
                if (this.cE != this.bJ) continue;
                this.cE = 0;
            } while (!this.cH);
        }
        return;
    }

    public final boolean b(short s, short s2) {
        int n = (this.M[s] - this.M[s2]) / 20;
        return this.ap.nextInt() % 10 < 5 + n;
    }

    public final byte c(short s, short s2) {
        if (this.L[s] < 20 && this.s() % 2 == 0) {
            return 4;
        }
        if (this.L[s] < this.K[s] / 2 && this.L[s2] - this.L[s] > 10 && this.s() % 10 < 3) {
            return 2;
        }
        if (this.L[s] < this.L[s2]) {
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
        if (this.Z == 1) {
            return this.c(s, s2);
        }
        if (this.Z == 2) {
            return this.l(s, s2);
        }
        return 0;
    }

    public final void ad() {
        int n = this.R[this.ax] / 8;
        int n2 = this.R[this.ay] / 8;
        if ((n -= n2) == 0) {
            this.cC = 0;
            return;
        }
        if (n == -1 || n == 1) {
            this.cC = (byte)-1;
            return;
        }
        this.cC = 1;
    }

    public final void ae() {
        this.cx = (short)(this.M[this.ax] + this.M[this.ax] * this.af[this.R[this.ax]] / 100);
        this.cy = (short)(this.M[this.ax] + this.M[this.ax] * this.af[this.S[this.ax]] / 100);
        this.cz = (short)(this.M[this.ay] + this.M[this.ay] * this.af[this.R[this.ay]] / 100);
        this.cA = (short)(this.M[this.ay] + this.M[this.ay] * this.af[this.S[this.ay]] / 100);
        this.ad();
        this.cB = false;
        if (this.ay == 61) {
            if (this.H[this.ax] <= 3 && this.L[this.ay] == this.K[this.ay]) {
                this.cB = true;
            }
            return;
        }
        if (this.ay == 4 || this.ay == 24 || this.ay == 30 || this.ay == 40 || this.ay == 69) {
            if (this.L[this.ay] == this.K[this.ay]) {
                this.cB = true;
            }
            return;
        }
        if (this.ay == 1) {
            this.cB = true;
        }
    }

    public final void d(boolean bl) {
        if (bl) {
            byte by = 0;
            while (by < this.bJ) {
                this.bY[1][by] = 2;
                by = (byte)(by + 1);
            }
            return;
        }
        this.bY[0][0] = 2;
        int n = 0;
        while (n < 4) {
            this.cj[n] = 2;
            n = (byte)(n + 1);
        }
    }

    public final int af() {
        if (this.cB) {
            if (this.ay == 61) {
                this.R[this.ax] = 6;
                return 0;
            }
            if (this.ay == 4) {
                this.S[this.ax] = 31;
                return 1;
            }
            if (this.ay == 1) {
                if (this.L[this.ax] == this.K[this.ax]) {
                    this.R[this.ax] = 7;
                    return 2;
                }
            } else if (this.ay == 24) {
                if (this.L[this.ax] * 5 <= this.K[this.ax]) {
                    this.R[this.ax] = 5;
                    return 3;
                }
            } else if (this.bP == 1) {
                this.R[this.ax] = 14;
                return 4;
            }
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void ag() {
        this.ao.a((byte)22);
        long l = System.currentTimeMillis();
        while (this.aq <= 0) {
            long l2;
            if (this.ao.a != 0) {
                this.ao.g();
                this.ao.a = 0;
            }
            if ((l2 = System.currentTimeMillis() - l) < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
        return;
    }

    public final void ah() {
        this.aq = 0;
        this.S();
        this.T();
        this.aq = 0;
        while (true) {
            this.cw = false;
            this.ac();
            if (this.aq == 1) {
                this.aq = 0;
                this.ae();
                this.ag();
                this.ao.a((byte)21);
                this.ao.af = (byte)2;
                continue;
            }
            if (this.aq == 2) break;
        }
        this.ab();
    }

    public final boolean a(short s, boolean bl) {
        if (bl) {
            byte by;
            if (s == this.bg[0]) {
                this.aq = (byte)2;
                this.bE = true;
                this.bu[0] = 3;
                byte[] byArray = this.bm[this.br[0]];
                byte by2 = this.bq[0];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                if (this.bg[0] == this.aZ) {
                    this.bG = (byte)2;
                }
                by = 0;
                while (by < this.bj) {
                    if (this.bv[by] == 2) {
                        this.bv[by] = 0;
                    }
                    by = (byte)(by + 1);
                }
                this.bC = 1;
                by = 1;
                while (by < this.bi) {
                    if (this.bu[by] == 0 || this.bu[by] > 3) break;
                    by = (byte)(by + 1);
                }
                if (by < this.bi) {
                    this.j(this.bb);
                    this.bC = by;
                    this.ax = this.bg[this.bC];
                    this.ao.c();
                    this.ao.Q = true;
                    this.ao.J = false;
                    this.ao.K = true;
                } else {
                    if (this.bp) {
                        this.C();
                    }
                    this.D();
                    return false;
                }
            }
            by = 1;
            while (by < this.bi) {
                if (this.bg[by] == s) {
                    this.bu[by] = 3;
                    byte[] byArray = this.bm[this.br[by]];
                    byte by3 = this.bq[by];
                    byArray[by3] = (byte)(byArray[by3] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        } else {
            if (s == this.bh[0]) {
                this.aq = (byte)2;
                this.bv[0] = 3;
                byte[] byArray = this.bm[this.bt[0]];
                byte by = this.bs[0];
                byArray[by] = (byte)(byArray[by] & 0x3F);
                byte by4 = 1;
                while (by4 < this.bi) {
                    if (this.bu[by4] == 2) {
                        this.bu[by4] = 0;
                    }
                    by4 = (byte)(by4 + 1);
                }
                this.ao.W = (byte)5;
                return true;
            }
            byte by = 1;
            while (by < this.bj) {
                if (this.bh[by] == s) {
                    this.bv[by] = 3;
                    byte[] byArray = this.bm[this.bt[by]];
                    byte by5 = this.bs[by];
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
            if (s == this.bg[0]) {
                this.aq = (byte)2;
                this.bE = true;
                this.bu[0] = 2;
                byte[] byArray = this.bm[this.br[0]];
                byte by2 = this.bq[0];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                by = 0;
                while (by < this.bj) {
                    if (this.bv[by] == 2) {
                        this.bv[by] = 0;
                    }
                    by = (byte)(by + 1);
                }
                this.bC = 1;
                by = 1;
                while (by < this.bi) {
                    if (this.bu[by] == 0 || this.bu[by] > 3) break;
                    by = (byte)(by + 1);
                }
                if (by < this.bi) {
                    this.j(this.bb);
                    this.bC = by;
                    this.ax = this.bg[this.bC];
                    this.ao.c();
                    this.ao.Q = true;
                    this.ao.J = false;
                    this.ao.K = true;
                } else {
                    if (this.bp) {
                        this.C();
                    }
                    this.D();
                    return false;
                }
            }
            by = 1;
            while (by < this.bi) {
                if (this.bg[by] == s) {
                    this.bu[by] = 2;
                    byte[] byArray = this.bm[this.br[by]];
                    byte by3 = this.bq[by];
                    byArray[by3] = (byte)(byArray[by3] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        } else {
            if (s == this.bh[0]) {
                this.aq = (byte)2;
                this.bv[0] = 2;
                byte[] byArray = this.bm[this.bt[0]];
                byte by = this.bs[0];
                byArray[by] = (byte)(byArray[by] & 0x3F);
                byte by4 = 1;
                while (by4 < this.bi) {
                    if (this.bu[by4] == 2) {
                        this.bu[by4] = 0;
                    }
                    by4 = (byte)(by4 + 1);
                }
                this.ao.W = (byte)5;
                return true;
            }
            byte by = 1;
            while (by < this.bj) {
                if (this.bh[by] == s) {
                    this.bv[by] = 2;
                    byte[] byArray = this.bm[this.bt[by]];
                    byte by5 = this.bs[by];
                    byArray[by5] = (byte)(byArray[by5] & 0x3F);
                    return true;
                }
                by = (byte)(by + 1);
            }
        }
        return true;
    }

    public final boolean ai() {
        switch (this.cc) {
            case 1: {
                return this.a(this.ay, false);
            }
            case 2: {
                return this.a(this.ax, true);
            }
            case 3: {
                return this.b(this.ay, false);
            }
            case 4: {
                return this.b(this.ax, true);
            }
        }
        return true;
    }

    public final void aj() {
        this.ao.aA = true;
        this.ao.a((byte)17);
        this.aq = 0;
        this.ap();
    }

    public final void ak() {
        this.ao.aA = true;
        this.ao.a((byte)16);
        this.aq = 0;
        this.ap();
    }

    public final void A(byte by) {
        this.ao.a((byte)20);
        this.ao.W = by;
        this.aq = 0;
        this.E();
    }

    public final void al() {
        this.ax = 0;
        this.z();
        this.E();
        this.aq = 0;
        while (true) {
            this.R();
            if (this.aq == 2 || this.aq == 3) {
                byte by = this.aq;
                this.ah();
                this.ao.a((byte)20);
                this.ao.c();
                this.aq = by;
                if (this.ai()) continue;
                this.aq = (byte)4;
                continue;
            }
            if (this.aq == 5) {
                this.aq = 0;
                this.aj();
                switch (this.aq) {
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
                this.aq = (byte)5;
                this.ao.c();
                continue;
            }
            if (this.aq == 6) {
                this.ak();
                switch (this.aq) {
                    case 1: {
                        this.A((byte)14);
                        break;
                    }
                    case 2: {
                        this.A((byte)10);
                    }
                }
                this.aq = (byte)6;
                this.ao.c();
                continue;
            }
            if (this.aq > 0) break;
        }
    }

    public final void am() {
        this.aq = 0;
        this.ba = this.b[this.as];
        this.bb = this.av;
        this.aZ = this.i[this.bb];
        this.bj = this.aA;
        this.bi = this.r[this.bb];
        byte by = 0;
        while (by < this.bj) {
            this.bh[by] = this.az[by];
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < this.bi) {
            this.bg[by] = this.A[this.bb][by];
            by = (byte)(by + 1);
        }
        this.bc = this.l[this.bb];
        this.bd = this.m[this.bb];
        this.bp = true;
        by = 0;
        while (by < this.bi) {
            this.bu[by] = 0;
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < this.bj) {
            this.bv[by] = 0;
            by = (byte)(by + 1);
        }
        this.bw = 1;
        this.bx = 1;
        this.by = 1;
        this.bz = 1;
        this.bA = 0;
        this.bk = 0;
        this.bl = 0;
        this.bB = true;
        this.bC = (byte)-1;
        this.bk = this.a(this.bg, this.bi, this.bu, this.bk);
        this.y();
        this.ao.b();
        this.ao.a();
        this.ao.j();
    }

    public final void an() {
        this.am();
        this.ax = 0;
        this.E();
        this.aq = 0;
        while (true) {
            this.R();
            if (this.aq == 2 || this.aq == 3) {
                byte by = this.aq;
                this.ah();
                this.ao.a((byte)20);
                this.ao.c();
                this.aq = by;
                if (this.ai()) continue;
                this.aq = (byte)4;
                continue;
            }
            if (this.aq == 5) {
                this.aq = 0;
                this.aj();
                switch (this.aq) {
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
                this.aq = (byte)5;
                this.ao.c();
                continue;
            }
            if (this.aq == 6) {
                this.ak();
                switch (this.aq) {
                    case 1: {
                        this.A((byte)14);
                        break;
                    }
                    case 2: {
                        this.A((byte)10);
                    }
                }
                this.aq = (byte)6;
                this.ao.c();
                continue;
            }
            if (this.aq > 0) break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void ao() {
        if (this.aq == 0) {
            this.aG = this.e(this.ar);
            this.bP = 0;
            this.bQ = 0;
            this.u();
            this.ao.m();
            this.ao.repaint();
            this.ao.f();
        } else if (this.aq == 2) {
            this.aK();
            this.aq = 0;
        } else if (this.aq == 4) {
            this.aq = 0;
            this.aL();
        }
        long l = System.currentTimeMillis();
        while (true) {
            if (this.ao.a != 0) {
                this.ao.f();
                if (!this.ao.aC) {
                    this.ao.a = 0;
                }
            }
            if (this.aq > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void a(byte by, byte by2, short s) {
        int n = 0;
        while (n < this.c[by]) {
            this.i[this.d[by][n]] = s;
            ++n;
        }
        this.c(s, by2);
        this.P[s] = 100;
        this.b[by] = s;
    }

    public final void B(byte by) {
        byte by2 = -1;
        short s = 0;
        byte by3 = 0;
        int n = 0;
        while (n < this.e[by]) {
            int n2 = 0;
            while (n2 < this.c[by]) {
                byte by4 = this.d[by][n2];
                int n3 = 0;
                while (n3 < this.r[by4]) {
                    short s2 = this.A[by4][n3];
                    if (this.P[s2] > by2) {
                        by2 = this.P[s2];
                        s = s2;
                        by3 = by4;
                    }
                    if (s2 == this.f[by][n]) {
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
        if (this.Z == 1) {
            this.B(by);
            return;
        }
        if (this.Z == 2) {
            this.aq(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void ap() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.ao.a != 0) {
                this.ao.f();
                if (!this.ao.aC) {
                    this.ao.a = 0;
                }
            }
            if (this.aq > 0) {
                return;
            }
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void aq() {
        this.ao.a((byte)4);
        this.ao.m();
        this.ap();
        this.aw = this.au;
    }

    public final void ar() {
        this.ao.a((byte)7);
        this.ap();
    }

    public final void as() {
        this.bP = (byte)22;
        this.au = this.d[this.ar][0];
        this.aL = true;
        while (true) {
            this.aq();
            this.aq = 0;
            this.ar();
            if (this.aq > 1) break;
            this.aq = 0;
        }
        this.a(this.ar, this.au, this.ax);
        this.aq = 0;
        this.ao.a((byte)4);
    }

    public final void a(byte by, short s, short s2) {
        this.ao.a(by, s, s2);
        this.ap();
        this.aq = 0;
    }

    public final void at() {
        this.ao.a((byte)4);
        this.aq = 0;
        if (this.bG == 1) {
            short s = this.b[this.at];
            this.C(this.at);
            this.a((byte)6, s, this.b[this.at]);
            this.bG = 0;
        } else if (this.bG == 2) {
            short s = this.b[this.ar];
            this.a((byte)2, s, (short)0);
            this.as();
            this.a((byte)6, s, this.b[this.ar]);
            this.bG = 0;
        } else {
            if (this.bG == 3) {
                this.aG = 1;
                this.bG = 0;
                this.a((byte)5, this.b[this.ar], (short)0);
                this.aq = (byte)99;
                return;
            }
            if (this.bG == 4) {
                this.a((byte)5, this.b[this.at], (short)0);
                this.bG = 0;
            }
        }
        this.aq = (byte)4;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void au() {
        byte by = this.aE;
        while (by < 8) {
            if (this.aq == 0) {
                this.t();
            }
            if (this.as <= 0) {
                this.as = this.cP;
            } else {
                this.cP = this.as;
                if (this.as == this.ar) {
                    this.ao();
                    if (this.cO) {
                        by = this.aE;
                        this.cO = false;
                    }
                    if (this.aq == 3) {
                        this.aq = 0;
                    }
                    if (this.aq == 4) {
                        this.al();
                        by = (byte)(by - 1);
                        this.at();
                        if (this.aq == 99) {
                            this.aq = 0;
                            return;
                        }
                    }
                } else {
                    long l;
                    this.ao.repaint();
                    this.ao.serviceRepaints();
                    long l2 = System.currentTimeMillis();
                    this.aq = 0;
                    this.bw();
                    if (this.aq == 3) {
                        this.an();
                        by = (byte)(by - 1);
                        this.at();
                        if (this.aq == 99) {
                            this.aq = 0;
                            return;
                        }
                    }
                    if ((l = System.currentTimeMillis() - l2) < 2000L) {
                        try {
                            b b2 = this;
                            synchronized (b2) {
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

    public final boolean av() {
        if (this.aC < 11 && this.aC > 2) {
            int n = this.s() % 500;
            return n < 4;
        }
        return false;
    }

    public final boolean aw() {
        return this.s() % 1000 <= 1;
    }

    public final int a(int n, byte by, int n2) {
        return by * (n /= 2) / n2;
    }

    public final void D(byte by) {
        if (this.q[by] < 90) {
            byte by2 = by;
            this.l[by2] = (short)(this.l[by2] - this.a((int)this.l[by], this.q[by], 90));
            byte by3 = by;
            this.m[by3] = (short)(this.m[by3] - this.a((int)this.m[by], this.q[by], 90));
            byte by4 = by;
            this.k[by4] = (byte)(this.k[by4] - this.a((int)this.k[by], this.q[by], 90));
        }
        if (this.q[by] < 99) {
            byte by5 = by;
            this.o[by5] = (short)(this.o[by5] - this.a((int)this.o[by], this.q[by], 99));
            byte by6 = by;
            this.n[by6] = (short)(this.n[by6] - this.a((int)this.n[by], this.q[by], 99));
        }
        if (this.q[by] > 0) {
            byte by7 = by;
            this.q[by7] = (byte)(this.q[by7] - (this.q[by] / 10 + 1));
        }
    }

    public final void ax() {
        this.bv();
        this.N((byte)4);
    }

    public final void E(byte by) {
        if (this.q[by] < 90) {
            this.u[by] = 0;
            byte by2 = 0;
            while (by2 < this.r[by]) {
                short s = this.A[by][by2];
                this.J[s] = (short)(this.J[s] - this.a((int)this.J[this.A[by][by2]], this.q[by], 90));
                byte by3 = by;
                this.u[by3] = (short)(this.u[by3] + this.J[this.A[by][by2]]);
                by2 = (byte)(by2 + 1);
            }
            byte by4 = by;
            this.v[by4] = (short)(this.v[by4] - this.a((int)this.v[by], this.q[by], 90));
            byte by5 = by;
            this.k[by5] = (byte)(this.k[by5] - this.a((int)this.k[by], this.q[by], 90));
        }
        if (this.q[by] < 99) {
            byte by6 = by;
            this.p[by6] = this.p[by6] - this.a(this.p[by], this.q[by], 99);
        }
        if (this.q[by] > 0) {
            byte by7 = by;
            this.q[by7] = (byte)(this.q[by7] - (this.q[by] / 10 + 1));
        }
    }

    public final boolean F(byte by) {
        if (this.k[by] < 15) {
            int n = this.k[by] * 10 / 15 + 80;
            if (this.s() % 100 >= n) {
                return true;
            }
        } else if (this.k[by] < 30) {
            int n = (this.k[by] - 15) / 3 + 90;
            if (this.s() % 100 >= n) {
                return true;
            }
        } else if (this.k[by] < 40) {
            int n = (this.k[by] - 30) / 5 + 98;
            if (this.s() % 100 >= n) {
                return true;
            }
        } else if (this.k[by] < 60) {
            int n = (this.k[by] - 40) / 5 + 998;
            if (this.s() % 1000 >= n) {
                return true;
            }
        }
        return false;
    }

    public final void G(byte by) {
        byte by2 = by;
        this.k[by2] = (byte)(this.k[by2] - this.s() % 15);
        if (this.k[by] < 0) {
            this.k[by] = 0;
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
        if (this.k[by] < 15) {
            byte by2 = by;
            this.p[by2] = this.p[by2] / 3;
            byte by3 = by;
            this.n[by3] = (short)(this.n[by3] / 3);
            byte by4 = by;
            this.o[by4] = (short)(this.o[by4] / 3);
            byte by5 = by;
            this.l[by5] = (short)(this.l[by5] / 2);
            byte by6 = by;
            this.m[by6] = (short)(this.m[by6] / 2);
            this.G(by);
            this.v[by] = this.a(this.v[by], 2, 3);
            byte by7 = 0;
            while (by7 < this.r[by]) {
                this.J[this.A[by][by7]] = this.a(this.J[this.A[by][by7]], 2, 3);
                n += this.J[this.A[by][by7]];
                by7 = (byte)(by7 + 1);
            }
            this.u[by] = (short)n;
            return;
        }
        if (this.k[by] < 30) {
            byte by8 = by;
            this.p[by8] = this.p[by8] / 2;
            byte by9 = by;
            this.n[by9] = (short)(this.n[by9] / 2);
            byte by10 = by;
            this.o[by10] = (short)(this.o[by10] / 2);
            this.l[by] = (short)(this.l[by] * 3 / 7);
            this.m[by] = (short)(this.m[by] * 3 / 7);
            this.G(by);
            this.v[by] = this.a(this.v[by], 1, 2);
            byte by11 = 0;
            while (by11 < this.r[by]) {
                this.J[this.A[by][by11]] = this.a(this.J[this.A[by][by11]], 1, 2);
                n += this.J[this.A[by][by11]];
                by11 = (byte)(by11 + 1);
            }
            this.u[by] = (short)n;
            return;
        }
        this.p[by] = this.p[by] * 2 / 3;
        this.n[by] = (short)(this.n[by] * 2 / 3);
        this.o[by] = (short)(this.o[by] * 2 / 3);
        this.l[by] = (short)(this.l[by] * 1 / 7);
        this.m[by] = (short)(this.m[by] * 1 / 7);
        this.G(by);
        this.v[by] = this.a(this.v[by], 1, 5);
        byte by12 = 0;
        while (by12 < this.r[by]) {
            this.J[this.A[by][by12]] = this.a(this.J[this.A[by][by12]], 1, 5);
            n += this.J[this.A[by][by12]];
            by12 = (byte)(by12 + 1);
        }
        this.u[by] = (short)n;
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
        int n = this.l[by] + this.b((int)this.o[by], (int)this.k[by], this.p[by]);
        if (n > 9999) {
            this.l[by] = 9999;
            return;
        }
        this.l[by] = (short)n;
    }

    public final void J(byte by) {
        int n = this.m[by] + this.b((int)this.n[by], (int)this.k[by], this.p[by]);
        if (n > 9999) {
            this.m[by] = 9999;
            return;
        }
        this.m[by] = (short)n;
    }

    public final void ay() {
        short s;
        int n;
        if (this.aD < 210) {
            return;
        }
        if (this.s() % 100 < 2 && this.b[this.ar] == this.i[14]) {
            n = 0;
            while (n < this.r[14]) {
                s = this.A[14][n];
                if (s == 55) {
                    this.N[s] = 99;
                    this.O[s] = 99;
                    this.M[s] = 99;
                    this.K[s] = 99;
                }
                ++n;
            }
        }
        if (this.s() % 100 < 3 && this.b[this.ar] == this.i[2]) {
            n = 0;
            while (n < this.r[2]) {
                s = this.A[2][n];
                if (s == 193) {
                    this.N[s] = 99;
                    this.O[s] = 99;
                    this.M[s] = 99;
                    this.K[s] = 99;
                }
                ++n;
            }
        }
    }

    public final boolean az() {
        int n = 1;
        while (n < 37) {
            if (this.i[n] != this.b[this.ar]) {
                return false;
            }
            n = (byte)(n + 1);
        }
        return true;
    }

    public final boolean aA() {
        return this.c[this.ar] == 0;
    }

    public final void K(byte by) {
        int n = this.s() % 4;
        int n2 = this.s() % 5 + 1;
        switch (n) {
            case 0: {
                byte by2 = by;
                this.n[by2] = (short)(this.n[by2] + n2);
                if (this.n[by] <= 999) break;
                this.n[by] = 999;
                break;
            }
            case 1: {
                byte by3 = by;
                this.o[by3] = (short)(this.o[by3] + n2);
                if (this.o[by] <= 999) break;
                this.o[by] = 999;
                break;
            }
            case 2: {
                byte by4 = by;
                this.p[by4] = this.p[by4] + n2 * 500;
                if (this.p[by] <= 990000) break;
                this.p[by] = 990000;
                break;
            }
            case 3: {
                byte by5 = by;
                this.q[by5] = (byte)(this.q[by5] + (n2 / 2 + 1));
                if (this.q[by] <= 99) break;
                this.q[by] = 99;
            }
        }
        n2 = this.s();
        if (this.k[by] < 30) {
            byte by6 = by;
            this.k[by6] = (byte)(this.k[by6] + (n2 % 4 + 2));
            return;
        }
        if (this.k[by] < 40) {
            byte by7 = by;
            this.k[by7] = (byte)(this.k[by7] + (n2 % 2 + 1));
            return;
        }
        if (this.k[by] < 60) {
            byte by8 = by;
            this.k[by8] = (byte)(this.k[by8] + n2 % 2);
        }
    }

    public final void aB() {
        int n = 1;
        while (n < 8) {
            if (this.a[n] > 0 && this.a[n] != this.ar) {
                int n2 = 0;
                while (n2 < this.c[n]) {
                    this.K(this.d[n][n2]);
                    ++n2;
                }
            }
            ++n;
        }
    }

    public final void aC() {
        this.aS = 0;
        if (this.az()) {
            this.aO = 0;
            this.N((byte)23);
            this.ap();
            this.aq = 1;
            return;
        }
        if (this.aA()) {
            this.aO = 1;
            this.N((byte)23);
            this.ap();
            this.aq = 1;
            return;
        }
        this.aC = (byte)(this.aC + 1);
        short s = 1;
        while (s < 37) {
            if (this.av()) {
                byte by = this.aS;
                this.aS = (byte)(by + 1);
                this.aR[by] = (byte)s;
            }
            s = (short)(s + 1);
        }
        if (this.aS < 1) {
            s = 1;
            while (s < 37) {
                if (this.aw()) {
                    byte by = this.aS;
                    this.aS = (byte)(by + 1);
                    this.aR[by] = (byte)s;
                }
                s = (short)(s + 1);
            }
        } else {
            s = 0;
            while (s < this.aS) {
                this.D(this.aR[s]);
                s = (short)(s + 1);
            }
            this.aO = this.s() % 3 + 6;
            this.N((byte)19);
            this.ax();
            this.aS = 0;
        }
        if (this.aS > 0) {
            s = 0;
            while (s < this.aS) {
                this.E(this.aR[s]);
                s = (short)(s + 1);
            }
            this.aO = 9;
            this.N((byte)19);
            this.ax();
            this.aS = 0;
        }
        s = 1;
        while (s < 37) {
            if (this.i[s] > 0 && this.F((byte)s)) {
                byte by = this.aS;
                this.aS = (byte)(by + 1);
                this.aR[by] = (byte)s;
            }
            s = (short)(s + 1);
        }
        if (this.aS > 0) {
            s = 0;
            while (s < this.aS) {
                this.H(this.aR[s]);
                s = (short)(s + 1);
            }
            this.aO = 13;
            this.N((byte)19);
            this.ax();
            this.aS = 0;
        }
        if (this.Z >= 2) {
            this.aB();
        }
        if (this.aC == 4) {
            s = 1;
            while (s < 37) {
                this.I((byte)s);
                s = (short)(s + 1);
            }
            this.aO = 10;
            this.N((byte)19);
            this.ax();
        } else if (this.aC == 10) {
            s = 1;
            while (s < 37) {
                this.J((byte)s);
                s = (short)(s + 1);
            }
            this.aO = 11;
            this.N((byte)19);
            this.ax();
        }
        s = 1;
        while (s < 232) {
            if (this.L[s] < this.K[s]) {
                short s2 = s;
                this.L[s2] = (byte)(this.L[s2] + this.K[s] / 10);
                if (this.L[s] > this.K[s]) {
                    this.L[s] = this.K[s];
                }
            }
            s = (short)(s + 1);
        }
        s = (short)(this.W - 1);
        while (s >= 0) {
            short s3 = s;
            this.Y[s3] = (byte)(this.Y[s3] - 1);
            if (this.Y[s] <= 0) {
                byte by = 1;
                while (by <= 7) {
                    if (by == this.X[s]) {
                        this.aO = 14;
                        this.at = by;
                        this.N((byte)19);
                        this.ax();
                        break;
                    }
                    by = (byte)(by + 1);
                }
                this.X[s] = this.X[this.W];
                this.Y[s] = this.Y[this.W];
                this.W = (byte)(this.W - 1);
            }
            s = (short)(s - 1);
        }
        this.ay();
        s = 0;
        while (s < 8) {
            this.aB[s] = 1;
            s = (short)(s + 1);
        }
    }

    public final void aD() {
        while (this.aC <= 12) {
            this.au();
            if (this.aq != 0) {
                return;
            }
            this.aE = 0;
            this.aC();
            if (this.aq == 0) continue;
            return;
        }
    }

    public final void k(short s) {
        this.J[s] = 0;
        if (this.U[s] <= 0) {
            return;
        }
        byte by = this.U[s];
        this.B[by] = this.b(this.B[by], this.s[by], s);
        byte by2 = by;
        this.s[by2] = (byte)(this.s[by2] + 1);
    }

    public final void l(short s) {
        short s2 = 1;
        while (s2 < 232) {
            if (this.T[s2] == s) {
                this.k(s2);
            }
            s2 = (short)(s2 + 1);
        }
    }

    public final void aE() {
        short s = 0;
        this.aC = 1;
        this.aD = (short)(this.aD + 1);
        this.l(this.aD);
        int n = 0;
        while (n < 26) {
            int n2 = 0;
            if (this.aa[n] == this.aD) {
                short s2 = this.ab[n];
                int n3 = 1;
                while (n3 < 37) {
                    byte by = 0;
                    while (by < this.r[n3]) {
                        if (this.A[n3][by] == this.ac[n]) {
                            s = this.ac[n];
                            n2 = n3;
                            break;
                        }
                        by = (byte)(by + 1);
                    }
                    if (n2 > 0) break;
                    n3 = (byte)(n3 + 1);
                }
                if (n2 != 0) {
                    if (this.r[n2] < 10) {
                        int n4 = n2;
                        byte by = this.r[n4];
                        this.r[n4] = (byte)(by + 1);
                        this.A[n2][by] = s2;
                        if (this.i[n2] == this.b[this.ar]) {
                            this.aO = 12;
                            this.ax = s2;
                            this.ay = s;
                            this.N((byte)19);
                            this.ax();
                        }
                    } else {
                        this.B[n2] = this.b(this.B[n2], this.s[n2], s2);
                        int n5 = n2;
                        this.s[n5] = (byte)(this.s[n5] + 1);
                    }
                }
            }
            n = (byte)(n + 1);
        }
    }

    public final void aF() {
        while (true) {
            this.aD();
            if (this.aq != 0) {
                return;
            }
            this.aE();
        }
    }

    public final void aG() {
        this.aF();
    }

    public final void run() {
        try {
            int n = 0;
            while (n < 8) {
                this.aB[n] = 1;
                n = (byte)(n + 1);
            }
            this.q();
            this.p();
            if (this.aq == 1) {
                this.aq = 0;
            }
            this.aG();
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            c.a(exception);
            return;
        }
    }

    public final void aH() {
        this.a[8] = 0;
        this.b[8] = 0;
        this.c[8] = 0;
        this.d[8] = new byte[37];
        String string = this.c("/lib114.jg");
        this.a(string, 3);
        string = this.c("/lib115.jg");
        this.a(string, 1);
        string = this.c("/lib116.jg");
        System.gc();
        this.a(string, 2);
        int n = 229;
        while (n < 232) {
            this.F[n] = 0;
            this.T[n] = 189;
            this.U[n] = 1;
            this.V[n] = false;
            n = (short)(n + 1);
        }
        this.aI();
        this.G(1);
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
                this.h[s] = d.a[s];
                this.g[s] = (byte)s;
                this.s[s] = 0;
                this.t[s] = 0;
                this.r[s] = 0;
                this.A[s] = new short[10];
                this.x[s] = 0;
                return;
            }
            case 1: {
                this.i[s] = Short.parseShort(string);
                if (this.i[s] <= 0) break;
                int n = 1;
                while (n < 8) {
                    if (this.i[s] == this.b[n]) {
                        int n2 = n;
                        byte by2 = this.c[n2];
                        this.c[n2] = (byte)(by2 + 1);
                        this.d[n][by2] = this.g[s];
                    }
                    n = (byte)(n + 1);
                }
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                this.j[s] = Short.parseShort(string);
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                this.k[s] = Byte.parseByte(string);
                return;
            }
            case 6: {
                this.l[s] = Short.parseShort(string);
                return;
            }
            case 7: {
                this.m[s] = Short.parseShort(string);
                return;
            }
            case 8: {
                this.n[s] = Short.parseShort(string);
                return;
            }
            case 9: {
                this.o[s] = Short.parseShort(string);
                return;
            }
            case 10: {
                this.p[s] = Integer.parseInt(string);
                return;
            }
            case 11: {
                this.q[s] = Byte.parseByte(string);
                return;
            }
            case 12: {
                this.r[s] = 0;
                return;
            }
            case 13: {
                this.u[s] = Short.parseShort(string);
                return;
            }
            case 14: {
                this.v[s] = Short.parseShort(string);
                return;
            }
            case 15: {
                this.w[s] = Byte.parseByte(string);
                return;
            }
            case 16: {
                if (Byte.parseByte(string) != 1) break;
                short s2 = s;
                this.x[s2] = (byte)(this.x[s2] | 1);
                return;
            }
            case 17: {
                if (Byte.parseByte(string) != 1) break;
                short s3 = s;
                this.x[s3] = (byte)(this.x[s3] | 2);
                return;
            }
            case 18: {
                if (Byte.parseByte(string) != 1) break;
                short s4 = s;
                this.x[s4] = (byte)(this.x[s4] | 4);
                return;
            }
            case 19: {
                if (Byte.parseByte(string) != 1) break;
                short s5 = s;
                this.x[s5] = (byte)(this.x[s5] | 8);
                return;
            }
            case 20: {
                this.y[s] = Byte.parseByte(string);
                return;
            }
            case 21: {
                this.z[s] = Byte.parseByte(string);
            }
        }
    }

    public final void c(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.F[s] = s;
                return;
            }
            case 1: {
                this.G[s] = string;
                return;
            }
            case 2: {
                this.H[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 3: {
                this.I[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 4: {
                this.J[s] = (short)Integer.parseInt(string);
                return;
            }
            case 5: {
                this.L[s] = (byte)Integer.parseInt(string);
                this.K[s] = this.L[s];
                return;
            }
            case 6: {
                this.N[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 7: {
                this.M[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 8: {
                this.O[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 9: {
                this.P[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 10: {
                this.Q[s] = (short)(Integer.parseInt(string) * 100);
                return;
            }
            case 11: {
                this.R[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 12: {
                this.S[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 13: {
                this.T[s] = (short)Integer.parseInt(string);
                return;
            }
            case 14: {
                this.U[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 15: {
                if (this.T[s] != 189) break;
                byte by2 = this.U[s];
                if (string.trim().compareTo("0") == 0) {
                    this.B[by2] = this.b(this.B[by2], this.s[by2], this.F[s]);
                    byte by3 = by2;
                    this.s[by3] = (byte)(this.s[by3] + 1);
                    return;
                }
                if (string.trim().compareTo("1") != 0) break;
                byte by4 = by2;
                byte by5 = this.r[by4];
                this.r[by4] = (byte)(by5 + 1);
                this.A[by2][by5] = this.F[s];
            }
        }
    }

    public final void d(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.a[s] = Byte.parseByte(string);
                this.d[s] = new byte[37];
                return;
            }
            case 1: {
                this.b[s] = Short.parseShort(string);
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                this.c[s] = 0;
            }
        }
    }

    public final void aI() {
        block22: {
            RecordStore recordStore = null;
            try {
                byte[] byArray;
                String string = null;
                int n = 0;
                while (n < 2) {
                    int n2;
                    string = "record" + n;
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
                        this.ak[n] = (short)(byArray[1] << 8 | byArray[0] & 0xFF);
                        this.al[n] = byArray[2];
                        this.am[n] = (short)(byArray[4] << 8 | byArray[3] & 0xFF);
                        this.an[n] = this.G[this.ak[n]];
                        if (this.ak[n] >= 229) {
                            byte by = byArray[5];
                            byte[] byArray2 = new byte[by - 1];
                            n2 = 0;
                            while (n2 < by - 1) {
                                byArray2[n2] = byArray[6 + n2];
                                n2 = (byte)(n2 + 1);
                            }
                            this.an[n] = new String(byArray2, "UTF-8");
                        }
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
                    n = (byte)(n + 1);
                }
                recordStore = RecordStore.openRecordStore((String)"newPs", (boolean)true, (int)1, (boolean)false);
                if (recordStore.getNumRecords() > 0) {
                    byArray = recordStore.getRecord(1);
                    this.a(byArray, 0);
                }
                try {
                    n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        ++n;
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
                return;
            }
            catch (Exception exception) {
                if (recordStore == null) break block22;
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

    public final void L(byte by) {
        this.ar = by;
        this.aq = 1;
        this.ao.a((byte)4);
    }

    public final boolean M(byte by) {
        byte by2 = 0;
        while (by2 < this.W) {
            if (this.i[by] == this.b[this.X[by2]]) {
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
            s = (short)(s + this.J[sArray[by2]]);
            by2 = (byte)(by2 + 1);
        }
        return s;
    }

    public final void b(short s, byte by) {
        byte by2 = by;
        this.v[by2] = (short)(this.v[by2] + s);
        byte by3 = by;
        this.l[by3] = (short)(this.l[by3] - (s + 4) / 5);
        byte by4 = by;
        this.p[by4] = this.p[by4] - s;
    }

    public final void N(byte by) {
        this.ao.a(by);
        this.ao.repaint();
        this.aU = true;
    }

    public final void aJ() {
        this.bQ = 0;
        this.bP = 0;
        this.aH = true;
        this.aI = true;
        this.aw = this.au;
        System.gc();
    }

    public final void aK() {
        this.aJ();
        this.N((byte)6);
    }

    public final void aL() {
        this.aJ();
        this.aG = (byte)(this.aG - 1);
        if (this.aG <= 0) {
            this.bP = 0;
            this.aq = (byte)3;
            this.N((byte)4);
            return;
        }
        if (this.i[this.au] != this.b[this.ar]) {
            this.u();
        }
        this.N((byte)6);
    }

    public final void aM() {
        this.ao.m();
        this.N((byte)4);
    }

    public final void aN() {
        this.au = this.av;
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final void aO() {
        this.ay = this.ax;
        this.N((byte)7);
    }

    public final void aP() {
        this.c(this.ax, this.au);
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final void aQ() {
        this.c(this.ax, this.au);
        this.a(this.ax, d.d[2][3]);
        this.ax = this.az[0];
        this.bQ = (byte)6;
    }

    public final void aR() {
        this.c(this.au, this.ax);
        this.N((byte)11);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void a(short s, String string) {
        this.aJ = s;
        this.aK = string;
        this.N((byte)10);
    }

    public final void aS() {
        if (this.c(this.au, this.ax, this.ay)) {
            this.a(this.ax, d.d[2][0]);
        } else {
            this.a(this.ax, d.d[2][1]);
        }
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void aT() {
        this.d(this.ax, this.au);
        this.bQ = (byte)(this.bQ + 1);
        this.a(this.ax, d.d[2][3]);
    }

    public final void aU() {
        int n = 0;
        while (n < this.aA) {
            if (this.az[n] == this.ax) {
                boolean bl = false;
                this.az[n] = this.az[0];
                this.az[0] = this.ax;
            }
            ++n;
        }
        this.bQ = (byte)(this.bQ + 1);
        this.a(this.ax, d.d[0][2]);
    }

    public final void aV() {
        if (this.aO > this.l[this.au]) {
            this.aO = this.l[this.au];
        }
        this.N((byte)13);
    }

    public final void aW() {
        this.aO = this.t(this.ax);
        this.aV();
    }

    public final void aX() {
        this.aO = this.u(this.ax);
        this.aV();
    }

    public final void a(byte by, short s, int n) {
        int n2 = this.c(s, n);
        byte by2 = by;
        this.l[by2] = (short)(this.l[by2] - n);
        byte by3 = by;
        this.n[by3] = (short)(this.n[by3] + n2);
        if (this.n[by] > 999) {
            this.n[by] = 999;
        }
        if (this.k[by] < 99) {
            byte by4 = by;
            this.k[by4] = (byte)(this.k[by4] + 1);
        }
    }

    public final void aY() {
        this.a(this.au, this.ax, this.aO);
        this.a(this.ax, d.d[3][0]);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void b(byte by, short s, int n) {
        int n2 = this.c(s, n);
        byte by2 = by;
        this.l[by2] = (short)(this.l[by2] - n);
        byte by3 = by;
        this.o[by3] = (short)(this.o[by3] + n2);
        if (this.o[by] > 999) {
            this.o[by] = 999;
        }
        if (this.k[by] < 99) {
            byte by4 = by;
            this.k[by4] = (byte)(this.k[by4] + 1);
        }
    }

    public final void aZ() {
        this.b(this.au, this.ax, this.aO);
        this.a(this.ax, d.d[3][1]);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void c(byte by, short s, int n) {
        int n2 = this.d(s, n);
        byte by2 = by;
        this.l[by2] = (short)(this.l[by2] - n);
        byte by3 = by;
        this.q[by3] = (byte)(this.q[by3] + n2);
        if (this.q[by] > 99) {
            this.q[by] = 99;
        }
    }

    public final void ba() {
        this.c(this.au, this.ax, this.aO);
        this.a(this.ax, d.d[3][3]);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void d(byte by, short s, int n) {
        int n2 = this.e(s, n);
        byte by2 = by;
        this.l[by2] = (short)(this.l[by2] - n);
        byte by3 = by;
        this.p[by3] = this.p[by3] + n2;
        if (this.p[by] > 990000) {
            this.p[by] = 990000;
        }
        if (this.k[by] < 99) {
            byte by4 = by;
            this.k[by4] = (byte)(this.k[by4] + 1);
        }
    }

    public final void bb() {
        this.d(this.au, this.ax, this.aO);
        this.a(this.ax, d.d[3][4]);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final int e(short s, short s2) {
        byte by = this.N[s];
        int n = this.N[s2];
        byte by2 = this.P[s2];
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
            this.P[s3] = (byte)(this.P[s3] - (byte)n);
            if (this.P[s2] < 0) {
                this.P[s2] = 0;
            }
            return true;
        }
        return false;
    }

    public final void bc() {
        if (this.f(this.ax, this.ay)) {
            this.a(this.ax, d.d[5][1]);
        } else {
            this.a(this.ax, d.d[5][0]);
        }
        this.bQ = (byte)(this.bQ + 1);
    }

    public final int g(short s, short s2) {
        byte by = this.O[s];
        byte by2 = this.N[s2];
        int n = by * by;
        n /= 100;
        if ((n -= by2) <= this.s() % 100) {
            return -1;
        }
        return 0;
    }

    public final void bd() {
        short s = this.b[this.at];
        int n = this.g(this.ax, s);
        if (n == 0) {
            this.X[this.W] = this.at;
            this.Y[this.W] = 12;
            this.W = (byte)(this.W + 1);
            this.a(this.ax, d.d[1][0]);
        } else {
            this.a(this.ax, d.d[1][1]);
        }
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void m(short s) {
        int n = 100 - this.P[s];
        int n2 = (n + 50) / 2;
        if (n == n2) {
            n2 = this.s() % 3 - 1;
        } else if (this.P[s] > 50) {
            n2 = this.s() % (n2 - n);
        } else {
            n = this.s() % (n - n2);
        }
        this.P[s] = (byte)(n + n2);
    }

    public final boolean h(short s, short s2) {
        byte by = this.N[s];
        byte by2 = this.N[s2];
        byte by3 = this.P[s2];
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
            while (n < this.r[by2]) {
                if (this.A[by2][n] == s2) break;
                ++n;
            }
            byte by3 = by2;
            this.r[by3] = (byte)(this.r[by3] - 1);
            int n2 = n;
            while (n2 < this.r[by2]) {
                this.A[by2][n2] = this.A[by2][n2 + 1];
                ++n2;
            }
            this.A[by][this.r[by]] = s2;
            byte by4 = by;
            this.r[by4] = (byte)(this.r[by4] + 1);
            byte by5 = by2;
            this.u[by5] = (short)(this.u[by5] - this.J[s2]);
            byte by6 = by;
            this.u[by6] = (short)(this.u[by6] + this.J[s2]);
            if (this.r[by2] == 0) {
                byte by7 = this.h(this.i[by2]);
                n = 0;
                while (n < this.c[by7]) {
                    if (this.d[by7][n] == by2) break;
                    ++n;
                }
                byte by8 = by7;
                this.c[by8] = (byte)(this.c[by8] - 1);
                n2 = n;
                while (n2 < this.c[by7]) {
                    this.d[by7][n2] = this.d[by7][n2 + 1];
                    ++n2;
                }
                this.i[by2] = 0;
                this.j[by2] = 0;
            } else {
                this.j[by2] = this.A[by2][0];
            }
            return true;
        }
        return false;
    }

    public final void be() {
        if (this.a(this.au, this.av, this.ax, this.ay)) {
            this.a(this.ax, d.d[5][3]);
        } else {
            this.a(this.ax, d.d[5][2]);
        }
        this.bQ = (byte)(this.bQ + 1);
    }

    public final int n(short s) {
        if (this.N[s] < 30 || this.N[s] > 70) {
            return this.s() % 5 + 6;
        }
        return this.s() % 7 + 6;
    }

    public final void bf() {
        short s = 10;
        if (this.N[this.ax] < 30) {
            s = 30;
        } else if (this.N[this.ax] < 60) {
            s = 20;
        }
        if (this.l[this.au] >= s) {
            short s2 = this.ax;
            this.N[s2] = (byte)(this.N[s2] + this.n(this.ax));
            byte by = this.au;
            this.l[by] = (short)(this.l[by] - s);
            this.a(this.ax, d.d[4][3]);
            this.bQ = (byte)(this.bQ + 1);
            return;
        }
        this.a(this.ax, "\u8d44\u8d22\u4e0d\u8db3");
        this.bQ = (byte)3;
    }

    public final void bg() {
        this.bJ();
        this.a(this.ax, d.d[4][4]);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final short a(byte by, byte[] byArray, short s) {
        short s2 = s;
        s = (short)(s2 + 1);
        byArray[s2] = (byte)(this.i[by] & 0xFF);
        short s3 = s;
        s = (short)(s3 + 1);
        byArray[s3] = (byte)(this.i[by] >> 8);
        short s4 = s;
        s = (short)(s4 + 1);
        byArray[s4] = (byte)(this.j[by] & 0xFF);
        short s5 = s;
        s = (short)(s5 + 1);
        byArray[s5] = (byte)(this.j[by] >> 8);
        short s6 = s;
        s = (short)(s6 + 1);
        byArray[s6] = this.k[by];
        short s7 = s;
        s = (short)(s7 + 1);
        byArray[s7] = (byte)(this.l[by] & 0xFF);
        short s8 = s;
        s = (short)(s8 + 1);
        byArray[s8] = (byte)(this.l[by] >> 8);
        short s9 = s;
        s = (short)(s9 + 1);
        byArray[s9] = (byte)(this.m[by] & 0xFF);
        short s10 = s;
        s = (short)(s10 + 1);
        byArray[s10] = (byte)(this.m[by] >> 8);
        short s11 = s;
        s = (short)(s11 + 1);
        byArray[s11] = (byte)(this.n[by] & 0xFF);
        short s12 = s;
        s = (short)(s12 + 1);
        byArray[s12] = (byte)(this.n[by] >> 8);
        short s13 = s;
        s = (short)(s13 + 1);
        byArray[s13] = (byte)(this.o[by] & 0xFF);
        short s14 = s;
        s = (short)(s14 + 1);
        byArray[s14] = (byte)(this.o[by] >> 8);
        short s15 = s;
        s = (short)(s15 + 1);
        byArray[s15] = (byte)(this.p[by] & 0xFF);
        short s16 = s;
        s = (short)(s16 + 1);
        byArray[s16] = (byte)(this.p[by] >> 8 & 0xFF);
        short s17 = s;
        s = (short)(s17 + 1);
        byArray[s17] = (byte)(this.p[by] >> 16 & 0xFF);
        short s18 = s;
        s = (short)(s18 + 1);
        byArray[s18] = (byte)(this.p[by] >> 24);
        short s19 = s;
        s = (short)(s19 + 1);
        byArray[s19] = this.q[by];
        short s20 = s;
        s = (short)(s20 + 1);
        byArray[s20] = this.r[by];
        short s21 = s;
        s = (short)(s21 + 1);
        byArray[s21] = this.s[by];
        short s22 = s;
        s = (short)(s22 + 1);
        byArray[s22] = this.t[by];
        short s23 = s;
        s = (short)(s23 + 1);
        byArray[s23] = (byte)(this.u[by] & 0xFF);
        short s24 = s;
        s = (short)(s24 + 1);
        byArray[s24] = (byte)(this.u[by] >> 8);
        short s25 = s;
        s = (short)(s25 + 1);
        byArray[s25] = (byte)(this.v[by] & 0xFF);
        short s26 = s;
        s = (short)(s26 + 1);
        byArray[s26] = (byte)(this.v[by] >> 8);
        short s27 = s;
        s = (short)(s27 + 1);
        byArray[s27] = this.w[by];
        short s28 = s;
        s = (short)(s28 + 1);
        byArray[s28] = this.x[by];
        short s29 = s;
        s = (short)(s29 + 1);
        byArray[s29] = this.y[by];
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
        byArray[s3] = this.H[s];
        short s4 = s2;
        s2 = (short)(s4 + 1);
        byArray[s4] = this.M[s];
        short s5 = s2;
        s2 = (short)(s5 + 1);
        byArray[s5] = (byte)(this.J[s] & 0xFF);
        short s6 = s2;
        s2 = (short)(s6 + 1);
        byArray[s6] = (byte)(this.J[s] >> 8);
        short s7 = s2;
        s2 = (short)(s7 + 1);
        byArray[s7] = this.L[s];
        short s8 = s2;
        s2 = (short)(s8 + 1);
        byArray[s8] = this.N[s];
        short s9 = s2;
        s2 = (short)(s9 + 1);
        byArray[s9] = this.P[s];
        short s10 = s2;
        s2 = (short)(s10 + 1);
        byArray[s10] = (byte)(this.Q[s] & 0xFF);
        short s11 = s2;
        s2 = (short)(s11 + 1);
        byArray[s11] = (byte)(this.Q[s] >> 8);
        short s12 = s2;
        s2 = (short)(s12 + 1);
        byArray[s12] = this.R[s];
        short s13 = s2;
        s2 = (short)(s13 + 1);
        byArray[s13] = this.S[s];
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

    public final short a(byte[] byArray, short s) {
        int n = 229;
        while (n < 232) {
            if (this.F[n] <= 0) {
                short s2 = s;
                s = (short)(s2 + 1);
                byArray[s2] = 0;
            } else {
                short s3 = s;
                s = (short)(s3 + 1);
                byArray[s3] = 1;
                s = (short)(s + 1);
                short s4 = 0;
                try {
                    byte[] byArray2 = this.G[n].getBytes("UTF-8");
                    while (s4 < 20) {
                        short s5 = s;
                        s = (short)(s5 + 1);
                        short s6 = s4;
                        s4 = (byte)(s6 + 1);
                        byArray[s5] = byArray2[s6];
                    }
                }
                catch (Exception exception) {}
                byArray[s - s4 - 1] = s4;
                short s7 = s;
                s = (short)(s7 + 1);
                byArray[s7] = this.H[n];
                short s8 = s;
                s = (short)(s8 + 1);
                byArray[s8] = this.M[n];
                short s9 = s;
                s = (short)(s9 + 1);
                byArray[s9] = (byte)(this.J[n] & 0xFF);
                short s10 = s;
                s = (short)(s10 + 1);
                byArray[s10] = (byte)(this.J[n] >> 8);
                short s11 = s;
                s = (short)(s11 + 1);
                byArray[s11] = this.L[n];
                short s12 = s;
                s = (short)(s12 + 1);
                byArray[s12] = this.N[n];
                short s13 = s;
                s = (short)(s13 + 1);
                byArray[s13] = this.P[n];
                short s14 = s;
                s = (short)(s14 + 1);
                byArray[s14] = (byte)(this.Q[n] & 0xFF);
                short s15 = s;
                s = (short)(s15 + 1);
                byArray[s15] = (byte)(this.Q[n] >> 8);
                short s16 = s;
                s = (short)(s16 + 1);
                byArray[s16] = this.R[n];
                short s17 = s;
                s = (short)(s17 + 1);
                byArray[s17] = this.S[n];
                short s18 = s;
                s = (short)(s18 + 1);
                byArray[s18] = this.I[n];
                short s19 = s;
                s = (short)(s19 + 1);
                byArray[s19] = this.K[n];
                short s20 = s;
                s = (short)(s20 + 1);
                byArray[s20] = this.O[n];
                short s21 = s;
                s = (short)(s21 + 1);
                byArray[s21] = (byte)(this.cY[n - 229] & 0xFF);
                short s22 = s;
                s = (short)(s22 + 1);
                byArray[s22] = (byte)(this.cY[n - 229] >> 8);
            }
            n = (short)(n + 1);
        }
        return s;
    }

    public final synchronized void bh() {
        Object var4_1 = null;
        boolean bl = false;
        Object var7_3 = null;
        Object var8_4 = null;
        boolean bl2 = false;
        this.ak[this.aO] = this.b[this.ar];
        this.an[this.aO] = this.G[this.b[this.ar]];
        this.al[this.aO] = this.aC;
        this.am[this.aO] = this.aD;
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final short a(byte by, byte[] byArray, int n) {
        this.i[by] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
        this.j[by] = (short)(byArray[n + 3] << 8 | byArray[n + 2] & 0xFF);
        this.k[by] = byArray[n + 4];
        this.l[by] = (short)(byArray[n + 6] << 8 | byArray[n + 5] & 0xFF);
        this.m[by] = (short)(byArray[n + 8] << 8 | byArray[n + 7] & 0xFF);
        this.n[by] = (short)(byArray[n + 10] << 8 | byArray[n + 9] & 0xFF);
        this.o[by] = (short)(byArray[n + 12] << 8 | byArray[n + 11] & 0xFF);
        this.p[by] = byArray[n + 16] << 24 | byArray[n + 15] << 16 & 0xFF0000 | byArray[n + 14] << 8 & 0xFF00 | byArray[n + 13] & 0xFF;
        this.q[by] = byArray[n + 17];
        this.r[by] = byArray[n + 18];
        this.s[by] = byArray[n + 19];
        this.t[by] = byArray[n + 20];
        this.u[by] = (short)(byArray[n + 22] << 8 | byArray[n + 21] & 0xFF);
        this.v[by] = (short)(byArray[n + 24] << 8 | byArray[n + 23] & 0xFF);
        this.w[by] = byArray[n + 25];
        this.x[by] = byArray[n + 26];
        this.y[by] = byArray[n + 27];
        return 28;
    }

    public final int a(byte[] byArray, int n, short s) {
        this.H[s] = byArray[n];
        this.M[s] = byArray[n + 1];
        this.J[s] = (short)(byArray[n + 3] << 8 | byArray[n + 2] & 0xFF);
        this.L[s] = byArray[n + 4];
        this.N[s] = byArray[n + 5];
        this.P[s] = byArray[n + 6];
        this.Q[s] = (short)(byArray[n + 8] << 8 | byArray[n + 7] & 0xFF);
        this.R[s] = byArray[n + 9];
        this.S[s] = byArray[n + 10];
        return n + 11;
    }

    public final int a(byte[] byArray, int n) {
        int n2 = 229;
        while (n2 < 232) {
            byte by;
            if ((by = byArray[n++]) <= 0) {
                this.F[n2] = 0;
            } else {
                this.F[n2] = n2;
                by = byArray[n++];
                byte[] byArray2 = new byte[by - 1];
                int n3 = 0;
                while (n3 < by - 1) {
                    byArray2[n3] = byArray[n++];
                    n3 = (byte)(n3 + 1);
                }
                ++n;
                try {
                    this.G[n2] = new String(byArray2, "UTF-8");
                }
                catch (Exception exception) {}
                this.H[n2] = byArray[n++];
                this.M[n2] = byArray[n++];
                this.J[n2] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
                n += 2;
                this.L[n2] = byArray[n++];
                this.N[n2] = byArray[n++];
                this.P[n2] = byArray[n++];
                this.Q[n2] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
                n += 2;
                this.R[n2] = byArray[n++];
                this.S[n2] = byArray[n++];
                this.I[n2] = byArray[n++];
                this.K[n2] = byArray[n++];
                this.O[n2] = byArray[n++];
                this.cY[n2 - 229] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
                n += 2;
            }
            n2 = (short)(n2 + 1);
        }
        return n;
    }

    public final synchronized void bi() {
        short s;
        Object var5_1 = null;
        byte[] byArray = this.da;
        int n = 4;
        this.aE = (byte)-1;
        byte by = 0;
        while (by < 8) {
            this.aB[by] = byArray[n++];
            if (this.aB[by] == 0) {
                this.aE = (byte)(this.aE + 1);
            }
            by = (byte)(by + 1);
        }
        this.as = this.ar = byArray[n++];
        this.cP = this.ar;
        this.au = byArray[n++];
        this.aG = byArray[n++];
        this.aC = byArray[n++];
        this.aD = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
        n += 2;
        this.Z = byArray[n++];
        by = 1;
        while (by < 37) {
            n += this.a(by, byArray, n);
            s = 0;
            while (s < this.r[by]) {
                this.A[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
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
            if (this.s[by] > 0) {
                this.B[by] = new short[this.s[by]];
            }
            s = 0;
            while (s < this.s[by]) {
                this.B[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                n += 2;
                s = (short)(s + 1);
            }
            by = (byte)(by + 1);
        }
        s = 1;
        while (s < 229) {
            n = this.a(byArray, n, s);
            s = (short)(s + 1);
        }
        n = this.a(byArray, n);
        by = 1;
        while (by <= 8) {
            this.b[by] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
            this.c[by] = 0;
            n += 2;
            if (this.b[by] > 0) {
                s = 1;
                while (s < 37) {
                    if (this.i[s] == this.b[by]) {
                        byte by2 = by;
                        byte by3 = this.c[by2];
                        this.c[by2] = (byte)(by3 + 1);
                        this.d[by][by3] = (byte)s;
                    }
                    s = (short)(s + 1);
                }
                this.a[by] = this.c[by] == 0 || this.b[by] <= 0 ? (byte)0 : by;
            }
            by = (byte)(by + 1);
        }
        this.W = byArray[n++];
        by = 0;
        while (by < this.W) {
            this.X[by] = byArray[n++];
            this.Y[by] = byArray[n++];
            by = (byte)(by + 1);
        }
    }

    public final void bj() {
        this.bi();
        this.cO = true;
        this.aK();
    }

    public final void bk() {
        byte by = this.au;
        this.l[by] = (short)(this.l[by] - this.bc);
        byte by2 = this.au;
        this.m[by2] = (short)(this.m[by2] - this.bd);
        if (this.i[this.av] == 0) {
            byte by3 = this.ar;
            byte by4 = this.c[by3];
            this.c[by3] = (byte)(by4 + 1);
            this.d[this.ar][by4] = this.av;
            this.i[this.av] = this.b[this.ar];
            this.j[this.av] = this.az[0];
            this.r[this.av] = this.aA;
            byte by5 = 0;
            while (by5 < this.aA) {
                this.A[this.av][by5] = this.az[by5];
                by5 = (byte)(by5 + 1);
            }
            this.u[this.av] = this.b(this.az, this.aA);
            byte by6 = this.av;
            this.l[by6] = (short)(this.l[by6] + this.bc);
            byte by7 = this.av;
            this.m[by7] = (short)(this.m[by7] + this.bd);
            this.au = this.av;
            this.bQ = (byte)(this.bQ + 2);
            this.bl();
            return;
        }
        this.aq = (byte)4;
    }

    public final void bl() {
        byte by = this.bR[this.bP][this.bQ];
        System.gc();
        switch (by) {
            case 0: {
                this.aK();
                return;
            }
            case 1: {
                this.aL();
                return;
            }
            case 2: {
                this.aM();
                return;
            }
            case 3: {
                this.aO();
                return;
            }
            case 4: {
                this.bQ = (byte)(this.bQ + 1);
                this.N((byte)10);
                return;
            }
            case 5: {
                this.N((byte)8);
                return;
            }
            case 6: {
                this.N((byte)14);
                return;
            }
            case 8: {
                this.N((byte)5);
                return;
            }
            case 9: {
                this.N((byte)15);
                return;
            }
            case 10: {
                this.aR();
                return;
            }
            case 11: {
                this.N((byte)12);
                return;
            }
            case 12: {
                this.aW();
                return;
            }
            case 13: {
                this.aX();
                return;
            }
            case 14: {
                this.N((byte)16);
                return;
            }
            case 15: {
                this.N((byte)17);
                return;
            }
            case 16: {
                this.N((byte)9);
                return;
            }
            case 17: {
                this.N((byte)18);
                return;
            }
            case 83: {
                this.aQ();
                return;
            }
            case 84: {
                this.aU();
                return;
            }
            case 85: {
                this.bk();
                return;
            }
            case 86: {
                this.bj();
                return;
            }
            case 87: {
                this.bh();
                return;
            }
            case 88: {
                this.bg();
                return;
            }
            case 89: {
                this.bf();
                return;
            }
            case 90: {
                this.be();
                return;
            }
            case 91: {
                this.bd();
                return;
            }
            case 92: {
                this.bc();
                return;
            }
            case 93: {
                this.bb();
                return;
            }
            case 94: {
                this.ba();
                return;
            }
            case 95: {
                this.aZ();
                return;
            }
            case 96: {
                this.aY();
                return;
            }
            case 97: {
                this.aT();
                return;
            }
            case 98: {
                this.aS();
                return;
            }
            case 99: {
                this.aP();
                return;
            }
            case 100: {
                this.aN();
            }
        }
    }

    public final boolean bm() {
        if (this.aU) {
            this.aU = false;
            return true;
        }
        return false;
    }

    public final void a(byte by, int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.aw = this.bP == 1 || this.bP == 3 ? this.au : by;
        this.av = by;
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final void i(int n) {
        byte by;
        short[] sArray;
        if (this.bP != 22 && !this.bm()) {
            return;
        }
        if (this.aL) {
            sArray = this.A[this.aw];
            by = this.r[this.aw];
        } else {
            sArray = this.aM;
            by = this.aN;
            this.aL = true;
        }
        if (n == 1) {
            this.c(sArray, by);
            if (this.bP == 22) {
                this.aq = 1;
                return;
            }
            this.aK();
            return;
        }
        byte by2 = 0;
        while (by2 < by) {
            if (this.V[sArray[by2]]) {
                this.ax = sArray[by2];
                this.V[this.ax] = false;
                break;
            }
            by2 = (byte)(by2 + 1);
        }
        if (this.bP == 22) {
            this.aq = (byte)2;
            return;
        }
        if (this.bP == 9) {
            this.aU = true;
        }
        this.bQ = (byte)(this.bQ + 1);
        this.aw = this.au;
        this.bl();
    }

    public final void c(short s, byte by) {
        byte by2 = 0;
        while (by2 < this.r[by]) {
            if (this.A[by][by2] == s) break;
            by2 = (byte)(by2 + 1);
        }
        byte by3 = by2;
        while (by3 > 0) {
            this.A[by][by3] = this.A[by][by3 - 1];
            by3 = (byte)(by3 - 1);
        }
        this.A[by][0] = s;
        this.j[by] = s;
    }

    public final void d(short s, byte by) {
        if (!this.bm()) {
            return;
        }
        this.c(s, by);
    }

    public final void O(byte by) {
        this.u[by] = 0;
        byte by2 = 0;
        while (by2 < this.r[by]) {
            byte by3 = by;
            this.u[by3] = (short)(this.u[by3] + this.J[this.A[by][by2]]);
            by2 = (byte)(by2 + 1);
        }
    }

    public final void bn() {
        byte by = 0;
        while (by < this.aA) {
            byte by2 = this.av;
            byte by3 = this.r[by2];
            this.r[by2] = (byte)(by3 + 1);
            this.A[this.av][by3] = this.az[by];
            by = (byte)(by + 1);
        }
        if (this.i[this.av] == this.az[0]) {
            this.aU = true;
            this.d(this.az[0], this.av);
        }
        this.O(this.av);
    }

    public final void bo() {
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        while (by3 < this.r[this.au]) {
            if (by < this.aA && this.A[this.au][by3] == this.az[by]) {
                by = (byte)(by + 1);
            } else {
                this.A[this.au][by2] = this.A[this.au][by3];
                by2 = (byte)(by2 + 1);
            }
            by3 = (byte)(by3 + 1);
        }
        this.r[this.au] = by2;
        this.O(this.au);
    }

    public final void bp() {
        this.bn();
        this.bo();
    }

    public final void e(short s, byte by) {
        short s2 = s;
        this.c[s2] = (byte)(this.c[s2] - 1);
        byte by2 = 0;
        while (by2 < this.c[s]) {
            if (this.d[s][by2] == by) break;
            by2 = (byte)(by2 + 1);
        }
        byte by3 = by2;
        while (by3 < this.c[s]) {
            this.d[s][by3] = this.d[s][by3 + 1];
            by3 = (byte)(by3 + 1);
        }
    }

    public final void bq() {
        this.r[this.au] = 0;
        this.j[this.au] = 0;
        this.i[this.au] = 0;
        this.u[this.au] = 0;
        this.e((short)this.ar, this.au);
    }

    public final void j(int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        if (this.bP == 1) {
            this.bn();
            this.bq();
            this.au = this.av;
            this.bQ = (byte)4;
        } else {
            this.ax = this.az[0];
            this.bQ = (byte)2;
        }
        this.bl();
    }

    public final void c(short[] sArray, byte by) {
        byte by2 = 0;
        while (by2 < by) {
            this.V[sArray[by2]] = false;
            by2 = (byte)(by2 + 1);
        }
    }

    public final void k(int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.c(this.A[this.au], this.r[this.au]);
            this.aK();
            return;
        }
        boolean bl = false;
        this.aH = true;
        this.aA = 0;
        byte by = 0;
        while (by < this.r[this.au]) {
            if (this.V[this.A[this.au][by]]) {
                if (by == 0) {
                    bl = true;
                }
                byte by2 = this.aA;
                this.aA = (byte)(by2 + 1);
                this.az[by2] = this.A[this.au][by];
                this.V[this.A[this.au][by]] = false;
            }
            by = (byte)(by + 1);
        }
        this.aJ = this.az[0];
        if (this.bP == 1) {
            if (this.aA + this.r[this.av] > 10) {
                this.a(this.az[0], "\u79fb\u52a8\u6b66\u5c06\u6570\u8fc7\u591a!");
                this.bQ = (byte)6;
                return;
            }
            this.aK = d.d[0][0];
        }
        if (this.aA == this.r[this.au]) {
            this.ao.n();
            this.aU = true;
            this.ao.repaint();
            return;
        }
        if (this.bP == 1) {
            if (bl) {
                this.aw = this.au;
                this.aI = false;
                this.bQ = (byte)2;
            } else {
                this.bQ = (byte)4;
            }
            this.bp();
        } else {
            this.bQ = (byte)(this.bQ + 1);
        }
        this.bl();
    }

    public final void l(int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final void m(int n) {
        this.l(n);
    }

    public final void br() {
        if (!this.bm()) {
            return;
        }
        this.bl();
    }

    public final void a(short[] sArray) {
        byte by = this.au;
        this.l[by] = (short)(this.l[by] - sArray[0]);
        byte by2 = this.au;
        this.m[by2] = (short)(this.m[by2] - sArray[1]);
        byte by3 = this.au;
        this.w[by3] = (byte)(this.w[by3] - sArray[2]);
        byte by4 = this.av;
        this.l[by4] = (short)(this.l[by4] + sArray[0]);
        byte by5 = this.av;
        this.m[by5] = (short)(this.m[by5] + sArray[1]);
        byte by6 = this.av;
        this.w[by6] = (byte)(this.w[by6] + sArray[2]);
    }

    public final void a(int n, short[] sArray) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.a(sArray);
        this.bQ = (byte)(this.bQ + 1);
        this.a(this.j[this.au], d.d[0][0]);
    }

    public final void a(short s, short s2, int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.bc = s;
        this.bd = s2;
        this.bQ = (byte)6;
        this.aK = d.d[0][2];
        if (this.aA == this.r[this.au]) {
            this.bq();
        } else {
            if (this.az[0] == this.j[this.au]) {
                this.aI = false;
                this.aw = this.au;
                this.bQ = (byte)3;
            } else {
                this.aI = false;
                this.aL = false;
                int n2 = 0;
                while (n2 < this.aA) {
                    this.aM[n2] = this.az[n2];
                    ++n2;
                }
                this.aN = this.aA;
                this.bQ = (byte)5;
            }
            this.bo();
        }
        this.bl();
    }

    public final void o(short s) {
        short s2 = this.ax;
        this.P[s2] = (byte)(this.P[s2] + this.w(s));
        if (this.P[this.ax] > 99 || this.P[this.ax] <= 0) {
            this.P[this.ax] = 99;
        }
        if (s == 200) {
            byte by = this.au;
            this.w[by] = (byte)(this.w[by] - 1);
            return;
        }
        byte by = this.au;
        this.l[by] = (short)(this.l[by] - s);
    }

    public final void a(short s, int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.o(s);
        this.bQ = (byte)(this.bQ + 1);
        this.a(this.ax, d.d[2][2]);
    }

    public final void n(int n) {
        this.l(n);
    }

    public final void b(byte by, int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.at = by;
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final void bs() {
        if (!this.bm()) {
            return;
        }
        this.bQ = 0;
        this.bl();
    }

    public final void o(int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 1) {
            this.aK();
            return;
        }
        this.a(this.j[this.au], d.d[4][0]);
        this.bQ = (byte)(this.bQ + 1);
    }

    public final void p(int n) {
        if (!this.bm()) {
            return;
        }
        switch (n) {
            case 0: {
                this.a(this.ax, d.d[4][1]);
                this.bQ = (byte)(this.bQ + 1);
                return;
            }
            case 1: {
                this.aK();
                return;
            }
            case 2: {
                this.a(this.j[this.au], "\u8d44\u8d22\u4e0d\u8db3");
                this.bQ = (byte)3;
                return;
            }
            case 3: {
                this.a(this.ax, "\u542f\u7980\u4e3b\u516c,\u5c5e\u4e0b\u65e0\u6cd5\u4f7f\u7528\u6b64\u6b66\u5668");
                this.bQ = (byte)3;
            }
        }
    }

    public final void q(int n) {
        if (!this.bm()) {
            return;
        }
        if (n == 5) {
            this.aK();
            return;
        }
        this.aO = n;
        this.bQ = (byte)(this.bQ + 1);
        this.bl();
    }

    public final void P(byte by) {
        this.bP = by;
        this.bl();
    }

    public final void Q(byte by) {
        this.bP = by;
        byte by2 = 0;
        while (by2 < this.E[this.au]) {
            if (this.i[this.D[this.au][by2]] == this.i[this.au]) {
                this.aH = false;
                this.bl();
                return;
            }
            by2 = (byte)(by2 + 1);
        }
        this.bQ = (byte)6;
        this.a(this.j[this.au], "\u5f53\u524d\u65e0\u53ef\u79fb\u52a8\u7684\u57ce!");
    }

    public final void R(byte by) {
        this.bP = by;
        byte by2 = 0;
        while (by2 < this.E[this.au]) {
            if (this.i[this.D[this.au][by2]] == this.i[this.au]) {
                this.bl();
                return;
            }
            by2 = (byte)(by2 + 1);
        }
        this.bQ = (byte)3;
        this.a(this.j[this.au], "\u5f53\u524d\u65e0\u53ef\u8f93\u9001\u7684\u57ce!");
    }

    public final void S(byte by) {
        this.bP = by;
        this.aN = 0;
        byte by2 = 0;
        while (by2 < this.r[this.au]) {
            if (this.P[this.A[this.au][by2]] < 90) {
                byte by3 = this.aN;
                this.aN = (byte)(by3 + 1);
                this.aM[by3] = this.A[this.au][by2];
            }
            by2 = (byte)(by2 + 1);
        }
        if (this.aN > 0) {
            this.aL = false;
            this.bl();
            return;
        }
        this.bQ = (byte)3;
        this.a(this.j[this.au], "\u540f\u58eb\u7686\u5fe0\u5fc3\u4e0d\u8d30!");
    }

    public final void T(byte by) {
        this.bP = by;
        if (this.r[this.au] == 10) {
            this.a(this.j[this.au], "\u672c\u57ce\u7f6e\u540f\u5df2\u8db3!");
            this.bQ = (byte)4;
            return;
        }
        if (this.t[this.au] == 0) {
            this.a(this.j[this.au], "\u91ce\u65e0\u9057\u8d24!");
            this.bQ = (byte)4;
            return;
        }
        this.aL = false;
        this.aN = this.t[this.au];
        byte by2 = 0;
        while (by2 < this.aN) {
            this.aM[by2] = this.C[this.au][by2];
            by2 = (byte)(by2 + 1);
        }
        this.bl();
    }

    public final void U(byte by) {
        this.bP = by;
        if (this.i[this.au] == this.j[this.au]) {
            this.a(this.i[this.au], d.d[2][4]);
            this.bQ = (byte)2;
            return;
        }
        this.bl();
    }

    public final boolean bt() {
        if (this.l[this.au] == 0) {
            this.a(this.j[this.au], "\u8d44\u8d22\u4e0d\u8db3");
            this.bQ = (byte)4;
            return true;
        }
        return false;
    }

    public final void V(byte by) {
        this.bP = by;
        if (this.bt()) {
            return;
        }
        if (this.n[this.au] == 999) {
            this.a(this.j[this.au], d.d[3][2]);
            this.bQ = (byte)4;
            return;
        }
        this.aP = 0;
        this.bQ = 0;
        this.bl();
    }

    public final void W(byte by) {
        this.bP = by;
        if (this.bt()) {
            return;
        }
        if (this.o[this.au] == 999) {
            this.a(this.j[this.au], d.d[3][5]);
            this.bQ = (byte)4;
            return;
        }
        this.aP = 1;
        this.bQ = 0;
        this.bl();
    }

    public final void X(byte by) {
        this.bP = by;
        if (this.bt()) {
            return;
        }
        if (this.q[this.au] == 99) {
            this.a(this.j[this.au], d.d[3][6]);
            this.bQ = (byte)4;
            return;
        }
        this.aP = 2;
        this.bQ = 0;
        this.bl();
    }

    public final void Y(byte by) {
        this.bP = by;
        if (this.bt()) {
            return;
        }
        if (this.p[this.au] == 990000) {
            this.a(this.j[this.au], d.d[3][7]);
            this.bQ = (byte)4;
            return;
        }
        this.aP = 3;
        this.bQ = 0;
        this.bl();
    }

    public final void Z(byte by) {
        this.bP = by;
        boolean bl = false;
        this.aT = 0;
        int n = 1;
        while (n <= 7) {
            if (this.a[n] > 0 && this.a[n] != this.ar) {
                byte by2 = 0;
                while (by2 < this.W) {
                    if (this.X[by2] == this.a[n]) break;
                    by2 = (byte)(by2 + 1);
                }
                if (by2 >= this.W) {
                    byte by3 = this.aT;
                    this.aT = (byte)(by3 + 1);
                    this.aQ[by3] = this.a[n];
                }
            }
            n = (byte)(n + 1);
        }
        if (this.aT == 0) {
            this.a(this.j[this.au], "\u5df2\u4e0e\u6240\u6709\u52bf\u529b\u540c\u76df");
            this.bQ = (byte)4;
        }
        this.bl();
    }

    public final void aa(byte by) {
        this.bP = by;
        if (this.r[this.au] == 10) {
            this.a(this.j[this.au], "\u672c\u57ce\u7f6e\u540f\u5df2\u8db3!");
            this.bQ = (byte)5;
            return;
        }
        this.bl();
    }

    public final void ab(byte by) {
        this.bP = by;
        if ((this.x[this.au] & 8) != 8) {
            this.bQ = (byte)2;
            this.a(this.j[this.au], "\u672c\u57ce\u65e0\u7cae\u5e97,\u65e0\u6cd5\u4e70\u5356\u7cae\u8349");
            return;
        }
        this.bl();
    }

    public final void ac(byte by) {
        this.bP = by;
        if ((this.x[this.au] & 1) != 1) {
            this.bQ = (byte)3;
            this.a(this.j[this.au], "\u672c\u57ce\u65e0\u6b66\u5668\u5e97,\u65e0\u6cd5\u8d2d\u5165\u6b66\u5668");
            return;
        }
        this.bl();
    }

    public final void ad(byte by) {
        this.bP = by;
        if ((this.x[this.au] & 2) == 2) {
            this.aN = 0;
            byte by2 = 0;
            while (by2 < this.r[this.au]) {
                if (this.N[this.A[this.au][by2]] < 80) {
                    byte by3 = this.aN;
                    this.aN = (byte)(by3 + 1);
                    this.aM[by3] = this.A[this.au][by2];
                }
                by2 = (byte)(by2 + 1);
            }
            if (this.aN > 0) {
                this.aL = false;
                this.bl();
                return;
            }
            this.a(this.j[this.au], "\u672c\u57ce\u6b66\u5c06\u7686\u624d\u534e\u6a2a\u6ea2, \u65e0\u9700\u5b66\u4e60");
            this.bQ = (byte)3;
            return;
        }
        this.a(this.j[this.au], "\u672c\u57ce\u65e0\u5b66\u5802,\u65e0\u6cd5\u4fee\u4e60\u667a\u529b");
        this.bQ = (byte)3;
    }

    public final void ae(byte by) {
        boolean bl = false;
        this.bP = by;
        if ((this.x[this.au] & 4) != 4) {
            this.a(this.j[this.au], "\u672c\u57ce\u65e0\u533b\u9662,\u65e0\u6cd5\u6cbb\u7597\u6b66\u5c06");
            this.bQ = (byte)3;
            return;
        }
        this.aN = 0;
        byte by2 = 0;
        while (by2 < this.r[this.au]) {
            if (this.L[this.A[this.au][by2]] < this.K[this.A[this.au][by2]]) {
                byte by3 = this.aN;
                this.aN = (byte)(by3 + 1);
                this.aM[by3] = this.A[this.au][by2];
            }
            by2 = (byte)(by2 + 1);
        }
        if (this.aN > 0) {
            if (this.l[this.au] >= 50) {
                this.aL = false;
                this.bl();
                return;
            }
            this.a(this.j[this.au], "\u8d44\u8d22\u4e0d\u8db3");
            this.bQ = (byte)3;
            return;
        }
        this.a(this.j[this.au], "\u5c06\u58eb\u5747\u9f99\u7cbe\u864e\u731b!");
        this.bQ = (byte)3;
    }

    public final void af(byte by) {
        this.bP = by;
        byte by2 = 0;
        while (by2 < this.E[this.au]) {
            byte by3 = this.D[this.au][by2];
            if (this.i[by3] != this.b[this.ar]) {
                byte by4 = 0;
                while (by4 < this.W) {
                    if (this.b[this.X[by4]] == this.i[by3]) break;
                    by4 = (byte)(by4 + 1);
                }
                if (by4 >= this.W) break;
            }
            by2 = (byte)(by2 + 1);
        }
        if (by2 < this.E[this.au]) {
            this.aH = false;
            this.bl();
            return;
        }
        this.a(this.j[this.au], "\u5f53\u524d\u65e0\u53ef\u6218\u4e89\u57ce\u5e02!");
        this.bQ = (byte)8;
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
            this.bP = (byte)-1;
        }
        return n;
    }

    public final int s(int n) {
        if (this.Z == 1) {
            return this.r(n);
        }
        if (this.Z == 2) {
            return this.C(n);
        }
        return this.E(n);
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
        this.u[by] = this.b(sArray, (byte)n);
    }

    public final void a(byte by, short[] sArray, byte by2) {
        this.j[by] = sArray[0];
    }

    public final void b(byte by, short[] sArray, byte by2) {
        if (this.Z == 1) {
            this.a(by, sArray, by2);
            return;
        }
        if (this.Z == 2) {
            this.c(by, sArray, by2);
            return;
        }
        this.d(by, sArray, by2);
    }

    public final void d(short[] sArray, byte n) {
        int n2 = 0;
        while (n2 < n) {
            if (sArray[n2] == this.i[this.au]) {
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
        byte by = this.r[this.au];
        int n2 = -1 * this.u[this.av];
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
                if (++by3 != this.r[this.au]) continue;
                by3 = 0;
            }
            sArray[by2] = this.A[this.au][by3];
            this.A[this.au][by3] = 0;
            by2 = (byte)(by2 + 1);
            byArray[by3] = 0;
            by = (byte)(by - 1);
            if ((n2 += this.J[sArray[by2]]) >= n) break;
        }
        this.r[this.au] = by;
        this.a(this.au, this.A[this.au], (int)by);
        this.b(this.au, this.A[this.au], by);
        this.d(sArray, by2);
        return by2;
    }

    public final byte b(short[] sArray, int n) {
        if (this.Z == 1) {
            return this.a(sArray, n);
        }
        if (this.Z == 2) {
            return this.e(sArray, n);
        }
        return this.f(sArray, n);
    }

    public final int c(short[] sArray, int n) {
        int n2 = 0;
        n2 = this.b(sArray, (byte)n);
        n2 = n2 * 4 / 1000 + 1;
        int n3 = this.s() % (n2 * 30) + n2 * 30;
        if (n3 < this.m[this.au] / 2) {
            n3 = this.m[this.au] / 2;
        } else if (n3 >= this.m[this.au]) {
            n3 = this.m[this.au] * 2 / 3;
        }
        return n3;
    }

    public final void a(short[] sArray, byte n, int n2, int n3) {
        this.d[this.as][this.c[this.as]] = this.av;
        byte by = this.as;
        this.c[by] = (byte)(this.c[by] + 1);
        int n4 = 0;
        while (n4 < n) {
            this.A[this.av][n4] = sArray[n4];
            ++n4;
        }
        this.r[this.av] = n;
        this.i[this.av] = this.i[this.au];
        this.j[this.av] = sArray[0];
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
        this.m[by3] = (short)(this.m[by3] - this.u[by2] * 4 * n2 / 1000);
        s = this.b(sArray, by);
        return s * 4 * n2 / 1000;
    }

    public final void b(short s, int n) {
        if (this.J[s] > 0) {
            int n2;
            if (this.J[s] < n) {
                n = this.J[s];
            }
            if ((n2 = n - 300) < 0) {
                n2 = 0;
            }
            if ((n = this.s() % 301 + n2) < this.J[s]) {
                this.J[s] = (short)n;
            }
        }
        if (this.L[s] > 0) {
            this.L[s] = (byte)(this.s() % this.L[s] + 1);
        }
    }

    public final short i(short s, short s2) {
        if (s2 <= 0) {
            return 0;
        }
        short s3 = (short)(this.s() % s2);
        s2 = (short)(s2 - s3);
        short s4 = s;
        this.Q[s4] = (short)(this.Q[s4] + s3);
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
        this.Q[s2] = (short)(this.Q[s2] + s);
        this.f(sArray[n]);
    }

    public final void bu() {
        byte by = this.h(this.i[this.au]);
        byte by2 = this.h(this.i[this.av]);
        this.d[by][this.c[by]] = this.av;
        byte by3 = by;
        this.c[by3] = (byte)(this.c[by3] + 1);
        byte by4 = by2;
        this.c[by4] = (byte)(this.c[by4] - 1);
        if (this.c[by2] > 0) {
            int n = 0;
            while (n <= this.c[by2]) {
                if (this.d[by2][n] == this.av) break;
                ++n;
            }
            this.d[by2][n] = this.d[by2][this.c[by2]];
        }
    }

    public final void p(short s) {
        this.B[this.av] = this.b(this.B[this.av], this.s[this.av], s);
        byte by = this.av;
        this.s[by] = (byte)(this.s[by] + 1);
    }

    public final void q(short s) {
        this.m(s);
        if (this.Z == 2 && this.P[s] < 70) {
            short s2 = s;
            this.P[s2] = (byte)(this.P[s2] + 30);
        }
        if (this.P[s] < 40) {
            short s3 = s;
            this.P[s3] = (byte)(this.P[s3] + 15);
        }
    }

    public final void r(short s) {
        this.q(s);
        this.A[this.av][this.r[this.av]] = s;
        byte by = this.av;
        this.r[by] = (byte)(this.r[by] + 1);
    }

    public final byte a(short s, byte[] byArray, byte by, byte by2, short s2) {
        int n;
        byte by3 = by2;
        if (this.i[by3] == 0) {
            byte by4 = this.h(s2);
            this.d[by4][this.c[by4]] = by3;
            byte by5 = by4;
            this.c[by5] = (byte)(this.c[by5] + 1);
            this.i[by3] = this.b[by4];
            this.j[by3] = s;
            this.r[by3] = 1;
            this.A[by3][0] = s;
            this.u[by3] = this.J[s];
            return by;
        }
        if (s == s2) {
            n = this.r[by3];
            while (n > 0) {
                this.A[by3][n] = this.A[by3][n - 1];
                --n;
            }
            this.A[by3][0] = s;
            this.j[by3] = s;
        } else {
            this.A[by3][this.r[by3]] = s;
        }
        byte by6 = by3;
        this.r[by6] = (byte)(this.r[by6] + 1);
        byte by7 = by3;
        this.u[by7] = (short)(this.u[by7] + this.J[s]);
        if (this.r[by3] == 10 && (by = (byte)(by - 1)) != 0) {
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
        this.l[by2] = (short)(this.l[by2] + n2);
        byte by3 = by;
        this.m[by3] = (short)(this.m[by3] + n);
        if (this.l[by] > 9999) {
            this.l[by] = 9999;
        }
        if (this.m[by] > 9999) {
            this.m[by] = 9999;
        }
    }

    /*
     * Unable to fully structure code
     */
    public final boolean a(short[] var1_1, byte var2_2, short var3_3, int var4_4, int var5_5) {
        var6_6 = 0;
        var8_7 = new byte[this.E[this.av]];
        var10_8 = 0;
        var12_9 = 0;
        var13_10 = false;
        var7_11 = 0;
        while (var7_11 < this.E[this.av]) {
            var9_12 = this.D[this.av][var7_11];
            if (this.i[var9_12] == 0 || this.i[var9_12] == var3_3 && this.r[var9_12] < 10) {
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
                            } else if (this.r[this.av] == 10) {
                                this.p(var1_1[var7_11]);
                            } else {
                                this.r(var1_1[var7_11]);
                            }
                            break block14;
                        }
                        if (this.r[this.av] != 10) break block15;
                        var11_13 = this.b(var3_3, var8_7, var6_6, this.av);
                        if (var7_11 == 0) {
                            var12_9 = var11_13;
                        }
                        var6_6 = this.a(var1_1[var7_11], var8_7, var6_6, (byte)var11_13, var3_3);
                        break block14;
                    }
                    if (this.L[var1_1[var7_11]] > 35 || this.J[var1_1[var7_11]] > 150) break block16;
                    var11_13 = this.N[var1_1[var7_11]] >= 95 || this.N[var1_1[var7_11]] >= 95 ? 10 : (this.N[var1_1[var7_11]] >= 90 || this.N[var1_1[var7_11]] >= 90 ? 20 : (this.N[var1_1[var7_11]] >= 80 || this.N[var1_1[var7_11]] >= 80 ? 40 : 80));
                    if (this.s() % 100 > var11_13) break block16;
                    if (var7_11 != 0) ** GOTO lbl46
                    if (var1_1[var7_11] == var3_3) {
                        v0 = this.b(var3_3, var8_7, var6_6, this.av);
                        var11_13 = v0;
                        var12_9 = v0;
                        var6_6 = this.a(var1_1[var7_11], var8_7, var6_6, (byte)var11_13, var3_3);
                    } else {
                        var10_8 = 1;
lbl46:
                        // 2 sources

                        this.r(var1_1[var7_11]);
                    }
                    break block14;
                }
                var11_13 = this.b(var3_3, var8_7, var6_6, this.av);
                if (var7_11 == 0) {
                    var12_9 = var11_13;
                }
                var6_6 = this.a(var1_1[var7_11], var8_7, var6_6, (byte)var11_13, var3_3);
            }
            ++var7_11;
        }
        if (var10_8 > 0) {
            this.a(this.av, var4_4, var5_5);
        } else {
            this.a((byte)var12_9, var4_4, var5_5);
        }
        return var13_10;
    }

    public final void b(short[] sArray, byte by, int n, int n2) {
        int n3 = this.r[this.av];
        short s = this.i[this.av];
        byte by2 = this.h(s);
        short[] sArray2 = new short[10];
        int n4 = 0;
        while (n4 < n3) {
            sArray2[n4] = this.A[this.av][n4];
            ++n4;
        }
        this.bu();
        n4 = 0;
        while (n4 < by) {
            this.A[this.av][n4] = sArray[n4];
            ++n4;
        }
        this.r[this.av] = by;
        this.i[this.av] = this.i[this.au];
        this.j[this.av] = sArray[0];
        n4 = this.l[this.av];
        this.l[this.av] = (short)n2;
        n2 = n4;
        n4 = this.m[this.av];
        this.m[this.av] = (short)n;
        n = n4;
        boolean bl = this.a(sArray2, (byte)n3, s, n, n2);
        if (bl) {
            if (this.c[by2] > 0) {
                this.C(by2);
            } else {
                this.a[by2] = 0;
            }
        }
        if (this.a[by2] > 0) {
            this.ag(this.a[by2]);
        }
    }

    public final void c(short[] sArray, byte by, int n, int n2) {
        short s = this.b(sArray, by);
        short s2 = this.b(this.A[this.av], this.r[this.av]);
        int n3 = 0;
        while (n3 < this.r[this.av]) {
            this.b(this.A[this.av][n3], 1000);
            ++n3;
        }
        n3 = 0;
        while (n3 < by) {
            this.b(sArray[n3], 1000);
            ++n3;
        }
        short s3 = this.b(this.A[this.av], this.r[this.av]);
        this.c(sArray, by, (short)(s2 - s3));
        s3 = this.b(sArray, by);
        this.c(this.A[this.av], this.r[this.av], (short)(s - s3));
        this.b(sArray, by, n, n2);
    }

    public final void a(short[] sArray, byte by, int n, int n2, int n3) {
        short s = this.b(sArray, by);
        short s2 = this.b(this.A[this.av], this.r[this.av]);
        int n4 = 0;
        while (n4 < this.r[this.av]) {
            if (this.Z == 1) {
                this.b(this.A[this.av][n4], 300);
            } else if (this.Z == 2) {
                this.b(this.A[this.av][n4], 500);
            }
            ++n4;
        }
        n4 = 0;
        while (n4 < by) {
            if (this.Z == 1) {
                this.b(sArray[n4], n3);
            } else if (this.Z == 2) {
                if (n3 < 700) {
                    this.b(sArray[n4], 700);
                } else {
                    this.b(sArray[n4], n3);
                }
            }
            ++n4;
        }
        short s3 = this.b(this.A[this.av], this.r[this.av]);
        this.c(sArray, by, (short)(s2 - s3));
        s3 = this.b(sArray, by);
        this.c(this.A[this.av], this.r[this.av], (short)(s - s3));
        this.b(sArray, by, n, n2);
    }

    public final void b(short[] sArray, byte by, int n, int n2, int n3) {
        short s = this.b(sArray, by);
        short s2 = this.b(this.A[this.av], this.r[this.av]);
        int n4 = 0;
        while (n4 < this.r[this.av]) {
            if (this.Z == 1) {
                this.b(this.A[this.av][n4], n3);
            } else if (this.Z == 2) {
                if (n3 < 700) {
                    this.b(this.A[this.av][n4], 700);
                } else {
                    this.b(this.A[this.av][n4], n3);
                }
            }
            ++n4;
        }
        n4 = 0;
        while (n4 < by) {
            if (this.Z == 1) {
                this.b(sArray[n4], 300);
            } else if (this.Z == 2) {
                this.b(sArray[n4], 500);
            }
            ++n4;
        }
        short s3 = this.b(this.A[this.av], this.r[this.av]);
        this.c(sArray, by, (short)(s2 - s3));
        s3 = this.b(sArray, by);
        this.c(this.A[this.av], this.r[this.av], (short)(s - s3));
        this.a(sArray, by, this.i[this.au], n, n2);
    }

    public final int s(short s) {
        int n = (this.N[s] + this.M[s]) * (this.J[s] + 150);
        n = this.c(this.N[s], n);
        n = this.c(this.M[s], n);
        return n;
    }

    public final int d(short[] sArray, int n) {
        int n2 = 0;
        int n3 = 0;
        n3 = 0;
        while (n3 < n) {
            int n4 = this.s(sArray[n3]);
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
        n4 = this.d(this.A[this.av], (int)this.r[this.av]);
        n5 = this.M[this.A[this.av][0]] * this.J[this.A[this.av][0]] * 3 / 2;
        n5 = this.c(this.M[this.A[this.av][0]], n5);
        if ((n6 = (n5 = n3 - (n4 += n5)) > 0 ? 300 * n3 / n4 : 300 * n4 / n3) > 1000) {
            n6 = 1000;
        }
        if ((n -= this.a(sArray, by, this.av, n6)) < 0) {
            n = 0;
        }
        if (this.m[this.av] < 0) {
            this.m[this.av] = 0;
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
    public final void bv() {
        long l = System.currentTimeMillis();
        while (this.ao.a == 0) {
            long l2 = System.currentTimeMillis() - l;
            if (l2 < 100L) {
                try {
                    b b2 = this;
                    synchronized (b2) {
                        Thread.sleep(100L - l2);
                    }
                }
                catch (Exception exception) {}
            }
            this.ao.repaint();
            l = System.currentTimeMillis();
        }
        return;
    }

    public final void t(int n) {
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        this.aA = this.b(this.az, n);
        n2 = this.c(this.az, (int)this.aA);
        n3 = this.l[this.au] < 2 ? 0 : this.s() % (this.l[this.au] / 2);
        byte by = this.au;
        this.l[by] = (short)(this.l[by] - n3);
        byte by2 = this.au;
        this.m[by2] = (short)(this.m[by2] - n2);
        System.gc();
        if (this.i[this.av] == this.b[this.ar]) {
            this.aO = 1;
            this.N((byte)19);
            this.bv();
            this.be = (short)n3;
            this.bf = (short)n2;
            this.aq = (byte)3;
            return;
        }
        if (this.i[this.av] == 0) {
            this.aO = 2;
            this.N((byte)19);
            this.ax();
            this.a(this.az, this.aA, n2, n3);
        } else {
            short s = this.i[this.av];
            byte by3 = this.h(s);
            this.aO = 1;
            this.N((byte)19);
            this.ax();
            this.aO = this.d(this.az, this.aA, n2, n3) ? 4 : 5;
            this.N((byte)19);
            this.ax();
            if (this.a[by3] <= 0) {
                this.ay = s;
                this.at = by3;
                this.aO = 18;
                this.N((byte)19);
                this.ax();
            } else if (this.b[by3] != s) {
                this.ay = s;
                this.at = by3;
                this.N((byte)19);
                this.ax();
            }
        }
        this.u[this.av] = this.b(this.A[this.av], this.r[this.av]);
        try {
            Thread.sleep(500L);
        }
        catch (Exception exception) {}
        System.gc();
    }

    public final void ag(byte by) {
        if (this.Z >= 2) {
            this.as(by);
        }
    }

    public final void bw() {
        int n = 0;
        boolean bl = false;
        byte by = this.e(this.as);
        while (this.aF < by) {
            this.aF = (byte)(this.aF + 1);
            this.bP = (byte)-1;
            this.au = 0;
            this.av = 0;
            System.gc();
            this.ag(this.as);
            n = this.bz();
            if (this.bP == 3) {
                n = this.s(n);
                if (this.bP == 3) {
                    this.t(n);
                    if (this.aq != 3) continue;
                    break;
                }
            }
            this.bB();
            if (this.bP == 2) {
                this.bD();
                continue;
            }
            n = this.bF();
            if (this.bP == 1) {
                this.v(n);
                continue;
            }
            this.bH();
            if (this.bP == 19) {
                this.bJ();
                continue;
            }
            this.bL();
            if (this.bP == 8) {
                this.bM();
                continue;
            }
            n = this.bO();
            if (this.bP == 12) {
                this.x(n);
                continue;
            }
            this.bQ();
        }
        this.aF = 0;
    }

    public final boolean ah(byte by) {
        int n = 0;
        while (n < this.r[by]) {
            if (this.L[this.A[by][n]] < this.K[this.A[by][n]] && this.L[this.A[by][n]] <= 40) {
                return false;
            }
            ++n;
        }
        return true;
    }

    public final int x(byte by, byte by2) {
        int n = this.m[by] / 4 * 1000 / 45;
        if (!this.ah(by)) {
            return -2000;
        }
        int n2 = n >= this.u[by] ? this.u[by] - this.u[by2] : n - this.u[by2];
        if (this.i[by2] == 0) {
            n2 -= 2500;
        }
        return n2;
    }

    public final int y(byte by, byte by2) {
        if (this.Z == 1) {
            return this.x(by, by2);
        }
        if (this.Z == 2) {
            return this.z(by, by2);
        }
        return this.A(by, by2);
    }

    public final boolean bx() {
        byte by = 0;
        while (by < this.W) {
            if (this.X[by] == this.as) {
                return true;
            }
            by = (byte)(by + 1);
        }
        return false;
    }

    public final int by() {
        int n = -1001;
        boolean bl = this.bx();
        byte by = 0;
        while (by < this.c[this.as]) {
            byte by2 = this.d[this.as][by];
            if (this.r[by2] > 2) {
                byte by3 = 0;
                while (by3 < this.E[by2]) {
                    int n2;
                    byte by4 = this.D[by2][by3];
                    if (!(this.i[by2] == this.i[by4] || bl && this.i[by4] == this.i[this.ar] || (n2 = this.y(by2, by4)) <= n)) {
                        n = n2;
                        this.au = by2;
                        this.av = by4;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
        if (n >= -1000) {
            this.bP = (byte)3;
        }
        return n;
    }

    public final int bz() {
        if (this.Z == 1) {
            return this.by();
        }
        if (this.Z == 2) {
            return this.cb();
        }
        return this.ci();
    }

    public final void bA() {
        if (this.s() % 3 > 0) {
            return;
        }
        byte by = 0;
        while (by < this.c[this.as]) {
            byte by2 = this.d[this.as][by];
            if (this.u[by2] < this.r[by2] * 1000 && (this.l[by2] >= 50 || this.v[by2] > 0)) {
                byte by3 = 0;
                while (by3 < this.E[by2]) {
                    if (this.i[this.D[by2][by3]] != this.i[by2]) {
                        this.bP = (byte)2;
                        this.au = by2;
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void bB() {
        if (this.Z == 1) {
            this.bA();
            return;
        }
        if (this.Z == 2) {
            this.cc();
            return;
        }
        this.cj();
    }

    public final void bC() {
        int n = 0;
        while (n < this.u[this.au] / 1000) {
            this.J[this.A[this.au][n]] = 1000;
            n = (byte)(n + 1);
        }
        if (n < this.r[this.au]) {
            this.J[this.A[this.au][n]] = (short)(this.u[this.au] % 1000);
        }
    }

    public final void bD() {
        int n = this.r[this.au] * 1000;
        int n2 = n - this.u[this.au];
        int n3 = n2 - this.v[this.au];
        if (n3 <= 0) {
            this.u[this.au] = (short)n;
            byte by = this.au;
            this.v[by] = (short)(this.v[by] - n2);
        } else {
            byte by = this.au;
            this.u[by] = (short)(this.u[by] + this.v[this.au]);
            int n4 = ((n3 - 1) / 100 + 1) * 20;
            if (this.l[this.au] >= n4) {
                byte by2 = this.au;
                this.l[by2] = (short)(this.l[by2] - n4);
                this.u[this.au] = (short)n;
                this.v[this.au] = (short)(n4 * 5 - n3);
            } else {
                this.v[this.au] = 0;
                n4 = this.l[this.au] / 20 * 20;
                byte by3 = this.au;
                this.u[by3] = (short)(this.u[by3] + n4 * 5);
                byte by4 = this.au;
                this.l[by4] = (short)(this.l[by4] - n4);
            }
        }
        this.bC();
    }

    public final boolean ai(byte by) {
        byte by2 = 0;
        while (by2 < this.E[by]) {
            if (this.i[this.D[by][by2]] != this.i[by]) {
                return true;
            }
            by2 = (byte)(by2 + 1);
        }
        return false;
    }

    public final int bE() {
        int n = 0;
        if (this.s() % 3 > 0) {
            return 0;
        }
        byte by = 0;
        while (by < this.c[this.as]) {
            block11: {
                byte by2;
                byte by3;
                byte by4;
                block7: {
                    by4 = this.d[this.as][by];
                    int n2 = 0;
                    by3 = 0;
                    while (by3 < this.E[by4]) {
                        n2 = this.i[this.D[by4][by3]] == this.i[by4] ? (int)((byte)(n2 | 1)) : (int)((byte)(n2 | 2));
                        if (n2 == 3) break;
                        by3 = (byte)(by3 + 1);
                    }
                    if (by3 >= this.E[by4]) break;
                    if (this.r[by4] >= 3) break block7;
                    by3 = 0;
                    while (by3 < this.E[by4]) {
                        block8: {
                            block10: {
                                block9: {
                                    by2 = this.D[by4][by3];
                                    if (this.i[by2] != this.i[by4] || this.r[by2] <= 1) break block8;
                                    if (!this.ai(by2)) break block9;
                                    if (this.r[by2] < 4) break block8;
                                    n = (this.r[by2] - this.r[by4]) / 2;
                                    break block10;
                                }
                                n = this.r[by2] - 1;
                            }
                            this.bP = 1;
                            this.au = by4;
                            this.av = by2;
                            return n;
                        }
                        by3 = (byte)(by3 + 1);
                    }
                    break block11;
                }
                if (this.r[by4] < 6) {
                    by3 = 0;
                    while (by3 < this.E[by4]) {
                        by2 = this.D[by4][by3];
                        if (this.i[by2] == this.i[by4] && this.r[by2] > 2 && !this.ai(by2)) {
                            this.bP = 1;
                            this.au = by4;
                            this.av = by2;
                            n = this.r[by2] - 2;
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

    public final int bF() {
        if (this.Z == 1) {
            return this.bE();
        }
        if (this.Z == 2) {
            return this.cd();
        }
        return this.ck();
    }

    public final void u(int n) {
        int n2 = 0;
        while (n2 < n) {
            byte by = this.av;
            this.r[by] = (byte)(this.r[by] - 1);
            this.A[this.au][this.r[this.au]] = this.A[this.av][this.r[this.av]];
            short s = this.J[this.A[this.au][this.r[this.au]]];
            byte by2 = this.au;
            this.u[by2] = (short)(this.u[by2] + s);
            byte by3 = this.av;
            this.u[by3] = (short)(this.u[by3] - s);
            this.A[this.av][this.r[this.av]] = 0;
            byte by4 = this.au;
            this.r[by4] = (byte)(this.r[by4] + 1);
            if (this.r[this.au] >= 10) {
                return;
            }
            ++n2;
        }
    }

    public final void v(int n) {
        if (this.Z == 1) {
            this.u(n);
            return;
        }
        if (this.Z == 2) {
            this.D(n);
            return;
        }
        this.F(n);
    }

    public final void bG() {
        if (this.s() % 3 > 0) {
            return;
        }
        byte by = 0;
        while (by < this.c[this.as]) {
            byte by2 = this.d[this.as][by];
            if ((this.x[by2] & 4) == 4 && this.l[by2] >= 100 && this.ai(by2)) {
                byte by3 = 0;
                while (by3 < this.r[by2]) {
                    if (this.K[this.A[by2][by3]] - 40 > this.L[this.A[by2][by3]]) {
                        this.bP = (byte)19;
                        this.au = by2;
                        this.ax = this.A[by2][by3];
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void bH() {
        if (this.Z == 1) {
            this.bG();
            return;
        }
        if (this.Z == 2) {
            this.ch();
            return;
        }
        this.cl();
    }

    public final int bI() {
        return 35 + this.s() % 17;
    }

    public final void bJ() {
        short s = this.ax;
        this.L[s] = (byte)(this.L[s] + this.bI());
        if (this.L[this.ax] > this.K[this.ax] || this.L[this.ax] <= 0) {
            this.L[this.ax] = this.K[this.ax];
        }
        byte by = this.au;
        this.l[by] = (short)(this.l[by] - 50);
    }

    public final void bK() {
        if (this.s() % 5 > 0) {
            return;
        }
        byte by = 0;
        while (by < this.c[this.as]) {
            byte by2 = this.d[this.as][by];
            if (this.l[by2] >= 200 || this.w[by2] != 0) {
                byte by3 = 0;
                while (by3 < this.r[by2]) {
                    if (this.P[this.A[by2][by3]] < 50) {
                        this.bP = (byte)8;
                        this.au = by2;
                        this.ax = this.A[by2][by3];
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void bL() {
        if (this.Z == 1) {
            this.bK();
            return;
        }
        if (this.Z == 2) {
            this.ce();
            return;
        }
        this.cm();
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

    public final void bM() {
        if (this.w[this.au] > 0) {
            short s = this.ax;
            this.P[s] = (byte)(this.P[s] + this.w(200));
            byte by = this.au;
            this.w[by] = (byte)(this.w[by] - 1);
        } else {
            short s = this.ax;
            this.P[s] = (byte)(this.P[s] + this.w(100));
            byte by = this.au;
            this.l[by] = (short)(this.l[by] - 100);
        }
        if (this.P[this.ax] > 99 || this.P[this.ax] <= 0) {
            this.P[this.ax] = 99;
        }
    }

    public final int bN() {
        int n = 0;
        if (this.s() % 3 > 0) {
            return 0;
        }
        byte by = 0;
        while (by < this.c[this.as]) {
            block6: {
                byte by2 = this.d[this.as][by];
                if (!this.ai(by2) || this.l[by2] + this.m[by2] >= 400) break block6;
                byte by3 = 0;
                while (by3 < this.E[by2]) {
                    block7: {
                        byte by4;
                        block9: {
                            block8: {
                                by4 = this.D[by2][by3];
                                if (this.b[this.as] != this.i[by4]) break block7;
                                if (!this.ai(by4) || this.l[by4] + this.m[by4] < 1500) break block8;
                                n = 1;
                                break block9;
                            }
                            if (this.ai(by4) || this.l[by4] + this.m[by4] < 800) break block7;
                            n = 0;
                        }
                        this.bP = (byte)12;
                        this.au = by2;
                        this.av = by4;
                        return n;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
        return 0;
    }

    public final int bO() {
        if (this.Z == 1) {
            return this.bN();
        }
        if (this.Z == 2) {
            return this.cf();
        }
        return this.cn();
    }

    public final void x(int n) {
        int n2;
        int n3;
        if (n == 0) {
            n3 = this.l[this.av] - 100;
            n2 = this.m[this.av] - 100;
        } else {
            n3 = this.l[this.av] - (this.l[this.au] + this.l[this.av]) / 2;
            n2 = this.m[this.av] - (this.m[this.au] + this.m[this.av]) / 2;
        }
        if (n3 > 0) {
            byte by = this.au;
            this.l[by] = (short)(this.l[by] + n3);
            byte by2 = this.av;
            this.l[by2] = (short)(this.l[by2] - n3);
        }
        if (n2 >= 0) {
            byte by = this.au;
            this.m[by] = (short)(this.m[by] + n2);
            byte by3 = this.av;
            this.m[by3] = (short)(this.m[by3] - n2);
        }
    }

    public final void b(byte by, short s) {
        if (this.N[by] < 30) {
            byte by2 = by;
            this.l[by2] = (short)(this.l[by2] - 30);
        } else if (this.N[by] < 60) {
            byte by3 = by;
            this.l[by3] = (short)(this.l[by3] - 20);
        } else {
            byte by4 = by;
            this.l[by4] = (short)(this.l[by4] - 10);
        }
        short s2 = s;
        this.N[s2] = (byte)(this.N[s2] + this.n(s));
    }

    public final boolean aj(byte by) {
        byte by2 = 0;
        short s = 0;
        if ((this.x[by] & 8) == 8) {
            if (this.l[by] < 100 && this.m[by] > 500) {
                byte by3 = by;
                this.m[by3] = (short)(this.m[by3] - 200);
                byte by4 = by;
                this.l[by4] = (short)(this.l[by4] + 150);
                return true;
            }
            if (this.m[by] < 200 && this.l[by] > 500) {
                byte by5 = by;
                this.l[by5] = (short)(this.l[by5] - 200);
                byte by6 = by;
                this.m[by6] = (short)(this.m[by6] + 266);
                return true;
            }
        }
        if (this.l[by] < 50 || (this.x[by] & 2) != 2) {
            return false;
        }
        byte by7 = 0;
        while (by7 < this.r[by]) {
            if (by2 < this.M[this.A[by][by7]] && this.N[this.A[by][by7]] < 80) {
                s = this.A[by][by7];
                by2 = this.M[s];
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
        if (this.i[by] == this.b[this.ar]) {
            this.aO = 16;
            this.ay = s2;
            this.N((byte)19);
            this.ax();
        }
        this.f(s, s2);
    }

    public final void b(byte by, byte by2, short s, short s2) {
        if (this.a(by, by2, s, s2) && this.i[by2] == this.b[this.ar]) {
            this.aO = 17;
            this.au = by;
            this.ay = s2;
            this.N((byte)19);
            this.ax();
        }
    }

    public final boolean ak(byte by) {
        byte by2 = 0;
        byte by3 = 100;
        byte by4 = 1;
        while (by4 < 37) {
            by2 = by4;
            if (this.i[by2] != this.i[by]) {
                byte by5 = 0;
                while (by5 < this.r[by2]) {
                    if (by3 > this.P[this.A[by2][by5]]) {
                        this.av = by2;
                        this.ay = this.A[by2][by5];
                        by3 = this.P[this.ay];
                    }
                    by5 = (byte)(by5 + 1);
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (by3 == 100) {
            return false;
        }
        this.ax = this.A[by][0];
        byte by6 = this.N[this.ax];
        by4 = 1;
        while (by4 < this.r[by]) {
            if (by6 < this.N[this.A[by][by4]]) {
                this.ax = this.A[by][by4];
                by6 = this.N[this.ax];
            }
            by4 = (byte)(by4 + 1);
        }
        if (this.r[by] < 10 && (by3 < 15 || by3 < 35 && this.s() % 2 > 0)) {
            this.b(by, this.av, this.ax, this.ay);
        } else {
            this.b(this.av, this.ax, this.ay);
        }
        return true;
    }

    public final boolean j(short s, short s2) {
        byte by = this.O[s];
        byte by2 = this.N[s2] > this.M[s2] ? this.N[s2] : this.M[s2];
        by2 = by2 > this.O[s2] ? by2 : this.O[s2];
        Math.abs(this.ap.nextInt());
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
            this.A[by][this.r[by]] = s2;
            byte by2 = by;
            this.u[by2] = (short)(this.u[by2] + this.J[s2]);
            byte by3 = by;
            this.r[by3] = (byte)(this.r[by3] + 1);
            this.C[by] = this.d(this.C[by], this.t[by], s2);
            byte by4 = by;
            this.t[by4] = (byte)(this.t[by4] - 1);
            return true;
        }
        return false;
    }

    public final int f(short s, byte by) {
        int n;
        byte by2 = this.N[s];
        byte by3 = this.O[s];
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
        this.aO = this.f(s, this.s[by]);
        this.aP = this.aO & 0xFF;
        this.aO >>= 8;
        if (this.aP == 1) {
            byte by2 = by;
            this.m[by2] = (short)(this.m[by2] + this.aO);
            if (this.m[by] > 9999) {
                this.m[by] = 9999;
                return;
            }
        } else if (this.aP == 2) {
            byte by3 = by;
            this.l[by3] = (short)(this.l[by3] + this.aO);
            if (this.l[by] > 9999) {
                this.l[by] = 9999;
                return;
            }
        } else {
            if (this.aP == 3) {
                this.C[by] = this.b(this.C[by], this.t[by], this.B[by][this.aO]);
                this.B[by] = this.d(this.B[by], this.s[by], this.B[by][this.aO]);
                byte by4 = by;
                this.t[by4] = (byte)(this.t[by4] + 1);
                byte by5 = by;
                this.s[by5] = (byte)(this.s[by5] - 1);
                return;
            }
            if (this.aP == 4) {
                byte by6 = by;
                this.w[by6] = (byte)(this.w[by6] + this.aO);
                if (this.w[by] > 99) {
                    this.w[by] = 99;
                }
            }
        }
    }

    public final short al(byte by) {
        short[] sArray = new short[this.r[by]];
        int n = 0;
        byte by2 = 0;
        byte by3 = 0;
        while (by3 < this.r[by]) {
            if (by2 < this.N[this.A[by][by3]]) {
                this.ax = this.A[by][by3];
                by2 = this.N[this.ax];
            }
            if (this.N[this.A[by][by3]] >= 70) {
                int n2 = n;
                n = (byte)(n2 + 1);
                sArray[n2] = this.A[by][by3];
            }
            by3 = (byte)(by3 + 1);
        }
        if (n == 0) {
            return this.ax;
        }
        return sArray[this.s() % n];
    }

    public final void am(byte by) {
        this.ax = this.al(by);
        if (this.r[by] < 10 && this.t[by] > 0) {
            this.ay = this.C[by][this.s() % this.t[by]];
            this.c(by, this.ax, this.ay);
            return;
        }
        this.c(by, this.ax);
    }

    public final int t(short s) {
        byte by = this.N[s];
        int n = 10;
        if (by > 70) {
            n += 8;
        } else if (by > 50) {
            n += 4;
        }
        return n += 2 * (this.s() % 5);
    }

    public final int c(short s, int n) {
        byte by = this.N[s];
        int n2 = 0;
        if (by < 12) {
            return 1;
        }
        int n3 = by * n / 100;
        int n4 = n3 * 7 / 10;
        n2 = n4 + this.s() % (n3 - n4 + 1);
        return n2;
    }

    public final int u(short s) {
        byte by = this.N[s];
        int n = 10;
        if (by > 70) {
            n += 6;
        } else if (by > 50) {
            n += 3;
        }
        return n += this.s() % 4;
    }

    public final int d(short s, int n) {
        byte by = this.N[s];
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
        byte by = this.N[s];
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
        int n = this.t(s);
        this.a(by, s, n);
    }

    public final void e(byte by, short s) {
        int n = this.t(s);
        this.b(by, s, n);
    }

    public final void f(byte by, short s) {
        int n = this.t(s);
        this.d(by, s, n);
    }

    public final void g(byte by, short s) {
        int n = this.u(s);
        this.c(by, s, n);
    }

    public final void an(byte by) {
        if (this.q[by] < 60) {
            this.g(by, this.ax);
            return;
        }
        if (this.s() % 2 > 0) {
            if (this.n[by] < 999 && this.o[by] < 999) {
                if (this.aC < 4 || this.aC >= 10) {
                    this.d(by, this.ax);
                    return;
                }
                this.e(by, this.ax);
                return;
            }
            if (this.n[by] < 999) {
                this.d(by, this.ax);
                return;
            }
            if (this.o[by] < 999) {
                this.e(by, this.ax);
                return;
            }
        }
        if (this.p[by] < 990000) {
            this.f(by, this.ax);
            return;
        }
        this.g(by, this.ax);
    }

    public final void ao(byte by) {
        this.ax = this.al(by);
        this.an(by);
    }

    public final void bP() {
        int n = this.s() % this.c[this.as];
        byte by = this.d[this.as][n];
        int n2 = this.l[by] >= 50 && (this.n[by] < 999 || this.o[by] < 999 || this.p[by] < 990000 || this.q[by] < 99) ? 350 : 150;
        int n3 = 0;
        while (n3 < 10) {
            n = this.s() % n2;
            if (n < 10) {
                if (this.aj(by)) {
                    break;
                }
            } else if (n < 50) {
                if (this.ak(by)) {
                    break;
                }
            } else {
                if (n < 150) {
                    this.am(by);
                    break;
                }
                if (n < 350) {
                    this.ao(by);
                    break;
                }
            }
            n3 = (byte)(n3 + 1);
        }
        if (n3 == 10) {
            this.am(by);
        }
    }

    public final void bQ() {
        if (this.Z == 1) {
            this.bP();
            return;
        }
        if (this.Z == 2) {
            this.cg();
            return;
        }
        this.cr();
    }

    public final boolean k(short s, short s2) {
        if (this.P[s2] > 99) {
            return false;
        }
        int n = (this.O[s] * 7 + this.N[s] * 3) * 10 / 99;
        if ((n = (n - 70) / 5) == 4) {
            n = 6;
        } else if (n == 5) {
            n = 8;
        }
        n = this.L[s2] + this.P[s2] - n + this.ap.nextInt() % 4;
        return n < 100;
    }

    public final int a(short s, short s2, short s3) {
        int n = this.M[s];
        n *= s2;
        if ((n /= s3) > 2 * this.M[s]) {
            n = 2 * this.M[s];
        }
        if (n < this.M[s] / 3) {
            n = this.M[s] / 3;
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
        if (this.cB && this.ay == 4 && s != 4) {
            this.cB = false;
        }
        by = s == this.ax ? (byte)(7 + this.cC) : (byte)(7 - this.cC);
        if (this.s() % 10 < by) {
            return (byte)(this.a(s, s2, s3) / 5);
        }
        return 0;
    }

    public final byte d(short s, short s2, short s3) {
        byte by = 0;
        this.bP = (byte)3;
        if (this.cB && this.ay == 4 && s != 4) {
            this.cB = false;
        }
        by = s == this.ax ? (byte)(2 + this.cC) : (byte)(2 - this.cC);
        if (this.s() % 10 < by) {
            return (byte)(this.a(s, s2, s3) / 2);
        }
        return (byte)(this.ap.nextInt() % 5 - 18);
    }

    public final short e(boolean bl) {
        short s = 0;
        if (bl) {
            int n = 1;
            while (n < 4) {
                s = (short)(s + this.cg[n]);
                n = (byte)(n + 1);
            }
        } else {
            int n = 1;
            while (n < 4) {
                s = (short)(s + this.ch[n]);
                n = (byte)(n + 1);
            }
        }
        return s;
    }

    public final boolean bR() {
        this.aN = 0;
        int n = 229;
        while (n < 232) {
            if (this.F[n] <= 0) break;
            this.V[n] = false;
            byte by = this.aN;
            this.aN = (byte)(by + 1);
            this.aM[by] = n;
            n = (short)(n + 1);
        }
        if (n > 229) {
            this.aL = false;
            this.aH = false;
            return true;
        }
        return false;
    }

    public final void bS() {
        this.aH = true;
        this.aL = false;
        this.aN = 0;
        int n = 229;
        while (n < 232) {
            if (this.F[n] <= 0) {
                return;
            }
            if (this.V[n]) {
                this.V[n] = false;
                byte by = this.aN;
                this.aN = (byte)(by + 1);
                this.aM[by] = n;
            }
            n = (short)(n + 1);
        }
    }

    public final void bT() {
        this.aL = true;
        short s = 0;
        while (s < this.aN) {
            if (this.V[this.aM[s]]) {
                this.az[0] = this.aM[s];
            }
            s = (short)(s + 1);
        }
        this.aA = 1;
        s = 0;
        while (s < this.aN) {
            if (!this.V[this.aM[s]]) {
                byte by = this.aA;
                this.aA = (byte)(by + 1);
                this.az[by] = this.aM[s];
            }
            s = (short)(s + 1);
        }
        this.V[this.az[0]] = false;
    }

    public final void ap(byte by) {
        this.ar = (byte)8;
        this.a[8] = 8;
        this.b[8] = this.az[0];
        this.c[8] = 1;
        this.d[8][0] = by;
        this.i[by] = this.az[0];
        this.j[by] = this.az[0];
        this.r[by] = this.aA;
        this.u[by] = 0;
        byte by2 = 0;
        while (by2 < this.aA) {
            this.P[this.az[by2]] = 99;
            this.A[by][by2] = this.az[by2];
            byte by3 = by;
            this.u[by3] = (short)(this.u[by3] + this.J[this.az[by2]]);
            by2 = (byte)(by2 + 1);
        }
        this.P[this.az[0]] = 100;
    }

    public final void bU() {
        int n = 229;
        int n2 = 229;
        while (n2 < 232) {
            if (this.F[n2] <= 0) break;
            if (this.V[n2]) {
                n = n2;
                this.F[n2] = 0;
                this.V[n2] = false;
                break;
            }
            n2 = (short)(n2 + 1);
        }
        n2 = (short)(n + 1);
        while (n2 < 232) {
            if (this.F[n2] <= 0) break;
            if (this.V[n2]) {
                this.F[n2] = 0;
                this.V[n2] = false;
            } else {
                this.F[n] = this.F[n2];
                this.G[n] = this.G[n2];
                this.H[n] = this.H[n2];
                this.I[n] = this.I[n2];
                this.J[n] = this.J[n2];
                this.K[n] = this.K[n2];
                this.L[n] = this.L[n2];
                this.M[n] = this.M[n2];
                this.N[n] = this.N[n2];
                this.O[n] = this.O[n2];
                this.P[n] = this.P[n2];
                this.Q[n] = this.Q[n2];
                this.R[n] = this.R[n2];
                this.S[n] = this.S[n2];
                this.cY[n - 229] = this.cY[n2 - 229];
                n = (short)(n + 1);
            }
            n2 = (short)(n2 + 1);
        }
        n2 = n;
        while (n2 < 232) {
            this.F[n2] = 0;
            n2 = (short)(n2 + 1);
        }
    }

    public final void bV() {
        block11: {
            short s = 0;
            s = this.a(this.cZ, (short)0);
            RecordStore recordStore = null;
            try {
                recordStore = RecordStore.openRecordStore((String)"newPs", (boolean)true, (int)1, (boolean)true);
                if (recordStore.getNumRecords() > 0) {
                    recordStore.setRecord(1, this.cZ, 0, (int)s);
                } else {
                    recordStore.addRecord(this.cZ, 0, (int)s);
                }
                try {
                    int n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        ++n;
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
                return;
            }
            catch (Exception exception) {
                if (recordStore == null) break block11;
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

    public final boolean bW() {
        if (!this.a(this.bb, this.ba)) {
            return false;
        }
        short s = this.G();
        short s2 = this.H();
        if (s2 < s / 4) {
            return true;
        }
        return this.J[this.bh[0]] < 100 && this.L[this.bh[0]] < 40;
    }

    public final int c(int n, int n2) {
        byte by = this.d(this.bh[n]);
        byte[] byArray = new byte[16];
        int n3 = 0;
        byte by2 = 0;
        while (by2 < by) {
            if (by2 != 8 && by2 != 11 && (this.J[this.bg[n2]] != 0 || by2 != 0 && by2 != 3 && by2 != 4) && this.N[this.bh[n]] > this.N[this.bg[n2]] && this.a(n, n2, by2, false) && this.g(by2) <= this.bl) {
                byArray[n3] = by2;
                n3 = (byte)(n3 + 1);
            }
            by2 = (byte)(by2 + 1);
        }
        if (n3 == 0) {
            return -1;
        }
        return byArray[this.s() % n3];
    }

    public final byte c(short s, byte[] byArray, byte by, byte by2) {
        byte[] byArray2 = new byte[by];
        int n = 0;
        byte by3 = 0;
        while (by3 < by) {
            if (this.i[byArray[by3]] == s) {
                int n2 = n;
                n = (byte)(n2 + 1);
                byArray2[n2] = byArray[by3];
            }
            by3 = (byte)(by3 + 1);
        }
        if (n > 0) {
            return byArray2[this.s() % n];
        }
        if (by > 0) {
            return byArray[this.s() % by];
        }
        return -1;
    }

    public final byte b(byte by, byte by2, byte by3) {
        int n = 0;
        if (by2 - by > 6) {
            n = by - 1;
            if (n < 0) {
                n = 0;
            }
        } else if (by2 - by >= 0) {
            n = (by + by2 + 1) / 2 - 4;
            if (n < 0) {
                n = 0;
            } else if (n + 7 >= by3) {
                n = by3 - 8;
            }
        } else if (by2 - by > -7) {
            n = (by + by2) / 2 - 3;
            if (n < 0) {
                n = 0;
            } else if (n + 7 >= by3) {
                n = by3 - 8;
            }
        } else {
            n = by - 6;
            if (n + 7 >= by3) {
                n = by3 - 8;
            }
        }
        return (byte)n;
    }

    public final void a(byte by, byte by2, int n, byte[][] byArray, short[][] sArray) {
        byte by3 = (byte)(by * 8 + by2);
        byte by4 = (byte)n;
        if (by2 > 0) {
            sArray[by3 - 1][0] = by4;
            byArray[by3 - 1][0] = by3;
        } else {
            sArray[by3][1] = 255;
            byArray[by3][1] = by3;
        }
        if (by2 < 7) {
            sArray[by3 + 1][1] = by4;
            byArray[by3 + 1][1] = by3;
        } else {
            sArray[by3][0] = 255;
            byArray[by3][0] = by3;
        }
        if (by > 0) {
            sArray[by3 - 8][2] = by4;
            byArray[by3 - 8][2] = by3;
        } else {
            sArray[by3][3] = 255;
            byArray[by3][3] = by3;
        }
        if (by < 7) {
            sArray[by3 + 8][3] = by4;
            byArray[by3 + 8][3] = by3;
            return;
        }
        sArray[by3][2] = 255;
        byArray[by3][2] = by3;
    }

    public final void a(byte by, byte by2, byte by3, byte[][] byArray, short[][] sArray) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        switch (by) {
            case 1: {
                n = 2;
                n2 = 5;
                n3 = 5;
                break;
            }
            case 3: {
                n = 3;
                n2 = 3;
                n3 = 6;
                break;
            }
            case 2: {
                n = 3;
                n2 = 6;
                n3 = 3;
            }
        }
        byte by4 = 0;
        while (by4 < 8) {
            byte by5 = 0;
            while (by5 < 8) {
                byte by6 = this.bm[by4 + by3][by5 + by2];
                if ((by6 & 0xC0) != 0) {
                    this.a(by4, by5, 100, byArray, sArray);
                } else {
                    by6 = (byte)(by6 & 0x1F);
                    switch (by6) {
                        case 0: 
                        case 1: 
                        case 3: 
                        case 4: {
                            this.a(by4, by5, n, byArray, sArray);
                            break;
                        }
                        case 7: 
                        case 8: 
                        case 9: 
                        case 10: 
                        case 11: 
                        case 12: {
                            this.a(by4, by5, n2, byArray, sArray);
                            break;
                        }
                        case 6: {
                            this.a(by4, by5, n3, byArray, sArray);
                            break;
                        }
                        case 5: {
                            this.a(by4, by5, 5, byArray, sArray);
                            break;
                        }
                        case 2: {
                            this.a(by4, by5, 6, byArray, sArray);
                            break;
                        }
                        default: {
                            this.a(by4, by5, 100, byArray, sArray);
                        }
                    }
                }
                by5 = (byte)(by5 + 1);
            }
            by4 = (byte)(by4 + 1);
        }
    }

    public final byte a(int n, int n2, byte by, byte by2) {
        byte by3 = 0;
        byte by4 = 0;
        byte by5 = (byte)(this.bq[n2] - this.bs[n]);
        byte by6 = (byte)(this.br[n2] - this.bt[n]);
        if (Math.abs(by5) > 6 || Math.abs(by6) > 6) {
            if (Math.abs(by5) >= Math.abs(by6)) {
                if (by5 > 0) {
                    by3 = (byte)(this.bs[n] + 6);
                    by4 = (byte)(this.bt[n] + 6 * by6 / by5);
                } else if (by5 < 0) {
                    by3 = (byte)(this.bs[n] - 6);
                    by4 = (byte)(this.bt[n] - 6 * by6 / by5);
                }
            } else if (by6 > 0) {
                by4 = (byte)(this.bt[n] + 6);
                by3 = (byte)(this.bs[n] + 6 * by5 / by6);
            } else {
                by4 = (byte)(this.bt[n] - 6);
                by3 = (byte)(this.bs[n] - 6 * by5 / by6);
            }
        } else {
            by3 = this.bq[n2];
            by4 = this.br[n2];
        }
        byte by7 = (byte)(by3 - by + (by4 - by2) * 8);
        return by7;
    }

    public final void a(short[] sArray, byte[] byArray, byte[][] byArray2, short[][] sArray2, byte by, byte by2) {
        int n = 0;
        while (n < 4) {
            short s;
            byte by3 = byArray2[by2][n];
            if ((sArray[by3] & 0x100) == 0 && sArray[by3] > (s = (short)(sArray[by2] & 0xFF)) + sArray2[by2][n]) {
                byArray[by3] = by2;
                sArray[by3] = (short)(s + sArray2[by2][n]);
            }
            ++n;
        }
    }

    public final void a(short[] sArray, byte[] byArray, byte[][] byArray2, short[][] sArray2, byte by, byte by2, byte[][] byArray3, byte[] byArray4, byte by3) {
        byte by4 = 0;
        int n = 0;
        while (n < 4) {
            by4 = byArray2[by][n];
            sArray[by4] = sArray2[by][n];
            byArray[by4] = by;
            ++n;
        }
        byte by5 = by;
        sArray[by5] = (short)(sArray[by5] | 0x100);
        n = 0;
        while (n < 63) {
            byte by6;
            byte by7 = by6 = this.a(sArray, byArray, by);
            sArray[by7] = (short)(sArray[by7] | 0x100);
            this.a(sArray, byArray, byArray2, sArray2, by, by6);
            ++n;
        }
        byArray4[by3] = 0;
        by4 = by2;
        n = 0;
        while (n < 63) {
            by4 = byArray[by4];
            byte by8 = by3;
            byArray4[by8] = (byte)(byArray4[by8] + 1);
            if (by4 == by) break;
            ++n;
        }
        byArray3[by3] = new byte[byArray4[by3]];
        by4 = by2;
        n = 0;
        while (n < byArray4[by3]) {
            byte by9 = by4;
            if (by9 == (by4 = byArray[by4]) - 8) {
                byArray3[by3][n] = 1;
            } else if (by9 == by4 + 8) {
                byArray3[by3][n] = 2;
            } else if (by9 == by4 - 1) {
                byArray3[by3][n] = 3;
            } else if (by9 == by4 + 1) {
                byArray3[by3][n] = 4;
            }
            ++n;
        }
    }

    public final short[] a(byte by, byte by2, byte[][] byArray, byte[] byArray2) {
        byte[][] byArray3 = new byte[64][];
        short[][] sArray = new short[64][];
        short[] sArray2 = new short[64];
        byte[] byArray4 = new byte[64];
        int n = 0;
        while (n < 64) {
            byArray3[n] = new byte[4];
            sArray[n] = new short[4];
            n = (byte)(n + 1);
        }
        byte by3 = this.b(this.bs[by], this.bq[by2], this.bn);
        byte by4 = this.b(this.bt[by], this.br[by2], this.bo);
        this.a(this.I[this.bh[by]], by3, by4, byArray3, sArray);
        byte by5 = (byte)(this.bs[by] - by3 + (this.bt[by] - by4) * 8);
        byte by6 = this.a((int)by, (int)by2, by3, by4);
        n = 0;
        while (n < 64) {
            sArray2[n] = 255;
            byArray4[n] = by5;
            n = (byte)(n + 1);
        }
        this.a(sArray2, byArray4, byArray3, sArray, by5, by6, byArray, byArray2, by);
        return sArray2;
    }

    public final void c(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        this.cM[by] = by2;
        this.a(by, by2, byArray2, byArray3);
    }

    public final boolean y(int n) {
        short s;
        if (!this.a(this.bb, this.ba)) {
            return false;
        }
        if (this.J[this.bh[n]] >= 400) {
            return false;
        }
        short s2 = this.M();
        return s2 * 2 / 33 > (s = this.J[this.bh[n]]);
    }

    public final void bX() {
        byte by = -1;
        byte[] byArray = new byte[this.bj];
        byte[][] byArray2 = new byte[this.bj][];
        byte[] byArray3 = new byte[this.bj];
        while (by != this.bl && this.bl >= 2) {
            System.gc();
            by = this.bl;
            byte by2 = 0;
            while (by2 < this.bj) {
                byArray[by2] = 0;
                byArray3[by2] = 0;
                by2 = (byte)(by2 + 1);
            }
            this.a(byArray, byArray2, byArray3);
            if (byArray[0] == 3) {
                this.ao.W = (byte)5;
                this.E();
                return;
            }
            by2 = 1;
            while (by2 < this.bj) {
                if ((this.bv[by2] & 0x1F) == 0) {
                    if (this.y((int)by2)) {
                        byArray[by2] = 3;
                    } else if (this.M[this.bg[0]] <= this.M[this.bh[by2]] && this.J[this.bg[0]] <= this.J[this.bh[by2]] && this.N[this.bg[0]] < this.N[this.bh[by2]]) {
                        this.b(by2, byArray, byArray2, byArray3);
                    } else {
                        this.c(by2, byArray, byArray2, byArray3);
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            by2 = this.bF;
            while (by2 < this.bj) {
                System.gc();
                if ((this.bv[by2] & 0x1F) == 0) {
                    this.d(by2, byArray[by2], byArray2[by2], byArray3);
                    if (this.aq > 0) {
                        this.bF = (byte)(by2 + 1);
                        return;
                    }
                }
                by2 = (byte)(by2 + 1);
            }
        }
        System.gc();
    }

    public final void c(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.I()) {
            byArray[0] = 3;
            return;
        }
        this.a((byte)0, byArray, byArray2, byArray3);
        if (byArray3[0] > 0) {
            byArray3[0] = 2;
            return;
        }
        this.b((byte)0, byArray, byArray2, byArray3);
    }

    public final void bY() {
        byte by = -1;
        byte[] byArray = new byte[this.bj];
        byte[][] byArray2 = new byte[this.bj][];
        byte[] byArray3 = new byte[this.bj];
        ++this.cN;
        while (by != this.bl && this.bl >= 2) {
            System.gc();
            by = this.bl;
            byte by2 = 0;
            while (by2 < this.bj) {
                byArray[by2] = 0;
                byArray3[by2] = 0;
                by2 = (byte)(by2 + 1);
            }
            if (this.cN < 4) {
                this.a(byArray, byArray2, byArray3);
            } else {
                this.c(byArray, byArray2, byArray3);
            }
            if (byArray[0] == 3) {
                this.ao.W = (byte)5;
                this.E();
                return;
            }
            by2 = 1;
            while (by2 < this.bj) {
                if ((by2 != 0 || this.cN >= 4) && (this.bv[by2] & 0x1F) == 0) {
                    if (by2 != 0 && this.y((int)by2)) {
                        byArray[by2] = 3;
                    } else {
                        this.a(by2, byArray, byArray2, byArray3);
                        if (byArray3[by2] > 0) {
                            byArray[by2] = 2;
                        } else {
                            this.b(by2, byArray, byArray2, byArray3);
                        }
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            by2 = 0;
            while (by2 < this.bj) {
                System.gc();
                if ((this.bv[by2] & 0x1F) == 0) {
                    this.d(by2, byArray[by2], byArray2[by2], byArray3);
                    if (this.k(by2)) {
                        return;
                    }
                    if (this.aq > 0) {
                        this.bF = (byte)(by2 + 1);
                        return;
                    }
                }
                by2 = (byte)(by2 + 1);
            }
            System.gc();
        }
    }

    public final void bZ() {
        byte by;
        byte by2;
        if (this.bO < 7) {
            return;
        }
        if (this.bO < 8) {
            byte by3 = 1;
            while (by3 < this.bJ) {
                if (this.cb[1][by3] && this.bU[1][by3] == 2) {
                    int n = 5;
                    while (n < 7) {
                        byte by4 = (byte)(this.bS[1][by3] - n);
                        byte by5 = this.bT[1][by3];
                        if (by4 < 0) break;
                        if ((this.bK[by5][by4] & 0x40) != 0) {
                            byte by6 = 1;
                            while (by6 < this.bI) {
                                if (this.cb[0][by6] && this.bS[0][by6] == by4 && this.bT[0][by6] == by5) {
                                    this.bO = (byte)(this.bO - 7);
                                    this.ce = (byte)51;
                                    return;
                                }
                                by6 = (byte)(by6 + 1);
                            }
                        }
                        n = (byte)(n + 1);
                    }
                }
                by3 = (byte)(by3 + 1);
            }
            return;
        }
        byte by7 = 1;
        while (by7 < this.bJ) {
            if (this.cb[1][by7] && this.bU[1][by7] == 2) {
                int n = 5;
                while (n < 7) {
                    by2 = (byte)(this.bS[1][by7] - n);
                    by = this.bT[1][by7];
                    if (by2 < 0) break;
                    if ((this.bK[by][by2] & 0x40) != 0) {
                        byte by8 = 1;
                        while (by8 < this.bI) {
                            if (this.cb[0][by8] && this.bS[0][by8] == by2 && this.bT[0][by8] == by && this.bU[0][by8] == 2) {
                                this.bO = (byte)(this.bO - 7);
                                this.ce = (byte)51;
                                return;
                            }
                            by8 = (byte)(by8 + 1);
                        }
                    }
                    n = (byte)(n + 1);
                }
            }
            by7 = (byte)(by7 + 1);
        }
        by7 = 1;
        while (by7 < this.bJ) {
            if (this.cb[1][by7]) {
                by2 = this.bS[1][by7];
                by = this.bT[1][by7];
                if (by > 0 && (this.bK[by - 1][by2] & 0x40) != 0 || by < 6 && (this.bK[by + 1][by2] & 0x40) != 0 || by2 > 0 && (this.bK[by][by2 - 1] & 0x40) != 0 || by2 < 15 && (this.bK[by][by2 + 1] & 0x40) != 0) {
                    this.bO = (byte)(this.bO - 8);
                    this.ce = (byte)68;
                    return;
                }
            }
            by7 = (byte)(by7 + 1);
        }
    }

    public final void z(int n) {
        if (this.U() && n >= 4) {
            byte by = 0;
            while (by < this.bJ) {
                this.bY[1][by] = 2;
                by = (byte)(by + 1);
            }
            return;
        }
        this.bZ();
        byte by = 0;
        while (by < this.bJ) {
            this.bY[1][by] = 1;
            by = (byte)(by + 1);
        }
    }

    public final void A(int n) {
        if (this.U() && n >= 4) {
            byte by = 0;
            while (by < this.bJ) {
                this.bY[1][by] = 2;
                by = (byte)(by + 1);
            }
            return;
        }
        this.bZ();
        this.bY[1][0] = 1;
        byte by = 1;
        while (by < this.bJ) {
            this.bY[1][by] = 0;
            by = (byte)(by + 1);
        }
    }

    public final void ca() {
        byte by = 1;
        while (by < this.bI) {
            if (this.cb[0][by]) {
                byte by2 = this.bS[0][by];
                byte by3 = this.bT[0][by];
                byte by4 = 1;
                while (by4 < 5) {
                    byte by5;
                    if (by3 >= by4 && (this.bK[by3 - by4][by2] & 0x80) != 0) {
                        by5 = 1;
                        while (by5 < this.bJ) {
                            if (this.cb[1][by5] && this.bS[1][by5] == by2 && this.bT[1][by5] == by3 - by4) {
                                if (this.bU[1][by5] == 2) break;
                                this.cw = true;
                                return;
                            }
                            by5 = (byte)(by5 + 1);
                        }
                    }
                    if (by3 + by4 <= 6 && (this.bK[by3 + by4][by2] & 0x80) != 0) {
                        by5 = 1;
                        while (by5 < this.bJ) {
                            if (this.cb[1][by5] && this.bS[1][by5] == by2 && this.bT[1][by5] == by3 + by4) {
                                if (this.bU[1][by5] == 2) break;
                                this.cw = true;
                                return;
                            }
                            by5 = (byte)(by5 + 1);
                        }
                    }
                    if (by2 >= by4 && (this.bK[by3][by2 - by4] & 0x80) != 0) {
                        by5 = 1;
                        while (by5 < this.bJ) {
                            if (this.cb[1][by5] && this.bS[1][by5] == by2 - by4 && this.bT[1][by5] == by3) {
                                if (this.bU[1][by5] == 2) break;
                                this.cw = true;
                                return;
                            }
                            by5 = (byte)(by5 + 1);
                        }
                    }
                    if (by2 + by4 <= 15 && (this.bK[by3][by2 + by4] & 0x80) != 0) {
                        by5 = 1;
                        while (by5 < this.bJ) {
                            if (this.cb[1][by5] && this.bS[1][by5] == by2 + by4 && this.bT[1][by5] == by3) {
                                if (this.bU[1][by5] == 2) break;
                                this.cw = true;
                                return;
                            }
                            by5 = (byte)(by5 + 1);
                        }
                    }
                    by4 = (byte)(by4 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final void B(int n) {
        if (this.U() && n >= 4) {
            byte by = 0;
            while (by < this.bJ) {
                this.bY[1][by] = 2;
                by = (byte)(by + 1);
            }
            return;
        }
        short s = this.e(true);
        short s2 = this.e(false);
        this.bZ();
        if (!this.cw) {
            byte by = 0;
            while (by < this.bJ) {
                this.bY[1][by] = 1;
                by = (byte)(by + 1);
            }
            if (s2 > s) {
                this.cw = true;
            } else {
                this.ca();
            }
            if (this.cw) {
                by = 1;
                while (by < this.bJ) {
                    this.bY[1][by] = 0;
                    by = (byte)(by + 1);
                }
            }
        }
    }

    public final byte l(short s, short s2) {
        if (this.L[s] < 20 && this.s() % 2 == 0) {
            return 4;
        }
        if (this.L[s2] < this.K[s2] / 2 && this.L[s] - this.L[s2] > 10) {
            return 0;
        }
        return 1;
    }

    public final void aq(byte by) {
        int n = -1;
        short s = 0;
        byte by2 = 0;
        int n2 = 0;
        while (n2 < this.e[by]) {
            int n3 = 0;
            while (n3 < this.c[by]) {
                byte by3 = this.d[by][n3];
                int n4 = 0;
                while (n4 < this.r[by3]) {
                    short s2 = this.A[by3][n4];
                    if (this.N[s2] + this.M[s2] + this.O[s2] > n) {
                        n = this.N[s2] + this.M[s2] + this.O[s2];
                        s = s2;
                        by2 = by3;
                    }
                    if (s2 == this.f[by][n2]) {
                        this.a(by, by3, s2);
                        return;
                    }
                    ++n4;
                }
                ++n3;
            }
            ++n2;
        }
        this.a(by, by2, s);
    }

    public final int C(int n) {
        int n2;
        switch (n /= 1000) {
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
        if (this.s() % 100 > n2) {
            this.bP = (byte)-1;
        }
        return n;
    }

    public final void c(byte by, short[] sArray, byte by2) {
        byte by3 = 0;
        short s = sArray[0];
        byte by4 = 1;
        while (by4 < by2) {
            if (this.P[s] < this.P[sArray[by4]]) {
                s = sArray[by4];
                by3 = by4;
            }
            by4 = (byte)(by4 + 1);
        }
        this.j[by] = s;
        by4 = by3;
        while (by4 > 0) {
            sArray[by4] = sArray[by4 - 1];
            by4 = (byte)(by4 - 1);
        }
        sArray[0] = s;
    }

    public final void ar(byte by) {
        if (this.A[by][0] == 0) {
            this.a(by, this.A[by], (int)this.r[by]);
            this.b(by, this.A[by], this.r[by]);
            return;
        }
        this.a(by, this.A[by], (int)this.r[by]);
    }

    public final void e(short[] sArray, byte by) {
        short s = sArray[0];
        byte by2 = -1;
        int n = 0;
        byte by3 = 0;
        while (by3 < by) {
            if (sArray[by3] == this.i[this.au]) {
                s = sArray[0];
                sArray[0] = sArray[by3];
                sArray[by3] = s;
                return;
            }
            if (this.N[sArray[by3]] > by2) {
                s = sArray[by3];
                n = by3;
                by2 = this.N[s];
            }
            by3 = (byte)(by3 + 1);
        }
        sArray[n] = sArray[0];
        sArray[0] = s;
    }

    public final byte e(short[] sArray, int n) {
        byte[] byArray = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        byte by = this.r[this.au];
        int n2 = -1 * (this.u[this.av] + this.r[this.av] * 200);
        byte by2 = 0;
        byte by3 = 0;
        n = this.s() % n;
        n *= 1000;
        while (by > 1) {
            int n3 = 0;
            int n4 = this.s() % by + 1;
            while ((n3 += byArray[by3]) != n4) {
                if (++by3 != this.r[this.au]) continue;
                by3 = 0;
            }
            sArray[by2] = this.A[this.au][by3];
            this.A[this.au][by3] = 0;
            n2 += this.J[sArray[by2]];
            by2 = (byte)(by2 + 1);
            byArray[by3] = 0;
            by = (byte)(by - 1);
            if ((n2 += 200) >= n) break;
        }
        this.r[this.au] = by;
        this.ar(this.au);
        this.e(sArray, by2);
        return by2;
    }

    public final void as(byte by) {
        this.aV = new byte[36];
        this.aW = new byte[36];
        this.aX = 0;
        this.aY = 0;
        byte by2 = 0;
        while (by2 < this.c[by]) {
            if (this.ai(this.d[by][by2])) {
                this.aV[this.aX] = this.d[by][by2];
                this.aX = (byte)(this.aX + 1);
            } else {
                this.aW[this.aY] = this.d[by][by2];
                this.aY = (byte)(this.aY + 1);
            }
            by2 = (byte)(by2 + 1);
        }
    }

    public final int z(byte by, byte by2) {
        int n = this.m[by] / 4 * 1000 / 45;
        int n2 = n >= this.u[by] ? this.u[by] - this.u[by2] : n - this.u[by2];
        return n2 += (this.r[by] - this.r[by2]) * 200;
    }

    public final int cb() {
        int n = 999;
        boolean bl = this.bx();
        this.bP = (byte)-1;
        byte by = 0;
        while (by < this.aX) {
            byte by2 = this.aV[by];
            if (this.r[by2] > 2) {
                byte by3 = 0;
                while (by3 < this.E[by2]) {
                    int n2;
                    byte by4 = this.D[by2][by3];
                    if (!(this.i[by2] == this.i[by4] || bl && this.i[by4] == this.b[this.ar] || (n2 = this.y(by2, by4)) <= n)) {
                        n = n2;
                        this.au = by2;
                        this.av = by4;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
        if (n >= 1000) {
            this.bP = (byte)3;
        }
        return n;
    }

    public final void cc() {
        int n = 0;
        if (this.s() % 3 > 1) {
            return;
        }
        byte by = 0;
        while (by < this.aX) {
            byte by2 = this.aV[by];
            int n2 = this.r[by2] * 1000 - this.u[by2];
            if (n2 > 0 && (this.l[by2] >= 50 || this.v[by2] > 0)) {
                int n3 = (this.l[by2] - 50) * 5;
                if (n2 > n3) {
                    n2 = n3;
                }
                if (n2 >= n) {
                    this.au = by2;
                    n = n2;
                }
            }
            by = (byte)(by + 1);
        }
        if (n > 0) {
            this.bP = (byte)2;
        }
    }

    public final int at(byte by) {
        int n = -20000;
        byte by2 = 0;
        while (by2 < this.E[by]) {
            byte by3 = this.D[by][by2];
            int n2 = this.u[by3] + this.r[by3] * 200 - this.u[by] - this.r[by] * 200;
            if (n < n2) {
                n = n2;
            }
            by2 = (byte)(by2 + 1);
        }
        return n;
    }

    public final int d(byte by, int n) {
        byte by2 = 1;
        int n2 = n - 1999;
        int n3 = 0;
        byte by3 = 0;
        while (by3 < this.E[by]) {
            byte by4 = this.D[by][by3];
            if (this.i[by4] == this.i[by] && this.r[by4] > 1) {
                if (!this.ai(by4)) {
                    if (this.r[by4] > by2) {
                        this.av = by4;
                        by2 = this.r[by4];
                    }
                } else if (by2 == 1 && n > 0 && (n3 = this.at(by4)) < n2) {
                    this.av = by4;
                    n2 = n3;
                }
            }
            by3 = (byte)(by3 + 1);
        }
        if (by2 > 1) {
            return by2 - 1;
        }
        if (n2 < n - 1999) {
            n3 = (n - n2) / 2;
            if (n3 > n) {
                return n;
            }
            return n3;
        }
        return 0;
    }

    public final int cd() {
        int n = -20000;
        int n2 = 0;
        int n3 = 0;
        this.au = 0;
        this.av = 0;
        if (this.s() % 3 > 1) {
            return 0;
        }
        byte by = 0;
        while (by < this.aX) {
            int n4;
            byte by2 = this.aV[by];
            if (this.r[by2] != 10 && (n4 = this.at(by2)) > n && (n3 = this.d(by2, n4)) != 0) {
                this.au = by2;
                this.bP = 1;
                n = n4;
                n2 = n3;
            }
            by = (byte)(by + 1);
        }
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void D(int n) {
        if (n < 50) {
            try {
                int n2 = 0;
                while (true) {
                    if (n2 >= n) {
                        return;
                    }
                    byte by = this.av;
                    this.r[by] = (byte)(this.r[by] - 1);
                    this.A[this.au][this.r[this.au]] = this.A[this.av][this.r[this.av]];
                    short s = this.J[this.A[this.au][this.r[this.au]]];
                    byte by2 = this.au;
                    this.u[by2] = (short)(this.u[by2] + s);
                    byte by3 = this.av;
                    this.u[by3] = (short)(this.u[by3] - s);
                    this.A[this.av][this.r[this.av]] = 0;
                    byte by4 = this.au;
                    this.r[by4] = (byte)(this.r[by4] + 1);
                    if (this.r[this.au] >= 10) {
                        return;
                    }
                    ++n2;
                }
            }
            catch (Exception exception) {
                System.out.println("move error" + exception.toString());
                return;
            }
        }
        try {
            int n3 = 0;
            while (n3 < n) {
                if (this.r[this.av] <= 1) return;
                if (this.r[this.au] >= 10) {
                    return;
                }
                byte by = this.av;
                this.r[by] = (byte)(this.r[by] - 1);
                this.A[this.au][this.r[this.au]] = this.A[this.av][this.r[this.av]];
                short s = this.J[this.A[this.au][this.r[this.au]]];
                byte by5 = this.au;
                this.u[by5] = (short)(this.u[by5] + s);
                byte by6 = this.av;
                this.u[by6] = (short)(this.u[by6] - s);
                n3 += 200 + s;
                this.A[this.av][this.r[this.av]] = 0;
                byte by7 = this.au;
                this.r[by7] = (byte)(this.r[by7] + 1);
            }
            return;
        }
        catch (Exception exception) {
            System.out.println("move error2" + exception.toString());
            try {
                this.wait();
                return;
            }
            catch (Exception exception2) {}
        }
    }

    public final void ce() {
        byte by = 100;
        if (this.s() % 2 > 0) {
            return;
        }
        byte by2 = 0;
        while (by2 < this.c[this.as]) {
            byte by3 = this.d[this.as][by2];
            if (this.l[by3] >= 200 || this.w[by3] != 0) {
                byte by4 = 0;
                while (by4 < this.r[by3]) {
                    if (this.P[this.A[by3][by4]] < by) {
                        this.au = by3;
                        this.ax = this.A[by3][by4];
                        by = this.P[this.A[by3][by4]];
                    }
                    by4 = (byte)(by4 + 1);
                }
            }
            by2 = (byte)(by2 + 1);
        }
        if (by >= 70 || by > 35 && this.s() % 2 > 0) {
            return;
        }
        this.bP = (byte)8;
    }

    public final int au(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        short s = -1;
        short s2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.i[by5] == this.i[by]) {
                if (!this.ai(by5)) {
                    if (this.m[by5] > s) {
                        s = this.m[by5];
                        by2 = by5;
                    }
                } else if (s < 1000 && this.m[by5] > s2) {
                    s2 = this.m[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (s >= 1000) {
            this.av = by2;
            return 0;
        }
        if (s2 - s >= 500 && s2 >= 600) {
            this.av = by3;
            return 1;
        }
        if (s >= 400) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int av(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        short s = -1;
        short s2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.i[by5] == this.i[by]) {
                if (!this.ai(by5)) {
                    if (this.l[by5] > s) {
                        s = this.l[by5];
                        by2 = by5;
                    }
                } else if (s < 1000 && this.l[by5] > s2) {
                    s2 = this.l[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (s >= 1000) {
            this.av = by2;
            return 0;
        }
        if (s2 - s >= 500 && s2 >= 600) {
            this.av = by3;
            return 1;
        }
        if (s >= 400) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int aw(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        int n = -1;
        int n2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.i[by5] == this.i[by]) {
                if (!this.ai(by5)) {
                    if (this.l[by5] + this.m[by5] > n) {
                        n = this.l[by5] + this.m[by5];
                        by2 = by5;
                    }
                } else if (n < 1500 && this.l[by5] + this.m[by5] > n2) {
                    n2 = this.l[by5] + this.m[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (n >= 1500) {
            this.av = by2;
            return 0;
        }
        if (n2 - n >= 800 && n2 >= 1500) {
            this.av = by3;
            return 1;
        }
        if (n >= 800) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int ax(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        int n = -1;
        int n2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.i[by5] == this.i[by]) {
                if (!this.ai(by5)) {
                    if (this.l[by5] + this.m[by5] > n) {
                        n = this.l[by5] + this.m[by5];
                        by2 = by5;
                    }
                } else if (n < 2000 && this.l[by5] + this.m[by5] > n2) {
                    n2 = this.l[by5] + this.m[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (n >= 2000) {
            this.av = by2;
            return 0;
        }
        if (n2 - this.l[by] - this.m[by] >= 1000 && n2 >= 3000) {
            this.av = by3;
            return 1;
        }
        if (n >= 1000) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int cf() {
        short s = 0;
        short s2 = 10000;
        int n = 0;
        this.au = 0;
        this.av = 0;
        if (this.s() % 2 > 1) {
            return 0;
        }
        byte by = 0;
        while (by < this.aX) {
            short s3;
            byte by2 = this.aV[by];
            if (this.m[by2] < 200 && this.m[by2] < s2 && (s3 = this.au(by2)) >= 0) {
                this.au = by2;
                s2 = this.m[by2];
                n = 4;
                s = s3;
            } else if (n < 4 && this.l[by2] < 200 && this.l[by2] < s2 && (s3 = this.av(by2)) >= 0) {
                this.au = by2;
                s2 = this.l[by2];
                n = 3;
                s = s3;
            } else {
                s3 = this.l[by2] + this.m[by2];
                if (s3 < s2) {
                    if (n < 3 && s3 < 1000 && (s3 = this.aw(by2)) >= 0) {
                        this.au = by2;
                        s2 = (short)(this.l[by2] + this.m[by2]);
                        n = 2;
                        s = s3;
                    } else {
                        s3 = this.l[by2] + this.m[by2];
                        if (n < 2 && s3 < 4000 && (s3 = this.ax(by2)) >= 0) {
                            this.au = by2;
                            s2 = (short)(this.l[by2] + this.m[by2]);
                            n = 1;
                            s = s3;
                        }
                    }
                }
            }
            by = (byte)(by + 1);
        }
        if (n > 2 || n == 2 && this.s() % 2 > 0 || n == 1 && this.s() % 5 > 3) {
            this.bP = (byte)12;
        }
        return s;
    }

    public final void cg() {
        int n = 0;
        int n2 = 0;
        byte by = 0;
        try {
            by = this.s() % 10 > 6 && this.aY > 0 || this.aX <= 0 ? this.aW[this.s() % this.aY] : this.aV[this.s() % this.aX];
            n = this.l[by] >= 50 && (this.n[by] < 999 || this.o[by] < 999 || this.p[by] < 990000 || this.q[by] < 99) ? 460 : 220;
        }
        catch (Exception exception) {
            System.out.println("other first");
        }
        int n3 = 0;
        while (n3 < 10) {
            n2 = this.s() % n;
            if (n2 < 40) {
                if (this.aj(by)) {
                    break;
                }
            } else if (n2 < 120) {
                if (this.ak(by)) {
                    break;
                }
            } else {
                if (n2 < 220) {
                    this.am(by);
                    break;
                }
                if (n2 < 460) {
                    this.ao(by);
                    break;
                }
            }
            n3 = (byte)(n3 + 1);
        }
        if (n3 == 10) {
            this.am(by);
        }
    }

    public final void ch() {
        if (this.s() % 3 > 1) {
            return;
        }
        byte by = 0;
        while (by < this.aX) {
            byte by2 = this.aV[by];
            if ((this.x[by2] & 4) == 4 && this.l[by2] >= 100) {
                byte by3 = 0;
                while (by3 < this.r[by2]) {
                    if (this.K[this.A[by2][by3]] - 40 > this.L[this.A[by2][by3]]) {
                        this.bP = (byte)19;
                        this.au = by2;
                        this.ax = this.A[by2][by3];
                        return;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
    }

    public final int A(byte by, byte by2) {
        int n = 0;
        int n2 = 0;
        int n3 = this.u[by] * 4 * 30 / 1000;
        if (this.i[by] > 0) {
            n = this.d(this.A[by], (int)this.r[by]);
        }
        if (n3 > this.m[by]) {
            n = n * this.m[by] / n3;
        }
        if (this.i[by2] > 0) {
            n2 = this.d(this.A[by2], (int)this.r[by2]);
            int n4 = this.M[this.A[by2][0]] * this.J[this.A[by2][0]] * 3 / 2;
            n4 = this.c(this.M[this.A[by2][0]], n4);
            n -= (n2 += n4);
        }
        return n;
    }

    public final int ci() {
        byte by = 0;
        byte by2 = 0;
        int n = 99999;
        boolean bl = false;
        byte by3 = 0;
        while (by3 < this.W) {
            if (this.X[by3] == this.as) {
                bl = true;
                break;
            }
            by3 = (byte)(by3 + 1);
        }
        this.bP = (byte)-1;
        by3 = 0;
        while (by3 < this.aX) {
            by = this.aV[by3];
            if (this.r[by] > 1 && this.m[by] / 4 * 1000 / 45 >= this.u[by]) {
                int n2;
                byte by4;
                int n3 = n;
                int n4 = -10000;
                byte by5 = 0;
                while (by5 < this.E[by]) {
                    by4 = this.D[by][by5];
                    if (!(this.i[by] == this.i[by4] || bl && this.i[by4] == this.i[this.ar] || (n2 = this.y(by, by4)) <= n3)) {
                        n3 = n2;
                        by2 = by4;
                    }
                    by5 = (byte)(by5 + 1);
                }
                if (n3 > n) {
                    by5 = 0;
                    while (by5 < this.E[by]) {
                        by4 = this.D[by][by5];
                        if (this.i[by] != this.i[by4] && by4 != by2 && (n2 = this.d(this.A[by4], (int)this.r[by4])) > n4) {
                            n4 = n2;
                        }
                        by5 = (byte)(by5 + 1);
                    }
                    if ((n4 -= 100000) > 0) {
                        n3 -= n4;
                    }
                    if (n3 > n) {
                        n = n3;
                        this.au = by;
                        this.av = by2;
                    }
                }
            }
            by3 = (byte)(by3 + 1);
        }
        if (n >= 100000) {
            this.bP = (byte)3;
        }
        return n;
    }

    public final int E(int n) {
        int n2;
        switch (n /= 100000) {
            case 1: {
                n2 = 50;
                break;
            }
            case 2: {
                n2 = 80;
                break;
            }
            default: {
                n2 = 90;
            }
        }
        if (this.s() % 100 > n2) {
            this.bP = (byte)-1;
        }
        return n;
    }

    public final byte[] a(byte by, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        byte by2;
        int n;
        byte[] byArray4 = new byte[3];
        byte by3 = 0;
        while (by3 < this.r[by]) {
            if (this.M[this.A[by][by3]] >= this.N[this.A[by][by3]] && this.M[this.A[by][by3]] >= 80) {
                byte by4 = byArray4[0];
                byArray4[0] = (byte)(by4 + 1);
                byArray[by4] = by3;
            } else if (this.N[this.A[by][by3]] >= 80) {
                byte by5 = byArray4[1];
                byArray4[1] = (byte)(by5 + 1);
                byArray2[by5] = by3;
            } else {
                byte by6 = byArray4[2];
                byArray4[2] = (byte)(by6 + 1);
                byArray3[by6] = by3;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 0;
        while (by3 < byArray4[0] - 1) {
            n = by3 + 1;
            while (n < byArray4[0]) {
                if (this.M[this.A[by][byArray[by3]]] < this.M[this.A[by][byArray[n]]]) {
                    by2 = byArray[by3];
                    byArray[by3] = byArray[n];
                    byArray[n] = by2;
                }
                ++n;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 0;
        while (by3 < byArray4[1] - 1) {
            n = by3 + 1;
            while (n < byArray4[1]) {
                if (this.N[this.A[by][byArray2[by3]]] < this.N[this.A[by][byArray2[n]]]) {
                    by2 = byArray2[by3];
                    byArray2[by3] = byArray2[n];
                    byArray2[n] = by2;
                }
                ++n;
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 0;
        while (by3 < byArray4[2] - 1) {
            n = by3 + 1;
            while (n < byArray4[2]) {
                if (this.N[this.A[by][byArray3[by3]]] + this.M[this.A[by][byArray3[by3]]] < this.N[this.A[by][byArray3[n]]] + this.M[this.A[by][byArray3[n]]]) {
                    by2 = byArray3[by3];
                    byArray3[by3] = byArray3[n];
                    byArray3[n] = by2;
                }
                ++n;
            }
            by3 = (byte)(by3 + 1);
        }
        return byArray4;
    }

    public final void d(byte by, short[] sArray, byte by2) {
        byte by3 = 0;
        int n = 0;
        short s = 0;
        byte by4 = 0;
        while (by4 < by2) {
            if (this.P[sArray[by4]] >= 70 && n < this.N[sArray[by4]] + this.M[sArray[by4]]) {
                n = this.N[sArray[by4]] + this.M[sArray[by4]];
                s = sArray[by4];
                by3 = by4;
            }
            by4 = (byte)(by4 + 1);
        }
        if (n == 0) {
            by3 = 0;
            s = sArray[0];
            by4 = 1;
            while (by4 < by2) {
                if (this.P[s] < this.P[sArray[by4]]) {
                    s = sArray[by4];
                    by3 = by4;
                }
                by4 = (byte)(by4 + 1);
            }
        }
        this.j[by] = s;
        by4 = by3;
        while (by4 > 0) {
            sArray[by4] = sArray[by4 - 1];
            by4 = (byte)(by4 - 1);
        }
        sArray[0] = s;
    }

    public final void f(short[] sArray, byte by) {
        short s = sArray[0];
        byte by2 = -1;
        int n = -1;
        int n2 = 0;
        byte by3 = 0;
        while (by3 < by) {
            if (sArray[by3] == this.i[this.au]) {
                s = sArray[0];
                sArray[0] = sArray[by3];
                sArray[by3] = s;
                return;
            }
            if (this.N[sArray[by3]] >= 90) {
                if (this.N[sArray[by3]] > by2) {
                    s = sArray[by3];
                    by2 = this.N[sArray[by3]];
                    n2 = by3;
                }
            } else if (this.N[sArray[by3]] >= 80 && by2 < 90) {
                if (this.N[sArray[by3]] + this.M[sArray[by3]] > n) {
                    s = sArray[by3];
                    by2 = this.N[sArray[by3]];
                    n = this.N[sArray[by3]] + this.M[sArray[by3]];
                    n2 = by3;
                }
            } else if (this.N[sArray[by3]] >= 70 && by2 < 80) {
                if (this.N[sArray[by3]] + this.M[sArray[by3]] > n) {
                    s = sArray[by3];
                    by2 = this.N[sArray[by3]];
                    n = this.N[sArray[by3]] + this.M[sArray[by3]];
                    n2 = by3;
                }
            } else if (this.N[sArray[by3]] + this.M[sArray[by3]] > n) {
                s = sArray[by3];
                n = this.N[sArray[by3]] + this.M[sArray[by3]];
                n2 = by3;
            }
            by3 = (byte)(by3 + 1);
        }
        sArray[n2] = sArray[0];
        sArray[0] = s;
    }

    public final byte f(short[] sArray, int n) {
        byte[] byArray = new byte[this.r[this.au]];
        byte[] byArray2 = new byte[this.r[this.au]];
        byte[] byArray3 = new byte[this.r[this.au]];
        int n2 = 0;
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        int n3 = 0;
        int n4 = 0;
        byte by4 = 0;
        int n5 = 0;
        byte[] byArray4 = this.a(this.au, byArray2, byArray, byArray3);
        by = byArray4[0];
        n2 = byArray4[1];
        by2 = byArray4[2];
        if (this.i[this.av] == 0) {
            by4 = 0;
            while (by4 < this.E[this.av]) {
                if (this.i[this.D[this.av][by4]] != this.au) break;
                by4 = (byte)(by4 + 1);
            }
            if (by4 == this.E[this.av]) {
                if (by2 > 0) {
                    sArray[0] = this.A[this.au][byArray3[by2 - 1]];
                    this.A[this.au][byArray3[by2 - 1]] = 0;
                } else if (n2 > 0) {
                    sArray[0] = this.A[this.au][byArray[n2 - 1]];
                    this.A[this.au][byArray[n2 - 1]] = 0;
                } else {
                    sArray[0] = this.A[this.au][byArray2[by - 1]];
                    this.A[this.au][byArray2[by - 1]] = 0;
                }
                byte by5 = this.au;
                this.r[by5] = (byte)(this.r[by5] - 1);
                this.ar(this.au);
                return 1;
            }
        }
        int n6 = 0;
        by4 = 0;
        while (by4 < this.E[this.au]) {
            if (this.i[this.D[this.au][by4]] != this.au) {
                ++n6;
            }
            by4 = (byte)(by4 + 1);
        }
        if (n6 == 1) {
            n4 = by2 > 0 ? byArray3[by2 - 1] : (n2 > 0 ? byArray[n2 - 1] : byArray2[by - 1]);
            by4 = 0;
            while (by4 < this.r[this.au]) {
                if (by4 != n4) {
                    byte by6 = by3;
                    by3 = (byte)(by6 + 1);
                    sArray[by6] = this.A[this.au][by4];
                    this.A[this.au][by4] = 0;
                }
                by4 = (byte)(by4 + 1);
            }
            byte by7 = this.au;
            this.r[by7] = (byte)(this.r[by7] - by3);
            this.ar(this.au);
            this.f(sArray, by3);
            return by3;
        }
        if (this.i[this.av] > 0) {
            n3 = this.d(this.A[this.av], (int)this.r[this.av]);
            n4 = this.M[this.A[this.av][0]] * this.J[this.A[this.av][0]] * 3 / 2;
            n4 = this.c(this.M[this.A[this.av][0]], n4);
            n3 += n4;
        }
        n3 += n;
        by4 = 0;
        n5 = 0;
        while (by3 < this.r[this.au] - 1) {
            if (by4 < by) {
                sArray[by3] = this.A[this.au][byArray2[by4]];
                this.A[this.au][byArray2[by4]] = 0;
                n4 = this.s(sArray[by3]);
                by3 = (byte)(by3 + 1);
                by4 = by4 == 0 ? (byte)(by4 + 2) : (byte)(by4 + 1);
                if ((n3 -= n4) <= 0 || by3 >= this.r[this.au] - 1) {
                    byte by8 = this.au;
                    this.r[by8] = (byte)(this.r[by8] - by3);
                    this.ar(this.au);
                    this.f(sArray, by3);
                    return by3;
                }
            }
            if (n5 < n2) {
                sArray[by3] = this.A[this.au][byArray[n5]];
                this.A[this.au][byArray[n5]] = 0;
                n4 = this.s(sArray[by3]);
                n5 = n5 == 0 ? (n5 += 2) : ++n5;
                if ((n3 -= n4) <= 0) {
                    byte by9 = this.au;
                    this.r[by9] = (byte)(this.r[by9] - by3);
                    this.ar(this.au);
                    this.f(sArray, by3);
                    return by3;
                }
            }
            if (by4 == by && n5 == n2) break;
        }
        by4 = 0;
        while (by3 < this.r[this.au] - 1) {
            if (by4 >= by2) break;
            sArray[by3] = this.A[this.au][byArray3[by4]];
            this.A[this.au][byArray3[by4]] = 0;
            n4 = this.s(sArray[by3]);
            by3 = (byte)(by3 + 1);
            by4 = (byte)(by4 + 1);
            if ((n3 -= n4) > 0) continue;
            byte by10 = this.au;
            this.r[by10] = (byte)(this.r[by10] - by3);
            this.ar(this.au);
            this.f(sArray, by3);
            return by3;
        }
        byte by11 = this.au;
        this.r[by11] = (byte)(this.r[by11] - by3);
        this.ar(this.au);
        this.f(sArray, by3);
        return by3;
    }

    public final void cj() {
        this.au = 0;
        int n = -10000000;
        byte by = 0;
        while (by < this.aX) {
            byte by2 = this.aV[by];
            int n2 = this.r[by2] * 1000 - this.u[by2];
            if (n2 > 0 && (this.l[by2] >= 50 || this.v[by2] > 0)) {
                byte by3 = 0;
                while (by3 < this.E[by2]) {
                    byte by4 = this.D[by2][by3];
                    if (this.i[by2] != this.i[by4] && (n2 = this.y(by4, by2)) > n) {
                        n = n2;
                        this.au = by2;
                    }
                    by3 = (byte)(by3 + 1);
                }
            }
            by = (byte)(by + 1);
        }
        if (this.au != 0) {
            this.bP = (byte)2;
        }
    }

    public final int ay(byte by) {
        int n = -10000000;
        int n2 = 0;
        byte by2 = 0;
        while (by2 < this.E[by]) {
            byte by3 = this.D[by][by2];
            if (this.i[by3] != this.i[by] && n < (n2 = this.y(by3, by))) {
                n = n2;
            }
            by2 = (byte)(by2 + 1);
        }
        return n;
    }

    public final int e(byte by, int n) {
        byte by2 = 1;
        int n2 = n - 199999;
        int n3 = 0;
        this.av = 0;
        byte by3 = 0;
        while (by3 < this.E[by]) {
            byte by4 = this.D[by][by3];
            if (this.i[by4] == this.i[by] && this.r[by4] > 1) {
                if (!this.ai(by4)) {
                    if (this.r[by4] > by2) {
                        this.av = by4;
                        by2 = this.r[by4];
                    }
                } else if (by2 == 1 && n > 0 && (n3 = this.ay(by4)) < n2) {
                    this.av = by4;
                    n2 = n3;
                }
            }
            by3 = (byte)(by3 + 1);
        }
        if (by2 > 1) {
            return by2 - 1;
        }
        if (this.av != 0) {
            n3 = (n - n2) / 2;
            if (n3 > n) {
                return n;
            }
            return n3;
        }
        return 0;
    }

    public final int ck() {
        int n = 0;
        int n2 = -20000;
        this.au = 0;
        this.av = 0;
        int n3 = 0;
        byte by = 0;
        while (by < this.aX) {
            byte by2 = this.aV[by];
            if (this.r[by2] != 10 && (n = this.ay(by2)) > n2 && (n3 = this.e(by2, n)) != 0) {
                this.au = by2;
                this.bP = 1;
                n2 = n;
            }
            by = (byte)(by + 1);
        }
        return n3;
    }

    public final void F(int n) {
        byte[] byArray = new byte[this.r[this.av]];
        byte[] byArray2 = new byte[this.r[this.av]];
        byte[] byArray3 = new byte[this.r[this.av]];
        byte by = 0;
        int n2 = 0;
        byte by2 = 0;
        byte[] byArray4 = this.a(this.av, byArray2, byArray, byArray3);
        n2 = byArray4[0];
        by = byArray4[1];
        by2 = byArray4[2];
        if (n < 50) {
            int n3;
            int n4 = by2 - 1;
            while (n4 >= 0) {
                if (this.P[this.A[this.av][byArray3[n4]]] >= 70) {
                    n3 = 0;
                    break;
                }
                --n4;
            }
            if (n4 < 0) {
                n4 = by - 1;
                while (n4 >= 0) {
                    if (this.P[this.A[this.av][byArray[n4]]] >= 70) {
                        n3 = 0;
                        break;
                    }
                    --n4;
                }
            }
            if (n4 < 0) {
                n4 = n2 - 1;
                while (n4 >= 0) {
                    if (this.P[this.A[this.av][byArray2[n4]]] >= 70) {
                        n3 = 0;
                        break;
                    }
                    --n4;
                }
            }
            if (n4 < 0) {
                byte by3 = this.P[this.A[this.av][0]];
                n3 = 0;
                n4 = 1;
                while (n4 < this.r[this.av]) {
                    if (by3 < this.P[this.A[this.av][n4]]) {
                        n3 = n4;
                        by3 = this.P[this.A[this.av][n4]];
                    }
                    ++n4;
                }
            } else {
                n3 = n4;
            }
            n4 = 0;
            while (n4 < n2) {
                if (this.r[this.au] >= 10) break;
                if (byArray2[n4] != n3) {
                    byte by4 = this.av;
                    this.r[by4] = (byte)(this.r[by4] - 1);
                    this.A[this.au][this.r[this.au]] = this.A[this.av][byArray2[n4]];
                    this.A[this.av][byArray2[n4]] = 0;
                    byte by5 = this.au;
                    this.r[by5] = (byte)(this.r[by5] + 1);
                }
                ++n4;
            }
            n4 = 0;
            while (n4 < by) {
                if (this.r[this.au] >= 10) break;
                if (byArray[n4] != n3) {
                    byte by6 = this.av;
                    this.r[by6] = (byte)(this.r[by6] - 1);
                    this.A[this.au][this.r[this.au]] = this.A[this.av][byArray[n4]];
                    this.A[this.av][byArray[n4]] = 0;
                    byte by7 = this.au;
                    this.r[by7] = (byte)(this.r[by7] + 1);
                }
                ++n4;
            }
            n4 = 0;
            while (n4 < by2) {
                if (this.r[this.au] < 10) {
                    if (byArray3[n4] != n3) {
                        byte by8 = this.av;
                        this.r[by8] = (byte)(this.r[by8] - 1);
                        this.A[this.au][this.r[this.au]] = this.A[this.av][byArray3[n4]];
                        this.A[this.av][byArray3[n4]] = 0;
                        byte by9 = this.au;
                        this.r[by9] = (byte)(this.r[by9] + 1);
                    }
                    ++n4;
                    continue;
                }
                break;
            }
        } else {
            int n5 = 1;
            while (n5 < n2) {
                if (this.r[this.au] >= 10 || n <= 0) break;
                byte by10 = this.av;
                this.r[by10] = (byte)(this.r[by10] - 1);
                this.A[this.au][this.r[this.au]] = this.A[this.av][byArray2[n5]];
                n -= this.s(this.A[this.av][byArray2[n5]]);
                this.A[this.av][byArray2[n5]] = 0;
                byte by11 = this.au;
                this.r[by11] = (byte)(this.r[by11] + 1);
                n5 += 2;
            }
            n5 = 1;
            while (n5 < by) {
                if (this.r[this.au] >= 10 || n <= 0) break;
                byte by12 = this.av;
                this.r[by12] = (byte)(this.r[by12] - 1);
                this.A[this.au][this.r[this.au]] = this.A[this.av][byArray[n5]];
                n -= this.s(this.A[this.av][byArray[n5]]);
                this.A[this.av][byArray[n5]] = 0;
                byte by13 = this.au;
                this.r[by13] = (byte)(this.r[by13] + 1);
                n5 += 2;
            }
            n5 = 0;
            while (n5 < by2) {
                if (this.r[this.au] >= 10 || n <= 0) break;
                byte by14 = this.av;
                this.r[by14] = (byte)(this.r[by14] - 1);
                this.A[this.au][this.r[this.au]] = this.A[this.av][byArray3[n5]];
                n -= this.s(this.A[this.av][byArray3[n5]]);
                this.A[this.av][byArray3[n5]] = 0;
                byte by15 = this.au;
                this.r[by15] = (byte)(this.r[by15] + 1);
                n5 += 2;
            }
            n5 = by2 - 1;
            while (n5 >= 0) {
                if (this.r[this.au] < 10 && n > 0 && this.r[this.av] > 1) {
                    if (this.A[this.av][byArray3[n5]] != 0) {
                        byte by16 = this.av;
                        this.r[by16] = (byte)(this.r[by16] - 1);
                        this.A[this.au][this.r[this.au]] = this.A[this.av][byArray3[n5]];
                        n -= this.s(this.A[this.av][byArray3[n5]]);
                        this.A[this.av][byArray3[n5]] = 0;
                        byte by17 = this.au;
                        this.r[by17] = (byte)(this.r[by17] + 1);
                    }
                    --n5;
                    continue;
                }
                break;
            }
        }
        this.ar(this.au);
        this.ar(this.av);
    }

    public final void cl() {
        byte by = -1;
        byte by2 = 0;
        while (by2 < this.aX) {
            byte by3 = this.aV[by2];
            if ((this.x[by3] & 4) == 4 && this.l[by3] >= 100) {
                byte by4 = 0;
                while (by4 < this.r[by3]) {
                    if (this.K[this.A[by3][by4]] - 30 > this.L[this.A[by3][by4]]) {
                        if (this.N[this.A[by3][by4]] > this.M[this.A[by3][by4]]) {
                            if (this.N[this.A[by3][by4]] > by) {
                                by = this.N[this.A[by3][by4]];
                                this.au = by3;
                                this.ax = this.A[by3][by4];
                            }
                        } else if (this.M[this.A[by3][by4]] > by) {
                            by = this.M[this.A[by3][by4]];
                            this.au = by3;
                            this.ax = this.A[by3][by4];
                        }
                        this.bP = (byte)19;
                        this.au = by3;
                        this.ax = this.A[by3][by4];
                    }
                    by4 = (byte)(by4 + 1);
                }
            }
            by2 = (byte)(by2 + 1);
        }
        if (by >= 0) {
            this.bP = (byte)19;
        }
    }

    public final byte a(short s, byte by, byte by2) {
        byte by3 = this.N[s] > this.M[s] ? this.N[s] : this.M[s];
        if (by3 >= 80 && by3 > by && this.P[s] <= 35) {
            return 8;
        }
        if (by2 < 8 && by3 >= 80 && by3 > by && this.P[s] <= 50) {
            return 7;
        }
        if (by2 < 7 && by3 > by && this.P[s] <= 35) {
            return 6;
        }
        if (by2 < 6 && by3 >= 80 && by3 > by && this.P[s] <= 70) {
            return 5;
        }
        if (by2 < 5 && by3 > by && this.P[s] < 50) {
            return 4;
        }
        if (by2 < 4 && by3 >= 90 && by3 > by && this.P[s] < 90) {
            return 3;
        }
        if (by2 < 3 && by3 >= 80 && by3 > by && this.P[s] < 90) {
            return 2;
        }
        if (by2 < 2 && by3 >= by && this.P[s] < 70) {
            return 1;
        }
        return by2;
    }

    public final void cm() {
        byte by;
        byte by2 = -1;
        byte by3 = 0;
        if (this.s() % 2 > 0) {
            return;
        }
        byte by4 = 0;
        while (by4 < this.c[this.as]) {
            byte by5 = this.d[this.as][by4];
            if (this.l[by5] >= 200 || this.w[by5] != 0) {
                byte by6 = 0;
                while (by6 < this.r[by5]) {
                    by = this.a(this.A[by5][by6], by2, by3);
                    if (by > 0) {
                        this.au = by5;
                        this.ax = this.A[by5][by6];
                        by2 = this.N[this.ax] > this.M[this.ax] ? this.N[this.ax] : this.M[this.ax];
                        by3 = by;
                    }
                    by6 = (byte)(by6 + 1);
                }
            }
            by4 = (byte)(by4 + 1);
        }
        by = (byte)(this.s() % 10);
        switch (by3) {
            case 0: {
                return;
            }
            case 1: 
            case 2: {
                if (by >= 9) break;
                return;
            }
            case 3: 
            case 4: {
                if (by >= 7) break;
                return;
            }
            case 5: {
                if (by >= 5) break;
                return;
            }
        }
        this.bP = (byte)8;
    }

    public final int az(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        short s = -1;
        short s2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.b[by5] == this.b[by]) {
                if (!this.ai(by5)) {
                    if (this.m[by5] > s) {
                        s = this.m[by5];
                        by2 = by5;
                    }
                } else if (s < 1000 && this.m[by5] > s2) {
                    s2 = this.m[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (s >= 1000) {
            this.av = by2;
            return 0;
        }
        if (s2 - s >= 500 && s2 >= 600) {
            this.av = by3;
            return 1;
        }
        if (s >= 400) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int aA(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        short s = -1;
        short s2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.b[by5] == this.b[by]) {
                if (!this.ai(by5)) {
                    if (this.l[by5] > s) {
                        s = this.l[by5];
                        by2 = by5;
                    }
                } else if (s < 1000 && this.l[by5] > s2) {
                    s2 = this.l[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (s >= 1000) {
            this.av = by2;
            return 0;
        }
        if (s2 - s >= 500 && s2 >= 600) {
            this.av = by3;
            return 1;
        }
        if (s >= 400) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int aB(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        int n = -1;
        int n2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.b[by5] == this.b[by]) {
                if (!this.ai(by5)) {
                    if (this.l[by5] + this.m[by5] > n) {
                        n = this.l[by5] + this.m[by5];
                        by2 = by5;
                    }
                } else if (n < 1500 && this.l[by5] + this.m[by5] > n2) {
                    n2 = this.l[by5] + this.m[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (n >= 1500) {
            this.av = by2;
            return 0;
        }
        if (n2 - n >= 800 && n2 >= 1500) {
            this.av = by3;
            return 1;
        }
        if (n >= 800) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int aC(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        int n = -1;
        int n2 = -1;
        byte by4 = 0;
        while (by4 < this.E[by]) {
            byte by5 = this.D[by][by4];
            if (this.b[by5] == this.b[by]) {
                if (!this.ai(by5)) {
                    if (this.l[by5] + this.m[by5] > n) {
                        n = this.l[by5] + this.m[by5];
                        by2 = by5;
                    }
                } else if (n < 2000 && this.l[by5] + this.m[by5] > n2) {
                    n2 = this.l[by5] + this.m[by5];
                    by3 = by5;
                }
            }
            by4 = (byte)(by4 + 1);
        }
        if (n >= 2000) {
            this.av = by2;
            return 0;
        }
        if (n2 - this.l[by] - this.m[by] >= 1000 && n2 >= 3000) {
            this.av = by3;
            return 1;
        }
        if (n >= 1000) {
            this.av = by2;
            return 0;
        }
        return -1;
    }

    public final int cn() {
        short s = 0;
        short s2 = 10000;
        int n = 0;
        this.au = 0;
        this.av = 0;
        byte by = 0;
        while (by < this.aX) {
            short s3;
            byte by2 = this.aV[by];
            if (this.m[by2] < 200 && this.m[by2] < s2 && (s3 = this.az(by2)) >= 0) {
                this.au = by2;
                s2 = this.m[by2];
                n = 4;
                s = s3;
            } else if (n < 4 && this.l[by2] < 200 && this.l[by2] < s2 && (s3 = this.aA(by2)) >= 0) {
                this.au = by2;
                s2 = this.l[by2];
                n = 3;
                s = s3;
            } else {
                s3 = this.l[by2] + this.m[by2];
                if (s3 < s2) {
                    if (n < 3 && s3 < 1000 && (s3 = this.aB(by2)) >= 0) {
                        this.au = by2;
                        s2 = (short)(this.l[by2] + this.m[by2]);
                        n = 2;
                        s = s3;
                    } else {
                        s3 = this.l[by2] + this.m[by2];
                        if (n < 2 && s3 < 4000 && (s3 = this.aC(by2)) >= 0) {
                            this.au = by2;
                            s2 = (short)(this.l[by2] + this.m[by2]);
                            n = 1;
                            s = s3;
                        }
                    }
                }
            }
            by = (byte)(by + 1);
        }
        if (n > 2 || n == 2 && this.s() % 2 > 0 || n == 1 && this.s() % 5 > 3) {
            this.bP = (byte)12;
        }
        return s;
    }

    public final boolean co() {
        byte by;
        byte by2;
        int n = 20000;
        int n2 = 20000;
        boolean bl = false;
        byte by3 = 0;
        while (by3 < this.aY) {
            if (this.r[this.aW[by3]] > 1) {
                by2 = 0;
                while (by2 < this.E[this.aW[by3]]) {
                    by = this.D[this.aW[by3]][by2];
                    if (this.ai(by)) {
                        this.av = this.aW[by3];
                        this.au = by;
                        this.v(this.r[this.aW[by3]] - 1);
                        return true;
                    }
                    by2 = (byte)(by2 + 1);
                }
                by2 = 0;
                while (by2 < this.E[this.aW[by3]]) {
                    by = this.D[this.aW[by3]][by2];
                    byte by4 = 0;
                    while (by4 < this.E[by]) {
                        if (this.ai(this.D[by][by4])) {
                            this.av = this.aW[by3];
                            this.au = by;
                            this.v(this.r[this.aW[by3]] - 1);
                        }
                        by4 = (byte)(by4 + 1);
                    }
                    by2 = (byte)(by2 + 1);
                }
            }
            by3 = (byte)(by3 + 1);
        }
        by3 = 0;
        while (by3 < this.aY) {
            if (this.l[this.aW[by3]] > 4000 || this.m[this.aW[by3]] > 4000) {
                by2 = 0;
                while (by2 < this.E[this.aW[by3]]) {
                    by = this.D[this.aW[by3]][by2];
                    if (this.l[by] <= 3000 || this.m[by] <= 3000) {
                        if (this.ai(by) && n2 > this.l[by] + this.m[by]) {
                            bl = true;
                            n2 = this.l[by] + this.m[by];
                            this.au = by;
                            this.av = this.aW[by3];
                        }
                        if (bl < true && n > this.l[by] + this.m[by]) {
                            n = this.l[by] + this.m[by];
                            this.au = by;
                            this.av = this.aW[by3];
                        }
                    }
                    by2 = (byte)(by2 + 1);
                }
            }
            if (n2 < 20000 || n < 20000) {
                this.x(1);
            }
            by3 = (byte)(by3 + 1);
        }
        return false;
    }

    public final boolean cp() {
        short s = 10000;
        short s2 = 0;
        byte by = 0;
        byte by2 = 0;
        by2 = 0;
        while (by2 < this.aX) {
            if ((this.x[this.aV[by2]] & 8) == 8 && this.m[this.aV[by2]] < 200 && this.l[this.aV[by2]] >= 500 && this.m[this.aV[by2]] < s) {
                s = this.m[this.aV[by2]];
                by = this.aV[by2];
            }
            by2 = (byte)(by2 + 1);
        }
        if (s < 200) {
            byte by3 = by;
            this.m[by3] = (short)(this.m[by3] + this.l[by] * 4 / 9);
            byte by4 = by;
            this.l[by4] = (short)(this.l[by4] - this.l[by] / 3);
            return true;
        }
        by2 = 0;
        while (by2 < this.aX) {
            if ((this.x[this.aV[by2]] & 8) == 8 && this.l[this.aV[by2]] < 200 && this.m[this.aV[by2]] >= 500 && this.l[this.aV[by2]] < s) {
                s = this.l[this.aV[by2]];
                by = this.aV[by2];
            }
            by2 = (byte)(by2 + 1);
        }
        if (s < 200) {
            byte by5 = by;
            this.l[by5] = (short)(this.l[by5] + this.m[by] / 4);
            byte by6 = by;
            this.m[by6] = (short)(this.m[by6] - this.m[by] / 3);
            return true;
        }
        s = 0;
        by2 = 0;
        while (by2 < this.aX) {
            byte by7 = 0;
            while (by7 < this.r[this.aV[by2]]) {
                if ((this.x[this.aV[by2]] & 2) == 2 && this.l[this.aV[by2]] >= 100 && this.M[this.A[this.aV[by2]][by7]] > s && this.N[this.A[this.aV[by2]][by7]] < 80 && this.P[this.A[this.aV[by2]][by7]] >= 70) {
                    by = this.aV[by2];
                    s2 = this.A[by][by7];
                    s = this.M[s2];
                }
                by7 = (byte)(by7 + 1);
            }
            by2 = (byte)(by2 + 1);
        }
        if (s > 0) {
            this.b(by, s2);
            return true;
        }
        return false;
    }

    public final short aD(byte by) {
        short s = 0;
        byte by2 = 0;
        byte by3 = 0;
        while (by3 < this.c[this.ar]) {
            byte by4 = 0;
            while (by4 < this.r[this.d[this.ar][by3]]) {
                short s2 = this.A[this.d[this.ar][by3]][by4];
                if (!(this.M[s2] <= by2 || this.P[s2] >= 100 || this.P[s2] > 96 && this.N[s2] + 70 > by || this.P[s2] > 89 && this.N[s2] + 40 > by || this.N[s2] + 20 > by)) {
                    s = s2;
                    by2 = this.M[s2];
                }
                by4 = (byte)(by4 + 1);
            }
            by3 = (byte)(by3 + 1);
        }
        return s;
    }

    public final boolean cq() {
        byte by;
        byte by2 = 0;
        byte by3 = 0;
        byte by4 = 0;
        short s = 0;
        short s2 = 0;
        byte by5 = 100;
        byte by6 = 1;
        while (by6 < 37) {
            by2 = by6;
            if (this.i[by2] != this.b[this.as]) {
                by = 0;
                while (by < this.r[by2]) {
                    if (by5 > this.P[this.A[by2][by]]) {
                        by3 = by2;
                        s = this.A[by2][by];
                        by5 = this.P[s];
                    }
                    by = (byte)(by + 1);
                }
            }
            by6 = (byte)(by6 + 1);
        }
        if (by5 == 100) {
            return false;
        }
        by5 = 0;
        by6 = 0;
        while (by6 < this.c[this.as]) {
            by = 0;
            while (by < this.r[this.d[this.as][by6]]) {
                if (this.N[this.A[this.d[this.as][by6]][by]] > by5) {
                    by4 = this.d[this.as][by6];
                    s2 = this.A[by4][by];
                    by5 = this.N[s2];
                }
                by = (byte)(by + 1);
            }
            by6 = (byte)(by6 + 1);
        }
        if (this.P[s] < 35 && this.r[by4] < 10) {
            this.b(by4, by3, s2, s);
            return true;
        }
        short s3 = this.aD(by5);
        s = s3;
        if (s3 != 0) {
            this.b(by3, s2, s);
            return true;
        }
        return false;
    }

    public final short aE(byte by) {
        short s = this.A[by][0];
        byte by2 = this.O[s];
        byte by3 = 1;
        while (by3 < this.r[by]) {
            if (by2 < this.A[by][by3]) {
                s = this.A[by][by3];
                by2 = this.O[s];
            }
            by3 = (byte)(by3 + 1);
        }
        return s;
    }

    public final short aF(byte by) {
        short s = this.A[by][0];
        int n = this.N[s] + this.O[s] / 2;
        byte by2 = 1;
        while (by2 < this.r[by]) {
            if (n < this.N[this.A[by][by2]] + this.O[this.A[by][by2]] / 2) {
                s = this.A[by][by2];
                n = this.N[s] + this.O[s] / 2;
            }
            by2 = (byte)(by2 + 1);
        }
        return s;
    }

    public final void aG(byte by) {
        if (this.r[by] < 10 && this.t[by] > 0) {
            short s = this.aE(by);
            short s2 = this.C[by][this.s() % this.t[by]];
            this.c(by, s, s2);
            return;
        }
        short s = this.aF(by);
        this.c(by, s);
    }

    public final short aH(byte by) {
        short s = this.A[by][0];
        byte by2 = this.N[s];
        byte by3 = 1;
        while (by3 < this.r[by]) {
            if (by2 < this.N[this.A[by][by3]]) {
                s = this.A[by][by3];
                by2 = this.N[s];
            }
            by3 = (byte)(by3 + 1);
        }
        return s;
    }

    public final void aI(byte by) {
        short s = this.aH(by);
        if (this.q[by] < 60) {
            this.g(by, s);
            return;
        }
        if (this.s() % 2 > 0) {
            if (this.n[by] < 999 && this.o[by] < 999) {
                if (this.aC < 4 || this.aC >= 10) {
                    this.e(by, s);
                    return;
                }
                this.d(by, s);
                return;
            }
            if (this.n[by] < 999) {
                this.d(by, s);
                return;
            }
            if (this.o[by] < 999) {
                this.e(by, s);
                return;
            }
        }
        if (this.p[by] < 990000) {
            this.f(by, s);
            return;
        }
        this.g(by, s);
    }

    public final void cr() {
        byte by;
        int n;
        if (this.s() % 10 > 7 && this.aY > 0) {
            n = this.s() % this.aY;
            by = this.aW[n];
        } else {
            n = this.s() % this.aX;
            by = this.aV[n];
        }
        int n2 = this.l[by] >= 50 && (this.n[by] < 999 || this.o[by] < 999 || this.p[by] < 990000 || this.q[by] < 99) ? 500 : 300;
        int n3 = 0;
        while (n3 < 10) {
            n = this.s() % n2;
            if (n < 40) {
                if (this.co()) {
                    break;
                }
            } else if (n < 100) {
                if (this.cp()) {
                    break;
                }
            } else if (n < 200) {
                if (this.cq()) {
                    break;
                }
            } else {
                if (n < 300) {
                    this.aG(by);
                    break;
                }
                if (n < 500) {
                    this.aI(by);
                    break;
                }
            }
            n3 = (byte)(n3 + 1);
        }
        if (n3 >= 10) {
            this.aG(by);
        }
    }

    public final synchronized int aJ(byte by) {
        Object var5_2 = null;
        boolean bl = false;
        Object var8_4 = null;
        Object var9_5 = null;
        short s = (short)(by + 4);
        short s2 = 0;
        while (s2 < 8) {
            short s3 = s;
            s = (short)(s3 + 1);
            this.cZ[s3] = this.aB[s2];
            s2 = (short)(s2 + 1);
        }
        short s4 = s;
        s = (short)(s4 + 1);
        this.cZ[s4] = this.ar;
        short s5 = s;
        s = (short)(s5 + 1);
        this.cZ[s5] = this.au;
        short s6 = s;
        s = (short)(s6 + 1);
        this.cZ[s6] = this.aG;
        short s7 = s;
        s = (short)(s7 + 1);
        this.cZ[s7] = this.aC;
        short s8 = s;
        s = (short)(s8 + 1);
        this.cZ[s8] = (byte)(this.aD & 0xFF);
        short s9 = s;
        s = (short)(s9 + 1);
        this.cZ[s9] = (byte)(this.aD >> 8);
        short s10 = s;
        s = (short)(s10 + 1);
        this.cZ[s10] = this.Z;
        s2 = 1;
        while (s2 < 37) {
            s = this.a((byte)s2, this.cZ, s);
            short s11 = 0;
            while (s11 < this.r[s2]) {
                s = this.a(this.A[s2][s11], this.cZ, s);
                s11 = (short)(s11 + 1);
            }
            s11 = 0;
            while (s11 < this.t[s2]) {
                s = this.a(this.C[s2][s11], this.cZ, s);
                s11 = (short)(s11 + 1);
            }
            s11 = 0;
            while (s11 < this.s[s2]) {
                s = this.a(this.B[s2][s11], this.cZ, s);
                s11 = (short)(s11 + 1);
            }
            s2 = (short)(s2 + 1);
        }
        s2 = 1;
        while (s2 < 229) {
            s = this.b(s2, this.cZ, s);
            s2 = (short)(s2 + 1);
        }
        s = this.a(this.cZ, s);
        s2 = 1;
        while (s2 <= 8) {
            s = this.b((byte)s2, this.cZ, s);
            s2 = (short)(s2 + 1);
        }
        short s12 = s;
        s = (short)(s12 + 1);
        this.cZ[s12] = this.W;
        s2 = 0;
        while (s2 < this.W) {
            short s13 = s;
            s = (short)(s13 + 1);
            this.cZ[s13] = this.X[s2];
            short s14 = s;
            s = (short)(s14 + 1);
            this.cZ[s14] = this.Y[s2];
            s2 = (short)(s2 + 1);
        }
        this.cZ[by] = (byte)(s & 0xFF);
        this.cZ[by + 1] = (byte)(s >> 8 & 0xFF);
        this.cZ[by + 2] = (byte)(s >> 16 & 0xFF);
        this.cZ[by + 3] = (byte)(s >> 24);
        byte[] byArray = new byte[100];
        this.db = new String();
        this.db = this.db = String.valueOf(this.b[this.ar]) + "_" + this.aD + "_" + this.aC + "_";
        try {
            byArray = this.db.getBytes("UTF-8");
        }
        catch (Exception exception) {}
        short s15 = s;
        s = (short)(s15 + 1);
        this.cZ[s15] = (byte)byArray.length;
        s2 = 0;
        while (s2 < byArray.length) {
            short s16 = s;
            s = (short)(s16 + 1);
            this.cZ[s16] = byArray[s2];
            s2 = (short)(s2 + 1);
        }
        return s;
    }

    public final synchronized void cs() {
        block11: {
            Object var4_1 = null;
            boolean bl = false;
            RecordStore recordStore = null;
            Object var8_4 = null;
            boolean bl2 = false;
            try {
                recordStore = RecordStore.openRecordStore((String)"login", (boolean)true, (int)1, (boolean)true);
                byte[] byArray = this.dc.getBytes("UTF-8");
                byte[] byArray2 = this.dd.getBytes("UTF-8");
                if (recordStore.getNumRecords() <= 0) {
                    recordStore.addRecord(byArray, 0, byArray.length);
                    recordStore.addRecord(byArray2, 0, byArray2.length);
                } else {
                    recordStore.setRecord(1, byArray, 0, byArray.length);
                    recordStore.setRecord(2, byArray2, 0, byArray2.length);
                }
                try {
                    int n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        n = (short)(n + 1);
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
                return;
            }
            catch (Exception exception) {
                if (recordStore == null) break block11;
                try {
                    int n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        n = (short)(n + 1);
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

    public final void ct() {
        block13: {
            RecordStore recordStore = null;
            try {
                recordStore = RecordStore.openRecordStore((String)"login", (boolean)true, (int)1, (boolean)false);
                if (recordStore.getNumRecords() <= 0) {
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
                }
                byte[] byArray = recordStore.getRecord(1);
                this.dc = new String(byArray, "UTF-8");
                byArray = recordStore.getRecord(2);
                this.dd = new String(byArray, "UTF-8");
                try {
                    int n = 0;
                    while (n < 100) {
                        recordStore.closeRecordStore();
                        ++n;
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
                return;
            }
            catch (Exception exception) {
                if (recordStore == null) break block13;
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

    public final synchronized int cu() {
        int n;
        block11: {
            block12: {
                Object var1_1 = null;
                byte[] byArray = this.cZ;
                n = this.aJ((byte)0);
                RecordStore recordStore = null;
                try {
                    recordStore = RecordStore.openRecordStore((String)"record", (boolean)true, (int)1, (boolean)true);
                    if (recordStore.getNumRecords() <= 0) {
                        recordStore.addRecord(byArray, 0, n);
                    } else {
                        recordStore.setRecord(1, byArray, 0, n);
                    }
                    try {
                        int n2 = 0;
                        while (n2 < 100) {
                            recordStore.closeRecordStore();
                            ++n2;
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                    recordStore = null;
                    break block11;
                }
                catch (Exception exception) {
                    if (recordStore == null) break block12;
                    try {
                        int n3 = 0;
                        while (n3 < 100) {
                            recordStore.closeRecordStore();
                            ++n3;
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    }
                    catch (Exception exception2) {}
                }
            }
            return -1;
        }
        return n;
    }

    public final synchronized boolean G(int n) {
        block15: {
            block16: {
                RecordStore recordStore;
                block14: {
                    recordStore = null;
                    recordStore = RecordStore.openRecordStore((String)"record", (boolean)true, (int)1, (boolean)false);
                    if (recordStore.getNumRecords() > 0) break block14;
                    try {
                        int n2 = 0;
                        while (n2 < 100) {
                            recordStore.closeRecordStore();
                            ++n2;
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                    return false;
                }
                try {
                    this.da = recordStore.getRecord(1);
                    int n3 = this.da[3] << 24 | this.da[2] << 16 & 0xFF0000 | this.da[1] << 8 & 0xFF00 | this.da[0] & 0xFF;
                    int n4 = this.da[n3++];
                    byte[] byArray = new byte[50];
                    int n5 = 0;
                    while (n5 < n4) {
                        byArray[n5] = this.da[n3++];
                        n5 = (byte)(n5 + 1);
                    }
                    this.ao.aS[n] = new String(byArray, "UTF-8");
                    this.ao.aS[n] = this.ao.b(this.ao.aS[n]);
                    this.ao.aX[n] = this.da;
                    try {
                        n5 = 0;
                        while (n5 < 100) {
                            recordStore.closeRecordStore();
                            ++n5;
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                    recordStore = null;
                    break block15;
                }
                catch (Exception exception) {
                    if (recordStore == null) break block16;
                    try {
                        int n6 = 0;
                        while (n6 < 100) {
                            recordStore.closeRecordStore();
                            ++n6;
                        }
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    }
                    catch (Exception exception2) {}
                }
            }
            return false;
        }
        return true;
    }
}
