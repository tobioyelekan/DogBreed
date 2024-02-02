package com.tobioyelekan.dogbreed.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DogBreedDaoTest {
    private lateinit var dogBreedDao: DogBreedDao
    private lateinit var db: DogBreedDatabase

    @Before
    fun createDb() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            DogBreedDatabase::class.java
        ).build()
        dogBreedDao = db.breedDao()
        dogBreedDao.saveBreeds(dogEntities)
    }

    @Test
    fun save_and_fetch_dogBreeds() = runTest {
        val allBreeds = dogBreedDao.getAllBreeds()
        assertEquals(dogEntities, allBreeds)
    }

    @Test
    fun save_and_fetch_breed_exists() = runTest {
        val dogBreed = dogBreedDao.getBreed(dogEntities[0].name)
        assertEquals(dogEntities[0], dogBreed.first())
    }

    @Test
    fun verify_insert_conflict_strategy_to_replace_existing_key() = runTest {
        val newDogBreedWithDuplicateKey =
            DogBreedEntity("dog1", "new_imageUrl1", listOf("doga", "dogb"), false)
        dogBreedDao.saveBreeds(listOf(newDogBreedWithDuplicateKey))

        val dogBreeds = dogBreedDao.getAllBreeds()
        assertEquals(dogEntities.size, dogBreeds.size)

        val dogBreed = dogBreedDao.getBreed(newDogBreedWithDuplicateKey.name)
        assertEquals(newDogBreedWithDuplicateKey, dogBreed.first())
    }

    @Test
    fun fetch_favorite_breeds() = runTest {
        val favoriteBreeds = dogBreedDao.getFavoriteBreeds()

        val expectedFavoriteBreeds = dogEntities.filter { it.isFavorite }
        assertEquals(expectedFavoriteBreeds, favoriteBreeds.first())
    }

    @Test
    fun add_breed_as_favorite() = runTest {
        dogBreedDao.updateBreed(dogEntities[0].name, true)

        val breed = dogBreedDao.getBreed(dogEntities[0].name)
        assertEquals(true, breed.first().isFavorite)
    }

    @Test
    fun remove_breed_as_favorite() = runTest {
        dogBreedDao.updateBreed(dogEntities[2].name, false)

        val breed = dogBreedDao.getBreed(dogEntities[2].name)
        assertEquals(false, breed.first().isFavorite)
    }

    @Test
    fun deleteAllBreedRecords() = runTest {
        dogBreedDao.nukeTable()

        val breed = dogBreedDao.getAllBreeds()
        assertEquals(emptyList(), breed)
    }
}

private val dogEntities = listOf(
    DogBreedEntity("dog1", "imageUrl1", listOf("doga", "dogb"), false),
    DogBreedEntity("dog2", "imageUrl2", emptyList(), false),
    DogBreedEntity("dog3", "imageUrl3", listOf("subbreed"), true),
    DogBreedEntity("dog4", "imageUrl4", listOf("dog1", "dog2", "dog3"), false),
)