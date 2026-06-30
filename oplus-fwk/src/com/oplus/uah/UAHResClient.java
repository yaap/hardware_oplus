package com.oplus.uah;

import android.view.MotionEvent;
import com.oplus.uah.info.UAHEventRequest;
import com.oplus.uah.info.UAHResRequest;
import com.oplus.uah.info.UAHRuleCtrlRequest;

public class UAHResClient {

    private UAHResClient(Class cls) {
        // No-op
    }

    public static UAHResClient get(Class cls) {
        return new UAHResClient(cls);
    }

    public void release(int handle) {
        // No-op
    }

    // The gallery calls these:
    public int acquireEvent(UAHEventRequest request) {
        return 0; // success
    }

    public int acquireResource(UAHResRequest request) {
        return 0;
    }

    public void ctrlRule(UAHRuleCtrlRequest request) {
        // No-op
    }

    public int getModeStatus(int mode) {
        return 0;
    }

    public String getResState(int opCode) {
        return null;
    }

    public long[][][] getHistory() {
        return null;
    }

    public void sendFling(MotionEvent ev, int duration) {
        // Gallery calls this for fling gestures – we ignore it
    }
}
