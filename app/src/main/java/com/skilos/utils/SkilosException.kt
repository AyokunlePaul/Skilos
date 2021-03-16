package com.skilos.utils

class SkilosException(exception: Exception) : Throwable() {
    override val message: String get() = "An exception has occurred"
}