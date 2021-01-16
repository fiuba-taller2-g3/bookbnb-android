package com.fiuba.bookbnb.forms.validators

import android.content.Context
import com.fiuba.bookbnb.R

class DisabledInputValidator(context: Context) : InputValidator(context) {

    override fun checkValidation(content: String): Boolean = true

    override fun getMsgEmptyRes(): Int = R.string.string_empty

}