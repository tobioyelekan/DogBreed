package com.tobioyelekan.dogbreed.feature.allbreeds

import com.tobioyelekan.dogbreed.domain.allbreeds.GetDogBreedListUseCase
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import com.tobioyelekan.dogbreed.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class AllBreedsViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val coroutineTestDispatcher = UnconfinedTestDispatcher()

    private val useCase: GetDogBreedListUseCase = mockk(relaxed = true)

    private lateinit var viewModel: AllBreedsViewModel

    @Test
    fun stateIsInitiallyLoading() = runTest{
        coEvery { useCase.invoke() } coAnswers {
            delay(1000)
            Result.success(dogBreeds)
        }
        viewModel = AllBreedsViewModel(useCase, coroutineTestDispatcher)

        assert(viewModel.uiState.value is AllBreedsUiState.Loading)
    }

    @Test
    fun `emit success when usecase returns list of breeds`() = runTest {
        coEvery { useCase.invoke() } returns Result.success(dogBreeds)

        viewModel = AllBreedsViewModel(useCase, coroutineTestDispatcher)

        assert(viewModel.uiState.value is AllBreedsUiState.Success)
        assertEquals(dogBreeds, (viewModel.uiState.value as AllBreedsUiState.Success).dogBreeds,)
    }

    @Test
    fun `emit error when usecase returns error`() = runTest {
        coEvery { useCase.invoke() } returns Result.failure(Exception("Something went wrong"))

        viewModel = AllBreedsViewModel(useCase, coroutineTestDispatcher)

        assert(viewModel.uiState.value is AllBreedsUiState.Error)
    }
}