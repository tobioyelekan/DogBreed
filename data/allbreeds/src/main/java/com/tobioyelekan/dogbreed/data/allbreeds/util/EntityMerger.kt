package com.tobioyelekan.dogbreed.data.allbreeds.util

import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity

/**
isFavorite doesn't come from the backend, hence when new dogBreeds come from the server, mapping it to entities makes all the breeds isFavorites field false by default,
to fix this, first get the locally stored breeds with `true` value
and then use that to update the fields gotten from the internet where their name (unique identity) matches.
 **/
fun mergeEntities(
    apiEntities: List<DogBreedEntity>,
    cachedEntities: List<DogBreedEntity>
): List<DogBreedEntity> {
    // Convert lists to maps for quick lookup
    val entities = apiEntities.map { apiEntity ->
        if (cachedEntities.find { cachedEntity ->
                apiEntity.name == cachedEntity.name && cachedEntity.isFavorite
            } != null) {
            apiEntity.copy(isFavorite = true)
        } else {
            apiEntity
        }
    }
    return entities
}
