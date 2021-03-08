package com.fiuba.bookbnb.ui.fragments.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.*
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        var receiverId = ""
        receiverId = if (UserManager.getUserInfo().getUserData().id == com.fiuba.bookbnb.GuestAndHost.getGuest()!!) {
            com.fiuba.bookbnb.GuestAndHost.getHost()!!
        } else {
            com.fiuba.bookbnb.GuestAndHost.getGuest()!!
        }
        val call = NetworkModule.buildRetrofitClient().sendNotification(NotificationData(receiverId, "BookBnB Chat", "Tenes un nuevo mensaje"))
        executeCallback(call)
    }

    private fun executeCallback(call: Call<MsgResponse>) {
        call.enqueue(object : Callback<MsgResponse> {

            override fun onResponse(call: Call<MsgResponse>, response: Response<MsgResponse>) {
                if (response.isSuccessful) {
                    onSuccessful(response)
                } else {
                    onFailure(response)
                }
            }

            override fun onFailure(call: Call<MsgResponse>, t: Throwable) {
                if (!call.isCanceled) {
                    Log.d("Chat noti", "sendRegistrationTokenToServer(${RegistrationToken.getToken()}) with error")
                }
            }
        })
    }

    fun onSuccessful(response: Response<MsgResponse>) {
        response.body()?.let {
            Log.d("Chat noti", "todo ok")

        }
    }

    fun onFailure(response: Response<MsgResponse>) {
        Log.d("Chat noti", "error")
    }
}

object ChatVM : ChatViewModel() {
    var currentChatId = chatId
}