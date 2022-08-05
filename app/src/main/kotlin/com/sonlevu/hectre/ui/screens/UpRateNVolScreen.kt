package com.sonlevu.hectre.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sonlevu.hectre.R
import com.sonlevu.hectre.data.api.OrchardJobResponse
import com.sonlevu.hectre.domain.model.jobs.HectreJob
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
        val dataFetchingResult by viewModel.dataFetchingResult.collectAsState()

        SwipeRefresh(
            state = rememberSwipeRefreshState(!(dataFetchingResult is LoadingResult || dataFetchingResult is SuccessResult)),
            onRefresh = { viewModel.refresh() },
        ) {
            dataFetchingResult.data?.let {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(it.hectreJobList.count()) { index ->
                        JobItem(modifier = Modifier,
                            hectreJob = it.hectreJobList[index])
                    }
                }
            }

        }
    }
}

@Composable
fun JobItem(modifier: Modifier, hectreJob: HectreJob) {
    ElevatedCard(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AvatarFromName(
                    name = "Bob"
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "First Name Of Smb",
                        style = MaterialTheme.typography.labelMedium
                    )
                    androidx.compose.material3.Text(
                        text = "20 mins ago",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Text(
                text = "SOME SUBJECT",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )

            Text(
                text = "THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.THis is a long body.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
                ) {
                    androidx.compose.material3.Text(
                        text = "Button 1",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
                ) {
                    androidx.compose.material3.Text(
                        text = "Button 2",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}