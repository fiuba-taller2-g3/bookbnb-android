package com.fiuba.bookbnb.domain.booking

import java.io.Serializable

data class BookingRequest(val userId: String, val postId: String, val beginDate: String, val endDate: String) : Serializable