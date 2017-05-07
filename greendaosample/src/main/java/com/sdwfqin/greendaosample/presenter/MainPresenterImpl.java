package com.sdwfqin.greendaosample.presenter;

import com.sdwfqin.greendaosample.model.entry.Student;
import com.sdwfqin.greendaosample.model.interactor.MainInteractor;
import com.sdwfqin.greendaosample.view.MainView;

import java.util.List;

/**
 * Created by sdwfqin on 2017/5/5.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnFinishedListener {

    private MainView mainView;
    private MainInteractor mainInteractor;
    private static final String TAG = "MainPresenterImpl";

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
        mainInteractor.initData(this);
    }

    @Override
    public void OnClickUpData(Student student, int position) {
        mainView.upData(student, position);
    }

    @Override
    public void OnClickDelData(Student student, int position) {
        mainView.delData(student, position);
    }

    @Override
    public void createData() {
        if (mainView != null) {
            mainView.showBottomSheetDialog();
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinishedSuccess(List<Student> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFinishedError(String s) {
        if (mainView != null) {
            mainView.showMessage(s);
        }
    }
}
