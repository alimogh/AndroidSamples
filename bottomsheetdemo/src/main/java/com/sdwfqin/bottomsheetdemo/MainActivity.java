package com.sdwfqin.bottomsheetdemo;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "----->";
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NestedScrollView scrollingView = (NestedScrollView) findViewById(R.id.nsv);
        mBottomSheetBehavior = BottomSheetBehavior.from(scrollingView);

        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetBehavior.setState(3);
            }
        });


//        mBottomSheetBehavior.setPeekHeight(0); // 设置当关闭时 底部 的高度 app:behavior_peekHeight="50dp"
//        //这里为蓝色的部分
//        mBottomSheetBehavior.setHideable(false);//设置当拉升到底部是否可以隐藏  app:behavior_hideable="true"
//        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//设置状态
//
//        //回掉监听
//        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                /**
//                 *public static final int STATE_DRAGGING = 1;  //拖动
//                 public static final int STATE_SETTLING = 2;//沉降中
//                 public static final int STATE_EXPANDED = 3;//打开了
//                 public static final int STATE_COLLAPSED = 4;//关闭了
//                 public static final int STATE_HIDDEN = 5;//隐藏了
//                 */
//                Log.d(TAG, "---->state:" + newState);
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                Log.d(TAG, "slideOffset:" + slideOffset);
//            }
//        });
    }
}
