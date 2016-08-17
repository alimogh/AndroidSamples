package com.sdwfqin.rxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

/**
 * Created by sdwfqin on 2016/8/17.
 */
public class PrintActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        String[] names = {"打印一", "打印二", "打印三"};
//        Observable.from(names)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String name) {
//                        Log.d(TAG, name);
//                    }
//                });

        Observable
                // 创建一个简单的Observable（被观察者）对象
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("打印一");
                        subscriber.onNext("打印二");
                        subscriber.onNext("打印三");
                        subscriber.onCompleted();
                    }
                })
                // 指定 subscribe() 发生在 IO 线程       事件产生线程
                .subscribeOn(Schedulers.io())
                // 指定 Subscriber 的回调发生在主线程     事件消费线程
                .observeOn(AndroidSchedulers.mainThread())
                /**
                 *  上面这段代码中，由于 subscribeOn(Schedulers.io()) 的指定，被创建的事件的内容 打印一、...
                 *  将会在 IO 线程发出；而由于 observeOn(AndroidScheculers.mainThread()) 的指定，
                 *  因此 subscriber 数字的打印将发生在主线程 。
                 *  事实上，这种在 subscribe() 之前写上两句 subscribeOn(Scheduler.io()) 和
                 *  observeOn(AndroidSchedulers.mainThread()) 的使用方式非常常见，
                 *  它适用于多数的 『后台线程取数据，主线程显示』的程序策略。
                 */
                // 订阅者
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.e(TAG, "onStart: ===============================");
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
                });
    }
}
