package com.fiuba.bookbnb.ui.fragments.booking

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class BookingViewModel : NetworkViewModel<MsgResponse>() {

    override fun execute(call: Call<MsgResponse>) {
        Log.i(TAG, "Purchasing...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<MsgResponse>) {
        Log.i(TAG, "Purchase successfully")
        NavigationManager.moveForwardWithPopUpTo(
            BookingFormFragmentDirections.actionBookingFormFragmentToConfirmationFragment(
            R.drawable.ic_checked, msgResponse, R.string.purchase_confirmed_description), R.id.searchFragment)
    }

    override fun onFailure(response: Response<MsgResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), MsgResponse::class.java) }?.error.toString()
        Log.e(TAG, "Purchase with error: $msgResponse")
    }

    companion object {
        private const val TAG = "BOOKING"
    }

}