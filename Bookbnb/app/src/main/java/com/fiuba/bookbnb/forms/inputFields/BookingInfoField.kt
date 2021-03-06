package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.view.LayoutInflater
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_booking_info_field.view.*

class BookingInfoField(context: Context) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_booking_info_field, this)
        setLayoutParams()
        setNightCounts(0)
        setTotalPrice(0)
    }

    fun setNightCounts(nights: Int) {
        night_count_text.text = context.getString(R.string.purchase_night_counts, nights)
    }

    fun setTotalPrice(price: Int) {
        total_price_text.text = context.getString(R.string.purchase_total_price, price)
    }

    override fun enableInput() {
        // Do nothing
    }

    override fun disableInput() {
        // Do nothing
    }

    override fun isValidated(): Boolean = true
}