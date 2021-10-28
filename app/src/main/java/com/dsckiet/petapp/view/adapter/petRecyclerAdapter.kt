package com.dsckiet.petapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.petapp.R

import com.dsckiet.petapp.view.profilemodel.pet
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.home_category_item.view.*
import android.content.Context as Context

class petRecyclerAdapter(val context: Context, private var petpost: List<pet>) :
    RecyclerView.Adapter<petRecyclerAdapter.ViewHolder>() {

    lateinit var inflater: LayoutInflater

    init {
        this.inflater= LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): petRecyclerAdapter.ViewHolder {



        //  return petRecyclerAdapter.petViewHolder(
        val view= inflater
                .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view,context)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is petRecyclerAdapter.ViewHolder -> {
                holder.bind(petpost.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return petpost.size
    }

    class ViewHolder(itemView: View,val context: Context) : RecyclerView.ViewHolder(itemView){
        val petdp=itemView.iv_dog
        val petbreed=itemView.tv_name

        fun bind(pe: pet){
            petbreed.setText(pe.breed)

       Glide.with(context)
                .load(pe.dp)
                .into(petdp)
        }
    }


}