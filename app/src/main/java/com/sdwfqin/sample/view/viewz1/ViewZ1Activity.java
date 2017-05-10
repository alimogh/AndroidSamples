package com.sdwfqin.sample.view.viewz1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewZ1Activity extends AppCompatActivity {

    @BindView(R.id.viewz1)
    ViewZ1View viewz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_z1);
        ButterKnife.bind(this);

        viewz1.setColor(Color.RED);
    }
}
