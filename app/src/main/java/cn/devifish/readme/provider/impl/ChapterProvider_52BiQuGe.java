package cn.devifish.readme.provider.impl;

import cn.devifish.readme.provider.BaseProvider;
import cn.devifish.readme.provider.ChapterProvider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by zhang on 2017/3/26.
 *
 * 章节内容提供源 (52笔趣阁)
 * 来自 http://www.52biquge.com/
 */

public class ChapterProvider_52BiQuGe extends BaseProvider implements ChapterProvider {

    private Document document;

    public ChapterProvider_52BiQuGe(String url) {
        this.document = super.getLink(url);
    }

    @Override
    public String getBookChapterText() {
        if (document != null) {
            Element content = document.getElementById("content");
            if (content.isBlock()) {
                return formatChapterText(content.html());
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
