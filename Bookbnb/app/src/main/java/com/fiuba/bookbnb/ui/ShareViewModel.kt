package com.fiuba.bookbnb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    private val mutableToolbarVisible = MutableLiveData<Boolean>()
    val toolbarVisible: LiveData<Boolean>
        get() = mutableToolbarVisible

    private val mutableFragmentIsOpen = MutableLiveData<Boolean>()
    val fragmentIsOpen: LiveData<Boolean>
        get() = mutableFragmentIsOpen

    private var isFragmentWithNetwork = false

    fun showToolbar(show: Boolean) { mutableToolbarVisible.value = show }

    fun setFragmentWithNetwork(haveNetwork: Boolean) { isFragmentWithNetwork = haveNetwork }
    fun fragmentHaveNetwork() = isFragmentWithNetwork

    fun closeFragment() { mutableFragmentIsOpen.value = false }
    fun openFragment() { mutableFragmentIsOpen.value = true }

}