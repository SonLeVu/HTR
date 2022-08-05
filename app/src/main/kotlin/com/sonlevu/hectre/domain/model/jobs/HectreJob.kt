package com.sonlevu.hectre.domain.model.jobs

import com.sonlevu.hectre.domain.model.users.Staff

data class HectreJob(
    val name: String,
    val jobType: JobType,
    val staffList: ArrayList<Staff>
)