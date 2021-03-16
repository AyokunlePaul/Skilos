package com.skilos.presentation

import app.cash.turbine.test
import com.skilos.data.DogRepository
import com.skilos.models.DogDetail
import com.skilos.presentation.breeddetail.DogBreedDetailFragmentViewModel
import com.skilos.utils.ResponseUtilities
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.`should be equal to`
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DogBreedDetailViewModelTest {

    @MockK
    lateinit var repository: DogRepository

    private lateinit var viewModel: DogBreedDetailFragmentViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DogBreedDetailFragmentViewModel(repository)
    }

    @Test
    fun `test that dog breed image is fetched appropriately`() {
        val expected = ResponseUtilities.expectedDogBreedImageResponse()
        runBlocking {
            coEvery { repository.getBreedImages("") } returns expected
            viewModel.getImages(DogDetail())

            coVerify(exactly = 1) { repository.getBreedImages("") }
            viewModel.images.take(1).test {
                expectItem() `should be equal to`
                    DogBreedDetailFragmentViewModel.DogBreedDetailResponse.Success(expected)
                expectComplete()
            }
        }
    }

    @Test
    fun `test that dog sub-breed image is fetched appropriately`() {
        val expected = ResponseUtilities.expectedDogBreedImageResponse()
        runBlocking {
            coEvery { repository.getSubBreedImages("", "sub") } returns expected
            viewModel.getImages(DogDetail(subBreed = "sub"))

            coVerify(exactly = 1) { repository.getSubBreedImages("", "sub") }
            viewModel.images.take(1).test {
                expectItem() `should be equal to`
                    DogBreedDetailFragmentViewModel.DogBreedDetailResponse.Success(expected)
                expectComplete()
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}