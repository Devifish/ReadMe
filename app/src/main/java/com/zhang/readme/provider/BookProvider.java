package com.zhang.readme.provider;

import com.zhang.readme.entity.ChapterList;

/**
 * Created by zhang on 2017/3/23.
 *
 * 书籍内容提供者接口
 */

public interface BookProvider {

    /** 获取所有章节列表 */
    public ChapterList getChapterList();

    /** 获取小说详情信息 */
    public String getBookInfo();

    /** 获取小说封面图路径 */
    public String getBookImagePath();

}
