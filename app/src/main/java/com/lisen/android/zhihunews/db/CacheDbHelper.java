package com.lisen.android.zhihunews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/2.
 */
public class CacheDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "cache.db";
    private static final String CREATE_TABLE = "create table if not exists CacheList (" +
            "id integer primary key autoincrement," +
            "date integer unique," +
            "json text" +
            ")";
    public CacheDbHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
