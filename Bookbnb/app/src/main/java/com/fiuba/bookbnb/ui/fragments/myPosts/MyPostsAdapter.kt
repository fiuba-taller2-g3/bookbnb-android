package com.fiuba.bookbnb.ui.fragments.myPosts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_publish_item.view.*
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.image
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.stay_description
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.stay_title

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
            post_footer.isVisible = false
            stay_status.isVisible = false

            Picasso.get()
                .load(itemData.images.firstOrNull())
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}