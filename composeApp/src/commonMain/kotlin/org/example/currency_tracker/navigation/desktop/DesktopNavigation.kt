package org.example.currency_tracker.navigation.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.currency_tracker.ui.coin_details.CoinDetailsScreen
import org.example.currency_tracker.ui.coin_list.CoinListScreen
import org.example.currency_tracker.ui.composable.ContentError
import org.example.currency_tracker.ui.composable.ContentLoading
import org.example.currency_tracker.ui.composable.ContentVisible
import org.example.currency_tracker.ui.shared.ui_state.ContentStatus
import org.example.currency_tracker.ui.shared.view_model.MainCoinViewModel
import org.example.currency_tracker.ui.theme.spacing
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DesktopNavigation() {
    val viewModel = koinViewModel<MainCoinViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    ContentLoading(isVisible = state.contentStatus == ContentStatus.LOADING)
    ContentVisible(isVisible = state.contentStatus == ContentStatus.VISIBLE) {
        Row(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space8)
        ) {
            CoinListScreen(
                modifier = Modifier.weight(1f),
                state = state,
                interactions = viewModel
            )
            CoinDetailsScreen(
                modifier = Modifier.weight(3f),
                state = state,
                interactions = viewModel
            )
        }
    }
    ContentError(
        isVisible = state.contentStatus == ContentStatus.FAILURE,
        onTryAgain = viewModel::initData
    )
}
