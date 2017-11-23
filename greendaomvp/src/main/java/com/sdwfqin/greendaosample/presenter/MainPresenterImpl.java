package com.sdwfqin.greendaosample.presenter;

import com.sdwfqin.greendaosample.model.entry.Student;
import com.sdwfqin.greendaosample.model.interactor.MainInteractor;
import com.sdwfqin.greendaosample.view.MainView;

import java.util.List;

/**
 * 描述：P层实现
 *
 * @author sdwfqin
 * @date 2017/5/5
 */
public class MainPresenterImpl implements MainPresenter {

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
        mainInteractor.initData(new MainInteractor.OnFinishedListener() {
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
        });
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
    public void OnUpData(final Student student, final int position) {
        mainInteractor.upData(student, new MainInteractor.OnMesListener() {
            @Override
            public void onMesSuccess(String s) {
                mainView.upAdapter(student, position);
                mainView.showMessage(s);
            }

            @Override
            public void onMesError(String s) {
                mainView.showMessage(s);
            }
        });
    }

    @Override
    public void OnDelData(Student student, final int position) {
        mainInteractor.delData(student, new MainInteractor.OnMesListener() {
            @Override
            public void onMesSuccess(String s) {
                mainView.upAdapter(position);
                mainView.showMessage(s);
            }

            @Override
            public void onMesError(String s) {
                mainView.showMessage(s);
            }
        });
    }

    @Override
    public void OnCreateData(final Student student) {
        mainInteractor.createData(student, new MainInteractor.OnMesListener() {
            @Override
            public void onMesSuccess(String s) {
                mainView.addAdapter(student);
                mainView.showMessage(s);
            }

            @Override
            public void onMesError(String s) {
                mainView.showMessage(s);
            }
        });
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
}
