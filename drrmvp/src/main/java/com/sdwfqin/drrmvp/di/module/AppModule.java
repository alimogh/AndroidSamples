package com.sdwfqin.drrmvp.di.module;

import com.sdwfqin.drrmvp.base.App;
import com.sdwfqin.drrmvp.model.DataManager;
import com.sdwfqin.drrmvp.model.http.HttpHelper;
import com.sdwfqin.drrmvp.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
