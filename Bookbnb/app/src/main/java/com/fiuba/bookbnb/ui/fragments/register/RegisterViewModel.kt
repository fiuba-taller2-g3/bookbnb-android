package com.fiuba.bookbnb.ui.fragments.register

import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.domain.register.RegisterRequest
import com.fiuba.bookbnb.domain.register.RegisterResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormViewModel
import com.google.gson.Gson
import retrofit2.Response

class RegisterViewModel : FormViewModel(), FormViewModel.CallResponse<RegisterResponse> {

    fun register(request: RegisterRequest) {
        executeCallback(request, this) { NetworkModule.buildRetrofitClient().register(request) }
    }

    override fun onSuccessful(response: Response<RegisterResponse>) {
        msgResponse = response.body()?.apiToken.toString()
    }

    override fun onFailure(response: Response<RegisterResponse>) {
        msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.error.toString()
    }

}