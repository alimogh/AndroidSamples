package com.sdwfqin.mvpseed.di.module;

import android.app.Activity;

import com.sdwfqin.mvpseed.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 描述：ActivityModule
 *
 * @author zhangqin
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
