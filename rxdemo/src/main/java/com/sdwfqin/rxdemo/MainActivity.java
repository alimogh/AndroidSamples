package com.sdwfqin.rxdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sdwfqin on 2016/12/06
 */
public class MainActivity extends BaseActivity {

    Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mObservable.subscribe(mObserver);
        // 或
        mObservable
                // 指定 subscribe() 发生在 IO 线程，事件产生线程
                .subscribeOn(Schedulers.io())
                // 指定 Subscriber 的回调发生在主线程，事件消费线程
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    // 被观察者
    Observable mObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    });

    // 观察者
    Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ");
        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext: " + s);
        }
    };

    //Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的
    Subscriber<String> mSubscriber = new Subscriber<String>() {

        // 新增加的方法。它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作。默认为空
        @Override
        public void onStart() {
            super.onStart();
            Log.e(TAG, "onStart: ");
        }

        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ");
        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext: " + s);
        }
    };

}
