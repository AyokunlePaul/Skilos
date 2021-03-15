package com.skilos.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogDetail(
    val breed: String = "",
    val subBreed: String = "",
    val images: List<String> = emptyList()
) : Parcelable