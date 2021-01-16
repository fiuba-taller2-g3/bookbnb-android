package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStayPriceStepFormFragment : FormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_price_title
    override fun getSubtitleTextRes(): Int = R.string.publish_price_subtitle
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.PRICE)
        )
    }

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayPriceStepFormFragmentDirections.actionPublishStayPriceStepFormFragmentToPublishStayFinishStepFormFragment())
    }

}