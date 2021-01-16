package com.fiuba.bookbnb.ui.fragments.dialogs

import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.recyclerView.ContextMenuAdapter
import kotlinx.android.synthetic.main.bookbnb_context_menu_dialog_fragment.view.*
import java.io.Serializable

class ContextMenuDialogFragment : DialogBaseFragment() {

    private val navArguments by navArgs<ContextMenuDialogFragmentArgs>()
    private val title by lazy { navArguments.title }
    private val contentItemList by lazy { navArguments.contentItemList }
    private val onClickItemListener by lazy { navArguments.onClickItemListener }

    override fun getBodyView(): View? {
        val contextMenuView = View.inflate(context, R.layout.bookbnb_context_menu_dialog_fragment, null)

        with(contextMenuView) {
            context_menu_title.text = title
            context_menu_list.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = ContextMenuAdapter(contentItemList, onClickItemListener) { dismiss() }
            }
        }

        return contextMenuView
    }

    interface OnClickItemListener : Serializable {
        fun setText(title: String)
    }

}