package com.example.sg.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.sg.R
import com.example.sg.databinding.DemonListItemBinding
import com.example.sg.domain.models.Demon

class DemonListAdapter(val context: Context) : ListAdapter<Demon, DemonViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemonViewHolder {
        val binding = DemonListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DemonViewHolder, position: Int) {
        with(holder.binding){
            with(getItem(position)){
                tvDemonName.text = name
                tvDemonPower.text = thumbnail
                tvIdNumber.text = id.toString()
                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.elinaicolast_background)
                    .into(igDemon)
            }
        }
    }
}