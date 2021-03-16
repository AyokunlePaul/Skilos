package com.skilos.utils

import com.skilos.models.DogDetail

object ResponseUtilities {
    internal fun expectedDogBreedResponse(): List<DogDetail> {
        return listOf(
            DogDetail(
                breed = "australian",
                subBreed = "",
                images = emptyList()
            ),
            DogDetail(
                breed = "australian",
                subBreed = "shepherd",
                images = emptyList()
            ),
            DogDetail(
                breed = "basenji",
                subBreed = "",
                images = emptyList()
            ),
            DogDetail(
                breed = "buhund",
                subBreed = "",
                images = emptyList()
            ),
            DogDetail(
                breed = "buhund",
                subBreed = "norwegian",
                images = emptyList()
            ),
            DogDetail(
                breed = "bulldog",
                subBreed = "",
                images = emptyList()
            ),
            DogDetail(
                breed = "bulldog",
                subBreed = "boston",
                images = emptyList()
            ),
            DogDetail(
                breed = "bulldog",
                subBreed = "english",
                images = emptyList()
            ),
            DogDetail(
                breed = "bulldog",
                subBreed = "french",
                images = emptyList()
            )
        )
    }

    internal fun expectedDogBreedImageResponse(): List<String> {
        return listOf(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_10982.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_11006.jpg"
        )
    }

    internal fun expectedDogSubBreedImageResponse(): List<String> {
        return listOf(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1150.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_11570.jpg"
        )
    }
}