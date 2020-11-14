package com.fiuba.bookbnb.forms.validators

class DefaultInputValidator : Validator {

    override fun checkValidation(content: String): Boolean = content.isNotEmpty()

    override fun getTextValidation(content: String): String = getEnterMsgWith("")

    companion object {
        const val NAME_TEXT = "un nombre"
        const val SURNAME_TEXT = "un apellido"
        const val PASS_TEXT = "una contraseña"
    }
}