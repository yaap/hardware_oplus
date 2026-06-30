package com.oplus.app;

import android.app.ActivityManager;
import java.util.List;

public class OplusTaskInfoChangeListener extends IOplusTaskInfoChangeListener.Stub {
    public void onVisibleTasksInfoChange(List<ActivityManager.RunningTaskInfo> tasks) {
    }
}
