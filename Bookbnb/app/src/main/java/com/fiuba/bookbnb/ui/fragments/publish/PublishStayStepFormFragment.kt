package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.ui.fragments.form.FormFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager

class PublishStayStepFormFragment : FormFragment() {

    override fun setHeaderContent(titleRes: Int, subtitleRes: Int, vararg titleArgs: String) {
        super.setHeaderContent(titleRes, R.string.string_empty, titleArgs = arrayOf(UserManager.getUserInfo().getUserData().name))
    }

    override fun getTitleTextRes(): Int = R.string.publish_welcome_title
    override fun getSubtitleTextRes(): Int = R.string.string_empty
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.AVAILABILITY_TYPE),
            FormInputData(FormInputType.STAY_TYPE)
        )
    }

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayStepFormFragmentDirections.actionPublishStayStepFormFragmentToPublishStayQuantitiesStepFormFragment())
    }

}