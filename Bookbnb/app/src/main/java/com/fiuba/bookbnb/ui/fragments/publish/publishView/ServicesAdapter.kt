package com.fiuba.bookbnb.ui.fragments.publish.publishView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_service_dialog_item.view.*
import kotlinx.android.synthetic.main.bookbnb_service_dialog_title.view.*

class ServicesAdapter(private val serviceItems: List<ServiceItemData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TitleTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String) {
            itemView.service_title.text = title
        }
    }

    inner class ItemTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, description: String?) {
            itemView.service_name.text = name
            itemView.service_description.isVisible = description != null
            itemView.service_description.text = description
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(serviceItems[position].serviceDialogViewType) {
            ServiceDialogViewType.TITLE -> TITLE_TYPE
            else -> ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return when(viewType) {
            TITLE_TYPE -> TitleTypeViewHolder(layout.inflate(R.layout.bookbnb_service_dialog_title, parent, false))
            ITEM_TYPE -> ItemTypeViewHolder(layout.inflate(R.layout.bookbnb_service_dialog_item, parent, false))
            else -> throw Exception("Error: ViewType not found!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val serviceItem = serviceItems[position]
        when(holder) {
            is TitleTypeViewHolder -> holder.bind(serviceItem.serviceName)
            is ItemTypeViewHolder -> holder.bind(serviceItem.serviceName, serviceItem.description)
        }
    }

    override fun getItemCount(): Int = serviceItems.size

    companion object {
        private const val TITLE_TYPE = 0
        private const val ITEM_TYPE = 1
    }
}