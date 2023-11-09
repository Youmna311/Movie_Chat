package com.example.movie_chat.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.movie_chat.base.BaseActivity
import com.example.movie_chat.home.HomeActivity
import com.example.movie_chat.R
import com.example.movie_chat.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() , RegisterNavigator {
//    var x1: Float = 0.toFloat()
//    var x2: Float = 0.toFloat()
//    var y1: Float = 0.toFloat()
//    var y2: Float = 0.toFloat()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        // kda hena ican use any obj (viewmodel / viewdatabinding ) that exist in base activity directly
        // kda ana 3yza a link el vm variable elly f el activity_register xml b el viewmodel obj elly f el code
        // kda vm = viewModel ( el 2 object mn no3 register viewmodel
        viewModel.navigator= this

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


    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)

    }
    override fun navigatetoHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}