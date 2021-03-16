package com.skilos.data.remote.impl

import com.skilos.data.DogRepository
import com.skilos.data.remote.DogService
import com.skilos.models.DogDetail
import com.skilos.utils.SkilosException
import javax.inject.Inject

class DopRepositoryRemoteImpl @Inject constructor(
    private val service: DogService
) : DogRepository {

    override suspend fun getDogBreeds(): List<DogDetail> = try {
        val response = service.getDogBreeds()
        response.message.flatMap { (key, value) ->
            listOf(key).map { DogDetail(breed = it) } + value.map {
                DogDetail(breed = key, subBreed = it)
            }
        }
    } catch (exception: Exception) {
        throw SkilosException(exception)
    }

    override suspend fun getSubBreeds(breed: String): List<String> = try {
        service.getSubBreed(breed).message
    } catch (exception: Exception) {
        throw SkilosException(exception)
    }

    override suspend fun getBreedImages(breed: String): List<String> = try {
        service.getBreedImages(breed).message
    } catch (exception: Exception) {
        throw SkilosException(exception)
    }

    override suspend fun getSubBreedImages(breed: String, subBreed: String): List<String> = try {
        service.getSubBreedImages(breed, subBreed).message
    } catch (exception: Exception) {
        throw SkilosException(exception)
    }
}