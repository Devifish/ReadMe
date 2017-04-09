package com.zhang.readme;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by zhang on 2017/3/31.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }



}
