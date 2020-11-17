package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.view.LayoutInflater
import androidx.navigation.findNavController
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.validators.Validator
import com.fiuba.bookbnb.ui.fragments.dialogs.DatePickerDialogFragment
import com.fiuba.bookbnb.ui.fragments.dialogs.DatePickerDialogFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*


class DatePickerInputField(context: Context, private val validation: Validator) : AbstractInputFieldItem(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.bookbnb_text_input_field_item, this)
        setLayoutParams()
        validation.msgValidator.observeForever { msgValidation -> validation_text.text = msgValidation }
        edit_text.isFocusable = false

        edit_text.setOnClickListener {

            NavigationManager.showDialog { DatePickerDialogFragmentDirections.showDatePickerDialogFragment() }

//            val currentDate = Calendar.getInstance()
//            val currentYear = currentDate.get(Calendar.YEAR)
//            val currentMonth = currentDate.get(Calendar.MONTH)
//            val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
//
//            val datePickerDialog = DatePickerDialog(context, { datepicker, selectedyear, selectedmonth, selectedday ->
//                edit_text.setText("$selectedday/$selectedmonth/$selectedyear")
//            }, currentYear, currentMonth, currentDay)
//            datePickerDialog.setTitle("Seleccione fecha")
//            datePickerDialog.show()
        }
    }

    override fun enableInput() {
        TODO("Not yet implemented")
    }

    override fun disableInput() {
        TODO("Not yet implemented")
    }

    override fun isValidated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getContentField(): String {
        TODO("Not yet implemented")
    }
}