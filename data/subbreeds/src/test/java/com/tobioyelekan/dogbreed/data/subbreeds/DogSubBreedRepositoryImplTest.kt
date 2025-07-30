package com.tobioyelekan.dogbreed.data.subbreeds

import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel
import com.tobioyelekan.dogbreed.data.subbreeds.mapper.toDomain
import com.tobioyelekan.dogbreed.data.subbreeds.repository.DogSubBreedRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
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
        } returns sampleResponse

        //when
        val actual = subject.getSubBreeds("breedName", "subBreedName")

        //then
        val expected = sampleResponse.toDomain()
        coVerify { service.getSubBreedImages(any(), any()) }
        assertEquals(Result.success(expected), actual)
    }

    @Test
    fun `getSubBreeds handles api exception`() = runTest {
        //given
        coEvery {
            service.getSubBreedImages(
                any(),
                any()
            )
        } throws Exception("something went wrong")

        //when
        val actual = subject.getSubBreeds("breedName", "subBreedName")

        //then
        coVerify { service.getSubBreedImages(any(), any()) }
        assertTrue(actual.isFailure)
    }

    private val sampleResponse = SubBreedImageApiModel(listOf("image1", "image2", "image3"))
}