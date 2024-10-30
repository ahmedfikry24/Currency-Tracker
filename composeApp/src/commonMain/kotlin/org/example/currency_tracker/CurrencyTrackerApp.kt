package org.example.currency_tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.example.currency_tracker.di.iniKoin
import org.example.currency_tracker.ui.home.HomeScreen
import org.example.currency_tracker.ui.theme.CurrencyTrackerTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun CurrencyTrackerApp() {
    iniKoin()
    CurrencyTrackerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Navigator(HomeScreen()) {
                    SlideTransition(it)
                }
            }
        }
    }
}