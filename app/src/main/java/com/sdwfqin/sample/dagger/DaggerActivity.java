package com.sdwfqin.sample.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.dagger.componet.DaggerUserComponet;
import com.sdwfqin.sample.dagger.controller.MoneyController;
import com.sdwfqin.sample.dagger.controller.OrderController;
import com.sdwfqin.sample.dagger.model.HttpModule;
import com.sdwfqin.sample.dagger.model.UserModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：Dagger2
 *
 * @author zhangqin
 * @date 2017/5/24
 */
public class DaggerActivity extends AppCompatActivity {

    @BindView(R.id.btnMakeCoffee)
    Button mBtnMakeCoffee;
    @BindView(R.id.tvCoffee)
    TextView mTvCoffee;

    // @Inject 通常在需要依赖的地方使用这个注解，也就是针水打进屁股的过程
    @Inject
    OrderController orderController;
    @Inject
    MoneyController moneyController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);

        // DaggerUserComponet是编译时生成的（不需要传参可以用create）
        DaggerUserComponet.builder()
                .userModule(new UserModule(this))
                .httpModule(new HttpModule())
                .build().inject(this);

        mBtnMakeCoffee.setOnClickListener(v -> {
            mTvCoffee.setText(moneyController.payMoney());
            orderController.order();
        });
    }
}
