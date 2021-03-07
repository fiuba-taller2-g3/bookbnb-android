package com.fiuba.bookbnb.ui.fragments.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.FirebaseChat
import com.fiuba.bookbnb.FirebaseChatMessage
import com.fiuba.bookbnb.FirebaseDBService
import com.fiuba.bookbnb.user.UserManager

open class ChatViewModel() : ViewModel() {
    val chatId: String = FirebaseDBService().getChatId(com.fiuba.bookbnb.GuestAndHost.getGuest()!!,
        com.fiuba.bookbnb.GuestAndHost.getHost()!!
    )

    private val _messageText = MutableLiveData<String>("")
    val messageText: MutableLiveData<String>
        get() = _messageText

    private val _messages = MutableLiveData<MutableList<FirebaseChatMessage>>(mutableListOf<FirebaseChatMessage>())
    val messages: MutableLiveData<MutableList<FirebaseChatMessage>>
        get() = _messages

    fun onSendClick(){
        FirebaseDBService().saveMessage(
            chatId,
            com.fiuba.bookbnb.GuestAndHost.getGuest()!!,
            UserManager.getUserInfo().getUserData().name,
            messageText.value!!)
        _messageText.value = ""
    }
}

object ChatVM : ChatViewModel() {
    var currentChatId = chatId
}