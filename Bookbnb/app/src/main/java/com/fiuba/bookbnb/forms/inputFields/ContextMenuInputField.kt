package com.fiuba.bookbnb.forms.inputFields

import android.content.Context
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.validators.InputValidator
import com.fiuba.bookbnb.ui.fragments.dialogs.ContextMenuDialogFragment
import com.fiuba.bookbnb.ui.fragments.dialogs.ContextMenuDialogFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.ui.recyclerView.ContextMenuItemData
import kotlinx.android.synthetic.main.bookbnb_text_input_field_item.view.*

class ContextMenuInputField(context: Context,
                            inputData: FormInputData,
                            storeInputContent: (formInputData: FormInputData) -> Unit,
                            private val titleDialog: String,
                            private val contentItemList: Array<ContextMenuItemData>,
                            validation: InputValidator)
    : EditTextAbstractInputField(context, inputData, storeInputContent, validation), ContextMenuDialogFragment.OnClickItemListener {

    init {
        edit_text.isFocusable = false
        edit_text.isLongClickable = false
        edit_text.setTextIsSelectable(false)
        checkInitContent(inputData.content)
        inputClickListener()
    }

    private fun inputClickListener() {
        edit_text.setOnClickListener {
            NavigationManager.showDialog {
                ContextMenuDialogFragmentDirections.showContextMenuDialogFragment(titleDialog, contentItemList, this)
            }
        }
    }

    private fun checkInitContent(content: String) {
        val initContent = if (contentItemList.any { it.title == content }) content else contentItemList.first().title
        edit_text.setText(initContent)
    }

    override fun setText(title: String) = edit_text.setText(title)

}