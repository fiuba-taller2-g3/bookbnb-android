package com.fiuba.bookbnb.ui.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_date_picker_dialog_fragment.view.*
import java.io.Serializable
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePicker.OnDateChangedListener {

    private val navArguments by navArgs<DatePickerDialogFragmentArgs>()
    private val selectedDate by lazy { navArguments.selectedDate }
    private val onDateSetListener by lazy { navArguments.onDateSetListener }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context).run {
            setView(getBodyView())
            create()
        }
    }

    private fun getBodyView(): View? {
        val datePickerView = View.inflate(context, R.layout.bookbnb_date_picker_dialog_fragment, null)

        with(datePickerView) {
            date_picker.init(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH), this@DatePickerDialogFragment)
            date_picker.maxDate = Date().time
            date_picker.descendantFocusability = DatePicker.FOCUS_BLOCK_DESCENDANTS

            cancel_button.setOnClickListener {
                dismiss()
            }

            accept_button.setOnClickListener {
                val date = getDate(date_picker.year, date_picker.month, date_picker.dayOfMonth)
                onDateSetListener.onDateSet(date, targetRequestCode)
                dismiss()
            }
        }

        return datePickerView
    }

    private fun getDate(year: Int, monthOfYear: Int, dayOfMonth: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        return calendar
    }

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //Do Nothing
    }

    // The callback used to indicate the user is done filling in the date.
    interface OnDateSetListener : Serializable {
        fun onDateSet(date: Calendar, requestCode: Int)
    }
}