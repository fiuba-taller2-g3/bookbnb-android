package com.fiuba.bookbnb.forms.validators

import android.content.Context
import com.fiuba.bookbnb.R

class EmailInputValidator(context: Context) : InputValidator(context) {

    private val emailPattern = Regex(PATTERN)

    override fun checkValidation(content: String): Boolean {
        return with(content) { trim().matches(emailPattern) && isNotEmpty() }
    }

    override fun getMsgEmptyRes(): Int = R.string.email_empty_msg_validation

    override fun updateTextValidation(content: String) {
        super.updateTextValidation(content)
        if (content.isNotEmpty()) msgValidatorMutable.value = context.getString(R.string.email_invalid_msg_validation)
    }

    companion object {
        private const val PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }
}