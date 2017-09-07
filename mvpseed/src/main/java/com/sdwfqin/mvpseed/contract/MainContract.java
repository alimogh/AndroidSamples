package com.sdwfqin.mvpseed.contract;

import com.sdwfqin.mvpseed.base.BasePresenter;
import com.sdwfqin.mvpseed.base.BaseView;
import com.sdwfqin.mvpseed.model.bean.TestBean;

/**
 * Created by sdwfqin on 2017/6/9.
 */

public interface MainContract {

    interface View extends BaseView {

        void showTextView(TestBean testBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getWeather(String cityId);
    }

}
