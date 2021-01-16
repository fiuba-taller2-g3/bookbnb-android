package com.fiuba.bookbnb.ui.fragments.form.services

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.data.ServicesFormItemData

class ServicesItemBuilder(private val context: Context) {

    fun build(service: Services, isChecked: Boolean = false) : ServicesFormItemData {
        return when(service) {
            Services.BASIC_ELEMENTS -> ServicesFormItemData(service, context.getString(R.string.publish_element_basics_service_label), context.getString(R.string.publish_element_basics_service_description), isChecked)
            Services.WIFI -> ServicesFormItemData(service, context.getString(R.string.publish_wifi_service_label), isChecked = isChecked)
            Services.SHAMPOO -> ServicesFormItemData(service, context.getString(R.string.publish_shampoo_service_label), isChecked = isChecked)
            Services.TV -> ServicesFormItemData(service, context.getString(R.string.publish_tv_service_label), isChecked = isChecked)
            Services.CABLE_TV -> ServicesFormItemData(service, context.getString(R.string.publish_cable_tv_service_label), isChecked = isChecked)
            Services.HEATING -> ServicesFormItemData(service, context.getString(R.string.publish_heating_service_label), isChecked = isChecked)
            Services.AIR_CONDITIONING -> ServicesFormItemData(service, context.getString(R.string.publish_air_conditioning_service_label), isChecked = isChecked)
            Services.FANS -> ServicesFormItemData(service, context.getString(R.string.publish_fans_service_label), isChecked = isChecked)
            Services.DESK -> ServicesFormItemData(service, context.getString(R.string.publish_desk_service_label), isChecked = isChecked)
            Services.BREAKFAST -> ServicesFormItemData(service, context.getString(R.string.publish_breakfast_service_label), isChecked = isChecked)
            Services.FIREPLACE -> ServicesFormItemData(service, context.getString(R.string.publish_fireplace_service_label), isChecked = isChecked)
            Services.GRIDDLE -> ServicesFormItemData(service, context.getString(R.string.publish_griddle_service_label), isChecked = isChecked)
            Services.HAIR_DRYER -> ServicesFormItemData(service, context.getString(R.string.publish_hair_dryer_service_label), isChecked = isChecked)
            Services.PETS -> ServicesFormItemData(service, context.getString(R.string.publish_pets_service_label), isChecked = isChecked)
            Services.PRIVATE_ENTRANCE -> ServicesFormItemData(service, context.getString(R.string.publish_private_entrance_service_label), isChecked = isChecked)
            Services.FRIDGE -> ServicesFormItemData(service, context.getString(R.string.publish_fridge_service_label), isChecked = isChecked)
            Services.OVEN -> ServicesFormItemData(service, context.getString(R.string.publish_oven_service_label), isChecked = isChecked)
            Services.MICROWAVE -> ServicesFormItemData(service, context.getString(R.string.publish_microwave_service_label), isChecked = isChecked)
            Services.COFFEE_MAKER -> ServicesFormItemData(service, context.getString(R.string.publish_coffee_maker_service_label), isChecked = isChecked)
            Services.KIT -> ServicesFormItemData(service, context.getString(R.string.publish_kit_service_label), isChecked = isChecked)
            Services.SMOKE_DETECTOR -> ServicesFormItemData(service, context.getString(R.string.publish_smoke_detector_service_label), isChecked = isChecked)
            Services.FIRE_EXTINGUISHER -> ServicesFormItemData(service, context.getString(R.string.publish_fire_extinguisher_service_label), isChecked = isChecked)
            Services.SAFETY_INSTRUCTIONS_SHEET -> ServicesFormItemData(service, context.getString(R.string.publish_safety_instructions_sheet_service_label), isChecked = isChecked)
            Services.LOOK_BEDROOM_DOOR -> ServicesFormItemData(service, context.getString(R.string.publish_look_bedroom_door_service_label), isChecked = isChecked)
            Services.PRIVATE_LOUNGE -> ServicesFormItemData(service, context.getString(R.string.publish_private_lounge_service_label), isChecked = isChecked)
            Services.SWIMMING_POOL -> ServicesFormItemData(service, context.getString(R.string.publish_swimming_pool_service_label), isChecked = isChecked)
            Services.KITCHEN -> ServicesFormItemData(service, context.getString(R.string.publish_kitchen_service_label), isChecked = isChecked)
            Services.WASHING_MACHINE -> ServicesFormItemData(service, context.getString(R.string.publish_washing_machine_service_label), isChecked = isChecked)
            Services.PARKING -> ServicesFormItemData(service, context.getString(R.string.publish_parking_service_label), isChecked = isChecked)
            Services.LIFT -> ServicesFormItemData(service, context.getString(R.string.publish_lift_service_label), isChecked = isChecked)
            Services.JACUZZI -> ServicesFormItemData(service, context.getString(R.string.publish_jacuzzi_service_label), isChecked = isChecked)
            Services.GYM -> ServicesFormItemData(service, context.getString(R.string.publish_gym_service_label), isChecked = isChecked)
            Services.COURTYARD -> ServicesFormItemData(service, context.getString(R.string.publish_courtyard_service_label), isChecked = isChecked)
            Services.TERRACE -> ServicesFormItemData(service, context.getString(R.string.publish_terrace_service_label), isChecked = isChecked)
        }
    }
}