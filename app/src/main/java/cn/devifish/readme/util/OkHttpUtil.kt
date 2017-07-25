package cn.devifish.readme.util

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by zhang on 2017/7/25.
 *
 */
class OkHttpUtil private constructor() {

    private object Inner {
        var Instance = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    companion object {
        fun getInstance(): OkHttpClient = Inner.Instance
    }




}