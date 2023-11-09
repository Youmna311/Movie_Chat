package com.example.movie_chat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>>:AppCompatActivity (){
    lateinit var viewDataBinding: DB
    lateinit var viewModel:VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding= DataBindingUtil.setContentView(this,getLayout())
        viewModel= initViewModel()
        subscribrToLiveData()
    }
    private fun subscribrToLiveData()
    {
        viewModel.Message.observe(this) {
            showmessageDialog(it,"OK")
        }
        viewModel.showLoading.observe(this) {
            if(it) showloadinDialog()
            else hideloadingDialog()

        }
    }
    var alertDialog:AlertDialog?=null
    var progressDialog:ProgressDialog?=null

    fun showmessageDialog(message:String,
                   posActionName: String?=null, posAction:DialogInterface.OnClickListener?=null,
                negActionName: String?=null, negAction:DialogInterface.OnClickListener?=null,
                   cancelable:Boolean=true){
        val Builder = AlertDialog.Builder(this).setMessage(message)
        val defualtAction= DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() }


        if (posActionName!=null){
            Builder.setPositiveButton(posActionName,
                posAction ?: defualtAction //posaction is not null is use it else use defualt
            )
        }

        if (negActionName!=null){
            Builder.setNegativeButton(negActionName,
                negAction ?: defualtAction //posaction is not null is use it else use defualt
            )

        }
        Builder.setCancelable(cancelable)

        alertDialog= Builder.show()



    }

    fun hidemessageDialog(){
        alertDialog?.dismiss()
        alertDialog=null
    }

    fun showloadinDialog(){

        progressDialog=ProgressDialog(this)
        progressDialog?.setMessage("Loading ....")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }
    fun hideloadingDialog(){
        progressDialog?.dismiss()
        progressDialog=null
    }

    abstract fun getLayout():Int
    abstract fun initViewModel() :VM
}