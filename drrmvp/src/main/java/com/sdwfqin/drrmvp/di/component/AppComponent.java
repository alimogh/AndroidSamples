package com.sdwfqin.drrmvp.di.component;

import com.sdwfqin.drrmvp.base.App;
import com.sdwfqin.drrmvp.di.module.AppModule;
import com.sdwfqin.drrmvp.di.module.HttpModule;
import com.sdwfqin.drrmvp.model.DataManager;
import com.sdwfqin.drrmvp.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
