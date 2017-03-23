package org.paradrops.viewcapturesample

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.paradrops.viewcapture.ViewCapture
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showedViewCaptureButton.setOnClickListener {
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ShowedViewCapture" + ".jpg")
            val result = ViewCapture().captureShowedViewBitmap(contentView).output(file, null, Bitmap.CompressFormat.JPEG, 80)
            if (result) {
                Toast.makeText(this, "Success! : " + file.path, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error! : " + file.path, Toast.LENGTH_LONG).show()
            }
        }

        scrolledViewCaptureButton.setOnClickListener {
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ScrolledViewCapture" + ".jpg")
            val result = ViewCapture().captureShowedScrollViewBitmap(contentView).output(file, null, Bitmap.CompressFormat.JPEG, 80)
            if (result) {
                Toast.makeText(this, "Success! : " + file.path, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error! : " + file.path, Toast.LENGTH_LONG).show()
            }
        }

        anyViewCaptureButton.setOnClickListener {
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "AnyViewCapture" + ".jpg")
            val result = ViewCapture().captureGoneViewBitmap(contentView).output(file, null, Bitmap.CompressFormat.JPEG, 80)
            if (result) {
                Toast.makeText(this, "Success! : " + file.path, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error! : " + file.path, Toast.LENGTH_LONG).show()
            }
        }
    }
}
