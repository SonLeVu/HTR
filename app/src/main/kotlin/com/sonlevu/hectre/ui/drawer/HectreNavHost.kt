package com.sonlevu.hectre.ui.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sonlevu.hectre.data.repo.OrchardRepository
import com.sonlevu.hectre.domain.repo.IOrchardRepository
import com.sonlevu.hectre.ui.screens.*
import kotlinx.coroutines.launch

@Composable
fun HectreNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    orchardRepository: IOrchardRepository,
    showMessage: (Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        composable(route = Admin.route) {
            AdminScreen()
        }
        composable(route = Harvest.route) {
            HarvestScreen()
        }
        composable(route = Insights.route) {
            InsightScreen()
        }
        composable(route = Maps.route) {
            MapsScreen()
        }
        composable(route = Scout.route) {
            ScoutScreen()
        }
        composable(route = Payroll.route) {
            PayrollScreen()
        }
        composable(route = TimeSheets.route) {
            TimeSheetsScreen(
                onListItemClicked = {
                    navController.navigateToUpRateNVolScreen(it)
                },
                showMessage = showMessage
            )
        }

        composable(
            route = UpdateRateAndVolume.routeWithArg,
            arguments = UpdateRateAndVolume.arg,
            deepLinks = UpdateRateAndVolume.deepLink
        ) { navBackStackEntry ->
            val arg = navBackStackEntry.arguments?.getString(UpdateRateAndVolume.orchardIdArg)
            UpRateNVolScreen(
                orchardID = arg ?: "",
                showMessage = showMessage,
                orchardRepository = orchardRepository,
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToUpRateNVolScreen(orchardIdArg: String) =
    this.navigateSingleTopTo("${UpdateRateAndVolume.route}/$orchardIdArg")