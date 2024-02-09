package com.tobioyelekan.dogbreed.domain.allbreeds

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.allbreeds.repository.DogBreedsRepositoryImpl
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetDogBreedListUseCaseTest {
    private val dogBreedRepository: DogBreedsRepositoryImpl = mockk()
    private val subject = GetDogBreedListUseCase(dogBreedRepository)

    @Test
    fun `return list of breeds`() = runTest {
        //given
        coEvery { dogBreedRepository.getAllBreeds() } returns Result.Success(dogBreeds)

        //when
        val actual = subject()

        //then
        assertEquals(Result.Success(dogBreeds), actual)
    }

    @Test
    fun `return error when repository throws error`() = runTest {
        //given
        coEvery { dogBreedRepository.getAllBreeds() } returns Result.Failure("something went wrong")

        //when
        val actual = subject()

        //then
        assert(actual is Result.Failure)

    }
}