package com.fiuba.bookbnb.ui.fragments.myPosts

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.booking.BookingResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class LoadingHostBookingsViewModel : NetworkViewModel<List<BookingResponse>>()  {

    var bookingResults : List<BookingResponse>? = null

    override fun execute(call: Call<List<BookingResponse>>) {
        Log.i(TAG, "Getting bookings...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<List<BookingResponse>>) {
        Log.i(TAG, "Bookings loaded successfully")
        bookingResults = response.body()
        response.body()?.let {
            NavigationManager.moveForwardWithPopUpTo(MyPostsLoadingFragmentDirections.actionMyPostsLoadingFragmentToMyPostsFragment(), R.id.profileMenuFragment)
        }
    }

    override fun onFailure(response: Response<List<BookingResponse>>) {
        // Do nothing
    }

    companion object {
        const val TAG = "MY_BOOKINGS"
    }

}