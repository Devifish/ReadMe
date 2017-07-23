package cn.devifish.readme.provider

import cn.devifish.readme.util.Config
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory

/**
 * Created by zhang on 2017/7/22.
 *
 */
open class BaseProvider(client: OkHttpClient) {

    protected val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create())
            .client(client)
            .build();

}