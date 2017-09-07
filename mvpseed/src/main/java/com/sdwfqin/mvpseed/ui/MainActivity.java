package com.sdwfqin.mvpseed.ui;

import android.widget.TextView;

import com.sdwfqin.mvpseed.R;
import com.sdwfqin.mvpseed.base.BaseActivity;
import com.sdwfqin.mvpseed.contract.MainContract;
import com.sdwfqin.mvpseed.model.bean.TestBean;
import com.sdwfqin.mvpseed.presenter.MainPresenter;

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        main_tv = (TextView) findViewById(R.id.main_tv);

        mPresenter.getWeather("101190201");
    }

    @Override
    public void showTextView(TestBean testBean) {
        main_tv.setText(testBean.getWeatherinfo().toString());
    }
}
