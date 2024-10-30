package org.example.currency_tracker.data.data_source

import io.ktor.client.HttpClient

class RemoteDataSourceImpl(
    private val client: HttpClient,
) : RemoteDataSource {

}
