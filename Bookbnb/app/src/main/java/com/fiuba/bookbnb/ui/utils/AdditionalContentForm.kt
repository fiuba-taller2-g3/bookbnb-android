package com.fiuba.bookbnb.ui.utils

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_input_field_module.view.*

class AdditionalContentForm(context: Context, label: SpannableStringBuilder) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_additional_content_form, this).also {
            it.label.text = label
        }
    }
}