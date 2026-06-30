package android.app;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.oplus.app.IOplusProtectConnection;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class OplusWhiteListManager {
    private static final boolean DEBUG = false;
    private static final long MAX_PROTECT_SELF_TIMEOUT = 10800000;
    private static final long MIN_PROTECT_SELF_TIMEOUT = 60000;
    private static final String REASON_LEGACY = "legacy_call";
    private static final String TAG = "OplusWhiteListManager";
    public static final int TYPE_DEEPSLEEP = 6;
    public static final int TYPE_FILTER_ALL_SELF_PROTECT_PKG = 11;
    public static final int TYPE_FILTER_CLEAR = 1;
    public static final int TYPE_FILTER_PERMISSION = 2;
    public static final int TYPE_FILTER_SELF_PROTECT_PKG = 12;
    public static final int TYPE_FILTER_SELF_PROTECT_PROC = 13;
    public static final int TYPE_FLOATWINDOW = 5;
    public static final int TYPE_FLOATWINDOW_DEFAULT_GRANT_BUILDIN = 4;
    public static final int TYPE_MIRAGE_DISPLAY = 101;
    public static final int TYPE_NO_CLEAR_NOTIFICATION = 10;
    public static final int TYPE_SCREENOFF_AUDIO_IN = 9;
    public static final int TYPE_SCREENOFF_AUDIO_OUT = 7;
    public static final int TYPE_SCREENOFF_POSSIBLE_AUDIO_OUT = 8;
    public static final int TYPE_SPECIFIG_PKG_PROTECT = 3;
    private Context mContext;
    private OplusActivityManager mOplusActivityManager = new OplusActivityManager();

    public OplusWhiteListManager(Context context) {
        this.mContext = context;
    }

    public ArrayList<String> getGlobalWhiteList() {
        return getGlobalWhiteList(1);
    }

    public ArrayList<String> getGlobalWhiteList(int type) {
        ArrayList<String> resultList = new ArrayList<>();
        try {
            List<String> tempList = this.mOplusActivityManager.getGlobalPkgWhiteList(type);
            if (!ArrayUtils.isEmpty(tempList)) {
                resultList.addAll(tempList);
            }
        } catch (RemoteException e) {
        }
        return resultList;
    }

    public ArrayList<String> getStageProtectListFromPkg(String callerPkg, int type) {
        ArrayList<String> resultList = new ArrayList<>();
        try {
            List<String> tempList = this.mOplusActivityManager.getStageProtectListFromPkg(callerPkg, type);
            if (!ArrayUtils.isEmpty(tempList)) {
                resultList.addAll(tempList);
            }
        } catch (RemoteException e) {
        }
        return resultList;
    }

    public List<String> getStageProtectListFromPkgAsUser(String callerPkg, int type, int userId) {
        List<String> resultList = new ArrayList<>();
        try {
            List<String> tempList = this.mOplusActivityManager.getStageProtectListFromPkgAsUser(callerPkg, type, userId);
            if (!ArrayUtils.isEmpty(tempList)) {
                resultList.addAll(tempList);
            }
        } catch (RemoteException e) {
        }
        return resultList;
    }

    public List<String> getStageProtectList(int type) {
        List<String> resultList = new ArrayList<>();
        try {
            List<String> tempList = this.mOplusActivityManager.getStageProtectList(type);
            if (!ArrayUtils.isEmpty(tempList)) {
                resultList.addAll(tempList);
            }
        } catch (RemoteException e) {
        }
        return resultList;
    }

    public List<String> getStageProtectListAsUser(int type, int userId) {
        List<String> resultList = new ArrayList<>();
        try {
            List<String> tempList = this.mOplusActivityManager.getStageProtectListAsUser(type, userId);
            if (!ArrayUtils.isEmpty(tempList)) {
                resultList.addAll(tempList);
            }
        } catch (RemoteException e) {
        }
        return resultList;
    }

    public List<String> getGlobalProcessWhiteList() {
        List<String> resultList = new ArrayList<>();
        try {
            List<String> tempList = this.mOplusActivityManager.getGlobalProcessWhiteList();
            if (!ArrayUtils.isEmpty(tempList)) {
                resultList.addAll(tempList);
            }
        } catch (RemoteException e) {
        }
        return resultList;
    }

    @Deprecated
    public void addStageProtectInfo(String pkg, long timeout) {
        addStageProtectInfo(pkg, REASON_LEGACY, timeout, null);
    }

    public void addStageProtectInfo(String pkg, String reason, long timeout, IOplusProtectConnection connection) {
        addStageProtectInfo(pkg, null, reason, timeout, connection);
    }

    public void addStageProtectInfo(String pkg, List<String> processList, String reason, long timeout, IOplusProtectConnection connection) {
        if (TextUtils.isEmpty(reason)) {
            throw new IllegalArgumentException("reason is null or length equals 0");
        }
        if (timeout <= 0) {
            if (REASON_LEGACY.equals(reason)) {
                Log.w(TAG, "timeout <= 0 ");
                return;
            }
            throw new IllegalArgumentException("timeout <= 0");
        }
        try {
            this.mOplusActivityManager.addStageProtectInfo(this.mContext.getPackageName(), pkg, processList, reason, timeout, connection);
        } catch (Exception e) {
            Log.e(TAG, "Error adding stage protect ", e);
        }
    }

    public void removeStageProtectInfo(String pkg) {
        if (TextUtils.isEmpty(pkg)) {
            Log.e(TAG, "can't add empty info to protect info");
            return;
        }
        try {
            this.mOplusActivityManager.removeStageProtectInfo(pkg, this.mContext.getPackageName());
        } catch (Exception e) {
            Log.e(TAG, "Error remove stage protect ", e);
        }
    }
}
