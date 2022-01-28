package br.com.weigner.netflix.model

class CategoryModel {

    private var name = ""
    private lateinit var movies: List<MovieModel>

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getMovies(): List<MovieModel> {
        return movies
    }

    fun setMovies(movies: List<MovieModel>) {
        this.movies = movies
    }

}