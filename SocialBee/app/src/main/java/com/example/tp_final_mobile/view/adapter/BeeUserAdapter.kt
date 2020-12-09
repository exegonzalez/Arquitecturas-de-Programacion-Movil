package com.example.tp_final_mobile.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tp_final_mobile.model.BeeUser
import com.example.tp_final_mobile.R
import java.util.*

class BeeUserAdapter(val beeUserListener: BeeUserListener) : RecyclerView.Adapter<BeeUserAdapter.ViewHolder>() {

    var listBeeUsers = ArrayList<BeeUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bee_user, parent, false))

    override fun onBindViewHolder(holder: BeeUserAdapter.ViewHolder, position: Int) {
        val beeuser = listBeeUsers[position]

        holder.tvItemBeeUserJobTitle.text = beeuser.jobtitle
        holder.tvItemBeeUserName.text = beeuser.name

        Glide.with(holder.itemView.context)
            .load(beeuser.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivItemBeeUserImage)

        holder.itemView.setOnClickListener {
            beeUserListener.onBeeUserClicked(beeuser, position)
        }

    }

    override fun getItemCount() = listBeeUsers.size

    fun updateData(data: List<BeeUser>) {
        listBeeUsers.clear()
        listBeeUsers.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItemBeeUserJobTitle = itemView.findViewById<TextView>(R.id.tvItemBeeUserJobTitle)
        val tvItemBeeUserName = itemView.findViewById<TextView>(R.id.tvItemBeeUserName)
        val ivItemBeeUserImage = itemView.findViewById<ImageView>(R.id.ivItemBeeUserImage)
    }

}