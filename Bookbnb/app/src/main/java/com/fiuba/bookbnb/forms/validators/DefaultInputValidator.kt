package com.fiuba.bookbnb.forms.validators

import android.content.Context

class DefaultInputValidator(context: Context, textEmptyValidation: String) : Validator(context, textEmptyValidation) {

    override fun checkValidation(content: String): Boolean = content.isNotEmpty()

}