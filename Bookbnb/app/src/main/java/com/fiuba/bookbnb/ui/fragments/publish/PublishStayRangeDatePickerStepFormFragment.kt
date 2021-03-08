package com.fiuba.bookbnb.ui.fragments.publish

import android.app.AlertDialog
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.utils.DateUtils
import java.util.*

class PublishStayRangeDatePickerStepFormFragment : FormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_range_date_picker_title
    override fun getSubtitleTextRes(): Int = R.string.publish_range_date_picker_description
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun getInputList(): List<FormInputData> {
        val today = Date()
        val aYearLater = Calendar.getInstance().also { it.add(Calendar.YEAR, 1) }
        return listOf(
            FormInputData(FormInputType.START_DATE, DateUtils.getDateOutputFormat().format(today)),
            FormInputData(FormInputType.END_DATE, DateUtils.getDateOutputFormat().format(aYearLater.time))
        )
    }

    override fun setActionEventButton() {
        val initialDate = DateUtils.getDateOutputFormat().parse(formViewModel.getContentFromItem(FormInputType.START_DATE))
        val endDate = DateUtils.getDateOutputFormat().parse(formViewModel.getContentFromItem(FormInputType.END_DATE))
        if (endDate!!.after(initialDate)) {
            NavigationManager.moveForward(PublishStayRangeDatePickerStepFormFragmentDirections.actionPublishStayRangeDatePickerStepFormFragmentToPublishStayPriceStepFormFragment())
        } else AlertDialog.Builder(context).setMessage(getString(R.string.error_data_range)).show()
    }

}