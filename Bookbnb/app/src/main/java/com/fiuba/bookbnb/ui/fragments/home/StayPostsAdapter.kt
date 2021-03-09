package com.fiuba.bookbnb.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.*

class StayPostsAdapter(private val dataSet: List<PublishData>) : RecyclerView.Adapter<StayPostsAdapter.StayPostsViewHolder>() {

    inner class StayPostsViewHolder(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StayPostsViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_stay_publish_search_results_item, parent, false) as ConstraintLayout)
            .also { return StayPostsViewHolder(it) }

    }

    override fun onBindViewHolder(holder: StayPostsViewHolder, position: Int) {
        with(holder.itemView) {
            val itemData = dataSet[position]
            stay_title.text = itemData.title
            stay_description.text = itemData.description
            price.text = context.getString(R.string.stay_post_price_text, itemData.price)
            recommendation_label.isVisible = itemData.recommended ?: false

            Picasso.get()
                .load(itemData.images.firstOrNull())
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)

            setOnClickListener {
                NavigationManager.moveForward(SearchResultsFragmentDirections.actionSearchResultsFragmentToLoadingPublishViewFragment(itemData))
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

}