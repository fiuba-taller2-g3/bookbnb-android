package com.fiuba.bookbnb.ui.fragments.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.FirebaseChat
import com.fiuba.bookbnb.FirebaseUser

class ChatsViewModel : ViewModel() {

    private val _chats = MutableLiveData<MutableList<FirebaseChat>>(mutableListOf<FirebaseChat>())
    val chats: MutableLiveData<MutableList<FirebaseChat>>
        get() = _chats
}