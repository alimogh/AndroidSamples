package com.sdwfqin.textdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by sdwfqin on 2016/8/31.
 */
public class MlinLayout extends LinearLayout {

    private final static String TAG = "test";

    public MlinLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MlinLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MlinLayout(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");

        Boolean state = false;
        if(onInterceptTouchEvent(ev)){
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
