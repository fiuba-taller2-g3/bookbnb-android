package com.fiuba.bookbnb.forms.validators

import android.content.Context
import com.fiuba.bookbnb.R

class PhoneNumberInputValidator(context: Context) : InputValidator(context) {

    override fun checkValidation(content: String): Boolean {
        return with(content) { isEmpty() || isMinLengthValidate(content) }
    }

    override fun getMsgEmptyRes(): Int = R.string.string_empty

    override fun updateTextValidation(content: String) {
        if (!checkValidation(content)) msgValidatorMutable.value = context.getString(R.string.phone_number_validation)
    }

    private fun isMinLengthValidate(content: String) = content.length >= MIN_LENGTH_PHONE_NUMBER_REQUIRED

    companion object {
        private const val MIN_LENGTH_PHONE_NUMBER_REQUIRED = 8
    }

}