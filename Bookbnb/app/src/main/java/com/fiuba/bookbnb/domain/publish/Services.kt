package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

data class Services(val basicElements: Boolean,
                    val wifi: Boolean,
                    val shampoo: Boolean,
                    val tv: Boolean,
                    val cableTv: Boolean,
                    val heating: Boolean,
                    val airConditioning: Boolean,
                    val fans: Boolean,
                    val desk: Boolean,
                    val breakfast: Boolean,
                    val fireplace: Boolean,
                    val griddle: Boolean,
                    val hairDryer: Boolean,
                    val pets: Boolean,
                    val privateEntrance: Boolean,
                    val fridge: Boolean,
                    val oven: Boolean,
                    val microWave: Boolean,
                    val coffeeMaker: Boolean) : Serializable
