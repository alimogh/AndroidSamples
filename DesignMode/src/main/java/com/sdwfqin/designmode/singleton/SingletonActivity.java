package com.sdwfqin.designmode.singleton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdwfqin.designmode.R;

public class SingletonActivity extends AppCompatActivity {

    private static SingletonActivity mSingletonActivity = null;

    public static SingletonActivity getInstance() {

        if(mSingletonActivity == null){
            mSingletonActivity = new SingletonActivity();
        }

        return mSingletonActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
    }
}
