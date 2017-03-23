package com.zhang.readme.util;

import com.zhang.readme.provider.BookProvider;
import com.zhang.readme.provider.ChapterProvider;
import com.zhang.readme.provider.impl.BookProvider_8DuShu;
import com.zhang.readme.provider.impl.ChapterProvider_8DuShu;

/**
 * Created by zhang on 2017/3/21.
 *
 * 内容提供者对象获取工具
 */

public class ProviderUtil {

    public static final int PROVIDER_8DUSHU = 0;

    private static int providerID;

    private static final ProviderUtil util = new ProviderUtil();
    private ProviderUtil() {}

    public static ProviderUtil Builder(int id) {
        providerID = id;
        return util;
    }

    public BookProvider getBookProvider(String url) {
        switch (providerID) {
            case PROVIDER_8DUSHU: return new BookProvider_8DuShu(url);
        }
        return null;
    }

    public ChapterProvider getChapterProvider(String url) {
        switch (providerID) {
            case PROVIDER_8DUSHU: return new ChapterProvider_8DuShu(url);
        }
        return null;
    }

}