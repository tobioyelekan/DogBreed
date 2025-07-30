package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetBreedDetailsUseCaseTest {
    private val repository: DogBreedDetailsRepositoryImpl = mockk()
    private val subject = GetBreedDetailsUseCase(repository)

    @Test
    fun `get breed details returns breed successfully`() = runTest {
        //given
        coEvery { repository.getBreedDetails(any()) } returns flowOf(Result.success(dogBreeds[0]))

        //when
        val actual = subject("breedName")

        //then
        assertEquals(Result.success(dogBreeds[0]), actual.first())
    }

    @Test
    fun `get breed details return error when repository throws error`() = runTest {
        //given
        coEvery { repository.getBreedDetails(any()) } returns
                flowOf(Result.failure(Exception("Something went wrong")))

        //when
        val actual = subject("breedName")

        //then
        assertTrue(actual.first().isFailure)
    }
}