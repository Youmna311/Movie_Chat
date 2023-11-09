package com.example.movie_chat.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N>:ViewModel() {

    // bma en momken akter mn activity use nfs el 2 live data dol so we make a general view midel and make a specific view model extend it
    val showLoading = MutableLiveData<Boolean>()
    val Message= MutableLiveData<String>()
    // w ana f el view model 3yza a3rf el activity enha t navigate l activity tanya w da momken y7sl been ay 2 activity
    // f hn3ml f el baseviewmodel enha bta5od Template N w kda ay haga use baseviewmodel h3mlha navigator interface
    // (kda l kol viewmodel uses baseview hykon leha el navigator bta3ha).
    var navigator:N?=null




}