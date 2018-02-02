package com.sdwfqin.mvpseed.di.component;

import android.app.Activity;

import com.sdwfqin.mvpseed.di.module.FragmentModule;
import com.sdwfqin.mvpseed.di.scope.FragmentScope;

import dagger.Component;

/**
 * 描述：Fragment注入器
 *
 * @author zhangqin
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
}
