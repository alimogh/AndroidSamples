package com.sdwfqin.bottomsheetdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        //回掉监听
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e(TAG, "onStateChanged: " + "状态改变" + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e(TAG, "onSlide: " + slideOffset);
            }
        });


//mBottomSheetBehavior.setPeekHeight(0); // 设置当关闭时底部的高度
//app:behavior_peekHeight="50dp"
//mBottomSheetBehavior.setHideable(false);//设置当拉升到底部是否可以隐藏
//app:behavior_hideable="true"
//bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//设置状态
//
//public static final int STATE_DRAGGING = 1;  //拖动
//public static final int STATE_SETTLING = 2;//沉降中
//public static final int STATE_EXPANDED = 3;//打开了
//public static final int STATE_COLLAPSED = 4;//关闭了
//public static final int STATE_HIDDEN = 5;//隐藏了
    }
}
