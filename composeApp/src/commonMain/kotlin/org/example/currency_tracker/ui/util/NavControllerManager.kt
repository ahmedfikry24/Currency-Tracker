package org.example.currency_tracker.ui.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object NavControllerManager {
    private var navController: NavHostController? = null

    @Composable
    fun init(): NavHostController {
        if (navController == null)
            navController = rememberNavController()

        return navController!!
    }
}