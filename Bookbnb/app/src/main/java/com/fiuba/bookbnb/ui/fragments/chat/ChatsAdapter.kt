package com.fiuba.bookbnb.ui.fragments.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.fiuba.bookbnb.FirebaseChat
import com.fiuba.bookbnb.GuestAndHost
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.publish.publishView.PublishViewFragmentDirections
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager
import kotlinx.android.synthetic.main.bookbnb_chat_item.view.*

class ChatsAdapter(private val dataSet: List<FirebaseChat>) : RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    inner class ChatsViewHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        (LayoutInflater.from(parent.context).inflate(R.layout.bookbnb_chat_item, parent, false) as LinearLayout)
            .also { return ChatsViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        with(holder.itemView) {
            val itemData = dataSet[position]
            GuestAndHost.setGuest(itemData.userHuespedId!!)
            GuestAndHost.setHost(itemData.userAnfitrionId!!)

            if (UserManager.getUserInfo().getUserData().name == itemData.userHuespedName) {
                title_chat_item.text = itemData.userAnfitrionName
                msg_chat_item.text = context.getString(R.string.chat_message_host)
            } else {
                title_chat_item.text = itemData.userHuespedName
                msg_chat_item.text = context.getString(R.string.chat_message_guest)
            }

            setOnClickListener {
                //NavigationManager.moveForward(ChatsFragmentDirections.actionChatsFragmentToChatFragment(itemData))
                NavigationManager.moveForward(PublishViewFragmentDirections.startChat(FirebaseChat()))
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

}