package com.sdwfqin.sample.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sdwfqin on 2016/12/8.
 */

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    // 需要注意构造方法
    public MyIntentService() {
        super("name");
        Log.e(TAG, "MyIntentService: ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 在此处执行耗时操作
        Log.e(TAG, "onHandleIntent: " + "开始执行");
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "onHandleIntent: " + "执行结束");
    }
}
