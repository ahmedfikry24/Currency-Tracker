package org.example.currency_tracker.ui.home.view_model

import org.example.currency_tracker.ui.shared_ui.ContentStatus

data class HomeUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
) 
