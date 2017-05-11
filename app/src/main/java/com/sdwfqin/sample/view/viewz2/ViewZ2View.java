package com.sdwfqin.sample.view.viewz2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.utils.ViewUtils;

/**
 * Created by sdwfqin on 2017/5/10.
 */
public class ViewZ2View extends LinearLayout {

    private static final String TAG = "ViewZ2View";
    private Context mContext;
    private int mColor = Color.WHITE;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 要提高精度可以使用float
    //圆的半径
    private int radius;
    //圆之间的间距
    private int gap;

    public ViewZ2View(Context context) {
        super(context);
        init(context);
    }

    public ViewZ2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ViewZ2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context) {
        mContext = context;
        mPaint.setColor(mColor);
        gap = ViewUtils.dp2px(context, 10);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewZ2View);
        // Color.WHITE为默认颜色
        mColor = typedArray.getColor(R.styleable.ViewZ2View_circle_color, Color.WHITE);
        radius = ViewUtils.dp2px(context, typedArray.getInteger(R.styleable.ViewZ2View_radius, 10));
        gap = ViewUtils.dp2px(context, typedArray.getInteger(R.styleable.ViewZ2View_gap, 10));
        typedArray.recycle();
        mPaint.setColor(mColor);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
        mPaint.setColor(mColor);
        // 刷新视图
        invalidate();
    }
    // 读取圆的半径
    public int getRadius() {
        return ViewUtils.px2dp(mContext, radius);
    }
    // 设置圆的半径
    public void setRadius(int radius) {
        this.radius = ViewUtils.dp2px(mContext, radius);
        // 刷新视图
        invalidate();
    }

    public int getGap() {
        return ViewUtils.px2dp(mContext, gap);
    }

    public void setGap(int gap) {
        this.gap = ViewUtils.dp2px(mContext, gap);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取宽度与高度
        int width = getWidth();
        int height = getHeight();
        //圆的个数
        int count;
        try {
            // 计算可以显示多少个圆
            count = (width - gap) / (radius * 2 + gap);
        } catch (Exception e) {
            count = 0;
            Log.e(TAG, "onDraw: ", e);
        }
        // 圆的直径
        int h = (radius * 2);
        // 圆以外的长度
        int cgap = (width - (count * h));
        // 两侧端点的长度，
        int x1 = (cgap + gap - (gap * count)) / 2;
        // 绘制
        for (int i = 0; i < count; i++) {
            int x = x1 + radius + (h + gap) * i;
            canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, height, radius, mPaint);
        }
    }
}
