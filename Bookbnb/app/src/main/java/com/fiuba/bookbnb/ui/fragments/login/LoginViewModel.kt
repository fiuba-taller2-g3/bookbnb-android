package com.fiuba.bookbnb.ui.fragments.login

import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormViewModel
import com.google.gson.Gson
import retrofit2.Response

class LoginViewModel : FormViewModel(), FormViewModel.CallResponse<LoginResponse> {

    fun login(request: LoginRequest) {
        executeCallback(request, this) { NetworkModule.buildRetrofitClient().login(request) }
    }

    override fun onSuccessful(response: Response<LoginResponse>) {
        msgResponse = response.body()?.apiToken.toString()
    }

    override fun onFailure(response: Response<LoginResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.error.toString()
    }
}