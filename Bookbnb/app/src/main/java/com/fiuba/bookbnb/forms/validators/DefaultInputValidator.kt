package com.fiuba.bookbnb.forms.validators

class DefaultInputValidator(defaultMsg: String) : Validator(defaultMsg) {

    override fun checkValidation(content: String): Boolean = content.isNotEmpty()

}