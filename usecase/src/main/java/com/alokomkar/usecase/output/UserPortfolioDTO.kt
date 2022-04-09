package com.alokomkar.usecase.output

import com.alokomkar.local.model.UserStocks

data class UserPortfolioDTO(
    val userStocks: List<UserStocks>,
    val userPortfolioSummary: UserPortfolioSummaryDTO
) {
    companion object {
        val emptyObject = UserPortfolioDTO(
            emptyList(),
            UserPortfolioSummaryDTO(
                currentValue = 0.0,
                totalInvestment = 0.0,
                todayProfitAndLoss = 0.0,
                overallProfitAndLoss = 0.0
            )
        )
    }
}

data class UserPortfolioSummaryDTO(
    val currentValue: Double,
    val totalInvestment: Double,
    val todayProfitAndLoss: Double,
    val overallProfitAndLoss: Double
)