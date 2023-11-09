package com.example.movie_chat.register

import android.util.Log
import androidx.databinding.ObservableField
import com.example.movie_chat.base.BaseViewModel
import com.example.movie_chat.base.DataUtils
import com.example.movie_chat.modelclasses.AppUser
import com.example.movie_chat.database.addusertoFireStore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterViewModel :BaseViewModel<RegisterNavigator>() {

    val firstname =ObservableField<String>()
    val lastname =ObservableField<String>()
    val username =ObservableField<String>()
    val email =ObservableField<String>()
    val password =ObservableField<String>()

    val firstnameError =ObservableField<String>()
    val lastnameError =ObservableField<String>()
    val usernameError =ObservableField<String>()
    val emailError =ObservableField<String>()
    val passwordError =ObservableField<String>()



    val auth= Firebase.auth

    fun createaccount(){
        if(validate()){
            addAccountToFirebase()

        }

    }

    private fun addAccountToFirebase() {
        showLoading.value=true
        auth.createUserWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {
                showLoading.value=false
                if (it.isSuccessful)
                {
                    Message.value= "Registration is succeeded"
                    createFireStoreUser(it.result.user?.uid)
                }
                else
                {
                    Message.value=  it.exception?.localizedMessage
                }




            }
    }

    private fun createFireStoreUser(uid: String?) {
        showLoading.value=true
        val user = AppUser(uid,firstname.get(),lastname.get(),username.get(),email.get())
        addusertoFireStore(user,
            onsuccessListener = {
                showLoading.value=false
                DataUtils.user= user

                navigator?.navigatetoHomeScreen()
                Log.e("Firestore","done")
            },
        onfailureListener = {
                 showLoading.value=false
                 Message.value=  it.localizedMessage
                Log.e("Firestore","fail")


        })

    }

    private fun validate(): Boolean  {
        var valid = true

        if(firstname.get().isNullOrBlank())
        {
            firstnameError.set("Enter Valid FirstName")
            valid= false
        }
        else
        {
            firstnameError.set(null)
        }


        if(lastname.get().isNullOrBlank())
        {
            lastnameError.set("Enter Valid LastName")
            valid= false

        }
        else
        {
            lastnameError.set(null)
        }
        if(username.get().isNullOrBlank())
        {
            usernameError.set("Enter Valid UserName")
            valid= false

        }
        else
        {
            usernameError.set(null)
        }
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