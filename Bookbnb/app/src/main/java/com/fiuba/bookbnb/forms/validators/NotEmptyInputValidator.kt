package com.fiuba.bookbnb.forms.validators

import android.content.Context

class NotEmptyInputValidator(context: Context, private val msgEmptyRes: Int) : InputValidator(context) {

    override fun checkValidation(content: String): Boolean = content.isNotEmpty()

    override fun getMsgEmptyRes(): Int = msgEmptyRes

}