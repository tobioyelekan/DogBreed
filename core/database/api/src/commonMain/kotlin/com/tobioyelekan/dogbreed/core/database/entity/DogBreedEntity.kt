package com.tobioyelekan.dogbreed.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import androidx.room.TypeConverter
import com.tobioyelekan.dogbreed.core.model.DogBreed

@Entity(tableName = "breed_table")
@TypeConverters(DogBreedEntityConverter::class)
data class DogBreedEntity(
    @PrimaryKey
    val name: String,
    val imageUrl: String,
    val subBreeds: List<String>,
    val isFavorite: Boolean
)

class DogBreedEntityConverter {
    @TypeConverter
    fun stringToList(data: String): List<String> {
        return if (data.isEmpty()) emptyList() else data.replace(" ", "").split(",")
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString()
    }
}

fun DogBreedEntity.toDomainModel() = DogBreed(
    name = this.name,
    imageUrl = this.imageUrl,
    subBreeds = this.subBreeds,
    isFavorite = this.isFavorite
)