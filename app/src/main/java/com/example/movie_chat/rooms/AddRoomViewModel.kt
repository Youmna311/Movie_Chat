package com.example.movie_chat.rooms

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.movie_chat.base.BaseViewModel
import com.example.movie_chat.database.addroomtoFireStore
import com.example.movie_chat.modelclasses.Item_Spinner_Categories
import com.example.movie_chat.modelclasses.Room

class AddRoomViewModel:BaseViewModel<AddRoomNavigator> (){

    val roomName =ObservableField<String>()
    val roomDesc =ObservableField<String>()
    val roomDescError =ObservableField<String>()
    val roomNameError =ObservableField<String>()
    val roomAdded =MutableLiveData<Boolean>()

    var categories = Item_Spinner_Categories.getCategoriesList()
    var spinnderSelectedItem = categories[0]


    fun createRoom(){
        showLoading.value=true
        if (validate())
        {
            val room = Room(name = roomName.get(), description = roomDesc.get(), categoryId = spinnderSelectedItem.id)
            addroomtoFireStore(room ,
            onsuccessListener = {
                showLoading.value=false
                roomAdded.value = true
                //nfs el modo3 lw 3mlt :
                  // 1) Message.value= "Room Added Succssfully"
//                   2) navigator?.navigatetoHomeScreen()

            },
            onfailureListener = {
                showLoading.value=false
                Message.value=it.localizedMessage
            })

        }
        showLoading.value=false



    }

    private fun validate(): Boolean  {
        var valid = true

        if(roomName.get().isNullOrBlank())
        {
            Log.e("roomviewmodel", roomName.get()?:"nothing")
            roomNameError.set("Enter Valid Room Name")
            valid= false

        }
        else
        {
            roomNameError.set(null)
        }
        if(roomDesc.get().isNullOrBlank())
        {
            roomDescError.set("Enter Valid Room Description")
            valid= false

        }
        else
        {
            roomDescError.set(null)
        }

        return valid


    }




}