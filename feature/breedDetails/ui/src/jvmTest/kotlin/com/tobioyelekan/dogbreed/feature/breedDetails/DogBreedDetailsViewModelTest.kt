package com.tobioyelekan.dogbreed.feature.breedDetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.tobioyelekan.dogbreed.core.common.toTitleCase
import com.tobioyelekan.dogbreed.core.testing.MainDispatcherRule
import com.tobioyelekan.dogbreed.core.testing.TestData
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.AddFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.DeleteFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.GetBreedDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random
import kotlin.test.assertEquals

internal class DogBreedDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getBreedDetailsUseCase = mockk<GetBreedDetailsUseCase>()
    private val addFavoriteBreedUseCase = mockk<AddFavoriteBreedUseCase>()
    private val deleteFavoriteBreedUseCase = mockk<DeleteFavoriteBreedUseCase>()
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var viewModel: DogBreedDetailsViewModel

    @Before
    fun setup() {
        every { savedStateHandle.get<String>(any()) } returns "breedName"
    }

    @Test
    fun `emit value from savedStateHandle as app bar title when viewmodel is launched`() =
        runTest {
            coEvery { getBreedDetailsUseCase(any()) } returns flowOf(Result.success(TestData.dogBreeds[0]))

            initializeViewModel()

            viewModel.appBarTitle.test {
                assertEquals("breedName".toTitleCase(), awaitItem())
            }
        }

    @Test
    fun `emit success when usecase returns success`() = runTest {
        // Given
        val expectedDog = TestData.dogBreeds[0]
        coEvery { getBreedDetailsUseCase(any()) } returns flowOf(Result.success(expectedDog))

        initializeViewModel()

        // When & Then
        viewModel.uiState.test {
            val item = awaitItem()

            assert(item is DogBreedDetailsUIState.Success)
            assertEquals(expectedDog, (item as DogBreedDetailsUIState.Success).breedDetails)
        }
    }

    @Test
    fun `emit error when usecase returns error`() = runTest {
        coEvery { getBreedDetailsUseCase(any()) } returns
                flowOf(Result.failure(Exception("Something went wrong")))

        initializeViewModel()

        viewModel.uiState.test {
            val item = awaitItem()
            assert(item is DogBreedDetailsUIState.Error)
            assertEquals("Something went wrong", (item as DogBreedDetailsUIState.Error).message)
        }
    }

    @Test
    fun `on add favorite emits success message`() = runTest {
        coEvery { getBreedDetailsUseCase(any()) } returns flowOf(Result.success(TestData.dogBreeds[0]))
        coEvery { addFavoriteBreedUseCase(any()) } returns Result.success(Unit)

        initializeViewModel()

        viewModel.actionState.test {
            viewModel.onFavoriteClicked(false)

            val item = awaitItem()

            assertEquals(
                "Added as favorite",
                (item as DogBreedDetailsViewModel.ActionState.ShowMessage).message
            )
            coVerify { addFavoriteBreedUseCase("breedName") }
        }
    }

    @Test
    fun `on remove favorite emits success`() = runTest {
        coEvery { getBreedDetailsUseCase(any()) } returns flowOf(Result.success(TestData.dogBreeds[0]))
        coEvery { deleteFavoriteBreedUseCase(any()) } returns Result.success(Unit)

        initializeViewModel()

        viewModel.actionState.test {
            viewModel.onFavoriteClicked(true)

            val item = awaitItem()

            assertEquals(
                "Removed as favorite",
                (item as DogBreedDetailsViewModel.ActionState.ShowMessage).message
            )
            coVerify { deleteFavoriteBreedUseCase("breedName") }
        }
    }

    @Test
    fun `emits error adding or removing favorite breed`() = runTest {
        coEvery { getBreedDetailsUseCase(any()) } returns flowOf(Result.success(TestData.dogBreeds[0]))
        coEvery { addFavoriteBreedUseCase(any()) } returns
                Result.failure(Exception("Something went wrong"))
        coEvery { deleteFavoriteBreedUseCase(any()) } returns
                Result.failure(Exception("Something went wrong"))

        initializeViewModel()

        viewModel.actionState.test {
            viewModel.onFavoriteClicked(Random.nextBoolean())

            val item = awaitItem()

            assertEquals(
                "Something went wrong",
                (item as DogBreedDetailsViewModel.ActionState.ShowMessage).message
            )
        }
    }

    private fun initializeViewModel() {
        viewModel = DogBreedDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getBreedDetailsUseCase = getBreedDetailsUseCase,
            addFavoriteBreedUseCase = addFavoriteBreedUseCase,
            deleteFavoriteBreedUseCase = deleteFavoriteBreedUseCase,
        )
    }
}