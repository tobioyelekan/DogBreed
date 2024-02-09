package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class AddFavoriteBreedUseCaseTest {
    private val repository: DogBreedDetailsRepositoryImpl = mockk()
    private val subject = AddFavoriteBreedUseCase(repository)

    @Test
    fun `add favorite returns success`() = runTest {
        //given
        coEvery { repository.addFavoriteBreed(any()) } returns Result.Success(Unit)

        //when
        val actual = subject("breedName")

        //then
        assertEquals(Result.Success(Unit), actual)
    }

    @Test
    fun `add favorite return error when repository throws error`() = runTest {
        //given
        coEvery { repository.addFavoriteBreed(any()) } returns Result.Failure("Something went wrong")

        //when
        val actual = subject("breedName")

        //then
        assert(actual is Result.Failure)
    }
}