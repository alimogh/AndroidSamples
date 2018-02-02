package com.sdwfqin.sample.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.view.animator1.Animator1Activity;
import com.sdwfqin.sample.view.gesturedetector.GestureDetectorActivity;
import com.sdwfqin.sample.view.motionslop.MeTsActivity;
import com.sdwfqin.sample.view.paypwdinput.PayPwdInputActivity;
import com.sdwfqin.sample.view.rippleanimation.RippleAnimationActivity;
import com.sdwfqin.sample.view.scroller.ScrollerActivity;
import com.sdwfqin.sample.view.scrolling.ScrollingActivity;
import com.sdwfqin.sample.view.surface.SurfaceActivity;
import com.sdwfqin.sample.view.surface2.Surface2Activity;
import com.sdwfqin.sample.view.viewevent.ViewEventActivity;
import com.sdwfqin.sample.view.viewposition.ViewPositionActivity;
import com.sdwfqin.sample.view.viewz1.ViewZ1Activity;
import com.sdwfqin.sample.view.viewz2.ViewZ2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：View相关
 *
 * @author zhangqin
 */
public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.view_list)
    ListView mViewList;
    private Context mContext;

    private String[] mStrings = new String[]{"View的位置参数", "MotionEvent与TouchSlop", "GestureDetector", "Scroller",
            "View触摸事件分发", "按钮放大（属性动画）", "自定义View1圆", "自定义View2凹凸边缘", "SurfaceView",
            "SurfaceView画板", "自定义输入密码", "网易云听歌识曲","ScrollingActivity"};
    private Class[] mClasses = new Class[]{ViewPositionActivity.class, MeTsActivity.class,
            GestureDetectorActivity.class, ScrollerActivity.class, ViewEventActivity.class,
            Animator1Activity.class, ViewZ1Activity.class, ViewZ2Activity.class, SurfaceActivity.class,
            Surface2Activity.class, PayPwdInputActivity.class, RippleAnimationActivity.class,ScrollingActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        mViewList.setAdapter(new ArrayAdapter<>(this, R.layout.item_list, R.id.tv_items, mStrings));

        mViewList.setOnItemClickListener((parent, view, position, id) -> startActivity(new Intent(mContext, mClasses[position])));
    }
}
