package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import com.fiuba.bookbnb.forms.validators.Validator
import com.fiuba.bookbnb.ui.fragments.dialogs.DatePickerDialogFragment
import com.fiuba.bookbnb.ui.fragments.dialogs.DatePickerDialogFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.utils.DateUtils
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*
import org.apache.commons.lang3.StringUtils
import java.util.*


class DatePickerInputField(context: Context, validation: Validator, initialContent: String = StringUtils.EMPTY)
    : EditTextAbstractInputField(context, validation, initialContent), DatePickerDialogFragment.OnDateSetListener {

    private val selectedDate by lazy { Calendar.getInstance() }

    init {
        edit_text.isFocusable = false
        edit_text.isLongClickable = false;
        edit_text.setTextIsSelectable(false);
        inputClickListener()
        if (initialContent.isNotEmpty()) formatInitialContentToDate(initialContent)
    }

    private fun inputClickListener() {
        edit_text.setOnClickListener {
            NavigationManager.showDialog { DatePickerDialogFragmentDirections.showDatePickerDialogFragment(selectedDate, this) }
        }
    }

    private fun formatDateToString(date: Calendar): String = DateUtils.getDateFormat().format(date.time)

    private fun formatInitialContentToDate(content: String) {
        val initDate = DateUtils.getDateOutputFormat().parse(content)
        selectedDate.time = initDate!!
        val outputFormat = DateUtils.getDateFormat()
        edit_text.setText(outputFormat.format(initDate))
    }

    override fun onDateSet(date: Calendar, requestCode: Int) {
        val selectedYear = date.get(Calendar.YEAR)
        val selectedMonth = date.get(Calendar.MONTH)
        val selectedDay = date.get(Calendar.DAY_OF_MONTH)

        selectedDate.set(selectedYear, selectedMonth, selectedDay)
        edit_text.setText(formatDateToString(selectedDate))
    }

    override fun getContentField(): String {
        with(edit_text.text.toString()) {
            if (isNotEmpty()) {
                val initDate = DateUtils.getDateFormat().parse(this)
                val outputFormat = DateUtils.getDateOutputFormat()

                return outputFormat.format(initDate!!)
            }
        }

        return super.getContentField()
    }

}