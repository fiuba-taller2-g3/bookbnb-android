package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStayInstallationsStepFormFragment : PublishStayServicesAbstractStepFormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_stay_installations_title

    override fun getSubtitleTextRes(): Int = R.string.publish_stay_installations_subtitle

    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayInstallationsStepFormFragmentDirections.actionPublishStayInstallationsStepFormFragmentToPublishStayUploadPhotoStepFormFragment())
    }

    override fun getServices(): List<Services> {
        return listOf(
            Services.PRIVATE_LOUNGE,
            Services.SWIMMING_POOL,
            Services.KITCHEN,
            Services.WASHING_MACHINE,
            Services.PARKING,
            Services.LIFT,
            Services.JACUZZI,
            Services.GYM,
            Services.COURTYARD,
            Services.TERRACE
        )
    }

}