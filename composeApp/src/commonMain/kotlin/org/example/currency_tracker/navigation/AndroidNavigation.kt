package org.example.currency_tracker.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.currency_tracker.ui.coin_list.CoinListScreen
import org.example.currency_tracker.ui.coin_list.view_model.CoinListViewModel
import org.example.currency_tracker.ui.util.NavControllerManager
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AndroidNavigation() {
    val navController = NavControllerManager.init()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = CoinList
            ) {
                composable<CoinList> {
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    CoinListScreen(state = state, interactions = viewModel)
                }
                composable<CoinDetails> {  }
            }
        }
    }
}
