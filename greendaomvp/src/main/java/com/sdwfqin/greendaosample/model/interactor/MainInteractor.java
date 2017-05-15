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

    interface OnMesListener {
        void onMesSuccess(String s);

        void onMesError(String s);
    }

    void upData(Student student, OnMesListener onMesListener);

    void delData(Student student, OnMesListener onMesListener);

    void createData(Student student, OnMesListener onMesListener);
}
