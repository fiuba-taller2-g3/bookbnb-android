package com.fiuba.bookbnb.ui.fragments.dialogs

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_date_picker_dialog_fragment.view.*
import java.util.*

class DatePickerItem(context: Context, selectedDate: Calendar, minDate: Date? = null, maxDate: Date? = null,
                     onDateChangedListener : DatePicker.OnDateChangedListener) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_date_picker_dialog_fragment, this)
        setLayoutParams()
        date_picker.init(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH), onDateChangedListener)
        minDate?.let { date_picker.minDate = it.time }
        maxDate?.let { date_picker.maxDate = it.time }
        date_picker.descendantFocusability = DatePicker.FOCUS_BLOCK_DESCENDANTS
    }

    fun getYear() = date_picker.year
    fun getMonth() = date_picker.month
    fun getDay() = date_picker.dayOfMonth

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).also {
            orientation = VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }

}