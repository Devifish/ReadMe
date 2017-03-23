package com.zhang.readme.provider.impl;

import com.zhang.readme.model.ChapterList;
import com.zhang.readme.model.Chapter;
import com.zhang.readme.provider.BookProvider;
import com.zhang.readme.provider.BaseProvider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhang on 2017/3/7.
 *
 * 书籍内容提供源 (88读书)
 * 来自 http://www.8dushu.com/
 */

public class BookProvider_8DuShu extends BaseProvider implements BookProvider {

    private Document document;
    private String url;

    public BookProvider_8DuShu(String url) {
        this.url = url;
        this.document = super.getLink(url);
    }

    @Override
    public ChapterList getChapterList() {
        ChapterList list = new ChapterList();
        if (document != null) {
            Element element = document.getElementsByClass("mulu").get(0);
            Elements dl = element.getElementsByTag("ul");
            Elements dd = dl.get(0).getElementsByTag("li");
            for (Element ele : dd) {
                Elements temp = ele.getElementsByTag("a");
                if (!temp.isEmpty()) {
                    Chapter chapter = new Chapter();
                    chapter.setName(temp.text());
                    chapter.setUrl(url + temp.attr("href"));
                    list.add(chapter);
                }
            }
        }
        return list;
    }

    @Override
    public String getBookInfo() {
        if (document != null) {
            Element element = document.getElementsByClass("intro").get(0);
            return element.text();
        }else return null;
    }

    @Override
    public String getBookImagePath() {
        if (document != null) {
            Element jieshao = document.getElementsByClass("jieshao").get(0);
            Element element = jieshao.getElementsByClass("lf").get(0);
            Elements img_tag = element.getElementsByTag("img");
            if (!img_tag.isEmpty()) {
                return img_tag.attr("src");
            }
        }
        return null;
    }

}
