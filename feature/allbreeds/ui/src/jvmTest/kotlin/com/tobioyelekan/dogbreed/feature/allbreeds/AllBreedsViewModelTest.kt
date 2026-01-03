package com.tobioyelekan.dogbreed.feature.allbreeds

import com.tobioyelekan.dogbreed.core.testing.MainDispatcherRule
import com.tobioyelekan.dogbreed.core.testing.TestData
import com.tobioyelekan.dogbreed.feature.allbreeds.usecase.GetDogBreedListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class AllBreedsViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val useCase: GetDogBreedListUseCase = mockk(relaxed = true)

    private lateinit var viewModel: AllBreedsViewModel

    @Test
    fun stateIsInitiallyLoading() = runTest {
        coEvery { useCase.invoke() } coAnswers {
            delay(1000)
            Result.success(TestData.dogBreeds)
        }
        viewModel = AllBreedsViewModel(useCase)

        assert(viewModel.uiState.value is AllBreedsUiState.Loading)
    }

    @Test
    fun `emit success when usecase returns list of breeds`() = runTest {
        coEvery { useCase.invoke() } returns Result.success(TestData.dogBreeds)

        viewModel = AllBreedsViewModel(useCase)

        assert(viewModel.uiState.value is AllBreedsUiState.Success)
        assertEquals(
            TestData.dogBreeds,
            (viewModel.uiState.value as AllBreedsUiState.Success).dogBreeds,
        )
    }

    @Test
    fun `emit error when usecase returns error`() = runTest {
        coEvery { useCase.invoke() } returns Result.failure(Exception("Something went wrong"))

        viewModel = AllBreedsViewModel(useCase)

        assert(viewModel.uiState.value is AllBreedsUiState.Error)
    }
}