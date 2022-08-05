package com.sonlevu.hectre.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sonlevu.hectre.R

@Composable
fun HarvestScreen (){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Placeholder for ${stringResource(id = R.string.title_des_harvest)} screen.")
    }
}