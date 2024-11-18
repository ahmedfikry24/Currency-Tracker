package org.example.currency_tracker

import androidx.compose.runtime.Composable
import org.example.currency_tracker.di.iniKoin
import org.example.currency_tracker.navigation.android.AndroidNavigation
import org.example.currency_tracker.navigation.desktop.DesktopNavigation
import org.example.currency_tracker.ui.theme.CurrencyTrackerTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun CurrencyTrackerApp() {
    iniKoin()
    CurrencyTrackerTheme {
        when (getPlatformName()) {
            ANDROID -> AndroidNavigation()
            else -> DesktopNavigation()
        }
    }
}