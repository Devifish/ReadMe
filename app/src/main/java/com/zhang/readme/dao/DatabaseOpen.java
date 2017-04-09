package com.zhang.readme.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhang.readme.util.Config;

/**
 * Created by zhang on 2017/3/5.
 *
 * 数据库创建
 */

public class DatabaseOpen extends SQLiteOpenHelper {

    DatabaseOpen(Context context) {
        super(context, Config.Database.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Config.Database.SQL_BOOKS);
        db.execSQL(Config.Database.SQL_BOOKMARK);
        db.execSQL(Config.Database.SQL_USER);

        db.execSQL("insert into books(title, author, book_path, image_path) values ('一念永恒', '耳根', 'http://www.8dushu.com/xiaoshuo/69/69059/', '/data/user/0/com.zhang.readme/cache/69059s.jpg')");
        db.execSQL("insert into books(title, author, book_path, image_path) values ('圣虚', '辰东', 'http://www.8dushu.com/xiaoshuo/81/81637/', '/data/user/0/com.zhang.readme/cache/81637s.jpg')");

        db.execSQL("insert into bookmark(book_id, name, book_index, mark_class) values (1, '一念永恒', 5, 'auto')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE *");
        onCreate(db);
    }
}
