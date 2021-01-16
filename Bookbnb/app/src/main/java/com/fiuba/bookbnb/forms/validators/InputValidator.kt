package com.fiuba.bookbnb.forms.validators

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fiuba.bookbnb.R

abstract class InputValidator(protected val context: Context) {

    protected val msgValidatorMutable = MutableLiveData<String>()
    val msgValidator : LiveData<String>
        get() = msgValidatorMutable

    abstract fun checkValidation(content: String) : Boolean
    protected abstract fun getMsgEmptyRes() : Int

    open fun updateTextValidation(content: String) {
        checkEmptyMsgValidation(content, getMsgEmptyRes())
    }

    private fun checkEmptyMsgValidation(content: String, msgRes: Int) {
        if (content.isEmpty()) msgValidatorMutable.value = with(context) { getString(R.string.prefix_empty_msg_validation, getString(msgRes)) }
    }
}