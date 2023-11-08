package com.emha.jagasa.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emha.jagasa.data.entity.AnimalEntity

@Dao
interface AnimalDao {

    @Query("SELECT * FROM favorites ORDER BY id_item DESC")
    fun getFavoriteAll(): LiveData<List<AnimalEntity>>

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteById(id: Long): LiveData<AnimalEntity>

    @Insert
    suspend fun insert(animal: AnimalEntity)

    @Update
    suspend fun update(animal: AnimalEntity)

    @Delete
    suspend fun delete(animal: AnimalEntity)
}