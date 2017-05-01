package com.sdwfqin.sample.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sdwfqin on 2016/12/8.
 * <p>
 * 可交互的后台服务,返回一个代理对象
 * <p>
 * startService和bindService -> 混合性交互的后台服务，即可以单独运行后台服务，也可以运行后台服务中提供的方法，
 * 其完整的生命周期是：onCreate->onStartCommand->onBind->onUnBind->onDestroy
 */

public class InteractiveService extends Service {

    private static final String TAG = "InteractiveService";

    // 绑定
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return new MyBind();
    }

    // 代理类
    public class MyBind extends Binder {
        public void showLog() {
            Log.e(TAG, "showLog: ");
        }

        public void show(String str) {
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            // 调用外部类
            callLog(str);
        }
    }

    public void callLog(String str) {
        Log.e(TAG, "callLog: " + str);
    }

    // 解绑
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }
}
