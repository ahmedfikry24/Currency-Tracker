package org.example.currency_tracker.ui.home.view_model

import androidx.compose.runtime.Immutable
import org.example.currency_tracker.ui.shared_ui.ContentStatus

@Immutable
data class HomeUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
) 
