package com.alokomkar.stocklist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.alokomkar.stocklist.databinding.ActivityMainBinding
import com.alokomkar.stocklist.ui.adapter.UserStocksAdapter
import com.alokomkar.stocklist.ui.adapter.UserStocksDiffUtilCallback
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserStocksViewModel by viewModels()
    private val adapter by lazy { UserStocksAdapter(UserStocksDiffUtilCallback) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            viewModel.uiState.observe(this@MainActivity) { state ->
                when(state) {
                    is UiState -> {
                        adapter.submitList(state.userPortfolio.userStocks)
                        tvTotalPnl.text = DecimalFormat.getInstance().format(state.userPortfolio.userPortfolioSummary.totalProfitAndLoss)
                        btnRefresh.visibility = View.GONE
                    }
                    is ErrorState -> {
                        Log.e(UserStockSummaryDialog.TAG, state.error?.localizedMessage ?: "Unknown error")
                        state.error?.printStackTrace()
                        Toast.makeText(this@MainActivity, state.error?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                        btnRefresh.visibility = View.VISIBLE
                    }
                }
            }
            btnRefresh.setOnClickListener { viewModel.fetchUserPortfolio() }
            summaryLayout.setOnClickListener {
                showUserStockSummary()
            }
            rvUserStocks.adapter = adapter
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