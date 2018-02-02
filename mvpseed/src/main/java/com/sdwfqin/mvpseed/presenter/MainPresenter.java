package com.sdwfqin.mvpseed.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.sdwfqin.mvpseed.base.RxPresenter;
import com.sdwfqin.mvpseed.contract.MainContract;
import com.sdwfqin.mvpseed.model.http.RetrofitHelper;
import com.sdwfqin.mvpseed.util.RxUtil;

import javax.inject.Inject;

/**
 * 描述：Presenter
 *
 * @author zhangqin
 * @date 2017/6/9
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void getWeather(String cityId) {
        addSubscribe(mRetrofitHelper.fetchTest(cityId)
                // compose() 是针对 Observable 自身进行变换
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(testBean -> {
                    mView.showTextView(testBean);
                    LogUtils.i(testBean.toString());
                }));
    }
}
