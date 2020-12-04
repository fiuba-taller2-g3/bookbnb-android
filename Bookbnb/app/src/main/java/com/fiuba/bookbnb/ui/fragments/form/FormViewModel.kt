package com.fiuba.bookbnb.ui.fragments.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.repository.LoadingStatus
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class FormViewModel : ViewModel() {

    protected var msgResponse = StringUtils.EMPTY
    protected val loadingStatusMutable = MutableLiveData<LoadingStatus>()
    val loadingStatus : LiveData<LoadingStatus>
        get() = loadingStatusMutable

    fun hideLoading() {
        loadingStatusMutable.value = LoadingStatus.HIDE
    }

    fun getMessageResponse() = msgResponse

    /* This function fixes a bug with the loading status during user login */
    open fun isLoginUser() = false

    protected fun <T> executeCallback(call: Call<T>, callResponse: CallResponse<T>) {
        loadingStatusMutable.value = LoadingStatus.LOADING
        call.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callResponse.onSuccessful(response)
                    if (!isLoginUser()) loadingStatusMutable.value = LoadingStatus.SUCCESS
                } else {
                    callResponse.onFailure(response)
                    loadingStatusMutable.value = LoadingStatus.FAILURE
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (!call.isCanceled) {
                    msgResponse = t.message.toString()
                    loadingStatusMutable.value = LoadingStatus.ERROR
                }
            }
        })
    }

    interface CallResponse<T> {
        fun onSuccessful(response: Response<T>)
        fun onFailure(response: Response<T>)
    }

}