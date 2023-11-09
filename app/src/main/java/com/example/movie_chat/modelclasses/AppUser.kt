package com.example.movie_chat.modelclasses

data class AppUser (
    var id :String?=null,
    var firstname :String?=null,
    var lastname :String?=null,
    var username :String?=null,
    var email :String?=null)

{
    companion object{

        const val COLLECTION_NAME="users"
    }
}