package com.example.movie_chat.modelclasses

import com.example.movie_chat.R

data class Item_Spinner_Categories (

    val id:String?=null,
    val name:String?=null,
    val imageid :Int?=null)
{

    companion object{
       const val MUSIC ="Music"
        const val SPORTS = "Sports"
        const val MOVIES ="Movies"


        fun createCategoryfromId (catId:String): Item_Spinner_Categories {
            when(catId)
            {
                MUSIC ->{ return Item_Spinner_Categories(MUSIC, name = "Music", imageid = R.drawable.music)
                }
                MOVIES ->{return Item_Spinner_Categories(MOVIES, name = "Movies", imageid = R.drawable.movie_icon)
                }
                else->{return Item_Spinner_Categories(SPORTS, name = "Sports", imageid = R.drawable.sports)
                }
            }

            }
        fun getCategoriesList():List<Item_Spinner_Categories> {
            return listOf(
                createCategoryfromId(MUSIC),
                createCategoryfromId(MOVIES),
                createCategoryfromId(SPORTS)
            )
        }

    }
}