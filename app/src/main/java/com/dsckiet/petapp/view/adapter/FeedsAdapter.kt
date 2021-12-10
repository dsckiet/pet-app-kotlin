package com.dsckiet.petapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.petapp.R
import com.dsckiet.petapp.view.model.get.feeds.FeedDataCheck

class FeedsAdapter(private val context: Context) : RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {

    private var feedsList: ArrayList<FeedDataCheck.Feed> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = feedsList[position]
        val index = item.feedImg?.indexOf(':')
        val src = "https"+item.feedImg?.subSequence(startIndex = index!!, item.feedImg?.length!!)
        Glide.with(context).load(src).into(holder.image)
        holder.name.text = item.feedCaption
        holder.time.text = item.feedCreation
    }

    override fun getItemCount(): Int {
        return feedsList.size
    }

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.feed_image)
        val name: TextView = itemView.findViewById(R.id.feed_name)
        val time: TextView = itemView.findViewById(R.id.feed_time)
        val like: ImageView = itemView.findViewById(R.id.feed_like_empty)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(item: ArrayList<FeedDataCheck.Feed>) {
        this.feedsList = item
        notifyDataSetChanged()
    }
}