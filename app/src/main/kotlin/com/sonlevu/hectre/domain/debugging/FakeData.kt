package com.sonlevu.hectre.domain.debugging

import com.sonlevu.hectre.data.api.OrchardJobResponse

class FakeData {
    fun fakeAsyncRequest(): OrchardJobResponse {
        return OrchardJobResponse("dfsfsdfsd")
    }
}