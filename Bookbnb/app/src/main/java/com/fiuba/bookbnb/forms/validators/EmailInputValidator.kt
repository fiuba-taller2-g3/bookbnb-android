package com.fiuba.bookbnb.forms.validators

class EmailInputValidator : Validator {

    private val emailPattern = Regex(PATTERN)

    override fun checkValidation(content: String): Boolean {
        return with(content) { trim().matches(emailPattern) && isNotEmpty() }
    }

    override fun getTextValidation(content: String): String {
        return if (content.isEmpty()) getEnterMsgWith(EMAIL_TEXT) else EMAIL_INVALID_VALIDATION_TEXT
    }

    companion object {
        private const val PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        private const val EMAIL_TEXT = "un email"
        private const val EMAIL_INVALID_VALIDATION_TEXT = "El formato de email no es válido"
    }
}