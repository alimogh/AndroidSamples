package com.sdwfqin.greendaosample.main;

import android.util.Log;
import android.view.View;

import com.sdwfqin.greendaosample.R;
import com.sdwfqin.greendaosample.model.Student;

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
    public void OnItemChildClick(View view, Student student, int position) {
        Log.e(TAG, "onItemClick: " + "单击");

        if (mainView != null) {
            switch (view.getId()) {
                case R.id.btn_xg:
                    Log.e(TAG, "onItemClick: " + "修改");
                    mainView.upData(student, position);
                    break;
                case R.id.btn_del:
                    Log.e(TAG, "onItemClick: " + "删除");
                    mainView.delData(student, position);
                    break;
                case R.id.ll_a:
                    Log.e(TAG, "onItemClick: " + "点击条目");
                    break;
            }
        }
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
