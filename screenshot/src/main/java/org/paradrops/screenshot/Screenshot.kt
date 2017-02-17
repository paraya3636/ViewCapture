package org.paradrops.screenshot

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import java.io.File
import java.io.FileOutputStream

class Screenshot(val targetView: View, showedView: Boolean) {
    val bitmap: Bitmap

    init {
        if (!showedView) {
            targetView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        bitmap = let {
            val width = targetView.measuredWidth
            val height = targetView.measuredHeight
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        if (!showedView) {
            targetView.layout(0, 0, targetView.measuredWidth, targetView.measuredHeight)
        }
        targetView.draw(Canvas(bitmap))
    }

    fun output(file: File, size: Size?, format: Bitmap.CompressFormat, quality: Int) : Boolean {
        FileOutputStream(file).use {
            if (size == null) {
                return bitmap.compress(format,quality, it)
            } else {
                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, size.width, size.height, false)
                return scaledBitmap.compress(format, quality, it)
            }
        }
    }
}
