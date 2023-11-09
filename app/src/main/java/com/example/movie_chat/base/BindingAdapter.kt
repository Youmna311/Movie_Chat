package com.example.movie_chat

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setError")
fun setError(textInputLayout: TextInputLayout , errormsg:String?){
        textInputLayout.error=errormsg
}

@BindingAdapter("setImageSource")
fun setImaage(imageView: ImageView , imageId: Int){
        imageView.setImageResource(imageId)
}