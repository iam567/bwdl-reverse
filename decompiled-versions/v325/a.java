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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class a
extends Thread {
    public byte[] a;
    public short[] a;
    public byte[] b;
    public byte[][] a;
    public byte[] c;
    public short[][] a;
    public byte[] d;
    public String[] a;
    public short[] b;
    public short[] c;
    public byte[] e;
    public short[] d;
    public short[] e;
    public short[] f;
    public short[] g;
    public int[] a;
    public byte[] f;
    public byte[] g;
    public byte[] h;
    public byte[] i;
    public short[] h;
    public short[] i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte[] m;
    public short[][] b;
    public short[][] c;
    public short[][] d;
    public byte[][] b;
    public byte[] n;
    public short[] j;
    public String[] b;
    public byte[] o;
    public byte[] p;
    public short[] k;
    public byte[] q;
    public byte[] r;
    public byte[] s;
    public byte[] t;
    public byte[] u;
    public byte[] v;
    public short[] l;
    public byte[] w;
    public byte[] x;
    public short[] m;
    public byte[] y;
    public boolean[] a;
    public byte a;
    public byte[] z;
    public byte[] A;
    public byte b;
    public short[] n;
    public short[] o;
    public short[] p;
    public String[] c;
    public short[] q;
    public byte[] B;
    public byte[][] c;
    public byte[][] d;
    public byte[][] e;
    public byte[][] f;
    public short[] r;
    public byte[] C;
    public short[] s;
    public String[] d;
    public b a;
    public Random a;
    public byte c;
    public byte d;
    public byte e;
    public byte f;
    public byte g;
    public byte h;
    public byte i;
    public short a;
    public short b;
    public short[] t;
    public byte j;
    public byte[] D;
    public byte k;
    public short c;
    public byte l;
    public byte m;
    public byte n;
    public boolean a;
    public boolean b;
    public short d;
    public String a;
    public boolean c;
    public short[] u;
    public byte o;
    public int a;
    public int b;
    public byte[] E;
    public byte[] F;
    public byte p;
    public byte q;
    public boolean d;
    public byte[] G;
    public byte[] H;
    public byte r;
    public byte s;
    public short e;
    public short f;
    public byte t;
    public short g;
    public short h;
    public short i;
    public short j;
    public short[] v;
    public short[] w;
    public byte u;
    public byte v;
    public byte w;
    public byte x;
    public byte[][] g;
    public byte y;
    public byte z;
    public boolean e;
    public byte[] I;
    public byte[] J;
    public byte[] K;
    public byte[] L;
    public byte[] M;
    public byte[] N;
    public byte A;
    public byte B;
    public byte C;
    public byte D;
    public byte E;
    public boolean f;
    public byte F;
    public String b;
    public boolean g;
    public byte G;
    public byte H = 0;
    public byte I;
    public byte J;
    public byte K;
    public byte[][] h;
    public byte L;
    public byte M;
    public byte N;
    public byte O;
    public byte P;
    public byte Q;
    public byte[][] i;
    public byte[][] j;
    public byte[][] k;
    public byte[][] l;
    public short[][] e;
    public short[][] f;
    public byte[][] m;
    public byte[][] n;
    public byte[][] o = 0;
    public boolean[][] a;
    public boolean[][] b = true;
    public byte R;
    public byte S = 0;
    public byte T = 0;
    public byte U;
    public short[] x;
    public short[] y;
    public byte V;
    public byte[] O;
    public byte W = 0;
    public boolean h;
    public boolean i;
    public byte X;
    public byte Y;
    public byte Z;
    public byte aa;
    public byte ab;
    public byte ac;
    public byte ad;
    public byte ae;
    public boolean j;
    public boolean k;
    public short k = 1;
    public short l;
    public short m;
    public short n;
    public boolean l = 0;
    public byte af;
    public byte ag;
    public byte ah;
    public byte ai;
    public int c = true;
    public boolean m = 0;
    public byte aj;
    public int d = true;
    public byte ak;
    public byte al;
    public byte[] P;
    public int e;
    public boolean n = false;
    public byte am = 0;
    public byte an;
    public int f;
    public int g;
    public byte ao;
    public int h;
    public int i;
    public byte[] Q;
    public String[][] a = true;
    public short[] z;
    public byte[] R;

    public final int a(int n, int n2, int n3, int n4, int n5) {
        return n * (n5 - n4 + 1) / (n3 - n2 + 1) + n4;
    }

    public final int a(int n, int n2, int n3) {
        return n * 1000 / (n3 - n2 + 1);
    }

    public final void a(int n, int n2, byte by) {
        if (n < this.h) {
            this.h = n;
            if (n == 1000) {
                this.ao = (byte)8;
                return;
            }
            this.ao = by;
            this.i = n2;
            return;
        }
        if (n < 1000 && n == this.h && n2 < this.i) {
            this.ao = by;
            this.i = n2;
        }
    }

    public final void a(int n, int n2) {
        int n3;
        int n4 = 0;
        if (n >= 999) {
            n3 = 2000;
            this.Q[n2] = 6;
            this.f += 100;
        } else if (n >= 900) {
            n3 = 2000;
            this.Q[n2] = 5;
            this.f += this.a(n, 900, 998, 85, 90);
        } else if (n >= 700) {
            n3 = 1000;
            this.Q[n2] = 4;
            this.f += this.a(n, 700, 899, 80, 84);
        } else if (n >= 500) {
            n3 = 1000;
            this.Q[n2] = 3;
            this.f += this.a(n, 500, 699, 60, 79);
        } else if (n >= 200) {
            n3 = 750;
            this.Q[n2] = 2;
            n4 = this.a(n, 200, 499);
            this.f += this.a(n, 200, 499, 30, 59);
        } else if (n >= 100) {
            n3 = 500;
            this.Q[n2] = 1;
            n4 = this.a(n, 100, 199);
            this.f += this.a(n, 100, 199, 1, 29);
        } else {
            n3 = 250;
            this.Q[n2] = 0;
            n4 = this.a(n, 0, 99);
        }
        this.a(n3, n4, (byte)0);
    }

    public final void a() {
        int n = 0;
        for (int n2 = 1; n2 < 37; n2 = (int)((byte)(n2 + 1))) {
            n += this.f[n2];
        }
        this.a(n /= 36, 0);
    }

    public final void b() {
        int n = 0;
        for (int n2 = 1; n2 < 37; n2 = (int)((byte)(n2 + 1))) {
            n += this.g[n2];
        }
        this.a(n /= 36, 1);
    }

    public final void c() {
        int n;
        int n2 = 0;
        int n3 = 0;
        for (int n4 = 1; n4 < 37; n4 = (int)((byte)(n4 + 1))) {
            n2 += this.f[n4];
        }
        if ((n2 /= 36) >= 99) {
            n = 2000;
            this.Q[2] = 4;
            this.f += 30;
        } else if (n2 >= 90) {
            n = 1000;
            this.Q[2] = 3;
            this.f += this.a(n2, 90, 98, 10, 20);
        } else if (n2 >= 70) {
            n = 1000;
            this.Q[2] = 2;
            this.f += this.a(n2, 70, 89, 5, 9);
        } else if (n2 >= 50) {
            n = 667;
            this.Q[2] = 1;
            n3 = this.a(n2, 50, 69);
            this.f += this.a(n2, 50, 69, 1, 4);
        } else {
            n = 333;
            this.Q[2] = 0;
            n3 = this.a(n2, 0, 49);
        }
        this.a(n, n3, (byte)2);
    }

    public final void d() {
        int n;
        int n2 = 0;
        int n3 = 0;
        for (int n4 = 1; n4 < 37; n4 = (int)((byte)(n4 + 1))) {
            n2 += this.a[n4];
        }
        if ((n2 /= 36) >= 990000) {
            n = 2000;
            this.Q[3] = 5;
            this.f += 100;
        } else if (n2 >= 500000) {
            n = 2000;
            this.Q[3] = 4;
            this.f += this.a(n2, 500000, 989999, 80, 90);
        } else if (n2 >= 200000) {
            n = 1000;
            this.Q[3] = 3;
            this.f += this.a(n2, 200000, 499999, 70, 79);
        } else if (n2 >= 100000) {
            n = 1000;
            this.Q[3] = 2;
            this.f += this.a(n2, 100000, 199999, 50, 69);
        } else if (n2 >= 50000) {
            n = 667;
            this.Q[3] = 1;
            n3 = this.a(n2, 50000, 99999);
            this.f += this.a(n2, 50000, 99999, 1, 49);
        } else {
            n = 333;
            this.Q[3] = 0;
            n3 = this.a(n2, 0, 49999);
        }
        this.a(n, n3, (byte)3);
    }

    public final void e() {
        int n;
        int n2 = 0;
        int n3 = 0;
        for (int n4 = 1; n4 < 37; n4 = (int)((byte)(n4 + 1))) {
            n2 += this.e[n4];
        }
        if ((n2 /= 36) >= 99) {
            n = 2000;
            this.Q[5] = 5;
            this.f += 70;
        } else if (n2 >= 90) {
            n = 2000;
            this.Q[5] = 4;
            this.f += this.a(n2, 90, 98, 40, 60);
        } else if (n2 >= 80) {
            n = 1000;
            this.Q[5] = 3;
            this.f += this.a(n2, 80, 89, 30, 39);
        } else if (n2 >= 60) {
            n = 1000;
            this.Q[5] = 2;
            this.f += this.a(n2, 60, 79, 10, 29);
        } else if (n2 >= 30) {
            n = 667;
            this.Q[5] = 1;
            n3 = this.a(n2, 30, 59);
            this.f += this.a(n2, 30, 59, 1, 9);
        } else {
            n = 333;
            this.Q[5] = 0;
            n3 = this.a(n2, 0, 29);
        }
        this.a(n, n3, (byte)5);
    }

    public final void f() {
        int n;
        int n2 = 0;
        int n3 = 0;
        for (int n4 = 1; n4 < 37; n4 = (int)((byte)(n4 + 1))) {
            for (byte by = 1; by < this.g[n4]; by = (byte)(by + 1)) {
                n2 += this.k[this.b[n4][by]];
            }
        }
        if ((n2 /= 222) >= 1000) {
            n = 2000;
            this.Q[6] = 5;
            this.f += 100;
        } else if (n2 >= 900) {
            n = 2000;
            this.Q[6] = 4;
            this.f += this.a(n2, 900, 999, 60, 80);
        } else if (n2 >= 800) {
            n = 1000;
            this.Q[6] = 3;
            this.f += this.a(n2, 800, 899, 50, 59);
        } else if (n2 >= 700) {
            n = 1000;
            this.Q[6] = 2;
            this.f += this.a(n2, 700, 799, 20, 49);
        } else if (n2 >= 500) {
            n = 667;
            this.Q[6] = 1;
            n3 = this.a(n2, 500, 699);
            this.f += this.a(n2, 500, 699, 1, 19);
        } else {
            n = 333;
            this.Q[6] = 0;
            n3 = this.a(n2, 0, 499);
        }
        this.a(n, n3, (byte)6);
    }

    public final void g() {
        int n;
        int n2 = -100;
        int n3 = 0;
        for (int n4 = 1; n4 < 36; n4 = (int)((byte)(n4 + 1))) {
            for (byte by = 1; by < this.g[n4]; by = (byte)(by + 1)) {
                n2 += this.v[this.b[n4][by]];
            }
        }
        if ((n2 /= 221) >= 99) {
            n = 2000;
            this.Q[4] = 6;
            this.f += 100;
        } else if (n2 >= 95) {
            n = 2000;
            this.Q[4] = 5;
            this.f += this.a(n2, 95, 98, 50, 70);
        } else if (n2 >= 90) {
            n = 1000;
            this.Q[4] = 4;
            this.f += this.a(n2, 90, 94, 30, 49);
        } else if (n2 >= 80) {
            n = 1000;
            this.Q[4] = 3;
            this.f += this.a(n2, 80, 899, 29, 29);
        } else if (n2 >= 60) {
            n = 750;
            this.Q[4] = 2;
            n3 = this.a(n2, 60, 79);
            this.f += this.a(n2, 60, 79, 10, 19);
        } else if (n2 >= 36) {
            n = 500;
            this.Q[4] = 1;
            n3 = this.a(n2, 36, 59);
            this.f += this.a(n2, 36, 59, 1, 9);
        } else {
            n = 250;
            this.Q[4] = 0;
            n3 = this.a(n2, 0, 35);
        }
        this.a(n, n3, (byte)4);
    }

    public final void h() {
        int n = 0;
        for (int n2 = 1; n2 < 36; n2 = (int)((byte)(n2 + 1))) {
            for (byte by = 1; by < this.g[n2]; by = (byte)(by + 1)) {
                n += this.l[this.b[n2][by]];
            }
        }
        if ((n /= 222) >= 30000) {
            this.f += 100;
        } else if (n >= 20000) {
            this.f += this.a(n, 20000, 29999, 50, 70);
        } else if (n >= 15000) {
            this.f += this.a(n, 15000, 19999, 30, 49);
        } else if (n >= 10000) {
            this.f += this.a(n, 10000, 14999, 29, 29);
        } else if (n >= 7000) {
            this.f += this.a(n, 7000, 9999, 1, 19);
        }
        if (this.ao == 9 && n < 20000) {
            this.ao = (byte)8;
            this.h = 1000;
        }
    }

    public final void i() {
        int n = 0;
        int n2 = 222;
        for (int n3 = 1; n3 < 36; n3 = (int)((byte)(n3 + 1))) {
            for (byte by = 1; by < this.g[n3]; by = (byte)(by + 1)) {
                byte by2 = this.t[this.b[n3][by]];
                if (by2 < 90) {
                    n += by2;
                    continue;
                }
                --n2;
            }
        }
        if ((n /= n2) >= 89) {
            this.f += 100;
        } else if (n == 88) {
            this.f += 80;
        } else if (n == 87) {
            this.f += 60;
        } else if (n >= 86) {
            this.f += 50;
        } else if (n >= 80) {
            this.f += this.a(n, 80, 84, 20, 40);
        } else if (n >= 70) {
            this.f += this.a(n, 70, 79, 1, 19);
        }
        if (this.ao == 9 && n < 80) {
            this.ao = (byte)8;
            this.h = 1000;
        }
    }

    public final void j() {
        int n = 0;
        int n2 = 0;
        if (this.an == 4 || this.an == 6 || this.an == 7) {
            n2 = 1;
        }
        for (int n3 = 1; n3 < 36; n3 = (int)((byte)(n3 + 1))) {
            block11: for (byte by = 1; by < this.g[n3]; by = (byte)(by + 1)) {
                switch (this.w[this.b[n3][by]]) {
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
                switch (this.x[this.b[n3][by]]) {
                    case 30: {
                        n += 55;
                        continue block11;
                    }
                    case 31: {
                        n += 100;
                    }
                }
            }
        }
        this.f += n * 100 / 800;
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
        this.g = this.f / 10;
        if (this.b == 2) {
            this.f = this.a * 3 / 2;
        }
    }

    public final String a(int n) {
        return this.a[n][this.Q[n]];
    }

    public final byte a(short s) {
        for (byte by = 15; by > 0; by = (byte)(by - 1)) {
            if (by * by > s) continue;
            return by;
        }
        return 0;
    }

    public final boolean a(short s, short s2) {
        int n = this.t[s] + 20 - this.t[s2];
        if (n < 0) {
            return false;
        }
        n = this.a((short)((byte)n)) * 10;
        return this.a() % 100 <= n;
    }

    public final boolean a() {
        if (this.a(this.a, this.b)) {
            this.S = (byte)20;
            return true;
        }
        return false;
    }

    public final boolean b() {
        int n = this.s[this.b] + this.r[this.b] - this.s[this.a] - this.r[this.a] + 30;
        n = this.a((short)n) * 10;
        return this.a() % 51 <= n;
    }

    public final boolean a(byte by) {
        this.N = (byte)(this.N - this.a(by));
        switch (by) {
            case 0: {
                return this.a();
            }
            case 1: {
                return this.b();
            }
            case 2: {
                this.S = (byte)51;
                return true;
            }
            case 3: {
                this.S = (byte)68;
                return true;
            }
            case 4: {
                this.S = (byte)83;
                return true;
            }
            case 5: {
                return true;
            }
        }
        return false;
    }

    public final byte a(byte by) {
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
            byte[] byArray = this.h[by2];
            byte by3 = by;
            byArray[by3] = (byte)(byArray[by3] | 0x80);
            return;
        }
        byte[] byArray = this.h[by2];
        byte by4 = by;
        byArray[by4] = (byte)(byArray[by4] | 0x40);
    }

    public final int a(boolean bl, byte by, byte by2, int n, byte by3) {
        if (bl) {
            for (byte by4 = by; by4 < by2; by4 = (byte)(by4 + 1)) {
                this.m[0][by4] = 3;
                this.l[0][by4] = by3;
                this.o[0][by4] = (byte)(n / (this.J - by4));
                n -= this.o[0][by4];
                this.d(true, by4);
                this.b[0][by4] = true;
            }
        } else {
            for (byte by5 = by; by5 < by2; by5 = (byte)(by5 + 1)) {
                this.m[1][by5] = 2;
                this.l[1][by5] = by3;
                this.o[1][by5] = (byte)(n / (this.K - by5));
                n -= this.o[1][by5];
                this.d(false, by5);
                this.b[1][by5] = true;
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
            s2 = this.k[this.a];
            by = this.J;
            s = this.a;
        } else {
            s2 = this.k[this.b];
            by = this.K;
            s = this.b;
        }
        switch (this.o[s]) {
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

    public final byte a(boolean bl) {
        byte by = 0;
        if (bl) {
            for (byte by2 = 1; by2 < this.J; by2 = (byte)(by2 + 1)) {
                if (this.l[0][by2] == 1) {
                    this.l[0][by2] = 2;
                }
                if (this.l[0][by2] != 1 && this.l[0][by2] != 2) continue;
                by = (byte)(by + 1);
            }
        } else {
            for (byte by3 = 1; by3 < this.K; by3 = (byte)(by3 + 1)) {
                if (this.l[1][by3] == 1) {
                    this.l[1][by3] = 2;
                }
                if (this.l[1][by3] != 1 && this.l[1][by3] != 2) continue;
                by = (byte)(by + 1);
            }
        }
        return by;
    }

    public final void a(boolean bl, byte by) {
        if (bl) {
            for (byte by2 = 1; by2 <= by; by2 = (byte)(by2 + 1)) {
                this.j[0][by2] = (byte)(8 - (by2 - 1) / 2);
                this.k[0][by2] = (byte)((by2 - 1) % 2 * 6);
                this.a(this.j[0][by2], this.k[0][by2], false);
            }
            return;
        }
        for (byte by3 = 1; by3 <= by; by3 = (byte)(by3 + 1)) {
            this.j[1][by3] = (byte)(7 + (by3 - 1) / 2);
            this.k[1][by3] = (byte)((by3 - 1) % 2 * 6);
            this.a(this.j[1][by3], this.k[1][by3], true);
        }
    }

    public final void b(boolean bl, byte by) {
        if (bl) {
            for (byte by2 = 1; by2 <= by; by2 = (byte)(by2 + 1)) {
                this.j[0][this.J - by2] = 2;
                this.k[0][this.J - by2] = by2;
                this.a(this.j[0][this.J - by2], this.k[0][this.J - by2], false);
            }
            return;
        }
        for (byte by3 = 1; by3 <= by; by3 = (byte)(by3 + 1)) {
            this.j[1][this.K - by3] = 13;
            this.k[1][this.K - by3] = by3;
            this.a(this.j[1][this.K - by3], this.k[1][this.K - by3], true);
        }
    }

    public final void c(boolean bl, byte by) {
        byte by2 = bl ? this.J : this.K;
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
                for (byte by3 = 1; by3 < by; by3 = (byte)(by3 + 1)) {
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
                    this.j[0][by3] = (byte)(n2 + 1);
                    this.k[0][by3] = n;
                    this.a(this.j[0][by3], this.k[0][by3], false);
                }
                break;
            }
            case 1: {
                boolean bl = false;
                boolean bl3 = false;
                byte by6 = 0;
                byte by7 = 0;
                for (byte by8 = 1; by8 < by; by8 = (byte)(by8 + 1)) {
                    by6 = (byte)(3 - (by8 - 1) / 4);
                    by7 = by8 % 2 == 1 ? (byte)(6 - (by8 + 1) / 4) : (byte)(by8 / 4);
                    this.j[0][by8] = (byte)(by6 + 1);
                    this.k[0][by8] = by7;
                    this.a(this.j[0][by8], this.k[0][by8], false);
                }
                break;
            }
            case 2: {
                boolean bl = false;
                boolean bl4 = false;
                byte by9 = 0;
                for (byte by10 = 1; by10 < by; by10 = (byte)(by10 + 1)) {
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
                    this.j[0][by10] = (byte)(n + 1);
                    this.k[0][by10] = by11;
                    this.a(this.j[0][by10], this.k[0][by10], false);
                }
                break;
            }
            case 3: {
                boolean bl = false;
                boolean bl5 = false;
                int n = 0;
                int n3 = 0;
                byte by12 = 0;
                for (byte by13 = 1; by13 < by; by13 = (byte)(by13 + 1)) {
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
                    this.j[0][by13] = (byte)(n + 1);
                    this.k[0][by13] = n3;
                    this.a(this.j[0][by13], this.k[0][by13], false);
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
                for (byte by3 = 1; by3 < by; by3 = (byte)(by3 + 1)) {
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
                    this.j[1][by3] = (byte)(n2 - 1);
                    this.k[1][by3] = n;
                    this.a(this.j[1][by3], this.k[1][by3], true);
                }
                break;
            }
            case 1: {
                boolean bl = false;
                boolean bl3 = false;
                byte by6 = 0;
                byte by7 = 0;
                for (byte by8 = 1; by8 < by; by8 = (byte)(by8 + 1)) {
                    by6 = (byte)(12 + (by8 - 1) / 4);
                    by7 = by8 % 2 == 1 ? (byte)(6 - (by8 + 1) / 4) : (byte)(by8 / 4);
                    this.j[1][by8] = (byte)(by6 - 1);
                    this.k[1][by8] = by7;
                    this.a(this.j[1][by8], this.k[1][by8], true);
                }
                break;
            }
            case 2: {
                boolean bl = false;
                boolean bl4 = false;
                byte by9 = 0;
                for (byte by10 = 1; by10 < by; by10 = (byte)(by10 + 1)) {
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
                    this.j[1][by10] = (byte)(n - 1);
                    this.k[1][by10] = by11;
                    this.a(this.j[1][by10], this.k[1][by10], true);
                }
                break;
            }
            case 3: {
                boolean bl = false;
                boolean bl5 = false;
                int n = 0;
                int n3 = 0;
                byte by12 = 0;
                for (byte by13 = 1; by13 < by; by13 = (byte)(by13 + 1)) {
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
                    this.j[1][by13] = (byte)(n - 1);
                    this.k[1][by13] = n3;
                    this.a(this.j[1][by13], this.k[1][by13], true);
                }
                break;
            }
        }
    }

    public final void a(byte by) {
        this.l = new byte[2][];
        this.j = new byte[2][];
        this.k = new byte[2][];
        this.m = new byte[2][];
        this.o = new byte[2][];
        this.a = new boolean[2][];
        this.b = new boolean[2][];
        this.e = new short[2][];
        this.f = new short[2][];
        this.n = new byte[2][];
        this.l[0] = new byte[this.J];
        this.j[0] = new byte[this.J];
        this.k[0] = new byte[this.J];
        this.m[0] = new byte[this.J];
        this.o[0] = new byte[this.J];
        this.a[0] = new boolean[this.J];
        this.b[0] = new boolean[this.J];
        this.e[0] = new short[this.J];
        this.f[0] = new short[this.J];
        this.n[0] = new byte[this.J];
        this.l[1] = new byte[this.K];
        this.j[1] = new byte[this.K];
        this.k[1] = new byte[this.K];
        this.m[1] = new byte[this.K];
        this.o[1] = new byte[this.K];
        this.a[1] = new boolean[this.K];
        this.b[1] = new boolean[this.K];
        this.e[1] = new short[this.K];
        this.f[1] = new short[this.K];
        this.n[1] = new byte[this.K];
        this.l[0][0] = 0;
        this.j[0][0] = 1;
        this.k[0][0] = 3;
        this.m[0][0] = 3;
        this.o[0][0] = 100;
        this.d(true, (byte)0);
        this.a(this.j[0][0], this.k[0][0], false);
        this.a(true);
        if (!this.h && this.i) {
            this.X = this.a(true);
            this.c(true, this.X);
        } else if (this.h || !this.i) {
            this.a(this.J, by);
        }
        this.l[1][0] = 0;
        this.j[1][0] = (byte)(this.L - 2);
        this.k[1][0] = 3;
        this.m[1][0] = 2;
        this.o[1][0] = 100;
        this.d(false, (byte)0);
        this.a(this.j[1][0], this.k[1][0], true);
        this.a(false);
        if (this.h && this.i) {
            this.X = this.a(false);
            this.c(false, this.X);
            return;
        }
        this.b(this.K, (byte)(this.a() % 4));
    }

    public final void l() {
        byte by;
        for (by = 1; by < 4; by = (byte)((byte)(by + 1))) {
            this.x[by] = 0;
            this.y[by] = 0;
        }
        this.x[0] = this.r[this.a];
        for (by = 1; by < this.J; by = (byte)(by + 1)) {
            byte by2 = this.l[0][by];
            this.x[by2] = (short)(this.x[by2] + this.o[0][by]);
        }
        this.y[0] = this.r[this.b];
        for (by = 1; by < this.K; by = (byte)(by + 1)) {
            byte by3 = this.l[1][by];
            this.y[by3] = (short)(this.y[by3] + this.o[1][by]);
        }
    }

    public final void d(boolean bl, byte by) {
        short s;
        int n;
        if (bl) {
            n = 0;
            s = this.a;
        } else {
            n = 1;
            s = this.b;
        }
        this.a[n][by] = false;
        this.b[n][by] = true;
        if (this.l[n][by] == 0) {
            this.e[n][by] = (short)(this.s[s] + this.s[s] * this.B[this.w[s]] / 100);
        } else if (this.l[n][by] == 1) {
            this.e[n][by] = this.s[s];
        } else if (this.l[n][by] == 2 || this.l[n][by] == 3) {
            this.e[n][by] = (short)(this.s[s] * 3 / 5);
        }
        if (this.e[n][by] == 0) {
            this.e[n][by] = 1;
        }
        if (this.b == 0) {
            this.f[n][by] = (short)(this.s[s] + this.s[s] * this.B[this.x[s]] / 100);
            return;
        }
        this.f[n][by] = this.e[n][by];
    }

    public final void m() {
        this.N = (byte)(this.t[this.a] * 13 / 100);
        this.O = (byte)(this.t[this.b] * 13 / 100);
    }

    public final void c(byte by, byte by2) {
        byte by3 = (byte)(this.h[this.k[by][by2]][this.j[by][by2]] & 0xC0);
        byte[] byArray = this.h[this.k[by][by2]];
        byte by4 = this.j[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.k[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] - 1);
        this.m[by][by2] = 0;
        byte[] byArray3 = this.h[this.k[by][by2]];
        byte by6 = this.j[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void d(byte by, byte by2) {
        byte by3 = (byte)(this.h[this.k[by][by2]][this.j[by][by2]] & 0xC0);
        byte[] byArray = this.h[this.k[by][by2]];
        byte by4 = this.j[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.k[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] + 1);
        this.m[by][by2] = 1;
        byte[] byArray3 = this.h[this.k[by][by2]];
        byte by6 = this.j[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void e(byte by, byte by2) {
        byte by3 = (byte)(this.h[this.k[by][by2]][this.j[by][by2]] & 0xC0);
        byte[] byArray = this.h[this.k[by][by2]];
        byte by4 = this.j[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.j[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] - 1);
        this.m[by][by2] = 2;
        byte[] byArray3 = this.h[this.k[by][by2]];
        byte by6 = this.j[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public final void f(byte by, byte by2) {
        byte by3 = (byte)(this.h[this.k[by][by2]][this.j[by][by2]] & 0xC0);
        byte[] byArray = this.h[this.k[by][by2]];
        byte by4 = this.j[by][by2];
        byArray[by4] = (byte)(byArray[by4] & 0x32);
        byte[] byArray2 = this.j[by];
        byte by5 = by2;
        byArray2[by5] = (byte)(byArray2[by5] + 1);
        this.m[by][by2] = 3;
        byte[] byArray3 = this.h[this.k[by][by2]];
        byte by6 = this.j[by][by2];
        byArray3[by6] = (byte)(byArray3[by6] | by3);
    }

    public a() {
        this.b = new byte[9];
        this.a = new byte[9][];
        this.c = new byte[]{0, 5, 11, 3, 5, 6, 1, 5};
        this.a = new short[][]{null, {161, 5, 6, 181, 200}, {211, 225, 215, 213, 217, 56, 58, 204, 210, 63, 57}, {37, 8, 34}, {191, 138, 201, 155, 208}, {26, 25, 18, 19, 28, 21}, {40}, {140, 2, 172, 190, 160}};
        this.d = new byte[37];
        this.a = new String[37];
        this.b = new short[37];
        this.c = new short[37];
        this.e = new byte[37];
        this.d = new short[37];
        this.e = new short[37];
        this.f = new short[37];
        this.g = new short[37];
        this.a = new int[37];
        this.f = new byte[37];
        this.g = new byte[37];
        this.h = new byte[37];
        this.i = new byte[37];
        this.h = new short[37];
        this.i = new short[37];
        this.j = new byte[37];
        this.k = new byte[37];
        this.l = new byte[37];
        this.m = new byte[37];
        this.b = new short[37][10];
        this.c = new short[37][];
        this.d = new short[37][];
        this.b = new byte[][]{null, {3, 5}, {4}, {1}, {2, 8, 15}, {1, 9}, {8, 9, 12}, {11}, {4, 6, 12}, {5, 6, 10, 14}, {9, 13}, {7, 15, 16, 18}, {6, 8, 14, 15}, {10, 17, 19, 20}, {9, 12, 15, 19, 23}, {4, 11, 12, 14, 21, 18}, {11}, {13, 22}, {11, 15, 21, 24}, {13, 14, 20, 23, 25}, {13, 19, 22}, {15, 18, 23}, {17, 20, 32, 28}, {14, 19, 21, 31, 34}, {18, 27}, {19, 30, 32, 34}, {27, 31}, {24, 26}, {22, 29}, {28}, {25, 32, 34}, {23, 26, 34, 35}, {22, 25, 30}, {35, 36}, {23, 25, 30, 31}, {31, 33}, {33}};
        this.n = new byte[]{0, 2, 1, 1, 3, 2, 3, 1, 3, 4, 2, 4, 4, 4, 5, 6, 1, 2, 4, 5, 3, 3, 4, 5, 2, 4, 2, 2, 2, 1, 3, 4, 3, 2, 4, 2, 1};
        this.j = new short[232];
        this.b = new String[232];
        this.o = new byte[232];
        this.p = new byte[232];
        this.k = new short[232];
        this.q = new byte[232];
        this.r = new byte[232];
        this.s = new byte[232];
        this.t = new byte[232];
        this.u = new byte[232];
        this.v = new byte[232];
        this.l = new short[232];
        this.w = new byte[232];
        this.x = new byte[232];
        this.m = new short[232];
        this.y = new byte[232];
        this.a = new boolean[232];
        this.z = new byte[7];
        this.A = new byte[7];
        this.b = (byte)2;
        this.n = new short[]{192, 192, 194, 195, 196, 197, 197, 198, 199, 199, 200, 201, 202, 203, 203, 203, 204, 204, 205, 205, 208, 208, 209, 210, 210, 210};
        this.o = new short[]{138, 140, 160, 172, 179, 190, 191, 194, 200, 201, 204, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 222, 225, 226, 227};
        this.p = new short[]{69, 1, 1, 1, 57, 1, 69, 5, 4, 69, 61, 61, 61, 6, 61, 63, 61, 5, 61, 5, 162, 63, 162, 211, 161, 164};
        this.c = new String[]{"\u77ed\u5251", "\u98de\u5251", "\u957f\u5251", "\u7834\u65a9\u5251", "\u5927\u5251", "\u4e03\u661f\u5251", "\u9752\u91ed\u5251", "\u501a\u5929\u5251", "\u96cd\u5200", "\u7709\u5c16\u5200", "\u4e09\u5c16\u5200", "\u94a9\u9570\u5200", "\u5043\u6708\u5200", "\u51e4\u5634\u5200", "\u9f99\u7259\u5200", "\u5173\u738b\u5200", "\u77db", "\u53cc\u621f", "\u5355\u94a9\u67aa", "\u6218\u65a7", "\u53cc\u94a9\u67aa", "\u65b9\u5929\u5316\u621f", "\u72fc\u7259\u67aa", "\u86c7\u77db", "\u7eb6\u5dfe", "\u7b3c\u624b", "\u515c", "\u786c\u76ae", "\u9e64\u6c05", "\u7532\u80c4", "\u5723\u8005\u4e4b\u8863", "\u9f99\u9cde\u5b9d\u94e0"};
        this.q = new short[]{50, 70, 120, 180, 250, 0, 0, 0, 60, 100, 150, 200, 240, 260, 0, 500, 80, 100, 120, 200, 250, 300, 500, 500, 50, 80, 100, 150, 200, 250, 500, 0};
        this.B = new byte[]{5, 7, 12, 18, 25, 50, 50, 50, 6, 10, 15, 20, 24, 26, 45, 50, 8, 10, 12, 20, 25, 30, 50, 50, 5, 8, 10, 15, 20, 25, 50, 60};
        this.c = new byte[][]{{0, 1, 2, 3}, {1, 2, 3, 4}, {1, 2, 3, 4}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}};
        this.d = new byte[][]{{8, 9, 10, 11}, {10, 11, 12, 13}, {10, 11, 12, 13}, {8, 9, 10, 11}, {8, 9, 10, 15}, {8, 9, 10, 11}};
        this.e = new byte[][]{{16, 17, 18, 19}, {18, 19, 20, 21}, {18, 19, 20, 21}, {16, 17, 18, 23}, {17, 18, 19, 20}, {16, 17, 18, 22}};
        this.f = new byte[][]{{24, 25, 26, 27}, {26, 27, 28, 29}, {26, 27, 28, 30}, {24, 25, 26, 27}, {24, 25, 26, 27}, {24, 25, 26, 27}};
        this.r = new short[3];
        this.C = new byte[3];
        this.s = new short[3];
        this.d = new String[3];
        this.a = new Random();
        this.t = new short[10];
        this.D = new byte[8];
        this.c = (short)189;
        this.u = new short[10];
        this.E = new byte[8];
        this.F = new byte[37];
        this.v = new short[10];
        this.w = new short[10];
        this.g = new byte[19][32];
        this.y = (byte)32;
        this.z = (byte)19;
        this.I = new byte[10];
        this.J = new byte[10];
        this.K = new byte[10];
        this.L = new byte[10];
        this.M = new byte[10];
        this.N = new byte[10];
        this.h = new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        this.L = (byte)16;
        this.M = (byte)7;
        this.i = new byte[][]{{2, 100, 0}, {2, 3, 3, 99, 4, 1, 0}, {5, 1}, {2, 3, 6, 3, 83, 3, 84, 85, 0, 1}, {2, 8}, {9, 3, 91, 1, 0}, {3, 10, 1}, {3, 3, 98, 1, 0}, {3, 11, 1, 0}, {3, 97, 0}, {3, 12, 96, 1, 0}, {3, 12, 95, 1, 0}, {2, 16, 1, 0}, {3, 13, 94, 1, 0}, {3, 12, 93, 1, 0}, {14, 1, 0}, {3, 15, 1, 0}, null, {3, 89, 1, 0}, {3, 88, 1, 0}, {2, 3, 3, 92, 1}, {2, 3, 3, 90, 1, 0}, {17, 87, 0}, {17, 86}};
        this.x = new short[]{0, 0, 0, 0};
        this.y = new short[]{0, 0, 0, 0};
        this.O = new byte[]{0, 0, 0, 0};
        this.Q = new byte[7];
        this.a = new String[][]{{"\u6781\u5ea6\u8d2b\u7620\u7684\u571f\u5730, \u6839\u672c\u65e0\u6cd5\u751f\u4ea7\u51fa\u7cae\u98df,\u997f\u6b8d\u904d\u91ce,\u767e\u59d3\u82e6\u4e0d\u582a\u8a00", "\u571f\u5730\u8d44\u6e90\u975e\u5e38\u532e\u4e4f,\u7cae\u98df\u6536\u6210\u975e\u5e38\u4f4e\u4e0b,\u5404\u5730\u767e\u59d3\u997f\u6b7b\u73b0\u8c61\u65f6\u6709\u53d1\u751f,\u65e0\u6cd5\u7ee7\u7eed\u751f\u6d3b\u4e0b\u53bb", "\u519c\u4e1a\u53d1\u5c55\u8f83\u4e3a\u843d\u540e,\u7cae\u98df\u4ea7\u91cf\u4ecd\u65e0\u6cd5\u6ee1\u8db3\u767e\u59d3\u7684\u751f\u6d3b,\u5404\u5730\u7530\u91cc,\u5730\u91cc\u4e00\u7247\u83dc\u8272", "\u968f\u7740\u519c\u4e1a\u5730\u9010\u6b65\u53d1\u5c55,\u7cae\u98df\u4ea7\u91cf\u57fa\u672c\u8fbe\u5230\u4e86\u767e\u59d3\u751f\u6d3b\u5730\u9700\u6c42", "\u968f\u7740\u571f\u5730\u5730\u4e0d\u65ad\u5f00\u53d1,\u7cae\u98df\u4ea7\u91cf\u5df2\u8f83\u4e3a\u5bcc\u88d5,\u767e\u59d3\u5403\u996d\u95ee\u9898\u5df2\u5b8c\u5168\u89e3\u51b3\u4e86", "\u65b0\u7684\u519c\u4e1a\u5de5\u5177\u5730\u4e0d\u65ad\u51fa\u73b0,\u5728\u571f\u5730\u4f5c\u4e1a\u4e2d\u7684\u9010\u6e10\u666e\u53ca,\u5404\u5730\u7cae\u98df\u5df2\u6781\u4e3a\u5145\u88d5", "\u519c\u4e1a\u53d1\u5c55\u7a7a\u524d,\u7cae\u98df\u5e74\u5e74\u4e30\u4ea7,\u4eba\u6c11\u8863\u98df\u65e0\u5fe7"}, {"\u6781\u5ea6\u843d\u540e\u7684\u5546\u4e1a,\u4eba\u4eec\u6700\u57fa\u672c\u7684\u7269\u54c1\u4ea4\u6362\u90fd\u65e0\u6cd5\u8fdb\u884c.\u56fd\u5bb6\u8d2b\u82e6\u4e0d\u582a", "\u5546\u4e1a\u8d44\u6e90\u975e\u5e38\u8d2b\u4e4f,\u4eba\u4eec\u65e0\u6cd5\u5f97\u5230\u6700\u57fa\u672c\u7684\u751f\u6d3b\u7528\u54c1,\u751f\u6d3b\u975e\u5e38\u8270\u8f9b", "\u5546\u4e1a\u53d1\u5c55\u4ecd\u8f83\u4e3a\u843d\u540e,\u5546\u52a1\u6d3b\u52a8\u8f83\u5c11, \u767e\u59d3\u751f\u6d3b\u6c34\u5e73\u975e\u5e38\u4f4e\u4e0b", "\u968f\u7740\u5546\u4e1a\u5730\u9010\u6b65\u53d1\u5c55,\u5546\u52a1\u6d3b\u52a8\u57fa\u672c\u8fbe\u5230\u4e86\u767e\u59d3\u751f\u6d3b\u5730\u9700\u6c42", "\u968f\u7740\u5b98\u5e9c\u7684\u4e0d\u65ad\u91cd\u89c6,\u5546\u4e1a\u53d1\u5c55\u5df2\u8f83\u4e3a\u53d1\u8fbe,\u56fd\u5bb6\u7ecf\u6d4e\u72b6\u51b5\u6709\u4e86\u5f88\u5927\u6539\u5584", "\u4e0e\u5916\u56fd\u7684\u7ecf\u6d4e\u5f80\u6765,\u4f7f\u5f97\u56fd\u5bb6\u5546\u52a1\u53d1\u5c55\u6781\u5ea6\u53d1\u8fbe,\u767e\u59d3\u751f\u6d3b\u5728\u4e86\u4e00\u4e2a\u5bcc\u88d5\u7684\u56fd\u5bb6\u4e2d", "\u56fd\u5bb6\u7684\u7ecf\u6d4e\u53d1\u5c55\u4e3e\u4e16\u7f55\u89c1, \u65b0\u7684\u9886\u57df\u7684\u7ecf\u6d4e\u5f80\u6765\u4e0d\u65ad\u589e\u5f3a\u672c\u56fd\u7684\u7ecf\u6d4e\u5b9e\u529b"}, {"\u761f\u75ab,\u8757\u866b,\u65f1\u6d9d,\u5404\u79cd\u707e\u5bb3\u904d\u5730,\u6c11\u4e0d\u804a\u751f", "\u761f\u75ab\u7b49\u8fd9\u79cd\u707e\u5bb3\u4ecd\u65f6\u6709\u53d1\u751f,\u767e\u59d3\u707e\u96be\u6df1\u91cd", "\u7531\u4e8e\u65b0\u56fd\u5bb6\u5b98\u5e9c\u7684\u4e00\u5b9a\u91cd\u89c6,\u707e\u5bb3\u5728\u4e00\u5b9a\u7a0b\u5ea6\u4e0a\u5f97\u5230\u4e86\u904f\u5236", "\u7ecf\u8fc7\u5b98\u5e9c\u7684\u4e0d\u65ad\u52aa\u529b,\u707e\u5bb3\u9632\u5fa1\u63aa\u65bd\u9010\u6e10\u5b9e\u65bd,\u767e\u59d3\u57fa\u672c\u8fdc\u79bb\u4e86\u707e\u5bb3\u75db\u82e6", "\u5927\u575d\u5efa\u6210,\u5404\u79cd\u6cbb\u7406\u707e\u5bb3\u63aa\u65bd\u4e95\u7136\u6709\u5e8f,\u767e\u59d3\u518d\u65e0\u707e\u5bb3\u4e4b\u5fe7"}, {"\u7ecf\u8fc7\u591a\u5e74\u7684\u6218\u4e89,\u5404\u5730\u4e00\u7247\u8352\u829c,\u5404\u57ce\u5e02\u4eba\u4e01\u7a00\u5c11,\u767e\u91cc\u96be\u89c1\u4e00\u4eba", "\u6218\u540e\u6062\u590d\u4ecd\u6ca1\u6709\u8db3\u591f\u7684\u8fdb\u884c,\u5404\u57ce\u5e02\u52b3\u52a8\u529b\u8d44\u6e90\u4ecd\u7a0d\u663e\u4e0d\u8db3", "\u6218\u4e89\u7684\u785d\u70df\u9010\u6e10\u8fdc\u53bb,\u5404\u57ce\u5e02\u4eba\u53e3\u8d44\u6e90\u5df2\u5b8c\u5168\u6ee1\u8db3\u4e86\u52b3\u52a8\u7684\u9700\u6c42", "\u56fd\u5bb6\u5f00\u59cb\u91cd\u89c6\u4e86\u57ce\u5e02\u7684\u5efa\u8bbe,\u57ce\u5e02\u89c4\u6a21\u53d1\u5c55\u7a33\u5b9a,\u5927\u578b\u57ce\u5e02\u9010\u6b65\u5174\u8d77", "\u7ecf\u8fc7\u56fd\u5bb6\u591a\u65b9\u9762\u52aa\u529b,\u57ce\u5e02\u5411\u90fd\u5e02\u5316\u53d1\u5c55\u5f00\u59cb,\u5404\u57ce\u5e02\u6b23\u6b23\u5411\u8363", "\u5404\u5730\u53d1\u5c55\u5e73\u8861,\u90fd\u5e02\u5316\u53d1\u5c55\u5b8c\u6210,\u56fd\u5bb6\u53d1\u5c55\u7a7a\u524d,\u767e\u59d3\u5b89\u5c45\u4e50\u4e1a"}, {"\u90e8\u4e0b\u91ce\u5fc3\u52c3\u52c3,\u53db\u53d8,\u53cd\u4e71\u4e8b\u4ef6\u5c42\u51fa\u4e0d\u7a77,\u56fd\u5bb6\u9a6c\u4e0a\u5c06\u88ab\u98a0\u8986", "\u5c5e\u4e0b\u4eba\u5fc3\u6d6e\u52a8,\u6d41\u8a00\u56db\u8d77,\u67d0\u4e9b\u6b66\u5c06\u7684\u53db\u4e71\u4f3c\u4e4e\u5df2\u5728\u773c\u524d", "\u5927\u591a\u6b66\u5c06\u5fe0\u4e8e\u541b\u4e3b,\u4f46\u5978\u81e3\u5f53\u9053,\u53db\u53d8\u7684\u79cd\u5b50\u4ecd\u8fdc\u672a\u6d88\u5931", "\u541b\u4e3b\u8d4f\u7f5a\u5206\u660e,\u6069\u5a01\u5e76\u65bd,\u4e0b\u5c5e\u6b66\u5c06\u5bf9\u56fd\u5bb6\u8f83\u4e3a\u5fe0\u8bda", "\u541b\u4e3b\u5584\u4e8e\u5fa1\u4eba\u4e4b\u9053, \u6b66\u5c06\u4eec\u5bf9\u541b\u4e3b, \u56fd\u5bb6\u5fe0\u5fc3\u803f\u803f", "\u4ee5\u6069\u4e49\u8457\u79f0\u7684\u65b0\u541b\u4e3b,\u5f97\u5230\u4e86\u5c5e\u4e0b\u8a93\u6b7b\u6548\u5fe0,\u5404\u6b66\u5c06\u7edd\u65e0\u4e8c\u5fc3", "\u7ecf\u8fc7\u591a\u5e74\u7684\u540c\u751f\u5171\u6b7b,\u81e3\u50da\u4eec\u7528\u9c9c\u8840\u6765\u53d1\u8a93,\u5b50\u5b50\u5b59\u5b59\u8a93\u6b7b\u6548\u5fe0\u541b\u4e3b"}, {"\u65b0\u56fd\u5bb6\u7684\u6240\u4f5c\u6240\u4e3a,\u767e\u59d3\u6df1\u6076\u75db\u75be,\u5404\u5730\u70fd\u70df\u56db\u8d77,\u8d77\u4e49\u904d\u5730,\u65e0\u6cd5\u63a7\u5236", "\u65b0\u56fd\u5bb6\u5e76\u6ca1\u6709\u5b9e\u65bd\u4ec1\u653f,\u767e\u59d3\u5bf9\u65b0\u56fd\u5bb6\u975e\u5e38\u4e0d\u6ee1, \u8d77\u4e49\u7684\u79cd\u5b50\u56db\u5904\u840c\u53d1", "\u65b0\u56fd\u5bb6\u7684\u4ec1\u653f\u601d\u60f3\u9010\u6e10\u4f20\u64ad,\u9010\u6e10\u4f7f\u5f97\u767e\u59d3\u5bf9\u65b0\u7684\u56fd\u5bb6\u57fa\u672c\u4e0a\u8ba4\u53ef\u4e86", "\u541b\u4e3b\u4e00\u5411\u4ee5\u4ec1\u653f\u800c\u95fb\u540d,\u56fd\u5bb6\u5efa\u7acb\u4ee5\u6765,\u5f97\u5230\u4e86\u5404\u5730\u767e\u59d3\u7684\u652f\u6301\u548c\u6b22\u8fce", "\u541b\u4e3b\u8d24\u540d\u8fdc\u64ad,\u7ba1\u7406\u56fd\u5bb6\u653f\u7b56\u5f97\u5f53,\u65bd\u4ee5\u767e\u59d3\u7684\u4ec1\u653f\u6df1\u5165\u4eba\u5fc3", "\u5168\u56fd\u767e\u59d3\u575a\u51b3\u62e5\u62a4\u65b0\u7684\u56fd\u5bb6,\u541b\u4e3b\u5df2\u6210\u4e3a\u5927\u4f17\u7684\u5076\u50cf,\u548c\u7cbe\u795e\u7825\u67f1"}, {"\u591a\u5e74\u6218\u4e89\u9020\u6210\u56fd\u5bb6\u5175\u7532\u6781\u5ea6\u4e0d\u8db3,\u8fdc\u8fdc\u65e0\u6cd5\u62b5\u5fa1\u5916\u654c\u4fb5\u7565,\u968f\u65f6\u6709\u706d\u56fd\u5371\u9669", "\u6218\u540e\u5175\u7532\u4ecd\u7136\u4e0d\u8db3,\u65e0\u6cd5\u62b5\u5fa1\u5308\u5974\u7b49\u5916\u654c\u7684\u5927\u89c4\u6a21\u8fdb\u653b,\u56fd\u5bb6\u5c8c\u5c8c\u53ef\u5371", "\u5308\u5974,\u5916\u65cf\u864e\u89c6\u803d\u803d,\u5168\u90e8\u5175\u4e01\u5df2\u6d3e\u5f80\u524d\u7ebf,\u4e0e\u5f3a\u5927\u7684\u654c\u4eba\u8fbe\u6210\u5fae\u5f31\u7684\u519b\u4e8b\u5e73\u8861", "\u5728\u6218\u4e89\u79ef\u7d2f\u4e86\u8db3\u591f\u519b\u4e8b\u7ecf\u9a8c,\u56fd\u5bb6\u519b\u4e8b\u5b9e\u529b\u7a33\u5b9a,\u5728\u968f\u540e\u7684\u6218\u4e89\u4e2d\u6570\u6b21\u6218\u8d25\u5916\u4fb5\u7565\u8005", "\u6b66\u5668,\u9635\u6cd5\u7684\u66f4\u65b0,\u5c06\u9886\u7684\u57f9\u517b,\u4f7f\u5f97\u519b\u4e8b\u5b9e\u529b\u5f3a\u5927,\u5f81\u670d\u4e86\u8bb8\u591a\u5468\u56f4\u7684\u5c0f\u56fd\u5bb6", "\u519b\u4e8b\u5b9e\u529b\u7a7a\u524d,\u5bf9\u5468\u8fb9\u56fd\u5bb6\u5f62\u6210\u4e86\u5f3a\u5927\u7684\u9707\u6151\u4f5c\u7528.\u5404\u56fd\u7eb7\u7eb7\u9063\u4f7f\u8fdb\u8d21,\u6216\u4ece\u5c5e.\u5f3a\u5927\u7684\u5e1d\u56fd\u5df2\u7ecf\u4ea7\u751f"}};
        this.z = new short[3];
        this.R = new byte[10240];
    }

    public final synchronized void a(b b2) {
        this.a = b2;
        this.d = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void n() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.a.a > 0) {
                this.a.k();
            }
            if (this.c > 0) {
                return;
            }
            this.a.a = 0;
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void o() {
        this.ao();
        this.a.a((byte)1);
    }

    public final void p() {
        this.a.a((byte)18);
    }

    public final void b(byte by) {
        if (by == 5) {
            this.a.a((byte)1);
            return;
        }
        this.a = by;
        this.aP();
        this.c = (byte)2;
    }

    public final int a() {
        return Math.abs(this.a.nextInt());
    }

    public final boolean a(byte by, byte by2) {
        for (byte by3 = 0; by3 < this.n[by]; by3 = (byte)(by3 + 1)) {
            if (this.b[by][by3] != by2) continue;
            return true;
        }
        return false;
    }

    public final void q() {
        int n = this.a() % 8 + 1;
        int n2 = 0;
        while (n > 0 && (n -= this.D[n2]) != 0) {
            if (++n2 != 8) continue;
            n2 = 0;
        }
        this.D[n2] = 0;
        this.e = this.a[n2 + 1];
    }

    public final byte b(byte by) {
        int n = this.b[by];
        if (n < 3) {
            n = 3;
        } else if (n > 8) {
            n = 8;
        }
        return (byte)n;
    }

    public final void r() {
        for (byte by = 0; by < this.b[this.d]; by = (byte)(by + 1)) {
            if (this.b[this.a[this.d][by]] != this.c[this.a[this.d][by]]) continue;
            this.i = this.g = this.a[this.d][by];
            return;
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
        this.g[s][by] = Byte.parseByte(string);
    }

    public final void a(String string) {
        int n = 0;
        byte by = 0;
        int n2 = 0;
        String string2 = null;
        try {
            for (int i = 0; i < string.length(); ++i) {
                if (string.charAt(i) != ',' && string.charAt(i) != '\n' && string.charAt(i) != '\r') continue;
                if (i != n) {
                    string2 = string.substring(n, i);
                    this.a((short)n2, by, string2);
                }
                n = i + 1;
                if (string.charAt(i) == '\n') {
                    n2 = (byte)(n2 + 1);
                    by = 0;
                    continue;
                }
                by = (byte)(by + 1);
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void s() {
        String string = this.a("/res/map/" + this.t + ".map");
        this.a(string);
    }

    public final int a(byte by) {
        int n = by & 0xC0;
        if (n == 64) {
            return 1;
        }
        if (n == 128) {
            return 2;
        }
        return 0;
    }

    public final void t() {
        byte by;
        byte by2 = 0;
        byte by3 = 0;
        byte by4 = 0;
        int n = 0;
        block0: for (by = 0; by < 32; by = (byte)((byte)(by + 1))) {
            for (int n2 = 0; n2 < 19; n2 = (int)((byte)(n2 + 1))) {
                if (this.g[n2][by] != 2) continue;
                by4 = by;
                n = n2;
                if (by > 15) {
                    this.A = (byte)-1;
                } else {
                    this.C = 0;
                }
                if (n2 > 10) {
                    this.B = (byte)-1;
                    continue block0;
                }
                this.D = 0;
                continue block0;
            }
        }
        this.K[0] = by4;
        this.L[0] = n;
        this.N[0] = 0;
        byte[] byArray = this.g[n];
        byte by5 = by4;
        byArray[by5] = (byte)(byArray[by5] | 0x80);
        for (by = 1; by < this.v; by = (byte)(by + 1)) {
            do {
                by2 = (byte)(this.a() % 2 * this.A + by4);
            } while (this.a(this.g[by3 = (byte)(this.a() % 5 * this.B + n)][by2]) > 0);
            this.K[by] = by2;
            this.L[by] = by3;
            byte[] byArray2 = this.g[by3];
            byte by6 = by2;
            byArray2[by6] = (byte)(byArray2[by6] | 0x80);
            this.N[by] = 0;
        }
        for (by = 0; by < this.u; by = (byte)(by + 1)) {
            do {
                by2 = (byte)((this.a() % 2 + 8) * this.A + by4);
            } while (this.a(this.g[by3 = (byte)(this.a() % 5 * this.B + n)][by2]) > 0);
            this.I[by] = by2;
            this.J[by] = by3;
            byte[] byArray3 = this.g[by3];
            byte by7 = by2;
            byArray3[by7] = (byte)(byArray3[by7] | 0x40);
            this.M[by] = 0;
        }
    }

    public final void u() {
        byte by;
        byte by2 = 0;
        byte by3 = 0;
        byte by4 = 0;
        int n = 0;
        block0: for (by = 0; by < 31; by = (byte)((byte)(by + 1))) {
            for (int n2 = 0; n2 < 19; n2 = (int)((byte)(n2 + 1))) {
                if (this.g[n2][by] != 2) continue;
                by4 = by;
                n = n2;
                if (by > 15) {
                    this.A = (byte)-1;
                    this.C = 0;
                }
                if (n2 <= 10) continue block0;
                this.B = (byte)-1;
                this.D = 0;
                continue block0;
            }
        }
        this.I[0] = by4;
        this.J[0] = n;
        this.M[0] = 0;
        byte[] byArray = this.g[n];
        byte by5 = by4;
        byArray[by5] = (byte)(byArray[by5] | 0x40);
        for (by = 1; by < this.u; by = (byte)(by + 1)) {
            do {
                by2 = (byte)(this.a() % 2 * this.A + by4);
            } while (this.a(this.g[by3 = (byte)(this.a() % 5 * this.B + n)][by2]) > 0);
            this.I[by] = by2;
            this.J[by] = by3;
            byte[] byArray2 = this.g[by3];
            byte by6 = by2;
            byArray2[by6] = (byte)(byArray2[by6] | 0x40);
            this.M[by] = 0;
        }
        for (by = 0; by < this.v; by = (byte)(by + 1)) {
            do {
                by2 = (byte)((this.a() % 2 + 8) * this.A + by4);
            } while (this.a(this.g[by3 = (byte)(this.a() % 5 * this.B + n)][by2]) > 0);
            this.K[by] = by2;
            this.L[by] = by3;
            byte[] byArray3 = this.g[by3];
            byte by7 = by2;
            byArray3[by7] = (byte)(byArray3[by7] | 0x80);
            this.N[by] = 0;
        }
    }

    public final void v() {
        this.s();
        if (this.e) {
            this.u();
        } else {
            this.t();
        }
        this.a.a((byte)20);
        if (this.b > 1) {
            this.P = new byte[this.v];
            for (byte by = 0; by < this.v; by = (byte)(by + 1)) {
                this.P[by] = 0;
            }
            this.e = 0;
        }
    }

    public final void w() {
        int n;
        this.c = 0;
        this.e = this.a[this.d];
        this.f = this.b[this.h];
        this.t = this.h;
        this.u = this.j;
        for (n = 0; n < this.u; ++n) {
            this.v[n] = this.t[n];
        }
        for (n = 0; n < this.u; n = (int)((byte)(n + 1))) {
            this.M[n] = 0;
        }
        this.v = this.g[this.t];
        for (n = 0; n < this.v; ++n) {
            this.w[n] = this.b[this.t][n];
        }
        for (n = 0; n < this.v; n = (int)((byte)(n + 1))) {
            this.N[n] = 0;
        }
        this.e = false;
        this.A = 1;
        this.B = 1;
        this.C = 1;
        this.D = 1;
        this.E = 0;
        this.x = 0;
        this.i = this.d[this.t];
        this.j = this.e[this.t];
        this.f = true;
        this.F = (byte)-1;
        this.w = this.a(this.v, this.u, this.M, this.w);
        this.v();
        this.a.b();
        this.a.a();
        this.a.i();
    }

    public final int a(byte by, byte by2) {
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
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            if (byArray[by2] != 0 && byArray[by2] <= 3) continue;
            s = (short)(s + this.k[sArray[by2]]);
        }
        return s;
    }

    public final int a(short s) {
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

    public final byte b(short s) {
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
        byte by4 = (byte)(this.a(s) + by2);
        if (by4 > (by3 = this.b(s))) {
            by4 = by3;
        }
        return by4;
    }

    public final byte c(short s) {
        byte by = this.t[s];
        byte by2 = this.o[s];
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

    public final byte c(byte by) {
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

    public final byte a(byte by, byte by2) {
        int n = 0;
        if (by > 0) {
            if (((this.g[by2][by] | this.g[by2][by - 1]) & 0xC0) == 192) {
                n = 16;
            } else if ((this.g[by2][by] & this.g[by2][by - 1] & 0xC0) != 0) {
                n = 1;
            }
        }
        if (by <= this.y) {
            if (((this.g[by2][by] | this.g[by2][by + 1]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.g[by2][by] & this.g[by2][by + 1] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        if (by2 > 0) {
            if (((this.g[by2][by] | this.g[by2 - 1][by]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.g[by2][by] & this.g[by2 - 1][by] & 0xC0) != 0) {
                n = (byte)(n | 1);
            }
        }
        if (by2 <= this.z) {
            if (((this.g[by2][by] | this.g[by2 + 1][by]) & 0xC0) == 192) {
                n = (byte)(n | 0x10);
            } else if ((this.g[by2][by] & this.g[by2 + 1][by] & 0xC0) != 0) {
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
            byArray4 = this.I;
            byArray3 = this.J;
            byArray2 = this.K;
            byArray = this.L;
        } else {
            byArray4 = this.K;
            byArray3 = this.L;
            byArray2 = this.I;
            byArray = this.L;
        }
        byte by2 = byArray4[n];
        byte by3 = byArray3[n];
        byte by4 = byArray2[n2];
        byte by5 = byArray[n2];
        byte by6 = (byte)(this.g[by3][by2] & 0x1F);
        byte by7 = (byte)(this.g[by5][by4] & 0x1F);
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
                if (by != 6 || (this.a(by4, by5) & 1) == 0 || !this.a(by2, by3, by4, by5, 1)) break;
                return true;
            }
            case 6: {
                if (by == 4 || by == 8) {
                    return true;
                }
                if (by != 10 && by != 12 || (this.a(by4, by5) & 1) == 0) break;
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
        if (by == 14 && by7 >= 5 && by7 <= 12 && (this.a(by4, by5) & 1) != 0) {
            return false;
        }
        return false;
    }

    public final int a(short s, short s2, byte by, short s3) {
        int n = 0;
        int n2 = 0;
        if (by == 8) {
            if (s2 != this.v[0] && s2 != this.w[0] && this.d(s, s2)) {
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
        int n3 = this.o[s] >= this.o[s2] ? this.t[s] + this.t[s] * (this.o[s] - this.o[s2]) / 10 - this.t[s2] : this.t[s] - this.t[s2] - this.t[s2] * (this.o[s2] - this.o[s]) / 10;
        if (n3 < n && this.a() % n >= n3) {
            return -1;
        }
        int n4 = 20 + this.a.nextInt() % 4;
        if (n4 >= this.r[s2]) {
            n4 = this.r[s2] - 1;
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
        if (this.o[s] > this.o[s2]) {
            n3 += n3 * (1 + (this.o[s] - this.o[s2]) / 10);
        }
        n3 += this.a.nextInt() % 30;
        if (by == 5) {
            if (n3 > s3) {
                n3 = s3;
            }
        } else if (n3 > this.k[s2]) {
            n3 = this.k[s2];
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
                this.b = "\u58eb\u5175\u6570\u51cf\u5c11 " + s;
                return;
            }
            case 1: {
                this.b = "\u6b66\u5c06\u4f53\u529b\u51cf\u5c11 " + by2;
                return;
            }
            case 2: {
                this.b = "\u519b\u961f\u4e2d\u8ba1\u88ab\u56f0";
                return;
            }
            case 5: {
                this.b = "\u7cae\u98df\u51cf\u5c11 " + s;
                return;
            }
            case 8: {
                this.b = "\u6210\u529f\u7b3c\u7edc\u6b66\u5c06!";
                return;
            }
            case 9: {
                this.b = "\u5c06\u4f53\u529b\u51cf\u5c11 " + by2 + ",\u5175\u6570\u51cf\u5c11 " + s;
                return;
            }
            case 10: {
                this.b = "\u519b\u961f\u4e2d\u8ba1, \u88ab\u56f0\u4e09\u5929";
                return;
            }
            case 11: {
                this.b = "\u519b\u961f\u57cb\u4f0f\u5b8c\u6bd5!";
                return;
            }
            case 15: {
                this.b = "\u5947\u95e8\u9041\u7532\u65bd\u5c55\u6210\u529f!";
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
                this.k[s3] = (short)(this.k[s3] - s2);
                return;
            }
            case 2: {
                short s4 = s;
                this.r[s4] = (byte)(this.r[s4] - by3);
                return;
            }
            case 3: {
                if (bl) {
                    this.j = (short)(this.j - s2);
                    return;
                }
                this.h = (short)(this.h - s2);
                return;
            }
            case 4: {
                short s5 = s;
                this.k[s5] = (short)(this.k[s5] - s2);
                short s6 = s;
                this.r[s6] = (byte)(this.r[s6] - by3);
                return;
            }
            case 5: {
                byArray[by] = 3;
                if (bl) {
                    if (this.u == 10) {
                        this.c[this.t] = this.a(this.c[this.t], this.h[this.t], s);
                        byte by5 = this.t;
                        this.h[by5] = (byte)(this.h[by5] + 1);
                        byte[] byArray2 = this.g[this.L[by]];
                        byte by6 = this.K[by];
                        byArray2[by6] = (byte)(byArray2[by6] & 0x3F);
                        return;
                    }
                    this.v[this.u] = this.w[by];
                    this.I[this.u] = this.K[by];
                    this.J[this.u] = this.L[by];
                    this.M[this.u] = 0;
                    byte[] byArray3 = this.g[this.L[by]];
                    byte by7 = this.K[by];
                    byArray3[by7] = (byte)(byArray3[by7] & 0x3F);
                    byte[] byArray4 = this.g[this.L[by]];
                    byte by8 = this.K[by];
                    byArray4[by8] = (byte)(byArray4[by8] | 0x40);
                    this.u = (byte)(this.u + 1);
                    return;
                }
                if (this.v == 10) {
                    this.c[this.t] = this.a(this.c[this.t], this.h[this.t], s);
                    byte by9 = this.t;
                    this.h[by9] = (byte)(this.h[by9] + 1);
                    byte[] byArray5 = this.g[this.J[by]];
                    byte by10 = this.I[by];
                    byArray5[by10] = (byte)(byArray5[by10] & 0x3F);
                    return;
                }
                this.w[this.v] = this.v[by];
                this.K[this.v] = this.I[by];
                this.L[this.v] = this.J[by];
                byte[] byArray6 = this.g[this.J[by]];
                byte by11 = this.I[by];
                byArray6[by11] = (byte)(byArray6[by11] & 0x3F);
                byte[] byArray7 = this.g[this.J[by]];
                byte by12 = this.I[by];
                byArray7[by12] = (byte)(byArray7[by12] | 0x80);
                this.N[this.v] = 0;
                this.v = (byte)(this.v + 1);
                return;
            }
            case 7: {
                byArray[by] = 4;
                return;
            }
            case 6: {
                short s7 = s;
                this.k[s7] = (short)(this.k[s7] - s2);
                if (bl) {
                    for (byte by13 = 0; by13 < this.v; by13 = (byte)(by13 + 1)) {
                        if (this.N[by13] > 0 && this.N[by13] < 4 || Math.abs(this.K[by13] - this.K[by]) + Math.abs(this.L[by13] - this.L[by]) != 1) continue;
                        if (this.k[this.w[by13]] >= s2) {
                            short s8 = this.w[by13];
                            this.k[s8] = (short)(this.k[s8] - s2);
                            continue;
                        }
                        this.k[this.w[by13]] = 0;
                    }
                } else {
                    for (byte by14 = 0; by14 < this.u; by14 = (byte)(by14 + 1)) {
                        if (this.M[by14] > 0 && this.M[by14] < 4 || Math.abs(this.I[by14] - this.I[by]) + Math.abs(this.J[by14] - this.J[by]) != 1) continue;
                        if (this.k[this.v[by14]] >= s2) {
                            short s9 = this.v[by14];
                            this.k[s9] = (short)(this.k[s9] - s2);
                            continue;
                        }
                        this.k[this.v[by14]] = 0;
                    }
                }
                break;
            }
            case 8: {
                byArray[by] = 7;
                if (bl) {
                    for (byte by15 = 0; by15 < this.v; by15 = (byte)(by15 + 1)) {
                        if (this.N[by15] > 0 && this.N[by15] < 4 || Math.abs(this.K[by15] - this.K[by]) + Math.abs(this.L[by15] - this.L[by]) != 1) continue;
                        this.N[by15] = 7;
                    }
                } else {
                    for (byte by16 = 0; by16 < this.u; by16 = (byte)(by16 + 1)) {
                        if (this.M[by16] > 0 && this.M[by16] < 4 || Math.abs(this.I[by16] - this.I[by]) + Math.abs(this.J[by16] - this.J[by]) != 1) continue;
                        this.M[by16] = 7;
                    }
                }
                break;
            }
            case 9: {
                byArray[by] = 17;
                return;
            }
            case 11: {
                if (bl) {
                    for (byte by17 = 0; by17 < this.v; by17 = (byte)(by17 + 1)) {
                        if (this.N[by17] > 0 && this.N[by17] < 4 || Math.abs(this.I[by2] - this.K[by17]) + Math.abs(this.J[by2] - this.L[by17]) != 1) continue;
                        if (this.k[this.w[by17]] >= s2) {
                            short s10 = this.w[by17];
                            this.k[s10] = (short)(this.k[s10] - s2);
                            continue;
                        }
                        this.k[this.w[by17]] = 0;
                    }
                } else {
                    for (byte by18 = 0; by18 < this.u; by18 = (byte)(by18 + 1)) {
                        if (this.M[by18] > 0 && this.M[by18] < 4 || Math.abs(this.K[by2] - this.I[by18]) + Math.abs(this.L[by2] - this.J[by18]) != 1) continue;
                        if (this.k[this.v[by18]] >= s2) {
                            short s11 = this.v[by18];
                            this.k[s11] = (short)(this.k[s11] - s2);
                            continue;
                        }
                        this.k[this.v[by18]] = 0;
                    }
                }
                break;
            }
        }
    }

    public final void a(short s) {
        int n = this.s[s] / 10;
        switch (n) {
            case 9: {
                return;
            }
            case 8: {
                short s2 = s;
                this.s[s2] = (byte)(this.s[s2] + 2);
                return;
            }
            case 7: {
                short s3 = s;
                this.s[s3] = (byte)(this.s[s3] + 3);
                return;
            }
            case 6: {
                short s4 = s;
                this.s[s4] = (byte)(this.s[s4] + 4);
                return;
            }
            case 5: {
                short s5 = s;
                this.s[s5] = (byte)(this.s[s5] + 5);
                return;
            }
        }
        short s6 = s;
        this.s[s6] = (byte)(this.s[s6] + 6);
    }

    public final void b(short s) {
        int n;
        if (this.l[s] < 0) {
            this.l[s] = 30000;
            n = 0;
        }
        if (this.l[s] < 2000) {
            n = 1;
        } else if (this.l[s] < 4000) {
            n = 2;
        } else if (this.l[s] < 7000) {
            n = 3;
        } else if (this.l[s] < 10000) {
            n = 4;
        } else if (this.l[s] < 15000) {
            n = 5;
        } else if (this.l[s] < 20000) {
            n = 6;
        } else if (this.l[s] < 30000) {
            n = 7;
        } else {
            this.l[s] = 30000;
            n = 8;
        }
        if (this.o[s] < n) {
            this.o[s] = n;
            this.a(s);
        }
    }

    public final byte a(short[] sArray, byte by, short s) {
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            if (sArray[by2] != s) continue;
            return by2;
        }
        return 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean c() {
        this.a = this.v[0];
        this.e(this.t);
        if (this.p == 0 && this.a == this.e) {
            if (!this.a(this.a, true)) return false;
            this.a = this.v[this.F];
            this.c = 0;
            return true;
        } else {
            this.F = 0;
            this.a.o = true;
        }
        return true;
    }

    public final void c(short s) {
        this.a = s;
        byte by = this.a(this.v, this.u, s);
        this.e(this.t);
        if (by == 0) {
            if (this.p == 0 && this.a == this.e) {
                this.a.r = (byte)18;
            } else {
                this.F = 0;
            }
        } else {
            this.F = -by;
        }
        this.a.o = true;
    }

    public final byte d(short s) {
        for (byte by = 1; by <= 8; by = (byte)(by + 1)) {
            if (this.a[by] != s) continue;
            return by;
        }
        return 0;
    }

    public final void c(byte by) {
        byte by2;
        byte by3 = 0;
        for (by2 = 0; by2 < this.g[by]; by2 = (byte)(by2 + 1)) {
            if (this.b[by][by2] != this.c[by]) continue;
            by3 = by2;
            break;
        }
        short s = this.c[by];
        for (by2 = by3; by2 > 0; by2 = (byte)(by2 - 1)) {
            this.b[by][by2] = this.b[by][by2 - 1];
        }
        this.b[by][0] = s;
    }

    public final void a(short s, byte by, short s2) {
        byte by2 = by;
        byte by3 = this.g[by2];
        this.g[by2] = (byte)(by3 + 1);
        this.b[by][by3] = s;
        byte by4 = by;
        this.h[by4] = (short)(this.h[by4] + this.k[s]);
        if (this.b[by] == 0) {
            byte by5 = this.d(s2);
            this.a[by5][this.b[by5]] = by;
            byte by6 = by5;
            this.b[by6] = (byte)(this.b[by6] + 1);
            this.b[by] = s2;
            this.c[by] = s;
            return;
        }
        if (s == s2) {
            this.c[by] = s;
            this.c(by);
        }
    }

    public final void x() {
        byte by = (byte)Math.abs(this.F);
        if (by < this.u) {
            this.M[by] = 1;
            byte[] byArray = this.g[this.J[by]];
            byte by2 = this.I[by];
            byArray[by2] = (byte)(byArray[by2] & 0x3F);
            return;
        }
        by = (byte)(by - this.u);
        this.N[by] = 1;
        byte[] byArray = this.g[this.L[by]];
        byte by3 = this.K[by];
        byArray[by3] = (byte)(byArray[by3] & 0x3F);
    }

    public final void y() {
        byte by;
        byte by2 = this.d;
        this.b[by2] = (byte)(this.b[by2] - 1);
        byte by3 = this.b[this.d];
        for (by = 0; by < this.b[this.d]; by = (byte)(by + 1)) {
            if (this.a[this.d][by] != this.t) continue;
            by3 = by;
            break;
        }
        for (by = by3; by < this.b[this.d]; by = (byte)(by + 1)) {
            this.a[this.d][by] = this.a[this.d][by];
        }
        this.a[this.e][this.b[this.e]] = this.t;
        byte by4 = this.e;
        this.b[by4] = (byte)(this.b[by4] + 1);
        this.b[this.t] = this.a[this.e];
        this.c[this.t] = this.w[0];
    }

    public final void z() {
        byte by;
        byte by2 = 0;
        for (by = 0; by < this.v; by = (byte)(by + 1)) {
            if (this.N[by] != 0 && this.N[by] <= 3) continue;
            byte by3 = by2;
            by2 = (byte)(by2 + 1);
            this.w[by3] = this.w[by];
        }
        this.v = by2;
        for (by = 0; by < this.u; by = (byte)(by + 1)) {
            if (this.M[by] != 2) continue;
            if (this.v < 10) {
                this.h(this.v[by]);
                byte by4 = this.v;
                this.v = (byte)(by4 + 1);
                this.w[by4] = this.v[by];
                continue;
            }
            this.c[this.t] = this.a(this.c[this.t], this.h[this.t], this.v[by]);
            byte by5 = this.t;
            this.h[by5] = (byte)(this.h[by5] + 1);
        }
        this.d[this.t] = this.i;
        this.e[this.t] = this.j;
        if (this.g) {
            byte by6 = this.t;
            this.d[by6] = (short)(this.d[by6] + this.g);
            byte by7 = this.t;
            this.e[by7] = (short)(this.e[by7] + this.h);
            if (this.d[this.t] > 9999) {
                this.d[this.t] = 9999;
            }
            if (this.e[this.t] > 9999) {
                this.e[this.t] = 9999;
            }
        }
        this.a(this.w, this.v);
        if (this.H > 1) {
            this.a.r = (byte)8;
            this.A();
            this.c = 0;
            if (this.b[this.d] <= 0) {
                this.H = (byte)3;
            }
        }
    }

    public final void d(byte by) {
        if (by > 0) {
            this.a(this.a, by, this.e);
        } else {
            this.c[this.t] = this.a(this.c[this.t], this.h[this.t], this.a);
            byte by2 = this.t;
            this.h[by2] = (byte)(this.h[by2] + 1);
        }
        if (this.F == 0 && by > 0) {
            byte by3 = by;
            this.d[by3] = (short)(this.d[by3] + this.g);
            byte by4 = by;
            this.e[by4] = (short)(this.e[by4] + this.h);
            if (this.d[by] > 9999) {
                this.d[by] = 9999;
            }
            if (this.e[by] > 9999) {
                this.e[by] = 9999;
            }
        }
        if (this.F < 0) {
            this.a.o = false;
        } else {
            byte by5;
            this.a.s = true;
            for (by5 = this.F = (byte)(this.F + 1); by5 < this.u && this.M[by5] != 0 && this.M[by5] <= 3; by5 = (byte)(by5 + 1)) {
            }
            if (by5 < this.u) {
                this.e(this.t);
                this.F = by5;
                this.a = this.v[this.F];
            } else {
                if (this.F < this.u) {
                    this.F = this.u;
                }
                for (by5 = (byte)(this.F - this.u); by5 < this.v && this.N[by5] != 2; by5 = (byte)(by5 + 1)) {
                }
                if (by5 < this.v) {
                    this.e(this.t);
                    if (this.p > 0) {
                        this.h(this.w[by5]);
                    }
                    this.F = (byte)(this.u + by5);
                    this.a = this.w[by5];
                } else {
                    this.a.o = false;
                    if (this.e) {
                        this.y();
                    }
                    this.z();
                    this.c = (byte)4;
                    return;
                }
            }
        }
        this.x();
    }

    public final void e(byte by) {
        int n = 0;
        for (byte by2 = 0; by2 < this.n[by]; by2 = (byte)(by2 + 1)) {
            byte by3 = this.b[by][by2];
            if ((this.e != this.b[by3] || this.g[by3] >= 10) && this.b[by3] != 0) continue;
            int n2 = n;
            n = (byte)(n + 1);
            this.F[n2] = by3;
        }
        this.F[n] = -1;
        this.p = (byte)n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void A() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.a.a != 0) {
                this.a.h();
                if (!this.a.B) {
                    this.a.a = 0;
                }
            }
            if (this.c > 0) {
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void a(short[] sArray, byte by, byte[] byArray) {
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            if (byArray[by2] < 4) continue;
            if (byArray[by2] > 7) {
                short s = sArray[by2];
                this.k[s] = (short)(this.k[s] - 100);
                if (this.k[sArray[by2]] < 0) {
                    this.k[sArray[by2]] = 0;
                }
            }
            if (byArray[by2] == 4 || byArray[by2] == 5 || byArray[by2] == 8) {
                byArray[by2] = 0;
                continue;
            }
            byte by3 = by2;
            byArray[by3] = (byte)(byArray[by3] - 1);
        }
    }

    public final boolean a(byte by, short s) {
        for (int i = 0; i < this.n[by]; ++i) {
            byte by2 = this.b[by][i];
            if ((this.b[by2] != s || this.g[by2] >= 10) && this.b[by2] != 0) continue;
            return true;
        }
        return false;
    }

    public final boolean d() {
        if (!this.a(this.t, this.f)) {
            return false;
        }
        return this.k[this.w[0]] < 100 && this.r[this.w[0]] < 40;
    }

    public final short a() {
        short s = 0;
        for (byte by = 0; by < this.u; by = (byte)(by + 1)) {
            if (this.M[by] > 0 && this.M[by] < 4) continue;
            s = (short)(s + 500);
            s = (short)(s + this.k[this.v[by]]);
        }
        return s;
    }

    public final short b() {
        short s = 0;
        for (byte by = 0; by < this.v; by = (byte)(by + 1)) {
            if (this.N[by] > 0 && this.N[by] < 4) continue;
            s = (short)(s + 500);
            s = (short)(s + this.k[this.w[by]]);
        }
        return s;
    }

    public final boolean e() {
        if (this.b == 1) {
            return this.d();
        }
        if (this.b == 2) {
            return this.q();
        }
        return true;
    }

    public final int b(byte by, byte by2) {
        byte by3 = this.c(this.w[by]);
        byte[] byArray = new byte[16];
        int n = 0;
        for (byte by4 = 0; by4 < by3; by4 = (byte)(by4 + 1)) {
            if (by4 == 8 || by4 == 11 || this.k[this.v[by2]] == 0 && (by4 == 0 || by4 == 3 || by4 == 4) || !this.a((int)by, (int)by2, by4, false) || this.c(by4) > this.x) continue;
            byArray[n] = by4;
            n = (byte)(n + 1);
        }
        if (n == 0) {
            return -1;
        }
        return byArray[this.a() % n];
    }

    public final int c(byte by, byte by2) {
        if (this.b == 1) {
            return this.b(by, by2);
        }
        if (this.b == 2) {
            return this.a((int)by, (int)by2);
        }
        return 0;
    }

    public final void a(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        byArray2[by] = new byte[this.u];
        for (byte by2 = 0; by2 < this.u; by2 = (byte)(by2 + 1)) {
            if (this.M[by2] > 0 && this.M[by2] < 4) continue;
            int n = Math.abs(this.K[by] - this.I[by2]);
            int n2 = Math.abs(this.L[by] - this.J[by2]);
            if (n > 5 || n2 > 5 || this.c(by, by2) < 0) continue;
            byArray2[by][byArray3[by]] = by2;
            byte by3 = by;
            byArray3[by3] = (byte)(byArray3[by3] + 1);
        }
    }

    public final void a(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.e()) {
            byArray[0] = 3;
            return;
        }
        this.a((byte)0, byArray, byArray2, byArray3);
        if (byArray3[0] > 0) {
            byArray[0] = 2;
        }
    }

    public final byte a(short s, byte[] byArray, byte by, byte by2) {
        return byArray[this.a() % by];
    }

    public final byte b(short s, byte[] byArray, byte by, byte by2) {
        if (this.b == 1) {
            return this.a(s, byArray, by, by2);
        }
        if (this.b == 2) {
            return this.c(s, byArray, by, by2);
        }
        return 0;
    }

    public final byte a() {
        byte[] byArray = new byte[this.n[this.t]];
        byte by = 0;
        for (int i = 0; i < this.n[this.t]; ++i) {
            byte by2 = this.b[this.t][i];
            if ((this.b[by2] != this.f || this.g[by2] >= 10) && this.b[by2] != 0) continue;
            byArray[by] = by2;
            by = (byte)(by + 1);
        }
        if (by > 0) {
            return this.b(this.f, byArray, by, this.t);
        }
        return -1;
    }

    public final void d(short s) {
        for (byte by = 0; by < this.v; by = (byte)(by + 1)) {
            if (this.w[by] != s) continue;
            this.N[by] = 1;
            byte[] byArray = this.g[this.L[by]];
            byte by2 = this.K[by];
            byArray[by2] = (byte)(byArray[by2] & 0x3F);
            return;
        }
    }

    public final void a(short s, byte by) {
        if (by > 0) {
            this.a(s, by, this.f);
        } else {
            this.c[this.t] = this.a(this.c[this.t], this.h[this.t], s);
            byte by2 = this.t;
            this.h[by2] = (byte)(this.h[by2] + 1);
        }
        this.d(s);
    }

    public final void a(short[] sArray, byte n) {
        this.h[this.t] = this.a(sArray, (byte)n);
        this.g[this.t] = n;
        for (int i = 0; i < n; ++i) {
            this.b[this.t][i] = sArray[i];
        }
    }

    public final void B() {
        int n;
        byte by;
        int n2;
        short[] sArray = new short[10];
        short[] sArray2 = new short[10];
        short[] sArray3 = new short[10];
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        boolean bl = false;
        for (n2 = 0; n2 < this.u; n2 = (int)((byte)(n2 + 1))) {
            if (this.M[n2] == 0 || this.M[n2] > 3) {
                int n6 = n3;
                n3 = (byte)(n3 + 1);
                sArray3[n6] = this.v[n2];
                continue;
            }
            if (this.M[n2] != 2) continue;
            int n7 = n4;
            n4 = (byte)(n4 + 1);
            sArray[n7] = this.v[n2];
        }
        for (n2 = 0; n2 < n3; ++n2) {
            this.v[n2] = sArray3[n2];
        }
        this.u = n3;
        sArray3 = new short[10];
        n3 = 0;
        if (this.N[0] == 2 || this.N[0] == 3 || this.a() < 0) {
            bl = true;
            if (this.v[this.w[0]] == 100) {
                this.f = this.d(this.f);
                this.H = 1;
                this.N[0] = 3;
            }
        }
        for (n2 = 0; n2 < this.v; n2 = (int)((byte)(n2 + 1))) {
            if (this.N[n2] == 0 || this.N[n2] > 3) {
                int n8 = n3;
                n3 = (byte)(n3 + 1);
                sArray3[n8] = this.w[n2];
                continue;
            }
            if (this.N[n2] != 2) continue;
            int n9 = n5;
            n5 = (byte)(n5 + 1);
            sArray2[n9] = this.w[n2];
        }
        this.v = (byte)n3;
        for (n2 = 0; n2 < this.v; n2 = (int)((byte)(n2 + 1))) {
            this.w[n2] = sArray3[n2];
        }
        for (n2 = 0; n2 < this.v; n2 = (int)((byte)(n2 + 1))) {
            by = this.a();
            if (by < 0) {
                for (n = n2; n < this.v; n = (int)((byte)(n + 1))) {
                    int n10 = n5;
                    n5 = (byte)(n5 + 1);
                    sArray2[n10] = this.w[n];
                }
                break;
            }
            if (!bl && n2 == 0) {
                byte by2 = by;
                this.d[by2] = (short)(this.d[by2] + this.i);
                byte by3 = by;
                this.e[by3] = (short)(this.e[by3] + this.j);
                if (this.d[by] > 9999) {
                    this.d[by] = 9999;
                }
                if (this.e[by] > 9999) {
                    this.e[by] = 9999;
                }
            }
            this.a(this.w[n2], by);
        }
        for (n2 = 0; n2 < n4; n2 = (int)((byte)(n2 + 1))) {
            by = this.a();
            if (by < 0) {
                n = n2;
                while (n < n4) {
                    byte by4 = this.u;
                    this.u = (byte)(by4 + 1);
                    this.v[by4] = sArray[n];
                }
                break;
            }
            this.h(sArray[n2]);
            this.a(sArray[n2], by);
        }
        for (n2 = 0; n2 < n5; n2 = (int)((byte)(n2 + 1))) {
            if (this.u >= 10) {
                this.c[this.t] = this.a(this.c[this.t], this.h[this.t], sArray2[n2]);
                byte by5 = this.t;
                this.h[by5] = (byte)(this.h[by5] + 1);
                continue;
            }
            this.h(sArray2[n2]);
            byte by6 = this.u;
            this.u = (byte)(by6 + 1);
            this.v[by6] = sArray2[n2];
        }
        this.a(this.v, this.u);
        this.e[this.t] = this.h;
        this.d[this.t] = this.g;
        if (bl) {
            byte by7 = this.t;
            this.d[by7] = (short)(this.d[by7] + this.i);
            byte by8 = this.t;
            this.e[by8] = (short)(this.e[by8] + this.j);
            if (this.d[this.t] > 9999) {
                this.d[this.t] = 9999;
            }
            if (this.e[this.t] > 9999) {
                this.e[this.t] = 9999;
            }
        }
        this.c = (byte)4;
    }

    public final void C() {
        byte by;
        byte by2;
        this.B();
        byte by3 = this.d(this.e);
        byte by4 = by2 = this.d(this.b[this.t]);
        this.b[by4] = (byte)(this.b[by4] - 1);
        byte by5 = this.b[by2];
        for (by = 0; by < this.b[by2]; by = (byte)(by + 1)) {
            if (this.a[by2][by] != this.t) continue;
            by5 = by;
            break;
        }
        for (by = by5; by < this.b[by2]; by = (byte)(by + 1)) {
            this.a[by2][by] = this.a[by2][by + 1];
        }
        this.a[by3][this.b[by3]] = this.t;
        byte by6 = by3;
        this.b[by6] = (byte)(this.b[by6] + 1);
        this.b[this.t] = this.a[by3];
        this.c[this.t] = this.v[0];
        if (this.b[by2] == 0) {
            this.H = (byte)4;
            this.a[this.f] = 0;
        }
    }

    public final boolean a(int n) {
        if (!this.a(this.t, this.f)) {
            return false;
        }
        return this.k[this.w[n]] < 100 && this.r[this.w[n]] < 50;
    }

    public final short c() {
        short s = 0;
        for (byte by = 0; by < this.u; by = (byte)(by + 1)) {
            if (this.M[by] > 0 && this.M[by] < 4) continue;
            s = (short)(s + 500);
            s = (short)(s + this.k[this.v[by]]);
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
                if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by4 < this.z - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                }
            } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            } else if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by4 < this.z - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            }
        } else if (n < 0 && n2 >= 0) {
            if (n + n2 < 0) {
                if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by4 < this.y - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                }
            } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            } else if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by4 < this.y - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            }
        } else if (n >= 0 && n2 < 0) {
            if (n + n2 >= 0) {
                if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                } else if (by4 < this.z - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                }
            } else if (by4 < this.z - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            } else if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            }
        } else if (n < 0 && n2 < 0) {
            if (n - n2 < 0) {
                if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                    by3 = (byte)(by3 + 1);
                } else if (by4 < this.z - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                    by4 = (byte)(by4 + 1);
                } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                    by4 = (byte)(by4 - 1);
                } else if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                    by3 = (byte)(by3 - 1);
                }
            } else if (by4 < this.z - 1 && this.a(this.g[by4 + 1][by3]) == 0) {
                by4 = (byte)(by4 + 1);
            } else if (by3 < this.y - 1 && this.a(this.g[by4][by3 + 1]) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by3 > 0 && this.a(this.g[by4][by3 - 1]) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by4 > 0 && this.a(this.g[by4 - 1][by3]) == 0) {
                by4 = (byte)(by4 - 1);
            }
        }
        while (by != by3 || by2 != by4) {
            n = by3 - by;
            n2 = by4 - by2;
            if (Math.abs(n) - Math.abs(n2) >= 0) {
                if (n >= 0 && (this.g[by2][by + 1] & 0xC0) == 0) {
                    byArray2[by5][byArray3[by5]] = 4;
                    by = (byte)(by + 1);
                } else if (n < 0 && (this.g[by2][by - 1] & 0xC0) == 0) {
                    byArray2[by5][byArray3[by5]] = 3;
                    by = (byte)(by - 1);
                } else if (n2 >= 0) {
                    byArray2[by5][byArray3[by5]] = 2;
                    by2 = (byte)(by2 + 1);
                } else {
                    byArray2[by5][byArray3[by5]] = 1;
                    by2 = (byte)(by2 - 1);
                }
            } else if (n2 >= 0 && (this.g[by2 + 1][by] & 0xC0) == 0) {
                byArray2[by5][byArray3[by5]] = 2;
                by2 = (byte)(by2 + 1);
            } else if (n2 < 0 && (this.g[by2 - 1][by] & 0xC0) == 0) {
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
            if (byArray3[by5] != 16) continue;
        }
        for (int n3 = 0; n3 < byArray3[by5] / 2; n3 = (int)((byte)(n3 + 1))) {
            byte by7 = byArray2[by5][n3];
            byArray2[by5][n3] = byArray2[by5][byArray3[by5] - n3 - 1];
            byArray2[by5][byArray3[by5] - n3 - 1] = by7;
        }
    }

    public final void a(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        this.a(this.K[by], this.L[by], this.I[by2], this.J[by2], byArray, byArray2, byArray3, by);
    }

    public final void b(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.b == 1) {
            this.a(by, by2, byArray, byArray2, byArray3);
            return;
        }
        this.c(by, by2, byArray, byArray2, byArray3);
    }

    public final void b(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if ((this.N[by] & 0x7F) != 0) {
            byArray[by] = 0;
            return;
        }
        byArray[by] = 1;
        this.b(by, (byte)0, byArray, byArray2, byArray3);
    }

    public final void a(int n, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        int n2 = 100;
        byArray2[n] = new byte[1];
        for (byte by = 0; by < this.u; by = (byte)(by + 1)) {
            if (this.M[by] > 0 && this.M[by] < 4) continue;
            int n3 = Math.abs(this.K[0] - this.I[by]);
            int n4 = Math.abs(this.L[0] - this.J[by]);
            if (n3 > 3 || n4 > 3) continue;
            byArray3[n] = 1;
            n3 = Math.abs(this.K[n] - this.I[by]);
            if (n2 <= n3 + (n4 = Math.abs(this.L[n] - this.J[by]))) continue;
            n2 = n3 + n4;
            byArray2[n][0] = by;
            byArray[n] = 1;
        }
    }

    public final void c(byte by, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.N[by] == 0) {
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

    public final void e(short s) {
        int n = this.k[s] / 2;
        if ((n += this.a() % 100) < 200) {
            n = 200 + this.a() % 100;
        }
        if (n > this.k[s]) {
            n = this.k[s];
        }
        short s2 = s;
        this.k[s2] = (short)(this.k[s2] - n);
    }

    public final void b(int n, int n2) {
        try {
            this.a = this.v[n];
            this.b = this.w[n2];
            this.a.c = this.a;
            this.a.d = this.b;
            this.I = (byte)(this.g[this.J[n]][this.I[n]] & 0xF);
            this.a.r = (byte)2;
            this.A();
            this.c = (byte)3;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void a(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        byte by3 = 0;
        int n = 0;
        if (this.x < 2) {
            return;
        }
        for (byte by4 = 0; by4 < this.u; by4 = (byte)(by4 + 1)) {
            byte by5;
            byte by6;
            if (this.M[by4] > 0 && this.M[by4] < 4 || (by6 = (byte)Math.abs(this.I[by4] - this.K[by])) + (by5 = (byte)Math.abs(this.J[by4] - this.L[by])) != 1) continue;
            this.x = (byte)(this.x - 2);
            this.b((int)by4, (int)by);
            return;
        }
        switch (byArray[byArray2[by] - 1]) {
            case 1: {
                by3 = this.K[by];
                n = (byte)(this.L[by] - 1);
                break;
            }
            case 2: {
                by3 = this.K[by];
                n = (byte)(this.L[by] + 1);
                break;
            }
            case 3: {
                by3 = (byte)(this.K[by] - 1);
                n = this.L[by];
                break;
            }
            case 4: {
                by3 = (byte)(this.K[by] + 1);
                n = this.L[by];
            }
        }
        byte by7 = (byte)this.a(this.g[n][by3], this.p[this.w[by]]);
        if (this.x >= by7) {
            this.x = (byte)(this.x - by7);
            byte[] byArray3 = this.g[this.L[by]];
            byte by8 = this.K[by];
            byArray3[by8] = (byte)(byArray3[by8] & 0x3F);
            byte[] byArray4 = this.g[n];
            byte by9 = by3;
            byArray4[by9] = (byte)(byArray4[by9] | 0x80);
            this.K[by] = by3;
            this.L[by] = n;
            byte by10 = by;
            byArray2[by10] = (byte)(byArray2[by10] - 1);
            if ((this.g[n][by3] & 0x20) != 0) {
                this.b = "\u4e2d\u8ba1\u8c0b\u5341\u9762\u57cb\u4f0f";
                this.a.r = (byte)100;
                this.A();
                this.c = 0;
                this.e(this.w[by]);
            }
        }
    }

    public final void b(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        this.a(by, by2, byArray, byArray2);
    }

    public final void c(byte by, byte by2, byte[] byArray, byte[] byArray2) {
        byte by3 = byArray[this.a() % byArray2[by]];
        byte by4 = (byte)this.c(by, by3);
        if (by4 < 0) {
            return;
        }
        this.x = (byte)(this.x - this.c(by4));
        int n = this.a(this.w[by], this.v[by3], by4, this.h);
        if (n > 0) {
            this.a(n, by4);
            short s = this.k[this.v[by3]];
            this.a(this.v[by3], this.M, by3, by, n, false);
            short s2 = this.w[by];
            this.l[s2] = (short)(this.l[s2] + (s - this.k[this.v[by3]]));
            this.b(this.w[by]);
        } else {
            this.b = "\u8ba1\u7b56\u5931\u8d25\uff01";
        }
        this.a.r = (byte)100;
        this.A();
        this.c = 0;
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
                this.a.r = (byte)3;
                this.A();
                this.c = 0;
                this.a(this.w[by], this.a());
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (Exception exception) {}
        this.a.repaint();
    }

    public final void D() {
        byte by = -1;
        byte[] byArray = new byte[this.v];
        byte[][] byArrayArray = new byte[this.v][];
        byte[] byArray2 = new byte[this.v];
        while (by != this.x && this.x >= 2) {
            byte by2;
            System.gc();
            by = this.x;
            for (by2 = 0; by2 < this.v; by2 = (byte)(by2 + 1)) {
                byArray[by2] = 0;
                byArray2[by2] = 0;
            }
            this.a(byArray, byArrayArray, byArray2);
            if (byArray[0] == 3) {
                this.a.r = (byte)5;
                this.A();
                return;
            }
            for (by2 = 1; by2 < this.v; by2 = (byte)(by2 + 1)) {
                if ((this.N[by2] & 0x1F) != 0) continue;
                if (this.a((int)by2)) {
                    byArray[by2] = 3;
                    continue;
                }
                this.b(by2, byArray, byArrayArray, byArray2);
            }
            for (by2 = this.G; by2 < this.v; by2 = (byte)(by2 + 1)) {
                System.gc();
                if ((this.N[by2] & 0x1F) != 0) continue;
                this.d(by2, byArray[by2], byArrayArray[by2], byArray2);
                if (this.c <= 0) continue;
                this.G = (byte)(by2 + 1);
                return;
            }
        }
        System.gc();
    }

    public final byte a(short[] sArray, byte[] byArray, byte by) {
        int n = 0;
        for (int i = 1; i < 64; ++i) {
            if ((sArray[i] & 0x100) != 0 || sArray[i] >= sArray[n]) continue;
            n = i;
        }
        return (byte)n;
    }

    public final void E() {
        if (this.b == 1) {
            this.D();
            return;
        }
        if (this.b == 2) {
            this.bs();
        }
    }

    public final boolean b(byte by) {
        if ((this.g[this.L[by]][this.K[by]] & 0xF) == 2) {
            this.a.r = (byte)21;
            this.A();
            return true;
        }
        return false;
    }

    public final void b(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.e()) {
            byArray[0] = 3;
            return;
        }
        this.b((byte)0, byArray, byArray2, byArray3);
    }

    public final void F() {
        byte by = -1;
        byte[] byArray = new byte[this.v];
        byte[][] byArrayArray = new byte[this.v][];
        byte[] byArray2 = new byte[this.v];
        while (by != this.x && this.x >= 2) {
            byte by2;
            System.gc();
            by = this.x;
            for (by2 = 0; by2 < this.v; by2 = (byte)(by2 + 1)) {
                byArray[by2] = 0;
                byArray2[by2] = 0;
            }
            this.b(byArray, byArrayArray, byArray2);
            if (byArray[0] == 3) {
                this.a.r = (byte)5;
                this.A();
                return;
            }
            for (by2 = 1; by2 < this.v; by2 = (byte)(by2 + 1)) {
                if ((this.N[by2] & 0x1F) != 0) continue;
                if (this.a((int)by2)) {
                    byArray[by2] = 3;
                    continue;
                }
                this.b(by2, byArray, byArrayArray, byArray2);
            }
            for (by2 = 0; by2 < this.v; by2 = (byte)(by2 + 1)) {
                System.gc();
                if ((this.N[by2] & 0x1F) != 0) continue;
                this.d(by2, byArray[by2], byArrayArray[by2], byArray2);
                if (this.b(by2)) {
                    this.c = (byte)4;
                    return;
                }
                if (this.c <= 0) continue;
                this.G = (byte)(by2 + 1);
                return;
            }
            System.gc();
        }
    }

    public final void G() {
        if (this.b == 1) {
            this.F();
            return;
        }
        if (this.b == 2) {
            this.bt();
        }
    }

    public final void H() {
        block13: {
            do {
                if (this.c == 0) {
                    this.h = true;
                    this.E = (byte)(this.E + 1);
                    this.f = true;
                    if (this.E > 1) {
                        this.w = this.a(this.v, this.u, this.M, this.w);
                    }
                    this.A();
                } else if (this.c == 2 || this.c == 5 || this.c == 6) {
                    this.c = 0;
                    this.A();
                }
                if (this.c == 1) {
                    this.c = 0;
                    this.a(this.v, this.u, this.M);
                    this.x = this.a(this.w, this.v, this.N, this.x);
                    this.f = false;
                    this.h = false;
                    this.G = 0;
                } else {
                    if (this.c == 2 || this.c == 4 || this.c == 5) break block13;
                    if (this.c == 6) {
                        return;
                    }
                    if (this.c == 3) {
                        this.c = 0;
                    }
                }
                if (this.e) {
                    this.G();
                } else {
                    this.E();
                }
                if (this.c == 3) {
                    return;
                }
                this.a(this.w, this.v, this.N);
                this.h = (short)(this.h - ((this.a(this.v, this.u, this.M) - 1) / 250 + 1));
                if (this.h <= 0) {
                    this.h = 0;
                    this.a.r = (byte)7;
                    this.A();
                    return;
                }
                this.j = (short)(this.j - ((this.a(this.w, this.v, this.N) - 1) / 250 + 1));
                if (this.j > 0) continue;
                this.j = 0;
                this.a.r = (byte)6;
            } while (this.E != 30);
            this.a.r = (byte)20;
            this.A();
        }
    }

    public final void I() {
        int n;
        this.i = false;
        this.J = this.k[this.a] == 0 ? (byte)1 : (byte)((this.k[this.a] - 1) / 100 + 2);
        this.K = this.k[this.b] == 0 ? (byte)1 : (byte)((this.k[this.b] - 1) / 100 + 2);
        for (n = 0; n < this.L; n = (byte)(n + 1)) {
            for (byte by = 0; by < this.M; by = (byte)(by + 1)) {
                this.h[by][n] = 0;
            }
        }
        for (n = 0; n < 4; n = (byte)(n + 1)) {
            this.O[n] = 0;
        }
        if (this.I == 2) {
            if (this.h) {
                this.h[0][11] = 32;
                this.h[6][11] = 32;
                this.h[1][11] = 32;
                this.h[5][11] = 32;
                this.h[3][9] = 32;
                this.h[0][6] = 2;
                this.h[1][10] = 16;
                this.h[6][6] = 2;
                this.h[5][10] = 16;
                for (n = 6; n < 10; n = (int)((byte)(n + 1))) {
                    this.h[1][n] = 32;
                    this.h[5][n] = 32;
                }
            } else {
                this.h[0][4] = 32;
                this.h[6][4] = 32;
                this.h[1][4] = 32;
                this.h[5][4] = 32;
                this.h[6][4] = 32;
                this.h[3][6] = 32;
                this.h[0][9] = 2;
                this.h[1][5] = 16;
                this.h[6][9] = 2;
                this.h[5][5] = 16;
                for (n = 6; n < 10; n = (int)((byte)(n + 1))) {
                    this.h[1][n] = 32;
                    this.h[5][n] = 32;
                }
                for (n = 0; n < 4; n = (int)((byte)(n + 1))) {
                    this.O[n] = 1;
                }
            }
            this.i = true;
        }
        this.m();
        this.R = 0;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.Y = 0;
        this.Z = 0;
        this.j = false;
        for (n = 0; n < 4; n = (byte)(n + 1)) {
            this.x[n] = 0;
            this.y[n] = 0;
        }
        this.a.a((byte)21);
        this.a.t = 1;
        this.a.t = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void J() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.a.a != 0) {
                this.a.g();
                this.a.a = 0;
            }
            if (this.c > 0) {
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final boolean f() {
        int n;
        int n2 = 0;
        int n3 = 0;
        if (this.n[1][0] == 2) {
            return true;
        }
        for (n = 1; n < this.J; ++n) {
            if (!this.b[0][n]) continue;
            n2 = (short)(n2 + this.o[0][n]);
        }
        for (n = 1; n < this.K; ++n) {
            if (!this.b[1][n]) continue;
            n3 = (short)(n3 + this.o[1][n]);
        }
        return n2 > 300 && n3 < 100;
    }

    public final void a(int n) {
        if (this.f() && n >= 4) {
            for (int i = 0; i < this.K; ++i) {
                this.n[1][i] = 2;
            }
            return;
        }
        for (int i = 0; i < this.K; ++i) {
            this.n[1][i] = 1;
        }
    }

    public final void b(int n) {
        if (this.b == 1) {
            this.a(n);
            return;
        }
        if (this.b == 2) {
            this.t(n);
        }
    }

    public final void c(int n) {
        if (this.f() && n >= 4) {
            for (int i = 0; i < this.K; ++i) {
                this.n[1][i] = 2;
            }
            return;
        }
        this.n[1][0] = n >= 7 ? (byte)0 : 1;
        for (int i = 1; i < this.K; ++i) {
            this.n[1][i] = 0;
        }
    }

    public final void d(int n) {
        if (this.b == 1) {
            this.c(n);
            return;
        }
        if (this.b == 2) {
            this.u(n);
        }
    }

    public final void e(int n) {
        if (this.b == 1) {
            this.c(n);
            return;
        }
        if (this.b == 2) {
            this.v(n);
        }
    }

    public final void f(int n) {
        if (this.i) {
            if (this.h) {
                this.b(n);
                return;
            }
            this.d(n);
            return;
        }
        this.e(n);
    }

    public final byte d(byte by) {
        byte by2 = this.j[1][0];
        byte by3 = this.k[1][0];
        byte by4 = this.j[0][by];
        byte by5 = this.k[0][by];
        int n = -1;
        if (by2 < 15 && (this.h[by3][by2 + 1] & 0x60) == 0 && by3 - by5 != 0) {
            by2 = (byte)(by2 + 1);
        } else if (by3 - by5 <= 0) {
            if (by3 > 0 && (this.h[by3 - 1][by2] & 0x60) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < 6 && (this.h[by3 + 1][by2] & 0x60) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by2 > 0 && (this.h[by3][by2 - 1] & 0x60) == 0) {
                by2 = (byte)(by2 - 1);
            }
        } else if (by3 < 6 && (this.h[by3 + 1][by2] & 0x60) == 0) {
            by3 = (byte)(by3 + 1);
        } else if (by3 > 0 && (this.h[by3 - 1][by2] & 0x60) == 0) {
            by3 = (byte)(by3 - 1);
        } else if (by2 > 0 && (this.h[by3][by2 - 1] & 0x60) == 0) {
            by2 = (byte)(by2 - 1);
        }
        if (by4 < 15 && by2 - by4 > 0 && (this.h[by5][by4 + 1] & 0x60) == 0) {
            if (by2 == by4 + 1 && by3 == by5) {
                n = 3;
            } else {
                return 3;
            }
        }
        if (by4 > 0 && by2 - by4 < 0 && (this.h[by5][by4 - 1] & 0x60) == 0) {
            if (by2 == by4 - 1 && by3 == by5) {
                n = 2;
            } else {
                return 2;
            }
        }
        if (by5 < 6 && by3 - by5 > 0 && (this.h[by5 + 1][by4] & 0x60) == 0) {
            return 1;
        }
        if (by5 > 0 && by3 - by5 < 0 && (this.h[by5 - 1][by4] & 0x60) == 0) {
            return 0;
        }
        if (by2 - by4 == 0) {
            if (by4 < 15 && by4 > 0 && (this.h[by5][by4 + 1] & 0x60) == 0 && this.h[by5][by4 - 1] == 0) {
                return (byte)(this.a() % 2 + 2);
            }
            if (by4 < 15 && (this.h[by5][by4 + 1] & 0x60) == 0) {
                return 3;
            }
            if (by4 > 0 && (this.h[by5][by4 - 1] & 0x60) == 0) {
                return 2;
            }
        }
        if (by3 - by5 == 0) {
            if (by5 < 6 && by5 > 0 && (this.h[by5 + 1][by4] & 0x60) == 0 && (this.h[by5 - 1][by4] & 0x60) == 0) {
                return (byte)(this.a() % 2);
            }
            if (by5 < 6 && (this.h[by5 + 1][by4] & 0x60) == 0) {
                return 1;
            }
            if (by5 > 0 && (this.h[by5 - 1][by4] & 0x60) == 0) {
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

    public final byte b(byte by, byte by2) {
        byte[] byArray = new byte[5];
        int n = 0;
        if (this.k[0][by] > 0 && (this.h[this.k[0][by] - 1][this.j[0][by]] & 0x80) != 0) {
            if (by2 == 0 || this.l[0][by] == 0 && this.j[1][0] == this.j[0][by] && this.k[1][0] == this.k[0][by] - 1) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (this.k[0][by] < 6 && (this.h[this.k[0][by] + 1][this.j[0][by]] & 0x80) != 0) {
            if (by2 == 1 || this.l[0][by] == 0 && this.j[1][0] == this.j[0][by] && this.k[1][0] == this.k[0][by] + 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (this.j[0][by] > 0 && (this.h[this.k[0][by]][this.j[0][by] - 1] & 0x80) != 0) {
            if (by2 == 2 || this.l[0][by] == 0 && this.j[1][0] == this.j[0][by] - 1 && this.k[1][0] == this.k[0][by]) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (this.j[0][by] < 15 && (this.h[this.k[0][by]][this.j[0][by] + 1] & 0x80) != 0) {
            if (by2 == 3 || this.l[0][by] == 0 && this.j[1][0] == this.j[0][by] + 1 && this.k[1][0] == this.k[0][by]) {
                return 3;
            }
            byArray[n++] = 3;
        }
        if (n > 0 && (by2 == -1 || this.a() % 10 >= 3)) {
            byte by3 = (byte)(this.a() % n);
            return byArray[by3];
        }
        return -1;
    }

    public final void a(byte by, byte by2, byte by3, byte by4, boolean bl) {
        this.ab = by;
        this.ac = by2;
        this.ad = by3;
        this.ae = by4;
        this.j = bl;
        this.aa = (byte)6;
    }

    public final synchronized void b(byte by, byte by2, boolean bl) {
        byte by3;
        byte by4;
        if (bl) {
            by4 = this.j[1][by];
            by3 = this.k[1][by];
        } else {
            by4 = this.j[0][by];
            by3 = this.k[0][by];
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
            for (byte by5 = 0; by5 < this.J; by5 = (byte)(by5 + 1)) {
                if (this.j[0][by5] != by4 || this.k[0][by5] != by3) continue;
                this.a((byte)1, by, (byte)0, by5, bl);
            }
            return;
        }
        for (byte by6 = 0; by6 < this.K; by6 = (byte)(by6 + 1)) {
            if (this.j[1][by6] != by4 || this.k[1][by6] != by3) continue;
            this.a((byte)0, by, (byte)1, by6, bl);
        }
    }

    public final boolean b(byte by, byte by2) {
        byte by3 = this.b(by, by2);
        if (by3 >= 0) {
            this.b(by, by3, false);
        } else if (by2 >= 0) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final byte c(byte by, byte by2) {
        int n;
        byte[] byArray = new byte[4];
        int n2 = 0;
        int n3 = 5;
        if (this.k[0][by] > 0 && (this.h[this.k[0][by] - 1][this.j[0][by]] & 0x80) != 0 && by2 == 0) {
            return 0;
        }
        if (this.k[0][by] < 6 && (this.h[this.k[0][by] + 1][this.j[0][by]] & 0x80) != 0 && by2 == 1) {
            return 1;
        }
        if (this.j[0][by] > 0 && (this.h[this.k[0][by]][this.j[0][by] - 1] & 0x80) != 0 && by2 == 2) {
            return 2;
        }
        if (this.j[0][by] < 15 && (this.h[this.k[0][by]][this.j[0][by] + 1] & 0x80) != 0 && by2 == 3) {
            return 3;
        }
        if ((this.S & 0xF0) == 48) {
            n3 = 7;
        }
        for (n = 1; n < n3 && this.k[0][by] + n <= 6; ++n) {
            if ((this.h[this.k[0][by] + n][this.j[0][by]] & 0x80) == 0) continue;
            ++n2;
            byArray[0] = 1;
            break;
        }
        for (n = 1; n < n3 && this.k[0][by] >= n; ++n) {
            if ((this.h[this.k[0][by] - n][this.j[0][by]] & 0x80) == 0) continue;
            byArray[n2++] = 0;
            break;
        }
        for (n = 1; n < n3 && this.j[0][by] >= n; ++n) {
            if ((this.h[this.k[0][by]][this.j[0][by] - n] & 0x80) == 0) continue;
            byArray[n2++] = 2;
            break;
        }
        for (n = 1; n < n3 && this.j[0][by] + n <= 15; ++n) {
            if ((this.h[this.k[0][by]][this.j[0][by] + n] & 0x80) == 0) continue;
            byArray[n2++] = 3;
            break;
        }
        if (n2 > 0 && (by2 == -1 || this.a() % 10 >= 2)) {
            return byArray[this.a() % n2];
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
            by5 = this.j[1][by];
            by4 = this.k[1][by];
            n = 64;
            if ((this.T & 0xF0) == 48) {
                by6 = 7;
            }
        } else {
            by5 = this.j[0][by];
            by4 = this.k[0][by];
            n = -128;
            if ((this.S & 0xF0) == 48) {
                by6 = 7;
            }
        }
        block0 : switch (by2) {
            case 0: {
                for (by3 = 1; by3 < by6; by3 = (byte)((byte)(by3 + 1))) {
                    if (by4 - by3 < 0 || (this.h[by4 - by3][by5] & n) == 0) continue;
                    by4 = (byte)(by4 - by3);
                    break block0;
                }
                break;
            }
            case 1: {
                for (by3 = 1; by3 < by6; by3 = (byte)((byte)(by3 + 1))) {
                    if (by4 + by3 > 6 || (this.h[by4 + by3][by5] & n) == 0) continue;
                    by4 = (byte)(by4 + by3);
                    break block0;
                }
                break;
            }
            case 2: {
                for (by3 = 1; by3 < by6; by3 = (byte)((byte)(by3 + 1))) {
                    if (by5 - by3 < 0 || (this.h[by4][by5 - by3] & n) == 0) continue;
                    by5 = (byte)(by5 - by3);
                    break block0;
                }
                break;
            }
            case 3: {
                for (by3 = 1; by3 < by6; by3 = (byte)((byte)(by3 + 1))) {
                    if (by5 + by3 > 15 || (this.h[by4][by5 + by3] & n) == 0) continue;
                    by5 = (byte)(by5 + by3);
                    break block0;
                }
                break;
            }
        }
        if (bl) {
            this.a.a(this.j[1][by], this.k[1][by], by5, by4, !bl);
            for (by3 = 0; by3 < this.J; by3 = (byte)(by3 + 1)) {
                if (this.j[0][by3] != by5 || this.k[0][by3] != by4) continue;
                this.a((byte)1, by, (byte)0, by3, bl);
            }
        } else {
            this.a.a(this.j[0][by], this.k[0][by], by5, by4, !bl);
            for (by3 = 0; by3 < this.K; by3 = (byte)(by3 + 1)) {
                if (this.j[1][by3] != by5 || this.k[1][by3] != by4) continue;
                this.a((byte)0, by, (byte)1, by3, bl);
            }
        }
    }

    public final boolean c(byte by, byte by2) {
        byte by3 = this.c(by, by2);
        if (by3 >= 0) {
            this.c(by, by3, false);
        } else if (by2 >= 0) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final boolean c(byte by) {
        byte by2 = this.d(by);
        if (by2 >= 0) {
            this.m[0][by] = by2;
        }
        switch (this.l[0][by]) {
            case 0: 
            case 1: 
            case 3: {
                return this.b(by, by2);
            }
            case 2: {
                return this.c(by, by2);
            }
        }
        return false;
    }

    public final boolean d(byte by) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.k[0][by] > 0 && (this.h[this.k[0][by] - 1][this.j[0][by]] & 0x80) != 0) {
            ++n;
            byArray[0] = 0;
        }
        if (this.k[0][by] < 6 && (this.h[this.k[0][by] + 1][this.j[0][by]] & 0x80) != 0) {
            byArray[n++] = 1;
        }
        if (this.j[0][by] > 0 && (this.h[this.k[0][by]][this.j[0][by] - 1] & 0x80) != 0) {
            byArray[n++] = 2;
        }
        if (this.j[0][by] < 15 && (this.h[this.k[0][by]][this.j[0][by] + 1] & 0x80) != 0) {
            byArray[n++] = 3;
        }
        if (n == 0) {
            return false;
        }
        this.b(by, byArray[this.a() % n], false);
        return true;
    }

    public final boolean e(byte by) {
        byte by2;
        byte[] byArray = new byte[4];
        int n = 0;
        byte by3 = 5;
        if ((this.S & 0xF0) == 48) {
            by3 = 7;
        }
        for (by2 = 1; by2 < by3 && this.k[0][by] >= by2; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[0][by] - by2][this.j[0][by]] & 0x80) == 0) continue;
            ++n;
            byArray[0] = 0;
            break;
        }
        for (by2 = 1; by2 < by3 && this.k[0][by] + by2 <= 6; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[0][by] + by2][this.j[0][by]] & 0x80) == 0) continue;
            byArray[n++] = 1;
            break;
        }
        for (by2 = 1; by2 < by3 && this.j[0][by] >= by2; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[0][by]][this.j[0][by] - by2] & 0x80) == 0) continue;
            byArray[n++] = 2;
            break;
        }
        for (by2 = 1; by2 < by3 && this.j[0][by] + by2 <= 15; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[0][by]][this.j[0][by] + by2] & 0x80) == 0) continue;
            byArray[n++] = 3;
            break;
        }
        if (n == 0) {
            return false;
        }
        this.c(by, byArray[this.a() % n], false);
        return true;
    }

    public final boolean f(byte by) {
        switch (this.l[0][by]) {
            case 0: 
            case 1: 
            case 3: {
                return this.d(by);
            }
            case 2: {
                return this.e(by);
            }
        }
        return false;
    }

    public final boolean g(byte by) {
        if (this.j[0][by] > 0 && (this.h[this.k[0][by]][this.j[0][by] - 1] & 0xE0) != 0) {
            int n = 0;
            if (this.k[0][by] > 0 && (this.h[this.k[0][by] - 1][this.j[0][by]] & 0xE0) == 0) {
                ++n;
            }
            if (this.k[0][by] < 6 && (this.h[this.k[0][by] + 1][this.j[0][by]] & 0xE0) == 0) {
                n += 2;
            }
            boolean bl = false;
            if (n == 1) {
                this.c((byte)0, by);
            } else if (n == 2) {
                this.d((byte)0, by);
            } else if (n == 3) {
                if (this.a() % 2 == 0) {
                    this.c((byte)0, by);
                } else {
                    this.d((byte)0, by);
                }
            } else {
                return false;
            }
            return true;
        }
        if (this.j[0][by] == 0) {
            byte[] byArray = this.h[this.k[0][by]];
            byte by2 = this.j[0][by];
            byArray[by2] = (byte)(byArray[by2] & 0x32);
            this.j[0][by] = -1;
            this.k[0][by] = -1;
            this.b[0][by] = false;
            return true;
        }
        this.e((byte)0, by);
        return true;
    }

    public final byte d(byte by, byte by2) {
        byte[] byArray = new byte[5];
        int n = 0;
        byte by3 = this.j[0][by];
        byte by4 = this.k[0][by];
        byte by5 = this.j[1][0];
        byte by6 = this.k[1][0];
        if (by2 == 0 && by6 == by4 - 1 && by5 == by3) {
            by2 = (byte)-1;
        } else if (by4 > 0 && (this.h[by4 - 1][by3] & 0x80) != 0 && (by6 != by4 - 1 || by5 != by3)) {
            if (by2 == 0) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (by2 == 1 && by6 == by4 + 1 && by5 == by3) {
            by2 = (byte)-1;
        } else if (by4 < 6 && (this.h[by4 + 1][by3] & 0x80) != 0 && (by6 != by4 + 1 || by5 != by3)) {
            if (by2 == 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (by2 == 2 && by6 == by4 && by5 == by3 - 1) {
            by2 = (byte)-1;
        } else if (by3 > 0 && (this.h[by4][by3 - 1] & 0x80) != 0 && (by6 != by4 || by5 != by3 - 1)) {
            if (by2 == 2) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (by2 == 3 && by6 == by4 && by5 == by3 + 1) {
            by2 = (byte)-1;
        } else if (by3 < 15 && (this.h[by4][by3 + 1] & 0x80) != 0 && (by5 != by3 + 1 || by6 != by4)) {
            if (by2 == 3) {
                return 3;
            }
            byArray[n++] = 3;
        }
        if (n > 0 && (by2 < 0 || this.a() % 10 >= 3)) {
            byte by7 = (byte)(this.a() % n);
            return byArray[by7];
        }
        if (by2 < 0) {
            return -2;
        }
        return -1;
    }

    public final boolean d(byte by, byte by2) {
        byte by3 = this.d(by, by2);
        if (by3 >= 0) {
            this.b(by, by3, false);
        } else if (by2 >= 0 && by3 != -2) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final byte e(byte by, byte by2) {
        int n;
        byte[] byArray = new byte[4];
        int n2 = 0;
        byte by3 = this.j[0][by];
        int n3 = this.k[0][by];
        byte by4 = this.j[1][0];
        byte by5 = this.k[1][0];
        int n4 = 5;
        if (n3 > 0 && (this.h[n3 - 1][by3] & 0x80) != 0 && by2 == 0) {
            if (by5 == n3 - 1 && by4 == by3) {
                by2 = (byte)-1;
            } else {
                return 0;
            }
        }
        if (n3 < 6 && (this.h[n3 + 1][by3] & 0x80) != 0 && by2 == 1) {
            if (by5 == n3 + 1 && by4 == by3) {
                by2 = (byte)-1;
            } else {
                return 1;
            }
        }
        if (by3 > 0 && (this.h[n3][by3 - 1] & 0x80) != 0 && by2 == 2) {
            if (by5 == n3 && by4 == by3 - 1) {
                by2 = (byte)-1;
            } else {
                return 2;
            }
        }
        if (by3 < 15 && (this.h[n3][by3 + 1] & 0x80) != 0 && by2 == 3) {
            if (by5 == n3 && by4 == by3 + 1) {
                by2 = (byte)-1;
            } else {
                return 3;
            }
        }
        if ((this.S & 0xF0) == 48) {
            n4 = 7;
        }
        for (n = 1; n < n4 && n3 >= n && (by5 != n3 - n || by4 != by3); ++n) {
            if ((this.h[n3 - n][by3] & 0x80) == 0) continue;
            ++n2;
            byArray[0] = 0;
            break;
        }
        for (n = 1; n < n4 && n3 + n <= 6 && (by5 != n3 + n || by4 != by3); ++n) {
            if ((this.h[n3 + n][by3] & 0x80) == 0) continue;
            byArray[n2++] = 1;
            break;
        }
        for (n = 1; n < n4 && by3 >= n && (by5 != n3 || by4 != by3 - n); ++n) {
            if ((this.h[n3][by3 - n] & 0x80) == 0) continue;
            byArray[n2++] = 2;
            break;
        }
        for (n = 1; n < n4 && by3 + n <= 15 && (by5 != n3 || by4 != by3 + n); ++n) {
            if ((this.h[n3][by3 + n] & 0x80) == 0) continue;
            byArray[n2++] = 3;
            break;
        }
        if (n2 > 0 && (by2 == -1 || this.a() % 10 >= 2)) {
            return byArray[this.a() % n2];
        }
        if (by2 == -1) {
            return -2;
        }
        return -1;
    }

    public final boolean e(byte by, byte by2) {
        by2 = this.d(by);
        byte by3 = this.e(by, by2);
        if (by3 >= 0) {
            this.c(by, by3, false);
        } else if (by2 >= 0 && by3 != -2) {
            this.a((byte)0, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final boolean h(byte by) {
        byte by2 = this.d(by);
        if (by2 >= 0) {
            this.m[0][by] = by2;
        }
        switch (this.l[0][by]) {
            case 0: 
            case 1: 
            case 3: {
                return this.d(by, by2);
            }
            case 2: {
                return this.e(by, by2);
            }
        }
        return false;
    }

    public final boolean i(byte by) {
        boolean bl = false;
        if (!this.b[0][by]) {
            return false;
        }
        this.n[0][by] = this.O[this.l[0][by]];
        switch (this.n[0][by]) {
            case 0: {
                bl = this.c(by);
                break;
            }
            case 1: {
                bl = this.f(by);
                break;
            }
            case 2: {
                bl = this.g(by);
                break;
            }
            case 3: {
                bl = this.h(by);
            }
        }
        return bl;
    }

    public final byte e(byte by) {
        byte by2 = this.j[0][0];
        byte by3 = this.k[0][0];
        byte by4 = this.j[1][by];
        byte by5 = this.k[1][by];
        int n = -1;
        if (by2 > 0 && (this.h[by3][by2 + 1] & 0xA0) == 0 && by3 - by5 != 0) {
            by2 = (byte)(by2 - 1);
        } else if (by3 - by5 <= 0) {
            if (by3 > 0 && (this.h[by3 - 1][by2] & 0xA0) == 0) {
                by3 = (byte)(by3 - 1);
            } else if (by3 < 6 && (this.h[by3 + 1][by2] & 0xA0) == 0) {
                by3 = (byte)(by3 + 1);
            } else if (by2 < 15 && (this.h[by3][by2 - 1] & 0xA0) == 0) {
                by2 = (byte)(by2 + 1);
            }
        } else if (by3 < 6 && (this.h[by3 + 1][by2] & 0xA0) == 0) {
            by3 = (byte)(by3 + 1);
        } else if (by3 > 0 && (this.h[by3 - 1][by2] & 0xA0) == 0) {
            by3 = (byte)(by3 - 1);
        } else if (by2 < 15 && (this.h[by3][by2 - 1] & 0xA0) == 0) {
            by2 = (byte)(by2 + 1);
        }
        if (by4 < 15 && by2 - by4 > 0 && (this.h[by5][by4 + 1] & 0xA0) == 0) {
            if (by2 == by4 + 1 && by3 == by5) {
                n = 3;
            } else {
                return 3;
            }
        }
        if (by4 > 0 && by2 - by4 < 0 && (this.h[by5][by4 - 1] & 0xA0) == 0) {
            if (by2 == by4 - 1 && by3 == by5) {
                n = 2;
            } else {
                return 2;
            }
        }
        if (by5 < 6 && by3 - by5 > 0 && (this.h[by5 + 1][by4] & 0xA0) == 0) {
            return 1;
        }
        if (by5 > 0 && by3 - by5 < 0 && (this.h[by5 - 1][by4] & 0xA0) == 0) {
            return 0;
        }
        if (by2 - by4 == 0) {
            if (by4 < 15 && by4 > 0 && (this.h[by5][by4 + 1] & 0xA0) == 0 && this.h[by5][by4 - 1] == 0) {
                return (byte)(this.a() % 2 + 2);
            }
            if (by4 < 15 && (this.h[by5][by4 + 1] & 0xA0) == 0) {
                return 3;
            }
            if (by4 > 0 && (this.h[by5][by4 - 1] & 0xA0) == 0) {
                return 2;
            }
        }
        if (by3 - by5 == 0) {
            if (by5 > 0 && by5 < 6 && (this.h[by5 + 1][by4] & 0xA0) == 0 && (this.h[by5 - 1][by4] & 0xA0) == 0) {
                return (byte)(this.a() % 2);
            }
            if (by5 < 6 && (this.h[by5 + 1][by4] & 0xA0) == 0) {
                return 1;
            }
            if (by5 > 0 && (this.h[by5 - 1][by4] & 0xA0) == 0) {
                return 0;
            }
        }
        return (byte)n;
    }

    public final byte f(byte by, byte by2) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.k[1][by] > 0 && (this.h[this.k[1][by] - 1][this.j[1][by]] & 0x40) != 0) {
            if (by2 == 0 || this.l[1][by] == 0 && this.j[0][0] == this.j[1][by] && this.k[0][0] == this.k[1][by] - 1) {
                return 0;
            }
            ++n;
            byArray[0] = 0;
        }
        if (this.k[1][by] < 6 && (this.h[this.k[1][by] + 1][this.j[1][by]] & 0x40) != 0) {
            if (by2 == 1 || this.l[1][by] == 0 && this.j[0][0] == this.j[1][by] && this.k[0][0] == this.k[1][by] + 1) {
                return 1;
            }
            byArray[n++] = 1;
        }
        if (this.j[1][by] > 0 && (this.h[this.k[1][by]][this.j[1][by] - 1] & 0x40) != 0) {
            if (by2 == 2 || this.l[1][by] == 0 && this.j[0][0] == this.j[1][by] - 1 && this.k[0][0] == this.k[1][by]) {
                return 2;
            }
            byArray[n++] = 2;
        }
        if (this.j[1][by] < 15 && (this.h[this.k[1][by]][this.j[1][by] + 1] & 0x40) != 0) {
            if (by2 == 3 || this.l[1][by] == 0 && this.j[0][0] == this.j[1][by] + 1 && this.k[0][0] == this.k[1][by]) {
                return 3;
            }
            byArray[n++] = 3;
        }
        if (n > 0 && (by2 == -1 || this.a() % 10 >= 3)) {
            return byArray[this.a() % n];
        }
        return -1;
    }

    public final boolean f(byte by, byte by2) {
        byte by3 = this.f(by, by2);
        if (by3 >= 0) {
            this.b(by, by3, true);
        } else if (by2 >= 0) {
            this.a((byte)1, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final byte g(byte by, byte by2) {
        int n;
        byte[] byArray = new byte[4];
        int n2 = 0;
        int n3 = 5;
        if (this.k[1][by] > 0 && (this.h[this.k[1][by] - 1][this.j[1][by]] & 0x40) != 0 && by2 == 0) {
            return 0;
        }
        if (this.k[1][by] < 6 && (this.h[this.k[1][by] + 1][this.j[1][by]] & 0x40) != 0 && by2 == 1) {
            return 1;
        }
        if (this.j[1][by] > 0 && (this.h[this.k[1][by]][this.j[1][by] - 1] & 0x40) != 0 && by2 == 2) {
            return 2;
        }
        if (this.j[1][by] < 15 && (this.h[this.k[1][by]][this.j[1][by] + 1] & 0x40) != 0 && by2 == 3) {
            return 3;
        }
        if ((this.T & 0xF0) == 48) {
            n3 = 7;
        }
        for (n = 1; n < n3 && this.k[1][by] >= n; ++n) {
            if ((this.h[this.k[1][by] - n][this.j[1][by]] & 0x40) == 0) continue;
            ++n2;
            byArray[0] = 0;
            break;
        }
        for (n = 1; n < n3 && this.k[1][by] + n <= 6; ++n) {
            if ((this.h[this.k[1][by] + n][this.j[1][by]] & 0x40) == 0) continue;
            byArray[n2++] = 1;
            break;
        }
        for (n = 1; n < n3 && this.j[1][by] >= n; ++n) {
            if ((this.h[this.k[1][by]][this.j[1][by] - n] & 0x40) == 0) continue;
            byArray[n2++] = 2;
            break;
        }
        for (n = 1; n < n3 && this.j[1][by] + n <= 15; ++n) {
            if ((this.h[this.k[1][by]][this.j[1][by] + n] & 0x40) == 0) continue;
            byArray[n2++] = 3;
            break;
        }
        if (n2 > 0 && (by2 == -1 || this.a() % 10 >= 2)) {
            return byArray[this.a() % n2];
        }
        return -1;
    }

    public final boolean g(byte by, byte by2) {
        byte by3 = this.g(by, by2);
        if (by3 >= 0) {
            this.c(by, by3, true);
        } else if (by2 >= 0) {
            this.a((byte)1, by, by2);
        } else {
            return false;
        }
        return true;
    }

    public final boolean j(byte by) {
        if (this.b[1][by]) {
            byte by2 = this.e(by);
            if (by2 >= 0) {
                this.m[1][by] = by2;
            }
            switch (this.l[1][by]) {
                case 0: 
                case 1: 
                case 3: {
                    return this.f(by, by2);
                }
                case 2: {
                    return this.g(by, by2);
                }
            }
        }
        return false;
    }

    public final boolean k(byte by) {
        byte[] byArray = new byte[4];
        int n = 0;
        if (this.k[1][by] > 0 && (this.h[this.k[1][by] - 1][this.j[1][by]] & 0x40) != 0) {
            ++n;
            byArray[0] = 0;
        }
        if (this.k[1][by] < 6 && (this.h[this.k[1][by] + 1][this.j[1][by]] & 0x40) != 0) {
            byArray[n++] = 1;
        }
        if (this.j[1][by] > 0 && (this.h[this.k[1][by]][this.j[1][by] - 1] & 0x40) != 0) {
            byArray[n++] = 2;
        }
        if (this.j[1][by] < 15 && (this.h[this.k[1][by]][this.j[1][by] + 1] & 0x40) != 0) {
            byArray[n++] = 3;
        }
        if (n == 0) {
            return false;
        }
        this.b(by, byArray[this.a() % n], true);
        return true;
    }

    public final boolean l(byte by) {
        byte by2;
        byte[] byArray = new byte[4];
        int n = 0;
        byte by3 = 5;
        if ((this.T & 0xF0) == 48) {
            by3 = 7;
        }
        for (by2 = 1; by2 < by3 && this.k[1][by] >= by2; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[1][by] - by2][this.j[1][by]] & 0x40) == 0) continue;
            ++n;
            byArray[0] = 0;
            break;
        }
        for (by2 = 1; by2 < by3 && this.k[1][by] + by2 <= 6; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[1][by] + by2][this.j[1][by]] & 0x40) == 0) continue;
            byArray[n++] = 1;
            break;
        }
        for (by2 = 1; by2 < by3 && this.j[1][by] >= by2; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[1][by]][this.j[1][by] - by2] & 0x40) == 0) continue;
            byArray[n++] = 2;
            break;
        }
        for (by2 = 1; by2 < by3 && this.j[1][by] + by2 <= 15; by2 = (byte)(by2 + 1)) {
            if ((this.h[this.k[1][by]][this.j[1][by] + by2] & 0x40) == 0) continue;
            byArray[n++] = 3;
            break;
        }
        if (n == 0) {
            return false;
        }
        this.c(by, byArray[this.a() % n], true);
        return true;
    }

    public final boolean m(byte by) {
        if (this.b[1][by]) {
            switch (this.l[1][by]) {
                case 0: 
                case 1: 
                case 3: {
                    return this.k(by);
                }
                case 2: {
                    return this.l(by);
                }
            }
        }
        return false;
    }

    public final boolean n(byte by) {
        if (this.j[1][by] < 15 && (this.h[this.k[1][by]][this.j[1][by] + 1] & 0xE0) != 0) {
            return false;
        }
        if (this.j[1][by] == 15) {
            byte[] byArray = this.h[this.k[1][by]];
            byte by2 = this.j[1][by];
            byArray[by2] = (byte)(byArray[by2] & 0x32);
            this.j[1][by] = -1;
            this.k[1][by] = -1;
            this.b[1][by] = false;
            return true;
        }
        this.f((byte)1, by);
        return true;
    }

    public final boolean o(byte by) {
        boolean bl = false;
        if (!this.b[1][by]) {
            return false;
        }
        switch (this.n[1][by]) {
            case 0: {
                bl = this.j(by);
                break;
            }
            case 1: {
                bl = this.m(by);
                break;
            }
            case 2: {
                bl = this.n(by);
            }
        }
        return bl;
    }

    public final byte b() {
        int n;
        int n2;
        int n3 = 0;
        int n4 = this.e[this.ab][this.ac] * this.e[this.ab][this.ac] / this.f[this.ad][this.ae];
        n3 = this.e[this.ab][this.ac] >= this.f[this.ad][this.ae] ? (n4 < (n2 = this.e[this.ab][this.ac] * 3 / 2) ? n4 : n2) : (n4 > (n = this.e[this.ab][this.ac] * 2 / 3) ? n4 : n);
        n3 = this.a((short)n3) * 5;
        if ((this.S & 0xF0) == 64) {
            n3 *= 1;
        } else if ((this.S & 0xF0) == 80 && this.l[this.ab][this.ac] == 2) {
            n3 *= 1;
        }
        n3 = n3 * this.o[this.ab][this.ac] / 100;
        n3 += this.a.nextInt() % (n3 / 10 + 1);
        if (n3 > 110) {
            n3 = 110;
        }
        return (byte)n3;
    }

    public final void b(boolean bl) {
        byte by = this.b();
        if (this.l[this.ad][this.ae] == 0) {
            if (this.l[this.ab][this.ac] == 0) {
                this.c = 1;
                return;
            }
            if (bl) {
                short s = this.a;
                this.r[s] = (byte)(this.r[s] - (by / 4 + 1));
                if (this.r[this.a] <= 0) {
                    this.b[this.ad][this.ae] = false;
                    this.r[this.a] = 0;
                    return;
                }
            } else {
                short s = this.b;
                this.r[s] = (byte)(this.r[s] - (by / 4 + 1));
                if (this.r[this.b] <= 0) {
                    this.b[this.ad][this.ae] = false;
                    this.r[this.b] = 0;
                    return;
                }
            }
        } else {
            byte[] byArray = this.o[this.ad];
            byte by2 = this.ae;
            byArray[by2] = (byte)(byArray[by2] - by);
            if (this.o[this.ad][this.ae] <= 0) {
                this.b[this.ad][this.ae] = false;
                this.o[this.ad][this.ae] = 0;
                byte[] byArray2 = this.h[this.k[this.ad][this.ae]];
                byte by3 = this.j[this.ad][this.ae];
                byArray2[by3] = (byte)(byArray2[by3] & 0x32);
                this.k[this.ad][this.ae] = -1;
                this.j[this.ad][this.ae] = -1;
            }
        }
    }

    public final void K() {
        byte by = this.j[0][0];
        byte by2 = this.k[0][0];
        block0: for (int i = by + 1; i < by + 4; ++i) {
            if (i > 15) {
                return;
            }
            for (int j = by2 - 1; j < by2 + 2; ++j) {
                if (j < 0) continue;
                if (j > 6) continue block0;
                for (int k = 0; k < this.K; ++k) {
                    if (this.j[1][k] != i || this.k[1][k] != j) continue;
                    if (k == 0) {
                        short s = this.b;
                        this.r[s] = (byte)(this.r[s] - (this.a() % 21 + 30));
                        if (this.r[this.b] > 0) continue;
                        this.r[this.b] = 0;
                        this.b[1][0] = false;
                        continue;
                    }
                    if (!this.b[1][k]) continue;
                    byte[] byArray = this.o[1];
                    int n = k;
                    byArray[n] = (byte)(byArray[n] - (this.a() % 21 + 60));
                    if (this.o[1][k] > 0) continue;
                    byte[] byArray2 = this.h[this.k[1][k]];
                    byte by3 = this.j[1][k];
                    byArray2[by3] = (byte)(byArray2[by3] & 0x3F);
                    this.o[1][k] = 0;
                    this.b[1][k] = false;
                    this.j[1][k] = -1;
                    this.k[1][k] = -1;
                }
            }
        }
    }

    public final void L() {
        byte by = this.j[1][0];
        byte by2 = this.k[1][0];
        block0: for (int i = by - 1; i > by - 4; --i) {
            if (i < 0) {
                return;
            }
            for (int j = by2 - 1; j < by2 + 2; ++j) {
                if (j < 0) continue;
                if (j > 6) continue block0;
                for (int k = 0; k < this.J; ++k) {
                    if (this.j[0][k] != i || this.k[0][k] != j) continue;
                    if (k == 0) {
                        short s = this.a;
                        this.r[s] = (byte)(this.r[s] - (this.a() % 21 + 30));
                        if (this.r[this.a] > 0) continue;
                        this.r[this.a] = 0;
                        this.b[0][0] = false;
                        continue;
                    }
                    if (!this.b[0][k]) continue;
                    byte[] byArray = this.o[0];
                    int n = k;
                    byArray[n] = (byte)(byArray[n] - (this.a() % 21 + 60));
                    if (this.o[0][k] > 0) continue;
                    byte[] byArray2 = this.h[this.k[0][k]];
                    byte by3 = this.j[0][k];
                    byArray2[by3] = (byte)(byArray2[by3] & 0x3F);
                    this.o[0][k] = 0;
                    this.b[0][k] = false;
                    this.j[0][k] = -1;
                    this.k[0][k] = -1;
                }
            }
        }
    }

    public final boolean g() {
        int n = 0;
        if (this.r[this.b] > 40 || this.v[this.b] > 99) {
            return false;
        }
        if (this.k[1][0] > 0 && (this.h[this.k[1][0] - 1][this.j[1][0]] & 0x40) != 0) {
            n = 1;
        }
        if (this.k[1][0] < 6 && (this.h[this.k[1][0] + 1][this.j[1][0]] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (this.j[1][0] > 0 && (this.h[this.k[1][0]][this.j[1][0] - 1] & 0x40) != 0) {
            n = (byte)(n + 1);
        }
        if (this.j[1][0] < 15 && (this.h[this.k[1][0]][this.j[1][0] + 1] & 0x40) != 0) {
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
        if (this.s[this.b] > 95 || this.v[this.b] > 95) {
            n = (byte)(n - 30);
        } else if (this.s[this.b] >= 90 || this.v[this.b] >= 90) {
            n = (byte)(n - 20);
        } else if (this.s[this.b] >= 80 || this.v[this.b] >= 80) {
            n = (byte)(n - 10);
        } else if (n > 0) {
            if (this.v[this.b] < 15) {
                n = (byte)(n + 40);
            } else if (this.v[this.b] < 35) {
                n = (byte)(n + 20);
            } else if (this.v[this.b] < 50) {
                n = (byte)(n + 10);
            }
        }
        return this.a() % 100 < n;
    }

    public final void M() {
        if (!this.b[0][0]) {
            if (this.r[this.a] <= 0) {
                this.R = (byte)2;
                return;
            }
            this.R = (byte)6;
            if (this.a() % 10 > 1) {
                this.U = (byte)(this.a() % 12 + 2);
                if (this.U >= this.r[this.a]) {
                    this.U = this.r[this.a];
                    this.R = (byte)2;
                }
                short s = this.a;
                this.r[s] = (byte)(this.r[s] - this.U);
            }
            return;
        }
        if (!this.b[1][0]) {
            if (this.r[this.b] <= 0) {
                this.R = 1;
                return;
            }
            this.R = (byte)5;
            if (this.a() % 10 > 1) {
                this.U = (byte)(this.a() % 12 + 2);
                if (this.U >= this.r[this.b]) {
                    this.U = this.r[this.b];
                    this.R = 1;
                }
                short s = this.b;
                this.r[s] = (byte)(this.r[s] - this.U);
            }
        }
    }

    public final void N() {
        byte by;
        short s = this.k[this.a];
        short s2 = this.k[this.b];
        this.k[this.a] = 0;
        for (by = 1; by < this.J; by = (byte)(by + 1)) {
            short s3 = this.a;
            this.k[s3] = (short)(this.k[s3] + this.o[0][by]);
        }
        this.k[this.b] = 0;
        for (by = 1; by < this.K; by = (byte)(by + 1)) {
            short s4 = this.b;
            this.k[s4] = (short)(this.k[s4] + this.o[1][by]);
        }
        short s5 = this.a;
        this.l[s5] = (short)(this.l[s5] + (s2 - this.k[this.b]));
        short s6 = this.b;
        this.l[s6] = (short)(this.l[s6] + (s - this.k[this.a]));
        this.b(this.a);
        this.b(this.b);
    }

    public final void O() {
        this.N();
        if (this.h) {
            this.c = (byte)2;
            return;
        }
        this.c = (byte)3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void P() {
        if (this.c == 0) {
            this.a.s = 0;
            this.ai = 0;
            this.c = 0;
            this.aj = 0;
            this.d = 0;
            this.ak = 0;
            this.ag = this.J > 1 ? (byte)1 : 0;
            this.ah = this.K > 1 ? (byte)1 : 0;
            this.al = 0;
            this.f(this.c);
        } else {
            this.c = 0;
        }
        long l = System.currentTimeMillis();
        block5: while (this.c <= 0) {
            long l2;
            if (this.a.a != 0) {
                this.a.g();
                this.a.a = 0;
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
            this.a.repaint();
            l = System.currentTimeMillis();
            if (this.V == 2 || this.R > 0) continue;
            if (this.S != 0) {
                if (this.d == 0) {
                    this.d = this.c;
                    this.ak = this.J;
                } else if (this.c > this.d && this.J >= this.ak) {
                    this.d = this.c;
                    this.S = (byte)(this.S - 1);
                    if ((this.S & 0xF) == 0) {
                        this.S = 0;
                        this.d = 0;
                    }
                }
            }
            this.al = (byte)(this.al + 1);
            if (this.a.u) continue;
            if (this.aa > 0) {
                this.a[this.ad][this.ae] = true;
                this.aa = (byte)(this.aa - 1);
                if (this.aa != 0) continue;
                this.b(this.j);
                this.al = 0;
                this.M();
                continue;
            }
            if (this.a.v || this.a.w) {
                if (this.Y > 0 && (this.Y = (byte)(this.Y - 1)) == 0) {
                    this.K();
                    this.a.v = false;
                    this.al = 0;
                }
                if (this.Z <= 0 || (this.Z = (byte)(this.Z - 1)) != 0) continue;
                this.L();
                this.a.w = false;
                continue;
            }
            if (this.al < 10) continue;
            this.M();
            if (this.R > 0) continue;
            this.al = 0;
            if (this.aj > 1) {
                ++this.c;
                this.ag = this.J > 1 ? (byte)1 : 0;
                this.ah = this.K > 1 ? (byte)1 : 0;
                this.aj = 0;
            }
            if (this.aj == 0) {
                this.m = false;
                do {
                    this.m = this.i(this.ag);
                    if (this.ag == 0 && this.ai >= 1) {
                        this.ai = 0;
                        this.aj = 1;
                        if (this.g()) {
                            this.R = (byte)3;
                            continue;
                        }
                        this.f(this.c);
                        if (this.T == 0) continue block5;
                        if ((this.T & 0xF) == 0) {
                            this.T = 0;
                            continue block5;
                        }
                        this.T = (byte)(this.T - 1);
                        continue block5;
                    }
                    if (!this.b[0][this.ag] || this.l[0][this.ag] >= 2 || this.ai >= 1) {
                        this.ag = (byte)(this.ag + 1);
                        this.ai = 0;
                    } else {
                        this.ai = (byte)(this.ai + 1);
                    }
                    if (this.ag != this.J) continue;
                    this.ag = 0;
                } while (!this.m);
                continue;
            }
            if (this.aj != 1) continue;
            this.m = false;
            do {
                this.m = this.ah == 0 && (this.S & 0xF0) == 16 ? this.m((byte)0) : this.o(this.ah);
                if (this.ah == 0 && this.ai >= 1) {
                    this.ai = 0;
                    this.aj = (byte)2;
                    continue block5;
                }
                if (!this.b[1][this.ah] || this.l[1][this.ah] >= 2 || this.ai >= 1) {
                    this.ah = (byte)(this.ah + 1);
                    this.ai = 0;
                } else {
                    this.ai = (byte)(this.ai + 1);
                }
                if (this.ah != this.K) continue;
                this.ah = 0;
            } while (!this.m);
        }
        return;
    }

    public final boolean b(short s, short s2) {
        int n = (this.s[s] - this.s[s2]) / 20;
        return this.a.nextInt() % 10 < 5 + n;
    }

    public final byte a(short s, short s2) {
        if (this.r[s] < 20 && this.a() % 2 == 0) {
            return 4;
        }
        if (this.r[s] < this.q[s] / 2 && this.r[s2] - this.r[s] > 10 && this.a() % 10 < 3) {
            return 2;
        }
        if (this.r[s] < this.r[s2]) {
            if (this.a() % 10 < 6) {
                return 1;
            }
            return 0;
        }
        if (this.a() % 10 < 6) {
            return 0;
        }
        return 1;
    }

    public final byte b(short s, short s2) {
        if (this.b == 1) {
            return this.a(s, s2);
        }
        if (this.b == 2) {
            return this.c(s, s2);
        }
        return 0;
    }

    public final void Q() {
        int n = this.w[this.a] / 8;
        int n2 = this.w[this.b] / 8;
        if ((n -= n2) == 0) {
            this.af = 0;
            return;
        }
        if (n == -1 || n == 1) {
            this.af = (byte)-1;
            return;
        }
        this.af = 1;
    }

    public final void R() {
        this.k = (short)(this.s[this.a] + this.s[this.a] * this.B[this.w[this.a]] / 100);
        this.l = (short)(this.s[this.a] + this.s[this.a] * this.B[this.x[this.a]] / 100);
        this.m = (short)(this.s[this.b] + this.s[this.b] * this.B[this.w[this.b]] / 100);
        this.n = (short)(this.s[this.b] + this.s[this.b] * this.B[this.x[this.b]] / 100);
        this.Q();
        this.l = false;
        if (this.b == 61) {
            if (this.o[this.a] <= 3 && this.r[this.b] == this.q[this.b]) {
                this.l = true;
            }
            return;
        }
        if (this.b == 4 || this.b == 24 || this.b == 30 || this.b == 40 || this.b == 69) {
            if (this.r[this.b] == this.q[this.b]) {
                this.l = true;
            }
            return;
        }
        if (this.b == 1) {
            this.l = true;
        }
    }

    public final void c(boolean bl) {
        if (bl) {
            for (byte by = 0; by < this.K; by = (byte)(by + 1)) {
                this.n[1][by] = 2;
            }
        } else {
            this.n[0][0] = 2;
            for (int n = 0; n < 4; n = (int)((byte)(n + 1))) {
                this.O[n] = 2;
            }
        }
    }

    public final int b() {
        if (this.l) {
            if (this.b == 61) {
                this.w[this.a] = 6;
                return 0;
            }
            if (this.b == 4) {
                this.x[this.a] = 31;
                return 1;
            }
            if (this.b == 1) {
                if (this.r[this.a] == this.q[this.a]) {
                    this.w[this.a] = 7;
                    return 2;
                }
            } else if (this.b == 24) {
                if (this.r[this.a] * 5 <= this.q[this.a]) {
                    this.w[this.a] = 5;
                    return 3;
                }
            } else if (this.P == 1) {
                this.w[this.a] = 14;
                return 4;
            }
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void S() {
        this.a.a((byte)22);
        long l = System.currentTimeMillis();
        while (this.c <= 0) {
            long l2;
            if (this.a.a != 0) {
                this.a.f();
                this.a.a = 0;
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
        return;
    }

    public final void T() {
        this.c = 0;
        this.I();
        this.J();
        this.c = 0;
        while (true) {
            this.k = false;
            this.P();
            if (this.c == 1) {
                this.c = 0;
                this.R();
                this.S();
                this.a.a((byte)21);
                this.a.t = (byte)2;
                continue;
            }
            if (this.c == 2) break;
        }
        this.O();
    }

    public final boolean a(short s, boolean bl) {
        if (bl) {
            byte by;
            if (s == this.v[0]) {
                this.c = (byte)2;
                this.g = true;
                this.M[0] = 3;
                byte[] byArray = this.g[this.J[0]];
                byte by2 = this.I[0];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                if (this.v[0] == this.e) {
                    this.H = (byte)2;
                }
                for (by = 0; by < this.v; by = (byte)(by + 1)) {
                    if (this.N[by] != 2) continue;
                    this.N[by] = 0;
                }
                this.F = 1;
                for (by = 1; by < this.u && this.M[by] != 0 && this.M[by] <= 3; by = (byte)(by + 1)) {
                }
                if (by < this.u) {
                    this.e(this.t);
                    this.F = by;
                    this.a = this.v[this.F];
                    this.a.c();
                    this.a.o = true;
                    this.a.k = false;
                    this.a.l = true;
                } else {
                    if (this.e) {
                        this.y();
                    }
                    this.z();
                    return false;
                }
            }
            for (by = 1; by < this.u; by = (byte)(by + 1)) {
                if (this.v[by] != s) continue;
                this.M[by] = 3;
                byte[] byArray = this.g[this.J[by]];
                byte by3 = this.I[by];
                byArray[by3] = (byte)(byArray[by3] & 0x3F);
                return true;
            }
        } else {
            if (s == this.w[0]) {
                this.c = (byte)2;
                this.N[0] = 3;
                byte[] byArray = this.g[this.L[0]];
                byte by = this.K[0];
                byArray[by] = (byte)(byArray[by] & 0x3F);
                for (byte by4 = 1; by4 < this.u; by4 = (byte)(by4 + 1)) {
                    if (this.M[by4] != 2) continue;
                    this.M[by4] = 0;
                }
                this.a.r = (byte)5;
                return true;
            }
            for (byte by = 1; by < this.v; by = (byte)(by + 1)) {
                if (this.w[by] != s) continue;
                this.N[by] = 3;
                byte[] byArray = this.g[this.L[by]];
                byte by5 = this.K[by];
                byArray[by5] = (byte)(byArray[by5] & 0x3F);
                return true;
            }
        }
        return true;
    }

    public final boolean b(short s, boolean bl) {
        if (bl) {
            byte by;
            if (s == this.v[0]) {
                this.c = (byte)2;
                this.g = true;
                this.M[0] = 2;
                byte[] byArray = this.g[this.J[0]];
                byte by2 = this.I[0];
                byArray[by2] = (byte)(byArray[by2] & 0x3F);
                for (by = 0; by < this.v; by = (byte)(by + 1)) {
                    if (this.N[by] != 2) continue;
                    this.N[by] = 0;
                }
                this.F = 1;
                for (by = 1; by < this.u && this.M[by] != 0 && this.M[by] <= 3; by = (byte)(by + 1)) {
                }
                if (by < this.u) {
                    this.e(this.t);
                    this.F = by;
                    this.a = this.v[this.F];
                    this.a.c();
                    this.a.o = true;
                    this.a.k = false;
                    this.a.l = true;
                } else {
                    if (this.e) {
                        this.y();
                    }
                    this.z();
                    return false;
                }
            }
            for (by = 1; by < this.u; by = (byte)(by + 1)) {
                if (this.v[by] != s) continue;
                this.M[by] = 2;
                byte[] byArray = this.g[this.J[by]];
                byte by3 = this.I[by];
                byArray[by3] = (byte)(byArray[by3] & 0x3F);
                return true;
            }
        } else {
            if (s == this.w[0]) {
                this.c = (byte)2;
                this.N[0] = 2;
                byte[] byArray = this.g[this.L[0]];
                byte by = this.K[0];
                byArray[by] = (byte)(byArray[by] & 0x3F);
                for (byte by4 = 1; by4 < this.u; by4 = (byte)(by4 + 1)) {
                    if (this.M[by4] != 2) continue;
                    this.M[by4] = 0;
                }
                this.a.r = (byte)5;
                return true;
            }
            for (byte by = 1; by < this.v; by = (byte)(by + 1)) {
                if (this.w[by] != s) continue;
                this.N[by] = 2;
                byte[] byArray = this.g[this.L[by]];
                byte by5 = this.K[by];
                byArray[by5] = (byte)(byArray[by5] & 0x3F);
                return true;
            }
        }
        return true;
    }

    public final boolean h() {
        switch (this.R) {
            case 1: {
                return this.a(this.b, false);
            }
            case 2: {
                return this.a(this.a, true);
            }
            case 3: {
                return this.b(this.b, false);
            }
            case 4: {
                return this.b(this.a, true);
            }
        }
        return true;
    }

    public final void U() {
        this.a.A = true;
        this.a.a((byte)17);
        this.c = 0;
        this.aa();
    }

    public final void V() {
        this.a.A = true;
        this.a.a((byte)16);
        this.c = 0;
        this.aa();
    }

    public final void f(byte by) {
        this.a.a((byte)20);
        this.a.r = by;
        this.c = 0;
        this.A();
    }

    public final void W() {
        this.a = 0;
        this.w();
        this.A();
        this.c = 0;
        while (true) {
            this.H();
            if (this.c == 2 || this.c == 3) {
                byte by = this.c;
                this.T();
                this.a.a((byte)20);
                this.a.c();
                this.c = by;
                if (this.h()) continue;
                this.c = (byte)4;
                continue;
            }
            if (this.c == 5) {
                this.c = 0;
                this.U();
                switch (this.c) {
                    case 1: {
                        this.f((byte)11);
                        break;
                    }
                    case 2: {
                        this.f((byte)10);
                        break;
                    }
                    case 3: {
                        this.f((byte)12);
                        break;
                    }
                    case 4: {
                        this.f((byte)13);
                    }
                }
                this.c = (byte)5;
                this.a.c();
                continue;
            }
            if (this.c == 6) {
                this.V();
                switch (this.c) {
                    case 1: {
                        this.f((byte)14);
                        break;
                    }
                    case 2: {
                        this.f((byte)10);
                    }
                }
                this.c = (byte)6;
                this.a.c();
                continue;
            }
            if (this.c > 0) break;
        }
    }

    public final void X() {
        byte by;
        this.c = 0;
        this.f = this.a[this.e];
        this.t = this.h;
        this.e = this.b[this.t];
        this.v = this.j;
        this.u = this.g[this.t];
        for (by = 0; by < this.v; by = (byte)(by + 1)) {
            this.w[by] = this.t[by];
        }
        for (by = 0; by < this.u; by = (byte)(by + 1)) {
            this.v[by] = this.b[this.t][by];
        }
        this.g = this.d[this.t];
        this.h = this.e[this.t];
        this.e = true;
        for (by = 0; by < this.u; by = (byte)(by + 1)) {
            this.M[by] = 0;
        }
        for (by = 0; by < this.v; by = (byte)(by + 1)) {
            this.N[by] = 0;
        }
        this.A = 1;
        this.B = 1;
        this.C = 1;
        this.D = 1;
        this.E = 0;
        this.w = 0;
        this.x = 0;
        this.f = true;
        this.F = (byte)-1;
        this.w = this.a(this.v, this.u, this.M, this.w);
        this.v();
        this.a.b();
        this.a.a();
        this.a.i();
    }

    public final void Y() {
        this.X();
        this.a = 0;
        this.A();
        this.c = 0;
        while (true) {
            this.H();
            if (this.c == 2 || this.c == 3) {
                byte by = this.c;
                this.T();
                this.a.a((byte)20);
                this.a.c();
                this.c = by;
                if (this.h()) continue;
                this.c = (byte)4;
                continue;
            }
            if (this.c == 5) {
                this.c = 0;
                this.U();
                switch (this.c) {
                    case 1: {
                        this.f((byte)11);
                        break;
                    }
                    case 2: {
                        this.f((byte)10);
                        break;
                    }
                    case 3: {
                        this.f((byte)12);
                        break;
                    }
                    case 4: {
                        this.f((byte)13);
                    }
                }
                this.c = (byte)5;
                this.a.c();
                continue;
            }
            if (this.c == 6) {
                this.V();
                switch (this.c) {
                    case 1: {
                        this.f((byte)14);
                        break;
                    }
                    case 2: {
                        this.f((byte)10);
                    }
                }
                this.c = (byte)6;
                this.a.c();
                continue;
            }
            if (this.c > 0) break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void Z() {
        if (this.c == 0) {
            this.n = this.b(this.d);
            this.P = 0;
            this.Q = 0;
            this.r();
            this.a.l();
            this.a.repaint();
            this.a.e();
        } else if (this.c == 2) {
            this.ar();
            this.c = 0;
        } else if (this.c == 4) {
            this.c = 0;
            this.as();
        }
        long l = System.currentTimeMillis();
        while (true) {
            if (this.a.a != 0) {
                this.a.e();
                if (!this.a.B) {
                    this.a.a = 0;
                }
            }
            if (this.c > 0) {
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void a(byte by, byte by2, short s) {
        for (int i = 0; i < this.b[by]; ++i) {
            this.b[this.a[by][i]] = s;
        }
        this.c(s, by2);
        this.v[s] = 100;
        this.a[by] = s;
    }

    public final void g(byte by) {
        byte by2 = -1;
        short s = 0;
        byte by3 = 0;
        for (int i = 0; i < this.c[by]; ++i) {
            for (int j = 0; j < this.b[by]; ++j) {
                byte by4 = this.a[by][j];
                for (int k = 0; k < this.g[by4]; ++k) {
                    short s2 = this.b[by4][k];
                    if (this.v[s2] > by2) {
                        by2 = this.v[s2];
                        s = s2;
                        by3 = by4;
                    }
                    if (s2 != this.a[by][i]) continue;
                    this.a(by, by4, s2);
                    return;
                }
            }
        }
        this.a(by, by3, s);
    }

    public final void h(byte by) {
        if (this.b == 1) {
            this.g(by);
            return;
        }
        if (this.b == 2) {
            this.O(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void aa() {
        long l = System.currentTimeMillis();
        while (true) {
            if (this.a.a != 0) {
                this.a.e();
                if (!this.a.B) {
                    this.a.a = 0;
                }
            }
            if (this.c > 0) {
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
    }

    public final void ab() {
        this.a.a((byte)4);
        this.a.l();
        this.aa();
        this.i = this.g;
    }

    public final void ac() {
        this.a.a((byte)7);
        this.aa();
    }

    public final void ad() {
        this.P = (byte)22;
        this.g = this.a[this.d][0];
        this.c = true;
        while (true) {
            this.ab();
            this.c = 0;
            this.ac();
            if (this.c > 1) break;
            this.c = 0;
        }
        this.a(this.d, this.g, this.a);
        this.c = 0;
        this.a.a((byte)4);
    }

    public final void a(byte by, short s, short s2) {
        this.a.a(by, s, s2);
        this.aa();
        this.c = 0;
    }

    public final void ae() {
        this.a.a((byte)4);
        this.c = 0;
        if (this.H == 1) {
            short s = this.a[this.f];
            this.h(this.f);
            this.a((byte)6, s, this.a[this.f]);
            this.H = 0;
        } else if (this.H == 2) {
            short s = this.a[this.d];
            this.a((byte)2, s, (short)0);
            this.ad();
            this.a((byte)6, s, this.a[this.d]);
            this.H = 0;
        } else {
            if (this.H == 3) {
                this.n = 1;
                this.H = 0;
                this.a((byte)5, this.a[this.d], (short)0);
                this.c = (byte)99;
                return;
            }
            if (this.H == 4) {
                this.a((byte)5, this.a[this.f], (short)0);
                this.H = 0;
            }
        }
        this.c = (byte)4;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void af() {
        for (byte by = this.l; by < 8; by = (byte)(by + 1)) {
            long l;
            if (this.c == 0) {
                this.q();
            }
            if (this.e <= 0) {
                this.e = this.am;
                continue;
            }
            this.am = this.e;
            if (this.e == this.d) {
                this.Z();
                if (this.n) {
                    by = this.l;
                    this.n = false;
                }
                if (this.c == 3) {
                    this.c = 0;
                }
                if (this.c != 4) continue;
                this.W();
                by = (byte)(by - 1);
                this.ae();
                if (this.c != 99) continue;
                this.c = 0;
                return;
            }
            this.a.repaint();
            this.a.serviceRepaints();
            long l2 = System.currentTimeMillis();
            this.c = 0;
            this.bb();
            if (this.c == 3) {
                this.Y();
                by = (byte)(by - 1);
                this.ae();
                if (this.c == 99) {
                    this.c = 0;
                    return;
                }
            }
            if ((l = System.currentTimeMillis() - l2) >= 2000L) continue;
            try {
                a a2 = this;
                synchronized (a2) {
                    Thread.sleep(2000L - l);
                    continue;
                }
            }
            catch (Exception exception) {}
        }
    }

    public final boolean i() {
        if (this.k < 11 && this.k > 2) {
            int n = this.a() % 500;
            return n < 4;
        }
        return false;
    }

    public final boolean j() {
        return this.a() % 1000 <= 1;
    }

    public final int a(int n, byte by, int n2) {
        return by * (n /= 2) / n2;
    }

    public final void i(byte by) {
        if (this.f[by] < 90) {
            byte by2 = by;
            this.d[by2] = (short)(this.d[by2] - this.a((int)this.d[by], this.f[by], 90));
            byte by3 = by;
            this.e[by3] = (short)(this.e[by3] - this.a((int)this.e[by], this.f[by], 90));
            byte by4 = by;
            this.e[by4] = (byte)(this.e[by4] - this.a((int)this.e[by], this.f[by], 90));
        }
        if (this.f[by] < 99) {
            byte by5 = by;
            this.g[by5] = (short)(this.g[by5] - this.a((int)this.g[by], this.f[by], 99));
            byte by6 = by;
            this.f[by6] = (short)(this.f[by6] - this.a((int)this.f[by], this.f[by], 99));
        }
        if (this.f[by] > 0) {
            byte by7 = by;
            this.f[by7] = (byte)(this.f[by7] - (this.f[by] / 10 + 1));
        }
    }

    public final void ag() {
        this.ba();
        this.q((byte)4);
    }

    public final void j(byte by) {
        if (this.f[by] < 90) {
            this.h[by] = 0;
            for (byte by2 = 0; by2 < this.g[by]; by2 = (byte)(by2 + 1)) {
                short s = this.b[by][by2];
                this.k[s] = (short)(this.k[s] - this.a((int)this.k[this.b[by][by2]], this.f[by], 90));
                byte by3 = by;
                this.h[by3] = (short)(this.h[by3] + this.k[this.b[by][by2]]);
            }
            byte by4 = by;
            this.i[by4] = (short)(this.i[by4] - this.a((int)this.i[by], this.f[by], 90));
            byte by5 = by;
            this.e[by5] = (byte)(this.e[by5] - this.a((int)this.e[by], this.f[by], 90));
        }
        if (this.f[by] < 99) {
            byte by6 = by;
            this.a[by6] = this.a[by6] - this.a(this.a[by], this.f[by], 99);
        }
        if (this.f[by] > 0) {
            byte by7 = by;
            this.f[by7] = (byte)(this.f[by7] - (this.f[by] / 10 + 1));
        }
    }

    public final boolean p(byte by) {
        if (this.e[by] < 15) {
            int n = this.e[by] * 10 / 15 + 80;
            if (this.a() % 100 >= n) {
                return true;
            }
        } else if (this.e[by] < 30) {
            int n = (this.e[by] - 15) / 3 + 90;
            if (this.a() % 100 >= n) {
                return true;
            }
        } else if (this.e[by] < 40) {
            int n = (this.e[by] - 30) / 5 + 98;
            if (this.a() % 100 >= n) {
                return true;
            }
        } else if (this.e[by] < 60) {
            int n = (this.e[by] - 40) / 5 + 998;
            if (this.a() % 1000 >= n) {
                return true;
            }
        }
        return false;
    }

    public final void k(byte by) {
        byte by2 = by;
        this.e[by2] = (byte)(this.e[by2] - this.a() % 15);
        if (this.e[by] < 0) {
            this.e[by] = 0;
        }
    }

    public final short a(short s, int n, int n2) {
        int n3 = s * n / n2;
        if (n3 > 0) {
            return (short)(s - this.a() % n3);
        }
        return s;
    }

    public final void l(byte by) {
        int n = 0;
        if (this.e[by] < 15) {
            byte by2 = by;
            this.a[by2] = this.a[by2] / 3;
            byte by3 = by;
            this.f[by3] = (short)(this.f[by3] / 3);
            byte by4 = by;
            this.g[by4] = (short)(this.g[by4] / 3);
            byte by5 = by;
            this.d[by5] = (short)(this.d[by5] / 2);
            byte by6 = by;
            this.e[by6] = (short)(this.e[by6] / 2);
            this.k(by);
            this.i[by] = this.a(this.i[by], 2, 3);
            for (byte by7 = 0; by7 < this.g[by]; by7 = (byte)(by7 + 1)) {
                this.k[this.b[by][by7]] = this.a(this.k[this.b[by][by7]], 2, 3);
                n += this.k[this.b[by][by7]];
            }
            this.h[by] = (short)n;
            return;
        }
        if (this.e[by] < 30) {
            byte by8 = by;
            this.a[by8] = this.a[by8] / 2;
            byte by9 = by;
            this.f[by9] = (short)(this.f[by9] / 2);
            byte by10 = by;
            this.g[by10] = (short)(this.g[by10] / 2);
            this.d[by] = (short)(this.d[by] * 3 / 7);
            this.e[by] = (short)(this.e[by] * 3 / 7);
            this.k(by);
            this.i[by] = this.a(this.i[by], 1, 2);
            for (byte by11 = 0; by11 < this.g[by]; by11 = (byte)(by11 + 1)) {
                this.k[this.b[by][by11]] = this.a(this.k[this.b[by][by11]], 1, 2);
                n += this.k[this.b[by][by11]];
            }
            this.h[by] = (short)n;
            return;
        }
        this.a[by] = this.a[by] * 2 / 3;
        this.f[by] = (short)(this.f[by] * 2 / 3);
        this.g[by] = (short)(this.g[by] * 2 / 3);
        this.d[by] = (short)(this.d[by] * 1 / 7);
        this.e[by] = (short)(this.e[by] * 1 / 7);
        this.k(by);
        this.i[by] = this.a(this.i[by], 1, 5);
        for (byte by12 = 0; by12 < this.g[by]; by12 = (byte)(by12 + 1)) {
            this.k[this.b[by][by12]] = this.a(this.k[this.b[by][by12]], 1, 5);
            n += this.k[this.b[by][by12]];
        }
        this.h[by] = (short)n;
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

    public final void m(byte by) {
        int n = this.d[by] + this.b((int)this.g[by], (int)this.e[by], this.a[by]);
        if (n > 9999) {
            this.d[by] = 9999;
            return;
        }
        this.d[by] = (short)n;
    }

    public final void n(byte by) {
        int n = this.e[by] + this.b((int)this.f[by], (int)this.e[by], this.a[by]);
        if (n > 9999) {
            this.e[by] = 9999;
            return;
        }
        this.e[by] = (short)n;
    }

    public final void ah() {
        short s;
        int n;
        if (this.c < 210) {
            return;
        }
        if (this.a() % 100 < 2 && this.a[this.d] == this.b[14]) {
            for (n = 0; n < this.g[14]; ++n) {
                s = this.b[14][n];
                if (s != 55) continue;
                this.t[s] = 99;
                this.u[s] = 99;
                this.s[s] = 99;
                this.q[s] = 99;
            }
        }
        if (this.a() % 100 < 3 && this.a[this.d] == this.b[2]) {
            for (n = 0; n < this.g[2]; ++n) {
                s = this.b[2][n];
                if (s != 193) continue;
                this.t[s] = 99;
                this.u[s] = 99;
                this.s[s] = 99;
                this.q[s] = 99;
            }
        }
    }

    public final boolean k() {
        for (int n = 1; n < 37; n = (int)((byte)(n + 1))) {
            if (this.b[n] == this.a[this.d]) continue;
            return false;
        }
        return true;
    }

    public final boolean l() {
        return this.b[this.d] == 0;
    }

    public final void o(byte by) {
        int n = this.a() % 4;
        int n2 = this.a() % 5 + 1;
        switch (n) {
            case 0: {
                byte by2 = by;
                this.f[by2] = (short)(this.f[by2] + n2);
                if (this.f[by] <= 999) break;
                this.f[by] = 999;
                break;
            }
            case 1: {
                byte by3 = by;
                this.g[by3] = (short)(this.g[by3] + n2);
                if (this.g[by] <= 999) break;
                this.g[by] = 999;
                break;
            }
            case 2: {
                byte by4 = by;
                this.a[by4] = this.a[by4] + n2 * 500;
                if (this.a[by] <= 990000) break;
                this.a[by] = 990000;
                break;
            }
            case 3: {
                byte by5 = by;
                this.f[by5] = (byte)(this.f[by5] + (n2 / 2 + 1));
                if (this.f[by] <= 99) break;
                this.f[by] = 99;
            }
        }
        n2 = this.a();
        if (this.e[by] < 30) {
            byte by6 = by;
            this.e[by6] = (byte)(this.e[by6] + (n2 % 4 + 2));
            return;
        }
        if (this.e[by] < 50) {
            byte by7 = by;
            this.e[by7] = (byte)(this.e[by7] + (n2 % 2 + 1));
            return;
        }
        byte by8 = by;
        this.e[by8] = (byte)(this.e[by8] + n2 % 2);
    }

    public final void ai() {
        for (int i = 1; i < 8; ++i) {
            if (this.a[i] <= 0 || this.a[i] == this.d) continue;
            for (int j = 0; j < this.b[i]; ++j) {
                this.o(this.a[i][j]);
            }
        }
    }

    public final void aj() {
        short s;
        this.p = 0;
        if (this.k()) {
            this.a = 0;
            this.q((byte)23);
            this.aa();
            this.c = 1;
            return;
        }
        if (this.l()) {
            this.a = 1;
            this.q((byte)23);
            this.aa();
            this.c = 1;
            return;
        }
        this.k = (byte)(this.k + 1);
        for (s = 1; s < 37; s = (short)((short)(s + 1))) {
            if (!this.i()) continue;
            byte by = this.p;
            this.p = (byte)(by + 1);
            this.F[by] = (byte)s;
        }
        if (this.p < 1) {
            for (s = 1; s < 37; s = (short)(s + 1)) {
                if (!this.j()) continue;
                byte by = this.p;
                this.p = (byte)(by + 1);
                this.F[by] = (byte)s;
            }
        } else {
            for (s = 0; s < this.p; s = (short)(s + 1)) {
                this.i(this.F[s]);
            }
            this.a = this.a() % 3 + 6;
            this.q((byte)19);
            this.ag();
            this.p = 0;
        }
        if (this.p > 0) {
            for (s = 0; s < this.p; s = (short)(s + 1)) {
                this.j(this.F[s]);
            }
            this.a = 9;
            this.q((byte)19);
            this.ag();
            this.p = 0;
        }
        for (s = 1; s < 37; s = (short)(s + 1)) {
            if (this.b[s] <= 0 || !this.p((byte)s)) continue;
            byte by = this.p;
            this.p = (byte)(by + 1);
            this.F[by] = (byte)s;
        }
        if (this.p > 0) {
            for (s = 0; s < this.p; s = (short)(s + 1)) {
                this.l(this.F[s]);
            }
            this.a = 13;
            this.q((byte)19);
            this.ag();
            this.p = 0;
        }
        if (this.b >= 2) {
            this.ai();
        }
        if (this.k == 4) {
            for (s = 1; s < 37; s = (short)(s + 1)) {
                this.m((byte)s);
            }
            this.a = 10;
            this.q((byte)19);
            this.ag();
        } else if (this.k == 10) {
            for (s = 1; s < 37; s = (short)(s + 1)) {
                this.n((byte)s);
            }
            this.a = 11;
            this.q((byte)19);
            this.ag();
        }
        for (s = 1; s < 232; s = (short)(s + 1)) {
            if (this.r[s] >= this.q[s]) continue;
            short s2 = s;
            this.r[s2] = (byte)(this.r[s2] + this.q[s] / 10);
            if (this.r[s] <= this.q[s]) continue;
            this.r[s] = this.q[s];
        }
        for (s = (short)(this.a - 1); s >= 0; s = (short)(s - 1)) {
            short s3 = s;
            this.A[s3] = (byte)(this.A[s3] - 1);
            if (this.A[s] > 0) continue;
            for (byte by = 1; by <= 7; by = (byte)(by + 1)) {
                if (by != this.z[s]) continue;
                this.a = 14;
                this.f = by;
                this.q((byte)19);
                this.ag();
                break;
            }
            this.z[s] = this.z[this.a];
            this.A[s] = this.A[this.a];
            this.a = (byte)(this.a - 1);
        }
        this.ah();
        for (s = 0; s < 8; s = (short)(s + 1)) {
            this.D[s] = 1;
        }
    }

    public final void ak() {
        while (this.k <= 12) {
            this.af();
            if (this.c != 0) {
                return;
            }
            this.l = 0;
            this.aj();
            if (this.c == 0) continue;
            return;
        }
    }

    public final void f(short s) {
        this.k[s] = 0;
        if (this.y[s] <= 0) {
            return;
        }
        byte by = this.y[s];
        this.c[by] = this.a(this.c[by], this.h[by], s);
        byte by2 = by;
        this.h[by2] = (byte)(this.h[by2] + 1);
    }

    public final void g(short s) {
        for (short s2 = 1; s2 < 232; s2 = (short)(s2 + 1)) {
            if (this.m[s2] != s) continue;
            this.f(s2);
        }
    }

    public final void al() {
        short s = 0;
        this.k = 1;
        this.c = (short)(this.c + 1);
        this.g(this.c);
        for (int n = 0; n < 26; n = (int)((byte)(n + 1))) {
            int n2 = 0;
            if (this.n[n] != this.c) continue;
            short s2 = this.o[n];
            for (int n3 = 1; n3 < 37; n3 = (int)((byte)(n3 + 1))) {
                for (byte by = 0; by < this.g[n3]; by = (byte)(by + 1)) {
                    if (this.b[n3][by] != this.p[n]) continue;
                    s = this.p[n];
                    n2 = n3;
                    break;
                }
                if (n2 > 0) break;
            }
            if (n2 == 0) continue;
            if (this.g[n2] < 10) {
                int n4 = n2;
                byte by = this.g[n4];
                this.g[n4] = (byte)(by + 1);
                this.b[n2][by] = s2;
                if (this.b[n2] != this.a[this.d]) continue;
                this.a = 12;
                this.a = s2;
                this.b = s;
                this.q((byte)19);
                this.ag();
                continue;
            }
            this.c[n2] = this.a(this.c[n2], this.h[n2], s2);
            int n5 = n2;
            this.h[n5] = (byte)(this.h[n5] + 1);
        }
    }

    public final void am() {
        while (true) {
            this.ak();
            if (this.c != 0) {
                return;
            }
            this.al();
        }
    }

    public final void an() {
        this.am();
    }

    public final void run() {
        try {
            for (int n = 0; n < 8; n = (int)((byte)(n + 1))) {
                this.D[n] = 1;
            }
            this.o();
            this.n();
            if (this.c == 1) {
                this.c = 0;
            }
            this.an();
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            b.a(exception);
            return;
        }
    }

    public final void ao() {
        this.a[8] = 0;
        this.a[8] = 0;
        this.b[8] = 0;
        this.a[8] = new byte[37];
        String string = this.b("/lib114.jg");
        this.a(string, 3);
        string = this.b("/lib115.jg");
        this.a(string, 1);
        string = this.b("/lib116.jg");
        System.gc();
        this.a(string, 2);
        for (int n = 229; n < 232; n = (int)((short)(n + 1))) {
            this.j[n] = 0;
            this.m[n] = 189;
            this.y[n] = 1;
            this.a[n] = false;
        }
        this.ap();
    }

    public final String b(String string) {
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
        byte by = 0;
        short s = 1;
        String string2 = null;
        try {
            for (int i = 0; i < string.length(); ++i) {
                if (string.charAt(i) != ',' && string.charAt(i) != '\n' && string.charAt(i) != '\r') continue;
                string2 = i == n2 ? "0" : string.substring(n2, i);
                n2 = i + 1;
                if (n == 1) {
                    this.b(s, by, string2);
                } else if (n == 2) {
                    this.c(s, by, string2);
                } else if (n == 3) {
                    this.d(s, by, string2);
                }
                if (string.charAt(i) == '\n') {
                    s = (short)(s + 1);
                    by = 0;
                    continue;
                }
                by = (byte)(by + 1);
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
                this.a[s] = c.a[s];
                this.d[s] = (byte)s;
                this.h[s] = 0;
                this.i[s] = 0;
                this.g[s] = 0;
                this.b[s] = new short[10];
                this.k[s] = 0;
                return;
            }
            case 1: {
                this.b[s] = Short.parseShort(string);
                if (this.b[s] <= 0) break;
                for (int n = 1; n < 8; n = (int)((byte)(n + 1))) {
                    if (this.b[s] != this.a[n]) continue;
                    int n2 = n;
                    byte by2 = this.b[n2];
                    this.b[n2] = (byte)(by2 + 1);
                    this.a[n][by2] = this.d[s];
                }
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                this.c[s] = Short.parseShort(string);
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                this.e[s] = Byte.parseByte(string);
                return;
            }
            case 6: {
                this.d[s] = Short.parseShort(string);
                return;
            }
            case 7: {
                this.e[s] = Short.parseShort(string);
                return;
            }
            case 8: {
                this.f[s] = Short.parseShort(string);
                return;
            }
            case 9: {
                this.g[s] = Short.parseShort(string);
                return;
            }
            case 10: {
                this.a[s] = Integer.parseInt(string);
                return;
            }
            case 11: {
                this.f[s] = Byte.parseByte(string);
                return;
            }
            case 12: {
                this.g[s] = 0;
                return;
            }
            case 13: {
                this.h[s] = Short.parseShort(string);
                return;
            }
            case 14: {
                this.i[s] = Short.parseShort(string);
                return;
            }
            case 15: {
                this.j[s] = Byte.parseByte(string);
                return;
            }
            case 16: {
                if (Byte.parseByte(string) != 1) break;
                short s2 = s;
                this.k[s2] = (byte)(this.k[s2] | 1);
                return;
            }
            case 17: {
                if (Byte.parseByte(string) != 1) break;
                short s3 = s;
                this.k[s3] = (byte)(this.k[s3] | 2);
                return;
            }
            case 18: {
                if (Byte.parseByte(string) != 1) break;
                short s4 = s;
                this.k[s4] = (byte)(this.k[s4] | 4);
                return;
            }
            case 19: {
                if (Byte.parseByte(string) != 1) break;
                short s5 = s;
                this.k[s5] = (byte)(this.k[s5] | 8);
                return;
            }
            case 20: {
                this.l[s] = Byte.parseByte(string);
                return;
            }
            case 21: {
                this.m[s] = Byte.parseByte(string);
            }
        }
    }

    public final void c(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.j[s] = s;
                return;
            }
            case 1: {
                this.b[s] = string;
                return;
            }
            case 2: {
                this.o[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 3: {
                this.p[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 4: {
                this.k[s] = (short)Integer.parseInt(string);
                return;
            }
            case 5: {
                this.r[s] = (byte)Integer.parseInt(string);
                this.q[s] = this.r[s];
                return;
            }
            case 6: {
                this.t[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 7: {
                this.s[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 8: {
                this.u[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 9: {
                this.v[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 10: {
                this.l[s] = (short)(Integer.parseInt(string) * 100);
                return;
            }
            case 11: {
                this.w[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 12: {
                this.x[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 13: {
                this.m[s] = (short)Integer.parseInt(string);
                return;
            }
            case 14: {
                this.y[s] = (byte)Integer.parseInt(string);
                return;
            }
            case 15: {
                if (this.m[s] != 189) break;
                byte by2 = this.y[s];
                if (string.trim().compareTo("0") == 0) {
                    this.c[by2] = this.a(this.c[by2], this.h[by2], this.j[s]);
                    byte by3 = by2;
                    this.h[by3] = (byte)(this.h[by3] + 1);
                    return;
                }
                if (string.trim().compareTo("1") != 0) break;
                byte by4 = by2;
                byte by5 = this.g[by4];
                this.g[by4] = (byte)(by5 + 1);
                this.b[by2][by5] = this.j[s];
            }
        }
    }

    public final void d(short s, byte by, String string) {
        switch (by) {
            case 0: {
                this.a[s] = Byte.parseByte(string);
                this.a[s] = new byte[37];
                return;
            }
            case 1: {
                this.a[s] = Short.parseShort(string);
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                this.b[s] = 0;
            }
        }
    }

    public final void ap() {
        RecordStore recordStore = null;
        try {
            byte[] byArray;
            int n;
            String string = null;
            for (n = 0; n < 3; n = (int)((byte)(n + 1))) {
                int n2;
                string = "record" + n;
                recordStore = null;
                recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)false);
                if (recordStore.getNumRecords() <= 0) {
                    try {
                        for (n2 = 0; n2 < 100; ++n2) {
                            recordStore.closeRecordStore();
                        }
                        continue;
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                        continue;
                    }
                }
                byArray = recordStore.getRecord(1);
                this.r[n] = (short)(byArray[1] << 8 | byArray[0] & 0xFF);
                this.C[n] = byArray[2];
                this.s[n] = (short)(byArray[4] << 8 | byArray[3] & 0xFF);
                this.d[n] = this.b[this.r[n]];
                if (this.r[n] >= 229) {
                    byte by = byArray[5];
                    byte[] byArray2 = new byte[by - 1];
                    for (n2 = 0; n2 < by - 1; n2 = (int)((byte)(n2 + 1))) {
                        byArray2[n2] = byArray[6 + n2];
                    }
                    this.d[n] = new String(byArray2, "UTF-8");
                }
                try {
                    for (n2 = 0; n2 < 100; ++n2) {
                        recordStore.closeRecordStore();
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
            }
            recordStore = RecordStore.openRecordStore((String)"newPs", (boolean)true, (int)1, (boolean)false);
            if (recordStore.getNumRecords() > 0) {
                byArray = recordStore.getRecord(1);
                this.a(byArray, 0);
            }
            try {
                for (n = 0; n < 100; ++n) {
                    recordStore.closeRecordStore();
                }
            }
            catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
            recordStore = null;
            return;
        }
        catch (Exception exception) {
            if (recordStore != null) {
                try {
                    for (int i = 0; i < 100; ++i) {
                        recordStore.closeRecordStore();
                    }
                    return;
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    return;
                }
                catch (Exception exception2) {}
            }
            return;
        }
    }

    public final short[] a(short[] sArray, byte by, short s) {
        short[] sArray2 = new short[by + 1];
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            sArray2[by2] = sArray[by2];
        }
        sArray2[by2] = s;
        return sArray2;
    }

    public final void p(byte by) {
        this.d = by;
        this.c = 1;
        this.a.a((byte)4);
    }

    public final boolean q(byte by) {
        for (byte by2 = 0; by2 < this.a; by2 = (byte)(by2 + 1)) {
            if (this.b[by] != this.a[this.z[by2]]) continue;
            return true;
        }
        return false;
    }

    public final short a(short[] sArray, byte by) {
        short s = 0;
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            s = (short)(s + this.k[sArray[by2]]);
        }
        return s;
    }

    public final void b(short s, byte by) {
        byte by2 = by;
        this.i[by2] = (short)(this.i[by2] + s);
        byte by3 = by;
        this.d[by3] = (short)(this.d[by3] - (s + 4) / 5);
        byte by4 = by;
        this.a[by4] = this.a[by4] - s;
    }

    public final void q(byte by) {
        this.a.a(by);
        this.a.repaint();
        this.d = true;
    }

    public final void aq() {
        this.Q = 0;
        this.P = 0;
        this.a = true;
        this.b = true;
        this.i = this.g;
        System.gc();
    }

    public final void ar() {
        this.aq();
        this.q((byte)6);
    }

    public final void as() {
        this.aq();
        this.n = (byte)(this.n - 1);
        if (this.n <= 0) {
            this.P = 0;
            this.c = (byte)3;
            this.q((byte)4);
            return;
        }
        if (this.b[this.g] != this.a[this.d]) {
            this.r();
        }
        this.q((byte)6);
    }

    public final void at() {
        this.a.l();
        this.q((byte)4);
    }

    public final void au() {
        this.g = this.h;
        this.Q = (byte)(this.Q + 1);
        this.aS();
    }

    public final void av() {
        this.b = this.a;
        this.q((byte)7);
    }

    public final void aw() {
        this.c(this.a, this.g);
        this.Q = (byte)(this.Q + 1);
        this.aS();
    }

    public final void ax() {
        this.c(this.a, this.g);
        this.a(this.a, c.b[2][3]);
        this.a = this.t[0];
        this.Q = (byte)6;
    }

    public final void ay() {
        this.b(this.g, this.a);
        this.q((byte)11);
        this.Q = (byte)(this.Q + 1);
    }

    public final void a(short s, String string) {
        this.d = s;
        this.a = string;
        this.q((byte)10);
    }

    public final void az() {
        if (this.a(this.g, this.a, this.b)) {
            this.a(this.a, c.b[2][0]);
        } else {
            this.a(this.a, c.b[2][1]);
        }
        this.Q = (byte)(this.Q + 1);
    }

    public final void aA() {
        this.d(this.a, this.g);
        this.Q = (byte)(this.Q + 1);
        this.a(this.a, c.b[2][3]);
    }

    public final void aB() {
        for (int i = 0; i < this.j; ++i) {
            if (this.t[i] != this.a) continue;
            boolean bl = false;
            this.t[i] = this.t[0];
            this.t[0] = this.a;
        }
        this.Q = (byte)(this.Q + 1);
        this.a(this.a, c.b[0][2]);
    }

    public final void aC() {
        if (this.a > this.d[this.g]) {
            this.a = this.d[this.g];
        }
        this.q((byte)13);
    }

    public final void aD() {
        this.a = this.d(this.a);
        this.aC();
    }

    public final void aE() {
        this.a = this.e(this.a);
        this.aC();
    }

    public final void a(byte by, short s, int n) {
        int n2 = this.a(s, n);
        byte by2 = by;
        this.d[by2] = (short)(this.d[by2] - n);
        byte by3 = by;
        this.f[by3] = (short)(this.f[by3] + n2);
        if (this.f[by] > 999) {
            this.f[by] = 999;
        }
        if (this.e[by] < 99) {
            byte by4 = by;
            this.e[by4] = (byte)(this.e[by4] + 1);
        }
    }

    public final void aF() {
        this.a(this.g, this.a, this.a);
        this.a(this.a, c.b[3][0]);
        this.Q = (byte)(this.Q + 1);
    }

    public final void b(byte by, short s, int n) {
        int n2 = this.a(s, n);
        byte by2 = by;
        this.d[by2] = (short)(this.d[by2] - n);
        byte by3 = by;
        this.g[by3] = (short)(this.g[by3] + n2);
        if (this.g[by] > 999) {
            this.g[by] = 999;
        }
        if (this.e[by] < 99) {
            byte by4 = by;
            this.e[by4] = (byte)(this.e[by4] + 1);
        }
    }

    public final void aG() {
        this.b(this.g, this.a, this.a);
        this.a(this.a, c.b[3][1]);
        this.Q = (byte)(this.Q + 1);
    }

    public final void c(byte by, short s, int n) {
        int n2 = this.b(s, n);
        byte by2 = by;
        this.d[by2] = (short)(this.d[by2] - n);
        byte by3 = by;
        this.f[by3] = (byte)(this.f[by3] + n2);
        if (this.f[by] > 99) {
            this.f[by] = 99;
        }
    }

    public final void aH() {
        this.c(this.g, this.a, this.a);
        this.a(this.a, c.b[3][3]);
        this.Q = (byte)(this.Q + 1);
    }

    public final void d(byte by, short s, int n) {
        int n2 = this.c(s, n);
        byte by2 = by;
        this.d[by2] = (short)(this.d[by2] - n);
        byte by3 = by;
        this.a[by3] = this.a[by3] + n2;
        if (this.a[by] > 990000) {
            this.a[by] = 990000;
        }
        if (this.e[by] < 99) {
            byte by4 = by;
            this.e[by4] = (byte)(this.e[by4] + 1);
        }
    }

    public final void aI() {
        this.d(this.g, this.a, this.a);
        this.a(this.a, c.b[3][4]);
        this.Q = (byte)(this.Q + 1);
    }

    public final int a(short s, short s2) {
        byte by = this.t[s];
        int n = this.t[s2];
        byte by2 = this.v[s2];
        if (by2 > 99) {
            n = 10000;
        } else if (by2 > 96) {
            n += 50;
        } else if (by2 > 89) {
            n += 20;
        }
        if (by - n <= this.a() % 30) {
            return 0;
        }
        int n2 = this.a() % (by - n);
        n2 /= 10;
        n2 *= 9;
        n2 /= 5;
        return ++n2;
    }

    public final boolean c(short s, short s2) {
        int n = this.a(s, s2);
        if (n > 0) {
            short s3 = s2;
            this.v[s3] = (byte)(this.v[s3] - (byte)n);
            if (this.v[s2] < 0) {
                this.v[s2] = 0;
            }
            return true;
        }
        return false;
    }

    public final void aJ() {
        if (this.c(this.a, this.b)) {
            this.a(this.a, c.b[5][1]);
        } else {
            this.a(this.a, c.b[5][0]);
        }
        this.Q = (byte)(this.Q + 1);
    }

    public final int b(short s, short s2) {
        byte by = this.u[s];
        byte by2 = this.t[s2];
        int n = by * by;
        n /= 100;
        if ((n -= by2) <= this.a() % 100) {
            return -1;
        }
        return 0;
    }

    public final void aK() {
        short s = this.a[this.f];
        int n = this.b(this.a, s);
        if (n == 0) {
            this.z[this.a] = this.f;
            this.A[this.a] = 12;
            this.a = (byte)(this.a + 1);
            this.a(this.a, c.b[1][0]);
        } else {
            this.a(this.a, c.b[1][1]);
        }
        this.Q = (byte)(this.Q + 1);
    }

    public final void h(short s) {
        int n = 100 - this.v[s];
        int n2 = (n + 50) / 2;
        if (n == n2) {
            n2 = this.a() % 3 - 1;
        } else if (this.v[s] > 50) {
            n2 = this.a() % (n2 - n);
        } else {
            n = this.a() % (n - n2);
        }
        this.v[s] = (byte)(n + n2);
    }

    public final boolean d(short s, short s2) {
        byte by = this.t[s];
        byte by2 = this.t[s2];
        byte by3 = this.v[s2];
        if (by3 > 35) {
            return false;
        }
        int n = by - by2 + (35 - by3);
        if (n >= this.a() % 20) {
            this.h(s2);
            return true;
        }
        return false;
    }

    public final boolean a(byte by, byte by2, short s, short s2) {
        if (this.d(s, s2)) {
            int n;
            int n2;
            for (n2 = 0; n2 < this.g[by2] && this.b[by2][n2] != s2; ++n2) {
            }
            byte by3 = by2;
            this.g[by3] = (byte)(this.g[by3] - 1);
            for (n = n2; n < this.g[by2]; ++n) {
                this.b[by2][n] = this.b[by2][n + 1];
            }
            this.b[by][this.g[by]] = s2;
            byte by4 = by;
            this.g[by4] = (byte)(this.g[by4] + 1);
            byte by5 = by2;
            this.h[by5] = (short)(this.h[by5] - this.k[s2]);
            byte by6 = by;
            this.h[by6] = (short)(this.h[by6] + this.k[s2]);
            if (this.g[by2] == 0) {
                byte by7 = this.d(this.b[by2]);
                for (n2 = 0; n2 < this.b[by7] && this.a[by7][n2] != by2; ++n2) {
                }
                byte by8 = by7;
                this.b[by8] = (byte)(this.b[by8] - 1);
                for (n = n2; n < this.b[by7]; ++n) {
                    this.a[by7][n] = this.a[by7][n + 1];
                }
                this.b[by2] = 0;
                this.c[by2] = 0;
            } else {
                this.c[by2] = this.b[by2][0];
            }
            return true;
        }
        return false;
    }

    public final void aL() {
        if (this.a(this.g, this.h, this.a, this.b)) {
            this.a(this.a, c.b[5][3]);
        } else {
            this.a(this.a, c.b[5][2]);
        }
        this.Q = (byte)(this.Q + 1);
    }

    public final int b(short s) {
        if (this.t[s] < 30 || this.t[s] > 70) {
            return this.a() % 5 + 6;
        }
        return this.a() % 7 + 6;
    }

    public final void aM() {
        short s = 10;
        if (this.t[this.a] < 30) {
            s = 30;
        } else if (this.t[this.a] < 60) {
            s = 20;
        }
        if (this.d[this.g] >= s) {
            short s2 = this.a;
            this.t[s2] = (byte)(this.t[s2] + this.b(this.a));
            byte by = this.g;
            this.d[by] = (short)(this.d[by] - s);
            this.a(this.a, c.b[4][3]);
            this.Q = (byte)(this.Q + 1);
            return;
        }
        this.a(this.a, "\u8d44\u8d22\u4e0d\u8db3");
        this.Q = (byte)3;
    }

    public final void aN() {
        this.bi();
        this.a(this.a, c.b[4][4]);
        this.Q = (byte)(this.Q + 1);
    }

    public final short a(byte by, byte[] byArray, short s) {
        short s2 = s;
        s = (short)(s + 1);
        byArray[s2] = (byte)(this.b[by] & 0xFF);
        short s3 = s;
        s = (short)(s + 1);
        byArray[s3] = (byte)(this.b[by] >> 8);
        short s4 = s;
        s = (short)(s + 1);
        byArray[s4] = (byte)(this.c[by] & 0xFF);
        short s5 = s;
        s = (short)(s + 1);
        byArray[s5] = (byte)(this.c[by] >> 8);
        short s6 = s;
        s = (short)(s + 1);
        byArray[s6] = this.e[by];
        short s7 = s;
        s = (short)(s + 1);
        byArray[s7] = (byte)(this.d[by] & 0xFF);
        short s8 = s;
        s = (short)(s + 1);
        byArray[s8] = (byte)(this.d[by] >> 8);
        short s9 = s;
        s = (short)(s + 1);
        byArray[s9] = (byte)(this.e[by] & 0xFF);
        short s10 = s;
        s = (short)(s + 1);
        byArray[s10] = (byte)(this.e[by] >> 8);
        short s11 = s;
        s = (short)(s + 1);
        byArray[s11] = (byte)(this.f[by] & 0xFF);
        short s12 = s;
        s = (short)(s + 1);
        byArray[s12] = (byte)(this.f[by] >> 8);
        short s13 = s;
        s = (short)(s + 1);
        byArray[s13] = (byte)(this.g[by] & 0xFF);
        short s14 = s;
        s = (short)(s + 1);
        byArray[s14] = (byte)(this.g[by] >> 8);
        short s15 = s;
        s = (short)(s + 1);
        byArray[s15] = (byte)(this.a[by] & 0xFF);
        short s16 = s;
        s = (short)(s + 1);
        byArray[s16] = (byte)(this.a[by] >> 8 & 0xFF);
        short s17 = s;
        s = (short)(s + 1);
        byArray[s17] = (byte)(this.a[by] >> 16 & 0xFF);
        short s18 = s;
        s = (short)(s + 1);
        byArray[s18] = (byte)(this.a[by] >> 24);
        short s19 = s;
        s = (short)(s + 1);
        byArray[s19] = this.f[by];
        short s20 = s;
        s = (short)(s + 1);
        byArray[s20] = this.g[by];
        short s21 = s;
        s = (short)(s + 1);
        byArray[s21] = this.h[by];
        short s22 = s;
        s = (short)(s + 1);
        byArray[s22] = this.i[by];
        short s23 = s;
        s = (short)(s + 1);
        byArray[s23] = (byte)(this.h[by] & 0xFF);
        short s24 = s;
        s = (short)(s + 1);
        byArray[s24] = (byte)(this.h[by] >> 8);
        short s25 = s;
        s = (short)(s + 1);
        byArray[s25] = (byte)(this.i[by] & 0xFF);
        short s26 = s;
        s = (short)(s + 1);
        byArray[s26] = (byte)(this.i[by] >> 8);
        short s27 = s;
        s = (short)(s + 1);
        byArray[s27] = this.j[by];
        short s28 = s;
        s = (short)(s + 1);
        byArray[s28] = this.k[by];
        short s29 = s;
        s = (short)(s + 1);
        byArray[s29] = this.l[by];
        return s;
    }

    public final short a(short s, byte[] byArray, short s2) {
        short s3 = s2;
        s2 = (short)(s2 + 1);
        byArray[s3] = (byte)(s & 0xFF);
        short s4 = s2;
        s2 = (short)(s2 + 1);
        byArray[s4] = (byte)(s >> 8);
        return s2;
    }

    public final short b(short s, byte[] byArray, short s2) {
        short s3 = s2;
        s2 = (short)(s2 + 1);
        byArray[s3] = this.o[s];
        short s4 = s2;
        s2 = (short)(s2 + 1);
        byArray[s4] = this.s[s];
        short s5 = s2;
        s2 = (short)(s2 + 1);
        byArray[s5] = (byte)(this.k[s] & 0xFF);
        short s6 = s2;
        s2 = (short)(s2 + 1);
        byArray[s6] = (byte)(this.k[s] >> 8);
        short s7 = s2;
        s2 = (short)(s2 + 1);
        byArray[s7] = this.r[s];
        short s8 = s2;
        s2 = (short)(s2 + 1);
        byArray[s8] = this.t[s];
        short s9 = s2;
        s2 = (short)(s2 + 1);
        byArray[s9] = this.v[s];
        short s10 = s2;
        s2 = (short)(s2 + 1);
        byArray[s10] = (byte)(this.l[s] & 0xFF);
        short s11 = s2;
        s2 = (short)(s2 + 1);
        byArray[s11] = (byte)(this.l[s] >> 8);
        short s12 = s2;
        s2 = (short)(s2 + 1);
        byArray[s12] = this.w[s];
        short s13 = s2;
        s2 = (short)(s2 + 1);
        byArray[s13] = this.x[s];
        return s2;
    }

    public final short b(byte by, byte[] byArray, short s) {
        short s2 = s;
        s = (short)(s + 1);
        byArray[s2] = (byte)(this.a[by] & 0xFF);
        short s3 = s;
        s = (short)(s + 1);
        byArray[s3] = (byte)(this.a[by] >> 8);
        return s;
    }

    public final short a(byte[] byArray, short s) {
        for (int n = 229; n < 232; n = (int)((short)(n + 1))) {
            short s2;
            if (this.j[n] <= 0) {
                short s3 = s;
                s = (short)(s + 1);
                byArray[s3] = 0;
                continue;
            }
            short s4 = s;
            s = (short)(s + 1);
            byArray[s4] = 1;
            s = (short)(s + 1);
            try {
                byte[] byArray2 = this.b[n].getBytes("UTF-8");
                for (s2 = 0; s2 < 20; s2 = (short)((byte)(s2 + 1))) {
                    short s5 = s;
                    s = (short)(s + 1);
                    short s6 = s2;
                    byArray[s5] = byArray2[s6];
                }
            }
            catch (Exception exception) {}
            byArray[s - s2 - 1] = s2;
            short s7 = s;
            s = (short)(s + 1);
            byArray[s7] = this.o[n];
            short s8 = s;
            s = (short)(s + 1);
            byArray[s8] = this.s[n];
            short s9 = s;
            s = (short)(s + 1);
            byArray[s9] = (byte)(this.k[n] & 0xFF);
            short s10 = s;
            s = (short)(s + 1);
            byArray[s10] = (byte)(this.k[n] >> 8);
            short s11 = s;
            s = (short)(s + 1);
            byArray[s11] = this.r[n];
            short s12 = s;
            s = (short)(s + 1);
            byArray[s12] = this.t[n];
            short s13 = s;
            s = (short)(s + 1);
            byArray[s13] = this.v[n];
            short s14 = s;
            s = (short)(s + 1);
            byArray[s14] = (byte)(this.l[n] & 0xFF);
            short s15 = s;
            s = (short)(s + 1);
            byArray[s15] = (byte)(this.l[n] >> 8);
            short s16 = s;
            s = (short)(s + 1);
            byArray[s16] = this.w[n];
            short s17 = s;
            s = (short)(s + 1);
            byArray[s17] = this.x[n];
            short s18 = s;
            s = (short)(s + 1);
            byArray[s18] = this.p[n];
            short s19 = s;
            s = (short)(s + 1);
            byArray[s19] = this.q[n];
            short s20 = s;
            s = (short)(s + 1);
            byArray[s20] = this.u[n];
            short s21 = s;
            s = (short)(s + 1);
            byArray[s21] = (byte)(this.z[n - 229] & 0xFF);
            short s22 = s;
            s = (short)(s + 1);
            byArray[s22] = (byte)(this.z[n - 229] >> 8);
        }
        return s;
    }

    public final synchronized void aO() {
        int n;
        block27: {
            String string = null;
            n = 0;
            RecordStore recordStore = null;
            Object var8_4 = null;
            short s = 0;
            try {
                short s2;
                string = "record" + this.a;
                recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)true);
                this.R[0] = (byte)(this.a[this.d] & 0xFF);
                this.R[1] = (byte)(this.a[this.d] >> 8);
                this.R[2] = this.k;
                this.R[3] = (byte)(this.c & 0xFF);
                this.R[4] = (byte)(this.c >> 8);
                s = 6;
                if (this.a[this.d] > 228) {
                    int n2;
                    byte[] byArray = this.b[this.a[this.d]].getBytes("UTF-8");
                    try {
                        for (n2 = 0; n2 < 20; n2 = (int)((byte)(n2 + 1))) {
                            short s3 = s;
                            s = (short)(s + 1);
                            int n3 = n2;
                            this.R[s3] = byArray[n3];
                        }
                    }
                    catch (Exception exception) {}
                    this.R[5] = n2;
                }
                if (recordStore.getNumRecords() <= 0) {
                    recordStore.addRecord(this.R, 0, (int)s);
                } else {
                    recordStore.setRecord(1, this.R, 0, (int)s);
                }
                for (s = 0; s < 8; s = (short)((short)(s + 1))) {
                    this.R[s] = this.D[s];
                }
                short s4 = s;
                s = (short)(s + 1);
                this.R[s4] = this.d;
                short s5 = s;
                s = (short)(s + 1);
                this.R[s5] = this.g;
                short s6 = s;
                s = (short)(s + 1);
                this.R[s6] = this.n;
                short s7 = s;
                s = (short)(s + 1);
                this.R[s7] = this.k;
                short s8 = s;
                s = (short)(s + 1);
                this.R[s8] = (byte)(this.c & 0xFF);
                short s9 = s;
                s = (short)(s + 1);
                this.R[s9] = (byte)(this.c >> 8);
                short s10 = s;
                s = (short)(s + 1);
                this.R[s10] = this.b;
                for (s2 = 1; s2 < 37; s2 = (short)(s2 + 1)) {
                    short s11;
                    s = this.a((byte)s2, this.R, s);
                    for (s11 = 0; s11 < this.g[s2]; s11 = (short)(s11 + 1)) {
                        s = this.a(this.b[s2][s11], this.R, s);
                    }
                    for (s11 = 0; s11 < this.i[s2]; s11 = (short)(s11 + 1)) {
                        s = this.a(this.d[s2][s11], this.R, s);
                    }
                    for (s11 = 0; s11 < this.h[s2]; s11 = (short)(s11 + 1)) {
                        s = this.a(this.c[s2][s11], this.R, s);
                    }
                }
                for (s2 = 1; s2 < 229; s2 = (short)(s2 + 1)) {
                    s = this.b(s2, this.R, s);
                }
                s = this.a(this.R, s);
                for (s2 = 1; s2 <= 8; s2 = (short)(s2 + 1)) {
                    s = this.b((byte)s2, this.R, s);
                }
                short s12 = s;
                s = (short)(s + 1);
                this.R[s12] = this.a;
                for (s2 = 0; s2 < this.a; s2 = (short)(s2 + 1)) {
                    short s13 = s;
                    s = (short)(s + 1);
                    this.R[s13] = this.z[s2];
                    short s14 = s;
                    s = (short)(s + 1);
                    this.R[s14] = this.A[s2];
                }
                if (recordStore.getNumRecords() <= 1) {
                    recordStore.addRecord(this.R, 0, (int)s);
                } else {
                    recordStore.setRecord(2, this.R, 0, (int)s);
                }
                try {
                    for (s2 = 0; s2 < 100; s2 = (short)(s2 + 1)) {
                        recordStore.closeRecordStore();
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
                recordStore = null;
                this.r[this.a] = this.a[this.d];
                this.d[this.a] = this.b[this.a[this.d]];
                this.C[this.a] = this.k;
                this.s[this.a] = this.c;
                n = 1;
                System.gc();
            }
            catch (Exception exception) {
                if (recordStore == null) break block27;
                try {
                    for (int n4 = 0; n4 < 100; n4 = (int)((short)(n4 + 1))) {
                        recordStore.closeRecordStore();
                    }
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                }
                catch (Exception exception2) {}
            }
        }
        if (n > 0) {
            this.a(this.c[this.g], "\u5b58\u50a8\u6210\u529f");
        } else {
            this.a(this.c[this.g], "\u5b58\u50a8\u5931\u8d25, \u8bf7\u7a0d\u540e\u518d\u8bd5");
        }
        this.Q = (byte)(this.Q + 1);
    }

    public final short a(byte by, byte[] byArray, int n) {
        this.b[by] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
        this.c[by] = (short)(byArray[n + 3] << 8 | byArray[n + 2] & 0xFF);
        this.e[by] = byArray[n + 4];
        this.d[by] = (short)(byArray[n + 6] << 8 | byArray[n + 5] & 0xFF);
        this.e[by] = (short)(byArray[n + 8] << 8 | byArray[n + 7] & 0xFF);
        this.f[by] = (short)(byArray[n + 10] << 8 | byArray[n + 9] & 0xFF);
        this.g[by] = (short)(byArray[n + 12] << 8 | byArray[n + 11] & 0xFF);
        this.a[by] = byArray[n + 16] << 24 | byArray[n + 15] << 16 & 0xFF0000 | byArray[n + 14] << 8 & 0xFF00 | byArray[n + 13] & 0xFF;
        this.f[by] = byArray[n + 17];
        this.g[by] = byArray[n + 18];
        this.h[by] = byArray[n + 19];
        this.i[by] = byArray[n + 20];
        this.h[by] = (short)(byArray[n + 22] << 8 | byArray[n + 21] & 0xFF);
        this.i[by] = (short)(byArray[n + 24] << 8 | byArray[n + 23] & 0xFF);
        this.j[by] = byArray[n + 25];
        this.k[by] = byArray[n + 26];
        this.l[by] = byArray[n + 27];
        return 28;
    }

    public final int a(byte[] byArray, int n, short s) {
        this.o[s] = byArray[n];
        this.s[s] = byArray[n + 1];
        this.k[s] = (short)(byArray[n + 3] << 8 | byArray[n + 2] & 0xFF);
        this.r[s] = byArray[n + 4];
        this.t[s] = byArray[n + 5];
        this.v[s] = byArray[n + 6];
        this.l[s] = (short)(byArray[n + 8] << 8 | byArray[n + 7] & 0xFF);
        this.w[s] = byArray[n + 9];
        this.x[s] = byArray[n + 10];
        return n + 11;
    }

    public final int a(byte[] byArray, int n) {
        for (int n2 = 229; n2 < 232; n2 = (int)((short)(n2 + 1))) {
            byte by;
            if ((by = byArray[n++]) <= 0) {
                this.j[n2] = 0;
                continue;
            }
            this.j[n2] = n2;
            by = byArray[n++];
            byte[] byArray2 = new byte[by - 1];
            for (int n3 = 0; n3 < by - 1; n3 = (int)((byte)(n3 + 1))) {
                byArray2[n3] = byArray[n++];
            }
            ++n;
            try {
                this.b[n2] = new String(byArray2, "UTF-8");
            }
            catch (Exception exception) {}
            this.o[n2] = byArray[n++];
            this.s[n2] = byArray[n++];
            this.k[n2] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
            n += 2;
            this.r[n2] = byArray[n++];
            this.t[n2] = byArray[n++];
            this.v[n2] = byArray[n++];
            this.l[n2] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
            n += 2;
            this.w[n2] = byArray[n++];
            this.x[n2] = byArray[n++];
            this.p[n2] = byArray[n++];
            this.q[n2] = byArray[n++];
            this.u[n2] = byArray[n++];
            this.z[n2 - 229] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
            n += 2;
        }
        return n;
    }

    public final synchronized void aP() {
        RecordStore recordStore = null;
        try {
            short s;
            byte by;
            int n;
            String string = "record" + this.a;
            System.gc();
            recordStore = RecordStore.openRecordStore((String)string, (boolean)true, (int)1, (boolean)false);
            byte[] byArray = recordStore.getRecord(2);
            this.l = (byte)-1;
            for (n = 0; n < 8; ++n) {
                this.D[n] = byArray[n];
                if (byArray[n] != 0) continue;
                this.l = (byte)(this.l + 1);
            }
            this.e = this.d = byArray[n++];
            this.am = this.d;
            this.g = byArray[n++];
            this.n = byArray[n++];
            this.k = byArray[n++];
            this.c = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
            n += 2;
            this.b = byArray[n++];
            for (by = 1; by < 37; by = (byte)(by + 1)) {
                n += this.a(by, byArray, n);
                for (s = 0; s < this.g[by]; s = (short)(s + 1)) {
                    this.b[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                    n += 2;
                }
                if (this.i[by] > 0) {
                    this.d[by] = new short[this.i[by]];
                }
                for (s = 0; s < this.i[by]; s = (short)(s + 1)) {
                    this.d[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                    n += 2;
                }
                if (this.h[by] > 0) {
                    this.c[by] = new short[this.h[by]];
                }
                for (s = 0; s < this.h[by]; s = (short)(s + 1)) {
                    this.c[by][s] = (short)(byArray[n + 1] << 8 | byArray[n + 0] & 0xFF);
                    n += 2;
                }
            }
            for (s = 1; s < 229; s = (short)(s + 1)) {
                n = this.a(byArray, n, s);
            }
            n = this.a(byArray, n);
            for (by = 1; by <= 8; by = (byte)(by + 1)) {
                this.a[by] = (short)(byArray[n + 1] << 8 | byArray[n] & 0xFF);
                this.b[by] = 0;
                n += 2;
                if (this.a[by] <= 0) continue;
                for (s = 1; s < 37; s = (short)(s + 1)) {
                    if (this.b[s] != this.a[by]) continue;
                    byte by2 = by;
                    byte by3 = this.b[by2];
                    this.b[by2] = (byte)(by3 + 1);
                    this.a[by][by3] = (byte)s;
                }
                this.a[by] = this.b[by] == 0 || this.a[by] <= 0 ? (byte)0 : by;
            }
            this.a = byArray[n++];
            for (by = 0; by < this.a; by = (byte)(by + 1)) {
                this.z[by] = byArray[n++];
                this.A[by] = byArray[n++];
            }
            try {
                for (by = 0; by < 100; by = (byte)(by + 1)) {
                    recordStore.closeRecordStore();
                }
            }
            catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
            recordStore = null;
            return;
        }
        catch (Exception exception) {
            if (recordStore != null) {
                try {
                    for (int n = 0; n < 100; n = (int)((byte)(n + 1))) {
                        recordStore.closeRecordStore();
                    }
                    return;
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    return;
                }
                catch (Exception exception2) {}
            }
            return;
        }
    }

    public final void aQ() {
        this.aP();
        this.n = true;
        this.ar();
    }

    public final void aR() {
        byte by = this.g;
        this.d[by] = (short)(this.d[by] - this.g);
        byte by2 = this.g;
        this.e[by2] = (short)(this.e[by2] - this.h);
        if (this.b[this.h] == 0) {
            byte by3 = this.d;
            byte by4 = this.b[by3];
            this.b[by3] = (byte)(by4 + 1);
            this.a[this.d][by4] = this.h;
            this.b[this.h] = this.a[this.d];
            this.c[this.h] = this.t[0];
            this.g[this.h] = this.j;
            for (byte by5 = 0; by5 < this.j; by5 = (byte)(by5 + 1)) {
                this.b[this.h][by5] = this.t[by5];
            }
            this.h[this.h] = this.a(this.t, this.j);
            byte by6 = this.h;
            this.d[by6] = (short)(this.d[by6] + this.g);
            byte by7 = this.h;
            this.e[by7] = (short)(this.e[by7] + this.h);
            this.g = this.h;
            this.Q = (byte)(this.Q + 2);
            this.aS();
            return;
        }
        this.c = (byte)4;
    }

    public final void aS() {
        byte by = this.i[this.P][this.Q];
        System.gc();
        switch (by) {
            case 0: {
                this.ar();
                return;
            }
            case 1: {
                this.as();
                return;
            }
            case 2: {
                this.at();
                return;
            }
            case 3: {
                this.av();
                return;
            }
            case 4: {
                this.Q = (byte)(this.Q + 1);
                this.q((byte)10);
                return;
            }
            case 5: {
                this.q((byte)8);
                return;
            }
            case 6: {
                this.q((byte)14);
                return;
            }
            case 8: {
                this.q((byte)5);
                return;
            }
            case 9: {
                this.q((byte)15);
                return;
            }
            case 10: {
                this.ay();
                return;
            }
            case 11: {
                this.q((byte)12);
                return;
            }
            case 12: {
                this.aD();
                return;
            }
            case 13: {
                this.aE();
                return;
            }
            case 14: {
                this.q((byte)16);
                return;
            }
            case 15: {
                this.q((byte)17);
                return;
            }
            case 16: {
                this.q((byte)9);
                return;
            }
            case 17: {
                this.q((byte)18);
                return;
            }
            case 83: {
                this.ax();
                return;
            }
            case 84: {
                this.aB();
                return;
            }
            case 85: {
                this.aR();
                return;
            }
            case 86: {
                this.aQ();
                return;
            }
            case 87: {
                this.aO();
                return;
            }
            case 88: {
                this.aN();
                return;
            }
            case 89: {
                this.aM();
                return;
            }
            case 90: {
                this.aL();
                return;
            }
            case 91: {
                this.aK();
                return;
            }
            case 92: {
                this.aJ();
                return;
            }
            case 93: {
                this.aI();
                return;
            }
            case 94: {
                this.aH();
                return;
            }
            case 95: {
                this.aG();
                return;
            }
            case 96: {
                this.aF();
                return;
            }
            case 97: {
                this.aA();
                return;
            }
            case 98: {
                this.az();
                return;
            }
            case 99: {
                this.aw();
                return;
            }
            case 100: {
                this.au();
            }
        }
    }

    public final boolean m() {
        if (this.d) {
            this.d = false;
            return true;
        }
        return false;
    }

    public final void a(byte by, int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.i = this.P == 1 || this.P == 3 ? this.g : by;
        this.h = by;
        this.Q = (byte)(this.Q + 1);
        this.aS();
    }

    public final void g(int n) {
        byte by;
        short[] sArray;
        if (this.P != 22 && !this.m()) {
            return;
        }
        if (this.c) {
            sArray = this.b[this.i];
            by = this.g[this.i];
        } else {
            sArray = this.u;
            by = this.o;
            this.c = true;
        }
        if (n == 1) {
            this.b(sArray, by);
            if (this.P == 22) {
                this.c = 1;
                return;
            }
            this.ar();
            return;
        }
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            if (!this.a[sArray[by2]]) continue;
            this.a = sArray[by2];
            this.a[this.a] = false;
            break;
        }
        if (this.P == 22) {
            this.c = (byte)2;
            return;
        }
        if (this.P == 9) {
            this.d = true;
        }
        this.Q = (byte)(this.Q + 1);
        this.i = this.g;
        this.aS();
    }

    public final void c(short s, byte by) {
        for (byte by2 = 0; by2 < this.g[by] && this.b[by][by2] != s; by2 = (byte)(by2 + 1)) {
        }
        for (byte by3 = by2; by3 > 0; by3 = (byte)(by3 - 1)) {
            this.b[by][by3] = this.b[by][by3 - 1];
        }
        this.b[by][0] = s;
        this.c[by] = s;
    }

    public final void d(short s, byte by) {
        if (!this.m()) {
            return;
        }
        this.c(s, by);
    }

    public final void r(byte by) {
        this.h[by] = 0;
        for (byte by2 = 0; by2 < this.g[by]; by2 = (byte)(by2 + 1)) {
            byte by3 = by;
            this.h[by3] = (short)(this.h[by3] + this.k[this.b[by][by2]]);
        }
    }

    public final void aT() {
        for (byte by = 0; by < this.j; by = (byte)(by + 1)) {
            byte by2 = this.h;
            byte by3 = this.g[by2];
            this.g[by2] = (byte)(by3 + 1);
            this.b[this.h][by3] = this.t[by];
        }
        if (this.b[this.h] == this.t[0]) {
            this.d = true;
            this.d(this.t[0], this.h);
        }
        this.r(this.h);
    }

    public final void aU() {
        byte by = 0;
        byte by2 = 0;
        for (byte by3 = 0; by3 < this.g[this.g]; by3 = (byte)(by3 + 1)) {
            if (by < this.j && this.b[this.g][by3] == this.t[by]) {
                by = (byte)(by + 1);
                continue;
            }
            this.b[this.g][by2] = this.b[this.g][by3];
            by2 = (byte)(by2 + 1);
        }
        this.g[this.g] = by2;
        this.r(this.g);
    }

    public final void aV() {
        this.aT();
        this.aU();
    }

    public final void e(short s, byte by) {
        short s2 = s;
        this.b[s2] = (byte)(this.b[s2] - 1);
        for (byte by2 = 0; by2 < this.b[s] && this.a[s][by2] != by; by2 = (byte)(by2 + 1)) {
        }
        for (byte by3 = by2; by3 < this.b[s]; by3 = (byte)(by3 + 1)) {
            this.a[s][by3] = this.a[s][by3 + 1];
        }
    }

    public final void aW() {
        this.g[this.g] = 0;
        this.c[this.g] = 0;
        this.b[this.g] = 0;
        this.h[this.g] = 0;
        this.e((short)this.d, this.g);
    }

    public final void h(int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        if (this.P == 1) {
            this.aT();
            this.aW();
            this.g = this.h;
            this.Q = (byte)4;
        } else {
            this.a = this.t[0];
            this.Q = (byte)2;
        }
        this.aS();
    }

    public final void b(short[] sArray, byte by) {
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            this.a[sArray[by2]] = false;
        }
    }

    public final void i(int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.b(this.b[this.g], this.g[this.g]);
            this.ar();
            return;
        }
        boolean bl = false;
        this.a = true;
        this.j = 0;
        for (byte by = 0; by < this.g[this.g]; by = (byte)(by + 1)) {
            if (!this.a[this.b[this.g][by]]) continue;
            if (by == 0) {
                bl = true;
            }
            byte by2 = this.j;
            this.j = (byte)(by2 + 1);
            this.t[by2] = this.b[this.g][by];
            this.a[this.b[this.g][by]] = false;
        }
        this.d = this.t[0];
        if (this.P == 1) {
            if (this.j + this.g[this.h] > 10) {
                this.a(this.t[0], "\u79fb\u52a8\u6b66\u5c06\u6570\u8fc7\u591a!");
                this.Q = (byte)6;
                return;
            }
            this.a = c.b[0][0];
        }
        if (this.j == this.g[this.g]) {
            this.a.m();
            this.d = true;
            this.a.repaint();
            return;
        }
        if (this.P == 1) {
            if (bl) {
                this.i = this.g;
                this.b = false;
                this.Q = (byte)2;
            } else {
                this.Q = (byte)4;
            }
            this.aV();
        } else {
            this.Q = (byte)(this.Q + 1);
        }
        this.aS();
    }

    public final void j(int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.Q = (byte)(this.Q + 1);
        this.aS();
    }

    public final void k(int n) {
        this.j(n);
    }

    public final void aX() {
        if (!this.m()) {
            return;
        }
        this.aS();
    }

    public final void a(short[] sArray) {
        byte by = this.g;
        this.d[by] = (short)(this.d[by] - sArray[0]);
        byte by2 = this.g;
        this.e[by2] = (short)(this.e[by2] - sArray[1]);
        byte by3 = this.g;
        this.j[by3] = (byte)(this.j[by3] - sArray[2]);
        byte by4 = this.h;
        this.d[by4] = (short)(this.d[by4] + sArray[0]);
        byte by5 = this.h;
        this.e[by5] = (short)(this.e[by5] + sArray[1]);
        byte by6 = this.h;
        this.j[by6] = (byte)(this.j[by6] + sArray[2]);
    }

    public final void a(int n, short[] sArray) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.a(sArray);
        this.Q = (byte)(this.Q + 1);
        this.a(this.c[this.g], c.b[0][0]);
    }

    public final void a(short s, short s2, int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.g = s;
        this.h = s2;
        this.Q = (byte)6;
        this.a = c.b[0][2];
        if (this.j == this.g[this.g]) {
            this.aW();
        } else {
            if (this.t[0] == this.c[this.g]) {
                this.b = false;
                this.i = this.g;
                this.Q = (byte)3;
            } else {
                this.b = false;
                this.c = false;
                for (int i = 0; i < this.j; ++i) {
                    this.u[i] = this.t[i];
                }
                this.o = this.j;
                this.Q = (byte)5;
            }
            this.aU();
        }
        this.aS();
    }

    public final void i(short s) {
        short s2 = this.a;
        this.v[s2] = (byte)(this.v[s2] + this.c((int)s));
        if (this.v[this.a] > 99 || this.v[this.a] <= 0) {
            this.v[this.a] = 99;
        }
        if (s == 200) {
            byte by = this.g;
            this.j[by] = (byte)(this.j[by] - 1);
            return;
        }
        byte by = this.g;
        this.d[by] = (short)(this.d[by] - s);
    }

    public final void a(short s, int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.i(s);
        this.Q = (byte)(this.Q + 1);
        this.a(this.a, c.b[2][2]);
    }

    public final void l(int n) {
        this.j(n);
    }

    public final void b(byte by, int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.f = by;
        this.Q = (byte)(this.Q + 1);
        this.aS();
    }

    public final void aY() {
        if (!this.m()) {
            return;
        }
        this.Q = 0;
        this.aS();
    }

    public final void m(int n) {
        if (!this.m()) {
            return;
        }
        if (n == 1) {
            this.ar();
            return;
        }
        this.a(this.c[this.g], c.b[4][0]);
        this.Q = (byte)(this.Q + 1);
    }

    public final void n(int n) {
        if (!this.m()) {
            return;
        }
        switch (n) {
            case 0: {
                this.a(this.a, c.b[4][1]);
                this.Q = (byte)(this.Q + 1);
                return;
            }
            case 1: {
                this.ar();
                return;
            }
            case 2: {
                this.a(this.c[this.g], "\u8d44\u8d22\u4e0d\u8db3");
                this.Q = (byte)3;
                return;
            }
            case 3: {
                this.a(this.a, "\u542f\u7980\u4e3b\u516c,\u5c5e\u4e0b\u65e0\u6cd5\u4f7f\u7528\u6b64\u6b66\u5668");
                this.Q = (byte)3;
            }
        }
    }

    public final void o(int n) {
        if (!this.m()) {
            return;
        }
        if (n == 5) {
            this.ar();
            return;
        }
        this.a = n;
        this.Q = (byte)(this.Q + 1);
        this.aS();
    }

    public final void s(byte by) {
        this.P = by;
        this.aS();
    }

    public final void t(byte by) {
        this.P = by;
        for (byte by2 = 0; by2 < this.n[this.g]; by2 = (byte)(by2 + 1)) {
            if (this.b[this.b[this.g][by2]] != this.b[this.g]) continue;
            this.a = false;
            this.aS();
            return;
        }
        this.Q = (byte)6;
        this.a(this.c[this.g], "\u5f53\u524d\u65e0\u53ef\u79fb\u52a8\u7684\u57ce!");
    }

    public final void u(byte by) {
        this.P = by;
        for (byte by2 = 0; by2 < this.n[this.g]; by2 = (byte)(by2 + 1)) {
            if (this.b[this.b[this.g][by2]] != this.b[this.g]) continue;
            this.aS();
            return;
        }
        this.Q = (byte)3;
        this.a(this.c[this.g], "\u5f53\u524d\u65e0\u53ef\u8f93\u9001\u7684\u57ce!");
    }

    public final void v(byte by) {
        this.P = by;
        this.o = 0;
        for (byte by2 = 0; by2 < this.g[this.g]; by2 = (byte)(by2 + 1)) {
            if (this.v[this.b[this.g][by2]] >= 90) continue;
            byte by3 = this.o;
            this.o = (byte)(by3 + 1);
            this.u[by3] = this.b[this.g][by2];
        }
        if (this.o > 0) {
            this.c = false;
            this.aS();
            return;
        }
        this.Q = (byte)3;
        this.a(this.c[this.g], "\u540f\u58eb\u7686\u5fe0\u5fc3\u4e0d\u8d30!");
    }

    public final void w(byte by) {
        this.P = by;
        if (this.g[this.g] == 10) {
            this.a(this.c[this.g], "\u672c\u57ce\u7f6e\u540f\u5df2\u8db3!");
            this.Q = (byte)4;
            return;
        }
        if (this.i[this.g] == 0) {
            this.a(this.c[this.g], "\u91ce\u65e0\u9057\u8d24!");
            this.Q = (byte)4;
            return;
        }
        this.c = false;
        this.o = this.i[this.g];
        for (byte by2 = 0; by2 < this.o; by2 = (byte)(by2 + 1)) {
            this.u[by2] = this.d[this.g][by2];
        }
        this.aS();
    }

    public final void x(byte by) {
        this.P = by;
        if (this.b[this.g] == this.c[this.g]) {
            this.a(this.b[this.g], c.b[2][4]);
            this.Q = (byte)2;
            return;
        }
        this.aS();
    }

    public final boolean n() {
        if (this.d[this.g] == 0) {
            this.a(this.c[this.g], "\u8d44\u8d22\u4e0d\u8db3");
            this.Q = (byte)4;
            return true;
        }
        return false;
    }

    public final void y(byte by) {
        this.P = by;
        if (this.n()) {
            return;
        }
        if (this.f[this.g] == 999) {
            this.a(this.c[this.g], c.b[3][2]);
            this.Q = (byte)4;
            return;
        }
        this.b = 0;
        this.Q = 0;
        this.aS();
    }

    public final void z(byte by) {
        this.P = by;
        if (this.n()) {
            return;
        }
        if (this.g[this.g] == 999) {
            this.a(this.c[this.g], c.b[3][5]);
            this.Q = (byte)4;
            return;
        }
        this.b = 1;
        this.Q = 0;
        this.aS();
    }

    public final void A(byte by) {
        this.P = by;
        if (this.n()) {
            return;
        }
        if (this.f[this.g] == 99) {
            this.a(this.c[this.g], c.b[3][6]);
            this.Q = (byte)4;
            return;
        }
        this.b = 2;
        this.Q = 0;
        this.aS();
    }

    public final void B(byte by) {
        this.P = by;
        if (this.n()) {
            return;
        }
        if (this.a[this.g] == 990000) {
            this.a(this.c[this.g], c.b[3][7]);
            this.Q = (byte)4;
            return;
        }
        this.b = 3;
        this.Q = 0;
        this.aS();
    }

    public final void C(byte by) {
        this.P = by;
        boolean bl = false;
        this.q = 0;
        for (int n = 1; n <= 7; n = (int)((byte)(n + 1))) {
            byte by2;
            if (this.a[n] <= 0 || this.a[n] == this.d) continue;
            for (by2 = 0; by2 < this.a && this.z[by2] != this.a[n]; by2 = (byte)(by2 + 1)) {
            }
            if (by2 < this.a) continue;
            byte by3 = this.q;
            this.q = (byte)(by3 + 1);
            this.E[by3] = this.a[n];
        }
        if (this.q == 0) {
            this.a(this.c[this.g], "\u5df2\u4e0e\u6240\u6709\u52bf\u529b\u540c\u76df");
            this.Q = (byte)4;
        }
        this.aS();
    }

    public final void D(byte by) {
        this.P = by;
        if (this.g[this.g] == 10) {
            this.a(this.c[this.g], "\u672c\u57ce\u7f6e\u540f\u5df2\u8db3!");
            this.Q = (byte)5;
            return;
        }
        this.aS();
    }

    public final void E(byte by) {
        this.P = by;
        if ((this.k[this.g] & 8) != 8) {
            this.Q = (byte)2;
            this.a(this.c[this.g], "\u672c\u57ce\u65e0\u7cae\u5e97,\u65e0\u6cd5\u4e70\u5356\u7cae\u8349");
            return;
        }
        this.aS();
    }

    public final void F(byte by) {
        this.P = by;
        if ((this.k[this.g] & 1) != 1) {
            this.Q = (byte)3;
            this.a(this.c[this.g], "\u672c\u57ce\u65e0\u6b66\u5668\u5e97,\u65e0\u6cd5\u8d2d\u5165\u6b66\u5668");
            return;
        }
        this.aS();
    }

    public final void G(byte by) {
        this.P = by;
        if ((this.k[this.g] & 2) == 2) {
            this.o = 0;
            for (byte by2 = 0; by2 < this.g[this.g]; by2 = (byte)(by2 + 1)) {
                if (this.t[this.b[this.g][by2]] >= 80) continue;
                byte by3 = this.o;
                this.o = (byte)(by3 + 1);
                this.u[by3] = this.b[this.g][by2];
            }
            if (this.o > 0) {
                this.c = false;
                this.aS();
                return;
            }
            this.a(this.c[this.g], "\u672c\u57ce\u6b66\u5c06\u7686\u624d\u534e\u6a2a\u6ea2, \u65e0\u9700\u5b66\u4e60");
            this.Q = (byte)3;
            return;
        }
        this.a(this.c[this.g], "\u672c\u57ce\u65e0\u5b66\u5802,\u65e0\u6cd5\u4fee\u4e60\u667a\u529b");
        this.Q = (byte)3;
    }

    public final void H(byte by) {
        boolean bl = false;
        this.P = by;
        if ((this.k[this.g] & 4) != 4) {
            this.a(this.c[this.g], "\u672c\u57ce\u65e0\u533b\u9662,\u65e0\u6cd5\u6cbb\u7597\u6b66\u5c06");
            this.Q = (byte)3;
            return;
        }
        this.o = 0;
        for (byte by2 = 0; by2 < this.g[this.g]; by2 = (byte)(by2 + 1)) {
            if (this.r[this.b[this.g][by2]] >= this.q[this.b[this.g][by2]]) continue;
            byte by3 = this.o;
            this.o = (byte)(by3 + 1);
            this.u[by3] = this.b[this.g][by2];
        }
        if (this.o > 0) {
            if (this.d[this.g] >= 50) {
                this.c = false;
                this.aS();
                return;
            }
            this.a(this.c[this.g], "\u8d44\u8d22\u4e0d\u8db3");
            this.Q = (byte)3;
            return;
        }
        this.a(this.c[this.g], "\u5c06\u58eb\u5747\u9f99\u7cbe\u864e\u731b!");
        this.Q = (byte)3;
    }

    public final void I(byte by) {
        byte by2;
        this.P = by;
        for (by2 = 0; by2 < this.n[this.g]; by2 = (byte)(by2 + 1)) {
            byte by3;
            byte by4 = this.b[this.g][by2];
            if (this.b[by4] == this.a[this.d]) continue;
            for (by3 = 0; by3 < this.a && this.a[this.z[by3]] != this.b[by4]; by3 = (byte)(by3 + 1)) {
            }
            if (by3 >= this.a) break;
        }
        if (by2 < this.n[this.g]) {
            this.a = false;
            this.aS();
            return;
        }
        this.a(this.c[this.g], "\u5f53\u524d\u65e0\u53ef\u6218\u4e89\u57ce\u5e02!");
        this.Q = (byte)8;
    }

    public final int a(int n) {
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
        if (this.a() % 250 > n2) {
            this.P = (byte)-1;
        }
        return n;
    }

    public final int b(int n) {
        if (this.b == 1) {
            return this.a(n);
        }
        if (this.b == 2) {
            return this.d(n);
        }
        return 0;
    }

    public final void a(byte by, short[] sArray, int n) {
        int n2;
        for (n2 = 0; n2 < 10 && sArray[n2] != 0; ++n2) {
        }
        int n3 = n2;
        for (n2 = n3 + 1; n2 < 10 && n3 < n; ++n2) {
            if (sArray[n2] == 0) continue;
            sArray[n3] = sArray[n2];
            sArray[n2] = 0;
            ++n3;
        }
        this.h[by] = this.a(sArray, (byte)n);
    }

    public final void a(byte by, short[] sArray, byte by2) {
        this.c[by] = sArray[0];
    }

    public final void b(byte by, short[] sArray, byte by2) {
        if (this.b == 1) {
            this.a(by, sArray, by2);
            return;
        }
        if (this.b == 2) {
            this.c(by, sArray, by2);
        }
    }

    public final void c(short[] sArray, byte n) {
        for (int i = 0; i < n; ++i) {
            if (sArray[i] != this.b[this.g]) continue;
            short s = sArray[0];
            sArray[0] = sArray[i];
            sArray[i] = s;
            return;
        }
    }

    public final byte a(short[] sArray, int n) {
        byte by;
        byte[] byArray = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int n2 = -1 * this.h[this.h];
        byte by2 = 0;
        byte by3 = 0;
        n += 3;
        n = this.a() % n;
        n -= 2;
        n *= 1000;
        for (by = this.g[this.g]; by > 1; by = (byte)(by - 1)) {
            int n3 = 0;
            int n4 = this.a() % by + 1;
            while ((n3 += byArray[by3]) != n4) {
                if (++by3 != this.g[this.g]) continue;
                by3 = 0;
            }
            sArray[by2] = this.b[this.g][by3];
            this.b[this.g][by3] = 0;
            by2 = (byte)(by2 + 1);
            byArray[by3] = 0;
            if ((n2 += this.k[sArray[by2]]) < n) continue;
        }
        this.g[this.g] = by;
        this.a(this.g, this.b[this.g], (int)by);
        this.b(this.g, this.b[this.g], by);
        this.c(sArray, by2);
        return by2;
    }

    public final byte b(short[] sArray, int n) {
        if (this.b == 1) {
            return this.a(sArray, n);
        }
        if (this.b == 2) {
            return this.c(sArray, n);
        }
        return 0;
    }

    public final int a(short[] sArray, int n) {
        int n2 = 0;
        n2 = this.a(sArray, (byte)n);
        n2 = n2 * 4 / 1000 + 1;
        int n3 = this.a() % (n2 * 30) + n2 * 30;
        if (n3 < this.e[this.g] / 2) {
            n3 = this.e[this.g] / 2;
        } else if (n3 >= this.e[this.g]) {
            n3 = this.e[this.g] * 2 / 3;
        }
        return n3;
    }

    public final void a(short[] sArray, byte n, int n2, int n3) {
        this.a[this.e][this.b[this.e]] = this.h;
        byte by = this.e;
        this.b[by] = (byte)(this.b[by] + 1);
        for (int i = 0; i < n; ++i) {
            this.b[this.h][i] = sArray[i];
        }
        this.g[this.h] = n;
        this.b[this.h] = this.b[this.g];
        this.c[this.h] = sArray[0];
    }

    public final int a(byte by, int n) {
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
        int n2 = n > 600 ? this.a() % 5 + 6 : (n > 450 ? this.a() % 10 + 11 : this.a() % 10 + 21);
        byte by3 = by2;
        this.e[by3] = (short)(this.e[by3] - this.h[by2] * 4 * n2 / 1000);
        s = this.a(sArray, by);
        return s * 4 * n2 / 1000;
    }

    public final void b(short s, int n) {
        if (this.k[s] > 0) {
            if (this.k[s] < n) {
                n = this.k[s];
            }
            this.k[s] = (short)(this.a() % (n + 1));
        }
        if (this.r[s] > 0) {
            this.r[s] = (byte)(this.a() % this.r[s] + 1);
        }
    }

    public final short a(short s, short s2) {
        if (s2 <= 0) {
            return 0;
        }
        short s3 = (short)(this.a() % s2);
        s2 = (short)(s2 - s3);
        short s4 = s;
        this.l[s4] = (short)(this.l[s4] + s3);
        this.b(s);
        return s2;
    }

    public final void a(short[] sArray, byte by, short s) {
        int n;
        for (n = 0; n < by - 1; n = (int)((byte)(n + 1))) {
            s = this.a(sArray[n], s);
        }
        short s2 = sArray[n];
        this.l[s2] = (short)(this.l[s2] + s);
        this.b(sArray[n]);
    }

    public final void aZ() {
        byte by = this.d(this.b[this.g]);
        byte by2 = this.d(this.b[this.h]);
        this.a[by][this.b[by]] = this.h;
        byte by3 = by;
        this.b[by3] = (byte)(this.b[by3] + 1);
        byte by4 = by2;
        this.b[by4] = (byte)(this.b[by4] - 1);
        if (this.b[by2] > 0) {
            for (int i = 0; i <= this.b[by2] && this.a[by2][i] != this.h; ++i) {
            }
            this.a[by2][i] = this.a[by2][this.b[by2]];
        }
    }

    public final void j(short s) {
        this.c[this.h] = this.a(this.c[this.h], this.h[this.h], s);
        byte by = this.h;
        this.h[by] = (byte)(this.h[by] + 1);
    }

    public final void k(short s) {
        this.h(s);
        this.b[this.h][this.g[this.h]] = s;
        byte by = this.h;
        this.g[by] = (byte)(this.g[by] + 1);
    }

    public final byte a(short s, byte[] byArray, byte by, byte by2, short s2) {
        int n;
        byte by3 = by2;
        if (this.b[by3] == 0) {
            byte by4 = this.d(s2);
            this.a[by4][this.b[by4]] = by3;
            byte by5 = by4;
            this.b[by5] = (byte)(this.b[by5] + 1);
            this.b[by3] = this.a[by4];
            this.c[by3] = s;
            this.g[by3] = 1;
            this.b[by3][0] = s;
            this.h[by3] = this.k[s];
            return by;
        }
        if (s == s2) {
            for (n = this.g[by3]; n > 0; --n) {
                this.b[by3][n] = this.b[by3][n - 1];
            }
            this.b[by3][0] = s;
            this.c[by3] = s;
        } else {
            this.b[by3][this.g[by3]] = s;
        }
        byte by6 = by3;
        this.g[by6] = (byte)(this.g[by6] + 1);
        byte by7 = by3;
        this.h[by7] = (short)(this.h[by7] + this.k[s]);
        if (this.g[by3] == 10 && (by = (byte)(by - 1)) != 0) {
            for (n = 0; n < by; n = (int)((byte)(n + 1))) {
                if (byArray[n] != by2) continue;
                byArray[n] = byArray[by];
                break;
            }
        }
        return by;
    }

    public final void a(byte by, int n, int n2) {
        byte by2 = by;
        this.d[by2] = (short)(this.d[by2] + n2);
        byte by3 = by;
        this.e[by3] = (short)(this.e[by3] + n);
        if (this.d[by] > 9999) {
            this.d[by] = 9999;
        }
        if (this.e[by] > 9999) {
            this.e[by] = 9999;
        }
    }

    public final boolean a(short[] sArray, byte by, short s, int n, int n2) {
        int n3;
        byte by2 = 0;
        byte[] byArray = new byte[this.n[this.h]];
        int n4 = 0;
        int n5 = 0;
        boolean bl = false;
        for (n3 = 0; n3 < this.n[this.h]; ++n3) {
            byte by3 = this.b[this.h][n3];
            if (this.b[by3] != 0 && (this.b[by3] != s || this.g[by3] >= 10)) continue;
            byArray[by2] = by3;
            by2 = (byte)(by2 + 1);
        }
        for (n3 = 0; n3 < by; ++n3) {
            int n6;
            if (by2 == 0) {
                if (n3 == 0) {
                    n4 = 1;
                    if (sArray[n3] == s) {
                        bl = true;
                        continue;
                    }
                }
                if (this.g[this.h] == 10) {
                    this.j(sArray[n3]);
                    continue;
                }
                this.k(sArray[n3]);
                continue;
            }
            if (this.g[this.h] == 10) {
                n6 = this.b(s, byArray, by2, this.h);
                if (n3 == 0) {
                    n5 = n6;
                }
                by2 = this.a(sArray[n3], byArray, by2, (byte)n6, s);
                continue;
            }
            if (this.r[sArray[n3]] <= 35 && this.k[sArray[n3]] <= 150) {
                n6 = this.t[sArray[n3]] >= 95 || this.t[sArray[n3]] >= 95 ? 10 : (this.t[sArray[n3]] >= 90 || this.t[sArray[n3]] >= 90 ? 20 : (this.t[sArray[n3]] >= 80 || this.t[sArray[n3]] >= 80 ? 40 : 80));
                if (this.a() % 100 <= n6) {
                    if (n3 == 0) {
                        if (sArray[n3] == s) {
                            byte by4 = this.b(s, byArray, by2, this.h);
                            n6 = by4;
                            n5 = by4;
                            by2 = this.a(sArray[n3], byArray, by2, (byte)n6, s);
                            continue;
                        }
                        n4 = 1;
                    }
                    this.k(sArray[n3]);
                    continue;
                }
            }
            n6 = this.b(s, byArray, by2, this.h);
            if (n3 == 0) {
                n5 = n6;
            }
            by2 = this.a(sArray[n3], byArray, by2, (byte)n6, s);
        }
        if (n4 > 0) {
            this.a(this.h, n, n2);
        } else {
            this.a((byte)n5, n, n2);
        }
        return bl;
    }

    public final void b(short[] sArray, byte by, int n, int n2) {
        int n3;
        int n4 = this.g[this.h];
        short s = this.b[this.h];
        byte by2 = this.d(s);
        short[] sArray2 = new short[10];
        for (n3 = 0; n3 < n4; ++n3) {
            sArray2[n3] = this.b[this.h][n3];
        }
        this.aZ();
        for (n3 = 0; n3 < by; ++n3) {
            this.b[this.h][n3] = sArray[n3];
        }
        this.g[this.h] = by;
        this.b[this.h] = this.b[this.g];
        this.c[this.h] = sArray[0];
        n3 = this.d[this.h];
        this.d[this.h] = (short)n2;
        n2 = n3;
        n3 = this.e[this.h];
        this.e[this.h] = (short)n;
        n = n3;
        boolean bl = this.a(sArray2, (byte)n4, s, n, n2);
        if (bl) {
            if (this.b[by2] > 0) {
                this.h(by2);
            } else {
                this.a[by2] = 0;
            }
        }
        if (this.a[by2] > 0) {
            this.J(this.a[by2]);
        }
    }

    public final void c(short[] sArray, byte by, int n, int n2) {
        int n3;
        short s = this.a(sArray, by);
        short s2 = this.a(this.b[this.h], this.g[this.h]);
        for (n3 = 0; n3 < this.g[this.h]; ++n3) {
            this.b(this.b[this.h][n3], 1000);
        }
        for (n3 = 0; n3 < by; ++n3) {
            this.b(sArray[n3], 1000);
        }
        short s3 = this.a(this.b[this.h], this.g[this.h]);
        this.a(sArray, by, (short)(s2 - s3));
        s3 = this.a(sArray, by);
        this.a(this.b[this.h], this.g[this.h], (short)(s - s3));
        this.b(sArray, by, n, n2);
    }

    public final void a(short[] sArray, byte by, int n, int n2, int n3) {
        int n4;
        short s = this.a(sArray, by);
        short s2 = this.a(this.b[this.h], this.g[this.h]);
        for (n4 = 0; n4 < this.g[this.h]; ++n4) {
            this.b(this.b[this.h][n4], 300);
        }
        for (n4 = 0; n4 < by; ++n4) {
            this.b(sArray[n4], n3);
        }
        short s3 = this.a(this.b[this.h], this.g[this.h]);
        this.a(sArray, by, (short)(s2 - s3));
        s3 = this.a(sArray, by);
        this.a(this.b[this.h], this.g[this.h], (short)(s - s3));
        this.b(sArray, by, n, n2);
    }

    public final void b(short[] sArray, byte by, int n, int n2, int n3) {
        int n4;
        short s = this.a(sArray, by);
        short s2 = this.a(this.b[this.h], this.g[this.h]);
        for (n4 = 0; n4 < this.g[this.h]; ++n4) {
            this.b(this.b[this.h][n4], n3);
        }
        for (n4 = 0; n4 < by; ++n4) {
            this.b(sArray[n4], 300);
        }
        short s3 = this.a(this.b[this.h], this.g[this.h]);
        this.a(sArray, by, (short)(s2 - s3));
        s3 = this.a(sArray, by);
        this.a(this.b[this.h], this.g[this.h], (short)(s - s3));
        this.a(sArray, by, this.b[this.g], n, n2);
    }

    public final int c(short s) {
        int n = (this.t[s] + this.s[s]) * (this.k[s] + 150);
        n = this.a(this.t[s], n);
        n = this.a(this.s[s], n);
        return n;
    }

    public final int b(short[] sArray, int n) {
        int n2 = 0;
        int n3 = 0;
        for (n3 = 0; n3 < n; ++n3) {
            int n4 = this.c(sArray[n3]);
            n2 += n4;
        }
        return n2;
    }

    public final boolean a(short[] sArray, byte by, int n, int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        n3 = this.b(sArray, (int)by);
        n4 = this.b(this.b[this.h], (int)this.g[this.h]);
        n5 = this.s[this.b[this.h][0]] * this.k[this.b[this.h][0]] * 3 / 2;
        n5 = this.a(this.s[this.b[this.h][0]], n5);
        if ((n6 = (n5 = n3 - (n4 += n5)) > 0 ? 300 * n3 / n4 : 300 * n4 / n3) > 1000) {
            n6 = 1000;
        }
        if ((n -= this.a(sArray, by, this.h, n6)) < 0) {
            n = 0;
        }
        if (this.e[this.h] < 0) {
            this.e[this.h] = 0;
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
    public final void ba() {
        long l = System.currentTimeMillis();
        while (this.a.a == 0) {
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
            this.a.repaint();
            l = System.currentTimeMillis();
        }
        return;
    }

    public final void p(int n) {
        int n2 = 0;
        int n3 = 0;
        boolean bl = false;
        this.j = this.b(this.t, n);
        n2 = this.a(this.t, (int)this.j);
        n3 = this.d[this.g] < 2 ? 0 : this.a() % (this.d[this.g] / 2);
        byte by = this.g;
        this.d[by] = (short)(this.d[by] - n3);
        byte by2 = this.g;
        this.e[by2] = (short)(this.e[by2] - n2);
        System.gc();
        if (this.b[this.h] == this.a[this.d]) {
            this.a = 1;
            this.q((byte)19);
            this.ba();
            this.i = (short)n3;
            this.j = (short)n2;
            this.c = (byte)3;
            return;
        }
        if (this.b[this.h] == 0) {
            this.a = 2;
            this.q((byte)19);
            this.ag();
            this.a(this.t, this.j, n2, n3);
        } else {
            short s = this.b[this.h];
            byte by3 = this.d(s);
            this.a = 1;
            this.q((byte)19);
            this.ag();
            this.a = this.a(this.t, this.j, n2, n3) ? 4 : 5;
            this.q((byte)19);
            this.ag();
            if (this.a[by3] <= 0) {
                this.b = s;
                this.f = by3;
                this.a = 18;
                this.q((byte)19);
                this.ag();
            } else if (this.a[by3] != s) {
                this.b = s;
                this.f = by3;
                this.q((byte)19);
                this.ag();
            }
        }
        this.h[this.h] = this.a(this.b[this.h], this.g[this.h]);
        try {
            Thread.sleep(500L);
        }
        catch (Exception exception) {}
        System.gc();
    }

    public final void J(byte by) {
        if (this.b >= 2) {
            this.Q(by);
        }
    }

    public final void bb() {
        int n = 0;
        boolean bl = false;
        byte by = this.b(this.e);
        while (this.m < by) {
            this.m = (byte)(this.m + 1);
            this.P = (byte)-1;
            this.g = 0;
            this.h = 0;
            System.gc();
            this.J(this.e);
            n = this.d();
            if (this.P == 3) {
                n = this.b(n);
                if (this.P == 3) {
                    this.p(n);
                    if (this.c != 3) continue;
                    break;
                }
            }
            this.bd();
            if (this.P == 2) {
                this.bf();
                continue;
            }
            n = this.f();
            if (this.P == 1) {
                this.r(n);
                continue;
            }
            this.bh();
            if (this.P == 19) {
                this.bi();
                continue;
            }
            this.bk();
            if (this.P == 8) {
                this.bl();
                continue;
            }
            n = this.i();
            if (this.P == 12) {
                this.s(n);
                continue;
            }
            this.bn();
        }
        this.m = 0;
    }

    public final boolean r(byte by) {
        for (int i = 0; i < this.g[by]; ++i) {
            if (this.r[this.b[by][i]] >= this.q[this.b[by][i]] || this.r[this.b[by][i]] > 40) continue;
            return false;
        }
        return true;
    }

    public final int d(byte by, byte by2) {
        int n = this.e[by] / 4 * 1000 / 45;
        if (!this.r(by)) {
            return -2000;
        }
        int n2 = n >= this.h[by] ? this.h[by] - this.h[by2] : n - this.h[by2];
        if (this.b[by2] == 0) {
            n2 -= 2500;
        }
        return n2;
    }

    public final int e(byte by, byte by2) {
        if (this.b == 1) {
            return this.d(by, by2);
        }
        if (this.b == 2) {
            return this.f(by, by2);
        }
        return 0;
    }

    public final boolean o() {
        for (byte by = 0; by < this.a; by = (byte)(by + 1)) {
            if (this.z[by] != this.e) continue;
            return true;
        }
        return false;
    }

    public final int c() {
        int n = -1001;
        boolean bl = this.o();
        for (byte by = 0; by < this.b[this.e]; by = (byte)(by + 1)) {
            byte by2 = this.a[this.e][by];
            if (this.g[by2] <= 2) continue;
            for (byte by3 = 0; by3 < this.n[by2]; by3 = (byte)(by3 + 1)) {
                int n2;
                byte by4 = this.b[by2][by3];
                if (this.b[by2] == this.b[by4] || bl && this.b[by4] == this.b[this.d] || (n2 = this.e(by2, by4)) <= n) continue;
                n = n2;
                this.g = by2;
                this.h = by4;
            }
        }
        if (n >= -1000) {
            this.P = (byte)3;
        }
        return n;
    }

    public final int d() {
        if (this.b == 1) {
            return this.c();
        }
        if (this.b == 2) {
            return this.j();
        }
        return 0;
    }

    public final void bc() {
        if (this.a() % 3 > 0) {
            return;
        }
        for (byte by = 0; by < this.b[this.e]; by = (byte)(by + 1)) {
            byte by2 = this.a[this.e][by];
            if (this.h[by2] >= this.g[by2] * 1000 || this.d[by2] < 50 && this.i[by2] <= 0) continue;
            for (byte by3 = 0; by3 < this.n[by2]; by3 = (byte)(by3 + 1)) {
                if (this.b[this.b[by2][by3]] == this.b[by2]) continue;
                this.P = (byte)2;
                this.g = by2;
                return;
            }
        }
    }

    public final void bd() {
        if (this.b == 1) {
            this.bc();
            return;
        }
        if (this.b == 2) {
            this.bw();
        }
    }

    public final void be() {
        int n;
        for (n = 0; n < this.h[this.g] / 1000; n = (int)((byte)(n + 1))) {
            this.k[this.b[this.g][n]] = 1000;
        }
        if (n < this.g[this.g]) {
            this.k[this.b[this.g][n]] = (short)(this.h[this.g] % 1000);
        }
    }

    public final void bf() {
        int n = this.g[this.g] * 1000;
        int n2 = n - this.h[this.g];
        int n3 = n2 - this.i[this.g];
        if (n3 <= 0) {
            this.h[this.g] = (short)n;
            byte by = this.g;
            this.i[by] = (short)(this.i[by] - n2);
        } else {
            byte by = this.g;
            this.h[by] = (short)(this.h[by] + this.i[this.g]);
            int n4 = ((n3 - 1) / 100 + 1) * 20;
            if (this.d[this.g] >= n4) {
                byte by2 = this.g;
                this.d[by2] = (short)(this.d[by2] - n4);
                this.h[this.g] = (short)n;
                this.i[this.g] = (short)(n4 * 5 - n3);
            } else {
                this.i[this.g] = 0;
                n4 = this.d[this.g] / 20 * 20;
                byte by3 = this.g;
                this.h[by3] = (short)(this.h[by3] + n4 * 5);
                byte by4 = this.g;
                this.d[by4] = (short)(this.d[by4] - n4);
            }
        }
        this.be();
    }

    public final boolean s(byte by) {
        for (byte by2 = 0; by2 < this.n[by]; by2 = (byte)(by2 + 1)) {
            if (this.b[this.b[by][by2]] == this.b[by]) continue;
            return true;
        }
        return false;
    }

    public final int e() {
        int n = 0;
        if (this.a() % 3 > 0) {
            return 0;
        }
        for (byte by = 0; by < this.b[this.e]; by = (byte)(by + 1)) {
            byte by2;
            byte by3;
            byte by4 = this.a[this.e][by];
            int n2 = 0;
            for (by3 = 0; by3 < this.n[by4] && (n2 = this.b[this.b[by4][by3]] == this.b[by4] ? (int)((byte)(n2 | 1)) : (int)((byte)(n2 | 2))) != 3; by3 = (byte)(by3 + 1)) {
            }
            if (by3 >= this.n[by4]) break;
            if (this.g[by4] < 3) {
                for (by3 = 0; by3 < this.n[by4]; by3 = (byte)(by3 + 1)) {
                    by2 = this.b[by4][by3];
                    if (this.b[by2] != this.b[by4] || this.g[by2] <= 1) continue;
                    if (this.s(by2)) {
                        if (this.g[by2] < 4) continue;
                        n = (this.g[by2] - this.g[by4]) / 2;
                    } else {
                        n = this.g[by2] - 1;
                    }
                    this.P = 1;
                    this.g = by4;
                    this.h = by2;
                    return n;
                }
                continue;
            }
            if (this.g[by4] >= 6) continue;
            for (by3 = 0; by3 < this.n[by4]; by3 = (byte)(by3 + 1)) {
                by2 = this.b[by4][by3];
                if (this.b[by2] != this.b[by4] || this.g[by2] <= 2 || this.s(by2)) continue;
                this.P = 1;
                this.g = by4;
                this.h = by2;
                n = this.g[by2] - 2;
                return n;
            }
        }
        return 0;
    }

    public final int f() {
        if (this.b == 1) {
            return this.e();
        }
        if (this.b == 2) {
            return this.k();
        }
        return 0;
    }

    public final void q(int n) {
        for (int i = 0; i < n; ++i) {
            byte by = this.h;
            this.g[by] = (byte)(this.g[by] - 1);
            this.b[this.g][this.g[this.g]] = this.b[this.h][this.g[this.h]];
            short s = this.k[this.b[this.g][this.g[this.g]]];
            byte by2 = this.g;
            this.h[by2] = (short)(this.h[by2] + s);
            byte by3 = this.h;
            this.h[by3] = (short)(this.h[by3] - s);
            this.b[this.h][this.g[this.h]] = 0;
            byte by4 = this.g;
            this.g[by4] = (byte)(this.g[by4] + 1);
            if (this.g[this.g] < 10) continue;
            return;
        }
    }

    public final void r(int n) {
        if (this.b == 1) {
            this.q(n);
            return;
        }
        if (this.b == 2) {
            this.w(n);
        }
    }

    public final void bg() {
        if (this.a() % 3 > 0) {
            return;
        }
        for (byte by = 0; by < this.b[this.e]; by = (byte)(by + 1)) {
            byte by2 = this.a[this.e][by];
            if ((this.k[by2] & 4) != 4 || this.d[by2] < 100 || !this.s(by2)) continue;
            for (byte by3 = 0; by3 < this.g[by2]; by3 = (byte)(by3 + 1)) {
                if (this.q[this.b[by2][by3]] - 40 <= this.r[this.b[by2][by3]]) continue;
                this.P = (byte)19;
                this.g = by2;
                this.a = this.b[by2][by3];
                return;
            }
        }
    }

    public final void bh() {
        if (this.b == 1) {
            this.bg();
            return;
        }
        if (this.b == 2) {
            this.bz();
        }
    }

    public final int g() {
        return 35 + this.a() % 17;
    }

    public final void bi() {
        short s = this.a;
        this.r[s] = (byte)(this.r[s] + this.g());
        if (this.r[this.a] > this.q[this.a] || this.r[this.a] <= 0) {
            this.r[this.a] = this.q[this.a];
        }
        byte by = this.g;
        this.d[by] = (short)(this.d[by] - 50);
    }

    public final void bj() {
        if (this.a() % 5 > 0) {
            return;
        }
        for (byte by = 0; by < this.b[this.e]; by = (byte)(by + 1)) {
            byte by2 = this.a[this.e][by];
            if (this.d[by2] < 200 && this.j[by2] == 0) continue;
            for (byte by3 = 0; by3 < this.g[by2]; by3 = (byte)(by3 + 1)) {
                if (this.v[this.b[by2][by3]] >= 50) continue;
                this.P = (byte)8;
                this.g = by2;
                this.a = this.b[by2][by3];
                return;
            }
        }
    }

    public final void bk() {
        if (this.b == 1) {
            this.bj();
            return;
        }
        if (this.b == 2) {
            this.bx();
        }
    }

    public final int c(int n) {
        if (n == 200) {
            return 11 + this.a() % 8;
        }
        int n2 = n / 8;
        int n3 = this.a() % 8;
        if (n3 < n % 8) {
            ++n2;
        }
        if (n2 > 7) {
            n2 += this.a() % 2 - 1;
        }
        return n2;
    }

    public final void bl() {
        if (this.j[this.g] > 0) {
            short s = this.a;
            this.v[s] = (byte)(this.v[s] + this.c(200));
            byte by = this.g;
            this.j[by] = (byte)(this.j[by] - 1);
        } else {
            short s = this.a;
            this.v[s] = (byte)(this.v[s] + this.c(100));
            byte by = this.g;
            this.d[by] = (short)(this.d[by] - 100);
        }
        if (this.v[this.a] > 99 || this.v[this.a] <= 0) {
            this.v[this.a] = 99;
        }
    }

    public final int h() {
        int n = 0;
        if (this.a() % 3 > 0) {
            return 0;
        }
        for (byte by = 0; by < this.b[this.e]; by = (byte)(by + 1)) {
            byte by2 = this.a[this.e][by];
            if (!this.s(by2) || this.d[by2] + this.e[by2] >= 400) continue;
            for (byte by3 = 0; by3 < this.n[by2]; by3 = (byte)(by3 + 1)) {
                byte by4 = this.b[by2][by3];
                if (this.a[this.e] != this.b[by4]) continue;
                if (this.s(by4) && this.d[by4] + this.e[by4] >= 1500) {
                    n = 1;
                } else {
                    if (this.s(by4) || this.d[by4] + this.e[by4] < 800) continue;
                    n = 0;
                }
                this.P = (byte)12;
                this.g = by2;
                this.h = by4;
                return n;
            }
        }
        return 0;
    }

    public final int i() {
        if (this.b == 1) {
            return this.h();
        }
        if (this.b == 2) {
            return this.l();
        }
        return 0;
    }

    public final void s(int n) {
        int n2;
        int n3;
        if (n == 0) {
            n3 = this.d[this.h] - 100;
            n2 = this.e[this.h] - 100;
        } else {
            n3 = this.d[this.h] - (this.d[this.g] + this.d[this.h]) / 2;
            n2 = this.e[this.h] - (this.e[this.g] + this.e[this.h]) / 2;
        }
        if (n3 > 0) {
            byte by = this.g;
            this.d[by] = (short)(this.d[by] + n3);
            byte by2 = this.h;
            this.d[by2] = (short)(this.d[by2] - n3);
        }
        if (n2 >= 0) {
            byte by = this.g;
            this.e[by] = (short)(this.e[by] + n2);
            byte by3 = this.h;
            this.e[by3] = (short)(this.e[by3] - n2);
        }
    }

    public final void a(byte by, short s) {
        if (this.t[by] < 30) {
            byte by2 = by;
            this.d[by2] = (short)(this.d[by2] - 30);
        } else if (this.t[by] < 60) {
            byte by3 = by;
            this.d[by3] = (short)(this.d[by3] - 20);
        } else {
            byte by4 = by;
            this.d[by4] = (short)(this.d[by4] - 10);
        }
        short s2 = s;
        this.t[s2] = (byte)(this.t[s2] + this.b(s));
    }

    public final boolean t(byte by) {
        byte by2 = 0;
        short s = 0;
        if ((this.k[by] & 8) == 8) {
            if (this.d[by] < 100 && this.e[by] > 500) {
                byte by3 = by;
                this.e[by3] = (short)(this.e[by3] - 200);
                byte by4 = by;
                this.d[by4] = (short)(this.d[by4] + 150);
                return true;
            }
            if (this.e[by] < 200 && this.d[by] > 500) {
                byte by5 = by;
                this.d[by5] = (short)(this.d[by5] - 200);
                byte by6 = by;
                this.e[by6] = (short)(this.e[by6] + 266);
                return true;
            }
        }
        if (this.d[by] < 50 || (this.k[by] & 2) != 2) {
            return false;
        }
        for (byte by7 = 0; by7 < this.g[by]; by7 = (byte)(by7 + 1)) {
            if (by2 >= this.s[this.b[by][by7]] || this.t[this.b[by][by7]] >= 80) continue;
            s = this.b[by][by7];
            by2 = this.s[s];
        }
        if (by2 > 0) {
            this.a(by, s);
            return true;
        }
        return false;
    }

    public final void b(byte by, short s, short s2) {
        if (this.b[by] == this.a[this.d]) {
            this.a = 16;
            this.b = s2;
            this.q((byte)19);
            this.ag();
        }
        this.c(s, s2);
    }

    public final void a(byte by, byte by2, short s, short s2) {
        if (this.a(by, by2, s, s2) && this.b[by2] == this.a[this.d]) {
            this.a = 17;
            this.g = by;
            this.b = s2;
            this.q((byte)19);
            this.ag();
        }
    }

    public final boolean u(byte by) {
        byte by2;
        byte by3 = 0;
        byte by4 = 100;
        for (by2 = 1; by2 < 37; by2 = (byte)((byte)(by2 + 1))) {
            by3 = by2;
            if (this.b[by3] == this.b[by]) continue;
            for (byte by5 = 0; by5 < this.g[by3]; by5 = (byte)(by5 + 1)) {
                if (by4 <= this.v[this.b[by3][by5]]) continue;
                this.h = by3;
                this.b = this.b[by3][by5];
                by4 = this.v[this.b];
            }
        }
        if (by4 == 100) {
            return false;
        }
        this.a = this.b[by][0];
        byte by6 = this.t[this.a];
        for (by2 = 1; by2 < this.g[by]; by2 = (byte)(by2 + 1)) {
            if (by6 >= this.t[this.b[by][by2]]) continue;
            this.a = this.b[by][by2];
            by6 = this.t[this.a];
        }
        if (this.g[by] < 10 && (by4 < 15 || by4 < 35 && this.a() % 2 > 0)) {
            this.a(by, this.h, this.a, this.b);
        } else {
            this.b(this.h, this.a, this.b);
        }
        return true;
    }

    public final boolean e(short s, short s2) {
        byte by = this.u[s];
        byte by2 = this.t[s2] > this.s[s2] ? this.t[s2] : this.s[s2];
        by2 = by2 > this.u[s2] ? by2 : this.u[s2];
        Math.abs(this.a.nextInt());
        boolean bl = false;
        return by2 - by < this.a() % 40;
    }

    public final short[] b(short[] sArray, byte by, short s) {
        if (by <= 1) {
            return null;
        }
        short[] sArray2 = new short[by - 1];
        int n = 0;
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            if (sArray[by2] == s) continue;
            int n2 = n;
            n = (byte)(n + 1);
            sArray2[n2] = sArray[by2];
        }
        return sArray2;
    }

    public final boolean a(byte by, short s, short s2) {
        if (this.e(s, s2)) {
            this.b[by][this.g[by]] = s2;
            byte by2 = by;
            this.h[by2] = (short)(this.h[by2] + this.k[s2]);
            byte by3 = by;
            this.g[by3] = (byte)(this.g[by3] + 1);
            this.d[by] = this.b(this.d[by], this.i[by], s2);
            byte by4 = by;
            this.i[by4] = (byte)(this.i[by4] - 1);
            return true;
        }
        return false;
    }

    public final int a(short s, byte by) {
        int n;
        byte by2 = this.t[s];
        byte by3 = this.u[s];
        int n2 = this.a() % 120;
        int n3 = 0;
        if (n2 > by2) {
            return 0;
        }
        if (n2 > 79) {
            int n4 = this.a() % 100;
            n3 = n4 < 5 ? 4 : (n4 < 15 ? 3 : (n4 < 40 ? 2 : 1));
            n = 4;
        } else if (n2 > 59 && by != 0) {
            n3 = this.a() % by;
            n = 3;
        } else {
            int n5 = by3 * 12 / 10;
            int n6 = by3 * 17 / 10;
            n3 = (n5 + this.a() % (n6 - n5)) * 6 / 10;
            n = n2 % 2 == 1 ? 2 : 1;
        }
        return n += (n3 <<= 8);
    }

    public final void b(byte by, short s) {
        this.a = this.a(s, this.h[by]);
        this.b = this.a & 0xFF;
        this.a >>= 8;
        if (this.b == 1) {
            byte by2 = by;
            this.e[by2] = (short)(this.e[by2] + this.a);
            if (this.e[by] > 9999) {
                this.e[by] = 9999;
                return;
            }
        } else if (this.b == 2) {
            byte by3 = by;
            this.d[by3] = (short)(this.d[by3] + this.a);
            if (this.d[by] > 9999) {
                this.d[by] = 9999;
                return;
            }
        } else {
            if (this.b == 3) {
                this.d[by] = this.a(this.d[by], this.i[by], this.c[by][this.a]);
                this.c[by] = this.b(this.c[by], this.h[by], this.c[by][this.a]);
                byte by4 = by;
                this.i[by4] = (byte)(this.i[by4] + 1);
                byte by5 = by;
                this.h[by5] = (byte)(this.h[by5] - 1);
                return;
            }
            if (this.b == 4) {
                byte by6 = by;
                this.j[by6] = (byte)(this.j[by6] + this.a);
                if (this.j[by] > 99) {
                    this.j[by] = 99;
                }
            }
        }
    }

    public final short a(byte by) {
        short[] sArray = new short[this.g[by]];
        int n = 0;
        byte by2 = 0;
        for (byte by3 = 0; by3 < this.g[by]; by3 = (byte)(by3 + 1)) {
            if (by2 < this.t[this.b[by][by3]]) {
                this.a = this.b[by][by3];
                by2 = this.t[this.a];
            }
            if (this.t[this.b[by][by3]] < 70) continue;
            int n2 = n;
            n = (byte)(n + 1);
            sArray[n2] = this.b[by][by3];
        }
        if (n == 0) {
            return this.a;
        }
        return sArray[this.a() % n];
    }

    public final void K(byte by) {
        this.a = this.a(by);
        if (this.g[by] < 10 && this.i[by] > 0) {
            this.b = this.d[by][this.a() % this.i[by]];
            this.a(by, this.a, this.b);
            return;
        }
        this.b(by, this.a);
    }

    public final int d(short s) {
        byte by = this.t[s];
        int n = 10;
        if (by > 70) {
            n += 8;
        } else if (by > 50) {
            n += 4;
        }
        return n += 2 * (this.a() % 5);
    }

    public final int a(short s, int n) {
        byte by = this.t[s];
        int n2 = 0;
        if (by < 12) {
            return 1;
        }
        int n3 = by * n / 100;
        int n4 = n3 * 7 / 10;
        n2 = n4 + this.a() % (n3 - n4 + 1);
        return n2;
    }

    public final int e(short s) {
        byte by = this.t[s];
        int n = 10;
        if (by > 70) {
            n += 6;
        } else if (by > 50) {
            n += 3;
        }
        return n += this.a() % 4;
    }

    public final int b(short s, int n) {
        byte by = this.t[s];
        int n2 = 0;
        if (by < 12) {
            return 1;
        }
        int n3 = by * n / 100;
        int n4 = n3 / 2;
        n2 = n4 + this.a() % (n3 - n4 + 1);
        return n2;
    }

    public final int c(short s, int n) {
        byte by = this.t[s];
        int n2 = 0;
        if (by < 17) {
            return 1000;
        }
        n2 = by * n / 100;
        int n3 = n2 / 2;
        int n4 = n2 - 1;
        n2 = n3 + this.a() % (n4 - n3 + 1);
        return n2 *= 100;
    }

    public final void c(byte by, short s) {
        int n = this.d(s);
        this.a(by, s, n);
    }

    public final void d(byte by, short s) {
        int n = this.d(s);
        this.b(by, s, n);
    }

    public final void e(byte by, short s) {
        int n = this.d(s);
        this.d(by, s, n);
    }

    public final void f(byte by, short s) {
        int n = this.e(s);
        this.c(by, s, n);
    }

    public final void L(byte by) {
        if (this.f[by] < 60) {
            this.f(by, this.a);
            return;
        }
        if (this.a() % 2 > 0) {
            if (this.f[by] < 999 && this.g[by] < 999) {
                if (this.k < 4 || this.k >= 10) {
                    this.c(by, this.a);
                    return;
                }
                this.d(by, this.a);
                return;
            }
            if (this.f[by] < 999) {
                this.c(by, this.a);
                return;
            }
            if (this.g[by] < 999) {
                this.d(by, this.a);
                return;
            }
        }
        if (this.a[by] < 990000) {
            this.e(by, this.a);
            return;
        }
        this.f(by, this.a);
    }

    public final void M(byte by) {
        this.a = this.a(by);
        this.L(by);
    }

    public final void bm() {
        int n;
        int n2 = this.a() % this.b[this.e];
        byte by = this.a[this.e][n2];
        int n3 = this.d[by] >= 50 && (this.f[by] < 999 || this.g[by] < 999 || this.a[by] < 990000 || this.f[by] < 99) ? 350 : 150;
        for (n = 0; n < 10; n = (int)((byte)(n + 1))) {
            n2 = this.a() % n3;
            if (n2 < 10) {
                if (!this.t(by)) continue;
                break;
            }
            if (n2 < 50) {
                if (!this.u(by)) continue;
                break;
            }
            if (n2 < 150) {
                this.K(by);
                break;
            }
            if (n2 >= 350) continue;
            this.M(by);
            break;
        }
        if (n == 10) {
            this.K(by);
        }
    }

    public final void bn() {
        if (this.b == 1) {
            this.bm();
            return;
        }
        if (this.b == 2) {
            this.by();
        }
    }

    public final boolean f(short s, short s2) {
        if (this.v[s2] > 99) {
            return false;
        }
        int n = (this.u[s] * 7 + this.t[s] * 3) * 10 / 99;
        if ((n = (n - 70) / 5) == 4) {
            n = 6;
        } else if (n == 5) {
            n = 8;
        }
        n = this.r[s2] + this.v[s2] - n + this.a.nextInt() % 4;
        return n < 100;
    }

    public final int a(short s, short s2, short s3) {
        int n = this.s[s];
        n *= s2;
        if ((n /= s3) > 2 * this.s[s]) {
            n = 2 * this.s[s];
        }
        if (n < this.s[s] / 3) {
            n = this.s[s] / 3;
        }
        n += n / 10;
        return n;
    }

    public final byte a(short s, short s2, short s3) {
        byte by = (byte)(this.a(s, s2, s3) / 20);
        if (by > 0) {
            return by;
        }
        return 1;
    }

    public final byte b(short s, short s2, short s3) {
        byte by = 0;
        if (this.l && this.b == 4 && s != 4) {
            this.l = false;
        }
        by = s == this.a ? (byte)(7 + this.af) : (byte)(7 - this.af);
        if (this.a() % 10 < by) {
            return (byte)(this.a(s, s2, s3) / 5);
        }
        return 0;
    }

    public final byte c(short s, short s2, short s3) {
        byte by = 0;
        this.P = (byte)3;
        if (this.l && this.b == 4 && s != 4) {
            this.l = false;
        }
        by = s == this.a ? (byte)(2 + this.af) : (byte)(2 - this.af);
        if (this.a() % 10 < by) {
            return (byte)(this.a(s, s2, s3) / 2);
        }
        return (byte)(this.a.nextInt() % 5 - 18);
    }

    public final short a(boolean bl) {
        short s = 0;
        if (bl) {
            for (int n = 1; n < 4; n = (int)((byte)(n + 1))) {
                s = (short)(s + this.x[n]);
            }
        } else {
            for (int n = 1; n < 4; n = (int)((byte)(n + 1))) {
                s = (short)(s + this.y[n]);
            }
        }
        return s;
    }

    public final boolean p() {
        int n;
        this.o = 0;
        for (n = 229; n < 232 && this.j[n] > 0; n = (int)((short)(n + 1))) {
            this.a[n] = false;
            byte by = this.o;
            this.o = (byte)(by + 1);
            this.u[by] = n;
        }
        if (n > 229) {
            this.c = false;
            this.a = false;
            return true;
        }
        return false;
    }

    public final void bo() {
        this.a = true;
        this.c = false;
        this.o = 0;
        for (int n = 229; n < 232; n = (int)((short)(n + 1))) {
            if (this.j[n] <= 0) {
                return;
            }
            if (!this.a[n]) continue;
            this.a[n] = false;
            byte by = this.o;
            this.o = (byte)(by + 1);
            this.u[by] = n;
        }
    }

    public final void bp() {
        short s;
        this.c = true;
        for (s = 0; s < this.o; s = (short)(s + 1)) {
            if (!this.a[this.u[s]]) continue;
            this.t[0] = this.u[s];
        }
        this.j = 1;
        for (s = 0; s < this.o; s = (short)(s + 1)) {
            if (this.a[this.u[s]]) continue;
            byte by = this.j;
            this.j = (byte)(by + 1);
            this.t[by] = this.u[s];
        }
        this.a[this.t[0]] = false;
    }

    public final void N(byte by) {
        this.d = (byte)8;
        this.a[8] = 8;
        this.a[8] = this.t[0];
        this.b[8] = 1;
        this.a[8][0] = by;
        this.b[by] = this.t[0];
        this.c[by] = this.t[0];
        this.g[by] = this.j;
        this.h[by] = 0;
        for (byte by2 = 0; by2 < this.j; by2 = (byte)(by2 + 1)) {
            this.v[this.t[by2]] = 99;
            this.b[by][by2] = this.t[by2];
            byte by3 = by;
            this.h[by3] = (short)(this.h[by3] + this.k[this.t[by2]]);
        }
        this.v[this.t[0]] = 100;
    }

    public final void bq() {
        int n;
        int n2 = 229;
        for (n = 229; n < 232 && this.j[n] > 0; n = (int)((short)(n + 1))) {
            if (!this.a[n]) continue;
            n2 = n;
            this.j[n] = 0;
            this.a[n] = false;
            break;
        }
        for (n = (int)((short)(n2 + 1)); n < 232 && this.j[n] > 0; n = (int)((short)(n + 1))) {
            if (this.a[n]) {
                this.j[n] = 0;
                this.a[n] = false;
                continue;
            }
            this.j[n2] = this.j[n];
            this.b[n2] = this.b[n];
            this.o[n2] = this.o[n];
            this.p[n2] = this.p[n];
            this.k[n2] = this.k[n];
            this.q[n2] = this.q[n];
            this.r[n2] = this.r[n];
            this.s[n2] = this.s[n];
            this.t[n2] = this.t[n];
            this.u[n2] = this.u[n];
            this.v[n2] = this.v[n];
            this.l[n2] = this.l[n];
            this.w[n2] = this.w[n];
            this.x[n2] = this.x[n];
            this.z[n2 - 229] = this.z[n - 229];
            n2 = (short)(n2 + 1);
        }
        for (n = n2; n < 232; n = (int)((short)(n + 1))) {
            this.j[n] = 0;
        }
    }

    public final void br() {
        short s = 0;
        s = this.a(this.R, (short)0);
        RecordStore recordStore = null;
        try {
            recordStore = RecordStore.openRecordStore((String)"newPs", (boolean)true, (int)1, (boolean)true);
            if (recordStore.getNumRecords() > 0) {
                recordStore.setRecord(1, this.R, 0, (int)s);
            } else {
                recordStore.addRecord(this.R, 0, (int)s);
            }
            try {
                for (int i = 0; i < 100; ++i) {
                    recordStore.closeRecordStore();
                }
            }
            catch (RecordStoreNotOpenException recordStoreNotOpenException) {}
            recordStore = null;
            return;
        }
        catch (Exception exception) {
            if (recordStore != null) {
                try {
                    for (int i = 0; i < 100; ++i) {
                        recordStore.closeRecordStore();
                    }
                    return;
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    return;
                }
                catch (Exception exception2) {}
            }
            return;
        }
    }

    public final boolean q() {
        if (!this.a(this.t, this.f)) {
            return false;
        }
        short s = this.a();
        short s2 = this.b();
        if (s2 < s / 4) {
            return true;
        }
        return this.k[this.w[0]] < 100 && this.r[this.w[0]] < 40;
    }

    public final int a(int n, int n2) {
        byte by = this.c(this.w[n]);
        byte[] byArray = new byte[16];
        int n3 = 0;
        for (byte by2 = 0; by2 < by; by2 = (byte)(by2 + 1)) {
            if (by2 == 8 || by2 == 11 || this.k[this.v[n2]] == 0 && (by2 == 0 || by2 == 3 || by2 == 4) || this.t[this.w[n]] <= this.t[this.v[n2]] || !this.a(n, n2, by2, false) || this.c(by2) > this.x) continue;
            byArray[n3] = by2;
            n3 = (byte)(n3 + 1);
        }
        if (n3 == 0) {
            return -1;
        }
        return byArray[this.a() % n3];
    }

    public final byte c(short s, byte[] byArray, byte by, byte by2) {
        byte[] byArray2 = new byte[by];
        int n = 0;
        for (byte by3 = 0; by3 < by; by3 = (byte)(by3 + 1)) {
            if (this.b[byArray[by3]] != s) continue;
            int n2 = n;
            n = (byte)(n + 1);
            byArray2[n2] = byArray[by3];
        }
        if (n > 0) {
            return byArray2[this.a() % n];
        }
        if (by > 0) {
            return byArray[this.a() % by];
        }
        return -1;
    }

    public final byte a(byte by, byte by2, byte by3) {
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
        for (byte by4 = 0; by4 < 8; by4 = (byte)((byte)(by4 + 1))) {
            block13: for (byte by5 = 0; by5 < 8; by5 = (byte)(by5 + 1)) {
                byte by6 = this.g[by4 + by3][by5 + by2];
                if ((by6 & 0xC0) != 0) {
                    this.a(by4, by5, 100, byArray, sArray);
                    continue;
                }
                by6 = (byte)(by6 & 0x1F);
                switch (by6) {
                    case 0: 
                    case 1: 
                    case 3: 
                    case 4: {
                        this.a(by4, by5, n, byArray, sArray);
                        continue block13;
                    }
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 11: 
                    case 12: {
                        this.a(by4, by5, n2, byArray, sArray);
                        continue block13;
                    }
                    case 6: {
                        this.a(by4, by5, n3, byArray, sArray);
                        continue block13;
                    }
                    case 5: {
                        this.a(by4, by5, 5, byArray, sArray);
                        continue block13;
                    }
                    case 2: {
                        this.a(by4, by5, 6, byArray, sArray);
                        continue block13;
                    }
                    default: {
                        this.a(by4, by5, 100, byArray, sArray);
                    }
                }
            }
        }
    }

    public final byte a(int n, int n2, byte by, byte by2) {
        byte by3 = 0;
        byte by4 = 0;
        byte by5 = (byte)(this.I[n2] - this.K[n]);
        byte by6 = (byte)(this.J[n2] - this.L[n]);
        if (Math.abs(by5) > 6 || Math.abs(by6) > 6) {
            if (Math.abs(by5) >= Math.abs(by6)) {
                if (by5 > 0) {
                    by3 = (byte)(this.K[n] + 6);
                    by4 = (byte)(this.L[n] + 6 * by6 / by5);
                } else if (by5 < 0) {
                    by3 = (byte)(this.K[n] - 6);
                    by4 = (byte)(this.L[n] - 6 * by6 / by5);
                }
            } else if (by6 > 0) {
                by4 = (byte)(this.L[n] + 6);
                by3 = (byte)(this.K[n] + 6 * by5 / by6);
            } else {
                by4 = (byte)(this.L[n] - 6);
                by3 = (byte)(this.K[n] - 6 * by5 / by6);
            }
        } else {
            by3 = this.I[n2];
            by4 = this.J[n2];
        }
        byte by7 = (byte)(by3 - by + (by4 - by2) * 8);
        return by7;
    }

    public final void a(short[] sArray, byte[] byArray, byte[][] byArray2, short[][] sArray2, byte by, byte by2) {
        for (int i = 0; i < 4; ++i) {
            short s;
            byte by3 = byArray2[by2][i];
            if ((sArray[by3] & 0x100) != 0 || sArray[by3] <= (s = (short)(sArray[by2] & 0xFF)) + sArray2[by2][i]) continue;
            byArray[by3] = by2;
            sArray[by3] = (short)(s + sArray2[by2][i]);
        }
    }

    public final void a(short[] sArray, byte[] byArray, byte[][] byArray2, short[][] sArray2, byte by, byte by2, byte[][] byArray3, byte[] byArray4, byte by3) {
        int n;
        byte by4 = 0;
        for (n = 0; n < 4; ++n) {
            by4 = byArray2[by][n];
            sArray[by4] = sArray2[by][n];
            byArray[by4] = by;
        }
        byte by5 = by;
        sArray[by5] = (short)(sArray[by5] | 0x100);
        for (n = 0; n < 63; ++n) {
            byte by6;
            byte by7 = by6 = this.a(sArray, byArray, by);
            sArray[by7] = (short)(sArray[by7] | 0x100);
            this.a(sArray, byArray, byArray2, sArray2, by, by6);
        }
        byArray4[by3] = 0;
        by4 = by2;
        for (n = 0; n < 63; ++n) {
            by4 = byArray[by4];
            byte by8 = by3;
            byArray4[by8] = (byte)(byArray4[by8] + 1);
            if (by4 == by) break;
        }
        byArray3[by3] = new byte[byArray4[by3]];
        by4 = by2;
        for (n = 0; n < byArray4[by3]; ++n) {
            byte by9 = by4;
            if (by9 == (by4 = byArray[by4]) - 8) {
                byArray3[by3][n] = 1;
                continue;
            }
            if (by9 == by4 + 8) {
                byArray3[by3][n] = 2;
                continue;
            }
            if (by9 == by4 - 1) {
                byArray3[by3][n] = 3;
                continue;
            }
            if (by9 != by4 + 1) continue;
            byArray3[by3][n] = 4;
        }
    }

    public final short[] a(byte by, byte by2, byte[][] byArray, byte[] byArray2) {
        int n;
        byte[][] byArrayArray = new byte[64][];
        short[][] sArrayArray = new short[64][];
        short[] sArray = new short[64];
        byte[] byArray3 = new byte[64];
        for (n = 0; n < 64; n = (int)((byte)(n + 1))) {
            byArrayArray[n] = new byte[4];
            sArrayArray[n] = new short[4];
        }
        byte by3 = this.a(this.K[by], this.I[by2], this.y);
        byte by4 = this.a(this.L[by], this.J[by2], this.z);
        this.a(this.p[this.w[by]], by3, by4, (byte[][])byArrayArray, (short[][])sArrayArray);
        byte by5 = (byte)(this.K[by] - by3 + (this.L[by] - by4) * 8);
        byte by6 = this.a((int)by, (int)by2, by3, by4);
        for (n = 0; n < 64; n = (int)((byte)(n + 1))) {
            sArray[n] = 255;
            byArray3[n] = by5;
        }
        this.a(sArray, byArray3, byArrayArray, sArrayArray, by5, by6, byArray, byArray2, by);
        return sArray;
    }

    public final void c(byte by, byte by2, byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        this.P[by] = by2;
        this.a(by, by2, byArray2, byArray3);
    }

    public final boolean b(int n) {
        short s;
        if (!this.a(this.t, this.f)) {
            return false;
        }
        if (this.k[this.w[n]] >= 400) {
            return false;
        }
        short s2 = this.c();
        return s2 * 2 / 33 > (s = this.k[this.w[n]]);
    }

    public final void bs() {
        byte by = -1;
        byte[] byArray = new byte[this.v];
        byte[][] byArrayArray = new byte[this.v][];
        byte[] byArray2 = new byte[this.v];
        while (by != this.x && this.x >= 2) {
            byte by2;
            System.gc();
            by = this.x;
            for (by2 = 0; by2 < this.v; by2 = (byte)(by2 + 1)) {
                byArray[by2] = 0;
                byArray2[by2] = 0;
            }
            this.a(byArray, byArrayArray, byArray2);
            if (byArray[0] == 3) {
                this.a.r = (byte)5;
                this.A();
                return;
            }
            for (by2 = 1; by2 < this.v; by2 = (byte)(by2 + 1)) {
                if ((this.N[by2] & 0x1F) != 0) continue;
                if (this.b((int)by2)) {
                    byArray[by2] = 3;
                    continue;
                }
                if (this.s[this.v[0]] <= this.s[this.w[by2]] && this.k[this.v[0]] <= this.k[this.w[by2]] && this.t[this.v[0]] < this.t[this.w[by2]]) {
                    this.b(by2, byArray, byArrayArray, byArray2);
                    continue;
                }
                this.c(by2, byArray, byArrayArray, byArray2);
            }
            for (by2 = this.G; by2 < this.v; by2 = (byte)(by2 + 1)) {
                System.gc();
                if ((this.N[by2] & 0x1F) != 0) continue;
                this.d(by2, byArray[by2], byArrayArray[by2], byArray2);
                if (this.c <= 0) continue;
                this.G = (byte)(by2 + 1);
                return;
            }
        }
        System.gc();
    }

    public final void c(byte[] byArray, byte[][] byArray2, byte[] byArray3) {
        if (this.e()) {
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

    public final void bt() {
        byte by = -1;
        byte[] byArray = new byte[this.v];
        byte[][] byArrayArray = new byte[this.v][];
        byte[] byArray2 = new byte[this.v];
        ++this.e;
        while (by != this.x && this.x >= 2) {
            byte by2;
            System.gc();
            by = this.x;
            for (by2 = 0; by2 < this.v; by2 = (byte)(by2 + 1)) {
                byArray[by2] = 0;
                byArray2[by2] = 0;
            }
            if (this.e < 4) {
                this.a(byArray, byArrayArray, byArray2);
            } else {
                this.c(byArray, byArrayArray, byArray2);
            }
            if (byArray[0] == 3) {
                this.a.r = (byte)5;
                this.A();
                return;
            }
            for (by2 = 1; by2 < this.v; by2 = (byte)(by2 + 1)) {
                if (by2 == 0 && this.e < 4 || (this.N[by2] & 0x1F) != 0) continue;
                if (by2 != 0 && this.b((int)by2)) {
                    byArray[by2] = 3;
                    continue;
                }
                this.a(by2, byArray, (byte[][])byArrayArray, byArray2);
                if (byArray2[by2] > 0) {
                    byArray[by2] = 2;
                    continue;
                }
                this.b(by2, byArray, byArrayArray, byArray2);
            }
            for (by2 = 0; by2 < this.v; by2 = (byte)(by2 + 1)) {
                System.gc();
                if ((this.N[by2] & 0x1F) != 0) continue;
                this.d(by2, byArray[by2], byArrayArray[by2], byArray2);
                if (this.b(by2)) {
                    return;
                }
                if (this.c <= 0) continue;
                this.G = (byte)(by2 + 1);
                return;
            }
            System.gc();
        }
    }

    public final void bu() {
        if (this.O < 7) {
            return;
        }
        if (this.O < 8) {
            block0: for (byte by = 1; by < this.K; by = (byte)(by + 1)) {
                if (!this.b[1][by] || this.l[1][by] != 2) continue;
                for (int n = 5; n < 7; n = (int)((byte)(n + 1))) {
                    byte by2 = (byte)(this.j[1][by] - n);
                    byte by3 = this.k[1][by];
                    if (by2 < 0) continue block0;
                    if ((this.h[by3][by2] & 0x40) == 0) continue;
                    for (byte by4 = 1; by4 < this.J; by4 = (byte)(by4 + 1)) {
                        if (!this.b[0][by4] || this.j[0][by4] != by2 || this.k[0][by4] != by3) continue;
                        this.O = (byte)(this.O - 7);
                        this.T = (byte)51;
                        return;
                    }
                }
            }
        } else {
            byte by;
            byte by5;
            byte by6;
            block3: for (by6 = 1; by6 < this.K; by6 = (byte)(by6 + 1)) {
                if (!this.b[1][by6] || this.l[1][by6] != 2) continue;
                for (int n = 5; n < 7; n = (int)((byte)(n + 1))) {
                    by5 = (byte)(this.j[1][by6] - n);
                    by = this.k[1][by6];
                    if (by5 < 0) continue block3;
                    if ((this.h[by][by5] & 0x40) == 0) continue;
                    for (byte by7 = 1; by7 < this.J; by7 = (byte)(by7 + 1)) {
                        if (!this.b[0][by7] || this.j[0][by7] != by5 || this.k[0][by7] != by || this.l[0][by7] != 2) continue;
                        this.O = (byte)(this.O - 7);
                        this.T = (byte)51;
                        return;
                    }
                }
            }
            for (by6 = 1; by6 < this.K; by6 = (byte)(by6 + 1)) {
                if (!this.b[1][by6]) continue;
                by5 = this.j[1][by6];
                by = this.k[1][by6];
                if (!(by > 0 && (this.h[by - 1][by5] & 0x40) != 0 || by < 6 && (this.h[by + 1][by5] & 0x40) != 0 || by5 > 0 && (this.h[by][by5 - 1] & 0x40) != 0) && (by5 >= 15 || (this.h[by][by5 + 1] & 0x40) == 0)) continue;
                this.O = (byte)(this.O - 8);
                this.T = (byte)68;
                return;
            }
        }
    }

    public final void t(int n) {
        if (this.f() && n >= 4) {
            for (byte by = 0; by < this.K; by = (byte)(by + 1)) {
                this.n[1][by] = 2;
            }
            return;
        }
        this.bu();
        for (byte by = 0; by < this.K; by = (byte)(by + 1)) {
            this.n[1][by] = 1;
        }
    }

    public final void u(int n) {
        if (this.f() && n >= 4) {
            for (byte by = 0; by < this.K; by = (byte)(by + 1)) {
                this.n[1][by] = 2;
            }
            return;
        }
        this.bu();
        this.n[1][0] = 1;
        for (byte by = 1; by < this.K; by = (byte)(by + 1)) {
            this.n[1][by] = 0;
        }
    }

    public final void bv() {
        for (byte by = 1; by < this.J; by = (byte)(by + 1)) {
            if (!this.b[0][by]) continue;
            byte by2 = this.j[0][by];
            byte by3 = this.k[0][by];
            block1: for (byte by4 = 1; by4 < 5; by4 = (byte)(by4 + 1)) {
                byte by5;
                if (by3 >= by4 && (this.h[by3 - by4][by2] & 0x80) != 0) {
                    for (by5 = 1; by5 < this.K; by5 = (byte)(by5 + 1)) {
                        if (!this.b[1][by5] || this.j[1][by5] != by2 || this.k[1][by5] != by3 - by4) continue;
                        if (this.l[1][by5] == 2) break;
                        this.k = true;
                        return;
                    }
                }
                if (by3 + by4 <= 6 && (this.h[by3 + by4][by2] & 0x80) != 0) {
                    for (by5 = 1; by5 < this.K; by5 = (byte)(by5 + 1)) {
                        if (!this.b[1][by5] || this.j[1][by5] != by2 || this.k[1][by5] != by3 + by4) continue;
                        if (this.l[1][by5] == 2) break;
                        this.k = true;
                        return;
                    }
                }
                if (by2 >= by4 && (this.h[by3][by2 - by4] & 0x80) != 0) {
                    for (by5 = 1; by5 < this.K; by5 = (byte)(by5 + 1)) {
                        if (!this.b[1][by5] || this.j[1][by5] != by2 - by4 || this.k[1][by5] != by3) continue;
                        if (this.l[1][by5] == 2) break;
                        this.k = true;
                        return;
                    }
                }
                if (by2 + by4 > 15 || (this.h[by3][by2 + by4] & 0x80) == 0) continue;
                for (by5 = 1; by5 < this.K; by5 = (byte)(by5 + 1)) {
                    if (!this.b[1][by5] || this.j[1][by5] != by2 + by4 || this.k[1][by5] != by3) continue;
                    if (this.l[1][by5] == 2) continue block1;
                    this.k = true;
                    return;
                }
            }
        }
    }

    public final void v(int n) {
        if (this.f() && n >= 4) {
            for (byte by = 0; by < this.K; by = (byte)(by + 1)) {
                this.n[1][by] = 2;
            }
            return;
        }
        short s = this.a(true);
        short s2 = this.a(false);
        this.bu();
        if (!this.k) {
            byte by;
            for (by = 0; by < this.K; by = (byte)(by + 1)) {
                this.n[1][by] = 1;
            }
            if (s2 > s) {
                this.k = true;
            } else {
                this.bv();
            }
            if (this.k) {
                for (by = 1; by < this.K; by = (byte)(by + 1)) {
                    this.n[1][by] = 0;
                }
            }
        }
    }

    public final byte c(short s, short s2) {
        if (this.r[s] < 20 && this.a() % 2 == 0) {
            return 4;
        }
        if (this.r[s2] < this.q[s2] / 2 && this.r[s] - this.r[s2] > 10) {
            return 0;
        }
        return 1;
    }

    public final void O(byte by) {
        int n = -1;
        short s = 0;
        byte by2 = 0;
        for (int i = 0; i < this.c[by]; ++i) {
            for (int j = 0; j < this.b[by]; ++j) {
                byte by3 = this.a[by][j];
                for (int k = 0; k < this.g[by3]; ++k) {
                    short s2 = this.b[by3][k];
                    if (this.t[s2] + this.s[s2] + this.u[s2] > n) {
                        n = this.t[s2] + this.s[s2] + this.u[s2];
                        s = s2;
                        by2 = by3;
                    }
                    if (s2 != this.a[by][i]) continue;
                    this.a(by, by3, s2);
                    return;
                }
            }
        }
        this.a(by, by2, s);
    }

    public final int d(int n) {
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
        if (this.a() % 100 > n2) {
            this.P = (byte)-1;
        }
        return n;
    }

    public final void c(byte by, short[] sArray, byte by2) {
        byte by3;
        byte by4 = 0;
        short s = sArray[0];
        for (by3 = 1; by3 < by2; by3 = (byte)(by3 + 1)) {
            if (this.v[s] >= this.v[sArray[by3]]) continue;
            s = sArray[by3];
            by4 = by3;
        }
        this.c[by] = s;
        for (by3 = by4; by3 > 0; by3 = (byte)(by3 - 1)) {
            sArray[by3] = sArray[by3 - 1];
        }
        sArray[0] = s;
    }

    public final void P(byte by) {
        if (this.b[by][0] == 0) {
            this.a(this.g, this.b[by], (int)this.g[by]);
            this.b(by, this.b[by], this.g[by]);
            return;
        }
        this.a(by, this.b[by], (int)this.g[by]);
    }

    public final void d(short[] sArray, byte by) {
        short s = sArray[0];
        byte by2 = -1;
        int n = 0;
        for (byte by3 = 0; by3 < by; by3 = (byte)(by3 + 1)) {
            if (sArray[by3] == this.b[this.g]) {
                s = sArray[0];
                sArray[0] = sArray[by3];
                sArray[by3] = s;
                return;
            }
            if (this.t[sArray[by3]] <= by2) continue;
            s = sArray[by3];
            n = by3;
            by2 = this.t[s];
        }
        sArray[n] = sArray[0];
        sArray[0] = s;
    }

    public final byte c(short[] sArray, int n) {
        byte by;
        byte[] byArray = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int n2 = -1 * (this.h[this.h] + this.g[this.h] * 200);
        byte by2 = 0;
        byte by3 = 0;
        n = this.a() % n;
        n *= 1000;
        for (by = this.g[this.g]; by > 1; by = (byte)(by - 1)) {
            int n3 = 0;
            int n4 = this.a() % by + 1;
            while ((n3 += byArray[by3]) != n4) {
                if (++by3 != this.g[this.g]) continue;
                by3 = 0;
            }
            sArray[by2] = this.b[this.g][by3];
            this.b[this.g][by3] = 0;
            n2 += this.k[sArray[by2]];
            by2 = (byte)(by2 + 1);
            byArray[by3] = 0;
            if ((n2 += 200) < n) continue;
        }
        this.g[this.g] = by;
        this.P(this.g);
        this.d(sArray, by2);
        return by2;
    }

    public final void Q(byte by) {
        this.G = new byte[36];
        this.H = new byte[36];
        this.r = 0;
        this.s = 0;
        for (byte by2 = 0; by2 < this.b[by]; by2 = (byte)(by2 + 1)) {
            if (this.s(this.a[by][by2])) {
                this.G[this.r] = this.a[by][by2];
                this.r = (byte)(this.r + 1);
                continue;
            }
            this.H[this.s] = this.a[by][by2];
            this.s = (byte)(this.s + 1);
        }
    }

    public final int f(byte by, byte by2) {
        int n = this.e[by] / 4 * 1000 / 45;
        int n2 = n >= this.h[by] ? this.h[by] - this.h[by2] : n - this.h[by2];
        return n2 += (this.g[by] - this.g[by2]) * 200;
    }

    public final int j() {
        int n = 999;
        boolean bl = this.o();
        this.P = (byte)-1;
        for (byte by = 0; by < this.r; by = (byte)(by + 1)) {
            byte by2 = this.G[by];
            if (this.g[by2] <= 2) continue;
            for (byte by3 = 0; by3 < this.n[by2]; by3 = (byte)(by3 + 1)) {
                int n2;
                byte by4 = this.b[by2][by3];
                if (this.b[by2] == this.b[by4] || bl && this.b[by4] == this.a[this.d] || (n2 = this.e(by2, by4)) <= n) continue;
                n = n2;
                this.g = by2;
                this.h = by4;
            }
        }
        if (n >= 1000) {
            this.P = (byte)3;
        }
        return n;
    }

    public final void bw() {
        int n = 0;
        if (this.a() % 3 > 1) {
            return;
        }
        for (byte by = 0; by < this.r; by = (byte)(by + 1)) {
            byte by2 = this.G[by];
            int n2 = this.g[by2] * 1000 - this.h[by2];
            if (n2 <= 0 || this.d[by2] < 50 && this.i[by2] <= 0) continue;
            int n3 = (this.d[by2] - 50) * 5;
            if (n2 > n3) {
                n2 = n3;
            }
            if (n2 < n) continue;
            this.g = by2;
            n = n2;
        }
        if (n > 0) {
            this.P = (byte)2;
        }
    }

    public final int b(byte by) {
        int n = -20000;
        for (byte by2 = 0; by2 < this.n[by]; by2 = (byte)(by2 + 1)) {
            byte by3 = this.b[by][by2];
            int n2 = this.h[by3] + this.g[by3] * 200 - this.h[by] - this.g[by] * 200;
            if (n >= n2) continue;
            n = n2;
        }
        return n;
    }

    public final int b(byte by, int n) {
        byte by2 = 1;
        int n2 = n - 1999;
        int n3 = 0;
        for (byte by3 = 0; by3 < this.n[by]; by3 = (byte)(by3 + 1)) {
            byte by4 = this.b[by][by3];
            if (this.b[by4] != this.b[by] || this.g[by4] <= 1) continue;
            if (!this.s(by4)) {
                if (this.g[by4] <= by2) continue;
                this.h = by4;
                by2 = this.g[by4];
                continue;
            }
            if (by2 != 1 || n <= 0 || (n3 = this.b(by4)) >= n2) continue;
            this.h = by4;
            n2 = n3;
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

    public final int k() {
        int n = -20000;
        int n2 = 0;
        int n3 = 0;
        this.g = 0;
        this.h = 0;
        if (this.a() % 3 > 1) {
            return 0;
        }
        for (byte by = 0; by < this.r; by = (byte)(by + 1)) {
            int n4;
            byte by2 = this.G[by];
            if (this.g[by2] == 10 || (n4 = this.b(by2)) <= n || (n3 = this.b(by2, n4)) == 0) continue;
            this.g = by2;
            this.P = 1;
            n = n4;
            n2 = n3;
        }
        return n2;
    }

    public final void w(int n) {
        if (n < 50) {
            try {
                for (int i = 0; i < n; ++i) {
                    byte by = this.h;
                    this.g[by] = (byte)(this.g[by] - 1);
                    this.b[this.g][this.g[this.g]] = this.b[this.h][this.g[this.h]];
                    short s = this.k[this.b[this.g][this.g[this.g]]];
                    byte by2 = this.g;
                    this.h[by2] = (short)(this.h[by2] + s);
                    byte by3 = this.h;
                    this.h[by3] = (short)(this.h[by3] - s);
                    this.b[this.h][this.g[this.h]] = 0;
                    byte by4 = this.g;
                    this.g[by4] = (byte)(this.g[by4] + 1);
                    if (this.g[this.g] < 10) continue;
                    return;
                }
                return;
            }
            catch (Exception exception) {
                System.out.println("move error" + exception.toString());
                return;
            }
        }
        try {
            short s;
            for (int i = 0; i < n && this.g[this.h] > 1 && this.g[this.g] < 10; i += 200 + s) {
                byte by = this.h;
                this.g[by] = (byte)(this.g[by] - 1);
                this.b[this.g][this.g[this.g]] = this.b[this.h][this.g[this.h]];
                s = this.k[this.b[this.g][this.g[this.g]]];
                byte by5 = this.g;
                this.h[by5] = (short)(this.h[by5] + s);
                byte by6 = this.h;
                this.h[by6] = (short)(this.h[by6] - s);
                this.b[this.h][this.g[this.h]] = 0;
                byte by7 = this.g;
                this.g[by7] = (byte)(this.g[by7] + 1);
            }
            return;
        }
        catch (Exception exception) {
            System.out.println("move error2" + exception.toString());
            try {
                this.wait();
                return;
            }
            catch (Exception exception2) {
                return;
            }
        }
    }

    public final void bx() {
        byte by = 100;
        if (this.a() % 2 > 0) {
            return;
        }
        for (byte by2 = 0; by2 < this.b[this.e]; by2 = (byte)(by2 + 1)) {
            byte by3 = this.a[this.e][by2];
            if (this.d[by3] < 200 && this.j[by3] == 0) continue;
            for (byte by4 = 0; by4 < this.g[by3]; by4 = (byte)(by4 + 1)) {
                if (this.v[this.b[by3][by4]] >= by) continue;
                this.g = by3;
                this.a = this.b[by3][by4];
                by = this.v[this.b[by3][by4]];
            }
        }
        if (by >= 70 || by > 35 && this.a() % 2 > 0) {
            return;
        }
        this.P = (byte)8;
    }

    public final int c(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        short s = -1;
        short s2 = -1;
        for (byte by4 = 0; by4 < this.n[by]; by4 = (byte)(by4 + 1)) {
            byte by5 = this.b[by][by4];
            if (this.b[by5] != this.b[by]) continue;
            if (!this.s(by5)) {
                if (this.e[by5] <= s) continue;
                s = this.e[by5];
                by2 = by5;
                continue;
            }
            if (s >= 1000 || this.e[by5] <= s2) continue;
            s2 = this.e[by5];
            by3 = by5;
        }
        if (s >= 1000) {
            this.h = by2;
            return 0;
        }
        if (s2 - s >= 500 && s2 >= 600) {
            this.h = by3;
            return 1;
        }
        if (s >= 400) {
            this.h = by2;
            return 0;
        }
        return -1;
    }

    public final int d(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        short s = -1;
        short s2 = -1;
        for (byte by4 = 0; by4 < this.n[by]; by4 = (byte)(by4 + 1)) {
            byte by5 = this.b[by][by4];
            if (this.b[by5] != this.b[by]) continue;
            if (!this.s(by5)) {
                if (this.d[by5] <= s) continue;
                s = this.d[by5];
                by2 = by5;
                continue;
            }
            if (s >= 1000 || this.d[by5] <= s2) continue;
            s2 = this.d[by5];
            by3 = by5;
        }
        if (s >= 1000) {
            this.h = by2;
            return 0;
        }
        if (s2 - s >= 500 && s2 >= 600) {
            this.h = by3;
            return 1;
        }
        if (s >= 400) {
            this.h = by2;
            return 0;
        }
        return -1;
    }

    public final int e(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        int n = -1;
        int n2 = -1;
        for (byte by4 = 0; by4 < this.n[by]; by4 = (byte)(by4 + 1)) {
            byte by5 = this.b[by][by4];
            if (this.b[by5] != this.b[by]) continue;
            if (!this.s(by5)) {
                if (this.d[by5] + this.e[by5] <= n) continue;
                n = this.d[by5] + this.e[by5];
                by2 = by5;
                continue;
            }
            if (n >= 1500 || this.d[by5] + this.e[by5] <= n2) continue;
            n2 = this.d[by5] + this.e[by5];
            by3 = by5;
        }
        if (n >= 1500) {
            this.h = by2;
            return 0;
        }
        if (n2 - n >= 800 && n2 >= 1500) {
            this.h = by3;
            return 1;
        }
        if (n >= 800) {
            this.h = by2;
            return 0;
        }
        return -1;
    }

    public final int f(byte by) {
        byte by2 = 0;
        byte by3 = 0;
        int n = -1;
        int n2 = -1;
        for (byte by4 = 0; by4 < this.n[by]; by4 = (byte)(by4 + 1)) {
            byte by5 = this.b[by][by4];
            if (this.b[by5] != this.b[by]) continue;
            if (!this.s(by5)) {
                if (this.d[by5] + this.e[by5] <= n) continue;
                n = this.d[by5] + this.e[by5];
                by2 = by5;
                continue;
            }
            if (n >= 2000 || this.d[by5] + this.e[by5] <= n2) continue;
            n2 = this.d[by5] + this.e[by5];
            by3 = by5;
        }
        if (n >= 2000) {
            this.h = by2;
            return 0;
        }
        if (n2 - this.d[by] - this.e[by] >= 1000 && n2 >= 3000) {
            this.h = by3;
            return 1;
        }
        if (n >= 1000) {
            this.h = by2;
            return 0;
        }
        return -1;
    }

    public final int l() {
        int n = 0;
        short s = 10000;
        int n2 = 0;
        this.g = 0;
        this.h = 0;
        if (this.a() % 2 > 1) {
            return 0;
        }
        for (byte by = 0; by < this.r; by = (byte)(by + 1)) {
            int n3;
            byte by2 = this.G[by];
            if (this.e[by2] < 200 && this.e[by2] < s && (n3 = this.c(by2)) >= 0) {
                this.g = by2;
                s = this.e[by2];
                n2 = 4;
                n = n3;
                continue;
            }
            if (n2 < 4 && this.d[by2] < 200 && this.d[by2] < s && (n3 = this.d(by2)) >= 0) {
                this.g = by2;
                s = this.d[by2];
                n2 = 3;
                n = n3;
                continue;
            }
            n3 = this.d[by2] + this.e[by2];
            if (n3 >= s) continue;
            if (n2 < 3 && n3 < 1000 && (n3 = this.e(by2)) >= 0) {
                this.g = by2;
                s = (short)(this.d[by2] + this.e[by2]);
                n2 = 2;
                n = n3;
                continue;
            }
            n3 = this.d[by2] + this.e[by2];
            if (n2 >= 2 || n3 >= 4000 || (n3 = this.f(by2)) < 0) continue;
            this.g = by2;
            s = (short)(this.d[by2] + this.e[by2]);
            n2 = 1;
            n = n3;
        }
        if (n2 > 2 || n2 == 2 && this.a() % 2 > 0 || n2 == 1 && this.a() % 5 > 3) {
            this.P = (byte)12;
        }
        return n;
    }

    public final void by() {
        int n;
        int n2 = 0;
        int n3 = 0;
        byte by = 0;
        try {
            by = this.a() % 10 > 6 && this.s > 0 || this.r <= 0 ? this.H[this.a() % this.s] : this.G[this.a() % this.r];
            n2 = this.d[by] >= 50 && (this.f[by] < 999 || this.g[by] < 999 || this.a[by] < 990000 || this.f[by] < 99) ? 460 : 220;
        }
        catch (Exception exception) {
            System.out.println("other first");
        }
        for (n = 0; n < 10; n = (int)((byte)(n + 1))) {
            n3 = this.a() % n2;
            if (n3 < 40) {
                if (!this.t(by)) continue;
                break;
            }
            if (n3 < 120) {
                if (!this.u(by)) continue;
                break;
            }
            if (n3 < 220) {
                this.K(by);
                break;
            }
            if (n3 >= 460) continue;
            this.M(by);
            break;
        }
        if (n == 10) {
            this.K(by);
        }
    }

    public final void bz() {
        if (this.a() % 3 > 1) {
            return;
        }
        for (byte by = 0; by < this.r; by = (byte)(by + 1)) {
            byte by2 = this.G[by];
            if ((this.k[by2] & 4) != 4 || this.d[by2] < 100) continue;
            for (byte by3 = 0; by3 < this.g[by2]; by3 = (byte)(by3 + 1)) {
                if (this.q[this.b[by2][by3]] - 40 <= this.r[this.b[by2][by3]]) continue;
                this.P = (byte)19;
                this.g = by2;
                this.a = this.b[by2][by3];
                return;
            }
        }
    }
}

