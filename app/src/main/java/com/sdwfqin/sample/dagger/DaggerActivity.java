package com.sdwfqin.sample.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.sdwfqin.sample.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaggerActivity extends Activity{

    @BindView(R.id.btnMakeCoffee)
    Button btnMakeCoffee;
    @BindView(R.id.tvCoffee)
    TextView tvCoffee;

    @Inject
    OrderController orderController;
    @Inject
    MoneyController moneyController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);
        // DaggerSimpleComponent编译时生成
//        daggerComponent = DaggerDaggerComponent.builder().simpleModule(getModule()).build();
//        daggerComponent.inject(this);

        // DaggerUserComponet是编译时生成的
        DaggerUserComponet.builder().userModule(new UserModule(this)).build().inject(this);

        orderController.order();
        moneyController.payMoney();
    }
}
