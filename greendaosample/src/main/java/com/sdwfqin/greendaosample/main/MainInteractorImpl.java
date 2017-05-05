package com.sdwfqin.greendaosample.main;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.sdwfqin.greendaosample.BaseApplication;
import com.sdwfqin.greendaosample.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

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
