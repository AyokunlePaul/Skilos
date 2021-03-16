package com.skilos.di.remote

import com.skilos.data.remote.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    internal fun provideAuthenticationService(retrofit: Retrofit): DogService {
        return retrofit.create(DogService::class.java)
    }
}