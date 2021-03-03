package com.skilos.data

interface DogRepository {

    suspend fun fetchDogs(): List<String>
}