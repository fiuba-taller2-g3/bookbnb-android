package com.fiuba.bookbnb.forms.validators

class DefaultInputValidator(postFixDefaultMsg: String) : Validator(postFixDefaultMsg) {

    override fun checkValidation(content: String): Boolean = content.isNotEmpty()

}