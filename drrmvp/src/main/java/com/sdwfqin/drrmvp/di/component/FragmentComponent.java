package com.sdwfqin.drrmvp.di.component;

import android.app.Activity;

import com.sdwfqin.drrmvp.di.module.FragmentModule;
import com.sdwfqin.drrmvp.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
}
