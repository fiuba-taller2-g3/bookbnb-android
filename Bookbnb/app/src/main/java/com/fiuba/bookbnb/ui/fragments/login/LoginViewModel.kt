package com.fiuba.bookbnb.ui.fragments.login

import android.util.Log
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : NetworkViewModel(), NetworkViewModel.CallResponse<LoginResponse> {

    fun login(call: Call<LoginResponse>) {
        Log.i(TAG, "Login user...")
        executeCallback(call, this)
    }

    override fun onSuccessful(response: Response<LoginResponse>) {
        msgResponse = response.body()?.apiToken.toString()
        Log.i(TAG, "User logged successfully with token $msgResponse")
        NavigationManager.moveForward(LoginFragmentDirections.actionLoginFragmentToLoadingFragment(msgResponse))
    }

    override fun onFailure(response: Response<LoginResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.error.toString()
        Log.e(TAG, "User login error: $msgResponse")
    }

    companion object {
        private const val TAG = "LOGIN"
    }
}