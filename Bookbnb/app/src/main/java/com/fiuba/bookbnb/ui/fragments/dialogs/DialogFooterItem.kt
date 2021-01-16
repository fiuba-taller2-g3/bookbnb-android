package com.fiuba.bookbnb.ui.fragments.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_dialog_footer.view.*

class DialogFooterItem(context: Context) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_dialog_footer, this)
        setLayoutParams()
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).also { orientation = VERTICAL }
    }

    private fun setButtonClickListener(button: View, function: () -> Unit) {
        button.setOnClickListener { function() }
    }

    fun setCancelClickListener(function: () -> Unit) = setButtonClickListener(cancel_button, function)

    fun setAcceptClickListener(function: () -> Unit) = setButtonClickListener(accept_button, function)
}