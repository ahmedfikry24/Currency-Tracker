package org.example.currency_tracker.ui.coin_details

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
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
import org.example.currency_tracker.ui.coin_details.composable.ChartStyle
import org.example.currency_tracker.ui.coin_details.composable.CurrencyChart
import org.example.currency_tracker.ui.coin_details.composable.DataPoint
import org.example.currency_tracker.ui.composable.CoinInfoCard
import org.example.currency_tracker.ui.shared.interactions.MainCoinInteractions
import org.example.currency_tracker.ui.shared.ui_state.MainCoinUiState
import org.example.currency_tracker.ui.shared.ui_state.toDisplayedNumber
import org.example.currency_tracker.ui.shared.ui_state.toPoints
import org.example.currency_tracker.ui.theme.greenBackground
import org.example.currency_tracker.ui.theme.spacing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreen(
    modifier: Modifier = Modifier,
    state: MainCoinUiState,
    interactions: MainCoinInteractions,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MaterialTheme.spacing.space16),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
    ) {
        if (getPlatformName() == ANDROID)
            item {
                Row(modifier = Modifier.fillMaxWidth().height(48.dp)) {
                    IconButton(
                        modifier = Modifier.padding(start = MaterialTheme.spacing.space16),
                        onClick = interactions::switchScreenContent,
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
                    painter = painterResource(state.selectedCoin.iconRes),
                    contentDescription = state.selectedCoin.name,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = state.selectedCoin.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = state.selectedCoin.symbol,
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
                    formattedText = state.selectedCoin.marketCapUsd.formated,
                    iconRes = Res.drawable.stock,
                )
                CoinInfoCard(
                    title = stringResource(Res.string.price),
                    formattedText = state.selectedCoin.priceUsd.formated,
                    iconRes = Res.drawable.dollar,
                )
                val changeFormatted =
                    state.selectedCoin.priceUsd.value.times(
                        state.selectedCoin.changePercent24Hr.value.div(
                            100
                        )
                    )
                        .toDisplayedNumber()
                val isPositive = changeFormatted.value > 0.0
                val contentColor = if (isPositive) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else MaterialTheme.colorScheme.error

                CoinInfoCard(
                    title = stringResource(Res.string.change_last_24h),
                    formattedText = state.selectedCoin.changePercent24Hr.formated,
                    iconRes = if (isPositive) Res.drawable.trending else Res.drawable.trending_down,
                    contentColor = contentColor
                )
            }
        }
        item {
            var selectedDataPoint by remember {
                mutableStateOf<DataPoint?>(null)
            }
            var labelWidth by remember {
                mutableFloatStateOf(0f)
            }
            var totalChartWidth by remember {
                mutableFloatStateOf(0f)
            }
            val amountOfVisibleDataPoints = if (labelWidth > 0) {
                ((totalChartWidth - 2.5 * labelWidth) / labelWidth).toInt()
            } else {
                0
            }
            val startIndex = (state.coinHistory.lastIndex - amountOfVisibleDataPoints)
                .coerceAtLeast(0)
            CurrencyChart(
                dataPoints = state.coinHistory.map { it.toPoints() },
                style = ChartStyle(
                    chartLineColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.secondary.copy(
                        alpha = 0.3f
                    ),
                    selectedColor = MaterialTheme.colorScheme.primary,
                    helperLinesThicknessPx = 5f,
                    axisLinesThicknessPx = 5f,
                    labelFontSize = 14.sp,
                    minYLabelSpacing = 25.dp,
                    verticalPadding = 8.dp,
                    horizontalPadding = 8.dp,
                    xAxisLabelSpacing = 8.dp
                ),
                visibleDataPointsIndices = startIndex..state.coinHistory.lastIndex,
                unit = "$",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .onSizeChanged { totalChartWidth = it.width.toFloat() },
                selectedDataPoint = selectedDataPoint,
                onSelectedDataPoint = {
                    selectedDataPoint = it
                },
                onXLabelWidthChange = { labelWidth = it }
            )
        }
    }
}