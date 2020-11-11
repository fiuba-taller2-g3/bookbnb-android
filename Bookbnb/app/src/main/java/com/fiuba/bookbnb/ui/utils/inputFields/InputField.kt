package com.fiuba.bookbnb.ui.utils.inputFields

interface InputField {

    fun enable()
    fun disable()
    fun isValidated()
    fun getContentField() : String

}