package com.alokomkar.stocklist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alokomkar.core.Result
import com.alokomkar.usecase.IFetchUserPortfolioUseCase
import com.alokomkar.usecase.output.UserPortfolioDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserStocksViewModel @Inject constructor(
    private val userPortfolioUseCase: IFetchUserPortfolioUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UserStocksState>()
    val uiState: LiveData<UserStocksState> = _uiState

    fun fetchUserPortfolio() {
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch {
            try {
                userPortfolioUseCase.execute().collect {
                    when(it) {
                        is Result.Success -> {
                            _uiState.postValue(
                                UiState(
                                    isLoading = false,
                                    userPortfolio = it.data
                                )
                            )
                        }
                        is Result.Error -> {
                            _uiState.postValue(
                                UiState(
                                    isLoading = false,
                                    userPortfolio = UserPortfolioDTO.emptyObject
                                )
                            )
                            _uiState.postValue(
                                ErrorState(
                                    it.exception
                                )
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.postValue(ErrorState(e))
            }
        }
    }

}