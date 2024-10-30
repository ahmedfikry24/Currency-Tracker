package org.example.currency_tracker

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.example.currency_tracker.di.appModule
import org.example.currency_tracker.di.iniKoin
import org.example.currency_tracker.ui.home.HomeScreen
import org.example.currency_tracker.ui.theme.CurrencyTrackerTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@Composable
@Preview
fun CurrencyTrackerApp() {
    iniKoin()
    CurrencyTrackerTheme {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}