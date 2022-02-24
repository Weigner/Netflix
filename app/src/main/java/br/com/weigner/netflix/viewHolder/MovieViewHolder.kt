package br.com.weigner.netflix.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R
import br.com.weigner.netflix.listeners.OnItemClickListener

class MovieViewHolder(itemView: View, onItemClickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    val imageViewCover: ImageView = itemView.findViewById(R.id.image_view_cover)

    init {
        itemView.setOnClickListener {
            onItemClickListener.onClick(adapterPosition)
        }
    }

}