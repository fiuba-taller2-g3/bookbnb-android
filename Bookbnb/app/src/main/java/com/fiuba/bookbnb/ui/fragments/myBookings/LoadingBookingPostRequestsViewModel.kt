package com.fiuba.bookbnb.ui.fragments.myBookings

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class LoadingBookingPostRequestsViewModel : NetworkViewModel<List<PublishData>>()  {

    var bookingRequestsResults : List<PublishData>? = null

    override fun execute(call: Call<List<PublishData>>) {
        Log.i(TAG, "Getting bookings requests...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<PublishData>>) {
        Log.i(TAG, "Bookings requests loaded successfully")
        bookingRequestsResults = response.body()
        response.body()?.let {
            NavigationManager.moveForwardWithPopUpTo(MyBookingsLoadingFragmentDirections.actionMyBookingsLoadingFragmentToMyBookingsFragment(), R.id.profileMenuFragment)
        }
    }

    override fun onFailure(response: Response<List<PublishData>>) {
        // Do nothing
    }

    companion object {
        const val TAG = "BOOKING"
    }

}