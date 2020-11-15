package com.fiuba.bookbnb.forms

import android.content.Context
import com.fiuba.bookbnb.forms.inputFields.AbstractInputFieldItem
import com.fiuba.bookbnb.forms.inputFields.EditTextInputFieldItem
import com.fiuba.bookbnb.forms.validators.*
import com.fiuba.bookbnb.ui.utils.KeyboardType

object InputFieldBuilder {

    fun build(context: Context, inputField: InputField): AbstractInputFieldItem {
        return when (inputField) {
            InputField.NAME -> EditTextInputFieldItem(context, DefaultInputValidator(DEFAULT_NAME_TEXT_VALIDATION))
            InputField.SURNAME -> EditTextInputFieldItem(context, DefaultInputValidator(DEFAULT_SURNAME_TEXT_VALIDATION))
            InputField.EMAIL -> EditTextInputFieldItem(context, EmailInputValidator(DEFAULT_EMAIL_TEXT_VALIDATION))
            InputField.PASSWORD -> EditTextInputFieldItem(context, PassInputValidator(DEFAULT_PASSWORD_TEXT_VALIDATION), KeyboardType.ALPHANUMERIC_PASSWORD)
            InputField.BIRTHDAY -> EditTextInputFieldItem(context, DefaultInputValidator(DEFAULT_BIRTHDAY_TEXT_VALIDATION))
            else -> throw Exception("Error: The input type entered does not exist")
        }
    }

    private const val PREFIX_TEXT_VALIDATION = "Ingrese "
    private const val DEFAULT_NAME_TEXT_VALIDATION = PREFIX_TEXT_VALIDATION + "un nombre"
    private const val DEFAULT_SURNAME_TEXT_VALIDATION = PREFIX_TEXT_VALIDATION + "un apellido"
    private const val DEFAULT_EMAIL_TEXT_VALIDATION = PREFIX_TEXT_VALIDATION + "un correo electrónico"
    private const val DEFAULT_PASSWORD_TEXT_VALIDATION = PREFIX_TEXT_VALIDATION + "una contraseña"
    private const val DEFAULT_BIRTHDAY_TEXT_VALIDATION = PREFIX_TEXT_VALIDATION + "una fecha de nacimiento"
}