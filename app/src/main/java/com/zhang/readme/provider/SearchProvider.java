package com.zhang.readme.provider;

import com.zhang.readme.model.BookList;

/**
 * Created by zhang on 2017/3/23.
 *
 * 搜索内容提供者接口
 */

public interface SearchProvider {

    public BookList getSearchResult(String text);

}
