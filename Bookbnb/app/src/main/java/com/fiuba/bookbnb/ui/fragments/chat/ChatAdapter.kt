package com.fiuba.bookbnb.ui.fragments.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.FirebaseChatMessage
import com.fiuba.bookbnb.R
import kotlinx.android.synthetic.main.bookbnb_chat_item.view.*

class ChatAdapter(private val dataSet: MutableList<FirebaseChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    inner class ChatViewHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_chat_item, parent, false) as LinearLayout)
            .also { return ChatViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        with(holder.itemView) {
            val itemData = dataSet[position]
            title_chat_item.text = itemData.getMsgTitle()
            msg_chat_item.text = itemData.message
        }
    }

    override fun getItemCount(): Int = dataSet.size
}