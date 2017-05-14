package com.sdwfqin.sample.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.RequestGetApi;
import com.sdwfqin.sample.retrofit.model.BookSearchResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Activity extends AppCompatActivity {

    private static final String TAG = "Retrofit2Activity";
    @BindView(R.id.retrofit2_tv)
    TextView retrofit2Tv;
    private Retrofit mRetrofit;
    private RequestGetApi searchApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
        ButterKnife.bind(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchApi = mRetrofit.create(RequestGetApi.class);
    }

    // 设置TextView
    private void setText(String s) {
        try {
            retrofit2Tv.setText(s);
        } catch (Exception e) {
            Log.e(TAG, "setText: ", e);
        }
    }

    @OnClick({R.id.retrofit2_btn_async, R.id.retrofit2_btn_sync})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 异步请求
            case R.id.retrofit2_btn_async:
                // 调用请求方法，并得到Call实例
                Call<BookSearchResponse> call = searchApi.getSearchBooks("小王子", "", 0, 3);
                call.enqueue(new Callback<BookSearchResponse>() {
                    @Override
                    public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                        setText("异步请求结果: " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
                break;
            // 同步请求
            case R.id.retrofit2_btn_sync:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call<BookSearchResponse> call = searchApi.getSearchBooks("小王子", "", 0, 3);
                        try {
                            BookSearchResponse response = call.execute().body();
                            setText("同步请求结果: " + response.toString());
                        } catch (IOException e) {
                            Log.e(TAG, "run: ", e);
                        }
                    }
                }).start();
                break;
        }
    }
}
