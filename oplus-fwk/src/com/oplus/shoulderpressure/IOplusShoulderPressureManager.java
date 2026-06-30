package com.oplus.shoulderpressure;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IOplusShoulderPressureManager extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.shoulderpressure.IOplusShoulderPressureManager";

    boolean isShoulderPressureNodeSupport(int i, int i2) throws RemoteException;

    String readShoulderPressureNodeFile(int i) throws RemoteException;

    String readShoulderPressureNodeFileByDevice(int i, int i2) throws RemoteException;

    boolean writeShoulderPressureNodeFile(int i, String str) throws RemoteException;

    boolean writeShoulderPressureNodeFileByDevice(int i, int i2, String str) throws RemoteException;

    public static class Default implements IOplusShoulderPressureManager {
        @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
        public String readShoulderPressureNodeFile(int nodeFlag) throws RemoteException {
            return null;
        }

        @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
        public String readShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag) throws RemoteException {
            return null;
        }

        @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
        public boolean writeShoulderPressureNodeFile(int nodeFlag, String info) throws RemoteException {
            return false;
        }

        @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
        public boolean writeShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag, String info) throws RemoteException {
            return false;
        }

        @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
        public boolean isShoulderPressureNodeSupport(int deviceId, int nodeFlag) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusShoulderPressureManager {
        static final int TRANSACTION_isShoulderPressureNodeSupport = 5;
        static final int TRANSACTION_readShoulderPressureNodeFile = 1;
        static final int TRANSACTION_readShoulderPressureNodeFileByDevice = 2;
        static final int TRANSACTION_writeShoulderPressureNodeFile = 3;
        static final int TRANSACTION_writeShoulderPressureNodeFileByDevice = 4;

        public Stub() {
            attachInterface(this, IOplusShoulderPressureManager.DESCRIPTOR);
        }

        public static IOplusShoulderPressureManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusShoulderPressureManager.DESCRIPTOR);
            if (iin != null && (iin instanceof IOplusShoulderPressureManager)) {
                return (IOplusShoulderPressureManager) iin;
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
                    return "readShoulderPressureNodeFile";
                case 2:
                    return "readShoulderPressureNodeFileByDevice";
                case 3:
                    return "writeShoulderPressureNodeFile";
                case 4:
                    return "writeShoulderPressureNodeFileByDevice";
                case 5:
                    return "isShoulderPressureNodeSupport";
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
                data.enforceInterface(IOplusShoulderPressureManager.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(IOplusShoulderPressureManager.DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    int _arg0 = data.readInt();
                    data.enforceNoDataAvail();
                    String _result = readShoulderPressureNodeFile(_arg0);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case 2:
                    int _arg02 = data.readInt();
                    int _arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    String _result2 = readShoulderPressureNodeFileByDevice(_arg02, _arg1);
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 3:
                    int _arg03 = data.readInt();
                    String _arg12 = data.readString();
                    data.enforceNoDataAvail();
                    boolean _result3 = writeShoulderPressureNodeFile(_arg03, _arg12);
                    reply.writeNoException();
                    reply.writeBoolean(_result3);
                    return true;
                case 4:
                    int _arg04 = data.readInt();
                    int _arg13 = data.readInt();
                    String _arg2 = data.readString();
                    data.enforceNoDataAvail();
                    boolean _result4 = writeShoulderPressureNodeFileByDevice(_arg04, _arg13, _arg2);
                    reply.writeNoException();
                    reply.writeBoolean(_result4);
                    return true;
                case 5:
                    int _arg05 = data.readInt();
                    int _arg14 = data.readInt();
                    data.enforceNoDataAvail();
                    boolean _result5 = isShoulderPressureNodeSupport(_arg05, _arg14);
                    reply.writeNoException();
                    reply.writeBoolean(_result5);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOplusShoulderPressureManager {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusShoulderPressureManager.DESCRIPTOR;
            }

            @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
            public String readShoulderPressureNodeFile(int nodeFlag) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusShoulderPressureManager.DESCRIPTOR);
                    _data.writeInt(nodeFlag);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
            public String readShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusShoulderPressureManager.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(nodeFlag);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
            public boolean writeShoulderPressureNodeFile(int nodeFlag, String info) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusShoulderPressureManager.DESCRIPTOR);
                    _data.writeInt(nodeFlag);
                    _data.writeString(info);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
            public boolean writeShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag, String info) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusShoulderPressureManager.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(nodeFlag);
                    _data.writeString(info);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.shoulderpressure.IOplusShoulderPressureManager
            public boolean isShoulderPressureNodeSupport(int deviceId, int nodeFlag) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusShoulderPressureManager.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(nodeFlag);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public int getMaxTransactionId() {
            return 4;
        }
    }
}
