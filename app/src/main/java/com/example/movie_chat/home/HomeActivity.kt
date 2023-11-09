package com.example.movie_chat.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.movie_chat.base.BaseActivity
import com.example.movie_chat.R
import com.example.movie_chat.chat.ChatActivity
import com.example.movie_chat.database.getroomFromFireStore
import com.example.movie_chat.databinding.ActivityHomeBinding
import com.example.movie_chat.modelclasses.Constants
import com.example.movie_chat.modelclasses.Room
import com.example.movie_chat.rooms.AddRoomActivity
import com.example.movie_chat.rooms.RoomAdapter

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>() , HomeNavigator{
     val adapter= RoomAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.homevm=viewModel
        viewModel.navigator=this
        subscribetoLiveData() ////////////
        initRecView()



    }
    fun subscribetoLiveData(){

    }
    fun initRecView(){
        adapter.onroomclick= object : RoomAdapter.OnRoomclicklistenr{
            override fun onRoomClick(position: Int, room: Room) {
                startChatActivity(room)
            }

        }
        viewDataBinding.recyclerView.adapter = adapter
    }

    private fun startChatActivity(room: Room) {
        val  intent :Intent= Intent(this,ChatActivity::class.java)
        intent.putExtra(Constants.Extra_Room , room)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        getroomFromFireStore(onsuccessListener =
        {
//          ana rag3ly mutiple document rooms of collection f h loop 3la doc doc w a7welo l
//          obj mn no3 room w a7to g list w de el ist elly hb3tha ll adapter
//            val roomlist = mutableListOf<Room?>()
//            it.documents.forEach {doc->
//                // hena h loop 3la document document w a7welo l object mn no3 room
//                val room = doc.toObject(Room::class.java)
//                roomlist.add(room)

            // aw kont momken acess el rooms 3la tol mn el  collection :
            val rooms = it.toObjects(Room::class.java)
            adapter.changeData(rooms)

        }, onfailureListener = {
                Toast.makeText(this , it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        )
    }

    override fun getLayout(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun gotioAddRoomScreen() {
        val intent = Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }

}

