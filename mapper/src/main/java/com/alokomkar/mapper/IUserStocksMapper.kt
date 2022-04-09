package com.alokomkar.mapper

import com.alokomkar.local.model.UserStocks
import com.alokomkar.network.response.UserStocksResponse
import javax.inject.Inject

interface IUserStocksMapper {
    fun mapToUserStocks(userStocksResponse: UserStocksResponse): List<UserStocks>
}

class UserStocksMapper @Inject constructor(): IUserStocksMapper {
    override fun mapToUserStocks(userStocksResponse: UserStocksResponse): List<UserStocks> = mutableListOf<UserStocks>().apply {
        for(stockDetails in userStocksResponse.userStockDetails) {
            val currentValue = stockDetails.ltp * stockDetails.quantity
            val investmentValue = stockDetails.avgPrice.toDouble() - stockDetails.quantity
            add(
                UserStocks(
                    symbol = stockDetails.symbol,
                    quantity = stockDetails.quantity,
                    ltp = stockDetails.ltp,
                    profitNLoss = currentValue - investmentValue,
                    currentValue = currentValue,
                    investmentValue = investmentValue,
                    todayPnL = (stockDetails.close - stockDetails.ltp) * stockDetails.quantity
                )
            )
        }
    }
}