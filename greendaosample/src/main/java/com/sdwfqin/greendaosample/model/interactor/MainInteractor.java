package com.sdwfqin.greendaosample.model.interactor;

import com.sdwfqin.greendaosample.model.entry.Student;

import java.util.List;

/**
 * Created by sdwfqin on 2017/5/5.
 */

public interface MainInteractor {

    interface OnFinishedListener {
        void onFinishedSuccess(List<Student> items);
        void onFinishedError(String s);
    }

    void initData(OnFinishedListener listener);
}
