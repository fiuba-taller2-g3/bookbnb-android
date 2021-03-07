package com.fiuba.bookbnb.ui.fragments.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.FirebaseChat
import com.fiuba.bookbnb.GuestAndHost
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.databinding.BookbnbChatListBindingImpl
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.bookbnb_chat_list.*


class ChatsFragment : BaseFragment(R.layout.bookbnb_chat_list) {

    private val chatsViewModel by activityViewModels<ChatsViewModel>()
    private lateinit var binding: BookbnbChatListBindingImpl
    private lateinit var chatsReference: Query
    private lateinit var chatsListener: ValueEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bookbnb_chat_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.chatsViewModel = chatsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*chats_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            chatsViewModel.chats.let { chats ->
                adapter = ChatsAdapter(chats.value!!)
                isVisible = chats.value!!.isNotEmpty()
            }
        }*/

        empty_chats_text.isVisible = !chats_container.isVisible
        val userId = com.fiuba.bookbnb.user.UserManager.getUserInfo().getUserId()

        val child = if (userId == GuestAndHost.getGuest()) "userHuespedId" else "userAnfitrionId"

        chatsReference =
            Firebase.database.reference
                .child("chats").orderByChild(child).startAt(userId).endAt(userId + "\uf8ff")
        val chatsListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val chats = dataSnapshot.getValue<HashMap<String, FirebaseChat>>()
                val mappedChats = chats?.map { c ->
                    FirebaseChat(c.value.chatId, c.value.userHuespedName, c.value.userAnfitrionName, c.value.userHuespedId, c.value.userAnfitrionId)
                }
                // TODO: Replace with child event listener to avoid getting all the data everytime
                if (chatsViewModel.chats.value!! != mappedChats) {
                    chatsViewModel.chats.value = mappedChats as MutableList<FirebaseChat>?
                    binding.chatsViewModel = chatsViewModel

                    chats_container.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        chatsViewModel.chats.let { chats ->
                            adapter = ChatsAdapter(chats.value!!)
                            isVisible = chats.value!!.isNotEmpty()
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // [START_EXCLUDE]
                Toast.makeText(requireContext(), "Failed to load chats.",
                    Toast.LENGTH_SHORT).show()
                // [END_EXCLUDE]
            }
        }

        chats_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            chatsViewModel.chats.let { chats ->
                adapter = ChatsAdapter(chats.value!!)
                isVisible = chats.value!!.isNotEmpty()
            }
        }

        chatsReference.addValueEventListener(chatsListener)

        this.chatsListener = chatsListener
    }

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    override fun onStop() {
        super.onStop()

        // Remove post value event listener
        chatsListener?.let {
            chatsReference.removeEventListener(it)
        }

    }
}