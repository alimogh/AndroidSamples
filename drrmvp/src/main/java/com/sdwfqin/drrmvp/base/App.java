package com.sdwfqin.drrmvp.base;

import android.app.Application;

import com.sdwfqin.drrmvp.di.component.AppComponent;
import com.sdwfqin.drrmvp.di.component.DaggerAppComponent;
import com.sdwfqin.drrmvp.di.module.AppModule;
import com.sdwfqin.drrmvp.di.module.HttpModule;

/**
 * Created by sdwfqin on 2017/6/9.
 */
public class App extends Application {

    public static AppComponent appComponent;
    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
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
