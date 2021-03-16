package com.skilos

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

private fun MockWebServer.enqueueResponse(path: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(path)
    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse().setResponseCode(code).setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}

internal fun MockWebServer.enqueueDogBreedSuccess() {
    enqueueResponse("breeds.json", 200)
}

internal fun MockWebServer.enqueueDogBreedImageResponse() {
    enqueueResponse("breed_images.json", 200)
}

internal fun MockWebServer.enqueueDogSubBreedImageResponse() {
    enqueueResponse("sub_breed_images.json", 200)
}