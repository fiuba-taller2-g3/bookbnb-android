package com.fiuba.bookbnb.ui.fragments.form.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.fragments.form.data.InputViewType
import kotlinx.android.synthetic.main.bookbnb_form_header.view.*

abstract class FormAdapter(private val formItems: List<FormItemData>,
                           private val headerFormData: HeaderFormData,
                           @LayoutRes private val layoutInputRes: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HeaderTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String, subtitle: String?) {
            itemView.bkbnb_form_title.text = title
            itemView.bkbnb_form_subtitle.isVisible = subtitle != null
            subtitle?.let { itemView.bkbnb_form_subtitle.text = it }
        }
    }

    inner class InputFieldTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) = bindInputView(position, itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return when(formItems[position].inputViewType) {
            InputViewType.HEADER -> HEADER_TYPE
            else -> INPUT_FIELD_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return when(viewType) {
            INPUT_FIELD_TYPE -> InputFieldTypeViewHolder(layout.inflate(layoutInputRes, parent, false))
            HEADER_TYPE -> HeaderTypeViewHolder(layout.inflate(R.layout.bookbnb_form_header, parent, false))
            else -> throw Exception("Error: ViewType not found!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is InputFieldTypeViewHolder -> holder.bind(position)
            is HeaderTypeViewHolder -> holder.bind(headerFormData.title, headerFormData.subtitle)
        }
    }

    override fun getItemCount(): Int = formItems.size

    protected abstract fun bindInputView(position: Int, itemView: View)

    companion object {
        private const val HEADER_TYPE = 0
        private const val INPUT_FIELD_TYPE = 1
    }

}