package com.skilos.data

import com.skilos.models.DogDetail

interface DogRepository {

    suspend fun getDogBreeds(): List<DogDetail>

    suspend fun getSubBreeds(breed: String): List<String>

    suspend fun getBreedImages(breed: String): List<String>

    suspend fun getSubBreedImages(breed: String, subBreed: String): List<String>
}