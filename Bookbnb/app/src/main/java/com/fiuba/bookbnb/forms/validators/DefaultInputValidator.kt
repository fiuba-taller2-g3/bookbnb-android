package com.fiuba.bookbnb.forms.validators

class DefaultInputValidator(textEmptyValidation: String) : Validator(textEmptyValidation) {

    override fun checkValidation(content: String): Boolean = content.isNotEmpty()

}