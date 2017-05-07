package com.sdwfqin.greendaosample.model.interactor;

import android.util.Log;

import com.sdwfqin.greendaosample.BaseApplication;
import com.sdwfqin.greendaosample.model.entry.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdwfqin on 2017/5/5.
 */

public class MainInteractorImpl implements MainInteractor {

    private static final String TAG = "MainInteractorImpl";

    @Override
    public void initData(OnFinishedListener listener) {
        Log.e(TAG, "initData: " + "刷新");
        List<Student> list = new ArrayList<Student>();
        try {
            list = BaseApplication.getDaoInstant().getStudentDao().loadAll();
        } catch (Exception e) {
            Log.e(TAG, "initData: ", e);
            listener.onFinishedError("数据读取失败");
        }
        Log.e(TAG, "initData: " + list.toString());
        listener.onFinishedSuccess(list);
    }
}
