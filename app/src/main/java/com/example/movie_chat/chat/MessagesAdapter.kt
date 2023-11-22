package com.example.movie_chat.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_chat.R
import com.example.movie_chat.base.DataUtils
import com.example.movie_chat.databinding.ItemReciveMessageBinding
import com.example.movie_chat.databinding.ItemSendMessageBinding
import com.example.movie_chat.modelclasses.Message
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// ليها 2 xml عشان المفروض هيعرض send xml / recive xml
// 2 xml ezn 2 view holder , ezn f el adapter extend m4 h7dded any view holder , hkteb el parent view holder : [ RecyclerView.Adapter<RecyclerView.ViewHolder>()]
class MessagesAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var messageitems = mutableListOf<Message?>()
    class sendmessageViewHolder(val sendviewDataBinding: ItemSendMessageBinding) :RecyclerView.ViewHolder(sendviewDataBinding.root){

        fun bind(mesaage:Message?){
            sendviewDataBinding.messageitem = mesaage
            sendviewDataBinding.invalidateAll() // or executePendingBindings()


        }

    }
    class recivemessageViewHolder(val reciveviewDataBinding:ItemReciveMessageBinding) :RecyclerView.ViewHolder(reciveviewDataBinding.root){
        fun bind(mesaage:Message?){
            reciveviewDataBinding.messageitem = mesaage
            reciveviewDataBinding.executePendingBindings()


        }

    }

    val RECIVED =1
    val SENT =2
    override fun getItemViewType(position: Int): Int {
    val message =  messageitems.get(position)
        if(message?.senderId == DataUtils.user?.id)   // lw el sender id == el id bta3 el user elly 3amel login( ya3ny bta3y) , ezn ana elly b send el message , ezn use send xml
            return SENT
        else
            return RECIVED
    }

    override fun getItemCount(): Int {
        return messageitems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SENT)
        {
            val itemBiding:ItemSendMessageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_send_message,parent,false)
            return sendmessageViewHolder(itemBiding)
        }

        else
        {
            val itemBiding:ItemReciveMessageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_recive_message,parent,false)
            return recivemessageViewHolder(itemBiding)
        }







    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        // on bind : used when inflate new item / message f el rec view , ezn before kol mra lazen a3rf el item da tb3 el send wla el recived
        // ezb konwing that by: 1/ type if sent or recived . 2/ holder if sendholder class or reciveholder class
            if (holder is sendmessageViewHolder)
            {
                holder.bind(messageitems.get(position))

               }
             else if (holder is recivemessageViewHolder)
               {
                   holder.bind(messageitems.get(position))

               }

    }

    fun appendmessages(newmessages: MutableList<Message>) {
        messageitems.addAll(newmessages)
//        notifyDataSetChanged()
        notifyItemRangeChanged(messageitems.size+1, newmessages.size) // size el old list w sizw new list : used to refresh adapter using new range

    }

}
