package com.wuxiaolong.androidmvpsample.mvp;

import android.os.Bundle;

import com.wuxiaolong.androidmvpsample.base.BasePresenter;
import com.wuxiaolong.androidmvpsample.base.BaseActivity;


/**
 * Created by WuXiaolong on 2016/3/30.
 * github:https://github.com/WuXiaolong/
 * weibo:http://weibo.com/u/2175011601
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 创建Presenter绑定View
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    // 子类实现，创建Presenter绑定View
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            // 调用BasePresenter中的方法取消View关联
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
