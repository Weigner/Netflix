package br.com.weigner.netflix.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textTitle: TextView = itemView.findViewById<TextView>(R.id.text_view_title)
    val recyclerViewMovie: RecyclerView =
        itemView.findViewById<RecyclerView>(R.id.recycler_view_movie)

}