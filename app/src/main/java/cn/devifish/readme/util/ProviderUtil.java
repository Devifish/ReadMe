package cn.devifish.readme.util;

import cn.devifish.readme.provider.BookProvider;
import cn.devifish.readme.provider.ChapterProvider;
import cn.devifish.readme.provider.SearchProvider;
import cn.devifish.readme.provider.impl.BookProvider_52BiQuGe;
import cn.devifish.readme.provider.impl.BookProvider_8DuShu;
import cn.devifish.readme.provider.impl.ChapterProvider_52BiQuGe;
import cn.devifish.readme.provider.impl.ChapterProvider_8DuShu;
import cn.devifish.readme.provider.impl.SearchProvider_8DuShu;

/**
 * Created by zhang on 2017/3/21.
 *
 * 内容提供者对象获取工具
 */

public class ProviderUtil {

    public static final int PROVIDER_8DUSHU = 0;
    public static final int PROVIDER_52BIQUGE = 1;

    private static int providerID = -1;

    private static ProviderUtil util;
    private ProviderUtil() {}

    public static ProviderUtil Builder(int id) {
        providerID = id;
        if (util == null) {
            util = new ProviderUtil();
        }
        return util;
    }

    public BookProvider getBookProvider(String url) {
        switch (providerID) {
            case PROVIDER_8DUSHU: return new BookProvider_8DuShu(url);
            case PROVIDER_52BIQUGE: return new BookProvider_52BiQuGe(url);
        }
        return null;
    }

    public ChapterProvider getChapterProvider(String url) {
        switch (providerID) {
            case PROVIDER_8DUSHU: return new ChapterProvider_8DuShu(url);
            case PROVIDER_52BIQUGE: return new ChapterProvider_52BiQuGe(url);
        }
        return null;
    }

    public SearchProvider getSearchProvider(String title) {
        switch (providerID) {
            case PROVIDER_8DUSHU: return new SearchProvider_8DuShu(title);
        }
        return null;
    }

}