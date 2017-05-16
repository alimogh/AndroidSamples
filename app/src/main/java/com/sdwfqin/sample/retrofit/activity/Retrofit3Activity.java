package com.sdwfqin.sample.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.retrofit.api.RequestPostApi;
import com.sdwfqin.sample.retrofit.model.PostModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit3Activity extends AppCompatActivity {

    private static final String TAG = "Retrofit3Activity";

    @BindView(R.id.retrofit3_btn_post)
    Button retrofit3BtnPost;
    @BindView(R.id.retrofit3_tv)
    TextView retrofit3Tv;

    private Retrofit mRetrofit;
    private RequestPostApi searchApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit3);
        ButterKnife.bind(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.32.206:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava2
                .build();
        searchApi = mRetrofit.create(RequestPostApi.class);

        retrofit3BtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用请求方法，并得到Observable实例
                Observable<PostModel> observable = searchApi.addReviews("码农Mrz", "www.sdwfqin.com");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<PostModel>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull PostModel postModel) {
                                Log.e(TAG, "onNext: " + postModel.toString());
                                setText(postModel.toString());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG, "onError: ", e);
                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG, "onComplete: " + "所有事件完成");
                            }
                        });
            }
        });
    }

    // 设置TextView
    private void setText(String s) {
        try {
            retrofit3Tv.setText(s);
        } catch (Exception e) {
            Log.e(TAG, "setText: ", e);
        }
    }
}
