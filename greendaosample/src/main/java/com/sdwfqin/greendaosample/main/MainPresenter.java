package com.sdwfqin.greendaosample.main;

import android.view.View;

import com.sdwfqin.greendaosample.model.Student;

/**
 * Created by sdwfqin on 2017/5/5.
 */

public interface MainPresenter {

    void onResume();

    void OnItemChildClick(View view, Student student, int position);

    void createData();

    void onDestroy();
}
