package com.skilos.di.remote

import com.skilos.data.DogRepository
import com.skilos.data.remote.impl.DopRepositoryRemoteImpl
import com.skilos.di.scopes.RemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteImplModule {

    @Binds
    @Singleton
    @RemoteImpl
    internal abstract fun bindDogRepositoryImpl(
        impl: DopRepositoryRemoteImpl
    ): DogRepository
}