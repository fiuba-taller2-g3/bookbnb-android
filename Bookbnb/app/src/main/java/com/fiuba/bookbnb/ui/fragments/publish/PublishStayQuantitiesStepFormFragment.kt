package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStayQuantitiesStepFormFragment : FormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_guest_quantity_label
    override fun getSubtitleTextRes(): Int = R.string.string_empty
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.GUEST_QUANTITY),
            FormInputData(FormInputType.BEDROOM_QUANTITY),
            FormInputData(FormInputType.BED_QUANTITY),
            FormInputData(FormInputType.BATHROOM_QUANTITY)
        )
    }

    override fun shouldClearInputsWhenBackPressed() = false

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayQuantitiesStepFormFragmentDirections.actionPublishStayQuantitiesStepFormFragmentToPublishStayBedDistributionFormFragment())
    }

}