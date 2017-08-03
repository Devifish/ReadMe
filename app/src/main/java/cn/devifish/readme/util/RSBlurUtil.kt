package cn.devifish.readme.util

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RSRuntimeException
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

/**
 * Copyright (C) 2017 Wasabeef

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object RSBlurUtil {

    private val BITMAP_SCALE = 0.5f

    @Throws(RSRuntimeException::class)
    fun blur(context: Context, bitmap: Bitmap, radius: Float): Bitmap {

        var rs: RenderScript? = null
        var input: Allocation? = null
        var output: Allocation? = null
        var blur: ScriptIntrinsicBlur? = null

        // 计算图片缩小后的长宽
        val width = Math.round(bitmap.width * BITMAP_SCALE)
        val height = Math.round(bitmap.height * BITMAP_SCALE)

        // 将缩小后的图片做为预渲染的图片
        val inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)

        try {
            rs = RenderScript.create(context)
            rs.messageHandler = RenderScript.RSMessageHandler()

            input = Allocation.createFromBitmap(rs, inputBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
            output = Allocation.createTyped(rs, input.type)
            blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

            blur.setRadius(radius)
            blur.setInput(input)
            blur.forEach(output)
            output.copyTo(inputBitmap)

        } finally {
            if (input != null) input.destroy()
            if (output != null) output.destroy()
            if (blur != null) blur.destroy()
            if (rs != null) rs.destroy()
        }

        return inputBitmap
    }
}
