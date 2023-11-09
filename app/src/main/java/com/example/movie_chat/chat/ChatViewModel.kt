package com.example.movie_chat.chat

import androidx.databinding.ObservableField
import com.example.movie_chat.base.BaseViewModel
import com.example.movie_chat.base.DataUtils
import com.example.movie_chat.database.addmessagestoFireStore
import com.example.movie_chat.modelclasses.Message
import com.example.movie_chat.modelclasses.Room
import java.util.*

class ChatViewModel:BaseViewModel<ChatNavigator> (){
    val messageField = ObservableField<String>()  //لما اكون عايزة استخدم حاجه من ال xml اذن هستخدم ال data binding feild دا
    var room: Room?=null // هننا بقا هياخد الداتا الي جيالي من الدوسه بتاعة ال recview واشتغل بداتا الرووم دي بقا

    fun sendMessage(){
        val message=Message(
            content = messageField.get(),
            roomId = room?.id,
            roomName = room?.name,
            senderName = DataUtils.user?.username,
            senderId = DataUtils.user?.id,
            datetime = Date().time
        )

        showLoading.value=true
        addmessagestoFireStore(message,
            onsuccessListener = {
                showLoading.value=false
                messageField.set("") // lma el add message t successd , b3ml hide llmsg mn el edittext bta3y


            },
        onfailureListener = {
            showLoading.value=false
            Message.value= it.localizedMessage
        })
        //then save the message in firestore

    }
}