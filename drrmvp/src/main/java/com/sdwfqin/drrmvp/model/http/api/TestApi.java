package com.sdwfqin.drrmvp.model.http.api;

import com.sdwfqin.drrmvp.model.bean.WeatherBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sdwfqin on 2017/6/9.
 */

public interface TestApi {

    String HOST = "http://www.weather.com.cn/";

    @GET("adat/sk/{cityId}.html")
    Flowable<WeatherBean> getWeather(@Path("cityId") String cityId);
}
