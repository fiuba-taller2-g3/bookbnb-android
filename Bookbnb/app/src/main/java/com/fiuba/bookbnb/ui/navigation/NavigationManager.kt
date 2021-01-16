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
        moveGlobalTo(R.id.nav_dialogs)
        mutableNavigationLiveData.value = NavigationUpdate.Dialog(creator)
    }

    fun moveForward(navigationDirections: NavDirections) {
        moveForwardWithPopUpTo(navigationDirections, null)
    }

    fun moveForwardWithPopUpTo(navigationDirections: NavDirections, popUpTo: Int?, popUpToInclusive: Boolean = false) {
        mutableNavigationLiveData.value = NavigationUpdate.Action(navigationDirections, popUpTo, popUpToInclusive)
    }

    fun moveGlobalTo(@IdRes action: Int) {
        moveGlobalToWithPopUpTo(action, null)
    }

    private fun moveGlobalToWithPopUpTo(@IdRes action: Int, popUpTo: Int?, popUpToInclusive: Boolean = false) {
        mutableNavigationLiveData.value = NavigationUpdate.GlobalAction(action, popUpTo, popUpToInclusive)
    }

    fun popBackStack() {
        mutableNavigationLiveData.value = NavigationUpdate.PopBackStack
    }

    fun cleanAction() {
        mutableNavigationLiveData.value = NavigationUpdate.NoAction
    }
}

typealias ShowDialogLazyCreator = () -> NavDirections

sealed class NavigationUpdate {
    data class GlobalAction(@IdRes val action: Int, val popUpTo: Int?, val popUpToInclusive: Boolean) : NavigationUpdate()
    data class Action(val directions: NavDirections, val popUpTo: Int?, val popUpToInclusive: Boolean) : NavigationUpdate()
    data class Dialog(val creator: ShowDialogLazyCreator) : NavigationUpdate()
    object PopBackStack : NavigationUpdate()
    object NoAction : NavigationUpdate()
}