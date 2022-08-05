package com.sonlevu.hectre.data.api

import com.sonlevu.hectre.domain.model.jobs.HectreJob
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrchardJobResponse(
    val orchardId: String?,
    val hectreJobList: ArrayList<HectreJob>
)