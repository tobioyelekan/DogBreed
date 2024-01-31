package com.tobioyelekan.dogbreed.data.favorites

import android.database.sqlite.SQLiteException
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.common.result.mapToSuccess
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import com.tobioyelekan.dogbreed.data.favorites.repository.FavoriteBreedRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class FavoriteBreedRepositoryImplTest {
    private val dogBreedDao: DogBreedDao = mockk()
    private val subject = FavoriteBreedRepositoryImpl(dogBreedDao)

    @Test
    fun `getFavoriteBreeds returns favorite breeds`() = runTest {
        //given
        val breedEntities = flowOf(cachedEntities.filter { it.isFavorite })
        coEvery { dogBreedDao.getFavoriteBreeds() } returns breedEntities

        //when
        val actual = subject.getFavoriteBreeds()

        //then
        val expected = breedEntities
            .map { it.map { it.toDomainModel() } }
            .mapToSuccess { it }
        coVerify { dogBreedDao.getFavoriteBreeds() }
        assertEquals(expected.first(), actual.first())
    }

    @Test
    fun `getBreedDetails returns error when db error occurs`() = runTest {
        //given
        coEvery { dogBreedDao.getFavoriteBreeds() } throws SQLiteException()

        //when
        val actual = subject.getFavoriteBreeds()

        //then
        val expected = flowOf(Result.Failure("something went wrong, please try again"))
        coVerify { dogBreedDao.getFavoriteBreeds() }
        assertEquals(expected.first(), actual.first())
    }

    private val sampleImageUrl = "imageUrl"

    private val cachedEntities = listOf(
        DogBreedEntity(
            name = "dog1",
            imageUrl = sampleImageUrl,
            subBreeds = listOf("dog1a", "dog1b"),
            isFavorite = true
        ),
        DogBreedEntity(
            name = "dog2",
            imageUrl = sampleImageUrl,
            subBreeds = emptyList(),
            isFavorite = false
        ),
        DogBreedEntity(
            name = "dog3",
            imageUrl = sampleImageUrl,
            subBreeds = listOf("dog3a"),
            isFavorite = true
        )
    )
}