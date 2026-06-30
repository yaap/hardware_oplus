package com.oplus.view;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.Context;
import java.util.ArrayList;

public interface IJankManager extends IOplusCommonFeature {
    public static final IJankManager DEFAULT = new IJankManager() {};
    public static final int MS_TO_NS = 1000000;
    public static final String NAME = "IJankManager";
    public static final int TASK_OPERATION_LATENCY_THRESHOLD = 435;
    public static final int TRANSIT_CLOSE = 2;
    public static final int TRANSIT_OPEN = 1;
    public static final int TRANSIT_TO_BACK = 4;
    public static final int TRANSIT_TO_FRONT = 3;

    public static class Action {
        public static final int ACTION_END = 0;
        public static final int ACTION_START = 1;
    }

    public static final class JPolicy {
        public static final long FLING_TYPE = 128;
        public static final long IGNORE_NEXT_SCENE_FLAG = 256;
        public static final long IGNORE_TIMEOUT = 32;
        public static final long MOVE_TYPE = 64;
        public static final long OVERRIDE_BY_NEXT_SCENE_FLAG = 16;
        public static final long USD_SELF_TIME_FLAG = 2;
        public static final long USE_DOWN_EVENT_FLAG = 4;
        public static final long USE_UP_EVENT_FLAG = 8;
    }

    public static class LauncherScene {
        public static final int APP_START_ANIM = 1;
        public static final int BACK_TO_LAUNCHER_ANIM = 2;
        public static final int ENTER_RECENT_TASK_ANIM_FROM_APP = 4;
        public static final int ENTER_RECENT_TASK_ANIM_FROM_LAUNCHER = 3;
        public static final int LAUNCH_ANIM_FROM_RECENT = 6;
        public static final int OUT_RECENT_TASK_ANIM = 5;
    }

    public static final class SsOperationScene {
        public static final int TASK_OPERATION_LATENCY = 1;
        public static final String TASK_OPERATION_LATENCY_DESC = "app_transition_latency";
    }

    public static final class FaultType {
        public static final int ACTIVITY_LAUNCH_FROM_LAUNCHER = 1;
        public static final int ACTIVITY_LAUNCH_WITHIN_APP = 2;
        public static final int ACTIVITY_RESPOND_WITHIN_APP = 3;
        FaultType() {}
    }

    public static final class SideGestureSceneId {
        public static final int BACK_TO_LAST_ACTIVITY = 1;
        public static final int BACK_TO_LAUNCHER = 2;
        SideGestureSceneId() {}
    }

    public static final class CameraSceneId {
        public static final int ENTER_PHOTO = 200;
        public static final int EXIT_PHOTO = 201;
        CameraSceneId() {}
    }

    public static final class AppSceneType {
        public static final int SCENE_TYPE_GFX_FRAME = 2;
        public static final int SCENE_TYPE_LATENCY = 0;
        public static final int SCENE_TYPE_SLIDE = 3;
        public static final int SCENE_TYPE_SS_ANIM = 1;
        public static boolean isInvalidType(int sceneType) {
            return sceneType < 0 || sceneType > 2;
        }
    }

    public enum SceneIndex {
        LIST_VIEW_SLIDE(1000), VIDEO_SLIDE(1001), WEBVIEW_SLIDE(1002), FLUTTER_VIEW_SLIDE(1003);
        int mValue;
        SceneIndex(int value) { this.mValue = value; }
        public int getValue() { return this.mValue; }
    }

    public enum SceneDescription {
        LIST_VIEW_SLIDE("listview_slide"), VIDEO_SLIDE("video_slide"),
        WEBVIEW_SLIDE("webView_slide"), FLUTTER_VIEW_SLIDE("flutterView_slide");
        String mDescription;
        SceneDescription(String description) { this.mDescription = description; }
        public String getDescription() { return this.mDescription; }
    }

    public static final class AppLatencyPolicy {
        public static final int SCENE_APPEND = 4;
        public static final int SCENE_BEGIN = 2;
        public static final int SCENE_END = 8;
        public static final int SCENE_EXIT = 16;
        AppLatencyPolicy() {}
    }

    public static final class SceneInfo {
        private long mAnimCostThreshold;
        private long mLatencyThresholdNs;
        private String mPackageName;
        private long mPolicy;
        private int mPreCount;
        private int mScene;
        private long mSceneBeginTime;
        private String mSceneDescription;
        private int mSceneType;
        private int mSkippedAnimFrame;
        private int mSkippedFrame;
        private long mSkippedFrameThreshold;
        private int mTransitionSyncId;

        public SceneInfo() {
            this.mSceneType = -1;
            this.mScene = -1;
            this.mAnimCostThreshold = 0L;
            this.mPreCount = 0;
            this.mTransitionSyncId = 0;
        }

        protected SceneInfo(int sceneType, int scene, String sceneDes, long animCostThreshold, int skippedAnimFrame) {
            this.mSceneType = -1; this.mScene = -1; this.mAnimCostThreshold = 0L;
            this.mPreCount = 0; this.mTransitionSyncId = 0;
            this.mSceneType = sceneType; this.mScene = scene;
            this.mSceneDescription = sceneDes; this.mAnimCostThreshold = animCostThreshold;
            this.mSkippedAnimFrame = skippedAnimFrame; this.mSceneBeginTime = System.nanoTime();
        }

        public void initBasicInfo(Context context, int sceneType, int scene, String sceneDes) {
            if (context != null) this.mPackageName = context.getPackageName();
            this.mSceneType = sceneType; this.mScene = scene; this.mSceneDescription = sceneDes;
        }

        public void updateThreshold(long latencyThresholdNs, long animCostThreshold, int skippedAnimFrame, long skippedFrameThreshold, int skippedFrame, long policy) {
            this.mLatencyThresholdNs = latencyThresholdNs; this.mAnimCostThreshold = animCostThreshold;
            this.mSkippedAnimFrame = skippedAnimFrame; this.mSkippedFrameThreshold = skippedFrameThreshold;
            this.mSkippedFrame = skippedFrame; this.mPolicy = policy;
        }

        public void reset() {
            this.mScene = -1; this.mSceneBeginTime = 0L; this.mSceneDescription = null;
            this.mLatencyThresholdNs = 0L; this.mAnimCostThreshold = 0L; this.mSkippedAnimFrame = 0;
            this.mSkippedFrameThreshold = 0L; this.mSkippedFrame = 0; this.mPreCount = 0;
            this.mTransitionSyncId = 0; this.mPolicy = 0L;
        }

        public void resetSceneType() { this.mSceneType = -1; }
        public String getPackageName() { return this.mPackageName; }
        public void setPackageName(String mPackageName) { this.mPackageName = mPackageName; }
        public void updateSceneBeginTime() { this.mSceneBeginTime = System.nanoTime(); }
        public void setLatencyThresholdMs(long latencyThresholdMs) { this.mLatencyThresholdNs = 1000000 * latencyThresholdMs; }
        public void setPolicy(long policy) { this.mPolicy = policy; }
        public void setAnimCostThreshold(long mAnimCostThreshold) { this.mAnimCostThreshold = mAnimCostThreshold; }
        public synchronized int getSceneType() { return this.mSceneType; }
        public synchronized int getScene() { return this.mScene; }
        public synchronized long getSceneBeginTime() { return this.mSceneBeginTime; }
        public synchronized String getSceneDescription() { return this.mSceneDescription; }
        public synchronized long getLatencyThresholdNs() { return this.mLatencyThresholdNs; }
        public synchronized long getAnimCostThreshold() { return this.mAnimCostThreshold; }
        public synchronized int getSkippedAnimFrame() { return this.mSkippedAnimFrame; }
        public synchronized long getSkippedFrameThreshold() { return this.mSkippedFrameThreshold; }
        public synchronized int getSkippedFrame() { return this.mSkippedFrame; }
        public void addPreCount() { this.mPreCount++; }
        public int getPreCount() { return this.mPreCount; }
        public void resetPreCount() { this.mPreCount = 0; }
        public void setTransitionSyncId(int syncId) { this.mTransitionSyncId = syncId; }
        public int getTransitionSyncId() { return this.mTransitionSyncId; }
        public synchronized long getScenePolicy() { return this.mPolicy; }
    }

    default OplusFeatureList.OplusIndex index() { return OplusFeatureList.OplusIndex.IJankManager; }
    default IOplusCommonFeature getDefault() { return DEFAULT; }
    default void updateAppSceneAndAction(String packageName, int scene, int action) {}
    default int getAppScene(String packageName) { return -1; }
    default int getAppAction(String packageName) { return -1; }
    default void latencySceneBegin(Context context, int scene, String sceneDes, long latencyThresholdMs) {}
    default void latencySceneBegin(Context context, SceneInfo sceneInfo) {}
    default void latencySceneEnd(Context context, int scene, ArrayList<String> latencyInfo) {}
    default void asyncLatencySceneBegin(SceneInfo sceneInfo) {}
    default void asyncLatencySceneEnd(String packageName, int scene, int policy, String appendMessage) {}
    default void updateFaultThresholdMs(String packageName, int faultType, int threshold) {}
    default void ssAnimSceneBegin(int scene, String sceneDes, int skippedFrameNum, long duration) {}
    default void ssAnimSceneEnd(int scene, boolean normal) {}
    default void gfxSceneBegin(Context context, int scene, String sceneDes, long policy) {}
    default void gfxSceneBegin(Context context, SceneInfo sceneInfo) {}
    default void gfxSceneEnd(Context context, int scene) {}
    default int getSceneType() { return -1; }
    default int getScene() { return -1; }
    default long getSceneBeginTime() { return -1L; }
    default String getSceneDescription() { return null; }
    default long getLatencyThresholdNs() { return -1L; }
    default long getAnimCostThreshold() { return -1L; }
    default int getSkippedAnimFrame() { return -1; }
    default long getSkippedFrameThreshold() { return -1L; }
    default int getSkippedFrame() { return -1; }
    default long getScenePolicy() { return -1L; }
    default boolean isSceneEnabled(int sceneType, int scene) { return false; }
    default void onAnimationUpdate() {}
    default void notifyActivityStarted(String packageName, String activityName, long longVersionCode) {}
    default void notifyAddStartingWindow(String pkgName, String activityName, String launchFromPkg, int reasonCode) {}
    default void startActivity(String pkgName, String activityName) {}
    default void finishDrawing(String pkgName, String activityName) {}
    default void startRemoteAnimation(String pkgName, String activityName) {}
    default void requestStartTransition(int type, int scene, String sceneDes, long latencyThresholdMs, int syncId, boolean hasRemoteTransition) {}
    default void requestEndTransition(int syncId) {}
    default void slideSceneBegin(Context context, SceneInfo sceneInfo) {}
    default void slideSceneEnd(Context context, int scene) {}
    default int getSlideSceneType() { return -1; }
    default int getSlideScene() { return -1; }
    default long getSlideScenePolicy() { return -1L; }
    default long getSlideSceneBeginTime() { return -1L; }
    default int getSlideSceneSkippedFrame() { return -1; }
    default long getSlideSceneThreshold() { return -1L; }
}
