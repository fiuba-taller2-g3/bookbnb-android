package com.fiuba.bookbnb.forms.validators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class Validator(private val postFixDefaultMsg: String) {

    protected val msgValidatorMutable = MutableLiveData<String>()
    val msgValidator : LiveData<String>
        get() = msgValidatorMutable

    init {
        msgValidatorMutable.value = getDefaultMsg()
    }

    abstract fun checkValidation(content: String) : Boolean

    open fun updateTextValidation(content: String) {
        if (content.isEmpty()) msgValidatorMutable.value = getDefaultMsg()
    }

    private fun getDefaultMsg() = "Ingrese $postFixDefaultMsg"
}