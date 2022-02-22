package br.com.weigner.netflix.util

import android.os.AsyncTask
import android.util.Log
import br.com.weigner.netflix.listeners.CategoryLoader
import br.com.weigner.netflix.model.CategoryModel
import br.com.weigner.netflix.model.MovieModel
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class CategoryJsonDownloadTask :
    AsyncTask<String?, Void?, List<CategoryModel>?>() {

    private lateinit var categoryLoader: CategoryLoader

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg url: String?): List<CategoryModel>? {

        try {
            val requestUrl = URL(url[0])

            val urlConnection = requestUrl.openConnection() as HttpURLConnection
            urlConnection.readTimeout = 2000
            urlConnection.connectTimeout = 2000

            if (urlConnection.responseCode >= 400) {
                throw IOException("Error na comunicação do servidor")
            }

            val inputStream = urlConnection.inputStream

            val bufferedInputStream = BufferedInputStream(inputStream)

            val jsonString = toString(bufferedInputStream)
            Log.d("Teste Json", jsonString)

            val categories = getCategories(JSONObject(jsonString))
            bufferedInputStream.close()

            return categories
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(result: List<CategoryModel>?) {
        super.onPostExecute(result)
        if (result != null) {
            categoryLoader.onResult(result)
        }
    }

    private fun toString(inputStream: InputStream): String {
        val bytes = ByteArray(1024)
        val baos = ByteArrayOutputStream()
        var lidos: Int
        while (inputStream.read(bytes).also { lidos = it } > 0) {
            baos.write(bytes, 0, lidos)
        }

        return String(baos.toByteArray())
    }

    private fun getCategories(jsonObject: JSONObject): List<CategoryModel> {

        val categories = arrayListOf<CategoryModel>()

        val categoryArray = jsonObject.getJSONArray("category")

        var i = 0
        while (i < categoryArray.length()) {
            val category = categoryArray.getJSONObject(i)
            val title = category.getString("title")

            val movies = arrayListOf<MovieModel>()
            val movieArray = category.getJSONArray("movie")

            var j = 0
            while (j < movieArray.length()) {
                val movieObj = MovieModel()
                var id = movieArray.getJSONObject(j).getInt("id")

                movieObj.coverUrl = movieArray.getJSONObject(j).getString("cover_url")
                movieObj.id = id

                movies.add(movieObj)
                j++
            }

            val categoryObj = CategoryModel()
            categoryObj.name = title
            categoryObj.movies = movies

            categories.add(categoryObj)
            i++
        }

        return categories
    }

    fun setCategoryLoader(categoryLoader: CategoryLoader){
        this.categoryLoader = categoryLoader
    }
}