package com.example.movie_chat.chat
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_chat.R
import com.example.movie_chat.base.BaseActivity
import com.example.movie_chat.database.getMessageRef
import com.example.movie_chat.databinding.ActivityChatBinding
import com.example.movie_chat.modelclasses.Constants
import com.example.movie_chat.modelclasses.Message
import com.example.movie_chat.modelclasses.Room
import com.google.firebase.firestore.DocumentChange.Type.*
import com.google.firebase.firestore.Query

class ChatActivity : BaseActivity<ActivityChatBinding,ChatViewModel>(),ChatNavigator {

    lateinit var room: Room
    val adapter = MessagesAdapter()
    lateinit var layoutManager :LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        room = intent.getParcelableExtra<Room>(Constants.Extra_Room)!! // receive the room data that send from home activity
        viewDataBinding.chatvm = viewModel
        viewModel.room = room // كدا الداتا الي جايالي من الدوسه علي ال recview ببعتها للروم الى ف ال فيو مودل عشان املى الداتا بتاعتها
        viewModel.navigator = this
        layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd =true
        viewDataBinding.messagesrecview.layoutManager = layoutManager

        viewDataBinding.messagesrecview.adapter = adapter
        listenforMessagesUpdate()
    }

    fun listenforMessagesUpdate() {

        getMessageRef(room.id!!).orderBy("datetime",Query.Direction.ASCENDING)

            .addSnapshotListener { snapshot, exception ->

            if (exception != null)
                Toast.makeText(this, "Cannot Retrive Messages ", Toast.LENGTH_LONG).show()
            else {
                val newmessages = mutableListOf<Message>()
                for (dc in snapshot!!.documentChanges) {
                    if (dc.type == ADDED) {
                        val messagesupdates = dc.document.toObject(Message::class.java)
                        newmessages.add(messagesupdates)

                    }
                }
                adapter.appendmessages(newmessages)
                viewDataBinding.messagesrecview.smoothScrollToPosition(adapter.itemCount)




            }
        }
    }
    override fun getLayout(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this).get(ChatViewModel::class.java)
    }
}