package com.emha.jagasa.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emha.jagasa.data.JagasaRepository
import com.emha.jagasa.ui.Model.Animal
import com.emha.jagasa.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: JagasaRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Animal>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Animal>>>
        get() = _uiState

    fun getAllAnimals() {
        viewModelScope.launch {
            repository.getAllAnimals()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { animals ->
                    _uiState.value = UiState.Success(animals)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery

        viewModelScope.launch {
            repository.searchAnimals(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _uiState.value = UiState.Success(data)
                }
        }
    }
}