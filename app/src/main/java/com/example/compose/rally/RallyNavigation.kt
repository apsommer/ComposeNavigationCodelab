package com.example.compose.rally

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.overview.OverviewScreen


@Composable
fun RallyNavHost(
    navController : NavHostController,
    modifier: Modifier = Modifier) {

    // create navigation host (container for current destination)
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {

        composable(route = Overview.route) {
            OverviewScreen(
                onClickSeeAllAccounts = {
                    navController.navigateSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigateSingleTopTo(Bills.route)
                },
                onAccountClick = { accountType ->
                    navController.navigateSingleAccount(accountType)
                }
            )
        }
        composable(route = Accounts.route) {
            AccountsScreen(
                onAccountClick = { accountType ->
                    navController.navigateSingleAccount(accountType)
                }
            )
        }
        composable(route = Bills.route) {
            BillsScreen()
        }
        composable(
            route = SingleAccount.routeWithArgs,
            deepLinks = SingleAccount.deepLinks,
            arguments = SingleAccount.arguments) { navBackStackEntry ->

            // retrieve argument from preceding destination
            val accountType = navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
    }
}

// helper extension, navigate anywhere since entire app is single top
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {

        // only one copy of each destination is on stack
        launchSingleTop = true

        // state must be saved in either PopUpToBuilder.saveState of popUpToSaveState to be restored
        restoreState = true
    }

// helper extension, navigate to single account
fun NavHostController.navigateSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}