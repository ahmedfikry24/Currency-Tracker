package org.example.currency_tracker.data.utils

private const val BASE_URL = "https://api.coincap.io/v2/"

fun getFullUrl(url: String): String {
    return when {
        url.contains(BASE_URL) -> url
        url.startsWith("/") -> BASE_URL + url.drop(1)
        else -> BASE_URL + url
    }
}