package com.sdwfqin.uidemo.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdwfqin.uidemo.MainActivity;
import com.sdwfqin.uidemo.R;

import java.util.Set;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // 获取服务
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("标题")
                .setContentText("内容")
                .setAutoCancel(true)
                // 设置通知集合的数量
                // .setNumber(2)
                .setSmallIcon(R.mipmap.ic_launcher)
                // 发出时间
                .setWhen(System.currentTimeMillis())
                // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                // Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音
                // <uses-permission android:name="android.permission.VIBRATE"/>
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setOngoing(false); //ture，设置他为一个正在进行的通知(不可取消)

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.getNotification();
        notificationManager.notify(1, notification);
    }

}
