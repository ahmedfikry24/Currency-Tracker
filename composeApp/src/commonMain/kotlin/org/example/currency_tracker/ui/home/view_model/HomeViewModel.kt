package org.example.currency_tracker.ui.home.view_model

import org.example.currency_tracker.data.repository.Repository
import org.example.currency_tracker.ui.base.BaseViewModel

class HomeViewModel(
    private val repository: Repository,
) : BaseViewModel<HomeUiState, HomeEvents>(HomeUiState()), HomeInteractions {

    override fun initData() {

    }

}
