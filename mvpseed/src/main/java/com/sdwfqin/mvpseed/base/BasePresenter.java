package com.sdwfqin.mvpseed.base;

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
