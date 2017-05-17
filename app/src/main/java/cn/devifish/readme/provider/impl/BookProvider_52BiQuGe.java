package cn.devifish.readme.provider.impl;

import cn.devifish.readme.entity.Chapter;
import cn.devifish.readme.entity.ChapterList;
import cn.devifish.readme.provider.BaseProvider;
import cn.devifish.readme.provider.BookProvider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhang on 2017/3/26.
 *
 * 书籍内容提供源 (52笔趣阁)
 * 来自 http://www.52biquge.com/
 */

public class BookProvider_52BiQuGe extends BaseProvider implements BookProvider {

    private Document document;
    private String url = "http://www.52biquge.com";

    public BookProvider_52BiQuGe(String url) {
        this.document = super.getLink(url);
    }

    @Override
    public ChapterList getChapterList() {
        ChapterList list = new ChapterList();
        if (document != null) {
            Element element = document.getElementById("list");
            Elements dl = element.getElementsByTag("dl");
            Elements dd = dl.get(0).getElementsByTag("dd");
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
            Element element = document.getElementById("intro");
            return element.text();
        }else return null;
    }

    @Override
    public String getBookImagePath() {
        if (document != null) {
            Element fmimg = document.getElementById("fmimg");
            Elements img_tag = fmimg.getElementsByTag("img");
            if (!img_tag.isEmpty()) {
                return url + img_tag.attr("src");
            }
        }
        return null;
    }

    @Override
    public String getUpdateInfo() {
        return null;
    }

    @Override
    public String getUpdateTime() {
        return null;
    }
}
