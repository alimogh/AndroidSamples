package com.sdwfqin.textdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sdwfqin on 2016/8/28.
 */
public class MTextView extends TextView implements GestureDetector.OnGestureListener ,GestureDetector.OnDoubleTapListener{
    private final static String TAG = "test";

    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    Scroller scroller = new Scroller(getContext());

    // 缓慢滚动到指定位置
    public void smoothScrollTo(int destX , int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        int scrollY = getScrollY();
        int deltb = destY - scrollY;
        // 1000ms内滑向destX
        scroller.startScroll(scrollX,scrollY,delta,deltb,1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    GestureDetector mGestureDetector = new GestureDetector(this);

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e("test", "onDown: ");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.e("test", "onShowPress: ");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.e("test", "onSingleTapUp: ");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e("test", "onScroll: ");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.e("test", "onLongPress: ");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e("test", "onFling: ");
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.e("test", "onSingleTapConfirmed: ");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.e("test", "onDoubleTap: ");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.e("test", "onDoubleTapEvent: ");
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
