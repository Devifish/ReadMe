package cn.devifish.readme.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zhang on 2017/3/13.
 *
 * 书籍详情页原型类
 */

public class BookDetail implements Parcelable {

    private Book book;
    private Bookmark bookmark;
    private String bookInfo;
    private List<Chapter> chapterList;

    public BookDetail(){}

    private BookDetail(Parcel in) {
        book = in.readParcelable(Book.class.getClassLoader());
        bookmark = in.readParcelable(Bookmark.class.getClassLoader());
        bookInfo = in.readString();
        chapterList = in.createTypedArrayList(Chapter.CREATOR);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark progress) {
        this.bookmark = progress;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> list) {
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

    public int getBookmarkIndex() {
        if (bookmark != null)
            return bookmark.getBookIndex();
        else return 0;
    }

    public void setBookmarkIndex(int bookmarkIndex) {
        if (bookmark != null) {
            bookmark.setBookIndex(bookmarkIndex);
        }
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "\n\tbook=" + book.toString() +
                "\n\tbookmark=" + bookmark.toString() +
                "\n}";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(book, flags);
        dest.writeParcelable(bookmark, flags);
        dest.writeString(bookInfo);
        dest.writeTypedList(chapterList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookDetail> CREATOR = new Creator<BookDetail>() {
        @Override
        public BookDetail createFromParcel(Parcel in) {
            return new BookDetail(in);
        }

        @Override
        public BookDetail[] newArray(int size) {
            return new BookDetail[size];
        }
    };
}
