/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.osense;

import android.os.Bundle;
import android.view.MotionEvent;

public class OsenseResClient {
    public static OsenseResClient get(Class clazz) {
        return new OsenseResClient();
    }

    public void requestSysResource(int eventId, Bundle extra) {}

    public void releaseSysResource(int eventId) {}

    public long osenseSetSceneAction(Object request) {
        return 0L;
    }

    public void osenseClrSceneAction(long handle) {}

    public void osenseSetNotification(Object request) {}

    public void osenseSetCtrlData(Object request) {}

    public void osenseClrCtrlData() {}

    public void reportKeyThread(String threadName, int tid, int supportedEventId, Bundle extra) {}

    public void removeKeyThread(int tid, Bundle extra) {}

    public void removeKeyThread(String threadName, Bundle extra) {}

    public void setHookKeyThread(String packageName, String threadName, int op, int pid, int[] tids) {}

    public void reportEvent(int eventId, Bundle extra) {}

    public void registerScene(int sceneId, Object listener) {}

    public void unregisterScene(int sceneId, Object listener) {}

    public int osenseGetModeStatus(int mode) {
        return 0;
    }

    public long[][][] osenseGetPerfLimit() {
        return null;
    }

    public void osenseSendFling(MotionEvent ev, int duration) {}
}
