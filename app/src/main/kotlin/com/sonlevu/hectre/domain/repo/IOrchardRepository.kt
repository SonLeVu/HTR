package com.sonlevu.hectre.domain.repo

import com.sonlevu.hectre.data.api.OrchardJobResponse

interface IOrchardRepository {
    suspend fun getJobListInOrchard(orchardId: String): OrchardJobResponse
}
