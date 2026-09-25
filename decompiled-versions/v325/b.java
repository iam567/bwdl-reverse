/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Form
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.lcdui.Item
 *  javax.microedition.lcdui.TextField
 *  javax.microedition.lcdui.game.GameCanvas
 *  javax.microedition.media.Manager
 *  javax.microedition.media.MediaException
 *  javax.microedition.media.Player
 *  javax.microedition.media.control.VolumeControl
 *  javax.microedition.midlet.MIDlet
 */
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
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;
import sango.Sango;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class b
extends GameCanvas
implements CommandListener {
    private byte E;
    private boolean C;
    private Image[] a;
    private Image a;
    private Image b;
    private Image c;
    private Image d;
    private Image e;
    private Image f;
    private Image g;
    private Image h;
    public byte a;
    public a a;
    private int f;
    private int g;
    private byte F;
    private byte G;
    private byte H;
    private boolean D;
    private boolean E = false;
    private boolean F = false;
    private byte I;
    private byte J;
    private byte K;
    private byte L;
    private byte M;
    public int[] a;
    public String a;
    private String d;
    private String e;
    private String f;
    private String g;
    public Font a;
    public int a;
    public int b;
    public Player a;
    public VolumeControl a;
    public byte b;
    public byte c;
    public boolean a;
    public byte[] a;
    private byte N;
    private byte O;
    private byte P;
    private int h;
    private byte[][] c;
    private byte Q;
    private boolean G = false;
    private short i;
    private short[] c;
    private byte R;
    public boolean b;
    private byte S;
    private byte T;
    private boolean H = false;
    private boolean I = false;
    private boolean J = true;
    private byte U;
    private byte[] c;
    private short j;
    private byte V;
    private byte[][] d;
    private short k;
    private boolean K = false;
    private short[] d;
    private short[] e;
    private short l;
    private boolean L = false;
    private boolean M = false;
    private int i;
    private boolean N = false;
    public Sango a;
    public byte d;
    public boolean c;
    public boolean d;
    public byte e;
    public byte f;
    public byte g;
    public byte h;
    public byte i;
    public byte j;
    public byte k;
    public byte l;
    public byte m;
    public short a;
    public short b;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i = false;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public byte n;
    public byte o;
    public byte p;
    public boolean n;
    public boolean o;
    public byte q;
    public boolean p = false;
    public boolean q = false;
    public boolean r;
    public String b;
    public byte r = 0;
    private short m;
    private short n;
    private boolean O = 0;
    public short c;
    public short d;
    public boolean s;
    private boolean P = false;
    public byte s = 0;
    public short e;
    public short f = 0;
    public short g = 0;
    public short h;
    public byte t;
    private byte W;
    public boolean t = false;
    public int c;
    public int d = false;
    public boolean u;
    public boolean v;
    public boolean w;
    private byte X;
    private boolean Q = false;
    private boolean R = false;
    private byte Y = 0;
    public byte u = 0;
    public byte v = 1;
    public byte w = 0;
    public byte x;
    public byte y;
    public boolean x = true;
    public byte z;
    public boolean y = 0;
    public byte A;
    public byte B;
    public byte C = 0;
    public boolean z = 0;
    public String c;
    public byte D = false;
    public boolean A = false;
    public int e = 210;
    private boolean S = false;
    private boolean T = false;
    private byte Z;
    private int j = false;
    private int k = false;
    private int l = false;
    private int m = false;
    private int n = 1;
    private boolean U = false;
    public boolean B = false;
    public Form a;
    public Form b;
    public Form c;
    public Command a;
    public Command b;
    public TextField a;
    public TextField b;
    public TextField c = "\u8bf7\u9009\u62e9\u6267\u884c\u547d\u4ee4";
    public byte[] b;
    public short[] a;
    public byte[][] a = new byte[]{0, 1, 0};
    public short[] b;
    private byte aa = 0;
    private byte ab = 0;
    private boolean V = false;
    private boolean W = false;
    public byte[][] b = false;
    private String h = "";
    private int o = 0;
    private boolean X = false;

    public b(Sango sango) {
        super(false);
        this.P = (byte)5;
        this.c = new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 0, 0, 0, 0, 0, 0}, {0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 13, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 35, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        this.c = new short[10];
        this.c = new byte[]{0, 0, 0, 0};
        this.d = new byte[10][4];
        this.d = new short[]{0, 0, 0};
        this.e = new short[]{0, 0, 0};
        this.a = (short)3;
        this.b = (short)3;
        this.o = (byte)16;
        this.D = (byte)-1;
        this.a = new Command("\u786e\u5b9a", 4, 1);
        this.b = new Command("\u8fd4\u56de", 7, 1);
        this.a = new TextField("\u6b66\u5c06\u540d:", "", 4, 0);
        this.b = new TextField("\u7528\u6237\u540d:", "", 30, 0);
        this.c = new TextField("\u5bc6\u7801:", "", 6, 65536);
        this.b = new byte[]{0, 5, 0};
        this.a = new short[]{34, 126, 55, 68, 144, 123, 66, 21, 124, 148, 29, 22, 180, 148, 29, 22, 124, 176, 29, 22, 180, 176, 29, 22};
        this.a = new byte[][]{{20, 80, 60, 60, 70}, {80, 20, 80, 50, 50}, {0, 0, 0, 0, 0}};
        this.b = new short[]{234, 235, 236, 237, 238, 229, 230, 231, 232, 233};
        this.b = new byte[][]{{20, 80, 60, 60, 70}, {80, 20, 80, 50, 50}, {0, 0, 0, 0, 0}};
        this.setFullScreenMode(true);
        this.f = 240;
        this.g = 320;
        this.a = sango;
    }

    private final void c(int n) {
        for (int i = 0; i < 10; ++i) {
            this.a[i] = null;
        }
        System.gc();
    }

    private final void p() {
        this.F = 0;
        this.E = false;
        this.I = 0;
        this.J = 0;
    }

    public final synchronized void a(byte by) {
        short s;
        this.a("curScreenID:" + this.E);
        switch (this.E) {
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
                this.L = false;
                this.G = false;
                break;
            }
            case 6: {
                this.c(4);
                this.F = false;
                this.G = 0;
                this.H = 0;
            }
            case 7: {
                this.c(1);
                this.Q = 0;
                this.G = false;
                this.i = 0;
                this.b = false;
                break;
            }
            case 8: {
                this.c(3);
                for (s = 0; s < 4; s = (short)((byte)(s + 1))) {
                    this.c[s] = 0;
                }
                break;
            }
            case 9: {
                for (s = 0; s < 3; s = (short)((byte)(s + 1))) {
                    this.d[s] = 0;
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
                for (s = 0; s < 4; s = (short)((byte)(s + 1))) {
                    this.c[s] = 0;
                }
                this.l = 0;
                break;
            }
            case 13: {
                this.c(3);
                break;
            }
            case 14: {
                for (s = 0; s < 4; s = (short)((byte)(s + 1))) {
                    this.c[s] = 0;
                }
                this.c(5);
                this.m = 0;
                this.n = 0;
                break;
            }
            case 15: {
                this.c(1);
                break;
            }
            case 16: {
                this.c(5);
                for (s = 0; s < 4; s = (short)((byte)(s + 1))) {
                    this.c[s] = 0;
                }
                this.A = false;
                break;
            }
            case 17: {
                this.c(2);
                this.K = 0;
                this.A = false;
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
                this.P = false;
                break;
            }
            case 22: {
                this.c(3);
                this.u = 0;
                this.v = 1;
                this.w = 0;
                this.x = 0;
                this.y = 0;
                this.x = true;
                this.z = 0;
                this.y = false;
                this.A = 0;
                this.B = 0;
                this.C = 0;
                this.z = false;
                this.D = (byte)-1;
                this.D = false;
                break;
            }
            case 27: {
                this.a.setString("");
                this.c(2);
            }
        }
        this.E = by;
        this.a("nextScreenID:" + this.E);
        switch (this.E) {
            case 0: {
                this.a[0] = this.a(300);
                break;
            }
            case 1: {
                this.a[0] = this.a(106);
                this.a[1] = this.a(107);
                if (this.a == null) {
                    this.a = this.a(109);
                }
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (!this.a) break;
                if (this.b == 0) {
                    this.b(1);
                    break;
                }
                if (this.b == 1) break;
                this.o();
                this.b(1);
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                this.a[0] = this.a(1);
                this.a[1] = this.a(2);
                this.X();
                if (!this.a) break;
                if (this.b == 0) {
                    this.b(1);
                    break;
                }
                if (this.b == 2) break;
                this.o();
                this.b(2);
                break;
            }
            case 5: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.e == null) {
                    this.e = this.a(303);
                }
                this.a[3] = this.a(10);
                if (this.a.b[this.a.h] > 0) {
                    this.a[0] = this.a(15);
                    this.a[1] = this.a(this.a.b[this.a.h][0]);
                    this.a[2] = this.a[1];
                    if (this.c != null) break;
                    this.c = this.a(14);
                    break;
                }
                this.a[1] = this.a((short)0);
                this.G = true;
                this.a = "\u57ce\u5e02\u57fa\u672c\u4fe1\u606f";
                this.I = 1;
                break;
            }
            case 6: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.e == null) {
                    this.e = this.a(303);
                }
                this.a[0] = this.a(this.a.c[this.a.g]);
                this.a[1] = this.a(10);
                this.a[2] = this.a(12);
                this.a[3] = this.a(307);
                this.a[4] = this.a(15);
                if (!this.a) break;
                if (this.b == 0) {
                    this.b(1);
                    break;
                }
                if (this.b == 2) break;
                this.o();
                this.b(2);
                break;
            }
            case 7: {
                this.a[0] = this.a(13);
                if (this.a.c) {
                    this.c = this.a.b[this.a.i];
                    this.R = this.a.g[this.a.i];
                } else if (this.a.P == 3 && this.a.Q == 5) {
                    this.b = true;
                    this.c = this.a.t;
                    this.R = this.a.j;
                } else {
                    this.c = this.a.u;
                    this.R = this.a.o;
                }
                this.S = (byte)((this.R - 1) / 10 + 1);
                this.T = 0;
                break;
            }
            case 8: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.d == null) {
                    this.d = this.a(90);
                }
                this.a[0] = this.a(89);
                this.a[1] = this.a(16);
                this.a[2] = this.a(96);
                this.H = false;
                this.I = false;
                this.J = true;
                this.U = 0;
                this.j = 0;
                this.V = 0;
                this.k = 0;
                for (s = 0; s < 4; s = (short)((byte)(s + 1))) {
                    this.c[s] = 0;
                }
                this.a = "\u5f81100\u5175 \u9700 20\u91d1";
                this.S = (byte)((this.a.g[this.a.g] - 1) / 10 + 1);
                this.T = 0;
                break;
            }
            case 9: {
                this.a[1] = this.a(16);
                this.a[2] = this.a(309);
                if (this.d == null) {
                    this.d = this.a(90);
                }
                if (this.f == null) {
                    this.f = this.a(302);
                }
                this.a = "\u57ce\u5185\u91d1\u5b50:" + this.a.d[this.a.g] + "\u7cae\u98df: " + this.a.e[this.a.g] + "\u5b9d:" + this.a.j[this.a.g];
                this.e[0] = this.a.d[this.a.g] + this.a.d[this.a.h] > 9999 ? (short)(9999 - this.a.d[this.a.h]) : this.a.d[this.a.g];
                this.e[1] = this.a.e[this.a.g] + this.a.e[this.a.h] > 9999 ? (short)(9999 - this.a.e[this.a.h]) : this.a.e[this.a.g];
                this.e[2] = this.a.j[this.a.g] + this.a.j[this.a.h] > 99 ? (short)(99 - this.a.j[this.a.h]) : (short)this.a.j[this.a.g];
                break;
            }
            case 10: {
                if (this.e == null) {
                    this.e = this.a(303);
                }
                this.a[0] = this.a(this.a.d);
                this.a = "\u6267\u884c\u7ed3\u679c";
                break;
            }
            case 11: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.e == null) {
                    this.e = this.a(303);
                }
                this.a[0] = this.a(this.a.a);
                this.a = "\u6267\u884c\u7ed3\u679c";
                break;
            }
            case 12: {
                this.l = 0;
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.d == null) {
                    this.d = this.a(90);
                }
                this.a[1] = this.a(16);
                this.a[4] = this.a(this.a.a);
                this.a[5] = this.a(309);
                this.a = "\u541b\u4e3b\u7292\u8d4f\u5c06\u58eb";
                break;
            }
            case 13: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.d == null) {
                    this.d = this.a(90);
                }
                this.a[1] = this.a(this.a.a);
                this.a = "\u5c06\u6d88\u8017\u91d1\u5b50" + this.a.a;
                break;
            }
            case 14: {
                this.a[0] = this.a(16);
                this.a[1] = this.a(308);
                if (this.d == null) {
                    this.d = this.a(90);
                }
                this.a[4] = this.a(309);
                if (this.f == null) {
                    this.f = this.a(302);
                }
                this.e[0] = this.a.d[this.a.g];
                this.e[1] = this.a.e[this.a.g];
                this.a = "\u57ce\u5185\u91d1\u5b50:" + this.e[0] + "\u7cae\u98df: " + this.e[1];
                break;
            }
            case 15: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.d == null) {
                    this.d = this.a(90);
                }
                s = this.a.j[this.a.a[this.a.E[0]]];
                this.a[0] = this.a(s);
                this.a = "\u8bf7\u9009\u62e9\u52bf\u529b\u8fdb\u884c\u64cd\u4f5c";
                break;
            }
            case 16: {
                for (int n = 0; n < 4; n = (int)((byte)(n + 1))) {
                    this.c[n] = 0;
                }
                this.M = true;
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.d == null) {
                    this.d = this.a(90);
                }
                this.a[1] = this.a(16);
                this.a[2] = this.a(308);
                this.a[3] = this.a(309);
                if (this.A) {
                    this.e[0] = this.a.g * 4 / 3 + this.a.h > 9999 ? (short)((9999 - this.a.h) * 3 / 4) : this.a.g;
                    this.e[1] = this.a.h * 3 / 4 + this.a.g > 9999 ? (short)((9999 - this.a.g) * 4 / 3) : this.a.h;
                    break;
                }
                this.e[0] = this.a.d[this.a.g] * 4 / 3 + this.a.e[this.a.g] > 9999 ? (short)((9999 - this.a.e[this.a.g]) * 3 / 4) : this.a.d[this.a.g];
                this.e[1] = this.a.e[this.a.g] * 3 / 4 + this.a.d[this.a.g] > 9999 ? (short)((9999 - this.a.d[this.a.g]) * 4 / 3) : this.a.e[this.a.g];
                break;
            }
            case 17: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                this.a[0] = this.a(93);
                this.a[1] = this.a(16);
                this.a = "\u8bf7\u9009\u62e9\u8d2d\u4e70\u7684\u5175\u5668";
                break;
            }
            case 18: {
                if (this.d == null) {
                    this.d = this.a(90);
                }
                if (this.f == null) {
                    this.f = this.a(302);
                }
                if (this.a == null) {
                    this.a = this.a(109);
                }
                this.a = "\u8bf7\u9009\u62e9\u8bb0\u5f55";
                break;
            }
            case 19: {
                if (this.a.a < 14) {
                    this.a[1] = this.a(11);
                } else if (this.b == null) {
                    this.b = this.a(20);
                }
                this.d();
                break;
            }
            case 20: {
                this.q();
                this.a[0] = this.a(2);
                this.a[4] = this.a(31);
                this.a[5] = this.a(51);
                this.a[6] = this.a(52);
                if (!this.a) break;
                if (this.b == 0) {
                    this.b(1);
                    break;
                }
                if (this.b == 3) break;
                this.o();
                this.b(3);
                break;
            }
            case 21: {
                this.W = 0;
                this.Y = this.a(this.a.I);
                this.a[0] = this.a(98);
                if (this.Y == 3) {
                    this.a[8] = this.a(306);
                    this.P = true;
                }
                this.a[9] = this.a(56);
                this.a[4] = this.a(63);
                this.a[2] = this.a(58);
                this.a[1] = this.a(305);
                this.a[5] = this.a(this.c);
                this.a[6] = this.a(this.d);
                break;
            }
            case 22: {
                this.a[0] = this.a(59);
                this.a[1] = this.a(60);
                this.a[2] = this.a(304);
                this.a[3] = this.a(this.c);
                break;
            }
            case 23: {
                this.a.k();
                this.a[1] = this.a(11);
                if (this.a.a == 1) {
                    this.a[0] = this.a(132);
                    this.a = this.a.b[this.a.a[this.a.d]] + c.h[this.a.a];
                    this.S = true;
                    break;
                }
                this.a[0] = this.a(131);
                this.a = this.a.b[this.a.a[this.a.d]] + c.h[this.a.a];
                this.Z = this.a.ao;
                break;
            }
            case 24: {
                if (this.d == null) {
                    this.d = this.a(90);
                }
                if (this.f == null) {
                    this.f = this.a(302);
                }
                this.a = "\u7cfb\u7edf\u73af\u5883\u8bbe\u7f6e";
                if (this.a) {
                    this.a[0] = 0;
                    break;
                }
                this.a[0] = 1;
                break;
            }
            case 25: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                this.a = "\u4e0a\u4e0b\u952e\u6eda\u52a8\u5c4f\u5e55";
                break;
            }
            case 27: {
                if (this.f == null) {
                    this.f = this.a(302);
                }
                this.a[0] = this.a(310);
                this.a[1] = this.b(this.b[this.b[0]]);
                this.h = "";
                this.F = 0;
                this.ab = 0;
                this.aa = 0;
            }
        }
        this.a = 0;
        this.p();
    }

    private final byte a(byte by) {
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
        this.f = (byte)(this.a.I[0] - 3);
        if (this.f < 0) {
            this.f = 0;
            return;
        }
        if (this.f > 16) {
            this.f = (byte)16;
        }
    }

    public final void b() {
        this.a("call clearWarFlag()");
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.c = false;
        this.o = false;
        this.d = false;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.a = (short)3;
        this.b = (short)3;
        this.g = 0;
        this.h = 0;
        this.J = 0;
        this.n = 0;
        this.q = 0;
        this.p = false;
        this.q = false;
        this.r = false;
        this.r = 0;
        this.s = false;
        this.P = false;
        this.d = 0;
    }

    public final void c() {
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = true;
        this.l = false;
        this.m = false;
    }

    private final void q() {
        this.e = null;
        this.f = null;
        this.d = null;
        this.c(10);
    }

    private final void e(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        graphics.drawImage(this.a[1], 137, 97, 20);
        graphics.setColor(0);
        graphics.drawRect(137, 97, this.a[1].getWidth(), this.a[1].getHeight());
        graphics.setColor(0xFFFFFF);
        graphics.drawRegion(this.c, 58, 0, 29, 17, 0, 70, 100, 17);
        graphics.drawString(this.a.b[this.a.a], 113, 100, 17);
        switch (this.a.b) {
            case 0: {
                graphics.drawString("\u7cae\u98df: " + this.a.e[this.a.g], 60, 120, 20);
                graphics.drawString("\u519c\u4e1a: " + this.a.f[this.a.g], 60, 140, 20);
                break;
            }
            case 1: {
                graphics.drawString("\u91d1\u5b50: " + this.a.d[this.a.g], 60, 120, 20);
                graphics.drawString("\u5de5\u4e1a: " + this.a.g[this.a.g], 60, 140, 20);
                break;
            }
            case 2: {
                graphics.drawString("\u7edf\u6cbb: " + this.a.e[this.a.g], 60, 120, 20);
                graphics.drawString("\u9632\u707e: " + this.a.f[this.a.g], 60, 140, 20);
                break;
            }
            case 3: {
                graphics.drawString("\u7edf\u6cbb: " + this.a.e[this.a.g], 50, 120, 20);
                graphics.drawString("\u4eba\u53e3: " + this.a.a[this.a.g], 50, 140, 20);
            }
        }
        this.b(graphics, c.a[2][this.a.b]);
        this.d(graphics);
        this.a(graphics, this.I);
    }

    private final void f(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        graphics.drawImage(this.a[4], 70, 80, 17);
        graphics.setColor(0);
        graphics.drawRect(43, 80, this.a[4].getWidth(), this.a[4].getHeight());
        graphics.drawRegion(this.c, 58, 0, 29, 17, 0, 130, 90, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.a.b[this.a.a], 135, 120, 20);
        graphics.drawRegion(this.a[5], 0, 15 * this.F, 27, 15, 0, 60, 165, 20);
        graphics.drawRegion(this.a[5], 0, 75 - 15 * this.F, 27, 15, 0, 150, 165, 20);
        if (this.I) {
            for (byte by = 0; by < 4; by = (byte)(by + 1)) {
                if (this.U == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.c[by]), 130 - this.h * by, 164, 24);
            }
            graphics.drawImage(this.a[1], 123 - this.h * this.U, 162, 20);
        } else {
            graphics.drawString("" + this.l, 118, 164, 17);
        }
        this.b(graphics, c.a[1][2]);
        this.d(graphics);
        if (this.K) {
            this.a(graphics, "\u5956\u8d4f\u9ec4\u91d1\u4e0d\u80fd\u4e3a0!");
        }
        if (this.E) {
            this.a(graphics, this.I);
        }
    }

    private final void g(Graphics graphics) {
        int n = this.a + 2;
        if (!this.H) {
            graphics.drawImage(this.f, 0, 0, 20);
            graphics.drawImage(this.d, this.f >> 1, 60, 17);
            graphics.drawImage(this.a[0], this.f >> 1, 83, 17);
            this.d(graphics);
            this.b(graphics, c.a[0][2]);
            graphics.setColor(0xFFFFFF);
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawString(String.valueOf(this.a.d[this.a.g]), 115, 84, 20);
            short s = this.a.a(this.a.b[this.a.g], this.a.g[this.a.g]);
            graphics.drawString(String.valueOf(s), 115, 108, 20);
            graphics.drawString(String.valueOf(this.a.i[this.a.g]), 115, 133, 20);
            graphics.drawImage(this.a[1], 153 - this.h * this.U, 151, 20);
            graphics.setColor(0xFFFF00);
            for (int n2 = 0; n2 < 4; n2 = (int)((byte)(n2 + 1))) {
                graphics.drawString(String.valueOf(this.c[n2]), 160 - this.h * n2, 153, 24);
            }
            if (this.E) {
                this.a(graphics, this.I);
            }
        } else {
            byte by = 0;
            int n3 = 0;
            graphics.setColor(0);
            graphics.drawImage(this.f, 0, 0, 20);
            graphics.drawImage(this.a[2], 180, this.g - 4, 36);
            graphics.drawString(String.valueOf(this.j), this.f - 15, this.g - 2, 40);
            graphics.drawString("\u8bf7\u5206\u914d\u540e\u5907\u5175", 90, this.g - 5, 33);
            this.b(graphics, c.a[0][2]);
            graphics.drawImage(this.a, 35, 36 + n * this.V, 0);
            if (this.S == 1) {
                for (by = 0; by < this.a.g[this.a.g]; by = (byte)(by + 1)) {
                    if (by == this.V) {
                        if (this.I) {
                            graphics.drawImage(this.a[1], 188 - this.h * this.U, 28 + n * by, 20);
                        }
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.a.b[this.a.b[this.a.g][by]], 45, 30 + n * by, 20);
                    graphics.drawString(c.e[this.a.p[this.a.b[this.a.g][by]]], 110, 30 + n * by, 20);
                    for (int n4 = 0; n4 < 4; n4 = (int)((byte)(n4 + 1))) {
                        graphics.drawString(String.valueOf(this.d[by][n4]), 186 - this.h * n4, 30 + n * by, 20);
                    }
                }
                graphics.setColor(0);
                graphics.drawString("\u51b3\u5b9a", 45, 30 + n * this.a.g[this.a.g], 20);
            } else {
                if (this.T < this.S - 1) {
                    for (by = (byte)(this.T * 10); by < (this.T + 1) * 10; by = (byte)(by + 1)) {
                        n3 = by % 10;
                        if (by == this.V + this.T * 10) {
                            if (this.I) {
                                graphics.drawImage(this.a[1], 145 - this.h * this.U, 24 + n * n3, 20);
                            }
                            graphics.setColor(16275794);
                        } else {
                            graphics.setColor(0);
                        }
                        graphics.drawString(this.a.b[this.a.b[this.a.g][by]], 30, 30 + n * n3, 20);
                        graphics.drawString(c.e[this.a.p[this.a.b[this.a.g][by]]], 80, 30 + n * n3, 20);
                        for (int n5 = 0; n5 < 4; n5 = (int)((byte)(n5 + 1))) {
                            graphics.drawString(String.valueOf(this.d[by][n5]), 146 - this.h * n5, 30 + n * n3, 20);
                        }
                    }
                } else if (this.T == this.S - 1) {
                    for (by = (byte)(this.T * 10); by < this.a.g[this.a.g]; by = (byte)(by + 1)) {
                        n3 = by % 10;
                        if (by == this.V + this.T * 10) {
                            if (this.I) {
                                graphics.drawImage(this.a[1], 145 - this.h * this.U, 24 + n * n3, 20);
                            }
                            graphics.setColor(16275794);
                        } else {
                            graphics.setColor(0);
                        }
                        graphics.drawString(this.a.b[this.a.b[this.a.g][by]], 30, 30 + n * n3, 20);
                        graphics.drawString(c.e[this.a.p[this.a.b[this.a.g][by]]], 80, 30 + n * n3, 20);
                        for (int n6 = 0; n6 < 4; n6 = (int)((byte)(n6 + 1))) {
                            graphics.drawString(String.valueOf(this.d[by][n6]), 146 - this.h * n6, 30 + n * n3, 20);
                        }
                    }
                    graphics.setColor(0);
                    graphics.drawString("\u51b3\u5b9a", 30, 30 + n * (this.a.g[this.a.g] % 10), 20);
                }
                graphics.setColor(0xFFFFFF);
                graphics.drawString(this.T + 1 + "/" + this.S, this.f >> 1, this.g - 32, 17);
                graphics.drawImage(this.a, 140, this.g - 30, 20);
                graphics.drawRegion(this.a, 0, 0, 6, 8, 2, 100, this.g - 30, 24);
            }
        }
        if (this.E) {
            this.a(graphics, this.I);
        }
    }

    private final void h(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(0, 0, this.f, this.g);
        graphics.drawImage(this.a[0], this.f >> 1, this.g >> 1, 3);
        graphics.setColor(0);
        graphics.drawString(this.f, 0, this.g, 36);
        graphics.drawString(this.g, this.f, this.g, 40);
    }

    private final void i(Graphics graphics) {
        System.gc();
        graphics.drawImage(this.a[0], 0, 0, 0);
        graphics.drawString("\u8f7d\u5165\u4e2d...", this.f >> 1, 40, 17);
        graphics.setColor(0xFFFF00);
        graphics.fillRect(53 + this.n, 89, 5, 5);
    }

    private final void j(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.a[0], 0, 0, 20);
        this.l(graphics);
        this.k(graphics);
        if (this.E) {
            this.a(graphics, this.I);
        }
        if (this.G) {
            this.a(graphics, "\u662f\u5426\u653e\u5f03\u6b64\u57ce\u5e02\uff1f", (byte)0);
        }
    }

    private final void k(Graphics graphics) {
        this.a = this.b ? "\u8bf7\u4efb\u547d\u519b\u961f\u4e3b\u5c06" : (this.a.P == 3 && this.a.Q == 3 || this.a.P == 9 || this.a.P == 100 || this.a.P == 1 && this.a.Q == 2 ? "\u8bf7\u4efb\u547d\u65b0\u592a\u5b88" : (this.a.P == 7 ? c.c : (this.a.P == 20 ? "\u8bf7\u9009\u62e9\u79bb\u95f4\u7684\u6b66\u5c06" : (this.a.P == 21 ? "\u8bf7\u9009\u62e9\u8981\u62db\u63fd\u6b66\u5c06" : (this.a.P == 22 ? c.b : (this.a.P == 112 ? c.d : (this.a.P == 111 ? c.e : (this.a.P == 113 ? c.f : "\u9009\u62e9\u6267\u884c\u6b66\u5c06"))))))));
        this.d(graphics);
    }

    private final void a(Graphics graphics, byte by) {
        if (this.c == null) {
            this.c = this.a(14);
        }
        if (this.a == null) {
            this.a = this.a(109);
        }
        graphics.drawImage(this.a, 72 + 45 * by, 264, 0);
        graphics.drawRegion(this.c, 0, 0, 29, 17, 0, 80, 260, 20);
        graphics.drawRegion(this.c, 29, 0, 29, 17, 0, 125, 260, 20);
    }

    private final void l(Graphics graphics) {
        int n = this.a + 2;
        int n2 = 0;
        byte by = 0;
        if (this.S == 1) {
            for (by = 0; by < this.R; by = (byte)(by + 1)) {
                if (this.a.a[this.c[by]]) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(this.a.b[this.c[by]], 25, 22 + n * by, 20);
                graphics.drawString(String.valueOf(this.a.r[this.c[by]]), 94, 22 + n * by, 17);
                graphics.drawString(String.valueOf(this.a.s[this.c[by]]), 123, 22 + n * by, 17);
                graphics.drawString(String.valueOf(this.a.t[this.c[by]]), 152, 22 + n * by, 17);
                graphics.drawString(String.valueOf(this.a.u[this.c[by]]), 181, 22 + n * by, 17);
                if (this.a.v[this.c[by]] > 99) {
                    graphics.drawString("--", 210, 22 + n * by, 17);
                    continue;
                }
                graphics.drawString(String.valueOf(this.a.v[this.c[by]]), 210, 22 + n * by, 17);
            }
            graphics.setColor(0xFFFFFF);
            graphics.drawString("\u51b3\u5b9a", 25, n * (by + 1), 20);
        } else {
            if (this.T < this.S - 1) {
                for (by = (byte)(this.T * 10); by < (this.T + 1) * 10; by = (byte)(by + 1)) {
                    if (this.a.a[this.c[by]]) {
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0xFFFFFF);
                    }
                    n2 = by % 10;
                    graphics.drawString(this.a.b[this.c[by]], 25, 22 + n * n2, 20);
                    graphics.drawString(String.valueOf(this.a.r[this.c[by]]), 94, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.a.s[this.c[by]]), 123, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.a.t[this.c[by]]), 152, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.a.u[this.c[by]]), 181, 22 + n * n2, 17);
                    if (this.a.v[this.c[by]] > 99) {
                        graphics.drawString("--", 210, 22 + n * n2, 17);
                        continue;
                    }
                    graphics.drawString(String.valueOf(this.a.v[this.c[by]]), 210, 22 + n * n2, 17);
                }
            } else if (this.T == this.S - 1) {
                for (by = (byte)(this.T * 10); by < this.R; by = (byte)(by + 1)) {
                    if (this.a.a[this.c[by]]) {
                        graphics.setColor(16275794);
                    } else {
                        graphics.setColor(0xFFFFFF);
                    }
                    n2 = by % 10;
                    graphics.drawString(this.a.b[this.c[by]], 25, 22 + n * n2, 20);
                    graphics.drawString(String.valueOf(this.a.r[this.c[by]]), 94, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.a.s[this.c[by]]), 123, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.a.t[this.c[by]]), 152, 22 + n * n2, 17);
                    graphics.drawString(String.valueOf(this.a.u[this.c[by]]), 181, 22 + n * n2, 17);
                    if (this.a.v[this.c[by]] > 99) {
                        graphics.drawString("--", 210, 22 + n * n2, 17);
                        continue;
                    }
                    graphics.drawString(String.valueOf(this.a.v[this.c[by]]), 210, 22 + n * n2, 17);
                }
                graphics.setColor(0xFFFFFF);
                graphics.drawString("\u51b3\u5b9a", 25, 22 + n * (this.R % 10), 20);
            }
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.T + 1 + "/" + this.S, this.f >> 1, 176, 17);
            graphics.drawImage(this.a, 104, 177, 20);
            graphics.drawRegion(this.a, 0, 0, 6, 8, 2, 76, 177, 24);
        }
        graphics.drawImage(this.a, 12, 27 + n * this.F, 0);
    }

    private final void m(Graphics graphics) {
        switch (this.O) {
            case 0: {
                graphics.drawImage(this.a[0], 0, 0, 20);
                graphics.drawRegion(this.a[1], 80 * this.F, 0, 80, 19, 0, this.f >> 1, this.g - 8, 33);
                if (!this.X) break;
                graphics.drawImage(this.b, this.f >> 1, 70, 17);
                graphics.setColor(0xFFFFFF);
                graphics.drawString("\u521b\u5efa\u6b66\u5c06", this.f >> 1, 90, 17);
                graphics.drawString("\u5220\u9664\u6b66\u5c06", this.f >> 1, 90 + this.a, 17);
                graphics.drawImage(this.a, (this.f >> 1) - 2 * this.b - 10, 95 + this.o * this.a, 20);
                return;
            }
            case 2: {
                this.T(graphics);
            }
        }
    }

    private final void n(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.e, this.f >> 1, 35, 17);
        graphics.setColor(0xFFFF00);
        graphics.fillRect(11 + 38 * this.G, 303, 29, 15);
        graphics.drawImage(this.a[1], this.f >> 1, 45, 17);
        graphics.drawRegion(this.a[4], 160, 0, 20, 20, 0, 150, 46, 0);
        graphics.drawImage(this.a[2], this.f >> 1, this.g - 3, 33);
        graphics.drawImage(this.a[0], 138, 82, 20);
        graphics.setColor(0);
        graphics.drawRect(138, 82, this.a[0].getWidth(), this.a[0].getHeight());
        this.t(graphics);
        if (this.F) {
            this.U(graphics);
        }
    }

    private final void o(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.e, this.f >> 1, 35, 17);
        graphics.drawImage(this.a[0], this.f >> 1, 60, 17);
        graphics.setColor(0);
        graphics.drawRect(93, 60, this.a[0].getWidth(), this.a[0].getHeight());
        graphics.setColor(0xFFFFFF);
        this.a(graphics, this.a.a, 35, 188, 185, 100);
        this.d(graphics);
        this.b(graphics, "\u6267\u884c\u6307\u4ee4");
    }

    private final void p(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        graphics.setColor(0xFFFF00);
        this.q(graphics);
        if (!this.E) {
            for (byte by = 0; by < 4; by = (byte)(by + 1)) {
                if (this.U == by && this.I) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.c[by]), 145 - this.h * by, 96 + this.F * 25, 20);
            }
            if (this.I) {
                graphics.drawImage(this.a[1], 147 - this.h * this.U, 92 + this.F * 25, 20);
            }
        }
        this.b(graphics, c.a[0][3]);
        this.d(graphics);
        if (this.K) {
            this.a(graphics, "\u8f93\u9001\u6570\u91cf\u4e0d\u80fd\u5168\u4e3a\u7a7a!");
        }
        if (this.E) {
            this.a(graphics, this.I);
        }
    }

    private final void q(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.F == 0) {
            graphics.drawRegion(this.a[2], 0, 0, 27, 15, 0, 80, 96, 17);
            graphics.drawRegion(this.a[2], 0, 45, 27, 15, 0, 80, 121, 17);
            graphics.drawRegion(this.a[2], 0, 75, 27, 15, 0, 80, 146, 17);
            graphics.drawString(String.valueOf(this.d[1]), 140, 121, 17);
            graphics.drawString(String.valueOf(this.d[2]), 140, 146, 17);
            return;
        }
        if (this.F == 1) {
            graphics.drawRegion(this.a[2], 0, 15, 27, 15, 0, 80, 96, 17);
            graphics.drawRegion(this.a[2], 0, 30, 27, 15, 0, 80, 121, 17);
            graphics.drawRegion(this.a[2], 0, 75, 27, 15, 0, 80, 146, 17);
            graphics.drawString(String.valueOf(this.d[0]), 140, 96, 17);
            graphics.drawString(String.valueOf(this.d[2]), 140, 146, 17);
            return;
        }
        if (this.F == 2) {
            graphics.drawRegion(this.a[2], 0, 15, 27, 15, 0, 80, 96, 17);
            graphics.drawRegion(this.a[2], 0, 45, 27, 15, 0, 80, 121, 17);
            graphics.drawRegion(this.a[2], 0, 60, 27, 15, 0, 80, 146, 17);
            graphics.drawString(String.valueOf(this.d[0]), 140, 96, 17);
            graphics.drawString(String.valueOf(this.d[1]), 140, 121, 17);
            return;
        }
        graphics.drawRegion(this.a[2], 0, 15, 27, 15, 0, 80, 96, 17);
        graphics.drawRegion(this.a[2], 0, 45, 27, 15, 0, 80, 121, 17);
        graphics.drawRegion(this.a[2], 0, 75, 27, 15, 0, 80, 146, 17);
        graphics.drawString(String.valueOf(this.d[0]), 140, 96, 17);
        graphics.drawString(String.valueOf(this.d[1]), 140, 121, 17);
        graphics.drawString(String.valueOf(this.d[2]), 140, 146, 17);
    }

    private final void r(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.e, this.f >> 1, 35, 17);
        graphics.drawImage(this.a[0], this.f >> 1, 60, 17);
        graphics.setColor(0);
        graphics.drawRect(93, 60, this.a[0].getWidth(), this.a[0].getHeight());
        this.b(graphics, c.a[1][0]);
        this.d(graphics);
        graphics.setColor(0xFFFFFF);
        if (this.a.b == 1 || this.a.b == 2) {
            graphics.drawString(c.b[this.a.b] + this.a.a, this.f >> 1, 180, 17);
            return;
        }
        graphics.drawString(c.b[this.a.b], this.f >> 1, 180, 17);
    }

    private final void s(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        if (this.F > 0) {
            graphics.drawRegion(this.a, 0, 0, 6, 8, 6, this.f >> 1, 80, 17);
        }
        if (this.F < this.a.q - 1) {
            graphics.drawRegion(this.a, 0, 0, 6, 8, 5, this.f >> 1, 192, 33);
        }
        graphics.drawImage(this.a[0], 137, 97, 20);
        graphics.setColor(0);
        graphics.drawRect(137, 97, this.a[0].getWidth(), this.a[0].getHeight());
        graphics.setColor(0xFFFFFF);
        graphics.drawString("\u52bf\u529b: " + this.a.b[this.a.a[this.a.E[this.F]]], 50, 110, 20);
        graphics.drawString("\u57ce\u6c60\u6570: " + this.a.b[this.a.E[this.F]], 50, 140, 20);
        this.b(graphics, c.a[3][0]);
        this.d(graphics);
        if (this.E) {
            this.a(graphics, this.I);
        }
    }

    private final void t(Graphics graphics) {
        boolean bl = false;
        boolean bl2 = false;
        graphics.setColor(16275794);
        this.W(graphics);
        graphics.drawString(String.valueOf(this.a.n), 180, 47, 17);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.a.a[this.a.g], 100, 47, 17);
        graphics.drawString(this.a.b[this.a.a[this.a.e]], 100, 82, 17);
        graphics.drawString(this.a.b[this.a.c[this.a.g]], 100, 109, 17);
        graphics.drawString(String.valueOf(this.a.e[this.a.g]), 100, 135, 17);
        int n = this.a.a[this.a.g] / 1000;
        int n2 = this.a.h[this.a.g] / 100;
        graphics.drawString(String.valueOf(n), 80, 165, 17);
        graphics.drawString(String.valueOf(this.a.g[this.a.g]), 128, 165, 17);
        graphics.drawString(String.valueOf(n2), 180, 165, 17);
        graphics.drawString(String.valueOf(this.a.f[this.a.g]), 80, 192, 17);
        graphics.drawString(String.valueOf(this.a.g[this.a.g]), 128, 192, 17);
        graphics.drawString(String.valueOf(this.a.f[this.a.g]), 180, 192, 17);
        graphics.drawString(String.valueOf(this.a.d[this.a.g]), 100, 220, 17);
        graphics.drawString(String.valueOf(this.a.e[this.a.g]), 170, 220, 17);
    }

    private final void u(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.e, this.f >> 1, 35, 17);
        if (this.G) {
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawImage(this.a[3], this.f >> 1, 45, 17);
            graphics.drawImage(this.a[1], 138, 82, 20);
            graphics.setColor(0);
            graphics.drawRect(138, 82, this.a[1].getWidth(), this.a[1].getHeight());
            this.b(graphics, c.a[3][3]);
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.a.a[this.a.h], 100, 47, 17);
            graphics.drawString(String.valueOf(this.a.e[this.a.h]), 99, 135, 17);
            int n = this.a.a[this.a.h] / 1000;
            int n2 = this.a.h[this.a.h] / 100;
            graphics.drawString(String.valueOf(n), 80, 165, 17);
            graphics.drawString(String.valueOf(this.a.g[this.a.h]), 128, 165, 17);
            graphics.drawString(String.valueOf(n2), 180, 165, 17);
            graphics.drawString(String.valueOf(this.a.f[this.a.h]), 80, 192, 17);
            graphics.drawString(String.valueOf(this.a.g[this.a.h]), 128, 192, 17);
            graphics.drawString(String.valueOf(this.a.f[this.a.h]), 180, 192, 17);
            graphics.drawString(String.valueOf(this.a.d[this.a.h]), 100, 220, 17);
            graphics.drawString(String.valueOf(this.a.e[this.a.h]), 170, 220, 17);
        } else if (this.L) {
            int n;
            boolean bl = false;
            boolean bl3 = false;
            this.b(graphics, c.a[3][3]);
            this.a = "\u8be5\u57ce\u6b66\u5c06\u4fe1\u606f,\u4e0a\u4e0b\u952e\u7ffb\u9875";
            graphics.drawImage(this.a[2], 57, 48, 20);
            graphics.setColor(0);
            graphics.drawRect(57, 48, this.a[2].getWidth(), this.a[2].getHeight());
            for (n = 0; n < 8; n = (int)((byte)(n + 1))) {
                graphics.drawRegion(this.a[0], n * 20, 0, 20, 20, 0, 60 + n % 2 * 75, n / 2 * 25 + 137, 0);
            }
            n = this.a.b[this.a.h][this.F];
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.a.b[n], 137, 54, 20);
            graphics.setColor(16275794);
            graphics.drawString("LV. " + this.a.o[n], 159, 80, 20);
            graphics.drawString(String.valueOf(this.a.s[n]), 85, 140, 20);
            graphics.drawString(String.valueOf(this.a.t[n]), 160, 140, 20);
            graphics.drawString(String.valueOf(this.a.r[n]), 85, 165, 20);
            graphics.drawString(String.valueOf(this.a.u[n]), 160, 165, 20);
            graphics.drawString(String.valueOf(this.a.k[n]), 85, 190, 20);
            graphics.drawString(String.valueOf(this.a.l[n] / 100), 160, 190, 20);
            graphics.drawString(this.a.c[this.a.w[n]], 85, 215, 20);
            graphics.drawString(this.a.c[this.a.x[n]], 160, 215, 20);
            graphics.drawRegion(this.c, 87, 0, 29, 17, 0, 72, 267, 0);
            graphics.drawRegion(this.c, 29, 0, 29, 17, 0, 135, 267, 0);
            graphics.drawImage(this.a, 60 + this.I * 63, 271, 0);
        } else {
            boolean bl = false;
            boolean bl4 = false;
            graphics.drawImage(this.a[3], this.f >> 1, 45, 17);
            graphics.drawImage(this.a[1], 138, 82, 20);
            graphics.setColor(0);
            graphics.drawRect(138, 82, this.a[1].getWidth(), this.a[1].getHeight());
            this.b(graphics, c.a[3][3]);
            graphics.setColor(0xFFFFFF);
            graphics.drawString(this.a.a[this.a.h], 100, 47, 17);
            graphics.drawString(this.a.b[this.a.b[this.a.h]], 100, 82, 17);
            graphics.drawString(this.a.b[this.a.c[this.a.h]], 100, 109, 17);
            graphics.drawString(String.valueOf(this.a.e[this.a.h]), 100, 135, 17);
            int n = this.a.a[this.a.h] / 1000;
            int n3 = this.a.h[this.a.h] / 100;
            graphics.drawString(String.valueOf(n), 80, 165, 17);
            graphics.drawString(String.valueOf(this.a.g[this.a.h]), 128, 165, 17);
            graphics.drawString(String.valueOf(n3), 180, 165, 17);
            graphics.drawString(String.valueOf(this.a.f[this.a.h]), 80, 192, 17);
            graphics.drawString(String.valueOf(this.a.g[this.a.h]), 128, 192, 17);
            graphics.drawString(String.valueOf(this.a.f[this.a.h]), 180, 192, 17);
            graphics.drawString(String.valueOf(this.a.d[this.a.h]), 100, 220, 17);
            graphics.drawString(String.valueOf(this.a.e[this.a.h]), 170, 220, 17);
            graphics.drawRegion(this.c, 58, 0, 29, 17, 0, 72, 267, 0);
            graphics.drawRegion(this.c, 29, 0, 29, 17, 0, 135, 267, 0);
            graphics.drawImage(this.a, 60 + this.I * 63, 271, 0);
        }
        this.d(graphics);
    }

    private final void v(Graphics graphics) {
        if (this.M) {
            graphics.setColor(0xFFFFFF);
            graphics.drawImage(this.f, 0, 0, 20);
            graphics.drawImage(this.d, this.f >> 1, 60, 17);
            graphics.drawRegion(this.a[2], 0, 0, 32, 39, 0, 60, 110, 20);
            graphics.drawRegion(this.a[2], 32, 0, 32, 39, 0, 140, 110, 20);
            graphics.drawImage(this.a, 51 + 80 * this.F, 126, 0);
            this.a = this.F == 0 ? "\u8d2d\u4e70\u7cae\u98df" : "\u8d2d\u4e70\u9ec4\u91d1";
        } else {
            short s = this.a(this.c);
            this.i = this.F == 0 ? s * 4 / 3 : 3 * s / 4;
            graphics.drawImage(this.f, 0, 0, 20);
            graphics.drawImage(this.d, this.f >> 1, 60, 17);
            this.a = "100\u7c73 \u53ef\u6613 75\u91d1";
            graphics.drawRegion(this.a[2], 32 * this.F, 0, 32, 39, 0, this.f >> 1, 90, 17);
            boolean bl = false;
            boolean bl2 = false;
            graphics.drawRegion(this.a[3], 0, 30, 27, 15, 0, 130 - 90 * this.F, 145, 20);
            graphics.drawRegion(this.a[3], 0, 0, 27, 15, 0, 40 + 90 * this.F, 145, 20);
            graphics.setColor(0xFFFFFF);
            graphics.drawString("= ", 112, 144, 20);
            graphics.drawString(String.valueOf(this.i), 158, 143, 20);
            for (byte by = 0; by < 4; by = (byte)(by + 1)) {
                if (this.U == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.c[by]), 102 - this.h * by, 143, 24);
            }
            graphics.drawImage(this.a[1], 95 - this.h * this.U, 141, 20);
            if (this.K) {
                this.a(graphics, "\u8d2d\u4e70\u6570\u91cf\u4e0d\u80fd\u4e3a0!");
            }
            if (this.E) {
                this.a(graphics, this.I);
            }
        }
        this.b(graphics, c.a[4][0]);
        this.d(graphics);
    }

    private final void w(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.setColor(10656072);
        graphics.fillRect(36, 77, 168, 120);
        graphics.setColor(0);
        graphics.drawRect(36, 77, 168, 120);
        graphics.drawImage(this.a[0], this.f >> 1, 60, 17);
        this.b(graphics, c.a[4][1]);
        this.d(graphics);
        graphics.drawRegion(this.a, 0, 0, 6, 8, 5, 50 + this.K * 45, 60, 20);
        graphics.drawImage(this.a, 50, 105 + 20 * this.F, 0);
        for (byte by = 0; by < 4; by = (byte)(by + 1)) {
            if (by == this.F) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            byte by2 = this.a(this.K, by);
            graphics.drawString(this.a.c[by2] + "  \u9700\u91d1: " + this.a.q[by2], 60, 100 + 20 * by, 0);
        }
        if (this.E) {
            this.a(graphics, this.I);
        }
    }

    private final byte a(byte by, byte by2) {
        byte by3 = 0;
        byte by4 = !this.A ? this.a.l[this.a.g] : this.a.m[this.a.t];
        switch (by) {
            case 0: {
                by3 = this.a.c[by4][by2];
                break;
            }
            case 1: {
                by3 = this.a.d[by4][by2];
                break;
            }
            case 2: {
                by3 = this.a.e[by4][by2];
                break;
            }
            case 3: {
                by3 = this.a.f[by4][by2];
            }
        }
        return by3;
    }

    private final void x(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        this.d(graphics);
        if (this.a.P == 22) {
            this.b(graphics, c.a[5][0]);
        } else {
            this.b(graphics, c.a[5][1]);
            if (this.b()) {
                graphics.setColor(0xFFFF00);
                graphics.drawString("\u65e0\u5b58\u8d2e\u8bb0\u5f55", this.f >> 1, 120, 17);
                return;
            }
        }
        for (byte by = 0; by < 3; by = (byte)(by + 1)) {
            if (this.F == by) {
                graphics.setColor(16275794);
                graphics.drawImage(this.a, 45, 95 + 25 * by, 20);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            if (this.a.r[by] == 0) {
                graphics.drawString("\u7a7a\u8bb0\u5f55", this.f >> 1, 90 + 25 * by, 17);
                continue;
            }
            String string = this.a.d[by] + "  " + String.valueOf(this.a.s[by]) + "\u5e74" + String.valueOf(this.a.C[by]) + "\u6708";
            graphics.drawString(string, this.f >> 1, 90 + 25 * by, 17);
        }
        if (this.E) {
            this.a(graphics, this.I);
        }
    }

    private final boolean b() {
        for (int n = 0; n < 3; n = (int)((byte)(n + 1))) {
            if (this.a.r[n] == 0) continue;
            return false;
        }
        return true;
    }

    public final synchronized boolean a() {
        if (!this.U) {
            this.U = true;
            return false;
        }
        return true;
    }

    public final void paint(Graphics graphics) {
        if (this.a()) {
            return;
        }
        try {
            this.b(graphics, (byte)0);
            switch (this.E) {
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
                    break;
                }
                case 27: {
                    this.Y(graphics);
                }
            }
            if (this.D) {
                this.a(graphics, this.d);
            }
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            b.a(exception);
            this.a("paint() screenid:" + this.E + "\n:error info:" + exception2.toString());
        }
        this.U = false;
    }

    private final void y(Graphics graphics) {
        byte by;
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        this.b(graphics, c.a[5][2]);
        graphics.setColor(0xFFFFFF);
        for (by = 0; by < 3; by = (byte)(by + 1)) {
            if (this.N == by) {
                graphics.setColor(0xFFFF00);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawString(c.l[by], 50, 90 + by * 30, 20);
        }
        for (by = 0; by < 2; by = (byte)(by + 1)) {
            for (int n = 0; n < 2; n = (int)((byte)(n + 1))) {
                if (this.a[n] == by) {
                    graphics.setColor(0xFFFF00);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(c.m[by], 90 + by * 50, 90 + n * 30, 20);
            }
        }
        for (by = 0; by < 5; by = (byte)(by + 1)) {
            if (this.a[2] == by) {
                graphics.setColor(0xFFFF00);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawString(c.n[by], 90 + by * 20, 150, 20);
        }
        if (this.E) {
            this.a(graphics, this.I);
        }
        this.d(graphics);
    }

    private final void z(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, this.f, this.g);
        if (this.T) {
            graphics.drawImage(this.a[0], this.f >> 1, 50, 17);
            graphics.setColor(0x996600);
            graphics.drawRect(38, 50, this.a[0].getWidth(), this.a[0].getHeight());
            graphics.drawImage(this.a[1], this.f >> 1, 40, 17);
            this.a = "\u7edf\u4e00\u5ea6:" + this.a.g + " \u5206\u6570:" + this.a.f;
            graphics.setColor(0xFFFF00);
            this.a(graphics, this.a, 40, 180, 170, 140);
            return;
        }
        graphics.drawImage(this.a[0], this.f >> 1, 50, 17);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 50, this.a[0].getWidth(), this.a[0].getHeight());
        graphics.drawImage(this.a[1], this.f >> 1, 40, 17);
        graphics.setColor(0xFFFF00);
        this.a(graphics, this.a, 40, 180, 170, 140);
    }

    private final void A(Graphics graphics) {
        try {
            switch (this.u) {
                case 0: {
                    if (this.A > 0) {
                        this.C(graphics);
                        break;
                    }
                    this.B(graphics);
                    break;
                }
                case 1: {
                    if (this.y) {
                        this.a.R = (byte)2;
                    } else {
                        this.a.R = 1;
                        this.D = (byte)this.a.b();
                    }
                    if (this.D >= 0) break;
                    this.a.c = 1;
                    break;
                }
                case 2: {
                    this.a.R = this.y ? (byte)4 : (byte)3;
                    this.a.c = 1;
                    break;
                }
                case 3: {
                    if (this.y) {
                        this.a.c(false);
                    } else {
                        this.a.c(true);
                    }
                    this.a.c = 1;
                }
            }
            if (this.D > -1) {
                String string = this.b(this.D);
                this.a(graphics, string);
            }
            return;
        }
        catch (Exception exception) {
            this.a("Duel Paint() error!" + exception.toString());
            return;
        }
    }

    private final String b(byte by) {
        return c.q[by];
    }

    private final void B(Graphics graphics) {
        this.D(graphics);
        if (this.x) {
            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
            graphics.drawImage(this.a[1], this.f >> 1, 50, 17);
            graphics.drawImage(this.a[3], 20, 54, 20);
            graphics.setColor(0);
            graphics.drawRect(20, 52, this.a[3].getWidth(), this.a[3].getHeight());
            int n = this.x % 3;
            int n2 = this.x / 3;
            if (this.a == null) {
                this.a = this.a(109);
            }
            graphics.drawImage(this.a, 95 + 42 * n, 68 + 35 * n2, 0);
            return;
        }
        if (this.z == 0) {
            switch (this.x) {
                case 0: 
                case 1: 
                case 2: {
                    this.c = "\u653b\u51fb\u654c\u65b9\u4e2d...";
                    this.a(graphics, (int)this.v, this.x, false);
                    break;
                }
                case 3: {
                    if (this.a.f(this.c, this.d)) {
                        this.A = (byte)2;
                        this.y = false;
                        this.c = "\u6210\u529f\u62db\u964d!";
                        return;
                    }
                    this.A = (byte)100;
                    this.c = "\u62db\u964d\u5931\u8d25!";
                    this.z = (byte)(this.z + 1);
                    this.a(500);
                    break;
                }
                case 4: {
                    if (this.a.b(this.c, this.d)) {
                        this.A = (byte)3;
                        this.y = true;
                        this.c = "\u81ea\u519b\u6210\u529f\u64a4\u79bb!";
                    } else {
                        this.A = (byte)100;
                        this.c = "\u64a4\u9000\u5931\u8d25...";
                    }
                    this.z = (byte)(this.z + 1);
                    return;
                }
                case 5: {
                    this.A = (byte)2;
                    this.y = true;
                    this.c = "\u6211\u65b9\u6295\u964d!";
                    return;
                }
            }
            return;
        }
        switch (this.y) {
            case 0: 
            case 1: 
            case 2: {
                this.c = "\u654c\u65b9\u6b66\u5c06\u653b\u51fb\u4e2d....";
                this.a(graphics, (int)this.v, this.y, true);
                return;
            }
            case 3: {
                if (this.a.f(this.d, this.c)) {
                    this.A = (byte)2;
                    this.y = true;
                    this.c = "\u6210\u529f\u62db\u964d!";
                    return;
                }
                this.A = (byte)100;
                this.c = "\u62db\u964d\u5931\u8d25!";
                this.x = 0;
                this.x = true;
                this.z = 0;
                this.a(500);
                return;
            }
            case 4: {
                if (this.a.b(this.d, this.c)) {
                    this.A = (byte)3;
                    this.y = false;
                    this.c = "\u654c\u65b9\u6b66\u5c06\u9003\u7a9c!";
                } else {
                    this.A = (byte)100;
                    this.c = "\u654c\u65b9\u9041\u9003\u5931\u8d25!";
                }
                this.x = 0;
                this.x = true;
                this.z = 0;
                return;
            }
            case 5: {
                this.A = (byte)2;
                this.y = false;
                this.c = "\u654c\u65b9\u6b66\u5c06\u5f52\u964d!";
                return;
            }
        }
    }

    private final void b(Graphics graphics, boolean bl) {
        try {
            if (bl) {
                int n = 179 - this.w * 6;
                if (this.w < 23) {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 300 + this.w % 2 * 540, 0, 60, 60, 2, n, this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    this.v = (byte)(this.v + 1);
                    this.w = 0;
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, 51, this.e, 20);
                }
            } else {
                int n = 139 - this.w * 6;
                if (this.w < 23) {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    graphics.drawRegion(this.a[2], 300 + this.w % 2 * 540, 0, 60, 60, 2, n, this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    this.v = 1;
                    this.w = 0;
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    this.z = (byte)(this.z + 1);
                    this.y = this.a.b(this.d, this.c);
                }
            }
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
                int n = 55 + this.w * 6;
                if (this.w < 22) {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 300 + this.w % 2 * 540, 0, 60, 60, 0, n, this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    this.v = 1;
                    this.w = 0;
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    this.z = 0;
                    this.x = 0;
                    this.x = true;
                    this.c = "\u8bf7\u9009\u62e9\u6267\u884c\u547d\u4ee4";
                }
            } else {
                int n = 5 + this.w * 6;
                if (this.w < 22) {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    graphics.drawRegion(this.a[2], 300 + this.w % 2 * 540, 0, 60, 60, 0, n, this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 137, this.e, 20);
                    this.v = (byte)(this.v + 1);
                    this.w = 0;
                }
            }
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
                if (this.w < 3) {
                    by = (byte)(this.w + 2);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 60 * by, 0, 60, 60, 2, 55 - 8 * (by % 2), this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    byte by2 = this.a.a(this.d, this.a.m, this.a.l);
                    int n = this.a.r[this.c] - by2;
                    if (n < 1) {
                        this.a.r[this.c] = 0;
                        this.a(true, 0);
                    } else {
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, 51, this.e, 20);
                        short s = this.c;
                        this.a.r[s] = (byte)(this.a.r[s] - by2);
                        this.v = (byte)(this.v + 1);
                        this.w = 0;
                    }
                }
            } else {
                byte by = 0;
                if (this.w < 3) {
                    by = (byte)(this.w + 2);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    graphics.drawRegion(this.a[2], 60 * by, 0, 60, 60, 0, 137 + 8 * (by % 2), this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    byte by3 = this.a.a(this.c, this.a.k, this.a.n);
                    int n = this.a.r[this.d] - by3;
                    if (n < 1) {
                        this.a.r[this.d] = 0;
                        this.a(false, 1);
                    } else {
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, this.f - 51, this.e, 24);
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                        short s = this.d;
                        this.a.r[s] = (byte)(this.a.r[s] - by3);
                        this.v = (byte)(this.v + 1);
                        this.w = 0;
                    }
                }
            }
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
                if (this.w < 5) {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 60 * this.w, 0, 60, 60, 2, 51, this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    byte by = this.a.b(this.d, this.a.m, this.a.l);
                    int n = this.a.r[this.c] - by;
                    if (n < 1) {
                        this.a.r[this.c] = 0;
                        this.a(true, 0);
                    } else {
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, 51, this.e, 20);
                        short s = this.c;
                        this.a.r[s] = (byte)(this.a.r[s] - by);
                        this.v = (byte)(this.v + 1);
                        this.w = 0;
                    }
                }
            } else if (this.w < 5) {
                graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                graphics.drawRegion(this.a[2], 60 * this.w, 0, 60, 60, 0, this.f - 51, this.e, 24);
                this.w = (byte)(this.w + 1);
            } else {
                byte by = this.a.b(this.c, this.a.k, this.a.n);
                int n = this.a.r[this.d] - by;
                if (n < 1) {
                    this.a.r[this.d] = 0;
                    this.a(false, 1);
                } else {
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, this.f - 51, this.e, 24);
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                    short s = this.d;
                    this.a.r[s] = (byte)(this.a.r[s] - by);
                    this.v = (byte)(this.v + 1);
                    this.w = 0;
                }
            }
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
                if (this.w < 11) {
                    int n = this.w < 6 ? this.w + 5 : this.w - 6;
                    graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                    graphics.drawRegion(this.a[2], 60 * n, 0, 60, 60, 2, 51, this.e, 20);
                    this.w = (byte)(this.w + 1);
                } else {
                    byte by = this.a.c(this.d, this.a.m, this.a.l);
                    if (by > 0) {
                        int n = this.a.r[this.c] - by;
                        if (n < 1) {
                            this.a.r[this.c] = 0;
                            this.a(true, 0);
                        } else {
                            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, 51, this.e, 20);
                            short s = this.c;
                            this.a.r[s] = (byte)(this.a.r[s] - by);
                            this.v = (byte)(this.v + 1);
                            this.w = 0;
                        }
                    } else {
                        int n = this.a.r[this.d] + by;
                        if (n < 1) {
                            this.a.r[this.d] = 0;
                            this.a(false, 0);
                        } else {
                            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, 51, this.e, 20);
                            short s = this.d;
                            this.a.r[s] = (byte)(this.a.r[s] + by);
                            this.v = (byte)(this.v + 1);
                            this.w = 0;
                        }
                    }
                }
            } else if (this.w < 11) {
                int n = this.w < 6 ? this.w + 5 : this.w - 6;
                graphics.drawRegion(this.a[2], 60 * n, 0, 60, 60, 0, this.f - 51, this.e, 24);
                graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                this.w = (byte)(this.w + 1);
            } else {
                byte by = this.a.c(this.c, this.a.k, this.a.n);
                if (by < 0) {
                    int n = this.a.r[this.c] + by;
                    if (n < 1) {
                        this.a.r[this.c] = 0;
                        this.a(true, 1);
                    } else {
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, this.f - 51, this.e, 24);
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                        short s = this.c;
                        this.a.r[s] = (byte)(this.a.r[s] + by);
                        this.v = (byte)(this.v + 1);
                        this.w = 0;
                    }
                } else {
                    int n = this.a.r[this.d] - by;
                    if (n < 1) {
                        this.a.r[this.d] = 0;
                        this.a(false, 1);
                    } else {
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, this.f - 51, this.e, 24);
                        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                        short s = this.d;
                        this.a.r[s] = (byte)(this.a.r[s] - by);
                        this.v = (byte)(this.v + 1);
                        this.w = 0;
                    }
                }
            }
            return;
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            return;
        }
    }

    private final void a(boolean bl, int n) {
        this.A = 1;
        this.y = bl;
        this.C = (byte)n;
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
                    break;
                }
                case 1: {
                    this.e(graphics, bl);
                    break;
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
        if (this.A == 1) {
            if (!this.y) {
                this.c = this.a.b[this.d] + "\u6218\u4ea1\u6c99\u573a.";
                if (this.B < 3) {
                    graphics.drawRegion(this.a[2], 660, 0, 60, 60, 0, 5 + 124 * this.C, this.e, 20);
                    graphics.drawRegion(this.a[2], 780, 0, 60, 60, 2, 51 + 128 * this.C, this.e + 10, 20);
                    this.B = (byte)3;
                    return;
                }
                graphics.drawRegion(this.a[2], 660, 0, 60, 60, 0, 5 + 124 * this.C, this.e, 20);
                graphics.drawRegion(this.a[2], 780, 0, 60, 60, 2, 51 + 128 * this.C, this.e + 10, 20);
                return;
            }
            this.c = this.a.b[this.c] + "\u6218\u4ea1\u6c99\u573a.";
            if (this.B < 3) {
                graphics.drawRegion(this.a[2], 780, 0, 60, 60, 0, 5 + 124 * this.C, this.e + 10, 20);
                graphics.drawRegion(this.a[2], 660, 0, 60, 60, 2, 51 + 128 * this.C, this.e, 20);
                this.B = (byte)(this.B + 1);
                return;
            }
            graphics.drawRegion(this.a[2], 780, 0, 60, 60, 0, 5 + 124 * this.C, this.e + 10, 20);
            graphics.drawRegion(this.a[2], 660, 0, 60, 60, 2, 51 + 128 * this.C, this.e, 20);
            return;
        }
        if (this.A == 2) {
            if (this.z) {
                graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
                graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                this.z = false;
                this.A = 0;
                this.x = (byte)3;
                this.x = false;
                return;
            }
            if (!this.y) {
                graphics.drawRegion(this.a[2], 660, 0, 60, 60, 0, 5, this.e, 20);
                graphics.drawRegion(this.a[2], 720, 0, 60, 60, 2, this.f - 5, this.e, 24);
                return;
            }
            graphics.drawRegion(this.a[2], 720, 0, 60, 60, 0, 5, this.e, 20);
            graphics.drawRegion(this.a[2], 660, 0, 60, 60, 2, this.f - 5, this.e, 24);
            return;
        }
        if (this.A == 3) {
            if (!this.y) {
                graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
                graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, this.f - 5, this.e, 24);
                return;
            }
            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, 5, this.e, 20);
            graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
            return;
        }
        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 0, 5, this.e, 20);
        graphics.drawRegion(this.a[2], 300, 0, 60, 60, 2, this.f - 5, this.e, 24);
    }

    private final void D(Graphics graphics) {
        byte by;
        graphics.drawImage(this.a[0], 0, 0, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.a.b[this.c], this.f >> 2, 2, 17);
        graphics.drawString(this.a.b[this.d], this.f * 3 >> 2, 2, 17);
        graphics.setColor(0);
        this.a = this.c;
        this.d(graphics);
        graphics.setColor(16275794);
        graphics.drawString(String.valueOf(this.a.r[this.c]), 0, 28, 0);
        graphics.drawString(String.valueOf(this.a.r[this.d]), this.f, 28, 24);
        for (by = 0; by < 100; by = (byte)(by + 1)) {
            if (by < this.a.r[this.c]) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawLine(18 + by, 28, 18 + by, 43);
        }
        for (by = 0; by < 100; by = (byte)(by + 1)) {
            if (by < this.a.r[this.d]) {
                graphics.setColor(16275794);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            graphics.drawLine(223 - by, 28, 223 - by, 43);
        }
        graphics.setColor(0);
        graphics.drawRect(18, 28, 100, 15);
        graphics.drawRect(123, 28, 100, 15);
    }

    private final void E(Graphics graphics) {
        switch (this.t) {
            case 1: {
                this.G(graphics);
                return;
            }
            case 2: {
                if (this.a.R > 0) {
                    this.d = this.a.R > 4 && this.a.U > 0 ? c.k[this.a.R] + " \u4f53\u529b\u51cf\u5c11" + String.valueOf(this.a.U) : c.k[this.a.R];
                    if (this.t) {
                        this.D = true;
                    }
                    this.t = true;
                    if (this.a.r[this.c] == 0) {
                        this.a.k[this.d] = this.a.a(false);
                        return;
                    }
                    this.a.k[this.c] = this.a.a(true);
                    return;
                }
                switch (this.a.V) {
                    case 0: {
                        this.a.V = 1;
                        this.a.a(this.W);
                        this.a.c = 1;
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
        int n;
        graphics.drawRegion(this.a[1], 0, 93, 240, 154, 0, this.f >> 1, this.g, 33);
        short s = 0;
        int n2 = 0;
        for (n = 1; n < 4; n = (int)((byte)(n + 1))) {
            s = (short)(s + this.a.x[n]);
            n2 = (short)(n2 + this.a.y[n]);
        }
        graphics.setColor(16275794);
        graphics.drawString(String.valueOf(n2), 135, 295, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawImage(this.a[5], 6, 167, 20);
        graphics.drawString(this.a.b[this.c], 36, 240, 17);
        graphics.drawString(String.valueOf(this.a.r[this.c]), 55, 274, 20);
        graphics.drawString(String.valueOf(this.a.s[this.c]), 135, 274, 20);
        graphics.drawString(String.valueOf(this.a.N), 208, 274, 20);
        graphics.drawString(String.valueOf(s), 55, 294, 20);
        for (n = 0; n < 4; n = (int)((byte)(n + 1))) {
            graphics.drawString(c.d[this.a.O[n]], 179, 174 + n * 19, 20);
        }
        graphics.drawImage(this.a, 170, 178 + this.a.W * 19, 0);
        if (this.R) {
            this.b(graphics);
        }
    }

    private final void G(Graphics graphics) {
        if (this.f == null) {
            this.f = this.a(302);
        }
        if (this.d == null) {
            this.d = this.a(90);
        }
        if (this.a == null) {
            this.a = this.a(109);
        }
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        if (this.g == null) {
            this.g = this.a(49);
        }
        graphics.drawImage(this.g, this.f >> 1, 80, 17);
        this.b(graphics, c.a[0][1]);
        graphics.drawRegion(this.a, 0, 0, 6, 8, 7, 50 + this.W * 43, 193, 0);
        graphics.drawImage(this.a[5], 35, 78, 20);
        graphics.drawImage(this.a[6], 147, 78, 20);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.a.b[this.c], 115, 82, 17);
        graphics.drawString(this.a.b[this.d], 128, 132, 17);
        graphics.setColor(0);
        this.a = "\u9635\u578b\u9009\u62e9";
        this.d(graphics);
    }

    private final void H(Graphics graphics) {
        graphics.setColor(0);
        boolean bl = false;
        boolean bl2 = false;
        this.c = 81;
        this.d = 171;
        graphics.drawRect(80, 170, this.a.L * 5 + 2, this.a.M * 5 + 2);
    }

    private final void a(Graphics graphics, byte by, byte by2) {
        if (by == 0) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(16275794);
        }
        if (by2 == 0) {
            graphics.fillRect(this.c + this.a.j[by][by2] * 5, this.d + this.a.k[by][by2] * 5, 5, 5);
            return;
        }
        graphics.drawRect(this.c + this.a.j[by][by2] * 5, this.d + this.a.k[by][by2] * 5, 4, 4);
    }

    private final void I(Graphics graphics) {
        byte by;
        this.M(graphics);
        graphics.drawRegion(this.a[1], 0, 0, 240, 92, 0, this.f >> 1, this.g, 33);
        graphics.setColor(16275794);
        int n = this.a.S >> 4;
        int n2 = this.a.T >> 4;
        graphics.drawString(c.j[n], 53, this.g, 36);
        graphics.drawString(c.j[n2], this.f - 53, this.g, 40);
        graphics.drawImage(this.a[5], 0, 230, 20);
        graphics.drawImage(this.a[6], this.f, 230, 24);
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.a.b[this.c], 24, this.g - 2, 33);
        graphics.drawString(this.a.b[this.d], 216, this.g - 2, 33);
        this.a.l();
        for (by = 0; by < 4; by = (byte)(by + 1)) {
            graphics.drawString(String.valueOf(this.a.x[by]), 110, 235 + by * 21, 24);
            graphics.drawString(String.valueOf(this.a.y[by]), 138, 235 + by * 21, 20);
        }
        this.H(graphics);
        for (by = 0; by < this.a.J; by = (byte)(by + 1)) {
            this.b(graphics, (byte)0, by);
        }
        for (by = 0; by < this.a.K; by = (byte)(by + 1)) {
            this.b(graphics, (byte)1, by);
        }
        if (this.u) {
            this.L(graphics);
        }
        if (this.v) {
            this.K(graphics);
        }
        if (this.w) {
            this.J(graphics);
        }
    }

    private final void J(Graphics graphics) {
        if (this.a.j[1][0] > 1) {
            int n = this.a.j[1][0] - 1;
            int n2 = this.a.k[1][0] - 1;
            if (n2 < 0) {
                n2 = 0;
            }
            if (this.a[7] == null) {
                this.a[7] = this.a(105);
            }
            graphics.drawImage(this.a[7], 16 * (n - this.s), 16 + 16 * n2, 20);
        }
    }

    private final void K(Graphics graphics) {
        if (this.a.j[0][0] < 13) {
            int n = this.a.j[0][0] + 1;
            int n2 = this.a.k[0][0] - 1;
            if (n2 < 0) {
                n2 = 0;
            }
            if (this.a[7] == null) {
                this.a[7] = this.a(105);
            }
            graphics.drawImage(this.a[7], 16 * (n - this.s), 20 + 16 * n2, 20);
        }
    }

    public final void a(byte by, byte by2, byte by3, byte by4, boolean bl) {
        this.e = (short)(16 * by);
        this.f = (short)(16 * by2);
        this.g = (short)(16 * by3);
        this.h = (short)(16 * by4);
        boolean bl2 = true;
        if (this.a.S >> 4 == 5 && bl || this.a.T >> 4 == 5 && !bl) {
            bl2 = false;
        }
        if (this.e == this.g && this.f < this.h) {
            this.X = (byte)8;
            this.a[3] = !bl2 ? Image.createImage((Image)this.a[4], (int)0, (int)0, (int)16, (int)16, (int)5) : Image.createImage((Image)this.a[4], (int)16, (int)0, (int)16, (int)16, (int)5);
        } else if (this.e == this.g && this.f > this.h) {
            this.X = (byte)2;
            this.a[3] = !bl2 ? Image.createImage((Image)this.a[4], (int)0, (int)0, (int)16, (int)16, (int)6) : Image.createImage((Image)this.a[4], (int)16, (int)0, (int)16, (int)16, (int)6);
        } else if (this.e < this.g && this.f == this.h) {
            this.X = (byte)6;
            this.a[3] = !bl2 ? Image.createImage((Image)this.a[4], (int)0, (int)0, (int)16, (int)16, (int)0) : Image.createImage((Image)this.a[4], (int)16, (int)0, (int)16, (int)16, (int)0);
        } else if (this.e > this.g && this.f == this.h) {
            this.X = (byte)4;
            this.a[3] = !bl2 ? Image.createImage((Image)this.a[4], (int)0, (int)0, (int)16, (int)16, (int)2) : Image.createImage((Image)this.a[4], (int)16, (int)0, (int)16, (int)16, (int)2);
        }
        this.u = true;
    }

    private final void L(Graphics graphics) {
        switch (this.X) {
            case 2: {
                if (this.f != this.h) {
                    this.f = (short)(this.f - 8);
                    break;
                }
                this.u = false;
                break;
            }
            case 4: {
                if (this.e != this.g) {
                    this.e = (short)(this.e - 8);
                    break;
                }
                this.u = false;
                break;
            }
            case 6: {
                if (this.e != this.g) {
                    this.e = (short)(this.e + 8);
                    break;
                }
                this.u = false;
                break;
            }
            case 8: {
                if (this.f != this.h) {
                    this.f = (short)(this.f + 8);
                    break;
                }
                this.u = false;
            }
        }
        if (this.u) {
            graphics.drawImage(this.a[3], this.e - this.s * 16, this.f + 32, 20);
        }
    }

    private final void b(Graphics graphics, byte by, byte by2) {
        if (this.a.b[by][by2]) {
            int n = this.a.j[by][by2] - this.s;
            if (n >= 0 && n < 15) {
                this.a(graphics, by, by2, n);
                if (this.a.a[by][by2]) {
                    this.a.a[by][by2] = false;
                    graphics.drawImage(this.a[2], n * 16, 32 + this.a.k[by][by2] * 16, 20);
                }
            }
            this.a(graphics, by, by2);
        }
    }

    private final void a(Graphics graphics, byte by, byte by2, int n) {
        switch (this.a.m[by][by2]) {
            case 0: {
                graphics.drawRegion(this.a[9], (this.a.l[by][by2] + 4) * 16, by * 16, 16, 16, 0, n * 16, 32 + this.a.k[by][by2] * 16, 0);
                return;
            }
            case 1: {
                graphics.drawRegion(this.a[9], (this.a.l[by][by2] + 8) * 16, by * 16, 16, 16, 0, n * 16, 32 + this.a.k[by][by2] * 16, 0);
                return;
            }
            case 2: {
                graphics.drawRegion(this.a[9], this.a.l[by][by2] * 16, by * 16, 16, 16, 2, n * 16, 32 + this.a.k[by][by2] * 16, 0);
                return;
            }
            case 3: {
                graphics.drawRegion(this.a[9], this.a.l[by][by2] * 16, by * 16, 16, 16, 0, n * 16, 32 + this.a.k[by][by2] * 16, 0);
            }
        }
    }

    private final void M(Graphics graphics) {
        int n;
        int n2;
        for (n2 = 0; n2 < 5; n2 = (int)((byte)(n2 + 1))) {
            for (n = 0; n < 4; n = (int)((byte)(n + 1))) {
                graphics.drawRegion(this.a[0], this.Y * 64, 0, 64, 64, 0, n * 64, n2 * 64, 0);
            }
        }
        if (this.P) {
            for (n2 = 0; n2 < 15; n2 = (int)((byte)(n2 + 1))) {
                graphics.drawRegion(this.a[8], 0, 0, 16, 16, 0, n2 * 16, 16, 20);
                graphics.drawRegion(this.a[8], 0, 0, 16, 16, 0, n2 * 16, 144, 20);
            }
            for (n2 = 0; n2 < 15; n2 = (int)((byte)(n2 + 1))) {
                for (n = 0; n < 7; n = (int)((byte)(n + 1))) {
                    byte by = (byte)(this.a.h[n][n2 + this.s] & 0x32);
                    byte by2 = this.b(by);
                    if (by2 <= -1) continue;
                    graphics.drawRegion(this.a[8], by2 * 16, 0, 16, 16, 0, n2 * 16, n * 16 + 32, 20);
                }
            }
        }
    }

    private final byte b(byte by) {
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
        graphics.drawImage(this.f, 0, 0, 20);
        graphics.drawImage(this.d, this.f >> 1, 60, 17);
        graphics.drawRegion(this.a[1], 32, 0, 32, 39, 0, 82, 98, 17);
        graphics.drawRegion(this.a[1], 0, 0, 32, 39, 0, 158, 98, 17);
        graphics.drawRegion(this.a[4], 0, this.F * 15, 27, 15, 0, 63, 158, 17);
        graphics.drawRegion(this.a[4], 0, 45 - this.F * 15, 27, 15, 0, 134, 158, 17);
        graphics.setColor(0xFFFFFF);
        if (this.I) {
            if (this.F == 0) {
                graphics.drawString(String.valueOf(this.m), 170, 158, 17);
            } else {
                graphics.drawString(String.valueOf(this.n), 96, 158, 17);
            }
            for (byte by = 0; by < 4; by = (byte)(by + 1)) {
                if (this.U == by) {
                    graphics.setColor(16275794);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                graphics.drawString(String.valueOf(this.c[by]), 75 - 8 * by + 70 * this.F + 30, 158, 20);
            }
            graphics.drawImage(this.a[0], 77 - 8 * this.U + 70 * this.F + 30, 155, 20);
        } else {
            graphics.drawString(String.valueOf(this.m), 170, 158, 17);
            graphics.drawString(String.valueOf(this.n), 96, 158, 17);
        }
        this.d(graphics);
        if (this.E) {
            this.a(graphics, this.I);
        }
        this.b(graphics, c.a[0][1]);
        if (this.G) {
            this.a(graphics, "\u8be5\u57ce\u5df2\u7ecf\u65e0\u4eba\u628a\u5b88!");
            return;
        }
        if (this.O) {
            this.a(graphics, "\u8bf7\u4efb\u547d\u65b0\u592a\u5b88");
        }
    }

    private final void O(Graphics graphics) {
        this.P(graphics);
        this.R(graphics);
    }

    private final void P(Graphics graphics) {
        for (int n = 0; n < 19; n = (int)((byte)(n + 1))) {
            for (int n2 = 0; n2 < 15; n2 = (int)((byte)(n2 + 1))) {
                int n3 = this.a.g[n + this.g][n2 + this.f] & 0xF;
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
                        graphics.drawRegion(this.a[4], n3 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
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
                graphics.drawRegion(this.a[4], n3 * 16, 0, 16, 16, 0, n2 * 16, n * 16, 20);
            }
        }
    }

    private final boolean a(int n, int n2) {
        return n >= this.f && n - this.f < 15;
    }

    private final boolean b(int n, int n2) {
        return (this.a.g[n2][n] & 0xF) != 2;
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
        if (this.a[3] == null) {
            this.a[3] = this.a(15);
        }
        for (int n = 0; n < 8; n = (int)((byte)(n + 1))) {
            graphics.drawRegion(this.a[3], n * 20, 0, 20, 20, 0, 50 + n % 2 * 70, 146 + n / 2 * 25, 0);
        }
        graphics.drawImage(this.a[9], 55, 68, 20);
        graphics.drawRect(55, 68, this.a[9].getWidth(), this.a[9].getHeight());
        graphics.setColor(0xFFFFFF);
        graphics.drawString(this.a.b[this.d], 135, 75, 20);
        graphics.drawString("LV. " + String.valueOf(this.a.o[this.d]), 150, 100, 20);
        graphics.drawString(String.valueOf(this.a.s[this.d]), 75, 146, 20);
        graphics.drawString(String.valueOf(this.a.t[this.d]), 145, 146, 20);
        graphics.drawString(String.valueOf(this.a.r[this.d]), 75, 171, 20);
        graphics.drawString(String.valueOf(this.a.u[this.d]), 145, 171, 20);
        graphics.drawString(String.valueOf(this.a.k[this.d]), 75, 196, 20);
        graphics.drawString(String.valueOf(this.a.l[this.d]), 145, 196, 20);
        graphics.drawString(this.a.c[this.a.w[this.d]], 75, 221, 20);
        graphics.drawString(this.a.c[this.a.x[this.d]], 145, 221, 20);
    }

    private final void Q(Graphics graphics) {
        if (this.a[7] == null) {
            this.a[7] = this.a(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(38, 57, 164, 160);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 57, 164, 160);
        graphics.drawLine(38, 170, 202, 170);
        graphics.drawImage(this.a[7], this.f >> 1, 50, 17);
        graphics.setColor(0xFFFF00);
        graphics.drawString(c.c[this.n], this.f >> 1, 175, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, c.o[this.n], 25, 70, 170, 100);
        if (this.n < this.o - 1) {
            graphics.drawString(c.c[this.n + 1], 190, 190, 24);
            graphics.drawImage(this.a, 195, 180, 20);
        }
        if (this.n > 0) {
            graphics.drawString(c.c[this.n - 1], 50, 190, 20);
            graphics.drawRegion(this.a, 0, 0, 6, 8, 2, 45, 180, 24);
        }
        this.a = "\u8bf7\u9009\u62e9\u4f7f\u7528\u8ba1\u7b56";
    }

    public final void b(Graphics graphics) {
        if (this.h == null) {
            this.h = this.a(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(38, 57, 164, 160);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 57, 164, 160);
        graphics.drawLine(38, 170, 202, 170);
        graphics.drawImage(this.h, this.f >> 1, 50, 17);
        graphics.setColor(0xFFFF00);
        graphics.drawString(c.i[this.n], this.f >> 1, 175, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, c.p[this.n], 20, 70, 180, 100);
        graphics.drawString("\u5269\u4f59\u8ba1\u7b56\u70b9:" + String.valueOf(this.a.N), this.f >> 1, 120, 17);
        if (this.n < 5) {
            graphics.drawString(c.i[this.n + 1], 190, 190, 24);
            graphics.drawImage(this.a, 195, 180, 20);
        }
        if (this.n > 0) {
            graphics.drawString(c.i[this.n - 1], 50, 190, 20);
            graphics.drawRegion(this.a, 0, 0, 6, 8, 2, 45, 180, 24);
        }
    }

    public final void c(Graphics graphics) {
        int n;
        int n2;
        if (this.a[8] == null) {
            this.a[8] = this.a(11);
        }
        graphics.setColor(10656072);
        graphics.fillRect(38, 57, 164, 120);
        graphics.setColor(0x996600);
        graphics.drawRect(38, 57, 164, 120);
        graphics.drawImage(this.a[8], this.f >> 1, 50, 17);
        for (n2 = 0; n2 <= this.a.p; n2 = (int)((byte)(n2 + 1))) {
            n = n2 % 3;
            int n3 = n2 / 3;
            if (n2 == this.a.p) {
                if (this.a.v[this.a.a] == 100) continue;
                graphics.setColor(16275794);
                graphics.drawString("\u4e0b\u91ce", 50 + 50 * n, 63 + 40 * n3, 20);
                continue;
            }
            graphics.setColor(0);
            graphics.drawString(this.a.a[this.a.F[n2]], 50 + 50 * n, 63 + 40 * n3, 20);
        }
        n2 = this.F % 3;
        n = this.F / 3;
        graphics.drawImage(this.a, 42 + 50 * n2, 67 + 40 * n, 0);
    }

    private final void R(Graphics graphics) {
        try {
            int n;
            byte by;
            for (by = 0; by < this.a.u; by = (byte)(by + 1)) {
                if (this.a.M[by] != 0 && this.a.M[by] <= 3 || !this.a((int)this.a.I[by], (int)this.a.J[by]) || !this.b(this.a.I[by], (int)this.a.J[by])) continue;
                n = this.a.k[this.a.v[by]] / 100;
                graphics.drawRegion(this.a[5], n * 15, (this.a.p[this.a.v[by]] - 1) * 15, 16, 16, 0, (this.a.I[by] - this.f) * 16, (this.a.J[by] - this.g) * 16, 20);
            }
            for (by = 0; by < this.a.v; by = (byte)(by + 1)) {
                if (this.a.N[by] != 0 && this.a.N[by] <= 3 || !this.a((int)this.a.K[by], (int)this.a.L[by]) || !this.b(this.a.K[by], (int)this.a.L[by])) continue;
                n = this.a.k[this.a.w[by]] / 100;
                graphics.drawRegion(this.a[5], n * 15, (this.a.p[this.a.w[by]] + 2) * 15, 16, 16, 0, (this.a.K[by] - this.f) * 16, (this.a.L[by] - this.g) * 16, 20);
            }
            if (this.a.f) {
                if (!this.e) {
                    graphics.drawImage(this.a[0], this.a * 16 + 8, this.b * 16 + 8, 20);
                } else {
                    this.a(graphics, (this.h - this.f) * 16, (this.i - this.g) * 16);
                }
            }
            graphics.drawRegion(this.a[6], 0, 0, 240, 22, 0, 0, this.g, 36);
            if (this.i) {
                this.a = "";
                graphics.drawRegion(this.a[6], 0, 22, 240, 22, 0, 0, this.g, 36);
                graphics.setColor(0xFFFF00);
                graphics.drawRect(14 + 36 * this.p, this.g - 18, 31, 16);
            }
            graphics.setColor(0xFFFFFF);
            if (this.a.f) {
                graphics.drawString("\u673a\u52a8\u529b:" + String.valueOf(this.a.w), this.f, 5, 24);
                graphics.drawString("\u7cae\u98df: " + String.valueOf(this.a.h), this.f, 25, 24);
                graphics.drawString("\u91d1\u5b50: " + String.valueOf(this.a.g), this.f, 45, 24);
            } else {
                graphics.drawString("\u673a\u52a8\u529b:" + String.valueOf(this.a.x), this.f, 5, 24);
                graphics.drawString("\u7cae\u98df: " + String.valueOf(this.a.j), this.f, 25, 24);
                graphics.drawString("\u91d1\u5b50: " + String.valueOf(this.a.i), this.f, 45, 24);
            }
            String string = this.a.E > 30 ? c.a(30) : c.a(this.a.E);
            graphics.drawString(string, 3, 3, 20);
            if (this.m) {
                this.a(graphics);
            }
            if (this.j) {
                this.Q(graphics);
            }
            if (this.n) {
                graphics.setColor(10656072);
                graphics.fillRect(36, 57, 167, 140);
                graphics.drawImage(this.a[9], this.f >> 1, 60, 17);
                graphics.setColor(0x996600);
                graphics.drawRect(36, 57, 167, 140);
                graphics.drawImage(this.a[7], this.f >> 1, 50, 17);
                graphics.setColor(0xFFFFFF);
                graphics.drawString(this.a(100), this.f >> 1, 156, 17);
                this.a = (byte)(this.h - this.f);
                this.b = (byte)(this.i - this.g);
                this.a = "\u4f7f\u7528\u8ba1\u7b56: " + c.c[this.n];
            }
            if (this.o) {
                this.c(graphics);
            }
            if (this.r > 0) {
                this.a(graphics, this.a((int)this.r));
            }
            if (this.q || this.r) {
                this.a(graphics, this.b, this.J);
            } else {
                this.a[1] = null;
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
        if (this.a.a < 14) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, this.f, this.g);
            graphics.drawImage(this.a[0], this.f >> 1, 50, 17);
            graphics.setColor(0x996600);
            graphics.drawRect(38, 50, this.a[0].getWidth(), this.a[0].getHeight());
            graphics.drawImage(this.a[1], this.f >> 1, 41, 17);
            graphics.setColor(0xFFFFFF);
            this.a(graphics, this.d, 40, 180, 170, 140);
            return;
        }
        this.a(graphics, this.d);
    }

    public final void d() {
        this.a("AI\u4e8b\u4ef6\u7c7b\u578b:" + this.a.a);
        switch (this.a.a) {
            case 1: {
                this.a[0] = this.a(120);
                this.d = this.a.b[this.a.b[this.a.g]] + " \u519b\u653b\u6253 " + this.a.a[this.a.h] + "...";
                return;
            }
            case 2: {
                this.a[0] = this.a(121);
                this.d = this.a.b[this.a.b[this.a.g]] + " \u5360\u9886\u7a7a\u57ce " + this.a.a[this.a.h] + "!";
                return;
            }
            case 3: {
                this.a[0] = this.a(120);
                this.d = this.a.b[0] + " \u519b\u653b\u6253 " + this.a.a[0] + "...";
                return;
            }
            case 4: {
                this.a[0] = this.a(122);
                this.d = this.a.b[this.a.b[this.a.g]] + " \u519b\u653b\u5360\u4e86 " + this.a.a[this.a.h] + "!";
                return;
            }
            case 5: {
                this.a[0] = this.a(123);
                this.d = this.a.b[this.a.b[this.a.g]] + " \u519b\u653b\u6253 " + this.a.a[this.a.h] + "\u5931\u8d25\u4e86!";
                return;
            }
            case 6: {
                this.a[0] = this.a(124);
                this.d = " \u53d1\u751f\u65f1\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                for (byte by = 0; by < this.a.p; by = (byte)(by + 1)) {
                    this.d = this.d + " " + this.a.a[this.a.F[by]];
                }
                return;
            }
            case 7: {
                this.a[0] = this.a(125);
                this.d = "\u53d1\u751f\u6c34\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                for (byte by = 0; by < this.a.p; by = (byte)(by + 1)) {
                    this.d = this.d + " " + this.a.a[this.a.F[by]];
                }
                return;
            }
            case 8: {
                this.a[0] = this.a(126);
                this.d = "\u53d1\u751f\u8757\u707e!\u6ce2\u53ca\u57ce\u5e02:";
                for (byte by = 0; by < this.a.p; by = (byte)(by + 1)) {
                    this.d = this.d + " " + this.a.a[this.a.F[by]];
                }
                return;
            }
            case 9: {
                this.a[0] = this.a(127);
                this.d = "\u53d1\u751f\u761f\u75ab!\u6ce2\u53ca\u57ce\u5e02:";
                for (byte by = 0; by < this.a.p; by = (byte)(by + 1)) {
                    this.d = this.d + " " + this.a.a[this.a.F[by]];
                }
                return;
            }
            case 10: {
                this.a[0] = this.a(128);
                this.d = "\u6536\u91d1\u7684\u5b63\u8282\u5230\u4e86!";
                return;
            }
            case 11: {
                this.a[0] = this.a(129);
                this.d = "\u6536\u7cae\u7684\u5b63\u8282\u5230\u4e86!";
                return;
            }
            case 12: {
                this.a[0] = this.a(130);
                this.d = this.a.b[this.a.a] + "\u8ddf\u968f " + this.a.b[this.a.b] + " \u52a0\u5165\u6211\u519b\u4e3a\u541b\u4e3b\u6548\u529b!";
                return;
            }
            case 13: {
                this.a[0] = this.a(136);
                this.d = "\u767e\u59d3\u53d1\u751f\u9a9a\u4e71,\u6ce2\u53ca\u57ce\u5e02:";
                for (byte by = 0; by < this.a.p; by = (byte)(by + 1)) {
                    this.d = this.d + " " + this.a.a[this.a.F[by]];
                }
                return;
            }
            case 14: {
                this.d = this.a.b[this.a.a[this.a.f]] + "\u3000\u540c\u76df\u89e3\u9664!";
                return;
            }
            case 15: {
                return;
            }
            case 16: {
                this.d = this.a.b[this.a.b] + "\u6b63\u4e0e\u654c\u65b9\u4f1a\u8c08\u4e2d...";
                return;
            }
            case 17: {
                this.d = "\u7cdf\u4e86," + this.a.b[this.a.b] + "\u6295\u5954\u4e86" + this.a.b[this.a.b[this.a.g]];
                return;
            }
            case 18: {
                this.d = this.a.b[this.a.b] + " \u52bf\u529b\u706d\u4ea1\u4e86!";
                return;
            }
            case 19: {
                this.d = this.a.b[this.a.b] + "\u6b7b\u4ea1,\u65b0\u541b\u4e3b " + this.a.b[this.a.a[this.a.f]] + " \u7ee7\u4f4d!";
            }
        }
    }

    public final void a(byte by, short s, short s2) {
        this.D = true;
        switch (by) {
            case 2: {
                this.d = this.a.b[s] + "\u3000\u541b\u4e3b\u6b7b\u4ea1!";
                return;
            }
            case 5: {
                this.d = this.a.b[s] + " \u52bf\u529b\u706d\u4ea1\u4e86!";
                return;
            }
            case 6: {
                this.d = this.a.b[s] + "\u6b7b\u4ea1,\u65b0\u541b\u4e3b " + this.a.b[s2] + " \u7ee7\u4f4d!";
            }
        }
    }

    public final void keyReleased(int n) {
        this.B = false;
    }

    public final void keyRepeated(int n) {
        this.B = true;
        switch (n) {
            case -2: 
            case 56: {
                this.a.getClass();
                this.a = (byte)2;
                return;
            }
            case -1: 
            case 50: {
                this.a.getClass();
                this.a = 1;
                return;
            }
            case -3: 
            case 52: {
                this.a.getClass();
                this.a = (byte)4;
                return;
            }
            case -4: 
            case 54: {
                this.a.getClass();
                this.a = (byte)8;
            }
        }
    }

    public final void keyPressed(int n) {
        switch (n) {
            case -2: 
            case 56: {
                this.a.getClass();
                this.a = (byte)2;
                return;
            }
            case -1: 
            case 50: {
                this.a.getClass();
                this.a = 1;
                return;
            }
            case -3: 
            case 52: {
                this.a.getClass();
                this.a = (byte)4;
                return;
            }
            case -4: 
            case 54: {
                this.a.getClass();
                this.a = (byte)8;
                return;
            }
            case -5: 
            case 53: {
                this.a.getClass();
                this.a = (byte)16;
                return;
            }
            case -7: 
            case 55: {
                this.a.getClass();
                this.a = (byte)32;
            }
        }
    }

    public final void e() {
        switch (this.E) {
            case 4: {
                this.j();
                return;
            }
            case 5: {
                if (this.G && this.a > 0) {
                    this.a.aY();
                    return;
                }
                this.R();
                return;
            }
            case 6: {
                this.W();
                return;
            }
            case 7: {
                this.V();
                return;
            }
            case 8: {
                this.U();
                return;
            }
            case 9: {
                this.v();
                return;
            }
            case 10: {
                if (this.a <= 0) break;
                this.a.aX();
                return;
            }
            case 11: {
                if (this.a > 0) {
                    this.a.aX();
                }
            }
            case 12: {
                this.u();
                return;
            }
            case 13: {
                this.T();
                return;
            }
            case 14: {
                this.t();
                return;
            }
            case 15: {
                this.S();
                return;
            }
            case 16: {
                this.Q();
                return;
            }
            case 17: {
                this.w();
                return;
            }
            case 18: {
                this.P();
                return;
            }
            case 23: {
                this.s();
                return;
            }
            case 24: {
                this.y();
            }
        }
    }

    private final void r() {
        switch (this.a) {
            case 32: {
                this.m = 0;
                this.a((byte)1);
                return;
            }
            case 1: {
                if (this.m <= 0) break;
                --this.m;
                return;
            }
            case 2: {
                if (this.m >= this.l - this.k) break;
                ++this.m;
                return;
            }
            default: {
                return;
            }
        }
    }

    private final void s() {
        if (this.T || this.S) {
            this.T = false;
            this.a.c = 1;
            this.a.a();
        }
        if (this.Z < 7) {
            this.a = this.e = this.a.a((int)this.Z);
            this.Z = (byte)(this.Z + 1);
            return;
        }
        this.T = true;
    }

    private final void t() {
        switch (this.a) {
            case 1: {
                if (this.I) {
                    this.a(this.c, this.U);
                    if (!this.a((int)this.e[this.F], this.c)) break;
                    this.b(this.c, this.U);
                    return;
                }
                if (!this.E) {
                    this.F = 0;
                    return;
                }
                if (!this.E) break;
                this.E = false;
                this.I = 0;
                return;
            }
            case 2: {
                if (this.I) {
                    this.b(this.c, this.U);
                    if (!this.a((int)this.e[this.F], this.c)) break;
                    this.a(this.c, this.U);
                    return;
                }
                if (this.E) break;
                this.E = true;
                return;
            }
            case 4: {
                if (this.I) {
                    this.U = this.c(this.U);
                    return;
                }
                if (!(this.E || this.G || this.O)) {
                    this.F = 0;
                    return;
                }
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (this.I) {
                    this.U = this.d(this.U);
                    return;
                }
                if (!(this.E || this.G || this.O)) {
                    this.F = 1;
                    return;
                }
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (this.E) {
                    if (this.I == 0) {
                        this.a.a(this.n, this.m, 0);
                        return;
                    }
                    this.a.a(this.n, this.m, 1);
                    return;
                }
                if (!this.E) {
                    if (this.I) {
                        if (this.F == 0) {
                            this.n = this.a(this.c);
                        } else {
                            this.m = this.a(this.c);
                        }
                    } else {
                        this.c = this.F == 0 ? this.a(this.n) : this.a(this.m);
                        this.U = 0;
                    }
                    this.I = !this.I;
                    return;
                }
                if (!this.E) break;
                this.E = false;
                if (this.I == 0) {
                    if (this.F == 0) {
                        this.n = this.a(this.c);
                    } else {
                        this.m = this.a(this.c);
                    }
                    this.a.a(this.n, this.m, 0);
                    return;
                }
                this.a.a(this.n, this.m, 0);
            }
        }
    }

    private final void u() {
        if (this.K) {
            this.K = false;
            this.E = false;
            this.I = true;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.I) {
                    this.a(this.c, this.U);
                    int n = this.a.d[this.a.g];
                    if (n > 100) {
                        n = 100;
                    }
                    if (this.a(n, this.c)) {
                        this.b(this.c, this.U);
                    }
                    return;
                }
                this.E = false;
                return;
            }
            case 2: {
                if (this.I) {
                    this.b(this.c, this.U);
                    int n = this.a.d[this.a.g];
                    if (n > 100) {
                        n = 100;
                    }
                    if (this.a(n, this.c)) {
                        this.a(this.c, this.U);
                    }
                    return;
                }
                this.E = true;
                return;
            }
            case 4: {
                if (this.I) {
                    this.U = this.c(this.U);
                    return;
                }
                if (!this.E) {
                    this.F = 0;
                    return;
                }
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (this.I) {
                    this.U = this.d(this.U);
                    return;
                }
                if (!this.E) {
                    if (this.a.j[this.a.g] <= 0) break;
                    this.F = 1;
                    this.l = 0;
                    this.c = this.a(this.l);
                    return;
                }
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (this.F == 0) {
                    this.I = !this.I;
                    this.U = 0;
                    this.l = this.a(this.c);
                    this.a("gold:" + this.l);
                }
                if (!this.E) break;
                if (this.K) {
                    this.K = false;
                    this.E = false;
                    return;
                }
                if (this.I == 0) {
                    if (this.F == 0) {
                        if (this.l == 0) {
                            this.K = true;
                            return;
                        }
                        this.K = false;
                        this.a.a(this.l, 0);
                        this.a("go here");
                        return;
                    }
                    this.a.a((short)200, 0);
                    return;
                }
                this.a.a((short)0, 1);
                return;
            }
            case 32: {
                this.a.a((short)0, 1);
            }
        }
    }

    private final void v() {
        if (this.K && this.a > 0) {
            this.K = false;
            this.E = false;
            this.F = 0;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.F == 3) {
                    this.E = false;
                }
                if (!this.I && !this.E) {
                    if (this.F > 0) {
                        this.F = (byte)(this.F - 1);
                    }
                    this.c = this.a(this.d[this.F]);
                    return;
                }
                if (!this.I) break;
                this.a(this.c, this.U);
                if (!this.a((int)this.e[this.F], this.c)) break;
                this.b(this.c, this.U);
                return;
            }
            case 2: {
                if (!this.I && !this.E) {
                    if (this.F < 3) {
                        this.F = (byte)(this.F + 1);
                    }
                    if (this.F == 3) {
                        this.E = true;
                        return;
                    }
                    this.c = this.a(this.d[this.F]);
                    return;
                }
                if (!this.I) break;
                this.b(this.c, this.U);
                if (!this.a((int)this.e[this.F], this.c)) break;
                this.a(this.c, this.U);
                return;
            }
            case 4: {
                if (!this.E && this.U < 3) {
                    this.U = (byte)(this.U + 1);
                }
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (!this.E && this.U > 0) {
                    this.U = (byte)(this.U - 1);
                }
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (!this.E) {
                    if (this.I) {
                        this.d[this.F] = this.a(this.c);
                    } else {
                        this.c = this.a(this.d[this.F]);
                    }
                    this.I = !this.I;
                    return;
                }
                if (this.I == 0) {
                    if (!this.c()) {
                        this.a.a(0, this.d);
                        return;
                    }
                    this.K = true;
                    return;
                }
                this.a.a(1, this.d);
            }
        }
    }

    private final boolean c() {
        for (int n = 0; n < 3; n = (int)((byte)(n + 1))) {
            if (this.d[n] <= 0) continue;
            return false;
        }
        return true;
    }

    private final void w() {
        switch (this.a) {
            case 1: {
                if (this.E || this.F <= 0) break;
                this.F = (byte)(this.F - 1);
                return;
            }
            case 2: {
                if (this.E || this.F >= 3) break;
                this.F = (byte)(this.F + 1);
                return;
            }
            case 4: {
                if (!this.E && this.K > 0) {
                    this.K = (byte)(this.K - 1);
                    this.F = 0;
                    return;
                }
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (!this.E && this.K < 3) {
                    this.K = (byte)(this.K + 1);
                    this.F = 0;
                    return;
                }
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (!this.E) {
                    this.E = true;
                    return;
                }
                if (this.I == 0) {
                    this.x();
                    return;
                }
                if (this.A) {
                    this.a.c = (byte)2;
                    return;
                }
                this.a.n(1);
                return;
            }
            case 32: {
                if (this.A) {
                    this.a.c = (byte)2;
                    return;
                }
                this.a.n(1);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void x() {
        byte by = this.a(this.K, this.F);
        if (by == 15 || by == 23) {
            if (this.a.s[this.a.a] > 95) {
                if (this.a(by)) {
                    if (!this.A) {
                        this.a.l[this.a.g] = 1;
                        this.a.n(0);
                        return;
                    }
                    this.a.m[this.a.t] = 1;
                    this.a.c = 1;
                    return;
                }
                if (this.A) {
                    this.a.c = (byte)3;
                    return;
                }
                this.a.n(2);
                return;
            }
            if (this.A) {
                this.a.c = (byte)4;
                return;
            }
            this.a.n(3);
            return;
        }
        if (by == 22) {
            if (this.a.s[this.a.a] > 90) {
                if (this.a(by)) {
                    if (!this.A) {
                        this.a.l[this.a.g] = 1;
                        this.a.n(0);
                        return;
                    }
                    this.a.m[this.a.t] = 1;
                    if (!this.A) return;
                    this.a.c = 1;
                    return;
                }
                if (this.A) {
                    this.a.c = (byte)3;
                    return;
                }
                this.a.n(2);
                return;
            }
            if (this.A) {
                this.a.c = (byte)4;
                return;
            }
            this.a.n(3);
            return;
        }
        if (by == 30) {
            if (this.a.u[this.a.a] > 90) {
                if (this.a(by)) {
                    if (!this.A) {
                        this.a.l[this.a.g] = 1;
                        this.a.n(0);
                        return;
                    }
                    this.a.m[this.a.t] = 1;
                    if (!this.A) return;
                    this.a.c = 1;
                    return;
                }
                if (this.A) {
                    this.a.c = (byte)3;
                    return;
                }
                this.a.n(2);
                return;
            }
            if (this.A) {
                this.a.c = (byte)4;
                return;
            }
            this.a.n(3);
            return;
        }
        if (this.a(by)) {
            if (this.A) {
                this.a.c = 1;
                return;
            }
            this.a.n(0);
            return;
        }
        if (this.A) {
            this.a.c = (byte)3;
            return;
        }
        this.a.n(2);
    }

    private final boolean a(byte by) {
        short s = this.a.q[by];
        if (this.A) {
            if (this.a.g < s) {
                return false;
            }
            this.a.g = (short)(this.a.g - s);
            if (this.K == 3) {
                this.a.x[this.c] = by;
            } else {
                this.a.w[this.c] = by;
            }
        } else {
            if (this.a.d[this.a.g] < s) {
                return false;
            }
            byte by2 = this.a.g;
            this.a.d[by2] = (short)(this.a.d[by2] - s);
            if (this.K == 3) {
                this.a.x[this.a.a] = by;
            } else {
                this.a.w[this.a.a] = by;
            }
        }
        return true;
    }

    private final void y() {
        switch (this.a) {
            case 1: {
                if (this.N <= 0) break;
                this.N = (byte)(this.N - 1);
                return;
            }
            case 2: {
                if (this.N >= 2) break;
                this.N = (byte)(this.N + 1);
                return;
            }
            case 4: {
                if (this.E) {
                    this.I = 0;
                    return;
                }
                byte by = this.N;
                this.a[by] = (byte)(this.a[by] - 1);
                if (this.a[this.N] >= 0) break;
                this.a[this.N] = 0;
                return;
            }
            case 8: {
                if (this.E) {
                    this.I = 1;
                    return;
                }
                byte by = this.N;
                this.a[by] = (byte)(this.a[by] + 1);
                if (this.N < 2 && this.a[this.N] > 1) {
                    this.a[this.N] = 1;
                }
                if (this.N != 2 || this.a[this.N] <= 4) break;
                this.a[this.N] = 4;
                return;
            }
            case 16: {
                if (!this.E) {
                    this.E = true;
                    return;
                }
                if (this.I == 0) {
                    this.c = this.a[2];
                    this.a = this.a[0] == 0;
                    if (this.a) {
                        if (this.b == 0) {
                            this.b(1);
                        } else {
                            this.b(this.c);
                        }
                    } else if (this.b > 0) {
                        this.b(this.c);
                        this.o();
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

    public final void f() {
        if (this.D) {
            this.D = false;
            return;
        }
        if (this.D > -1) {
            this.D = (byte)-1;
            this.a.c = 1;
            return;
        }
        if (this.A == 100) {
            this.A = 0;
            return;
        }
        if (this.A > 0) {
            this.u = this.A;
            return;
        }
        if (this.x) {
            switch (this.a) {
                case 4: {
                    if (this.x <= 0) break;
                    this.x = (byte)(this.x - 1);
                    return;
                }
                case 8: {
                    if (this.x >= 5) break;
                    this.x = (byte)(this.x + 1);
                    return;
                }
                case 16: {
                    if (this.x == 5 && this.a.v[this.c] == 100) {
                        this.d = c.a;
                        this.D = true;
                        return;
                    }
                    if (!this.z && this.x && this.x == 3) {
                        this.A = (byte)2;
                        this.z = true;
                        this.c = "\u826f\u79bd\u62e9\u6728,\u4f55\u4e0d\u8d81\u6b64\u53e6\u6295\u660e\u4e3b...";
                        return;
                    }
                    this.x = false;
                }
            }
        }
    }

    private final void d(int n) {
        int n2 = 4;
        if (this.a.W == 0) {
            n2 = 5;
        }
        switch (n) {
            case 2: {
                if (this.a.W <= 0) break;
                this.a.W = (byte)(this.a.W - 1);
                return;
            }
            case 4: {
                if (this.a.O[this.a.W] <= 0) break;
                byte by = this.a.W;
                this.a.O[by] = (byte)(this.a.O[by] - 1);
                return;
            }
            case 6: {
                if (this.a.O[this.a.W] >= n2 - 1) break;
                byte by = this.a.W;
                this.a.O[by] = (byte)(this.a.O[by] + 1);
                return;
            }
            case 8: {
                if (this.a.W >= 3) break;
                this.a.W = (byte)(this.a.W + 1);
            }
        }
    }

    private final void e(int n) {
        switch (n) {
            case 4: {
                if (this.W <= 0) break;
                this.W = (byte)(this.W - 1);
                return;
            }
            case 6: {
                if (this.W >= 3) break;
                this.W = (byte)(this.W + 1);
            }
        }
    }

    public final void g() {
        if (this.D) {
            this.D = false;
            if (this.t) {
                this.t = false;
                this.a.c = (byte)2;
                return;
            }
            this.a.V = 1;
            if (this.Q) {
                this.Q = false;
                this.a.c = 1;
            }
            this.a.O[0] = 1;
            return;
        }
        if (this.v) {
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.t != 2 || this.R) break;
                this.d(2);
                return;
            }
            case 2: {
                if (this.t != 2 || this.R) break;
                this.d(8);
                return;
            }
            case 4: {
                if (this.t == 1) {
                    this.e(4);
                    return;
                }
                if (this.a.V == 1) {
                    if (this.s <= 0) break;
                    this.s = (byte)(this.s - 1);
                    return;
                }
                if (this.R && this.n > 0) {
                    this.n = (byte)(this.n - 1);
                    return;
                }
                if (this.R) break;
                this.d(4);
                return;
            }
            case 8: {
                if (this.t == 1) {
                    this.e(6);
                    return;
                }
                if (this.a.V == 1) {
                    return;
                }
                if (this.R && this.n < 5) {
                    this.n = (byte)(this.n + 1);
                    return;
                }
                if (this.j) break;
                this.d(6);
                return;
            }
            case 16: {
                this.z();
                return;
            }
            case 32: {
                if (this.a.V == 2) {
                    this.a.V = 1;
                }
                if (!this.R) break;
                this.R = false;
                this.n = 0;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void z() {
        switch (this.t) {
            case 1: {
                this.t = (byte)(this.t + 1);
                this.g = null;
                this.f = null;
                this.d = null;
                return;
            }
            case 2: {
                if (this.R) {
                    this.R = false;
                    this.D = true;
                    if (this.a.N < this.a.a(this.n)) {
                        this.d = "\u6218\u672f\u80fd\u529b\u4e0d\u591f";
                        return;
                    }
                    if (this.a.a(this.n)) {
                        this.d = "\u8ba1\u7b56[" + c.i[this.n].substring(0, 2) + "] \u65bd\u7528\u6210\u529f\uff01";
                        if (this.n == 1) {
                            this.Q = true;
                            return;
                        }
                        if (this.n != 5) return;
                        this.v = true;
                        this.a.Y = (byte)40;
                        this.D = false;
                        this.a.W = 0;
                        this.a.O[0] = 0;
                        this.a.V = 1;
                        return;
                    } else {
                        this.d = "\u8ba1\u7b56[" + c.i[this.n].substring(0, 2) + "] \u65bd\u7528\u5931\u8d25\uff01";
                    }
                    return;
                }
                if (this.a.V == 1) {
                    this.a.V = (byte)2;
                    return;
                }
                if (this.a.O[0] == 4) {
                    if (this.a.S != 0) {
                        this.D = true;
                        this.d = "\u8ba1\u7b56\u4f7f\u7528\u4e2d!";
                        this.a.O[0] = 0;
                        return;
                    }
                    this.R = true;
                    this.n = 0;
                } else {
                    this.a.V = 1;
                }
                this.a.W = 0;
                return;
            }
        }
    }

    private final String a(int n) {
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
                string = this.a.b;
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
                this.a.c = 1;
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
                string = this.a.b;
            }
        }
        return string;
    }

    public final void h() {
        if (this.D) {
            this.D = false;
            return;
        }
        if (this.n) {
            this.n = false;
            this.i = true;
            this.k = false;
            return;
        }
        switch (this.r) {
            case 0: {
                break;
            }
            case 2: 
            case 3: {
                this.a.c = 1;
                this.r = 0;
                return;
            }
            case 4: {
                this.r = 0;
                break;
            }
            case 8: {
                this.r = 0;
                this.a.c = 1;
                return;
            }
            case 5: 
            case 6: {
                this.r = 0;
                this.a.C();
                this.a.c = (byte)4;
                break;
            }
            case 7: 
            case 9: 
            case 20: 
            case 21: {
                this.r = 0;
                this.k = false;
                this.l = true;
                if (this.a.c()) {
                    this.a = "\u64a4\u9000\u6b66\u5c06 " + this.a.b[this.a.a];
                }
                return;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: {
                this.a.c = 1;
            }
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: {
                this.r = 0;
                break;
            }
            case 100: {
                this.r = 0;
                this.a.c = 1;
                return;
            }
        }
        if (this.m) {
            this.m = false;
            this.a = "\u8bf7\u9009\u62e9\u8981\u4fa6\u5bdf\u7684\u6b66\u5c06";
            return;
        }
        switch (this.a) {
            case 1: {
                this.J();
                return;
            }
            case 2: {
                this.K();
                return;
            }
            case 4: {
                this.H();
                return;
            }
            case 8: {
                this.I();
                return;
            }
            case 16: {
                this.E();
                return;
            }
            case 32: {
                if (this.l) {
                    if (this.f) {
                        this.i = true;
                        this.k = false;
                        this.f = false;
                        this.l = false;
                        this.a = this.j;
                        this.b = this.k;
                        this.f = this.l;
                        this.g = this.m;
                        return;
                    }
                    if (this.e) {
                        if (!this.c) {
                            this.a.I[this.d] = this.j;
                            this.a.J[this.d] = this.k;
                            this.e = (byte)3;
                            this.h = this.j;
                            this.i = this.k;
                            this.a = (byte)(this.h - this.f);
                            this.b = (byte)(this.i - this.g);
                            return;
                        }
                        if (this.p) {
                            this.p = false;
                            this.h = this.j;
                            this.i = this.k;
                            this.a.w = this.q;
                            this.a.I[this.d] = this.h;
                            this.a.J[this.d] = this.i;
                            this.a = (byte)(this.h - this.f);
                            this.b = (byte)(this.i - this.g);
                            return;
                        }
                        this.l = false;
                        this.e = false;
                        this.i = true;
                        this.k = false;
                        byte[] byArray = this.a.g[this.k];
                        byte by = this.j;
                        byArray[by] = (byte)(byArray[by] | 0x40);
                        return;
                    }
                    if (this.g) {
                        this.j = false;
                        this.n = 0;
                        this.i = true;
                        this.k = false;
                        this.g = false;
                        this.l = false;
                        this.f = this.l;
                        this.g = this.m;
                        this.a = this.j;
                        this.b = this.k;
                        return;
                    }
                    if (this.h) {
                        this.h = false;
                        this.i = true;
                        this.k = false;
                        return;
                    }
                    if (this.o && !this.s) {
                        this.o = false;
                        this.l = false;
                        this.i = true;
                        this.k = false;
                    }
                    return;
                }
                if (!this.i) break;
                this.p = 0;
                this.i = false;
                this.k = true;
                this.a = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
            }
        }
    }

    private final void A() {
        if (this.a.r[this.c] == this.a.q[this.c]) {
            this.k = true;
            this.r = (byte)15;
            return;
        }
        if (this.a.g < 50) {
            this.k = true;
            this.r = (byte)16;
            return;
        }
        this.b = "\u662f\u5426\u5bf9\u6b66\u5c06\u8fdb\u884c\u6cbb\u7597?";
        this.r = true;
    }

    private final void B() {
        if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 1) {
            for (byte by = 0; by < this.a.u; by = (byte)(by + 1)) {
                if (this.a.I[by] != (byte)(this.a + this.f) || this.a.J[by] != (byte)(this.b + this.g) || this.a.M[by] != 0 && this.a.M[by] <= 3) continue;
                this.k = false;
                this.i = true;
                this.c = this.a.v[by];
                this.h = this.a.I[by];
                this.i = this.a.J[by];
                this.d = by;
            }
        }
    }

    private final void c(byte by) {
        switch (by) {
            case 0: {
                this.e = true;
                this.a = "\u6b66\u5c06\u79fb\u52a8";
                for (byte by2 = 0; by2 < this.a.u; by2 = (byte)(by2 + 1)) {
                    if (this.a.I[by2] != this.a + this.f || this.a.J[by2] != this.b + this.g) continue;
                    this.e = true;
                    this.c = this.a.v[by2];
                    this.h = (byte)(this.a + this.f);
                    this.i = (byte)(this.b + this.g);
                    this.d = by2;
                    this.j = this.h;
                    this.k = this.i;
                    this.q = this.a.w;
                    byte[] byArray = this.a.g[this.i];
                    byte by3 = this.h;
                    byArray[by3] = (byte)(byArray[by3] & 0x3F);
                    break;
                }
                return;
            }
            case 1: {
                this.g = true;
                this.j = true;
                this.j = (byte)this.a;
                this.k = (byte)this.b;
                this.l = this.f;
                this.m = this.g;
                this.o = this.a.c(this.c);
                return;
            }
            case 2: {
                if ((this.a.a(this.h, this.i) & 0x10) == 16) {
                    this.h = true;
                    this.a = "\u9009\u62e9\u6b32\u653b\u51fb\u654c\u5c06";
                    return;
                }
                this.a = "\u90bb\u8fd1\u65e0\u654c\u5c06!";
                this.k = true;
                this.l = false;
                return;
            }
            case 3: {
                this.f = true;
                this.j = (byte)this.a;
                this.k = (byte)this.b;
                this.l = this.f;
                this.m = this.g;
                this.a = "\u8bf7\u9009\u62e9\u8981\u4fa6\u5bdf\u7684\u6b66\u5c06";
                return;
            }
            case 4: {
                this.a.c(this.c);
                if (this.r == 18) {
                    this.o = false;
                    this.l = false;
                    this.k = true;
                    return;
                }
                this.a = "\u64a4\u9000\u6b66\u5c06 " + this.a.b[this.c];
                return;
            }
            case 5: {
                this.k = true;
                this.l = false;
                this.a = "\u6211\u519b\u5f85\u673a\u4e2d...";
                this.a.c = 1;
            }
        }
    }

    public final void i() {
        this.a("call doSelPos()");
        if (!this.d) {
            this.c = this.a.v[this.d];
            this.h = this.a.I[this.d];
            this.i = this.a.J[this.d];
            this.j = this.a.I[this.d];
            this.k = this.a.J[this.d];
            this.a = (byte)(this.h - this.f);
            this.b = (byte)(this.i - this.g);
            this.l = true;
            this.e = true;
            byte[] byArray = this.a.g[this.i];
            byte by = this.h;
            byArray[by] = (byte)(byArray[by] & 0x3F);
            this.e = (byte)3;
            this.d = true;
        }
        this.a = "\u521d\u59cb\u4f4d\u7f6e(\u654c:" + c.f[this.a.C] + c.g[this.a.D] + ")";
    }

    private final void C() {
        this.a.I[this.d] = this.h;
        this.a.J[this.d] = this.i;
        byte[] byArray = this.a.g[this.i];
        byte by = this.h;
        byArray[by] = (byte)(byArray[by] | 0x40);
        this.l = false;
        this.e = false;
        this.p = false;
        if (!this.c) {
            if (this.d == this.a.u - 1) {
                this.c = true;
                this.d = 0;
                this.k = true;
                this.e = (byte)3;
                this.a.c = 1;
                this.a = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
                return;
            }
            this.l = true;
            this.e = true;
            this.d = (byte)(this.d + 1);
            this.d = false;
            this.i();
            return;
        }
        if (this.b(this.a.g[this.i][this.h])) {
            this.b = "\u662f\u5426\u8fdb\u5165\u6751\u5e84?";
            this.q = true;
            return;
        }
        this.k = true;
        this.a = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
    }

    private final boolean b(byte by) {
        byte by2 = (byte)(by & 0xF);
        return by2 == 3 || by2 == 4 || by2 == 1;
    }

    private final void D() {
        if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 2) {
            for (byte by = 0; by < this.a.v; by = (byte)(by + 1)) {
                if (this.a.N[by] > 0 && this.a.N[by] <= 3 || this.a.K[by] != (byte)(this.a + this.f) || this.a.L[by] != (byte)(this.b + this.g)) continue;
                this.d = this.a.w[by];
                this.a[9] = null;
                this.a[9] = this.a(this.d);
                this.m = true;
                this.a = "\u6b66\u5c06\u4fe1\u606f";
                break;
            }
            return;
        }
        if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 1) {
            for (byte by = 0; by < this.a.u; by = (byte)(by + 1)) {
                if (this.a.I[by] != (byte)(this.a + this.f) || this.a.J[by] != (byte)(this.b + this.g)) continue;
                this.d = this.a.v[by];
                this.a[9] = null;
                this.a[9] = this.a(this.d);
                this.m = true;
                this.a = "\u6b66\u5c06\u4fe1\u606f";
                return;
            }
        }
    }

    private final void E() {
        if (this.r) {
            if (this.J == 0) {
                this.a.g = (short)(this.a.g - 50);
                short s = this.c;
                this.a.r[s] = (byte)(this.a.r[s] + this.a.g());
                if (this.a.r[this.c] > this.a.q[this.c] || this.a.r[this.c] < 0) {
                    this.a.r[this.c] = this.a.q[this.c];
                }
                this.r = (byte)17;
            } else {
                this.J = 0;
            }
            this.k = true;
            this.r = false;
            return;
        }
        if (this.q) {
            if (this.J == 0) {
                byte by = (byte)(this.a.g[this.i][this.h] & 0xF);
                if (by == 4) {
                    this.A();
                } else if (by == 1) {
                    this.a.c = (byte)5;
                } else if (by == 3) {
                    this.a.c = (byte)6;
                }
            } else {
                this.J = 0;
                this.k = true;
            }
            this.q = false;
            return;
        }
        if (this.k) {
            this.B();
            return;
        }
        if (this.i) {
            this.i = false;
            if (this.p == 0 && this.a.M[this.d] > 3) {
                this.r = (byte)19;
                this.k = true;
                return;
            }
            this.c(this.p);
            this.p = 0;
            this.l = true;
            return;
        }
        if (this.l) {
            if (this.e) {
                this.C();
                return;
            }
            if (this.f) {
                this.D();
                return;
            }
            if (this.h) {
                this.G();
                return;
            }
            if (this.g) {
                this.F();
                return;
            }
            if (this.o) {
                if (this.F == this.a.p) {
                    this.a.d((byte)0);
                } else {
                    this.a.d(this.a.F[this.F]);
                }
                this.F = 0;
                if (!this.s) {
                    this.l = false;
                    this.b = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
                    this.k = true;
                    return;
                }
                this.a = "\u64a4\u9000\u6b66\u5c06 " + this.a.b[this.a.a];
            }
        }
    }

    private final void F() {
        byte by = 0;
        if (this.j) {
            by = this.a.c(this.n);
            if (by > this.a.w) {
                this.a = "\u673a\u52a8\u529b\u4e0d\u8db3";
                this.j = false;
                this.g = false;
                this.n = 0;
                this.l = false;
                this.k = true;
                return;
            }
            this.j = false;
            this.j = (byte)this.a;
            this.k = (byte)this.b;
            this.l = this.f;
            this.m = this.g;
            this.a = "\u9009\u62e9\u8ba1\u7b56\u5b9e\u65bd\u5730\u70b9";
            return;
        }
        if (this.n != 11) {
            if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 2) {
                for (byte by2 = 0; by2 < this.a.v; by2 = (byte)(by2 + 1)) {
                    if (this.a.N[by2] > 0 && this.a.N[by2] <= 3 || this.a.K[by2] != (byte)(this.a + this.f) || this.a.L[by2] != (byte)(this.b + this.g)) continue;
                    if (this.a.a((int)this.d, (int)by2, this.n, true)) {
                        this.n = true;
                        this.a[9] = null;
                        this.a[9] = this.a(this.n + 33);
                        this.d = this.a.w[by2];
                        int n = this.a.a(this.c, this.d, this.n, this.a.j);
                        if (n > 0) {
                            this.a.a(n, this.n);
                            short s = this.a.k[this.d];
                            this.a.a(this.d, this.a.N, by2, this.d, n, true);
                            short s2 = this.c;
                            this.a.l[s2] = (short)(this.a.l[s2] + (s - this.a.k[this.d]));
                            this.a.b(this.c);
                        } else {
                            this.a.b = "\u8ba1\u7b56\u5b9e\u65bd\u5931\u8d25!";
                        }
                        this.a.w = (byte)(this.a.w - this.a.c(this.n));
                        this.g = false;
                        this.l = false;
                        this.k = true;
                        continue;
                    }
                    this.a = "\u6b64\u5730\u65e0\u6cd5\u4f7f\u7528\u8be5\u8ba1\u7b56";
                    this.g = false;
                    this.l = false;
                    this.k = true;
                    this.n = 0;
                }
                return;
            }
        } else if (this.a.g[this.b + this.g][this.a + this.f] == 5 && this.a.k[this.c] >= 100) {
            this.n = true;
            this.a.w = (byte)(this.a.w - this.a.c(this.n));
            this.a.b = "\u57cb\u4f0f\u5b8c\u6bd5";
            this.g = false;
            this.n = 0;
            this.l = false;
            this.k = true;
            short s = this.c;
            this.a.k[s] = (short)(this.a.k[s] - 100);
            byte[] byArray = this.a.g[this.b + this.g];
            int n = this.a + this.f;
            byArray[n] = (byte)(byArray[n] | 0x20);
        }
    }

    private final boolean d() {
        return Math.abs(this.h - this.f - this.a + (this.i - this.g - this.b)) == 1;
    }

    private final void G() {
        if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 2 && this.d()) {
            if (this.a.w >= 2) {
                for (byte by = 0; by < this.a.v; by = (byte)(by + 1)) {
                    if (this.a.N[by] > 0 && this.a.N[by] <= 3 || this.a.K[by] != (byte)(this.a + this.f) || this.a.L[by] != (byte)(this.g + this.b)) continue;
                    this.d = this.a.w[by];
                    this.a.w = (byte)(this.a.w - 2);
                    this.a.I = (byte)(this.a.g[this.b + this.g][this.a + this.f] & 0xF);
                    this.a.a = this.c;
                    this.a.b = this.d;
                    this.a.c = (byte)2;
                    return;
                }
            } else {
                this.a = "\u673a\u52a8\u529b\u4e0d\u8db3";
            }
        } else {
            this.a = "\u653b\u51fb\u65e0\u6548!";
        }
        this.h = false;
        this.l = false;
        this.k = true;
    }

    private final void f(int n) {
        switch (n) {
            case 4: {
                if (this.p > 0) {
                    this.p = (byte)(this.p - 1);
                    return;
                }
                this.p = (byte)5;
                return;
            }
            case 6: {
                if (this.p < 5) {
                    this.p = (byte)(this.p + 1);
                    return;
                }
                this.p = 0;
            }
        }
    }

    private final void g(int n) {
        switch (n) {
            case 2: {
                if (this.b <= 0) break;
                this.b = (short)(this.b - 1);
                break;
            }
            case 4: {
                if (this.a > 0) {
                    this.a = (short)(this.a - 1);
                    break;
                }
                if (this.f <= 0) break;
                this.f = (byte)(this.f - 1);
                break;
            }
            case 6: {
                if (this.a < 14) {
                    this.a = (short)(this.a + 1);
                    break;
                }
                if (this.f >= this.a.y - 15) break;
                this.f = (byte)(this.f + 1);
                break;
            }
            case 8: {
                if (this.b >= 18) break;
                this.b = (short)(this.b + 1);
            }
        }
        this.n();
    }

    private final void h(int n) {
        switch (n) {
            case 4: {
                this.n = (byte)(this.n - 1);
                if (this.n >= 0) break;
                this.n = (byte)(this.n + 1);
                return;
            }
            case 6: {
                this.n = (byte)(this.n + 1);
                if (this.n <= this.o - 1) break;
                this.n = (byte)(this.n - 1);
            }
        }
    }

    private final void i(int n) {
        this.a("go to selCity");
        switch (n) {
            case 4: {
                this.F = (byte)(this.F - 1);
                if (this.F >= 0) break;
                this.F = 0;
                return;
            }
            case 6: {
                this.F = (byte)(this.F + 1);
                if (this.F < this.a.p) break;
                if (this.a.v[this.a.a] != 100) {
                    this.F = this.a.p;
                    return;
                }
                this.F = (byte)(this.F - 1);
            }
        }
    }

    private final void H() {
        if (this.q || this.r) {
            this.J = 0;
            return;
        }
        if (this.e && this.h > 0) {
            this.M();
            return;
        }
        if (this.i) {
            this.f(4);
            return;
        }
        if (this.k || this.f && !this.m || this.g && !this.j || this.h) {
            this.g(4);
            return;
        }
        if (this.j) {
            this.h(4);
            return;
        }
        if (this.o) {
            this.i(4);
        }
    }

    private final void I() {
        if (this.q || this.r) {
            this.J = 1;
            return;
        }
        if (this.e && this.h < this.a.y - 2) {
            this.N();
            return;
        }
        if (this.i) {
            this.f(6);
            return;
        }
        if (this.k || this.f && !this.m || this.g && !this.j || this.h) {
            this.g(6);
            return;
        }
        if (this.j) {
            this.h(6);
            return;
        }
        if (this.o) {
            this.i(6);
        }
    }

    private final void J() {
        if (this.e && this.i > 0) {
            this.L();
            return;
        }
        if (this.j) {
            this.h(2);
            return;
        }
        if (this.k || this.f && !this.m || this.g && !this.j || this.h) {
            this.g(2);
        }
    }

    private final void K() {
        if (this.e && this.i < this.a.z - 2) {
            this.O();
            return;
        }
        if (this.j) {
            this.h(8);
            return;
        }
        if (this.k || this.f && !this.m || this.g && !this.j || this.h) {
            this.g(8);
        }
    }

    private final void L() {
        if (this.a.a(this.a.g[this.i - 1][this.h]) == 0) {
            int n = this.a.a(this.a.g[this.i - 1][this.h], this.a.p[this.c]);
            this.a("aStep:" + n + "-" + this.a.w);
            if (n <= this.a.w) {
                if (this.c) {
                    this.a.w = (byte)(this.a.w - n);
                } else if (this.e > 0) {
                    this.e = (byte)(this.e - 1);
                } else {
                    return;
                }
                if (this.b > 0) {
                    this.b = (short)(this.b - 1);
                }
                this.p = true;
                this.a.J[this.d] = this.i = (byte)(this.i - 1);
            }
        }
        this.a("curP:" + this.a.I[this.d] + "-" + this.a.J[this.d]);
    }

    private final void M() {
        int n;
        if (this.a.a(this.a.g[this.i][this.h - 1]) == 0 && (n = this.a.a(this.a.g[this.i][this.h - 1], this.a.p[this.c])) <= this.a.w) {
            if (this.c) {
                this.a.w = (byte)(this.a.w - n);
            } else if (this.e > 0) {
                this.e = (byte)(this.e - 1);
            } else {
                return;
            }
            if (this.a > 0) {
                this.a = (short)(this.a - 1);
            }
            this.a.I[this.d] = this.h = (byte)(this.h - 1);
            this.p = true;
        }
    }

    private final void N() {
        int n;
        if (this.a.a(this.a.g[this.i][this.h + 1]) == 0 && (n = this.a.a(this.a.g[this.i][this.h + 1], this.a.p[this.c])) <= this.a.w) {
            if (this.c) {
                this.a.w = (byte)(this.a.w - n);
            } else if (this.e > 0) {
                this.e = (byte)(this.e - 1);
            } else {
                return;
            }
            if (this.a < 14) {
                this.a = (short)(this.a + 1);
            } else if (this.f < this.a.y - 15) {
                this.f = (byte)(this.f + 1);
            }
            this.a.I[this.d] = this.h = (byte)(this.h + 1);
            this.p = true;
        }
    }

    private final void O() {
        int n;
        if (this.a.a(this.a.g[this.i + 1][this.h]) == 0 && (n = this.a.a(this.a.g[this.i + 1][this.h], this.a.p[this.c])) <= this.a.w) {
            if (this.c) {
                this.a.w = (byte)(this.a.w - n);
            } else if (this.e > 0) {
                this.e = (byte)(this.e - 1);
            } else {
                return;
            }
            if (this.b < 18) {
                this.b = (short)(this.b + 1);
            }
            this.a.J[this.d] = this.i = (byte)(this.i + 1);
            this.p = true;
        }
    }

    private final void P() {
        switch (this.a) {
            case 1: {
                if (this.E || this.F <= 0) break;
                this.F = (byte)(this.F - 1);
                return;
            }
            case 2: {
                if (this.E) break;
                this.a.getClass();
                if (this.F >= 2) break;
                this.F = (byte)(this.F + 1);
                return;
            }
            case 4: 
            case 8: {
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (this.b() && this.a.P == 23) {
                    this.a.o(5);
                }
                if (this.E) {
                    if (this.I == 0) {
                        if (this.a.P == 22) {
                            this.a.o((int)this.F);
                            return;
                        }
                        if (this.a.P != 23 || this.a.r[this.F] == 0) break;
                        this.a.o((int)this.F);
                        return;
                    }
                    this.a.o(5);
                    return;
                }
                this.a(this.a.P + "-" + this.a.r[this.F]);
                if (this.a.r[this.F] == 0 && this.a.P != 22) break;
                this.E = true;
                return;
            }
            case 32: {
                this.a.o(5);
            }
        }
    }

    private final void Q() {
        switch (this.a) {
            case 1: {
                if (this.M || this.E) break;
                this.a(this.c, this.U);
                if (!this.a((int)this.e[this.F], this.c)) break;
                this.b(this.c, this.U);
                return;
            }
            case 2: {
                if (this.M || this.E) break;
                this.b(this.c, this.U);
                if (!this.a((int)this.e[this.F], this.c)) break;
                this.a(this.c, this.U);
                return;
            }
            case 4: {
                if (this.M) {
                    this.F = (byte)(this.F ^ 1);
                    return;
                }
                if (!this.E) {
                    this.U = this.c(this.U);
                    return;
                }
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (this.M) {
                    this.F = (byte)(this.F ^ 1);
                    return;
                }
                if (!this.E) {
                    this.U = this.d(this.U);
                    return;
                }
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (this.M) {
                    this.M = false;
                    return;
                }
                if (!this.E) {
                    this.E = true;
                    return;
                }
                if (!this.E) break;
                if (this.K) {
                    this.K = false;
                    this.E = false;
                    return;
                }
                if (this.I == 0) {
                    short s = this.a(this.c);
                    if (s == 0) {
                        this.K = true;
                        return;
                    }
                    if (this.F == 0) {
                        this.i = s * 4 / 3;
                        if (this.A) {
                            this.a.g = (short)(this.a.g - s);
                            this.a.h = (short)(this.a.h + (short)this.i);
                        } else {
                            byte by = this.a.g;
                            this.a.d[by] = (short)(this.a.d[by] - s);
                            byte by2 = this.a.g;
                            this.a.e[by2] = (short)(this.a.e[by2] + (short)this.i);
                        }
                    } else {
                        this.i = 3 * s / 4;
                        if (this.A) {
                            this.a.h = (short)(this.a.h - s);
                            this.a.g = (short)(this.a.g + (short)this.i);
                        } else {
                            byte by = this.a.g;
                            this.a.e[by] = (short)(this.a.e[by] - s);
                            byte by3 = this.a.g;
                            this.a.d[by3] = (short)(this.a.d[by3] + (short)this.i);
                        }
                    }
                }
                if (this.A) {
                    this.a.c = (byte)(this.I + 1);
                    return;
                }
                this.a.m((int)this.I);
                return;
            }
            case 32: {
                if (this.A) {
                    this.a.c = (byte)2;
                    return;
                }
                this.a.m(1);
            }
        }
    }

    private final void R() {
        switch (this.a) {
            case 1: {
                if (!this.L) break;
                this.F = (byte)(this.F - 1);
                if (this.F > -1) {
                    this.a[2] = null;
                    this.a[2] = this.a(this.a.b[this.a.h][this.F]);
                    return;
                }
                this.F = (byte)(this.F + 1);
                return;
            }
            case 2: {
                if (!this.L) break;
                this.F = (byte)(this.F + 1);
                if (this.F < this.a.g[this.a.h]) {
                    this.a[2] = null;
                    this.a[2] = this.a(this.a.b[this.a.h][this.F]);
                    return;
                }
                this.F = (byte)(this.F - 1);
                return;
            }
            case 4: 
            case 8: {
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (!this.L) {
                    if (this.I == 0) {
                        this.L = true;
                        return;
                    }
                    this.a.aY();
                    return;
                }
                this.L = false;
                if (this.I != 1) break;
                this.a.aY();
                return;
            }
            case 32: {
                this.a.aY();
            }
        }
    }

    private final void S() {
        switch (this.a) {
            case 1: {
                if (this.E || this.F <= 0) break;
                this.a[0] = null;
                this.F = (byte)(this.F - 1);
                short s = this.a.j[this.a.a[this.a.E[this.F]]];
                this.a[0] = this.a(s);
                return;
            }
            case 2: {
                if (this.E || this.F >= this.a.q - 1) break;
                this.a[0] = null;
                this.F = (byte)(this.F + 1);
                short s = this.a.j[this.a.a[this.a.E[this.F]]];
                this.a[0] = this.a(s);
                return;
            }
            case 4: 
            case 8: {
                if (!this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                if (this.E) {
                    this.a.b(this.a.E[this.F], (int)this.I);
                    return;
                }
                this.E = true;
                return;
            }
            case 32: {
                this.a.b(this.a.E[this.F], 1);
            }
        }
    }

    private final void T() {
        switch (this.a) {
            case 4: 
            case 8: {
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                this.a.l((int)this.I);
                return;
            }
            case 32: {
                this.a.l(1);
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

    private final byte c(byte by) {
        if (by < 3) {
            by = (byte)(by + 1);
        }
        return by;
    }

    private final byte d(byte by) {
        if (by > 0) {
            by = (byte)(by - 1);
        }
        return by;
    }

    private final byte[] a(short s) {
        byte[] byArray = new byte[]{0, 0, 0, 0};
        byte[] byArray2 = byArray;
        byArray[3] = (byte)(s / 1000);
        if (byArray2[3] == 0) {
            byArray2[2] = (byte)(s / 100);
            byArray2[1] = (byte)(s / 10 % 10);
            byArray2[0] = (byte)(s % 10);
        }
        return byArray2;
    }

    private final void U() {
        switch (this.a) {
            case 1: {
                if (this.H && !this.E) {
                    if (this.J) {
                        if (this.V <= 0) break;
                        this.V = (byte)(this.V - 1);
                        return;
                    }
                    this.a(this.d[this.V + this.T * 10], this.U);
                    int n = this.j + this.k;
                    if (n > 1000) {
                        n = 1000;
                    }
                    if (this.a(n, this.d[this.V + this.T * 10])) {
                        this.b(this.d[this.V + this.T * 10], this.U);
                    }
                    this.j = (short)(this.j - (short)(this.a(this.d[this.V + this.T * 10]) - this.k));
                    this.k = this.a(this.d[this.V + this.T * 10]);
                    return;
                }
                if (this.E) break;
                if (this.a.i[this.a.g] >= 9900) {
                    return;
                }
                this.a(this.c, this.U);
                int n = 9900 - this.a.i[this.a.g];
                if (n > this.a.d[this.a.g] * 5) {
                    n = this.a.d[this.a.g] * 5;
                }
                if (n > this.a.a[this.a.g]) {
                    n = this.a.a[this.a.g];
                }
                if (this.a(n, this.c)) {
                    this.b(this.c, this.U);
                }
                return;
            }
            case 2: {
                if (this.H && !this.E) {
                    if (this.J) {
                        int n = 0;
                        n = this.S == 1 ? this.a.g[this.a.g] : (this.T < this.S - 1 ? 9 : this.a.g[this.a.g] % 10);
                        if (this.V < n) {
                            this.V = (byte)(this.V + 1);
                        }
                        return;
                    }
                    this.b(this.d[this.V + this.T * 10], this.U);
                    short s = (short)(this.j - this.a(this.d[this.V + this.T * 10]) + this.k);
                    if (s > 9900 || this.a(this.d[this.V + this.T * 10]) > 1000 || s < 0) {
                        this.a(this.d[this.V + this.T * 10], this.U);
                    } else {
                        this.j = s;
                        this.k = this.a(this.d[this.V + this.T * 10]);
                    }
                    return;
                }
                if (this.E) break;
                if (this.a.i[this.a.g] >= 9900) {
                    return;
                }
                this.b(this.c, this.U);
                int n = 9900 - this.a.i[this.a.g];
                if (n > this.a.d[this.a.g] * 5) {
                    n = this.a.d[this.a.g] * 5;
                }
                if (n > this.a.a[this.a.g]) {
                    n = this.a.a[this.a.g];
                }
                if (this.a(n, this.c)) {
                    this.a(this.c, this.U);
                }
                return;
            }
            case 4: {
                if (this.H) {
                    if (this.I) {
                        this.U = this.c(this.U);
                    } else if (this.T > 0) {
                        this.T = (byte)(this.T - 1);
                        this.V = 0;
                    }
                    if (!this.E) break;
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                if (this.E) {
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                this.U = this.c(this.U);
                return;
            }
            case 8: {
                if (this.H) {
                    if (this.I) {
                        this.U = this.d(this.U);
                    } else if (this.T < this.S - 1) {
                        this.T = (byte)(this.T + 1);
                        this.V = 0;
                    }
                    if (!this.E) break;
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                if (this.E) {
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                this.U = this.d(this.U);
                return;
            }
            case 16: {
                if (this.H) {
                    if (!this.E && this.V + this.T * 10 == this.a.g[this.a.g]) {
                        this.E = true;
                        return;
                    }
                    if (this.E) {
                        if (this.I == 0) {
                            this.a.h[this.a.g] = 0;
                            for (byte by = 0; by < this.a.g[this.a.g]; by = (byte)(by + 1)) {
                                this.a.k[this.a.b[this.a.g][by]] = this.a(this.d[by]);
                                byte by2 = this.a.g;
                                this.a.h[by2] = (short)(this.a.h[by2] + this.a.k[this.a.b[this.a.g][by]]);
                            }
                            this.a.i[this.a.g] = this.j;
                        }
                        this.a.k(0);
                        return;
                    }
                    if (!this.I) {
                        this.I = true;
                        this.J = false;
                        this.k = this.a(this.d[this.V + this.T * 10]);
                        return;
                    }
                    this.U = 0;
                    this.I = false;
                    this.J = true;
                    return;
                }
                if (!this.E) {
                    this.E = true;
                    return;
                }
                if (this.I == 0) {
                    short s = this.a(this.c);
                    this.a.b(s, this.a.g);
                    this.U = 0;
                    this.H = true;
                    this.E = false;
                    for (byte by = 0; by < this.a.g[this.a.g]; by = (byte)(by + 1)) {
                        this.d[by] = this.a(this.a.k[this.a.b[this.a.g][by]]);
                    }
                    this.j = this.a.i[this.a.g];
                    return;
                }
                this.a.k(1);
                return;
            }
            case 32: {
                if (this.H) {
                    this.a.k(0);
                    return;
                }
                this.a.k(1);
            }
        }
    }

    private final void V() {
        switch (this.a) {
            case 1: {
                if (this.E || this.G || this.F <= 0) break;
                this.F = (byte)(this.F - 1);
                return;
            }
            case 2: {
                byte by = 0;
                by = this.S == 1 ? this.R : (this.T < this.S - 1 ? (byte)9 : (byte)((byte)(this.R % 10)));
                if (this.E || this.G || this.F >= by) break;
                this.F = (byte)(this.F + 1);
                return;
            }
            case 4: {
                if (this.G) {
                    this.J = (byte)(this.J ^ 1);
                    return;
                }
                if (this.E) {
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                if (this.T <= 0) break;
                this.T = (byte)(this.T - 1);
                this.F = 0;
                return;
            }
            case 8: {
                if (this.G) {
                    this.J = (byte)(this.J ^ 1);
                    return;
                }
                if (this.E) {
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                if (this.T >= this.S - 1) break;
                this.T = (byte)(this.T + 1);
                this.F = 0;
                return;
            }
            case 16: {
                if (this.G) {
                    if (this.J == 0) {
                        this.a.h(0);
                        return;
                    }
                    this.a.h(1);
                    return;
                }
                if (this.a.a) {
                    this.Z();
                    return;
                }
                this.Y();
                return;
            }
            case 32: {
                if (!this.a.b) {
                    return;
                }
                if (this.G) {
                    this.a.h(1);
                    return;
                }
                if (this.a.a) {
                    this.a.g(1);
                    return;
                }
                this.a.i(1);
            }
        }
    }

    private final void W() {
        switch (this.a) {
            case 4: {
                if (!this.F) {
                    if (this.G > 0) {
                        this.G = (byte)(this.G - 1);
                        return;
                    }
                    this.G = (byte)5;
                    return;
                }
                if (this.H > 0) {
                    this.H = (byte)(this.H - 1);
                    return;
                }
                this.H = (byte)3;
                return;
            }
            case 8: {
                if (!this.F) {
                    if (this.G < 5) {
                        this.G = (byte)(this.G + 1);
                        return;
                    }
                    this.G = 0;
                    return;
                }
                if (this.H < 3) {
                    this.H = (byte)(this.H + 1);
                    return;
                }
                this.H = 0;
                return;
            }
            case 16: {
                if (!this.F) {
                    this.F = true;
                    this.H = 0;
                    return;
                }
                if (this.F && this.G == 0 && this.H == 0) {
                    this.a.t((byte)1);
                    return;
                }
                if (this.F && this.G == 0 && this.H == 1) {
                    this.a.I((byte)3);
                    return;
                }
                if (this.F && this.G == 0 && this.H == 2) {
                    this.a.s((byte)2);
                    return;
                }
                if (this.F && this.G == 0 && this.H == 3) {
                    this.a.u((byte)12);
                    return;
                }
                if (this.F && this.G == 1 && this.H == 0) {
                    this.a.s((byte)6);
                    return;
                }
                if (this.F && this.G == 1 && this.H == 1) {
                    this.a.w((byte)7);
                    return;
                }
                if (this.F && this.G == 1 && this.H == 2) {
                    this.a.v((byte)8);
                    return;
                }
                if (this.F && this.G == 1 && this.H == 3) {
                    this.a.x((byte)9);
                    return;
                }
                if (this.F && this.G == 2 && this.H == 0) {
                    this.a.y((byte)10);
                    return;
                }
                if (this.F && this.G == 2 && this.H == 1) {
                    this.a.z((byte)11);
                    return;
                }
                if (this.F && this.G == 2 && this.H == 2) {
                    this.a.A((byte)13);
                    return;
                }
                if (this.F && this.G == 2 && this.H == 3) {
                    this.a.B((byte)14);
                    return;
                }
                if (this.F && this.G == 3 && this.H == 0) {
                    this.a.C((byte)5);
                    return;
                }
                if (this.F && this.G == 3 && this.H == 1) {
                    this.a.s((byte)20);
                    return;
                }
                if (this.F && this.G == 3 && this.H == 2) {
                    this.a.D((byte)21);
                    return;
                }
                if (this.F && this.G == 3 && this.H == 3) {
                    this.a.s((byte)4);
                    return;
                }
                if (this.F && this.G == 4 && this.H == 0) {
                    this.a.E((byte)15);
                    return;
                }
                if (this.F && this.G == 4 && this.H == 1) {
                    this.a.F((byte)16);
                    return;
                }
                if (this.F && this.G == 4 && this.H == 2) {
                    this.a.G((byte)18);
                    return;
                }
                if (this.F && this.G == 4 && this.H == 3) {
                    this.a.H((byte)19);
                    return;
                }
                if (this.F && this.G == 5 && this.H == 0) {
                    this.a.s((byte)22);
                    return;
                }
                if (this.F && this.G == 5 && this.H == 1) {
                    this.a.s((byte)23);
                    return;
                }
                if (this.F && this.G == 5 && this.H == 2) {
                    this.a((byte)24);
                    return;
                }
                if (!this.F || this.G != 5 || this.H != 3) break;
                this.a.a();
                return;
            }
            case 32: {
                if (this.F) {
                    this.F = false;
                    return;
                }
                this.a.s(this.a.P);
            }
        }
    }

    public final void j() {
        if (this.D) {
            this.D = false;
            this.a.c = 1;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.M <= 2) break;
                this.M = (byte)(this.M - 1);
                break;
            }
            case 2: {
                if (this.M >= 33) break;
                this.M = (byte)(this.M + 1);
                break;
            }
            case 4: {
                if (this.L <= 0) break;
                this.L = (byte)(this.L - 1);
                break;
            }
            case 8: {
                if (this.L >= 28) break;
                this.L = (byte)(this.L + 1);
                break;
            }
            case 16: {
                switch (this.a.P) {
                    case 0: {
                        if (this.c[this.M][this.L] <= 0 || this.a.a[this.a.d] != this.a.b[this.c[this.M][this.L]]) break;
                        byte by = this.c[this.M][this.L];
                        this.a.a(by, 0);
                        return;
                    }
                    case 1: 
                    case 12: {
                        if (this.c[this.M][this.L] <= 0) break;
                        byte by = this.c[this.M][this.L];
                        if (this.a.g != by && this.a.b[this.a.g] == this.a.b[by] && this.a.a(this.a.g, by)) {
                            this.a.a(by, 0);
                        }
                        return;
                    }
                    case 3: {
                        if (this.c[this.M][this.L] <= 0) break;
                        byte by = this.c[this.M][this.L];
                        if (this.a.b[this.a.g] != this.a.b[by] && this.a.a(this.a.g, by) && !this.a.q(by)) {
                            this.a.a(by, 0);
                        }
                        return;
                    }
                    case 4: {
                        if (this.c[this.M][this.L] <= 0) break;
                        byte by = this.c[this.M][this.L];
                        this.a.a(by, 0);
                        return;
                    }
                    case 20: 
                    case 21: {
                        if (this.c[this.M][this.L] <= 0) break;
                        byte by = this.c[this.M][this.L];
                        this.a("objCity:" + by);
                        if (this.a.b[by] > 0 && this.a.b[this.a.g] != this.a.b[by]) {
                            this.a.a(by, 0);
                        }
                        return;
                    }
                    case 111: {
                        if (this.c[this.M][this.L] <= 0) break;
                        byte by = this.c[this.M][this.L];
                        if (this.a.b[by] == 0) {
                            this.a.N(this.c[this.M][this.L]);
                            this.a.P = 0;
                            this.a.p((byte)8);
                            return;
                        }
                        this.D = true;
                        this.d = "\u8bf7\u9009\u62e9\u7a7a\u57ce!";
                        break;
                    }
                    case 22: {
                        byte by;
                        if (this.c[this.M][this.L] <= 0 || this.a.b[by = this.c[this.M][this.L]] != this.a.a[this.a.d]) break;
                        this.a.g = by;
                        this.a.c = 1;
                        return;
                    }
                }
                break;
            }
            case 32: {
                if (this.a.P == 111) {
                    this.O = 0;
                    this.a((byte)1);
                    break;
                }
                this.a.a((byte)1, 1);
            }
        }
        this.X();
    }

    public final void k() {
        if (this.D) {
            this.D = false;
            return;
        }
        if (this.E == 4) {
            this.j();
            return;
        }
        if (this.E == 25) {
            this.r();
            return;
        }
        if (this.E == 7) {
            this.aa();
            return;
        }
        if (this.E == 27) {
            this.ad();
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.E) {
                    return;
                }
                if (this.O == 0 && this.X) {
                    if (this.o <= 0) break;
                    --this.o;
                    return;
                }
                if (this.O == 1) {
                    if (!this.N || this.E || this.F <= 0) break;
                    this.F = (byte)(this.F - 1);
                    return;
                }
                if (this.O != 2) break;
                this.F = (byte)(this.F - 1);
                if (this.F < 1) {
                    this.F = (byte)8;
                }
                this.a[3] = null;
                this.a[3] = this.a(this.a.j[this.a.a[this.F]]);
                return;
            }
            case 2: {
                if (this.E) {
                    return;
                }
                if (this.O == 0 && this.X) {
                    if (this.o >= 1) break;
                    ++this.o;
                    return;
                }
                if (this.O == 1) {
                    if (!this.N || this.E) break;
                    this.a.getClass();
                    if (this.F >= 2) break;
                    this.F = (byte)(this.F + 1);
                    return;
                }
                if (this.O != 2) break;
                this.F = (byte)(this.F + 1);
                if (this.F > 8) {
                    this.F = 1;
                }
                this.a[3] = null;
                this.a[3] = this.a(this.a.j[this.a.a[this.F]]);
                return;
            }
            case 4: {
                if (this.O == 0 && !this.X) {
                    this.F = (byte)(this.F - 1);
                    if (this.F >= 0) break;
                    this.F = (byte)(this.P - 1);
                    return;
                }
                if (this.O != 2 && !this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (this.O == 0 && !this.X) {
                    this.F = (byte)(this.F + 1);
                    if (this.F <= this.P - 1) break;
                    this.F = 0;
                    return;
                }
                if (this.O != 2 && !this.E) break;
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 16: {
                switch (this.O) {
                    case 0: {
                        if (this.X) {
                            if (this.o == 0) {
                                if (this.a() == 0) {
                                    this.D = true;
                                    this.d = "\u81ea\u5efa\u6b66\u5c06\u6570\u91cf\u5df2\u6ee1!";
                                } else {
                                    this.a((byte)27);
                                }
                            } else if (this.a.p()) {
                                this.a.P = (byte)113;
                                this.a((byte)7);
                            } else {
                                this.D = true;
                                this.d = "\u5c1a\u65e0\u81ea\u5efa\u6b66\u5c06!";
                            }
                            this.X = false;
                            this.o = 0;
                            return;
                        }
                        switch (this.F) {
                            case 0: {
                                this.O = (byte)2;
                                this.F = 1;
                                this.a = "\u8bf7\u9009\u62e9\u626e\u6f14\u541b\u4e3b";
                                this.a[2] = this.a(110);
                                this.a[3] = this.a(this.a.j[this.a.a[this.F]]);
                                this.a[4] = this.a(15);
                                break;
                            }
                            case 1: {
                                this.O = 1;
                                this.N = true;
                                this.a.p();
                                break;
                            }
                            case 2: {
                                this.X = true;
                                if (this.b == null) {
                                    this.b = this.a(20);
                                }
                                if (this.a != null) break;
                                this.a = this.a(109);
                                break;
                            }
                            case 3: {
                                this.a((byte)25);
                                break;
                            }
                            case 4: {
                                this.a.a();
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (!this.N) break;
                        if (this.E) {
                            this.a("curDecide:" + this.I);
                            if (this.I == 0) {
                                if (this.a.r[this.F] == 0) break;
                                this.a.b(this.F);
                                break;
                            }
                            this.O = 0;
                            this.a.b((byte)5);
                            break;
                        }
                        if (this.a.r[this.F] == 0) break;
                        this.E = true;
                        break;
                    }
                    case 2: {
                        if (this.I == 0) {
                            if (this.F == 8) {
                                this.a("result:" + this.a.p());
                                if (this.a.p()) {
                                    this.a.P = (byte)112;
                                    this.a((byte)7);
                                    break;
                                }
                                this.D = true;
                                this.d = "\u6b66\u5c06\u5c1a\u672a\u521b\u5efa!";
                                return;
                            }
                            this.a.p(this.a.a[this.F]);
                            break;
                        }
                        this.F = 0;
                        this.I = 0;
                        this.O = 0;
                    }
                }
                return;
            }
            case 32: {
                if (this.X) {
                    this.X = false;
                    this.o = 0;
                    return;
                }
                if (this.O == 1) {
                    if (this.E) {
                        this.E = false;
                        this.I = 0;
                        return;
                    }
                    this.F = 0;
                    this.O = 0;
                    if (!this.N) break;
                    this.a.b((byte)5);
                    return;
                }
                return;
            }
        }
    }

    private final synchronized Image a(int n) {
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

    private final Image a(short s) {
        this.a("*****createAvatar:" + s);
        if (s > 228) {
            s = this.a.z[s - 229];
        }
        return this.b(s);
    }

    private final Image b(short s) {
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

    private final void a(String string) {
        if (this.C) {
            System.out.println(string);
        }
    }

    private final void T(Graphics graphics) {
        byte by;
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        graphics.drawImage(this.f, 0, 0, 0);
        graphics.drawImage(this.a[2], 90, 40, 20);
        for (by = 0; by < 8; by = (byte)((byte)(by + 1))) {
            graphics.drawRegion(this.a[4], by * 20, 0, 20, 20, 0, 107 + by % 2 * 51, by / 2 * 30 + 140, 0);
        }
        graphics.setColor(12359020);
        graphics.fillRect(18, 25 * this.F + 20, 72, this.a);
        for (by = 1; by < 9; by = (byte)(by + 1)) {
            graphics.setColor(0);
            if (by == 8) {
                graphics.setColor(0);
                graphics.drawString("\u81ea\u5efa", 20, 20 + 25 * by, 20);
                graphics.drawString("=0", 70, 21 + 25 * by, 20);
                this.a(graphics, by, 55, 22 + 25 * by);
                continue;
            }
            graphics.setColor(0);
            graphics.drawString(this.a.b[this.a.a[by]], 20, 20 + 25 * by, 20);
            graphics.drawString("=" + String.valueOf(this.a.b[by]), 70, 21 + 25 * by, 20);
            this.a(graphics, by, 55, 22 + 25 * by);
        }
        graphics.drawImage(this.a[3], 100, 51, 20);
        graphics.setColor(0);
        graphics.drawRect(100, 51, this.a[3].getWidth(), this.a[3].getHeight());
        graphics.setColor(0xFFFFFF);
        if (this.F < 8) {
            graphics.setColor(0xFFFFFF);
            by = this.a.j[this.a.a[this.F]];
            graphics.drawString(this.a.b[by], 172, 63, 20);
            graphics.setColor(16275794);
            graphics.drawString("LV. " + String.valueOf(this.a.o[by]), 177, 90, 20);
            graphics.drawString(String.valueOf(this.a.s[by]), 130, 140, 20);
            graphics.drawString(String.valueOf(this.a.t[by]), 180, 140, 20);
            graphics.drawString(String.valueOf(this.a.r[by]), 130, 171, 20);
            graphics.drawString(String.valueOf(this.a.u[by]), 180, 171, 20);
            graphics.drawString(String.valueOf(this.a.k[by]), 130, 202, 20);
            graphics.drawString(String.valueOf(this.a.l[by] / 100), 180, 202, 20);
            graphics.drawString(this.a.c[this.a.w[by]], 130, 232, 20);
            graphics.drawString(this.a.c[this.a.x[by]], 180, 232, 20);
        } else {
            graphics.setColor(0xFFFFFF);
            graphics.drawString("\u81ea\u5efa", 172, 63, 20);
        }
        graphics.drawImage(this.a, 110 + this.I * 47, 274, 0);
        if (this.c == null) {
            this.c = this.a(14);
        }
        graphics.drawRegion(this.c, 0, 0, 29, 17, 0, 118, 270, 20);
        graphics.drawRegion(this.c, 29, 0, 29, 17, 0, 165, 270, 20);
        this.b(graphics, "\u541b\u4e3b\u9009\u62e9");
        this.d(graphics);
    }

    private final void a(Graphics graphics, byte by, int n, int n2) {
        graphics.setColor(0xFCFCFC);
        graphics.fillRect(n + 3, n2, 6, 3);
        graphics.fillRect(n, n2 + 3, 12, 8);
        graphics.setColor(this.a[by]);
        graphics.fillRect(n + 4, n2 + 1, 4, 2);
        graphics.fillRect(n + 1, n2 + 4, 10, 6);
        graphics.setColor(0xFFFFFF);
    }

    private final void U(Graphics graphics) {
        graphics.drawRegion(this.a[3], 0, 0, 188, 24, 0, this.f >> 1, 298, 33);
        graphics.drawRegion(this.a[3], 0, (this.G + 1) * 24, 188, 24, 0, this.f >> 1, 298, 33);
        graphics.setColor(0xFFFF00);
        graphics.drawRect(40 + 42 * this.H, 278, 34, 15);
    }

    private final void V(Graphics graphics) {
        if (this.a.e != this.a.d) {
            this.a = this.a.b[this.a.a[this.a.e]] + " \u6218\u7565\u4e2d.....";
        }
        graphics.drawImage(this.a[0], 0, 0, 20);
        if (this.a.P == 4) {
            this.b(graphics, c.a[3][3]);
        } else if (this.a.P == 111 || this.a.P == 112) {
            this.b(graphics, "\u9009\u57ce\u5efa\u90fd");
        } else {
            this.W(graphics);
        }
        for (int n = 0; n < 34; n = (int)((byte)(n + 1))) {
            for (int n2 = 0; n2 < 29; n2 = (int)((byte)(n2 + 1))) {
                if (this.c[n][n2] <= 0) continue;
                if (this.a.b[this.c[n][n2]] > 0) {
                    for (int n3 = 1; n3 < 9; n3 = (int)((byte)(n3 + 1))) {
                        if (this.a.b[this.c[n][n2]] != this.a.a[n3]) continue;
                        this.a(graphics, this.a.a[n3], n2 * 8, n * 8);
                    }
                    continue;
                }
                this.a(graphics, (byte)0, n2 * 8, n * 8);
            }
        }
        this.d(graphics);
        if (this.a.e == this.a.d || this.a.P == 112) {
            graphics.drawImage(this.a[1], this.L * 8 + 4, this.M * 8 + 4, 20);
        }
    }

    private final void X() {
        this.a("cmd:" + this.a.P);
        String string = null;
        if (this.c[this.M][this.L] > 0) {
            string = "\u57ce\u6c60: " + this.a.a[this.c[this.M][this.L]];
            if (this.a.b[this.c[this.M][this.L]] > 0) {
                string = string + " \u541b\u4e3b:" + this.a.b[this.a.b[this.c[this.M][this.L]]];
            }
            if (this.a.q(this.a.d[this.c[this.M][this.L]])) {
                string = string + "(\u76df)";
            }
        } else {
            string = this.a.P == 1 || this.a.P == 20 || this.a.P == 12 || this.a.P == 21 ? "\u8bf7\u9009\u62e9\u76ee\u7684\u57ce\u6c60" : (this.a.P == 22 ? c.b : (this.a.P == 3 ? "\u6b32\u653b\u6253\u4f55\u57ce?" : (this.a.P == 4 ? "\u8bf7\u9009\u62e9\u4fa6\u67e5\u7684\u57ce\u6c60" : (this.a.P == 111 ? c.g : "\u5bf9\u4f55\u5dde\u90e1\u4e0b\u4ee4"))));
        }
        this.a = string;
    }

    private final void W(Graphics graphics) {
        this.b(graphics, String.valueOf(this.a.c) + "\u5e74" + String.valueOf(this.a.k % 13) + "\u6708");
    }

    private final void a(Graphics graphics, String string) {
        if (this.b == null) {
            this.b = this.a(20);
        }
        graphics.drawImage(this.b, this.f >> 1, 70, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, string, 50, 85, 150, 100);
    }

    private final void a(Graphics graphics, String string, byte by) {
        if (this.b == null) {
            this.b = this.a(20);
        }
        if (this.c == null) {
            this.c = this.a(14);
        }
        graphics.drawImage(this.b, this.f >> 1, 70, 17);
        graphics.setColor(0xFFFFFF);
        this.a(graphics, string, 50, 85, 150, 100);
        graphics.drawImage(this.a, 65 + 50 * this.J, 135, 0);
        graphics.drawRegion(this.c, 0, 0, 29, 17, 0, 76, 130, 20);
        graphics.drawRegion(this.c, 29, 0, 29, 17, 0, 126, 130, 20);
    }

    private final void b(Graphics graphics, String string) {
        graphics.setColor(16275794);
        graphics.drawString(string, this.f >> 1, 1, 17);
    }

    public final void l() {
        if (this.a.P != 4 || this.L == 0) {
            block0: for (int n = 0; n < 32; n = (int)((byte)(n + 1))) {
                for (int n2 = 0; n2 < 29; n2 = (int)((byte)(n2 + 1))) {
                    if (this.c[n][n2] != this.a.g) continue;
                    this.L = (byte)n2;
                    this.M = (byte)n;
                    continue block0;
                }
            }
        }
    }

    private final void Y() {
        int n = this.F + this.T * 10;
        if (!this.E && n < this.R) {
            if (!this.a.a[this.c[n]]) {
                this.a.a[this.c[n]] = true;
                this.Q = (byte)(this.Q + 1);
                return;
            }
            this.a.a[this.c[n]] = false;
            this.Q = (byte)(this.Q - 1);
            return;
        }
        if (this.E) {
            this.a.i((int)this.I);
            return;
        }
        if (n == this.R && this.Q > 0) {
            this.E = true;
        }
    }

    private final void Z() {
        int n = this.F + this.T * 10;
        if (!this.E && n < this.R) {
            if (this.i > 0) {
                this.a.a[this.i] = false;
                if (this.c[n] != this.i) {
                    this.a.a[this.c[n]] = true;
                    this.i = this.c[n];
                    return;
                }
                this.i = 0;
                return;
            }
            this.i = this.c[n];
            this.a.a[this.i] = true;
            return;
        }
        if (this.E) {
            this.a.g((int)this.I);
            return;
        }
        if (n == this.R && this.i > 0) {
            if (!this.a.b) {
                this.a.g((int)this.I);
                return;
            }
            this.E = true;
        }
    }

    public final void m() {
        this.G = true;
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
        graphics.drawString(this.a, this.f >> 1, this.g - 2, 33);
    }

    public final void n() {
        if (this.g) {
            this.a = "\u9009\u62e9\u8ba1\u7b56\u5b9e\u65bd\u5730\u70b9";
            return;
        }
        if (this.f) {
            this.a = "\u8bf7\u9009\u62e9\u8981\u4fa6\u5bdf\u7684\u6b66\u5c06";
            return;
        }
        if (this.h) {
            this.a = "\u9009\u62e9\u6b32\u653b\u51fb\u654c\u5c06";
            return;
        }
        if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 1) {
            for (byte by = 0; by < this.a.u; by = (byte)(by + 1)) {
                if (this.a.I[by] != (byte)(this.a + this.f) || this.a.J[by] != (byte)(this.b + this.g) || this.a.M[by] != 0 && this.a.M[by] <= 3) continue;
                this.a = "\u6b66\u5c06: " + this.a.b[this.a.v[by]];
                return;
            }
            return;
        }
        if (this.a.a(this.a.g[this.b + this.g][this.a + this.f]) == 2) {
            this.a = "\u654c\u5c06!";
            return;
        }
        this.a = "\u9009\u62e9\u6b66\u5c06\u4e0b\u547d\u4ee4";
    }

    private final void a(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        graphics.setClip(n, n2, n3, n4);
        this.j = n3 / this.b;
        this.k = n4 / this.a;
        this.l = (string.length() - 1) / this.j + 1;
        int n5 = this.m + this.k;
        if (n5 > this.l) {
            n5 = this.l;
        }
        for (int i = this.m; i < n5; ++i) {
            int n6 = i * this.j;
            int n7 = n6 + this.j;
            if (n7 > string.length()) {
                n7 = string.length();
            }
            graphics.drawString(string.substring(n6, n7), this.f >> 1, n2 + this.a * ((i - this.m) % this.k), 17);
        }
        graphics.setClip(0, 0, this.f, this.g);
        if (this.l > this.k) {
            if (this.a == null) {
                this.a = this.a(109);
            }
            if (this.m > 0) {
                graphics.drawRegion(this.a, 0, 0, 6, 8, 6, this.f >> 1, 20, 17);
            }
            if (n5 < this.l) {
                graphics.drawRegion(this.a, 0, 0, 6, 8, 5, this.f >> 1, this.g - 20, 33);
            }
        }
    }

    public final void b(int n) {
        String string = this.a((byte)n);
        try {
            InputStream inputStream = ((Object)((Object)this)).getClass().getResourceAsStream(string);
            this.a = Manager.createPlayer((InputStream)inputStream, (String)"audio/midi");
            this.a.realize();
            this.b(this.c);
            this.a.setLoopCount(10);
            this.a.start();
            System.gc();
            return;
        }
        catch (Exception exception) {
            this.a("music error:" + exception.toString());
            return;
        }
    }

    public final String a(byte by) {
        this.b = by;
        return "/res/music/" + by + ".mid";
    }

    public final void o() {
        try {
            this.a.stop();
            this.a.close();
            this.a = null;
            this.b = 0;
            return;
        }
        catch (MediaException mediaException) {
            this.a("close Music error:" + mediaException.toString());
            return;
        }
    }

    public final void b(byte by) {
        if (this.a != null && this.a.getState() > 0) {
            this.a = (VolumeControl)this.a.getControl("VolumeControl");
            int n = 10 + by * 20;
            this.a.setLevel(n);
        }
    }

    private final void X(Graphics graphics) {
        if (this.f == null) {
            this.f = this.a(302);
        }
        graphics.drawImage(this.f, 0, 0, 0);
        this.d(graphics);
        this.b(graphics, "\u5173\u4e8e\u6e38\u620f");
        graphics.setColor(0);
        this.b(graphics, (byte)1);
        this.a(graphics, "\u300a\u9738\u738b\u7684\u5927\u9646Mobile\u300b\u91c7\u7528\u4e86\u6307\u4ee4\u724c\u6e38\u620f\u7cfb\u7edf\uff0c\u541b\u4e3b\u62e5\u6709\u57ce\u6570\u8d8a\u591a\uff0c\u6bcf\u6708\u53ef\u4ee5\u53d1\u5e03\u6307\u4ee4\u7684\u4ee4\u724c\u8d8a\u591a\u3002\u5728\u53ea\u6709\u4e00\u5ea7\u57ce\u7684\u60c5\u51b5\u4e0b\u6bcf\u6708\u53ea\u53ef\u4ee5\u505a\u4e09\u4ef6\u4e8b\u3002\u641c\u7d22\u6b66\u5c06\u4e3b\u8981\u53d6\u51b3\u4e8e\u541b\u4e3b\u7684\u5fb7\u884c\uff0c\u5fb7\u884c\u503c\u8d8a\u9ad8\u8d8a\u5bb9\u6613\u8ba9\u6b66\u5c06\u52a0\u5165\u65d7\u4e0b\u3002\u6e38\u620f\u4e2d\u8fd8\u9996\u521b\u4e86\u4ee5\u4eba\u671b\u503c\u51b3\u5b9a\u884c\u52a8\u529b\u7684\u6982\u5ff5\u3002\u5916\u4ea4\u7b56\u7565\u5171\u6709\u4e09\u9879\uff0c\u5206\u522b\u4e3a\u540c\u76df\u3001\u79bb\u95f4\u548c\u62db\u63fd\u3002\u540c\u76df\u662f\u4e0e\u5176\u5b83\u56fd\u5bb6\u7b7e\u5b9a\u4e92\u4e0d\u4fb5\u72af\u6761\u7ea6\uff0c\u79bb\u95f4\u7528\u6765\u964d\u4f4e\u654c\u65b9\u6b66\u5c06\u7684\u5fe0\u8bda\u5ea6\uff0c\u62db\u63fd\u5373\u5c06\u5bf9\u65b9\u6b66\u5c06\u62c9\u81f3\u9ebe\u4e0b\u3002\u6e38\u620f\u6700\u5927\u7684\u4eae\u70b9\u8fd8\u662f\u5728\u4e8e\u5176\u6218\u4e89\u7cfb\u7edf\uff0c\u9635\u578b\u7684\u8bbe\u5b9a\u548c\u91cd\u5927\u4f5c\u7528\u8ba9\u6218\u4e89\u8fc7\u7a0b\u5145\u6ee1\u7b56\u7565\u6027\u3002    \u6e38\u620f\u952e\u76d8\u64cd\u4f5c\u8bbe\u7f6e:\u5bfc\u822a\u65b9\u5411\u952e\u6216\u6570\u5b57\u952e\u76d82,8,4,6\u5206\u522b\u4e3a'\u4e0a\u4e0b\u5de6\u53f3\u7684\u65b9\u5411\u9009\u62e9',\u5bfc\u822a\u7684\u786e\u8ba4\u952e\u6216\u6570\u5b57\u952e5\u4e3a'\u786e\u8ba4',\u624b\u673a\u53f3\u8f6f\u5efa\u6216\u6570\u5b57\u952e7\u4e3a'\u53d6\u6d88'.\u66f4\u591a\u6e38\u620f\u4fe1\u606f,\u8bf7\u8bbf\u95ee\u5b98\u65b9\u7f51\u7ad9http://www.justgame.com.cn", 15, 35, this.f - 35, this.g - 60);
    }

    private final void b(Graphics graphics, byte by) {
        this.a = Font.getFont((int)0, (int)0, (int)8);
        switch (by) {
            case 0: {
                this.a = Font.getFont((int)0, (int)0, (int)8);
                break;
            }
            case 1: {
                this.a = Font.getFont((int)0, (int)0, (int)0);
                break;
            }
            case 2: {
                this.a = Font.getFont((int)0, (int)0, (int)16);
            }
        }
        graphics.setFont(this.a);
        this.b = this.a.charWidth('\u7231');
        this.a = this.a.getHeight();
    }

    public static final void a(Exception exception) {
        exception.toString();
    }

    private final void d(byte by) {
        if (this.a[this.ab][by - 2] < 99 && this.a[this.ab][4] > 0) {
            byte[] byArray = this.a[this.ab];
            int n = by - 2;
            byArray[n] = (byte)(byArray[n] + 1);
            byte[] byArray2 = this.a[this.ab];
            byArray2[4] = (byte)(byArray2[4] - 1);
        }
    }

    private final void e(byte by) {
        if (this.a[this.ab][by - 2] > this.b[this.ab][by - 2]) {
            byte[] byArray = this.a[this.ab];
            int n = by - 2;
            byArray[n] = (byte)(byArray[n] - 1);
            byte[] byArray2 = this.a[this.ab];
            byArray2[4] = (byte)(byArray2[4] + 1);
        }
    }

    private final byte[] a() {
        Random random = new Random();
        byte[] byArray = new byte[4];
        for (int n = 0; n < 4; n = (int)((byte)(n + 1))) {
            byArray[n] = (byte)(Math.abs(random.nextInt()) % 1000 / 10 + 1);
            if (byArray[n] <= 99) continue;
            byArray[n] = 99;
        }
        return byArray;
    }

    public final void commandAction(Command command, Displayable displayable) {
        if (command == this.a) {
            if (displayable == this.a) {
                this.b.getString();
            } else if (displayable == this.b) {
                this.c.getString();
            } else if (displayable == this.c) {
                this.h = this.a.getString().trim();
                for (short s = 1; s < this.a(); s = (short)(s + 1)) {
                    if (this.h.compareTo(this.a.b[s]) != 0) continue;
                    this.D = true;
                    this.d = "\u6b66\u5c06\u540d\u91cd\u590d!";
                    this.h = "";
                    break;
                }
            }
        }
        Display.getDisplay((MIDlet)this.a).setCurrent((Displayable)this);
    }

    private final short a() {
        for (short s = 229; s < 232; s = (short)(s + 1)) {
            if (this.a.j[s] != 0) continue;
            return s;
        }
        return 0;
    }

    private final void aa() {
        switch (this.a) {
            case 1: {
                if (this.E || this.F <= 0) break;
                this.F = (byte)(this.F - 1);
                return;
            }
            case 2: {
                byte by = 0;
                by = this.S == 1 ? this.R : (this.T < this.S - 1 ? (byte)9 : (byte)(this.R % 10));
                if (this.E || this.F >= by) break;
                this.F = (byte)(this.F + 1);
                return;
            }
            case 4: {
                if (this.E) {
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                if (this.T <= 0) break;
                this.T = (byte)(this.T - 1);
                this.F = 0;
                return;
            }
            case 8: {
                if (this.E) {
                    this.I = (byte)(this.I ^ 1);
                    return;
                }
                if (this.T >= this.S - 1) break;
                this.T = (byte)(this.T + 1);
                this.F = 0;
                return;
            }
            case 16: {
                if (this.a.a) {
                    this.ac();
                    return;
                }
                this.ab();
                return;
            }
            case 32: {
                this.O = 0;
                this.a((byte)1);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final void ab() {
        int n = this.F + this.T * 10;
        if (!this.E && n < this.R) {
            if (!this.a.a[this.c[n]]) {
                this.a.a[this.c[n]] = true;
                this.Q = (byte)(this.Q + 1);
                return;
            }
            this.a.a[this.c[n]] = false;
            this.Q = (byte)(this.Q - 1);
            return;
        }
        if (this.E) {
            if (this.I == 0) {
                if (this.a.P == 112) {
                    this.a.bo();
                    this.a.P = (byte)111;
                    this.a((byte)7);
                    return;
                }
                if (this.a.P != 113) return;
                this.a.bq();
                this.a.br();
                this.O = 0;
                this.D = true;
                this.d = "\u5220\u9664\u6210\u529f!";
                this.a((byte)1);
                return;
            }
            this.O = 0;
            this.a((byte)1);
            return;
        }
        if (n != this.R || this.Q <= 0) return;
        this.E = true;
    }

    private final void ac() {
        int n = this.F + this.T * 10;
        if (!this.E && n < this.R) {
            if (this.i > 0) {
                this.a.a[this.i] = false;
                if (this.c[n] != this.i) {
                    this.a.a[this.c[n]] = true;
                    this.i = this.c[n];
                    return;
                }
                this.i = 0;
                return;
            }
            this.i = this.c[n];
            this.a.a[this.i] = true;
            return;
        }
        if (this.E) {
            if (this.I == 0) {
                this.a.bp();
                this.L = (byte)16;
                this.M = (byte)16;
                this.a.P = (byte)111;
                this.a((byte)4);
                return;
            }
            this.O = 0;
            this.a((byte)1);
            return;
        }
        if (n == this.R && this.i > 0) {
            if (!this.a.b) {
                this.a.g((int)this.I);
                return;
            }
            this.E = true;
        }
    }

    private final void ad() {
        if (this.D) {
            this.D = false;
            return;
        }
        switch (this.a) {
            case 1: {
                if (this.W) {
                    if (this.ab == 0) {
                        this.b[0] = this.b[0] > 0 ? (int)(this.b[0] - 1) : 4;
                        this.a[1] = null;
                        this.a[1] = this.b(this.b[this.b[0]]);
                        return;
                    }
                    if (this.ab != 1) break;
                    this.b[1] = this.b[1] > 5 ? (int)(this.b[1] - 1) : 9;
                    this.a[1] = null;
                    this.a[1] = this.b(this.b[this.b[1]]);
                    return;
                }
                if (this.V) {
                    this.d(this.F);
                    return;
                }
                if (this.aa <= 0) break;
                this.aa = (byte)(this.aa - 1);
                return;
            }
            case 2: {
                if (this.W) {
                    if (this.ab == 0) {
                        this.b[0] = this.b[0] < 4 ? (byte)(this.b[0] + 1) : (byte)0;
                        this.a[1] = null;
                        this.a[1] = this.b(this.b[this.b[0]]);
                        return;
                    }
                    if (this.ab != 1) break;
                    this.b[1] = this.b[1] < 9 ? (int)(this.b[1] + 1) : 5;
                    this.a[1] = null;
                    this.a[1] = this.b(this.b[this.b[1]]);
                    return;
                }
                if (this.V) {
                    this.e(this.F);
                    return;
                }
                if (this.aa >= 2) break;
                this.aa = (byte)(this.aa + 1);
                return;
            }
            case 4: {
                if (this.V || this.W) {
                    return;
                }
                if (this.aa == 0) {
                    if (this.ab <= 0) break;
                    this.ab = (byte)(this.ab - 1);
                    if (this.ab == 0) {
                        this.a[1] = null;
                        this.a[1] = this.b(this.b[0]);
                        return;
                    }
                    if (this.ab != 1) break;
                    this.a[1] = null;
                    this.a[1] = this.b(this.b[5]);
                    return;
                }
                if (this.aa == 1) {
                    this.F = this.F > 0 ? (byte)(this.F - 1) : (byte)5;
                    if (this.F >= 2) break;
                    this.V = false;
                    return;
                }
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 8: {
                if (this.V || this.W) {
                    return;
                }
                if (this.aa == 0) {
                    if (this.ab < 2) {
                        this.ab = (byte)(this.ab + 1);
                        if (this.ab == 0) {
                            this.a[1] = null;
                            this.a[1] = this.b(this.b[0]);
                        } else if (this.ab == 1) {
                            this.a[1] = null;
                            this.a[1] = this.b(this.b[5]);
                        }
                    }
                    if (this.ab != 2) break;
                    byte[] byArray = this.a();
                    this.b[2] = (byte)(byArray[0] % 10);
                    this.a[1] = null;
                    this.a[1] = this.b(this.b[this.b[2]]);
                    for (int n = 0; n < 4; n = (int)((byte)(n + 1))) {
                        this.a[2][n] = byArray[n];
                    }
                    return;
                }
                if (this.aa == 1) {
                    if (this.F < 5) {
                        this.F = (byte)(this.F + 1);
                        return;
                    }
                    this.F = 0;
                    return;
                }
                this.I = (byte)(this.I ^ 1);
                return;
            }
            case 32: {
                this.a((byte)1);
                this.F = (byte)2;
                return;
            }
            case 16: {
                if (this.aa == 1) {
                    if (this.W) {
                        this.W = false;
                        return;
                    }
                    if (this.F > 1 && this.ab != 2) {
                        this.V = !this.V;
                        return;
                    }
                    if (this.F == 0) {
                        this.W = true;
                        return;
                    }
                    if (this.F != 1) break;
                    if (this.c == null) {
                        this.c = new Form("\u6b66\u5c06\u540d");
                        this.c.append((Item)this.a);
                        this.c.addCommand(this.a);
                        this.c.addCommand(this.b);
                        this.c.setCommandListener((CommandListener)this);
                    }
                    Display.getDisplay((MIDlet)this.a).setCurrent((Displayable)this.c);
                    return;
                }
                if (this.aa != 2) break;
                if (this.I == 0) {
                    if (this.h.compareTo("") == 0) {
                        this.D = true;
                        this.d = "\u6b66\u5c06\u540d\u4e0d\u80fd\u4e3a\u7a7a!";
                        return;
                    }
                    this.a("pre create OK!");
                    try {
                        Random random = new Random();
                        short s = this.a();
                        this.a("newID:" + s);
                        this.a.j[s] = s;
                        this.a.b[s] = this.h;
                        this.a.o[s] = 1;
                        this.a.p[s] = (byte)(Math.abs(random.nextInt()) % 3 + 1);
                        this.a.k[s] = 500;
                        this.a.q[s] = this.a[this.ab][2];
                        this.a.r[s] = this.a[this.ab][2];
                        this.a.s[s] = this.a[this.ab][0];
                        this.a.t[s] = this.a[this.ab][1];
                        this.a.u[s] = this.a[this.ab][3];
                        this.a.v[s] = 99;
                        this.a.l[s] = 0;
                        this.a.w[s] = 0;
                        this.a.x[s] = 24;
                        this.a.a[s] = false;
                        this.a.z[s - 229] = this.b[this.b[this.ab]];
                    }
                    catch (Exception exception) {
                        Exception exception2 = exception;
                        exception.printStackTrace();
                    }
                    this.a.br();
                    this.D = true;
                    this.d = "\u521b\u5efa\u6210\u529f!";
                    this.a((byte)1);
                    this.F = (byte)2;
                    this.a("create OK!");
                    return;
                }
                this.a((byte)1);
                this.F = (byte)2;
                return;
            }
            default: {
                return;
            }
        }
    }

    private final void Y(Graphics graphics) {
        graphics.drawImage(this.f, 0, 0, 0);
        graphics.drawImage(this.a[0], this.f >> 1, 30, 17);
        this.b(graphics, "\u81ea\u5efa\u6b66\u5c06");
        this.a = "\u8bbe\u7f6e\u81ea\u5efa\u6b66\u5c06\u53c2\u6570";
        this.d(graphics);
        if (this.aa == 0) {
            graphics.setColor(0xFFFF33);
        } else {
            graphics.setColor(0xFFFFFF);
        }
        if (this.ab < 2) {
            graphics.drawRect(31 + this.ab * 72, 70, 46, 25);
        } else {
            graphics.drawRect(167, 70, 46, 25);
        }
        graphics.drawImage(this.a[1], 34, 126, 0);
        graphics.setColor(0xFFFF33);
        graphics.drawString(String.valueOf(this.a[this.ab][4]), 143, 227, 17);
        graphics.setColor(16275794);
        for (int n = 0; n < 4; n = (int)((byte)(n + 1))) {
            graphics.drawString(String.valueOf(this.a[this.ab][n]), this.a[(n + 2) * 4] + 15, this.a[(n + 2) * 4 + 1] + 2, 17);
        }
        graphics.drawString(this.h, 177, 124, 17);
        if (this.aa == 1) {
            graphics.setColor(0xFFFF33);
            if (this.W) {
                graphics.setColor(16275794);
            }
            graphics.drawRect((int)this.a[this.F * 4], (int)this.a[this.F * 4 + 1], (int)this.a[this.F * 4 + 2], (int)this.a[this.F * 4 + 3]);
            if (this.V) {
                graphics.setColor(16275794);
                graphics.drawRect(121, 226, 43, 19);
            }
        }
        if (this.aa == 2) {
            this.a(graphics, this.I);
        }
    }
}

