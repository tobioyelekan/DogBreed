package com.tobioyelekan.dogbreed.feature.breedDetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.common.util.toTitleCase
import com.tobioyelekan.dogbreed.domain.breedDetails.AddFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.domain.breedDetails.DeleteFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.domain.breedDetails.GetBreedDetailsUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.DogBreedDetailsViewModel.*
import com.tobioyelekan.dogbreed.testing.data.TestData.dogBreeds
import com.tobioyelekan.dogbreed.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class DogBreedDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val coroutineTestDispatcher = UnconfinedTestDispatcher()

    private val getBreedDetailsUseCase = mockk<GetBreedDetailsUseCase>(relaxed = true)
    private val addFavoriteBreedUseCase = mockk<AddFavoriteBreedUseCase>(relaxed = true)
    private val deleteFavoriteBreedUseCase = mockk<DeleteFavoriteBreedUseCase>(relaxed = true)
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var viewModel: DogBreedDetailsViewModel

    @Before
    fun setup() {
        every { savedStateHandle.get<String>(any()) } returns "breedName"
    }

    @Test
    fun `emit value from savedStateHandle as app bar title  when viewmodel is launched`() =
        runTest {
            initializeViewModel()

            viewModel.appBarTitle.test {
                assertEquals("breedName".toTitleCase(), awaitItem())
            }
        }

    @Test
    fun `emit success when usecase returns success`() = runTest {
        coEvery { getBreedDetailsUseCase.invoke(any()) } returns flowOf(Result.Success(dogBreeds[0]))

        initializeViewModel()

        val collectJob = launch(coroutineTestDispatcher) {
            viewModel.uiState.collect()
        }

        assert(viewModel.uiState.value is DogBreedDetailsUIState.Success)

        collectJob.cancel()
    }

    @Test
    fun `emit error when usecase returns error`() = runTest {
        coEvery { getBreedDetailsUseCase.invoke(any()) } returns flowOf(Result.Failure("Something went wrong"))

        initializeViewModel()

        val collectJob = launch(coroutineTestDispatcher) {
            viewModel.uiState.collect()
        }

        assert(viewModel.uiState.value is DogBreedDetailsUIState.Error)

        collectJob.cancel()
    }

    @Test
    fun `on add favorite emits success message`() = runTest {
        coEvery { addFavoriteBreedUseCase.invoke(any()) } returns Result.Success(Unit)

        initializeViewModel()

        viewModel.actionState.test {
            viewModel.onFavoriteClicked(false)

            val item = awaitItem()

            assertEquals( "Added as favorite", (item as ActionState.ShowMessage).message)
            coVerify { addFavoriteBreedUseCase("breedName") }
        }
    }

    @Test
    fun `on remove favorite emits success`() = runTest {
        coEvery { deleteFavoriteBreedUseCase.invoke(any()) } returns Result.Success(Unit)

        initializeViewModel()

        viewModel.actionState.test {
            viewModel.onFavoriteClicked(true)

            val item = awaitItem()

            assertEquals( "Removed as favorite", (item as ActionState.ShowMessage).message)
            coVerify { deleteFavoriteBreedUseCase("breedName") }
        }
    }

    @Test
    fun `emits error adding or removing favorite breed`() = runTest {
        coEvery { addFavoriteBreedUseCase.invoke(any()) } returns Result.Failure("Something went wrong")
        coEvery { deleteFavoriteBreedUseCase.invoke(any()) } returns Result.Failure("Something went wrong")

        initializeViewModel()

        viewModel.actionState.test {
            viewModel.onFavoriteClicked(Random.nextBoolean())

            val item = awaitItem()

            assertEquals( "Something went wrong", (item as ActionState.ShowMessage).message)
        }
    }

    private fun initializeViewModel() {
        viewModel = DogBreedDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getBreedDetailsUseCase = getBreedDetailsUseCase,
            addFavoriteBreedUseCase = addFavoriteBreedUseCase,
            deleteFavoriteBreedUseCase = deleteFavoriteBreedUseCase,
            ioDispatcher = coroutineTestDispatcher
        )
    }

}