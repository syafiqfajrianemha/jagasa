package com.emha.jagasa.ui.screen.favorite

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emha.jagasa.ui.Model.Animal
import com.emha.jagasa.ui.Model.FavoriteViewModel
import com.emha.jagasa.ui.Model.FavoriteViewModelFactory
import com.emha.jagasa.ui.components.AnimalItem

@Composable
fun FavoriteScreen(
    navigateToDetail: (Long) -> Unit,
) {
    val context = LocalContext.current
    val mFavoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(context.applicationContext as Application)
    )

    val list = mFavoriteViewModel.readAllFavorite.observeAsState(listOf()).value

    Column {
        Text(
            text = "Favorite",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 8.dp)
        )
        if (list.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "There is no favorite."
                )
            }
        } else {
            LazyColumn() {
                items(list) { animal ->
                    val data = Animal(
                        id = animal.id,
                        name = animal.name,
                        description = animal.description,
                        photo = animal.photo,
                        populasi = animal.populasi
                    )
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
}