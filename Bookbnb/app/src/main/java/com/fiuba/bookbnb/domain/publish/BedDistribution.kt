package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

data class BedDistribution(val roomNumber: Int, var singleBeds: Int = 0, var doubleBeds: Int = 0, var cribs: Int = 0) : Serializable