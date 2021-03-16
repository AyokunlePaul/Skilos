package com.skilos.di.remote

import com.skilos.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        val builder = Retrofit.Builder().apply {
            client(client)
            addConverterFactory(converterFactory)
            baseUrl(BuildConfig.BASE_URL)
        }
        return builder.build()
    }
}