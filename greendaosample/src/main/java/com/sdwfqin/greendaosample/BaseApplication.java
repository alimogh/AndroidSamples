package com.sdwfqin.greendaosample;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.sdwfqin.greendaosample.model.entry.DaoMaster;
import com.sdwfqin.greendaosample.model.entry.DaoSession;

/**
 * Created by sdwfqin on 2017/5/1.
 */

public class BaseApplication extends Application {

    // DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // 配置数据库
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        // DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
        // DevOpenHelper: 创建SQLite数据库的SQLiteOpenHelper的具体实现
        // 创建数据库student.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "student.db", null);
        // 获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        // 获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        // 获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
