package com.fiuba.bookbnb.ui.fragments.login

import android.util.Log
import com.auth0.jwt.JWT
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.form.FormViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserInfo
import com.fiuba.bookbnb.user.UserManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginViewModel : FormViewModel(), FormViewModel.CallResponse<LoginResponse> {

    fun login(request: LoginRequest) {
        Log.i(TAG, "Login user...")
        executeCallback(request, this) { NetworkModule.buildRetrofitClient().login(request) }
    }

    override fun onSuccessful(response: Response<LoginResponse>) {
        msgResponse = response.body()?.apiToken.toString()
        Log.i(TAG, "User logged successfully with token $msgResponse")
        loginUser(msgResponse)
    }

    override fun onFailure(response: Response<LoginResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.error.toString()
        Log.e(TAG, "User login error: $msgResponse")
    }

    override fun isLoginUser() = true

    private fun loginUser(token: String) {
        Log.i(TAG, "Decoding token...")
        val decode = JWT.decode(token)
        decode.claims?.let { claim ->
            val id = claim["id"]?.asInt()
            val exp = decode.expiresAt
            loadUserData(id!!, token, exp)
        }
    }

    private fun loadUserData(userId: Int, token: String, exp: Date) {
        Log.i(TAG, "Getting logged user information...")
        val userDataResponse = NetworkModule.buildRetrofitClient().getUser(userId, token)
        userDataResponse.enqueue(object : Callback<UserData> {

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful) {
                    loadingStatusMutable.value = LoadingStatus.SUCCESS
                    response.body()?.let {
                        Log.i(TAG, "Getting logged successfully with id: $userId")
                        UserManager.setUserInfo(UserInfo(token, userId, exp, it))
                        NavigationManager.moveForwardWithPopUpTo(LoginFragmentDirections.actionLoginFragmentToProfileMenuFragment(), R.id.homeFragment)
                    }
                } else { loadingStatusMutable.value = LoadingStatus.FAILURE }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                val msg = t.message.toString()
                Log.e(TAG, "User login error: $msg")
                throw Exception(msg)
            }
        })
    }

    companion object {
        private const val TAG = "LOGIN"
    }
}