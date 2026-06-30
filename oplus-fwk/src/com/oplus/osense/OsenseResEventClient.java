package com.oplus.osense;

import android.content.Context;
import com.oplus.osense.eventinfo.EventConfig;
import com.oplus.osense.eventinfo.OsenseEventCallback;
import android.os.Bundle;
import com.oplus.osense.task.BgRunningCallback;

public class OsenseResEventClient {

    private static OsenseResEventClient sInstance;

    public static OsenseResEventClient getInstance() {
        if (sInstance == null) {
            sInstance = new OsenseResEventClient();
        }
        return sInstance;
    }

    public int registerEventCallback(OsenseEventCallback callback, EventConfig eventConfig) {
        return 0;
    }

    public int unregisterEventCallback(OsenseEventCallback callback, EventConfig eventConfig) {
        return 0;
    }

    public void requestSceneAction(Bundle bundle) {
        return;
    }

    public int requestInstantCpuLoad() {
        return 0;
    }

    public void startBackgroundRunning(Context context, int type, BgRunningCallback callback) {
    }

    public boolean stopBackgroundRunning(Context context, int type) {
        return false;
    }
}
