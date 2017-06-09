package com.sdwfqin.drrmvp.contract;

import com.sdwfqin.drrmvp.base.BasePresenter;
import com.sdwfqin.drrmvp.base.BaseView;
import com.sdwfqin.drrmvp.model.bean.WeatherBean;

/**
 * Created by sdwfqin on 2017/6/9.
 */

public interface MainContract {

    interface View extends BaseView {

        void showTextView(WeatherBean weatherBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getWeather(String cityId);
    }

}
