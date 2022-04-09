package com.alokomkar.local

import com.alokomkar.core.Result
import com.alokomkar.local.dao.IUserStocksDao
import com.alokomkar.local.model.UserStocks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

interface IStocksPersistenceSource {
    fun getAllUserStocks(): Flow<Result<List<UserStocks>>>
    fun updateAllUserStocks(userStocks: List<UserStocks>)
}

@Singleton
class StockPersistenceSource @Inject constructor(
    private val dao: IUserStocksDao
): IStocksPersistenceSource {

    override fun getAllUserStocks(): Flow<Result<List<UserStocks>>> = dao.getAllAsFlow().map { userStocks ->
        return@map if(userStocks.isNullOrEmpty())
            Result.Error(Exception("No User Stocks Present"))
        else
            Result.Success(userStocks)
    }

    override fun updateAllUserStocks(userStocks: List<UserStocks>) = dao.insertAll(userStocks)

}