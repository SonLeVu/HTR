package com.sonlevu.hectre

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sonlevu.hectre.ui.theme.HectreTheme
import com.sonlevu.hectre.ui.theme.PrimaryColorYellow
import com.sonlevu.hectre.ui.theme.ThemeSetting
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            val darkTheme = when (viewModel.theme) {//by viewModel.theme.collectAsState()
                ThemeSetting.Light -> false
                ThemeSetting.Dark -> true
                ThemeSetting.System -> isSystemInDarkTheme()
            }

            HectreTheme(darkTheme) {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    systemUiController.let {
                        it.setStatusBarColor(
                            Color.Transparent,
                            darkIcons = !darkTheme
                        )
                        it.setNavigationBarColor(
                            Color.Transparent,
                            darkIcons = !darkTheme
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    androidx.compose.material.Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = stringResource(id = R.string.app_name))
                                },
                                backgroundColor = PrimaryColorYellow,
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                scaffoldState.drawerState.open()
                                            }
                                        },
                                    ) {
                                        Icon(
                                            Icons.Rounded.Menu,
                                            contentDescription = ""
                                        )
                                    }
                                })
                        },
                        drawerContent = { DrawerView() },
                        bottomBar = {}
                    ) {

                    }
                }


            }
        }
    }
}


@Composable
fun DrawerView() {
    val management = listOf("TimeSheet ", "Hindi", "Arabic")
    val category = listOf("Cloth", "electronics", "fashion", "Food")
    LazyColumn {
        item {
            MenuHeader(title = "Language")
        }
        items(management.size) { index ->
            AddDrawerContentView(title = management[index], selected = index == 1)
        }
        item {
            MenuHeader(title = "Category")
        }
        items(category.size) { index ->
            AddDrawerContentView(title = category[index], selected = index == 2)
        }
    }

}

@Composable
fun AddDrawerContentView(title: String, selected: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {}
            .padding(horizontal = 16.dp, vertical = 12.dp),


        ) {

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HectreTheme {
        DrawerView()
    }
}