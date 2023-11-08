package com.emha.jagasa.data

import com.emha.jagasa.ui.Model.Animal
import com.emha.jagasa.ui.Model.FakeAnimalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class JagasaRepository {

    private val animals = mutableListOf<Animal>()

    init {
        FakeAnimalDataSource.dummyAnimals.forEach {
            animals.add(
                Animal(
                    it.id,
                    it.name,
                    it.description,
                    it.photo,
                    it.populasi
                )
            )
        }
    }

    fun getAllAnimals(): Flow<List<Animal>> {
        return flowOf(animals)
    }

    fun getAnimalById(animalId: Long): Animal {
        return animals.first {
            it.id == animalId
        }
    }

    fun searchAnimals(query: String): Flow<List<Animal>> {
        return flowOf(
            animals
                .filter {
                    it.name.contains(query, ignoreCase = true)
                }
        )
    }

    companion object {
        @Volatile
        private var instance: JagasaRepository? = null

        fun getInstance(): JagasaRepository =
            instance ?: synchronized(this) {
                JagasaRepository().apply {
                    instance = this
                }
            }
    }
}