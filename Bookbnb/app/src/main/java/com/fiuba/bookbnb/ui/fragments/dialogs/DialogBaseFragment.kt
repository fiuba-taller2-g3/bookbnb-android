package com.fiuba.bookbnb.ui.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

abstract class DialogBaseFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context).run {
            setView(getBodyView())
            create()
        }
    }

    protected abstract fun getBodyView() : View?

}