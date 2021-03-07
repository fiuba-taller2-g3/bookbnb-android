package com.fiuba.bookbnb.ui.fragments.myPosts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.*

class MyPostsAdapter(private val dataSet: List<PublishData>) : RecyclerView.Adapter<MyPostsAdapter.MyPostsViewHolder>() {

    inner class MyPostsViewHolder(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostsViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_publish_item, parent, false) as ConstraintLayout)
            .also { return MyPostsViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MyPostsViewHolder, position: Int) {
        val itemData = dataSet[position]

        with(holder.itemView) {
            stay_title.text = itemData.title
            stay_description.text = itemData.description

            Picasso.get()
                .load(itemData.images.firstOrNull())
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}