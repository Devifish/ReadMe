package com.zhang.readme.provider;

/**
 * Created by zhang on 2017/3/23.
 *
 * 章节内容提供者接口
 */

public interface ChapterProvider {

    public String getBookChapterText();

    public String formatChapterText(String text);

}
