package com.tobioyelekan.dogbreed.domain.subbreeds

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.subbreeds.repository.DogSubBreedRepositoryImpl
import com.tobioyelekan.dogbreed.testing.data.TestData.subBreedImages
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetSubBreedImageUseCaseTest {
    private val repository: DogSubBreedRepositoryImpl = mockk()
    private val subject = GetSubBreedImageUseCase(repository)

    @Test
    fun `return list of favorites breeds`() = runTest {
        //given
        coEvery { repository.getSubBreeds(any(), any()) } returns Result.Success(subBreedImages)

        //when
        val actual = subject("breedName", "subBreedName")

        //then
        assertEquals(Result.Success(subBreedImages), actual)
    }

    @Test
    fun `return error when repository throws error`() = runTest {
        //given
        coEvery { repository.getSubBreeds(any(), any()) } returns Result.Failure("something went wrong")

        //when
        val actual = subject("breedName", "subBreedName")

        //then
        assert(actual is Result.Failure)

    }
}