package com.fiuba.bookbnb.ui.fragments.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.*
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.user.UserManager
import com.fiuba.bookbnb.utils.notifyObserver
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ChatViewModel : ViewModel() {

    val chatId: String = FirebaseDBService().getChatId(GuestAndHost.getGuest()!!, GuestAndHost.getHost()!!)

    private val _messageText = MutableLiveData("")
    val messageText: MutableLiveData<String>
        get() = _messageText

    private val mutableMessagesLiveData = MutableLiveData(mutableListOf<FirebaseChatMessage>())
    val messagesLiveData: LiveData<MutableList<FirebaseChatMessage>>
        get() = mutableMessagesLiveData

    fun onSendClick(){
        FirebaseDBService().saveMessage(chatId, GuestAndHost.getGuest()!!, UserManager.getUserInfo().getUserData().name, messageText.value!!)
        _messageText.value = StringUtils.EMPTY

        val receiverId = if (UserManager.getUserInfo().getUserData().id == GuestAndHost.getGuest()!!) GuestAndHost.getHost()!!
        else GuestAndHost.getGuest()!!

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

    fun notifyObserver() = mutableMessagesLiveData.notifyObserver()

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