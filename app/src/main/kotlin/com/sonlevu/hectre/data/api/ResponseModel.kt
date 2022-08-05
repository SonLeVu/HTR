package com.sonlevu.hectre.data.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrchardJobResponse(
    val orchardId: String?
)