package org.example.currency_tracker.ui.base

abstract class BaseError(message: String? = null) : Throwable(message)
class NoInternetConnection(message: String?= null) : BaseError(message)
class SomeThingWentWrong(message: String?= null) : BaseError(message)