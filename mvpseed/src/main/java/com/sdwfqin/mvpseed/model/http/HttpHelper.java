package com.sdwfqin.mvpseed.model.http;

import com.sdwfqin.mvpseed.model.bean.TestBean;

import io.reactivex.Flowable;

/**
 * @author sdwfqin
 * @date 2017/9/7
 */
public interface HttpHelper {

    /**
     * 测试
     * @param cityId
     * @return
     */
    Flowable<TestBean> fetchTest(String cityId);

}
