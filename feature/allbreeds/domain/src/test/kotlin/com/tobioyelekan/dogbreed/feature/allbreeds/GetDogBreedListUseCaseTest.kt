package com.tobioyelekan.dogbreed.feature.allbreeds

import com.tobioyelekan.dogbreed.feature.allbreeds.repository.DogBreedsRepository
import com.tobioyelekan.dogbreed.feature.allbreeds.usecase.GetDogBreedListUseCase
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetDogBreedListUseCaseTest {
    private val dogBreedRepository: DogBreedsRepository = mockk()
    private val subject = GetDogBreedListUseCase(dogBreedRepository)

    @Test
    fun `return list of breeds`() = runTest {
        //given
        coEvery { dogBreedRepository.getAllBreeds() } returns Result.success(dogBreeds)

        //when
        val actual = subject()

        //then
        assertEquals(Result.success(dogBreeds), actual)
    }

    @Test
    fun `return error when repository throws error`() = runTest {
        //given
        coEvery { dogBreedRepository.getAllBreeds() } returns
                Result.failure(Exception("something went wrong"))

        //when
        val actual = subject()

        //then
        assertTrue(actual.isFailure)

    }
}