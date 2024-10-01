package com.nadev.binetservice.entity.dto

import com.nadev.binetservice.entity.Drugs

data class DrugsDto(

    override val id: Int,
    override val image: String,
    override val categories: CategoriesDto,
    override val name: String,
    override val description: String,
    override val documentation: String?,
    override val fields: List<FieldDto>,
    override val gtin: String,

    ) : Drugs
