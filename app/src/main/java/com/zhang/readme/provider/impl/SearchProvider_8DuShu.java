package com.zhang.readme.provider.impl;

import android.util.Log;

import com.zhang.readme.entity.Book;
import com.zhang.readme.entity.BookList;
import com.zhang.readme.provider.BaseProvider;
import com.zhang.readme.provider.SearchProvider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhang on 2017/3/23.
 *
 * 搜索内容提供源 (88读书)
 * 来自 http://www.8dushu.com/
 */

public class SearchProvider_8DuShu extends BaseProvider implements SearchProvider {

    /** 88读书网全部小说页面 */
    private static final String URL = "http://zn.8dushu.com/cse/search?s=16617183703213723707&entry=1&ie=%s&q=%s";
    private static final String ENCODE = "utf-8";

    private Document document;

    public SearchProvider_8DuShu(String title) {
        String url = String.format(URL, ENCODE, title);
        this.document = getLink(url);
    }

    @Override
    public BookList getSearchResult() {
        BookList books = new BookList();
        if (document != null) {
            Element results = document.getElementById("results");
            Elements result_list = results.getElementsByClass("result-list");
            if (! result_list.isEmpty()) {
                Elements book_div = result_list.get(0).getElementsByClass("result-item result-game-item");
                for (Element e : book_div) {
                    Element detail = e.getElementsByClass("result-game-item-detail").get(0);
                    //获取标题和链接
                    Element title_h3 = detail.getElementsByClass("result-item-title result-game-item-title").get(0);
                    Element title_a = title_h3.getElementsByTag("a").get(0);
                    String title = title_a.attr("title");
                    String url = title_a.attr("href");
                    //获取作者
                    Element author_p = detail.getElementsByClass("result-game-item-info-tag").get(0);
                    String author = author_p.getElementsByTag("span").get(1).text();

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setBookPath(url);
                    books.add(book);
                }
            }
        }
        return books;
    }
}
