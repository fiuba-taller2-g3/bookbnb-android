package com.fiuba.bookbnb.networking

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import retrofit2.Call
import java.io.Serializable

abstract class NetworkFragment<T : Serializable>(@LayoutRes layout: Int) : BaseFragment(layout) {

    private val networkViewModel by activityViewModels<NetworkViewModel>()
    protected var currentRunningCall : Call<T>? = null
    override val isNetworkRequired: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkViewModel.openFragment()
        setNetworkObserver()
    }

    protected open fun setNetworkObserver() {
        networkViewModel.fragmentIsOpen.observe(viewLifecycleOwner) { isOpen ->
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