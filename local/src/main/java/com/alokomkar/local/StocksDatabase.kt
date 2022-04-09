package com.alokomkar.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alokomkar.local.dao.IUserStocksDao
import com.alokomkar.local.model.UserStocks

@Database(entities = [UserStocks::class], version = 1, exportSchema = false)
abstract class StocksDatabase : RoomDatabase() {
    abstract fun userStocksDao(): IUserStocksDao
}