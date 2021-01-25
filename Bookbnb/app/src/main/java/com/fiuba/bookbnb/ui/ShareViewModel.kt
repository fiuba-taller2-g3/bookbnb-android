package com.fiuba.bookbnb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons

class ShareViewModel : ViewModel() {

    private val mutableToolbarVisible = MutableLiveData<Boolean>()
    val toolbarVisible: LiveData<Boolean>
        get() = mutableToolbarVisible

    private val mutableFooterBarMenuVisible = MutableLiveData<Boolean>()
    val footerBarMenu: LiveData<Boolean>
        get() = mutableFooterBarMenuVisible

    private val mutableFragmentIsOpen = MutableLiveData<Boolean>()
    val fragmentIsOpen: LiveData<Boolean>
        get() = mutableFragmentIsOpen

    private val mutableFooterBarMenuOptionSelected = MutableLiveData<FooterBarButtons>()
    val footerBarMenuOptionSelected: LiveData<FooterBarButtons>
        get() = mutableFooterBarMenuOptionSelected

    private var isFragmentWithNetwork = false
    var shouldClearInputsWhenBackPressed = true

    fun showToolbar(show: Boolean) { mutableToolbarVisible.value = show }
    fun showFooterBarMenu(show: Boolean) { mutableFooterBarMenuVisible.value = show }

    fun setFragmentWithNetwork(haveNetwork: Boolean) { isFragmentWithNetwork = haveNetwork }

    fun openFragment() { mutableFragmentIsOpen.value = true }

    fun setOption(option: FooterBarButtons) {
        mutableFooterBarMenuOptionSelected.value = option
    }

}