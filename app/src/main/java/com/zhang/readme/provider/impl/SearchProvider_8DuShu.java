package com.zhang.readme.provider.impl;

import com.zhang.readme.model.BookList;
import com.zhang.readme.provider.BaseProvider;
import com.zhang.readme.provider.SearchProvider;

import org.jsoup.nodes.Document;

/**
 * Created by zhang on 2017/3/23.
 *
 * 搜索内容提供源 (88读书)
 * 来自 http://www.8dushu.com/
 */

public class SearchProvider_8DuShu extends BaseProvider implements SearchProvider {

    /** 88读书网全部小说页面 */
    private static final String url = "http://www.8dushu.com/allbook/";
    private Document document;

    public SearchProvider_8DuShu() {
        document = getLink(url);
    }

    @Override
    public BookList getSearchResult(String text) {
        return null;
    }



}
