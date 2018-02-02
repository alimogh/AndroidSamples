package com.sdwfqin.mvpseed.contract;

import com.sdwfqin.mvpseed.base.BasePresenter;
import com.sdwfqin.mvpseed.base.BaseView;
import com.sdwfqin.mvpseed.model.bean.TestBean;

/**
 * 描述：绑定类
 *
 * @author zhangqin
 * @date 2017/6/9
 */
public interface MainContract {

    interface View extends BaseView {

        /**
         * 设置TextView中的数据
         *
         * @param testBean
         */
        void showTextView(TestBean testBean);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取数据
         *
         * @param cityId
         */
        void getWeather(String cityId);
    }

}
