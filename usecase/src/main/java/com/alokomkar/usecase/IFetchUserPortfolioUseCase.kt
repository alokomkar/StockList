package com.alokomkar.usecase

import com.alokomkar.core.DispatcherProvider
import com.alokomkar.core.Result
import com.alokomkar.repository.IUserStocksRepository
import com.alokomkar.usecase.output.UserPortfolioDTO
import com.alokomkar.usecase.output.UserPortfolioSummaryDTO
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

interface IFetchUserPortfolioUseCase {
    fun execute(): Flow<Result<UserPortfolioDTO>>
}

@DelicateCoroutinesApi
class FetchUserPortfolioUseCase @Inject constructor(
    private val repository: IUserStocksRepository,
    private val dispatcherProvider: DispatcherProvider
) : IFetchUserPortfolioUseCase {

    //Use channel flow for concurrent emissions
    override fun execute(): Flow<Result<UserPortfolioDTO>> = channelFlow {
        send(Result.Success(UserPortfolioDTO.emptyObject))
        repository.refreshUserStocks()
        repository.fetchUserStocks().collectLatest { result ->
            when(result) {
                is Result.Success ->  {
                    var totalCurrentValue = 0.0
                    var totalInvestmentValue = 0.0
                    var todayPnL = 0.0
                    var totalPnL = 0.0
                    for(stockDetails in result.data) {
                        totalCurrentValue += stockDetails.currentValue
                        totalInvestmentValue += stockDetails.investmentValue
                        totalPnL += totalCurrentValue - totalInvestmentValue
                        todayPnL += stockDetails.todayPnL
                    }
                    send(Result.Success(UserPortfolioDTO(
                        result.data,
                        UserPortfolioSummaryDTO(
                            currentValue = totalCurrentValue,
                            totalInvestment = totalInvestmentValue,
                            todayProfitAndLoss = todayPnL,
                            totalProfitAndLoss = totalPnL,
                        )
                    )))
                }
                is Result.Error -> {
                    send(Result.Error(result.exception))
                }
            }
        }
    }

}