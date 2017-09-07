package com.sdwfqin.mvpseed.di.component;

import com.sdwfqin.mvpseed.base.App;
import com.sdwfqin.mvpseed.di.module.AppModule;
import com.sdwfqin.mvpseed.di.module.HttpModule;
import com.sdwfqin.mvpseed.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
