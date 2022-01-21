package br.com.weigner.netflix.model

class Category {

    private var name = ""
    private lateinit var movies: List<Movie>

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getMovies(): List<Movie> {
        return movies
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
    }

}