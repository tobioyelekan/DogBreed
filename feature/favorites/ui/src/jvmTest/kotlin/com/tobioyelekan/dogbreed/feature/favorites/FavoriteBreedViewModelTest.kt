package com.tobioyelekan.dogbreed.feature.favorites

import app.cash.turbine.test
import com.tobioyelekan.dogbreed.core.testing.MainDispatcherRule
import com.tobioyelekan.dogbreed.core.testing.TestData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class FavoriteBreedViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetFavoriteBreedsUseCase = mockk(relaxed = true)
    private lateinit var viewModel: FavoriteBreedViewModel

    @Test
    fun stateIsInitiallyLoading() = runTest {
        // Given
        coEvery { useCase.invoke() } returns flow { }
        viewModel = FavoriteBreedViewModel(useCase)

        // Then
        viewModel.uiState.test {
            assertEquals(FavoriteBreedUIState.Loading, awaitItem())
        }
    }

    @Test
    fun `emit favorite breeds when usecase returns success and list is not empty`() = runTest {
        // Given
        coEvery { useCase.invoke() } returns flowOf(Result.success(favoriteBreeds))
        viewModel = FavoriteBreedViewModel(useCase)

        // When & Then
        viewModel.uiState.test {
            val state = awaitItem()

            assert(state is FavoriteBreedUIState.Success)
            assertEquals(favoriteBreeds, (state as FavoriteBreedUIState.Success).favoriteBreeds)
        }
    }

    @Test
    fun `emit error state when usecase returns error`() = runTest {
        // Given
        coEvery { useCase.invoke() } returns flowOf(Result.failure(Exception("Error")))
        viewModel = FavoriteBreedViewModel(useCase)

        // When & Then
        viewModel.uiState.test {
            val state = awaitItem()

            assert(state is FavoriteBreedUIState.Error)
            assertEquals("something went wrong", (state as FavoriteBreedUIState.Error).message)
        }
    }

    private val favoriteBreeds = TestData.dogBreeds.filter { it.isFavorite }
}