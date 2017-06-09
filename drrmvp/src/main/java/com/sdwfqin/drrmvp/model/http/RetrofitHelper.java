package com.sdwfqin.drrmvp.model.http;

import com.sdwfqin.drrmvp.model.bean.WeatherBean;
import com.sdwfqin.drrmvp.model.http.api.TestApi;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper {

    private TestApi mTestApiService;

    @Inject
    public RetrofitHelper(TestApi testApiService) {
        this.mTestApiService = testApiService;
    }

    @Override
    public Flowable<WeatherBean> fetchWeather(String cityId) {
        return mTestApiService.getWeather(cityId);
    }
}
