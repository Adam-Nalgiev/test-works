package com.nadev.binetservice.data

import com.nadev.binetservice.data.network.Network
import com.nadev.binetservice.entity.dto.DrugsDto
import javax.inject.Inject

class Repository @Inject constructor(private val client: Network) {

    suspend fun getDrugsList(page: Int, requestText: String? = null): List<DrugsDto> {
        return client.request.getDrugs(page, requestText)
    }

    suspend fun getDrug(id: Int): DrugsDto {
        return client.request.getOneDrug(id)
    }

}