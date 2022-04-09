package com.alokomkar.stocklist.ui

import com.alokomkar.usecase.output.UserPortfolioDTO

sealed class UserStocksState

data class UiState(
    val isLoading: Boolean = false,
    val userPortfolio: UserPortfolioDTO = UserPortfolioDTO.emptyObject
): UserStocksState()

data class ErrorState(val error: Throwable? = null): UserStocksState()