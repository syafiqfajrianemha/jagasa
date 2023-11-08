package com.emha.jagasa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class AnimalEntity(
    @ColumnInfo("id_item")
    @PrimaryKey(autoGenerate = true)
    val idItem: Int? = null,

    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("photo")
    val photo: Int,

    @ColumnInfo("populasi")
    val populasi: String
)