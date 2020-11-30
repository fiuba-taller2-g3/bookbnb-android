package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.validators.Validator
import com.fiuba.bookbnb.utils.AnimUtils
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

abstract class EditTextAbstractInputField(context: Context, private val validation: Validator,
                                          initialContent: String) : AbstractInputFieldItem(context) {

    private var isTextValidationDisplayed = false

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_item, this)
        setLayoutParams()
        edit_text.setText(initialContent)
        validation.msgValidator.observeForever { msgValidation -> validation_text.text = msgValidation }
        onChangedTextListener()
    }

    override fun enableInput() = setInputStatus(true, R.color.colorTitle, R.color.colorWhite)

    override fun disableInput() = setInputStatus(false, R.color.colorTextInputFieldDisabled, R.color.colorBackgroundInputFieldDisabled)

    override fun isValidated(): Boolean {
        val textContent = edit_text.text.toString()

        return validation.checkValidation(textContent).also { isValid ->
            if (!isValid && !isTextValidationDisplayed) {
                AnimUtils.expandLayout(validation_text)
                validation_text.startAnimation(AnimUtils.fadeInit())
                isTextValidationDisplayed = true
            }
            validation.updateTextValidation(textContent)
        }
    }

    override fun getContentField() = edit_text.text.toString()

    private fun onChangedTextListener() {
        edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // Do nothing
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (isTextValidationDisplayed) {
                    AnimUtils.collapseLayout(validation_text)
                    isTextValidationDisplayed = false
                }
            }
        })
    }

    private fun setInputStatus(isEnabled: Boolean, textColor: Int, backgroundColor: Int) {
        edit_text.isEnabled = isEnabled
        edit_text.setTextColor(ContextCompat.getColor(context, textColor))
        (edit_text.background as GradientDrawable).setColor(ContextCompat.getColor(context, backgroundColor))
    }

}