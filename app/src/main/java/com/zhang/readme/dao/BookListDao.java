package com.zhang.readme.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhang.readme.entity.BookList;
import com.zhang.readme.entity.Book;

/**
 * Created by zhang on 2017/3/5.
 *
 */

public class BookListDao extends DatabaseOpen{

    private final static String TABLE_NAME = "books";

    public BookListDao(Context context) {super(context);}

    public BookList getBookList() {
        BookList bookList = new BookList();

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM books", null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setBookPath(cursor.getString(3));
            book.setImagePath(cursor.getString(4));
            bookList.add(book);
        }
        cursor.close();
        db.close();
        return bookList;
    }

    public boolean insert(Book book) {
        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM books WHERE title='%s' AND author='%s'",
                            book.getTitle(), book.getAuthor()), null);
        if (!cursor.moveToNext()) {
            cursor.close();
            String sql = String.format("INSERT INTO books(title, author, book_path, image_path) VALUES ('%s', '%s', '%s', '%s')",
                            book.getTitle(), book.getAuthor(), book.getBookPath(), book.getImagePath());
            db.execSQL(sql);
            db.close();
            return true;
        }else{
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean delete(Book book) {
        SQLiteDatabase db = super.getReadableDatabase();
        int i = db.delete(TABLE_NAME, "title=? and author=?", new String[] {book.getTitle(), book.getAuthor()});
        db.close();
        return i == 1;
    }

    public boolean exists(Book book) {
        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM books WHERE title='%s' AND author='%s'",
                            book.getTitle(), book.getAuthor()), null);
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
