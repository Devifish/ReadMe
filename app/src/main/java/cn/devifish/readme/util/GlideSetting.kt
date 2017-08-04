package cn.devifish.readme.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * Created by zhang on 2017/8/2.
 * Glide模块
 */

@GlideModule
class GlideSetting : AppGlideModule() {

    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        super.registerComponents(context, glide, registry)
    }

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        if (builder != null) {
            builder.setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
        }
    }

}