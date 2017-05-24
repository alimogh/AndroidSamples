package com.sdwfqin.sample.dagger.model;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by sdwfqin on 2017/5/24.
 */
@Module
public class HttpModule {

    @Provides
    public OkHttpClient providerOkHttpClient(){
        return new OkHttpClient().newBuilder().build();
    }
}
