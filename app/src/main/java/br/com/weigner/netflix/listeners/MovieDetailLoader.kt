package br.com.weigner.netflix.listeners

import br.com.weigner.netflix.model.MovieDetail

interface MovieDetailLoader {
    fun onResult(movieDetail: MovieDetail)
}