/*
 * Decompiled with CFR 0.152.
 */
import com.alcatelonetouchx.JavaMagicGameCanvas;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;
import sango.Sango;

public final class c
extends JavaMagicGameCanvas
implements CommandListener {
    private byte aZ = 0;
    private boolean ba = false;
    private Image[] bb = new Image[10];
    private Image bc;
    private Image bd;
    private Image be;
    private Image bf;
    private Image bg;
    private Image bh;
    private Image bi;
    private Image bj;
    public byte a = 0;
    public b b;
    private int bk;
    private int bl;
    private byte bm = 0;
    private byte bn = 0;
    private byte bo = 0;
    private boolean bp = false;
    private boolean bq = false;
    private boolean br = false;
    private byte bs = 0;
    private byte bt = 0;
    private byte bu = 0;
    private byte bv = 0;
    private byte bw = 0;
    public int[] c = new int[]{0xFFFFFF, 0xFFFF00, 16741219, 3250840, 10232295, 11339784, 16753920, 2181863, 15147367, 2211047, 9299745, 0x636363};
    public Font d = Font.getFont((int)0, (int)0, (int)8);
    public int e = this.d.getHeight();
    public int f = this.d.charWidth('\u56fd');
    public String g = "";
    private String bx = null;
    private String by = null;
    private String bz = "www.justgame.com.cn/forum";
    public Player h = null;
    public VolumeControl i = null;
    public byte j = 0;
    public byte k = 1;
    public boolean l = false;
    public byte[] m;
    private byte bA;
    private byte bB;
    private byte bC;
    private int bD;
    private byte[][] bE;
    private byte bF;
    private boolean bG;
    private short bH;
    private short[] bI;
    private byte bJ;
    public boolean n;
    private byte bK;
    private byte bL;
    private boolean bM;
    private boolean bN;
    private boolean bO;
    private byte bP;
    private byte[] bQ;
    private short bR;
    private byte bS;
    private byte[][] bT;
    private short bU;
    private boolean bV;
    private short[] bW;
    private short[] bX;
    private short bY;
    private boolean bZ;
    private boolean ca;
    private int cb;
    private boolean cc;
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
    private short cd;
    private short ce;
    private boolean cf;
    public short X;
    public short Y;
    public boolean Z;
    private boolean cg;
    public byte aa;
    public short ab;
    public short ac;
    public short ad;
    public short ae;
    public byte af;
    private byte ch;
    public boolean ag;
    public int ah;
    public int ai;
    public boolean aj;
    public boolean ak;
    public boolean al;
    private byte ci;
    private boolean cj;
    private boolean ck;
    private byte cl;
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
    private boolean cm;
    private boolean cn;
    private byte co;
    private int cp;
    private int cq;
    private int cr;
    private int cs;
    private int ct;
    private boolean cu;
    public boolean aC;
    public byte aD;
    public Form aE;
    public Form aF;
    public Form aG;
    public Command aH;
    public Command aI;
    public TextField aJ;
    public TextField aK;
    public TextField aL;
    private String cv;
    private String cw;
    private byte cx;
    public String aM;
    public byte[] aN;
    public byte[] aO;
    public byte[][] aP;
    public short[] aQ;
    private byte cy;
    private byte cz;
    private boolean cA;
    private boolean cB;
    public byte[][] aR;
    private String cC;
    private int cD;
    private boolean cE;
    private boolean cF;
    public String[] aS;
    public byte aT;
    private String cG;
    private int cH;
    private boolean cI;
    public boolean aU;
    public boolean aV;
    private boolean cJ;
    private byte cK;
    public boolean aW;
    public byte[][] aX;
    public a aY;

    public c(Sango sango) {
        super(false);
        byte[] byArray = new byte[3];
        byArray[1] = 1;
        this.m = byArray;
        this.bA = 0;
        this.bB = 0;
        this.bC = (byte)5;
        this.bD = 8;
        byte[][] byArrayArray = new byte[32][];
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
        byArray4[13] = 17;
        byArray4[20] = 28;
        byArrayArray[6] = byArray4;
        byte[] byArray5 = new byte[30];
        byArray5[1] = 1;
        byArray5[17] = 22;
        byArrayArray[7] = byArray5;
        byte[] byArray6 = new byte[30];
        byArray6[19] = 32;
        byArrayArray[8] = byArray6;
        byte[] byArray7 = new byte[30];
        byArray7[16] = 20;
        byArrayArray[9] = byArray7;
        byte[] byArray8 = new byte[30];
        byArray8[5] = 5;
        byArray8[11] = 10;
        byArrayArray[10] = byArray8;
        byte[] byArray9 = new byte[30];
        byArray9[14] = 13;
        byArray9[23] = 30;
        byArrayArray[11] = byArray9;
        byte[] byArray10 = new byte[30];
        byArray10[19] = 25;
        byArrayArray[12] = byArray10;
        byte[] byArray11 = new byte[30];
        byArray11[9] = 9;
        byArray11[17] = 19;
        byArrayArray[13] = byArray11;
        byte[] byArray12 = new byte[30];
        byArray12[22] = 34;
        byArrayArray[14] = byArray12;
        byte[] byArray13 = new byte[30];
        byArray13[8] = 6;
        byArrayArray[15] = byArray13;
        byte[] byArray14 = new byte[30];
        byArray14[15] = 14;
        byArray14[20] = 23;
        byArrayArray[16] = byArray14;
        byte[] byArray15 = new byte[30];
        byArray15[12] = 12;
        byArrayArray[17] = byArray15;
        byte[] byArray16 = new byte[30];
        byArray16[23] = 31;
        byArrayArray[18] = byArray16;
        byte[] byArray17 = new byte[30];
        byArray17[9] = 8;
        byArrayArray[19] = byArray17;
        byte[] byArray18 = new byte[30];
        byArray18[14] = 15;
        byArray18[17] = 21;
        byArray18[20] = 26;
        byArrayArray[20] = byArray18;
        byte[] byArray19 = new byte[30];
        byArray19[25] = 35;
        byArrayArray[21] = byArray19;
        byArrayArray[22] = new byte[30];
        byte[] byArray20 = new byte[30];
        byArray20[5] = 4;
        byArray20[21] = 27;
        byArrayArray[23] = byArray20;
        byte[] byArray21 = new byte[30];
        byArray21[16] = 18;
        byArrayArray[24] = byArray21;
        byte[] byArray22 = new byte[30];
        byArray22[24] = 33;
        byArrayArray[25] = byArray22;
        byte[] byArray23 = new byte[30];
        byArray23[12] = 11;
        byArrayArray[26] = byArray23;
        byArrayArray[27] = new byte[30];
        byte[] byArray24 = new byte[30];
        byArray24[9] = 7;
        byArray24[20] = 24;
        byArrayArray[28] = byArray24;
        byte[] byArray25 = new byte[30];
        byArray25[3] = 2;
        byArray25[16] = 16;
        byArray25[27] = 36;
        byArrayArray[29] = byArray25;
        byArrayArray[30] = new byte[30];
        byArrayArray[31] = new byte[30];
        this.bE = byArrayArray;
        this.bF = 0;
        this.bG = false;
        this.bH = 0;
        this.bI = new short[10];
        this.bJ = 0;
        this.n = false;
        this.bK = 0;
        this.bL = 0;
        this.bM = false;
        this.bN = false;
        this.bO = true;
        this.bP = 0;
        this.bQ = new byte[4];
        this.bR = 0;
        this.bS = 0;
        this.bT = new byte[10][4];
        this.bU = 0;
        this.bV = false;
        this.bW = new short[3];
        this.bX = new short[3];
        this.bY = 0;
        this.bZ = false;
        this.ca = false;
        this.cb = 0;
        this.cc = false;
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
        this.cg = false;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 1;
        this.ch = 0;
        this.ag = false;
        this.aj = false;
        this.ak = false;
        this.al = false;
        this.ci = 0;
        this.cj = false;
        this.ck = false;
        this.cl = 0;
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
        this.aB = 110;
        this.cm = false;
        this.cn = false;
        this.ct = 1;
        this.cu = false;
        this.aC = false;
        this.aD = 0;
        this.aH = new Command("\u786e\u5b9a", 4, 1);
        this.aI = new Command("\u8fd4\u56de", 7, 1);
        this.aJ = new TextField("\u6b66\u5c06\u540d", "", 4, 0);
        this.aK = new TextField("\u7528\u6237:", "", 30, 0);
        this.aL = new TextField("\u53e3\u4ee4:", "", 10, 65536);
        this.cv = "";
        this.cw = "";
        this.cx = 0;
        this.aM = null;
        byte[] byArray26 = new byte[3];
        byArray26[1] = 5;
        this.aN = byArray26;
        this.aO = new byte[]{28, 84, 42, 50, 103, 84, 43, 14, 89, 101, 20, 15, 126, 101, 20, 15, 89, 119, 20, 15, 126, 119, 20, 15};
        this.aP = new byte[3][5];
        this.aQ = new short[]{234, 235, 236, 237, 238, 229, 230, 231, 232, 233};
        this.cy = 0;
        this.cz = 0;
        this.cA = false;
        this.cB = false;
        this.aR = new byte[][]{{20, 80, 60, 60, 70}, {80, 20, 80, 50, 50}, new byte[5]};
        this.cC = "";
        this.cD = 0;
        this.cE = false;
        this.cF = false;
        this.aS = new String[]{"\u5c1a\u65e0\u5b58\u6863", "\u5c1a\u65e0\u5b58\u6863", "\u5c1a\u65e0\u5b58\u6863"};
        this.aT = 0;
        this.cG = "";
        this.cH = 0;
        this.cI = false;
        this.aU = false;
        this.aV = false;
        this.cJ = false;
        this.cK = 0;
        this.aW = false;
        this.aX = new byte[3][];
        this.aY = new a(this);
        this.setFullScreenMode(true);
        this.bk = 176;
        this.bl = 220;
        this.o = sango;
        this.cG = System.getProperty("microedition.platform");
    }

    private final void c(int n) {
        int n2 = 0;
        while (n2 < 10) {
            this.bb[n2] = null;
            ++n2;
        }
        System.gc();
    }

    private final void q() {
        this.bm = 0;
        this.bq = false;
        this.bs = 0;
        this.bt = 0;
    }

    public final synchronized void a(byte by) {
        short s;
        this.a("curScreenID:" + this.aZ);
        switch (this.aZ) {
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
                this.bZ = false;
                this.bG = false;
                break;
            }
            case 6: {
                this.c(4);
                this.br = false;
                this.bn = 0;
                this.bo = 0;
            }
            case 7: {
                this.c(1);
                this.bF = 0;
                this.bG = false;
                this.bH = 0;
                this.n = false;
                break;
            }
            case 8: {
                this.c(3);
                s = 0;
                while (s < 4) {
                    this.bQ[s] = 0;
                    s = (byte)(s + 1);
                }
                break;
            }
            case 9: {
                s = 0;
                while (s < 3) {
                    this.bW[s] = 0;
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
                    this.bQ[s] = 0;
                    s = (byte)(s + 1);
                }
                this.bY = 0;
                break;
            }
            case 13: {
                this.c(3);
                break;
            }
            case 14: {
                s = 0;
                while (s < 4) {
                    this.bQ[s] = 0;
                    s = (byte)(s + 1);
                }
                this.c(5);
                this.cd = 0;
                this.ce = 0;
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
                    this.bQ[s] = 0;
                    s = (byte)(s + 1);
                }
                this.aA = false;
                break;
            }
            case 17: {
                this.c(2);
                this.bu = 0;
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
                this.cg = false;
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
                this.bp = false;
                break;
            }
            case 27: {
                this.aJ.setString("");
                this.c(2);
            }
        }
        this.aZ = by;
        this.a("nextScreenID:" + this.aZ);
        switch (this.aZ) {
            case 0: {
                this.bb[0] = this.k(300);
                break;
            }
            case 1: {
                this.bb[0] = this.k(106);
                this.bb[1] = this.k(107);
                if (this.bc == null) {
                    this.bc = this.k(109);
                }
                if (this.bh == null) {
                    this.bh = this.k(302);
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
                this.bb[0] = this.k(1);
                this.bb[1] = this.k(2);
                this.aa();
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
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bg == null) {
                    this.bg = this.k(303);
                }
                this.bb[3] = this.k(10);
                if (this.b.i[this.b.av] > 0) {
                    this.bb[0] = this.k(15);
                    this.bb[1] = this.b(this.b.A[this.b.av][0]);
                    this.bb[2] = this.bb[1];
                    if (this.be != null) break;
                    this.be = this.k(14);
                    break;
                }
                this.bb[1] = this.b((short)0);
                this.bG = true;
                this.g = "\u57ce\u5e02\u57fa\u672c\u4fe1\u606f";
                this.bs = 1;
                break;
            }
            case 6: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bg == null) {
                    this.bg = this.k(303);
                }
                this.bb[0] = this.b(this.b.j[this.b.au]);
                this.bb[1] = this.k(10);
                this.bb[2] = this.k(12);
                this.bb[3] = this.k(307);
                this.bb[4] = this.k(15);
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
                this.bb[0] = this.k(13);
                if (this.b.aL) {
                    this.bI = this.b.A[this.b.aw];
                    this.bJ = this.b.r[this.b.aw];
                } else if (this.b.bP == 3 && this.b.bQ == 5) {
                    this.n = true;
                    this.bI = this.b.az;
                    this.bJ = this.b.aA;
                } else {
                    this.bI = this.b.aM;
                    this.bJ = this.b.aN;
                }
                this.bK = (byte)((this.bJ - 1) / 8 + 1);
                this.bL = 0;
                break;
            }
            case 8: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                this.bb[0] = this.k(89);
                this.bb[1] = this.k(16);
                this.bb[2] = this.k(96);
                this.bM = false;
                this.bN = false;
                this.bO = true;
                this.bP = 0;
                this.bR = 0;
                this.bS = 0;
                this.bU = 0;
                s = 0;
                while (s < 4) {
                    this.bQ[s] = 0;
                    s = (byte)(s + 1);
                }
                this.g = "\u5f81100\u5175 \u9700 20\u91d1";
                this.bK = (byte)((this.b.r[this.b.au] - 1) / 8 + 1);
                this.bL = 0;
                break;
            }
            case 9: {
                this.bb[1] = this.k(16);
                this.bb[2] = this.k(309);
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                this.g = "\u57ce\u5185\u91d1\u5b50:" + this.b.l[this.b.au] + "\u7cae\u98df: " + this.b.m[this.b.au] + "\u5b9d:" + this.b.w[this.b.au];
                this.bX[0] = this.b.l[this.b.au] + this.b.l[this.b.av] > 9999 ? (short)(9999 - this.b.l[this.b.av]) : this.b.l[this.b.au];
                this.bX[1] = this.b.m[this.b.au] + this.b.m[this.b.av] > 9999 ? (short)(9999 - this.b.m[this.b.av]) : this.b.m[this.b.au];
                this.bX[2] = this.b.w[this.b.au] + this.b.w[this.b.av] > 99 ? (short)(99 - this.b.w[this.b.av]) : this.b.w[this.b.au];
                break;
            }
            case 10: {
                if (this.bg == null) {
                    this.bg = this.k(303);
                }
                this.bb[0] = this.b(this.b.aJ);
                this.g = "\u6267\u884c\u7ed3\u679c";
                break;
            }
            case 11: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bg == null) {
                    this.bg = this.k(303);
                }
                this.bb[0] = this.b(this.b.ax);
                this.g = "\u6267\u884c\u7ed3\u679c";
                break;
            }
            case 12: {
                this.bY = 0;
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                this.bb[1] = this.k(16);
                this.bb[4] = this.b(this.b.ax);
                this.bb[5] = this.k(309);
                this.g = "\u541b\u4e3b\u7292\u8d4f\u5c06\u58eb";
                break;
            }
            case 13: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                this.bb[1] = this.b(this.b.ax);
                this.g = "\u5c06\u6d88\u8017\u91d1\u5b50" + this.b.aO;
                break;
            }
            case 14: {
                this.bb[0] = this.k(16);
                this.bb[1] = this.k(308);
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                this.bb[4] = this.k(309);
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                this.bX[0] = this.b.l[this.b.au];
                this.bX[1] = this.b.m[this.b.au];
                this.g = "\u57ce\u5185\u91d1\u5b50:" + this.bX[0] + "\u7cae\u98df: " + this.bX[1];
                break;
            }
            case 15: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                s = this.b.F[this.b.b[this.b.aQ[0]]];
                this.bb[0] = this.b(s);
                this.g = "\u8bf7\u9009\u62e9\u52bf\u529b\u8fdb\u884c\u64cd\u4f5c";
                break;
            }
            case 16: {
                int n = 0;
                while (n < 4) {
                    this.bQ[n] = 0;
                    n = (byte)(n + 1);
                }
                this.ca = true;
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                this.bb[1] = this.k(16);
                this.bb[2] = this.k(308);
                this.bb[3] = this.k(309);
                if (this.aA) {
                    this.bX[0] = this.b.bc * 4 / 3 + this.b.bd > 9999 ? (short)((9999 - this.b.bd) * 3 / 4) : this.b.bc;
                    this.bX[1] = this.b.bd * 3 / 4 + this.b.bc > 9999 ? (short)((9999 - this.b.bc) * 4 / 3) : this.b.bd;
                    break;
                }
                this.bX[0] = this.b.l[this.b.au] * 4 / 3 + this.b.m[this.b.au] > 9999 ? (short)((9999 - this.b.m[this.b.au]) * 3 / 4) : this.b.l[this.b.au];
                this.bX[1] = this.b.m[this.b.au] * 3 / 4 + this.b.l[this.b.au] > 9999 ? (short)((9999 - this.b.l[this.b.au]) * 4 / 3) : this.b.m[this.b.au];
                break;
            }
            case 17: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                this.bb[0] = this.k(93);
                this.bb[1] = this.k(16);
                this.g = "\u8bf7\u9009\u62e9\u8d2d\u4e70\u7684\u5175\u5668";
                break;
            }
            case 18: {
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                if (this.bc == null) {
                    this.bc = this.k(109);
                }
                this.aW = false;
                this.g = "\u8bf7\u9009\u62e9\u8bb0\u5f55";
                break;
            }
            case 19: {
                if (this.b.aO < 14) {
                    this.bb[1] = this.k(11);
                } else if (this.bd == null) {
                    this.bd = this.k(20);
                }
                this.e();
                break;
            }
            case 20: {
                this.r();
                this.bb[0] = this.k(2);
                this.bb[4] = this.k(31);
                this.bb[5] = this.k(51);
                this.bb[6] = this.k(52);
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
                this.ch = 0;
                this.cl = this.e(this.b.bH);
                this.bb[0] = this.k(98);
                if (this.cl == 3) {
                    this.bb[0] = this.k(98);
                    this.bb[8] = this.k(306);
                    this.cg = true;
                }
                this.bb[9] = this.k(56);
                this.bb[4] = this.k(63);
                this.bb[2] = this.k(58);
                this.bb[1] = this.k(305);
                this.bb[5] = this.b(this.X);
                this.bb[6] = this.b(this.Y);
                break;
            }
            case 22: {
                this.bb[0] = this.k(59);
                this.bb[1] = this.k(60);
                this.bb[2] = this.k(304);
                this.bb[3] = this.b(this.X);
                break;
            }
            case 23: {
                this.b.k();
                this.bj = this.k(11);
                if (this.b.aO == 1) {
                    this.bb[0] = this.k(132);
                    this.g = String.valueOf(this.b.G[this.b.b[this.b.ar]]) + d.j[this.b.aO];
                    this.cm = true;
                    break;
                }
                this.bb[0] = this.k(131);
                this.g = String.valueOf(this.b.G[this.b.b[this.b.ar]]) + d.j[this.b.aO];
                this.co = this.b.cT;
                break;
            }
            case 24: {
                if (this.bf == null) {
                    this.bf = this.k(90);
                }
                if (this.bh == null) {
                    this.bh = this.k(302);
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
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                this.g = "\u4e0a\u4e0b\u952e\u6eda\u52a8\u5c4f\u5e55";
                break;
            }
            case 26: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                this.g = "\u8f93\u5165\u4f1a\u5458\u4fe1\u606f";
                this.b.ct();
                if (this.b.dc == null) break;
                this.aV = true;
                this.cv = this.b.dc;
                this.cw = this.b.dd;
                break;
            }
            case 27: {
                if (this.bh == null) {
                    this.bh = this.k(302);
                }
                this.bb[0] = this.k(310);
                this.bb[1] = this.c(this.aQ[this.aN[0]]);
                this.cC = "";
                this.bm = 0;
                this.cz = 0;
                this.cy = 0;
                this.cA = false;
                this.cB = false;
                int n = 0;
                while (n < 3) {
                    System.arraycopy(this.aR[n], 0, this.aP[n], 0, 5);
                    n = (byte)(n + 1);
                }
                break;
            }
        }
        this.a = 0;
        this.q();
    }

    private final byte e(byte by) {
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
        this.t = (byte)(this.b.bq[0] - 3);
        this.u = (byte)(this.b.br[0] - 3);
        if (this.t < 0) {
            this.t = 0;
        } else if (this.t > 21) {
            this.t = (byte)21;
        }
        if (this.u < 0) {
            this.u = 0;
            return;
        }
        if (this.u > 6) {
            this.u = (byte)6;
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
        this.bt = 0;
        this.M = 0;
        this.R = 0;
        this.S = false;
        this.T = false;
        this.U = false;
        this.W = 0;
        this.Z = false;
        this.cg = false;
        this.p = 0;
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
        this.bg = null;
        this.bh = null;
        this.c(10);
    }

    private final void e(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        graphics.drawImage(this.bb[1], 107, 59, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawRegion(this.be, 58, 0, 29, 17, 0, 50, 62, 17);
        graphics.drawString(this.b.G[this.b.ax], 83, 62, 17);
        switch (this.b.aP) {
            case 0: {
                this.aM = "\u7cae\u98df: " + this.b.m[this.b.au];
                graphics.drawString(this.aM, 30, 82, 20);
                this.aM = "\u519c\u4e1a: " + this.b.n[this.b.au];
                graphics.drawString("\u519c\u4e1a: " + this.b.n[this.b.au], 30, 102, 20);
                break;
            }
            case 1: {
                this.aM = "\u91d1\u5b50: " + this.b.l[this.b.au];
                graphics.drawString(this.aM, 30, 82, 20);
                this.aM = "\u5de5\u4e1a: " + this.b.o[this.b.au];
                graphics.drawString(this.aM, 30, 102, 20);
                break;
            }
            case 2: {
                this.aM = "\u7edf\u6cbb: " + this.b.k[this.b.au];
                graphics.drawString(this.aM, 30, 82, 20);
                this.aM = "\u9632\u707e: " + this.b.q[this.b.au];
                graphics.drawString(this.aM, 30, 102, 20);
                break;
            }
            case 3: {
                this.aM = "\u7edf\u6cbb: " + this.b.k[this.b.au];
                graphics.drawString(this.aM, 30, 82, 20);
                this.aM = "\u4eba\u53e3: " + this.b.p[this.b.au];
                graphics.drawString(this.aM, 30, 102, 20);
            }
        }
        this.aM = null;
        this.b(graphics, d.b[2][this.b.aP]);
        this.d(graphics);
        this.a(graphics, this.bs);
    }

    private final void f(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        graphics.drawImage(this.bb[4], 55, 55, 17);
        graphics.drawRegion(this.be, 58, 0, 29, 17, 0, 95, 58, 20);
        graphics.drawString(this.b.G[this.b.ax], 97, 86, 20);
        graphics.drawRegion(this.bb[5], 0, 15 * this.bm, 27, 15, 0, 30, 115, 20);
        graphics.drawRegion(this.bb[5], 0, 75 - 15 * this.bm, 27, 15, 0, 120, 115, 20);
        if (this.bN) {
            byte by = 0;
            while (by < 4) {
                if (this.bP == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bQ[by]), 100 - this.bD * by, 114, 24);
                by = (byte)(by + 1);
            }
            graphics.drawImage(this.bb[1], 92 - this.bD * this.bP, 112, 20);
        } else {
            graphics.drawString(String.valueOf(this.bY), 86, 114, 17);
        }
        this.b(graphics, d.b[1][2]);
        this.d(graphics);
        if (this.bV) {
            this.a(graphics, "\u5956\u8d4f\u9ec4\u91d1\u4e0d\u80fd\u4e3a0!");
        }
        if (this.bq) {
            this.a(graphics, this.bs);
        }
    }

    private final void g(Graphics graphics) {
        if (!this.bM) {
            graphics.drawImage(this.bh, 0, 0, 20);
            graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
            graphics.drawImage(this.bb[0], this.bk >> 1, 56, 17);
            this.d(graphics);
            this.b(graphics, d.b[0][2]);
            graphics.setColor(0xFFFFFF);
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawString(String.valueOf(this.b.l[this.b.au]), 92, 54, 20);
            short s = this.b.b(this.b.A[this.b.au], this.b.r[this.b.au]);
            graphics.drawString(String.valueOf(s), 92, 77, 20);
            graphics.drawString(String.valueOf(this.b.v[this.b.au]), 92, 97, 20);
            graphics.drawImage(this.bb[1], 119 - this.bD * this.bP, 116, 20);
            graphics.setColor(0xFFFF00);
            int n = 0;
            while (n < 4) {
                graphics.drawString(String.valueOf(this.bQ[n]), 127 - this.bD * n, 117, 24);
                n = (byte)(n + 1);
            }
            if (this.bq) {
                this.a(graphics, this.bs);
                return;
            }
        } else {
            byte by = 0;
            int n = 0;
            graphics.setColor(0);
            graphics.drawImage(this.bh, 0, 0, 20);
            graphics.drawImage(this.bb[2], 122, this.bl - 2, 36);
            graphics.drawString(String.valueOf(this.bR), 140, this.bl - 2, 36);
            graphics.drawString("\u8bf7\u5206\u914d\u540e\u5907\u5175", 60, this.bl - 2, 33);
            this.b(graphics, d.b[0][2]);
            graphics.drawImage(this.bc, 22, 33 + 18 * this.bS, 0);
            if (this.bK == 1) {
                by = 0;
                while (by < this.b.r[this.b.au]) {
                    if (by == this.bS) {
                        if (this.bN) {
                            graphics.drawImage(this.bb[1], 146 - this.bD * this.bP, 26 + 18 * by, 20);
                        }
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.b.G[this.b.A[this.b.au][by]], 30, 30 + 18 * by, 20);
                    graphics.drawString(d.g[this.b.I[this.b.A[this.b.au][by]]], 80, 30 + 18 * by, 20);
                    int n2 = 0;
                    while (n2 < 4) {
                        graphics.drawString(String.valueOf(this.bT[by][n2]), 146 - this.bD * n2, 30 + 18 * by, 20);
                        n2 = (byte)(n2 + 1);
                    }
                    by = (byte)(by + 1);
                }
                graphics.setColor(0);
                graphics.drawString("\u51b3\u5b9a", 30, 30 + 18 * this.b.r[this.b.au], 20);
            } else if (this.bL < this.bK - 1) {
                by = (byte)(this.bL * 8);
                while (by < (this.bL + 1) * 8) {
                    n = by % 8;
                    if (by == this.bS + this.bL * 8) {
                        if (this.bN) {
                            graphics.drawImage(this.bb[1], 146 - this.bD * this.bP, 26 + 18 * n, 20);
                        }
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.b.G[this.b.A[this.b.au][by]], 30, 30 + 18 * n, 20);
                    graphics.drawString(d.g[this.b.I[this.b.A[this.b.au][by]]], 80, 30 + 18 * n, 20);
                    int n3 = 0;
                    while (n3 < 4) {
                        graphics.drawString(String.valueOf(this.bT[by][n3]), 146 - this.bD * n3, 30 + 18 * n, 20);
                        n3 = (byte)(n3 + 1);
                    }
                    by = (byte)(by + 1);
                }
            } else if (this.bL == this.bK - 1) {
                by = (byte)(this.bL * 8);
                while (by < this.b.r[this.b.au]) {
                    n = by % 8;
                    if (by == this.bS + this.bL * 8) {
                        if (this.bN) {
                            graphics.drawImage(this.bb[1], 146 - this.bD * this.bP, 26 + 18 * n, 20);
                        }
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.b.G[this.b.A[this.b.au][by]], 30, 30 + 18 * n, 20);
                    graphics.drawString(d.g[this.b.I[this.b.A[this.b.au][by]]], 80, 30 + 18 * n, 20);
                    int n4 = 0;
                    while (n4 < 4) {
                        graphics.drawString(String.valueOf(this.bT[by][n4]), 146 - this.bD * n4, 30 + 18 * n, 20);
                        n4 = (byte)(n4 + 1);
                    }
                    by = (byte)(by + 1);
                }
                graphics.setColor(0);
                graphics.drawString("\u51b3\u5b9a", 30, 30 + 18 * (this.b.r[this.b.au] % 8), 20);
            }
            graphics.setColor(0xFFFFFF);
            this.aM = String.valueOf(this.bL + 1) + "/" + this.bK;
            graphics.drawString(this.aM, this.bk >> 1, 176, 17);
            graphics.drawImage(this.bc, 104, 181, 20);
            graphics.drawRegion(this.bc, 0, 0, 6, 8, 2, 76, 181, 24);
            this.aM = null;
            if (this.bq) {
                this.a(graphics, this.bs);
            }
        }
    }

    private final void h(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(0, 0, this.bk, this.bl);
        graphics.drawImage(this.bb[0], this.bk >> 1, this.bl >> 1, 3);
        graphics.setColor(0);
        graphics.drawString("3.2.2", this.bk, this.bl, 40);
    }

    private final void i(Graphics graphics) {
        System.gc();
        graphics.drawImage(this.bb[0], 0, 0, 0);
        graphics.drawString("\u8f7d\u5165\u4e2d...", this.bk >> 1, 40, 17);
        graphics.setColor(0xFFFF00);
        graphics.fillRect(53 + this.ct, 89, 5, 5);
    }

    private final void j(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.bb[0], 0, 0, 20);
        this.l(graphics);
        this.k(graphics);
        if (this.bq) {
            this.a(graphics, this.bs);
        }
        if (this.bG) {
            this.a(graphics, "\u662f\u5426\u653e\u5f03\u6b64\u57ce\u5e02\uff1f", (byte)0);
        }
    }

    private final void k(Graphics graphics) {
        this.g = this.n ? "\u8bf7\u4efb\u547d\u519b\u961f\u4e3b\u5c06" : (this.b.bP == 3 && this.b.bQ == 3 || this.b.bP == 9 || this.b.bP == 100 || this.b.bP == 1 && this.b.bQ == 2 ? "\u8bf7\u4efb\u547d\u65b0\u592a\u5b88" : (this.b.bP == 7 ? d.v : (this.b.bP == 20 ? "\u8bf7\u9009\u62e9\u79bb\u95f4\u7684\u6b66\u5c06" : (this.b.bP == 21 ? "\u8bf7\u9009\u62e9\u8981\u62db\u63fd\u6b66\u5c06" : (this.b.bP == 22 ? d.u : (this.b.bP == 112 ? d.w : (this.b.bP == 111 ? d.x : (this.b.bP == 113 ? d.y : "\u9009\u62e9\u6267\u884c\u6b66\u5c06"))))))));
        this.d(graphics);
    }

    private final void a(Graphics graphics, byte by) {
        if (this.be == null) {
            this.be = this.k(14);
        }
        if (this.bc == null) {
            this.bc = this.k(109);
        }
        graphics.drawImage(this.bc, 47 + 45 * by, 175, 0);
        graphics.drawRegion(this.be, 0, 0, 29, 17, 0, 55, 170, 20);
        graphics.drawRegion(this.be, 29, 0, 29, 17, 0, 100, 170, 20);
    }

    private final void l(Graphics graphics) {
        int n = 0;
        byte by = 0;
        if (this.bK == 1) {
            by = 0;
            while (by < this.bJ) {
                if (this.b.V[this.bI[by]]) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(this.b.G[this.bI[by]], 15, 22 + 18 * by, 20);
                graphics.drawString(String.valueOf(this.b.L[this.bI[by]]), 65, 22 + 18 * by, 17);
                graphics.drawString(String.valueOf(this.b.M[this.bI[by]]), 88, 22 + 18 * by, 17);
                graphics.drawString(String.valueOf(this.b.N[this.bI[by]]), 111, 22 + 18 * by, 17);
                graphics.drawString(String.valueOf(this.b.O[this.bI[by]]), 134, 22 + 18 * by, 17);
                if (this.b.P[this.bI[by]] > 99) {
                    graphics.drawString("--", 157, 22 + 18 * by, 17);
                } else {
                    graphics.drawString(String.valueOf(this.b.P[this.bI[by]]), 157, 22 + 18 * by, 17);
                }
                by = (byte)(by + 1);
            }
            graphics.setColor(0xFFFFFF);
            graphics.drawString("\u51b3\u5b9a", 15, 22 + 18 * by, 20);
        } else {
            if (this.bL < this.bK - 1) {
                by = (byte)(this.bL * 8);
                while (by < (this.bL + 1) * 8) {
                    if (this.b.V[this.bI[by]]) {
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0xFFFFFF);
                    }
                    n = by % 8;
                    graphics.drawString(this.b.G[this.bI[by]], 15, 22 + 18 * n, 20);
                    graphics.drawString(String.valueOf(this.b.L[this.bI[by]]), 65, 22 + 18 * n, 17);
                    graphics.drawString(String.valueOf(this.b.M[this.bI[by]]), 88, 22 + 18 * n, 17);
                    graphics.drawString(String.valueOf(this.b.N[this.bI[by]]), 111, 22 + 18 * n, 17);
                    graphics.drawString(String.valueOf(this.b.O[this.bI[by]]), 134, 22 + 18 * n, 17);
                    if (this.b.P[this.bI[by]] > 99) {
                        graphics.drawString("--", 157, 22 + 18 * n, 17);
                    } else {
                        graphics.drawString(String.valueOf(this.b.P[this.bI[by]]), 157, 22 + 18 * n, 17);
                    }
                    by = (byte)(by + 1);
                }
            } else if (this.bL == this.bK - 1) {
                by = (byte)(this.bL * 8);
                while (by < this.bJ) {
                    if (this.b.V[this.bI[by]]) {
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0xFFFFFF);
                    }
                    n = by % 8;
                    graphics.drawString(this.b.G[this.bI[by]], 15, 22 + 18 * n, 20);
                    graphics.drawString(String.valueOf(this.b.L[this.bI[by]]), 65, 22 + 18 * n, 17);
                    graphics.drawString(String.valueOf(this.b.M[this.bI[by]]), 88, 22 + 18 * n, 17);
                    graphics.drawString(String.valueOf(this.b.N[this.bI[by]]), 111, 22 + 18 * n, 17);
                    graphics.drawString(String.valueOf(this.b.O[this.bI[by]]), 134, 22 + 18 * n, 17);
                    if (this.b.P[this.bI[by]] > 99) {
                        graphics.drawString("--", 157, 22 + 18 * n, 17);
                    } else {
                        graphics.drawString(String.valueOf(this.b.P[this.bI[by]]), 157, 22 + 18 * n, 17);
                    }
                    by = (byte)(by + 1);
                }
                graphics.setColor(0xFFFFFF);
                graphics.drawString("\u51b3\u5b9a", 15, 22 + 18 * (this.bJ % 8), 20);
            }
            graphics.setColor(0xFFFFFF);
            this.aM = String.valueOf(this.bL + 1) + "/" + this.bK;
            graphics.drawString(this.aM, this.bk >> 1, 176, 17);
            graphics.drawImage(this.bc, 104, 181, 20);
            graphics.drawRegion(this.bc, 0, 0, 6, 8, 2, 76, 181, 24);
            this.aM = null;
        }
        graphics.drawImage(this.bc, 8, 27 + 18 * this.bm, 0);
    }

    private final void m(Graphics graphics) {
        switch (this.bB) {
            case 0: {
                graphics.drawImage(this.bb[0], 0, 0, 20);
                graphics.drawRegion(this.bb[1], 56 * this.bm, 0, 56, 18, 0, this.bk >> 1, this.bl - 22, 17);
                if (!this.cE) break;
                graphics.drawImage(this.bd, this.bk >> 1, 50, 17);
                graphics.setColor(0xFFFFFF);
                graphics.drawString("\u521b\u5efa\u6b66\u5c06", this.bk >> 1, 65, 17);
                graphics.drawString("\u5220\u9664\u6b66\u5c06", this.bk >> 1, 65 + this.e, 17);
                graphics.drawImage(this.bc, (this.bk >> 1) - 2 * this.f - 10, 70 + this.cD * this.e, 20);
                return;
            }
            case 2: {
                this.S(graphics);
            }
        }
    }

    private final void n(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bg, this.bk >> 1, 40, 17);
        graphics.drawImage(this.bb[2], 11, this.bl - 3, 36);
        graphics.drawImage(this.bb[1], this.bk >> 1, 48, 17);
        graphics.drawImage(this.bb[0], 102, 65, 20);
        graphics.drawRegion(this.bb[4], 120, 0, 15, 15, 0, 110, 46, 0);
        graphics.setColor(0xFFFF00);
        graphics.drawRect(9 + 27 * this.bn, this.bl - 16, 25, 14);
        this.U(graphics);
        if (this.br) {
            this.T(graphics);
        }
    }

    private final void o(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bg, this.bk >> 1, 32, 17);
        graphics.drawImage(this.bb[0], this.bk >> 1, 45, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, this.b.aK, 28, 113, 120, 60);
        this.d(graphics);
        this.b(graphics, "\u6267\u884c\u6307\u4ee4");
    }

    private final void p(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        graphics.setColor(0xFFFF00);
        this.q(graphics);
        if (!this.bq) {
            byte by = 0;
            while (by < 4) {
                if (this.bP == by && this.bN) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bQ[by]), 115 - this.bD * by, 60 + this.bm * 25, 20);
                by = (byte)(by + 1);
            }
            if (this.bN) {
                graphics.drawImage(this.bb[1], 115 - this.bD * this.bP, 58 + this.bm * 25, 20);
            }
        }
        this.b(graphics, d.b[0][3]);
        this.d(graphics);
        if (this.bV) {
            this.a(graphics, "\u8f93\u9001\u6570\u91cf\u4e0d\u80fd\u5168\u4e3a\u7a7a!");
        }
        if (this.bq) {
            this.a(graphics, this.bs);
        }
    }

    private final void q(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.bm == 0) {
            graphics.drawRegion(this.bb[2], 0, 0, 27, 15, 0, 50, 61, 17);
            graphics.drawRegion(this.bb[2], 0, 45, 27, 15, 0, 50, 86, 17);
            graphics.drawRegion(this.bb[2], 0, 75, 27, 15, 0, 50, 111, 17);
            graphics.drawString(String.valueOf(this.bW[1]), 110, 86, 17);
            graphics.drawString(String.valueOf(this.bW[2]), 110, 111, 17);
            return;
        }
        if (this.bm == 1) {
            graphics.drawRegion(this.bb[2], 0, 15, 27, 15, 0, 50, 61, 17);
            graphics.drawRegion(this.bb[2], 0, 30, 27, 15, 0, 50, 86, 17);
            graphics.drawRegion(this.bb[2], 0, 75, 27, 15, 0, 50, 111, 17);
            graphics.drawString(String.valueOf(this.bW[0]), 110, 61, 17);
            graphics.drawString(String.valueOf(this.bW[2]), 110, 111, 17);
            return;
        }
        if (this.bm == 2) {
            graphics.drawRegion(this.bb[2], 0, 15, 27, 15, 0, 50, 61, 17);
            graphics.drawRegion(this.bb[2], 0, 45, 27, 15, 0, 50, 86, 17);
            graphics.drawRegion(this.bb[2], 0, 60, 27, 15, 0, 50, 111, 17);
            graphics.drawString(String.valueOf(this.bW[0]), 110, 61, 17);
            graphics.drawString(String.valueOf(this.bW[1]), 110, 86, 17);
            return;
        }
        graphics.drawRegion(this.bb[2], 0, 15, 27, 15, 0, 50, 61, 17);
        graphics.drawRegion(this.bb[2], 0, 45, 27, 15, 0, 50, 86, 17);
        graphics.drawRegion(this.bb[2], 0, 75, 27, 15, 0, 50, 111, 17);
        graphics.drawString(String.valueOf(this.bW[0]), 110, 61, 17);
        graphics.drawString(String.valueOf(this.bW[1]), 110, 86, 17);
        graphics.drawString(String.valueOf(this.bW[2]), 110, 111, 17);
    }

    private final void r(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bg, this.bk >> 1, 27, 17);
        graphics.drawImage(this.bb[0], this.bk >> 1, 48, 17);
        this.b(graphics, d.b[1][0]);
        this.d(graphics);
        graphics.setColor(0xFFFFFF);
        if (this.b.aP == 1 || this.b.aP == 2) {
            this.aM = String.valueOf(d.c[this.b.aP]) + this.b.aO;
            graphics.drawString(this.aM, this.bk >> 1, 108, 17);
            this.aM = null;
            return;
        }
        graphics.drawString(d.c[this.b.aP], this.bk >> 1, 108, 17);
    }

    private final void s(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        if (this.bm > 0) {
            graphics.drawRegion(this.bc, 0, 0, 6, 8, 6, this.bk >> 1, 50, 17);
        }
        if (this.bm < this.b.aT - 1) {
            graphics.drawRegion(this.bc, 0, 0, 6, 8, 5, this.bk >> 1, 142, 33);
        }
        graphics.drawImage(this.bb[0], 105, 67, 20);
        graphics.setColor(0xFFFFFF);
        this.aM = "\u52bf\u529b: " + this.b.G[this.b.b[this.b.aQ[this.bm]]];
        graphics.drawString(this.aM, 30, 67, 20);
        this.aM = "\u57ce\u6c60\u6570: " + this.b.c[this.b.aQ[this.bm]];
        graphics.drawString(this.aM, 30, 90, 20);
        this.b(graphics, d.b[3][0]);
        this.d(graphics);
        if (this.bq) {
            this.a(graphics, this.bs);
        }
    }

    private final void t(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bg, this.bk >> 1, 40, 17);
        graphics.setColor(16275794);
        this.b(graphics, d.b[3][3]);
        if (this.bG) {
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawImage(this.bb[3], this.bk >> 1, 48, 17);
            graphics.drawImage(this.bb[1], 102, 65, 20);
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.b.h[this.b.av], 75, 45, 17);
            graphics.drawString(String.valueOf(this.b.k[this.b.av]), 74, 99, 17);
            int n = this.b.p[this.b.av] / 1000;
            int n2 = this.b.u[this.b.av] / 100;
            graphics.drawString(String.valueOf(n), 61, 116, 17);
            graphics.drawString(String.valueOf(this.b.r[this.b.av]), 96, 116, 17);
            graphics.drawString(String.valueOf(n2), 133, 116, 17);
            graphics.drawString(String.valueOf(this.b.n[this.b.av]), 61, 131, 17);
            graphics.drawString(String.valueOf(this.b.o[this.b.av]), 96, 131, 17);
            graphics.drawString(String.valueOf(this.b.q[this.b.av]), 133, 131, 17);
            graphics.drawString(String.valueOf(this.b.l[this.b.av]), 66, 146, 17);
            graphics.drawString(String.valueOf(this.b.m[this.b.av]), 123, 146, 17);
        } else if (this.bZ) {
            boolean bl = false;
            boolean bl3 = false;
            this.g = "\u8be5\u57ce\u6b66\u5c06\u4fe1\u606f,\u4e0a\u4e0b\u952e\u7ffb\u9875";
            graphics.drawImage(this.bb[2], 40, 47, 20);
            graphics.setColor(0);
            graphics.drawRect(40, 47, this.bb[2].getWidth(), this.bb[2].getHeight());
            int n = 0;
            while (n < 8) {
                graphics.drawRegion(this.bb[0], n * 15, 0, 15, 15, 0, 35 + n % 2 * 60, n / 2 * 16 + 102, 0);
                n = (byte)(n + 1);
            }
            n = this.b.A[this.b.av][this.bm];
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.b.G[n], 103, 56, 20);
            graphics.setColor(16275794);
            this.aM = "LV. " + this.b.H[n];
            graphics.drawString(this.aM, 115, 75, 20);
            this.aM = null;
            graphics.drawString(String.valueOf(this.b.M[n]), 53, 103, 20);
            graphics.drawString(String.valueOf(this.b.N[n]), 113, 103, 20);
            graphics.drawString(String.valueOf(this.b.L[n]), 53, 119, 20);
            graphics.drawString(String.valueOf(this.b.O[n]), 113, 119, 20);
            graphics.drawString(String.valueOf(this.b.J[n]), 53, 135, 20);
            graphics.drawString(String.valueOf(this.b.P[n]), 113, 135, 20);
            graphics.drawString(this.b.ad[this.b.R[n]], 53, 151, 20);
            graphics.drawString(this.b.ad[this.b.S[n]], 113, 151, 20);
            graphics.drawRegion(this.be, 87, 0, 29, 17, 0, 42, 177, 0);
            graphics.drawRegion(this.be, 29, 0, 29, 17, 0, 92, 177, 0);
            graphics.drawImage(this.bc, 33 + this.bs * 50, 181, 0);
        } else {
            boolean bl = false;
            boolean bl4 = false;
            graphics.drawImage(this.bb[3], this.bk >> 1, 48, 17);
            graphics.drawImage(this.bb[1], 102, 65, 20);
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.b.h[this.b.av], 75, 45, 17);
            graphics.drawString(this.b.G[this.b.i[this.b.av]], 74, 66, 17);
            graphics.drawString(this.b.G[this.b.j[this.b.av]], 74, 81, 17);
            graphics.drawString(String.valueOf(this.b.k[this.b.av]), 74, 99, 17);
            int n = this.b.p[this.b.av] / 1000;
            int n3 = this.b.u[this.b.av] / 100;
            graphics.drawString(String.valueOf(n), 61, 116, 17);
            graphics.drawString(String.valueOf(this.b.r[this.b.av]), 96, 116, 17);
            graphics.drawString(String.valueOf(n3), 133, 116, 17);
            graphics.drawString(String.valueOf(this.b.n[this.b.av]), 61, 131, 17);
            graphics.drawString(String.valueOf(this.b.o[this.b.av]), 96, 131, 17);
            graphics.drawString(String.valueOf(this.b.q[this.b.av]), 133, 131, 17);
            graphics.drawString(String.valueOf(this.b.l[this.b.av]), 66, 146, 17);
            graphics.drawString(String.valueOf(this.b.m[this.b.av]), 123, 146, 17);
            graphics.drawRegion(this.be, 58, 0, 29, 17, 0, 42, 177, 0);
            graphics.drawRegion(this.be, 29, 0, 29, 17, 0, 92, 177, 0);
            graphics.drawImage(this.bc, 33 + this.bs * 50, 181, 0);
        }
        this.d(graphics);
    }

    private final void u(Graphics graphics) {
        if (this.ca) {
            graphics.setColor(0xFFFFFF);
            graphics.drawImage(this.bh, 0, 0, 20);
            graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
            graphics.drawRegion(this.bb[2], 0, 0, 32, 39, 0, 40, 70, 20);
            graphics.drawRegion(this.bb[2], 32, 0, 32, 39, 0, 100, 70, 20);
            graphics.drawImage(this.bc, 31 + 60 * this.bm, 86, 0);
            this.g = this.bm == 0 ? "\u8d2d\u4e70\u7cae\u98df" : "\u8d2d\u4e70\u9ec4\u91d1";
        } else {
            short s = this.a(this.bQ);
            this.cb = this.bm == 0 ? s * 4 / 3 : 3 * s / 4;
            graphics.drawImage(this.bh, 0, 0, 20);
            graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
            this.g = "100\u7c73 \u53ef\u6613 75\u91d1";
            graphics.drawRegion(this.bb[2], 32 * this.bm, 0, 32, 39, 0, 88, 60, 17);
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawRegion(this.bb[3], 0, 30, 27, 15, 0, 104 - 81 * this.bm, 118, 20);
            graphics.drawRegion(this.bb[3], 0, 0, 27, 15, 0, 23 + 81 * this.bm, 118, 20);
            graphics.setColor(0xFFFFFF);
            graphics.drawString("= ", 90, 117, 20);
            graphics.drawString(String.valueOf(this.cb), 132, 118, 20);
            byte by = 0;
            while (by < 4) {
                if (this.bP == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bQ[by]), 80 - this.bD * by, 118, 24);
                by = (byte)(by + 1);
            }
            graphics.drawImage(this.bb[1], 72 - this.bD * this.bP, 115, 20);
            if (this.bV) {
                this.a(graphics, "\u8d2d\u4e70\u6570\u91cf\u4e0d\u80fd\u4e3a0!");
            }
            if (this.bq) {
                this.a(graphics, this.bs);
            }
        }
        this.b(graphics, d.b[4][0]);
        this.d(graphics);
    }

    private final void v(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bb[0], this.bk >> 1, 40, 17);
        this.b(graphics, d.b[4][1]);
        this.d(graphics);
        graphics.drawRegion(this.bc, 0, 0, 6, 8, 5, 30 + this.bu * 36, 35, 20);
        graphics.drawImage(this.bc, 20, 75 + 20 * this.bm, 0);
        byte by = 0;
        while (by < 4) {
            if (by == this.bm) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            byte by2 = this.a(this.bu, by);
            this.aM = String.valueOf(this.b.ad[by2]) + "  \u9700\u91d1: " + this.b.ae[by2];
            graphics.drawString(this.aM, 28, 71 + 20 * by, 0);
            this.aM = null;
            by = (byte)(by + 1);
        }
        if (this.bq) {
            this.a(graphics, this.bs);
        }
    }

    private final byte a(byte by, byte by2) {
        byte by3 = 0;
        byte by4 = !this.aA ? this.b.y[this.b.au] : this.b.z[this.b.bb];
        switch (by) {
            case 0: {
                by3 = this.b.ag[by4][by2];
                break;
            }
            case 1: {
                by3 = this.b.ah[by4][by2];
                break;
            }
            case 2: {
                by3 = this.b.ai[by4][by2];
                break;
            }
            case 3: {
                by3 = this.b.aj[by4][by2];
            }
        }
        return by3;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private final void w(Graphics graphics) {
        block9: {
            byte by;
            block8: {
                graphics.drawImage(this.bh, 0, 0, 20);
                graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
                this.d(graphics);
                if (this.b.bP == 22) {
                    this.b(graphics, d.b[5][0]);
                } else {
                    this.b(graphics, d.b[5][1]);
                }
                by = 0;
                if (!true) break block8;
                this.b.getClass();
                if (by > 2) break block9;
            }
            do {
                if (this.bm == by) {
                    graphics.setColor(16275794);
                    graphics.drawImage(this.bc, 30, 62 + 22 * by, 20);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                this.aM = String.valueOf(by + 1) + "." + this.aS[by] + d.A[by];
                graphics.drawString(this.aM, this.bk >> 1, 60 + 22 * by, 17);
                this.aM = null;
                by = (byte)(by + 1);
                this.b.getClass();
            } while (by <= 2);
        }
        if (this.bq) {
            this.a(graphics, this.bs);
        }
        if (this.aW) {
            this.a(graphics, "\u662f\u5426\u540c\u6b65\u7f51\u7edc\u5b58\u6863\u6570\u636e?", this.bt);
        }
    }

    public final synchronized boolean d() {
        if (!this.cu) {
            this.cu = true;
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
            switch (this.aZ) {
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
                    this.t(graphics);
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
                    this.M(graphics);
                    break;
                }
                case 15: {
                    this.s(graphics);
                    break;
                }
                case 16: {
                    this.u(graphics);
                    break;
                }
                case 17: {
                    this.v(graphics);
                    break;
                }
                case 18: {
                    this.w(graphics);
                    break;
                }
                case 19: {
                    this.R(graphics);
                    break;
                }
                case 20: {
                    this.N(graphics);
                    break;
                }
                case 21: {
                    this.D(graphics);
                    break;
                }
                case 22: {
                    this.z(graphics);
                    break;
                }
                case 23: {
                    this.y(graphics);
                    break;
                }
                case 24: {
                    this.x(graphics);
                    break;
                }
                case 25: {
                    this.X(graphics);
                    break;
                }
                case 26: {
                    this.Z(graphics);
                    break;
                }
                case 27: {
                    this.Y(graphics);
                }
            }
            if (this.bp) {
                this.a(graphics, this.bx);
            }
            if (this.cF) {
                this.aa(graphics);
            }
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            c.a(exception);
        }
        this.cu = false;
    }

    private final void x(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        this.b(graphics, d.b[5][2]);
        graphics.setColor(0xFFFFFF);
        byte by = 0;
        while (by < 3) {
            if (this.bA == by) {
                graphics.setColor(0xFFFF00);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawString(d.n[by], 25, 60 + by * 30, 20);
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
                graphics.drawString(d.o[by], 60 + by * 30, 60 + n * 30, 20);
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
            graphics.drawString(d.p[by], 55 + by * 20, 120, 20);
            by = (byte)(by + 1);
        }
        if (this.bq) {
            this.a(graphics, this.bs);
        }
        this.d(graphics);
    }

    private final void y(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, this.bk, this.bl);
        if (this.cn) {
            graphics.drawImage(this.bb[0], this.bk >> 1, 24, 17);
            graphics.setColor(0x996600);
            graphics.drawRect(12, 24, this.bb[0].getWidth(), this.bb[0].getHeight());
            graphics.drawImage(this.bj, this.bk >> 1, 16, 17);
            this.g = this.aM = "\u7edf\u4e00\u5ea6:" + this.b.cS + " \u5206\u6570:" + this.b.cR;
            this.aM = null;
            graphics.setColor(0xFFFF00);
            this.a(graphics, this.g, 8, 135, 150, 70);
            return;
        }
        graphics.drawImage(this.bb[0], this.bk >> 1, 24, 17);
        graphics.setColor(0x996600);
        graphics.drawRect(12, 24, this.bb[0].getWidth(), this.bb[0].getHeight());
        graphics.drawImage(this.bj, this.bk >> 1, 16, 17);
        graphics.setColor(0xFFFF00);
        this.a(graphics, this.g, 8, 135, 150, 70);
    }

    private final void z(Graphics graphics) {
        try {
            switch (this.am) {
                case 0: {
                    if (this.au > 0) {
                        this.B(graphics);
                        break;
                    }
                    this.A(graphics);
                    break;
                }
                case 1: {
                    if (this.at) {
                        this.b.cc = (byte)2;
                    } else {
                        this.b.cc = 1;
                        this.az = (byte)this.b.af();
                    }
                    if (this.az >= 0) break;
                    this.b.aq = 1;
                    break;
                }
                case 2: {
                    this.b.cc = this.at ? (byte)4 : (byte)3;
                    this.b.aq = 1;
                    break;
                }
                case 3: {
                    if (this.at) {
                        this.b.d(false);
                    } else {
                        this.b.d(true);
                    }
                    this.b.aq = 1;
                }
            }
            if (this.az > -1) {
                String string = this.f(this.az);
                this.a(graphics, string);
                return;
            }
        }
        catch (Exception exception) {
            this.a("Duel Paint() error!" + exception.toString());
        }
    }

    private final String f(byte by) {
        return d.s[by];
    }

    private final void A(Graphics graphics) {
        this.C(graphics);
        if (this.ar) {
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
            graphics.drawImage(this.bb[1], 5, 37, 20);
            graphics.drawImage(this.bb[3], 12, 42, 20);
            int n = this.ap % 3;
            int n2 = this.ap / 3;
            if (this.bc == null) {
                this.bc = this.k(109);
            }
            graphics.drawImage(this.bc, 60 + 37 * n, 52 + 24 * n2, 0);
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
                int n = 115 - this.ao * 4;
                if (this.ao < 17) {
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.bb[2], 300 + this.ao % 2 * 540, 0, 60, 60, 2, n, this.aB, 20);
                    Thread.sleep(20L);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                return;
            }
            int n = 75 - this.ao * 4;
            if (this.ao < 20) {
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300 + this.ao % 2 * 540, 0, 60, 60, 2, n, this.aB, 20);
                Thread.sleep(20L);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            this.an = 1;
            this.ao = 0;
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
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
                if (this.ao < 19) {
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.bb[2], 300 + this.ao % 2 * 540, 0, 60, 60, 0, n, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                this.an = 1;
                this.ao = 0;
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                this.as = 0;
                this.ap = 0;
                this.ar = true;
                this.ay = "\u8bf7\u9009\u62e9\u6267\u884c\u547d\u4ee4";
                return;
            }
            int n = 5 + this.ao * 4;
            if (this.ao < 19) {
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300 + this.ao % 2 * 540, 0, 60, 60, 0, n, this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 77, this.aB, 20);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private final void d(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                byte by = 0;
                if (this.ao < 3) {
                    by = (byte)(this.ao + 2);
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.bb[2], 60 * by, 0, 60, 60, 2, 55 - 8 * (by % 2), this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                byte by2 = this.b.b(this.Y, this.b.cz, this.b.cy);
                int n = this.b.L[this.X] - by2;
                if (n < 1) {
                    this.b.L[this.X] = 0;
                    this.a(true, 0);
                    return;
                }
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                short s = this.X;
                this.b.L[s] = (byte)(this.b.L[s] - by2);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            byte by = 0;
            if (this.ao < 3) {
                by = (byte)(this.ao + 2);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                graphics.drawRegion(this.bb[2], 60 * by, 0, 60, 60, 0, 73 + 8 * (by % 2), this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            byte by3 = this.b.b(this.X, this.b.cx, this.b.cA);
            int n = this.b.L[this.Y] - by3;
            if (n < 1) {
                this.b.L[this.Y] = 0;
                this.a(false, 1);
                return;
            }
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 73, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
            short s = this.Y;
            this.b.L[s] = (byte)(this.b.L[s] - by3);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private final void e(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                if (this.ao < 5) {
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.bb[2], 60 * this.ao, 0, 60, 60, 2, 51, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                byte by = this.b.c(this.Y, this.b.cz, this.b.cy);
                int n = this.b.L[this.X] - by;
                if (n < 1) {
                    this.b.L[this.X] = 0;
                    this.a(true, 0);
                    return;
                }
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                short s = this.X;
                this.b.L[s] = (byte)(this.b.L[s] - by);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            if (this.ao < 5) {
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                graphics.drawRegion(this.bb[2], 60 * this.ao, 0, 60, 60, 0, 75, this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            byte by = this.b.c(this.X, this.b.cx, this.b.cA);
            int n = this.b.L[this.Y] - by;
            if (n < 1) {
                this.b.L[this.Y] = 0;
                this.a(false, 1);
                return;
            }
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 73, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
            short s = this.Y;
            this.b.L[s] = (byte)(this.b.L[s] - by);
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
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.bb[2], 60 * n, 0, 60, 60, 2, 51, this.aB, 20);
                    this.ao = (byte)(this.ao + 1);
                    return;
                }
                byte by = this.b.d(this.Y, this.b.cz, this.b.cy);
                if (by > 0) {
                    int n = this.b.L[this.X] - by;
                    if (n < 1) {
                        this.b.L[this.X] = 0;
                        this.a(true, 0);
                        return;
                    }
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                    graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                    short s = this.X;
                    this.b.L[s] = (byte)(this.b.L[s] - by);
                    this.an = (byte)(this.an + 1);
                    this.ao = 0;
                    return;
                }
                int n = this.b.L[this.Y] + by;
                if (n < 1) {
                    this.b.L[this.Y] = 0;
                    this.a(false, 0);
                    return;
                }
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 51, this.aB, 20);
                short s = this.Y;
                this.b.L[s] = (byte)(this.b.L[s] + by);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            if (this.ao < 11) {
                int n = this.ao < 6 ? this.ao + 5 : this.ao - 6;
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                graphics.drawRegion(this.bb[2], 60 * n, 0, 60, 60, 0, 75, this.aB, 20);
                this.ao = (byte)(this.ao + 1);
                return;
            }
            byte by = this.b.d(this.X, this.b.cx, this.b.cA);
            if (by < 0) {
                int n = this.b.L[this.X] + by;
                if (n < 1) {
                    this.b.L[this.X] = 0;
                    this.a(true, 1);
                    return;
                }
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 73, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                short s = this.X;
                this.b.L[s] = (byte)(this.b.L[s] + by);
                this.an = (byte)(this.an + 1);
                this.ao = 0;
                return;
            }
            int n = this.b.L[this.Y] - by;
            if (n < 1) {
                this.b.L[this.Y] = 0;
                this.a(false, 1);
                return;
            }
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 73, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
            short s = this.Y;
            this.b.L[s] = (byte)(this.b.L[s] - by);
            this.an = (byte)(this.an + 1);
            this.ao = 0;
            return;
        }
        catch (Exception exception) {
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

    private final void B(Graphics graphics) {
        this.C(graphics);
        if (this.au == 1) {
            if (!this.at) {
                this.ay = String.valueOf(this.b.G[this.Y]) + "\u6218\u4ea1\u6c99\u573a.";
                if (this.av < 3) {
                    graphics.drawRegion(this.bb[2], 660, 0, 60, 60, 0, 5 + 60 * this.aw, this.aB + 10, 20);
                    graphics.drawRegion(this.bb[2], 780, 0, 60, 60, 2, 51 + 64 * this.aw, this.aB + 20, 20);
                    this.av = (byte)3;
                    try {
                        Thread.sleep(20L);
                        return;
                    }
                    catch (Exception exception) {
                        System.out.println(exception.toString());
                        return;
                    }
                }
                graphics.drawRegion(this.bb[2], 660, 0, 60, 60, 0, 5 + 60 * this.aw, this.aB + 10, 20);
                graphics.drawRegion(this.bb[2], 780, 0, 60, 60, 2, 51 + 64 * this.aw, this.aB + 20, 20);
                return;
            }
            this.ay = String.valueOf(this.b.G[this.X]) + "\u6218\u4ea1\u6c99\u573a.";
            if (this.av < 3) {
                graphics.drawRegion(this.bb[2], 780, 0, 60, 60, 0, 5 + 60 * this.aw, this.aB + 20, 20);
                graphics.drawRegion(this.bb[2], 660, 0, 60, 60, 2, 51 + 64 * this.aw, this.aB + 10, 20);
                this.av = (byte)(this.av + 1);
                try {
                    Thread.sleep(20L);
                    return;
                }
                catch (Exception exception) {
                    System.out.println(exception.toString());
                    return;
                }
            }
            graphics.drawRegion(this.bb[2], 780, 0, 60, 60, 0, 5 + 60 * this.aw, this.aB + 20, 20);
            graphics.drawRegion(this.bb[2], 660, 0, 60, 60, 2, 51 + 64 * this.aw, this.aB + 10, 20);
            return;
        }
        if (this.au == 2) {
            if (this.ax) {
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                this.ax = false;
                this.au = 0;
                this.ap = (byte)3;
                this.ar = false;
                return;
            }
            if (!this.at) {
                graphics.drawRegion(this.bb[2], 660, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 720, 0, 60, 60, 2, 115, this.aB, 20);
                return;
            }
            graphics.drawRegion(this.bb[2], 720, 0, 60, 60, 0, 5, this.aB, 20);
            graphics.drawRegion(this.bb[2], 660, 0, 60, 60, 2, 115, this.aB, 20);
            return;
        }
        if (this.au == 3) {
            if (!this.at) {
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
                graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 115, this.aB, 20);
                return;
            }
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 5, this.aB, 20);
            graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
            return;
        }
        graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 0, 5, this.aB, 20);
        graphics.drawRegion(this.bb[2], 300, 0, 60, 60, 2, 115, this.aB, 20);
    }

    private final void C(Graphics graphics) {
        graphics.drawImage(this.bb[0], 0, 0, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.G[this.X], this.bk >> 2, 1, 17);
        graphics.drawString(this.b.G[this.Y], this.bk * 3 >> 2, 1, 17);
        graphics.setColor(0);
        this.g = this.ay;
        this.d(graphics);
        graphics.setColor(16275794);
        graphics.drawString(String.valueOf(this.b.L[this.X]), 0, 29, 0);
        graphics.drawString(String.valueOf(this.b.L[this.Y]), this.bk, 29, 24);
        byte by = 0;
        while (by < 100) {
            if (by < this.b.L[this.X]) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawLine(10 + by * 3 / 4, 24, 10 + by * 3 / 4, 29);
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < 100) {
            if (by < this.b.L[this.Y]) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawLine(166 - by * 3 / 4, 24, 166 - by * 3 / 4, 29);
            by = (byte)(by + 1);
        }
    }

    private final void D(Graphics graphics) {
        switch (this.af) {
            case 1: {
                this.F(graphics);
                return;
            }
            case 2: {
                this.bh = null;
                this.bf = null;
                if (this.b.cc > 0) {
                    this.bx = this.b.cc > 4 && this.b.cf > 0 ? String.valueOf(d.m[this.b.cc]) + " \u4f53\u529b\u51cf\u5c11" + String.valueOf(this.b.cf) : d.m[this.b.cc];
                    if (this.ag) {
                        this.bp = true;
                    }
                    this.ag = true;
                    if (this.b.L[this.X] == 0) {
                        this.b.J[this.Y] = this.b.e(false);
                        return;
                    }
                    this.b.J[this.X] = this.b.e(true);
                    return;
                }
                switch (this.b.ci) {
                    case 0: {
                        this.b.ci = 1;
                        this.b.c(this.ch);
                        this.b.aq = 1;
                        return;
                    }
                    case 1: {
                        this.H(graphics);
                        return;
                    }
                    case 2: {
                        this.E(graphics);
                    }
                }
            }
        }
    }

    private final void E(Graphics graphics) {
        graphics.drawRegion(this.bb[1], 0, 65, 170, 109, 0, 3, 88, 20);
        short s = 0;
        int n = 0;
        int n2 = 1;
        while (n2 < 4) {
            s = (short)(s + this.b.cg[n2]);
            n = (short)(n + this.b.ch[n2]);
            n2 = (byte)(n2 + 1);
        }
        graphics.setColor(16275794);
        graphics.drawString(String.valueOf(n), 100, 175, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.bb[5], 8, 89, 20);
        graphics.drawString(this.b.G[this.X], 30, 138, 17);
        graphics.drawString(String.valueOf(this.b.L[this.X]), 43, 160, 20);
        graphics.drawString(String.valueOf(this.b.M[this.X]), 100, 160, 20);
        graphics.drawString(String.valueOf(this.b.bN), 155, 160, 20);
        graphics.drawString(String.valueOf(s), 43, 175, 20);
        n2 = 0;
        while (n2 < 4) {
            graphics.drawString(d.f[this.b.cj[n2]], 125, 90 + n2 * 14, 20);
            n2 = (byte)(n2 + 1);
        }
        graphics.drawImage(this.bc, 115, 95 + this.b.ck * 14, 0);
        if (this.ck) {
            this.b(graphics);
        }
    }

    private final void F(Graphics graphics) {
        if (this.bh == null) {
            this.bh = this.k(302);
        }
        if (this.bf == null) {
            this.bf = this.k(90);
        }
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        if (this.bi == null) {
            this.bi = this.k(49);
        }
        graphics.drawImage(this.bi, this.bk >> 1, 73, 17);
        this.b(graphics, d.b[0][1]);
        graphics.setColor(16275794);
        graphics.drawRect(25 + this.ch * 33, 114, 27, 27);
        graphics.drawImage(this.bb[5], 29, 52, 20);
        graphics.drawImage(this.bb[6], 107, 52, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.G[this.X], 52, 102, 17);
        graphics.drawString(this.b.G[this.Y], 130, 102, 17);
        graphics.setColor(0);
        this.g = "\u9635\u578b\u9009\u62e9";
        this.d(graphics);
    }

    private final void G(Graphics graphics) {
        graphics.setColor(0);
        boolean bl = false;
        boolean bl2 = false;
        this.ah = 58;
        this.ai = 108;
        graphics.drawRect(57, 107, this.b.bL * 3 + 2, this.b.bM * 3 + 2);
    }

    private final void H(Graphics graphics) {
        this.L(graphics);
        graphics.drawRegion(this.bb[1], 0, 0, 170, 65, 0, 3, 134, 20);
        graphics.setColor(16275794);
        int n = this.b.cd >> 4;
        int n2 = this.b.ce >> 4;
        graphics.drawString(d.l[n], 53, 180, 20);
        graphics.drawString(d.l[n2], 125, 180, 24);
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.bb[5], 3, 133, 20);
        graphics.drawImage(this.bb[6], 131, 133, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.G[this.X], 24, 182, 17);
        graphics.drawString(this.b.G[this.Y], 152, 182, 17);
        this.b.n();
        byte by = 0;
        while (by < 4) {
            graphics.drawString(String.valueOf(this.b.cg[by]), 78, 135 + by * 14, 24);
            graphics.drawString(String.valueOf(this.b.ch[by]), 101, 135 + by * 14, 20);
            by = (byte)(by + 1);
        }
        this.G(graphics);
        by = 0;
        while (by < this.b.bI) {
            this.b(graphics, (byte)0, by);
            by = (byte)(by + 1);
        }
        by = 0;
        while (by < this.b.bJ) {
            this.b(graphics, (byte)1, by);
            by = (byte)(by + 1);
        }
        if (this.aj) {
            this.K(graphics);
        }
        if (this.ak) {
            this.J(graphics);
        }
        if (this.al) {
            this.I(graphics);
        }
    }

    private final void I(Graphics graphics) {
        if (this.b.bS[1][0] > 1) {
            int n = this.b.bS[1][0] - 1;
            int n2 = this.b.bT[1][0] - 1;
            if (n2 < 0) {
                n2 = 0;
            }
            if (this.bb[7] == null) {
                this.bb[7] = this.k(105);
            }
            graphics.drawImage(this.bb[7], 16 * (n - this.aa), 16 * n2, 20);
        }
    }

    private final void J(Graphics graphics) {
        if (this.b.bS[0][0] < 13) {
            int n = this.b.bS[0][0] + 1;
            int n2 = this.b.bT[0][0] - 1;
            if (n2 < 0) {
                n2 = 0;
            }
            if (this.bb[7] == null) {
                this.bb[7] = this.k(105);
            }
            graphics.drawImage(this.bb[7], 16 * (n - this.aa), 16 * n2, 20);
        }
    }

    public final void a(byte by, byte by2, byte by3, byte by4, boolean bl) {
        this.ab = (short)(16 * by);
        this.ac = (short)(16 * by2);
        this.ad = (short)(16 * by3);
        this.ae = (short)(16 * by4);
        boolean bl2 = true;
        if (this.b.cd >> 4 == 5 && bl || this.b.ce >> 4 == 5 && !bl) {
            bl2 = false;
        }
        if (this.ab == this.ad && this.ac < this.ae) {
            this.ci = (byte)8;
            this.bb[3] = !bl2 ? Image.createImage((Image)this.bb[4], (int)0, (int)0, (int)16, (int)16, (int)5) : Image.createImage((Image)this.bb[4], (int)16, (int)0, (int)16, (int)16, (int)5);
        } else if (this.ab == this.ad && this.ac > this.ae) {
            this.ci = (byte)2;
            this.bb[3] = !bl2 ? Image.createImage((Image)this.bb[4], (int)0, (int)0, (int)16, (int)16, (int)6) : Image.createImage((Image)this.bb[4], (int)16, (int)0, (int)16, (int)16, (int)6);
        } else if (this.ab < this.ad && this.ac == this.ae) {
            this.ci = (byte)6;
            this.bb[3] = !bl2 ? Image.createImage((Image)this.bb[4], (int)0, (int)0, (int)16, (int)16, (int)0) : Image.createImage((Image)this.bb[4], (int)16, (int)0, (int)16, (int)16, (int)0);
        } else if (this.ab > this.ad && this.ac == this.ae) {
            this.ci = (byte)4;
            this.bb[3] = !bl2 ? Image.createImage((Image)this.bb[4], (int)0, (int)0, (int)16, (int)16, (int)2) : Image.createImage((Image)this.bb[4], (int)16, (int)0, (int)16, (int)16, (int)2);
        }
        this.aj = true;
    }

    private final void K(Graphics graphics) {
        switch (this.ci) {
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
            graphics.drawImage(this.bb[3], this.ab - this.aa * 16, (int)this.ac, 20);
        }
    }

    private final void a(Graphics graphics, byte by, byte by2) {
        if (by == 0) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(16275794);
        }
        if (by2 == 0) {
            graphics.fillRect(this.ah + this.b.bS[by][by2] * 3, this.ai + this.b.bT[by][by2] * 3, 3, 3);
            return;
        }
        graphics.drawRect(this.ah + this.b.bS[by][by2] * 3, this.ai + this.b.bT[by][by2] * 3, 2, 2);
    }

    private final void b(Graphics graphics, byte by, byte by2) {
        if (this.b.cb[by][by2]) {
            int n = this.b.bS[by][by2] - this.aa;
            if (n >= 0 && n < 11) {
                this.a(graphics, by, by2, n);
                if (this.b.ca[by][by2]) {
                    this.b.ca[by][by2] = false;
                    graphics.drawImage(this.bb[2], n * 16, this.b.bT[by][by2] * 16, 20);
                }
            }
            this.a(graphics, by, by2);
        }
    }

    private final void a(Graphics graphics, byte by, byte by2, int n) {
        switch (this.b.bX[by][by2]) {
            case 0: {
                graphics.drawRegion(this.bb[9], (this.b.bU[by][by2] + 4) * 16, by * 16, 16, 16, 0, n * 16, this.b.bT[by][by2] * 16, 0);
                return;
            }
            case 1: {
                graphics.drawRegion(this.bb[9], (this.b.bU[by][by2] + 8) * 16, by * 16, 16, 16, 0, n * 16, this.b.bT[by][by2] * 16, 0);
                return;
            }
            case 2: {
                graphics.drawRegion(this.bb[9], this.b.bU[by][by2] * 16, by * 16, 16, 16, 2, n * 16, this.b.bT[by][by2] * 16, 0);
                return;
            }
            case 3: {
                graphics.drawRegion(this.bb[9], this.b.bU[by][by2] * 16, by * 16, 16, 16, 0, n * 16, this.b.bT[by][by2] * 16, 0);
            }
        }
    }

    private final void L(Graphics graphics) {
        int n;
        int n2 = 0;
        while (n2 < 4) {
            n = 0;
            while (n < 3) {
                graphics.drawRegion(this.bb[0], this.cl * 64, 0, 64, 64, 0, n * 64, n2 * 64, 0);
                n = (byte)(n + 1);
            }
            n2 = (byte)(n2 + 1);
        }
        if (this.cg) {
            n2 = 0;
            while (n2 < 11) {
                graphics.drawRegion(this.bb[8], 0, 0, 16, 16, 0, n2 * 16, 112, 20);
                n2 = (byte)(n2 + 1);
            }
            n2 = 0;
            while (n2 < 11) {
                n = 0;
                while (n < 7) {
                    byte by = (byte)(this.b.bK[n][n2 + this.aa] & 0x32);
                    byte by2 = this.g(by);
                    if (by2 > -1) {
                        graphics.drawRegion(this.bb[8], by2 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
                    }
                    n = (byte)(n + 1);
                }
                n2 = (byte)(n2 + 1);
            }
        }
    }

    private final byte g(byte by) {
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

    private final void M(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 20);
        graphics.drawImage(this.bf, this.bk >> 1, 40, 17);
        graphics.drawRegion(this.bb[1], 32, 0, 32, 39, 0, 52, 68, 17);
        graphics.drawRegion(this.bb[1], 0, 0, 32, 39, 0, 128, 68, 17);
        graphics.drawRegion(this.bb[4], 0, this.bm * 15, 27, 15, 0, 33, 118, 17);
        graphics.drawRegion(this.bb[4], 0, 45 - this.bm * 15, 27, 15, 0, 104, 118, 17);
        graphics.setColor(0xFFFFFF);
        if (this.bN) {
            if (this.bm == 0) {
                graphics.drawString(String.valueOf(this.cd), 140, 117, 17);
            } else {
                graphics.drawString(String.valueOf(this.ce), 66, 117, 17);
            }
            byte by = 0;
            while (by < 4) {
                if (this.bP == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.bQ[by]), 75 - 8 * by + 70 * this.bm, 117, 20);
                by = (byte)(by + 1);
            }
            graphics.drawImage(this.bb[0], 75 - 8 * this.bP + 70 * this.bm, 115, 20);
        } else {
            graphics.drawString(String.valueOf(this.cd), 140, 117, 17);
            graphics.drawString(String.valueOf(this.ce), 66, 117, 17);
        }
        this.d(graphics);
        if (this.bq) {
            this.a(graphics, this.bs);
        }
        this.b(graphics, d.b[0][1]);
        if (this.bG) {
            this.a(graphics, "\u8be5\u57ce\u5df2\u7ecf\u65e0\u4eba\u628a\u5b88!");
            return;
        }
        if (this.cf) {
            this.a(graphics, "\u8bf7\u4efb\u547d\u65b0\u592a\u5b88");
        }
    }

    private final void N(Graphics graphics) {
        this.O(graphics);
        this.Q(graphics);
    }

    private final void O(Graphics graphics) {
        int n = 0;
        while (n < 13) {
            int n2 = 0;
            while (n2 < 11) {
                int n3 = this.b.bm[n + this.u][n2 + this.t] & 0xF;
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
                        graphics.drawRegion(this.bb[4], n3 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
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
                graphics.drawRegion(this.bb[4], n3 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
                n2 = (byte)(n2 + 1);
            }
            n = (byte)(n + 1);
        }
        graphics.setColor(0xFFFFFF);
    }

    private final boolean a(int n, int n2) {
        return n >= this.t && n2 >= this.u && n - this.t < 11 && n2 - this.u < 13;
    }

    private final boolean b(int n, int n2) {
        return (this.b.bm[n2][n] & 0xF) != 2;
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
        graphics.fillRect(23, 25, 130, 135);
        graphics.setColor(0x996600);
        graphics.drawRect(23, 25, 130, 135);
        if (this.bb[3] == null) {
            this.bb[3] = this.k(15);
        }
        int n = 0;
        while (n < 8) {
            graphics.drawRegion(this.bb[3], n * 15, 0, 15, 15, 0, 35 + n % 2 * 50, 89 + n / 2 * 16, 0);
            n = (byte)(n + 1);
        }
        graphics.drawImage(this.bb[9], 35, 34, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.G[this.Y], 95, 43, 20);
        this.aM = "LV. " + this.b.H[this.Y];
        graphics.drawString(this.aM, 105, 64, 20);
        this.aM = null;
        graphics.drawString(String.valueOf(this.b.M[this.Y]), 55, 88, 20);
        graphics.drawString(String.valueOf(this.b.N[this.Y]), 111, 88, 20);
        graphics.drawString(String.valueOf(this.b.L[this.Y]), 55, 105, 20);
        graphics.drawString(String.valueOf(this.b.O[this.Y]), 111, 105, 20);
        graphics.drawString(String.valueOf(this.b.J[this.Y]), 55, 122, 20);
        graphics.drawString(String.valueOf(this.b.P[this.Y]), 111, 122, 20);
        graphics.drawString(this.b.ad[this.b.R[this.Y]], 55, 138, 20);
        graphics.drawString(this.b.ad[this.b.S[this.Y]], 105, 138, 20);
    }

    private final void P(Graphics graphics) {
        if (this.bb[7] == null) {
            this.bb[7] = this.k(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(12, 27, 152, 130);
        graphics.setColor(0x996600);
        graphics.drawRect(12, 27, 152, 130);
        graphics.drawLine(12, 110, 164, 110);
        graphics.drawImage(this.bb[7], this.bk >> 1, 20, 17);
        graphics.setColor(0xFFFF00);
        graphics.drawString(d.e[this.M], this.bk >> 1, 130, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, d.q[this.M], 15, 35, 150, 80);
        if (this.M < this.N - 1) {
            graphics.drawString(d.e[this.M + 1], 163, 135, 24);
            graphics.drawImage(this.bc, 167, 135, 20);
        }
        if (this.M > 0) {
            graphics.drawString(d.e[this.M - 1], 13, 135, 20);
            graphics.drawRegion(this.bc, 0, 0, 6, 8, 2, 9, 135, 24);
        }
        this.g = "\u8bf7\u9009\u62e9\u4f7f\u7528\u8ba1\u7b56";
    }

    public final void b(Graphics graphics) {
        if (this.bj == null) {
            this.bj = this.k(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(12, 27, 152, 140);
        graphics.setColor(0x996600);
        graphics.drawRect(12, 27, 152, 140);
        graphics.drawLine(12, 120, 164, 120);
        graphics.drawImage(this.bj, this.bk >> 1, 20, 17);
        graphics.setColor(0xFFFF00);
        graphics.drawString(d.k[this.M], this.bk >> 1, 130, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, d.r[this.M], 15, 35, 140, 80);
        String string = "\u5269\u4f59\u8ba1\u7b56\u70b9:" + this.b.bN;
        graphics.drawString(string, this.bk >> 1, 90, 17);
        if (this.M < 5) {
            graphics.drawString(d.k[this.M + 1], 163, 135, 24);
            graphics.drawImage(this.bc, 167, 135, 20);
        }
        if (this.M > 0) {
            graphics.drawString(d.k[this.M - 1], 13, 135, 20);
            graphics.drawRegion(this.bc, 0, 0, 6, 8, 2, 9, 135, 24);
        }
    }

    public final void c(Graphics graphics) {
        int n;
        if (this.bb[8] == null) {
            this.bb[8] = this.k(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(12, 27, 152, 110);
        graphics.setColor(0x996600);
        graphics.drawRect(12, 27, 152, 110);
        graphics.drawImage(this.bb[8], this.bk >> 1, 20, 17);
        int n2 = 0;
        while (n2 <= this.b.aS) {
            n = n2 % 3;
            int n3 = n2 / 3;
            if (n2 == this.b.aS) {
                if (this.b.P[this.b.ax] != 100) {
                    graphics.setColor(16275794);
                    graphics.drawString("\u4e0b\u91ce", 25 + 40 * n, 30 + 35 * n3, 20);
                }
            } else {
                graphics.setColor(0);
                graphics.drawString(this.b.h[this.b.aR[n2]], 25 + 40 * n, 30 + 35 * n3, 20);
            }
            n2 = (byte)(n2 + 1);
        }
        n2 = this.bm % 3;
        n = this.bm / 3;
        graphics.drawImage(this.bc, 17 + 40 * n2, 34 + 35 * n, 0);
    }

    private final void Q(Graphics graphics) {
        try {
            int n;
            byte by = 0;
            while (by < this.b.bi) {
                if ((this.b.bu[by] == 0 || this.b.bu[by] > 3) && this.a((int)this.b.bq[by], (int)this.b.br[by]) && this.b(this.b.bq[by], (int)this.b.br[by])) {
                    n = this.b.J[this.b.bg[by]] / 100;
                    graphics.drawRegion(this.bb[5], n * 15, (this.b.I[this.b.bg[by]] - 1) * 15, 16, 16, 0, (this.b.bq[by] - this.t) * 16, (this.b.br[by] - this.u) * 16, 20);
                }
                by = (byte)(by + 1);
            }
            by = 0;
            while (by < this.b.bj) {
                if ((this.b.bv[by] == 0 || this.b.bv[by] > 3) && this.a((int)this.b.bs[by], (int)this.b.bt[by]) && this.b(this.b.bs[by], (int)this.b.bt[by])) {
                    n = this.b.J[this.b.bh[by]] / 100;
                    graphics.drawRegion(this.bb[5], n * 15, (this.b.I[this.b.bh[by]] + 2) * 15, 16, 16, 0, (this.b.bs[by] - this.t) * 16, (this.b.bt[by] - this.u) * 16, 20);
                }
                by = (byte)(by + 1);
            }
            if (this.b.bB) {
                if (!this.D) {
                    graphics.drawImage(this.bb[0], this.B * 16 + 8, this.C * 16 + 8, 20);
                } else {
                    this.a(graphics, (this.v - this.t) * 16, (this.w - this.u) * 16);
                }
            }
            graphics.drawRegion(this.bb[6], 0, 0, 176, 19, 0, 0, this.bl, 36);
            if (this.H) {
                this.g = "";
                graphics.drawRegion(this.bb[6], 0, 19, 176, 19, 0, 0, this.bl, 36);
                graphics.setColor(0xFFFF00);
                graphics.drawRect(9 + 27 * this.O - 1, this.bl - 16, 25, 13);
                graphics.setColor(0xFFFFFF);
            }
            if (this.b.bB) {
                this.aM = "\u673a\u52a8\u529b:" + String.valueOf(this.b.bk);
                graphics.drawString(this.aM, this.bk, 5, 24);
                this.aM = "\u7cae\u98df: " + String.valueOf(this.b.bd);
                graphics.drawString(this.aM, this.bk, 25, 24);
                this.aM = "\u91d1\u5b50: " + String.valueOf(this.b.bc);
                graphics.drawString(this.aM, this.bk, 45, 24);
            } else {
                this.aM = "\u673a\u52a8\u529b:" + String.valueOf(this.b.bl);
                graphics.drawString(this.aM, this.bk, 5, 24);
                this.aM = "\u7cae\u98df: " + String.valueOf(this.b.bf);
                graphics.drawString(this.aM, this.bk, 25, 24);
                this.aM = "\u91d1\u5b50: " + String.valueOf(this.b.be);
                graphics.drawString(this.aM, this.bk, 45, 24);
            }
            this.aM = null;
            String string = this.b.bA > 30 ? d.a(30) : d.a(this.b.bA);
            graphics.drawString(string, 3, 3, 20);
            if (this.L) {
                this.a(graphics);
            }
            if (this.I) {
                this.P(graphics);
            }
            if (this.P) {
                graphics.setColor(10656072);
                graphics.fillRect(11, 28, 153, 140);
                graphics.drawImage(this.bb[9], this.bk >> 1, 28, 17);
                graphics.setColor(0x996600);
                graphics.drawRect(11, 28, 153, 140);
                graphics.drawImage(this.bb[7], this.bk >> 1, 20, 17);
                graphics.setColor(0xFFFFFF);
                graphics.drawString(this.f(100), this.bk >> 1, 125, 17);
                this.B = (byte)(this.v - this.t);
                this.C = (byte)(this.w - this.u);
                this.g = "\u4f7f\u7528\u8ba1\u7b56: " + d.e[this.M];
            }
            if (this.Q) {
                this.c(graphics);
            }
            if (this.W > 0) {
                this.a(graphics, this.f((int)this.W));
            }
            if (this.T || this.U) {
                this.a(graphics, this.V, this.bt);
            } else {
                this.bb[1] = null;
            }
            this.d(graphics);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private final void R(Graphics graphics) {
        if (this.b.aO < 14) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, this.bk, this.bl);
            graphics.setColor(0xFFFFFF);
            graphics.drawImage(this.bb[0], this.bk >> 1, 24, 17);
            graphics.setColor(0x996600);
            graphics.drawRect(12, 24, this.bb[0].getWidth(), this.bb[0].getHeight());
            graphics.drawImage(this.bb[1], this.bk >> 1, 16, 17);
            graphics.setColor(0xFFFFFF);
            this.a(graphics, this.bx, 10, 150, 150, 100);
            return;
        }
        this.a(graphics, this.bx);
    }

    public final void e() {
        this.a("AI\u4e8b\u4ef6\u7c7b\u578b:" + this.b.aO);
        switch (this.b.aO) {
            case 1: {
                this.bb[0] = this.k(120);
                this.bx = String.valueOf(this.b.G[this.b.i[this.b.au]]) + " \u519b\u653b\u6253 " + this.b.h[this.b.av] + "...";
                return;
            }
            case 2: {
                this.bb[0] = this.k(121);
                this.bx = String.valueOf(this.b.G[this.b.i[this.b.au]]) + " \u5360\u9886\u7a7a\u57ce " + this.b.h[this.b.av] + "!";
                return;
            }
            case 3: {
                this.bb[0] = this.k(120);
                this.bx = String.valueOf(this.b.G[0]) + " \u519b\u653b\u6253 " + this.b.h[0] + "...";
                return;
            }
            case 4: {
                this.bb[0] = this.k(122);
                this.bx = String.valueOf(this.b.G[this.b.i[this.b.au]]) + " \u519b\u653b\u5360\u4e86 " + this.b.h[this.b.av] + "!";
                return;
            }
            case 5: {
                this.bb[0] = this.k(123);
                this.bx = String.valueOf(this.b.G[this.b.i[this.b.au]]) + " \u519b\u653b\u6253 " + this.b.h[this.b.av] + "\u5931\u8d25\u4e86!";
                return;
            }
            case 6: {
                this.bb[0] = this.k(124);
                this.bx = " \u53d1\u751f\u65f1\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aS) {
                    this.bx = String.valueOf(this.bx) + " " + this.b.h[this.b.aR[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 7: {
                this.bb[0] = this.k(125);
                this.bx = "\u53d1\u751f\u6c34\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aS) {
                    this.bx = String.valueOf(this.bx) + " " + this.b.h[this.b.aR[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 8: {
                this.bb[0] = this.k(126);
                this.bx = "\u53d1\u751f\u8757\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aS) {
                    this.bx = String.valueOf(this.bx) + " " + this.b.h[this.b.aR[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 9: {
                this.bb[0] = this.k(127);
                this.bx = "\u53d1\u751f\u761f\u75ab!\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aS) {
                    this.bx = String.valueOf(this.bx) + " " + this.b.h[this.b.aR[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 10: {
                this.bb[0] = this.k(128);
                this.bx = "\u6536\u91d1\u7684\u5b63\u8282\u5230\u4e86!";
                return;
            }
            case 11: {
                this.bb[0] = this.k(129);
                this.bx = "\u6536\u7cae\u7684\u5b63\u8282\u5230\u4e86!";
                return;
            }
            case 12: {
                this.bb[0] = this.k(130);
                this.bx = String.valueOf(this.b.G[this.b.ax]) + "\u8ddf\u968f " + this.b.G[this.b.ay] + " \u52a0\u5165\u6211\u519b\u4e3a\u541b\u4e3b\u6548\u529b!";
                return;
            }
            case 13: {
                this.bb[0] = this.k(136);
                this.bx = "\u767e\u59d3\u53d1\u751f\u9a9a\u4e71,\u6ce2\u53ca\u57ce\u5e02:";
                byte by = 0;
                while (by < this.b.aS) {
                    this.bx = String.valueOf(this.bx) + " " + this.b.h[this.b.aR[by]];
                    by = (byte)(by + 1);
                }
                return;
            }
            case 14: {
                this.bx = String.valueOf(this.b.G[this.b.b[this.b.at]]) + "\u3000\u540c\u76df\u89e3\u9664!";
                return;
            }
            case 15: {
                return;
            }
            case 16: {
                this.bx = String.valueOf(this.b.G[this.b.ay]) + "\u6b63\u4e0e\u654c\u65b9\u4f1a\u8c08\u4e2d...";
                return;
            }
            case 17: {
                this.bx = "\u7cdf\u4e86," + this.b.G[this.b.ay] + "\u6295\u5954\u4e86" + this.b.G[this.b.i[this.b.au]];
                return;
            }
            case 18: {
                this.bx = String.valueOf(this.b.G[this.b.ay]) + " \u52bf\u529b\u706d\u4ea1\u4e86!";
                return;
            }
            case 19: {
                this.bx = String.valueOf(this.b.G[this.b.ay]) + "\u6b7b\u4ea1,\u65b0\u541b\u4e3b " + this.b.G[this.b.b[this.b.at]] + " \u7ee7\u4f4d!";
            }
        }
    }

    public final void a(byte by, short s, short s2) {
        this.bp = true;
        switch (by) {
            case 2: {
                this.bx = String.valueOf(this.b.G[s]) + "\u3000\u541b\u4e3b\u6b7b\u4ea1!";
                return;
            }
            case 5: {
                this.bx = String.valueOf(this.b.G[s]) + " \u52bf\u529b\u706d\u4ea1\u4e86!";
                return;
            }
            case 6: {
                this.bx = String.valueOf(this.b.G[s]) + "\u6b7b\u4ea1,\u65b0\u541b\u4e3b " + this.b.G[s2] + " \u7ee7\u4f4d!";
            }
        }
    }

    public final void keyReleasee(int n) {
        this.aC = false;
    }

    public final void keyRepeatee(int n) {
        this.aC = true;
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
            }
        }
    }

    public final void keyPressee(int n) {
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
                if (this.cF) {
                    a.a();
                    this.cI = true;
                    return;
                }
                this.b.getClass();
                this.a = (byte)32;
            }
        }
    }

    public final void f() {
        switch (this.aZ) {
            case 4: {
                this.k();
                return;
            }
            case 5: {
                if (this.bG && this.a > 0) {
                    this.b.bs();
                    return;
                }
                this.U();
                return;
            }
            case 6: {
                this.Z();
                return;
            }
            case 7: {
                this.Y();
                return;
            }
            case 8: {
                this.X();
                return;
            }
            case 9: {
                this.w();
                return;
            }
            case 10: {
                if (this.a <= 0) break;
                this.b.br();
                return;
            }
            case 11: {
                if (this.a > 0) {
                    this.b.br();
                }
            }
            case 12: {
                this.v();
                return;
            }
            case 13: {
                this.W();
                return;
            }
            case 14: {
                this.u();
                return;
            }
            case 15: {
                this.V();
                return;
            }
            case 16: {
                this.T();
                return;
            }
            case 17: {
                this.y();
                return;
            }
            case 18: {
                this.S();
                return;
            }
            case 23: {
                this.t();
                return;
            }
            case 24: {
                this.A();
                return;
            }
            case 26: {
                this.aj();
                return;
            }
        }
    }

    private final void s() {
        switch (this.a) {
            case 32: {
                this.cs = 0;
                this.a((byte)1);
                return;
            }
            case 1: {
                if (this.cs <= 0) break;
                --this.cs;
                return;
            }
            case 2: {
                if (this.cs >= this.cr - this.cq) break;
                ++this.cs;
                return;
            }
            default: {
                return;
            }
        }
    }

    private final void t() {
        if (this.cn || this.cm) {
            this.cn = false;
            this.b.aq = 1;
            this.o.a();
        }
        if (this.co < 7) {
            this.g = this.by = this.b.a((int)this.co);
            this.co = (byte)(this.co + 1);
            return;
        }
        this.cn = true;
    }

    private final void u() {
        switch (this.a) {
            case 1: {
                if (this.bN) {
                    this.a(this.bQ, this.bP);
                    if (!this.a((int)this.bX[this.bm], this.bQ)) break;
                    this.b(this.bQ, this.bP);
                    return;
                }
                if (!this.bq) {
                    this.bm = 0;
                    return;
                }
                if (!this.bq) break;
                this.bq = false;
                this.bs = 0;
                return;
            }
            case 2: {
                if (this.bN) {
                    this.b(this.bQ, this.bP);
                    if (!this.a((int)this.bX[this.bm], this.bQ)) break;
                    this.a(this.bQ, this.bP);
                    return;
                }
                if (this.bq) break;
                this.bq = true;
                return;
            }
            case 4: {
                if (this.bN) {
                    this.bP = this.k(this.bP);
                    return;
                }
                if (!(this.bq || this.bG || this.cf)) {
                    this.bm = 0;
                    return;
                }
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (this.bN) {
                    this.bP = this.l(this.bP);
                    return;
                }
                if (!(this.bq || this.bG || this.cf)) {
                    this.bm = 1;
                    return;
                }
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (this.bq) {
                    if (this.bs == 0) {
                        this.b.a(this.ce, this.cd, 0);
                        return;
                    }
                    this.b.a(this.ce, this.cd, 1);
                    return;
                }
                if (!this.bq) {
                    if (this.bN) {
                        if (this.bm == 0) {
                            this.ce = this.a(this.bQ);
                        } else {
                            this.cd = this.a(this.bQ);
                        }
                    } else {
                        this.bQ = this.bm == 0 ? this.a(this.ce) : this.a(this.cd);
                        this.bP = 0;
                    }
                    this.bN = !this.bN;
                    return;
                }
                if (!this.bq) break;
                this.bq = false;
                if (this.bs == 0) {
                    if (this.bm == 0) {
                        this.ce = this.a(this.bQ);
                    } else {
                        this.cd = this.a(this.bQ);
                    }
                    this.b.a(this.ce, this.cd, 0);
                    return;
                }
                this.b.a(this.ce, this.cd, 0);
            }
        }
    }

    private final void v() {
        if (this.bV) {
            this.bV = false;
            this.bq = false;
            this.bN = true;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.bN) {
                    this.a(this.bQ, this.bP);
                    int n = this.b.l[this.b.au];
                    if (n > 100) {
                        n = 100;
                    }
                    if (!this.a(n, this.bQ)) break;
                    this.b(this.bQ, this.bP);
                    return;
                }
                this.bq = false;
                return;
            }
            case 2: {
                if (this.bN) {
                    this.b(this.bQ, this.bP);
                    int n = this.b.l[this.b.au];
                    if (n > 100) {
                        n = 100;
                    }
                    if (!this.a(n, this.bQ)) break;
                    this.a(this.bQ, this.bP);
                    return;
                }
                this.bq = true;
                return;
            }
            case 4: {
                if (this.bN) {
                    this.bP = this.k(this.bP);
                    return;
                }
                if (!this.bq) {
                    this.bm = 0;
                    return;
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (this.bN) {
                    this.bP = this.l(this.bP);
                    return;
                }
                if (!this.bq) {
                    if (this.b.w[this.b.au] <= 0) break;
                    this.bm = 1;
                    this.bY = 0;
                    this.bQ = this.a(this.bY);
                    return;
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (this.bm == 0) {
                    this.bN = !this.bN;
                    this.bP = 0;
                    this.bY = this.a(this.bQ);
                    this.a("gold:" + this.bY);
                }
                if (!this.bq) break;
                if (this.bV) {
                    this.bV = false;
                    this.bq = false;
                    return;
                }
                if (this.bs == 0) {
                    if (this.bm == 0) {
                        if (this.bY == 0) {
                            this.bV = true;
                            return;
                        }
                        this.bV = false;
                        this.b.a(this.bY, 0);
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

    private final void w() {
        if (this.bV && this.a > 0) {
            this.bV = false;
            this.bq = false;
            this.bm = 0;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.bm == 3) {
                    this.bq = false;
                }
                if (!this.bN && !this.bq) {
                    if (this.bm > 0) {
                        this.bm = (byte)(this.bm - 1);
                    }
                    this.bQ = this.a(this.bW[this.bm]);
                    return;
                }
                if (!this.bN) break;
                this.a(this.bQ, this.bP);
                if (!this.a((int)this.bX[this.bm], this.bQ)) break;
                this.b(this.bQ, this.bP);
                return;
            }
            case 2: {
                if (!this.bN && !this.bq) {
                    if (this.bm < 3) {
                        this.bm = (byte)(this.bm + 1);
                    }
                    if (this.bm == 3) {
                        this.bq = true;
                        return;
                    }
                    this.bQ = this.a(this.bW[this.bm]);
                    return;
                }
                if (!this.bN) break;
                this.b(this.bQ, this.bP);
                if (!this.a((int)this.bX[this.bm], this.bQ)) break;
                this.a(this.bQ, this.bP);
                return;
            }
            case 4: {
                if (!this.bq && this.bP < 3) {
                    this.bP = (byte)(this.bP + 1);
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (!this.bq && this.bP > 0) {
                    this.bP = (byte)(this.bP - 1);
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (!this.bq) {
                    if (this.bN) {
                        this.bW[this.bm] = this.a(this.bQ);
                    } else {
                        this.bQ = this.a(this.bW[this.bm]);
                    }
                    this.bN = !this.bN;
                    return;
                }
                if (this.bs == 0) {
                    if (!this.x()) {
                        this.b.a(0, this.bW);
                        return;
                    }
                    this.bV = true;
                    return;
                }
                this.b.a(1, this.bW);
            }
        }
    }

    private final boolean x() {
        int n = 0;
        while (n < 3) {
            if (this.bW[n] > 0) {
                return false;
            }
            n = (byte)(n + 1);
        }
        return true;
    }

    private final void y() {
        switch (this.a) {
            case 1: {
                if (this.bq || this.bm <= 0) break;
                this.bm = (byte)(this.bm - 1);
                return;
            }
            case 2: {
                if (this.bq || this.bm >= 3) break;
                this.bm = (byte)(this.bm + 1);
                return;
            }
            case 4: {
                if (!this.bq && this.bu > 0) {
                    this.bu = (byte)(this.bu - 1);
                    this.bm = 0;
                    return;
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (!this.bq && this.bu < 3) {
                    this.bu = (byte)(this.bu + 1);
                    this.bm = 0;
                    return;
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (!this.bq) {
                    this.bq = true;
                    return;
                }
                if (this.bs == 0) {
                    this.z();
                    return;
                }
                if (this.aA) {
                    this.b.aq = (byte)2;
                    return;
                }
                this.b.p(1);
                return;
            }
            case 32: {
                if (this.aA) {
                    this.b.aq = (byte)2;
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
    private final void z() {
        byte by = this.a(this.bu, this.bm);
        if (by == 15 || by == 23) {
            if (this.b.M[this.b.ax] > 95) {
                if (this.h(by)) {
                    if (!this.aA) {
                        this.b.y[this.b.au] = 1;
                        this.b.p(0);
                        return;
                    }
                    this.b.z[this.b.bb] = 1;
                    this.b.aq = 1;
                    return;
                }
                if (this.aA) {
                    this.b.aq = (byte)3;
                    return;
                }
                this.b.p(2);
                return;
            }
            if (this.aA) {
                this.b.aq = (byte)4;
                return;
            }
            this.b.p(3);
            return;
        }
        if (by == 22) {
            if (this.b.M[this.b.ax] > 90) {
                if (this.h(by)) {
                    if (!this.aA) {
                        this.b.y[this.b.au] = 1;
                        this.b.p(0);
                        return;
                    }
                    this.b.z[this.b.bb] = 1;
                    if (!this.aA) return;
                    this.b.aq = 1;
                    return;
                }
                if (this.aA) {
                    this.b.aq = (byte)3;
                    return;
                }
                this.b.p(2);
                return;
            }
            if (this.aA) {
                this.b.aq = (byte)4;
                return;
            }
            this.b.p(3);
            return;
        }
        if (by == 30) {
            if (this.b.O[this.b.ax] > 90) {
                if (this.h(by)) {
                    if (!this.aA) {
                        this.b.y[this.b.au] = 1;
                        this.b.p(0);
                        return;
                    }
                    this.b.z[this.b.bb] = 1;
                    if (!this.aA) return;
                    this.b.aq = 1;
                    return;
                }
                if (this.aA) {
                    this.b.aq = (byte)3;
                    return;
                }
                this.b.p(2);
                return;
            }
            if (this.aA) {
                this.b.aq = (byte)4;
                return;
            }
            this.b.p(3);
            return;
        }
        if (this.h(by)) {
            if (this.aA) {
                this.b.aq = 1;
                return;
            }
            this.b.p(0);
            return;
        }
        if (this.aA) {
            this.b.aq = (byte)3;
            return;
        }
        this.b.p(2);
    }

    private final boolean h(byte by) {
        short s = this.b.ae[by];
        if (this.aA) {
            if (this.b.bc < s) {
                return false;
            }
            this.b.bc = (short)(this.b.bc - s);
            if (this.bu == 3) {
                this.b.S[this.X] = by;
            } else {
                this.b.R[this.X] = by;
            }
        } else {
            if (this.b.l[this.b.au] < s) {
                return false;
            }
            byte by2 = this.b.au;
            this.b.l[by2] = (short)(this.b.l[by2] - s);
            if (this.bu == 3) {
                this.b.S[this.b.ax] = by;
            } else {
                this.b.R[this.b.ax] = by;
            }
        }
        return true;
    }

    private final void A() {
        switch (this.a) {
            case 1: {
                if (this.bA <= 0) break;
                this.bA = (byte)(this.bA - 1);
                return;
            }
            case 2: {
                if (this.bA >= 2) break;
                this.bA = (byte)(this.bA + 1);
                return;
            }
            case 4: {
                if (this.bq) {
                    this.bs = 0;
                    return;
                }
                byte by = this.bA;
                this.m[by] = (byte)(this.m[by] - 1);
                if (this.m[this.bA] >= 0) break;
                this.m[this.bA] = 0;
                return;
            }
            case 8: {
                if (this.bq) {
                    this.bs = 1;
                    return;
                }
                byte by = this.bA;
                this.m[by] = (byte)(this.m[by] + 1);
                if (this.bA < 2 && this.m[this.bA] > 1) {
                    this.m[this.bA] = 1;
                }
                if (this.bA != 2 || this.m[this.bA] <= 4) break;
                this.m[this.bA] = 4;
                return;
            }
            case 16: {
                if (!this.bq) {
                    this.bq = true;
                    return;
                }
                if (this.bs == 0) {
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
        if (this.bp) {
            this.bp = false;
            return;
        }
        if (this.az > -1) {
            this.az = (byte)-1;
            this.b.aq = 1;
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
        if (this.ar) {
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
                    if (this.ap == 5 && this.b.P[this.X] == 100) {
                        this.bx = d.t;
                        this.bp = true;
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
    }

    private final void d(int n) {
        int n2 = 4;
        if (this.b.ck == 0) {
            n2 = 5;
        }
        switch (n) {
            case 2: {
                if (this.b.ck <= 0) break;
                this.b.ck = (byte)(this.b.ck - 1);
                return;
            }
            case 4: {
                if (this.b.cj[this.b.ck] <= 0) break;
                byte by = this.b.ck;
                this.b.cj[by] = (byte)(this.b.cj[by] - 1);
                return;
            }
            case 6: {
                if (this.b.cj[this.b.ck] >= n2 - 1) break;
                byte by = this.b.ck;
                this.b.cj[by] = (byte)(this.b.cj[by] + 1);
                return;
            }
            case 8: {
                if (this.b.ck >= 3) break;
                this.b.ck = (byte)(this.b.ck + 1);
            }
        }
    }

    private final void e(int n) {
        switch (n) {
            case 4: {
                if (this.ch <= 0) break;
                this.ch = (byte)(this.ch - 1);
                return;
            }
            case 6: {
                if (this.ch >= 3) break;
                this.ch = (byte)(this.ch + 1);
            }
        }
    }

    public final void h() {
        if (this.bp) {
            this.bp = false;
            if (this.ag) {
                this.ag = false;
                this.b.aq = (byte)2;
                return;
            }
            this.b.ci = 1;
            if (this.cj) {
                this.cj = false;
                this.b.aq = 1;
            }
            this.b.cj[0] = 1;
            return;
        }
        if (this.ak) {
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.af != 2 || this.ck) break;
                this.d(2);
                return;
            }
            case 2: {
                if (this.af != 2 || this.ck) break;
                this.d(8);
                return;
            }
            case 4: {
                if (this.af == 1) {
                    this.e(4);
                    return;
                }
                if (this.b.ci == 1) {
                    if (this.aa <= 0) break;
                    this.aa = (byte)(this.aa - 1);
                    return;
                }
                if (this.ck && this.M > 0) {
                    this.M = (byte)(this.M - 1);
                    return;
                }
                if (this.ck) break;
                this.d(4);
                return;
            }
            case 8: {
                if (this.af == 1) {
                    this.e(6);
                    return;
                }
                if (this.b.ci == 1) {
                    if (this.aa >= 5) break;
                    this.aa = (byte)(this.aa + 1);
                    return;
                }
                if (this.ck && this.M < 5) {
                    this.M = (byte)(this.M + 1);
                    return;
                }
                if (this.I) break;
                this.d(6);
                return;
            }
            case 16: {
                this.B();
                return;
            }
            case 32: {
                if (this.b.ci == 2) {
                    this.b.ci = 1;
                }
                if (!this.ck) break;
                this.ck = false;
                this.M = 0;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void B() {
        switch (this.af) {
            case 1: {
                this.af = (byte)(this.af + 1);
                this.bi = null;
                return;
            }
            case 2: {
                if (this.ck) {
                    this.ck = false;
                    this.bp = true;
                    if (this.b.bN < this.b.b(this.M)) {
                        this.bx = "\u6218\u672f\u80fd\u529b\u4e0d\u591f";
                        return;
                    }
                    if (this.b.a(this.M)) {
                        this.bx = "\u8ba1\u7b56[" + d.k[this.M].substring(0, 2) + "] \u65bd\u7528\u6210\u529f\uff01";
                        if (this.M == 1) {
                            this.cj = true;
                            return;
                        }
                        if (this.M != 5) return;
                        this.ak = true;
                        this.b.co = (byte)40;
                        this.bp = false;
                        this.b.ck = 0;
                        this.b.cj[0] = 0;
                        this.b.ci = 1;
                        return;
                    } else {
                        this.bx = "\u8ba1\u7b56[" + d.k[this.M].substring(0, 2) + "] \u65bd\u7528\u5931\u8d25\uff01";
                    }
                    return;
                }
                if (this.b.ci == 1) {
                    this.b.ci = (byte)2;
                    return;
                }
                if (this.b.cj[0] == 4) {
                    if (this.b.cd != 0) {
                        this.bp = true;
                        this.bx = "\u8ba1\u7b56\u4f7f\u7528\u4e2d!";
                        this.b.cj[0] = 0;
                        return;
                    }
                    this.ck = true;
                    this.M = 0;
                } else {
                    this.b.ci = 1;
                }
                this.b.ck = 0;
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
                string = this.b.bD;
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
                this.b.aq = 1;
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
                string = this.b.bD;
            }
        }
        return string;
    }

    public final void i() {
        if (this.bp) {
            this.bp = false;
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
                this.b.aq = 1;
                this.W = 0;
                return;
            }
            case 4: {
                this.W = 0;
                break;
            }
            case 8: {
                this.W = 0;
                this.b.aq = 1;
                return;
            }
            case 5: 
            case 6: 
            case 22: {
                this.W = 0;
                this.b.L();
                this.b.aq = (byte)4;
                break;
            }
            case 7: 
            case 9: 
            case 20: 
            case 21: {
                this.W = 0;
                this.J = false;
                this.K = true;
                if (this.b.A()) {
                    this.g = "\u64a4\u9000\u6b66\u5c06 " + this.b.G[this.b.ax];
                }
                return;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: {
                this.b.aq = 1;
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
                this.b.aq = 1;
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
                this.M();
                return;
            }
            case 2: {
                this.N();
                return;
            }
            case 4: {
                this.K();
                return;
            }
            case 8: {
                this.L();
                return;
            }
            case 16: {
                this.G();
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
                            this.b.bq[this.p] = this.x;
                            this.b.br[this.p] = this.y;
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
                            this.b.bk = this.R;
                            this.b.bq[this.p] = this.v;
                            this.b.br[this.p] = this.w;
                            this.B = (byte)(this.v - this.t);
                            this.C = (byte)(this.w - this.u);
                            return;
                        }
                        this.K = false;
                        this.D = false;
                        this.H = true;
                        this.J = false;
                        byte[] byArray = this.b.bm[this.y];
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

    private final void C() {
        if (this.b.L[this.X] == this.b.K[this.X]) {
            this.J = true;
            this.W = (byte)15;
            return;
        }
        if (this.b.bc < 50) {
            this.J = true;
            this.W = (byte)16;
            return;
        }
        this.V = "\u662f\u5426\u5bf9\u6b66\u5c06\u8fdb\u884c\u6cbb\u7597?";
        this.U = true;
    }

    private final void D() {
        if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 1) {
            byte by = 0;
            while (by < this.b.bi) {
                if (this.b.bq[by] == (byte)(this.B + this.t) && this.b.br[by] == (byte)(this.C + this.u) && (this.b.bu[by] == 0 || this.b.bu[by] > 3)) {
                    this.J = false;
                    this.H = true;
                    this.X = this.b.bg[by];
                    this.v = this.b.bq[by];
                    this.w = this.b.br[by];
                    this.p = by;
                }
                by = (byte)(by + 1);
            }
        }
    }

    private final void i(byte by) {
        switch (by) {
            case 0: {
                this.D = true;
                this.g = "\u6b66\u5c06\u79fb\u52a8";
                byte by2 = 0;
                while (by2 < this.b.bi) {
                    if (this.b.bq[by2] == this.B + this.t && this.b.br[by2] == this.C + this.u) {
                        this.D = true;
                        this.X = this.b.bg[by2];
                        this.v = (byte)(this.B + this.t);
                        this.w = (byte)(this.C + this.u);
                        this.p = by2;
                        this.x = this.v;
                        this.y = this.w;
                        this.R = this.b.bk;
                        byte[] byArray = this.b.bm[this.w];
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
                this.g = "\u64a4\u9000\u6b66\u5c06 " + this.b.G[this.X];
                return;
            }
            case 5: {
                this.J = true;
                this.K = false;
                this.g = "\u6211\u519b\u5f85\u673a\u4e2d...";
                this.b.aq = 1;
            }
        }
    }

    public final void j() {
        this.a("call doSelPos()");
        if (!this.r) {
            this.X = this.b.bg[this.p];
            this.v = this.b.bq[this.p];
            this.w = this.b.br[this.p];
            this.x = this.b.bq[this.p];
            this.y = this.b.br[this.p];
            this.B = (byte)(this.v - this.t);
            this.C = (byte)(this.w - this.u);
            this.K = true;
            this.D = true;
            byte[] byArray = this.b.bm[this.w];
            byte by = this.v;
            byArray[by] = (byte)(byArray[by] & 0x3F);
            this.s = (byte)3;
            this.r = true;
        }
        this.g = "\u521d\u59cb\u4f4d\u7f6e(\u654c:" + d.h[this.b.by] + d.i[this.b.bz] + ")";
    }

    private final void E() {
        this.b.bq[this.p] = this.v;
        this.b.br[this.p] = this.w;
        byte[] byArray = this.b.bm[this.w];
        byte by = this.v;
        byArray[by] = (byte)(byArray[by] | 0x40);
        this.K = false;
        this.D = false;
        this.S = false;
        if (!this.q) {
            if (this.p == this.b.bi - 1) {
                this.q = true;
                this.p = 0;
                this.J = true;
                this.s = (byte)3;
                this.b.aq = 1;
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
        if (this.j(this.b.bm[this.w][this.v])) {
            this.V = "\u662f\u5426\u8fdb\u5165\u6751\u5e84?";
            this.T = true;
            return;
        }
        this.J = true;
        this.g = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
    }

    private final boolean j(byte by) {
        byte by2 = (byte)(by & 0xF);
        return by2 == 3 || by2 == 4 || by2 == 1;
    }

    private final void F() {
        if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 2) {
            byte by = 0;
            while (by < this.b.bj) {
                if ((this.b.bv[by] <= 0 || this.b.bv[by] > 3) && this.b.bs[by] == (byte)(this.B + this.t) && this.b.bt[by] == (byte)(this.C + this.u)) {
                    this.Y = this.b.bh[by];
                    this.bb[9] = null;
                    this.bb[9] = this.b(this.Y);
                    this.L = true;
                    this.g = "\u6b66\u5c06\u4fe1\u606f";
                    return;
                }
                by = (byte)(by + 1);
            }
            return;
        }
        if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 1) {
            byte by = 0;
            while (by < this.b.bi) {
                if (this.b.bq[by] == (byte)(this.B + this.t) && this.b.br[by] == (byte)(this.C + this.u)) {
                    this.Y = this.b.bg[by];
                    this.bb[9] = null;
                    this.bb[9] = this.b(this.Y);
                    this.L = true;
                    this.g = "\u6b66\u5c06\u4fe1\u606f";
                    return;
                }
                by = (byte)(by + 1);
            }
        }
    }

    private final void G() {
        if (this.U) {
            if (this.bt == 0) {
                this.b.bc = (short)(this.b.bc - 50);
                short s = this.X;
                this.b.L[s] = (byte)(this.b.L[s] + this.b.bI());
                if (this.b.L[this.X] > this.b.K[this.X] || this.b.L[this.X] < 0) {
                    this.b.L[this.X] = this.b.K[this.X];
                }
                this.W = (byte)17;
            } else {
                this.bt = 0;
            }
            this.J = true;
            this.U = false;
            return;
        }
        if (this.T) {
            if (this.bt == 0) {
                byte by = (byte)(this.b.bm[this.w][this.v] & 0xF);
                if (by == 4) {
                    this.C();
                } else if (by == 1) {
                    this.b.aq = (byte)5;
                } else if (by == 3) {
                    this.b.aq = (byte)6;
                }
            } else {
                this.bt = 0;
                this.J = true;
            }
            this.T = false;
            return;
        }
        if (this.J) {
            this.D();
            return;
        }
        if (this.H) {
            this.H = false;
            if (this.O == 0 && this.b.bu[this.p] > 3) {
                this.W = (byte)19;
                this.J = true;
                return;
            }
            this.i(this.O);
            this.O = 0;
            this.K = true;
            return;
        }
        if (this.K) {
            if (this.D) {
                this.E();
                return;
            }
            if (this.E) {
                this.F();
                return;
            }
            if (this.G) {
                this.J();
                return;
            }
            if (this.F) {
                this.H();
                return;
            }
            if (this.Q) {
                if (this.bm == this.b.aS) {
                    this.b.i((byte)0);
                } else {
                    this.b.i(this.b.aR[this.bm]);
                }
                this.bm = 0;
                if (!this.Z) {
                    this.K = false;
                    this.V = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
                    this.J = true;
                    return;
                }
                this.g = "\u64a4\u9000\u6b66\u5c06 " + this.b.G[this.b.ax];
            }
        }
    }

    private final void H() {
        byte by = 0;
        if (this.I) {
            by = this.b.g(this.M);
            if (by > this.b.bk) {
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
            if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 2) {
                byte by2 = 0;
                while (by2 < this.b.bj) {
                    if ((this.b.bv[by2] <= 0 || this.b.bv[by2] > 3) && this.b.bs[by2] == (byte)(this.B + this.t) && this.b.bt[by2] == (byte)(this.C + this.u)) {
                        if (this.b.a((int)this.p, (int)by2, this.M, true)) {
                            this.P = true;
                            this.bb[9] = null;
                            this.bb[9] = this.k(this.M + 33);
                            this.Y = this.b.bh[by2];
                            int n = this.b.a(this.X, this.Y, this.M, this.b.bf);
                            if (n > 0) {
                                this.b.a(n, this.M);
                                short s = this.b.J[this.Y];
                                this.b.a(this.Y, this.b.bv, by2, this.p, n, true);
                                short s2 = this.X;
                                this.b.Q[s2] = (short)(this.b.Q[s2] + (s - this.b.J[this.Y]));
                                this.b.f(this.X);
                            } else {
                                this.b.bD = "\u8ba1\u7b56\u5b9e\u65bd\u5931\u8d25!";
                            }
                            this.b.bk = (byte)(this.b.bk - this.b.g(this.M));
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
        } else if (this.b.bm[this.C + this.u][this.B + this.t] == 5 && this.b.J[this.X] >= 100) {
            this.P = true;
            this.b.bk = (byte)(this.b.bk - this.b.g(this.M));
            this.b.bD = "\u57cb\u4f0f\u5b8c\u6bd5";
            this.F = false;
            this.M = 0;
            this.K = false;
            this.J = true;
            short s = this.X;
            this.b.J[s] = (short)(this.b.J[s] - 100);
            byte[] byArray = this.b.bm[this.C + this.u];
            int n = this.B + this.t;
            byArray[n] = (byte)(byArray[n] | 0x20);
        }
    }

    private final boolean I() {
        return Math.abs(this.v - this.t - this.B + (this.w - this.u - this.C)) == 1;
    }

    private final void J() {
        if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 2 && this.I()) {
            if (this.b.bk >= 2) {
                byte by = 0;
                while (by < this.b.bj) {
                    if ((this.b.bv[by] <= 0 || this.b.bv[by] > 3) && this.b.bs[by] == (byte)(this.B + this.t) && this.b.bt[by] == (byte)(this.u + this.C)) {
                        this.Y = this.b.bh[by];
                        this.b.bk = (byte)(this.b.bk - 2);
                        this.b.bH = (byte)(this.b.bm[this.C + this.u][this.B + this.t] & 0xF);
                        this.b.ax = this.X;
                        this.b.ay = this.Y;
                        this.b.aq = (byte)2;
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
                if (this.C > 0) {
                    this.C = (short)(this.C - 1);
                    break;
                }
                if (this.u <= 0) break;
                this.u = (byte)(this.u - 1);
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
                if (this.B < 10) {
                    this.B = (short)(this.B + 1);
                    break;
                }
                if (this.t >= this.b.bn - 12) break;
                this.t = (byte)(this.t + 1);
                break;
            }
            case 8: {
                if (this.C < 11) {
                    this.C = (short)(this.C + 1);
                    break;
                }
                if (this.u >= this.b.bo - 13) break;
                this.u = (byte)(this.u + 1);
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
                this.bm = (byte)(this.bm - 1);
                if (this.bm >= 0) break;
                this.bm = 0;
                return;
            }
            case 6: {
                this.bm = (byte)(this.bm + 1);
                if (this.bm < this.b.aS) break;
                if (this.b.P[this.b.ax] != 100) {
                    this.bm = this.b.aS;
                    return;
                }
                this.bm = (byte)(this.bm - 1);
            }
        }
    }

    private final void K() {
        if (this.T || this.U) {
            this.bt = 0;
            return;
        }
        if (this.D && this.v > 0) {
            this.P();
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

    private final void L() {
        if (this.T || this.U) {
            this.bt = 1;
            return;
        }
        if (this.D && this.v < this.b.bn - 2) {
            this.Q();
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

    private final void M() {
        if (this.D && this.w > 0) {
            this.O();
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

    private final void N() {
        if (this.D && this.w < this.b.bo - 2) {
            this.R();
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

    private final void O() {
        if (this.b.f(this.b.bm[this.w - 1][this.v]) == 0) {
            int n = this.b.h(this.b.bm[this.w - 1][this.v], this.b.I[this.X]);
            this.a("aStep:" + n + "-" + this.b.bk);
            if (n <= this.b.bk) {
                if (this.q) {
                    this.b.bk = (byte)(this.b.bk - n);
                } else if (this.s > 0) {
                    this.s = (byte)(this.s - 1);
                } else {
                    return;
                }
                if (this.C > 0) {
                    this.C = (short)(this.C - 1);
                } else {
                    this.u = (byte)(this.u - 1);
                }
                this.S = true;
                this.b.br[this.p] = this.w = (byte)(this.w - 1);
            }
        }
        this.a("curP:" + this.b.bq[this.p] + "-" + this.b.br[this.p]);
    }

    private final void P() {
        int n;
        if (this.b.f(this.b.bm[this.w][this.v - 1]) == 0 && (n = this.b.h(this.b.bm[this.w][this.v - 1], this.b.I[this.X])) <= this.b.bk) {
            if (this.q) {
                this.b.bk = (byte)(this.b.bk - n);
            } else if (this.s > 0) {
                this.s = (byte)(this.s - 1);
            } else {
                return;
            }
            if (this.B > 0) {
                this.B = (short)(this.B - 1);
            } else {
                this.t = (byte)(this.t - 1);
            }
            this.b.bq[this.p] = this.v = (byte)(this.v - 1);
            this.S = true;
        }
    }

    private final void Q() {
        int n;
        if (this.b.f(this.b.bm[this.w][this.v + 1]) == 0 && (n = this.b.h(this.b.bm[this.w][this.v + 1], this.b.I[this.X])) <= this.b.bk) {
            if (this.q) {
                this.b.bk = (byte)(this.b.bk - n);
            } else if (this.s > 0) {
                this.s = (byte)(this.s - 1);
            } else {
                return;
            }
            if (this.B < 10) {
                this.B = (short)(this.B + 1);
            } else if (this.t < this.b.bn - 12) {
                this.t = (byte)(this.t + 1);
            }
            this.b.bq[this.p] = this.v = (byte)(this.v + 1);
            this.S = true;
        }
    }

    private final void R() {
        int n;
        if (this.b.f(this.b.bm[this.w + 1][this.v]) == 0 && (n = this.b.h(this.b.bm[this.w + 1][this.v], this.b.I[this.X])) <= this.b.bk) {
            if (this.q) {
                this.b.bk = (byte)(this.b.bk - n);
            } else if (this.s > 0) {
                this.s = (byte)(this.s - 1);
            } else {
                return;
            }
            if (this.C < 11) {
                this.C = (short)(this.C + 1);
            } else if (this.u < this.b.bo - 13) {
                this.u = (byte)(this.u + 1);
            }
            this.b.br[this.p] = this.w = (byte)(this.w + 1);
            this.S = true;
        }
    }

    private final void S() {
        if (this.bp) {
            this.bp = false;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.bq || this.bm <= 0 || this.aW) break;
                this.bm = (byte)(this.bm - 1);
                return;
            }
            case 2: {
                if (this.bq) break;
                this.b.getClass();
                if (this.bm >= 2 || this.aW) break;
                this.bm = (byte)(this.bm + 1);
                return;
            }
            case 4: 
            case 8: {
                if (this.aW) {
                    this.bt = (byte)(this.bt ^ 1);
                    return;
                }
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (this.aW) {
                    if (this.bt == 0) {
                        this.a((byte)26);
                        return;
                    }
                    this.aW = false;
                    return;
                }
                if (this.bq) {
                    if (this.bs == 0) {
                        this.aT = this.bm;
                        if (this.b.bP == 22) {
                            if (this.bm == 2) {
                                int n = this.b.aJ((byte)0);
                                this.aX[this.bm] = null;
                                this.aX[this.bm] = new byte[n];
                                System.arraycopy(this.b.cZ, 0, this.aX[this.bm], 0, n);
                                this.aS[2] = this.b(this.b.db);
                                this.bp = true;
                                this.bx = "\u5b58\u50a8\u6210\u529f";
                                this.b.q(5);
                                return;
                            }
                            if (this.bm == 1) {
                                int n = this.b.cu();
                                if (n == -1) {
                                    this.bp = true;
                                    this.bx = "\u5b58\u50a8\u5931\u8d25, \u8bf7\u7a0d\u540e\u518d\u8bd5";
                                    return;
                                }
                                this.aX[this.bm] = null;
                                this.aX[this.bm] = new byte[n];
                                System.arraycopy(this.b.cZ, 0, this.aX[this.bm], 0, n);
                                this.aS[1] = this.b(this.b.db);
                                this.bp = true;
                                this.bx = "\u5b58\u50a8\u6210\u529f";
                                this.b.q(5);
                                return;
                            }
                            if (this.bm != 0) break;
                            if (this.al()) {
                                this.bp = true;
                                this.bx = "\u5b58\u50a8\u6210\u529f";
                                this.b.q((int)this.aT);
                                return;
                            }
                            this.b.q(5);
                            return;
                        }
                        if (this.b.bP != 23) break;
                        if (this.bm == 1) {
                            if (this.aX[0] != null) {
                                this.b.da = this.aX[this.bm];
                                this.b.q((int)this.bm);
                                return;
                            }
                            this.b.G((int)this.bm);
                            this.b.q((int)this.bm);
                            return;
                        }
                        if (this.bm == 2) {
                            this.b.da = this.aX[this.bm];
                            this.b.q((int)this.bm);
                            return;
                        }
                        if (this.bm != 0) break;
                        if (this.aU) {
                            if (this.aX[0] != null) {
                                this.b.da = this.aX[this.bm];
                                this.b.q((int)this.bm);
                                return;
                            }
                            if (!this.am()) break;
                            this.b.q((int)this.bm);
                            return;
                        }
                        this.a((byte)26);
                        return;
                    }
                    this.b.q(5);
                    return;
                }
                if (this.bm < 1 && !this.aU) {
                    this.aW = true;
                    this.bt = 0;
                    return;
                }
                if (this.aS[this.bm].compareTo("\u5c1a\u65e0\u5b58\u6863") == 0 && this.b.bP != 22) break;
                this.bq = true;
                return;
            }
            case 32: {
                if (this.aW) {
                    this.aW = false;
                    return;
                }
                this.b.q(5);
            }
        }
    }

    private final void T() {
        switch (this.a) {
            case 1: {
                if (this.ca || this.bq) break;
                this.a(this.bQ, this.bP);
                if (!this.a((int)this.bX[this.bm], this.bQ)) break;
                this.b(this.bQ, this.bP);
                return;
            }
            case 2: {
                if (this.ca || this.bq) break;
                this.b(this.bQ, this.bP);
                if (!this.a((int)this.bX[this.bm], this.bQ)) break;
                this.a(this.bQ, this.bP);
                return;
            }
            case 4: {
                if (this.ca) {
                    this.bm = (byte)(this.bm ^ 1);
                    return;
                }
                if (!this.bq) {
                    this.bP = this.k(this.bP);
                    return;
                }
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (this.ca) {
                    this.bm = (byte)(this.bm ^ 1);
                    return;
                }
                if (!this.bq) {
                    this.bP = this.l(this.bP);
                    return;
                }
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (this.ca) {
                    this.ca = false;
                    return;
                }
                if (!this.bq) {
                    this.bq = true;
                    return;
                }
                if (!this.bq) break;
                if (this.bV) {
                    this.bV = false;
                    this.bq = false;
                    return;
                }
                if (this.bs == 0) {
                    short s = this.a(this.bQ);
                    if (s == 0) {
                        this.bV = true;
                        return;
                    }
                    if (this.bm == 0) {
                        this.cb = s * 4 / 3;
                        if (this.aA) {
                            this.b.bc = (short)(this.b.bc - s);
                            this.b.bd = (short)(this.b.bd + (short)this.cb);
                        } else {
                            byte by = this.b.au;
                            this.b.l[by] = (short)(this.b.l[by] - s);
                            byte by2 = this.b.au;
                            this.b.m[by2] = (short)(this.b.m[by2] + (short)this.cb);
                        }
                    } else {
                        this.cb = 3 * s / 4;
                        if (this.aA) {
                            this.b.bd = (short)(this.b.bd - s);
                            this.b.bc = (short)(this.b.bc + (short)this.cb);
                        } else {
                            byte by = this.b.au;
                            this.b.m[by] = (short)(this.b.m[by] - s);
                            byte by3 = this.b.au;
                            this.b.l[by3] = (short)(this.b.l[by3] + (short)this.cb);
                        }
                    }
                }
                if (this.aA) {
                    this.b.aq = (byte)(this.bs + 1);
                    return;
                }
                this.b.o((int)this.bs);
                return;
            }
            case 32: {
                if (this.aA) {
                    this.b.aq = (byte)2;
                    return;
                }
                this.b.o(1);
            }
        }
    }

    private final void U() {
        switch (this.a) {
            case 1: {
                if (!this.bZ) break;
                this.bm = (byte)(this.bm - 1);
                if (this.bm > -1) {
                    this.bb[2] = null;
                    this.bb[2] = this.b(this.b.A[this.b.av][this.bm]);
                    return;
                }
                this.bm = (byte)(this.bm + 1);
                return;
            }
            case 2: {
                if (!this.bZ) break;
                this.bm = (byte)(this.bm + 1);
                if (this.bm < this.b.r[this.b.av]) {
                    this.bb[2] = null;
                    this.bb[2] = this.b(this.b.A[this.b.av][this.bm]);
                    return;
                }
                this.bm = (byte)(this.bm - 1);
                return;
            }
            case 4: 
            case 8: {
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (!this.bZ) {
                    if (this.bs == 0) {
                        this.bZ = true;
                        return;
                    }
                    this.b.bs();
                    return;
                }
                this.bZ = false;
                if (this.bs != 1) break;
                this.b.bs();
                return;
            }
            case 32: {
                this.b.bs();
            }
        }
    }

    private final void V() {
        switch (this.a) {
            case 1: {
                if (this.bq || this.bm <= 0) break;
                this.bb[0] = null;
                this.bm = (byte)(this.bm - 1);
                short s = this.b.F[this.b.b[this.b.aQ[this.bm]]];
                this.bb[0] = this.b(s);
                return;
            }
            case 2: {
                if (this.bq || this.bm >= this.b.aT - 1) break;
                this.bb[0] = null;
                this.bm = (byte)(this.bm + 1);
                short s = this.b.F[this.b.b[this.b.aQ[this.bm]]];
                this.bb[0] = this.b(s);
                return;
            }
            case 4: 
            case 8: {
                if (!this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (this.bq) {
                    this.b.b(this.b.aQ[this.bm], (int)this.bs);
                    return;
                }
                this.bq = true;
                return;
            }
            case 32: {
                this.b.b(this.b.aQ[this.bm], 1);
            }
        }
    }

    private final void W() {
        switch (this.a) {
            case 4: 
            case 8: {
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                this.b.n((int)this.bs);
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

    private final byte k(byte by) {
        if (by < 3) {
            by = (byte)(by + 1);
        }
        return by;
    }

    private final byte l(byte by) {
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

    private final void X() {
        switch (this.a) {
            case 1: {
                if (this.bM && !this.bq) {
                    if (this.bO) {
                        if (this.bS <= 0) break;
                        this.bS = (byte)(this.bS - 1);
                        return;
                    }
                    this.a(this.bT[this.bS + this.bL * 8], this.bP);
                    int n = this.bR + this.bU;
                    if (n > 1000) {
                        n = 1000;
                    }
                    if (this.a(n, this.bT[this.bS + this.bL * 8])) {
                        this.b(this.bT[this.bS + this.bL * 8], this.bP);
                    }
                    this.bR = (short)(this.bR - (short)(this.a(this.bT[this.bS + this.bL * 8]) - this.bU));
                    this.bU = this.a(this.bT[this.bS + this.bL * 8]);
                    return;
                }
                if (this.bq) break;
                if (this.b.v[this.b.au] >= 9900) {
                    return;
                }
                this.a(this.bQ, this.bP);
                int n = 9900 - this.b.v[this.b.au];
                if (n > this.b.l[this.b.au] * 5) {
                    n = this.b.l[this.b.au] * 5;
                }
                if (n > this.b.p[this.b.au]) {
                    n = this.b.p[this.b.au];
                }
                if (!this.a(n, this.bQ)) break;
                this.b(this.bQ, this.bP);
                return;
            }
            case 2: {
                if (this.bM && !this.bq) {
                    if (this.bO) {
                        int n = 0;
                        n = this.bK == 1 ? this.b.r[this.b.au] : (this.bL < this.bK - 1 ? 7 : this.b.r[this.b.au] % 8);
                        if (this.bS >= n) break;
                        this.bS = (byte)(this.bS + 1);
                        return;
                    }
                    this.b(this.bT[this.bS + this.bL * 8], this.bP);
                    short s = (short)(this.bR - this.a(this.bT[this.bS + this.bL * 8]) + this.bU);
                    if (s > 9900 || this.a(this.bT[this.bS + this.bL * 8]) > 1000 || s < 0) {
                        this.a(this.bT[this.bS + this.bL * 8], this.bP);
                        return;
                    }
                    this.bR = s;
                    this.bU = this.a(this.bT[this.bS + this.bL * 8]);
                    return;
                }
                if (this.bq) break;
                if (this.b.v[this.b.au] >= 9900) {
                    return;
                }
                this.b(this.bQ, this.bP);
                int n = 9900 - this.b.v[this.b.au];
                if (n > this.b.l[this.b.au] * 5) {
                    n = this.b.l[this.b.au] * 5;
                }
                if (n > this.b.p[this.b.au]) {
                    n = this.b.p[this.b.au];
                }
                if (!this.a(n, this.bQ)) break;
                this.a(this.bQ, this.bP);
                return;
            }
            case 4: {
                if (this.bM) {
                    if (this.bN) {
                        this.bP = this.k(this.bP);
                    } else if (this.bL > 0) {
                        this.bL = (byte)(this.bL - 1);
                        this.bS = 0;
                    }
                    if (!this.bq) break;
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                if (this.bq) {
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                this.bP = this.k(this.bP);
                return;
            }
            case 8: {
                if (this.bM) {
                    if (this.bN) {
                        this.bP = this.l(this.bP);
                    } else if (this.bL < this.bK - 1) {
                        this.bL = (byte)(this.bL + 1);
                        this.bS = 0;
                    }
                    if (!this.bq) break;
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                if (this.bq) {
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                this.bP = this.l(this.bP);
                return;
            }
            case 16: {
                if (this.bM) {
                    if (!this.bq && this.bS + this.bL * 8 == this.b.r[this.b.au]) {
                        this.bq = true;
                        return;
                    }
                    if (this.bq) {
                        if (this.bs == 0) {
                            this.b.u[this.b.au] = 0;
                            byte by = 0;
                            while (by < this.b.r[this.b.au]) {
                                this.b.J[this.b.A[this.b.au][by]] = this.a(this.bT[by]);
                                byte by2 = this.b.au;
                                this.b.u[by2] = (short)(this.b.u[by2] + this.b.J[this.b.A[this.b.au][by]]);
                                by = (byte)(by + 1);
                            }
                            this.b.v[this.b.au] = this.bR;
                        }
                        this.b.m(0);
                        return;
                    }
                    if (!this.bN) {
                        this.bN = true;
                        this.bO = false;
                        this.bU = this.a(this.bT[this.bS + this.bL * 8]);
                        return;
                    }
                    this.bP = 0;
                    this.bN = false;
                    this.bO = true;
                    return;
                }
                if (!this.bq) {
                    this.bq = true;
                    return;
                }
                if (this.bs == 0) {
                    short s = this.a(this.bQ);
                    this.b.b(s, this.b.au);
                    this.bP = 0;
                    this.bM = true;
                    this.bq = false;
                    byte by = 0;
                    while (by < this.b.r[this.b.au]) {
                        this.bT[by] = this.a(this.b.J[this.b.A[this.b.au][by]]);
                        by = (byte)(by + 1);
                    }
                    this.bR = this.b.v[this.b.au];
                    return;
                }
                this.b.m(1);
                return;
            }
            case 32: {
                if (this.bM) {
                    this.b.m(0);
                    return;
                }
                this.b.m(1);
            }
        }
    }

    private final void Y() {
        switch (this.a) {
            case 1: {
                if (this.bq || this.bG || this.bm <= 0) break;
                this.bm = (byte)(this.bm - 1);
                return;
            }
            case 2: {
                byte by = 0;
                by = this.bK == 1 ? this.bJ : (this.bL < this.bK - 1 ? (byte)7 : (byte)((byte)(this.bJ % 8)));
                if (this.bq || this.bG || this.bm >= by) break;
                this.bm = (byte)(this.bm + 1);
                return;
            }
            case 4: {
                if (this.bG) {
                    this.bt = (byte)(this.bt ^ 1);
                    return;
                }
                if (this.bq) {
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                if (this.bL <= 0) break;
                this.bL = (byte)(this.bL - 1);
                this.bm = 0;
                return;
            }
            case 8: {
                if (this.bG) {
                    this.bt = (byte)(this.bt ^ 1);
                    return;
                }
                if (this.bq) {
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                if (this.bL >= this.bK - 1) break;
                this.bL = (byte)(this.bL + 1);
                this.bm = 0;
                return;
            }
            case 16: {
                if (this.bG) {
                    if (this.bt == 0) {
                        this.b.j(0);
                        return;
                    }
                    this.b.j(1);
                    return;
                }
                if (this.b.aH) {
                    this.ac();
                    return;
                }
                this.ab();
                return;
            }
            case 32: {
                if (!this.b.aI) {
                    return;
                }
                if (this.bG) {
                    this.b.j(1);
                    return;
                }
                if (this.b.aH) {
                    this.b.i(1);
                    return;
                }
                this.b.k(1);
            }
        }
    }

    private final void Z() {
        if (this.bp) {
            this.bp = false;
            return;
        }
        switch (this.a) {
            case 4: {
                if (!this.br) {
                    if (this.bn > 0) {
                        this.bn = (byte)(this.bn - 1);
                        return;
                    }
                    this.bn = (byte)5;
                    return;
                }
                if (this.bo > 0) {
                    this.bo = (byte)(this.bo - 1);
                    return;
                }
                this.bo = (byte)3;
                return;
            }
            case 8: {
                if (!this.br) {
                    if (this.bn < 5) {
                        this.bn = (byte)(this.bn + 1);
                        return;
                    }
                    this.bn = 0;
                    return;
                }
                if (this.bo < 3) {
                    this.bo = (byte)(this.bo + 1);
                    return;
                }
                this.bo = 0;
                return;
            }
            case 16: {
                if (!this.br) {
                    this.br = true;
                    this.bo = 0;
                    return;
                }
                if (this.br && this.bn == 0 && this.bo == 0) {
                    this.b.Q((byte)1);
                    return;
                }
                if (this.br && this.bn == 0 && this.bo == 1) {
                    this.b.af((byte)3);
                    return;
                }
                if (this.br && this.bn == 0 && this.bo == 2) {
                    this.b.P((byte)2);
                    return;
                }
                if (this.br && this.bn == 0 && this.bo == 3) {
                    this.b.R((byte)12);
                    return;
                }
                if (this.br && this.bn == 1 && this.bo == 0) {
                    this.b.P((byte)6);
                    return;
                }
                if (this.br && this.bn == 1 && this.bo == 1) {
                    this.b.T((byte)7);
                    return;
                }
                if (this.br && this.bn == 1 && this.bo == 2) {
                    this.b.S((byte)8);
                    return;
                }
                if (this.br && this.bn == 1 && this.bo == 3) {
                    this.b.U((byte)9);
                    return;
                }
                if (this.br && this.bn == 2 && this.bo == 0) {
                    this.b.V((byte)10);
                    return;
                }
                if (this.br && this.bn == 2 && this.bo == 1) {
                    this.b.W((byte)11);
                    return;
                }
                if (this.br && this.bn == 2 && this.bo == 2) {
                    this.b.X((byte)13);
                    return;
                }
                if (this.br && this.bn == 2 && this.bo == 3) {
                    this.b.Y((byte)14);
                    return;
                }
                if (this.br && this.bn == 3 && this.bo == 0) {
                    this.b.Z((byte)5);
                    return;
                }
                if (this.br && this.bn == 3 && this.bo == 1) {
                    this.b.P((byte)20);
                    return;
                }
                if (this.br && this.bn == 3 && this.bo == 2) {
                    this.b.aa((byte)21);
                    return;
                }
                if (this.br && this.bn == 3 && this.bo == 3) {
                    this.b.P((byte)4);
                    return;
                }
                if (this.br && this.bn == 4 && this.bo == 0) {
                    this.b.ab((byte)15);
                    return;
                }
                if (this.br && this.bn == 4 && this.bo == 1) {
                    this.b.ac((byte)16);
                    return;
                }
                if (this.br && this.bn == 4 && this.bo == 2) {
                    this.b.ad((byte)18);
                    return;
                }
                if (this.br && this.bn == 4 && this.bo == 3) {
                    this.b.ae((byte)19);
                    return;
                }
                if (this.br && this.bn == 5 && this.bo == 0) {
                    this.b.P((byte)22);
                    return;
                }
                if (this.br && this.bn == 5 && this.bo == 1) {
                    this.b.P((byte)23);
                    return;
                }
                if (this.br && this.bn == 5 && this.bo == 2) {
                    this.a((byte)24);
                    return;
                }
                if (!this.br || this.bn != 5 || this.bo != 3) break;
                this.o.a();
                return;
            }
            case 32: {
                if (this.br) {
                    this.br = false;
                    return;
                }
                this.b.P(this.b.bP);
            }
        }
    }

    public final void k() {
        if (this.bp) {
            this.bp = false;
            this.b.aq = 1;
            return;
        }
        block0 : switch (this.a) {
            case 1: {
                if (this.bw <= 0) break;
                this.bw = (byte)(this.bw - 1);
                break;
            }
            case 2: {
                if (this.bw >= 29) break;
                this.bw = (byte)(this.bw + 1);
                break;
            }
            case 4: {
                if (this.bv <= 0) break;
                this.bv = (byte)(this.bv - 1);
                break;
            }
            case 8: {
                if (this.bv >= 28) break;
                this.bv = (byte)(this.bv + 1);
                break;
            }
            case 16: {
                switch (this.b.bP) {
                    case 0: {
                        if (this.bE[this.bw][this.bv] <= 0 || this.b.b[this.b.ar] != this.b.i[this.bE[this.bw][this.bv]]) break block0;
                        byte by = this.bE[this.bw][this.bv];
                        this.b.a(by, 0);
                        return;
                    }
                    case 1: 
                    case 12: {
                        if (this.bE[this.bw][this.bv] <= 0) break block0;
                        byte by = this.bE[this.bw][this.bv];
                        if (this.b.au != by && this.b.i[this.b.au] == this.b.i[by] && this.b.g(this.b.au, by)) {
                            this.b.a(by, 0);
                        }
                        return;
                    }
                    case 3: {
                        if (this.bE[this.bw][this.bv] <= 0) break block0;
                        byte by = this.bE[this.bw][this.bv];
                        if (this.b.i[this.b.au] != this.b.i[by] && this.b.g(this.b.au, by) && !this.b.M(by)) {
                            this.b.a(by, 0);
                        }
                        return;
                    }
                    case 4: {
                        if (this.bE[this.bw][this.bv] <= 0) break block0;
                        byte by = this.bE[this.bw][this.bv];
                        this.b.a(by, 0);
                        return;
                    }
                    case 20: 
                    case 21: {
                        if (this.bE[this.bw][this.bv] <= 0) break block0;
                        byte by = this.bE[this.bw][this.bv];
                        this.a("objCity:" + by);
                        if (this.b.i[by] > 0 && this.b.i[this.b.au] != this.b.i[by]) {
                            this.b.a(by, 0);
                        }
                        return;
                    }
                    case 111: {
                        if (this.bE[this.bw][this.bv] <= 0) break block0;
                        byte by = this.bE[this.bw][this.bv];
                        if (this.b.i[by] == 0) {
                            this.b.ap(this.bE[this.bw][this.bv]);
                            this.b.bP = 0;
                            this.b.L((byte)8);
                            return;
                        }
                        this.bp = true;
                        this.bx = "\u8bf7\u9009\u62e9\u7a7a\u57ce!";
                        break block0;
                    }
                    case 22: {
                        byte by;
                        if (this.bE[this.bw][this.bv] <= 0 || this.b.i[by = this.bE[this.bw][this.bv]] != this.b.b[this.b.ar]) break block0;
                        this.b.au = by;
                        this.b.aq = 1;
                        return;
                    }
                }
                break;
            }
            case 32: {
                if (this.b.bP == 111) {
                    this.bB = 0;
                    this.a((byte)1);
                    break;
                }
                this.b.a((byte)1, 1);
            }
        }
        this.aa();
    }

    public final void l() {
        if (this.bp) {
            this.bp = false;
            return;
        }
        if (this.aZ == 4) {
            this.k();
            return;
        }
        if (this.aZ == 25) {
            this.s();
            return;
        }
        if (this.aZ == 7) {
            this.af();
            return;
        }
        if (this.aZ == 26) {
            this.aj();
            return;
        }
        if (this.aZ == 27) {
            this.ai();
            return;
        }
        block0 : switch (this.a) {
            case 1: {
                if (this.bq) {
                    return;
                }
                if (this.bB == 0 && this.cE) {
                    if (this.cD <= 0) break;
                    --this.cD;
                    return;
                }
                if (this.bB == 1) {
                    if (!this.cc || this.bq || this.bm <= 0) break;
                    this.bm = (byte)(this.bm - 1);
                    return;
                }
                if (this.bB != 2) break;
                this.bm = (byte)(this.bm - 1);
                if (this.bm < 1) {
                    this.bm = (byte)8;
                }
                this.bb[3] = null;
                this.bb[3] = this.b(this.b.F[this.b.b[this.bm]]);
                return;
            }
            case 2: {
                if (this.bq) {
                    return;
                }
                if (this.bB == 0 && this.cE) {
                    if (this.cD >= 1) break;
                    ++this.cD;
                    return;
                }
                if (this.bB == 1) {
                    if (!this.cc || this.bq) break;
                    this.b.getClass();
                    if (this.bm >= 2) break;
                    this.bm = (byte)(this.bm + 1);
                    return;
                }
                if (this.bB != 2) break;
                this.bm = (byte)(this.bm + 1);
                if (this.bm > 8) {
                    this.bm = 1;
                }
                this.bb[3] = null;
                this.bb[3] = this.b(this.b.F[this.b.b[this.bm]]);
                return;
            }
            case 4: {
                if (this.aW) {
                    this.bt = (byte)(this.bt ^ 1);
                    return;
                }
                if (this.bB == 0 && !this.cE) {
                    this.bm = (byte)(this.bm - 1);
                    if (this.bm >= 0) break;
                    this.bm = (byte)(this.bC - 1);
                    return;
                }
                if (this.bB != 2 && !this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (this.aW) {
                    this.bt = (byte)(this.bt ^ 1);
                    return;
                }
                if (this.bB == 0 && !this.cE) {
                    this.bm = (byte)(this.bm + 1);
                    if (this.bm <= this.bC - 1) break;
                    this.bm = 0;
                    return;
                }
                if (this.bB != 2 && !this.bq) break;
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                switch (this.bB) {
                    case 0: {
                        if (this.cE) {
                            if (this.cD == 0) {
                                if (this.ae() == 0) {
                                    this.bp = true;
                                    this.bx = "\u81ea\u5efa\u6b66\u5c06\u6570\u91cf\u5df2\u6ee1!";
                                } else {
                                    this.a((byte)27);
                                }
                            } else if (this.b.bR()) {
                                this.b.bP = (byte)113;
                                this.a((byte)7);
                            } else {
                                this.bp = true;
                                this.bx = "\u5c1a\u65e0\u81ea\u5efa\u6b66\u5c06!";
                            }
                            this.cE = false;
                            this.cD = 0;
                            return;
                        }
                        switch (this.bm) {
                            case 0: {
                                this.bB = (byte)2;
                                this.bm = 1;
                                this.g = "\u8bf7\u9009\u62e9\u626e\u6f14\u541b\u4e3b";
                                this.bb[2] = this.k(110);
                                this.bb[3] = this.b(this.b.F[this.b.b[this.bm]]);
                                return;
                            }
                            case 1: {
                                this.bB = 1;
                                this.cc = true;
                                this.b.r();
                                return;
                            }
                            case 2: {
                                this.cE = true;
                                if (this.bd == null) {
                                    this.bd = this.k(20);
                                }
                                if (this.bc != null) break block0;
                                this.bc = this.k(109);
                                return;
                            }
                            case 3: {
                                this.a((byte)25);
                                return;
                            }
                            case 4: {
                                this.o.a();
                            }
                        }
                        return;
                    }
                    case 1: {
                        if (!this.cc) break block0;
                        if (this.aW) {
                            if (this.bt == 0) {
                                this.a((byte)26);
                            }
                            this.aW = false;
                            return;
                        }
                        if (this.bq) {
                            if (this.bs == 0) {
                                if (this.bm == 1) {
                                    this.b.da = this.aX[this.bm];
                                    this.b.d(this.bm);
                                    return;
                                }
                                if (this.bm != 0) break block0;
                                if (this.aU) {
                                    if (this.aX[0] != null) {
                                        this.b.da = this.aX[this.bm];
                                        this.b.d(this.bm);
                                        return;
                                    }
                                    if (!this.am()) break block0;
                                    this.b.d(this.bm);
                                    return;
                                }
                                this.a((byte)26);
                                return;
                            }
                            this.bB = 0;
                            this.b.d((byte)5);
                            return;
                        }
                        if (this.bm < 1 && !this.aU) {
                            this.aW = true;
                            this.bt = 0;
                            return;
                        }
                        if (this.aS[this.bm].compareTo("\u5c1a\u65e0\u5b58\u6863") == 0) break block0;
                        this.bq = true;
                        return;
                    }
                    case 2: {
                        if (this.bs == 0) {
                            if (this.bm == 8) {
                                this.a("result:" + this.b.bR());
                                if (this.b.bR()) {
                                    this.b.bP = (byte)112;
                                    this.a((byte)7);
                                    return;
                                }
                                this.bp = true;
                                this.bx = "\u6b66\u5c06\u5c1a\u672a\u521b\u5efa!";
                                return;
                            }
                            this.b.L(this.b.a[this.bm]);
                            return;
                        }
                        this.bm = 0;
                        this.bs = 0;
                        this.bB = 0;
                    }
                }
                return;
            }
            case 32: {
                if (this.cE) {
                    this.cE = false;
                    this.cD = 0;
                    return;
                }
                if (this.bB == 1) {
                    if (this.bq) {
                        this.bq = false;
                        this.bs = 0;
                        return;
                    }
                    if (!this.cc) break;
                    if (this.aW) {
                        this.aW = false;
                        return;
                    }
                    this.bm = 0;
                    this.bB = 0;
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
        byte[] byArray = null;
        int n2 = 0;
        String string = "/res/lib" + n + ".jg";
        try {
            InputStream inputStream = ((Object)((Object)this)).getClass().getResourceAsStream(string);
            n2 = inputStream.available();
            byArray = new byte[n2];
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
        if (s > 228) {
            s = this.b.cY[s - 229];
        }
        return this.c(s);
    }

    private final Image c(short s) {
        this.a("*****createAvatar2:" + s);
        Image image = null;
        String string = "/res/avatar/" + s + ".png";
        try {
            image = Image.createImage((String)string);
        }
        catch (Exception exception) {
            this.a("Load avatar2 error - " + s);
        }
        return image;
    }

    public final void a(String string) {
        if (this.ba) {
            System.out.println(string);
        }
    }

    private final void S(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.bh, 0, 0, 0);
        graphics.drawImage(this.bb[2], 73, 24, 20);
        graphics.setColor(12359020);
        graphics.fillRect(11, 18 * this.bm + 8, 62, this.e);
        byte by = 1;
        while (by < 9) {
            if (by == 8) {
                graphics.setColor(0);
                graphics.drawString("\u81ea\u5efa", 14, 8 + 18 * by, 20);
                graphics.drawString("0", 58, 9 + 18 * by, 20);
                this.a(graphics, by, 47, 12 + 18 * by);
            } else {
                graphics.setColor(0);
                graphics.drawString(this.b.G[this.b.b[by]], 14, 8 + 18 * by, 20);
                graphics.drawString(String.valueOf(this.b.c[by]), 58, 9 + 18 * by, 20);
                this.a(graphics, by, 47, 12 + 18 * by);
            }
            by = (byte)(by + 1);
        }
        graphics.drawImage(this.bb[3], 80, 31, 20);
        graphics.setColor(0xFFFFFF);
        if (this.bm < 8) {
            graphics.setColor(0xFFFFFF);
            by = this.b.F[this.b.b[this.bm]];
            graphics.drawString(this.b.G[by], 125, 38, 20);
            graphics.setColor(16275794);
            this.aM = "LV. " + this.b.H[by];
            graphics.drawString(this.aM, 130, 60, 20);
            graphics.drawString(String.valueOf(this.b.M[by]), 98, 85, 20);
            graphics.drawString(String.valueOf(this.b.N[by]), 141, 85, 20);
            graphics.drawString(String.valueOf(this.b.L[by]), 98, 102, 20);
            graphics.drawString(String.valueOf(this.b.O[by]), 141, 102, 20);
            graphics.drawString(String.valueOf(this.b.J[by]), 98, 121, 20);
            graphics.drawString(String.valueOf(this.b.Q[by] / 100), 141, 121, 20);
            graphics.drawString(this.b.ad[this.b.R[by]], 98, 140, 20);
            graphics.drawString(this.b.ad[this.b.S[by]], 138, 140, 20);
        } else {
            graphics.setColor(0xFFFFFF);
            graphics.drawString("\u81ea\u5efa", 125, 38, 20);
        }
        graphics.drawImage(this.bc, 70 + this.bs * 47, 178, 0);
        if (this.be == null) {
            this.be = this.k(14);
        }
        graphics.drawRegion(this.be, 0, 0, 29, 17, 0, 78, 172, 20);
        graphics.drawRegion(this.be, 29, 0, 29, 17, 0, 125, 172, 20);
        this.b(graphics, "\u541b\u4e3b\u9009\u62e9");
        this.d(graphics);
    }

    private final void a(Graphics graphics, byte by, int n, int n2) {
        graphics.setColor(0xFCFCFC);
        graphics.fillRect(n + 2, n2, 4, 2);
        graphics.fillRect(n, n2 + 2, 8, 5);
        graphics.setColor(this.c[by]);
        graphics.fillRect(n + 3, n2 + 1, 1, 2);
        graphics.fillRect(n + 1, n2 + 3, 6, 3);
        graphics.setColor(0xFFFFFF);
    }

    private final void T(Graphics graphics) {
        graphics.drawRegion(this.bb[3], 0, 0, 148, 21, 0, this.bk >> 1, this.bl - 19, 33);
        graphics.drawRegion(this.bb[3], 0, (this.bn + 1) * 21, 148, 21, 0, this.bk >> 1, this.bl - 19, 33);
        graphics.setColor(0xFFFF00);
        graphics.drawRect(21 + 36 * this.bo, this.bl - 37, 26, 14);
    }

    private final void U(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.setColor(16275794);
        this.W(graphics);
        graphics.drawString(String.valueOf(this.b.aG), 133, 45, 17);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.b.h[this.b.au], 75, 45, 17);
        graphics.drawString(this.b.G[this.b.b[this.b.as]], 74, 66, 17);
        graphics.drawString(this.b.G[this.b.j[this.b.au]], 74, 81, 17);
        graphics.drawString(String.valueOf(this.b.k[this.b.au]), 74, 99, 17);
        int n = this.b.p[this.b.au] / 1000;
        int n2 = this.b.u[this.b.au] / 100;
        graphics.drawString(String.valueOf(n), 61, 116, 17);
        graphics.drawString(String.valueOf(this.b.r[this.b.au]), 96, 116, 17);
        graphics.drawString(String.valueOf(n2), 133, 116, 17);
        graphics.drawString(String.valueOf(this.b.n[this.b.au]), 61, 131, 17);
        graphics.drawString(String.valueOf(this.b.o[this.b.au]), 96, 131, 17);
        graphics.drawString(String.valueOf(this.b.q[this.b.au]), 133, 131, 17);
        graphics.drawString(String.valueOf(this.b.l[this.b.au]), 66, 146, 17);
        graphics.drawString(String.valueOf(this.b.m[this.b.au]), 123, 146, 17);
    }

    private final void V(Graphics graphics) {
        if (this.b.as != this.b.ar) {
            this.g = String.valueOf(this.b.G[this.b.b[this.b.as]]) + " \u6218\u7565\u4e2d.....";
        }
        graphics.drawImage(this.bb[0], 0, 0, 20);
        if (this.b.bP == 4) {
            this.b(graphics, d.b[3][3]);
        } else if (this.b.bP == 111 || this.b.bP == 112) {
            this.b(graphics, "\u9009\u57ce\u5efa\u90fd");
        } else {
            this.W(graphics);
        }
        int n = 0;
        while (n < 32) {
            int n2 = 0;
            while (n2 < 29) {
                if (this.bE[n][n2] > 0) {
                    if (this.b.i[this.bE[n][n2]] > 0) {
                        int n3 = 1;
                        while (n3 < 9) {
                            if (this.b.i[this.bE[n][n2]] == this.b.b[n3]) {
                                this.a(graphics, this.b.a[n3], n2 * 6, n * 6);
                            }
                            n3 = (byte)(n3 + 1);
                        }
                    } else {
                        this.a(graphics, (byte)0, n2 * 6, n * 6);
                    }
                }
                n2 = (byte)(n2 + 1);
            }
            n = (byte)(n + 1);
        }
        this.d(graphics);
        if (this.b.as == this.b.ar || this.b.bP == 112) {
            graphics.drawImage(this.bb[1], this.bv * 6 + 3, this.bw * 6 + 3, 20);
        }
    }

    private final void aa() {
        this.a("cmd:" + this.b.bP);
        String string = null;
        if (this.bE[this.bw][this.bv] > 0) {
            string = "\u57ce\u6c60: " + this.b.h[this.bE[this.bw][this.bv]];
            if (this.b.i[this.bE[this.bw][this.bv]] > 0) {
                string = String.valueOf(string) + " \u541b\u4e3b:" + this.b.G[this.b.i[this.bE[this.bw][this.bv]]];
            }
            if (this.b.M(this.b.g[this.bE[this.bw][this.bv]])) {
                string = String.valueOf(string) + "(\u76df)";
            }
        } else {
            string = this.b.bP == 1 || this.b.bP == 20 || this.b.bP == 12 || this.b.bP == 21 ? "\u8bf7\u9009\u62e9\u76ee\u7684\u57ce\u6c60" : (this.b.bP == 22 ? d.u : (this.b.bP == 3 ? "\u6b32\u653b\u6253\u4f55\u57ce?" : (this.b.bP == 4 ? "\u8bf7\u9009\u62e9\u4fa6\u67e5\u7684\u57ce\u6c60" : (this.b.bP == 111 ? d.z : "\u5bf9\u4f55\u5dde\u90e1\u4e0b\u4ee4"))));
        }
        this.g = string;
    }

    private final void W(Graphics graphics) {
        this.b(graphics, String.valueOf(String.valueOf(this.b.aD)) + "\u5e74" + String.valueOf(this.b.aC % 13) + "\u6708");
    }

    private final void a(Graphics graphics, String string) {
        if (this.bd == null) {
            this.bd = this.k(20);
        }
        graphics.drawImage(this.bd, 88, 50, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, string, 20, 65, 130, 95);
    }

    private final void a(Graphics graphics, String string, byte by) {
        if (this.bd == null) {
            this.bd = this.k(20);
        }
        if (this.be == null) {
            this.be = this.k(14);
        }
        graphics.drawImage(this.bd, 88, 50, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, string, 20, 65, 130, 95);
        graphics.drawImage(this.bc, 35 + 50 * this.bt, 105, 0);
        graphics.drawRegion(this.be, 0, 0, 29, 17, 0, 46, 100, 20);
        graphics.drawRegion(this.be, 29, 0, 29, 17, 0, 96, 100, 20);
    }

    private final void b(Graphics graphics, String string) {
        graphics.setColor(16275794);
        graphics.drawString(string, this.bk >> 1, 1, 17);
    }

    public final void m() {
        if (this.b.bP != 4 || this.bv == 0) {
            int n = 0;
            while (n < 32) {
                int n2 = 0;
                while (n2 < 29) {
                    if (this.bE[n][n2] == this.b.au) {
                        this.bv = (byte)n2;
                        this.bw = (byte)n;
                        break;
                    }
                    n2 = (byte)(n2 + 1);
                }
                n = (byte)(n + 1);
            }
        }
    }

    private final void ab() {
        int n = this.bm + this.bL * 8;
        if (!this.bq && n < this.bJ) {
            if (!this.b.V[this.bI[n]]) {
                this.b.V[this.bI[n]] = true;
                this.bF = (byte)(this.bF + 1);
                return;
            }
            this.b.V[this.bI[n]] = false;
            this.bF = (byte)(this.bF - 1);
            return;
        }
        if (this.bq) {
            this.b.k((int)this.bs);
            return;
        }
        if (n == this.bJ && this.bF > 0) {
            this.bq = true;
        }
    }

    private final void ac() {
        int n = this.bm + this.bL * 8;
        if (!this.bq && n < this.bJ) {
            if (this.bH > 0) {
                this.b.V[this.bH] = false;
                if (this.bI[n] != this.bH) {
                    this.b.V[this.bI[n]] = true;
                    this.bH = this.bI[n];
                    return;
                }
                this.bH = 0;
                return;
            }
            this.bH = this.bI[n];
            this.b.V[this.bH] = true;
            return;
        }
        if (this.bq) {
            this.b.i((int)this.bs);
            return;
        }
        if (n == this.bJ && this.bH > 0) {
            if (!this.b.aI) {
                this.b.i((int)this.bs);
                return;
            }
            this.bq = true;
        }
    }

    public final void n() {
        this.bG = true;
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
        graphics.drawString(this.g, this.bk >> 1, this.bl - 2, 33);
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
        if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 1) {
            byte by = 0;
            while (by < this.b.bi) {
                if (this.b.bq[by] == (byte)(this.B + this.t) && this.b.br[by] == (byte)(this.C + this.u) && (this.b.bu[by] == 0 || this.b.bu[by] > 3)) {
                    this.g = "\u6b66\u5c06: " + this.b.G[this.b.bg[by]];
                    return;
                }
                by = (byte)(by + 1);
            }
            return;
        }
        if (this.b.f(this.b.bm[this.C + this.u][this.B + this.t]) == 2) {
            this.g = "\u654c\u5c06!";
            return;
        }
        this.g = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
    }

    private final void a(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        graphics.setClip(n, n2, n3, n4);
        this.cp = n3 / this.f;
        this.cq = n4 / this.e;
        this.cr = (string.length() - 1) / this.cp + 1;
        int n5 = this.cs + this.cq;
        if (n5 > this.cr) {
            n5 = this.cr;
        }
        int n6 = this.cs;
        while (n6 < n5) {
            int n7 = n6 * this.cp;
            int n8 = n7 + this.cp;
            if (n8 > string.length()) {
                n8 = string.length();
            }
            graphics.drawString(string.substring(n7, n8), this.bk >> 1, n2 + this.e * ((n6 - this.cs) % this.cq), 17);
            ++n6;
        }
        graphics.setClip(0, 0, this.bk, this.bl);
        if (this.cr > this.cq) {
            if (this.bc == null) {
                this.bc = this.k(109);
            }
            if (this.cs > 0) {
                graphics.drawRegion(this.bc, 0, 0, 6, 8, 6, this.bk >> 1, 20, 17);
            }
            if (n5 < this.cr) {
                graphics.drawRegion(this.bc, 0, 0, 6, 8, 5, this.bk >> 1, this.bl - 20, 33);
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
        if (this.bh == null) {
            this.bh = this.k(302);
        }
        graphics.drawImage(this.bh, 0, 0, 0);
        this.d(graphics);
        this.b(graphics, "\u5173\u4e8e\u6e38\u620f");
        graphics.setColor(0);
        this.a(graphics, "\u300a\u9738\u738b\u7684\u5927\u9646Mobile\u300b\u91c7\u7528\u4e86\u6307\u4ee4\u724c\u6e38\u620f\u7cfb\u7edf\uff0c\u541b\u4e3b\u62e5\u6709\u57ce\u6570\u8d8a\u591a\uff0c\u6bcf\u6708\u53ef\u4ee5\u53d1\u5e03\u6307\u4ee4\u7684\u4ee4\u724c\u8d8a\u591a\u3002\u5728\u53ea\u6709\u4e00\u5ea7\u57ce\u7684\u60c5\u51b5\u4e0b\u6bcf\u6708\u53ea\u53ef\u4ee5\u505a\u4e09\u4ef6\u4e8b\u3002\u641c\u7d22\u6b66\u5c06\u4e3b\u8981\u53d6\u51b3\u4e8e\u541b\u4e3b\u7684\u5fb7\u884c\uff0c\u5fb7\u884c\u503c\u8d8a\u9ad8\u8d8a\u5bb9\u6613\u8ba9\u6b66\u5c06\u52a0\u5165\u65d7\u4e0b\u3002\u6e38\u620f\u4e2d\u8fd8\u9996\u521b\u4e86\u4ee5\u4eba\u671b\u503c\u51b3\u5b9a\u884c\u52a8\u529b\u7684\u6982\u5ff5\u3002\u5916\u4ea4\u7b56\u7565\u5171\u6709\u4e09\u9879\uff0c\u5206\u522b\u4e3a\u540c\u76df\u3001\u79bb\u95f4\u548c\u62db\u63fd\u3002\u540c\u76df\u662f\u4e0e\u5176\u5b83\u56fd\u5bb6\u7b7e\u5b9a\u4e92\u4e0d\u4fb5\u72af\u6761\u7ea6\uff0c\u79bb\u95f4\u7528\u6765\u964d\u4f4e\u654c\u65b9\u6b66\u5c06\u7684\u5fe0\u8bda\u5ea6\uff0c\u62db\u63fd\u5373\u5c06\u5bf9\u65b9\u6b66\u5c06\u62c9\u81f3\u9ebe\u4e0b\u3002\u6e38\u620f\u6700\u5927\u7684\u4eae\u70b9\u8fd8\u662f\u5728\u4e8e\u5176\u6218\u4e89\u7cfb\u7edf\uff0c\u9635\u578b\u7684\u8bbe\u5b9a\u548c\u91cd\u5927\u4f5c\u7528\u8ba9\u6218\u4e89\u8fc7\u7a0b\u5145\u6ee1\u7b56\u7565\u6027\u3002    \u6e38\u620f\u952e\u76d8\u64cd\u4f5c\u8bbe\u7f6e:\u5bfc\u822a\u65b9\u5411\u952e\u6216\u6570\u5b57\u952e\u76d82,8,4,6\u5206\u522b\u4e3a'\u4e0a\u4e0b\u5de6\u53f3\u7684\u65b9\u5411\u9009\u62e9',\u5bfc\u822a\u7684\u786e\u8ba4\u952e\u6216\u6570\u5b57\u952e5\u4e3a'\u786e\u8ba4',\u624b\u673a\u53f3\u8f6f\u5efa\u6216\u6570\u5b57\u952e7\u4e3a'\u53d6\u6d88'.\u66f4\u591a\u6e38\u620f\u4fe1\u606f,\u8bf7\u8bbf\u95ee\u5b98\u65b9\u7f51\u7ad9http://www.justgame.com.cn", 15, 35, this.bk - 30, this.bl - 60);
    }

    public static final void a(Exception exception) {
        exception.toString();
    }

    private final void m(byte by) {
        if (this.aP[this.cz][by - 2] < 99 && this.aP[this.cz][4] > 0) {
            byte[] byArray = this.aP[this.cz];
            int n = by - 2;
            byArray[n] = (byte)(byArray[n] + 1);
            byte[] byArray2 = this.aP[this.cz];
            byArray2[4] = (byte)(byArray2[4] - 1);
        }
    }

    private final void n(byte by) {
        if (this.aP[this.cz][by - 2] > this.aR[this.cz][by - 2]) {
            byte[] byArray = this.aP[this.cz];
            int n = by - 2;
            byArray[n] = (byte)(byArray[n] - 1);
            byte[] byArray2 = this.aP[this.cz];
            byArray2[4] = (byte)(byArray2[4] + 1);
        }
    }

    private final byte[] ad() {
        Random random = new Random();
        byte[] byArray = new byte[4];
        int n = 0;
        while (n < 4) {
            byArray[n] = (byte)(Math.abs(random.nextInt()) % 1000 / 10 + 1);
            if (byArray[n] > 99) {
                byArray[n] = 99;
            }
            n = (byte)(n + 1);
        }
        return byArray;
    }

    public final void commandAction(Command command, Displayable displayable) {
        if (command == this.aH) {
            if (displayable == this.aE) {
                this.cv = this.aK.getString();
            } else if (displayable == this.aF) {
                this.cw = this.aL.getString();
            } else if (displayable == this.aG) {
                this.cC = this.aJ.getString().trim();
                short s = 1;
                while (s < this.ae()) {
                    if (this.cC.compareTo(this.b.G[s]) == 0) {
                        this.bp = true;
                        this.bx = "\u6b66\u5c06\u540d\u91cd\u590d!";
                        this.cC = "";
                        break;
                    }
                    s = (short)(s + 1);
                }
            }
        }
        Display.getDisplay((MIDlet)this.o).setCurrent((Displayable)this);
    }

    private final short ae() {
        short s = 229;
        while (s < 232) {
            if (this.b.F[s] == 0) {
                return s;
            }
            s = (short)(s + 1);
        }
        return 0;
    }

    private final void af() {
        switch (this.a) {
            case 1: {
                if (this.bq || this.bm <= 0) break;
                this.bm = (byte)(this.bm - 1);
                return;
            }
            case 2: {
                byte by = 0;
                by = this.bK == 1 ? this.bJ : (this.bL < this.bK - 1 ? (byte)7 : (byte)(this.bJ % 8));
                if (this.bq || this.bm >= by) break;
                this.bm = (byte)(this.bm + 1);
                return;
            }
            case 4: {
                if (this.bq) {
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                if (this.bL <= 0) break;
                this.bL = (byte)(this.bL - 1);
                this.bm = 0;
                return;
            }
            case 8: {
                if (this.bq) {
                    this.bs = (byte)(this.bs ^ 1);
                    return;
                }
                if (this.bL >= this.bK - 1) break;
                this.bL = (byte)(this.bL + 1);
                this.bm = 0;
                return;
            }
            case 16: {
                if (this.b.aH) {
                    this.ah();
                    return;
                }
                this.ag();
                return;
            }
            case 32: {
                this.bB = 0;
                this.a((byte)1);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void ag() {
        int n = this.bm + this.bL * 8;
        if (!this.bq && n < this.bJ) {
            if (!this.b.V[this.bI[n]]) {
                this.b.V[this.bI[n]] = true;
                this.bF = (byte)(this.bF + 1);
                return;
            }
            this.b.V[this.bI[n]] = false;
            this.bF = (byte)(this.bF - 1);
            return;
        }
        if (this.bq) {
            if (this.bs == 0) {
                if (this.b.bP == 112) {
                    this.b.bS();
                    this.b.bP = (byte)111;
                    this.a((byte)7);
                    return;
                }
                if (this.b.bP != 113) return;
                this.b.bU();
                this.b.bV();
                this.bB = 0;
                this.bp = true;
                this.bx = "\u5220\u9664\u6210\u529f!";
                this.a((byte)1);
                return;
            }
            this.bB = 0;
            this.a((byte)1);
            return;
        }
        if (n != this.bJ || this.bF <= 0) return;
        this.bq = true;
    }

    private final void ah() {
        int n = this.bm + this.bL * 8;
        if (!this.bq && n < this.bJ) {
            if (this.bH > 0) {
                this.b.V[this.bH] = false;
                if (this.bI[n] != this.bH) {
                    this.b.V[this.bI[n]] = true;
                    this.bH = this.bI[n];
                    return;
                }
                this.bH = 0;
                return;
            }
            this.bH = this.bI[n];
            this.b.V[this.bH] = true;
            return;
        }
        if (this.bq) {
            if (this.bs == 0) {
                this.b.bT();
                this.bv = (byte)16;
                this.bw = (byte)16;
                this.b.bP = (byte)111;
                this.a((byte)4);
                return;
            }
            this.bB = 0;
            this.a((byte)1);
            return;
        }
        if (n == this.bJ && this.bH > 0) {
            if (!this.b.aI) {
                this.b.i((int)this.bs);
                return;
            }
            this.bq = true;
        }
    }

    private final void ai() {
        if (this.bp) {
            this.bp = false;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.cB) {
                    if (this.cz == 0) {
                        this.aN[0] = this.aN[0] > 0 ? (int)(this.aN[0] - 1) : 4;
                        this.bb[1] = null;
                        this.bb[1] = this.c(this.aQ[this.aN[0]]);
                        return;
                    }
                    if (this.cz != 1) break;
                    this.aN[1] = this.aN[1] > 5 ? (int)(this.aN[1] - 1) : 9;
                    this.bb[1] = null;
                    this.bb[1] = this.c(this.aQ[this.aN[1]]);
                    return;
                }
                if (this.cA) {
                    this.m(this.bm);
                    return;
                }
                if (this.cy <= 0) break;
                this.cy = (byte)(this.cy - 1);
                return;
            }
            case 2: {
                if (this.cB) {
                    if (this.cz == 0) {
                        this.aN[0] = this.aN[0] < 4 ? (byte)(this.aN[0] + 1) : (byte)0;
                        this.bb[1] = null;
                        this.bb[1] = this.c(this.aQ[this.aN[0]]);
                        return;
                    }
                    if (this.cz != 1) break;
                    this.aN[1] = this.aN[1] < 9 ? (int)(this.aN[1] + 1) : 5;
                    this.bb[1] = null;
                    this.bb[1] = this.c(this.aQ[this.aN[1]]);
                    return;
                }
                if (this.cA) {
                    this.n(this.bm);
                    return;
                }
                if (this.cy >= 2) break;
                this.cy = (byte)(this.cy + 1);
                return;
            }
            case 4: {
                if (this.cA || this.cB) {
                    return;
                }
                if (this.cy == 0) {
                    if (this.cz <= 0) break;
                    this.cz = (byte)(this.cz - 1);
                    if (this.cz == 0) {
                        this.bb[1] = null;
                        this.bb[1] = this.c(this.aQ[0]);
                        return;
                    }
                    if (this.cz != 1) break;
                    this.bb[1] = null;
                    this.bb[1] = this.c(this.aQ[5]);
                    return;
                }
                if (this.cy == 1) {
                    this.bm = this.bm > 0 ? (byte)(this.bm - 1) : (byte)5;
                    if (this.bm >= 2) break;
                    this.cA = false;
                    return;
                }
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 8: {
                if (this.cA || this.cB) {
                    return;
                }
                if (this.cy == 0) {
                    if (this.cz < 2) {
                        this.cz = (byte)(this.cz + 1);
                        if (this.cz == 0) {
                            this.bb[1] = null;
                            this.bb[1] = this.c(this.aQ[0]);
                        } else if (this.cz == 1) {
                            this.bb[1] = null;
                            this.bb[1] = this.c(this.aQ[5]);
                        }
                    }
                    if (this.cz != 2) break;
                    byte[] byArray = this.ad();
                    this.aN[2] = (byte)(byArray[0] % 10);
                    this.bb[1] = null;
                    this.bb[1] = this.c(this.aQ[this.aN[2]]);
                    int n = 0;
                    while (n < 4) {
                        this.aP[2][n] = byArray[n];
                        n = (byte)(n + 1);
                    }
                    return;
                }
                if (this.cy == 1) {
                    if (this.bm < 5) {
                        this.bm = (byte)(this.bm + 1);
                        return;
                    }
                    this.bm = 0;
                    return;
                }
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 32: {
                this.a((byte)1);
                this.bm = (byte)2;
                return;
            }
            case 16: {
                if (this.cy == 1) {
                    if (this.cB) {
                        this.cB = false;
                        return;
                    }
                    if (this.bm > 1 && this.cz != 2) {
                        this.cA = !this.cA;
                        return;
                    }
                    if (this.bm == 0) {
                        this.cB = true;
                        return;
                    }
                    if (this.bm != 1) break;
                    if (this.aG == null) {
                        this.aG = new Form("\u6b66\u5c06\u540d");
                        this.aG.append((Item)this.aJ);
                        this.aG.addCommand(this.aH);
                        this.aG.addCommand(this.aI);
                        this.aG.setCommandListener((CommandListener)this);
                    }
                    Display.getDisplay((MIDlet)this.o).setCurrent((Displayable)this.aG);
                    return;
                }
                if (this.cy != 2) break;
                if (this.bs == 0) {
                    if (this.cC.compareTo("") == 0) {
                        this.bp = true;
                        this.bx = "\u6b66\u5c06\u540d\u4e0d\u80fd\u4e3a\u7a7a!";
                        return;
                    }
                    this.a("pre create OK!");
                    try {
                        Random random = new Random();
                        short s = this.ae();
                        this.a("newID:" + s);
                        this.b.F[s] = s;
                        this.b.G[s] = this.cC;
                        this.b.H[s] = 1;
                        this.b.I[s] = (byte)(Math.abs(random.nextInt()) % 3 + 1);
                        this.b.J[s] = 500;
                        this.b.K[s] = this.aP[this.cz][2];
                        this.b.L[s] = this.aP[this.cz][2];
                        this.b.M[s] = this.aP[this.cz][0];
                        this.b.N[s] = this.aP[this.cz][1];
                        this.b.O[s] = this.aP[this.cz][3];
                        this.b.P[s] = 99;
                        this.b.Q[s] = 0;
                        this.b.R[s] = 0;
                        this.b.S[s] = 24;
                        this.b.V[s] = false;
                        this.b.cY[s - 229] = this.aQ[this.aN[this.cz]];
                    }
                    catch (Exception exception) {
                        Exception exception2 = exception;
                        exception.printStackTrace();
                    }
                    this.b.bV();
                    this.bp = true;
                    this.bx = "\u521b\u5efa\u6210\u529f!";
                    this.a((byte)1);
                    this.bm = (byte)2;
                    this.a("create OK!");
                    return;
                }
                this.a((byte)1);
                this.bm = (byte)2;
                return;
            }
            default: {
                return;
            }
        }
    }

    private final void Y(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 0);
        graphics.drawImage(this.bb[0], this.bk >> 1, 25, 17);
        this.b(graphics, "\u81ea\u5efa\u6b66\u5c06");
        this.g = "\u8bbe\u7f6e\u81ea\u5efa\u6b66\u5c06\u53c2\u6570";
        this.d(graphics);
        if (this.cy == 0) {
            graphics.setColor(0xFFFF33);
        } else {
            graphics.setColor(0xFFFFFF);
        }
        if (this.cz < 2) {
            graphics.drawRect(31 + this.cz * 45, 50, 28, 16);
        } else {
            graphics.drawRect(116, 50, 28, 16);
        }
        graphics.drawImage(this.bb[1], 28, 84, 0);
        graphics.setColor(0xFFFF33);
        graphics.drawString(String.valueOf(this.aP[this.cz][4]), 102, 152, 17);
        graphics.setColor(16275794);
        int n = 0;
        while (n < 4) {
            graphics.drawString(String.valueOf(this.aP[this.cz][n]), this.aO[(n + 2) * 4] + 12, this.aO[(n + 2) * 4 + 1] + 2, 17);
            n = (byte)(n + 1);
        }
        graphics.drawString(this.cC, 124, 85, 17);
        if (this.cy == 1) {
            graphics.setColor(0xFFFF33);
            if (this.cB) {
                graphics.setColor(16275794);
            }
            graphics.drawRect((int)this.aO[this.bm * 4], (int)this.aO[this.bm * 4 + 1], (int)this.aO[this.bm * 4 + 2], (int)this.aO[this.bm * 4 + 3]);
            if (this.cA) {
                graphics.setColor(16275794);
                graphics.drawRect(88, 151, 28, 13);
            }
        }
        if (this.cy == 2) {
            this.a(graphics, this.bs);
        }
    }

    private final void Z(Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, 0);
        this.b(graphics, "\u7528\u6237\u767b\u9646");
        this.d(graphics);
        graphics.drawString("\u7528\u6237:", 20, 50, 0);
        graphics.drawString(this.cv, 65, 50, 0);
        graphics.drawString("\u53e3\u4ee4:", 20, 80, 0);
        if (this.cw.compareTo("") != 0) {
            graphics.drawString("******", 65, 80, 0);
        }
        graphics.drawString("\u8bb0\u4f4f\u767b\u9646\u4fe1\u606f", this.bk >> 1, 110, 17);
        graphics.drawRect(30, 112, 8, 8);
        if (this.aV) {
            graphics.setColor(0xFFFF00);
            graphics.fillRect(32, 114, 5, 5);
        }
        byte by = 0;
        while (by < 3) {
            if (this.aD == by) {
                graphics.setColor(0xFFFF00);
            } else {
                graphics.setColor(0);
            }
            if (by == 2) {
                graphics.drawRect(30, 112, 8, 8);
            } else {
                graphics.drawRect(55, 48 + 30 * by, 80, 20);
            }
            by = (byte)(by + 1);
        }
        graphics.setColor(0xFFFF00);
        this.a(graphics, "[\u5e10\u6237\u514d\u8d39\u6ce8\u518c\u5730\u5740]", 5, 110 + this.e, 176, this.e * 2);
        graphics.drawString(this.bz, this.bk >> 1, 110 + this.e * 2, 17);
        if (this.aD == 3) {
            this.a(graphics, this.bs);
        }
        if (this.cx == 1) {
            this.a(graphics, "\u8bf7\u8f93\u5165\u7528\u6237\u540d!");
        } else if (this.cx == 2) {
            this.a(graphics, "\u8bf7\u8f93\u5165\u53e3\u4ee4!");
        }
        if (this.cJ) {
            this.ab(graphics);
        }
    }

    private final void aj() {
        switch (this.a) {
            case 1: {
                if (this.cJ) {
                    this.cK = (byte)(this.cK ^ 1);
                    return;
                }
                if (this.aD <= 0) break;
                this.aD = (byte)(this.aD - 1);
                return;
            }
            case 2: {
                if (this.cJ) {
                    this.cK = (byte)(this.cK ^ 1);
                    return;
                }
                if (this.aD >= 3) break;
                this.aD = (byte)(this.aD + 1);
                return;
            }
            case 4: 
            case 8: {
                this.bs = (byte)(this.bs ^ 1);
                return;
            }
            case 16: {
                if (this.cx > 0) {
                    this.aD = (byte)(this.cx - 1);
                    this.cx = 0;
                    return;
                }
                if (this.aD == 0) {
                    if (this.aE == null) {
                        this.aE = new Form("\u8bf7\u8f93\u5165\u7528\u6237\u540d!");
                        this.aE.append((Item)this.aK);
                        this.aE.addCommand(this.aH);
                        this.aE.addCommand(this.aI);
                        this.aE.setCommandListener((CommandListener)this);
                    }
                    Display.getDisplay((MIDlet)this.o).setCurrent((Displayable)this.aE);
                    return;
                }
                if (this.aD == 1) {
                    if (this.aF == null) {
                        this.aF = new Form("\u8bf7\u8f93\u5165\u53e3\u4ee4!");
                        this.aF.append((Item)this.aL);
                        this.aF.addCommand(this.aH);
                        this.aF.addCommand(this.aI);
                        this.aF.setCommandListener((CommandListener)this);
                    }
                    Display.getDisplay((MIDlet)this.o).setCurrent((Displayable)this.aF);
                    return;
                }
                if (this.aD == 2) {
                    this.aV = !this.aV;
                    return;
                }
                if (this.bs == 0) {
                    if (this.cv.compareTo("") == 0) {
                        this.cx = 1;
                        return;
                    }
                    if (this.cw.compareTo("") == 0) {
                        this.cx = (byte)2;
                        return;
                    }
                    if (this.cJ) {
                        this.cJ = false;
                        a.g = this.cK;
                        this.ak();
                        return;
                    }
                    if (this.cJ) break;
                    this.cJ = true;
                    return;
                }
                if (this.b.bP == 22 || this.b.bP == 23) {
                    this.b.q(5);
                    return;
                }
                this.bB = 0;
                this.a((byte)1);
                return;
            }
            case 32: {
                if (this.b.bP == 22 || this.b.bP == 23) {
                    this.b.q(5);
                    return;
                }
                this.bB = 0;
                this.a((byte)1);
                return;
            }
            default: {
                return;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void ak() {
        Object object;
        String string = "POST";
        String string2 = "http://www.justgame.com.cn/ri/grl.php";
        String string3 = String.valueOf(this.cv) + "," + this.cw + "," + this.cG;
        this.cF = true;
        this.aY.a(string, string2, string3);
        while (this.aY.m != 0) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                object = interruptedException;
                interruptedException.printStackTrace();
            }
        }
        this.cF = false;
        if (this.cI) {
            this.cI = false;
            return;
        }
        if (this.cH < 10) {
            object = this.aY.b();
            if (((String)object).compareTo("0") == 0) {
                this.bp = true;
                this.bx = "\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef!";
                return;
            }
            if (((String)object).compareTo("1") == 0 || ((String)object).compareTo("2") == 0) {
                this.bp = true;
                this.bx = "\u975e\u6cd5\u8bf7\u6c42!\u9519\u8bef\u4ee3\u7801:" + (String)object;
                return;
            }
            if (((String)object).charAt(0) == '3') {
                this.a("result:" + (String)object);
                this.aU = true;
                this.c((String)object);
                this.a("change screen");
                this.a((byte)18);
                if (this.aV) {
                    if (this.b.dc == this.cv) return;
                    this.b.dc = this.cv;
                    this.b.dd = this.cw;
                    this.b.cs();
                    return;
                }
                this.b.dc = "";
                this.b.dd = "";
                this.b.cs();
                return;
            }
            this.bp = true;
            this.bx = "\u670d\u52a1\u5668\u8fde\u63a5\u5931\u8d25!";
            return;
        }
        this.cH = 0;
    }

    private final boolean al() {
        String string = "POSTBYTES";
        int n = this.b.aJ((byte)0);
        this.aX[0] = new byte[n + 1];
        System.arraycopy(this.b.cZ, 0, this.aX[0], 0, n + 1);
        String string2 = this.b.db;
        this.cF = true;
        this.repaint();
        byte by = (byte)(n / 1024 + 1);
        this.a("time:" + by);
        int n2 = n % 1024 + 1;
        this.a("remainLength:" + n2);
        int n3 = Math.abs(new Random().nextInt() % 10);
        byte by2 = 0;
        while (by2 < by) {
            Object object;
            String string3;
            if (by2 == 0) {
                string3 = "http://www.justgame.com.cn/ri/pr_part.php?req=" + n3 + "&part=0&un=" + this.cv + "&pd=" + this.cw + "&index=" + this.aT + "&ua=" + this.cG + "&title=" + string2;
                this.aY.a(string, string3, this.aX[0], 1024 * by2, 1024);
            } else if (by2 == by - 1) {
                string3 = "http://www.justgame.com.cn/ri/pr_part.php?req=" + n3 + "&part=2&un=" + this.cv + "&pd=" + this.cw + "&index=" + this.aT + "&ua=" + this.cG + "&title=" + string2;
                this.aY.a(string, string3, this.aX[0], 1024 * by2, n2);
            } else {
                string3 = "http://www.justgame.com.cn/ri/pr_part.php?req=" + n3 + "&part=1&un=" + this.cv + "&pd=" + this.cw + "&index=" + this.aT + "&ua=" + this.cG + "&title=" + string2;
                this.aY.a(string, string3, this.aX[0], 1024 * by2, 1024);
            }
            while (this.aY.m != 0) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException interruptedException) {
                    object = interruptedException;
                    interruptedException.printStackTrace();
                }
            }
            if (this.cI) {
                this.cI = false;
                this.cF = false;
                return false;
            }
            if (this.cH < 10) {
                object = new String(this.aY.l);
                this.a("result:" + (String)object);
                if (((String)object).compareTo("0") == 0) {
                    this.bp = true;
                    this.bx = "\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef!";
                    this.cF = false;
                    return false;
                }
                if (((String)object).compareTo("1") == 0) {
                    this.bp = true;
                    this.bx = "\u975e\u6cd5\u8bf7\u6c42!\u9519\u8bef\u4ee3\u7801:" + (String)object;
                    this.cF = false;
                    return false;
                }
                if (((String)object).compareTo("succ") == 0 && by2 == by - 1) {
                    this.aS[this.aT] = this.b(string2);
                    this.cF = false;
                    return true;
                }
            } else {
                this.cH = 0;
            }
            by2 = (byte)(by2 + 1);
        }
        this.cF = false;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean am() {
        Object object;
        String string = "POST";
        String string2 = "http://www.justgame.com.cn/ri/gr.php";
        String string3 = String.valueOf(this.cv) + "," + this.cw + "," + this.aT + "," + this.cG;
        this.cF = true;
        this.repaint();
        this.aY.a(string, string2, string3);
        while (this.aY.m != 0) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                object = interruptedException;
                interruptedException.printStackTrace();
            }
        }
        this.cF = false;
        if (this.cI) {
            this.cI = false;
            return false;
        }
        if (this.cH < 10) {
            if (this.aY.l.length < 1024) {
                object = new String(this.aY.l);
                if (((String)object).compareTo("0") == 0) {
                    this.bp = true;
                    this.bx = "\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef!";
                    return false;
                }
                if (((String)object).compareTo("1") != 0 && ((String)object).compareTo("2") != 0 && ((String)object).compareTo("3") != 0) return false;
                this.bp = true;
                this.bx = "\u975e\u6cd5\u8bf7\u6c42!\u9519\u8bef\u4ee3\u7801:" + (String)object;
                return false;
            }
            this.b.da = this.aY.l;
            this.aX[0] = null;
            this.aX[0] = new byte[this.b.da.length];
            System.arraycopy(this.b.da, 0, this.aX[0], 0, this.b.da.length);
            return true;
        }
        this.cH = 0;
        return false;
    }

    private final void c(String string) {
        String string2 = string.substring(string.indexOf(",") + 1);
        String string3 = string2.substring(0, string2.indexOf(","));
        if (string3.compareTo("0") == 0) {
            this.aS[0] = "\u5c1a\u65e0\u5b58\u6863";
            return;
        }
        this.aS[0] = this.b(string3);
    }

    public final String b(String string) {
        short[] sArray = new short[3];
        int n = 0;
        while (n < 3) {
            sArray[n] = Short.parseShort(string.substring(0, string.indexOf("_")));
            string = string.substring(string.indexOf("_") + 1);
            n = (byte)(n + 1);
        }
        if (sArray[0] > 228) {
            return "\u81ea\u5efa" + sArray[1] + "\u5e74" + sArray[2] + "\u6708";
        }
        return String.valueOf(this.b.G[sArray[0]]) + sArray[1] + "\u5e74" + sArray[2] + "\u6708";
    }

    public final void d(byte by) {
        this.cF = false;
        this.bp = true;
        this.cH = by;
        switch (by) {
            case 10: {
                this.bx = "\u7f51\u7edc\u6253\u5f00\u5931\u8d25!\u8bf7\u68c0\u67e5\u7f51\u7edc\u8bbe\u7f6e";
                return;
            }
            case 11: {
                this.bx = "\u6570\u636e\u4f20\u8f93\u5931\u8d25!";
                return;
            }
            case 12: {
                this.bx = "\u7f51\u7edc\u5173\u95ed\u5931\u8d25!";
                return;
            }
            case 13: {
                this.bx = "\u670d\u52a1\u5668\u8fde\u63a5\u5931\u8d25!";
            }
        }
    }

    private final void aa(Graphics graphics) {
        this.a(graphics, "\u8bbf\u95ee\u7f51\u7edc\u4e2d...");
    }

    private final void ab(Graphics graphics) {
        graphics.drawImage(this.bd, this.bk >> 1, 50, 17);
        graphics.setColor(0xFFFFFF);
        graphics.drawString("GPRS\u76f4\u8fde\u63a5", this.bk >> 1, 65, 17);
        graphics.drawString("\u79fb\u52a8\u68a6\u7f51\u4ee3\u7406", this.bk >> 1, 65 + this.e, 17);
        graphics.drawImage(this.bc, 22, 70 + this.cK * this.e, 20);
    }
}
