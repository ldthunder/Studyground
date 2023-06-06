package com.example.sg.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.sg.domain.models.Demon

object DiffCallback : DiffUtil.ItemCallback<Demon>() {
    override fun areItemsTheSame(oldItem: Demon, newItem: Demon) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Demon, newItem: Demon) =
        oldItem == newItem
}