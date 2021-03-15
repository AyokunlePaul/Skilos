package com.skilos.data.remote.model

data class BaseRemoteModel<T>(
    val message: T,
    val status: String
)