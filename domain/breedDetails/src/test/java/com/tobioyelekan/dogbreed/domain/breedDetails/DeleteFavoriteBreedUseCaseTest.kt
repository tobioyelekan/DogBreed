package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class DeleteFavoriteBreedUseCaseTest {
    private val repository: DogBreedDetailsRepositoryImpl = mockk()
    private val subject = DeleteFavoriteBreedUseCase(repository)

    @Test
    fun `remove favorite returns success`() = runTest {
        //given
        coEvery { repository.removeFavoriteBreed(any()) } returns Result.success(Unit)

        //when
        val actual = subject("breedName")

        //then
        assertEquals(Result.success(Unit), actual)
    }

    @Test
    fun `remove favorite return error when repository throws error`() = runTest {
        //given
        coEvery { repository.removeFavoriteBreed(any()) } returns
                Result.failure(Exception("Something went wrong"))

        //when
        val actual = subject("breedName")

        //then
        assertTrue(actual.isFailure)
    }
}