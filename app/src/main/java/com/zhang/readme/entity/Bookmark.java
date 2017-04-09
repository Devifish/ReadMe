package com.zhang.readme.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhang on 2017/4/7.
 *
 * 书签实体类
 */

public class Bookmark implements Parcelable {

    private int id;
    private int bookId;
    private String name;
    private int bookIndex;
    private String markClass;

    public Bookmark() {}

    private Bookmark(Parcel in) {
        id = in.readInt();
        bookId = in.readInt();
        name = in.readString();
        bookIndex = in.readInt();
        markClass = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }

    public String getMarkClass() {
        return markClass;
    }

    public void setMarkClass(String markClass) {
        this.markClass = markClass;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", name='" + name + '\'' +
                ", bookIndex=" + bookIndex +
                ", markClass='" + markClass + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(bookId);
        dest.writeString(name);
        dest.writeInt(bookIndex);
        dest.writeString(markClass);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bookmark> CREATOR = new Creator<Bookmark>() {
        @Override
        public Bookmark createFromParcel(Parcel in) {
            return new Bookmark(in);
        }

        @Override
        public Bookmark[] newArray(int size) {
            return new Bookmark[size];
        }
    };
}
