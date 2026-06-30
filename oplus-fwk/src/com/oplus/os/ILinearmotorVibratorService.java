package com.oplus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ILinearmotorVibratorService extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.os.ILinearmotorVibratorService";

    void cancelVibrate(WaveformEffect waveformEffect, IBinder iBinder) throws RemoteException;

    boolean checkRichtapBlackList(String str) throws RemoteException;

    int getSettingsNotificationEffectStrength() throws RemoteException;

    int getSettingsRingtoneEffectStrength() throws RemoteException;

    int getSettingsTouchEffectStrength() throws RemoteException;

    int getVibratorStatus() throws RemoteException;

    int getVibratorTouchStyle() throws RemoteException;

    void setVibratorStrength(int i) throws RemoteException;

    void setVibratorTouchStyle(int i) throws RemoteException;

    void updateVibrationAmplitude(int i, String str, float f) throws RemoteException;

    void vibrate(int i, String str, WaveformEffect waveformEffect, IBinder iBinder) throws RemoteException;

    public static class Default implements ILinearmotorVibratorService {
        @Override // com.oplus.os.ILinearmotorVibratorService
        public void vibrate(int uid, String opPkg, WaveformEffect we, IBinder token) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void cancelVibrate(WaveformEffect we, IBinder token) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getVibratorStatus() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void setVibratorStrength(int strength) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getSettingsTouchEffectStrength() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getSettingsRingtoneEffectStrength() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getSettingsNotificationEffectStrength() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getVibratorTouchStyle() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void setVibratorTouchStyle(int style) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void updateVibrationAmplitude(int uid, String opPkg, float amplitudeRatio) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public boolean checkRichtapBlackList(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILinearmotorVibratorService {
        static final int TRANSACTION_cancelVibrate = 2;
        static final int TRANSACTION_checkRichtapBlackList = 11;
        static final int TRANSACTION_getSettingsNotificationEffectStrength = 7;
        static final int TRANSACTION_getSettingsRingtoneEffectStrength = 6;
        static final int TRANSACTION_getSettingsTouchEffectStrength = 5;
        static final int TRANSACTION_getVibratorStatus = 3;
        static final int TRANSACTION_getVibratorTouchStyle = 8;
        static final int TRANSACTION_setVibratorStrength = 4;
        static final int TRANSACTION_setVibratorTouchStyle = 9;
        static final int TRANSACTION_updateVibrationAmplitude = 10;
        static final int TRANSACTION_vibrate = 1;

        public Stub() {
            attachInterface(this, ILinearmotorVibratorService.DESCRIPTOR);
        }

        public static ILinearmotorVibratorService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILinearmotorVibratorService.DESCRIPTOR);
            if (iin != null && (iin instanceof ILinearmotorVibratorService)) {
                return (ILinearmotorVibratorService) iin;
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
                    return "vibrate";
                case 2:
                    return "cancelVibrate";
                case 3:
                    return "getVibratorStatus";
                case 4:
                    return "setVibratorStrength";
                case 5:
                    return "getSettingsTouchEffectStrength";
                case 6:
                    return "getSettingsRingtoneEffectStrength";
                case 7:
                    return "getSettingsNotificationEffectStrength";
                case 8:
                    return "getVibratorTouchStyle";
                case 9:
                    return "setVibratorTouchStyle";
                case 10:
                    return "updateVibrationAmplitude";
                case 11:
                    return "checkRichtapBlackList";
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
                data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(ILinearmotorVibratorService.DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    int _arg0 = data.readInt();
                    String _arg1 = data.readString();
                    WaveformEffect _arg2 = (WaveformEffect) data.readTypedObject(WaveformEffect.CREATOR);
                    IBinder _arg3 = data.readStrongBinder();
                    data.enforceNoDataAvail();
                    vibrate(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 2:
                    WaveformEffect _arg02 = (WaveformEffect) data.readTypedObject(WaveformEffect.CREATOR);
                    IBinder _arg12 = data.readStrongBinder();
                    data.enforceNoDataAvail();
                    cancelVibrate(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    int _result = getVibratorStatus();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 4:
                    int _arg03 = data.readInt();
                    data.enforceNoDataAvail();
                    setVibratorStrength(_arg03);
                    reply.writeNoException();
                    return true;
                case 5:
                    int _result2 = getSettingsTouchEffectStrength();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 6:
                    int _result3 = getSettingsRingtoneEffectStrength();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 7:
                    int _result4 = getSettingsNotificationEffectStrength();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 8:
                    int _result5 = getVibratorTouchStyle();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 9:
                    int _arg04 = data.readInt();
                    data.enforceNoDataAvail();
                    setVibratorTouchStyle(_arg04);
                    reply.writeNoException();
                    return true;
                case 10:
                    int _arg05 = data.readInt();
                    String _arg13 = data.readString();
                    float _arg22 = data.readFloat();
                    data.enforceNoDataAvail();
                    updateVibrationAmplitude(_arg05, _arg13, _arg22);
                    reply.writeNoException();
                    return true;
                case 11:
                    String _arg06 = data.readString();
                    data.enforceNoDataAvail();
                    boolean _result6 = checkRichtapBlackList(_arg06);
                    reply.writeNoException();
                    reply.writeBoolean(_result6);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ILinearmotorVibratorService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILinearmotorVibratorService.DESCRIPTOR;
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void vibrate(int uid, String opPkg, WaveformEffect we, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(opPkg);
                    _data.writeTypedObject(we, 0);
                    _data.writeStrongBinder(token);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void cancelVibrate(WaveformEffect we, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeTypedObject(we, 0);
                    _data.writeStrongBinder(token);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getVibratorStatus() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void setVibratorStrength(int strength) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(strength);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getSettingsTouchEffectStrength() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getSettingsRingtoneEffectStrength() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getSettingsNotificationEffectStrength() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getVibratorTouchStyle() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void setVibratorTouchStyle(int style) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(style);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void updateVibrationAmplitude(int uid, String opPkg, float amplitudeRatio) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(opPkg);
                    _data.writeFloat(amplitudeRatio);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public boolean checkRichtapBlackList(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeString(packageName);
                    this.mRemote.transact(11, _data, _reply, 0);
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
            return 10;
        }
    }
}
