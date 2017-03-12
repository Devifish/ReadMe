package com.zhang.readme.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang on 2017/2/28.
 *
 * 书籍信息原型类
 */

public class BookInfo implements Serializable{

    private int id;
    private String title;
    private String author;
    private String detail;
    private String imagePath;
    private String bookPath;
    private List<ChapterInfo> chapter;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<ChapterInfo> getChapter() {
        return chapter;
    }

    public void setChapter(List<ChapterInfo> chapter) {
        this.chapter = chapter;
    }

    public String[] getChapterNameArray() {
        if (chapter != null) {
            String[] strings = new String[chapter.size()];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = chapter.get(strings.length - i - 1).toString();
            }
            return strings;
        }else return null;
    }

    @Override
    public int hashCode() {
        return title.hashCode() + author.hashCode();
    }
}
