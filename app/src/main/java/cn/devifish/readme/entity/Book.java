package cn.devifish.readme.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhang on 2017/2/28.
 *
 * 书籍信息原型类
 */

public class Book implements Parcelable {

    private int id;
    private String title;
    private String author;
    private String imagePath;
    private String bookPath;

    public Book() {}

    private Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        imagePath = in.readString();
        bookPath = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", bookPath='" + bookPath + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(imagePath);
        dest.writeString(bookPath);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
