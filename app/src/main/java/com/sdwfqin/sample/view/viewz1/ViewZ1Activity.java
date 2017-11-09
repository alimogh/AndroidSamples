package com.sdwfqin.sample.view.viewz1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：自定义View1圆
 *
 * @author sdwfqin
 */
public class ViewZ1Activity extends AppCompatActivity {

    @BindView(R.id.viewz1)
    ViewZ1View mViewz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_z1);
        ButterKnife.bind(this);

        mViewz1.setColor(Color.RED);
    }
}
