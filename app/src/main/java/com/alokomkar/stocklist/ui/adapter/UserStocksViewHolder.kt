package com.alokomkar.stocklist.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alokomkar.local.model.UserStocks
import com.alokomkar.stocklist.databinding.ItemStockListBinding
import java.text.DecimalFormat

class UserStocksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding: ItemStockListBinding = ItemStockListBinding.bind(itemView)

    fun bindData(item: UserStocks) {
        binding.apply {
            tvSymbol.text = item.symbol
            tvQuantity.text = item.quantity.toString()
            tvLtpValue.text = DecimalFormat.getInstance().format(item.ltp)
            tvPnlValue.text = DecimalFormat.getInstance().format(item.profitNLoss)
        }
    }

}