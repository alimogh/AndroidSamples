package com.sdwfqin.drrmvp.di.component;

import android.app.Activity;

import com.sdwfqin.drrmvp.ui.MainActivity;
import com.sdwfqin.drrmvp.di.module.ActivityModule;
import com.sdwfqin.drrmvp.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
