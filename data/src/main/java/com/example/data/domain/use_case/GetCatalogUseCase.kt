package com.example.data.domain.use_case

import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.repos.CatalogRepos
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val catalogRepos: CatalogRepos,
) {
    suspend fun execute() = runCatching<Map<String, List<CatalogEntryModel>>>{
        val answer = mutableMapOf<String,MutableList<CatalogEntryModel>>()
        for (item in catalogRepos.getListCatalogEntry()){
            answer[item.category]?.add(item) ?: run {
                answer[item.category] = mutableListOf(item)
            }
        }
        return@runCatching answer
    }
}