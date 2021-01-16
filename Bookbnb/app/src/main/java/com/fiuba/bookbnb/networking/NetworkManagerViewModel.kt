package com.fiuba.bookbnb.networking

import androidx.lifecycle.ViewModel

class NetworkManagerViewModel : ViewModel() {

    private var cancelCurrentRunningCall : (() -> Unit)? = null

    fun clearCancelRunningCallReference() {
        cancelCurrentRunningCall = null
    }

    fun setCancelCurrentRunningCallReference(cancelFunction: () -> Unit) {
        cancelCurrentRunningCall = cancelFunction
    }

    fun cancelCurrentRunningCall() {
        cancelCurrentRunningCall?.invoke()
        clearCancelRunningCallReference()
    }

}