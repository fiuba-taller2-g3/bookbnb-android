package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.validators.Validator
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

abstract class EditTextAbstractInputField(context: Context, private val validation: Validator) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_item, this)
        setLayoutParams()
        validation.msgValidator.observeForever { msgValidation -> validation_text.text = msgValidation }
        onChangedTextListener()
    }

    override fun enableInput() = setInputStatus(true, R.color.colorTitle, R.color.colorWhite)

    override fun disableInput() = setInputStatus(false, R.color.colorTextInputFieldDisabled, R.color.colorBackgroundInputFieldDisabled)

    override fun isValidated(): Boolean {
        val textContent = edit_text.text.toString()
        val animation = AlphaAnimation(0.0f, 1.0f).also { it.duration = 1000 }

        return validation.checkValidation(textContent).also { isValid ->
            if (!validation_container.isVisible) validation_container.startAnimation(animation)
            validation_container.isVisible = !isValid
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
                validation_container.isVisible = false
            }
        })
    }

    private fun setInputStatus(isEnabled: Boolean, textColor: Int, backgroundColor: Int) {
        edit_text.isEnabled = isEnabled
        edit_text.setTextColor(ContextCompat.getColor(context, textColor))
        (edit_text.background as GradientDrawable).setColor(ContextCompat.getColor(context, backgroundColor))
    }

}