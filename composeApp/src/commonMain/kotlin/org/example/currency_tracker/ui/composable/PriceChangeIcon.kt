package org.example.currency_tracker.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.currency_tracker.ui.shared_ui.DisplayedNumber
import org.example.currency_tracker.ui.theme.greenBackground
import org.example.currency_tracker.ui.theme.spacing

@Composable
fun PriceChangeIcon(
    modifier: Modifier = Modifier,
    state: DisplayedNumber,
) {
    val contentColor =
        if (state.value < 0.0) MaterialTheme.colorScheme.onErrorContainer else Color.Green
    val backgroundColor =
        if (state.value < 0.0) MaterialTheme.colorScheme.errorContainer else greenBackground
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(MaterialTheme.spacing.space4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = if (state.value < 0.0) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            tint = contentColor
        )
        Text(
            text = "${state.formated} %",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = contentColor
        )
    }
}