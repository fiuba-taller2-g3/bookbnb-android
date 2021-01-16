package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

data class Installations(val privateLounge: Boolean,
                         val swimmingPool: Boolean,
                         val kitchen: Boolean,
                         val washingMachine: Boolean,
                         val parking: Boolean,
                         val lift: Boolean,
                         val jacuzzi: Boolean,
                         val gym: Boolean,
                         val courtyard: Boolean,
                         val terrace: Boolean) : Serializable
