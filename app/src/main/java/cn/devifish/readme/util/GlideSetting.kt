package cn.devifish.readme.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

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
    }

}