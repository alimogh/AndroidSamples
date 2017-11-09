package com.sdwfqin.mvpseed.model.http;

import com.sdwfqin.mvpseed.model.bean.TestBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * 描述：Retrofit帮助类
 *
 * @author sdwfqin
 * @date 2017/9/7
 */
public class RetrofitHelper implements HttpHelper {

    private TestApi mTestApiService;

    /**
     * 注入
     * <p>
     * 详见：HttpModule -> provideTestService
     *
     * @param testApiService TestApi接口的实现类的实例对象
     */
    @Inject
    public RetrofitHelper(TestApi testApiService) {
        this.mTestApiService = testApiService;
    }

    /**
     * 调用接口中的业务方法
     *
     * @param cityId
     * @return
     */
    @Override
    public Flowable<TestBean> fetchTest(String cityId) {
        return mTestApiService.getTest(cityId);
    }
}
