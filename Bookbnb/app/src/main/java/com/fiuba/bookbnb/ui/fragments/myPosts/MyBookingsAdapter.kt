package com.fiuba.bookbnb.ui.fragments.myPosts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.user.UserManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_publish_item.view.*
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.image
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.stay_description
import kotlinx.android.synthetic.main.bookbnb_stay_publish_search_results_item.view.stay_title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBookingsAdapter(private val dataSet: ArrayList<BookingPendingData>) : RecyclerView.Adapter<MyBookingsAdapter.MyBookingsViewHolder>() {

    inner class MyBookingsViewHolder(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookingsViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_publish_item, parent, false) as ConstraintLayout)
            .also { return MyBookingsViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MyBookingsViewHolder, position: Int) {
        val itemData = dataSet[position].publishData

        with(holder.itemView) {
            stay_title.text = itemData.title
            stay_description.text = itemData.description

            accept_booking_button.setOnClickListener {
                NetworkModule.buildRetrofitClient().acceptBooking(dataSet[position].bookingResponse, UserManager.getUserInfo().getToken())
                    .enqueue(object : Callback<MsgResponse> {

                        override fun onResponse(call: Call<MsgResponse>, response: Response<MsgResponse>) {
                            dataSet.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, dataSet.size)
                        }

                        override fun onFailure(call: Call<MsgResponse>, t: Throwable) {
                            // Do nothing
                        }
                    }
                )
            }

            reject_booking_button.setOnClickListener {

            }

            Picasso.get()
                .load(itemData.images.firstOrNull())
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}