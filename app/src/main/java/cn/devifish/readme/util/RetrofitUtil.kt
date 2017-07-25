package cn.devifish.readme.util

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory

/**
 * Created by zhang on 2017/7/25.
 *
 */
class RetrofitUtil {

    private object Inner {
        val Instance = Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(OkHttpUtil.getInstance())
                .build()!!;
    }

    companion object {
        fun getInstance() = Inner.Instance
    }

}