package com.alokomkar.stocklist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.alokomkar.stocklist.R

class MainActivity : AppCompatActivity() {

    private val viewModel: UserStocksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.uiState.observe(this) { state ->
            when(state) {
                is UiState -> {

                }
                is ErrorState -> {

                }
            }
        }
    }

    private fun showUserStockSummary() {
        UserStockSummaryDialog().show(supportFragmentManager, UserStockSummaryDialog.TAG)
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchUserPortfolio()
    }
}