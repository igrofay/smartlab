package com.example.smartlab.analyzes.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.model.catalog.Ratio
import com.example.data.domain.repos.CartRepos
import com.example.data.domain.repos.CatalogRepos
import com.example.data.domain.use_case.CalculateSumUseCase
import com.example.data.domain.use_case.GetCatalogUseCase
import com.example.smartlab.analyzes.model.AnalyzesEvent
import com.example.smartlab.analyzes.model.AnalyzesState
import com.example.smartlab.common.view_model.EventBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AnalyzesVM @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val catalogRepos: CatalogRepos,
    private val calculateSumUseCase: CalculateSumUseCase,
    private val cartRepos: CartRepos,
): ViewModel(), EventBase<AnalyzesEvent> {
    private val _state = mutableStateOf<AnalyzesState>(AnalyzesState.Load)
    val state: State<AnalyzesState> by::_state

    private val subscription = viewModelScope.launch {
        while (_state.value is AnalyzesState.Load){ delay(1000L) }
        launch {
            calculateSumUseCase.execute().collect{sum->
                _state.value = (_state.value as AnalyzesState.Success).copy(
                    cartValue = sum
                )
            }
        }
        launch {
            cartRepos.getCartCatalog().collect{ list->
                val setId = list.map { ratio -> ratio.product.id }.toSet()
                _state.value = (_state.value as AnalyzesState.Success).copy(
                    setIdCatalogEntry = setId
                )
            }
        }

    }
    init {
        viewModelScope.launch { load() }
    }
    private suspend fun load(){
        runCatching {
            catalogRepos.getListPromotion()
        }.onSuccess {list->
            _state.value = when(val localState = _state.value){
                AnalyzesState.Load -> {
                    AnalyzesState.Success(mapOf(), list)
                }
                is AnalyzesState.Success -> {
                    localState.copy(promotions = list, refreshing = false)
                }
            }
        }.onFailure(::onError)
        getCatalogUseCase.execute().onSuccess { map->
            _state.value = when(val localState = _state.value){
                AnalyzesState.Load -> {
                    AnalyzesState.Success(map, listOf())
                }
                is AnalyzesState.Success -> {
                    localState.copy(
                        catalog = map,
                        refreshing = false
                    )
                }
            }
        }.onFailure(::onError)
    }
    private fun onError(e:Throwable){
        Log.e("AnalyzesVM::", e.message.toString())
    }

    override fun onEvent(event: AnalyzesEvent) {
        when(event){
            is AnalyzesEvent.OpenCatalogEntry -> {
                val localState = _state.value as? AnalyzesState.Success ?: return
                _state.value = localState.copy(
                    currentCatalogEntryModel =  event.catalogEntry
                )
            }
            AnalyzesEvent.CloseCatalogEntry -> {
                val localState = _state.value as? AnalyzesState.Success ?: return
                _state.value = localState.copy(
                    currentCatalogEntryModel = null
                )
            }
            is AnalyzesEvent.SelectedCategoryCatalog -> {
                val localState = _state.value as? AnalyzesState.Success ?: return
                _state.value = localState.copy(
                    currentCatalog = event.category
                )
            }
            AnalyzesEvent.Refresh -> {
                _state.value = (_state.value as? AnalyzesState.Success)?.copy(
                    refreshing = true
                ) ?: return
                viewModelScope.launch { load() }
            }
            is AnalyzesEvent.Add -> {
                viewModelScope.launch {
                    cartRepos.addCatalogEntry(
                        Ratio(event.catalogEntry)
                    )
                }
            }
            is AnalyzesEvent.Remove -> viewModelScope.launch {
                cartRepos.removeCatalogEntry(
                    event.catalogEntry
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        subscription.cancel()
    }
}