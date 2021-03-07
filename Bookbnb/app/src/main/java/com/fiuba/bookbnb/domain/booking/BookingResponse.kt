package com.fiuba.bookbnb.domain.booking

import java.io.Serializable

data class BookingResponse (
    val userId: String,
    val walletId: String,
    val guest_user_id: String,
    val guest_wallet_id: String ,
    val postId: String,
    val beginDate: String,
    val endDate: String,
    val status: String
) : Serializable
