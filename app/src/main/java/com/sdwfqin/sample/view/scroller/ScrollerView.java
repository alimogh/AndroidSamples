package com.sdwfqin.sample.view.scroller;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;

/**
 * Created by sdwfqin on 2017/4/26.
 */

public class ScrollerView extends AppCompatTextView {

    private static final String TAG = "ScrollerView";
    private Context mContext;
    private Scroller scroller;

    public ScrollerView(Context context) {
        super(context);
        init(context);
    }

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;
        scroller = new Scroller(mContext);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            smoothScrollTo(-400, -400);
        }
        return true;
    }

    // 缓慢滚动到指定位置
    private void smoothScrollTo(int destX, int destY) {
        Log.e(TAG, "smoothScrollTo: ");
        // scrollX,scrollY对应原始位置左上角，水平与竖直方向
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int delta = destX - scrollX;
        int deltb = destY - scrollY;
        // 1000ms内滑向destX
        scroller.startScroll(scrollX, scrollY, delta, deltb, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
