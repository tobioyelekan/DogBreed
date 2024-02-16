package com.tobioyelekan.dogbreed.data.allbreeds.repository

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.adapter.ApiResult
import com.tobioyelekan.dogbreed.core.network.model.BreedImageApiModel
import com.tobioyelekan.dogbreed.core.network.model.DogBreedsApiModel
import com.tobioyelekan.dogbreed.data.allbreeds.mapper.toEntity
import com.tobioyelekan.dogbreed.data.allbreeds.util.mergeEntities
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreedApiResponseTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.UnknownHostException
import kotlin.test.assertEquals

class DogBreedsRepositoryImplTest {
    private val dogBreedService: DogBreedApiService = mockk()
    private val dogBreedDao: DogBreedDao = mockk(relaxed = true)

    private val subject = DogBreedsRepositoryImpl(dogBreedDao, dogBreedService)

    @Test
    fun `getAllBreeds handles api success and returns dogBreeds`() = runTest {
        //given
        val sampleImageUrl = "imageUrl"

        coEvery { dogBreedService.getAllDogBreeds() } returns
                ApiResult.Success(data = DogBreedsApiModel(dogBreedApiResponseTestData))

        coEvery { dogBreedService.getBreedRandomImage(any()) } returns
                ApiResult.Success(data = BreedImageApiModel(sampleImageUrl))

        //when
        val actual = subject.getAllBreeds()

        //then
        val dogBreedDomain =
            dogBreedApiResponseTestData
                .map { it.toEntity(sampleImageUrl) }
                .map { it.toDomainModel() }
        assertEquals(Result.Success(dogBreedDomain), actual)
    }

    @Test
    fun `getAllBreeds handles api success and saves breeds in database`() = runTest {
        //given
        val sampleImageUrl = "imageUrl"

        coEvery { dogBreedService.getAllDogBreeds() } returns
                ApiResult.Success(data = DogBreedsApiModel(dogBreedApiResponseTestData))

        coEvery { dogBreedService.getBreedRandomImage(any()) } returns
                ApiResult.Success(data = BreedImageApiModel(sampleImageUrl))

        //when
        subject.getAllBreeds()

        //then
        val breedEntities = dogBreedApiResponseTestData.map { it.toEntity(sampleImageUrl) }
        coVerify { dogBreedDao.nukeTable() }
        coVerify { dogBreedDao.saveBreeds(breedEntities) }
    }

    @Test
    fun `getAllBreeds handles logic to retain previously liked breeds after api success`() = runTest {
        //given
        val dogBreedEntities = dogBreedApiResponseTestData.map { it.toEntity(sampleImageUrl) }

        coEvery { dogBreedService.getAllDogBreeds() } returns
                ApiResult.Success(data = DogBreedsApiModel(dogBreedApiResponseTestData))

        coEvery { dogBreedService.getBreedRandomImage(any()) } returns
                ApiResult.Success(data = BreedImageApiModel(sampleImageUrl))

        coEvery { dogBreedDao.getAllBreeds() } returns cachedEntities

        //when
        val actual = subject.getAllBreeds()

        //then
        val expectedDogBreedDomains = mergeEntities(dogBreedEntities, cachedEntities).map { it.toDomainModel() }
        assertEquals(Result.Success(expectedDogBreedDomains), actual)
    }

    @Test
    fun `getAllBreeds handles api exception and fallbacks to database`() = runTest {
        //given
        coEvery { dogBreedService.getAllDogBreeds() } returns ApiResult.Exception(
            UnknownHostException()
        )
        coEvery { dogBreedDao.getAllBreeds() } returns cachedEntities

        //when
        val actual = subject.getAllBreeds()

        //then
        val domainBreeds = cachedEntities.map { it.toDomainModel() }
        coVerify(exactly = 0) { dogBreedDao.saveBreeds(any()) }
        assertEquals(Result.Success(domainBreeds), actual)
    }

    @Test
    fun `getAllBreeds handles api error`() = runTest {
        //given
        coEvery { dogBreedService.getAllDogBreeds() } returns ApiResult.Error(
            500,
            "Something went wrong"
        )

        //when
        val actual = subject.getAllBreeds()

        //then
        coVerify(exactly = 0) { dogBreedDao.getAllBreeds() }
        coVerify(exactly = 0) { dogBreedDao.saveBreeds(any()) }
        assert(actual is Result.Failure)
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