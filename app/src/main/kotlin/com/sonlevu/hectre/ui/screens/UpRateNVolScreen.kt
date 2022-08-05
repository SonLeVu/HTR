package com.sonlevu.hectre.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
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
    if (orchardID.isEmpty()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Placeholder for ${stringResource(R.string.title_des_rate_n_vol)} screen.")
        }
        showMessage(R.string.common_error_message)
    } else {
        val viewModel = UpRateNVolViewModel(orchardID, orchardRepository, showMessage)
        LaunchedEffect(Unit) {
            viewModel.onOpen()
        }
        val loginResult by viewModel.loginResult.collectAsState()

        SwipeRefresh(
            state = rememberSwipeRefreshState(!(loginResult is LoadingResult || loginResult is SuccessResult)),
            onRefresh = { viewModel.refresh() },
        ) {
            LazyColumn {
                items(130) { index ->
                    Text(text = "somthing $index")
                }
            }
        }
    }
}
