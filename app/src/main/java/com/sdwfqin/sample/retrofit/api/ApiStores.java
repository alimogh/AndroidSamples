package com.sdwfqin.sample.retrofit.api;

import com.sdwfqin.sample.retrofit.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sdwfqin on 2016/12/7.
 */

public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";
    //加载天气
    @GET("adat/sk/{cityId}.html")
    Call<WeatherModel> loadDataByJson(@Path("cityId") String cityId);
    //返回字符串
    @GET("adat/sk/{cityId}.html")
    Call<String> loadDataByString(@Path("cityId") String cityId);
}
