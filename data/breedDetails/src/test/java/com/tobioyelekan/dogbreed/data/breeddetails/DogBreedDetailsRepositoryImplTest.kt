package com.tobioyelekan.dogbreed.data.breeddetails

import android.database.sqlite.SQLiteException
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.common.result.mapToSuccess
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class DogBreedDetailsRepositoryImplTest {
    private val dogBreedDao: DogBreedDao = mockk()
    private val subject = DogBreedDetailsRepositoryImpl(dogBreedDao)

    @Test
    fun `getBreedDetails returns breed`() = runTest {
        //given
        val breedEntity = flowOf(cachedEntities[0])
        coEvery { dogBreedDao.getBreed(any()) } returns breedEntity

        //when
        val actual = subject.getBreedDetails("breedName")

        //then
        val expected = breedEntity.mapToSuccess { it.toDomainModel() }
        coVerify { dogBreedDao.getBreed(any()) }
        assertEquals(expected.first(), actual.first())
    }

    @Test
    fun `getBreedDetails returns error when db error occurs`() = runTest {
        //given
        coEvery { dogBreedDao.getBreed(any()) } throws SQLiteException()

        //when
        val actual = subject.getBreedDetails("breedName")

        //then
        val expected = flowOf(Result.Failure("something went wrong, please try again"))
        coVerify { dogBreedDao.getBreed(any()) }
        assertEquals(expected.first(), actual.first())
    }

    @Test
    fun `addFavoriteBreed returns success`() = runTest {
        //given
        coEvery { dogBreedDao.updateBreed(any(), true) } returns Unit

        //when
        val actual = subject.addFavoriteBreed("breedName")

        //then
        val expected = flowOf(Result.Success(Unit))
        coVerify { dogBreedDao.updateBreed(any(), true) }
        assertEquals(expected.first(), actual)
    }

    @Test
    fun `addFavoriteBreed returns error if error occurs`() = runTest {
        //given
        coEvery { dogBreedDao.updateBreed(any(), true) } throws SQLiteException()

        //when
        val actual = subject.addFavoriteBreed("breedName")

        //then
        val expected = flowOf(Result.Failure("something went wrong, please try again"))
        coVerify { dogBreedDao.updateBreed(any(), true) }
        assertEquals(expected.first(), actual)
    }

    @Test
    fun `removeFavoriteBreed returns success`() = runTest {
        //given
        coEvery { dogBreedDao.updateBreed(any(), false) } returns Unit

        //when
        val actual = subject.removeFavoriteBreed("breedName")

        //then
        val expected = flowOf(Result.Success(Unit))
        coVerify { dogBreedDao.updateBreed(any(), false) }
        assertEquals(expected.first(), actual)
    }

    @Test
    fun `removeFavoriteBreed returns error if error occurs`() = runTest {
        //given
        coEvery { dogBreedDao.updateBreed(any(), false) } throws SQLiteException()

        //when
        val actual = subject.removeFavoriteBreed("breedName")

        //then
        val expected = flowOf(Result.Failure("something went wrong, please try again"))
        coVerify { dogBreedDao.updateBreed(any(), false) }
        assertEquals(expected.first(), actual)
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