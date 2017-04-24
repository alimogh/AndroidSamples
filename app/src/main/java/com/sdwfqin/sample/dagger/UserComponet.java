package com.sdwfqin.sample.dagger;

import dagger.Component;

/**
 * Created by sdwfqin on 2017/4/24.
 */
@Component(modules = {UserModule.class})
public interface UserComponet {
    void inject(DaggerActivity daggerActivity);
}
