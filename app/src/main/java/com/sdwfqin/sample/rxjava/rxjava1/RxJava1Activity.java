package com.sdwfqin.sample.rxjava.rxjava1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sdwfqin.sample.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RxJava1Activity extends AppCompatActivity {

    private static final String TAG = "RxJava1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java1);

        // 入门
        rx01();
        // 链式操作,dispose()
        rx02();
    }

    private void rx01() {

        Log.e(TAG, "rx01: ====== start ======");

        //创建一个 Observable (被观察者)
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            /**
             * ObservableEmitter：它可以通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)
             * 发出next事件、complete（完成）事件和error（错误）事件
             *
             * Observable可以发送无限个onNext, Observer也可以接收无限个onNext.
             *
             * 当Observable发送了一个onComplete后, Observable的onComplete之后的事件将会继续发送,
             * 而Observer收到onComplete事件之后将不再继续接收事件.
             *
             * 当Observable发送了一个onError后, Observableon的Error之后的事件将继续发送,
             * 而Observer收到onError事件之后将不再继续接收事件.
             */
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                // 完成
                emitter.onComplete();
            }
        });
        //创建一个 Observer(观察者)
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error", e);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "complete");
                Log.e(TAG, "rx01: ====== end ======");
            }
        };

        //建立连接
        observable.subscribe(observer);
    }

    private void rx02() {

        Log.e(TAG, "rx02: ====== start ======");

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 3; i++) {
                    Log.e(TAG, "发送: " + i);
                    e.onNext(i);
                }
                e.onComplete();
                Log.e(TAG, "发送: " + 99);
                e.onNext(99);
            }
        }).subscribe(new Observer<Integer>() {

            // 调用dispose()会导致Observer不在接收事件
            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "绑定: ");
                disposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(TAG, "接收: " + integer);
                if (integer == 1) {
                    disposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
                Log.e(TAG, "rx02: ====== end ======");
            }
        });
    }
}
