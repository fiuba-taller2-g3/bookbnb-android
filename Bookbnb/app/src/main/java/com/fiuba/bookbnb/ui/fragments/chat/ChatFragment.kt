package com.fiuba.bookbnb.ui.fragments.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.*
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.databinding.BookbnbChatBindingImpl
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.bookbnb_chat.*

class ChatFragment : BaseFragment(R.layout.bookbnb_chat) {

    private lateinit var childMessagesReference: DatabaseReference
    private var childListener: ChildEventListener? = null
    private val chatViewModel by viewModels<ChatViewModel>()
    private lateinit var binding: BookbnbChatBindingImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bookbnb_chat, container, false)
        binding.lifecycleOwner = this
        binding.chatViewModel = chatViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.chatViewModel = chatViewModel

        messageRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            chatViewModel.messagesLiveData.value!!.let { results ->
                adapter = ChatAdapter(results)
                isVisible = results.isNotEmpty()
            }
        }

        if (chatViewModel.messagesLiveData.value!!.isNotEmpty()) {
            messageRecyclerView.smoothScrollToPosition((chatViewModel.messagesLiveData.value!!.size.minus(1)))
        }

        val firebaseDbSvc = FirebaseDBService()
        firebaseDbSvc.updateChat(GuestAndHost.getGuest()!!, GuestAndHost.getHost()!!, this::updateFragmentTitle)
        addFirebaseListeners()
    }

    private fun updateFragmentTitle(){
        Firebase.database.reference.child("chats").child(ChatVM.currentChatId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    (requireActivity() as? AppCompatActivity)?.supportActionBar?.title =
                        dataSnapshot.getValue<FirebaseChat>()?.userHuespedName
                }
            })
    }

    private fun addFirebaseListeners() {
        childMessagesReference =
            Firebase.database.reference.child("messages").child(ChatVM.currentChatId)

        val childMessagesListener = object : ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                if (!chatViewModel.messagesLiveData.value!!.contains(dataSnapshot.getValue<FirebaseChatMessage>()!!)) {
                    chatViewModel.messagesLiveData.value!!.add(dataSnapshot.getValue<FirebaseChatMessage>()!!)
                }

                messageRecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    chatViewModel.messagesLiveData.value!!.let { results ->
                        adapter = ChatAdapter(results)
                        isVisible = results.isNotEmpty()
                    }
                }

                if (chatViewModel.messagesLiveData.value!!.isNotEmpty()) {
                    messageRecyclerView.smoothScrollToPosition((chatViewModel.messagesLiveData.value!!.size.minus(1)))
                }

                chatViewModel.notifyObserver()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

        }

        if (chatViewModel.messagesLiveData.value!!.isNotEmpty()) {
            messageRecyclerView.smoothScrollToPosition((chatViewModel.messagesLiveData.value?.size?.minus(1)!!))
        }

        childMessagesReference.addChildEventListener(childMessagesListener)
        this.childListener = childMessagesListener
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun onStop() {
        super.onStop()
        childListener?.let {
            childMessagesReference.removeEventListener(it)
        }
    }
}