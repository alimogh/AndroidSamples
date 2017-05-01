package com.sdwfqin.sample.service.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sdwfqin.sample.MainActivity;
import com.sdwfqin.sample.R;

/**
 * Created by sdwfqin on 2016/12/8.
 *
 * 常驻通知栏
 */

public class StageService extends Service {

    private static final String TAG = "StageService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        showNotification();
        super.onCreate();
    }

    private void showNotification() {
        // 创建通知栏
        Notification.Builder mBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.img_tm)
                .setContentTitle("标题")
                .setContentText("文本");
        // 点击跳转到Activity
        Intent intent = new Intent(this, MainActivity.class);
        // 创建任务栈
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置跳转
        mBuilder.setContentIntent(pendingIntent);

        // 获取通知服务
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 构建通知
        Notification notification = mBuilder.build();
        // 显示通知
        nm.notify(0,notification);

        // 启动前台服务
        startForeground(0,notification);
    }


}
