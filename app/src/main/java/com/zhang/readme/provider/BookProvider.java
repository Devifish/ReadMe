package com.zhang.readme.provider;

import android.support.annotation.Nullable;

import com.zhang.readme.model.ChapterList;
import com.zhang.readme.provider.impl.BookProvider_8DuShu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by zhang on 2017/3/21.
 *
 * 书籍信息内容提供者
 */

public abstract class BookProvider {

    private final static String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36";

    public final static int PROVIDER_8DUSHU = 0;

    protected Document getLink(String url) {
        try {
            return Jsoup.connect(url).header("User-Agent", USERAGENT).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static BookProvider Builder(String url, int source) {
        switch (source) {
            case PROVIDER_8DUSHU: return new BookProvider_8DuShu(url);
        }
        return null;
    }

    public abstract ChapterList getChapterList();
    public abstract String getBookInfo();
    public abstract String getBookImagePath();
    public abstract String getBookChapterText(String url);

}