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
import rx.functions.Func1;
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

//        mObservable.subscribe(mObserver);
        // 或
        mObservable
                // 将String类型的对象转化为整数类型的对象
                // 过程： 接收到来自mObservable的全部数据，然后在返回一个新的Observable对象
                .flatMap(new Func1<String,Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(final String o) {
                        Log.e(TAG, "call: " + o);
                        return Observable.create(new Observable.OnSubscribe<Integer>() {
                            @Override
                            public void call(Subscriber<? super Integer> subscriber) {
                                subscriber.onNext(o.length());
                            }
                        });
                    }
                })
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
    //Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的
    Subscriber<Integer> mSubscriber = new Subscriber<Integer>() {

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
        public void onNext(Integer s) {
            Log.e(TAG, "onNext: " + s);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if(mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
    }
}
