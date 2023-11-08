package com.emha.jagasa.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emha.jagasa.di.Injection
import com.emha.jagasa.ui.Model.Animal
import com.emha.jagasa.ui.ViewModelFactory
import com.emha.jagasa.ui.common.UiState
import com.emha.jagasa.ui.components.AnimalItem
import com.emha.jagasa.ui.components.Search

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllAnimals()
            }

            is UiState.Success -> {
                HomeContent(
                    uiState.data,
                    navigateToDetail = navigateToDetail,
                    viewModel
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    animal: List<Animal>,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel
) {
    val query by viewModel.query

    Column {
        Search(query = query, onQueryChange = viewModel::search)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(animal) { data ->
                AnimalItem(
                    animal = data,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }
        }
    }
}