package com.fiuba.bookbnb.forms

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import kotlinx.android.synthetic.main.bookbnb_input_field_module.view.*


class InputFieldModule @JvmOverloads constructor(context: Context, label: String,
                                                 private val inputFieldItem: AbstractInputFieldItem,
                                                 descriptionRes: Int?,
                                                 attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        setLayoutParams()
        LayoutInflater.from(context).inflate(R.layout.bookbnb_input_field_module, this).also {
            it.label.text = label
        }

        descriptionRes?.let {
            val description = context.resources.getString(it)
            description_text.isVisible = description.isNotEmpty()
            description_text.text = description
        }

        input_field.addView(inputFieldItem)
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL
    }

    fun getInputFieldItem() = inputFieldItem
}