package org.example.currency_tracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Currency Tracker",
    ) {
        App()
    }
}