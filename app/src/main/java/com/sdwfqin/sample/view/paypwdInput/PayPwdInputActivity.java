package com.sdwfqin.sample.view.paypwdInput;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayPwdInputActivity extends AppCompatActivity {

    @BindView(R.id.password)
    PayPwdInputView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pwd_input);
        ButterKnife.bind(this);

        password.setComparePassword("123456", new PayPwdInputView.onPasswordListener() {
            @Override
            public void onSuccess(String psd) {
                Toast.makeText(PayPwdInputActivity.this, "匹配成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(PayPwdInputActivity.this, "匹配失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
