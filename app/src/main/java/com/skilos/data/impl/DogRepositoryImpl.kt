package com.skilos.data.impl

import com.skilos.data.DogRepository
import com.skilos.di.scopes.RemoteImpl
import com.skilos.models.DogDetail
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    @RemoteImpl private val repository: DogRepository
) : DogRepository {

    override suspend fun getDogBreeds(): List<DogDetail> = repository.getDogBreeds()

    override suspend fun getSubBreeds(breed: String): List<String> = repository.getSubBreeds(breed)

    override suspend fun getBreedImages(breed: String): List<String> =
        repository.getBreedImages(breed)

    override suspend fun getSubBreedImages(breed: String, subBreed: String): List<String> =
        repository.getSubBreedImages(breed, subBreed)
}