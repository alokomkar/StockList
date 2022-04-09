package com.alokomkar.stocklist.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alokomkar.stocklist.databinding.ItemCurrencyRateBinding
import com.alokomkar.local.model.CurrencyRate
import com.alokomkar.local.model.UserStocks
import java.text.DecimalFormat

class UserStocksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding: ItemCurrencyRateBinding = ItemCurrencyRateBinding.bind(itemView)

    fun bindData(item: UserStocks) {
        binding.apply {
            tvCurrencyKey.text = item.currency
            tvCurrencyValue.text = DecimalFormat.getInstance().format(item.rate)
        }
    }

}