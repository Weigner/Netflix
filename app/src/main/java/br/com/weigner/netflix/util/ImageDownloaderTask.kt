package br.com.weigner.netflix.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import android.os.AsyncTask
import android.widget.ImageView
import androidx.core.content.ContextCompat
import br.com.weigner.netflix.R
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ImageDownloaderTask(imageView: ImageView) :
    AsyncTask<String?, Void?, Bitmap>() {

    private var imageView = WeakReference(imageView)
    private var shadowEnabled = false

    override fun doInBackground(vararg url: String?): Bitmap? {

        try {
            val requestUrl = URL(url[0])

            val urlConnection = requestUrl.openConnection() as HttpURLConnection

            if (urlConnection.responseCode != 200) {
                return null
            }

            if (urlConnection.inputStream != null) {
                return BitmapFactory.decodeStream(urlConnection.inputStream)
            }

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }


        return null
    }

    override fun onPostExecute(result: Bitmap?) {
        val imageView = imageView.get()
        if (imageView != null && result != null) {
            imageView.setImageBitmap(result)

            if (shadowEnabled) {
                val drawable = ContextCompat.getDrawable(
                    imageView.context,
                    R.drawable.shadows
                ) as (LayerDrawable)

                drawable.let {
                    val bitmapDrawable = BitmapDrawable(result)
                    drawable.setDrawableByLayerId(R.id.cover_drawable, bitmapDrawable)
                    imageView.setImageDrawable(drawable)
                }
            } else {
                if (result.width < imageView.width || result.height < imageView.height) {
                    //TODO()
                    /*val matrix = Matrix()
                    matrix.postScale(
                        (imageView.width / result.width).toFloat(),
                        (imageView.height / result.height).toFloat()
                    )
                    result = Bitmap.createBitmap(result, 0, 0, result.width, result.height, matrix, false)*/
                }
            }
        }
    }

    fun setShadowEnabled(shadowEnabled: Boolean) {
        this.shadowEnabled = shadowEnabled
    }
}