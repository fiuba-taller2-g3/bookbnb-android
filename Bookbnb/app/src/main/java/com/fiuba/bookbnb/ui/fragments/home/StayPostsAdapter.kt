package com.fiuba.bookbnb.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.*
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.image

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

            Picasso.get()
                .load(itemData.images.firstOrNull())
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)

            setOnClickListener {
                NavigationManager.moveForward(SearchResultsFragmentDirections.actionSearchResultsFragmentToLoadingPublishViewFragment(itemData))
                // TODO: En cuanto estén las fechas de inicio y final de la publicación, agregarlas a los parámetros correspondientes
                //val currentDate = DateUtils.getDateOutputFormat().format(Date().time)
//                val bookingRequest = BookingRequest(UserManager.getUserInfo().getUserId(), itemData.id, currentDate, currentDate)
//                NetworkModule.buildRetrofitClient().purchase(bookingRequest, UserManager.getUserInfo().getToken()).enqueue(object : Callback<MsgResponse> {
//                    override fun onResponse(call: Call<MsgResponse>, response: Response<MsgResponse>) {
//                        AlertDialog.Builder(context).run {
//                            setMessage("Compra satisfactoria")
//                        }.show()
//                    }
//
//                    override fun onFailure(call: Call<MsgResponse>, t: Throwable) {
//                        AlertDialog.Builder(context).run {
//                            setMessage("Error en la compra")
//                        }.show()
//                    }
//                })
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

}