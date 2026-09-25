/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.midlet.MIDlet
 */
package sango;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class Sango
extends MIDlet {
    public final void startApp() {
        Displayable displayable = Display.getDisplay((MIDlet)this).getCurrent();
        if (displayable == null) {
            b b2 = new b(this);
            a a2 = new a();
            a2.a(b2);
            b2.b = a2;
            b2.a((byte)0);
            Display.getDisplay((MIDlet)this).setCurrent((Displayable)b2);
            a2.start();
        }
    }

    public final void pauseApp() {
    }

    public final void destroyApp(boolean bl) {
    }

    public final void a() {
        this.notifyDestroyed();
    }
}

