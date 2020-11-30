package com.fiuba.bookbnb.forms

import android.content.Context
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import com.fiuba.bookbnb.forms.inputFields.DatePickerInputField
import com.fiuba.bookbnb.forms.inputFields.EditTextInputFieldItem
import com.fiuba.bookbnb.forms.inputFields.RadioButtonInputField
import com.fiuba.bookbnb.forms.validators.DatePickerInputValidator
import com.fiuba.bookbnb.forms.validators.DefaultInputValidator
import com.fiuba.bookbnb.forms.validators.EmailInputValidator
import com.fiuba.bookbnb.forms.validators.PassInputValidator
import com.fiuba.bookbnb.ui.utils.KeyboardType

object InputFieldBuilder {

    fun build(context: Context, inputField: InputField, initialContent: String): AbstractInputFieldItem {
        return when (inputField) {
            InputField.EMAIL -> EditTextInputFieldItem(context, EmailInputValidator(context, inputField.getTextEmptyValidator()), initialContent)
            InputField.PASSWORD -> EditTextInputFieldItem(context, PassInputValidator(context, inputField.getTextEmptyValidator()), initialContent, KeyboardType.ALPHANUMERIC_PASSWORD)
            InputField.PROFILE_TYPE -> RadioButtonInputField(context, Pair("Huesped","Anfitrión"))
            InputField.GENDER -> RadioButtonInputField(context, Pair("Hombre","Mujer"))
            InputField.BIRTHDATE -> DatePickerInputField(context, DatePickerInputValidator(context, inputField.getTextEmptyValidator()), initialContent)
            else -> defaultBuild(context, inputField, KeyboardType.ALPHANUMERIC, initialContent)
        }
    }

    fun defaultBuild(context: Context, inputField: InputField, keyboardType: KeyboardType, initialContent: String): AbstractInputFieldItem {
        return EditTextInputFieldItem(context, DefaultInputValidator(context, inputField.getTextEmptyValidator()), initialContent, keyboardType)
    }
}