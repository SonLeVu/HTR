package com.sonlevu.hectre.domain.debugging

import com.sonlevu.hectre.data.api.OrchardJobResponse
import com.sonlevu.hectre.domain.model.jobs.HectreJob
import com.sonlevu.hectre.domain.model.jobs.JobType
import com.sonlevu.hectre.domain.model.users.Staff

class FakeData {
    fun fakeAsyncRequest(): OrchardJobResponse {
        return OrchardJobResponse(
            orchardId = "VS2397",
            hectreJobList = arrayListOf(
                HectreJob(
                    name = "Pruning", jobType = JobType.PRUNING,
                    staffList = arrayListOf(
                        Staff(
                            firstName = "Bob", lastName = "Sinbad", userID = 11, rate = 35f,
                            workingBlock = "UJ123"
                        ),
                        Staff(
                            firstName = "Sarah", lastName = "Sinbad", userID = 12, rate = 39f,
                            workingBlock = "UJ123"
                        )
                    )
                ),
                HectreJob(
                    name = "Thinning", jobType = JobType.THINNING,
                    staffList = arrayListOf(
                        Staff(
                            firstName = "Darijan", lastName = "Sinbad", userID = 11, rate = 31f,
                            workingBlock = "UJ124"
                        )
                    )
                )
            )
        )
    }
}