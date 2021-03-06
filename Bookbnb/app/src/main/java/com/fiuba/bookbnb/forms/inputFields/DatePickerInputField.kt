package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.forms.validators.DatePickerInputValidator
import com.fiuba.bookbnb.forms.validators.InputValidator
import com.fiuba.bookbnb.ui.fragments.dialogs.DatePickerDialogFragment
import com.fiuba.bookbnb.ui.fragments.dialogs.DatePickerDialogFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.utils.AnimUtils
import com.fiuba.bookbnb.utils.DateUtils
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*
import org.apache.commons.lang3.StringUtils
import java.util.*


class DatePickerInputField(context: Context, inputData: FormInputData,
                           private val titleDialogRes: Int,
                           private val storeInputContent: (formInputData: FormInputData) -> Unit,
                           private val minDate: Date? = null,
                           private val maxDate: Date? = null,
                           showCleanFieldButton: Boolean = false,
                           validator: InputValidator = DatePickerInputValidator(context))
    : EditTextAbstractInputField(context, inputData, storeInputContent, validator), DatePickerDialogFragment.OnDateSetListener {

    private val selectedDate by lazy { Calendar.getInstance() }

    init {
        edit_text.isFocusable = false
        edit_text.isLongClickable = false;
        edit_text.setTextIsSelectable(false);
        inputClickListener()
        if (inputData.content.isNotEmpty()) formatInitialContentToDate(inputData.content)
        if (showCleanFieldButton) setClearFieldButton()
    }

    private fun setClearFieldButton() {
        clear_field_button.isVisible = edit_text.text.isNotEmpty()
        clear_field_button.setOnClickListener { edit_text.text.clear() }

        edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // Do nothing
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                clear_field_button.isVisible = s.isNotEmpty()
            }
        })
    }

    private fun inputClickListener() {
        edit_text.setOnClickListener {
            NavigationManager.showDialog { DatePickerDialogFragmentDirections.showDatePickerDialogFragment(titleDialogRes, selectedDate, minDate, maxDate, this) }
        }
    }

    private fun formatDateToString(date: Calendar): String = DateUtils.getDateFormat().format(date.time)

    private fun formatInitialContentToDate(content: String) {
        val initDate = DateUtils.getDateOutputFormat().parse(content)
        selectedDate.time = initDate!!
        val outputFormat = DateUtils.getDateFormat()
        edit_text.setText(outputFormat.format(initDate))
    }

    override fun setInputContentInStore(inputField: FormInputType, content: String) {
        storeInputContent(FormInputData(inputField, getContentField()))
    }

    override fun onDateSet(date: Calendar, requestCode: Int) {
        val selectedYear = date.get(Calendar.YEAR)
        val selectedMonth = date.get(Calendar.MONTH)
        val selectedDay = date.get(Calendar.DAY_OF_MONTH)

        selectedDate.set(selectedYear, selectedMonth, selectedDay)
        edit_text.setText(formatDateToString(selectedDate))
    }

    private fun getContentField(): String {
        with(edit_text.text.toString()) {
            if (isNotEmpty()) {
                val initDate = DateUtils.getDateFormat().parse(this)
                val outputFormat = DateUtils.getDateOutputFormat()

                return outputFormat.format(initDate!!)
            }
        }

        return StringUtils.EMPTY
    }

}