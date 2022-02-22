package br.com.weigner.netflix.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import br.com.weigner.netflix.model.CategoryModel
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ImageDownloaderTask(imageView: ImageView):
    AsyncTask<String?, Void?, Bitmap>()  {

    private var imageView = WeakReference(imageView)

    override fun doInBackground(vararg url: String?): Bitmap? {

        try {
            val requestUrl = URL(url[0])

            val urlConnection = requestUrl.openConnection() as HttpURLConnection

            if (urlConnection.responseCode != 200){
                return null
            }

            if (urlConnection.inputStream != null){
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
        if (imageView != null && result != null){
            imageView.setImageBitmap(result)
        }
    }
}