package com.sonlevu.hectre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sonlevu.hectre.data.repo.OrchardRepository
import com.sonlevu.hectre.ui.drawer.*
import com.sonlevu.hectre.ui.screens.TopBarViewWithBack
import com.sonlevu.hectre.ui.theme.HectreTheme
import com.sonlevu.hectre.ui.theme.PrimaryColorRed
import com.sonlevu.hectre.ui.theme.ThemeSetting
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val viewModel: MainActivityViewModel = viewModel()

            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentScreen = drawerMenu.find { it.route == currentBackStack?.destination?.route }
                ?: startDestination

            val systemUiController = rememberSystemUiController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            val orchardRepo =
                OrchardRepository() // Repalce this with whatever implementation that extends IOrchardRepository

            val showMessage: (Int) -> Unit = { messageID ->
                val strMessage = context.getString(messageID)
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(strMessage)
                }
            }

            val darkTheme = when (viewModel.theme) {//by viewModel.theme.collectAsState()
                ThemeSetting.Light -> false
                ThemeSetting.Dark -> true
                ThemeSetting.System -> isSystemInDarkTheme()
            }

            HectreTheme(darkTheme) {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    systemUiController.let {
                        it.setStatusBarColor(
                            PrimaryColorRed,
                            darkIcons = !darkTheme
                        )
                        it.setNavigationBarColor(
                            Color.Transparent,
                            darkIcons = !darkTheme
                        )
                    }
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    androidx.compose.material.Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = { HectreSnackbar(it) },
                        topBar = {
                            if (currentBackStack?.destination?.route?.contains(UpdateRateAndVolume.route) == true) {
                                TopBarViewWithBack(
                                    navigateBack = {
                                        navController.popBackStack()
                                    },
                                    idString = UpdateRateAndVolume.title
                                )
                            } else {
                                TopBarView(
                                    scaffoldState = scaffoldState,
                                    scope = scope,
                                    titleID = currentScreen.title
                                )
                            }

                        },
                        drawerGesturesEnabled = currentBackStack?.destination?.route?.contains(
                            UpdateRateAndVolume.route
                        ) == false,
                        drawerContent = {
                            DrawerView(
                                currentScreen = currentScreen,
                                onItemClicked = {
                                    navController.navigateSingleTopTo(it.route)
                                    scope.launch { scaffoldState.drawerState.close() }
                                })
                        },
                        bottomBar = {},
                        content = {
                            HectreNavHost(
                                navController = navController,
                                modifier = Modifier.padding(it),
                                orchardRepository = orchardRepo,
                                showMessage = showMessage
                            )
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HectreTheme {
        DrawerView(TimeSheets) {}
    }
}