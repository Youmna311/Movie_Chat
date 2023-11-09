package com.example.movie_chat.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_chat.R
import com.example.movie_chat.databinding.ItemSpinnerCategoryBinding
import com.example.movie_chat.databinding.RoomlistitemsRecviewBinding
import com.example.movie_chat.modelclasses.Item_Spinner_Categories
import com.example.movie_chat.modelclasses.Room

class RoomAdapter(var listofRooms: List<Room?>?):RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    class ViewHolder (val viewDataBinding : RoomlistitemsRecviewBinding):RecyclerView.ViewHolder(viewDataBinding.root){
        fun bind(room :Room?){
            viewDataBinding.item = room
            viewDataBinding.invalidateAll()
        }

    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder { //container == parent
        val viewDataBinding : RoomlistitemsRecviewBinding = DataBindingUtil.inflate(LayoutInflater.from(container.context)
            ,R.layout.roomlistitems_recview,
            container,false)
        return ViewHolder(viewDataBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listofRooms!![position])
        onroomclick?.let {
            // lw not null , ezn e3ml set ll clicklistent bta3 el view elly 3mlt 3leh click
//            holder.viewDataBinding.root // refrence 3l el view bta3 el item elly 3mlt 3leh click ( de kda b el databinding way)
            holder.itemView.setOnClickListener {
                onroomclick?.onRoomClick(position,listofRooms!![position]!!)
            }

        }
    }

    override fun getItemCount(): Int {
        return listofRooms?.size ?: 0
    }

    var onroomclick : OnRoomclicklistenr?=null
    interface OnRoomclicklistenr{
        fun onRoomClick(position: Int , room: Room)

    }

    fun changeData(rooms: List<Room>) {
        listofRooms = rooms
        notifyDataSetChanged()

    }
}