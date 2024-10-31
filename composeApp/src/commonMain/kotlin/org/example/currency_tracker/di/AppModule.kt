package org.example.currency_tracker.di

import io.ktor.client.engine.cio.CIO
import org.example.currency_tracker.data.remote.HttpClientManager
import org.example.currency_tracker.data.repository.Repository
import org.example.currency_tracker.data.repository.RepositoryImpl
import org.example.currency_tracker.ui.coin_list.view_model.CoinListViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientManager.getClient(CIO.create()) }
    singleOf(::RepositoryImpl).bind<Repository>()
    factory { CoinListViewModel(get()) }
}


fun iniKoin() {
    startKoin {
        modules(appModule)
    }
}