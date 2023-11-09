package com.example.movie_chat.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProvider
import com.example.movie_chat.base.BaseActivity
import com.example.movie_chat.home.HomeActivity
import com.example.movie_chat.R
import com.example.movie_chat.register.RegisterActivity
import com.example.movie_chat.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(), LoginNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.loginvm =viewModel
        viewModel.navigator= this
    }
    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun navigatetoHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun navigatetoRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
