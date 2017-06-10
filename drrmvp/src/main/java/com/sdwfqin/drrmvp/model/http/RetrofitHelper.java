package com.sdwfqin.drrmvp.model.http;

import com.sdwfqin.drrmvp.model.bean.WeatherBean;
import com.sdwfqin.drrmvp.model.http.api.TestApi;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper {

    private TestApi mTestApiService;

    /**
     * 注入
     * <p>
     * 详见：HttpModule -> provideZhihuService
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
    public Flowable<WeatherBean> fetchWeather(String cityId) {
        return mTestApiService.getWeather(cityId);
    }
}
