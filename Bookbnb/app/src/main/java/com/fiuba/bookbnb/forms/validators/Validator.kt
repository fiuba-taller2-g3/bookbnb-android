package com.fiuba.bookbnb.forms.validators

interface Validator {

    fun checkValidation(content: String) : Boolean
    fun getTextValidation(content: String) : String

    fun getEnterMsgWith(nameField: String) = ENTER_TEXT_PREFIX + nameField

    companion object {
        private const val ENTER_TEXT_PREFIX = "Ingrese "
        const val NAME_TEXT = ENTER_TEXT_PREFIX + "un nombre"
        const val SURNAME_TEXT = ENTER_TEXT_PREFIX + "un apellido"
        const val PASS_TEXT = ENTER_TEXT_PREFIX + "una contraseña"
    }
}