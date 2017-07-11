package com.sdwfqin.sample.retrofit.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by sdwfqin on 2017/7/11.
 */
public interface TranslateApi {

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("ai/bufferSend")
    Observable<String> uploadImg(
            @Body String s);

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("ai/identify")
    Observable<String> getData(
            @Body String s);

}
