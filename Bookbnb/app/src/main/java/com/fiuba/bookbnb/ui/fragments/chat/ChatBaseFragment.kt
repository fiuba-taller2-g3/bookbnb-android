package com.fiuba.bookbnb.ui.fragments.chat

import androidx.annotation.LayoutRes
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons

abstract class ChatBaseFragment(@LayoutRes layout: Int) : BaseFragment(layout) {

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onResume() {
        super.onResume()
        sharedViewModel.setOption(FooterBarButtons.MESSAGES)
    }

}