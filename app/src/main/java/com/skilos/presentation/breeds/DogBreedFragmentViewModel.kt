package com.skilos.presentation.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skilos.data.DogRepository
import com.skilos.di.scopes.DataImpl
import com.skilos.models.DogDetail
import com.skilos.utils.SkilosException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedFragmentViewModel @Inject constructor(
    @DataImpl private val repository: DogRepository
) : ViewModel() {

    private val _dogBreeds = MutableStateFlow<DogBreedResponse>(DogBreedResponse.Loading)
    val dogBreeds: StateFlow<DogBreedResponse> get() = _dogBreeds.asStateFlow()

    fun getDogBreeds() {
        viewModelScope.launch {
            val response = runCatching { repository.getDogBreeds() }
            response.onSuccess { _dogBreeds.value = DogBreedResponse.Success(it) }
            response.onFailure {
                _dogBreeds.value = DogBreedResponse.Failure(
                    (it as SkilosException).message
                )
            }
        }
    }

    sealed class DogBreedResponse {
        object Loading : DogBreedResponse()
        data class Success(val data: List<DogDetail>) : DogBreedResponse()
        data class Failure(val message: String) : DogBreedResponse()
    }
}