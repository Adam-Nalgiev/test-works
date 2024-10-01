package com.nadev.binetservice.presentation.listFragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(private val viewModel: ListViewModel): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return viewModel as T
        }else {
            throw IllegalArgumentException()
        }
    }
}