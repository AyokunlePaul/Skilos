package com.skilos.di.data

import com.skilos.data.DogRepository
import com.skilos.data.impl.DogRepositoryImpl
import com.skilos.di.scopes.DataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataImplModule {

    @Binds
    @Singleton
    @DataImpl
    internal abstract fun bindDogRepository(
        impl: DogRepositoryImpl
    ): DogRepository
}