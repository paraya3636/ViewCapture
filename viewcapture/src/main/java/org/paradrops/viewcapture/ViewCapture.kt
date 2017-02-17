package org.paradrops.viewcapture

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import java.io.File
import java.io.FileOutputStream

class ViewCapture(val targetView: View, showedView: Boolean) {
    val bitmap: Bitmap

    init {
        bitmap = if (showedView) {
            initShowedViewBitmap()
        } else {
            initViewBitmap()
        }
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

    private fun initShowedViewBitmap() : Bitmap {
        targetView.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(targetView.drawingCache)
        targetView.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun initViewBitmap() : Bitmap{
        targetView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val bitmap = let {
            val width = targetView.measuredWidth
            val height = targetView.measuredHeight
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        targetView.layout(0, 0, targetView.measuredWidth, targetView.measuredHeight)
        targetView.draw(Canvas(bitmap))
        return bitmap
    }
}
