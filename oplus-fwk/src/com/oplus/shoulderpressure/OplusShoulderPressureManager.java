package com.oplus.shoulderpressure;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oplus.shoulderpressure.IOplusShoulderPressureManager;
import com.oplus.wrapper.os.ServiceManager;
import com.oplus.wrapper.os.SystemProperties;

/* loaded from: classes.dex */
public class OplusShoulderPressureManager {
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final int HALL_LEFT_PRESS_GEAR = 124;
    public static final int HALL_RIGHT_PRESS_GEAR = 125;
    public static final int MECHAICAL_KEY_PARAMS = 127;
    public static final int PRESS_CHARGE_STATE = 128;
    public static final int PRESS_GAME_SWITCH_ENABLE = 126;
    private static final String TAG = "OplusShoulderPressureManager";
    private IOplusShoulderPressureManager mOplusShoulderPressureManager;

    public OplusShoulderPressureManager() {
        IBinder binder = ServiceManager.getService("OplusShoulderPressureManagerService");
        if (binder == null) {
            Log.d(TAG, "OplusShoulderPressureManagerService is null");
        } else if (this.mOplusShoulderPressureManager != IOplusShoulderPressureManager.Stub.asInterface(binder)) {
            this.mOplusShoulderPressureManager = IOplusShoulderPressureManager.Stub.asInterface(binder);
        }
    }

    public String readShoulderPressureNodeFile(int nodeFlag) {
        if (DEBUG) {
            Log.d(TAG, "readShoulderPressureNodeFile, uid: " + Binder.getCallingUid() + ", pid: " + Binder.getCallingPid() + ", nodeFlag: " + nodeFlag);
        }
        try {
            return this.mOplusShoulderPressureManager.readShoulderPressureNodeFile(nodeFlag);
        } catch (RemoteException e) {
            Log.e(TAG, "readShoulderPressureNodeFile failed, err: " + e);
            return null;
        }
    }

    public String readShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag) {
        if (DEBUG) {
            Log.d(TAG, "readShoulderPressureNodeFileByDevice, uid: " + Binder.getCallingUid() + ", pid: " + Binder.getCallingPid() + ", nodeFlag: " + nodeFlag + ", deviceId :" + deviceId);
        }
        try {
            return this.mOplusShoulderPressureManager.readShoulderPressureNodeFileByDevice(deviceId, nodeFlag);
        } catch (RemoteException e) {
            Log.e(TAG, "readShoulderPressureNodeFileByDevice failed, err: " + e + ", deviceId :" + deviceId);
            return null;
        }
    }

    public boolean writeShoulderPressureNodeFile(int nodeFlag, String info) {
        boolean zWriteShoulderPressureNodeFile;
        if (DEBUG) {
            Log.d(TAG, "writeShoulderPressureNodeFile, uid: " + Binder.getCallingUid() + ", pid: " + Binder.getCallingPid() + ", nodeFlag: " + nodeFlag + ", info: " + info);
        }
        try {
            synchronized (OplusShoulderPressureManager.class) {
                zWriteShoulderPressureNodeFile = this.mOplusShoulderPressureManager.writeShoulderPressureNodeFile(nodeFlag, info);
            }
            return zWriteShoulderPressureNodeFile;
        } catch (RemoteException e) {
            Log.e(TAG, "writeShoulderPressureNodeFile failed, err: " + e);
            return false;
        }
    }

    public boolean writeShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag, String info) {
        boolean zWriteShoulderPressureNodeFileByDevice;
        if (DEBUG) {
            Log.d(TAG, "writeShoulderPressureNodeFileByDevice, uid: " + Binder.getCallingUid() + ", pid: " + Binder.getCallingPid() + ", nodeFlag: " + nodeFlag + ", info: " + info);
        }
        try {
            synchronized (OplusShoulderPressureManager.class) {
                zWriteShoulderPressureNodeFileByDevice = this.mOplusShoulderPressureManager.writeShoulderPressureNodeFileByDevice(deviceId, nodeFlag, info);
            }
            return zWriteShoulderPressureNodeFileByDevice;
        } catch (RemoteException e) {
            Log.e(TAG, "writeShoulderPressureNodeFileByDevice failed, err: " + e);
            return false;
        }
    }

    public boolean isShoulderPressureNodeSupport(int deviceId, int nodeFlag) {
        if (mOplusShoulderPressureManager == null) return false;
        if (DEBUG) {
            Log.d(TAG, "isShoulderPressureNodeSupport, uid: " + Binder.getCallingUid() + ", pid: " + Binder.getCallingPid() + ", nodeFlag: " + nodeFlag + ", deviceId :" + deviceId);
        }
        try {
            return this.mOplusShoulderPressureManager.isShoulderPressureNodeSupport(deviceId, nodeFlag);
        } catch (RemoteException e) {
            Log.e(TAG, "isShoulderPressureNodeSupport failed, err: " + e + ", deviceId :" + deviceId);
            return false;
        }
    }
}
