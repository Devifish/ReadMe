package com.zhang.readme.dao;

import android.content.Context;

/**
 * Created by zhang on 2017/3/28.
 */

public class UserDao extends DatabaseOpen {

    private final static String TABLE_NAME = "user";

    public UserDao(Context context) {
        super(context);
    }
}
