package org.example.currency_tracker.ui.coin_details

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import currencytracker.composeapp.generated.resources.Res
import currencytracker.composeapp.generated.resources.change_last_24h
import currencytracker.composeapp.generated.resources.dollar
import currencytracker.composeapp.generated.resources.market_cap
import currencytracker.composeapp.generated.resources.price
import currencytracker.composeapp.generated.resources.stock
import currencytracker.composeapp.generated.resources.trending
import currencytracker.composeapp.generated.resources.trending_down
import org.example.currency_tracker.ANDROID
import org.example.currency_tracker.getPlatformName
import org.example.currency_tracker.ui.composable.CoinInfoCard
import org.example.currency_tracker.ui.composable.ContentError
import org.example.currency_tracker.ui.composable.ContentLoading
import org.example.currency_tracker.ui.composable.ContentVisible
import org.example.currency_tracker.ui.shared.interactions.CoinDetailsInteractions
import org.example.currency_tracker.ui.shared.ui_state.CoinDetailsUiState
import org.example.currency_tracker.ui.shared.ui_state.ContentStatus
import org.example.currency_tracker.ui.shared.ui_state.toDisplayedNumber
import org.example.currency_tracker.ui.theme.greenBackground
import org.example.currency_tracker.ui.theme.spacing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreen(
    modifier: Modifier = Modifier,
    state: CoinDetailsUiState,
    interactions: CoinDetailsInteractions,
) {
    ContentLoading(isVisible = state.contentStatus == ContentStatus.LOADING)
    ContentVisible(isVisible = state.contentStatus == ContentStatus.VISIBLE) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
        ) {
            if (getPlatformName() == ANDROID)
                item {
                    Row(modifier = Modifier.fillMaxWidth().height(48.dp)) {
                        IconButton(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.space16),
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                }
            item {
                val iconSize = when (getPlatformName()) {
                    ANDROID -> 100.dp
                    else -> 180.dp
                }
                val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space8),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        painter = painterResource(state.coin.iconRes),
                        contentDescription = state.coin.name,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = state.coin.name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = state.coin.symbol,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = textColor
                    )
                }
            }
            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CoinInfoCard(
                        title = stringResource(Res.string.market_cap),
                        formattedText = state.coin.marketCapUsd.formated,
                        iconRes = Res.drawable.stock,
                    )
                    CoinInfoCard(
                        title = stringResource(Res.string.price),
                        formattedText = state.coin.priceUsd.formated,
                        iconRes = Res.drawable.dollar,
                    )
                    val changeFormatted =
                        state.coin.priceUsd.value.times(state.coin.changePercent24Hr.value.div(100))
                            .toDisplayedNumber()
                    val isPositive = changeFormatted.value > 0.0
                    val contentColor = if (isPositive) {
                        if (isSystemInDarkTheme()) Color.Green else greenBackground
                    } else MaterialTheme.colorScheme.error

                    CoinInfoCard(
                        title = stringResource(Res.string.change_last_24h),
                        formattedText = state.coin.changePercent24Hr.formated,
                        iconRes = if (isPositive) Res.drawable.trending else Res.drawable.trending_down,
                        contentColor = contentColor
                    )
                }
            }
        }
    }
    ContentError(
        isVisible = state.contentStatus == ContentStatus.FAILURE,
        onTryAgain = interactions::initData
    )
}