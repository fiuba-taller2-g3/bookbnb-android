package com.fiuba.bookbnb.forms.validators

import org.apache.commons.lang3.StringUtils

class PassInputValidator(textEmptyValidation: String) : Validator(textEmptyValidation) {

    override fun checkValidation(content: String): Boolean {
        return with(content) { !contains(StringUtils.SPACE) && isNotEmpty() && length >= MIN_LENGTH_PASSWORD_REQUIRED }
    }

    companion object {
        private const val MIN_LENGTH_PASSWORD_REQUIRED = 4
    }
}