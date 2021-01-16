package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import kotlinx.android.synthetic.main.bookbnb_radiobutton_input_field_item.view.*
import org.apache.commons.lang3.StringUtils

class RadioButtonInputField(context: Context,
                            private val inputData: FormInputData,
                            private val storeInputContent: (formInputData: FormInputData) -> Unit,
                            private val optionsText: Pair<String, String>) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_radiobutton_input_field_item, this)
        setLayoutParams()
        first_radio_button.text = optionsText.first
        second_radio_button.text = optionsText.second

        loadColorState()
        loadChangeListener()
        initContent()
    }

    private fun loadChangeListener() {
        radio_group.setOnCheckedChangeListener { radioGroup, checkedId ->
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            storeInputContent(FormInputData(inputData.inputField, getOption(radioGroup.indexOfChild(radioButton))))
        }
    }

    private fun getOption(index: Int): String {
        return when(index) {
            0 -> optionsText.first
            else -> optionsText.second
        }
    }

    private fun initContent() {
        val initContent = checkContentAndGet()
        storeInputContent(FormInputData(inputData.inputField, initContent))
    }

    private fun checkContentAndGet(): String {
        with(inputData.content) {
            if (contentEquals(optionsText.first)) {
                first_radio_button.isChecked = true
                return inputData.content
            }
            if (contentEquals(optionsText.second)) {
                second_radio_button.isChecked = true
                return inputData.content
            }

            return optionsText.first
        }
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

    private fun setInputStatus(isEnabled: Boolean) {
        first_radio_button.isEnabled = isEnabled
        second_radio_button.isEnabled = isEnabled
    }
}