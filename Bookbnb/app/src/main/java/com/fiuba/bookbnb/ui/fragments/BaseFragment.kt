package com.fiuba.bookbnb.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.ui.ShareViewModel

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    protected open val shouldShowToolbar = true
    protected open val shouldShowFooterBarMenu = false
    protected open val isNetworkRequired = false
    protected val sharedViewModel by activityViewModels<ShareViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.showToolbar(shouldShowToolbar)
        sharedViewModel.showFooterBarMenu(shouldShowFooterBarMenu)
        sharedViewModel.setFragmentWithNetwork(isNetworkRequired)
    }

}