package com.fiuba.bookbnb.ui.fragments.register

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel : NetworkViewModel<MsgResponse>() {

    override fun execute(call: Call<MsgResponse>) {
        Log.i(TAG, "Registering user...")
        executeCallback(call)
    }

    override fun onSuccessful(response: Response<MsgResponse>) {
        msgResponse = response.body()?.msg.toString()
        Log.i(TAG, "User registered successfully")
        NavigationManager.moveForwardWithPopUpTo(RegisterFragmentDirections.actionRegisterFragmentToConfirmationFragment(
            R.drawable.ic_checked, msgResponse, R.string.register_confirmed_description), R.id.startLoginFragment)
    }

    override fun onFailure(response: Response<MsgResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.error.toString()
        Log.e(TAG, "User register error: $msgResponse")
    }

    companion object {
        private const val TAG = "REGISTER"
    }

}