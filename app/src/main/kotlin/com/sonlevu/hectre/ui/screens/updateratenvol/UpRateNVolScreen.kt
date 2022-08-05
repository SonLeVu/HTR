package com.sonlevu.hectre.ui.screens.updateratenvol

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sonlevu.hectre.R
import com.sonlevu.hectre.domain.repo.IOrchardRepository
import com.sonlevu.hectre.utils.LoadingResult
import com.sonlevu.hectre.utils.SuccessResult

@Composable
fun UpRateNVolScreen(
    orchardID: String,
    showMessage: (Int) -> Unit,
    orchardRepository: IOrchardRepository,
) {
    val viewModel = UpRateNVolViewModel(orchardID, orchardRepository, showMessage)
    val dataFetchingResult by viewModel.dataFetchingResult.collectAsState()
    dataFetchingResult.takeIf { it is SuccessResult }?.data?.let {
        //showMessage(R.string.common_success_message)
    }
    if (orchardID.isEmpty() || dataFetchingResult.data == null) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Placeholder for ${stringResource(R.string.title_des_rate_n_vol)} screen.")
            Button(
                onClick = { viewModel.refresh() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                androidx.compose.material3.Text(
                    text = "Try Again",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        showMessage(R.string.common_error_message)
    } else {
        LaunchedEffect(Unit) {
            viewModel.onOpen()
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(!(dataFetchingResult is LoadingResult || dataFetchingResult is SuccessResult)),
            onRefresh = { viewModel.refresh() },
        ) {
            dataFetchingResult.data?.let {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(it.hectreJobList.count()) { index ->
                        JobItem(
                            modifier = Modifier,
                            hectreJob = it.hectreJobList[index]
                        )
                    }
                }
            }

        }
    }
}
