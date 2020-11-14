package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_input_field_module.view.*
import org.apache.commons.lang3.StringUtils


class InputFieldModule @JvmOverloads constructor(context: Context, label: String,
                                                 inputFieldItem: AbstractInputFieldItem,
                                                 description: String = StringUtils.EMPTY,
                                                 attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        setLayoutParams()
        LayoutInflater.from(context).inflate(R.layout.bookbnb_input_field_module, this).also {
            it.label.text = label
        }
        description_text.isVisible = description.isNotEmpty()
        input_field.addView(inputFieldItem)
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL
    }
}