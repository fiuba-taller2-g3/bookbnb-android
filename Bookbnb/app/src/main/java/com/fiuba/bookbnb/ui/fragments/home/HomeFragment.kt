package com.fiuba.bookbnb.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons

class HomeFragment : BaseFragment(R.layout.bookbnb_home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override val shouldShowToolbar: Boolean
        get() = false

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    override fun onResume() {
        super.onResume()
        sharedViewModel.setOption(FooterBarButtons.SEARCH)
    }

}