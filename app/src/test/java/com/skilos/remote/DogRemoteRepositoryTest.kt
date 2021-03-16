package com.skilos.remote

import com.skilos.data.remote.DogService
import com.skilos.data.remote.impl.DopRepositoryRemoteImpl
import com.skilos.enqueueDogBreedImageResponse
import com.skilos.enqueueDogBreedSuccess
import com.skilos.enqueueDogSubBreedImageResponse
import com.skilos.utils.ResponseUtilities
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.`should be equal to`
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DogRemoteRepositoryTest {

    private val server = MockWebServer()
    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val dogService = Retrofit.Builder()
        .baseUrl(server.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(DogService::class.java)

    private val repository = DopRepositoryRemoteImpl(dogService)

    @Test
    fun `fetch dog breeds correctly with 200`() {
        runBlocking {
            server.enqueueDogBreedSuccess()
            val expected = ResponseUtilities.expectedDogBreedResponse()
            val response = repository.getDogBreeds().sortedBy { it.breed }.take(expected.size)

            coVerify(exactly = 1) { dogService.getDogBreeds() }
            expected `should be equal to` response
        }
    }

    @Test
    fun `fetch dog breed images correctly`() {
        runBlocking {
            server.enqueueDogBreedImageResponse()
            val expected = ResponseUtilities.expectedDogBreedImageResponse()
            val response = repository.getBreedImages("")

            coVerify(exactly = 1) { dogService.getBreedImages("") }
            expected.size `should be equal to` response.size
            expected `should be equal to` response
        }
    }

    @Test
    fun `fetch dog sub-breed images correctly`() {
        runBlocking {
            server.enqueueDogSubBreedImageResponse()
            val expected = ResponseUtilities.expectedDogSubBreedImageResponse()
            val response = repository.getSubBreedImages("", "")

            coVerify(exactly = 1) { dogService.getSubBreedImages("", "") }
            expected.size `should be equal to` response.size
            expected `should be equal to` response
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}