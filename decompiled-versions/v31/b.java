/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.lcdui.game.GameCanvas
 *  javax.microedition.media.Manager
 *  javax.microedition.media.MediaException
 *  javax.microedition.media.Player
 *  javax.microedition.media.control.VolumeControl
 */
import java.io.InputStream;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import sango.Sango;

public final class b
extends GameCanvas {
    private byte aC = 0;
    private boolean aD = false;
    private Image[] aE = new Image[10];
    private Image aF;
    private Image aG;
    private Image aH;
    private Image aI;
    private Image aJ;
    private Image aK;
    private Image aL;
    private Image aM;
    public byte a = 0;
    public a b;
    private int aN;
    private int aO;
    private byte aP = 0;
    private byte aQ = 0;
    private byte aR = 0;
    private boolean aS = false;
    private boolean aT = false;
    private boolean aU = false;
    private byte aV = 0;
    private byte aW = 0;
    private byte aX = 0;
    private byte aY = 0;
    private byte aZ = 0;
    public int[] c = new int[]{0xFFFFFF, 0xFFFF00, 16741219, 3250840, 10232295, 11339784, 16753920, 2181863, 15147367, 2211047, 9299745, 0x636363};
    public Font d = Font.getFont((int)0, (int)0, (int)8);
    public int e = this.d.getHeight();
    public int f = this.d.charWidth('\u56fd');
    public String g = "";
    private String ba = null;
    private String bb = null;
    private String bc = "V3.1";
    public Player h = null;
    public VolumeControl i = null;
    public byte j = 0;
    public byte k = 1;
    public boolean l = false;
    public byte[] m;
    private byte bd;
    private byte be;
    private byte bf;
    private int bg;
    private byte[][] bh;
    private byte bi;
    private boolean bj;
    private short bk;
    private short[] bl;
    private byte bm;
    public boolean n;
    private byte bn;
    private byte bo;
    private boolean bp;
    private boolean bq;
    private boolean br;
    private byte bs;
    private byte[] bt;
    private short bu;
    private byte bv;
    private byte[][] bw;
    private short bx;
    private boolean by;
    private short[] bz;
    private short[] bA;
    private short bB;
    private boolean bC;
    private boolean bD;
    private int bE;
    private boolean bF;
    public Sango o;
    public byte p;
    public boolean q;
    public boolean r;
    public byte s;
    public byte t;
    public byte u;
    public byte v;
    public byte w;
    public byte x;
    public byte y;
    public byte z;
    public byte A;
    public short B;
    public short C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public byte M;
    public byte N;
    public byte O;
    public boolean P;
    public boolean Q;
    public byte R;
    public boolean S;
    public boolean T;
    public boolean U;
    public String V;
    public byte W;
    private short bG;
    private short bH;
    private boolean bI;
    public short X;
    public short Y;
    public boolean Z;
    private boolean bJ;
    public byte aa;
    public short ab;
    public short ac;
    public short ad;
    public short ae;
    public byte af;
    private byte bK;
    public boolean ag;
    public int ah;
    public int ai;
    public boolean aj;
    public boolean ak;
    public boolean al;
    private byte bL;
    private boolean bM;
    private boolean bN;
    private byte bO;
    public byte am;
    public byte an;
    public byte ao;
    public byte ap;
    public byte aq;
    public boolean ar;
    public byte as;
    public boolean at;
    public byte au;
    public byte av;
    public byte aw;
    public boolean ax;
    public String ay;
    public byte az;
    public boolean aA;
    public int aB;
    private boolean bP;
    private boolean bQ;
    private byte bR;
    private Object bS;
    private byte[] bT;
    private int bU;
    private int bV;
    private int bW;
    private int bX;
    private int bY;
    private boolean bZ;

    public b(Sango sango) {
        super(false);
        byte[] byArray = new byte[3];
        byArray[1] = 1;
        this.m = byArray;
        this.bd = 0;
        this.be = 0;
        this.bf = (byte)4;
        this.bg = 8;
        byte[][] byArrayArray = new byte[34][];
        byArrayArray[0] = new byte[30];
        byArrayArray[1] = new byte[30];
        byArrayArray[2] = new byte[30];
        byte[] byArray2 = new byte[30];
        byArray2[23] = 29;
        byArrayArray[3] = byArray2;
        byte[] byArray3 = new byte[30];
        byArray3[2] = 3;
        byArrayArray[4] = byArray3;
        byArrayArray[5] = new byte[30];
        byte[] byArray4 = new byte[30];
        byArray4[14] = 17;
        byArray4[21] = 28;
        byArrayArray[6] = byArray4;
        byte[] byArray5 = new byte[30];
        byArray5[1] = 1;
        byArray5[18] = 22;
        byArrayArray[7] = byArray5;
        byte[] byArray6 = new byte[30];
        byArray6[20] = 32;
        byArrayArray[8] = byArray6;
        byArrayArray[9] = new byte[30];
        byte[] byArray7 = new byte[30];
        byArray7[17] = 20;
        byArrayArray[10] = byArray7;
        byte[] byArray8 = new byte[30];
        byArray8[5] = 5;
        byArray8[24] = 30;
        byArrayArray[11] = byArray8;
        byte[] byArray9 = new byte[30];
        byArray9[12] = 10;
        byArray9[15] = 13;
        byArray9[20] = 25;
        byArrayArray[12] = byArray9;
        byte[] byArray10 = new byte[30];
        byArray10[9] = 9;
        byArrayArray[13] = byArray10;
        byte[] byArray11 = new byte[30];
        byArray11[22] = 34;
        byArrayArray[14] = byArray11;
        byte[] byArray12 = new byte[30];
        byArray12[18] = 19;
        byArrayArray[15] = byArray12;
        byte[] byArray13 = new byte[30];
        byArray13[8] = 6;
        byArrayArray[16] = byArray13;
        byte[] byArray14 = new byte[30];
        byArray14[15] = 14;
        byArray14[21] = 23;
        byArrayArray[17] = byArray14;
        byArrayArray[18] = new byte[30];
        byte[] byArray15 = new byte[30];
        byArray15[13] = 12;
        byArrayArray[19] = byArray15;
        byArrayArray[20] = new byte[30];
        byte[] byArray16 = new byte[30];
        byArray16[9] = 8;
        byArray16[23] = 31;
        byArrayArray[21] = byArray16;
        byte[] byArray17 = new byte[30];
        byArray17[15] = 15;
        byArray17[18] = 21;
        byArrayArray[22] = byArray17;
        byte[] byArray18 = new byte[30];
        byArray18[21] = 26;
        byArrayArray[23] = byArray18;
        byte[] byArray19 = new byte[30];
        byArray19[26] = 35;
        byArrayArray[24] = byArray19;
        byArrayArray[25] = new byte[30];
        byte[] byArray20 = new byte[30];
        byArray20[5] = 4;
        byArrayArray[26] = byArray20;
        byte[] byArray21 = new byte[30];
        byArray21[16] = 18;
        byArray21[21] = 27;
        byArrayArray[27] = byArray21;
        byte[] byArray22 = new byte[30];
        byArray22[25] = 33;
        byArrayArray[28] = byArray22;
        byte[] byArray23 = new byte[30];
        byArray23[12] = 11;
        byArrayArray[29] = byArray23;
        byArrayArray[30] = new byte[30];
        byte[] byArray24 = new byte[30];
        byArray24[9] = 7;
        byArray24[21] = 24;
        byArrayArray[31] = byArray24;
        byte[] byArray25 = new byte[30];
        byArray25[4] = 2;
        byArray25[16] = 16;
        byArray25[28] = 36;
        byArrayArray[32] = byArray25;
        byArrayArray[33] = new byte[30];
        this.bh = byArrayArray;
        this.bi = 0;
        this.bj = false;
        this.bk = 0;
        this.bl = new short[10];
        this.bm = 0;
        this.n = false;
        this.bn = 0;
        this.bo = 0;
        this.bp = false;
        this.bq = false;
        this.br = true;
        this.bs = 0;
        this.bt = new byte[4];
        this.bu = 0;
        this.bv = 0;
        this.bw = new byte[10][4];
        this.bx = 0;
        this.by = false;
        this.bz = new short[3];
        this.bA = new short[3];
        this.bB = 0;
        this.bC = false;
        this.bD = false;
        this.bE = 0;
        this.bF = false;
        this.p = 0;
        this.q = false;
        this.r = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = (short)3;
        this.C = (short)3;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = 0;
        this.N = (byte)16;
        this.O = 0;
        this.P = false;
        this.Q = false;
        this.R = 0;
        this.S = false;
        this.T = false;
        this.U = false;
        this.W = 0;
        this.Z = false;
        this.bJ = false;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 1;
        this.bK = 0;
        this.ag = false;
        this.aj = false;
        this.ak = false;
        this.al = false;
        this.bL = 0;
        this.bM = false;
        this.bN = false;
        this.bO = 0;
        this.am = 0;
        this.an = 1;
        this.ao = 0;
        this.ap = 0;
        this.aq = 0;
        this.ar = true;
        this.as = 0;
        this.au = 0;
        this.av = 0;
        this.aw = 0;
        this.ay = "\u8bf7\u9009\u62e9\u6267\u884c\u547d\u4ee4";
        this.az = (byte)-1;
        this.aA = false;
        this.aB = 210;
        this.bP = false;
        this.bQ = false;
        this.bS = new Object();
        this.bT = new byte[30720];
        this.bY = 1;
        this.bZ = false;
        this.setFullScreenMode(true);
        this.aN = 240;
        this.aO = 320;
        this.o = sango;
    }

    private final void c(int n) {
        int n2 = 0;
        while (n2 < 10) {
            this.aE[n2] = null;
            ++n2;
        }
        System.gc();
    }

    private final void q() {
        this.aP = 0;
        this.aT = false;
        this.aV = 0;
        this.aW = 0;
    }

    public final synchronized void a(byte by) {
        short s;
        if (this.d()) {
            return;
        }
        this.a("curScreenID:" + this.aC);
        switch (this.aC) {
            case 0: {
                this.c(1);
                break;
            }
            case 1: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                this.c(2);
                break;
            }
            case 5: {
                this.c(3);
                this.bC = false;
                this.bj = false;
                break;
            }
            case 6: {
                this.c(4);
                this.aU = false;
                this.aQ = 0;
                this.aR = 0;
            }
            case 7: {
                this.c(1);
                this.bi = 0;
                this.bj = false;
                this.bk = 0;
                this.n = false;
                break;
            }
            case 8: {
                this.c(3);
                s = 0;
                while (s < 4) {
                    this.bt[s] = 0;
                    s = (byte)(s + 1);
                }
                break;
            }
            case 9: {
                s = 0;
                while (s < 3) {
                    this.bz[s] = 0;
                    s = (byte)(s + 1);
                }
                this.c(4);
            }
            case 10: {
                this.c(1);
                break;
            }
            case 11: {
                this.c(1);
                break;
            }
            case 12: {
                this.c(6);
                s = 0;
                while (s < 4) {
                    this.bt[s] = 0;
                    s = (byte)(s + 1);
                }
                this.bB = 0;
                break;
            }
            case 13: {
                this.c(3);
                break;
            }
            case 14: {
                this.c(5);
                this.bG = 0;
                this.bH = 0;
                break;
            }
            case 15: {
                this.c(1);
                break;
            }
            case 16: {
                this.c(5);
                s = 0;
                while (s < 4) {
                    this.bt[s] = 0;
                    s = (byte)(s + 1);
                }
                this.aA = false;
                break;
            }
            case 17: {
                this.c(2);
                this.aX = 0;
                this.aA = false;
                break;
            }
            case 18: {
                break;
            }
            case 19: {
                this.c(2);
                break;
            }
            case 20: {
                this.c(7);
                break;
            }
            case 21: {
                this.c(10);
                break;
            }
            case 22: {
                this.c(3);
                this.am = 0;
                this.an = 1;
                this.ao = 0;
                this.ap = 0;
                this.aq = 0;
                this.ar = true;
                this.as = 0;
                this.at = false;
                this.au = 0;
                this.av = 0;
                this.aw = 0;
                this.ax = false;
                this.az = (byte)-1;
                this.aS = false;
            }
        }
        this.aC = by;
        this.a("nextScreenID:" + this.aC);
        switch (this.aC) {
            case 0: {
                this.aE[0] = this.k(300);
                break;
            }
            case 1: {
                this.aE[0] = this.k(106);
                this.aE[1] = this.k(107);
                if (this.aF == null) {
                    this.aF = this.k(109);
                }
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (!this.l) break;
                if (this.j == 0) {
                    this.b(1);
                    break;
                }
                if (this.j == 1) break;
                this.p();
                this.b(1);
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                this.aE[0] = this.k(1);
                this.aE[1] = this.k(2);
                this.ab();
                if (!this.l) break;
                if (this.j == 0) {
                    this.b(1);
                    break;
                }
                if (this.j == 2) break;
                this.p();
                this.b(2);
                break;
            }
            case 5: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aJ == null) {
                    this.aJ = this.k(303);
                }
                this.aE[3] = this.k(10);
                if (this.b.j[this.b.ax] > 0) {
                    this.aE[0] = this.k(15);
                    this.aE[1] = this.b(this.b.B[this.b.ax][0]);
                    this.aE[2] = this.aE[1];
                    if (this.aH != null) break;
                    this.aH = this.k(14);
                    break;
                }
                this.aE[1] = this.b((short)0);
                this.bj = true;
                this.g = "\u57ce\u5e02\u57fa\u672c\u4fe1\u606f";
                this.aV = 1;
                break;
            }
            case 6: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aJ == null) {
                    this.aJ = this.k(303);
                }
                this.aE[0] = this.b(this.b.k[this.b.aw]);
                this.aE[1] = this.k(10);
                this.aE[2] = this.k(12);
                this.aE[3] = this.k(307);
                this.aE[4] = this.k(15);
                if (!this.l) break;
                if (this.j == 0) {
                    this.b(1);
                    break;
                }
                if (this.j == 2) break;
                this.p();
                this.b(2);
                break;
            }
            case 7: {
                this.aE[0] = this.k(13);
                if (this.b.aN) {
                    this.bl = this.b.B[this.b.ay];
                    this.bm = this.b.s[this.b.ay];
                } else if (this.b.bM == 3 && this.b.bN == 5) {
                    this.n = true;
                    this.bl = this.b.aB;
                    this.bm = this.b.aC;
                } else {
                    this.bl = this.b.aO;
                    this.bm = this.b.aP;
                }
                this.bn = (byte)((this.bm - 1) / 10 + 1);
                this.bo = 0;
                break;
            }
            case 8: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                this.aE[0] = this.k(89);
                this.aE[1] = this.k(16);
                this.aE[2] = this.k(96);
                this.bp = false;
                this.bq = false;
                this.br = true;
                this.bs = 0;
                this.bu = 0;
                this.bv = 0;
                this.bx = 0;
                s = 0;
                while (s < 4) {
                    this.bt[s] = 0;
                    s = (byte)(s + 1);
                }
                this.g = "\u5f81100\u5175 \u9700 20\u91d1";
                this.bn = (byte)((this.b.s[this.b.aw] - 1) / 10 + 1);
                this.bo = 0;
                break;
            }
            case 9: {
                this.aE[1] = this.k(16);
                this.aE[2] = this.k(309);
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                this.g = "\u57ce\u5185\u91d1\u5b50:" + this.b.m[this.b.aw] + "\u7cae\u98df: " + this.b.n[this.b.aw] + "\u5b9d:" + this.b.x[this.b.aw];
                this.bA[0] = this.b.m[this.b.aw] + this.b.m[this.b.ax] > 9999 ? (short)(9999 - this.b.m[this.b.ax]) : this.b.m[this.b.aw];
                this.bA[1] = this.b.n[this.b.aw] + this.b.n[this.b.ax] > 9999 ? (short)(9999 - this.b.n[this.b.ax]) : this.b.n[this.b.aw];
                this.bA[2] = this.b.x[this.b.aw] + this.b.x[this.b.ax] > 99 ? (short)(99 - this.b.x[this.b.ax]) : this.b.x[this.b.aw];
                break;
            }
            case 10: {
                if (this.aJ == null) {
                    this.aJ = this.k(303);
                }
                this.aE[0] = this.b(this.b.aL);
                this.g = "\u6267\u884c\u7ed3\u679c";
                break;
            }
            case 11: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aJ == null) {
                    this.aJ = this.k(303);
                }
                this.aE[0] = this.b(this.b.az);
                this.g = "\u6267\u884c\u7ed3\u679c";
                break;
            }
            case 12: {
                this.bB = 0;
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                this.aE[1] = this.k(16);
                this.aE[4] = this.b(this.b.az);
                this.aE[5] = this.k(309);
                this.g = "\u541b\u4e3b\u7292\u8d4f\u5c06\u58eb";
                break;
            }
            case 13: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                this.aE[1] = this.b(this.b.az);
                this.g = "\u5c06\u6d88\u8017\u91d1\u5b50" + this.b.aQ;
                break;
            }
            case 14: {
                this.aE[0] = this.k(16);
                this.aE[1] = this.k(308);
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                this.aE[4] = this.k(309);
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                this.bA[0] = this.b.m[this.b.aw];
                this.bA[1] = this.b.n[this.b.aw];
                this.g = "\u57ce\u5185\u91d1\u5b50:" + this.bA[0] + "\u7cae\u98df: " + this.bA[1];
                break;
            }
            case 15: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                s = this.b.G[this.b.b[this.b.aS[0]]];
                this.aE[0] = this.b(s);
                this.g = "\u8bf7\u9009\u62e9\u52bf\u529b\u8fdb\u884c\u64cd\u4f5c";
                break;
            }
            case 16: {
                int n = 0;
                while (n < 4) {
                    this.bt[n] = 0;
                    n = (byte)(n + 1);
                }
                this.bD = true;
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                this.aE[1] = this.k(16);
                this.aE[2] = this.k(308);
                this.aE[3] = this.k(309);
                if (this.aA) {
                    this.bA[0] = this.b.ba * 4 / 3 + this.b.bb > 9999 ? (short)((9999 - this.b.bb) * 3 / 4) : this.b.ba;
                    this.bA[1] = this.b.bb * 3 / 4 + this.b.ba > 9999 ? (short)((9999 - this.b.ba) * 4 / 3) : this.b.bb;
                    break;
                }
                this.bA[0] = this.b.m[this.b.aw] * 4 / 3 + this.b.n[this.b.aw] > 9999 ? (short)((9999 - this.b.n[this.b.aw]) * 3 / 4) : this.b.m[this.b.aw];
                this.bA[1] = this.b.n[this.b.aw] * 3 / 4 + this.b.m[this.b.aw] > 9999 ? (short)((9999 - this.b.m[this.b.aw]) * 4 / 3) : this.b.n[this.b.aw];
                break;
            }
            case 17: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                this.aE[0] = this.k(93);
                this.aE[1] = this.k(16);
                this.g = "\u8bf7\u9009\u62e9\u8d2d\u4e70\u7684\u5175\u5668";
                break;
            }
            case 18: {
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                if (this.aF == null) {
                    this.aF = this.k(109);
                }
                this.g = "\u8bf7\u9009\u62e9\u8bb0\u5f55";
                break;
            }
            case 19: {
                if (this.b.aQ < 14) {
                    this.aE[1] = this.k(11);
                } else if (this.aG == null) {
                    this.aG = this.k(20);
                }
                this.e();
                break;
            }
            case 20: {
                this.r();
                this.aE[0] = this.k(2);
                this.aE[4] = this.k(31);
                this.aE[5] = this.k(51);
                this.aE[6] = this.k(52);
                if (!this.l) break;
                if (this.j == 0) {
                    this.b(1);
                    break;
                }
                if (this.j == 3) break;
                this.p();
                this.b(3);
                break;
            }
            case 21: {
                this.bK = 0;
                this.bO = this.d(this.b.bF);
                this.aE[0] = this.k(98);
                if (this.bO == 3) {
                    this.aE[8] = this.k(306);
                    this.bJ = true;
                }
                this.aE[9] = this.k(56);
                this.aE[4] = this.k(63);
                this.aE[2] = this.k(58);
                this.aE[1] = this.k(305);
                this.aE[5] = this.b(this.X);
                this.aE[6] = this.b(this.Y);
                break;
            }
            case 22: {
                this.aE[0] = this.k(59);
                this.aE[1] = this.k(60);
                this.aE[2] = this.k(304);
                this.aE[3] = this.b(this.X);
                break;
            }
            case 23: {
                this.b.k();
                this.aE[1] = this.k(11);
                if (this.b.aQ == 1) {
                    this.aE[0] = this.k(132);
                    this.g = String.valueOf(this.b.H[this.b.b[this.b.at]]) + c.k[this.b.aQ];
                    this.bP = true;
                    break;
                }
                this.aE[0] = this.k(131);
                this.g = String.valueOf(this.b.H[this.b.b[this.b.at]]) + c.k[this.b.aQ];
                this.bR = this.b.cL;
                break;
            }
            case 24: {
                if (this.aI == null) {
                    this.aI = this.k(90);
                }
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                this.g = "\u7cfb\u7edf\u73af\u5883\u8bbe\u7f6e";
                if (this.l) {
                    this.m[0] = 0;
                    break;
                }
                this.m[0] = 1;
                break;
            }
            case 25: {
                if (this.aK == null) {
                    this.aK = this.k(302);
                }
                this.g = "\u4e0a\u4e0b\u952e\u6eda\u52a8\u5c4f\u5e55";
            }
        }
        this.a = 0;
        this.q();
        this.bZ = false;
    }

    private final byte d(byte by) {
        byte by2 = 0;
        switch (by) {
            case 0: 
            case 1: 
            case 3: 
            case 4: {
                by2 = 0;
                break;
            }
            case 2: {
                by2 = 3;
                break;
            }
            case 6: {
                by2 = 1;
                break;
            }
            case 5: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                by2 = 2;
            }
        }
        return by2;
    }

    public final void a() {
        this.a("call initCenterPos()");
        this.t = (byte)(this.b.bo[0] - 3);
        if (this.t < 0) {
            this.t = 0;
            return;
        }
        if (this.t > 16) {
            this.t = (byte)16;
        }
    }

    public final void b() {
        this.a("call clearWarFlag()");
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = false;
        this.q = false;
        this.Q = false;
        this.r = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.B = (short)3;
        this.C = (short)3;
        this.ad = 0;
        this.ae = 0;
        this.aW = 0;
        this.M = 0;
        this.R = 0;
        this.S = false;
        this.T = false;
        this.U = false;
        this.W = 0;
        this.Z = false;
        this.bJ = false;
    }

    public final void c() {
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = true;
        this.K = false;
        this.L = false;
    }

    private final void r() {
        this.aJ = null;
        this.aK = null;
        this.c(10);
    }

    private final void e(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        graphics.drawImage(this.aE[1], 137, 97, 20);
        graphics.setColor(0);
        graphics.drawRect(137, 97, this.aE[1].getWidth(), this.aE[1].getHeight());
        graphics.setColor(0xFFFFFF);
        graphics.drawRegion(this.aH, 58, 0, 29, 17, 0, 70, 100, 17);
        graphics.drawString(this.b.H[this.b.az], 113, 100, 17);
        switch (this.b.aR) {
            case 0: {
                graphics.drawString("\u7cae\u98df: " + this.b.n[this.b.aw], 60, 120, 20);
                graphics.drawString("\u519c\u4e1a: " + this.b.o[this.b.aw], 60, 140, 20);
                break;
            }
            case 1: {
                graphics.drawString("\u91d1\u5b50: " + this.b.m[this.b.aw], 60, 120, 20);
                graphics.drawString("\u5de5\u4e1a: " + this.b.p[this.b.aw], 60, 140, 20);
                break;
            }
            case 2: {
                graphics.drawString("\u7edf\u6cbb: " + this.b.l[this.b.aw], 60, 120, 20);
                graphics.drawString("\u9632\u707e: " + this.b.r[this.b.aw], 60, 140, 20);
                break;
            }
            case 3: {
                graphics.drawString("\u7edf\u6cbb: " + this.b.l[this.b.aw], 50, 120, 20);
                graphics.drawString("\u4eba\u53e3: " + this.b.q[this.b.aw], 50, 140, 20);
            }
        }
        this.b(graphics, c.b[2][this.b.aR]);
        this.d(graphics);
        this.a(graphics, this.aV);
    }

    private final void f(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        graphics.drawImage(this.aE[4], 70, 80, 17);
        graphics.setColor(0);
        graphics.drawRect(43, 80, this.aE[4].getWidth(), this.aE[4].getHeight());
        graphics.drawRegion(this.aH, 58, 0, 29, 17, 0, 130, 90, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.H[this.b.az], 135, 120, 20);
        graphics.drawRegion(this.aE[5], 0, 15 * this.aP, 27, 15, 0, 60, 165, 20);
        graphics.drawRegion(this.aE[5], 0, 75 - 15 * this.aP, 27, 15, 0, 150, 165, 20);
        if (this.bq) {
            byte by = 0;
            while (by < 4) {
                if (this.bs == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bt[by]), 130 - this.bg * by, 164, 24);
                by = (byte)(by + 1);
            }
            graphics.drawImage(this.aE[1], 123 - this.bg * this.bs, 162, 20);
        } else {
            graphics.drawString("" + this.bB, 118, 164, 17);
        }
        this.b(graphics, c.b[1][2]);
        this.d(graphics);
        if (this.by) {
            this.a(graphics, "\u5956\u8d4f\u9ec4\u91d1\u4e0d\u80fd\u4e3a0!");
        }
        if (this.aT) {
            this.a(graphics, this.aV);
        }
    }

    private final void g(Graphics graphics) {
        int n = this.e + 2;
        if (!this.bp) {
            graphics.drawImage(this.aK, 0, 0, 20);
            graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
            graphics.drawImage(this.aE[0], this.aN >> 1, 83, 17);
            this.d(graphics);
            this.b(graphics, c.b[0][2]);
            graphics.setColor(0xFFFFFF);
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawString(String.valueOf(this.b.m[this.b.aw]), 115, 84, 20);
            short s = this.b.b(this.b.B[this.b.aw], this.b.s[this.b.aw]);
            graphics.drawString(String.valueOf(s), 115, 108, 20);
            graphics.drawString(String.valueOf(this.b.w[this.b.aw]), 115, 133, 20);
            graphics.drawImage(this.aE[1], 133 - this.bg * this.bs, 151, 20);
            graphics.setColor(0xFFFF00);
            int n2 = 0;
            while (n2 < 4) {
                graphics.drawString(String.valueOf(this.bt[n2]), 150 - this.bg * n2, 153, 24);
                n2 = (byte)(n2 + 1);
            }
            if (this.aT) {
                this.a(graphics, this.aV);
            }
        } else {
            byte by = 0;
            int n3 = 0;
            graphics.setColor(0);
            graphics.drawImage(this.aK, 0, 0, 20);
            graphics.drawImage(this.aE[2], 186, this.aO - 4, 36);
            graphics.drawString(String.valueOf(this.bu), this.aN - 15, this.aO - 2, 40);
            graphics.drawString("\u8bf7\u5206\u914d\u540e\u5907\u5175", 90, this.aO - 5, 33);
            this.b(graphics, c.b[0][2]);
            graphics.drawImage(this.aF, 35, 36 + n * this.bv, 0);
            if (this.bn == 1) {
                by = 0;
                while (by < this.b.s[this.b.aw]) {
                    if (by == this.bv) {
                        if (this.bq) {
                            graphics.drawImage(this.aE[1], 188 - this.bg * this.bs, 28 + n * by, 20);
                        }
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.b.H[this.b.B[this.b.aw][by]], 45, 30 + n * by, 20);
                    graphics.drawString(c.g[this.b.J[this.b.B[this.b.aw][by]]], 110, 30 + n * by, 20);
                    int n4 = 0;
                    while (n4 < 4) {
                        graphics.drawString(String.valueOf(this.bw[by][n4]), 186 - this.bg * n4, 30 + n * by, 20);
                        n4 = (byte)(n4 + 1);
                    }
                    by = (byte)(by + 1);
                }
                graphics.setColor(0);
                graphics.drawString("\u51b3\u5b9a", 45, 30 + n * this.b.s[this.b.aw], 20);
            } else {
                if (this.bo < this.bn - 1) {
                    by = (byte)(this.bo * 10);
                    while (by < (this.bo + 1) * 10) {
                        n3 = by % 10;
                        if (by == this.bv + this.bo * 10) {
                            if (this.bq) {
                                graphics.drawImage(this.aE[1], 145 - this.bg * this.bs, 24 + n * n3, 20);
                            }
                            graphics.setColor(16275794);
                        } else {
                            graphics.setColor(0);
                        }
                        graphics.drawString(this.b.H[this.b.B[this.b.aw][by]], 30, 30 + n * n3, 20);
                        graphics.drawString(c.g[this.b.J[this.b.B[this.b.aw][by]]], 80, 30 + n * n3, 20);
                        int n5 = 0;
                        while (n5 < 4) {
                            graphics.drawString(String.valueOf(this.bw[by][n5]), 146 - this.bg * n5, 30 + n * n3, 20);
                            n5 = (byte)(n5 + 1);
                        }
                        by = (byte)(by + 1);
                    }
                } else if (this.bo == this.bn - 1) {
                    by = (byte)(this.bo * 10);
                    while (by < this.b.s[this.b.aw]) {
                        n3 = by % 10;
                        if (by == this.bv + this.bo * 10) {
                            if (this.bq) {
                                graphics.drawImage(this.aE[1], 145 - this.bg * this.bs, 24 + n * n3, 20);
                            }
                            graphics.setColor(16275794);
                        } else {
                            graphics.setColor(0);
                        }
                        graphics.drawString(this.b.H[this.b.B[this.b.aw][by]], 30, 30 + n * n3, 20);
                        graphics.drawString(c.g[this.b.J[this.b.B[this.b.aw][by]]], 80, 30 + n * n3, 20);
                        int n6 = 0;
                        while (n6 < 4) {
                            graphics.drawString(String.valueOf(this.bw[by][n6]), 146 - this.bg * n6, 30 + n * n3, 20);
                            n6 = (byte)(n6 + 1);
                        }
                        by = (byte)(by + 1);
                    }
                    graphics.setColor(0);
                    graphics.drawString("\u51b3\u5b9a", 30, 30 + n * (this.b.s[this.b.aw] % 10), 20);
                }
                graphics.setColor(0xFFFFFF);
                graphics.drawString(String.valueOf(this.bo + 1) + "/" + this.bn, this.aN >> 1, this.aO - 32, 17);
                graphics.drawImage(this.aF, 140, this.aO - 30, 20);
                graphics.drawRegion(this.aF, 0, 0, 6, 8, 2, 100, this.aO - 30, 24);
            }
        }
        if (this.aT) {
            this.a(graphics, this.aV);
        }
    }

    private final void h(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(0, 0, this.aN, this.aO);
        graphics.drawImage(this.aE[0], this.aN >> 1, this.aO >> 1, 3);
        graphics.setColor(0);
        graphics.drawString(this.bc, this.aN, this.aO, 40);
    }

    private final void i(Graphics graphics) {
        System.gc();
        graphics.drawImage(this.aE[0], 0, 0, 0);
        graphics.drawString("\u8f7d\u5165\u4e2d...", this.aN >> 1, 40, 17);
        graphics.setColor(0xFFFF00);
        graphics.fillRect(53 + this.bY, 89, 5, 5);
    }

    private final void j(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.aE[0], 0, 0, 20);
        this.l(graphics);
        this.k(graphics);
        if (this.aT) {
            this.a(graphics, this.aV);
        }
        if (this.bj) {
            this.a(graphics, "\u662f\u5426\u653e\u5f03\u6b64\u57ce\u5e02\uff1f", (byte)0);
        }
    }

    private final void k(Graphics graphics) {
        this.g = this.n ? "\u8bf7\u4efb\u547d\u519b\u961f\u4e3b\u5c06" : (this.b.bM == 3 && this.b.bN == 3 || this.b.bM == 9 || this.b.bM == 100 || this.b.bM == 1 && this.b.bN == 2 ? "\u8bf7\u4efb\u547d\u65b0\u592a\u5b88" : (this.b.bM == 7 ? c.w : (this.b.bM == 20 ? "\u8bf7\u9009\u62e9\u79bb\u95f4\u7684\u6b66\u5c06" : (this.b.bM == 21 ? "\u8bf7\u9009\u62e9\u8981\u62db\u63fd\u6b66\u5c06" : (this.b.bM == 22 ? c.v : "\u9009\u62e9\u6267\u884c\u6b66\u5c06")))));
        this.d(graphics);
    }

    private final void a(Graphics graphics, byte by) {
        if (this.aH == null) {
            this.aH = this.k(14);
        }
        if (this.aF == null) {
            this.aF = this.k(109);
        }
        graphics.drawImage(this.aF, 72 + 45 * by, 264, 0);
        graphics.drawRegion(this.aH, 0, 0, 29, 17, 0, 80, 260, 20);
        graphics.drawRegion(this.aH, 29, 0, 29, 17, 0, 125, 260, 20);
    }

    private final void l(Graphics graphics) {
        int n = this.e + 2;
        int n2 = 0;
        byte by = 0;
        if (this.bn == 1) {
            by = 0;
            while (by < this.bm) {
                if (this.b.W[this.bl[by]]) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(this.b.H[this.bl[by]], 25, 22 + n * by, 20);
                graphics.drawString(String.valueOf(this.b.M[this.bl[by]]), 94, 22 + n * by, 17);
                graphics.drawString(String.valueOf(this.b.N[this.bl[by]]), 123, 22 + n * by, 17);
                graphics.drawString(String.valueOf(this.b.O[this.bl[by]]), 152, 22 + n * by, 17);
                graphics.drawString(String.valueOf(this.b.P[this.bl[by]]), 181, 22 + n * by, 17);
                if (this.b.Q[this.bl[by]] > 99) {
                    graphics.drawString("--", 210, 22 + n * by, 17);
                } else {
                    graphics.drawString(String.valueOf(this.b.Q[this.bl[by]]), 210, 22 + n * by, 17);
                }
                by = (byte)(by + 1);
            }
            graphics.setColor(0xFFFFFF);
            graphics.drawString("\u51b3\u5b9a", 25, n * (by + 1), 20);
        } else {
            if (this.bo < this.bn - 1) {
                by = (byte)(this.bo * 10);
                while (by < (this.bo + 1) * 10) {
                    if (this.b.W[this.bl[by]]) {
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0xFFFFFF);
                    }
                    n2 = by % 10;
                    graphics.drawString(this.b.H[this.bl[by]], 25, 22 + n * n2, 20);
                    graphics.drawString(String.valueOf(this.b.M[this.bl[by]]), 94, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.b.N[this.bl[by]]), 123, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.b.O[this.bl[by]]), 152, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.b.P[this.bl[by]]), 181, 22 + n * n2, 17);
                    if (this.b.Q[this.bl[by]] > 99) {
                        graphics.drawString("--", 210, 22 + n * n2, 17);
                    } else {
                        graphics.drawString(String.valueOf(this.b.Q[this.bl[by]]), 210, 22 + n * n2, 17);
                    }
                    by = (byte)(by + 1);
                }
            } else if (this.bo == this.bn - 1) {
                by = (byte)(this.bo * 10);
                while (by < this.bm) {
                    if (this.b.W[this.bl[by]]) {
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0xFFFFFF);
                    }
                    n2 = by % 10;
                    graphics.drawString(this.b.H[this.bl[by]], 25, 22 + n * n2, 20);
                    graphics.drawString(String.valueOf(this.b.M[this.bl[by]]), 94, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.b.N[this.bl[by]]), 123, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.b.O[this.bl[by]]), 152, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.b.P[this.bl[by]]), 181, 22 + n * n2, 17);
                    if (this.b.Q[this.bl[by]] > 99) {
                        graphics.drawString("--", 210, 22 + n * n2, 17);
                    } else {
                        graphics.drawString(String.valueOf(this.b.Q[this.bl[by]]), 210, 22 + n * n2, 17);
                    }
                    by = (byte)(by + 1);
                }
                graphics.setColor(0xFFFFFF);
                graphics.drawString("\u51b3\u5b9a", 25, 22 + n * (this.bm % 10), 20);
            }
            graphics.setColor(0xFFFFFF);
            graphics.drawString(String.valueOf(this.bo + 1) + "/" + this.bn, this.aN >> 1, 176, 17);
            graphics.drawImage(this.aF, 104, 177, 20);
            graphics.drawRegion(this.aF, 0, 0, 6, 8, 2, 76, 177, 24);
        }
        graphics.drawImage(this.aF, 12, 27 + n * this.aP, 0);
    }

    private final void m(Graphics graphics) {
        switch (this.be) {
            case 0: {
                graphics.drawImage(this.aE[0], 0, 0, 20);
                graphics.drawRegion(this.aE[1], 80 * this.aP, 0, 80, 19, 0, this.aN >> 1, this.aO - 8, 33);
                return;
            }
            case 2: {
                this.T(graphics);
            }
        }
    }

    private final void n(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aJ, this.aN >> 1, 35, 17);
        graphics.setColor(0xFFFF00);
        graphics.fillRect(11 + 38 * this.aQ, 303, 29, 15);
        graphics.drawImage(this.aE[1], this.aN >> 1, 45, 17);
        graphics.drawRegion(this.aE[4], 160, 0, 20, 20, 0, 150, 46, 0);
        graphics.drawImage(this.aE[2], this.aN >> 1, this.aO - 3, 33);
        graphics.drawImage(this.aE[0], 138, 82, 20);
        graphics.setColor(0);
        graphics.drawRect(138, 82, this.aE[0].getWidth(), this.aE[0].getHeight());
        this.t(graphics);
        if (this.aU) {
            this.U(graphics);
        }
    }

    private final void o(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aJ, this.aN >> 1, 35, 17);
        graphics.drawImage(this.aE[0], this.aN >> 1, 60, 17);
        graphics.setColor(0);
        graphics.drawRect(93, 60, this.aE[0].getWidth(), this.aE[0].getHeight());
        graphics.setColor(0xFFFFFF);
        this.a(graphics, this.b.aM, 35, 188, 185, 100);
        this.d(graphics);
        this.b(graphics, "\u6267\u884c\u6307\u4ee4");
    }

    private final void p(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        graphics.setColor(0xFFFF00);
        this.q(graphics);
        if (!this.aT) {
            byte by = 0;
            while (by < 4) {
                if (this.bs == by && this.bq) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bt[by]), 145 - this.bg * by, 96 + this.aP * 25, 20);
                by = (byte)(by + 1);
            }
            if (this.bq) {
                graphics.drawImage(this.aE[1], 147 - this.bg * this.bs, 92 + this.aP * 25, 20);
            }
        }
        this.b(graphics, c.b[0][3]);
        this.d(graphics);
        if (this.by) {
            this.a(graphics, "\u8f93\u9001\u6570\u91cf\u4e0d\u80fd\u5168\u4e3a\u7a7a!");
        }
        if (this.aT) {
            this.a(graphics, this.aV);
        }
    }

    private final void q(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.aP == 0) {
            graphics.drawRegion(this.aE[2], 0, 0, 27, 15, 0, 80, 96, 17);
            graphics.drawRegion(this.aE[2], 0, 45, 27, 15, 0, 80, 121, 17);
            graphics.drawRegion(this.aE[2], 0, 75, 27, 15, 0, 80, 146, 17);
            graphics.drawString(String.valueOf(this.bz[1]), 140, 121, 17);
            graphics.drawString(String.valueOf(this.bz[2]), 140, 146, 17);
            return;
        }
        if (this.aP == 1) {
            graphics.drawRegion(this.aE[2], 0, 15, 27, 15, 0, 80, 96, 17);
            graphics.drawRegion(this.aE[2], 0, 30, 27, 15, 0, 80, 121, 17);
            graphics.drawRegion(this.aE[2], 0, 75, 27, 15, 0, 80, 146, 17);
            graphics.drawString(String.valueOf(this.bz[0]), 140, 96, 17);
            graphics.drawString(String.valueOf(this.bz[2]), 140, 146, 17);
            return;
        }
        if (this.aP == 2) {
            graphics.drawRegion(this.aE[2], 0, 15, 27, 15, 0, 80, 96, 17);
            graphics.drawRegion(this.aE[2], 0, 45, 27, 15, 0, 80, 121, 17);
            graphics.drawRegion(this.aE[2], 0, 60, 27, 15, 0, 80, 146, 17);
            graphics.drawString(String.valueOf(this.bz[0]), 140, 96, 17);
            graphics.drawString(String.valueOf(this.bz[1]), 140, 121, 17);
            return;
        }
        graphics.drawRegion(this.aE[2], 0, 15, 27, 15, 0, 80, 96, 17);
        graphics.drawRegion(this.aE[2], 0, 45, 27, 15, 0, 80, 121, 17);
        graphics.drawRegion(this.aE[2], 0, 75, 27, 15, 0, 80, 146, 17);
        graphics.drawString(String.valueOf(this.bz[0]), 140, 96, 17);
        graphics.drawString(String.valueOf(this.bz[1]), 140, 121, 17);
        graphics.drawString(String.valueOf(this.bz[2]), 140, 146, 17);
    }

    private final void r(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aJ, this.aN >> 1, 35, 17);
        graphics.drawImage(this.aE[0], this.aN >> 1, 60, 17);
        graphics.setColor(0);
        graphics.drawRect(93, 60, this.aE[0].getWidth(), this.aE[0].getHeight());
        this.b(graphics, c.b[1][0]);
        this.d(graphics);
        graphics.setColor(0xFFFFFF);
        if (this.b.aR == 1 || this.b.aR == 2) {
            graphics.drawString(String.valueOf(c.c[this.b.aR]) + this.b.aQ, this.aN >> 1, 180, 17);
            return;
        }
        graphics.drawString(c.c[this.b.aR], this.aN >> 1, 180, 17);
    }

    private final void s(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        if (this.aP > 0) {
            graphics.drawRegion(this.aF, 0, 0, 6, 8, 6, this.aN >> 1, 80, 17);
        }
        if (this.aP < this.b.aV - 1) {
            graphics.drawRegion(this.aF, 0, 0, 6, 8, 5, this.aN >> 1, 192, 33);
        }
        graphics.drawImage(this.aE[0], 137, 97, 20);
        graphics.setColor(0);
        graphics.drawRect(137, 97, this.aE[0].getWidth(), this.aE[0].getHeight());
        graphics.setColor(0xFFFFFF);
        graphics.drawString("\u52bf\u529b: " + this.b.c[this.b.aS[this.aP]], 50, 110, 20);
        graphics.drawString("\u57ce\u6c60\u6570: " + this.b.d[this.b.aS[this.aP]], 50, 140, 20);
        this.b(graphics, c.b[3][0]);
        this.d(graphics);
        if (this.aT) {
            this.a(graphics, this.aV);
        }
    }

    private final void t(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.setColor(16275794);
        this.W(graphics);
        graphics.drawString(String.valueOf(this.b.aI), 180, 47, 17);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.i[this.b.aw], 100, 47, 17);
        graphics.drawString(this.b.H[this.b.b[this.b.au]], 100, 82, 17);
        graphics.drawString(this.b.H[this.b.k[this.b.aw]], 100, 109, 17);
        graphics.drawString(String.valueOf(this.b.l[this.b.aw]), 100, 135, 17);
        int n = this.b.q[this.b.aw] / 1000;
        int n2 = this.b.v[this.b.aw] / 100;
        graphics.drawString(String.valueOf(n), 80, 165, 17);
        graphics.drawString(String.valueOf(this.b.s[this.b.aw]), 128, 165, 17);
        graphics.drawString(String.valueOf(n2), 180, 165, 17);
        graphics.drawString(String.valueOf(this.b.o[this.b.aw]), 80, 192, 17);
        graphics.drawString(String.valueOf(this.b.p[this.b.aw]), 128, 192, 17);
        graphics.drawString(String.valueOf(this.b.r[this.b.aw]), 180, 192, 17);
        graphics.drawString(String.valueOf(this.b.m[this.b.aw]), 100, 220, 17);
        graphics.drawString(String.valueOf(this.b.n[this.b.aw]), 170, 220, 17);
    }

    private final void u(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aJ, this.aN >> 1, 35, 17);
        if (this.bj) {
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawImage(this.aE[3], this.aN >> 1, 45, 17);
            graphics.drawImage(this.aE[1], 138, 82, 20);
            graphics.setColor(0);
            graphics.drawRect(138, 82, this.aE[1].getWidth(), this.aE[1].getHeight());
            this.b(graphics, c.b[3][3]);
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.b.i[this.b.ax], 100, 47, 17);
            graphics.drawString(String.valueOf(this.b.l[this.b.ax]), 99, 135, 17);
            int n = this.b.q[this.b.ax] / 1000;
            int n2 = this.b.v[this.b.ax] / 100;
            graphics.drawString(String.valueOf(n), 80, 165, 17);
            graphics.drawString(String.valueOf(this.b.s[this.b.ax]), 128, 165, 17);
            graphics.drawString(String.valueOf(n2), 180, 165, 17);
            graphics.drawString(String.valueOf(this.b.o[this.b.ax]), 80, 192, 17);
            graphics.drawString(String.valueOf(this.b.p[this.b.ax]), 128, 192, 17);
            graphics.drawString(String.valueOf(this.b.r[this.b.ax]), 180, 192, 17);
            graphics.drawString(String.valueOf(this.b.m[this.b.ax]), 100, 220, 17);
            graphics.drawString(String.valueOf(this.b.n[this.b.ax]), 170, 220, 17);
        } else if (this.bC) {
            boolean bl = false;
            boolean bl3 = false;
            this.b(graphics, c.b[3][3]);
            this.g = "\u8be5\u57ce\u6b66\u5c06\u4fe1\u606f,\u4e0a\u4e0b\u952e\u7ffb\u9875";
            graphics.drawImage(this.aE[2], 57, 48, 20);
            graphics.setColor(0);
            graphics.drawRect(57, 48, this.aE[2].getWidth(), this.aE[2].getHeight());
            int n = 0;
            while (n < 8) {
                graphics.drawRegion(this.aE[0], n * 20, 0, 20, 20, 0, 60 + n % 2 * 75, n / 2 * 25 + 137, 0);
                n = (byte)(n + 1);
            }
            n = this.b.B[this.b.ax][this.aP];
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.b.H[n], 137, 54, 20);
            graphics.setColor(16275794);
            graphics.drawString("LV. " + this.b.I[n], 159, 80, 20);
            graphics.drawString(String.valueOf(this.b.N[n]), 85, 140, 20);
            graphics.drawString(String.valueOf(this.b.O[n]), 160, 140, 20);
            graphics.drawString(String.valueOf(this.b.M[n]), 85, 165, 20);
            graphics.drawString(String.valueOf(this.b.P[n]), 160, 165, 20);
            graphics.drawString(String.valueOf(this.b.K[n]), 85, 190, 20);
            graphics.drawString(String.valueOf(this.b.R[n] / 100), 160, 190, 20);
            graphics.drawString(this.b.ae[this.b.S[n]], 85, 215, 20);
            graphics.drawString(this.b.ae[this.b.T[n]], 160, 215, 20);
            graphics.drawRegion(this.aH, 87, 0, 29, 17, 0, 72, 267, 0);
            graphics.drawRegion(this.aH, 29, 0, 29, 17, 0, 135, 267, 0);
            graphics.drawImage(this.aF, 60 + this.aV * 63, 271, 0);
        } else {
            boolean bl = false;
            boolean bl4 = false;
            graphics.drawImage(this.aE[3], this.aN >> 1, 45, 17);
            graphics.drawImage(this.aE[1], 138, 82, 20);
            graphics.setColor(0);
            graphics.drawRect(138, 82, this.aE[1].getWidth(), this.aE[1].getHeight());
            this.b(graphics, c.b[3][3]);
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.b.i[this.b.ax], 100, 47, 17);
            graphics.drawString(this.b.H[this.b.j[this.b.ax]], 100, 82, 17);
            graphics.drawString(this.b.H[this.b.k[this.b.ax]], 100, 109, 17);
            graphics.drawString(String.valueOf(this.b.l[this.b.ax]), 100, 135, 17);
            int n = this.b.q[this.b.ax] / 1000;
            int n3 = this.b.v[this.b.ax] / 100;
            graphics.drawString(String.valueOf(n), 80, 165, 17);
            graphics.drawString(String.valueOf(this.b.s[this.b.ax]), 128, 165, 17);
            graphics.drawString(String.valueOf(n3), 180, 165, 17);
            graphics.drawString(String.valueOf(this.b.o[this.b.ax]), 80, 192, 17);
            graphics.drawString(String.valueOf(this.b.p[this.b.ax]), 128, 192, 17);
            graphics.drawString(String.valueOf(this.b.r[this.b.ax]), 180, 192, 17);
            graphics.drawString(String.valueOf(this.b.m[this.b.ax]), 100, 220, 17);
            graphics.drawString(String.valueOf(this.b.n[this.b.ax]), 170, 220, 17);
            graphics.drawRegion(this.aH, 58, 0, 29, 17, 0, 72, 267, 0);
            graphics.drawRegion(this.aH, 29, 0, 29, 17, 0, 135, 267, 0);
            graphics.drawImage(this.aF, 60 + this.aV * 63, 271, 0);
        }
        this.d(graphics);
    }

    private final void v(Graphics graphics) {
        if (this.bD) {
            graphics.setColor(0xFFFFFF);
            graphics.drawImage(this.aK, 0, 0, 20);
            graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
            graphics.drawRegion(this.aE[2], 0, 0, 32, 39, 0, 60, 110, 20);
            graphics.drawRegion(this.aE[2], 32, 0, 32, 39, 0, 140, 110, 20);
            graphics.drawImage(this.aF, 51 + 80 * this.aP, 126, 0);
            this.g = this.aP == 0 ? "\u8d2d\u4e70\u7cae\u98df" : "\u8d2d\u4e70\u9ec4\u91d1";
        } else {
            short s = this.a(this.bt);
            this.bE = this.aP == 0 ? s * 4 / 3 : 3 * s / 4;
            graphics.drawImage(this.aK, 0, 0, 20);
            graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
            this.g = "100\u7c73 \u53ef\u6613 75\u91d1";
            graphics.drawRegion(this.aE[2], 32 * this.aP, 0, 32, 39, 0, this.aN >> 1, 90, 17);
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawRegion(this.aE[3], 0, 30, 27, 15, 0, 130 - 90 * this.aP, 145, 20);
            graphics.drawRegion(this.aE[3], 0, 0, 27, 15, 0, 40 + 90 * this.aP, 145, 20);
            graphics.setColor(0xFFFFFF);
            graphics.drawString("= ", 112, 144, 20);
            graphics.drawString(String.valueOf(this.bE), 158, 143, 20);
            byte by = 0;
            while (by < 4) {
                if (this.bs == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bt[by]), 102 - this.bg * by, 143, 24);
                by = (byte)(by + 1);
            }
            graphics.drawImage(this.aE[1], 95 - this.bg * this.bs, 141, 20);
            if (this.by) {
                this.a(graphics, "\u8d2d\u4e70\u6570\u91cf\u4e0d\u80fd\u4e3a0!");
            }
            if (this.aT) {
                this.a(graphics, this.aV);
            }
        }
        this.b(graphics, c.b[4][0]);
        this.d(graphics);
    }

    private final void w(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.setColor(10656072);
        graphics.fillRect(36, 77, 168, 120);
        graphics.setColor(0);
        graphics.drawRect(36, 77, 168, 120);
        graphics.drawImage(this.aE[0], this.aN >> 1, 60, 17);
        this.b(graphics, c.b[4][1]);
        this.d(graphics);
        graphics.drawRegion(this.aF, 0, 0, 6, 8, 5, 50 + this.aX * 45, 60, 20);
        graphics.drawImage(this.aF, 50, 105 + 20 * this.aP, 0);
        byte by = 0;
        while (by < 4) {
            if (by == this.aP) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            byte by2 = this.a(this.aX, by);
            graphics.drawString(String.valueOf(this.b.ae[by2]) + "  \u9700\u91d1: " + this.b.af[by2], 60, 100 + 20 * by, 0);
            by = (byte)(by + 1);
        }
        if (this.aT) {
            this.a(graphics, this.aV);
        }
    }

    private final byte a(byte by, byte by2) {
        byte by3 = 0;
        byte by4 = !this.aA ? this.b.z[this.b.aw] : this.b.A[this.b.aZ];
        switch (by) {
            case 0: {
                by3 = this.b.ah[by4][by2];
                break;
            }
            case 1: {
                by3 = this.b.ai[by4][by2];
                break;
            }
            case 2: {
                by3 = this.b.aj[by4][by2];
                break;
            }
            case 3: {
                by3 = this.b.ak[by4][by2];
            }
        }
        return by3;
    }

    private final void x(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        this.d(graphics);
        if (this.b.bM == 22) {
            this.b(graphics, c.b[5][0]);
        } else {
            this.b(graphics, c.b[5][1]);
            if (this.s()) {
                graphics.setColor(0xFFFF00);
                graphics.drawString("\u65e0\u5b58\u8d2e\u8bb0\u5f55", this.aN >> 1, 120, 17);
                return;
            }
        }
        byte by = 0;
        while (by < 3) {
            if (this.aP == by) {
                graphics.setColor(16275794);
                graphics.drawImage(this.aF, 45, 95 + 25 * by, 20);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            if (this.b.al[by] == 0) {
                graphics.drawString("\u7a7a\u8bb0\u5f55", this.aN >> 1, 90 + 25 * by, 17);
            } else {
                String string = String.valueOf(this.b.ao[by]) + "  " + String.valueOf(this.b.an[by]) + "\u5e74" + String.valueOf(this.b.am[by]) + "\u6708";
                graphics.drawString(string, this.aN >> 1, 90 + 25 * by, 17);
            }
            by = (byte)(by + 1);
        }
        if (this.aT) {
            this.a(graphics, this.aV);
        }
    }

    private final boolean s() {
        int n = 0;
        while (n < 3) {
            if (this.b.al[n] != 0) {
                return false;
            }
            n = (byte)(n + 1);
        }
        return true;
    }

    public final synchronized boolean d() {
        if (!this.bZ) {
            this.bZ = true;
            return false;
        }
        return true;
    }

    public final void paint(Graphics graphics) {
        if (this.d()) {
            return;
        }
        try {
            graphics.setFont(this.d);
            switch (this.aC) {
                case -1: {
                    this.i(graphics);
                    break;
                }
                case 0: {
                    this.h(graphics);
                    break;
                }
                case 1: {
                    this.m(graphics);
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    this.V(graphics);
                    break;
                }
                case 5: {
                    this.u(graphics);
                    break;
                }
                case 6: {
                    this.n(graphics);
                    break;
                }
                case 7: {
                    this.j(graphics);
                    break;
                }
                case 8: {
                    this.g(graphics);
                    break;
                }
                case 9: {
                    this.p(graphics);
                    break;
                }
                case 10: {
                    this.o(graphics);
                    break;
                }
                case 11: {
                    this.r(graphics);
                    break;
                }
                case 12: {
                    this.f(graphics);
                    break;
                }
                case 13: {
                    this.e(graphics);
                    break;
                }
                case 14: {
                    this.N(graphics);
                    break;
                }
                case 15: {
                    this.s(graphics);
                    break;
                }
                case 16: {
                    this.v(graphics);
                    break;
                }
                case 17: {
                    this.w(graphics);
                    break;
                }
                case 18: {
                    this.x(graphics);
                    break;
                }
                case 19: {
                    this.S(graphics);
                    break;
                }
                case 20: {
                    this.O(graphics);
                    break;
                }
                case 21: {
                    this.E(graphics);
                    break;
                }
                case 22: {
                    this.A(graphics);
                    break;
                }
                case 23: {
                    this.z(graphics);
                    break;
                }
                case 24: {
                    this.y(graphics);
                    break;
                }
                case 25: {
                    this.X(graphics);
                }
            }
            if (this.aS) {
                this.a(graphics, this.ba);
            }
        }
        catch (Exception exception) {
            this.a("paint() screenid:" + this.aC + "\n:error info:" + exception.toString());
        }
        this.bZ = false;
    }

    private final void y(Graphics graphics) {
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        this.b(graphics, c.b[5][2]);
        graphics.setColor(0xFFFFFF);
        byte by = 0;
        while (by < 3) {
            if (this.bd == by) {
                graphics.setColor(0xFFFF00);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawString(c.o[by], 50, 90 + by * 30, 20);
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < 2) {
            int n = 0;
            while (n < 2) {
                if (this.m[n] == by) {
                    graphics.setColor(0xFFFF00);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(c.p[by], 90 + by * 50, 90 + n * 30, 20);
                n = (byte)(n + 1);
            }
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < 5) {
            if (this.m[2] == by) {
                graphics.setColor(0xFFFF00);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawString(c.q[by], 90 + by * 20, 150, 20);
            by = (byte)(by + 1);
        }
        if (this.aT) {
            this.a(graphics, this.aV);
        }
        this.d(graphics);
    }

    private final void z(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, this.aN, this.aO);
        if (this.bQ) {
            graphics.drawImage(this.aE[0], this.aN >> 1, 50, 17);
            graphics.setColor(0x996600);
            graphics.drawRect(38, 50, this.aE[0].getWidth(), this.aE[0].getHeight());
            graphics.drawImage(this.aE[1], this.aN >> 1, 40, 17);
            this.g = "\u7edf\u4e00\u5ea6:" + this.b.cK + " \u5206\u6570:" + this.b.cJ;
            graphics.setColor(0xFFFF00);
            this.a(graphics, this.g, 40, 180, 170, 140);
            return;
        }
        graphics.drawImage(this.aE[0], this.aN >> 1, 50, 17);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 50, this.aE[0].getWidth(), this.aE[0].getHeight());
        graphics.drawImage(this.aE[1], this.aN >> 1, 40, 17);
        graphics.setColor(0xFFFF00);
        this.a(graphics, this.g, 40, 180, 170, 140);
    }

    private final void A(Graphics graphics) {
        try {
            switch (this.am) {
                case 0: {
                    if (this.au > 0) {
                        this.C(graphics);
                        break;
                    }
                    this.B(graphics);
                    break;
                }
                case 1: {
                    if (this.at) {
                        this.b.bZ = (byte)2;
                    } else {
                        this.b.bZ = 1;
                        this.az = (byte)this.b.ab();
                    }
                    if (this.az >= 0) break;
                    this.b.as = 1;
                    break;
                }
                case 2: {
                    this.b.bZ = this.at ? (byte)4 : (byte)3;
                    this.b.as = 1;
                    break;
                }
                case 3: {
                    if (this.at) {
                        this.b.d(false);
                    } else {
                        this.b.d(true);
                    }
                    this.b.as = 1;
                }
            }
            if (this.az > -1) {
                String string = this.e(this.az);
                this.a(graphics, string);
                return;
            }
        }
        catch (Exception exception) {
            this.a("Duel Paint() error!" + exception.toString());
        }
    }

    private final String e(byte by) {
        return c.t[by];
    }

    private final void B(Graphics graphics) {
        this.D(graphics);
        if (this.ar) {
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            graphics.drawImage(this.aE[1], this.aN >> 1, 50, 17);
            graphics.drawImage(this.aE[3], 20, 54, 20);
            graphics.setColor(0);
            graphics.drawRect(20, 52, this.aE[3].getWidth(), this.aE[3].getHeight());
            int n = this.ap % 3;
            int n2 = this.ap / 3;
            if (this.aF == null) {
                this.aF = this.k(109);
            }
            graphics.drawImage(this.aF, 95 + 42 * n, 68 + 35 * n2, 0);
            return;
        }
        if (this.as == 0) {
            switch (this.ap) {
                case 0: 
                case 1: 
                case 2: {
                    this.ay = "\u653b\u51fb\u654c\u65b9\u4e2d...";
                    this.a(graphics, (int)this.an, this.ap, false);
                    return;
                }
                case 3: {
                    if (this.b.k(this.X, this.Y)) {
                        this.au = (byte)2;
                        this.at = false;
                        this.ay = "\u6210\u529f\u62db\u964d!";
                        return;
                    }
                    this.au = (byte)100;
                    this.ay = "\u62db\u964d\u5931\u8d25!";
                    this.as = (byte)(this.as + 1);
                    this.a(500);
                    return;
                }
                case 4: {
                    if (this.b.b(this.X, this.Y)) {
                        this.au = (byte)3;
                        this.at = true;
                        this.ay = "\u81ea\u519b\u6210\u529f\u64a4\u79bb!";
                    } else {
                        this.au = (byte)100;
                        this.ay = "\u64a4\u9000\u5931\u8d25...";
                    }
                    this.as = (byte)(this.as + 1);
                    return;
                }
                case 5: {
                    this.au = (byte)2;
                    this.at = true;
                    this.ay = "\u6211\u65b9\u6295\u964d!";
                    return;
                }
            }
            return;
        }
        switch (this.aq) {
            case 0: 
            case 1: 
            case 2: {
                this.ay = "\u654c\u65b9\u6b66\u5c06\u653b\u51fb\u4e2d....";
                this.a(graphics, (int)this.an, this.aq, true);
                return;
            }
            case 3: {
                if (this.b.k(this.Y, this.X)) {
                    this.au = (byte)2;
                    this.at = true;
                    this.ay = "\u6210\u529f\u62db\u964d!";
                    return;
                }
                this.au = (byte)100;
                this.ay = "\u62db\u964d\u5931\u8d25!";
                this.ap = 0;
                this.ar = true;
                this.as = 0;
                this.a(500);
                return;
            }
            case 4: {
                if (this.b.b(this.Y, this.X)) {
                    this.au = (byte)3;
                    this.at = false;
                    this.ay = "\u654c\u65b9\u6b66\u5c06\u9003\u7a9c!";
                } else {
                    this.au = (byte)100;
                    this.ay = "\u654c\u65b9\u9041\u9003\u5931\u8d25!";
                }
                this.ap = 0;
                this.ar = true;
                this.as = 0;
                return;
            }
            case 5: {
                this.au = (byte)2;
                this.at = false;
                this.ay = "\u654c\u65b9\u6b66\u5c06\u5f52\u964d!";
                return;
            }
        }
    }

    private final void b(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                int n = 179 - this.ao * 4;
                if (this.ao < 32) {
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 300 + this.ao % 2 * 540, 0, 60, 60, 2, n, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                return;
            }
            int n = 139 - this.ao * 4;
            if (this.ao < 35) {
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                graphics.drawRegion(this.aE[2], 300 + this.ao % 2 * 540, 0, 60, 60, 2, n, this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            this.an = 1;
            this.ao = 0;
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            this.as = (byte)(this.as + 1);
            this.aq = this.b.d(this.Y, this.X);
            return;
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            return;
        }
    }

    private final void c(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                int n = 55 + this.ao * 4;
                if (this.ao < 35) {
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 300 + this.ao % 2 * 540, 0, 60, 60, 0, n, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                this.an = 1;
                this.ao = 0;
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                this.as = 0;
                this.ap = 0;
                this.ar = true;
                this.ay = "\u8bf7\u9009\u62e9\u6267\u884c\u547d\u4ee4";
                return;
            }
            int n = 5 + this.ao * 4;
            if (this.ao < 33) {
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                graphics.drawRegion(this.aE[2], 300 + this.ao % 2 * 540, 0, 60, 60, 0, n, this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 137, this.aB, 20);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            return;
        }
    }

    private final void d(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                byte by = 0;
                if (this.ao < 3) {
                    by = (byte)(this.ao + 2);
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 60 * by, 0, 60, 60, 2, 55 - 8 * (by % 2), this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                byte by2 = this.b.b(this.Y, this.b.cv, this.b.cu);
                int n = this.b.M[this.X] - by2;
                if (n < 1) {
                    this.b.M[this.X] = 0;
                    this.a(true, 0);
                    return;
                }
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                short s = this.X;
                this.b.M[s] = (byte)(this.b.M[s] - by2);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            byte by = 0;
            if (this.ao < 3) {
                by = (byte)(this.ao + 2);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                graphics.drawRegion(this.aE[2], 60 * by, 0, 60, 60, 0, 137 + 8 * (by % 2), this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            byte by3 = this.b.b(this.X, this.b.ct, this.b.cw);
            int n = this.b.M[this.Y] - by3;
            if (n < 1) {
                this.b.M[this.Y] = 0;
                this.a(false, 1);
                return;
            }
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, this.aN - 51, this.aB, 24);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            short s = this.Y;
            this.b.M[s] = (byte)(this.b.M[s] - by3);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            return;
        }
    }

    private final void e(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                if (this.ao < 5) {
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 60 * this.ao, 0, 60, 60, 2, 51, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                byte by = this.b.c(this.Y, this.b.cv, this.b.cu);
                int n = this.b.M[this.X] - by;
                if (n < 1) {
                    this.b.M[this.X] = 0;
                    this.a(true, 0);
                    return;
                }
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                short s = this.X;
                this.b.M[s] = (byte)(this.b.M[s] - by);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            if (this.ao < 5) {
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                graphics.drawRegion(this.aE[2], 60 * this.ao, 0, 60, 60, 0, this.aN - 51, this.aB, 24);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            byte by = this.b.c(this.X, this.b.ct, this.b.cw);
            int n = this.b.M[this.Y] - by;
            if (n < 1) {
                this.b.M[this.Y] = 0;
                this.a(false, 1);
                return;
            }
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, this.aN - 51, this.aB, 24);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            short s = this.Y;
            this.b.M[s] = (byte)(this.b.M[s] - by);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            return;
        }
    }

    public final void a(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                if (this.ao < 11) {
                    int n = this.ao < 6 ? this.ao + 5 : this.ao - 6;
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 60 * n, 0, 60, 60, 2, 51, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                byte by = this.b.d(this.Y, this.b.cv, this.b.cu);
                if (by > 0) {
                    int n = this.b.M[this.X] - by;
                    if (n < 1) {
                        this.b.M[this.X] = 0;
                        this.a(true, 0);
                        return;
                    }
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                    short s = this.X;
                    this.b.M[s] = (byte)(this.b.M[s] - by);
                    this.an = (byte)(this.an + 1);
                    this.ao = 0;
                    return;
                }
                int n = this.b.M[this.Y] + by;
                if (n < 1) {
                    this.b.M[this.Y] = 0;
                    this.a(false, 0);
                    return;
                }
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                short s = this.Y;
                this.b.M[s] = (byte)(this.b.M[s] + by);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            if (this.ao < 11) {
                int n = this.ao < 6 ? this.ao + 5 : this.ao - 6;
                graphics.drawRegion(this.aE[2], 60 * n, 0, 60, 60, 0, this.aN - 51, this.aB, 24);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            byte by = this.b.d(this.X, this.b.ct, this.b.cw);
            if (by < 0) {
                int n = this.b.M[this.X] + by;
                if (n < 1) {
                    this.b.M[this.X] = 0;
                    this.a(true, 1);
                    return;
                }
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, this.aN - 51, this.aB, 24);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                short s = this.X;
                this.b.M[s] = (byte)(this.b.M[s] + by);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            int n = this.b.M[this.Y] - by;
            if (n < 1) {
                this.b.M[this.Y] = 0;
                this.a(false, 1);
                return;
            }
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, this.aN - 51, this.aB, 24);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            short s = this.Y;
            this.b.M[s] = (byte)(this.b.M[s] - by);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            return;
        }
    }

    private final void a(boolean bl, int n) {
        this.au = 1;
        this.at = bl;
        this.aw = (byte)n;
    }

    private final void a(Graphics graphics, int n, byte by, boolean bl) {
        if (n == 1) {
            if (bl) {
                this.b(graphics, bl);
                return;
            }
            this.c(graphics, bl);
            return;
        }
        if (n == 2) {
            switch (by) {
                case 0: {
                    this.d(graphics, bl);
                    return;
                }
                case 1: {
                    this.e(graphics, bl);
                    return;
                }
                case 2: {
                    this.a(graphics, bl);
                }
            }
            return;
        }
        if (n == 3) {
            if (bl) {
                this.c(graphics, bl);
                return;
            }
            this.b(graphics, bl);
        }
    }

    private final void C(Graphics graphics) {
        this.D(graphics);
        if (this.au == 1) {
            if (!this.at) {
                this.ay = String.valueOf(this.b.H[this.Y]) + "\u6218\u4ea1\u6c99\u573a.";
                if (this.av < 3) {
                    graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 0, 5 + 124 * this.aw, this.aB, 20);
                    graphics.drawRegion(this.aE[2], 780, 0, 60, 60, 2, 51 + 128 * this.aw, this.aB + 10, 20);
                    this.av = (byte)3;
                    return;
                }
                graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 0, 5 + 124 * this.aw, this.aB, 20);
                graphics.drawRegion(this.aE[2], 780, 0, 60, 60, 2, 51 + 128 * this.aw, this.aB + 10, 20);
                return;
            }
            this.ay = String.valueOf(this.b.H[this.X]) + "\u6218\u4ea1\u6c99\u573a.";
            if (this.av < 3) {
                graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 0, 5 + 124 * this.aw, this.aB + 10, 20);
                graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 2, 51 + 128 * this.aw, this.aB, 20);
                this.av = (byte)(this.av + 1);
                return;
            }
            graphics.drawRegion(this.aE[2], 780, 0, 60, 60, 0, 5 + 124 * this.aw, this.aB + 10, 20);
            graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 2, 51 + 128 * this.aw, this.aB, 20);
            return;
        }
        if (this.au == 2) {
            if (this.ax) {
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                this.ax = false;
                this.au = 0;
                this.ap = (byte)3;
                this.ar = false;
                return;
            }
            if (!this.at) {
                graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 720, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
                return;
            }
            graphics.drawRegion(this.aE[2], 720, 0, 60, 60, 0, 5, this.aB, 20);
            graphics.drawRegion(this.aE[2], 660, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            return;
        }
        if (this.au == 3) {
            if (!this.at) {
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, this.aN - 5, this.aB, 24);
                return;
            }
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, 5, this.aB, 20);
            graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
            return;
        }
        graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
        graphics.drawRegion(this.aE[2], 300, 0, 60, 60, 2, this.aN - 5, this.aB, 24);
    }

    private final void D(Graphics graphics) {
        graphics.drawImage(this.aE[0], 0, 0, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.H[this.X], this.aN >> 2, 2, 17);
        graphics.drawString(this.b.H[this.Y], this.aN * 3 >> 2, 2, 17);
        graphics.setColor(0);
        this.g = this.ay;
        this.d(graphics);
        graphics.setColor(16275794);
        byte by = 0;
        while (by < 100) {
            if (by < this.b.M[this.X]) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawLine(18 + by, 28, 18 + by, 43);
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < 100) {
            if (by < this.b.M[this.Y]) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawLine(223 - by, 28, 223 - by, 43);
            by = (byte)(by + 1);
        }
        graphics.setColor(0);
        graphics.drawRect(18, 28, 100, 15);
        graphics.drawRect(123, 28, 100, 15);
    }

    private final void E(Graphics graphics) {
        switch (this.af) {
            case 1: {
                this.G(graphics);
                return;
            }
            case 2: {
                this.aK = null;
                this.aI = null;
                if (this.b.bZ > 0) {
                    this.ba = this.b.bZ > 4 && this.b.cc > 0 ? String.valueOf(c.n[this.b.bZ]) + " \u4f53\u529b\u51cf\u5c11" + String.valueOf(this.b.cc) : c.n[this.b.bZ];
                    if (this.ag) {
                        this.aS = true;
                    }
                    this.ag = true;
                    if (this.b.M[this.X] == 0) {
                        this.b.K[this.Y] = this.b.e(false);
                        return;
                    }
                    this.b.K[this.X] = this.b.e(true);
                    return;
                }
                switch (this.b.cf) {
                    case 0: {
                        this.b.cf = 1;
                        this.b.c(this.bK);
                        this.b.as = 1;
                        return;
                    }
                    case 1: {
                        this.I(graphics);
                        return;
                    }
                    case 2: {
                        this.F(graphics);
                    }
                }
            }
        }
    }

    private final void F(Graphics graphics) {
        graphics.drawRegion(this.aE[1], 0, 93, 240, 154, 0, this.aN >> 1, this.aO, 33);
        short s = 0;
        int n = 0;
        int n2 = 1;
        while (n2 < 4) {
            s = (short)(s + this.b.cd[n2]);
            n = (short)(n + this.b.ce[n2]);
            n2 = (byte)(n2 + 1);
        }
        graphics.setColor(16275794);
        graphics.drawString(String.valueOf(n), 135, 295, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.aE[5], 6, 167, 20);
        graphics.drawString(this.b.H[this.X], 36, 240, 17);
        graphics.drawString(String.valueOf(this.b.M[this.X]), 55, 274, 20);
        graphics.drawString(String.valueOf(this.b.N[this.X]), 135, 274, 20);
        graphics.drawString(String.valueOf(this.b.bL), 208, 274, 20);
        graphics.drawString(String.valueOf(s), 55, 294, 20);
        n2 = 0;
        while (n2 < 4) {
            graphics.drawString(c.f[this.b.cg[n2]], 179, 174 + n2 * 19, 20);
            n2 = (byte)(n2 + 1);
        }
        graphics.drawImage(this.aF, 170, 178 + this.b.ch * 19, 0);
        if (this.bN) {
            this.b(graphics);
        }
    }

    private final void G(Graphics graphics) {
        if (this.aK == null) {
            this.aK = this.k(302);
        }
        if (this.aI == null) {
            this.aI = this.k(90);
        }
        if (this.aF == null) {
            this.aF = this.k(109);
        }
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        if (this.aL == null) {
            this.aL = this.k(49);
        }
        graphics.drawImage(this.aL, this.aN >> 1, 80, 17);
        this.b(graphics, c.b[0][1]);
        graphics.drawRegion(this.aF, 0, 0, 6, 8, 7, 50 + this.bK * 43, 193, 0);
        graphics.drawImage(this.aE[5], 35, 78, 20);
        graphics.drawImage(this.aE[6], 147, 78, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.H[this.X], 115, 82, 17);
        graphics.drawString(this.b.H[this.Y], 128, 132, 17);
        graphics.setColor(0);
        this.g = "\u9635\u578b\u9009\u62e9";
        this.d(graphics);
    }

    private final void H(Graphics graphics) {
        graphics.setColor(0);
        boolean bl = false;
        boolean bl2 = false;
        this.ah = 81;
        this.ai = 171;
        graphics.drawRect(80, 170, this.b.bJ * 5 + 2, this.b.bK * 5 + 2);
    }

    private final void a(Graphics graphics, byte by, byte by2) {
        if (by == 0) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(16275794);
        }
        if (by2 == 0) {
            graphics.fillRect(this.ah + this.b.bP[by][by2] * 5, this.ai + this.b.bQ[by][by2] * 5, 5, 5);
            return;
        }
        graphics.drawRect(this.ah + this.b.bP[by][by2] * 5, this.ai + this.b.bQ[by][by2] * 5, 4, 4);
    }

    private final void I(Graphics graphics) {
        this.M(graphics);
        graphics.drawRegion(this.aE[1], 0, 0, 240, 92, 0, this.aN >> 1, this.aO, 33);
        graphics.setColor(16275794);
        int n = this.b.ca >> 4;
        int n2 = this.b.cb >> 4;
        graphics.drawString(c.m[n], 53, this.aO, 36);
        graphics.drawString(c.m[n2], this.aN - 53, this.aO, 40);
        graphics.drawImage(this.aE[5], 0, 230, 20);
        graphics.drawImage(this.aE[6], this.aN, 230, 24);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.H[this.X], 24, this.aO - 2, 33);
        graphics.drawString(this.b.H[this.Y], 216, this.aO - 2, 33);
        this.b.n();
        byte by = 0;
        while (by < 4) {
            graphics.drawString(String.valueOf(this.b.cd[by]), 110, 235 + by * 21, 24);
            graphics.drawString(String.valueOf(this.b.ce[by]), 138, 235 + by * 21, 20);
            by = (byte)(by + 1);
        }
        this.H(graphics);
        by = 0;
        while (by < this.b.bG) {
            this.b(graphics, (byte)0, by);
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < this.b.bH) {
            this.b(graphics, (byte)1, by);
            by = (byte)(by + 1);
        }
        if (this.aj) {
            this.L(graphics);
        }
        if (this.ak) {
            this.K(graphics);
        }
        if (this.al) {
            this.J(graphics);
        }
    }

    private final void J(Graphics graphics) {
        if (this.b.bP[1][0] > 1) {
            int n = this.b.bP[1][0] - 1;
            int n2 = this.b.bQ[1][0] - 1;
            if (n2 < 0) {
                n2 = 0;
            }
            if (this.aE[7] == null) {
                this.aE[7] = this.k(105);
            }
            graphics.drawImage(this.aE[7], 16 * (n - this.aa), 16 + 16 * n2, 20);
        }
    }

    private final void K(Graphics graphics) {
        if (this.b.bP[0][0] < 13) {
            int n = this.b.bP[0][0] + 1;
            int n2 = this.b.bQ[0][0] - 1;
            if (n2 < 0) {
                n2 = 0;
            }
            if (this.aE[7] == null) {
                this.aE[7] = this.k(105);
            }
            graphics.drawImage(this.aE[7], 16 * (n - this.aa), 20 + 16 * n2, 20);
        }
    }

    public final void a(byte by, byte by2, byte by3, byte by4, boolean bl) {
        this.ab = (short)(16 * by);
        this.ac = (short)(16 * by2);
        this.ad = (short)(16 * by3);
        this.ae = (short)(16 * by4);
        boolean bl2 = true;
        if (this.b.ca >> 4 == 5 && bl || this.b.cb >> 4 == 5 && !bl) {
            bl2 = false;
        }
        if (this.ab == this.ad && this.ac < this.ae) {
            this.bL = (byte)8;
            this.aE[3] = !bl2 ? Image.createImage((Image)this.aE[4], (int)0, (int)0, (int)16, (int)16, (int)5) : Image.createImage((Image)this.aE[4], (int)16, (int)0, (int)16, (int)16, (int)5);
        } else if (this.ab == this.ad && this.ac > this.ae) {
            this.bL = (byte)2;
            this.aE[3] = !bl2 ? Image.createImage((Image)this.aE[4], (int)0, (int)0, (int)16, (int)16, (int)6) : Image.createImage((Image)this.aE[4], (int)16, (int)0, (int)16, (int)16, (int)6);
        } else if (this.ab < this.ad && this.ac == this.ae) {
            this.bL = (byte)6;
            this.aE[3] = !bl2 ? Image.createImage((Image)this.aE[4], (int)0, (int)0, (int)16, (int)16, (int)0) : Image.createImage((Image)this.aE[4], (int)16, (int)0, (int)16, (int)16, (int)0);
        } else if (this.ab > this.ad && this.ac == this.ae) {
            this.bL = (byte)4;
            this.aE[3] = !bl2 ? Image.createImage((Image)this.aE[4], (int)0, (int)0, (int)16, (int)16, (int)2) : Image.createImage((Image)this.aE[4], (int)16, (int)0, (int)16, (int)16, (int)2);
        }
        this.aj = true;
    }

    private final void L(Graphics graphics) {
        switch (this.bL) {
            case 2: {
                if (this.ac != this.ae) {
                    this.ac = (short)(this.ac - 8);
                    break;
                }
                this.aj = false;
                break;
            }
            case 4: {
                if (this.ab != this.ad) {
                    this.ab = (short)(this.ab - 8);
                    break;
                }
                this.aj = false;
                break;
            }
            case 6: {
                if (this.ab != this.ad) {
                    this.ab = (short)(this.ab + 8);
                    break;
                }
                this.aj = false;
                break;
            }
            case 8: {
                if (this.ac != this.ae) {
                    this.ac = (short)(this.ac + 8);
                    break;
                }
                this.aj = false;
            }
        }
        if (this.aj) {
            graphics.drawImage(this.aE[3], this.ab - this.aa * 16, this.ac + 32, 20);
        }
    }

    private final void b(Graphics graphics, byte by, byte by2) {
        if (this.b.bY[by][by2]) {
            int n = this.b.bP[by][by2] - this.aa;
            if (n >= 0 && n < 15) {
                this.a(graphics, by, by2, n);
                if (this.b.bX[by][by2]) {
                    this.b.bX[by][by2] = false;
                    graphics.drawImage(this.aE[2], n * 16, 32 + this.b.bQ[by][by2] * 16, 20);
                }
            }
            this.a(graphics, by, by2);
        }
    }

    private final void a(Graphics graphics, byte by, byte by2, int n) {
        switch (this.b.bU[by][by2]) {
            case 0: {
                graphics.drawRegion(this.aE[9], (this.b.bR[by][by2] + 4) * 16, by * 16, 16, 16, 0, n * 16, 32 + this.b.bQ[by][by2] * 16, 0);
                return;
            }
            case 1: {
                graphics.drawRegion(this.aE[9], (this.b.bR[by][by2] + 8) * 16, by * 16, 16, 16, 0, n * 16, 32 + this.b.bQ[by][by2] * 16, 0);
                return;
            }
            case 2: {
                graphics.drawRegion(this.aE[9], this.b.bR[by][by2] * 16, by * 16, 16, 16, 2, n * 16, 32 + this.b.bQ[by][by2] * 16, 0);
                return;
            }
            case 3: {
                graphics.drawRegion(this.aE[9], this.b.bR[by][by2] * 16, by * 16, 16, 16, 0, n * 16, 32 + this.b.bQ[by][by2] * 16, 0);
            }
        }
    }

    private final void M(Graphics graphics) {
        int n;
        int n2 = 0;
        while (n2 < 5) {
            n = 0;
            while (n < 4) {
                graphics.drawRegion(this.aE[0], this.bO * 64, 0, 64, 64, 0, n * 64, n2 * 64, 0);
                n = (byte)(n + 1);
            }
            n2 = (byte)(n2 + 1);
        }
        if (this.bJ) {
            n2 = 0;
            while (n2 < 15) {
                graphics.drawRegion(this.aE[8], 0, 0, 16, 16, 0, n2 * 16, 16, 20);
                graphics.drawRegion(this.aE[8], 0, 0, 16, 16, 0, n2 * 16, 144, 20);
                n2 = (byte)(n2 + 1);
            }
            n2 = 0;
            while (n2 < 11) {
                n = 0;
                while (n < 7) {
                    byte by = (byte)(this.b.bI[n][n2 + this.aa] & 0x32);
                    byte by2 = this.f(by);
                    if (by2 > -1) {
                        graphics.drawRegion(this.aE[8], by2 * 16, 0, 16, 16, 0, n2 * 16, n * 16 + 32, 20);
                    }
                    n = (byte)(n + 1);
                }
                n2 = (byte)(n2 + 1);
            }
        }
    }

    private final byte f(byte by) {
        byte by2 = 0;
        switch (by) {
            case 0: {
                by2 = -1;
                break;
            }
            case 32: {
                by2 = 0;
                break;
            }
            case 2: {
                by2 = 1;
                break;
            }
            case 16: {
                by2 = 2;
            }
        }
        return by2;
    }

    private final void N(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.aK, 0, 0, 20);
        graphics.drawImage(this.aI, this.aN >> 1, 60, 17);
        graphics.drawRegion(this.aE[1], 32, 0, 32, 39, 0, 82, 98, 17);
        graphics.drawRegion(this.aE[1], 0, 0, 32, 39, 0, 158, 98, 17);
        graphics.drawRegion(this.aE[4], 0, this.aP * 15, 27, 15, 0, 63, 158, 17);
        graphics.drawRegion(this.aE[4], 0, 45 - this.aP * 15, 27, 15, 0, 134, 158, 17);
        graphics.setColor(0xFFFFFF);
        if (this.bq) {
            if (this.aP == 0) {
                graphics.drawString(String.valueOf(this.bG), 170, 158, 17);
            } else {
                graphics.drawString(String.valueOf(this.bH), 96, 158, 17);
            }
            byte by = 0;
            while (by < 4) {
                if (this.bs == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bt[by]), 75 - 8 * by + 70 * this.aP + 30, 158, 20);
                by = (byte)(by + 1);
            }
            graphics.drawImage(this.aE[0], 77 - 8 * this.bs + 70 * this.aP + 30, 155, 20);
        } else {
            graphics.drawString(String.valueOf(this.bG), 170, 158, 17);
            graphics.drawString(String.valueOf(this.bH), 96, 158, 17);
        }
        this.d(graphics);
        if (this.aT) {
            this.a(graphics, this.aV);
        }
        this.b(graphics, c.b[0][1]);
        if (this.bj) {
            this.a(graphics, "\u8be5\u57ce\u5df2\u7ecf\u65e0\u4eba\u628a\u5b88!");
            return;
        }
        if (this.bI) {
            this.a(graphics, "\u8bf7\u4efb\u547d\u65b0\u592a\u5b88");
        }
    }

    private final void O(Graphics graphics) {
        this.P(graphics);
        this.R(graphics);
    }

    private final void P(Graphics graphics) {
        int n = 0;
        while (n < 19) {
            int n2 = 0;
            while (n2 < 15) {
                int n3 = this.b.bk[n + this.u][n2 + this.t] & 0xF;
                switch (n3) {
                    case 0: {
                        n3 = 6;
                        break;
                    }
                    case 1: 
                    case 3: 
                    case 4: {
                        n3 = 16;
                        break;
                    }
                    case 2: {
                        n3 = 17;
                        break;
                    }
                    case 5: {
                        graphics.drawRegion(this.aE[4], n3 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
                        n3 = 7;
                        break;
                    }
                    case 6: {
                        n3 = 13;
                        break;
                    }
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 11: 
                    case 12: {
                        n3 = 9;
                    }
                }
                graphics.drawRegion(this.aE[4], n3 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
                n2 = (byte)(n2 + 1);
            }
            n = (byte)(n + 1);
        }
    }

    private final boolean a(int n, int n2) {
        return n >= this.t && n - this.t < 15;
    }

    private final boolean b(int n, int n2) {
        return (this.b.bk[n2][n] & 0xF) != 2;
    }

    public final void a(Graphics graphics, int n, int n2) {
        graphics.setColor(16275794);
        graphics.drawRect(n, n2, 15, 15);
        graphics.setColor(0xFFFFFF);
    }

    public final void a(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.setColor(10656072);
        graphics.fillRect(40, 50, 160, 200);
        graphics.setColor(0x996600);
        graphics.drawRect(40, 50, 160, 200);
        if (this.aE[3] == null) {
            this.aE[3] = this.k(15);
        }
        int n = 0;
        while (n < 8) {
            graphics.drawRegion(this.aE[3], n * 20, 0, 20, 20, 0, 50 + n % 2 * 70, 146 + n / 2 * 25, 0);
            n = (byte)(n + 1);
        }
        graphics.drawImage(this.aE[9], 55, 68, 20);
        graphics.drawRect(55, 68, this.aE[9].getWidth(), this.aE[9].getHeight());
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.H[this.X], 135, 75, 20);
        graphics.drawString("LV. " + String.valueOf(this.b.I[this.X]), 150, 100, 20);
        graphics.drawString(String.valueOf(this.b.N[this.X]), 75, 146, 20);
        graphics.drawString(String.valueOf(this.b.O[this.X]), 145, 146, 20);
        graphics.drawString(String.valueOf(this.b.M[this.X]), 75, 171, 20);
        graphics.drawString(String.valueOf(this.b.P[this.X]), 145, 171, 20);
        graphics.drawString(String.valueOf(this.b.K[this.X]), 75, 196, 20);
        graphics.drawString(String.valueOf(this.b.R[this.X]), 145, 196, 20);
        graphics.drawString(this.b.ae[this.b.S[this.X]], 75, 221, 20);
        graphics.drawString(this.b.ae[this.b.T[this.X]], 145, 221, 20);
    }

    private final void Q(Graphics graphics) {
        if (this.aE[7] == null) {
            this.aE[7] = this.k(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(38, 57, 164, 160);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 57, 164, 160);
        graphics.drawLine(38, 170, 202, 170);
        graphics.drawImage(this.aE[7], this.aN >> 1, 50, 17);
        graphics.setColor(0xFFFF00);
        graphics.drawString(c.e[this.M], this.aN >> 1, 175, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, c.r[this.M], 25, 70, 170, 100);
        if (this.M < this.N - 1) {
            graphics.drawString(c.e[this.M + 1], 190, 190, 24);
            graphics.drawImage(this.aF, 195, 180, 20);
        }
        if (this.M > 0) {
            graphics.drawString(c.e[this.M - 1], 50, 190, 20);
            graphics.drawRegion(this.aF, 0, 0, 6, 8, 2, 45, 180, 24);
        }
        this.g = "\u8bf7\u9009\u62e9\u4f7f\u7528\u8ba1\u7b56";
    }

    public final void b(Graphics graphics) {
        if (this.aM == null) {
            this.aM = this.k(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(38, 57, 164, 160);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 57, 164, 160);
        graphics.drawLine(38, 170, 202, 170);
        graphics.drawImage(this.aM, this.aN >> 1, 50, 17);
        graphics.setColor(0xFFFF00);
        graphics.drawString(c.l[this.M], this.aN >> 1, 175, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, c.s[this.M], 20, 70, 180, 100);
        graphics.drawString("\u5269\u4f59\u8ba1\u7b56\u70b9:" + String.valueOf(this.b.bL), this.aN >> 1, 120, 17);
        if (this.M < 5) {
            graphics.drawString(c.l[this.M + 1], 190, 190, 24);
            graphics.drawImage(this.aF, 195, 180, 20);
        }
        if (this.M > 0) {
            graphics.drawString(c.l[this.M - 1], 50, 190, 20);
            graphics.drawRegion(this.aF, 0, 0, 6, 8, 2, 45, 180, 24);
        }
    }

    public final void c(Graphics graphics) {
        int n;
        if (this.aE[8] == null) {
            this.aE[8] = this.k(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(38, 57, 164, 120);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 57, 164, 120);
        graphics.drawImage(this.aE[8], this.aN >> 1, 50, 17);
        int n2 = 0;
        while (n2 <= this.b.aU) {
            n = n2 % 3;
            int n3 = n2 / 3;
            if (n2 == this.b.aU) {
                if (this.b.Q[this.b.az] != 100) {
                    graphics.setColor(16275794);
                    graphics.drawString("\u4e0b\u91ce", 50 + 50 * n, 63 + 40 * n3, 20);
                }
            } else {
                graphics.setColor(0);
                graphics.drawString(this.b.i[this.b.aT[n2]], 50 + 50 * n, 63 + 40 * n3, 20);
            }
            n2 = (byte)(n2 + 1);
        }
        n2 = this.aP % 3;
        n = this.aP / 3;
        graphics.drawImage(this.aF, 42 + 50 * n2, 67 + 40 * n, 0);
    }

    private final void R(Graphics graphics) {
        try {
            int n;
            byte by = 0;
            while (by < this.b.bg) {
                if ((this.b.bs[by] == 0 || this.b.bs[by] > 3) && this.a((int)this.b.bo[by], (int)this.b.bp[by]) && this.b(this.b.bo[by], (int)this.b.bp[by])) {
                    n = this.b.K[this.b.be[by]] / 100;
                    graphics.drawRegion(this.aE[5], n * 15, (this.b.J[this.b.be[by]] - 1) * 15, 16, 16, 0, (this.b.bo[by] - this.t) * 16, (this.b.bp[by] - this.u) * 16, 20);
                }
                by = (byte)(by + 1);
            }
            by = 0;
            while (by < this.b.bh) {
                if ((this.b.bt[by] == 0 || this.b.bt[by] > 3) && this.a((int)this.b.bq[by], (int)this.b.br[by]) && this.b(this.b.bq[by], (int)this.b.br[by])) {
                    n = this.b.K[this.b.bf[by]] / 100;
                    graphics.drawRegion(this.aE[5], n * 15, (this.b.J[this.b.bf[by]] + 2) * 15, 16, 16, 0, (this.b.bq[by] - this.t) * 16, (this.b.br[by] - this.u) * 16, 20);
                }
                by = (byte)(by + 1);
            }
            if (this.b.bz) {
                if (!this.D) {
                    graphics.drawImage(this.aE[0], this.B * 16 + 8, this.C * 16 + 8, 20);
                } else {
                    this.a(graphics, (this.v - this.t) * 16, (this.w - this.u) * 16);
                }
            }
            graphics.drawRegion(this.aE[6], 0, 0, 240, 22, 0, 0, this.aO, 36);
            if (this.H) {
                this.g = "";
                graphics.drawRegion(this.aE[6], 0, 22, 240, 22, 0, 0, this.aO, 36);
                graphics.setColor(0xFFFF00);
                graphics.drawRect(14 + 36 * this.O, this.aO - 18, 31, 16);
            }
            graphics.setColor(0xFFFFFF);
            if (this.b.bz) {
                graphics.drawString("\u673a\u52a8\u529b:" + String.valueOf(this.b.bi), this.aN, 5, 24);
                graphics.drawString("\u7cae\u98df: " + String.valueOf(this.b.bb), this.aN, 25, 24);
                graphics.drawString("\u91d1\u5b50: " + String.valueOf(this.b.ba), this.aN, 45, 24);
            } else {
                graphics.drawString("\u673a\u52a8\u529b:" + String.valueOf(this.b.bj), this.aN, 5, 24);
                graphics.drawString("\u7cae\u98df: " + String.valueOf(this.b.bd), this.aN, 25, 24);
                graphics.drawString("\u91d1\u5b50: " + String.valueOf(this.b.bc), this.aN, 45, 24);
            }
            String string = this.b.by > 30 ? c.a(30) : c.a(this.b.by);
            graphics.drawString(string, 3, 3, 20);
            if (this.L) {
                this.a(graphics);
            }
            if (this.I) {
                this.Q(graphics);
            }
            if (this.P) {
                graphics.setColor(10656072);
                graphics.fillRect(36, 57, 167, 140);
                graphics.drawImage(this.aE[9], this.aN >> 1, 60, 17);
                graphics.setColor(0x996600);
                graphics.drawRect(36, 57, 167, 140);
                graphics.drawImage(this.aE[7], this.aN >> 1, 50, 17);
                graphics.setColor(0xFFFFFF);
                graphics.drawString(this.f(100), this.aN >> 1, 156, 17);
                this.B = (byte)(this.v - this.t);
                this.C = (byte)(this.w - this.u);
                this.g = "\u4f7f\u7528\u8ba1\u7b56: " + c.e[this.M];
            }
            if (this.Q) {
                this.c(graphics);
            }
            if (this.W > 0) {
                this.a(graphics, this.f((int)this.W));
            }
            if (this.T || this.U) {
                this.a(graphics, this.V, this.aW);
            } else {
                this.aE[1] = null;
            }
            this.d(graphics);
            return;
        }
        catch (Exception exception) {
            this.a("drawObject():" + exception.toString());
            return;
        }
    }

    private final void S(Graphics graphics) {
        if (this.b.aQ < 14) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, this.aN, this.aO);
            graphics.drawImage(this.aE[0], this.aN >> 1, 50, 17);
            graphics.setColor(0x996600);
            graphics.drawRect(38, 50, this.aE[0].getWidth(), this.aE[0].getHeight());
            graphics.drawImage(this.aE[1], this.aN >> 1, 41, 17);
            graphics.setColor(0xFFFFFF);
            this.a(graphics, this.ba, 40, 180, 170, 140);
            return;
        }
        this.a(graphics, this.ba);
    }

    public final void e() {
        this.a("AI\u4e8b\u4ef6\u7c7b\u578b:" + this.b.aQ);
        switch (this.b.aQ) {
            case 1: {
                this.aE[0] = this.k(120);
                this.ba = String.valueOf(this.b.H[this.b.j[this.b.aw]]) + " \u519b\u653b\u6253 " + this.b.i[this.b.ax] + "...";
                return;
            }
            case 2: {
                this.aE[0] = this.k(121);
                this.ba = String.valueOf(this.b.H[this.b.j[this.b.aw]]) + " \u5360\u9886\u7a7a\u57ce " + this.b.i[this.b.ax] + "!";
                return;
            }
            case 3: {
                this.aE[0] = this.k(120);
                this.ba = String.valueOf(this.b.H[0]) + " \u519b\u653b\u6253 " + this.b.i[0] + "...";
                return;
            }
            case 4: {
                this.aE[0] = this.k(122);
                this.ba = String.valueOf(this.b.H[this.b.j[this.b.aw]]) + " \u519b\u653b\u5360\u4e86 " + this.b.i[this.b.ax] + "!";
                return;
            }
            case 5: {
                this.aE[0] = this.k(123);
                this.ba = String.valueOf(this.b.H[this.b.j[this.b.aw]]) + " \u519b\u653b\u6253 " + this.b.i[this.b.ax] + "\u5931\u8d25\u4e86!";
                return;
            }
            case 6: {
                this.aE[0] = this.k(124);
                this.ba = " \u53d1\u751f\u65f1\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aU) {
                    this.ba = String.valueOf(this.ba) + " " + this.b.i[this.b.aT[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 7: {
                this.aE[0] = this.k(125);
                this.ba = "\u53d1\u751f\u6c34\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aU) {
                    this.ba = String.valueOf(this.ba) + " " + this.b.i[this.b.aT[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 8: {
                this.aE[0] = this.k(126);
                this.ba = "\u53d1\u751f\u8757\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aU) {
                    this.ba = String.valueOf(this.ba) + " " + this.b.i[this.b.aT[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 9: {
                this.aE[0] = this.k(127);
                this.ba = "\u53d1\u751f\u761f\u75ab!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aU) {
                    this.ba = String.valueOf(this.ba) + " " + this.b.i[this.b.aT[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 10: {
                this.aE[0] = this.k(128);
                this.ba = "\u6536\u91d1\u7684\u5b63\u8282\u5230\u4e86!";
                return;
            }
            case 11: {
                this.aE[0] = this.k(129);
                this.ba = "\u6536\u7cae\u7684\u5b63\u8282\u5230\u4e86!";
                return;
            }
            case 12: {
                this.aE[0] = this.k(130);
                this.ba = String.valueOf(this.b.H[this.b.az]) + "\u8ddf\u968f " + this.b.H[this.b.aA] + " \u52a0\u5165\u6211\u519b\u4e3a\u541b\u4e3b\u6548\u529b!";
                return;
            }
            case 13: {
                this.aE[0] = this.k(136);
                this.ba = "\u767e\u59d3\u53d1\u751f\u9a9a\u4e71,\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aU) {
                    this.ba = String.valueOf(this.ba) + " " + this.b.i[this.b.aT[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 14: {
                this.ba = String.valueOf(this.b.c[this.b.av]) + "\u3000\u540c\u76df\u89e3\u9664!";
                return;
            }
            case 15: {
                return;
            }
            case 16: {
                this.ba = String.valueOf(this.b.H[this.b.aA]) + "\u6b63\u4e0e\u654c\u65b9\u4f1a\u8c08\u4e2d...";
                return;
            }
            case 17: {
                this.ba = "\u7cdf\u4e86," + this.b.H[this.b.aA] + "\u6295\u5954\u4e86" + this.b.H[this.b.j[this.b.aw]];
                return;
            }
            case 18: {
                this.ba = String.valueOf(this.b.c[this.b.av]) + " \u52bf\u529b\u706d\u4ea1\u4e86!";
                return;
            }
            case 19: {
                this.ba = String.valueOf(this.b.H[this.b.aA]) + "\u6b7b\u4ea1,\u65b0\u541b\u4e3b " + this.b.c[this.b.av] + " \u7ee7\u4f4d!";
            }
        }
    }

    public final void a(byte by, short s, short s2) {
        this.aS = true;
        switch (by) {
            case 2: {
                this.ba = String.valueOf(this.b.H[s]) + "\u3000\u541b\u4e3b\u6b7b\u4ea1!";
                return;
            }
            case 5: {
                this.ba = String.valueOf(this.b.H[s]) + " \u52bf\u529b\u706d\u4ea1\u4e86!";
                return;
            }
            case 6: {
                this.ba = String.valueOf(this.b.H[s]) + "\u6b7b\u4ea1,\u65b0\u541b\u4e3b " + this.b.H[s2] + " \u7ee7\u4f4d!";
            }
        }
    }

    public final void keyPressed(int n) {
        switch (n) {
            case -2: 
            case 56: {
                this.b.getClass();
                this.a = (byte)2;
                return;
            }
            case -1: 
            case 50: {
                this.b.getClass();
                this.a = 1;
                return;
            }
            case -3: 
            case 52: {
                this.b.getClass();
                this.a = (byte)4;
                return;
            }
            case -4: 
            case 54: {
                this.b.getClass();
                this.a = (byte)8;
                return;
            }
            case -5: 
            case 53: {
                this.b.getClass();
                this.a = (byte)16;
                return;
            }
            case -7: 
            case 55: {
                this.b.getClass();
                this.a = (byte)32;
            }
        }
    }

    public final void f() {
        switch (this.aC) {
            case 4: {
                this.k();
                return;
            }
            case 5: {
                if (this.bj && this.a > 0) {
                    this.b.bn();
                    return;
                }
                this.V();
                return;
            }
            case 6: {
                this.aa();
                return;
            }
            case 7: {
                this.Z();
                return;
            }
            case 8: {
                this.Y();
                return;
            }
            case 9: {
                this.x();
                return;
            }
            case 10: {
                if (this.a <= 0) break;
                this.b.bm();
                return;
            }
            case 11: {
                if (this.a > 0) {
                    this.b.bm();
                }
            }
            case 12: {
                this.w();
                return;
            }
            case 13: {
                this.X();
                return;
            }
            case 14: {
                this.v();
                return;
            }
            case 15: {
                this.W();
                return;
            }
            case 16: {
                this.U();
                return;
            }
            case 17: {
                this.z();
                return;
            }
            case 18: {
                this.T();
                return;
            }
            case 23: {
                this.u();
                return;
            }
            case 24: {
                this.B();
            }
        }
    }

    private final void t() {
        switch (this.a) {
            case 32: {
                this.bX = 0;
                this.a((byte)1);
                return;
            }
            case 1: {
                if (this.bX <= 0) break;
                --this.bX;
                return;
            }
            case 2: {
                if (this.bX >= this.bW - this.bV) break;
                ++this.bX;
                return;
            }
            default: {
                return;
            }
        }
    }

    private final void u() {
        if (this.bQ || this.bP) {
            this.bQ = false;
            this.b.as = 1;
            this.o.a();
        }
        if (this.bR < 7) {
            this.g = this.bb = this.b.a((int)this.bR);
            this.bR = (byte)(this.bR + 1);
            return;
        }
        this.bQ = true;
    }

    private final void v() {
        switch (this.a) {
            case 1: {
                if (this.bq) {
                    this.a(this.bt, this.bs);
                    if (!this.a((int)this.bA[this.aP], this.bt)) break;
                    this.b(this.bt, this.bs);
                    return;
                }
                if (!this.aT) {
                    this.aP = 0;
                    return;
                }
                if (!this.aT) break;
                this.aT = false;
                this.aV = 0;
                return;
            }
            case 2: {
                if (this.bq) {
                    this.b(this.bt, this.bs);
                    if (!this.a((int)this.bA[this.aP], this.bt)) break;
                    this.a(this.bt, this.bs);
                    return;
                }
                if (this.aT) break;
                this.aT = true;
                return;
            }
            case 4: {
                if (this.bq) {
                    this.bs = this.j(this.bs);
                    return;
                }
                if (!(this.aT || this.bj || this.bI)) {
                    this.aP = 0;
                    return;
                }
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 8: {
                if (this.bq) {
                    this.bs = this.k(this.bs);
                    return;
                }
                if (!(this.aT || this.bj || this.bI)) {
                    this.aP = 1;
                    return;
                }
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (this.aT) {
                    if (this.aV == 0) {
                        this.b.a(this.bH, this.bG, 0);
                        return;
                    }
                    this.b.a(this.bH, this.bG, 1);
                    return;
                }
                if (!this.aT) {
                    if (this.bq) {
                        if (this.aP == 0) {
                            this.bH = this.a(this.bt);
                        } else {
                            this.bG = this.a(this.bt);
                        }
                    } else {
                        this.bt = this.aP == 0 ? this.a(this.bH) : this.a(this.bG);
                        this.bs = 0;
                    }
                    this.bq = !this.bq;
                    return;
                }
                if (!this.aT) break;
                this.aT = false;
                if (this.aV == 0) {
                    if (this.aP == 0) {
                        this.bH = this.a(this.bt);
                    } else {
                        this.bG = this.a(this.bt);
                    }
                    this.b.a(this.bH, this.bG, 0);
                    return;
                }
                this.b.a(this.bH, this.bG, 0);
            }
        }
    }

    private final void w() {
        if (this.by) {
            this.by = false;
            this.aT = false;
            this.bq = true;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.bq) {
                    this.a(this.bt, this.bs);
                    int n = this.b.m[this.b.aw];
                    if (n > 100) {
                        n = 100;
                    }
                    if (!this.a(n, this.bt)) break;
                    this.b(this.bt, this.bs);
                    return;
                }
                this.aT = false;
                return;
            }
            case 2: {
                if (this.bq) {
                    this.b(this.bt, this.bs);
                    int n = this.b.m[this.b.aw];
                    if (n > 100) {
                        n = 100;
                    }
                    if (!this.a(n, this.bt)) break;
                    this.a(this.bt, this.bs);
                    return;
                }
                this.aT = true;
                return;
            }
            case 4: {
                if (this.bq) {
                    this.bs = this.j(this.bs);
                    return;
                }
                if (!this.aT) {
                    this.aP = 0;
                    return;
                }
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 8: {
                if (this.bq) {
                    this.bs = this.k(this.bs);
                    return;
                }
                if (!this.aT) {
                    if (this.b.x[this.b.aw] <= 0) break;
                    this.aP = 1;
                    this.bB = 0;
                    this.bt = this.a(this.bB);
                    return;
                }
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (this.aP == 0) {
                    this.bq = !this.bq;
                    this.bs = 0;
                    this.bB = this.a(this.bt);
                    this.a("gold:" + this.bB);
                }
                if (!this.aT) break;
                if (this.by) {
                    this.by = false;
                    this.aT = false;
                    return;
                }
                if (this.aV == 0) {
                    if (this.aP == 0) {
                        if (this.bB == 0) {
                            this.by = true;
                            return;
                        }
                        this.by = false;
                        this.b.a(this.bB, 0);
                        this.a("go here");
                        return;
                    }
                    this.b.a((short)200, 0);
                    return;
                }
                this.b.a((short)0, 1);
                return;
            }
            case 32: {
                this.b.a((short)0, 1);
            }
        }
    }

    private final void x() {
        if (this.by && this.a > 0) {
            this.by = false;
            this.aT = false;
            this.aP = 0;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.aP == 3) {
                    this.aT = false;
                }
                if (!this.bq && !this.aT) {
                    if (this.aP > 0) {
                        this.aP = (byte)(this.aP - 1);
                    }
                    this.bt = this.a(this.bz[this.aP]);
                    return;
                }
                if (!this.bq) break;
                this.a(this.bt, this.bs);
                if (!this.a((int)this.bA[this.aP], this.bt)) break;
                this.b(this.bt, this.bs);
                return;
            }
            case 2: {
                if (!this.bq && !this.aT) {
                    if (this.aP < 3) {
                        this.aP = (byte)(this.aP + 1);
                    }
                    if (this.aP == 3) {
                        this.aT = true;
                        return;
                    }
                    this.bt = this.a(this.bz[this.aP]);
                    return;
                }
                if (!this.bq) break;
                this.b(this.bt, this.bs);
                if (!this.a((int)this.bA[this.aP], this.bt)) break;
                this.a(this.bt, this.bs);
                return;
            }
            case 4: {
                if (!this.aT && this.bs < 3) {
                    this.bs = (byte)(this.bs + 1);
                }
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 8: {
                if (!this.aT && this.bs > 0) {
                    this.bs = (byte)(this.bs - 1);
                }
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (!this.aT) {
                    if (this.bq) {
                        this.bz[this.aP] = this.a(this.bt);
                    } else {
                        this.bt = this.a(this.bz[this.aP]);
                    }
                    this.bq = !this.bq;
                    return;
                }
                if (this.aV == 0) {
                    if (!this.y()) {
                        this.b.a(0, this.bz);
                        return;
                    }
                    this.by = true;
                    return;
                }
                this.b.a(1, this.bz);
            }
        }
    }

    private final boolean y() {
        int n = 0;
        while (n < 3) {
            if (this.bz[n] > 0) {
                return false;
            }
            n = (byte)(n + 1);
        }
        return true;
    }

    private final void z() {
        switch (this.a) {
            case 1: {
                if (this.aT || this.aP <= 0) break;
                this.aP = (byte)(this.aP - 1);
                return;
            }
            case 2: {
                if (this.aT || this.aP >= 3) break;
                this.aP = (byte)(this.aP + 1);
                return;
            }
            case 4: {
                if (!this.aT && this.aX > 0) {
                    this.aX = (byte)(this.aX - 1);
                    this.aP = 0;
                    return;
                }
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 8: {
                if (!this.aT && this.aX < 3) {
                    this.aX = (byte)(this.aX + 1);
                    this.aP = 0;
                    return;
                }
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (!this.aT) {
                    this.aT = true;
                    return;
                }
                if (this.aV == 0) {
                    this.A();
                    return;
                }
                if (this.aA) {
                    this.b.as = (byte)2;
                    return;
                }
                this.b.p(1);
                return;
            }
            case 32: {
                if (this.aA) {
                    this.b.as = (byte)2;
                    return;
                }
                this.b.p(1);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void A() {
        byte by = this.a(this.aX, this.aP);
        if (by == 15 || by == 23) {
            if (this.b.N[this.b.az] > 95) {
                if (this.g(by)) {
                    if (!this.aA) {
                        this.b.z[this.b.aw] = 1;
                        this.b.p(0);
                        return;
                    }
                    this.b.A[this.b.aZ] = 1;
                    if (!this.aA) return;
                    this.b.as = 1;
                    return;
                }
                if (this.aA) {
                    this.b.as = (byte)3;
                    return;
                }
                this.b.p(2);
                return;
            }
            if (this.aA) {
                this.b.as = (byte)4;
                return;
            }
            this.b.p(3);
            return;
        }
        if (this.g(by)) {
            if (this.aA) {
                this.b.as = 1;
                return;
            }
            this.b.p(0);
            return;
        }
        if (this.aA) {
            this.b.as = (byte)3;
            return;
        }
        this.b.p(2);
    }

    private final boolean g(byte by) {
        short s = this.b.af[by];
        if (this.aA) {
            if (this.b.ba < s) {
                return false;
            }
            this.b.ba = (short)(this.b.ba - s);
            if (this.aX == 3) {
                this.b.T[this.X] = by;
            } else {
                this.b.S[this.X] = by;
            }
        } else {
            if (this.b.m[this.b.aw] < s) {
                return false;
            }
            byte by2 = this.b.aw;
            this.b.m[by2] = (short)(this.b.m[by2] - s);
            if (this.aX == 3) {
                this.b.T[this.b.az] = by;
            } else {
                this.b.S[this.b.az] = by;
            }
        }
        return true;
    }

    private final void B() {
        switch (this.a) {
            case 1: {
                if (this.bd <= 0) break;
                this.bd = (byte)(this.bd - 1);
                return;
            }
            case 2: {
                if (this.bd >= 2) break;
                this.bd = (byte)(this.bd + 1);
                return;
            }
            case 4: {
                if (this.aT) {
                    this.aV = 0;
                    return;
                }
                byte by = this.bd;
                this.m[by] = (byte)(this.m[by] - 1);
                if (this.m[this.bd] >= 0) break;
                this.m[this.bd] = 0;
                return;
            }
            case 8: {
                if (this.aT) {
                    this.aV = 1;
                    return;
                }
                byte by = this.bd;
                this.m[by] = (byte)(this.m[by] + 1);
                if (this.bd < 2 && this.m[this.bd] > 1) {
                    this.m[this.bd] = 1;
                }
                if (this.bd != 2 || this.m[this.bd] <= 4) break;
                this.m[this.bd] = 4;
                return;
            }
            case 16: {
                if (!this.aT) {
                    this.aT = true;
                    return;
                }
                if (this.aV == 0) {
                    this.k = this.m[2];
                    this.l = this.m[0] == 0;
                    if (this.l) {
                        if (this.j == 0) {
                            this.b(1);
                        } else {
                            this.c(this.k);
                        }
                    } else if (this.j > 0) {
                        this.c(this.k);
                        this.p();
                    }
                }
                this.a((byte)6);
                return;
            }
            case 32: {
                this.a((byte)6);
            }
        }
    }

    public final void g() {
        if (this.aS) {
            this.aS = false;
            return;
        }
        if (this.az > -1) {
            this.az = (byte)-1;
            return;
        }
        if (this.au == 100) {
            this.au = 0;
            return;
        }
        if (this.au > 0) {
            this.am = this.au;
            return;
        }
        switch (this.a) {
            case 4: {
                if (this.ap <= 0) break;
                this.ap = (byte)(this.ap - 1);
                return;
            }
            case 8: {
                if (this.ap >= 5) break;
                this.ap = (byte)(this.ap + 1);
                return;
            }
            case 16: {
                if (this.ap == 5 && this.b.Q[this.X] == 100) {
                    this.ba = c.u;
                    this.aS = true;
                    return;
                }
                if (!this.ax && this.ar && this.ap == 3) {
                    this.au = (byte)2;
                    this.ax = true;
                    this.ay = "\u826f\u79bd\u62e9\u6728,\u4f55\u4e0d\u8d81\u6b64\u53e6\u6295\u660e\u4e3b...";
                    return;
                }
                this.ar = false;
            }
        }
    }

    private final void d(int n) {
        int n2 = 4;
        if (this.b.ch == 0) {
            n2 = 5;
        }
        switch (n) {
            case 2: {
                if (this.b.ch <= 0) break;
                this.b.ch = (byte)(this.b.ch - 1);
                return;
            }
            case 4: {
                if (this.b.cg[this.b.ch] <= 0) break;
                byte by = this.b.ch;
                this.b.cg[by] = (byte)(this.b.cg[by] - 1);
                return;
            }
            case 6: {
                if (this.b.cg[this.b.ch] >= n2 - 1) break;
                byte by = this.b.ch;
                this.b.cg[by] = (byte)(this.b.cg[by] + 1);
                return;
            }
            case 8: {
                if (this.b.ch >= 3) break;
                this.b.ch = (byte)(this.b.ch + 1);
            }
        }
    }

    private final void e(int n) {
        switch (n) {
            case 4: {
                if (this.bK <= 0) break;
                this.bK = (byte)(this.bK - 1);
                return;
            }
            case 6: {
                if (this.bK >= 3) break;
                this.bK = (byte)(this.bK + 1);
            }
        }
    }

    public final void h() {
        if (this.aS) {
            this.aS = false;
            if (this.ag) {
                this.ag = false;
                this.b.as = (byte)2;
                return;
            }
            this.b.cf = 1;
            if (this.bM) {
                this.bM = false;
                this.b.as = 1;
            }
            this.b.cg[0] = 1;
            return;
        }
        if (this.ak) {
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.af != 2 || this.bN) break;
                this.d(2);
                return;
            }
            case 2: {
                if (this.af != 2 || this.bN) break;
                this.d(8);
                return;
            }
            case 4: {
                if (this.af == 1) {
                    this.e(4);
                    return;
                }
                if (this.b.cf == 1) {
                    if (this.aa <= 0) break;
                    this.aa = (byte)(this.aa - 1);
                    return;
                }
                if (this.bN && this.M > 0) {
                    this.M = (byte)(this.M - 1);
                    return;
                }
                if (this.bN) break;
                this.d(4);
                return;
            }
            case 8: {
                if (this.af == 1) {
                    this.e(6);
                    return;
                }
                if (this.b.cf == 1) break;
                if (this.bN && this.M < 5) {
                    this.M = (byte)(this.M + 1);
                    return;
                }
                if (this.I) break;
                this.d(6);
                return;
            }
            case 16: {
                this.C();
                return;
            }
            case 32: {
                if (this.b.cf == 2) {
                    this.b.cf = 1;
                }
                if (!this.bN) break;
                this.bN = false;
                this.M = 0;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void C() {
        switch (this.af) {
            case 1: {
                this.af = (byte)(this.af + 1);
                this.aL = null;
                return;
            }
            case 2: {
                if (this.bN) {
                    this.bN = false;
                    this.aS = true;
                    if (this.b.bL < this.b.b(this.M)) {
                        this.ba = "\u6218\u672f\u80fd\u529b\u4e0d\u591f";
                        return;
                    }
                    if (this.b.a(this.M)) {
                        this.ba = "\u8ba1\u7b56[" + c.l[this.M].substring(0, 2) + "] \u65bd\u7528\u6210\u529f\uff01";
                        if (this.M == 1) {
                            this.bM = true;
                            return;
                        }
                        if (this.M != 5) return;
                        this.ak = true;
                        this.b.cl = (byte)40;
                        this.aS = false;
                        this.b.ch = 0;
                        this.b.cg[0] = 0;
                        this.b.cf = 1;
                        return;
                    } else {
                        this.ba = "\u8ba1\u7b56[" + c.l[this.M].substring(0, 2) + "] \u65bd\u7528\u5931\u8d25\uff01";
                    }
                    return;
                }
                if (this.b.cf == 1) {
                    this.b.cf = (byte)2;
                    return;
                }
                if (this.b.cg[0] == 4) {
                    if (this.b.ca != 0) {
                        this.aS = true;
                        this.ba = "\u8ba1\u7b56\u4f7f\u7528\u4e2d!";
                        this.b.cg[0] = 0;
                        return;
                    }
                    this.bN = true;
                    this.M = 0;
                } else {
                    this.b.cf = 1;
                }
                this.b.ch = 0;
                return;
            }
        }
    }

    private final String f(int n) {
        String string = "";
        switch (n) {
            case 2: {
                string = "\u654c\u519b\u653b\u6253\u6211\u519b!";
                break;
            }
            case 3: {
                string = "\u654c\u5c06\u9041\u9003!";
                break;
            }
            case 4: {
                string = this.b.bB;
                break;
            }
            case 5: {
                string = "\u654c\u65b9\u5168\u519b\u9003\u7a9c!";
                break;
            }
            case 6: {
                string = "\u654c\u65b9\u7cae\u8349\u8017\u7aed,\u5df2\u5168\u519b\u9003\u7a9c!";
                break;
            }
            case 8: {
                string = "\u541b\u4e3b\u6218\u4ea1!";
                this.b.as = 1;
                break;
            }
            case 10: {
                string = "\u9000\u51fa\u6751\u5e84!";
                break;
            }
            case 11: {
                string = "\u6b66\u5668\u8d2d\u4e70\u6210\u529f!";
                break;
            }
            case 12: {
                string = "\u91d1\u5b50\u4e0d\u8db3,\u65e0\u6cd5\u8d2d\u4e70\u5175\u5668!";
                break;
            }
            case 13: {
                string = "\u9601\u4e0b\u65e0\u80fd\u529b\u8d2d\u4e70\u6b64\u5175\u5668!";
                break;
            }
            case 14: {
                string = "\u8d2d\u4e70\u6210\u529f!";
                break;
            }
            case 15: {
                string = "\u5f53\u524d\u6b66\u5c06\u6bcb\u9700\u6cbb\u7597!";
                break;
            }
            case 16: {
                string = "\u91d1\u5b50\u4e0d\u591f,\u65e0\u6cd5\u8fdb\u884c\u6cbb\u7597!";
                break;
            }
            case 17: {
                string = "\u6b66\u5c06\u4f53\u529b\u6062\u590d\u4e86";
                break;
            }
            case 9: {
                string = "\u541b\u4e3b\u6240\u5728\u57ce\u5e02\u88ab\u653b\u5360,\u65e0\u53ef\u9000\u57ce\u5e02!";
                break;
            }
            case 19: {
                string = "\u6b66\u5c06\u4e2d\u8ba1,\u65e0\u6cd5\u52a8\u5f39!";
                break;
            }
            case 18: {
                string = "\u65e0\u57ce\u53ef\u9000\uff0c\u80cc\u6c34\u4e00\u6218\u5427";
                break;
            }
            case 7: {
                string = "\u6211\u519b\u7cae\u8349\u8017\u7aed,\u5168\u519b\u64a4\u9000!";
                break;
            }
            case 20: {
                string = "\u6218\u4e89\u56de\u5408\u7ed3\u675f\uff01";
                break;
            }
            case 21: {
                string = "\u654c\u519b\u5360\u9886\u6211\u65b9\u57ce\u6c60!";
                break;
            }
            case 100: {
                string = this.b.bB;
            }
        }
        return string;
    }

    public final void i() {
        if (this.aS) {
            this.aS = false;
            return;
        }
        if (this.P) {
            this.P = false;
            this.H = true;
            this.J = false;
            return;
        }
        switch (this.W) {
            case 0: {
                break;
            }
            case 2: 
            case 3: {
                this.b.as = 1;
                this.W = 0;
                return;
            }
            case 4: {
                this.W = 0;
                break;
            }
            case 8: {
                this.W = 0;
                this.b.as = 1;
                return;
            }
            case 5: 
            case 6: {
                this.W = 0;
                this.b.I();
                this.b.as = (byte)4;
                break;
            }
            case 7: 
            case 9: 
            case 20: 
            case 21: {
                this.W = 0;
                this.J = false;
                this.K = true;
                this.b.g(this.b.be[0]);
                this.g = "\u64a4\u9000\u6b66\u5c06 " + this.b.H[this.b.be[0]];
                return;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: {
                this.b.as = 1;
            }
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: {
                this.W = 0;
                break;
            }
            case 100: {
                this.W = 0;
                this.b.as = 1;
                return;
            }
        }
        if (this.L) {
            this.L = false;
            this.g = "\u8bf7\u9009\u62e9\u8981\u4fa6\u5bdf\u7684\u6b66\u5c06";
            return;
        }
        switch (this.a) {
            case 1: {
                this.N();
                return;
            }
            case 2: {
                this.O();
                return;
            }
            case 4: {
                this.L();
                return;
            }
            case 8: {
                this.M();
                return;
            }
            case 16: {
                this.H();
                return;
            }
            case 32: {
                if (this.K) {
                    if (this.E) {
                        this.H = true;
                        this.J = false;
                        this.E = false;
                        this.K = false;
                        this.B = this.x;
                        this.C = this.y;
                        this.t = this.z;
                        this.u = this.A;
                        return;
                    }
                    if (this.D) {
                        if (!this.q) {
                            this.b.bo[this.p] = this.x;
                            this.b.bp[this.p] = this.y;
                            this.s = (byte)3;
                            this.v = this.x;
                            this.w = this.y;
                            this.B = (byte)(this.v - this.t);
                            this.C = (byte)(this.w - this.u);
                            return;
                        }
                        if (this.S) {
                            this.S = false;
                            this.v = this.x;
                            this.w = this.y;
                            this.b.bi = this.R;
                            this.b.bo[this.p] = this.v;
                            this.b.bp[this.p] = this.w;
                            this.B = (byte)(this.v - this.t);
                            this.C = (byte)(this.w - this.u);
                            return;
                        }
                        this.K = false;
                        this.D = false;
                        this.H = true;
                        this.J = false;
                        byte[] byArray = this.b.bk[this.y];
                        byte by = this.x;
                        byArray[by] = (byte)(byArray[by] | 0x40);
                        return;
                    }
                    if (this.F) {
                        this.I = false;
                        this.M = 0;
                        this.H = true;
                        this.J = false;
                        this.F = false;
                        this.K = false;
                        this.t = this.z;
                        this.u = this.A;
                        this.B = this.x;
                        this.C = this.y;
                        return;
                    }
                    if (this.G) {
                        this.G = false;
                        this.H = true;
                        this.J = false;
                        return;
                    }
                    if (this.Q && !this.Z) {
                        this.Q = false;
                        this.K = false;
                        this.H = true;
                        this.J = false;
                    }
                    return;
                }
                if (!this.H) break;
                this.O = 0;
                this.H = false;
                this.J = true;
                this.g = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
            }
        }
    }

    private final void D() {
        if (this.b.M[this.X] == this.b.L[this.X]) {
            this.J = true;
            this.W = (byte)15;
            return;
        }
        if (this.b.ba < 50) {
            this.J = true;
            this.W = (byte)16;
            return;
        }
        this.V = "\u662f\u5426\u5bf9\u6b66\u5c06\u8fdb\u884c\u6cbb\u7597?";
        this.U = true;
    }

    private final void E() {
        if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 1) {
            byte by = 0;
            while (by < this.b.bg) {
                if (this.b.bo[by] == (byte)(this.B + this.t) && this.b.bp[by] == (byte)(this.C + this.u) && (this.b.bs[by] == 0 || this.b.bs[by] > 3)) {
                    this.J = false;
                    this.H = true;
                    this.X = this.b.be[by];
                    this.v = this.b.bo[by];
                    this.w = this.b.bp[by];
                    this.p = by;
                }
                by = (byte)(by + 1);
            }
        }
    }

    private final void h(byte by) {
        switch (by) {
            case 0: {
                this.D = true;
                this.g = "\u6b66\u5c06\u79fb\u52a8";
                byte by2 = 0;
                while (by2 < this.b.bg) {
                    if (this.b.bo[by2] == this.B + this.t && this.b.bp[by2] == this.C + this.u) {
                        this.D = true;
                        this.X = this.b.be[by2];
                        this.v = (byte)(this.B + this.t);
                        this.w = (byte)(this.C + this.u);
                        this.p = by2;
                        this.x = this.v;
                        this.y = this.w;
                        this.R = this.b.bi;
                        byte[] byArray = this.b.bk[this.w];
                        byte by3 = this.v;
                        byArray[by3] = (byte)(byArray[by3] & 0x3F);
                        return;
                    }
                    by2 = (byte)(by2 + 1);
                }
                return;
            }
            case 1: {
                this.F = true;
                this.I = true;
                this.x = (byte)this.B;
                this.y = (byte)this.C;
                this.z = this.t;
                this.A = this.u;
                this.N = this.b.d(this.X);
                return;
            }
            case 2: {
                if ((this.b.i(this.v, this.w) & 0x10) == 16) {
                    this.G = true;
                    this.g = "\u9009\u62e9\u6b32\u653b\u51fb\u654c\u5c06";
                    return;
                }
                this.g = "\u90bb\u8fd1\u65e0\u654c\u5c06!";
                this.J = true;
                this.K = false;
                return;
            }
            case 3: {
                this.E = true;
                this.x = (byte)this.B;
                this.y = (byte)this.C;
                this.z = this.t;
                this.A = this.u;
                this.g = "\u8bf7\u9009\u62e9\u8981\u4fa6\u5bdf\u7684\u6b66\u5c06";
                return;
            }
            case 4: {
                this.b.g(this.X);
                if (this.W == 18) {
                    this.Q = false;
                    this.K = false;
                    this.J = true;
                    return;
                }
                this.g = "\u64a4\u9000\u6b66\u5c06 " + this.b.H[this.X];
                return;
            }
            case 5: {
                this.J = true;
                this.K = false;
                this.g = "\u6211\u519b\u5f85\u673a\u4e2d...";
                this.b.as = 1;
            }
        }
    }

    public final void j() {
        this.a("call doSelPos()");
        if (!this.r) {
            this.X = this.b.be[this.p];
            this.v = this.b.bo[this.p];
            this.w = this.b.bp[this.p];
            this.x = this.b.bo[this.p];
            this.y = this.b.bp[this.p];
            this.B = (byte)(this.v - this.t);
            this.C = (byte)(this.w - this.u);
            this.K = true;
            this.D = true;
            byte[] byArray = this.b.bk[this.w];
            byte by = this.v;
            byArray[by] = (byte)(byArray[by] & 0x3F);
            this.s = (byte)3;
            this.r = true;
        }
        this.g = "\u521d\u59cb\u4f4d\u7f6e(\u654c:" + c.i[this.b.bw] + c.j[this.b.bx] + ")";
    }

    private final void F() {
        this.b.bo[this.p] = this.v;
        this.b.bp[this.p] = this.w;
        byte[] byArray = this.b.bk[this.w];
        byte by = this.v;
        byArray[by] = (byte)(byArray[by] | 0x40);
        this.K = false;
        this.D = false;
        this.S = false;
        if (!this.q) {
            if (this.p == this.b.bg - 1) {
                this.q = true;
                this.p = 0;
                this.J = true;
                this.s = (byte)3;
                this.b.as = 1;
                this.g = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
                return;
            }
            this.K = true;
            this.D = true;
            this.p = (byte)(this.p + 1);
            this.r = false;
            this.j();
            return;
        }
        if (this.i(this.b.bk[this.w][this.v])) {
            this.V = "\u662f\u5426\u8fdb\u5165\u6751\u5e84?";
            this.T = true;
            return;
        }
        this.J = true;
        this.g = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
    }

    private final boolean i(byte by) {
        byte by2 = (byte)(by & 0xF);
        return by2 == 3 || by2 == 4 || by2 == 1;
    }

    private final void G() {
        if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 2) {
            byte by = 0;
            while (by < this.b.bh) {
                if ((this.b.bt[by] <= 0 || this.b.bt[by] > 3) && this.b.bq[by] == (byte)(this.B + this.t) && this.b.br[by] == (byte)(this.C + this.u)) {
                    this.X = this.b.bf[by];
                    this.aE[9] = null;
                    this.aE[9] = this.b(this.X);
                    this.L = true;
                    this.g = "\u6b66\u5c06\u4fe1\u606f";
                    return;
                }
                by = (byte)(by + 1);
            }
            return;
        }
        if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 1) {
            byte by = 0;
            while (by < this.b.bg) {
                if (this.b.bo[by] == (byte)(this.B + this.t) && this.b.bp[by] == (byte)(this.C + this.u)) {
                    this.X = this.b.be[by];
                    this.aE[9] = null;
                    this.aE[9] = this.b(this.X);
                    this.L = true;
                    this.g = "\u6b66\u5c06\u4fe1\u606f";
                    return;
                }
                by = (byte)(by + 1);
            }
        }
    }

    private final void H() {
        if (this.U) {
            if (this.aW == 0) {
                this.b.ba = (short)(this.b.ba - 50);
                short s = this.X;
                this.b.M[s] = (byte)(this.b.M[s] + this.b.bD());
                if (this.b.M[this.X] > this.b.L[this.X] || this.b.M[this.X] < 0) {
                    this.b.M[this.X] = this.b.L[this.X];
                }
                this.W = (byte)17;
            } else {
                this.aW = 0;
            }
            this.J = true;
            this.U = false;
            return;
        }
        if (this.T) {
            if (this.aW == 0) {
                byte by = (byte)(this.b.bk[this.w][this.v] & 0xF);
                if (by == 4) {
                    this.D();
                } else if (by == 1) {
                    this.b.as = (byte)5;
                } else if (by == 3) {
                    this.b.as = (byte)6;
                }
            } else {
                this.aW = 0;
                this.J = true;
            }
            this.T = false;
            return;
        }
        if (this.J) {
            this.E();
            return;
        }
        if (this.H) {
            this.H = false;
            if (this.O == 0 && this.b.bs[this.p] > 3) {
                this.W = (byte)19;
                this.J = true;
                return;
            }
            this.h(this.O);
            this.O = 0;
            this.K = true;
            return;
        }
        if (this.K) {
            if (this.D) {
                this.F();
                return;
            }
            if (this.E) {
                this.G();
                return;
            }
            if (this.G) {
                this.K();
                return;
            }
            if (this.F) {
                this.I();
                return;
            }
            if (this.Q) {
                if (this.aP == this.b.aU) {
                    this.b.i((byte)0);
                } else {
                    this.b.i(this.b.aT[this.aP]);
                }
                this.aP = 0;
                if (!this.Z) {
                    this.K = false;
                    this.V = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
                    this.J = true;
                    return;
                }
                this.g = "\u64a4\u9000\u6b66\u5c06 " + this.b.H[this.b.az];
            }
        }
    }

    private final void I() {
        byte by = 0;
        if (this.I) {
            by = this.b.g(this.M);
            if (by > this.b.bi) {
                this.g = "\u673a\u52a8\u529b\u4e0d\u8db3";
                this.I = false;
                this.F = false;
                this.M = 0;
                this.K = false;
                this.J = true;
                return;
            }
            this.I = false;
            this.x = (byte)this.B;
            this.y = (byte)this.C;
            this.z = this.t;
            this.A = this.u;
            this.g = "\u9009\u62e9\u8ba1\u7b56\u5b9e\u65bd\u5730\u70b9";
            return;
        }
        if (this.M != 11) {
            if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 2) {
                byte by2 = 0;
                while (by2 < this.b.bh) {
                    if ((this.b.bt[by2] <= 0 || this.b.bt[by2] > 3) && this.b.bq[by2] == (byte)(this.B + this.t) && this.b.br[by2] == (byte)(this.C + this.u)) {
                        if (this.b.a((int)this.p, (int)by2, this.M, true)) {
                            this.P = true;
                            this.aE[9] = null;
                            this.aE[9] = this.k(this.M + 33);
                            this.Y = this.b.bf[by2];
                            int n = this.b.a(this.X, this.Y, this.M, this.b.bd);
                            if (n > 0) {
                                this.b.a(n, this.M);
                                short s = this.b.K[this.X];
                                this.b.a(this.Y, this.b.bt, by2, this.p, n, true);
                                short s2 = this.X;
                                this.b.R[s2] = (short)(this.b.R[s2] + (s - this.b.K[this.Y]));
                                this.b.f(this.X);
                            } else {
                                this.b.bB = "\u8ba1\u7b56\u5b9e\u65bd\u5931\u8d25!";
                            }
                            this.b.bi = (byte)(this.b.bi - this.b.g(this.M));
                            this.F = false;
                            this.K = false;
                            this.J = true;
                        } else {
                            this.g = "\u6b64\u5730\u65e0\u6cd5\u4f7f\u7528\u8be5\u8ba1\u7b56";
                            this.F = false;
                            this.K = false;
                            this.J = true;
                            this.M = 0;
                        }
                    }
                    by2 = (byte)(by2 + 1);
                }
                return;
            }
        } else if (this.b.bk[this.C + this.u][this.B + this.t] == 5 && this.b.K[this.X] >= 100) {
            this.P = true;
            this.b.bi = (byte)(this.b.bi - this.b.g(this.M));
            this.b.bB = "\u57cb\u4f0f\u5b8c\u6bd5";
            this.F = false;
            this.M = 0;
            this.K = false;
            this.J = true;
            short s = this.X;
            this.b.K[s] = (short)(this.b.K[s] - 100);
            byte[] byArray = this.b.bk[this.C + this.u];
            int n = this.B + this.t;
            byArray[n] = (byte)(byArray[n] | 0x20);
        }
    }

    private final boolean J() {
        return Math.abs(this.v - this.t - this.B + (this.w - this.u - this.C)) == 1;
    }

    private final void K() {
        if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 2 && this.J()) {
            if (this.b.bi >= 2) {
                byte by = 0;
                while (by < this.b.bh) {
                    if ((this.b.bt[by] <= 0 || this.b.bt[by] > 3) && this.b.bq[by] == (byte)(this.B + this.t) && this.b.br[by] == (byte)(this.u + this.C)) {
                        this.Y = this.b.bf[by];
                        this.b.bi = (byte)(this.b.bi - 2);
                        this.b.bF = (byte)(this.b.bk[this.C + this.u][this.B + this.t] & 0xF);
                        this.b.az = this.X;
                        this.b.aA = this.Y;
                        this.b.as = (byte)2;
                        return;
                    }
                    by = (byte)(by + 1);
                }
            } else {
                this.g = "\u673a\u52a8\u529b\u4e0d\u8db3";
            }
        } else {
            this.g = "\u653b\u51fb\u65e0\u6548!";
        }
        this.G = false;
        this.K = false;
        this.J = true;
    }

    private final void g(int n) {
        switch (n) {
            case 4: {
                if (this.O > 0) {
                    this.O = (byte)(this.O - 1);
                    return;
                }
                this.O = (byte)5;
                return;
            }
            case 6: {
                if (this.O < 5) {
                    this.O = (byte)(this.O + 1);
                    return;
                }
                this.O = 0;
            }
        }
    }

    private final void h(int n) {
        switch (n) {
            case 2: {
                if (this.C <= 0) break;
                this.C = (short)(this.C - 1);
                break;
            }
            case 4: {
                if (this.B > 0) {
                    this.B = (short)(this.B - 1);
                    break;
                }
                if (this.t <= 0) break;
                this.t = (byte)(this.t - 1);
                break;
            }
            case 6: {
                if (this.B < 14) {
                    this.B = (short)(this.B + 1);
                    break;
                }
                if (this.t >= this.b.bl - 15) break;
                this.t = (byte)(this.t + 1);
                break;
            }
            case 8: {
                if (this.C >= 18) break;
                this.C = (short)(this.C + 1);
            }
        }
        this.o();
    }

    private final void i(int n) {
        switch (n) {
            case 4: {
                this.M = (byte)(this.M - 1);
                if (this.M >= 0) break;
                this.M = (byte)(this.M + 1);
                return;
            }
            case 6: {
                this.M = (byte)(this.M + 1);
                if (this.M <= this.N - 1) break;
                this.M = (byte)(this.M - 1);
            }
        }
    }

    private final void j(int n) {
        this.a("go to selCity");
        switch (n) {
            case 4: {
                this.aP = (byte)(this.aP - 1);
                if (this.aP >= 0) break;
                this.aP = 0;
                return;
            }
            case 6: {
                this.aP = (byte)(this.aP + 1);
                if (this.aP < this.b.aU) break;
                if (this.b.Q[this.b.az] != 100) {
                    this.aP = this.b.aU;
                    return;
                }
                this.aP = (byte)(this.aP - 1);
            }
        }
    }

    private final void L() {
        if (this.T || this.U) {
            this.aW = 0;
            return;
        }
        if (this.D && this.v > 0) {
            this.Q();
            return;
        }
        if (this.H) {
            this.g(4);
            return;
        }
        if (this.J || this.E && !this.L || this.F && !this.I || this.G) {
            this.h(4);
            return;
        }
        if (this.I) {
            this.i(4);
            return;
        }
        if (this.Q) {
            this.j(4);
        }
    }

    private final void M() {
        if (this.T || this.U) {
            this.aW = 1;
            return;
        }
        if (this.D && this.v < this.b.bl - 2) {
            this.R();
            return;
        }
        if (this.H) {
            this.g(6);
            return;
        }
        if (this.J || this.E && !this.L || this.F && !this.I || this.G) {
            this.h(6);
            return;
        }
        if (this.I) {
            this.i(6);
            return;
        }
        if (this.Q) {
            this.j(6);
        }
    }

    private final void N() {
        if (this.D && this.w > 0) {
            this.P();
            return;
        }
        if (this.I) {
            this.i(2);
            return;
        }
        if (this.J || this.E && !this.L || this.F && !this.I || this.G) {
            this.h(2);
        }
    }

    private final void O() {
        if (this.D && this.w < this.b.bm - 2) {
            this.S();
            return;
        }
        if (this.I) {
            this.i(8);
            return;
        }
        if (this.J || this.E && !this.L || this.F && !this.I || this.G) {
            this.h(8);
        }
    }

    private final void P() {
        if (this.b.f(this.b.bk[this.w - 1][this.v]) == 0) {
            int n = this.b.h(this.b.bk[this.w - 1][this.v], this.b.J[this.X]);
            this.a("aStep:" + n + "-" + this.b.bi);
            if (n <= this.b.bi) {
                if (this.q) {
                    this.b.bi = (byte)(this.b.bi - n);
                } else if (this.s > 0) {
                    this.s = (byte)(this.s - 1);
                } else {
                    return;
                }
                if (this.C > 0) {
                    this.C = (short)(this.C - 1);
                }
                this.S = true;
                this.b.bp[this.p] = this.w = (byte)(this.w - 1);
            }
        }
        this.a("curP:" + this.b.bo[this.p] + "-" + this.b.bp[this.p]);
    }

    private final void Q() {
        int n;
        if (this.b.f(this.b.bk[this.w][this.v - 1]) == 0 && (n = this.b.h(this.b.bk[this.w][this.v - 1], this.b.J[this.X])) <= this.b.bi) {
            if (this.q) {
                this.b.bi = (byte)(this.b.bi - n);
            } else if (this.s > 0) {
                this.s = (byte)(this.s - 1);
            } else {
                return;
            }
            if (this.B > 0) {
                this.B = (short)(this.B - 1);
            }
            this.b.bo[this.p] = this.v = (byte)(this.v - 1);
            this.S = true;
        }
    }

    private final void R() {
        int n;
        if (this.b.f(this.b.bk[this.w][this.v + 1]) == 0 && (n = this.b.h(this.b.bk[this.w][this.v + 1], this.b.J[this.X])) <= this.b.bi) {
            if (this.q) {
                this.b.bi = (byte)(this.b.bi - n);
            } else if (this.s > 0) {
                this.s = (byte)(this.s - 1);
            } else {
                return;
            }
            if (this.B < 14) {
                this.B = (short)(this.B + 1);
            } else if (this.t < this.b.bl - 15) {
                this.t = (byte)(this.t + 1);
            }
            this.b.bo[this.p] = this.v = (byte)(this.v + 1);
            this.S = true;
        }
    }

    private final void S() {
        int n;
        if (this.b.f(this.b.bk[this.w + 1][this.v]) == 0 && (n = this.b.h(this.b.bk[this.w + 1][this.v], this.b.J[this.X])) <= this.b.bi) {
            if (this.q) {
                this.b.bi = (byte)(this.b.bi - n);
            } else if (this.s > 0) {
                this.s = (byte)(this.s - 1);
            } else {
                return;
            }
            if (this.C < 18) {
                this.C = (short)(this.C + 1);
            }
            this.b.bp[this.p] = this.w = (byte)(this.w + 1);
            this.S = true;
        }
    }

    private final void T() {
        switch (this.a) {
            case 1: {
                if (this.aT || this.aP <= 0) break;
                this.aP = (byte)(this.aP - 1);
                return;
            }
            case 2: {
                if (this.aT) break;
                this.b.getClass();
                if (this.aP >= 2) break;
                this.aP = (byte)(this.aP + 1);
                return;
            }
            case 4: 
            case 8: {
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (this.s() && this.b.bM == 23) {
                    this.b.q(5);
                }
                if (this.aT) {
                    if (this.aV == 0) {
                        if (this.b.bM == 22) {
                            this.b.q((int)this.aP);
                            return;
                        }
                        if (this.b.bM != 23 || this.b.al[this.aP] == 0) break;
                        this.b.q((int)this.aP);
                        return;
                    }
                    this.b.q(5);
                    return;
                }
                this.a(String.valueOf(this.b.bM) + "-" + this.b.al[this.aP]);
                if (this.b.al[this.aP] == 0 && this.b.bM != 22) break;
                this.aT = true;
                return;
            }
            case 32: {
                this.b.q(5);
            }
        }
    }

    private final void U() {
        switch (this.a) {
            case 1: {
                if (this.bD || this.aT) break;
                this.a(this.bt, this.bs);
                if (!this.a((int)this.bA[this.aP], this.bt)) break;
                this.b(this.bt, this.bs);
                return;
            }
            case 2: {
                if (this.bD || this.aT) break;
                this.b(this.bt, this.bs);
                if (!this.a((int)this.bA[this.aP], this.bt)) break;
                this.a(this.bt, this.bs);
                return;
            }
            case 4: {
                if (this.bD) {
                    this.aP = (byte)(this.aP ^ 1);
                    return;
                }
                if (!this.aT) {
                    this.bs = this.j(this.bs);
                    return;
                }
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 8: {
                if (this.bD) {
                    this.aP = (byte)(this.aP ^ 1);
                    return;
                }
                if (!this.aT) {
                    this.bs = this.k(this.bs);
                    return;
                }
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (this.bD) {
                    this.bD = false;
                    return;
                }
                if (!this.aT) {
                    this.aT = true;
                    return;
                }
                if (!this.aT) break;
                if (this.by) {
                    this.by = false;
                    this.aT = false;
                    return;
                }
                if (this.aV == 0) {
                    short s = this.a(this.bt);
                    if (s == 0) {
                        this.by = true;
                        return;
                    }
                    if (this.aP == 0) {
                        this.bE = s * 4 / 3;
                        if (this.aA) {
                            this.b.ba = (short)(this.b.ba - s);
                            this.b.bb = (short)(this.b.bb + (short)this.bE);
                        } else {
                            byte by = this.b.aw;
                            this.b.m[by] = (short)(this.b.m[by] - s);
                            byte by2 = this.b.aw;
                            this.b.n[by2] = (short)(this.b.n[by2] + (short)this.bE);
                        }
                    } else {
                        this.bE = 3 * s / 4;
                        if (this.aA) {
                            this.b.bb = (short)(this.b.bb - s);
                            this.b.ba = (short)(this.b.ba + (short)this.bE);
                        } else {
                            byte by = this.b.aw;
                            this.b.n[by] = (short)(this.b.n[by] - s);
                            byte by3 = this.b.aw;
                            this.b.m[by3] = (short)(this.b.m[by3] + (short)this.bE);
                        }
                    }
                }
                if (this.aA) {
                    this.b.as = (byte)(this.aV + 1);
                    return;
                }
                this.b.o((int)this.aV);
                return;
            }
            case 32: {
                if (this.aA) {
                    this.b.as = (byte)2;
                    return;
                }
                this.b.o(1);
            }
        }
    }

    private final void V() {
        switch (this.a) {
            case 1: {
                if (!this.bC) break;
                this.aP = (byte)(this.aP - 1);
                if (this.aP > -1) {
                    this.aE[2] = null;
                    this.aE[2] = this.b(this.b.B[this.b.ax][this.aP]);
                    return;
                }
                this.aP = (byte)(this.aP + 1);
                return;
            }
            case 2: {
                if (!this.bC) break;
                this.aP = (byte)(this.aP + 1);
                if (this.aP < this.b.s[this.b.ax]) {
                    this.aE[2] = null;
                    this.aE[2] = this.b(this.b.B[this.b.ax][this.aP]);
                    return;
                }
                this.aP = (byte)(this.aP - 1);
                return;
            }
            case 4: 
            case 8: {
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (!this.bC) {
                    if (this.aV == 0) {
                        this.bC = true;
                        return;
                    }
                    this.b.bn();
                    return;
                }
                this.bC = false;
                if (this.aV != 1) break;
                this.b.bn();
                return;
            }
            case 32: {
                this.b.bn();
            }
        }
    }

    private final void W() {
        switch (this.a) {
            case 1: {
                if (this.aT || this.aP <= 0) break;
                this.aE[0] = null;
                this.aP = (byte)(this.aP - 1);
                short s = this.b.G[this.b.b[this.b.aS[this.aP]]];
                this.aE[0] = this.b(s);
                return;
            }
            case 2: {
                if (this.aT || this.aP >= this.b.aV - 1) break;
                this.aE[0] = null;
                this.aP = (byte)(this.aP + 1);
                short s = this.b.G[this.b.b[this.b.aS[this.aP]]];
                this.aE[0] = this.b(s);
                return;
            }
            case 4: 
            case 8: {
                if (!this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                if (this.aT) {
                    this.b.b(this.b.aS[this.aP], (int)this.aV);
                    return;
                }
                this.aT = true;
                return;
            }
            case 32: {
                this.b.b(this.b.aS[this.aP], 1);
            }
        }
    }

    private final void X() {
        switch (this.a) {
            case 4: 
            case 8: {
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                this.b.n((int)this.aV);
                return;
            }
            case 32: {
                this.b.n(1);
            }
        }
    }

    private final void a(byte[] byArray, byte by) {
        if (byArray[by] < 9) {
            byte by2 = by;
            byArray[by2] = (byte)(byArray[by2] + 1);
            return;
        }
        byArray[by] = 0;
    }

    private final void b(byte[] byArray, byte by) {
        if (byArray[by] > 0) {
            byte by2 = by;
            byArray[by2] = (byte)(byArray[by2] - 1);
            return;
        }
        byArray[by] = 9;
    }

    private final boolean a(int n, byte[] byArray) {
        short s = this.a(byArray);
        return s > n;
    }

    private final short a(byte[] byArray) {
        return (short)(byArray[3] * 1000 + byArray[2] * 100 + byArray[1] * 10 + byArray[0]);
    }

    private final byte j(byte by) {
        if (by < 3) {
            by = (byte)(by + 1);
        }
        return by;
    }

    private final byte k(byte by) {
        if (by > 0) {
            by = (byte)(by - 1);
        }
        return by;
    }

    private final byte[] a(short s) {
        byte[] byArray = new byte[4];
        byte[] byArray2 = byArray;
        byArray[3] = (byte)(s / 1000);
        if (byArray2[3] == 0) {
            byArray2[2] = (byte)(s / 100);
            byArray2[1] = (byte)(s / 10 % 10);
            byArray2[0] = (byte)(s % 10);
        }
        return byArray2;
    }

    private final void Y() {
        switch (this.a) {
            case 1: {
                if (this.bp && !this.aT) {
                    if (this.br) {
                        if (this.bv <= 0) break;
                        this.bv = (byte)(this.bv - 1);
                        return;
                    }
                    this.a(this.bw[this.bv + this.bo * 10], this.bs);
                    int n = this.bu + this.bx;
                    if (n > 1000) {
                        n = 1000;
                    }
                    if (this.a(n, this.bw[this.bv + this.bo * 10])) {
                        this.b(this.bw[this.bv + this.bo * 10], this.bs);
                    }
                    this.bu = (short)(this.bu - (short)(this.a(this.bw[this.bv + this.bo * 10]) - this.bx));
                    this.bx = this.a(this.bw[this.bv + this.bo * 10]);
                    return;
                }
                if (this.aT) break;
                if (this.b.w[this.b.aw] >= 9900) {
                    return;
                }
                this.a(this.bt, this.bs);
                int n = 9900 - this.b.w[this.b.aw];
                if (n > this.b.m[this.b.aw] * 5) {
                    n = this.b.m[this.b.aw] * 5;
                }
                if (n > this.b.q[this.b.aw]) {
                    n = this.b.q[this.b.aw];
                }
                if (!this.a(n, this.bt)) break;
                this.b(this.bt, this.bs);
                return;
            }
            case 2: {
                if (this.bp && !this.aT) {
                    if (this.br) {
                        int n = 0;
                        n = this.bn == 1 ? this.b.s[this.b.aw] : (this.bo < this.bn - 1 ? 9 : this.b.s[this.b.aw] % 10);
                        if (this.bv >= n) break;
                        this.bv = (byte)(this.bv + 1);
                        return;
                    }
                    this.b(this.bw[this.bv + this.bo * 10], this.bs);
                    short s = (short)(this.bu - this.a(this.bw[this.bv + this.bo * 10]) + this.bx);
                    if (s > 9900 || this.a(this.bw[this.bv + this.bo * 10]) > 1000 || s < 0) {
                        this.a(this.bw[this.bv + this.bo * 10], this.bs);
                        return;
                    }
                    this.bu = s;
                    this.bx = this.a(this.bw[this.bv + this.bo * 10]);
                    return;
                }
                if (this.aT) break;
                if (this.b.w[this.b.aw] >= 9900) {
                    return;
                }
                this.b(this.bt, this.bs);
                int n = 9900 - this.b.w[this.b.aw];
                if (n > this.b.m[this.b.aw] * 5) {
                    n = this.b.m[this.b.aw] * 5;
                }
                if (n > this.b.q[this.b.aw]) {
                    n = this.b.q[this.b.aw];
                }
                if (!this.a(n, this.bt)) break;
                this.a(this.bt, this.bs);
                return;
            }
            case 4: {
                if (this.bp) {
                    if (this.bq) {
                        this.bs = this.j(this.bs);
                    } else if (this.bo > 0) {
                        this.bo = (byte)(this.bo - 1);
                        this.bv = 0;
                    }
                    if (!this.aT) break;
                    this.aV = (byte)(this.aV ^ 1);
                    return;
                }
                if (this.aT) {
                    this.aV = (byte)(this.aV ^ 1);
                    return;
                }
                this.bs = this.j(this.bs);
                return;
            }
            case 8: {
                if (this.bp) {
                    if (this.bq) {
                        this.bs = this.k(this.bs);
                    } else if (this.bo < this.bn - 1) {
                        this.bo = (byte)(this.bo + 1);
                        this.bv = 0;
                    }
                    if (!this.aT) break;
                    this.aV = (byte)(this.aV ^ 1);
                    return;
                }
                if (this.aT) {
                    this.aV = (byte)(this.aV ^ 1);
                    return;
                }
                this.bs = this.k(this.bs);
                return;
            }
            case 16: {
                if (this.bp) {
                    if (!this.aT && this.bv + this.bo * 10 == this.b.s[this.b.aw]) {
                        this.aT = true;
                        return;
                    }
                    if (this.aT) {
                        if (this.aV == 0) {
                            this.b.v[this.b.aw] = 0;
                            byte by = 0;
                            while (by < this.b.s[this.b.aw]) {
                                this.b.K[this.b.B[this.b.aw][by]] = this.a(this.bw[by]);
                                byte by2 = this.b.aw;
                                this.b.v[by2] = (short)(this.b.v[by2] + this.b.K[this.b.B[this.b.aw][by]]);
                                by = (byte)(by + 1);
                            }
                            this.b.w[this.b.aw] = this.bu;
                        }
                        this.b.m(0);
                        return;
                    }
                    if (!this.bq) {
                        this.bq = true;
                        this.br = false;
                        this.bx = this.a(this.bw[this.bv]);
                        return;
                    }
                    this.bs = 0;
                    this.bq = false;
                    this.br = true;
                    return;
                }
                if (!this.aT) {
                    this.aT = true;
                    return;
                }
                if (this.aV == 0) {
                    short s = this.a(this.bt);
                    this.b.b(s, this.b.aw);
                    this.bs = 0;
                    this.bp = true;
                    this.aT = false;
                    byte by = 0;
                    while (by < this.b.s[this.b.aw]) {
                        this.bw[by] = this.a(this.b.K[this.b.B[this.b.aw][by]]);
                        by = (byte)(by + 1);
                    }
                    this.bu = this.b.w[this.b.aw];
                    return;
                }
                this.b.m(1);
                return;
            }
            case 32: {
                if (this.bp) {
                    this.b.m(0);
                    return;
                }
                this.b.m(1);
            }
        }
    }

    private final void Z() {
        switch (this.a) {
            case 1: {
                if (this.aT || this.bj || this.aP <= 0) break;
                this.aP = (byte)(this.aP - 1);
                return;
            }
            case 2: {
                byte by = 0;
                by = this.bn == 1 ? this.bm : (this.bo < this.bn - 1 ? (byte)9 : (byte)((byte)(this.bm % 10)));
                if (this.aT || this.bj || this.aP >= by) break;
                this.aP = (byte)(this.aP + 1);
                return;
            }
            case 4: {
                if (this.bj) {
                    this.aW = (byte)(this.aW ^ 1);
                    return;
                }
                if (this.aT) {
                    this.aV = (byte)(this.aV ^ 1);
                    return;
                }
                if (this.bo <= 0) break;
                this.bo = (byte)(this.bo - 1);
                this.aP = 0;
                return;
            }
            case 8: {
                if (this.bj) {
                    this.aW = (byte)(this.aW ^ 1);
                    return;
                }
                if (this.aT) {
                    this.aV = (byte)(this.aV ^ 1);
                    return;
                }
                if (this.bo >= this.bn - 1) break;
                this.bo = (byte)(this.bo + 1);
                this.aP = 0;
                return;
            }
            case 16: {
                if (this.bj) {
                    if (this.aW == 0) {
                        this.b.j(0);
                        return;
                    }
                    this.b.j(1);
                    return;
                }
                if (this.b.aJ) {
                    this.ad();
                    return;
                }
                this.ac();
                return;
            }
            case 32: {
                if (!this.b.aK) {
                    return;
                }
                if (this.bj) {
                    this.b.j(1);
                    return;
                }
                if (this.b.aJ) {
                    this.b.i(1);
                    return;
                }
                this.b.k(1);
            }
        }
    }

    private final void aa() {
        switch (this.a) {
            case 4: {
                if (!this.aU) {
                    if (this.aQ > 0) {
                        this.aQ = (byte)(this.aQ - 1);
                        return;
                    }
                    this.aQ = (byte)5;
                    return;
                }
                if (this.aR > 0) {
                    this.aR = (byte)(this.aR - 1);
                    return;
                }
                this.aR = (byte)3;
                return;
            }
            case 8: {
                if (!this.aU) {
                    if (this.aQ < 5) {
                        this.aQ = (byte)(this.aQ + 1);
                        return;
                    }
                    this.aQ = 0;
                    return;
                }
                if (this.aR < 3) {
                    this.aR = (byte)(this.aR + 1);
                    return;
                }
                this.aR = 0;
                return;
            }
            case 16: {
                if (!this.aU) {
                    this.aU = true;
                    this.aR = 0;
                    return;
                }
                if (this.aU && this.aQ == 0 && this.aR == 0) {
                    this.b.P((byte)1);
                    return;
                }
                if (this.aU && this.aQ == 0 && this.aR == 1) {
                    this.b.ae((byte)3);
                    return;
                }
                if (this.aU && this.aQ == 0 && this.aR == 2) {
                    this.b.O((byte)2);
                    return;
                }
                if (this.aU && this.aQ == 0 && this.aR == 3) {
                    this.b.Q((byte)12);
                    return;
                }
                if (this.aU && this.aQ == 1 && this.aR == 0) {
                    this.b.O((byte)6);
                    return;
                }
                if (this.aU && this.aQ == 1 && this.aR == 1) {
                    this.b.S((byte)7);
                    return;
                }
                if (this.aU && this.aQ == 1 && this.aR == 2) {
                    this.b.R((byte)8);
                    return;
                }
                if (this.aU && this.aQ == 1 && this.aR == 3) {
                    this.b.T((byte)9);
                    return;
                }
                if (this.aU && this.aQ == 2 && this.aR == 0) {
                    this.b.U((byte)10);
                    return;
                }
                if (this.aU && this.aQ == 2 && this.aR == 1) {
                    this.b.V((byte)11);
                    return;
                }
                if (this.aU && this.aQ == 2 && this.aR == 2) {
                    this.b.W((byte)13);
                    return;
                }
                if (this.aU && this.aQ == 2 && this.aR == 3) {
                    this.b.X((byte)14);
                    return;
                }
                if (this.aU && this.aQ == 3 && this.aR == 0) {
                    this.b.Y((byte)5);
                    return;
                }
                if (this.aU && this.aQ == 3 && this.aR == 1) {
                    this.b.O((byte)20);
                    return;
                }
                if (this.aU && this.aQ == 3 && this.aR == 2) {
                    this.b.Z((byte)21);
                    return;
                }
                if (this.aU && this.aQ == 3 && this.aR == 3) {
                    this.b.O((byte)4);
                    return;
                }
                if (this.aU && this.aQ == 4 && this.aR == 0) {
                    this.b.aa((byte)15);
                    return;
                }
                if (this.aU && this.aQ == 4 && this.aR == 1) {
                    this.b.ab((byte)16);
                    return;
                }
                if (this.aU && this.aQ == 4 && this.aR == 2) {
                    this.b.ac((byte)18);
                    return;
                }
                if (this.aU && this.aQ == 4 && this.aR == 3) {
                    this.b.ad((byte)19);
                    return;
                }
                if (this.aU && this.aQ == 5 && this.aR == 0) {
                    this.b.O((byte)22);
                    return;
                }
                if (this.aU && this.aQ == 5 && this.aR == 1) {
                    this.b.O((byte)23);
                    return;
                }
                if (this.aU && this.aQ == 5 && this.aR == 2) {
                    this.a((byte)24);
                    return;
                }
                if (!this.aU || this.aQ != 5 || this.aR != 3) break;
                this.o.a();
                return;
            }
            case 32: {
                if (this.aU) {
                    this.aU = false;
                    return;
                }
                this.b.O(this.b.bM);
            }
        }
    }

    public final void k() {
        if (this.aS) {
            this.aS = false;
            this.b.as = 1;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.aZ <= 0) break;
                this.aZ = (byte)(this.aZ - 1);
                break;
            }
            case 2: {
                if (this.aZ >= 33) break;
                this.aZ = (byte)(this.aZ + 1);
                break;
            }
            case 4: {
                if (this.aY <= 0) break;
                this.aY = (byte)(this.aY - 1);
                break;
            }
            case 8: {
                if (this.aY >= 28) break;
                this.aY = (byte)(this.aY + 1);
                break;
            }
            case 16: {
                switch (this.b.bM) {
                    case 0: {
                        if (this.bh[this.aZ][this.aY] <= 0 || this.b.b[this.b.at] != this.b.j[this.bh[this.aZ][this.aY]]) break;
                        byte by = this.bh[this.aZ][this.aY];
                        this.b.a(by, 0);
                        return;
                    }
                    case 1: 
                    case 12: {
                        if (this.bh[this.aZ][this.aY] <= 0) break;
                        byte by = this.bh[this.aZ][this.aY];
                        if (this.b.aw != by && this.b.j[this.b.aw] == this.b.j[by] && this.b.g(this.b.aw, by)) {
                            this.b.a(by, 0);
                        }
                        return;
                    }
                    case 3: {
                        if (this.bh[this.aZ][this.aY] <= 0) break;
                        byte by = this.bh[this.aZ][this.aY];
                        if (this.b.j[this.b.aw] != this.b.j[by] && this.b.g(this.b.aw, by) && !this.b.L(by)) {
                            this.b.a(by, 0);
                        }
                        return;
                    }
                    case 4: {
                        if (this.bh[this.aZ][this.aY] <= 0) break;
                        byte by = this.bh[this.aZ][this.aY];
                        this.b.a(by, 0);
                        return;
                    }
                    case 20: 
                    case 21: {
                        if (this.bh[this.aZ][this.aY] <= 0) break;
                        byte by = this.bh[this.aZ][this.aY];
                        this.a("objCity:" + by);
                        if (this.b.j[by] > 0 && this.b.aw != this.b.j[by]) {
                            this.b.a(by, 0);
                        }
                        return;
                    }
                    case 111: {
                        if (this.bh[this.aZ][this.aY] <= 0) break;
                        return;
                    }
                    case 22: {
                        byte by;
                        if (this.bh[this.aZ][this.aY] <= 0 || this.b.j[by = this.bh[this.aZ][this.aY]] != this.b.b[this.b.at]) break;
                        this.b.aw = by;
                        this.b.as = 1;
                        return;
                    }
                }
                break;
            }
            case 32: {
                this.b.a((byte)1, 1);
            }
        }
        this.ab();
    }

    public final void l() {
        if (this.aC == 25) {
            this.t();
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.aT) {
                    return;
                }
                if (this.be == 1) {
                    if (!this.bF || this.aT || this.aP <= 0) break;
                    this.aP = (byte)(this.aP - 1);
                    return;
                }
                if (this.be != 2) break;
                this.aP = (byte)(this.aP - 1);
                if (this.aP < 1) {
                    this.aP = (byte)7;
                }
                this.aE[3] = null;
                this.aE[3] = this.b(this.b.G[this.b.b[this.aP]]);
                return;
            }
            case 2: {
                if (this.aT) {
                    return;
                }
                if (this.be == 1) {
                    if (!this.bF || this.aT) break;
                    this.b.getClass();
                    if (this.aP >= 2) break;
                    this.aP = (byte)(this.aP + 1);
                    return;
                }
                if (this.be != 2) break;
                this.aP = (byte)(this.aP + 1);
                if (this.aP > 7) {
                    this.aP = 1;
                }
                this.aE[3] = null;
                this.aE[3] = this.b(this.b.G[this.b.b[this.aP]]);
                return;
            }
            case 4: {
                if (this.be == 0) {
                    this.aP = (byte)(this.aP - 1);
                    if (this.aP >= 0) break;
                    this.aP = (byte)(this.bf - 1);
                    return;
                }
                if (this.be != 2 && !this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 8: {
                if (this.be == 0) {
                    this.aP = (byte)(this.aP + 1);
                    if (this.aP <= this.bf - 1) break;
                    this.aP = 0;
                    return;
                }
                if (this.be != 2 && !this.aT) break;
                this.aV = (byte)(this.aV ^ 1);
                return;
            }
            case 16: {
                switch (this.be) {
                    case 0: {
                        switch (this.aP) {
                            case 0: {
                                this.be = (byte)2;
                                this.aP = 1;
                                this.g = "\u8bf7\u9009\u62e9\u626e\u6f14\u541b\u4e3b";
                                this.aE[2] = this.k(110);
                                this.aE[3] = this.b(this.b.G[this.b.b[this.aP]]);
                                this.aE[4] = this.k(15);
                                break;
                            }
                            case 1: {
                                this.be = 1;
                                this.bF = true;
                                this.b.r();
                                break;
                            }
                            case 2: {
                                this.a((byte)25);
                                break;
                            }
                            case 3: {
                                this.o.a();
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (!this.bF) break;
                        if (this.aT) {
                            this.a("curDecide:" + this.aV);
                            if (this.aV == 0) {
                                if (this.b.al[this.aP] == 0) break;
                                this.b.d(this.aP);
                                break;
                            }
                            this.be = 0;
                            this.b.d((byte)5);
                            break;
                        }
                        if (this.b.al[this.aP] == 0) break;
                        this.aT = true;
                        break;
                    }
                    case 2: {
                        if (this.aV == 0) {
                            this.b.K(this.b.a[this.aP]);
                            break;
                        }
                        this.aP = 0;
                        this.aV = 0;
                        this.be = 0;
                    }
                }
                return;
            }
            case 32: {
                if (this.be == 1) {
                    if (this.aT) {
                        this.aT = false;
                        this.aV = 0;
                        return;
                    }
                    this.aP = 0;
                    this.be = 0;
                    if (!this.bF) break;
                    this.b.d((byte)5);
                    return;
                }
                return;
            }
        }
    }

    private final synchronized Image k(int n) {
        this.a("*****createImage:" + n);
        Image image = null;
        byte[] byArray = this.bT;
        int n2 = 0;
        String string = "/res/lib" + n + ".jg";
        try {
            InputStream inputStream = this.bS.getClass().getResourceAsStream(string);
            n2 = inputStream.read(byArray);
            inputStream.close();
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            System.out.println("Load resource error - " + string);
        }
        byArray[0] = -119;
        byArray[1] = 80;
        byArray[2] = 78;
        byArray[3] = 71;
        byArray[4] = 13;
        byArray[5] = 10;
        byArray[6] = 26;
        byArray[7] = 10;
        byArray[8] = 0;
        byArray[9] = 0;
        byArray[10] = 0;
        byArray[11] = 13;
        byArray[12] = 73;
        byArray[13] = 72;
        byArray[14] = 68;
        byArray[15] = 82;
        try {
            image = Image.createImage((byte[])byArray, (int)0, (int)n2);
        }
        catch (Exception exception) {
            System.out.println("Create Image Error!" + string);
        }
        return image;
    }

    private final Image b(short s) {
        this.a("*****createAvatar:" + s);
        Image image = null;
        String string = "/res/avatar/" + s + ".png";
        try {
            image = Image.createImage((String)string);
        }
        catch (Exception exception) {
            this.a("Load avatar error - " + s);
        }
        return image;
    }

    private final void a(String string) {
        if (this.aD) {
            System.out.println(string);
        }
    }

    private final void T(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        graphics.drawImage(this.aK, 0, 0, 0);
        graphics.drawImage(this.aE[2], 90, 40, 20);
        byte by = 0;
        while (by < 8) {
            graphics.drawRegion(this.aE[4], by * 20, 0, 20, 20, 0, 107 + by % 2 * 51, by / 2 * 30 + 140, 0);
            by = (byte)(by + 1);
        }
        graphics.setColor(12359020);
        graphics.fillRect(18, 25 * this.aP + 20, 72, this.e);
        by = 1;
        while (by < 8) {
            graphics.setColor(0);
            graphics.drawString(this.b.c[by], 20, 20 + 25 * by, 20);
            graphics.drawString("=" + String.valueOf(this.b.d[by]), 70, 21 + 25 * by, 20);
            this.a(graphics, by, 55, 22 + 25 * by);
            by = (byte)(by + 1);
        }
        graphics.drawImage(this.aE[3], 100, 51, 20);
        graphics.setColor(0);
        graphics.drawRect(100, 51, this.aE[3].getWidth(), this.aE[3].getHeight());
        graphics.setColor(0xFFFFFF);
        if (this.aP < 8) {
            graphics.setColor(0xFFFFFF);
            by = this.b.G[this.b.b[this.aP]];
            graphics.drawString(this.b.H[by], 172, 63, 20);
            graphics.setColor(16275794);
            graphics.drawString("LV. " + String.valueOf(this.b.I[by]), 177, 90, 20);
            graphics.drawString(String.valueOf(this.b.N[by]), 130, 140, 20);
            graphics.drawString(String.valueOf(this.b.O[by]), 180, 140, 20);
            graphics.drawString(String.valueOf(this.b.M[by]), 130, 171, 20);
            graphics.drawString(String.valueOf(this.b.P[by]), 180, 171, 20);
            graphics.drawString(String.valueOf(this.b.K[by]), 130, 202, 20);
            graphics.drawString(String.valueOf(this.b.R[by] / 100), 180, 202, 20);
            graphics.drawString(this.b.ae[this.b.S[by]], 130, 232, 20);
            graphics.drawString(this.b.ae[this.b.T[by]], 180, 232, 20);
        }
        graphics.drawImage(this.aF, 110 + this.aV * 47, 274, 0);
        if (this.aH == null) {
            this.aH = this.k(14);
        }
        graphics.drawRegion(this.aH, 0, 0, 29, 17, 0, 118, 270, 20);
        graphics.drawRegion(this.aH, 29, 0, 29, 17, 0, 165, 270, 20);
        this.b(graphics, "\u541b\u4e3b\u9009\u62e9");
        this.d(graphics);
    }

    private final void a(Graphics graphics, byte by, int n, int n2) {
        graphics.setColor(0xFCFCFC);
        graphics.fillRect(n + 3, n2, 6, 3);
        graphics.fillRect(n, n2 + 3, 12, 8);
        graphics.setColor(this.c[by]);
        graphics.fillRect(n + 4, n2 + 1, 4, 2);
        graphics.fillRect(n + 1, n2 + 4, 10, 6);
        graphics.setColor(0xFFFFFF);
    }

    private final void U(Graphics graphics) {
        graphics.drawRegion(this.aE[3], 0, 0, 188, 24, 0, this.aN >> 1, 298, 33);
        graphics.drawRegion(this.aE[3], 0, (this.aQ + 1) * 24, 188, 24, 0, this.aN >> 1, 298, 33);
        graphics.setColor(0xFFFF00);
        graphics.drawRect(40 + 42 * this.aR, 278, 34, 15);
    }

    private final void V(Graphics graphics) {
        if (this.b.au != this.b.at) {
            this.g = String.valueOf(this.b.c[this.b.au]) + " \u6218\u7565\u4e2d.....";
        }
        graphics.drawImage(this.aE[0], 0, 0, 20);
        if (this.b.bM == 4) {
            this.b(graphics, c.b[3][3]);
        } else if (this.b.bM == 111 || this.b.bM == 112) {
            this.b(graphics, "\u9009\u65b0\u541b\u4e3b\u7ee7\u4efb");
        } else {
            this.W(graphics);
        }
        int n = 0;
        while (n < 34) {
            int n2 = 0;
            while (n2 < 29) {
                if (this.bh[n][n2] > 0) {
                    if (this.b.j[this.bh[n][n2]] > 0) {
                        int n3 = 1;
                        while (n3 < 9) {
                            if (this.b.j[this.bh[n][n2]] == this.b.b[n3]) {
                                this.a(graphics, this.b.a[n3], n2 * 8, n * 8);
                            }
                            n3 = (byte)(n3 + 1);
                        }
                    } else {
                        this.a(graphics, (byte)0, n2 * 8, n * 8);
                    }
                }
                n2 = (byte)(n2 + 1);
            }
            n = (byte)(n + 1);
        }
        this.d(graphics);
        if (this.b.au == this.b.at || this.b.bM == 112) {
            graphics.drawImage(this.aE[1], this.aY * 8 + 4, this.aZ * 8 + 4, 20);
        }
    }

    private final void ab() {
        this.a("cmd:" + this.b.bM);
        String string = null;
        if (this.bh[this.aZ][this.aY] > 0) {
            string = "\u57ce\u6c60: " + this.b.i[this.bh[this.aZ][this.aY]];
            if (this.b.j[this.bh[this.aZ][this.aY]] > 0) {
                string = String.valueOf(string) + " \u541b\u4e3b:" + this.b.H[this.b.j[this.bh[this.aZ][this.aY]]];
            }
            if (this.b.L(this.b.h[this.bh[this.aZ][this.aY]])) {
                string = String.valueOf(string) + "(\u76df)";
            }
        } else {
            string = this.b.bM == 1 || this.b.bM == 20 || this.b.bM == 12 || this.b.bM == 21 ? "\u8bf7\u9009\u62e9\u76ee\u7684\u57ce\u6c60" : (this.b.bM == 22 ? c.v : (this.b.bM == 3 ? "\u6b32\u653b\u6253\u4f55\u57ce?" : (this.b.bM == 4 ? "\u8bf7\u9009\u62e9\u4fa6\u67e5\u7684\u57ce\u6c60" : "\u5bf9\u4f55\u5dde\u90e1\u4e0b\u4ee4")));
        }
        this.g = string;
    }

    private final void W(Graphics graphics) {
        int n = (this.b.aE % 13 - 1) / 3;
        this.b(graphics, String.valueOf(String.valueOf(this.b.aF)) + "\u5e74" + String.valueOf(this.b.aE % 13) + "\u6708" + "(" + c.h[n] + ")");
    }

    private final void a(Graphics graphics, String string) {
        if (this.aG == null) {
            this.aG = this.k(20);
        }
        graphics.drawImage(this.aG, this.aN >> 1, 70, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, string, 50, 85, 150, 100);
    }

    private final void a(Graphics graphics, String string, byte by) {
        if (this.aG == null) {
            this.aG = this.k(20);
        }
        if (this.aH == null) {
            this.aH = this.k(14);
        }
        graphics.drawImage(this.aG, this.aN >> 1, 70, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, string, 50, 85, 150, 100);
        graphics.drawImage(this.aF, 65 + 50 * this.aW, 135, 0);
        graphics.drawRegion(this.aH, 0, 0, 29, 17, 0, 76, 130, 20);
        graphics.drawRegion(this.aH, 29, 0, 29, 17, 0, 126, 130, 20);
    }

    private final void b(Graphics graphics, String string) {
        graphics.setColor(16275794);
        graphics.drawString(string, this.aN >> 1, 1, 17);
    }

    public final void m() {
        int n = 0;
        while (n < 32) {
            int n2 = 0;
            while (n2 < 29) {
                if (this.bh[n][n2] == this.b.aw) {
                    this.aY = (byte)n2;
                    this.aZ = (byte)n;
                    break;
                }
                n2 = (byte)(n2 + 1);
            }
            n = (byte)(n + 1);
        }
    }

    private final void ac() {
        int n = this.aP + this.bo * 10;
        if (!this.aT && n < this.bm) {
            if (!this.b.W[this.bl[n]]) {
                this.b.W[this.bl[n]] = true;
                this.bi = (byte)(this.bi + 1);
                return;
            }
            this.b.W[this.bl[n]] = false;
            this.bi = (byte)(this.bi - 1);
            return;
        }
        if (this.aT) {
            this.b.k((int)this.aV);
            return;
        }
        if (n == this.bm && this.bi > 0) {
            this.aT = true;
        }
    }

    private final void ad() {
        int n = this.aP + this.bo * 10;
        if (!this.aT && n < this.bm) {
            if (this.bk > 0) {
                this.b.W[this.bk] = false;
                if (this.bl[n] != this.bk) {
                    this.b.W[this.bl[n]] = true;
                    this.bk = this.bl[n];
                    return;
                }
                this.bk = 0;
                return;
            }
            this.bk = this.bl[n];
            this.b.W[this.bk] = true;
            return;
        }
        if (this.aT) {
            this.b.i((int)this.aV);
            return;
        }
        if (n == this.bm && this.bk > 0) {
            if (!this.b.aK) {
                this.b.i((int)this.aV);
                return;
            }
            this.aT = true;
        }
    }

    public final void n() {
        this.bj = true;
    }

    public final void a(int n) {
        try {
            Thread.sleep(n);
            return;
        }
        catch (InterruptedException interruptedException) {
            InterruptedException interruptedException2 = interruptedException;
            interruptedException.printStackTrace();
            return;
        }
    }

    public final void d(Graphics graphics) {
        graphics.setColor(0);
        graphics.drawString(this.g, this.aN >> 1, this.aO - 2, 33);
    }

    public final void o() {
        if (this.F) {
            this.g = "\u9009\u62e9\u8ba1\u7b56\u5b9e\u65bd\u5730\u70b9";
            return;
        }
        if (this.E) {
            this.g = "\u8bf7\u9009\u62e9\u8981\u4fa6\u5bdf\u7684\u6b66\u5c06";
            return;
        }
        if (this.G) {
            this.g = "\u9009\u62e9\u6b32\u653b\u51fb\u654c\u5c06";
            return;
        }
        if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 1) {
            byte by = 0;
            while (by < this.b.bg) {
                if (this.b.bo[by] == (byte)(this.B + this.t) && this.b.bp[by] == (byte)(this.C + this.u) && (this.b.bs[by] == 0 || this.b.bs[by] > 3)) {
                    this.g = "\u6b66\u5c06: " + this.b.H[this.b.be[by]];
                    return;
                }
                by = (byte)(by + 1);
            }
            return;
        }
        if (this.b.f(this.b.bk[this.C + this.u][this.B + this.t]) == 2) {
            this.g = "\u654c\u5c06!";
            return;
        }
        this.g = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
    }

    private final void a(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        graphics.setClip(n, n2, n3, n4);
        this.bU = n3 / this.f;
        this.bV = n4 / this.e;
        this.bW = (string.length() - 1) / this.bU + 1;
        int n5 = this.bX + this.bV;
        if (n5 > this.bW) {
            n5 = this.bW;
        }
        int n6 = this.bX;
        while (n6 < n5) {
            int n7 = n6 * this.bU;
            int n8 = n7 + this.bU;
            if (n8 > string.length()) {
                n8 = string.length();
            }
            graphics.drawString(string.substring(n7, n8), this.aN >> 1, n2 + this.e * ((n6 - this.bX) % this.bV), 17);
            ++n6;
        }
        graphics.setClip(0, 0, this.aN, this.aO);
        if (this.bW > this.bV) {
            if (this.aF == null) {
                this.aF = this.k(109);
            }
            if (this.bX > 0) {
                graphics.drawRegion(this.aF, 0, 0, 6, 8, 6, this.aN >> 1, 20, 17);
            }
            if (n5 < this.bW) {
                graphics.drawRegion(this.aF, 0, 0, 6, 8, 5, this.aN >> 1, this.aO - 20, 33);
            }
        }
    }

    public final void b(int n) {
        String string = this.b((byte)n);
        try {
            InputStream inputStream = ((Object)((Object)this)).getClass().getResourceAsStream(string);
            this.h = Manager.createPlayer((InputStream)inputStream, (String)"audio/midi");
            this.h.realize();
            this.c(this.k);
            this.h.setLoopCount(10);
            this.h.start();
            System.gc();
            return;
        }
        catch (Exception exception) {
            this.a("music error:" + exception.toString());
            return;
        }
    }

    public final String b(byte by) {
        this.j = by;
        return "/res/music/" + by + ".mid";
    }

    public final void p() {
        try {
            this.h.stop();
            this.h.close();
            this.h = null;
            this.j = 0;
            return;
        }
        catch (MediaException mediaException) {
            this.a("close Music error:" + mediaException.toString());
            return;
        }
    }

    public final void c(byte by) {
        if (this.h != null && this.h.getState() > 0) {
            this.i = (VolumeControl)this.h.getControl("VolumeControl");
            int n = 10 + by * 20;
            this.i.setLevel(n);
        }
    }

    private final void X(Graphics graphics) {
        if (this.aK == null) {
            this.aK = this.k(302);
        }
        graphics.drawImage(this.aK, 0, 0, 0);
        this.d(graphics);
        this.b(graphics, "\u5173\u4e8e\u6e38\u620f");
        graphics.setColor(0);
        this.a(graphics, "\u300a\u9738\u738b\u7684\u5927\u9646Mobile\u300b\u91c7\u7528\u4e86\u6307\u4ee4\u724c\u6e38\u620f\u7cfb\u7edf\uff0c\u541b\u4e3b\u62e5\u6709\u57ce\u6570\u8d8a\u591a\uff0c\u6bcf\u6708\u53ef\u4ee5\u53d1\u5e03\u6307\u4ee4\u7684\u4ee4\u724c\u8d8a\u591a\u3002\u5728\u53ea\u6709\u4e00\u5ea7\u57ce\u7684\u60c5\u51b5\u4e0b\u6bcf\u6708\u53ea\u53ef\u4ee5\u505a\u4e09\u4ef6\u4e8b\u3002\u641c\u7d22\u6b66\u5c06\u4e3b\u8981\u53d6\u51b3\u4e8e\u541b\u4e3b\u7684\u5fb7\u884c\uff0c\u5fb7\u884c\u503c\u8d8a\u9ad8\u8d8a\u5bb9\u6613\u8ba9\u6b66\u5c06\u52a0\u5165\u65d7\u4e0b\u3002\u6e38\u620f\u4e2d\u8fd8\u9996\u521b\u4e86\u4ee5\u4eba\u671b\u503c\u51b3\u5b9a\u884c\u52a8\u529b\u7684\u6982\u5ff5\u3002\u5916\u4ea4\u7b56\u7565\u5171\u6709\u4e09\u9879\uff0c\u5206\u522b\u4e3a\u540c\u76df\u3001\u79bb\u95f4\u548c\u62db\u63fd\u3002\u540c\u76df\u662f\u4e0e\u5176\u5b83\u56fd\u5bb6\u7b7e\u5b9a\u4e92\u4e0d\u4fb5\u72af\u8df3\u8dc3\uff0c\u79bb\u95f4\u7528\u6765\u964d\u4f4e\u654c\u65b9\u6b66\u5c06\u7684\u5fe0\u8bda\u5ea6\uff0c\u62db\u63fd\u5373\u5c06\u5bf9\u65b9\u6b66\u5c06\u62c9\u81f3\u9ebe\u4e0b\u3002\u6e38\u620f\u6700\u5927\u7684\u4eae\u70b9\u8fd8\u662f\u5728\u4e8e\u5176\u6218\u4e89\u7cfb\u7edf\uff0c\u9635\u578b\u7684\u8bbe\u5b9a\u548c\u91cd\u5927\u4f5c\u7528\u8ba9\u6218\u4e89\u8fc7\u7a0b\u5145\u6ee1\u7b56\u7565\u6027\u3002    \u6e38\u620f\u952e\u76d8\u64cd\u4f5c\u8bbe\u7f6e:\u5bfc\u822a\u65b9\u5411\u952e\u6216\u6570\u5b57\u952e\u76d82,8,4,6\u5206\u522b\u4e3a'\u4e0a\u4e0b\u5de6\u53f3\u7684\u65b9\u5411\u9009\u62e9',\u5bfc\u822a\u7684\u786e\u8ba4\u952e\u6216\u6570\u5b57\u952e5\u4e3a'\u786e\u8ba4',\u624b\u673a\u53f3\u8f6f\u5efa\u6216\u6570\u5b57\u952e7\u4e3a'\u53d6\u6d88'.\u66f4\u591a\u6e38\u620f\u4fe1\u606f,\u8bf7\u8bbf\u95ee\u5b98\u65b9\u7f51\u7ad9http://www.justgame.com.cn", 15, 35, this.aN - 35, this.aO - 60);
    }
}

