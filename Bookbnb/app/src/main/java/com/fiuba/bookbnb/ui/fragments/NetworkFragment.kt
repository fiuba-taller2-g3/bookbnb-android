package com.fiuba.bookbnb.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import retrofit2.Call
import java.io.Serializable

@Deprecated("Se reemplazó por el networkManagerViewModel. Este fragment puede eliminarse cuando se termine toda la migración.")
abstract class NetworkFragment<T : Serializable>(@LayoutRes layout: Int) : BaseFragment(layout) {

    protected var currentRunningCall : Call<T>? = null
    override val isNetworkRequired: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.openFragment()
        setNetworkObserver()
    }

    protected open fun setNetworkObserver() {
        sharedViewModel.fragmentIsOpen.observe(viewLifecycleOwner) { isOpen ->
            if (!isOpen) {
                currentRunningCall?.cancel()
                setNetworkAdditionalObserver()
            }
        }
    }

    protected open fun setNetworkAdditionalObserver() {
        // Do Nothing
    }
}