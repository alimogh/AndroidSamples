package com.sdwfqin.greendaosample.presenter;

import com.sdwfqin.greendaosample.model.entry.Student;

/**
 * Created by sdwfqin on 2017/5/5.
 */

public interface MainPresenter {

    void onResume();

    void OnClickUpData(Student student, int position);

    void OnClickDelData(Student student, int position);

    void createData();

    void onDestroy();
}
