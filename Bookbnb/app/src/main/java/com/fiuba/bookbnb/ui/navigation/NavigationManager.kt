package com.fiuba.bookbnb.ui.navigation

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.fiuba.bookbnb.R

object NavigationManager {

    private val mutableNavigationLiveData = MutableLiveData<NavigationUpdate>()
    val navigationLiveData: LiveData<NavigationUpdate>
        get() = mutableNavigationLiveData

    fun showDialog(creator: ShowDialogLazyCreator) {
        mutableNavigationLiveData.value = NavigationUpdate.GlobalAction(R.id.nav_dialogs)
        mutableNavigationLiveData.value = NavigationUpdate.Dialog(creator)
    }

    fun moveForward(navigationDirections: NavDirections) {
        mutableNavigationLiveData.value = NavigationUpdate.Action(navigationDirections)
    }
}

typealias ShowDialogLazyCreator = () -> NavDirections

sealed class NavigationUpdate {
    data class GlobalAction(@IdRes val action: Int) : NavigationUpdate()
    data class Action(val directions: NavDirections) : NavigationUpdate()
    data class Dialog(val creator: ShowDialogLazyCreator) : NavigationUpdate()
}