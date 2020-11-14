package com.fiuba.bookbnb.forms.validators

import org.apache.commons.lang3.StringUtils

class PassInputValidator : Validator {

    override fun checkValidation(content: String): Boolean {
        return with(content) { !contains(StringUtils.SPACE) && isNotEmpty() && length >= MIN_LENGTH_PASSWORD_REQUIRED }
    }

    override fun getTextValidation(content: String): String = getEnterMsgWith(PASS_TEXT)

    companion object {
        private const val MIN_LENGTH_PASSWORD_REQUIRED = 4
        private const val PASS_TEXT = "una contraseña válida"
    }
}