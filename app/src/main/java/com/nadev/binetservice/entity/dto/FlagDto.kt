package com.nadev.binetservice.entity.dto

import com.nadev.binetservice.entity.Flag

data class FlagDto(

    override val html: Int,
    override val no_value: Int,
    override val no_name: Int,
    override val no_image: Int,
    override val no_wrap: Int,
    override val no_wrap_name: Int,
    override val system: Int,

    ) : Flag