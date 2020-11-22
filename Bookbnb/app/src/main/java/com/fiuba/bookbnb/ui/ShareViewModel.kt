package com.fiuba.bookbnb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    private val mutableToolbarVisible = MutableLiveData<Boolean>()
    val toolbarVisible: LiveData<Boolean>
        get() = mutableToolbarVisible

    fun showToolbar(show: Boolean) { mutableToolbarVisible.value = show }

}