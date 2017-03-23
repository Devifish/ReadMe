package com.zhang.readme.provider;

import com.zhang.readme.model.ChapterList;

/**
 * Created by zhang on 2017/3/23.
 *
 * 书籍内容提供者接口
 */

public interface BookProvider {

    public ChapterList getChapterList();

    public String getBookInfo();

    public String getBookImagePath();

}
