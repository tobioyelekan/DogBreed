package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class DeleteFavoriteBreedUseCaseTest {
    private val repository: DogBreedDetailsRepositoryImpl = mockk()
    private val subject = DeleteFavoriteBreedUseCase(repository)

    @Test
    fun `remove favorite returns success`() = runTest {
        //given
        coEvery { repository.removeFavoriteBreed(any()) } returns Result.Success(Unit)

        //when
        val actual = subject("breedName")

        //then
        assertEquals(Result.Success(Unit), actual)
    }

    @Test
    fun `remove favorite return error when repository throws error`() = runTest {
        //given
        coEvery { repository.removeFavoriteBreed(any()) } returns Result.Failure("Something went wrong")

        //when
        val actual = subject("breedName")

        //then
        assert(actual is Result.Failure)
    }
}