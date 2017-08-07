package cn.devifish.readme.util

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by zhang on 2017/7/25.
 *
 */
class OkHttpUtil private constructor() {

    private object Inner {
        val Instance = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                            .header("User-Agent","ZhuiShuShenQi/3.10.1 (Android;;)")
                            .build()
                    chain.proceed(request)
                }
                .build()
    }

    companion object {
        fun getInstance(): OkHttpClient = Inner.Instance
    }




}