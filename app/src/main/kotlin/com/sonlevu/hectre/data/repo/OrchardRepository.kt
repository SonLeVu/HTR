package com.sonlevu.hectre.data.repo

import com.sonlevu.hectre.data.api.OrchardJobResponse
import com.sonlevu.hectre.domain.debugging.FakeData
import com.sonlevu.hectre.domain.repo.IOrchardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun <T> withIO(block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.IO, block)

class OrchardRepository: IOrchardRepository {
    override suspend fun getJobListInOrchard(orchardId: String) : OrchardJobResponse = withIO {
        // Do some request with retrofit 2 for example
        delay(1000)
        return@withIO FakeData().fakeAsyncRequest()
    }
}