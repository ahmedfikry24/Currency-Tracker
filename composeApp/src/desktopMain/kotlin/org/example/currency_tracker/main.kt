package org.example.currency_tracker

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import currencytracker.composeapp.generated.resources.Res
import currencytracker.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import java.awt.Dimension
import java.awt.Toolkit

fun main() = application {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val windowState = rememberWindowState(
        width = screenSize.width.dp * .9f,
        height = screenSize.height.dp * 2 / 3,
        position = WindowPosition(Alignment.Center)
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = windowState,
    ) {
        window.minimumSize = Dimension(1300, 650)
        CurrencyTrackerApp()
    }
}