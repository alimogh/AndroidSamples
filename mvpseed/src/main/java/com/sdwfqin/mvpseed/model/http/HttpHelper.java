package com.sdwfqin.mvpseed.model.http;

import com.sdwfqin.mvpseed.model.bean.TestBean;

import io.reactivex.Flowable;

/**
 * Created by sdwfqin on 2017/9/7.
 */
public interface HttpHelper {

    Flowable<TestBean> fetchTest(String cityId);

}
