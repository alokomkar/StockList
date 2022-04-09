package com.alokomkar.repository

import com.alokomkar.core.DispatcherProvider
import com.alokomkar.core.Result
import com.alokomkar.local.IStocksPersistenceSource
import com.alokomkar.local.model.UserStocks
import com.alokomkar.mapper.IUserStocksMapper
import com.alokomkar.network.IUserStocksNetworkSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IUserStocksRepository {
    suspend fun refreshUserStocks()
    fun fetchUserStocks(): Flow<Result<List<UserStocks>>>
}

@Singleton
class UserStocksRepository @Inject constructor(
    private val persistenceSource: IStocksPersistenceSource,
    private val networkSource: IUserStocksNetworkSource,
    private val mapper: IUserStocksMapper,
    private val dispatcherProvider: DispatcherProvider
) : IUserStocksRepository {

    override suspend fun refreshUserStocks() = withContext(dispatcherProvider.io) {
        val networkResult = networkSource.getUserStocks()
        if(networkResult is Result.Success) {
            val userStocksResponse = networkResult.data
            val userStocks = mapper.mapToUserStocks(userStocksResponse)
            persistenceSource.updateAllUserStocks(userStocks)
        }
    }

    override fun fetchUserStocks(): Flow<Result<List<UserStocks>>> = persistenceSource.getAllUserStocks()
}