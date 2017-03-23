package org.paradrops.viewcapture

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import java.io.File
import java.io.FileOutputStream

class ViewCapture {
    lateinit var bitmap: Bitmap

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

    fun captureShowedViewBitmap(targetView: View) : ViewCapture {
        targetView.isDrawingCacheEnabled = true
        targetView.buildDrawingCache()
        bitmap = Bitmap.createBitmap(targetView.drawingCache)
        targetView.destroyDrawingCache()
        targetView.isDrawingCacheEnabled = false
        return this
    }

    fun captureShowedScrollViewBitmap(targetView: ScrollView) : ViewCapture {
        bitmap = let {
            val width = targetView.getChildAt(0).width
            val height = targetView.getChildAt(0).height
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        targetView.draw(Canvas(bitmap))
        return this
    }

    fun captureGoneViewBitmap(targetView: View) : ViewCapture {
        targetView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        bitmap = let {
            val width = targetView.measuredWidth
            val height = targetView.measuredHeight
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        targetView.layout(0, 0, targetView.measuredWidth, targetView.measuredHeight)
        targetView.draw(Canvas(bitmap))
        return this
    }
}
