package com.oplus.wrapper.view;

import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: classes2.dex */
public class View {
    public static final int DRAG_FLAG_REQUEST_SURFACE_FOR_RETURN_ANIMATION = getDragFlagRequestSurfaceForReturnAnimation();
    private final android.view.View mView;

    public static boolean isVisibleToUser(android.view.View target) {
        return target.isVisibleToUser();
    }

    public static boolean requestAccessibilityFocus(android.view.View target) {
        return target.requestAccessibilityFocus();
    }

    public static boolean isLayoutRtl(android.view.View target) {
        return target.isLayoutRtl();
    }

    public static ViewRootImpl getViewRootImpl(android.view.View target) {
        android.view.ViewRootImpl viewRootImpl = target.getViewRootImpl();
        if (viewRootImpl == null) {
            return null;
        }
        return new ViewRootImpl(viewRootImpl);
    }

    public static void notifyViewAccessibilityStateChangedIfNeeded(android.view.View target, int changeType) {
        target.notifyViewAccessibilityStateChangedIfNeeded(changeType);
    }

    public static void onMovedToDisplay(android.view.View target, int displayId, Configuration config) {
        target.onMovedToDisplay(displayId, config);
    }

    public static void clearAccessibilityFocus(android.view.View target) {
        target.clearAccessibilityFocus();
    }

    public static final boolean hasIdentityMatrix(android.view.View target) {
        return target.hasIdentityMatrix();
    }

    public static final Matrix getInverseMatrix(android.view.View target) {
        return target.getInverseMatrix();
    }

    public static int[] getLocationOnScreen(android.view.View target) {
        return target.getLocationOnScreen();
    }

    public static int getPrivateFlags(android.view.View target) {
        return target.mPrivateFlags;
    }

    public static void setPrivateFlags(android.view.View target, int privateFlags) {
        target.mPrivateFlags = privateFlags;
    }

    public static boolean isForegroundInsidePadding(android.view.View target) {
        return target.isForegroundInsidePadding();
    }

    public static void getBoundsOnScreen(android.view.View target, Rect outRect) {
        target.getBoundsOnScreen(outRect);
    }

    public static AccessibilityNodeInfo createAccessibilityNodeInfo(android.view.View target) {
        return target.createAccessibilityNodeInfo();
    }

    public View(android.view.View view) {
        this.mView = view;
    }

    public boolean isVisibleToUser() {
        return this.mView.isVisibleToUser();
    }

    public boolean requestAccessibilityFocus() {
        return this.mView.requestAccessibilityFocus();
    }

    public boolean isLayoutRtl() {
        return this.mView.isLayoutRtl();
    }

    public ViewRootImpl getViewRootImpl() {
        android.view.ViewRootImpl viewRootImpl = this.mView.getViewRootImpl();
        if (viewRootImpl == null) {
            return null;
        }
        return new ViewRootImpl(viewRootImpl);
    }

    public void notifyViewAccessibilityStateChangedIfNeeded(int changeType) {
        this.mView.notifyViewAccessibilityStateChangedIfNeeded(changeType);
    }

    public void onMovedToDisplay(int displayId, Configuration config) {
        this.mView.onMovedToDisplay(displayId, config);
    }

    public void clearAccessibilityFocus() {
        this.mView.clearAccessibilityFocus();
    }

    public final boolean hasIdentityMatrix() {
        return this.mView.hasIdentityMatrix();
    }

    public final Matrix getInverseMatrix() {
        return this.mView.getInverseMatrix();
    }

    public int[] getLocationOnScreen() {
        return this.mView.getLocationOnScreen();
    }

    public int getPrivateFlags() {
        return this.mView.mPrivateFlags;
    }

    public void setPrivateFlags(int privateFlags) {
        this.mView.mPrivateFlags = privateFlags;
    }

    public boolean isForegroundInsidePadding() {
        return this.mView.isForegroundInsidePadding();
    }

    private static int getDragFlagRequestSurfaceForReturnAnimation() {
        return 2048;
    }

    public void getBoundsOnScreen(Rect outRect) {
        this.mView.getBoundsOnScreen(outRect);
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo() {
        return this.mView.createAccessibilityNodeInfo();
    }

    public static class AccessibilityDelegate {
        private final View.AccessibilityDelegate mAccessibilityDelegate;

        public static AccessibilityNodeInfo createAccessibilityNodeInfo(View.AccessibilityDelegate target, android.view.View host) {
            return target.createAccessibilityNodeInfo(host);
        }

        public AccessibilityDelegate(View.AccessibilityDelegate accessibilityDelegate) {
            this.mAccessibilityDelegate = accessibilityDelegate;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(android.view.View host) {
            return this.mAccessibilityDelegate.createAccessibilityNodeInfo(host);
        }
    }
}
