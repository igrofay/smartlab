package com.example.data.data.repos

import com.example.data.data.di.RegularHttpClient
import com.example.data.data.model.CatalogEntryBody
import com.example.data.data.model.PromotionBody
import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.model.catalog.PromotionModel
import com.example.data.domain.repos.CatalogRepos
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

internal class CatalogReposImpl @Inject constructor(
    @RegularHttpClient private val client: HttpClient,
) : CatalogRepos{
    override suspend fun getListPromotion(): List<PromotionModel> {
        return client.get("/api/news").body<List<PromotionBody>>()
    }

    override suspend fun getListCatalogEntry(): List<CatalogEntryModel> {
        return client.get("/api/catalog").body<List<CatalogEntryBody>>()
    }
}