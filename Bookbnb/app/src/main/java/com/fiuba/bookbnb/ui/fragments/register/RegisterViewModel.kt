package com.fiuba.bookbnb.ui.fragments.register

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.google.gson.Gson
import retrofit2.Response

class RegisterViewModel : FormViewModel(), FormViewModel.CallResponse<MsgResponse> {

    fun register(request: UserData) {
        Log.i(TAG, "Registering user...")
        executeCallback(request, this) { NetworkModule.buildRetrofitClient().register(request) }
    }

    override fun onSuccessful(response: Response<MsgResponse>) {
        msgResponse = response.body()?.msg.toString()
        Log.i(TAG, "User registered successfully")
        NavigationManager.moveForwardWithPopUpTo(RegisterFragmentDirections.actionRegisterFragmentToConfirmationFragment(
            R.drawable.ic_checked, msgResponse, R.string.register_confirmed_description), R.id.loginFragment)
    }

    override fun onFailure(response: Response<MsgResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.error.toString()
        Log.e(TAG, "User register error: $msgResponse")
    }

    companion object {
        private const val TAG = "REGISTER"
    }

}