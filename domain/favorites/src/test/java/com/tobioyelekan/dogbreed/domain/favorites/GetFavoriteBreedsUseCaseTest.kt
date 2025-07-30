package com.tobioyelekan.dogbreed.domain.favorites

import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetFavoriteBreedsUseCaseTest {
    private val repository: DogBreedDetailsRepositoryImpl = mockk()
    private val subject = GetFavoriteBreedsUseCase(repository)

    @Test
    fun `get favorites breeds returns breeds successfully`() = runTest {
        //given
        coEvery { repository.getFavoriteBreeds() } returns flowOf(Result.success(dogBreeds))

        //when
        val actual = subject()

        //then
        assertEquals(Result.success(dogBreeds), actual.first())
    }

    @Test
    fun `get favorites breeds returns error when repository throws error`() = runTest {
        //given
        coEvery { repository.getFavoriteBreeds() } returns
                flowOf(Result.failure(Exception("Something went wrong")))

        //when
        val actual = subject()

        //then
        assertTrue(actual.first().isFailure)
    }
}