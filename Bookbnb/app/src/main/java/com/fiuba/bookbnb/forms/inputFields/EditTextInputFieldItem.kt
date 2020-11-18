package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.validators.Validator
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

class EditTextInputFieldItem(context: Context,
                             validation: Validator,
                             type: KeyboardType = KeyboardType.ALPHANUMERIC) : EditTextAbstractInputField(context, validation) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_item, this)
        setLayoutParams()
        setInputType(type)
        validation.msgValidator.observeForever { msgValidation -> validation_text.text = msgValidation }
    }

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

}