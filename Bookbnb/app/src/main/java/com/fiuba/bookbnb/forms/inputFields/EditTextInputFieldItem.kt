package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.validators.Validator
import com.fiuba.bookbnb.ui.utils.KeyboardType
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

class EditTextInputFieldItem(context: Context,
                             private val validation: Validator,
                             type: KeyboardType = KeyboardType.ALPHANUMERIC) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_item, this)
        setLayoutParams()
        setInputType(type)
    }

    override fun enableInput() = setInputStatus(true, R.color.colorTitle, R.color.colorWhite)

    override fun disableInput() = setInputStatus(false, R.color.colorTextInputFieldDisabled, R.color.colorBackgroundInputFieldDisabled)

    override fun isValidated(): Boolean {
        val textContent = edit_text.text.toString()
        val animation = AlphaAnimation(0.0f, 1.0f).also { it.duration = 1000 }

        return validation.checkValidation(textContent).also { isValid ->
            validation_text.isVisible = !isValid
            validation_text.startAnimation(animation)
            validation_text.text = validation.getTextValidation(textContent)
        }
    }

    private fun setInputStatus(isEnabled: Boolean, textColor: Int, backgroundColor: Int) {
        edit_text.isEnabled = isEnabled
        edit_text.setTextColor(ContextCompat.getColor(context, textColor))
        (edit_text.background as GradientDrawable).setColor(ContextCompat.getColor(context, backgroundColor))
    }

    private fun setInputType(keyboardType: KeyboardType) {
        when (keyboardType) {
            KeyboardType.NUMERIC -> edit_text.inputType = InputType.TYPE_CLASS_NUMBER
            KeyboardType.NUMERIC_PASSWORD -> {
                edit_text.inputType = InputType.TYPE_CLASS_NUMBER
                edit_text.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            KeyboardType.ALPHANUMERIC_PASSWORD -> {
                edit_text.inputType = InputType.TYPE_CLASS_TEXT
                edit_text.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            else -> edit_text.inputType = InputType.TYPE_CLASS_TEXT
        }
    }

}