package com.sonlevu.hectre.domain.model.users

import com.sonlevu.hectre.domain.model.field.Block

data class Staff(val firstName: String,
                 val lastName: String,
                 val userID: Int,
                 val workingBlock: Block,
                 val rate: Float)
