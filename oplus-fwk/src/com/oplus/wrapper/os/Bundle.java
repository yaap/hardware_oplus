package com.oplus.wrapper.os;

/* JADX INFO: loaded from: classes2.dex */
public class Bundle {
    private final android.os.Bundle mBundle;

    public Bundle(android.os.Bundle bundle) {
        this.mBundle = bundle;
    }

    public static void putObject(android.os.Bundle target, String key, Object value) {
        target.putObject(key, value);
    }

    public static int getSize(android.os.Bundle target) {
        return target.getSize();
    }

    public void putObject(String key, Object value) {
        this.mBundle.putObject(key, value);
    }

    public int getSize() {
        return this.mBundle.getSize();
    }
}
