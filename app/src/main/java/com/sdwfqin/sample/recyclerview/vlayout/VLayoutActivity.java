package com.sdwfqin.sample.recyclerview.vlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdwfqin.sample.R;

/**
 * 描述：天猫VLayout
 *
 * @author sdwfqin
 */
public class VLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fl, VLayoutFragment.newInstance())
                .commit();
    }
}
