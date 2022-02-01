package br.com.weigner.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R
import br.com.weigner.netflix.model.CategoryModel
import br.com.weigner.netflix.model.MovieModel
import br.com.weigner.netflix.viewHolder.CategoryViewHolder

class MainAdapter(categories: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories: List<CategoryModel> = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var category = categories.get(position)
        holder.textTitle.text = category.name
        holder.recyclerViewMovie.adapter =
            MovieAdapter(category.movies as MutableList<MovieModel>)
        holder.recyclerViewMovie.layoutManager =
            LinearLayoutManager(null, RecyclerView.HORIZONTAL, false)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}