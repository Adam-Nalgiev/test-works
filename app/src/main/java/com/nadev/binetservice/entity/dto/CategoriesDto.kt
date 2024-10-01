package com.nadev.binetservice.entity.dto

import com.nadev.binetservice.entity.Categories

data class CategoriesDto(

    override val id: String,
    override val icon: String,
    override val image: String,
    override val name: String,

    ) : Categories