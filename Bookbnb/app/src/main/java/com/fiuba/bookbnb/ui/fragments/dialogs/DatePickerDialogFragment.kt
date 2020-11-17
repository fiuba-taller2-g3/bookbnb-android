package com.fiuba.bookbnb.ui.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.date_picker_dialog_fragment.view.*
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePicker.OnDateChangedListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context).run {
            setView(getBodyView())
            create()
        }
    }

    private fun getBodyView(): View? {
        val datePickerView = View.inflate(context, R.layout.date_picker_dialog_fragment, null)
        val calendar = Calendar.getInstance()

        with(datePickerView) {
            date_picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this@DatePickerDialogFragment)
            date_picker.maxDate = Date().time
            date_picker.descendantFocusability = DatePicker.FOCUS_BLOCK_DESCENDANTS
        }

        return datePickerView
    }

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //Do Nothing
    }
}