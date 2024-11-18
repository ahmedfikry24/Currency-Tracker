package org.example.currency_tracker.data.utils

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.currency_tracker.ui.base.NoInternetConnection
import org.example.currency_tracker.ui.base.RequestTimeout
import org.example.currency_tracker.ui.base.SerializationResponse
import org.example.currency_tracker.ui.base.ServerError
import org.example.currency_tracker.ui.base.SomeThingWentWrong
import org.example.currency_tracker.ui.base.TooManyRequests


suspend inline fun <reified T> warpApiCall(request: () -> HttpResponse): T {
    return runCatching {
        val response = request()
        when (response.status.value) {
            in 200..299 -> response.body<T>()
            408 -> throw RequestTimeout()
            429 -> throw TooManyRequests()
            in 500..599 -> throw ServerError()
            else -> throw SomeThingWentWrong()
        }
    }.onFailure { error ->
        when (error) {
            is RequestTimeout -> RequestTimeout()
            is TooManyRequests -> TooManyRequests()
            is UnresolvedAddressException -> NoInternetConnection()
            is SerializationException -> SerializationResponse()
            is NoTransformationFoundException -> SerializationResponse()
            else -> SomeThingWentWrong()
        }
    }.getOrThrow()
}
