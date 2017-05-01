package com.sdwfqin.sample.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sdwfqin on 2016/12/8.
 * <p>
 * 不可交互的后台服务即是普通的Service，Service的生命周期很简单，
 * 分别为onCreate、onStartCommand、onDestroy这三个。
 * 当我们startService()的时候，首次创建Service会回调onCreate()方法，
 * 然后回调onStartCommand()方法，再次startService()的时候，
 * 就只会执行一次onStartCommand()。
 * 服务一旦开启后，我们就需要通过stopService()方法或者stopSelf()方法，
 * 就能把服务关闭，这时就会回调onDestroy()
 */

public class NotInteractiveService extends Service {

    private Timer mTimer;
    private static final String TAG = "NotInteractiveService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e(TAG, "====NotInteractiveService====");
            }
        },0,1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mTimer.cancel();
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
