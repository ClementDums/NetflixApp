package com.gmail.eamosse.imdb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.ProductionCompany
import com.gmail.eamosse.imdb.databinding.ProductionCompaniesItemBinding
import com.gmail.eamosse.imdb.databinding.TrendingMovieItemBinding


class ProductionCompaniesAdapter(private val items: List<ProductionCompany>) :

    RecyclerView.Adapter<ProductionCompaniesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ProductionCompaniesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductionCompany) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ProductionCompaniesItemBinding = ProductionCompaniesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}