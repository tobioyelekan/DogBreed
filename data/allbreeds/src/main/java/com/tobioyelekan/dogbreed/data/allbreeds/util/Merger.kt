package com.tobioyelekan.dogbreed.data.allbreeds.util

import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity

/**
isFavorite doesn't come from the backend, hence when a refresh to the server is called, isFavorite field in the db is overwritten to false,
to avoid that we first get the locally stored breeds with `true` value
and then use that to update the fields gotten from the internet where their name (unique identity) matches.
 **/
fun mergeLists(
    apiEntities: List<DogBreedEntity>,
    cachedEntities: List<DogBreedEntity>
): List<DogBreedEntity> {
    // Convert lists to maps for quick lookup
    val mappedApiEntities = apiEntities.associateBy { it.name }.toMutableMap()
    val mappedCachedEntities = cachedEntities.associateBy { it.name }

    // Merge maps while prioritizing isFavorite from mappedCachedEntities when it is 'true'
    mappedCachedEntities.forEach { (id, item) ->
        if (mappedApiEntities.containsKey(id) && item.isFavorite) {
            mappedApiEntities[id] = item
        }
    }

    // Convert the merged map back to a list
    return mappedApiEntities.values.toList()
}