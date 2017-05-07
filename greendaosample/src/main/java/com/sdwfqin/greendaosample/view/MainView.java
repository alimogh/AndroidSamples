package com.sdwfqin.greendaosample.view;

import com.sdwfqin.greendaosample.model.entry.Student;

import java.util.List;

/**
 * Created by sdwfqin on 2017/5/5.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void showBottomSheetDialog();

    void upData(Student student, int position);

    void delData(Student student, int position);

    void showMessage(String message);

    void setItems(List<Student> items);
}
