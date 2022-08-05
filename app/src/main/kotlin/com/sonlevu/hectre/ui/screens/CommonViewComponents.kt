package com.sonlevu.hectre.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sonlevu.hectre.ui.theme.PrimaryColorRed

@Composable
fun TopBarViewWithBack(navigateBack: () -> Unit, @StringRes idString: Int) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = idString))
        },
        modifier = Modifier.padding(top = 24.dp),
        backgroundColor = PrimaryColorRed,
        navigationIcon = {
            IconButton(
                onClick = navigateBack,
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = ""
                )
            }
        })
}