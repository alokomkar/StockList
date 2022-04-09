package com.alokomkar.stocklist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alokomkar.stocklist.R
import com.alokomkar.local.model.UserStocks

class UserStocksAdapter(diffUtilCallback: UserStocksDiffUtilCallback): ListAdapter<UserStocks, UserStocksViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserStocksViewHolder
        = UserStocksViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_stock_list, parent, false)
        )

    override fun onBindViewHolder(holder: UserStocksViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

}