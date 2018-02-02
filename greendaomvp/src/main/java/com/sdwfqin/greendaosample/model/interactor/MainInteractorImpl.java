package com.sdwfqin.greendaosample.model.interactor;

import android.util.Log;

import com.sdwfqin.greendaosample.BaseApplication;
import com.sdwfqin.greendaosample.model.entry.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqin on 2017/5/5.
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

    @Override
    public void upData(Student student, OnMesListener onMesListener) {
        Log.e(TAG, "initData: " + "修改");
        try {
            BaseApplication.getDaoInstant().getStudentDao().update(student);
            onMesListener.onMesSuccess("修改成功");
        } catch (Exception e) {
            onMesListener.onMesError("修改失败");
            Log.e(TAG, "onClick: ", e);
        }
    }

    @Override
    public void delData(Student student, OnMesListener onMesListener) {
        try {
            BaseApplication.getDaoInstant().getStudentDao().delete(student);
            onMesListener.onMesSuccess("删除成功");
        } catch (Exception e) {
            Log.e(TAG, "delData: ", e);
            onMesListener.onMesError("删除失败");
        }
    }

    @Override
    public void createData(Student student, OnMesListener onMesListener) {
        try {
            BaseApplication.getDaoInstant().getStudentDao().insertOrReplace(student);
            onMesListener.onMesSuccess("添加成功");
        } catch (Exception e) {
            onMesListener.onMesError("添加失败");
            Log.e(TAG, "onClick: ", e);
        }
    }
}
