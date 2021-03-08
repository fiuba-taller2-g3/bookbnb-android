package com.fiuba.bookbnb.ui.fragments.booking

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.utils.DateUtils
import kotlinx.android.synthetic.main.bookbnb_booking_info_field.*

class BookingSelectDateFormFragment : FormFragment() {

    private val navArguments by navArgs<BookingConfirmFormFragmentArgs>()
    private val publishData by lazy { navArguments.publishData }

    override fun getTitleTextRes(): Int = R.string.booking_dates_title_text
    override fun getSubtitleTextRes(): Int = R.string.booking_dates_subtitle_text
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun getInputList(): List<FormInputData> {
        val startDate = DateUtils.getDateOutputFormat().parse(publishData.availabilityDates.startDate)
        val endDate = DateUtils.getDateOutputFormat().parse(publishData.availabilityDates.endDate)

        return listOf(
            FormInputData(FormInputType.BOOKING_START_DATE, DateUtils.getDateOutputFormat().format(startDate!!), startDate, endDate!!),
            FormInputData(FormInputType.BOOKING_END_DATE, DateUtils.getDateOutputFormat().format(endDate), startDate, endDate)
        )
    }

    override fun setActionEventButton() {
        if (isDateRangeValidated()) {
            NavigationManager.moveForward(BookingSelectDateFormFragmentDirections.actionBookingSelectDateFormFragmentToBookingConfirmFormFragment(publishData))
        }
    }

    private fun isDateRangeValidated() : Boolean {
        val initialDateContentField = formViewModel.getContentFromItem(FormInputType.BOOKING_START_DATE)
        val endDateContentField = formViewModel.getContentFromItem(FormInputType.BOOKING_END_DATE)
        val initialDate = DateUtils.getDateOutputFormat().parse(initialDateContentField)
        val endDate = DateUtils.getDateOutputFormat().parse(endDateContentField)
        return endDate!!.after(initialDate).also { isEndDateAfterOfInitialDate ->
            if (!isEndDateAfterOfInitialDate) AlertDialog.Builder(context).setMessage(getString(R.string.error_data_range)).show()
        }
    }
}