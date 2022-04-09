package com.alokomkar.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stocks")
data class UserStocks(
    @PrimaryKey
    val id: String
)