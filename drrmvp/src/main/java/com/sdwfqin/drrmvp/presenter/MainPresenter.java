package com.sdwfqin.drrmvp.presenter;

import com.sdwfqin.drrmvp.base.RxPresenter;
import com.sdwfqin.drrmvp.contract.MainContract;
import com.sdwfqin.drrmvp.model.DataManager;
import com.sdwfqin.drrmvp.model.bean.WeatherBean;
import com.sdwfqin.drrmvp.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by sdwfqin on 2017/6/9.
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getWeather(String cityId) {
        addSubscribe(mDataManager.fetchWeather(cityId)
                // compose() 是针对 Observable 自身进行变换
                .compose(RxUtil.<WeatherBean>rxSchedulerHelper())
                .subscribe(new Consumer<WeatherBean>() {
                    @Override
                    public void accept(@NonNull WeatherBean weatherBean) throws Exception {
                        mView.showTextView(weatherBean);
                    }
                }));
    }
}
