package org.example.currency_tracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DefaultResponseDto<out T>(
    val data: List<T>,
)
