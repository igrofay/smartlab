package com.example.data.data.repos

import androidx.datastore.core.DataStore
import com.example.data.data.model.CatalogEntryBody
import com.example.data.domain.model.catalog.Ratio
import com.example.data.domain.model.catalog.CatalogEntryModel
import com.example.data.domain.repos.CartRepos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CartReposImpl @Inject constructor(
    private val dataStore: DataStore<List<Ratio<CatalogEntryBody>>>
): CartRepos {
    override fun getCart(): Flow<List<Ratio<CatalogEntryModel>>> {
        return dataStore.data as Flow<List<Ratio<CatalogEntryModel>>>
    }

    override suspend fun add(ratio: Ratio<CatalogEntryModel>) {
        val asRatio: Ratio<CatalogEntryBody> = ratio as? Ratio<CatalogEntryBody> ?: return
        dataStore.updateData {list->
            list.toMutableList().apply {
                this.add(asRatio)
            }
        }
    }

    override suspend fun update(ratio: Ratio<CatalogEntryModel>) {
        dataStore.updateData {list->
            val index = list.indexOfFirst { it.product.id == ratio.product.id }
            list.toMutableList().apply {
                this[index] = this[index].copy(amount = ratio.amount)
            }
        }
    }

    override suspend fun remove(entryModel: CatalogEntryModel) {
        dataStore.updateData {list->
            val index = list.indexOfFirst { it.product.id == entryModel.id }
            if(index <0 ) return@updateData list
            list.toMutableList().apply {
                this.removeAt(index)
            }
        }
    }

}