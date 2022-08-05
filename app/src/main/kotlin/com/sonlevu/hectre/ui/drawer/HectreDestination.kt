package com.sonlevu.hectre.ui.drawer


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.sonlevu.hectre.R

interface HectreDestination {
    val icon: ImageVector
    val route: String
    val title : Int
}

object TimeSheets : HectreDestination {
    override val icon = Icons.Filled.Timelapse
    override val route = "timesheets"
    @StringRes override val title = R.string.title_des_timesheets
}

object Harvest : HectreDestination {
    override val icon = Icons.Filled.ContentCut
    override val route = "harvest"
    @StringRes override val title = R.string.title_des_harvest
}

object Payroll : HectreDestination {
    override val icon = Icons.Filled.Payments
    override val route = "payroll"
    @StringRes override val title = R.string.title_des_payroll
}

object Insights : HectreDestination {
    override val icon = Icons.Filled.Insights
    override val route = "insights"
    @StringRes override val title = R.string.title_des_insights
}

object Maps : HectreDestination {
    override val icon = Icons.Filled.Map
    override val route = "maps"
    @StringRes override val title = R.string.title_des_maps
}

object Scout : HectreDestination {
    override val icon = Icons.Filled.BikeScooter
    override val route = "scout"
    @StringRes override val title = R.string.title_des_scout
}

object Admin : HectreDestination {
    override val icon = Icons.Filled.AdminPanelSettings
    override val route = "admin"
    @StringRes override val title = R.string.title_des_admin
}

object UpdateRateAndVolume : HectreDestination {
    override val icon = Icons.Filled.Money
    override val route = "upratenvol"
    @StringRes override val title = R.string.title_des_rate_n_vol

    const val orchardIdArg = "ochard_id"
    val routeWithArg = "$route/{$orchardIdArg}"
    val arg = listOf(navArgument(orchardIdArg){ type = NavType.StringType })
    val deepLink = listOf(navDeepLink { uriPattern = "hectre://$routeWithArg" })
}

val drawerMenu = listOf(Harvest, Payroll, TimeSheets, Maps, Insights, Scout, Admin)
val startDestination = TimeSheets
