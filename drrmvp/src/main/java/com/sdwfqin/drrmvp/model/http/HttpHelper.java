package com.sdwfqin.drrmvp.model.http;

import com.sdwfqin.drrmvp.model.bean.WeatherBean;

import io.reactivex.Flowable;

public interface HttpHelper {

    Flowable<WeatherBean> fetchWeather(String cityId);

}
