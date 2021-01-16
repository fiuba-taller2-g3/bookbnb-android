package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

data class PublishRequest(val userId: String,
                          val title: String,
                          val description: String,
                          val availabilityDates: AvailabilityDates,
                          val pricePerNight: String,
                          val images: ArrayList<String>,
                          val availabilityType: String,
                          val type: String,
                          val guests: String,
                          val beds: String,
                          val bedrooms: String,
                          val bathrooms: String,
                          val bedsDistribution: ArrayList<BedDistribution>,
                          val location: Location,
                          val services: Services,
                          val security: Security,
                          val installations: Installations,
                          val date: String,
                          val walletId: String) : Serializable