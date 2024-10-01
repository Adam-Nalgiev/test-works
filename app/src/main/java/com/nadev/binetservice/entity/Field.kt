package com.nadev.binetservice.entity

interface Field {

    val types_id: Int
    val type: String
    val name: String
    val value: String
    val image: String
    val flags: Flag
    val show: Int
    val group: Int

}