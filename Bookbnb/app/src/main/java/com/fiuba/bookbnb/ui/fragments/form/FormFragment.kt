package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_form.*

abstract class FormFragment : Fragment(R.layout.bookbnb_form) {

    private val forms by lazy { HashMap<String, String>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bkbnb_form_title.text = getString(getTitle())
        bkbnb_form_subtitle.text = getString(getSubtitle())
    }

    protected abstract fun getTitle() : Int

    protected abstract fun getSubtitle() : Int

}