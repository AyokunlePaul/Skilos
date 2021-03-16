package com.skilos.presentation.breeddetail

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
class DogBreedDetailFragmentViewModel @Inject constructor(
    @DataImpl private val repository: DogRepository
) : ViewModel() {

    private val _images =
        MutableStateFlow<DogBreedDetailResponse>(DogBreedDetailResponse.Loading)
    val images: StateFlow<DogBreedDetailResponse> get() = _images.asStateFlow()

    fun getImages(detail: DogDetail) {
        viewModelScope.launch {
            val response = runCatching {
                if (detail.subBreed.isEmpty()) repository.getBreedImages(detail.breed)
                else repository.getSubBreedImages(detail.breed, detail.subBreed)
            }
            response.onSuccess {
                _images.value = DogBreedDetailResponse.Success(it)
            }
            response.onFailure {
                _images.value = DogBreedDetailResponse.Failure(
                    (it as SkilosException).message
                )
            }
        }
    }

    sealed class DogBreedDetailResponse {
        object Loading : DogBreedDetailResponse()
        data class Success(val data: List<String>) : DogBreedDetailResponse()
        data class Failure(val message: String) : DogBreedDetailResponse()
    }
}