package com.alokomkar.network

import com.alokomkar.core.DispatcherProvider
import com.alokomkar.core.Result
import com.alokomkar.network.api.UserStocksAPI
import com.alokomkar.network.base.toUpdateResponse
import com.alokomkar.network.response.UserStocksResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IUserStocksNetworkSource {
    suspend fun getUserStocks(): Result<UserStocksResponse>
}

class UserStocksNetworkSource @Inject constructor(
    private val api: UserStocksAPI,
    private val dispatcherProvider: DispatcherProvider
): IUserStocksNetworkSource {

    override suspend fun getUserStocks(): Result<UserStocksResponse> = withContext(dispatcherProvider.io) {
        api.getUserStocks().toUpdateResponse {
            "Unable to retrieve user stocks"
        }
    }

}