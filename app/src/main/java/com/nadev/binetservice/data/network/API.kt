package com.nadev.binetservice.data.network

import com.nadev.binetservice.entity.dto.DrugsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("/api/ppp/index")
    suspend fun getDrugs(
        @Query("page") page: Int = 0,
        @Query("search") search: String?,
    ): List<DrugsDto>

    @GET("/api/ppp/item")
    suspend fun getOneDrug(
        @Query("id") id: Int
    ): DrugsDto

}