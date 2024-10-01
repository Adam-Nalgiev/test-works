package com.nadev.binetservice.domain

import com.nadev.binetservice.data.Repository
import com.nadev.binetservice.entity.dto.DrugsDto
import javax.inject.Inject

class GetDrugUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(id: Int): DrugsDto {
        return repository.getDrug(id)
    }
}