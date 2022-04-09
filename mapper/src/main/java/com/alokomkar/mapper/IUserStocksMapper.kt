package com.alokomkar.mapper

import com.alokomkar.local.model.UserStocks
import com.alokomkar.network.response.UserStocksResponse
import javax.inject.Inject

interface IUserStocksMapper {
    fun mapToUserStocks(userStocksResponse: UserStocksResponse): List<UserStocks>
}

class UserStocksMapper @Inject constructor(): IUserStocksMapper {
    override fun mapToUserStocks(userStocksResponse: UserStocksResponse): List<UserStocks> = mutableListOf<UserStocks>().apply {
        add(UserStocks(userStocksResponse.id))
    }
}