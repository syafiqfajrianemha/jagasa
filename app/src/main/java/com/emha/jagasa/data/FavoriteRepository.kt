package com.emha.jagasa.data

import androidx.lifecycle.LiveData
import com.emha.jagasa.data.dao.AnimalDao
import com.emha.jagasa.data.entity.AnimalEntity

class FavoriteRepository(
    private val animalDao: AnimalDao
) {

    val readAllFavorite: LiveData<List<AnimalEntity>> = animalDao.getFavoriteAll()

    fun isFavoriteAnimal(id: Long) = animalDao.getFavoriteById(id)

    suspend fun addFavorite(animal: AnimalEntity) {
        animalDao.insert(animal)
    }

    suspend fun deleteFavorite(animal: AnimalEntity) {
        animalDao.delete(animal)
    }
}