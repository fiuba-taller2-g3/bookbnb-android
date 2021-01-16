package com.fiuba.bookbnb.forms.validators

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.utils.DateUtils
import java.util.*

class DatePickerInputValidator(context: Context) : InputValidator(context) {

    override fun checkValidation(content: String): Boolean = with(content) { isNotEmpty() && isOlder(content) }

    override fun getMsgEmptyRes(): Int = R.string.date_picker_empty_msg_validation

    override fun updateTextValidation(content: String) {
        super.updateTextValidation(content)
        if (content.isNotEmpty() && !isOlder(content)) msgValidatorMutable.value = context.getString(R.string.birth_date_min_age_validation)
    }

    private fun isOlder(content: String): Boolean {
        if (content.isNotEmpty()) {
            val dateOfBirth = Calendar.getInstance()
            val today = Calendar.getInstance()
            return DateUtils.getDateFormat().parse(content)?.let { date ->
                dateOfBirth.time = date

                var age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)
                if ((today.get(Calendar.MONTH) <= dateOfBirth.get(Calendar.MONTH))
                    && (today.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH))) { age-- }

                (MIN_AGE_REQUIRED <= age)
            } ?: false
        }

        return false
    }

    companion object {
        private const val MIN_AGE_REQUIRED = 18
    }

}