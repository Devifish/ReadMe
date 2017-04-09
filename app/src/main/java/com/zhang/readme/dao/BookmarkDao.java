package com.zhang.readme.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public boolean updateAutoBookmark(int book_id, int book_index) {
        SQLiteDatabase db = super.getReadableDatabase();
        db.execSQL(String.format("UPDATE %s bookmark=%s WHERE _id=%s and book_class='auto'", TABLE_NAME, book_index, book_id));
        db.close();
        return true;
    }

}
