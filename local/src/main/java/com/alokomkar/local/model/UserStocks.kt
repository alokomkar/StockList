package com.alokomkar.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stocks")
data class UserStocks(
    @PrimaryKey
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val profitNLoss: Double
)