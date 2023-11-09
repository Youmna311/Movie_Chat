package com.example.movie_chat.modelclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(

    var id:String?=null,
    var name:String?=null,
    var description: String?=null,
    var categoryId :String?=null
) :Parcelable


{
    fun getImageofCategoryId():Int?{

        return Item_Spinner_Categories.createCategoryfromId(categoryId?:Item_Spinner_Categories.MOVIES).imageid
    }
    companion object{
        val COLLECTION_NAME= "rooms"
    }
}
