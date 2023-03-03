package com.example.smartlab.analyzes.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.repos.CatalogRepos
import com.example.data.domain.use_case.GetCatalogUseCase
import com.example.smartlab.analyzes.model.AnalyzesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AnalyzesVM @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val catalogRepos: CatalogRepos,
): ViewModel() {
    private val _state = mutableStateOf<AnalyzesState>(AnalyzesState.Load)
    val state: State<AnalyzesState> by::_state
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
                    localState.copy(promotions = list)
                }
            }
        }.onFailure(::onError)
        getCatalogUseCase.execute().onSuccess { map->
            _state.value = when(val localState = _state.value){
                AnalyzesState.Load -> {
                    AnalyzesState.Success(map, listOf())
                }
                is AnalyzesState.Success -> {
                    localState.copy(catalog = map)
                }
            }
        }.onFailure(::onError)
    }
    private fun onError(e:Throwable){
        Log.e("AnalyzesVM::", e.message.toString())
    }
}