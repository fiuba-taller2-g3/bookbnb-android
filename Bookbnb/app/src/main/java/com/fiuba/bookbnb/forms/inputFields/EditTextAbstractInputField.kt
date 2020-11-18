package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.animation.AlphaAnimation
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.validators.Validator
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

abstract class EditTextAbstractInputField(context: Context, private val validation: Validator) : AbstractInputFieldItem(context) {

    override fun enableInput() = setInputStatus(true, R.color.colorTitle, R.color.colorWhite)

    override fun disableInput() = setInputStatus(false, R.color.colorTextInputFieldDisabled, R.color.colorBackgroundInputFieldDisabled)

    override fun isValidated(): Boolean {
        val textContent = edit_text.text.toString()
        val animation = AlphaAnimation(0.0f, 1.0f).also { it.duration = 1000 }

        return validation.checkValidation(textContent).also { isValid ->
            validation_text.isVisible = !isValid
            validation_text.startAnimation(animation)
            validation.updateTextValidation(textContent)
        }
    }

    override fun getContentField() = edit_text.text.toString()

    private fun setInputStatus(isEnabled: Boolean, textColor: Int, backgroundColor: Int) {
        edit_text.isEnabled = isEnabled
        edit_text.setTextColor(ContextCompat.getColor(context, textColor))
        (edit_text.background as GradientDrawable).setColor(ContextCompat.getColor(context, backgroundColor))
    }

}