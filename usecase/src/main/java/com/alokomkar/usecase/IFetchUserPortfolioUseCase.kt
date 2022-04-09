package com.alokomkar.usecase

import com.alokomkar.core.DispatcherProvider
import com.alokomkar.core.Result
import com.alokomkar.repository.IUserStocksRepository
import com.alokomkar.usecase.output.UserPortfolioDTO
import com.alokomkar.usecase.output.UserPortfolioSummaryDTO
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface IFetchUserPortfolioUseCase {
    suspend fun execute(): Flow<Result<UserPortfolioDTO>>
}

@DelicateCoroutinesApi
class FetchUserPortfolioUseCase @Inject constructor(
    private val repository: IUserStocksRepository,
    private val dispatcherProvider: DispatcherProvider
) : IFetchUserPortfolioUseCase {

    override suspend fun execute(): Flow<Result<UserPortfolioDTO>> = flow {
        emit(Result.Success(UserPortfolioDTO.emptyObject))
        GlobalScope.launch(dispatcherProvider.io) {
            repository.refreshUserStocks()
            repository.fetchUserStocks().collectLatest { result ->
                when(result) {
                    is Result.Success ->  {
                        emit(Result.Success(UserPortfolioDTO(
                            result.data,
                            UserPortfolioSummaryDTO(
                                currentValue = 0.0,
                                totalInvestment = 0.0,
                                todayProfitAndLoss = 0.0,
                                overallProfitAndLoss = 0.0
                            )
                        )))
                    }
                    is Result.Error -> {
                        emit(Result.Error(result.exception))
                    }
                }
            }
        }
    }

}