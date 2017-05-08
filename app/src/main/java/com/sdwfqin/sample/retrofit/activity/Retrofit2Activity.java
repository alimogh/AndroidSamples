package com.sdwfqin.sample.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.ApiStores;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofit2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 返回字符串
        Retrofit mRetrofit2 = new Retrofit.Builder() //01:获取Retrofit对象
                .baseUrl(ApiStores.API_SERVER_URL) //02采用链式结构绑定Base url
                .addConverterFactory(ScalarsConverterFactory.create()) //使用工厂模式创建字符串解析器
                .build();
        ApiStores mApiStores2 = mRetrofit2.create(ApiStores.class);
        Call<String> mModelCall2 = mApiStores2.loadDataByString("101190201");
        mModelCall2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("test", "onResponse字符串：" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
