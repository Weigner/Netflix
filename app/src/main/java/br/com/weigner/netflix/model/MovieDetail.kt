package br.com.weigner.netflix.model


class MovieDetail(movie: MovieModel, moviesSimilar: MutableList<MovieModel>) {

    val movie: MovieModel = movie
    val moviesSimilar: MutableList<MovieModel> = moviesSimilar

}