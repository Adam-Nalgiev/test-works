package com.nadev.binetservice.entity

interface Drugs {

    val id: Int
    val image: String
    val categories: Categories
    val name: String
    val description: String
    val documentation: String?
    val fields: List<Field>
    val gtin: String

}