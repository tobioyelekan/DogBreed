package com.tobioyelekan.dogbreed.feature.allbreeds

import com.tobioyelekan.dogbreed.core.testing.TestData
import com.tobioyelekan.dogbreed.feature.allbreeds.repository.DogBreedsRepository
import com.tobioyelekan.dogbreed.feature.allbreeds.usecase.GetDogBreedListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetDogBreedListUseCaseTest {
    private val dogBreedRepository: DogBreedsRepository = mockk()
    private val subject = GetDogBreedListUseCase(dogBreedRepository)

    @Test
    fun `return list of breeds`() = runTest {
        //given
        coEvery { dogBreedRepository.getAllBreeds() } returns Result.success(TestData.dogBreeds)

        //when
        val actual = subject()

        //then
        assertEquals(Result.success(TestData.dogBreeds), actual)
    }

    @Test
    fun `return error when repository throws error`() = runTest {
        //given
        coEvery { dogBreedRepository.getAllBreeds() } returns
                Result.failure(Exception("something went wrong"))

        //when
        val actual = subject()

        //then
        TestCase.assertTrue(actual.isFailure)

    }
}