package android.hardware.camera2;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.Context;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public interface IOplusCameraManager extends IOplusCommonFeature {
    public static final IOplusCameraManager DEFAULT = new IOplusCameraManager() { // from class: android.hardware.camera2.IOplusCameraManager.1
    };
    public static final String NAME = "IOplusCameraManager";

    public enum Cmd {
        CMD_NONE,
        CMD_PRE_CAPTURE,
        CMD_PRE_OPEN,
        CMD_PRE_EVLIST,
        CMD_READ_MEM,
        CMD_PRE_PRO_MANUAL_WB,
        CMD_UI_EVENT,
        CMD_PRE_CLOSE,
        CMD_CAMERA_CONTROL,
        CMD_OPLUS_SEND_RUS_CONFIG_TO_HAL,
        CMD_OPLUS_FORCE_FALLBACK,
        CMD_OPLUS_FORCE_EIS,
        CMD_OPLUS_OPERATION_MODE,
        CMD_OPLUS_PRO_COLOR,
        CMD_OPLUS_FORCE_FPS,
        CMD_OPLUS_HCS,
        CMD_OPLUS_IO_PRIORITY,
        CMD_OPLUS_FORCE_DCG,
        CMD_OPLUS_FORCE_AINR
    }

    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusCameraManager;
    }

    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default void addAuthResultInfo(Context context, int uid, int pid, int permBits, String packageName) {
    }

    default void setDeathRecipient(IBinder client) {
    }

    default boolean isAuthedClient(Context context) {
        return false;
    }

    default void preOpenCamera(Context context) {
    }

    default void sendOplusExtCamCmd(Context context, Cmd cmd, int[] param) {
    }

    default void setCallInfo() {
    }

    default void saveOpPackageName(String packageName) {
    }

    default void setPackageName() {
    }

    default boolean isPrivilegedApp(String packageName) {
        return false;
    }

    default boolean isCameraUnitSession() {
        return false;
    }

    default void parseSessionParameters(CaptureRequest sessionParams) {
    }
}
