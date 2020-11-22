package com.fiuba.bookbnb.forms.validators

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class Validator(protected val context: Context, private val textEmptyValidation: String) {

    protected val msgValidatorMutable = MutableLiveData<String>()
    val msgValidator : LiveData<String>
        get() = msgValidatorMutable

    init {
        msgValidatorMutable.value = textEmptyValidation
    }

    abstract fun checkValidation(content: String) : Boolean

    open fun updateTextValidation(content: String) {
        if (content.isEmpty()) msgValidatorMutable.value = textEmptyValidation
    }
}