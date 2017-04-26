package com.sdwfqin.sample.view.viewposition;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.sdwfqin.sample.view.Utils;

/**
 * Created by sdwfqin on 2017/4/26.
 */

public class PositionView extends AppCompatTextView {

    private static final String TAG = "TestView";
    private Context context;
    private boolean tranFlag = false;

    public PositionView(Context context) {
        super(context);
        this.context = context;
    }

    public PositionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PositionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setTranslation() {
        if (!tranFlag) {
            // 正数往右，负数往左
            setTranslationX(100);
            setTranslationY(100);
            // 设置该组件在Z方向（垂直屏幕方向）上的位移，可以看到添加了阴影
//            setTranslationZ(100);
            tranFlag = true;
        } else {
            // 回复原来的位置
            setTranslationX(0);
            setTranslationY(0);
            // 设置该组件在Z方向（垂直屏幕方向）上的位移。
//            setTranslationZ(0);
            tranFlag = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int left = getLeft();
        int Right = getRight();
        int Top = getTop();
        int Bottom = getBottom();
//        float Z = getZ();

        Log.e(TAG, "left: " + left);
        Log.e(TAG, "right: " + Right);
        Log.e(TAG, "top: " + Top);
        Log.e(TAG, "bottom: " + Bottom);
//        Log.e(TAG, "Z: " + Z);

        int width = Right - left;
        int widthdp = Utils.px2dp(context, width);
        int height = Bottom - Top;
        int heightdp = Utils.px2dp(context, height);
        // 转换为dp
        Log.e(TAG, "width:" + width);
        Log.e(TAG, "宽度（dp）:" + widthdp);
        Log.e(TAG, "height:" + height);
        Log.e(TAG, "高度（dp）:" + heightdp);

        float translationX = getTranslationX();
        float translationY = getTranslationY();
//        float translationZ = getTranslationZ();

        Log.e(TAG, "translationX:" + translationX);
        Log.e(TAG, "translationY:" + translationY);
//        Log.e(TAG, "translationZ:" + translationZ);

        // x,y:移动后left与top的位置
        float x = left + translationX;
        float y = Top + translationY;

        Log.e(TAG, "移动后left的位置" + x);
        Log.e(TAG, "移动后top的位置" + y);

        super.onDraw(canvas);
    }
}
