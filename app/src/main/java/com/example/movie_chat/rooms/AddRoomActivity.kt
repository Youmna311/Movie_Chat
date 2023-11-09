package com.example.movie_chat.rooms

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.movie_chat.R
import com.example.movie_chat.base.BaseActivity
import com.example.movie_chat.databinding.ActivityAddRoomBinding
import com.example.movie_chat.modelclasses.Item_Spinner_Categories

class AddRoomActivity :BaseActivity<ActivityAddRoomBinding,AddRoomViewModel> (), AddRoomNavigator {
    lateinit var adapter: CategorySpinnerAdapter
    val categoriesList = Item_Spinner_Categories.getCategoriesList()
    val spinnderSelectedItem = categoriesList[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.roomvm=viewModel
        viewModel.navigator=this

        viewModel.roomAdded.observe(this) {
            if (it)
                showmessageDialog("Room Added Successfully", posActionName = "OK",
                    posAction = DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        finish()
                    }, cancelable = false

                )
        }

        adapter= CategorySpinnerAdapter(categoriesList)
        viewDataBinding.roomcatSpinner.adapter = adapter
        viewDataBinding.roomcatSpinner.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                    viewModel.spinnderSelectedItem=viewModel.categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
       return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

}