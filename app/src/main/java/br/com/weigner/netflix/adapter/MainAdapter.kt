package br.com.weigner.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R
import br.com.weigner.netflix.model.Category
import br.com.weigner.netflix.model.Movie
import br.com.weigner.netflix.viewHolder.CategoryViewHolder

class MainAdapter(categories: MutableList<Category>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories: List<Category> = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var category = categories.get(position)
        holder.textTitle.text = category.getName()
        holder.recyclerViewMovie.adapter =
            MovieAdapter(category.getMovies() as MutableList<Movie>)
        holder.recyclerViewMovie.layoutManager =
            LinearLayoutManager(null, RecyclerView.HORIZONTAL, false)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}