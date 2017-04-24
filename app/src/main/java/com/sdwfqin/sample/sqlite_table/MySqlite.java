package com.sdwfqin.sample.sqlite_table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sdwfqin on 2017/2/28.
 */

public class MySqlite extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lights.db";

    public MySqlite(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //创建表SQL语句
        String stu_table = "create table usertable" +
                "(_id integer primary key autoincrement," +
                "name text," +
                "red text," +
                "green text," +
                "yellow text)";
        sqLiteDatabase.execSQL(stu_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
