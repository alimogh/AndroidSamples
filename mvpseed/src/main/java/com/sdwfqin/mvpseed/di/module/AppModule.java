package com.sdwfqin.mvpseed.di.module;

import com.sdwfqin.mvpseed.base.App;
import com.sdwfqin.mvpseed.model.http.HttpHelper;
import com.sdwfqin.mvpseed.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 描述：AppModule
 *
 * @author sdwfqin
 */
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
}
