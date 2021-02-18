package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import com.fiuba.bookbnb.ui.fragments.form.services.ServicesUtils
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStayServicesStepFormFragment : PublishStayServicesAbstractStepFormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_stay_services_title

    override fun getSubtitleTextRes(): Int = R.string.publish_stay_services_subtitle

    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayServicesStepFormFragmentDirections.actionPublishStayServicesStepFormFragmentToPublishStaySecurityStepFormFragment())
    }

    override fun getServices(): List<Services> = ServicesUtils.getStandardServices()

}