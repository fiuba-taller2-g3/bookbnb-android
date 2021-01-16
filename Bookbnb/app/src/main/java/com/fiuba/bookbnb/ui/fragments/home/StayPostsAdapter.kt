package com.fiuba.bookbnb.ui.fragments.home

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.booking.BookingRequest
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.publish.PublishResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.user.UserManager
import com.fiuba.bookbnb.utils.DateUtils
import kotlinx.android.synthetic.main.bookbnb_stay_post_card_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class StayPostsAdapter(private val dataSet: List<PublishResponse>) : RecyclerView.Adapter<StayPostsAdapter.StayPostsViewHolder>() {

    inner class StayPostsViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StayPostsViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_stay_post_card_view, parent, false) as CardView)
            .also { return StayPostsViewHolder(it) }
    }

    override fun onBindViewHolder(holder: StayPostsViewHolder, position: Int) {
        with(holder.itemView) {
            val itemData = dataSet[position]
            stay_title.text = itemData.title
            stay_description.text = itemData.description
            price.text = context.getString(R.string.stay_post_price_text, itemData.price)

            purchase_button.setOnClickListener {
                // TODO: En cuanto estén las fechas de inicio y final de la publicación, agregarlas a los parámetros correspondientes
                val currentDate = DateUtils.getDateOutputFormat().format(Date().time)
                val bookingRequest = BookingRequest(UserManager.getUserInfo().getUserId(), itemData.id, currentDate, currentDate)
                NetworkModule.buildRetrofitClient().purchase(bookingRequest, UserManager.getUserInfo().getToken()).enqueue(object : Callback<MsgResponse> {
                    override fun onResponse(call: Call<MsgResponse>, response: Response<MsgResponse>) {
                        AlertDialog.Builder(context).run {
                            setMessage("Compra satisfactoria")
                        }.show()
                    }

                    override fun onFailure(call: Call<MsgResponse>, t: Throwable) {
                        AlertDialog.Builder(context).run {
                            setMessage("Error en la compra")
                        }.show()
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

}