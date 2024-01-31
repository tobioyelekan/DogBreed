package com.tobioyelekan.dogbreed.data.subbreeds

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.adapter.ApiResult
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel
import com.tobioyelekan.dogbreed.data.subbreeds.mapper.toDomain
import com.tobioyelekan.dogbreed.data.subbreeds.repository.DogSubBreedRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.UnknownHostException
import kotlin.test.assertEquals

class DogSubBreedRepositoryImplTest {
    private val service: DogBreedApiService = mockk()
    private val subject = DogSubBreedRepositoryImpl(service)

    @Test
    fun `getSubBreeds handles api success and returns images`() = runTest {
        //given
        coEvery {
            service.getSubBreedImages(
                any(),
                any()
            )
        } returns ApiResult.Success(sampleResponse)

        //when
        val actual = subject.getSubBreeds("breedName", "subBreedName")

        //then
        val expected = sampleResponse.toDomain()
        coVerify { service.getSubBreedImages(any(), any()) }
        assertEquals(Result.Success(expected), actual)
    }

    @Test
    fun `getSubBreeds handles api exception`() = runTest {
        //given
        coEvery {
            service.getSubBreedImages(
                any(),
                any()
            )
        } returns ApiResult.Exception(UnknownHostException())

        //when
        val actual = subject.getSubBreeds("breedName", "subBreedName")

        //then
        coVerify { service.getSubBreedImages(any(), any()) }
        assert(actual is Result.Failure)
    }

    @Test
    fun `getSubBreeds handles api error`() = runTest {
        //given
        coEvery {
            service.getSubBreedImages(
                any(),
                any()
            )
        } returns ApiResult.Error(
            500,
            "Something went wrong"
        )

        //when
        val actual = subject.getSubBreeds("breedName", "subBreedName")

        //then
        coVerify { service.getSubBreedImages(any(), any()) }
        assert(actual is Result.Failure)
    }

    private val sampleResponse = SubBreedImageApiModel(listOf("image1", "image2", "image3"))
}