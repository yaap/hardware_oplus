package com.oplus.view;

import android.content.Context;
import java.util.ArrayList;

/* Stub implementation - all OxygenOS-specific telemetry removed */
public class JankManager implements IJankManager {

    private static JankManager sInstance;

    public static JankManager getInstance() {
        if (sInstance == null) {
            sInstance = new JankManager();
        }
        return sInstance;
    }

    @Override
    public void updateAppSceneAndAction(String packageName, int scene, int action) {}

    @Override
    public int getAppScene(String packageName) { return -1; }

    @Override
    public int getAppAction(String packageName) { return -1; }

    @Override
    public void latencySceneBegin(Context context, int scene, String sceneDes, long latencyThresholdMs) {}

    @Override
    public void latencySceneBegin(Context context, IJankManager.SceneInfo sceneInfo) {}

    @Override
    public void latencySceneEnd(Context context, int scene, ArrayList<String> latencyInfo) {}

    @Override
    public void asyncLatencySceneBegin(IJankManager.SceneInfo sceneInfo) {}

    @Override
    public void asyncLatencySceneEnd(String packageName, int scene, int policy, String appendMessage) {}

    @Override
    public void updateFaultThresholdMs(String packageName, int faultType, int threshold) {}

    @Override
    public void ssAnimSceneBegin(int scene, String sceneDes, int skippedFrameNum, long duration) {}

    @Override
    public void ssAnimSceneEnd(int scene, boolean normal) {}

    @Override
    public void gfxSceneBegin(Context context, int scene, String sceneDes, long policy) {}

    @Override
    public void gfxSceneBegin(Context context, IJankManager.SceneInfo sceneInfo) {}

    @Override
    public void gfxSceneEnd(Context context, int scene) {}

    @Override
    public int getSceneType() { return -1; }

    @Override
    public int getScene() { return -1; }

    @Override
    public long getSceneBeginTime() { return -1L; }

    @Override
    public String getSceneDescription() { return null; }

    @Override
    public long getLatencyThresholdNs() { return -1L; }

    @Override
    public long getAnimCostThreshold() { return -1L; }

    @Override
    public int getSkippedAnimFrame() { return -1; }

    @Override
    public long getSkippedFrameThreshold() { return -1L; }

    @Override
    public int getSkippedFrame() { return -1; }

    @Override
    public long getScenePolicy() { return -1L; }

    @Override
    public boolean isSceneEnabled(int sceneType, int scene) { return false; }

    @Override
    public void onAnimationUpdate() {}

    @Override
    public void notifyActivityStarted(String packageName, String activityName, long longVersionCode) {}

    @Override
    public void notifyAddStartingWindow(String pkgName, String activityName, String launchFromPkg, int reasonCode) {}

    @Override
    public void startActivity(String pkgName, String activityName) {}

    @Override
    public void finishDrawing(String pkgName, String activityName) {}

    @Override
    public void startRemoteAnimation(String pkgName, String activityName) {}

    @Override
    public void requestStartTransition(int type, int scene, String sceneDes, long latencyThresholdMs, int syncId, boolean hasRemoteTransition) {}

    @Override
    public void requestEndTransition(int syncId) {}

    @Override
    public void slideSceneBegin(Context context, IJankManager.SceneInfo sceneInfo) {}

    @Override
    public void slideSceneEnd(Context context, int scene) {}

    @Override
    public int getSlideSceneType() { return -1; }

    @Override
    public int getSlideScene() { return -1; }

    @Override
    public long getSlideScenePolicy() { return -1L; }

    @Override
    public long getSlideSceneBeginTime() { return -1L; }

    @Override
    public int getSlideSceneSkippedFrame() { return -1; }

    @Override
    public long getSlideSceneThreshold() { return -1L; }
}
