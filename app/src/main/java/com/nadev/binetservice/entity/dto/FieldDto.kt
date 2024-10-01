package com.nadev.binetservice.entity.dto

import com.nadev.binetservice.entity.Field

data class FieldDto(

    override val types_id: Int,
    override val type: String,
    override val name: String,
    override val value: String,
    override val image: String,
    override val flags: FlagDto,
    override val show: Int,
    override val group: Int,

    ) : Field