package com.sonlevu.hectre.data.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrchardJobRequest(
    val password: String,
    val username: String,
    val type: String
)