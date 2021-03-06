package com.fiuba.bookbnb.ui.fragments.form.services

object ServicesUtils {

    fun getStandardServices(): List<Services> {
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

    fun getSecurityServices(): List<Services> {
        return listOf(
            Services.KIT,
            Services.SMOKE_DETECTOR,
            Services.FIRE_EXTINGUISHER,
            Services.SAFETY_INSTRUCTIONS_SHEET,
            Services.LOOK_BEDROOM_DOOR
        )
    }

    fun getInstallationsServices(): List<Services> {
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