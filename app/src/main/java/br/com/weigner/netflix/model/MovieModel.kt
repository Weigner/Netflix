package br.com.weigner.netflix.model

class MovieModel {

    private var coverUrl = ""

    fun getCoverUrl(): String {
        return coverUrl
    }

    fun setCoverUrl(url: String){
        coverUrl = url
    }
}