package com.sonlevu.hectre.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonlevu.hectre.R
import com.sonlevu.hectre.data.api.OrchardJobResponse
import com.sonlevu.hectre.domain.repo.IOrchardRepository
import com.sonlevu.hectre.utils.ErrorResult
import com.sonlevu.hectre.utils.MutableResultFlow
import com.sonlevu.hectre.utils.SuccessResult
import com.sonlevu.hectre.utils.loadOrError
import kotlinx.coroutines.launch

class UpRateNVolViewModel(
    orchardID: String,
    orchardRepository: IOrchardRepository,
    showMessage: (Int) -> Unit
) : ViewModel() {
    private var orchardID: String
    private var orchardRepository: IOrchardRepository
    private var showMessage: (Int) -> Unit
    val dataFetchingResult = MutableResultFlow<OrchardJobResponse>()

    init {
        this.orchardID = orchardID
        this.orchardRepository = orchardRepository
        this.showMessage = showMessage
        this.refresh()
    }

    fun refresh() = viewModelScope.launch {
        dataFetchingResult.loadOrError(R.string.common_error_message) {
            orchardRepository.getJobListInOrchard(orchardID)
        }
        dataFetchingResult.also {
            when (it) {
                is ErrorResult<*> -> showMessage(it.message!!)
                is SuccessResult<*> -> {
                    updateData()
                }
                else -> {}
            }
        }
    }

    private fun updateData() {
        showMessage(R.string.common_success_message)
    }

    fun onOpen() {
        refresh()
    }
}
