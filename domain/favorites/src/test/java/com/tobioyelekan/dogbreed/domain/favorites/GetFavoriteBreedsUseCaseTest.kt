package com.tobioyelekan.dogbreed.domain.favorites

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.favorites.repository.FavoriteBreedRepositoryImpl
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetFavoriteBreedsUseCaseTest {
    private val repository: FavoriteBreedRepositoryImpl = mockk()
    private val subject = GetFavoriteBreedsUseCase(repository)

    @Test
    fun `get favorites breeds returns breeds successfully`() = runTest {
        //given
        coEvery { repository.getFavoriteBreeds() } returns flowOf(Result.Success(dogBreeds))

        //when
        val actual = subject()

        //then
        assertEquals(Result.Success(dogBreeds), actual.first())
    }

    @Test
    fun `get favorites breeds returns error when repository throws error`() = runTest {
        //given
        coEvery { repository.getFavoriteBreeds() } returns flowOf(Result.Failure("Something went wrong"))

        //when
        val actual = subject()

        //then
        assert(actual.first() is Result.Failure)
    }
}