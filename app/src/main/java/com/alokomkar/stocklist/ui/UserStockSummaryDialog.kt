package com.alokomkar.stocklist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.alokomkar.stocklist.databinding.DialogUserStockSummaryBinding
import com.alokomkar.usecase.output.UserPortfolioSummaryDTO
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DecimalFormat

class UserStockSummaryDialog: BottomSheetDialogFragment() {

    private val viewModel: UserStocksViewModel by activityViewModels()
    private lateinit var dialogUserStockSummaryBinding: DialogUserStockSummaryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialogUserStockSummaryBinding = DialogUserStockSummaryBinding.inflate(inflater, container, false)
        return dialogUserStockSummaryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(this) {
            when(it) {
                is UiState -> {
                    bindUserPortfolioSummary(it.userPortfolio.userPortfolioSummary)
                }
                is ErrorState -> {
                    Log.e(TAG, it.error?.localizedMessage ?: "Unknown error")
                    it.error?.printStackTrace()
                }
            }
        }

    }

    private fun bindUserPortfolioSummary(userPortfolioSummary: UserPortfolioSummaryDTO) {
        dialogUserStockSummaryBinding.apply {
            tvCurrentValue.text = DecimalFormat.getInstance().format(userPortfolioSummary.currentValue)
            tvTotalInvestment.text = DecimalFormat.getInstance().format(userPortfolioSummary.totalInvestment)
            tvTodayPNL.text = DecimalFormat.getInstance().format(userPortfolioSummary.todayProfitAndLoss)
            tvTotalPnl.text = DecimalFormat.getInstance().format(userPortfolioSummary.totalProfitAndLoss)
        }
    }

    companion object {
        const val TAG = "UserStockSummaryDialog"
    }

}