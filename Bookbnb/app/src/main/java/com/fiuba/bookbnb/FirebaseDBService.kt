package com.fiuba.bookbnb

import android.util.Log
import com.fiuba.bookbnb.user.UserManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class FirebaseDBService {
    private var database: DatabaseReference = Firebase.database.reference
    private val TAG = "FirebaseDBService"
    private val USERS_KEY: String = "users"
    private val CHATS_KEY: String = "chats"
    private val MESSAGES_KEY: String = "messages"
    private val CHAT_ID_DELIMITER = "_"

    fun createUserIfNotExists() {
        val user = FirebaseUser(UserManager.getUserInfo().getUserData().name, UserManager.getUserInfo().getUserData().email)
        database.child(USERS_KEY).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.child(UserManager.getUserInfo().getUserId()).exists()) {
                    database.child(USERS_KEY).child(UserManager.getUserInfo().getUserId()).setValue(user)
                            .addOnSuccessListener {
                                Log.d(TAG, "Se creó el usuario")
                            }
                            .addOnFailureListener {
                                Log.d(TAG, "Error creando el usuario")
                            }
                }
            }
        })
    }

    fun updateChat(
        guestUserId: String,
        hostUserId: String,
        onSuccess: () -> Unit
    ){
        database.child(USERS_KEY).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val guest = dataSnapshot.child(guestUserId).getValue(FirebaseUser::class.java)
                val host = dataSnapshot.child(hostUserId).getValue(FirebaseUser::class.java)
                val chatId = getChatId(guestUserId, hostUserId)
                val chat = FirebaseChat(
                        chatId,
                        guest?.username,
                        host?.username,
                        guestUserId,
                        hostUserId
                )
                database.child(CHATS_KEY).child(chatId).setValue(chat)
                onSuccess()
            }
        })
    }

    fun saveMessage(chatId: String, senderId: String, senderName: String, msg: String){
        val chatMsg = FirebaseChatMessage(chatId, senderId, senderName, msg)
        database.child(MESSAGES_KEY).child(chatId).push().setValue(chatMsg);
    }

    fun getChatId(huespedUserId: String, anfitrionUserId: String) : String{
        return "${huespedUserId}$CHAT_ID_DELIMITER$anfitrionUserId"
    }

    fun getUserIdsFromChatId(chatId: String) : List<String> {
        return chatId.split(CHAT_ID_DELIMITER)
    }
}

@IgnoreExtraProperties
data class FirebaseUser(val username: String? = null, val email: String? = null)

data class FirebaseChat (
        val chatId: String? = "",
        val userHuespedName: String? = "",
        val userAnfitrionName: String? = "",
        val userHuespedId: String? = "",
        val userAnfitrionId: String? = ""
) : Serializable

data class FirebaseChatMessage(
        val chatId: String = "",
        val senderId: String = "",
        val senderName: String = "",
        val message: String = "",
        var timestamp: HashMap<String, Any> = HashMap()
) {
    init {
        timestamp["time"] = ServerValue.TIMESTAMP
    }

    fun getMsgTitle() : String{
        var date: Date = Date()
        val timestamp = timestamp["time"]
        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US)
        if (timestamp is Long){
            date = Date(timestamp)
        }
        return "$senderName (${dateFormat.format(date)}):";
    }
}