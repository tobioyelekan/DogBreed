package com.tobioyelekan.dogbreed.core.database.di

import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import com.tobioyelekan.dogbreed.core.database.createDatabase
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import org.koin.core.module.Module
import org.koin.dsl.module

val dogBreedDatabaseModule: Module = module {
    includes(platformModule)

    single<DogBreedDatabase> {
        createDatabase(get())
    }

    single<DogBreedDao> {
        get<DogBreedDatabase>().breedDao()
    }
}