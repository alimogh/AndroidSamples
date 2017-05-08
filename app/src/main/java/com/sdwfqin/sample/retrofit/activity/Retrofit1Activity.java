package com.sdwfqin.sample.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.ApiStores;
import com.sdwfqin.sample.retrofit.model.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit1);

        // 返回Json
        Retrofit mRetrofit = new Retrofit.Builder() //01:获取Retrofit对象
                .baseUrl(ApiStores.API_SERVER_URL) //02采用链式结构绑定Base url
                .addConverterFactory(GsonConverterFactory.create()) //使用工厂模式创建Gson的解析器
                .build();
        //04获取API接口的实现类的实例对象
        ApiStores mApiStores = mRetrofit.create(ApiStores.class);
        Call<WeatherModel> mModelCall = mApiStores.loadDataByJson("101190201");
        mModelCall.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

                if (response.body() == null) {
                    Log.e("test", "onResponse: " + null);
                } else {
                    Log.e("test", "onResponse: " + response.body().getWeatherinfo().toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

            }
        });
    }
}
