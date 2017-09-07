package com.sdwfqin.mvpseed.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.sdwfqin.mvpseed.di.component.DaggerFragmentComponent;
import com.sdwfqin.mvpseed.di.component.FragmentComponent;
import com.sdwfqin.mvpseed.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sdwfqin on 2017/8/3.
 * Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected LayoutInflater mInflater;
    /**
     * 标志位，标志已经初始化完成。
     */
    protected boolean isPrepared;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisible;
    /**
     * 是否加载过
     */
    protected boolean isLoad = false;
    private Unbinder mUnBinder;
    private String TAG = getLogTag();


    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    /**
     * Fragment的UI是否是可见
     * <p>
     * 在onCreateView方法之前调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), null);
        //指出fragment愿意添加item到选项菜单
        setHasOptionsMenu(true);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnBinder = ButterKnife.bind(this, view);
        mInflater = getLayoutInflater(savedInstanceState);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        initEventAndData();
        // 界面加载完成
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        mUnBinder.unbind();
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
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

    /**
     * 页面懒加载
     */
    protected void lazyLoad() {

    }

    private String getLogTag() {
        return this.getClass().getSimpleName();
    }

    protected void log_d(String msg) {
        LogUtils.d(TAG, msg);
    }

    protected void log_i(String msg) {
        LogUtils.i(TAG, msg);
    }

    protected void log_w(String msg) {
        LogUtils.w(TAG, msg);
    }

    protected void log_e(String msg) {
        LogUtils.e(TAG, msg);
    }
}