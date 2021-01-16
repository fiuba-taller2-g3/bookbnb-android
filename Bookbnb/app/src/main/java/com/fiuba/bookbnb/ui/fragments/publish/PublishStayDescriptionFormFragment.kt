package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStayDescriptionFormFragment : FormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_stay_name_title
    override fun getSubtitleTextRes(): Int = R.string.publish_stay_name_subtitle
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.STAY_TITLE),
            FormInputData(FormInputType.STAY_DESCRIPTION)
        )
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayDescriptionFormFragmentDirections.actionPublishStayDescriptionFormFragmentToPublishStayServicesStepFormFragment())
    }

}