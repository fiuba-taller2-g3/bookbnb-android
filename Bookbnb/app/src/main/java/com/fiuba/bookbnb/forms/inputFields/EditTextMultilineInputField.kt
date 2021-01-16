package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.validators.InputValidator
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

class EditTextMultilineInputField(context: Context, inputData: FormInputData, storeInputContent: (formInputData: FormInputData) -> Unit,
                                  validation: InputValidator) : EditTextAbstractInputField(context, inputData, storeInputContent, validation) {

    init {
        edit_text.isSingleLine = false
        edit_text.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
        edit_text.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
        edit_text.setLines(LINE_HEIGHT)
        edit_text.maxLines = LINE_HEIGHT
        edit_text.filters = arrayOf(InputFilter.LengthFilter(MAX_LENGTH))
        edit_text.gravity = Gravity.TOP or Gravity.START
    }

    companion object {
        const val LINE_HEIGHT = 8
        const val MAX_LENGTH = 300
    }
}