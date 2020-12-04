package com.fiuba.bookbnb.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkViewModel : ViewModel() {

    private val mutableFragmentIsOpen = MutableLiveData<Boolean>()
    val fragmentIsOpen: LiveData<Boolean>
        get() = mutableFragmentIsOpen

    fun closeFragment() { mutableFragmentIsOpen.value = false }
    fun openFragment() { mutableFragmentIsOpen.value = true }
}