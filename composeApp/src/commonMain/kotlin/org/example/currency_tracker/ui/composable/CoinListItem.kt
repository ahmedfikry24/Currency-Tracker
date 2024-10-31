package org.example.currency_tracker.ui.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.example.currency_tracker.ui.shared.ui_state.CoinUiState
import org.example.currency_tracker.ui.theme.spacing
import org.jetbrains.compose.resources.painterResource


@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    state: CoinUiState,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier,
        onClick = onClick
    ) {
        val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        Row(
            modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacing.space16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
        ) {
            Icon(
                painter = painterResource(state.iconRes),
                contentDescription = state.name,
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = state.symbol,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = state.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = textColor
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$ ${state.priceUsd.formated}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                VerticalDivider(modifier = Modifier.height(MaterialTheme.spacing.space8))
                PriceChangeIcon(state = state.changePercent24Hr)
            }
        }
    }
}
