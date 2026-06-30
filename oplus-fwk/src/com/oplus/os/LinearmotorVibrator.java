package com.oplus.os;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.CombinedVibration;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.util.Slog;

public class LinearmotorVibrator {
    private static final String TAG = "LinearmotorVibrator";
    private final Vibrator mVibrator;
    private final VibratorManager mVibratorManager;
    private final Context mContext;

    // Constructor: ignore the ILinearmotorVibratorService parameter
    public LinearmotorVibrator(Context context, ILinearmotorVibratorService serviceIgnored) {
        this.mContext = context;
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mVibratorManager = (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
    }

    // All vibrate calls use EFFECT_DOUBLE_CLICK (4)
    public void vibrate(WaveformEffect we) {
        if (mVibrator == null) return;
        VibrationEffect effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
        mVibrator.vibrate(effect);
        Slog.d(TAG, "vibrate (stub) with WaveformEffect -> EFFECT_DOUBLE_CLICK");
    }

    public void cancelVibrate(WaveformEffect we) {
        if (mVibrator != null) mVibrator.cancel();
    }

    public int getVibratorStatus() {
        return 0; // always ready
    }

    public void setVibratorStrength(int strength) {
        // no-op
    }

    public int getSettingsTouchEffectStrength() {
        return 2400; // default max
    }

    public int getSettingsRingtoneEffectStrength() {
        return 2400;
    }

    public int getSettingsNotificationEffectStrength() {
        return 2400;
    }

    public int getVibratorTouchStyle() {
        return 0;
    }

    public void setVibratorTouchStyle(int style) {
        // no-op
    }

    public void updateVibrationAmplitude(float amplitudeRatio) {
        // no-op
    }

    // Create waveform effect – return double-click instead of Oplus proprietary
    public VibrationEffect createWaveformEffect(int effectId, int strength, int repeat) {
        return VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
    }

    public VibrationEffect createWaveformEffect(String ringtonePath, int strength, int repeat) {
        return VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
    }

    public void vibrate(VibrationEffect effect, VibrationAttributes attributes) {
        if (mVibrator == null) return;
        VibrationEffect defaultEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
        mVibrator.vibrate(defaultEffect);
        Slog.d(TAG, "vibrate with EFFECT_DOUBLE_CLICK");
    }

    public void vibrate(VibrationEffect effect, String reason, VibrationAttributes attributes) {
        if (mVibrator == null) return;
        VibrationEffect defaultEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
        mVibrator.vibrate(defaultEffect);
        Slog.d(TAG, "vibrate with EFFECT_DOUBLE_CLICK");
    }

    public void vibrate(int uid, String opPkg, VibrationEffect effect, String reason, VibrationAttributes attributes) {
        if (mVibrator == null) return;
        VibrationEffect defaultEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
        mVibrator.vibrate(defaultEffect);
        Slog.d(TAG, "vibrate with EFFECT_DOUBLE_CLICK");
    }
}
