package com.gmail.eamosse.imdb.ui.actors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.imdb.databinding.ActorsListItemBinding

class ActorsAdapter(private val items: List<Actor>, private val onClick: (actor: Actor) -> Unit) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ActorsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Actor) {
            binding.item = item
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ActorsListItemBinding.inflate(inflater, parent, false))    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}