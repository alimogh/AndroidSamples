package com.sdwfqin.mvpseed.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.sdwfqin.mvpseed.base.RxPresenter;
import com.sdwfqin.mvpseed.contract.MainContract;
import com.sdwfqin.mvpseed.model.bean.TestBean;
import com.sdwfqin.mvpseed.model.http.RetrofitHelper;
import com.sdwfqin.mvpseed.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by sdwfqin on 2017/6/9.
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
                .compose(RxUtil.<TestBean>rxSchedulerHelper())
                .subscribe(new Consumer<TestBean>() {
                    @Override
                    public void accept(@NonNull TestBean testBean) throws Exception {
                        mView.showTextView(testBean);
                        LogUtils.i(testBean.toString());
                    }
                }));
    }
}
