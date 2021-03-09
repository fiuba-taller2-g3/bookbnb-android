package com.fiuba.bookbnb.ui.fragments.myPosts

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import retrofit2.Call
import retrofit2.Response

class BookingResponseProcessViewModel : NetworkViewModel<MsgResponse>() {

    override fun execute(call: Call<MsgResponse>) {
        Log.i(TAG, "Sending booking response...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<MsgResponse>) {
        Log.i(TAG, "Booking response sent successfully")
        response.body()?.let {
            NavigationManager.moveForwardWithPopUpTo(
                BookingResponseProcessFragmentDirections.actionBookingRequestProcessFragmentToMyPostsLoadingFragment(
                    false
                ), R.id.profileMenuFragment
            )
        }
    }

    override fun onFailure(response: Response<MsgResponse>) {
        // Do nothing
    }

    companion object {
        const val TAG = "BOOKING_RESPONSE"
    }

}