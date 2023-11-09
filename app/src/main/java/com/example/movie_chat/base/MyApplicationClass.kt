package com.example.movie_chat.base

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MyApplicationClass:Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}