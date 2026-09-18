/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public final class a
implements Runnable {
    public c a;
    public OutputStream b;
    public InputStream c;
    public HttpConnection d;
    public int e;
    public int f;
    public static byte g = 0;
    public String h;
    public String i = "utf-8";
    public String j;
    public int k = 5120;
    public byte[] l;
    public byte m = 0;
    public static byte n;
    private String o = null;
    private byte[] p = null;

    public a(c c2) {
        n = (byte)-1;
        this.a = c2;
    }

    public final void a(String string, String string2, String string3) {
        this.h = string2;
        this.j = string.toUpperCase();
        this.o = string3;
        n = (byte)-1;
        this.m = 1;
        this.run();
    }

    public final void a(String string, String string2, byte[] byArray, int n, int n2) {
        this.h = string2;
        this.j = string.toUpperCase();
        this.p = byArray;
        a.n = (byte)-1;
        this.m = 1;
        this.e = n;
        this.f = n2;
        this.run();
    }

    public static final void a() {
        n = 0;
    }

    public static final void a(Exception exception) {
        exception.toString();
    }

    public final synchronized void run() {
        try {
            if (g == 1) {
                this.d = (HttpConnection)Connector.open((String)("http://10.0.0.172:80/" + a.a(this.h)));
                this.d.setRequestProperty("X-Online-Host", a.b(this.h));
                this.d.setRequestProperty("Accept", "*/*");
                this.d.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
            } else {
                this.d = (HttpConnection)Connector.open((String)this.h);
            }
            if (this.j == "POST") {
                this.d.setRequestMethod("POST");
                this.d.setRequestProperty("User-Agent", "Mozilla/4.0");
                this.d.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                this.d.setRequestProperty("Content-Length", Integer.toString(this.o.length()));
                this.b = this.d.openOutputStream();
                this.b.write(this.o.getBytes(this.i));
            } else if (this.j == "POSTBYTES") {
                this.d.setRequestMethod("POST");
                this.d.setRequestProperty("User-Agent", "Mozilla/4.0");
                this.d.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                this.b = this.d.openOutputStream();
                this.b.write(this.p, this.e, this.f);
            }
            this.b.close();
            this.b = null;
            if (this.d.getResponseCode() != 200) {
                this.a((byte)13);
                return;
            }
        }
        catch (Exception exception) {
            this.a((byte)10);
            exception.toString();
            a.a(exception);
            return;
        }
        try {
            this.c = this.d.openInputStream();
            this.d.getType();
            this.d.getLength();
            this.a(this.c);
            this.c.close();
            this.c = null;
        }
        catch (Exception exception) {
            this.a((byte)11);
            return;
        }
        try {
            this.d.close();
            this.d = null;
        }
        catch (Exception exception) {
            this.a((byte)12);
            return;
        }
        this.m = 0;
    }

    public final String b() {
        try {
            return new String(this.l, this.i);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
    }

    private final void a(InputStream inputStream) throws IOException {
        byte[] byArray = new byte[this.k];
        this.l = new byte[0];
        int n = 0;
        boolean bl = false;
        while ((n = inputStream.read(byArray)) > 0) {
            if (a.n == 2) {
                this.m = (byte)2;
                while (a.n == 2) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (Exception exception) {}
                }
            }
            if (a.n == 0) {
                this.m = 0;
                return;
            }
            int n2 = this.l.length;
            byte[] byArray2 = new byte[n2 + n];
            System.arraycopy(this.l, 0, byArray2, 0, n2);
            System.arraycopy(byArray, 0, byArray2, n2, n);
            this.l = byArray2;
        }
    }

    private static final String a(String string) {
        return string.substring(string.indexOf("/", 8) + 1);
    }

    private static final String b(String string) {
        return string.substring(7, string.indexOf("/", 8));
    }

    private final void a(byte by) {
        a.a();
        this.a.d(by);
        this.m = 0;
    }
}
