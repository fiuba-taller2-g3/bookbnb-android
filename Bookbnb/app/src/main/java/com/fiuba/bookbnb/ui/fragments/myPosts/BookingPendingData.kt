package com.fiuba.bookbnb.ui.fragments.myPosts

import com.fiuba.bookbnb.domain.booking.BookingResponse
import com.fiuba.bookbnb.domain.publish.PublishData

data class BookingPendingData(val publishData: PublishData, val bookingResponse: BookingResponse)
