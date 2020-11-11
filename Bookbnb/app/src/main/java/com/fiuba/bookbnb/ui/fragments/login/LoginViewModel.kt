package com.fiuba.bookbnb.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.domain.login.LoginRequest
import com.fiuba.bookbnb.domain.login.LoginResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.repository.LoadingStatus
import com.google.gson.Gson
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private var msgResponse = StringUtils.EMPTY
    private val loadingStatusMutable = MutableLiveData<LoadingStatus>()
    val loadingStatus : LiveData<LoadingStatus>
        get() = loadingStatusMutable

    fun getMsgResponse() = msgResponse

    fun hideLoading() {
        loadingStatusMutable.value = LoadingStatus.HIDE
    }

    fun login(request: LoginRequest) {
        loadingStatusMutable.value = LoadingStatus.LOADING
        NetworkModule.buildRetrofitClient().login(request).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    msgResponse = response.body()?.msg.toString()
                    loadingStatusMutable.value = LoadingStatus.SUCCESS
                } else {
                    msgResponse = response.errorBody()?.let { Gson().fromJson(it.string(), LoginResponse::class.java) }?.msg.toString()
                    loadingStatusMutable.value = LoadingStatus.FAILURE
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                msgResponse = t.message.toString()
                loadingStatusMutable.value = LoadingStatus.ERROR
            }
        })
    }
}