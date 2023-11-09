package com.example.movie_chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.example.movie_chat.base.DataUtils
import com.example.movie_chat.database.signinwithUser
import com.example.movie_chat.home.HomeActivity
import com.example.movie_chat.login.LoginActivity
import com.example.movie_chat.modelclasses.AppUser
import com.example.movie_chat.register.RegisterActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
//     var x1: Float = 0.toFloat()
//     var x2: Float = 0.toFloat()
//     var y1: Float = 0.toFloat()
//     var y2: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoggedUser()},2000)
    }

    private fun checkLoggedUser() {
        // كدا لو انا مش عامله لوجين قبل كدا هيفتحلي صفحه اللوجين
        val firbaseuser = Firebase.auth.currentUser
        if (firbaseuser == null) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)


        } else {
            // دا معناه انى عامله لوجين قبل كدا وفي يوزر مفتوح ان هعرف ال يوزر دا مين واسجله معايا وافتح على الهووم اسكرين
            signinwithUser(firbaseuser.uid, onsuccessListener = {
                val currentuser = it.toObject(AppUser::class.java)
                DataUtils.user = currentuser

                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)

            }, onfailureListener = {
                Toast.makeText(this , it.localizedMessage, Toast.LENGTH_LONG).show()
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
            })
        }
    }

//    override fun onTouchEvent(tochevent: MotionEvent): Boolean {
//        when (tochevent.action) {
//            MotionEvent.ACTION_DOWN -> {
//                x1 = tochevent.x
//                y1 = tochevent.y
//            }
//            MotionEvent.ACTION_UP -> {
//                x2 = tochevent.x
//                y2 = tochevent.y
//                if (x1 >x2) {  //right swap
//                    val i = Intent(this, LoginActivity::class.java)
//                    startActivity(i)
//                }
//
//            }
//        }
//        return false
//    }

}