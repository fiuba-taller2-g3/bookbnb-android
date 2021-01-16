package com.fiuba.bookbnb.ui.fragments.form.adapters

import android.view.View
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.fragments.form.data.ServicesFormItemData
import com.fiuba.bookbnb.ui.fragments.form.services.Services
import kotlinx.android.synthetic.main.bookbnb_service_checkbox.view.*

class ServicesFormAdapter(private var serviceItems: List<FormItemData>, headerFormData: HeaderFormData, private val updateItemListener: UpdateItemListener)
    : FormAdapter(serviceItems, headerFormData, R.layout.bookbnb_service_checkbox) {

    override fun bindInputView(position: Int, itemView: View) {
        val formItem = serviceItems[position] as ServicesFormItemData

        with(itemView) {
            check_item.isChecked = formItem.isChecked
            item_label.text = formItem.label
            item_description.isVisible = formItem.description != null
            item_description.text = formItem.description

            check_item.setOnClickListener {
                formItem.isChecked = check_item.isChecked
                updateItemListener.update(formItem.service, formItem.isChecked)
            }
        }
    }

    interface UpdateItemListener {
        fun update(service: Services, value: Boolean)
    }

}