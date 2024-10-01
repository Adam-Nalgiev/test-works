package com.nadev.binetservice.presentation.listFragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nadev.binetservice.entity.dto.DrugsDto

class DiffUtilCallback : DiffUtil.ItemCallback<DrugsDto>() {

    override fun areItemsTheSame(oldItem: DrugsDto, newItem: DrugsDto): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DrugsDto, newItem: DrugsDto): Boolean =
        oldItem == newItem

}