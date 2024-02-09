package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetBreedDetailsUseCaseTest {
    private val repository: DogBreedDetailsRepositoryImpl = mockk()
    private val subject = GetBreedDetailsUseCase(repository)

    @Test
    fun `get breed details returns breed successfully`() = runTest {
        //given
        coEvery { repository.getBreedDetails(any()) } returns flowOf(Result.Success(dogBreeds[0]))

        //when
        val actual = subject("breedName")

        //then
        assertEquals(Result.Success(dogBreeds[0]), actual.first())
    }

    @Test
    fun `get breed details return error when repository throws error`() = runTest {
        //given
        coEvery { repository.getBreedDetails(any()) } returns flowOf(Result.Failure("Something went wrong"))

        //when
        val actual = subject("breedName")

        //then
        assert(actual.first() is Result.Failure)
    }
}