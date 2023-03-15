package com.example.smartlab.cart.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.repos.CartRepos
import com.example.data.domain.use_case.CalculateSumUseCase
import com.example.smartlab.cart.model.CartEvent
import com.example.smartlab.cart.model.CartState
import com.example.smartlab.common.view_model.EventBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartVM @Inject constructor(
    private val cartRepos: CartRepos,
    private val sumUseCase: CalculateSumUseCase,
): ViewModel(), EventBase<CartEvent>  {
    private val _state = mutableStateOf(CartState())
    val state: State<CartState> by:: _state
    private val subscription = viewModelScope.launch{
        launch {
            cartRepos.getCartCatalog().collect{ list->
                _state.value = _state.value.copy(
                    catalogCart = list,
                )
            }
        }
        launch {
            sumUseCase.execute().collect{ sum ->
                _state.value = _state.value.copy(
                    sumCart = sum,
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        subscription.cancel()
    }

    override fun onEvent(event: CartEvent) {
        when(event){
            CartEvent.ClearCart -> {
                viewModelScope.launch {
                    cartRepos.clearCatalogEntry()
                }
            }
            is CartEvent.DecrementNumber -> {
                viewModelScope.launch {
                    cartRepos.updateCatalogEntry(
                        event.ratio.copy(
                            amount = event.ratio.amount.dec()
                        )
                    )
                }
            }
            is CartEvent.IncrementNumber -> {
                viewModelScope.launch {
                    cartRepos.updateCatalogEntry(
                        event.ratio.copy(
                            amount = event.ratio.amount.inc()
                        )
                    )
                }
            }
            is CartEvent.Delete -> {
                viewModelScope.launch {
                    cartRepos.removeCatalogEntry(event.catalogEntryModel)
                }
            }
        }
    }
}