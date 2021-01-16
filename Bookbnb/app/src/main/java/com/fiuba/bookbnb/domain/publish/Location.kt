package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

data class Location(val country: String?, val city: String?, val address: String?, val zipCode: String?, val apartment: String?) : Serializable
