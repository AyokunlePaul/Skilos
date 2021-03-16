package com.skilos.data

import com.skilos.data.impl.DogRepositoryImpl
import com.skilos.utils.ResponseUtilities
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class DataRepositoryTest {

    @MockK
    lateinit var repository: DogRepository
    lateinit var impl: DogRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        impl = DogRepositoryImpl(repository)
    }

    @Test
    fun `test that get dog breeds returns the correct result`() {
        runBlocking {
            val expected = ResponseUtilities.expectedDogBreedResponse()
            coEvery { repository.getDogBreeds() } returns expected
            val actual = impl.getDogBreeds()

            coVerify(exactly = 1) { repository.getDogBreeds() }
            actual.size `should be equal to` expected.size
            actual `should be equal to` expected
        }
    }

    @Test
    fun `test that get dog breed images returns the correct result`() {
        runBlocking {
            val expected = ResponseUtilities.expectedDogBreedImageResponse()
            coEvery { repository.getBreedImages("") } returns expected
            val actual = impl.getBreedImages("")

            coVerify(exactly = 1) { repository.getBreedImages("") }
            actual.size `should be equal to` expected.size
            actual `should be equal to` expected
        }
    }

    @Test
    fun `test that get dog sub breed images returns the correct result`() {
        runBlocking {
            val expected = ResponseUtilities.expectedDogSubBreedImageResponse()
            coEvery { repository.getSubBreedImages("", "") } returns expected
            val actual = impl.getSubBreedImages("", "")

            coVerify(exactly = 1) { repository.getSubBreedImages("", "") }
            actual.size `should be equal to` expected.size
            actual `should be equal to` expected
        }
    }
}