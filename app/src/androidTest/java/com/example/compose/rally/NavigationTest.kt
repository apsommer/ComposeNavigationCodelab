package com.example.compose.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.TestCase.fail
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    lateinit var testNavController: TestNavHostController

    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun rallyNavHost() {
        composeTestRule.setContent {

            // create test navigation controller
            testNavController = TestNavHostController(LocalContext.current)

            // create test "navigator" to crawl composables
            testNavController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )

            RallyNavHost(navController = testNavController)
        }

        fail()
    }
}