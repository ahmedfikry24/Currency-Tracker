package org.example.currency_tracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import currencytracker.composeapp.generated.resources.Res
import currencytracker.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        CurrencyTrackerApp()
    }
}