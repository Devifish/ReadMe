package com.zhang.readme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhang on 2017/3/13.
 *
 * 书籍详情页原型类
 */

public class BookDetail {

    private Book book;
    private int readProgress;
    private String bookInfo;
    private ChapterList chapterList;

    public BookDetail(){}

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getReadProgress() {
        return readProgress;
    }

    public void setReadProgress(int progress) {
        this.readProgress = progress;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public ChapterList getChapterList() {
        return chapterList;
    }

    public void setChapterList(ChapterList list) {
        this.chapterList = list;
    }

    public String[] getChapterNameArray() {
        if (chapterList != null) {
            String[] strings = new String[chapterList.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = chapterList.get(strings.length - i - 1).toString();
            }
            return strings;
        }else return null;
    }
}
