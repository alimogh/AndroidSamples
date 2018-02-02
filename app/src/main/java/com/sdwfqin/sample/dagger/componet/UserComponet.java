package com.sdwfqin.sample.dagger.componet;

import com.sdwfqin.sample.dagger.DaggerActivity;
import com.sdwfqin.sample.dagger.model.HttpModule;
import com.sdwfqin.sample.dagger.model.UserModule;

import dagger.Component;

/**
 * 描述：注入器
 *
 * @author zhangqin
 * @date 2017/4/24
 */
// @Component：注入器，是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分
@Component(modules = {UserModule.class, HttpModule.class})
public interface UserComponet {

    /**
     * 绑定注入
     *
     * @param daggerActivity DaggerActivity
     */
    void inject(DaggerActivity daggerActivity);
}