package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.forms.FormViewModel
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_button.*

abstract class FormBaseFragment(@LayoutRes layout: Int) : BaseFragment(layout) {

    protected val formViewModel : FormViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonText(getButtonTextRes())
    }

    protected fun setButtonText(buttonTextRes: Int) {
        button.text = getString(buttonTextRes)
    }

    protected abstract fun getTitleTextRes() : Int
    protected abstract fun getSubtitleTextRes() : Int
    protected abstract fun getButtonTextRes() : Int
    protected abstract fun setActionEventButton()
}