package com.sdwfqin.sample.rxjava.rxjava5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sdwfqin.sample.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJava5Activity extends AppCompatActivity {

    private static final String TAG = "RxJava5Activity";
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java5);

        /**
         * Subscription.request()方法表示Subscriber要处理几个事件
         * emitter.requested()方法表示减少几个事件
         * 在Flowable中使用Subscription.cancel()关闭事件处理
         */
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 128; i++) {
                    Log.e(TAG, "emit " + i);
                    emitter.onNext(i);
                }
            }
            // BackpressureStrategy.ERROR  默认128kb  超出会抛出MissingBackpressureException异常
            // BackpressureStrategy.BUFFER 无大小限制
            // BackpressureStrategy.DROP   存不下的事件直接丢弃
            // BackpressureStrategy.LATEST 只保留最新的事件，与DROP相反
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                // .onBackpressureDrop()//添加背压策略
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e(TAG, "onSubscribe");
                        // 全局的
                        mSubscription = s;
                        // 处理多少个发送过来的事件
                        mSubscription.request(Long.MAX_VALUE);
                        // 取消接收事件
                        // mSubscription.cancel();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }
}
