package com.sdwfqin.mvpseed.di.component;

import android.app.Activity;

import com.sdwfqin.mvpseed.di.module.FragmentModule;
import com.sdwfqin.mvpseed.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
}
