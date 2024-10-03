package com.example.compose.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.TestCase.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    lateinit var testNavController: TestNavHostController

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {

            // create test navigation controller
            testNavController = TestNavHostController(LocalContext.current)

            // create test "navigator" to crawl composables
            testNavController.navigatorProvider.addNavigator(ComposeNavigator())

            RallyNavHost(navController = testNavController)
        }
    }

    @Test
    fun rallyNavHost_verifyOverviewStartDestination() {

        composeTestRule
            .onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_clickAllAccount_navigatesToAccounts() {
        composeTestRule
            .onNodeWithContentDescription("All Accounts")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }
}