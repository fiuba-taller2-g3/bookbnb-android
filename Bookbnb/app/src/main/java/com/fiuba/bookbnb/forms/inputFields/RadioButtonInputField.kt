package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.view.LayoutInflater
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_radiobutton_input_field_item.view.*

class RadioButtonInputField(context: Context, optionsText: Pair<String, String>) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_radiobutton_input_field_item, this)
        setLayoutParams()
        first_radio_button.text = optionsText.first
        second_radio_button.text = optionsText.second
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