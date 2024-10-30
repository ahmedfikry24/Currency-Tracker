package org.example.currency_tracker.ui.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


abstract class BaseViewModel<U, E>(uiState: U) : ScreenModel {

    protected val _state = MutableStateFlow(uiState)
    val state = _state
        .onStart { initData() }
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = uiState
        )

    private val _event = MutableSharedFlow<E>()
    val event = _event.asSharedFlow()

    abstract fun initData()

    protected fun <T> tryExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onFailure: (BaseError) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        val handler = CoroutineExceptionHandler { _, error ->
            when (error) {
                is NoInternetConnection -> onFailure(NoInternetConnection())
                else -> onFailure(SomeThingWentWrong())
            }
        }
        screenModelScope.launch(handler + dispatcher) {
            val result = call()
            onSuccess(result)
        }
    }


    protected fun sendEvent(event: E) {
        screenModelScope.launch {
            _event.emit(event)
        }
    }
}
