package com.sdwfqin.sample.dagger.model;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * 描述：通过这个Module绑定Controller
 *
 * @author sdwfqin
 * @date 2017/4/24
 */
@Module
public class HttpModule {

    @Provides
    public OkHttpClient providerOkHttpClient(){
        return new OkHttpClient().newBuilder().build();
    }
}
