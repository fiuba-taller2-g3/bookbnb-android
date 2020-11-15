package com.fiuba.bookbnb.forms

import android.content.Context
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import com.fiuba.bookbnb.forms.inputFields.EditTextInputFieldItem
import com.fiuba.bookbnb.forms.inputFields.RadioButtonInputField
import com.fiuba.bookbnb.forms.validators.*
import com.fiuba.bookbnb.ui.utils.KeyboardType

object InputFieldBuilder {

    fun build(context: Context, inputField: InputField): AbstractInputFieldItem {
        return when (inputField) {
            InputField.EMAIL -> EditTextInputFieldItem(context, EmailInputValidator(inputField.getTextEmptyValidator()))
            InputField.PASSWORD -> EditTextInputFieldItem(context, PassInputValidator(inputField.getTextEmptyValidator()), KeyboardType.ALPHANUMERIC_PASSWORD)
            InputField.PROFILE_TYPE -> RadioButtonInputField(context, Pair("Huesped","Anfitrión"))
            else -> EditTextInputFieldItem(context, DefaultInputValidator(inputField.getTextEmptyValidator()))
        }
    }
}