package com.fiuba.bookbnb.ui.fragments.dialogs

import android.view.View
import android.widget.LinearLayout
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.FormInputData
import kotlinx.android.synthetic.main.bookbnb_abstract_dialog_fragment.view.*
import kotlinx.android.synthetic.main.bookbnb_form_fragment.*

abstract class AbstractDialogFragment : DialogBaseFragment() {

    override fun getBodyView(): View? {
        val dialogView = View.inflate(context, R.layout.bookbnb_abstract_dialog_fragment, null)

        with(dialogView) {
            dialog_title.text = getString(getDialogTitleRes())
            getItemList().forEach { itemView ->
                dialog_container.addView(itemView)
            }
            dialog_container.addView(DialogFooterItem(context).also { footer ->
                footer.setCancelClickListener { dismiss() }
                footer.setAcceptClickListener { setAcceptFunction() }
            })
        }

        return dialogView
    }

    protected abstract fun getDialogTitleRes() : Int

    protected abstract fun getItemList() : List<LinearLayout>

    protected abstract fun setAcceptFunction()

}