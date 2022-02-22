package br.com.weigner.netflix.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import br.com.weigner.netflix.listeners.MovieDetailLoader
import br.com.weigner.netflix.model.MovieDetail
import br.com.weigner.netflix.model.MovieModel
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MovieDetailTask(val context: Context) :
    AsyncTask<String?, Void?, MovieDetail>() {

    private lateinit var movieDetailLoader: MovieDetailLoader

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg url: String?): MovieDetail? {
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

            val movieDetail: MovieDetail = getMovieDetail(JSONObject(jsonString))
            bufferedInputStream.close()

            return movieDetail
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(result: MovieDetail?) {
        super.onPostExecute(result)

        if (result != null) {
            movieDetailLoader.onResult(result)
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

    private fun getMovieDetail(jsonObject: JSONObject): MovieDetail {
        val id = jsonObject.getInt("id")
        val title = jsonObject.getString("title")
        val description = jsonObject.getString("desc")
        val cast = jsonObject.getString("cast")
        val coverUrl = jsonObject.getString("cover_url")

        val movies = arrayListOf<MovieModel>()
        val movieArray = jsonObject.getJSONArray("movie")
        var i = 0
        while (i < movieArray.length()) {
            val similarMovie = MovieModel()
            similarMovie.id = movieArray.getJSONObject(i).getInt("id")
            similarMovie.coverUrl = movieArray.getJSONObject(i).getString("cover_url")

            movies.add(similarMovie)
            i++
        }
        val movie = MovieModel()
        movie.id = id
        movie.coverUrl = coverUrl
        movie.title = title
        movie.description = description
        movie.cast = cast

        return MovieDetail(movie, movies)
    }

    fun setMovieDetailLoader(movieDetailLoader: MovieDetailLoader){
        this.movieDetailLoader = movieDetailLoader
    }

}