package com.zhang.readme.provider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by zhang on 2017/3/23.
 *
 * 内容提供者基类
 */

public abstract class BaseProvider {

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36";

    protected Document getLink(String url) {
        try {
            return Jsoup.connect(url)
                    .timeout(5000)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
