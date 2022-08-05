package com.sonlevu.hectre.domain.debugging

import com.sonlevu.hectre.data.api.OrchardJobResponse
import com.sonlevu.hectre.domain.model.jobs.HectreJob
import com.sonlevu.hectre.domain.model.jobs.JobType

class FakeData {
    fun fakeAsyncRequest(): OrchardJobResponse {
        return OrchardJobResponse(
            orchardId = "VS2397",
            hectreJobList = arrayListOf(
                HectreJob(name = "Pruning", jobType = JobType.PRUNING),
                HectreJob(name = "Thinning", jobType = JobType.THINNING)
            )
        )
    }
}