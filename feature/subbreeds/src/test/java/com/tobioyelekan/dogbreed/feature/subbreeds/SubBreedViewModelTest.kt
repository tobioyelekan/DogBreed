package com.tobioyelekan.dogbreed.feature.subbreeds

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.tobioyelekan.dogbreed.core.common.util.toTitleCase
import com.tobioyelekan.dogbreed.domain.subbreeds.GetSubBreedImageUseCase
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.breedNameArgs
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.subBreedNameArgs
import com.tobioyelekan.dogbreed.testing.data.TestData.subBreedImages
import com.tobioyelekan.dogbreed.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SubBreedViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetSubBreedImageUseCase = mockk(relaxed = true)
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var viewModel: SubBreedViewModel

    @Before
    fun setup() {
        every { savedStateHandle.get<String>(breedNameArgs) } returns "breedName"
        every { savedStateHandle.get<String>(subBreedNameArgs) } returns "subBreedName"
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        coEvery { useCase.invoke(any(), any()) } coAnswers {
            delay(1000)
            Result.success(subBreedImages)
        }

        viewModel = SubBreedViewModel(savedStateHandle, useCase)

        assert(viewModel.uiState.value is SubBreedUIState.Loading)
    }

    @Test
    fun `emit value from savedStateHandle as app bar title when viewmodel is launched`() =
        runTest {
            coEvery { useCase.invoke(any(), any()) } returns Result.success(subBreedImages)
            viewModel = SubBreedViewModel(savedStateHandle, useCase)

            viewModel.appBarTitle.test {
                val appBarTitle = "${"breedName".toTitleCase()} ${"subBreedName".toTitleCase()}"
                assertEquals(appBarTitle, awaitItem())
            }
        }

    @Test
    fun `emit subbreed images when usecase returns success`() =
        runTest {
            coEvery { useCase.invoke(any(), any()) } returns Result.success(subBreedImages)
            viewModel = SubBreedViewModel(savedStateHandle, useCase)

            viewModel.uiState.test {
                val item = awaitItem()
                assert(viewModel.uiState.value is SubBreedUIState.Success)
                assertEquals(subBreedImages, (item as SubBreedUIState.Success).images)
            }
        }

    @Test
    fun `emit error when usecase returns success`() =
        runTest {
            coEvery { useCase.invoke(any(), any()) } returns
                    Result.failure(Exception("something went wrong"))
            viewModel = SubBreedViewModel(savedStateHandle, useCase)

            viewModel.uiState.test {
                val item = awaitItem()
                assert(viewModel.uiState.value is SubBreedUIState.Error)
                assertEquals("something went wrong", (item as SubBreedUIState.Error).message)
            }
        }
}