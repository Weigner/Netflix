package br.com.weigner.netflix.model

class Movie {

    private var coverUrl = 0

    fun getCoverUrl(): Int {
        return coverUrl
    }

    fun setCoverUrl(url: Int){
        coverUrl = url
    }
}