package com.zhang.readme.tools;

import android.support.annotation.Nullable;
import android.util.Log;

import com.zhang.readme.model.ChapterInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/3/7.
 *
 *
 */

public class BookReader {

    private final static String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393";
    private Document document;

    public BookReader(String url) {
        this.document = getLink(url);
    }

    @Nullable
    private Document getLink(String url) {
        try {
            return Jsoup.connect(url).header("User-Agent", USERAGENT).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChapterInfo> getChapterList() {
        List<ChapterInfo> list = new ArrayList<>();
        if (document != null) {
            Element element = document.getElementsByClass("mulu").get(0);
            Elements dl = element.getElementsByTag("ul");
            Elements dd = dl.get(0).getElementsByTag("li");
            for (Element ele : dd) {
                Elements temp = ele.getElementsByTag("a");
                if (!temp.isEmpty()) {
                    String name = temp.text();
                    String path = temp.attr("href");
                    list.add(new ChapterInfo(name, path));
                }
            }
        }

        return list;
    }

    public String getBookDetail() {
        Element element = document.getElementsByClass("intro").get(0);
        return element.text();
    }

    public String getBookImagePath() {
        Element root = document.getElementsByClass("jieshao").get(0);
        Element element = root.getElementsByClass("lf").get(0);
        Elements img_tag = element.getElementsByTag("img");
        if (!img_tag.isEmpty()) {
            return img_tag.attr("src");
        }else return null;
    }

}
