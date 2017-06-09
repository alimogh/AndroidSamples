package com.sdwfqin.drrmvp.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.sdwfqin.drrmvp.R;
import com.sdwfqin.drrmvp.base.BaseActivity;
import com.sdwfqin.drrmvp.contract.MainContract;
import com.sdwfqin.drrmvp.model.bean.WeatherBean;
import com.sdwfqin.drrmvp.presenter.MainPresenter;

/**
 * Dagger2+Rxjava2+Retrofit
 *
 * @author sdwfqin
 * @version 1.0.0
 * @since 2017-06-09
 * <p/>
 * 博客: www.sdwfqin.com  邮箱: zhangqin@sdwfqin.com
 * 项目地址：https://github.com/sdwfqin/AndroidSamples
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private TextView main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_tv = (TextView) findViewById(R.id.main_tv);

        mPresenter.getWeather("101190201");
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showTextView(WeatherBean weatherBean) {
        main_tv.setText(weatherBean.getWeatherinfo().toString());
    }
}
