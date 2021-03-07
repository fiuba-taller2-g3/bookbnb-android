package com.fiuba.bookbnb.ui.fragments.chat

import com.fiuba.bookbnb.FirebaseChat

class ChatClickListener(val clickListener: (chatId: String) -> Unit) {
    fun onClick(chat: FirebaseChat) = clickListener(chat.chatId!!)
}