package com.sdwfqin.mvpseed.di.component;

import android.app.Activity;

import com.sdwfqin.mvpseed.ui.MainActivity;
import com.sdwfqin.mvpseed.di.module.ActivityModule;
import com.sdwfqin.mvpseed.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
