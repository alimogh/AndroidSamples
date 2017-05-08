package com.sdwfqin.sample.dagger.Componet;

import com.sdwfqin.sample.dagger.DaggerActivity;
import com.sdwfqin.sample.dagger.model.UserModule;

import dagger.Component;

/**
 * Created by sdwfqin on 2017/4/24.
 */
// @Component：注入器，是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分
@Component(modules = {UserModule.class})
public interface UserComponet {
    void inject(DaggerActivity daggerActivity);
}