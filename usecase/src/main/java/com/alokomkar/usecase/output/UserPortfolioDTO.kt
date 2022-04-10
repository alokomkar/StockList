package com.alokomkar.usecase.output

import com.alokomkar.local.model.UserStocks

data class UserPortfolioDTO(
    val userStocks: List<UserStocks>,
    val userPortfolioSummary: UserPortfolioSummaryDTO
) {
    companion object {
        val emptyObject = UserPortfolioDTO(
            emptyList(),
            UserPortfolioSummaryDTO.emptyObject
        )
    }
}

data class UserPortfolioSummaryDTO(
    val currentValue: Double,
    val totalInvestment: Double,
    val todayProfitAndLoss: Double,
    val totalProfitAndLoss: Double
) {
    companion object {
        val emptyObject = UserPortfolioSummaryDTO(
            currentValue = 0.0,
            totalInvestment = 0.0,
            todayProfitAndLoss = 0.0,
            totalProfitAndLoss = 0.0
        )
    }
}