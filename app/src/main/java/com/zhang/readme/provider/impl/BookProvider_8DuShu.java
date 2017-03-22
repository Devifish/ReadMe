package com.zhang.readme.provider.impl;

import com.zhang.readme.model.ChapterList;
import com.zhang.readme.model.Chapter;
import com.zhang.readme.provider.BookProvider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhang on 2017/3/7.
 *
 * 书籍内容提供源 (88读书)
 * 来自 http://www.8dushu.com/
 */

public class BookProvider_8DuShu extends BookProvider {


    private Document document;

    public BookProvider_8DuShu(String url) {
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
                    chapter.setUrl(temp.attr("href"));
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

    @Override
    public String getBookChapterText(String url) {
        if (document != null) {
            Element novel = document.getElementsByClass("novel").get(0);
            Element yd_text2 = novel.getElementsByClass("yd_text2").get(0);
            if (!yd_text2.isBlock()) {
                return yd_text2.text();
            }
        }

        return null;
    }

}
