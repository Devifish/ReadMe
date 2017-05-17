package cn.devifish.readme.provider.impl;

import cn.devifish.readme.entity.ChapterList;
import cn.devifish.readme.entity.Chapter;
import cn.devifish.readme.provider.BookProvider;
import cn.devifish.readme.provider.BaseProvider;

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
            Element mulu = document.getElementsByClass("mulu").get(0);
            Elements ul = mulu.getElementsByTag("ul");
            Elements li = ul.get(0).getElementsByTag("li");
            for (Element ele : li) {
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
            Element intro = document.getElementsByClass("intro").get(0);
            return intro.text();
        }else return null;
    }

    @Override
    public String getBookImagePath() {
        if (document != null) {
            Element jieshao = document.getElementsByClass("jieshao").get(0);
            Element lf = jieshao.getElementsByClass("lf").get(0);
            Elements img = lf.getElementsByTag("img");
            if (!img.isEmpty()) {
                return img.attr("src");
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
