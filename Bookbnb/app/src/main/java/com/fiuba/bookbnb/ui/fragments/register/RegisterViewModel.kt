package com.fiuba.bookbnb.ui.fragments.register

import com.fiuba.bookbnb.domain.register.RegisterRequest
import com.fiuba.bookbnb.domain.register.RegisterResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.form.FormViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : FormViewModel() {

    fun register(request: RegisterRequest) {
        loadingStatusMutable.value = LoadingStatus.LOADING
        NetworkModule.buildRetrofitClient().register(request).enqueue(object : Callback<RegisterResponse> {

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    msgResponse = response.body()?.apiToken.toString()
                    loadingStatusMutable.value = LoadingStatus.SUCCESS
                } else {
                    msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), RegisterResponse::class.java) }?.error.toString()
                    loadingStatusMutable.value = LoadingStatus.FAILURE
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                msgResponse = t.message.toString()
                loadingStatusMutable.value = LoadingStatus.ERROR
            }
        })
    }
}