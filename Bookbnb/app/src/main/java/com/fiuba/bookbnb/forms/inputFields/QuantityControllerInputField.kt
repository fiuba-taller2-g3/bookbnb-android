package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import kotlinx.android.synthetic.main.bookbnb_quantity_controller_input_field_item.view.*
import org.apache.commons.lang3.StringUtils

class QuantityControllerInputField(context: Context, labelRes: Int,
                                   private val inputData: FormInputData,
                                   private val storeInputContent: (formInputData: FormInputData) -> Unit,
                                   private val minNumberLimit: Int = 0,
                                   private val maxNumberLimit: Int) : AbstractInputFieldItem(context) {

    private val mutableCurrentNumber = MutableLiveData<Int>()
    private val currentNumber: LiveData<Int>
        get() = mutableCurrentNumber

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_quantity_controller_input_field_item, this)
        setLayoutParams()
        quantity_controller_text.text = context.getString(labelRes)
        mutableCurrentNumber.value = getInitValue()
        initObserver()
        initListeners()
    }

    private fun getInitValue() : Int {
        return with(inputData.content) {
            if (isDigitsOnly() && isNotEmpty()) checkNumberLimits(toInt()) else minNumberLimit
        }
    }

    private fun checkNumberLimits(value: Int): Int {
        return if (value <= maxNumberLimit) value else maxNumberLimit
    }

    private fun initObserver() {
        currentNumber.observeForever { number ->
            quantity_controller_number_text.text = number.toString().plus(getPlusChar())
            checkInputState()
            storeInputContent(FormInputData(inputData.inputField, number.toString()))
        }
    }

    private fun initListeners() {
        dec_button.setOnClickListener {
            if (!isMinLimit()) mutableCurrentNumber.value = mutableCurrentNumber.value?.dec()
        }

        inc_button.setOnClickListener {
            if (!isMaxLimit()) mutableCurrentNumber.value = mutableCurrentNumber.value?.inc()
        }
    }

    private fun isMaxLimit() = mutableCurrentNumber.value == maxNumberLimit

    private fun isMinLimit() = mutableCurrentNumber.value == minNumberLimit

    private fun getPlusChar() = if (isMaxLimit()) PLUS_CHARACTER else StringUtils.EMPTY

    private fun setButtonState(isEnabled: Boolean, button: ImageView) {
        if (isEnabled) button.clearColorFilter() else button.setColorFilter(ContextCompat.getColor(context, R.color.colorQuantityControllerDisabled))
        button.isEnabled = isEnabled
    }

    private fun checkInputState() {
        setButtonState(!isMinLimit(), dec_button)
        setButtonState(!isMaxLimit(), inc_button)
    }

    override fun enableInput() = checkInputState()

    override fun disableInput() {
        setButtonState(false, inc_button)
        setButtonState(false, dec_button)
    }

    override fun isValidated(): Boolean = true

    companion object {
        private const val PLUS_CHARACTER = "+"
    }

}