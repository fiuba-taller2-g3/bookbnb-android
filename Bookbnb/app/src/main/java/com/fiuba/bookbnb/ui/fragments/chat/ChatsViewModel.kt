package com.fiuba.bookbnb.ui.fragments.chat

import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.FirebaseChat

class ChatsViewModel : ViewModel() {

    private var chats = listOf<FirebaseChat>()

    fun getChats() = chats

    fun setChats(chatList: List<FirebaseChat>) {
        chats = chatList
    }

}