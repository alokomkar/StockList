package com.alokomkar.stocklist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alokomkar.local.model.UserStocks

object UserStocksDiffUtilCallback : DiffUtil.ItemCallback<UserStocks>()  {
    override fun areItemsTheSame(oldItem: UserStocks, newItem: UserStocks): Boolean
        = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UserStocks, newItem: UserStocks): Boolean
        = oldItem == newItem
}