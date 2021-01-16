package com.fiuba.bookbnb.ui.fragments.form

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.adapters.FormAdapter
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import kotlinx.android.synthetic.main.bookbnb_button.*
import kotlinx.android.synthetic.main.bookbnb_form_list_fragment.*

abstract class FormListFragment : FormBaseFragment(R.layout.bookbnb_form_list_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        form_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = getFormAdapter()
        }

        button.setOnClickListener { setActionEventButton() }
    }

    override fun shouldClearInputsWhenBackPressed() = false

    protected abstract fun getFormItems() : List<FormItemData>
    protected abstract fun getFormAdapter(): FormAdapter

}