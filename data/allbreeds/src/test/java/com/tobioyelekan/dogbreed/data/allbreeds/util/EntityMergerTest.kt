package com.tobioyelekan.dogbreed.data.allbreeds.util

import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import org.junit.Test
import kotlin.test.assertEquals

class EntityMergerTest {

    @Test
    fun `merge list should return dog1,dog2, and dog3 with its cached isFavorite`() {
        val apiEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog2", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog3", "imageUrl", listOf("subDog1"), false)
        )

        val cachedEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog2", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog3", "imageUrl", listOf("subDog1"), true)
        )

        val actualEntities = mergeEntities(apiEntities, cachedEntities)

        assertEquals(cachedEntities, actualEntities)
    }

    @Test
    fun `merge list should return same copies as nothing changed`() {
        val cachedEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog2", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog3", "imageUrl", listOf("subDog1"), false)
        )

        val apiEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog2", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog3", "imageUrl", listOf("subDog1"), false)
        )

        val actualEntities = mergeEntities(apiEntities, cachedEntities)

        assertEquals(cachedEntities, actualEntities)
    }

    @Test
    fun `merge list should return dog1,dog2,dog4 entities ignoring dog3`() {
        val apiEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog4", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog2", "updatedImageUrl", listOf("subDog1"), false),
        )

        val cachedEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog2", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog3", "imageUrl", listOf("subDog1"), true)
        )

        val actualEntities = mergeEntities(apiEntities, cachedEntities)

        assertEquals(apiEntities, actualEntities)
    }

    @Test
    fun `merge list should return dog1 item and copy the isFavorite cached value`() {
        val apiEntities = listOf(
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), false),
        )

        val cachedEntities = listOf(
            DogBreedEntity("dog3", "imageUrl", listOf("subDog1"), false),
            DogBreedEntity("dog1", "imageUrl", listOf("subDog1"), true),
            DogBreedEntity("dog4", "imageUrl", listOf("subDog1"), false)
        )

        val actualEntities = mergeEntities(apiEntities, cachedEntities)

        assertEquals(apiEntities.map { it.copy(isFavorite = true) }, actualEntities)
    }
}