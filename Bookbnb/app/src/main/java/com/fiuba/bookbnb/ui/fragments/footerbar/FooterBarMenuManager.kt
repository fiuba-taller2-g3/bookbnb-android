package com.fiuba.bookbnb.ui.fragments.footerbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FooterBarMenuManager {

    private val mutableFooterBarMenuOptionSelected = MutableLiveData<FooterBarButtons>()
    val footerBarMenuOptionSelected: LiveData<FooterBarButtons>
        get() = mutableFooterBarMenuOptionSelected

    init {
        setOption(FooterBarButtons.SEARCH)
    }

    fun setOption(option: FooterBarButtons) {
        mutableFooterBarMenuOptionSelected.value = option
    }
}