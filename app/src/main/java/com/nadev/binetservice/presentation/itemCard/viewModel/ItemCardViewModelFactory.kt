package com.nadev.binetservice.presentation.itemCard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ItemCardViewModelFactory @Inject constructor(private val viewModel: ItemCardViewModel): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemCardViewModel::class.java)) {
            return viewModel as T
        }else {
            throw IllegalArgumentException()
        }
    }
}