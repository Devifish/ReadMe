package com.zhang.readme.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhang.readme.model.BookInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/3/5.
 *
 */

public class BooksDao {

    private Context context;

    public BooksDao(Context context) {
        this.context = context;
    }

    public List<BookInfo> getBookList() {
        List<BookInfo> book_list = new ArrayList<>();

        DatabaseOpen database = new DatabaseOpen(context);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books", null);

        while (cursor.moveToNext()) {
            BookInfo book = new BookInfo();
            book.setId(cursor.getInt(0));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setBookPath(cursor.getString(3));
            book.setImagePath(cursor.getString(4));
            book.setDetail(cursor.getString(5));

            book_list.add(book);
        }

        cursor.close();
        db.close();
        database.close();

        return book_list;
    }
}
