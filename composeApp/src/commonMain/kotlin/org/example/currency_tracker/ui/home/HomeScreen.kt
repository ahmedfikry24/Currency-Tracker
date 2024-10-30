package org.example.currency_tracker.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import org.example.currency_tracker.ui.composable.ContentError
import org.example.currency_tracker.ui.composable.ContentLoading
import org.example.currency_tracker.ui.composable.ContentVisible
import org.example.currency_tracker.ui.home.view_model.HomeInteractions
import org.example.currency_tracker.ui.home.view_model.HomeUiState
import org.example.currency_tracker.ui.home.view_model.HomeViewModel
import org.example.currency_tracker.ui.shared_ui.ContentStatus
import org.example.currency_tracker.ui.util.EventHandler


class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        EventHandler(viewModel.event) { event, _ ->

        }
        HomeContent(state = state, interactions = viewModel)
    }
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    interactions: HomeInteractions,
) {
    ContentLoading(isVisible = state.contentStatus == ContentStatus.LOADING)
    ContentVisible(isVisible = state.contentStatus == ContentStatus.VISIBLE) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

        }
    }
    ContentError(
        isVisible = state.contentStatus == ContentStatus.FAILURE,
        onTryAgain = {}
    )
}
