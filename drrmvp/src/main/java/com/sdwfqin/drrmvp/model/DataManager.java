package com.sdwfqin.drrmvp.model;

import com.sdwfqin.drrmvp.model.bean.WeatherBean;
import com.sdwfqin.drrmvp.model.http.HttpHelper;

import io.reactivex.Flowable;

public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;

    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @Override
    public Flowable<WeatherBean> fetchWeather(String cityId) {
        return mHttpHelper.fetchWeather(cityId);
    }
}
