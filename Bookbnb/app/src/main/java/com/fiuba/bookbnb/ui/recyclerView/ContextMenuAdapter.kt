package com.fiuba.bookbnb.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.dialogs.ContextMenuDialogFragment
import kotlinx.android.synthetic.main.bookbnb_context_menu_dialog_item.view.*

class ContextMenuAdapter(private val dataSet: Array<ContextMenuItemData>,
                         private val onClickItemListener: ContextMenuDialogFragment.OnClickItemListener,
                         private val closeDialog: () -> Unit)
    : RecyclerView.Adapter<ContextMenuAdapter.ContextMenuViewHolder>() {

    inner class ContextMenuViewHolder(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContextMenuViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_context_menu_dialog_item, parent, false) as ConstraintLayout)
            .also { return ContextMenuViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ContextMenuViewHolder, position: Int) {
        with(holder.itemView) {
            val itemData = dataSet[position]
            item_text.text = itemData.title
            itemData.subtitle?.let {
                item_description.text = itemData.subtitle
                item_description.isVisible = true
            }
            item_container.setOnClickListener {
                onClickItemListener.setText(itemData.title)
                closeDialog()
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

}