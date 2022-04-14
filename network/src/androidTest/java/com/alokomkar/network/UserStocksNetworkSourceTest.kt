package com.alokomkar.network

import com.alokomkar.network.api.UserStocksAPI
import com.alokomkar.network.base.BaseNetworkSourceTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UserStocksNetworkSourceTest: BaseNetworkSourceTest<UserStocksAPI>(UserStocksAPI::class) {

    @Test
    fun requestGetUserStocks() = testCoroutineRule.runTest {
        enqueueResponse("raw/get_client_stock_list_response.json")
        serviceMock.getUserStocks()
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("6d0ad460-f600-47a7-b973-4a779ebbaeaf", request.path)
    }

}