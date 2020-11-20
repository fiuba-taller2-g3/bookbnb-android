package com.fiuba.bookbnb.ui.fragments.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.repository.LoadingStatus
import org.apache.commons.lang3.StringUtils

abstract class FormViewModel : ViewModel() {

    protected var msgResponse = StringUtils.EMPTY
    protected val loadingStatusMutable = MutableLiveData<LoadingStatus>()
    val loadingStatus : LiveData<LoadingStatus>
        get() = loadingStatusMutable

    fun hideLoading() {
        loadingStatusMutable.value = LoadingStatus.HIDE
    }

    fun getMessageResponse() = msgResponse
}