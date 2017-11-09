package com.sdwfqin.mvpseed.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sdwfqin.mvpseed.di.component.ActivityComponent;
import com.sdwfqin.mvpseed.di.component.DaggerActivityComponent;
import com.sdwfqin.mvpseed.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 描述：Activity的基类
 *
 * @author sdwfqin
 * @date 2017/6/9
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected Activity mContext;
    private Unbinder mUnBinder;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        setContentView(getLayout());
        onViewCreated();
    }

    protected void onViewCreated() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册
     */
    protected abstract void initInject();

    /**
     * 加载布局
     */
    protected abstract int getLayout();

    /**
     * 加载数据
     */
    protected abstract void initEventAndData();
}