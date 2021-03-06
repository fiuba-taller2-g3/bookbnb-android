package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import com.fiuba.bookbnb.ui.fragments.form.services.ServicesUtils
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStaySecurityStepFormFragment : PublishStayServicesAbstractStepFormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_stay_security_title

    override fun getSubtitleTextRes(): Int = R.string.string_empty

    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStaySecurityStepFormFragmentDirections.actionPublishStaySecurityStepFormFragmentToPublishStayInstallationsStepFormFragment())
    }

    override fun getServices(): List<Services> = ServicesUtils.getSecurityServices()

}