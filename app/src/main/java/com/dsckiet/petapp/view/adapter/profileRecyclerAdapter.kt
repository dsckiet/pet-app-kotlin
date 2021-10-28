package com.dsckiet.petapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.petapp.R
import com.dsckiet.petapp.view.profilemodel.profile
import kotlinx.android.synthetic.main.pet_item.view.*

class profileRecyclerAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var posts: List<profile> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return profileRecyclerAdapter.profileViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pet_item, parent, false), context)//messages is xml file
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is profileRecyclerAdapter.profileViewHolder -> {
                holder.bind(posts.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
       return posts.size
    }
    fun submitList(blogList: List<profile>) {
        posts = blogList
    }
    class profileViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView){
        val profileimage=itemView.iv_profilepic
        val ownernam=itemView.tv_petName
        val age=itemView.tv_age
        val breed=itemView.tv_breed
        val distance=itemView.tv_dis

        fun bind(prof:profile){
            ownernam.setText(prof.ownername)
            age.setText(prof.petage)
            breed.setText(prof.breed)
            distance.setText(prof.distance)


            Glide.with(context)
                .load(prof.dp)
                .into(profileimage)
        }

    }
}