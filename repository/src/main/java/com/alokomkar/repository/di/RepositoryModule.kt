package com.alokomkar.repository.di

import com.alokomkar.local.IStocksPersistenceSource
import com.alokomkar.local.StocksPersistenceSource
import com.alokomkar.local.di.PersistenceModule
import com.alokomkar.mapper.di.MapperModule
import com.alokomkar.network.IUserStocksNetworkSource
import com.alokomkar.network.UserStocksNetworkSource
import com.alokomkar.network.di.NetworkModule
import com.alokomkar.repository.IUserStocksRepository
import com.alokomkar.repository.UserStocksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module(includes = [NetworkModule::class, PersistenceModule::class, MapperModule::class])
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindUserStocksNetworkSource(
        userStocksNetworkSource: UserStocksNetworkSource
    ): IUserStocksNetworkSource

    @Binds
    @Singleton
    fun bindUserStocksPersistenceSource(
        userStocksPersistenceSource: StocksPersistenceSource
    ): IStocksPersistenceSource

    @Binds
    @Singleton
    fun bindUserStocksRepository(
        userStocksRepository: UserStocksRepository
    ): IUserStocksRepository
}