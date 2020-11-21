package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_radiobutton_input_field_item.view.*

class RadioButtonInputField(context: Context, optionsText: Pair<String, String>) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_radiobutton_input_field_item, this)
        setLayoutParams()
        first_radio_button.text = optionsText.first
        second_radio_button.text = optionsText.second

        loadColorState()
    }

    private fun loadColorState() {
        ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled)
            ), intArrayOf(
                ContextCompat.getColor(context, R.color.colorTextInputFieldDisabled), //disabled
                ContextCompat.getColor(context, R.color.colorRed) //enabled
            )
        ).also {
            first_radio_button.buttonTintList = it
            second_radio_button.buttonTintList = it
        }
    }

    override fun enableInput() = setInputStatus(true)

    override fun disableInput() = setInputStatus(false)

    override fun isValidated(): Boolean = true

    override fun getContentField() = if (first_radio_button.isChecked) first_radio_button.text.toString() else second_radio_button.text.toString()

    private fun setInputStatus(isEnabled: Boolean) {
        first_radio_button.isEnabled = isEnabled
        second_radio_button.isEnabled = isEnabled
    }
}