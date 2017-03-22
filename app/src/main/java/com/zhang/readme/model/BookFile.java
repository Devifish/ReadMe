package com.zhang.readme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhang on 2017/3/16.
 *
 * 文件信息原型类
 */

public class BookFile implements Parcelable{

    private String name;
    private String path;

    public BookFile(){}

    private BookFile(Parcel in) {
        name = in.readString();
        path = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(path);
    }

    public static final Creator<BookFile> CREATOR = new Creator<BookFile>() {
        @Override
        public BookFile createFromParcel(Parcel in) {
            return new BookFile(in);
        }

        @Override
        public BookFile[] newArray(int size) {
            return new BookFile[size];
        }
    };

}
