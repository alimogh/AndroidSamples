package com.sdwfqin.sample.view.motionslop;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by sdwfqin on 2017/4/26.
 */

public class MeTsView extends LinearLayout {

    private static final String TAG = "MeTsView";

    public MeTsView(Context context) {
        super(context);
    }

    public MeTsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeTsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float x = 0, y = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float slop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: " + "按下");
                Log.e(TAG, "getX: " + event.getX());
                Log.e(TAG, "getY: " + event.getY());
                Log.e(TAG, "getRawX: " + event.getRawX());
                Log.e(TAG, "getRawY: " + event.getRawY());

                x = event.getX();
                y = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: " + "移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: " + "松开" + x);
                if (event.getX() - x > slop) {
                    Log.e(TAG, "onTouchEvent: " + "往右滑动" + event.getX());
                } else if (x - event.getX() > slop) {
                    Log.e(TAG, "onTouchEvent: " + "往左滑动" + event.getX());
                } else {
                    Log.e(TAG, "onTouchEvent: " + "无效滑动" + event.getX());
                }
                x = 0;
                y = 0;
                break;
        }
        // 返回true，拦截这个事件
        // 返回false，不拦截
        return true;
    }
}
