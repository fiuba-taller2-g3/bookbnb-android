package com.fiuba.bookbnb.ui.fragments.dialogs

import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.navigation.fragment.navArgs
import java.io.Serializable
import java.util.*

class DatePickerDialogFragment : AbstractDialogFragment(), DatePicker.OnDateChangedListener {

    private val navArguments by navArgs<DatePickerDialogFragmentArgs>()
    private val titleDialogRes by lazy { navArguments.titleDialogRes }
    private val selectedDate by lazy { navArguments.selectedDate }
    private val onDateSetListener by lazy { navArguments.onDateSetListener }
    private val minDate by lazy { navArguments.minDate }
    private val maxDate by lazy { navArguments.maxDate }

    private val datePickerItem by lazy { DatePickerItem(requireContext(), selectedDate, minDate, maxDate, this) }

    override fun getDialogTitleRes(): Int = titleDialogRes

    override fun getItemList(): List<LinearLayout> {
        return listOf(datePickerItem)
    }

    override fun setAcceptFunction() {
        val date = with(datePickerItem) { getDate(getYear(), getMonth(), getDay()) }
        onDateSetListener.onDateSet(date, targetRequestCode)
        dismiss()
    }

    private fun getDate(year: Int, monthOfYear: Int, dayOfMonth: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        return calendar
    }

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //Do Nothing
    }

    // The callback used to indicate the user is done filling in the date.
    interface OnDateSetListener : Serializable {
        fun onDateSet(date: Calendar, requestCode: Int)
    }
}