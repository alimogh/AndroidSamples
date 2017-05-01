package com.sdwfqin.sample.broadcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sdwfqin on 2016/12/10.
 * <p>
 * 普通广播，静态注册
 */

public class OrdinaryBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "OrdinaryBroadcastReceiv";

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("name");
        Log.e(TAG, "onReceive(普通广播，静态注册): " + str);
    }
}
