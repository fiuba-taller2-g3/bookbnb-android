package com.fiuba.bookbnb.ui.fragments.myBookings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fiuba.bookbnb.domain.booking.BookingResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import retrofit2.Call
import retrofit2.Response

class LoadingMyBookingsViewModel : NetworkViewModel<List<BookingResponse>>()  {

    var bookingsResults : List<BookingResponse>? = null

    private val mutableBookingsLoadedSuccessfully = MutableLiveData<Boolean>()
    val bookingsLoadedSuccessfully: LiveData<Boolean>
        get() = mutableBookingsLoadedSuccessfully

    init {
        mutableBookingsLoadedSuccessfully.value = false
    }

    override fun execute(call: Call<List<BookingResponse>>) {
        Log.i(TAG, "Getting bookings...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<BookingResponse>>) {
        Log.i(TAG, "Bookings loaded successfully")
        bookingsResults = response.body()
        mutableBookingsLoadedSuccessfully.value = true
        mutableBookingsLoadedSuccessfully.value = false
    }

    override fun onFailure(response: Response<List<BookingResponse>>) {
        // Do nothing
    }

    companion object {
        const val TAG = "MY_BOOKINGS"
    }

}