package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IOplusProtectConnection extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusProtectConnection";

    void onError(int i) throws RemoteException;

    void onSuccess() throws RemoteException;

    void onTimeout() throws RemoteException;

    public static class Default implements IOplusProtectConnection {
        @Override // com.oplus.app.IOplusProtectConnection
        public void onSuccess() throws RemoteException {
        }

        @Override // com.oplus.app.IOplusProtectConnection
        public void onError(int errorCode) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusProtectConnection
        public void onTimeout() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusProtectConnection {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onSuccess = 1;
        static final int TRANSACTION_onTimeout = 3;

        public Stub() {
            attachInterface(this, IOplusProtectConnection.DESCRIPTOR);
        }

        public static IOplusProtectConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusProtectConnection.DESCRIPTOR);
            if (iin != null && (iin instanceof IOplusProtectConnection)) {
                return (IOplusProtectConnection) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSuccess";
                case 2:
                    return "onError";
                case 3:
                    return "onTimeout";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IOplusProtectConnection.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(IOplusProtectConnection.DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    onSuccess();
                    return true;
                case 2:
                    int _arg0 = data.readInt();
                    data.enforceNoDataAvail();
                    onError(_arg0);
                    return true;
                case 3:
                    onTimeout();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOplusProtectConnection {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusProtectConnection.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusProtectConnection
            public void onSuccess() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IOplusProtectConnection.DESCRIPTOR);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusProtectConnection
            public void onError(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IOplusProtectConnection.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusProtectConnection
            public void onTimeout() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IOplusProtectConnection.DESCRIPTOR);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public int getMaxTransactionId() {
            return 2;
        }
    }
}
