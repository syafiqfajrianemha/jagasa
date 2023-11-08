package com.emha.jagasa.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emha.jagasa.data.JagasaRepository
import com.emha.jagasa.ui.Model.Animal
import com.emha.jagasa.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: JagasaRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Animal>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Animal>>
        get() = _uiState

    fun getAnimalById(animalId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAnimalById(animalId))
        }
    }
}