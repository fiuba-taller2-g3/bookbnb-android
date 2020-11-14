package com.fiuba.bookbnb.forms.validators

class EmailInputValidator(postFixDefaultMsg: String) : Validator(postFixDefaultMsg) {

    private val emailPattern = Regex(PATTERN)

    override fun checkValidation(content: String): Boolean {
        return with(content) { trim().matches(emailPattern) && isNotEmpty() }
    }

    override fun updateTextValidation(content: String) {
        super.updateTextValidation(content)
        if (content.isNotEmpty()) msgValidatorMutable.value = EMAIL_INVALID_VALIDATION_TEXT
    }

    companion object {
        private const val PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        private const val EMAIL_INVALID_VALIDATION_TEXT = "El formato de email no es válido"
    }
}