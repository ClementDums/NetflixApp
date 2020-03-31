package com.gmail.eamosse.imdb.ui.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Person
import com.gmail.eamosse.imdb.databinding.TrendingPersonItemBinding

class TrendingPersonAdapter(private val items: List<Person>) :

    RecyclerView.Adapter<TrendingPersonAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: TrendingPersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TrendingPersonItemBinding =
            TrendingPersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}