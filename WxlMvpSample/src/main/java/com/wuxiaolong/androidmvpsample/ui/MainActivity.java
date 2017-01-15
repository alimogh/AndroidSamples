package com.wuxiaolong.androidmvpsample.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.wuxiaolong.androidmvpsample.R;
import com.wuxiaolong.androidmvpsample.mvp.MainModel;
import com.wuxiaolong.androidmvpsample.mvp.MainPresenter;
import com.wuxiaolong.androidmvpsample.mvp.MainView;
import com.wuxiaolong.androidmvpsample.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 由Activity实现View里方法，包含一个Presenter的引用
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBarAsHome("MVP+Retrofit+Rxjava");
    }

    // 创建Presenter，在MvpActivity中调用
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
        //请求接口
        mvpPresenter.loadDataByRetrofitRxjava("101310222");
    }

    private void dataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }

    // MainView
    @Override
    public void getDataSuccess(MainModel model) {
        //接口成功回调
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("网络不给力");
    }

}
