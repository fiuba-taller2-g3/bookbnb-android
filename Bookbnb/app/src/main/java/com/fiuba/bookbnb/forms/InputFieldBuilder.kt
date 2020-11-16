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
            InputField.EMAIL -> EditTextInputFieldItem(context, EmailInputValidator(context, inputField.getTextEmptyValidator()))
            InputField.PASSWORD -> EditTextInputFieldItem(context, PassInputValidator(context, inputField.getTextEmptyValidator()), KeyboardType.ALPHANUMERIC_PASSWORD)
            InputField.PROFILE_TYPE -> RadioButtonInputField(context, Pair("Huesped","Anfitrión"))
            InputField.GENDER -> RadioButtonInputField(context, Pair("Hombre","Mujer"))
            else -> defaultBuild(context, inputField, KeyboardType.ALPHANUMERIC_PASSWORD)
        }
    }

    fun defaultBuild(context: Context, inputField: InputField, keyboardType: KeyboardType): AbstractInputFieldItem {
        return EditTextInputFieldItem(context, DefaultInputValidator(context, inputField.getTextEmptyValidator()), keyboardType)
    }
}