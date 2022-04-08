package com.alokomkar.network.api

import com.alokomkar.network.response.UserStocksResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserStocksAPI {

    @GET("6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getUserStocks(): Response<UserStocksResponse>
}