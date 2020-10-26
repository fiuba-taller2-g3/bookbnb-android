package com.fiuba.bookbnb.ui.utils

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FieldsId
import kotlinx.android.synthetic.main.bookbnb_text_input_field_view.view.*

class TextInputField(context: Context, label: String, private val fieldId: FieldsId, type: KeyboardType = KeyboardType.ALPHANUMERIC) : LinearLayout(context) {

    init {
        setLayoutParams()
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_view, this).also {
            it.label.text = label
        }
        setInputType(type)
    }

    fun getContentField() = edit_text.text.toString()

    fun getFieldId() = fieldId

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
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        orientation = VERTICAL
    }
}