package com.example.confbeerdemo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.confbeerdemo.R
import com.example.confbeerdemo.model.Speaker
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.confbeerdemo.view.ui.fragments.SpeakersFragment

class SpeakersAdapter(val speakerListener: SpeakersFragment) : RecyclerView.Adapter<SpeakersAdapter.ViewHolder>(){

    val listSpeaker = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SpeakersAdapter.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun getItemCount() = listSpeaker.size

    override fun onBindViewHolder(holder: SpeakersAdapter.ViewHolder, position: Int) {

        val speaker = listSpeaker[position] as Speaker

        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivSpeakerImage)

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker, position)
        }

    }

    fun updateData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpeakerName  = itemView.findViewById<TextView>(R.id.tvNameSpeaker)
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvSpeakerWork)
        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)

    }

}

