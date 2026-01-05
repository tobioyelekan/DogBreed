package com.tobioyelekan.dogbreed.core.testing.integration

import androidx.room.Room
import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.network.api.DogBreedApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val testDatabaseModule: Module = module {
    single<DogBreedDatabase> {
        Room.inMemoryDatabaseBuilder(
            get(),
            DogBreedDatabase::class.java
        ).build()
    }

    single<DogBreedDao> {
        get<DogBreedDatabase>().breedDao()
    }
}

val testNetworkModule = module {
    single<DogBreedApiService> { FakeDogBreedApiService() }
}

val testCoroutineDispatcherModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }
}