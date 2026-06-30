package com.oplus.app;

import android.content.Context;

public class OplusHansFreezeManager {

    public static final OplusHansFreezeManager instance = new OplusHansFreezeManager();

    public OplusHansFreezeManager() {
    }

    public static OplusHansFreezeManager getInstance() {
        return instance;
    }

    public int requestFastFreeze(Context context, int freezeType, String reason) {
        return 0;
    }
}
