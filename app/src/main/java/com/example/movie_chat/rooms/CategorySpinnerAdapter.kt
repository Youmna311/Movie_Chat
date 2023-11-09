package com.example.movie_chat.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.movie_chat.R
import com.example.movie_chat.modelclasses.Item_Spinner_Categories

//Custom Adapter for spinner
class CategorySpinnerAdapter(val listofCategories: List<Item_Spinner_Categories>) :BaseAdapter() {
    override fun getCount(): Int {
        return listofCategories.size
    }

    override fun getItem(position: Int): Any {
        return listofCategories[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, container: ViewGroup?): View {
        //return view that appear in the spinner
        var myView = view
        var viewHolder:ViewHolder
        if (myView == null) {
            //create the view
            myView = LayoutInflater.from(container!!.context)
                .inflate(R.layout.item_spinner_category, container, false)
            //find view by id ll view by ViewHolder
            viewHolder = ViewHolder(myView)
            //save viewholder refrence inside the view
            myView.setTag(viewHolder)

        }
        else
        {
            //ezn fe already view so that n bind el data f el view b2a (recycle el view)
            viewHolder= myView.tag as ViewHolder
        }
        val item = listofCategories[position]
        viewHolder.title.setText(item.name)
        viewHolder.image.setImageResource(item.imageid!!)

        return myView!!
    }

    class ViewHolder (val view: View) {
        val title :TextView = view.findViewById(R.id.categorytitle)
        val image :ImageView = view.findViewById(R.id.categoryimage)

    }
}

