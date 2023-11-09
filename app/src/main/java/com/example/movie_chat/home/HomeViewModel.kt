package com.example.movie_chat.home

import com.example.movie_chat.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeNavigator>(){

    fun createRoom(){
        navigator?.gotioAddRoomScreen()

    }

    fun updateRoom(){


    }
}
