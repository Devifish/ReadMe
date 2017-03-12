package com.zhang.readme.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhang on 2017/3/5.
 *
 *
 */

class DatabaseOpen extends SQLiteOpenHelper {

    private final static String NAME_DB = "readme.db";
    private final static String BOOKS_SQL = "CREATE TABLE BOOKS (" +
                    "  id              INTEGER PRIMARY KEY NOT NULL," +
                    "  name            VARCHAR(50)         NOT NULL," +
                    "  author          VARCHAR(10)," +
                    "  book_path       VARCHAR(100)        NOT NULL," +
                    "  image_path      VARCHAR(100)" +
                    ")";

    DatabaseOpen(Context context) {
        super(context, NAME_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BOOKS_SQL);
        db.execSQL("insert into books values (1, '一念永恒', '耳根', 'http://www.8dushu.com/xiaoshuo/69/69059/', null)");
        db.execSQL("insert into books values (2, '圣虚', '辰东', 'http://www.8dushu.com/xiaoshuo/81/81637/', null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
