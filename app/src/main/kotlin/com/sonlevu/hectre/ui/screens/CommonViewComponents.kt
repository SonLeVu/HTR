package com.sonlevu.hectre.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sonlevu.hectre.ui.theme.PrimaryColorRed
import com.sonlevu.hectre.ui.theme.Purple40

@Composable
fun TopBarViewWithBack(navigateBack: () -> Unit, @StringRes idString: Int) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = idString),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier.padding(top = 24.dp),
        backgroundColor = PrimaryColorRed,
        navigationIcon = {
            IconButton(
                onClick = navigateBack,
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        })
}

@Composable
fun AvatarFromName(name: String) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(3.dp)
            .background(shape = CircleShape, color = Purple40),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name[0].toString(),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium)
    }
}