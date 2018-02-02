package com.sdwfqin.mvpseed.base;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.sdwfqin.mvpseed.di.component.AppComponent;
import com.sdwfqin.mvpseed.di.component.DaggerAppComponent;
import com.sdwfqin.mvpseed.di.module.AppModule;
import com.sdwfqin.mvpseed.di.module.HttpModule;

/**
 * 描述：
 *
 * @author zhangqin
 * @date 2017/6/9
 */
public class App extends Application {

    private static AppComponent appComponent;
    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        Utils.init(this);
        initLog();
    }

    private void initLog() {
        LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(Constants.LOG_TYPE)
                .setConsoleSwitch(Constants.LOG_TYPE);
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
