package com.fiuba.bookbnb.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_text_input_field_view.view.*


@SuppressLint("ViewConstructor")
class TextInputField(context: Context, label: String, type: KeyboardType = KeyboardType.ALPHANUMERIC) : LinearLayout(context) {

    init {
        setLayoutParams()
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_view, this).also {
            it.label.text = label
        }
        setInputType(type)
    }

    fun getContentField() = edit_text.text.toString()

    fun setInputFieldStatus(isEnabled: Boolean) {
        edit_text.isEnabled = isEnabled
        edit_text.setTextColor(getTextColor())
        (edit_text.background as GradientDrawable).setColor(getBackgroundColor())
    }

    private fun getTextColor() = ContextCompat.getColor(context, if (edit_text.isEnabled) R.color.colorTitle else R.color.colorTextInputFieldDisabled)

    private fun getBackgroundColor() = ContextCompat.getColor(context, if (edit_text.isEnabled) R.color.colorWhite else R.color.colorBackgroundInputFieldDisabled)

    private fun setInputType(keyboardType: KeyboardType) {
        when (keyboardType) {
            KeyboardType.NUMERIC -> edit_text.inputType = InputType.TYPE_CLASS_NUMBER
            KeyboardType.NUMERIC_PASSWORD -> {
                edit_text.inputType = InputType.TYPE_CLASS_NUMBER
                edit_text.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            KeyboardType.ALPHANUMERIC_PASSWORD -> {
                edit_text.inputType = InputType.TYPE_CLASS_TEXT
                edit_text.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            else -> edit_text.inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    private fun setLayoutParams() {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL
    }
}