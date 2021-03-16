package com.skilos.presentation

import app.cash.turbine.test
import com.skilos.data.DogRepository
import com.skilos.presentation.breeds.DogBreedFragmentViewModel
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
class DogBreedViewModelTest {

    @MockK
    lateinit var repository: DogRepository

    private lateinit var viewModel: DogBreedFragmentViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DogBreedFragmentViewModel(repository)
    }

    @Test
    fun `test that dog breed is fetch appropriately`() {
        val expected = ResponseUtilities.expectedDogBreedResponse()
        runBlocking {
            coEvery { repository.getDogBreeds() } returns expected
            viewModel.getDogBreeds()

            coVerify(exactly = 1) { repository.getDogBreeds() }
            viewModel.dogBreeds.take(1).test {
                expectItem() `should be equal to`
                    DogBreedFragmentViewModel.DogBreedResponse.Success(
                        expected
                    )
                expectComplete()
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}