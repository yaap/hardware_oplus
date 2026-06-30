package com.oplus.wrapper.os;

/* loaded from: classes2.dex */
public class UserHandle {
    private final android.os.UserHandle mUserHandle;
    public static final android.os.UserHandle CURRENT = getCurrent();
    public static final android.os.UserHandle OWNER = getOwner();
    public static final int USER_CURRENT = getUserCurrent();
    public static final int USER_SYSTEM = getUserSystem();
    public static final android.os.UserHandle SYSTEM = getSystem();
    public static final int USER_ALL = getUserAll();
    public static final int USER_NULL = getUserNull();

    public UserHandle(android.os.UserHandle userHandle) {
        this.mUserHandle = userHandle;
    }

    public UserHandle(int userId) {
        this.mUserHandle = new android.os.UserHandle(userId);
    }

    public static android.os.UserHandle create(int userId) {
        return new android.os.UserHandle(userId);
    }

    private static android.os.UserHandle getCurrent() {
        return android.os.UserHandle.CURRENT;
    }

    private static android.os.UserHandle getOwner() {
        return android.os.UserHandle.OWNER;
    }

    private static int getUserCurrent() {
        return -2;
    }

    private static int getUserSystem() {
        return 0;
    }

    private static android.os.UserHandle getSystem() {
        return android.os.UserHandle.SYSTEM;
    }

    private static int getUserAll() {
        return -1;
    }

    private static int getUserNull() {
        return -10000;
    }

    public static int getIdentifier(android.os.UserHandle target) {
        return target.getIdentifier();
    }

    public static boolean isSystem(android.os.UserHandle target) {
        return target.isSystem();
    }

    public int getIdentifier() {
        return this.mUserHandle.getIdentifier();
    }

    public android.os.UserHandle getUserHandle() {
        return this.mUserHandle;
    }

    public boolean isSystem() {
        return this.mUserHandle.isSystem();
    }

    public static int getUserId(int uid) {
        return android.os.UserHandle.getUserId(uid);
    }

    public static int myUserId() {
        return android.os.UserHandle.myUserId();
    }

    public static int getAppId(int uid) {
        return android.os.UserHandle.getAppId(uid);
    }

    public static int getUid(int userId, int appId) {
        return android.os.UserHandle.getUid(userId, appId);
    }

    public static android.os.UserHandle of(int userId) {
        return android.os.UserHandle.of(userId);
    }

    public static boolean isApp(int uid) {
        return android.os.UserHandle.isApp(uid);
    }

    public static int getCallingUserId() {
        return android.os.UserHandle.getCallingUserId();
    }
}
