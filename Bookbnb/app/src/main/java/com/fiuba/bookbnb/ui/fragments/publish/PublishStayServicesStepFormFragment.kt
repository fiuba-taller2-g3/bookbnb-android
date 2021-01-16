package com.fiuba.bookbnb.ui.fragments.publish

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import com.fiuba.bookbnb.ui.navigation.NavigationManager

class PublishStayServicesStepFormFragment : PublishStayServicesAbstractStepFormFragment() {

    override fun getTitleTextRes(): Int = R.string.publish_stay_services_title

    override fun getSubtitleTextRes(): Int = R.string.publish_stay_services_subtitle

    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayServicesStepFormFragmentDirections.actionPublishStayServicesStepFormFragmentToPublishStaySecurityStepFormFragment())
    }

    override fun getServices(): List<Services> {
        return listOf(
            Services.BASIC_ELEMENTS,
            Services.WIFI,
            Services.SHAMPOO,
            Services.TV,
            Services.CABLE_TV,
            Services.HEATING,
            Services.AIR_CONDITIONING,
            Services.FANS,
            Services.DESK,
            Services.BREAKFAST,
            Services.FIREPLACE,
            Services.GRIDDLE,
            Services.HAIR_DRYER,
            Services.PETS,
            Services.PRIVATE_ENTRANCE,
            Services.FRIDGE,
            Services.OVEN,
            Services.MICROWAVE,
            Services.COFFEE_MAKER
        )
    }

}