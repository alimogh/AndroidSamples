package com.sdwfqin.popupwindowdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(mContext,PopDropDownBgActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(mContext,PopshowAtLocationActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(mContext,PopupAnimActivity.class));
                break;
            case R.id.btn_4:
                startActivity(new Intent(mContext,PopupShowAsDropDownActivity.class));
                break;
            case R.id.btn_5:
                startActivity(new Intent(mContext,PopDropDownBgSeniorActivity.class));
                break;
        }
    }
}
