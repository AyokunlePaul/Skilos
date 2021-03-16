package com.skilos.data.remote

import com.skilos.data.remote.model.BaseRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {

    @GET("breeds/list/all")
    suspend fun getDogBreeds(): BaseRemoteModel<HashMap<String, List<String>>>

    @GET("breed/{breed}/list")
    suspend fun getSubBreed(@Path("breed") breed: String): BaseRemoteModel<List<String>>

    @GET("breed/{breed}/images/random/6")
    suspend fun getBreedImages(@Path("breed") breed: String): BaseRemoteModel<List<String>>

    @GET("breed/{breed}/{sub-breed}/images/random/6")
    suspend fun getSubBreedImages(
        @Path("breed") breed: String,
        @Path("sub-breed") subBreed: String
    ): BaseRemoteModel<List<String>>
}