package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public interface IOplusTaskInfoChangeListener extends IInterface {
    public static abstract class Stub extends Binder implements IOplusTaskInfoChangeListener {
        public Stub() {
            this.attachInterface(this, "com.oplus.app.IOplusTaskInfoChangeListener");
        }
        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
