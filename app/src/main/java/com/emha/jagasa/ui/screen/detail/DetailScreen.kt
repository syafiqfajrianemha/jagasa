package com.emha.jagasa.ui.screen.detail

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emha.jagasa.data.entity.AnimalEntity
import com.emha.jagasa.di.Injection
import com.emha.jagasa.ui.Model.Animal
import com.emha.jagasa.ui.Model.FavoriteViewModel
import com.emha.jagasa.ui.Model.FavoriteViewModelFactory
import com.emha.jagasa.ui.ViewModelFactory
import com.emha.jagasa.ui.common.UiState

@Composable
fun DetailScreen(
    animalId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAnimalById(animalId)
            }

            is UiState.Success -> {
                DetailContent(animal = uiState.data)
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    animal: Animal,
) {
    val context = LocalContext.current
    val mFavoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(context.applicationContext as Application)
    )

    val isFavorite = mFavoriteViewModel.isFavorite(animal.id).observeAsState().value

    Column {
        Image(
            painter = painterResource(animal.photo),
            contentDescription = "Photo ${animal.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            FloatingActionButton(
                onClick = {
                    val animalEntity = AnimalEntity(
                        id = animal.id,
                        name = animal.name,
                        description = animal.description,
                        photo = animal.photo,
                        populasi = animal.populasi
                    )
                    if (isFavorite?.id == animal.id) {
                        mFavoriteViewModel.deleteFavoriteAnimal(isFavorite)
                        Toast.makeText(
                            context,
                            "Favorite deleted successfully.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        mFavoriteViewModel.addFavoriteAnimal(animalEntity)
                        Toast.makeText(context, "Favorite added successfully.", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                if (isFavorite?.id == animal.id) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                } else {
                    Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
                }
            }
            Text(
                text = animal.name,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
            Divider()
            Text(
                text = "Description:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animal.description,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
            Divider()
            Text(
                text = "Populasi:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animal.populasi
            )
        }
    }
}
