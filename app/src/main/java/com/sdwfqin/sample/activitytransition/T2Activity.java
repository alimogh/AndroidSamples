package com.sdwfqin.sample.activitytransition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sdwfqin.sample.R;

/**
 * @author sdwfqin
 */
public class T2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2);
    }

    public void exit(View view){
        finish();
    }
}
