package com.sdwfqin.mvpseed.model.http;

import com.sdwfqin.mvpseed.model.bean.TestBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sdwfqin on 2017/6/9.
 */

public interface TestApi {

    String HOST = "http://www.weather.com.cn/";

    @GET("adat/sk/{cityId}.html")
    Flowable<TestBean> getTest(@Path("cityId") String cityId);
}
