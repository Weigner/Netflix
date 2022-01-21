package br.com.weigner.netflix.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageViewCover: ImageView = itemView.findViewById(R.id.image_view_cover)

}