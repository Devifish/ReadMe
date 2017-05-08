package com.zhang.readme.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhang.readme.App;
import com.zhang.readme.entity.Book;
import com.zhang.readme.entity.Bookmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/4/8.
 *
 *
 */

public class BookmarkDao extends DatabaseOpen {

    public static final String BOOKMARK_AUTO = "auto";
    public static final String BOOKMARK_USER = "user";
    private final static String TABLE_NAME = "bookmark";

    public BookmarkDao(Context context) {
        super(context);
    }

    public Bookmark getAutoBookmark(int book_id) {
        Bookmark bookmark = new Bookmark();
        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE book_id=%s AND mark_class='auto'", TABLE_NAME, book_id), null);

        if (cursor.moveToNext()) {
            bookmark.setId(cursor.getInt(0));
            bookmark.setBookId(cursor.getInt(1));
            bookmark.setName(cursor.getString(2));
            bookmark.setBookIndex(cursor.getInt(3));
            bookmark.setMarkClass(cursor.getString(4));
        }
        cursor.close();
        db.close();
        return bookmark;
    }

    public void updateAutoBookmark(int book_id, int index) {
        SQLiteDatabase db = super.getReadableDatabase();
        db.execSQL(String.format("UPDATE %s SET book_index=%s WHERE book_id=%s and mark_class='auto'",
                    TABLE_NAME, index, book_id));
        db.close();
    }

    public void insertBookmark(Bookmark bookmark) {
        SQLiteDatabase db = super.getReadableDatabase();
        String sql = String.format("INSERT INTO %s(book_id, name, book_index, mark_class) VALUES (%s, '%s', %s, '%s')",
                TABLE_NAME, bookmark.getBookId(), bookmark.getName(), bookmark.getBookIndex(), bookmark.getMarkClass());
        db.execSQL(sql);
        db.close();
    }

    public boolean exists(int id) {
        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM bookmark WHERE _id=%s",
                id), null);
        if (cursor.moveToNext()) {
            cursor.close();
            db.close();
            return true;
        }else{
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean existsByClass(int book_id, String mark_class) {
        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM bookmark WHERE book_id=%s and mark_class='%s'" ,
                book_id, mark_class), null);
        if (cursor.moveToNext()) {
            cursor.close();
            db.close();
            return true;
        }else{
            cursor.close();
            db.close();
            return false;
        }
    }

}
