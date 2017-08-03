package cn.devifish.readme.util

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * Created by zhang on 2017/8/3.
 *
 */
class BlurTransformation(val context: Context, val blurRadius: Float) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest?) {

    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return RSBlurUtil.blur(context, toTransform, blurRadius)
    }

}