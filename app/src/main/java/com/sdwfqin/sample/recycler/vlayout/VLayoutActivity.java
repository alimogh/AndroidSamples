package com.sdwfqin.sample.recycler.vlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdwfqin.sample.R;

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
