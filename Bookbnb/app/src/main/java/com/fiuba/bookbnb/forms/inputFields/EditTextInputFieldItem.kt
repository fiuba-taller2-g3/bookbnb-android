package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.validators.InputValidator
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

class EditTextInputFieldItem(context: Context, inputData: FormInputData, storeInputContent: (formInputData: FormInputData) -> Unit,
                             validation: InputValidator, type: KeyboardType)
    : EditTextAbstractInputField(context, inputData, storeInputContent, validation) {

    init {
        setInputType(type)
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