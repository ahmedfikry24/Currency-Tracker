package org.example.currency_tracker.ui.base

abstract class BaseError(message: String? = null) : Throwable(message)
class RequestTimeout(message: String? = null) : BaseError(message)
class TooManyRequests(message: String? = null) : BaseError(message)
class ServerError(message: String? = null) : BaseError(message)
class SerializationResponse(message: String? = null) : BaseError(message)
class NoInternetConnection(message: String? = null) : BaseError(message)
class SomeThingWentWrong(message: String? = null) : BaseError(message)