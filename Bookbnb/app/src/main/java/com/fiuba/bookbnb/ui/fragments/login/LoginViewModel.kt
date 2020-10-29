package com.fiuba.bookbnb.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val showLoadingMutable = MutableLiveData<Boolean>()
    val showLoading : LiveData<Boolean>
        get() = showLoadingMutable

    init {
        hideLoading()
    }

    fun showLoading() {
        showLoadingMutable.value = true
    }

    fun hideLoading() {
        showLoadingMutable.value = false
    }
}