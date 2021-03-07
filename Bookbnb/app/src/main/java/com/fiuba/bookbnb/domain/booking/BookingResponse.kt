package com.fiuba.bookbnb.domain.booking

import java.io.Serializable

data class BookingResponse (
    val userId: String,
    val walletId: String,
    val guestUserId: String,
    val guestWalletId: String ,
    val postId: String,
    val beginDate: String,
    val endDate: String,
    val status: String
) : Serializable
