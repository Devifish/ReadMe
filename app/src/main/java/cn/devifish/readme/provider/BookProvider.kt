package cn.devifish.readme.provider

import cn.devifish.readme.util.Config
import cn.devifish.readme.util.OkHttpUtil
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory

/**
 * Created by zhang on 2017/7/27.
 *
 */
class BookProvider {

    private object Inner {
        val Instance = Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(OkHttpUtil.getInstance())
                .build()!!;
    }

    companion object {
        fun getInstance(): Retrofit = Inner.Instance
    }

}