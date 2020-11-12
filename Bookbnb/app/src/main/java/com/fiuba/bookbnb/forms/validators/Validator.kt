package com.fiuba.bookbnb.forms.validators

interface Validator {

    fun checkValidation(content: String) : Boolean
    fun getTextValidation(content: String) : String
}