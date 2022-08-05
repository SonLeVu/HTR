package com.sonlevu.hectre.ui.drawer

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.sonlevu.hectre.R
import com.sonlevu.hectre.ui.theme.PrimaryColorRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuHeader(
    title: String,
    titleColor: Color = Color.Black,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, color = Color.Gray),
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor
            ),
            modifier = Modifier.padding(14.dp)
        )

    }
}


@Composable
fun MenuDrawerItemView(title: String, selected: Boolean, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {}
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {

        Icon(
            imageVector = icon,
            modifier = Modifier.padding(end = 20.dp),
            contentDescription = title,
            tint = if (selected) PrimaryColorRed else PrimaryColorRed.copy(alpha = .6f)
        )
        if (title.isNotEmpty()) {
            if (selected)
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
            else
                Text(text = title, modifier = Modifier.weight(1f), fontSize = 12.sp)
        }

    }
}

@Composable
fun DrawerView(currentScreen : HectreDestination) {
    LazyColumn(modifier = Modifier.padding(top = 24.dp)) {
        items(drawerMenu.size) { index ->
            MenuDrawerItemView(
                title = stringResource(id = drawerMenu[index].title),
                selected = drawerMenu[index].route == currentScreen.route,
                icon = drawerMenu[index].icon
            )
        }
    }
}

@Composable
fun TopBarView(scope: CoroutineScope, scaffoldState: ScaffoldState, @StringRes titleID: Int) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = titleID))
        },
        modifier = Modifier.padding(top = 24.dp),
        backgroundColor = PrimaryColorRed,
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
            ) {
                androidx.compose.material3.Icon(
                    Icons.Rounded.Menu,
                    contentDescription = ""
                )
            }
        })
}

@Composable
fun HectreSnackbar(snackBarHostState: SnackbarHostState) {
    androidx.compose.material.SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier.navigationBarsPadding()
    ) { snackBarData ->
        androidx.compose.material.Snackbar(
            snackbarData = snackBarData,
            backgroundColor = MaterialTheme.colorScheme.surface,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
            shape = MaterialTheme.shapes.small
        )
    }

}