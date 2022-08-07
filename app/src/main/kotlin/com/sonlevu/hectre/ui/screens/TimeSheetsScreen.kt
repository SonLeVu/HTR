package com.sonlevu.hectre.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sonlevu.hectre.domain.model.field.Orchard

val allAvailableOrchard = listOf(
    Orchard(id = "V1394J", name = "Winterfell", areaAcre = 101f),
    Orchard(id = "G7744L", name = "Dorne", areaAcre = 102f),
    Orchard(id = "A1755J", name = "Highgarden", areaAcre = 103f),
)

@Composable
fun TimeSheetsScreen(onListItemClicked: (orchardId: String) -> Unit,
                     showMessage: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(allAvailableOrchard.size) { indx ->
            OrchardRow(
                orchardName = allAvailableOrchard[indx].name,
                orchardID = allAvailableOrchard[indx].id,
                onClicked = onListItemClicked
            )
        }
    }
}

@Composable
fun OrchardRow(orchardName: String, orchardID: String, onClicked: (orchardId: String) -> Unit) {
    Box(modifier = Modifier.padding(5.dp)){
        Text(
            text = "Orchard: $orchardName ($orchardID)",
            fontSize = 24.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClicked(orchardID)
                },
            textAlign = TextAlign.Center
        )
    }
}