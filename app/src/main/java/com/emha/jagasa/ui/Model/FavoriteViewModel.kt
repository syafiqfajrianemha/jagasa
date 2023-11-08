package com.emha.jagasa.ui.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.emha.jagasa.data.AppDatabase
import com.emha.jagasa.data.FavoriteRepository
import com.emha.jagasa.data.entity.AnimalEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    val readAllFavorite: LiveData<List<AnimalEntity>>
    private val favoriteRepository: FavoriteRepository

    init {
        val animalDao = AppDatabase.getDatabase(application).animalDao()
        favoriteRepository = FavoriteRepository(animalDao)
        readAllFavorite = favoriteRepository.readAllFavorite
    }

    fun addFavoriteAnimal(animal: AnimalEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.addFavorite(animal)
        }
    }

    fun deleteFavoriteAnimal(animal: AnimalEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(animal)
        }
    }

    fun isFavorite(id: Long) = favoriteRepository.isFavoriteAnimal(id)
}

class FavoriteViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}