package com.fiuba.bookbnb.ui.fragments.myPosts

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.user.UserManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_publish_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBookingsAdapter(private val dataSet: ArrayList<BookingPendingData>, private val showFooter: Boolean = true, private val showStatus: Boolean = false) : RecyclerView.Adapter<MyBookingsAdapter.MyBookingsViewHolder>() {

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
            post_footer.isVisible = showFooter

            if (showStatus) {
                stay_status.isVisible = showStatus
                stay_status.setBackgroundResource(R.drawable.driver)

                when(dataSet[position].bookingResponse.status) {
                    PENDING -> {
                        stay_status.text = context.getString(R.string.my_bookings_pending)
                        stay_status.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPending))
                    }
                    ACCEPTED -> {
                        stay_status.text = context.getString(R.string.my_bookings_accepted)
                        stay_status.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccept))
                    }
                    REJECTED -> {
                        stay_status.text = context.getString(R.string.my_bookings_rejected)
                        stay_status.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorRed))
                    }
                }
            }

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
                NetworkModule.buildRetrofitClient().rejectBooking(dataSet[position].bookingResponse, UserManager.getUserInfo().getToken())
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

            Picasso.get()
                .load(itemData.images.firstOrNull())
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    companion object {
        private const val PENDING = "pending"
        private const val ACCEPTED = "accepted"
        private const val REJECTED = "rejected"
    }

}