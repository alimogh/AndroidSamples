package com.sdwfqin.mvpseed.di.component;

import android.app.Activity;

import com.sdwfqin.mvpseed.ui.MainActivity;
import com.sdwfqin.mvpseed.di.module.ActivityModule;
import com.sdwfqin.mvpseed.di.scope.ActivityScope;

import dagger.Component;

/**
 * 描述：Activity注入器
 *
 * @author zhangqin
 * @date 2017/11/9
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    /**
     * MainActivity注入
     *
     * @param mainActivity
     */
    void inject(MainActivity mainActivity);
}
