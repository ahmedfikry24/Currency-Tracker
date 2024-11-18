package org.example.currency_tracker.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.currency_tracker.ANDROID
import org.example.currency_tracker.getPlatformName
import org.example.currency_tracker.ui.theme.spacing
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CoinInfoCard(
    modifier: Modifier = Modifier,
    title: String,
    formattedText: String,
    iconRes: DrawableResource,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val iconSize = when (getPlatformName()) {
        ANDROID -> 40.dp
        else -> 80.dp
    }
    val defaultTextStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Center,
        fontSize = 18.sp
    )
    Card(
        modifier = modifier
            .padding(MaterialTheme.spacing.space8)
            .shadow(
                elevation = MaterialTheme.spacing.space16,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            ),
        shape = RectangleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.space16),
            verticalArrangement = Arrangement.spacedBy((MaterialTheme.spacing.space8)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(iconSize).align(Alignment.CenterHorizontally),
                painter = painterResource(iconRes),
                contentDescription = title,
                tint = contentColor
            )
            Text(
                text = formattedText,
                style = defaultTextStyle,
                color = contentColor
            )
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )
        }
    }
}
