package com.nadev.binetservice.presentation.listFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.nadev.binetservice.data.network.Network
import com.nadev.binetservice.databinding.ItemDrugsBinding
import com.nadev.binetservice.entity.dto.DrugsDto

class ListAdapter(private val onClick: (Int) -> Unit) : PagingDataAdapter<DrugsDto, ListViewHolder>(DiffUtilCallback()) {

    private lateinit var binding: ItemDrugsBinding

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)!!

        with(holder.binding) {
            item.let { item ->
                Glide.with(imageItem).load(Network.BASE_URL.plus(item.image)).into(imageItem) // Кто вообще такую ссылку отправляет???

                textItemName.text = item.name
                textItemDescription.text = item.description

                root.setOnClickListener {
                    onClick(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemDrugsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
}