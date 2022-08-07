package com.sonlevu.hectre.ui.screens.updateratenvol

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sonlevu.hectre.domain.model.jobs.HectreJob
import com.sonlevu.hectre.ui.screens.AvatarFromName
import com.sonlevu.hectre.ui.screens.HyperlinkText
import com.sonlevu.hectre.ui.screens.OrchardRow
import com.sonlevu.hectre.ui.theme.CardTitleBackgroundColor
import com.sonlevu.hectre.ui.theme.PrimaryColorRed
import com.sonlevu.hectre.ui.theme.Purple80

@Composable
fun JobItem(modifier: Modifier, hectreJob: HectreJob) {
    ElevatedCard(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = CardTitleBackgroundColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Pruning",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
            )
            OutlinedButton(
                modifier = Modifier.padding(end = 16.dp),
                border = BorderStroke(1.dp, PrimaryColorRed),
                contentPadding = PaddingValues(horizontal = 10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = CardTitleBackgroundColor,
                    contentColor = PrimaryColorRed
                ),
                shape = RoundedCornerShape(4.dp),
                onClick = {

                }
            ) {
                Text(
                    "ADD MAX TREES",
                    color = PrimaryColorRed,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            AvatarFromName(name = "Henry", backgroundColor = Purple80, size = 50.dp)
            Spacer(modifier = Modifier.size(8.dp))
            HyperlinkText(
                modifier = Modifier,
                fullText = "Orchard   Benji(V1032F)",
                linkText = listOf("Benji(V1032F)"),
                linkTextColor = Color.Black,
                isLockClick = true
            )
            HyperlinkText(
                modifier = Modifier,
                fullText = "Block   UB13",
                linkText = listOf("UB13"),
                linkTextColor = Color.Black,
                isLockClick = true
            )
            Text(
                text = "Rate Type",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 12.dp),
            )
            val btn1Color = remember {
                mutableStateOf(Color.LightGray)
            }
            val btn2Color = remember {
                mutableStateOf(PrimaryColorRed)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Button(
                    onClick = {
                        btn1Color.value = PrimaryColorRed
                        btn2Color.value = Color.LightGray
                    },
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = btn1Color.value)
                ) {
                    Text(
                        "PIECE RATE",
                        color = Color.White
                    )
                }
                Button(
                    onClick = {
                        btn2Color.value = PrimaryColorRed
                        btn1Color.value = Color.LightGray
                    },
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = btn2Color.value)
                ) {
                    Text(
                        "WAGES",
                        color = Color.White
                    )
                }
            }
            if (btn2Color.value == PrimaryColorRed) {
                Text(
                    text = "Canker Removal (Job Name) will be paid by wages in this timesheet.",
                    style = MaterialTheme.typography.labelLarge,
                    color = PrimaryColorRed,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OrchardRow(text = "1", isPartiallyDone = false, isAssigned = false)
                OrchardRow(text = "2", isPartiallyDone = true, isAssigned = false)
                OrchardRow(text = "3", isPartiallyDone = false, isAssigned = false)
                OrchardRow(text = "4", isPartiallyDone = true, isAssigned = true)
            }

            Text(
                text = "Trees for row 4",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}