package android.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.app.OplusAppInfo;
import java.util.List;

/* loaded from: classes.dex */
public class OplusActivityManager extends OplusBaseActivityManager implements IOplusActivityManager, IOplusActivityTaskManager {
    private static final String TAG = "OplusActivityManager";
    private final OplusActivityTaskManager mOplusAtm = OplusActivityTaskManager.getInstance();

    public static OplusActivityManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {
        private static final OplusActivityManager INSTANCE = new OplusActivityManager();

        private LazyHolder() {
        }
    }

    public void swapDockedFullscreenStack() throws RemoteException {
    }

    @Override
    public ComponentName getTopActivityComponentName() throws RemoteException {
        return this.mOplusAtm.getTopActivityComponentName();
    }

    @Override
    public ApplicationInfo getTopApplicationInfo() throws RemoteException {
        return this.mOplusAtm.getTopApplicationInfo();
    }

    @Override
    public List<OplusAppInfo> getAllTopAppInfos() throws RemoteException {
        return this.mOplusAtm.getAllTopAppInfos();
    }

    @Override
    public List<OplusAppInfo> getAllTopApps() throws RemoteException {
        return this.mOplusAtm.getAllTopApps();
    }

    @Override // android.app.IOplusActivityManager
    public boolean putConfigInfo(String configName, Bundle bundle, int flag, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(configName);
            data.writeBundle(bundle);
            data.writeInt(flag);
            data.writeInt(userId);
            this.mRemote.transact(10062, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return true;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public Bundle getConfigInfo(String configName, int flag, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new Bundle();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(configName);
            data.writeInt(flag);
            data.writeInt(userId);
            this.mRemote.transact(10063, data, reply, 0);
            reply.readException();
            Bundle bundle = reply.readBundle();
            return bundle;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public java.util.List<String> getGlobalPkgWhiteList(int type) throws android.os.RemoteException {
        return new java.util.ArrayList<>();
    }

    public java.util.List<String> getStageProtectListFromPkg(String callerPkg, int type) throws android.os.RemoteException {
        return new java.util.ArrayList<>();
    }

    public java.util.List<String> getStageProtectListFromPkgAsUser(String callerPkg, int type, int userId) throws android.os.RemoteException {
        return new java.util.ArrayList<>();
    }

    public java.util.List<String> getStageProtectList(int type) throws android.os.RemoteException {
        return new java.util.ArrayList<>();
    }

    public java.util.List<String> getStageProtectListAsUser(int type, int userId) throws android.os.RemoteException {
        return new java.util.ArrayList<>();
    }

    public java.util.List<String> getGlobalProcessWhiteList() throws android.os.RemoteException {
        return new java.util.ArrayList<>();
    }

    public void addStageProtectInfo(String callerPkg, String pkg, java.util.List<String> processList, String reason, long timeout, com.oplus.app.IOplusProtectConnection connection) throws android.os.RemoteException {
    }

    public void removeStageProtectInfo(String pkg, String callerPkg) throws android.os.RemoteException {
    }

    public boolean registerTaskInfoChangeListener(com.oplus.app.OplusTaskInfoChangeListener listener, int arg1, int arg2) {
        return true;
    }
}
