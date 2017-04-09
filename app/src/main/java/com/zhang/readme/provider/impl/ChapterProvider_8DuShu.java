package com.zhang.readme.provider.impl;

import com.zhang.readme.provider.ChapterProvider;
import com.zhang.readme.provider.BaseProvider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhang on 2017/3/23.
 *
 * 章节内容提供源 (88读书)
 * 来自 http://www.8dushu.com/
 */

public class ChapterProvider_8DuShu extends BaseProvider implements ChapterProvider {

    private Document document;

    public ChapterProvider_8DuShu(String url) {
        this.document = super.getLink(url);
    }

    @Override
    public String getBookChapterText() {
        if (document != null) {
            Element novel = document.getElementsByClass("novel").get(0);
            Elements yd_text2 = novel.getElementsByClass("yd_text2");
            if (!yd_text2.isEmpty()) {
                return formatChapterText(yd_text2.html());
            }
        }
        return null;
    }

    @Override
    public String formatChapterText(String text) {
        text = text.replaceAll("<br> &nbsp;&nbsp;&nbsp;&nbsp;", "    ");
        text = text.replaceAll("<br>", "");
        text = text.replaceAll("&nbsp;", " ");
        return text;
    }
}
