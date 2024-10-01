package com.nadev.binetservice.presentation.itemCard.viewModel

import androidx.lifecycle.ViewModel
import com.nadev.binetservice.domain.GetDrugUseCase
import com.nadev.binetservice.entity.dto.DrugsDto
import javax.inject.Inject

class ItemCardViewModel @Inject constructor(private val getDrugUseCase: GetDrugUseCase) : ViewModel() {

    suspend fun getDrug(id: Int): DrugsDto {
        return getDrugUseCase.execute(id)
    }

}