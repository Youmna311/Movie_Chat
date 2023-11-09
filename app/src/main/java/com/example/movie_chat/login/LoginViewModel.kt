package com.example.movie_chat.login

import android.util.Log
import androidx.databinding.ObservableField
import com.example.movie_chat.base.BaseViewModel
import com.example.movie_chat.base.DataUtils
import com.example.movie_chat.modelclasses.AppUser
import com.example.movie_chat.database.signinwithUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:BaseViewModel<LoginNavigator>() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val emailError =ObservableField<String>()
    val passwordError =ObservableField<String>()

    val loginauth= Firebase.auth

    fun login()
    {
        if(validate())
            signinwithFirebase()
    }
    private fun signinwithFirebase() {
        showLoading.value=true
        loginauth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {
                showLoading.value=false
                if (it.isSuccessful)
                {
                    Message.value= "Login is succeeded"
                    ceckUserFromFirstore(it.result.user?.uid)
//                    navigator?.navigatetoHomeScreen()
                    //kda el login activity hya elly ht3ml implement ll interface so hya elly ht3ml implement ll function
                }
                else
                {
                    Message.value=  it.exception?.localizedMessage
                }
            }
    }

    private fun ceckUserFromFirstore(uid: String?) {
        showLoading.value=true
        signinwithUser(uid!!, onsuccessListener = {

            showLoading.value=false
            val user = it.toObject(AppUser::class.java)
            if (user==null)
            {
                Message.value=  "Invalid Mail or Password"
                return@signinwithUser

            }
            else
            {
                Log.e("loginviewmodel",it.data.toString())
                DataUtils.user= user
                navigator?.navigatetoHomeScreen()
            }





        }, onfailureListener = {
            showLoading.value=false
            Message.value=  it.localizedMessage
        })

    }

    private fun validate(): Boolean  {
        var valid = true
        if(email.get().isNullOrBlank())
        {
            emailError.set("Enter Valid Mail")
            valid= false

        }
        else
        {
            emailError.set(null)
        }
        if(password.get().isNullOrBlank())
        {
            passwordError.set("Enter Valid Password")
            valid= false

        }
        else
        {
            passwordError.set(null)
        }

        return valid


    }
}