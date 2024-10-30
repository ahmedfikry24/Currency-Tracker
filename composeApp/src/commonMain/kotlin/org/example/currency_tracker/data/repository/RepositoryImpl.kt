package org.example.currency_tracker.data.repository

import org.example.currency_tracker.data.data_source.RemoteDataSource

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : Repository {
}
