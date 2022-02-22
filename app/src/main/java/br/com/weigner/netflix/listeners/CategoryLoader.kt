package br.com.weigner.netflix.listeners

import br.com.weigner.netflix.model.CategoryModel

interface CategoryLoader {
    fun onResult(categories: List<CategoryModel>)
}