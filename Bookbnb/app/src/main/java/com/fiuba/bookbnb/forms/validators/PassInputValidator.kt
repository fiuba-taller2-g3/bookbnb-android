package com.fiuba.bookbnb.forms.validators

import android.content.Context
import com.fiuba.bookbnb.R
import org.apache.commons.lang3.StringUtils

class PassInputValidator(context: Context) : InputValidator(context) {

    override fun checkValidation(content: String): Boolean {
        return with(content) { !contains(StringUtils.SPACE) && isNotEmpty() && isMinLengthValidate(content) }
    }

    override fun getMsgEmptyRes(): Int = R.string.pass_empty_msg_validation

    override fun updateTextValidation(content: String) {
        super.updateTextValidation(content)
        if (!isMinLengthValidate(content) && content.isNotEmpty()) msgValidatorMutable.value = context.getString(R.string.pass_min_length_msg_validation)
        if (content.contains(StringUtils.SPACE)) msgValidatorMutable.value = context.getString(R.string.pass_spaces_msg_validation)
    }

    private fun isMinLengthValidate(content: String) = content.length >= MIN_LENGTH_PASSWORD_REQUIRED

    companion object {
        private const val MIN_LENGTH_PASSWORD_REQUIRED = 8
    }
}