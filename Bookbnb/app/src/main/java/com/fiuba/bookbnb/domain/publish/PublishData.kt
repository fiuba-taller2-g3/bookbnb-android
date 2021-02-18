package com.fiuba.bookbnb.domain.publish

import com.fiuba.bookbnb.ui.fragments.form.services.Services
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class PublishData(val id: String?,
                       val isBlocked: Boolean,
                       val userId: String,
                       val title: String,
                       val description: String,
                       val availabilityDates: AvailabilityDates,
                       val price: String,
                       val images: ArrayList<String>,
                       val availabilityType: String,
                       val type: String,
                       val guests: String,
                       val beds: String,
                       val bedrooms: String,
                       val bathrooms: String,
                       val bedsDistribution: ArrayList<BedDistribution>,
                       val location: Location,
                       val services: EnumMap<Services, Boolean>,
                       val date: String,
                       val walletId: String) : Serializable