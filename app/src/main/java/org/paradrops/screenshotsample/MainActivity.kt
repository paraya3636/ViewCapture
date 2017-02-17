package org.paradrops.screenshotsample

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.paradrops.screenshot.Screenshot
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showedViewCaptureButton.setOnClickListener {
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ShowedViewCapture" + ".jpg")
            val result = Screenshot(activityMain.rootView, true).output(file, null, Bitmap.CompressFormat.JPEG, 80)
            if (result) {
                Toast.makeText(this, "Success! : " + file.path, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error! : " + file.path, Toast.LENGTH_LONG).show()
            }
        }

        anyViewCaptureButton.setOnClickListener {
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "AnyViewCapture" + ".jpg")
            val result = Screenshot(activityMain.rootView, false).output(file, null, Bitmap.CompressFormat.JPEG, 80)
            if (result) {
                Toast.makeText(this, "Success! : " + file.path, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error! : " + file.path, Toast.LENGTH_LONG).show()
            }
        }
    }
}
