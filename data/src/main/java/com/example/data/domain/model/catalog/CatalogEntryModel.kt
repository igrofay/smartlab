package com.example.data.domain.model.catalog

interface CatalogEntryModel {
    val id: Int
    val name:String
    val description:String
    val price:String
    val category:String
    val timeResult:String
    val preparation: String
    val bio:String
}