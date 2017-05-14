package com.sdwfqin.sample.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.ApiStores;
import com.sdwfqin.sample.retrofit.model.WeatherModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofit1Activity extends AppCompatActivity {

    @BindView(R.id.retrofit1_tv)
    TextView retrofit1Tv;
    private static final String TAG = "Retrofit1Activity";

    private Retrofit mRetrofit;
    private ApiStores mApiStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit1);
        ButterKnife.bind(this);

        // 返回Json
        mRetrofit = new Retrofit.Builder() //01:获取Retrofit对象
                .baseUrl(ApiStores.API_SERVER_URL) //02采用链式结构绑定Base url
                // 注意：字符创解析器要放在Gson解析器前面，不然无法解析字符串
                //使用工厂模式创建字符串解析器
                .addConverterFactory(ScalarsConverterFactory.create())
                //使用工厂模式创建Gson的解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //04获取API接口的实现类的实例对象
        mApiStores = mRetrofit.create(ApiStores.class);

    }

    // 设置TextView
    private void setText(String s) {
        try {
            retrofit1Tv.setText(s);
        } catch (Exception e) {
            Log.e(TAG, "setText: ", e);
        }
    }

    @OnClick({R.id.retrofit1_btn_json, R.id.retrofit1_btn_str})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 返回JSON
            case R.id.retrofit1_btn_json:
                Log.e(TAG, "onViewClicked: " + "返回json数据");
                // 调用请求方法，并得到Call实例
                Call<WeatherModel> mModelCall = mApiStores.loadDataByJson("101190201");
                // 异步请求
                mModelCall.enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

                        if (response.body() == null) {
                            setText("null");
                        } else {
                            setText(response.body().getWeatherinfo().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
                break;
            // 返回字符串
            case R.id.retrofit1_btn_str:
                Call<String> mModelCall2 = mApiStores.loadDataByString("101190201");
                mModelCall2.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() == null) {
                            setText("null");
                        } else {
                            setText(response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
                break;
        }
    }
}
