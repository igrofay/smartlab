package com.example.smartlab.analyzes.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.analyzes.model.AnalyzesState
import com.example.smartlab.analyzes.view_model.AnalyzesVM

@Composable
internal fun AnalyzesScreen(
    viewModel: AnalyzesVM = hiltViewModel()
) {
    when(val state = viewModel.state.value){
        AnalyzesState.Load -> Box(Modifier.fillMaxSize(), Alignment.Center){
            CircularProgressIndicator()
        }
        is AnalyzesState.Success -> ContentView(state)
    }
}