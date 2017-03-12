package com.zhang.readme.model;

import java.io.Serializable;

/**
 * Created by zhang on 2017/3/8.
 *
 * 章节信息原型类
 */

public class ChapterInfo implements Serializable {

    private String name;
    private String url;

    public ChapterInfo() {}
    public ChapterInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return name;
    }
}
