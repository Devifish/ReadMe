package cn.devifish.readme.provider

import cn.devifish.readme.util.Config
import cn.devifish.readme.util.OkHttpUtil
import cn.devifish.readme.util.RetrofitUtil
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory

/**
 * Created by zhang on 2017/7/22.
 * 没什么说的
 */
open class BaseProvider {

    protected val retrofit: Retrofit = RetrofitUtil.getInstance()

}