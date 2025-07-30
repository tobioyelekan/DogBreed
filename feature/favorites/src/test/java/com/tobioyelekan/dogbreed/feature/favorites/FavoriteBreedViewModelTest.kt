package com.tobioyelekan.dogbreed.feature.favorites

import com.tobioyelekan.dogbreed.domain.favorites.GetFavoriteBreedsUseCase
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import com.tobioyelekan.dogbreed.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class FavoriteBreedViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val coroutineTestDispatcher = UnconfinedTestDispatcher()

    private val useCase: GetFavoriteBreedsUseCase = mockk(relaxed = true)
    private lateinit var viewModel: FavoriteBreedViewModel

    @Test
    fun stateIsInitiallyLoading() = runTest {
        viewModel = FavoriteBreedViewModel(useCase)
        assert(viewModel.uiState.value is FavoriteBreedUIState.Loading)
    }

    @Test
    fun `emit favorite breeds when usecase returns success and list is not empty`() = runTest {
        coEvery { useCase.invoke() } returns flowOf(Result.success(favoriteBreeds))
        viewModel = FavoriteBreedViewModel(useCase)

        val collectJob = launch(coroutineTestDispatcher) {
            viewModel.uiState.collect()
        }

        assert(viewModel.uiState.value is FavoriteBreedUIState.Success)
        assertEquals(
            favoriteBreeds,
            (viewModel.uiState.value as FavoriteBreedUIState.Success).favoriteBreeds,
        )

        collectJob.cancel()
    }

    @Test
    fun `emit error state when usecase returns error`() = runTest {
        coEvery { useCase.invoke() } returns
                flowOf(Result.failure(Exception("Something went wrong")))
        viewModel = FavoriteBreedViewModel(useCase)

        val collectJob = launch(coroutineTestDispatcher) {
            viewModel.uiState.collect()
        }

        assert(viewModel.uiState.value is FavoriteBreedUIState.Error)
        collectJob.cancel()
    }

    private val favoriteBreeds = dogBreeds.filter { it.isFavorite }

}