package com.zhangqin.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * |================================================================================================
 * |
 * |    作    者：张钦
 * |
 * |    版    本：1.0
 * |
 * |    创建时间：2016/2/27  15:03
 * |
 * |    简要描述：
 * |
 * |    修改历史：
 * |
 * |================================================================================================
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book (" +
            "_id integer primary key autoincrement," +
            "name text," +
            "result integer)";

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
