package com.fiuba.bookbnb.forms.validators

import android.content.Context
import com.fiuba.bookbnb.R

class EmailInputValidator(context: Context, textEmptyValidation: String) : Validator(context, textEmptyValidation) {

    private val emailPattern = Regex(PATTERN)

    override fun checkValidation(content: String): Boolean {
        return with(content) { trim().matches(emailPattern) && isNotEmpty() }
    }

    override fun updateTextValidation(content: String) {
        super.updateTextValidation(content)
        if (content.isNotEmpty()) msgValidatorMutable.value = context.resources.getString(R.string.email_invalid_msg_validation)
    }

    companion object {
        private const val PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }
}