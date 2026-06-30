package com.oplus.media;

public class OplusAudioManager {
    private static final String TAG = "OplusAudioManager";

    private OplusAudioManager() {
    }

    private static final class InstanceHolder {
        private static final OplusAudioManager INSTANCE = new OplusAudioManager();

        private InstanceHolder() {
        }
    }

    public static OplusAudioManager getInstance() {
        return new OplusAudioManager();
    }

    public void setRingerModeInternal(int ringerMode) {
    }
}
