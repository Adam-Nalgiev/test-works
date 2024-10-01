package com.nadev.binetservice.domain

import com.nadev.binetservice.data.Repository
import com.nadev.binetservice.entity.dto.DrugsDto
import javax.inject.Inject

class GetFoundDrugsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(page: Int, request: String): List<DrugsDto> {
        return repository.getDrugsList(page, request)
    }
}