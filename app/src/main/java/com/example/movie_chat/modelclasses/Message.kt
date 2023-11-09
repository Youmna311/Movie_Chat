package com.example.movie_chat.modelclasses

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Message (
    var id :String?=null,
    var content: String?=null,
    var datetime :Long?=null,
    var senderName :String?=null,
    var senderId :String?=null,
    var roomId :String?=null,
    var roomName :String?=null

){

    companion object{
        const val COLLECTION_NAME="Messages"

    }

    fun DateTimeFormat() :String{

        val date= Date(datetime!!)
        val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}
