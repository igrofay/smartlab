package com.example.data.domain.use_case

import com.example.data.domain.repos.CartRepos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalculateSumUseCase @Inject constructor(
    private val cartRepos: CartRepos,
) {
    fun execute() : Flow<Int> = cartRepos.getCartCatalog().map { list->
        list.sumOf {ratio ->
            ratio.amount * (ratio.product.price.toIntOrNull() ?: 0)
        }
    }

}