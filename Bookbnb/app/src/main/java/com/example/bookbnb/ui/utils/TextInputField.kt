package com.example.bookbnb.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_text_input_field_view.view.*

class TextInputField(context: Context, label: String) : LinearLayout(context) {

    init {
        setLayoutParams()
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_view, this).also {
            it.label.text = label
        }
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        orientation = VERTICAL
    }
}